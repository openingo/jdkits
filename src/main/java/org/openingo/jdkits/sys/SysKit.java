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

package org.openingo.jdkits.sys;

/**
 * SysKit
 *
 * @author Qicz
 */
public final class SysKit {

    private SysKit(){}

    /**
     * Windows 系统
     */
    public static final String WINDOWS_OS = "windows";

    /**
     * Linux 系统
     */
    public static final String LINUX_OS = "linux";

    /**
     * Mac OS 系统
     */
    public static final String MACOS_OS = "macOS";

    /**
     * 未知系统
     */
    public static final String UNKNOWN_OS = "unknown";

    /**
     * 获取操作系统类型
     */
    public static String getOSType() {
        String osType = System.getProperties().getProperty("os.name").toLowerCase();
        if (osType.startsWith("win")) {
            osType = WINDOWS_OS;
        } else if(osType.startsWith("linux")) {
            osType = LINUX_OS;
        } else if (osType.startsWith("mac")) {
            osType = MACOS_OS;
        } else {
            osType = UNKNOWN_OS;
        }
        return osType;
    }
}
