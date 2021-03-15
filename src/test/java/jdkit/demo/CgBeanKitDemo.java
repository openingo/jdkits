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

import org.openingo.jdkits.lang.CgBeanKit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CgBeanKitDemo
 *
 * @author Qicz
 */
public class CgBeanKitDemo {

    public static void main(String[] args) {

        Map m1 = new HashMap<>();

        m1.put("a", "b");
        Map m2 = new HashMap<>();

        CgBeanKit.copyProperties(m1, m2);

        System.out.println(m2);

        A a = new A();
        a.a1 = "a1";
        a.a2 = "a2";
        a.a3 = 11;
        a.list = Arrays.asList("111", "222");

        B b = new B();

        CgBeanKit.copyProperties(a, b);

        System.out.println(b);
    }

    static class A {
        String a1;
        String a2;
        int a3;

        List<String> list;

        public String getA1() {
            return a1;
        }

        public void setA1(String a1) {
            this.a1 = a1;
        }

        public String getA2() {
            return a2;
        }

        public void setA2(String a2) {
            this.a2 = a2;
        }

        public int getA3() {
            return a3;
        }

        public void setA3(int a3) {
            this.a3 = a3;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }

    static class B {
        String a1;
        String a2;
        int a3;
        int b1;
        int b2;
        List<String> list;

        public String getA1() {
            return a1;
        }

        public void setA1(String a1) {
            this.a1 = a1;
        }

        public String getA2() {
            return a2;
        }

        public void setA2(String a2) {
            this.a2 = a2;
        }

        public int getA3() {
            return a3;
        }

        public void setA3(int a3) {
            this.a3 = a3;
        }

        public int getB1() {
            return b1;
        }

        public void setB1(int b1) {
            this.b1 = b1;
        }

        public int getB2() {
            return b2;
        }

        public void setB2(int b2) {
            this.b2 = b2;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "B{" +
                    "a1='" + a1 + '\'' +
                    ", a2='" + a2 + '\'' +
                    ", a3=" + a3 +
                    ", b1=" + b1 +
                    ", b2=" + b2 +
                    ", list=" + list +
                    '}';
        }
    }
}
