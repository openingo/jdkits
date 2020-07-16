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

package org.openingo.jdkits.image;

import org.openingo.jdkits.coding.Base64Kit;
import org.openingo.jdkits.file.FileKit;
import org.openingo.jdkits.validate.ValidateKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

/**
 * 图片工具 ImageKit
 *
 * @author Qicz
 */
public final class ImageKit {

    private static Logger log = LoggerFactory.getLogger(ImageKit.class);

    private static String DEFAULT_PREFIX = "thumb_";
    private static Boolean DEFAULT_FORCE = false;//建议该值为false

    private ImageKit(){}

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

    /**
     * 生成缩略图
     *
     * @param imagePath 图片路径
     * @param w         缩略图宽度
     * @param h         缩略图高度
     * @return 缩略图路径
     */
    public static String makeThumbnailImage(String imagePath, int w, int h) {
        return makeThumbnailImage(imagePath, w, h, DEFAULT_PREFIX, DEFAULT_FORCE);
    }

    /**
     * 生成缩略图
     *
     * @param imagePath 图片路径
     * @param w         缩略图宽度
     * @param h         缩略图高度
     * @param prefix   缩略图前缀
     * @param force     是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     * @return 缩略图路径
     */
    public static String makeThumbnailImage(String imagePath,
                                            int w,
                                            int h,
                                            String prefix,
                                            boolean force) {
        File imgFile = new File(imagePath);
        String newPath = "";
        if (!imgFile.exists()) {
            log.warn("the image is not exist.");
        }
        try {
            // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
            String types = Arrays.toString(ImageIO.getReaderFormatNames());
            String suffix = null;
            // 获取图片后缀
            if (imgFile.getName().contains(".")) {// 飒飒大
                suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
            }// 类型和图片后缀全部小写，然后判断后缀是否合法
            if (suffix == null || !types.toLowerCase().contains(suffix.toLowerCase())) {
                log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                return "";
            }
            log.debug("target image's size, width:{}, height:{}.", w, h);
            Image img = ImageIO.read(imgFile);
            if (!force) {
                // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                int width = img.getWidth(null);
                int height = img.getHeight(null);
                if ((width * 1.0) / w < (height * 1.0) / h) {
                    if (width > w) {
                        h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
                        log.debug("change image's height, width:{}, height:{}.", w, h);
                    }
                } else {
                    if (height > h) {
                        w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
                        log.debug("change image's width, width:{}, height:{}.", w, h);
                    }
                }
            }
            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();
            g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
            g.dispose();
            String p = imgFile.getPath();
            newPath = p.substring(0, p.lastIndexOf(File.separator)) + File.separator + prefix + imgFile.getName();
            // 将图片保存在原目录并加上前缀
            ImageIO.write(bi, suffix, new File(newPath));
        } catch (IOException e) {
            log.error("generate thumbnail image failed.", e);
        }
        return newPath;
    }


    /**
     * 指定图片宽度和高度和压缩比例对图片进行压缩
     *
     * @param srcImagePath     源图片地址
     * @param targetImagePath    目标图片地址
     * @param targetWidth  压缩后图片的宽度
     * @param targetHeight 压缩后图片的高度
     * @param rate       压缩的比例
     * @return 压缩后图片路径
     */
    public static String reduceImage(String srcImagePath,
                                     String targetImagePath,
                                     int targetWidth,
                                     int targetHeight,
                                     Float rate) throws Exception {
        String outFilePath = "";
        File srcImageFile = new File(srcImagePath);
        String fileName = srcImageFile.getName();
        // check file
        if (!srcImageFile.exists()) {
            throw new RuntimeException("file is not exists.");
        }
        String types = Arrays.toString(ImageIO.getReaderFormatNames());
        String suffix = null;
        // 获取图片后缀
        if (fileName.contains(".")) {
            suffix = srcImageFile.getName().substring(fileName.lastIndexOf(".") + 1);
        }// 类型和图片后缀全部小写，然后判断后缀是否合法
        if (suffix == null || !types.toLowerCase().contains(suffix.toLowerCase())) {
            log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
        }

        // 如果比例不为空则说明是按比例压缩
        if (rate != null && rate > 0) {
            //获得源图片的宽高存入数组中
            int height = getImageHeight(srcImageFile);
            int width = getImageWidth(srcImageFile);
            if (height == 0 || width == 0) {
                throw new IllegalArgumentException("输入参数错误");
            } else {
                //按比例缩放或扩大图片大小，将浮点型转为整型
                targetWidth = (int) (width * rate);
                targetHeight = (int) (height * rate);
            }
        }
        // 开始读取文件并进行压缩
        Image src = ImageIO.read(srcImageFile);

        // 构造一个类型为预定义图像类型之一的 BufferedImage
        BufferedImage tag = new BufferedImage((int) targetWidth, (int) targetHeight, BufferedImage.TYPE_INT_RGB);

        //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
        //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
        Graphics g = tag.getGraphics();
        g.drawImage(src.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH), 0, 0, null);
        g.dispose();

