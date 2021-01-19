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

package jdkit.demo;

import org.openingo.jdkits.file.OfficeKit;
import org.openingo.jdkits.file.PdfKit;

/**
 * OfficeKitDemo
 *
 * @author Qicz
 */
public class OfficeKitDemo {

    public static void main(String[] args) throws Exception {

        String content = OfficeKit.readDoc(Thread.currentThread().getContextClassLoader().getResourceAsStream("abc.doc"));
        System.out.println(content);
        content = OfficeKit.readDocX(Thread.currentThread().getContextClassLoader().getResourceAsStream("abc.docx"));
        System.out.println(content);
        content = OfficeKit.readPowerPoint(Thread.currentThread().getContextClassLoader().getResourceAsStream("abc.ppt"));
        System.out.println(content);

        content = OfficeKit.readPowerPointX(Thread.currentThread().getContextClassLoader().getResourceAsStream("abc.pptx"));
        System.out.println(content);
        content = PdfKit.readPdf(Thread.currentThread().getContextClassLoader().getResourceAsStream("abc.pdf"));
        System.out.println(content);
    }
}
