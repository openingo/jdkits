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

import java.net.*;
import java.util.Enumeration;

/**
 * IPKit
 *
 * @author Qicz
 */
public final class IPKit {

    private static final String OBTAIN_IP_ERROR = "获取IP信息失败";

    /**
     * 获取服务器IP地址
     */
    public static String getServerIp() {
        // 获取操作系统类型
        String sysType = SysKit.getOSType();
        String ip = OBTAIN_IP_ERROR;
        if (SysKit.WINDOWS_OS.equals(sysType)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        } else if (SysKit.LINUX_OS.equals(sysType)) {
            ip = getIpByEthId("eth0");
        } else if (SysKit.MACOS_OS.equals(sysType)) {
            ip = getIpByEthId("en0");
        }

        return ip;
    }

    private static String getIpByEthId(String ethId) {
        String ip = OBTAIN_IP_ERROR;
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ipAddress;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (ethId.equals(netInterface.getName())) {
                    Enumeration addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ipAddress = (InetAddress) addresses.nextElement();
                        if (ValidateKit.isNotNull(ipAddress)
                                && ipAddress instanceof Inet4Address) {
                            return ipAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
