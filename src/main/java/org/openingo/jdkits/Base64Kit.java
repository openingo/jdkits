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

import java.nio.charset.Charset;

/**
 * Base64工具
 *
 * @author Qicz
 */
public final class Base64Kit {

    public static final Charset UTF_8 = Charset.forName("UTF-8");
    private static IBase64 delegate;

    static {
        if (isPresent("java.util.Base64", Base64Kit.class.getClassLoader())) {
            delegate = new Java8Base64();
        } else {
            delegate = new Java67Base64();
        }
    }

    /**
     * 编码
     *
     * @param value
     *            byte数组
     * @return {String}
     */
    public static String encode(byte[] value) {
        return delegate.encode(value);
    }

    /**
     * 编码
     *
     * @param value
     *            字符串
     * @return {String}
     */
    public static String encode(String value) {
        byte[] val = value.getBytes(UTF_8);
        return delegate.encode(val);
    }

    /**
     * 编码
     *
     * @param value
     *            字符串
     * @param charsetName
     *            charSet
     * @return {String}
     */
    public static String encode(String value, String charsetName) {
        byte[] val = value.getBytes(Charset.forName(charsetName));
        return delegate.encode(val);
    }

    /**
     * 解码
     *
     * @param value
     *            字符串
     * @return {byte[]}
     */
    public static byte[] decode(String value) {
        return delegate.decode(value);
    }

    /**
     * 解码
     *
     * @param value
     *            字符串
     * @return {String}
     */
    public static String decodeToStr(String value) {
        byte[] decodedValue = delegate.decode(value);
        return new String(decodedValue, UTF_8);
    }

    /**
     * 解码
     *
     * @param value
     *            字符串
     * @param charsetName
     *            字符集
     * @return {String}
     */
    public static String decodeToStr(String value, String charsetName) {
        byte[] decodedValue = delegate.decode(value);
        return new String(decodedValue, Charset.forName(charsetName));
    }

    private static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            Class.forName(className, true, classLoader);
            return true;
        } catch (Throwable ex) {
            return false;
        }
    }

    interface IBase64 {
        String encode(byte[] value);

        byte[] encode(String value);

        byte[] decode(String value);

        byte[] decode(byte[] value);
    }

    static class Java8Base64 implements IBase64 {
        @Override
        public String encode(byte[] value) {
            return java.util.Base64.getEncoder().encodeToString(value);
        }

        @Override
        public byte[] encode(String value) {
            return java.util.Base64.getEncoder().encode(value.getBytes());
        }

        @Override
        public byte[] decode(String value) {
            return java.util.Base64.getDecoder().decode(value);
        }

        @Override
        public byte[] decode(byte[] value) {
            return java.util.Base64.getDecoder().decode(value);
        }
    }

    static class Java67Base64 implements IBase64 {
        public String encode(byte[] data) {
            return javax.xml.bind.DatatypeConverter.printBase64Binary(data);
        }

        @Override
        public byte[] encode(String value) {
            return javax.xml.bind.DatatypeConverter.printBase64Binary(value.getBytes()).getBytes();
        }

        public byte[] decode(String base64) {
            return javax.xml.bind.DatatypeConverter.parseBase64Binary(base64);
        }

        @Override
        public byte[] decode(byte[] value) {
            return javax.xml.bind.DatatypeConverter.parseBase64Binary(new String(value));
        }
    }
}
