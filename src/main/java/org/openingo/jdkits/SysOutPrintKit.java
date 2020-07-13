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

import java.util.Collection;
import java.util.Map;

/**
 * SysOutPrint Kit
 *
 *
 * printf方法中,格式为"%s"表示以字符串的形式输出第二个可变长参数的第一个参数值;
 * 格式为"%n"表示换行;格式为"%S"表示将字符串以大写形式输出;在"%s"之间用"n$"表示
 * 输出可变长参数的第n个参数值.格式为"%b"表示以布尔值的形式输出第二个可变长参数
 * 的第一个参数值.
 *
 * 格式为"%d"表示以十进制整数形式输出;"%o"表示以八进制形式输出;"%x"表示以十六进制
 * 输出;"%X"表示以十六进制输出,并且将字母(A、B、C、D、E、F)换成大写.格式为"%e"表
 * 以科学计数法输出浮点数;格式为"%E"表示以科学计数法输出浮点数,而且将e大写;格式为
 * "%f"表示以十进制浮点数输出,在"%f"之间加上".n"表示输出时保留小数点后面n位.
 *
 * 格式为"%t"表示输出时间日期类型."%t"之后用y表示输出日期的二位数的年份(如99)、用m
 * 表示输出日期的月份,用d表示输出日期的日号;"%t"之后用Y表示输出日期的四位数的年份
 * (如1999)、用B表示输出日期的月份的完整名,用b表示输出日期的月份的简称."%t"之后用D
 * 表示以"%tm/%td/%ty"的格式输出日期、用F表示以"%tY-%tm-%td"的格式输出日期.
 *
 * "%t"之后用H表示输出时间的时(24进制),用I表示输出时间的时(12进制),用M表示输出时间
 * 分,用S表示输出时间的秒,用L表示输出时间的秒中的毫秒数、用 p 表示输出时间的是上午还是
 * 下午."%t"之后用R表示以"%tH:%tM"的格式输出时间、用T表示以"%tH:%tM:%tS"的格式输出
 * 时间、用r表示以"%tI:%tM:%tS %Tp"的格式输出时间.
 * "%t"之后用A表示输出日期的全称,用a表示输出日期的星期简称.
 *
 * @author Qicz
 */
public final class SysOutPrintKit {

    private SysOutPrintKit(){}

    public static <T> void print(String format, T... args) {
        System.out.print(String.format(format, args));
    }

    public static <T> void println(String format, T... args) {
        System.out.println(String.format(format, args));
    }

    /**
     * 打印一个数组
     * @param array
     */
    public static void printArray(Object[] array) {
        if (ValidateKit.isNull(array)) {
            return;
        }

        StringBuilder formatBuilder = new StringBuilder("len:%s, data=[");
        int len = array.length;
        String[] values = new String[len+1];
        values[0] = String.valueOf(len);
        for (int i = 0; i < len; i++) {
            formatBuilder.append("%s");
            values[i+1] = array[i].toString();
            if (i != len - 1) {
                formatBuilder.append(", ");
            }
        }
        formatBuilder.append("]");
        println(formatBuilder.toString(), values);
    }

    /**
     * 打印一个list
     * @param collection
     */
    public static void printList(final Collection<?> collection) {
        if (ValidateKit.isNull(collection)) {
            return;
        }

        StringBuilder formatBuilder = new StringBuilder("len:%s, data=");
        formatBuilder.append(collection.toString());
        println(formatBuilder.toString(), collection.size());
    }

    /**
     * 打印Map的keys
     * @param map
     */
    public static void printMapKeys(final Map<?, ?> map) {
        if (ValidateKit.isNull(map)) {
            println("Map ==> null.");
            return;
        }
        printList(map.keySet());
    }

    /**
     * 打印Map的Values
     * @param map
     */
    public static void printMapValues(final Map<?, ?> map) {
        if (ValidateKit.isNull(map)) {
            println("Map ==> null.");
            return;
        }
        printList(map.values());
    }

    /**
     * 打印一个Map，单列出Keys和Values
     * @param map
     */
    public static void printMap(final Map<?, ?> map) {
        if (ValidateKit.isNull(map)) {
            println("Map ==> null.");
            return;
        }
        print("Map-Keys ==> ");
        printMapKeys(map);
        print("Map-Values ==> ");
        printMapValues(map);
        print("Map ==> ");
        System.out.println(map);
    }
}
