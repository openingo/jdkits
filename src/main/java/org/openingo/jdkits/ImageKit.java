/*
 * MIT License
 *
 * Copyright (c) 2020 OpeningO Co.,Ltd.
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

package org.openingo.jdkits;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片工具 ImageKit
 *
 * @author Qicz
 */
public final class ImageKit {

    /**
     * 生成形如data:image/jpeg;base64,iVBORw0KGgoA……的字符串，将图片文件Data URI化
     *
     * @param imageFilePath 图片文件路径
     * @return 形如data:image/jpeg;base64,iVBORw0KGgoA……的字符串
     * @throws IOException
     */
    public static String encodeDataUri(String imageFilePath) throws IOException {
        return encodeDataUri(new File(imageFilePath));
    }

    /**
     * 生成形如data:image/jpeg;base64,iVBORw0KGgoA……的字符串，将图片文件Data URI化
     *
     * @param imageFile 图片文件对象
     * @return 形如data:image/jpeg;base64,iVBORw0KGgoA……的字符串
     * @throws IOException
     */
    public static String encodeDataUri(File imageFile) throws IOException {
        String type = FileKit.getFileExtension(imageFile).toLowerCase();
        if("jpg".equals(type)) {
            type = "jpeg";
        }
        return "data:image/"+type+";base64," + encodeBase64(imageFile);
    }

    /**
     * 将文件编码成base64格式
     *
     * @param imageFilePath 图片文件路径
     * @return base64编码格式的字符串
     * @throws IOException
     */
    public static String encodeBase64(String imageFilePath) throws IOException {
        return encodeBase64(new File(imageFilePath));
    }

    /**
     * 将文件编码成base64格式
     *
     * @param imageFile 图片文件对象
     * @return base64编码格式的字符串
     * @throws IOException
     */
    public static String encodeBase64(File imageFile) throws IOException {
        BufferedImage bi = ImageIO.read(imageFile);
        String type = FileKit.getFileExtension(imageFile).toLowerCase();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bi,type, byteArrayOutputStream);
        return Base64Kit.encode(byteArrayOutputStream.toByteArray());
    }

    /**
     * 将base64格式编码成文件
     *
     * @param imageBase64String 已经Base64的图片文件String
     * @param fileName 文件存储的目录及文件名
     * @return image file
     * @throws IOException
     */
    public static File decodeBase64(String imageBase64String, String fileName) throws IOException{
        byte[] decode = Base64Kit.decode(imageBase64String);
        File imageFile = new File(fileName);
        String type = FileKit.getFileExtension(imageFile).toLowerCase();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decode);
        BufferedImage bi = ImageIO.read(byteArrayInputStream);
        if (!ImageIO.write(bi, type, imageFile)) {
            return null;
        }
        return imageFile;
    }
}
