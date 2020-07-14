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

import org.openingo.jdkits.validate.AssertKit;
import org.openingo.jdkits.validate.ValidateKit;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * NumberKit
 *
 * @author Qicz
 */
public final class NumberKit {

    private NumberKit(){}

    public static Boolean isByte(Object obj) {
        return (obj instanceof Byte);
    }

    public static Boolean isInt(Object obj) {
        return (obj instanceof Integer);
    }

    public static Boolean isLong(Object obj) {
        return (obj instanceof Long);
    }

    public static Boolean isShort(Object obj) {
        return (obj instanceof Short);
    }

    public static Boolean isDouble(Object obj) {
        return (obj instanceof Double);
    }

    public static Boolean isFloat(Object obj) {
        return (obj instanceof Float);
    }

    public static Boolean isBigDecimal(Object obj) {
        return (obj instanceof BigDecimal);
    }

    public static Boolean isBigInteger(Object obj) {
        return (obj instanceof BigInteger);
    }

    /**
     * 取值是否为0或0.0
     * @param obj
     * @return true为0，false不为0
     */
    public static Boolean isZero(Object obj) {
        if (ValidateKit.isNull(obj)) {
            return false;
        }
        // int etc
        if ((isInt(obj) || isByte(obj) || isShort(obj) || isLong(obj))
                && ((Integer)obj).intValue() == 0) {
            return true;
        }

        // double etc
        if ((isFloat(obj) || isDouble(obj))
                && ((Double)obj).doubleValue() == 0.0) {
            return true;
        }

        // BigInteger
        if (isBigInteger(obj) && BigInteger.ZERO.equals(obj)) {
            return true;
        }

        // BigDecimal
        if (isBigDecimal(obj) && BigDecimal.ZERO.equals(obj)) {
            return true;
        }

        return false;
    }

    /**
     * 转化为十六进制
     * @param obj
     * @return
     */
    public static String toHex(Object obj) {
        AssertKit.notNull(obj, "obj不能为null.");
        String hexString = "";
        // int or short or byte
        if (isInt(obj) || isShort(obj) || isByte(obj)) {
            hexString = Integer.toHexString(((Integer)obj));
        }
        // long
        if (isLong(obj)) {
            hexString = Long.toHexString(((Long)obj));
        }
        // double or float
        if (isFloat(obj) || isDouble(obj)) {
            hexString = Double.toHexString((Double)obj);
        }
        if (isBigInteger(obj)) {
            hexString = ((BigInteger)obj).toString(16);
        }
        if (isBigDecimal(obj)) {
            hexString = ((BigDecimal)obj).toBigInteger().toString(16);
        }
        return hexString;
    }

    public static Integer toInteger(Object obj) {
        return ObjectKit.toInteger(obj);
    }

    public static Long toLong(Object obj) {
        return ObjectKit.toLong(obj);
    }

    public static Double toDouble(Object obj) {
        return ObjectKit.toDouble(obj);
    }

    public static Float toFloat(Object obj) {
        return ObjectKit.toFloat(obj);
    }
}
