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

/**
 * 异常工具 ExceptionKit
 *
 * @author Qicz
 */
public final class ExceptionKit {

    private ExceptionKit(){}

    /**
     * 抛出IllegalArgumentException
     * @param message
     */
    public static void throwIllegalArgumentException(String message) {
        throw new IllegalArgumentException(message);
    }

    /**
     * 抛出BizException
     * @param format
     * @param args
     */
    public static void throwIllegalArgumentException(String format, Object... args) {
        throw new IllegalArgumentException(String.format(format, args));
    }

    /**
     * 抛出 NullPointerException
     * @param message
     */
    public static void throwNullPointerException(String message) {
        throw new NullPointerException(message);
    }

    /**
     * 抛出 NullPointerException
     * @param format
     * @param args
     */
    public static void throwNullPointerException(String format, Object... args) {
        throw new NullPointerException(String.format(format, args));
    }

    /**
     * 抛出 ClassCastException
     * @param message
     */
    public static void throwClassCastException(String message) {
        throw new ClassCastException(message);
    }

    /**
     * 抛出 RuntimeException
     * @param message
     */
    public static void throwRuntimeException(String message) {
        throw new RuntimeException(message);
    }
}
