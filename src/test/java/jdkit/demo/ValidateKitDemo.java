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

import org.openingo.jdkits.validate.ValidateKit;

/**
 * ValidateKitDemo
 *
 * @author Qicz
 */
public class ValidateKitDemo {

    public static void main(String[] args) {
        Object[] objs = new Object[]{null, "ok"};
        objs = new Object[]{null, null};
        System.out.println("isContainNull->"+ ValidateKit.isContainNull(objs));
        System.out.println("isAllNull->"+ValidateKit.isAllNull(objs));
        System.out.println("isAllNotNull->"+ValidateKit.isAllNotNull(objs));


        System.out.println("isNull->"+ValidateKit.isNull(objs));
        System.out.println("isNotNull->"+ValidateKit.isNotNull(objs));
        System.out.println("isEmpty->"+ValidateKit.isEmpty(objs));
        System.out.println("isNotEmpty->"+ValidateKit.isNotEmpty(objs));
    }
}
