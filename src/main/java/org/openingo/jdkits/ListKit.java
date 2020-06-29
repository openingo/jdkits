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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * List Kit
 *
 * @author Qicz
 */
public class ListKit {

    /**
     * 返回一个空的ArrayList
     */
    public static <T> List<T> emptyArrayList() {
        return new ArrayList<>();
    }

    /**
     * 返回一个空的List
     */
    public static <T> List<T> emptyList() {
        return Collections.<T>emptyList();
    }

    /**
     * 如果list 为null 返回一个emptyList
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> emptyIfNull(final List<T> list) {
        return ValidateKit.isNull(list) ? emptyList() : list;
    }

    public static <T> List<T> defaultIfNull(final List<T> list, final List<T> defaultList) {
        return ValidateKit.isNull(list) ? defaultList : list;
    }

    /**
     * 递归的方式计算排列组合
     * @param permutationRet 组合结果
     * @param list  传入list.size()个集合
     * @param prefix 上一步递归中生成的排列组合
     */
    public static void permutation(final List<String> permutationRet,
                                   final List<List<Object>> list,
                                   final String prefix,
                                   final String split) {
        if (ValidateKit.isContainNull(permutationRet, list)) {
            return;
        }

        int size = list.size();
        if(1 == size){
            for(int i=0; i<list.get(0).size(); i++) {
                permutationRet.add(prefix + list.get(0).get(i));
            }
        } else {
            List<Object> permu = new ArrayList<>(list.get(0));
            List<List<Object>> now = new ArrayList<>(list);
            now.remove(0);
            for(int i = 0; i < permu.size(); i++){
                permutation(permutationRet, now, prefix + permu.get(i) + split, ":");
            }
        }
    }

    /**
     * list转化为Array
     * @param list
     * @param <T>
     */
    public static <T> T[] toArray(final List<T> list) {
        return list.toArray(ArrayKit.empty(list.size()));
    }

    /**
     * ListOps类型
     */
    private static enum ListOpsEnum {
        UNION,// 合集
        INTERSECT, // 交集
        MINUS; // 差集
    }

    /**
     * list ops: union, intersect
     * @param list1
     * @param list2
     * @param opsEnum
     * @param <T>
     * @return
     */
    private static <T> List<T> listOps(final List<T> list1, final List<T> list2, ListOpsEnum opsEnum) {
        if (ValidateKit.isAllNull(list1, list1)) {
            return emptyArrayList();
        }

        if (ValidateKit.isEqual(list2, defaultIfNull(list1, list2))) {
            return list2;
        }

        if (ValidateKit.isEqual(list1, defaultIfNull(list2, list1))) {
            return list1;
        }

        List<T> ret = emptyArrayList();
        T[] list1Array = toArray(list1);
        T[] list2Array = toArray(list2);
        switch (opsEnum) {
            case UNION:{
                ret = Arrays.asList(ArrayKit.union(list1Array, list2Array));
            }
                break;

            case INTERSECT: {
                ret = Arrays.asList(ArrayKit.intersect(list1Array, list2Array));
            }
                break;

            case MINUS: {
                ret = Arrays.asList(ArrayKit.minus(list1Array, list2Array));
            }
                break;
        }

        return ret;
    }

    /**
     * List1 union List2
     * 合集
     * @param list1
     * @param list2
     */
    public static <T> List<T> union(final List<T> list1, final List<T> list2) {
        return listOps(list1, list2, ListOpsEnum.UNION);
    }

    /**
     * List1 intersect List2
     * 交集
     * @param list1
     * @param list2
     */
    public static <T> List<T> intersect(final List<T> list1, final List<T> list2) {
        return listOps(list1, list2, ListOpsEnum.INTERSECT);
    }

    /**
     * List1 minus List2
     * 差集
     * @param list1
     * @param list2
     */
    public static <T> List<T> minus(final List<T> list1, final List<T> list2) {
        return listOps(list1, list2, ListOpsEnum.MINUS);
    }

    /**
     * 校验合法性
     * @param list
     * @param <T>
     */
    private static <T> void validateList(final List<T> list) {
        AssertKit.notNull(list, "当前操作list不能为null.");
    }

    /**
     * 翻转一个List
     * @param list
     */
    public static <T> List<T> reverse(final List<T> list) {
        validateList(list);

        T[] listArray = toArray(list);
        ArrayKit.reverse(listArray);
        return ArrayKit.toList(listArray);
    }

    /**
     * 从list移除包含在removingList中元素
     * @param list
     * @param removingList
     * @param <T>
     * @return list移除removingList中元素后剩余的元素集合
     */
    public static <T> List<T> removeAll(final List<T> list, final List<T> removingList) {
        list.removeAll(removingList);
        return list;
    }

    /**
     * 线程安全的List
     * @param list
     * @param <T>
     */
    public static <T> List<T> synchronizedList(final List<T> list) {
        validateList(list);

        return Collections.synchronizedList(list);
    }

    /**
     * 判断list是否为Null
     * @param list
     * @param <T>
     * @return true为空，false不为空
     */
    public static <T> Boolean isNull(final List<T> list) {
        return ValidateKit.isNull(list);
    }

    /**
     * 判断list是否为Empty
     * @param list
     * @param <T>
     * @return true为空，false不为空
     */
    public static <T> Boolean isEmpty(final List<T> list) {
        return isNull(list) || list.isEmpty();
    }

    /**
     * 判断list是否只有一个元素
     * @param list
     * @param <T>
     * @return true只有一个元素，false多个元素或list为空
     */
    public static <T> Boolean hasOneElement(final List<T> list) {
        validateList(list);

        return (list.size() == 1);
    }

    /**
     * 判断list是否只有一个元素
     * @param list
     * @param <T>
     * @return true只有一个元素，false多个元素或list为空
     */
    public static <T> Boolean hasManyElement(final List<T> list) {
        validateList(list);

        return (list.size() > 1);
    }

    /**
     * replace一个索引对应的元素
     * @param list
     * @param idx
     * @param obj
     * @param <T>
     */
    public static <T> void replace(List<T> list, Integer idx, T obj) {
        AssertKit.isTrue(idx < list.size() && idx >= 0, "索引不合法。");
        list.set(idx, obj);
    }
}
