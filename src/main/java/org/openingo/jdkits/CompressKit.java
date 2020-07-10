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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.*;

/**
 * Compress Kit
 *
 * @author Qicz
 */
public final class CompressKit {

    private CompressKit(){}

    /**
     * zip文件压缩
     * @param inputFile 待压缩文件夹/文件名
     * @param outputFile 生成的压缩包名字
     */
    public static void zipFile(String inputFile, String outputFile) throws Exception {
        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
        // 最大限度的压缩
        out.setLevel(Deflater.BEST_COMPRESSION);
        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        File input = new File(inputFile);
        zipFile(out, bos, input,null);
        bos.close();
        out.closeEntry();
        out.close();
    }

    /**
     * @param name 压缩文件名，可以写为null保持默认
     */
    private static void zipFile(ZipOutputStream out, BufferedOutputStream bos, File input, String name) throws IOException {
        if (name == null) {
            name = input.getName();
        }

        if (input.isDirectory()) { // 如果路径为目录（文件夹）
            // 取出文件夹中的文件（或子文件夹）
            File[] flist = input.listFiles();

            if (flist.length == 0) { // 如果文件夹为空，则只需在目的地zip文件中写入一个目录进入
                out.putNextEntry(new ZipEntry(name + "/"));
            } else { // 如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
                for (int i = 0; i < flist.length; i++) {
                    zipFile(out, bos, flist[i], name + "/" + flist[i].getName());
                }
            }
        } else { // 如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
            out.putNextEntry(new ZipEntry(name));
            FileInputStream fos = new FileInputStream(input);
            BufferedInputStream bis = new BufferedInputStream(fos);
            int len = -1;
            // 将源文件写入到zip文件中
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf,0,len);
            }
            bis.close();
            fos.close();
        }
    }

    /**
     * zip解压
     * @param inputFile 待解压文件名
     * @param destDirPath  解压路径
     */
    public static void unZipFile(String inputFile, String destDirPath) throws Exception {
        File srcFile = new File(inputFile);//获取当前压缩文件
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "所指文件不存在");
        }
        //开始解压
        //构建解压输入流
        ZipInputStream zIn = new ZipInputStream(new FileInputStream(srcFile));
        ZipEntry entry = null;
        File file = null;
        while ((entry = zIn.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                file = new File(destDirPath, entry.getName());
                if (!file.exists()) {
                    new File(file.getParent()).mkdirs();//创建此文件的上级目录
                }
                OutputStream out = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = zIn.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                bos.close();
                out.close();
            }
        }
    }

    /**
     *
     * @param compressSource
     * @param GZIPFormat
     */
    public static byte[] compress(byte[] compressSource, boolean GZIPFormat) {
        Deflater compressor = new Deflater(Deflater.BEST_COMPRESSION, GZIPFormat);
        compressor.setInput(compressSource);
        compressor.finish();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] readBuffer = new byte[1024];
        int readCount = 0;
        while (!compressor.finished()) {
            readCount = compressor.deflate(readBuffer);
            if (readCount > 0) {
                bao.write(readBuffer, 0, readCount);
            }
        }
        compressor.end();
        return bao.toByteArray();
    }

    /**
     * 解压缩
     * @param decompressSource
     * @param GZIPFormat
     */
    public static byte[] decompress(byte[] decompressSource, boolean GZIPFormat) throws Exception {
        Inflater decompressor = new Inflater(GZIPFormat);
        decompressor.setInput(decompressSource);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] readBuffer = new byte[1024];
        int readCount = 0;
        while (!decompressor.finished()) {
            readCount = decompressor.inflate(readBuffer);
            if (readCount > 0) {
                bao.write(readBuffer, 0, readCount);
            }
        }
        decompressor.end();
        return bao.toByteArray();
    }

    /**
     * 使用GZIP压缩
     * @param compressSource
     * @return 压缩后的byte[]
     */
    public static byte[] compress(String compressSource) {
        return compress(compressSource.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 使用GZIP压缩
     * @param compressSource
     * @return 压缩后的byte[]
     */
    public static byte[] compress(byte[] compressSource) {
        return compress(compressSource, true);
    }

    /**
     * 使用密码压缩
     * @param compressSource
     * @param password
     * @return 压缩后的byte[]
     */
    public static byte[] compress(String compressSource, String password) {
        return compress(compressSource.getBytes(StandardCharsets.UTF_8), password);
    }

    /**
     * 使用密码压缩
     * @param compressSource
     * @param password
     * @return 压缩后的byte[]
     */
    public static byte[] compress(byte[] compressSource, String password) {
        return AesKit.encrypt(compress(compressSource, true), HashKit.md5(password));
    }

    /**
     * 解压缩GZIP
     * @param decompressSource
     * @return 解压之后的byte[]
     * @throws Exception
     */
    public static byte[] decompress(byte[] decompressSource) throws Exception {
        return decompress(decompressSource, true);
    }

    /**
     * 使用密码解压GZIP的内容
     * @param decompressSource
     * @param password
     * @return 解压之后的byte[]
     * @throws Exception
     */
    public static byte[] decompress(byte[] decompressSource, String password) throws Exception {
        return decompress(AesKit.decrypt(decompressSource, HashKit.md5(password)), true);
    }
}