        if (!targetImagePath.endsWith(File.separator)) {
            targetImagePath += File.separator;
        }
        outFilePath = targetImagePath + "compress_" + fileName;
        ImageIO.write(tag, suffix, new File(outFilePath));

        return targetImagePath + "compress_" + fileName;
    }

    /**
     * 指定压缩比例对图片进行压缩
     *
     * @param srcImagePath  源图片地址
     * @param targetImagePath 目标图片地址
     * @param rate    压缩的比例
     * @return 压缩后的图片
     */
    public static String reduceImage(String srcImagePath,
                                     String targetImagePath,
                                     Float rate) throws Exception {
        return reduceImage(srcImagePath, targetImagePath, 0, 0, rate);
    }

    /**
     * 指定图片宽度和高度图片进行压缩
     *
     * @param srcImagePath     源图片地址
     * @param targetImagePath    目标图片地址
     * @param targetWidth  压缩后图片的宽度
     * @param targetHeight 压缩后图片的高度
     * @return 压缩后的图片地址
     */
    public static String reduceImage(String srcImagePath,
                                     String targetImagePath,
                                     int targetWidth,
                                     int targetHeight) throws Exception {
        return reduceImage(srcImagePath, targetImagePath, targetWidth, targetHeight, 0f);
    }

    private static BufferedImage getImageBufferedImage(FileInputStream imageFileInputStream) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(imageFileInputStream);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return bufferedImage;
    }

    private static BufferedImage getImageBufferedImage(File imageFile) {
        FileInputStream imageFileInputStream = null;
        try {
            imageFileInputStream = new FileInputStream(imageFile);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
        return getImageBufferedImage(imageFileInputStream);
    }

    /**
     * 获取图片宽度
     *
     * @param fileInputStream 图片文件 input stream
     * @return 图片宽度
     */
    public static int getImageWidth(FileInputStream fileInputStream) {
        BufferedImage bufferedImage = getImageBufferedImage(fileInputStream);
        int ret = -1;
        if (ValidateKit.isNotNull(bufferedImage)) {
            ret = bufferedImage.getWidth();
        }
        return ret;
    }


    /**
     * 获取图片宽度
     *
     * @param imageFile 图片文件
     * @return 图片宽度
     */
    public static int getImageWidth(File imageFile) {
        BufferedImage bufferedImage = getImageBufferedImage(imageFile);
        int ret = -1;
        if (ValidateKit.isNotNull(bufferedImage)) {
            ret = bufferedImage.getWidth();
        }
        return ret;
    }

    /**
     * 获取图片高度
     *
     * @param fileInputStream 图片文件inputstream
     * @return 图片高度
     */
    public static int getImageHeight(FileInputStream fileInputStream) {
        BufferedImage bufferedImage = getImageBufferedImage(fileInputStream);
        int ret = -1;
        if (ValidateKit.isNotNull(bufferedImage)) {
            ret = bufferedImage.getHeight();
        }
        return ret;
    }

    /**
     * 获取图片高度
     *
     * @param imageFile 图片文件
     * @return 图片高度
     */
    public static int getImageHeight(File imageFile) {
        BufferedImage bufferedImage = getImageBufferedImage(imageFile);
        int ret = -1;
        if (ValidateKit.isNotNull(bufferedImage)) {
            ret = bufferedImage.getHeight();
        }
        return ret;
    }
}
