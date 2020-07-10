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

import java.util.Random;
import java.util.UUID;

/**
 * 随机工具 RandomKit
 *
 * @author Qicz
 */
public final class RandomKit {

    private RandomKit(){}

    private static final Integer NUMBERS = 1;
    private static final Integer CHAR_AND_NUMBERS = 2;

    /**
     * 随机范围内的数
     * @param min
     * @param max
     */
    public static Integer range(Integer min, Integer max){
        Random random = new Random();
        return random.nextInt(max) % (max-min+1) + min;
    }

    /**
     * 随机字符串：UUID方式
     */
    public static String uuidStr(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     *  随机字符串再 md5：UUID方式
     */
    public static String md5Str(){
        return HashKit.md5(uuidStr());
    }

    /**
     * 获取随机6位数字
     */
    public static Integer nInt() {
        return nInt(6);
    }

    /**
     * 获取随机数字
     */
    public static Integer nInt(Integer randomLen) {
        String random = random(randomLen, RandomKit.NUMBERS);
        random = random.length() < randomLen ? random+"0":random;
        return Integer.parseInt(random);
    }

    /**
     * 获取随机8为数字+字符串（大写）
     */
    public static String ucStr() {
        return ucStr(8);
    }

    /**
     * 获取随机字符串（大写）
     */
    public static String ucStr(Integer randomLen) {
        String random = random(randomLen, RandomKit.CHAR_AND_NUMBERS).toUpperCase();
        return random.length() < randomLen ? (random+"0") : random;
    }

    /**
     * 获取随机8为数字+字符串（小写）
     */
    public static String lcStr() {
        return lcStr(8);
    }

    /**
     * 获取随机字符串（小写）
     */
    public static String lcStr(Integer randomLen) {
        String random = random(randomLen, RandomKit.CHAR_AND_NUMBERS).toLowerCase();
        return random.length() < randomLen ? (random+"0") : random;
    }


    private static String random(Integer randomLen,
                                 Integer type){
        String randomRet = "";
        String dataTable = ( RandomKit.NUMBERS.equals(type)
                ? "1234567890"
                : "1234567890abcdefghijkmnpqrstuvwxyz");
        int dataTableLen = dataTable.length();
        boolean bDone = true;
        do {
            randomRet = "";
            int count = 0;
            for (int i = 0; i < randomLen; i++) {
                double dblR = Math.random() * dataTableLen;
                int intR = (int) Math.floor(dblR);
                char c = dataTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                randomRet += dataTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return randomRet;
    }
}