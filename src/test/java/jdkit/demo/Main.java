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

package jdkit.demo;

import org.openingo.jdkits.collection.ListKit;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String ss[]) {

//        perm("这个人abc");
        String str = "这个人,abc";
        permStr(str);
        System.out.println();
    }
 
    // 求字符串中所有字符的组合abc>a,b,c,ab,ac,bc,abc
    public static void perm(String s) {
        List<String> result = new ArrayList<String>();
        for (int i = 1; i <= s.length(); i++) {
            perm(s, i, result);
        }
    }
 
    // 从字符串s中选择m个字符
    public static void perm(String s, int m, List<String> result) {
 
        // 如果m==0，则递归结束。输出当前结果
        if (m == 0) {
            for (String value : result) {
                System.out.print(value);
            }
            System.out.print(" ");
             return;
        }
 
        if (s.length() != 0) {
            // 选择当前元素
            result.add(s.charAt(0) + "");
            perm(s.substring(1), m - 1, result);
            result.remove(result.size() - 1);
            // 不选当前元素
            perm(s.substring(1), m, result);
        }
    }

    public static void permStr(String str) {
        List<String> ret = ListKit.emptyArrayList();
        String[] split = str.split("[, ]");
        for (String subStr : split) {
            int length = subStr.length();
            int step = length;
            for (int i = 0; i < length; i++, step--) {
                if (step <= 1) {
                    break;
                }
                String substring = subStr.substring(0, step);
                    ret.add(substring);
            }
            // a part
            char[] chars = subStr.toCharArray();
            for (char aChar : chars) {
                ret.add(String.valueOf(aChar));
            }
        }

        for (String value : ret) {
            System.out.print(value + " ");
        }
        System.out.print(" " + String.join(",", ret).toString());
    }
}