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

package org.openingo.jdkits.validate;

/**
 * AssertKit 工具
 *
 * @author Qicz
 */
public final class AssertKit {

    private AssertKit(){}

    /**
     * 断言当前为true
     * @param expression
     * @param message
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言当前为false
     * @param expression
     * @param message
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言当前为null
     * @param object
     * @param message
     */
    public static void isNull(Object object, String message) {
        if (ValidateKit.isNotNull(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言当前不为null
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        if (ValidateKit.isNull(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个数组为Empty
     * @param array
     * @param message
     */
    public static void isEmpty(Object[] array, String message) {
        if (ValidateKit.isNotEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个数组不为Empty
     * @param array
     * @param message
     */
    public static void notEmpty(Object[] array, String message) {
        if (ValidateKit.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言两个对象相等
     * @param a
     * @param b
     * @param message
     */
    public static void isEqual(Object a, Object b, String message) {
        if (ValidateKit.isNotEqual(a, b)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言两个对象不相等
     * @param a
     * @param b
     * @param message
     */
    public static void isNotEqual(Object a, Object b, String message) {
        if (ValidateKit.isEqual(a, b)) {
            throw new IllegalArgumentException(message);
        }
    }
}
