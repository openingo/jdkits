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

package org.openingo.jdkits.platform;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * PlatformKit
 *
 * @author Qicz
 */
public final class PlatformKit {

    private PlatformKit() {}

    private static OperatingSystem getOs(HttpServletRequest request) {
        return UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getOperatingSystem();
    }

    private static DeviceType getDeviceType(HttpServletRequest request) {
        return PlatformKit.getOs(request).getDeviceType();
    }

    public static boolean isWeb(HttpServletRequest request) {
        return PlatformKit.getDeviceType(request).equals(DeviceType.COMPUTER)
                || PlatformKit.getDeviceType(request).equals(DeviceType.UNKNOWN);
    }

    public static boolean isMobile(HttpServletRequest request) {
        return PlatformKit.getDeviceType(request).equals(DeviceType.MOBILE);
    }

    public static boolean isAndroid(HttpServletRequest request) {
        return OperatingSystem.ANDROID.equals(PlatformKit.getOs(request));
    }

    public static boolean isIOS(HttpServletRequest request) {
        return OperatingSystem.IOS.equals(PlatformKit.getOs(request));
    }
}
