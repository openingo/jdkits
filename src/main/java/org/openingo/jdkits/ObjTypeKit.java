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

import java.util.HashMap;
import java.util.Map;

/**
 * Obj 类型 Kit
 *
 * @author Qicz
 */
public final class ObjTypeKit {

    private ObjTypeKit(){}

    public enum TypeSelector {
        MYSQL_TYPE,
        JAVA_TYPE
    }

    private static TypeSelector selector;
    private static ObjTypeKit instance = new ObjTypeKit();

    private static Map<String, Class<?>> javaTypeMapping = new HashMap<String, Class<?>>(32) {{

        // varchar, char, enum, set, text, tinytext, mediumtext, longtext
        put("java.lang.String", java.lang.String.class);

        // int, integer, tinyint, smallint, mediumint
        put("java.lang.Integer", java.lang.Integer.class);

        // bigint
        put("java.lang.Long", java.lang.Long.class);

        // java.util.Date can not be returned
        // java.sql.Date, java.sql.Time, java.sql.Timestamp all extends java.util.Date so getDate can return the three types data
        // put("java.util.Date", java.util.Date.class);

        // date, year
        put("java.sql.Date", java.sql.Date.class);

        // real, double
        put("java.lang.Double", java.lang.Double.class);

        // float
        put("java.lang.Float", java.lang.Float.class);

        // bit
        put("java.lang.Boolean", java.lang.Boolean.class);

        // time
        put("java.sql.Time", java.sql.Time.class);

        // timestamp, datetime
        put("java.sql.Timestamp", java.sql.Timestamp.class);

        // decimal, numeric
        put("java.math.BigDecimal", java.math.BigDecimal.class);

        // unsigned bigint
        put("java.math.BigInteger", java.math.BigInteger.class);

        // binary, varbinary, tinyblob, blob, mediumblob, longblob
        // qjd project: print_info.content varbinary(61800);
        put("[B", byte[].class);

        // 支持需要保持 short 与 byte 而非转成 int 的场景
        put("java.lang.Short", java.lang.Short.class);
        put("java.lang.Byte", java.lang.Byte.class);
    }};

    public static ObjTypeKit selector(TypeSelector selector) {
        ObjTypeKit.selector = selector;
        return ObjTypeKit.instance;
    }

    public static Class<?> getType(String typeString) {
        return javaTypeMapping.get(typeString);
    }
}
