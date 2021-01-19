/*
 * MIT License
 *
 * Copyright (c) 2021 OpeningO Co.,Ltd.
 *
 *    https://openingo.org
 *    contactus(at)openingo.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.openingo.jdkits.file;

import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.sl.extractor.SlideShowExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openingo.jdkits.validate.ValidateKit;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Office 工具集
 *
 * @author Qicz
 */
public final class OfficeKit {

    public static String readDoc(InputStream in) throws IOException {
        return readDoc(in, true);
    }

    public static String readDoc(InputStream in, boolean replaceR) throws IOException {
        String content = "";
        HWPFDocument doc = new HWPFDocument(in);
        if (ValidateKit.isNotNull(doc)
                && ValidateKit.isNotEmpty(content = doc.getText().toString().trim())
                && replaceR) {
            content = content.replaceAll("\r", "\n");
        }
        return content;
    }

    public static String readDocX(InputStream in) throws Exception {
        XWPFWordExtractor extractor = new XWPFWordExtractor(new XWPFDocument(in));
        return extractor.getText();
    }

    public static String readPowerPoint(InputStream in) throws IOException {
        StringBuilder content = new StringBuilder();
        HSLFSlideShow hslfSlideShow = new HSLFSlideShow(in);
        List<HSLFSlide> slides = hslfSlideShow.getSlides();
        SlideShowExtractor<HSLFShape, HSLFTextParagraph> slideShowExtractor = new SlideShowExtractor<>(hslfSlideShow);

        for (HSLFSlide slide : slides) {
            content.append(slideShowExtractor.getText(slide));
        }
        slideShowExtractor.close();
        return content.toString();
    }

    public static String readPowerPointX(InputStream in) throws IOException {
        StringBuilder content = new StringBuilder();
        XMLSlideShow xmlSlideShow = new XMLSlideShow(in);
        List<XSLFSlide> slides = xmlSlideShow.getSlides();
        for (XSLFSlide slide : slides) {
            CTSlide rawSlide = slide.getXmlObject();
            CTGroupShape spTree = rawSlide.getCSld().getSpTree();
            List<CTShape> spList = spTree.getSpList();
            for (CTShape shape : spList) {
                CTTextBody txBody = shape.getTxBody();
                if (null == txBody) {
                    continue;
                }
                List<CTTextParagraph> pList = txBody.getPList();
                for (CTTextParagraph textParagraph : pList) {
                    List<CTRegularTextRun> textRuns = textParagraph.getRList();
                    for (CTRegularTextRun textRun : textRuns) {
                        content.append(textRun.getT());
                    }
                }
            }
        }
        xmlSlideShow.close();
        return content.toString();
    }

    public static String readXls(InputStream in) throws IOException {
        StringBuilder content = new StringBuilder();
        HSSFWorkbook excel = new HSSFWorkbook(in);
        //获取第一个sheet
        HSSFSheet sheet0 = excel.getSheetAt(0);
        for (Row cells : sheet0) {
            HSSFRow row = (HSSFRow) cells;
            for (Iterator<Cell> iterator = row.cellIterator(); iterator.hasNext(); ) {
                HSSFCell cell = (HSSFCell) iterator.next();
                //根据单元的的类型 读取相应的结果
                if (cell.getCellType() == CellType.STRING) {
                    content.append(cell.getStringCellValue()).append("\t");
                } else if (cell.getCellType() == CellType.NUMERIC
                        || cell.getCellType() == CellType.FORMULA) {
                    content.append(cell.getNumericCellValue()).append("\t");
                } else {
                    content.append("" + "\t");
                }
            }
        }
        return content.toString();
    }

    public static String readXlsx(InputStream in) throws IOException {
        StringBuilder content = new StringBuilder();
        XSSFWorkbook excel = new XSSFWorkbook(in);
        //获取第一个sheet
        XSSFSheet sheet0 = excel.getSheetAt(0);
        for (Row cells : sheet0) {
            XSSFRow row = (XSSFRow) cells;
            for (Iterator<Cell> iterator = row.cellIterator(); iterator.hasNext(); ) {
                XSSFCell cell = (XSSFCell) iterator.next();
                //根据单元格的类型 读取相应的结果
                if (cell.getCellType() == CellType.STRING) {
                    content.append(cell.getStringCellValue()).append("\t");
                } else if (cell.getCellType() == CellType.NUMERIC
                        || cell.getCellType() == CellType.FORMULA) {
                    content.append(cell.getNumericCellValue()).append("\t");
                } else {
                    content.append("" + "\t");
                }
            }
        }
        return content.toString();
    }
}
