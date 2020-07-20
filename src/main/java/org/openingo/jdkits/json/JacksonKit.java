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

package org.openingo.jdkits.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import org.openingo.jdkits.collection.ListKit;
import org.openingo.jdkits.lang.StrKit;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Jackson工具 JacksonKit
 *
 * @author Qicz
 */
public final class JacksonKit {

    private JacksonKit(){}

    private static final ThreadLocal<ObjectMapper> OBJECT_MAPPER_HOLDER = ThreadLocal.withInitial(() -> {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        return objectMapper;
    });

    private static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER_HOLDER.get();
    }

    public static String toJson(Object object, JsonInclude.Include include) throws JsonProcessingException {
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.setSerializationInclusion(include);
        return objectMapper.writeValueAsString(object);
    }

    public static String toJson(Object object, String datePattern, JsonInclude.Include include) throws JsonProcessingException {
        if (StrKit.isBlank(datePattern)) {
            return toJson(object, include);
        }

        return getObjectMapper()
                .setDateFormat(new SimpleDateFormat(datePattern))
                .setSerializationInclusion(include)
                .writeValueAsString(object);
    }

    public static String toJson(Object object) throws JsonProcessingException {
        return toJson(object, JsonInclude.Include.ALWAYS);
    }

    public static String toJson(Object object, String datePattern) throws JsonProcessingException {
        return toJson(object, datePattern, JsonInclude.Include.ALWAYS);
    }

    public static <T> T toObj(String json, Class<T> clazz) throws JsonProcessingException {
        if (StrKit.isBlank(json)) {
            return null;
        }
        return getObjectMapper().readValue(json, clazz);
    }

    public static <T> List<T> toList(String json, Class<? extends List> collectionClass, Class<T> elementClass) throws JsonProcessingException {
        if (StrKit.isBlank(json)) {
            return ListKit.emptyArrayList();
        }
        ObjectMapper objectMapper = getObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
        return objectMapper.readValue(json, javaType);
    }

    public static <T> List<T> toList(String json, Class<T> elementClass) throws JsonProcessingException {
        return toList(json, ArrayList.class, elementClass);
    }

    public static <T> List<T[]> to2DList(String json, Class<T[]> clazz) throws JsonProcessingException {
        return toList(json, clazz);
    }

    public static <K, V> Map<K, V> toMap(String json,
                                         Class<? extends Map> mapClass,
                                         Class<K> keyClass,
                                         Class<V> valueClass) throws JsonProcessingException {
        ObjectMapper objectMapper = getObjectMapper();
        MapType mapType = objectMapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
        return objectMapper.readValue(json, mapType);
    }

    public static <K, V> List<Map<K, V>> toMapList(String json,
                                                   Class<K> keyClass,
                                                   Class<V> valueClass) throws JsonProcessingException {
        List<Object> objects = toList(json, List.class, Object.class);
        List<Map<K, V>> list = ListKit.emptyArrayList(objects.size());
        for (Object object : objects) {
            Map<K, V> kvMap = toMap(toJson(object), HashMap.class, keyClass, valueClass);
            list.add(kvMap);
        }
        return list;
    }

    public static <K> List<Map<K, Object>> toMapList(String json,
                                                     Class<K> keyClass) throws JsonProcessingException  {
        return toMapList(json, keyClass, Object.class);
    }

    public static <K, V> LinkedHashMap<K, V> toLinkedMap(String json,
                                                         Class<K> keyClass,
                                                         Class<V> valueClass) throws JsonProcessingException {
        ObjectMapper objectMapper = getObjectMapper();
        MapType mapType = objectMapper.getTypeFactory().constructMapType(LinkedHashMap.class, keyClass, valueClass);
        return objectMapper.readValue(json, mapType);
    }

    public static <K, V> List<LinkedHashMap<K, V>> toLinkedMapList(String json,
                                                   Class<K> keyClass,
                                                   Class<V> valueClass) throws JsonProcessingException {
        List<Object> objects = toList(json, List.class, Object.class);
        List<LinkedHashMap<K, V>> list = ListKit.emptyArrayList(objects.size());
        for (Object object : objects) {
            LinkedHashMap<K, V> kvMap = toLinkedMap(toJson(object), keyClass, valueClass);
            list.add(kvMap);
        }
        return list;
    }

    public static <K> List<LinkedHashMap<K, Object>> toLinkedMapList(String json,
                                                     Class<K> keyClass) throws JsonProcessingException  {
        return toLinkedMapList(json, keyClass, Object.class);
    }
}
