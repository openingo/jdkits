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

import java.util.List;

/**
 * ListKitDemo
 *
 * @author Qicz
 */
public class ListKitDemo {

    public static void main(String[] args) {
        List<String> a = ListKit.emptyArrayList();
        a.add("a5");
        a.add("a1");
        a.add("a4");
        a.add("a2");
        a.add("a3");

        List<String> b = ListKit.emptyArrayList();
        b.add("a1");
        b.add("a3");
        b.add("b6");

//        System.out.println(ListKit.minus(a, b));
//        System.out.println(a.removeAll(b)+"aa"+a);
//
//        System.out.println(ListKit.removeAll(a, b));

//        List<String> ret = ListKit.emptyArrayList();
//
//        List list = ListKit.emptyArrayList();
//        list.add(a);
//        list.add(b);
//
//        ListKit.permutation(ret, list, "", "::");
//
//        System.out.println(ret);
//
//
//        List<String> union = ListKit.union(a, b);
//        System.out.println("union"+union);
//
//        List<String> minus = ListKit.minus(a, b);
//        System.out.println(minus);
//
//        System.out.println(a);
//
//        a = ListKit.reverse(a);
//
//        System.out.println(a);

        ListKit.replace(a, 0, "zczz");
        ListKit.addAll(a, b);

        System.out.println(a);
    }
}
