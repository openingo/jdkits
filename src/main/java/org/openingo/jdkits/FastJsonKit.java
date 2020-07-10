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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.beust.jcommander.internal.Lists;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FastJson工具 FastJsonKit
 *
 * @author Qicz
 */
public final class FastJsonKit {

    private FastJsonKit(){}

    /**
     * 转化为Json
     * @param obj
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 转化为Json
     * @param obj
     */
    public static String toJson(Object obj, String datePattern) {
        return JSON.toJSONStringWithDateFormat(obj, datePattern, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 转化为Obj
     * @param json
     * @param clazz
     * @param <T>
     */
    public static <T> T toObj(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 转化为Map
     * @param json
     */
    public static <K, V> Map<K, V> toMap(String json) {
        Map<K, V> aMap = toObj(json, Map.class);
        if (ValidateKit.isNull(aMap)) {
            aMap = new HashMap<>();
        }
        return aMap;
    }

    /**
     * 转化为Map
     * @param obj
     */
    public static <K, V> Map<K, V> toMap(Object obj) {
        return toMap(toJson(obj));
    }

    /**
     * 转化为List
     * @param json
     * @param clazz
     * @param <T>
     */
    public static <T> List<T> toList(String json, Class<T> clazz) {
        return ListKit.emptyArrayListIfNull(JSONArray.parseArray(json, clazz));
    }

    /**
     * 转化为2D List
     * @param json
     * @param clazz
     * @param <T>
     */
    public static <T> List<T[]> to2DList(String json, Class<T[]> clazz) {
        return ListKit.emptyArrayListIfNull(JSONArray.parseArray(json, clazz));
    }

    /**
     * 转化为List< Map< K, Object>>
     * @param json
     * @param <K>
     */
    public static <K> List<Map<K, Object>> toMapList(String json) {
        return ListKit.emptyArrayListIfNull(toObj(json, List.class));
    }
}
