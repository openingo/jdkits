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

package org.openingo.jdkits.collection;

import org.openingo.java.util.OverrideList;
import org.openingo.jdkits.validate.AssertKit;
import org.openingo.jdkits.validate.ValidateKit;

import java.util.*;

/**
 * List Kit
 *
 * @author Qicz
 */
public final class ListKit {

    private ListKit(){}

    /**
     * 返回一个空的OverrideList
     */
    public static <T> OverrideList<T> emptyOverrideList() {
        return new OverrideList<>();
    }

    public static <T> OverrideList<T> emptyOverrideList(int initialCapacity) {
        return new OverrideList<>(initialCapacity);
    }

    /**
     * 返回一个空的ArrayList
     */
    public static <T> List<T> emptyArrayList() {
        return new ArrayList<>();
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  the initial capacity of the list
     */
    public static <T> List<T> emptyArrayList(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
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
        return defaultIfNull(list, emptyList());
    }

    /**
     * 如果list 为null 返回一个emptyArrayList
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> emptyArrayListIfNull(final List<T> list) {
        return defaultIfNull(list, emptyArrayList());
    }

    /**
     * if the list is null or empty, return a default list
     * @param list
     * @param defaultList
     * @param <T>
     * @return list or default list when list is null or empty.
     */
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
    enum ListOpsEnum {
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
        AssertKit.isTrue(null != list, "list is null.");
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
    public static <T> boolean removeAll(final List<T> list, final Collection<?> removingList) {
        validateList(list);
        boolean ret = true;
        if (ValidateKit.isNotNull(removingList)) {
            ret = list.removeAll(removingList);
        }
        return ret;
    }

    /**
     * Synchronized List
     * @param list
     * @param <T>
     */
    public static <T> List<T> synchronizedList(final List<T> list) {
        validateList(list);

        return Collections.synchronizedList(list);
    }

    /**
     * validate list is Null or not Null
     * @param list
     * @param <T>
     * @return <tt>true</tt> if this list is Null.
     */
    public static <T> boolean isNull(final List<T> list) {
        return ValidateKit.isNull(list);
    }

    /**
     * 判断list是否只有一个元素
     * @param list
     * @param <T>
     * @return true只有一个元素，false多个元素或list为空
     */
    public static <T> boolean hasOneElement(final List<T> list) {
        validateList(list);

        return (list.size() == 1);
    }

    /**
     * 判断list是否只有一个元素
     * @param list
     * @param <T>
     * @return true只有一个元素，false多个元素或list为空
     */
    public static <T> boolean hasManyElement(final List<T> list) {
        validateList(list);

        return (list.size() > 1);
    }

    /**
     * replace一个索引对应的元素
     * @param list
     * @param index
     * @param obj
     * @param <T>
     */
    public static <T> void replace(final List<T> list, int index, T obj) {
        AssertKit.isTrue(index < list.size() && index >= 0, "索引不合法。");
        list.set(index, obj);
    }

    /**
     * Rewrite {@linkplain List#addAll(Collection)}, check the specified collection
     * @param list
     * @param collection
     * @param <T>
     * @return <tt>true</tt> if this list changed as a result of the call
     */
    public static <T> boolean addAll(final List<T> list, final Collection<T> collection){
        validateList(list);
        boolean ret = true;
        if (ValidateKit.isNotNull(collection)) {
            ret = list.addAll(collection);
        }
        return ret;
    }

    /**
     * Rewrite {@linkplain List#addAll(int, Collection)}, check the specified collection
     * @param list
     * @param index
     * @param collection
     * @param <T>
     * @return <tt>true</tt> if this list changed as a result of the call
     */
    public static <T> boolean addAll(final List<T> list, final int index, final Collection<T> collection){
        validateList(list);
        boolean ret = true;
        if (ValidateKit.isNotNull(collection)) {
            ret = list.addAll(index, collection);
        }
        return ret;
    }
}
