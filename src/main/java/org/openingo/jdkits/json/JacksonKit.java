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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openingo.jdkits.collection.ListKit;
import org.openingo.jdkits.lang.StrKit;
import org.openingo.jdkits.validate.ValidateKit;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jackson工具 JacksonKit
 *
 * @author Qicz
 */
public final class JacksonKit {

    private JacksonKit(){}

    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        return objectMapper;
    }

    public static String toJson(Object object) {
        String json = "";
        try {
            json = getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getOriginalMessage());
        }
        return json;
    }

    public static String toJson(Object object, JsonInclude.Include include) {
        String json = "";
        try {
            ObjectMapper objectMapper = getObjectMapper();
            json = objectMapper.writeValueAsString(object);
            objectMapper.setSerializationInclusion(include);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getOriginalMessage());
        }
        return json;
    }

    public static String toJson(Object object, String datePattern) {
        if (StrKit.isBlank(datePattern)) {
            return toJson(object);
        }

        String json = "";
        try {
            ObjectMapper objectMapper = getObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat(datePattern));
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getOriginalMessage());
        }
        return json;
    }

    public static String toJson(Object object, String datePattern, JsonInclude.Include include) {
        if (StrKit.isBlank(datePattern)) {
            return toJson(object);
        }

        String json = "";
        try {
            ObjectMapper objectMapper = getObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat(datePattern));
            objectMapper.setSerializationInclusion(include);
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getOriginalMessage());
        }
        return json;
    }

    public static <T> T toObj(String json, Class<T> clazz) {
        T obj = null;
        if (StrKit.isBlank(json)) {
            return obj;
        }
        try {
            obj = getObjectMapper().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getOriginalMessage());
        }
        return obj;
    }

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
     * 转化为List< Map< K, Object>>
     * @param json
     * @param <K>
     */
    public static <K> List<Map<K, Object>> toMapList(String json) {
        return ListKit.emptyArrayListIfNull(toObj(json, List.class));
    }
}
