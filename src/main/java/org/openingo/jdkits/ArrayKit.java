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

import java.util.*;

/**
 * 数组工具
 *
 * @author Qicz
 */
public final class ArrayKit {

    /**
     * 返回一个空数组
     * @param size
     * @param <T>
     */
    public static <T> T[] empty(Integer size) {
       return (T[])new Object[size];
    }

    /**
     * Arr1 union Arr2
     * 合集
     * @param arr1
     * @param arr2
     */
    public static <T> T[] union(final T[] arr1, final T[] arr2) {
        Set<T> set = new LinkedHashSet<>();
        for (T item : arr1) {
            set.add(item);
        }
        for (T item : arr2) {
            set.add(item);
        }
        return set.toArray(empty(set.size()));
    }

    /**
     * Arr1 intersect Arr2
     * 交集
     * @param arr1
     * @param arr2
     */
    public static <T> T[] intersect(final T[] arr1, final T[] arr2) {
        Map<T, Boolean> map = new HashMap<>();
        for (T item : arr1) {
            if (!map.containsKey(item)) {
                map.put(item, Boolean.FALSE);
            }
        }
        for (T item : arr2) {
            if (map.containsKey(item)) {
                map.put(item, Boolean.TRUE);
            }
        }

        LinkedList<T> list = new LinkedList<>();
        Set<Map.Entry<T, Boolean>> entries = map.entrySet();
        for (Map.Entry<T, Boolean> e : entries) {
            if (e.getValue().equals(Boolean.TRUE)) {
                list.add(e.getKey());
            }
        }

        return list.toArray(empty(list.size()));
    }

    /**
     * Arr1 minus Arr2
     * 差集
     * @param arr1
     * @param arr2
     */
    public static <T> T[] minus(final T[] arr1, final T[] arr2) {
        T[] longerArr = arr1;
        T[] shorterArr = arr2;

        if (arr1.length > arr2.length) {
            longerArr = arr2;
            shorterArr = arr1;
        }

        LinkedList<T> list = new LinkedList<>();
        for (T item : longerArr) {
            if (!list.contains(item)) {
                list.add(item);
            }
        }

        LinkedList<T> history = new LinkedList<>();
        for (T item : shorterArr) {
            if (list.contains(item)) {
                history.add(item);
                list.remove(item);
            } else {
                if (!history.contains(item)) {
                    list.add(item);
                }
            }
        }

        return list.toArray(empty(list.size()));
    }

    /**
     * 翻转一个Array
     * @param array
     */
    public static <T> void reverse(final T[] array) {
        int i = 0;
        int j = array.length - 1;
        T tmp;

        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * 从arr中移除removingArr的内容
     * @param arr
     * @param removingArr
     * @param <T>
     * @return
     */
    public static <T> T[] removeAll(final T[] arr, final T[] removingArr) {
        LinkedList<T> list = new LinkedList<>();
        for (T item : arr) {
            if (!list.contains(item)) {
                list.add(item);
            }
        }
        for (T item : removingArr) {
            if (list.contains(item)) {
                list.remove(item);
            }
        }
        return list.toArray(empty(list.size()));
    }

    /**
     * 数组转化为List
     * @param array
     * @param <T>
     */
    public static <T> List<T> toList(final T[] array) {
        return Arrays.asList(array);
    }
}
