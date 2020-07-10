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

import java.io.File;

/**
 * 文件工具 FileKit
 *
 * @author Qicz
 */
public final class FileKit {

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

}
