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

package org.openingo.jdkits.file;

import org.openingo.jdkits.image.ImageKit;
import org.openingo.jdkits.lang.StrKit;
import org.openingo.jdkits.validate.ValidateKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.StringTokenizer;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;


/**
 * 文件工具 FileKit
 *
 * @author Qicz
 */
public final class FileKit {

    private static Logger log = LoggerFactory.getLogger(ImageKit.class);

    private FileKit(){}

    public static void delete(File file) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
            }
            else if (file.isDirectory()) {
                File files[] = file.listFiles();
                if (files != null) {
                    for (int i=0; i<files.length; i++) {
                        delete(files[i]);
                    }
                }
                file.delete();
            }
        }
    }

    public static String getFileExtension(String fileFullName) {
        if (StrKit.isBlank(fileFullName)) {
            throw new RuntimeException("fileFullName is empty");
        }
        return  getFileExtension(new File(fileFullName));
    }

    public static String getFileExtension(File file) {
        if (null == file) {
            throw new NullPointerException();
        }
        String fileName = file.getName();
        int dotIdx = fileName.lastIndexOf('.');
        return (dotIdx == -1) ? "" : fileName.substring(dotIdx + 1);
    }

    private static String getFileCRCCode(InputStream inputStream) {
        CRC32 crc32 = new CRC32();
        byte[] buffer = null;
        try {
            CheckedInputStream checkedinputstream = new CheckedInputStream(inputStream, crc32);
            buffer = new byte[1024];
            while (checkedinputstream.read(buffer) != -1) {
            }
            inputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Long.toHexString(crc32.getValue());
    }

    /**
     * 获得文件的CRC32校验和
     *
     * @param file 要进行校验的文件
     * @return
     * @throws Exception
     */
    public static String getFileCRCCode(File file) throws Exception {
        FileInputStream inputStream = new FileInputStream(file);
        return getFileCRCCode(inputStream);
    }

    /**
     * 获得字串的CRC32校验和
     *
     * @param string 要进行校验的字串
     * @return
     * @throws Exception
     */
    public static String getStringCRCCode(String string) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(string.getBytes());
        return getFileCRCCode(inputStream);
    }

    /**
     * 判断文件和目录是否已存在
     *
     * @param filePath 文件和目录完整路径
     * @return true:存在 false：不存在
     */
    public static boolean exists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 判断特定的路径是否为文件
     *
     * @param filePath 文件完整的路径
     * @return 若是文件，则返回true，否则返回false
     */
    public static boolean isFile(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    /**
     * 判断特定的路径是否为目录
     *
     * @param filePath 文件完整的路径
     * @return 若是目录，则返回true，否则返回false
     */
    public static boolean isDirectory(String filePath) {
        File file = new File(filePath);
        return file.isDirectory();
    }

    /**
     * 更改文件的名称，若不在同一个目录下,则系统会移动文件
     *
     * @param srcFile  源文件路径名称
     * @param targetFile 目的文件路径名称
     * @return true 成功，false失败
     */
    public static boolean renameTo(String srcFile, String targetFile) {
        File file = new File(srcFile);
        return file.renameTo(new File(targetFile));
    }

    /**
     * 创建多级目录
     *
     * @param path 目录的绝对路径
     */
    public static void createDirectories(String path) {
        try {
            StringTokenizer st = new StringTokenizer(path, "/");
            String path1 = st.nextToken() + "/";
            String path2 = path1;
            while (st.hasMoreTokens()) {
                path1 = st.nextToken() + "/";
                path2 += path1;
                File inbox = new File(path2);
                if (!inbox.exists()) {
                    inbox.mkdir();
                }

            }
        } catch (Exception e) {
            System.out.println("目录创建失败" + e);
            e.printStackTrace();
        }
    }

    /**
     * 删除文件/目录(递归删除文件/目录)
     *
     * @param dirPath 文件或文件夹的绝对路径
     */
    public static boolean deleteDirectory(String dirPath) {
        if (ValidateKit.isNotNull(dirPath)) {
            return false;
        }
        File path = new File(dirPath);
        if (!path.exists()) {
            return false;
        }
        // 如果是文件删除
        if (path.isFile()) {
            return path.delete();
        }

        // 如果目录中有文件递归删除文件
        File[] files = path.listFiles();
        for (File file : files) {
            deleteDirectory(file.getAbsolutePath());
        }
        return path.delete();
    }

    /**
     * 文件/目录 重命名
     *
     * @param srcPath 原有路径（绝对路径）
     * @param targetPath 更新路径
     * @author lyf 注：不能修改上层次的目录
     */
    public static boolean renameDirectory(String srcPath, String targetPath) {
        File srcFile = new File(srcPath);// 文件或目录
        File targetFile = new File(targetPath);// 文件或目录
        return srcFile.renameTo(targetFile);
    }

    /**
     * 新建目录
     */
    public static boolean makeDirectory(String path) throws Exception {
        File file = new File(path);
        return file.mkdirs();//创建目录
    }
}
