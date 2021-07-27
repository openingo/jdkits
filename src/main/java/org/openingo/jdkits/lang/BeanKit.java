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

import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;
import org.openingo.jdkits.collection.ListKit;
import org.openingo.jdkits.reflect.ClassKit;
import org.openingo.jdkits.validate.ValidateKit;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * BeanKit
 *
 * @author Qicz
 */
public final class BeanKit {

    private BeanKit() {
    }

    /**
     * 将对象装换为 map,对象转成 map，key肯定是字符串
     *
     * @param bean 转换对象
     * @return 返回转换后的 map 对象
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> beanToMap(Object bean) {
        return null == bean ? null : BeanMap.create(bean);
    }

    /**
     * map 装换为 java bean 对象
     *
     * @param map   转换 MAP
     * @param clazz 对象 Class
     * @return 返回 bean 对象
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T bean = ClassKit.newInstance(clazz);
        BeanMap.create(bean).putAll(map);
        return bean;
    }

    /**
     * List&lt;T&gt; 转换为 List&lt;Map&lt;String, Object&gt;&gt;
     *
     * @param beans 转换对象集合
     * @return 返回转换后的 bean 列表
     */
    public static <T> List<Map<String, Object>> beansToMaps(List<T> beans) {
        if (ValidateKit.isEmpty(beans)) {
            return ListKit.emptyList();
        }
        return beans.stream().map(BeanKit::beanToMap).collect(toList());
    }

    /**
     * List&lt;Map&lt;String, Object&gt;&gt; 转换为 List&lt;T&gt;
     *
     * @param maps  转换 MAP 集合
     * @param clazz 对象 Class
     * @return 返回转换后的 bean 集合
     */
    public static <T> List<T> mapsToBeans(List<Map<String, Object>> maps, Class<T> clazz) {
        if (ValidateKit.isEmpty(maps)) {
            return ListKit.emptyList();
        }
        return maps.stream().map(e -> mapToBean(e, clazz)).collect(toList());
    }

    public static <A, B> B copy(A a, Class<B> bClass) {
        final Class<A> aClass = (Class<A>)a.getClass();
        return converter(aClass, bClass).convert(a);
    }

    public static <A, B> List<B> copy(Collection<A> a, Class<B> bClass) {
        final Class<A> aClass = (Class<A>)a.iterator().next().getClass();
        return Lists.newArrayList(converter(aClass, bClass).convertAll(a));
    }

    private static <A, B> Converter<A, B> converter(Class<A> aClass, Class<B> bClass) {
        BeanCopier a2bCopier = BeanCopier.create(aClass, bClass, false);
        BeanCopier b2aCopier = BeanCopier.create(bClass, aClass, false);
        return Converter.from(a -> {
            final B b = ClassKit.newInstance(bClass);
            a2bCopier.copy(a, b, null);
            return b;
        }, b -> {
            final A a = ClassKit.newInstance(aClass);
            b2aCopier.copy(b, a, null);
            return a;
        });
    }
}