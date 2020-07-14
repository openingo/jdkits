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

package org.openingo.jdkits.lang;

/**
 * Int Kit
 *
 * @author Qicz
 */
public final class IntKit {

    private IntKit(){}

    public static String toHex(Integer i) {
        return Integer.toHexString(i);
    }

    public static String toHex(Short si) {
        return Integer.toHexString(si);
    }

    public static String toHex(Byte bi) {
        return Integer.toHexString(bi);
    }

    public static String toOctal(Integer i) {
        return Integer.toOctalString(i);
    }

    public static String toOctal(Short si) {
        return Integer.toOctalString(si);
    }

    public static String toOctal(Byte bi) {
        return Integer.toOctalString(bi);
    }

    public static String toBinary(Integer i) {
        return Integer.toBinaryString(i);
    }

    public static String toBinary(Short si) {
        return Integer.toBinaryString(si);
    }

    public static String toBinary(Byte bi) {
        return Integer.toBinaryString(bi);
    }

    public static Integer binaryToDecimal(String s) {
        return strToDecimal(s, 2);
    }

    public static Integer octalToDecimal(String s) {
        return strToDecimal(s, 8);
    }

    private static Integer strToDecimal(String s, int radix) {
        return Integer.valueOf(s, radix);
    }
}
