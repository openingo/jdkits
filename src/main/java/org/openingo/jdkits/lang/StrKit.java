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

import org.openingo.jdkits.hash.HashKit;
import org.openingo.jdkits.validate.ValidateKit;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具 StrKit
 *
 * @author Qicz
 */
public final class StrKit implements StringPoolKit {

    private StrKit(){}

    private static final String ILLEGAL_CHARS = "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆";

    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 字符串为 null 或者内部字符全部为 ' ' '\t' '\n' '\r' 这四类字符时返回 true
     */
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        int len = str.length();
        if (len == 0) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            switch (str.charAt(i)) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    // case '\b':
                    // case '\f':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public static boolean notBlank(String str) {
        return !isBlank(str);
    }

    public static boolean notBlank(String... strings) {
        if (strings == null || strings.length == 0) {
            return false;
        }
        for (String str : strings) {
            if (isBlank(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean notNull(Object... paras) {
        if (paras == null) {
            return false;
        }
        for (Object obj : paras) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }

    public static String defaultIfBlank(String str, String defaultValue) {
        return isBlank(str) ? defaultValue : str;
    }

    public static String toCamelCase(String stringWithUnderline) {
        if (stringWithUnderline.indexOf('_') == -1) {
            return stringWithUnderline;
        }

        stringWithUnderline = stringWithUnderline.toLowerCase();
        char[] fromArray = stringWithUnderline.toCharArray();
        char[] toArray = new char[fromArray.length];
        int j = 0;
        for (int i=0; i<fromArray.length; i++) {
            if (fromArray[i] == '_') {
                // 当前字符为下划线时，将指针后移一位，将紧随下划线后面一个字符转成大写并存放
                i++;
                if (i < fromArray.length) {
                    toArray[j++] = Character.toUpperCase(fromArray[i]);
                }
            }
            else {
                toArray[j++] = fromArray[i];
            }
        }
        return new String(toArray, 0, j);
    }

    public static String join(String[] stringArray) {
        StringBuilder sb = new StringBuilder();
        for (String s : stringArray) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String join(String[] stringArray, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<stringArray.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(stringArray[i]);
        }
        return sb.toString();
    }

    public static String join(java.util.List<String> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i=0, len=list.size(); i<len; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    public static boolean slowEquals(String a, String b) {
        byte[] aBytes = (a != null ? a.getBytes() : null);
        byte[] bBytes = (b != null ? b.getBytes() : null);
        return HashKit.slowEquals(aBytes, bBytes);
    }

    public static boolean equals(String a, String b) {
        return a == null ? b == null : a.equals(b);
    }

    public static String getRandomUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    public static Integer toInteger(String obj) {
        return Integer.valueOf(obj);
    }

    public static Long toLong(String obj) {
        return Long.valueOf(obj);
    }

    public static Double toDouble(String obj) {
        return Double.valueOf(obj);
    }

    public static Float toFloat(String obj) {
        return Float.valueOf(obj);
    }

    /**
     * <p>Compares given <code>string</code> to a CharSequences vararg of <code>searchStrings</code>,
     * returning {@code true} if the <code>string</code> is equal to any of the <code>searchStrings</code>.</p>
     *
     * <pre>
     * StringUtils.equalsAny(null, (CharSequence[]) null) = false
     * StringUtils.equalsAny(null, null, null)    = true
     * StringUtils.equalsAny(null, "abc", "def")  = false
     * StringUtils.equalsAny("abc", null, "def")  = false
     * StringUtils.equalsAny("abc", "abc", "def") = true
     * StringUtils.equalsAny("abc", "ABC", "DEF") = false
     * </pre>
     *
     * @param string to compare, may be {@code null}.
     * @param searchStrings a vararg of strings, may be {@code null}.
     * @return {@code true} if the string is equal (case-sensitive) to any other element of <code>searchStrings</code>;
     * {@code false} if <code>searchStrings</code> is null or contains no matches.
     * @since 3.5
     */
    public static boolean equalsAny(final CharSequence string, final CharSequence... searchStrings) {
        if (ValidateKit.isNotEmpty(searchStrings)) {
            for (final CharSequence next : searchStrings) {
                if (equals(string, next)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Equals
    //-----------------------------------------------------------------------
    /**
     * <p>Compares two CharSequences, returning {@code true} if they represent
     * equal sequences of characters.</p>
     *
     * <p>{@code null}s are handled without exceptions. Two {@code null}
     * references are considered to be equal. The comparison is <strong>case sensitive</strong>.</p>
     *
     * <pre>
     * StringUtils.equals(null, null)   = true
     * StringUtils.equals(null, "abc")  = false
     * StringUtils.equals("abc", null)  = false
     * StringUtils.equals("abc", "abc") = true
     * StringUtils.equals("abc", "ABC") = false
     * </pre>
     *
     * @param cs1  the first CharSequence, may be {@code null}
     * @param cs2  the second CharSequence, may be {@code null}
     * @return {@code true} if the CharSequences are equal (case-sensitive), or both {@code null}
     * @since 3.0 Changed signature from equals(String, String) to equals(CharSequence, CharSequence)
     * @see Object#equals(Object)
     */
    public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1.length() != cs2.length()) {
            return false;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }
        // Step-wise comparison
        final int length = cs1.length();
        for (int i = 0; i < length; i++) {
            if (cs1.charAt(i) != cs2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @throws NumberFormatException
     */
    public static int[] convertCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    /**
     * 判断字符串中是否包含中文
     * <note>不能校验是否为中文标点符号</note>
     * @param str 待校验字符串
     * @return 是否为中文
     */
    public static boolean isContainChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        return Pattern.compile(regEx).matcher(str).find();
    }

    /**
     * 过滤掉中文
     * @param string 待过滤中文的字符串
     * @return 过滤掉中文后字符串
     */
    public static String filterChinese(String string) {
        String ret = string;
        // 包含中文
        if (isContainChinese(string)) {
            StringBuilder retBuilder = new StringBuilder();
            char[] chars = string.toCharArray();
            // 过滤到中文及中文字符
            for (char c : chars) {
                // 不是中日韩文字及标点符号
                if (!isChinese(c)) {
                    retBuilder.append(c);
                }
            }
            ret = retBuilder.toString();
        }
        return ret;
    }

    /**
     * 校验一个字符是否是汉字
     *
     * @param c 被校验的字符
     * @return true代表是汉字
     */
    public static boolean isChineseChar(char c) {
        try {
            return String.valueOf(c).getBytes(StandardCharsets.UTF_8).length > 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 验证字符串内容是否包含下列非法字符<br>
     * `~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆
     *
     * @param string 字符串内容
     * @return true包含，false不包含
     */
    public static boolean hasIllegalChar(String string) {
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            if (isIllegalChar(aChar)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证字符内容是否包含下列非法字符<br>
     * `~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆
     *
     * @param c 字符
     * @return true包含，false不包含
     */
    public static boolean isIllegalChar(char c) {
        return ILLEGAL_CHARS.contains(String.valueOf(c));
    }

    /**
     * 校验某个字符是否是a-z、A-Z、_、0-9
     *
     * @param c 被校验的字符
     * @return true代表符合条件
     */
    public static boolean isWord(char c) {
        String regEx = "[\\w]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher("" + c);
        return m.matches();
    }

    /**
     * 判定输入的是否是汉字
     *
     * @param c 被校验的字符
     * @return true代表是汉字
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * 校验String是否全是中文
     *
     * @param string 被校验的字符串
     * @return true代表全是汉字
     */
    public static boolean isChinese(String string) {
        char[] chars = string.toCharArray();
        for (char c: chars) {
            if (!isChinese(c)) {
                return false;
            }
        }
        return true;
    }
}
