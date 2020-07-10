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

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 数据校验工具 ValidateKit
 *
 * @author Qicz
 */
public final class ValidateKit {

    private ValidateKit(){}

    /**
     * 是否为null
     * @param obj:List,Map,String,Object
     * @return true为空，false不为空
     */
    public static boolean isNull(final Object obj) {
        return isEmpty(obj);
    }

    /**
     * 是否不为null
     * @param obj:List,Map,String,Object
     * @return true不为空，false为空
     */
    public static boolean isNotNull(final Object obj) {
        return !isNull(obj);
    }

    /**
     * 判断数组中的元素是否存在Null
     * @param objs
     * @return true为存在，false不存在
     */
    public static boolean isContainNull(final Object... objs) {
        if (null == objs) {
            return true;
        }

        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断数组中的元素是否均为Null
     * @param objs
     * @return true全部为null，false部分不为null
     */
    public static boolean isAllNull(final Object... objs) {
        if (null == objs) {
            return true;
        }

        for (Object obj : objs) {
            if (isNotEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断数组中的元素是否均为Null
     * @param objs
     * @return true全部不为null，false部分为null
     */
    public static boolean isAllNotNull(final Object... objs) {
        return !isContainNull(objs);
    }

    /**
     * 是否为Empty
     * @param obj:List,Map,String,Object
     * @return true为空，false不为空
     */
    public static boolean isEmpty(final Object obj) {
        if (null == obj) {
            return true;
        }

        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        // Collection, Map, CharSequence 之外的
        return false;
    }

    /**
     * 是否不为Empty
     * @param obj:List,Map,String,Object
     * @return true为空，false不为空
     */
    public static boolean isNotEmpty(final Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 是否相等
     * @param a
     * @param b
     * @return true相等false不相等
     */
    public static boolean isEqual(final Object a, final Object b) {
        return (a == b) || (ValidateKit.isNotNull(a) ? a.equals(b) : (ValidateKit.isNotNull(b) ? b.equals(a) : true));
    }

    /**
     * 是否不相等
     * @param a
     * @param b
     * @return true相等false不相等
     */
    public static boolean isNotEqual(final Object a, final Object b) {
        return ValidateKit.isNotNull(a) ? !a.equals(b) : (ValidateKit.isNotNull(b) ? !b.equals(a) : false);
    }
}
