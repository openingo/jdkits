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

import org.openingo.jdkits.json.JacksonKit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JasksonKit demo
 *
 * @author Qicz
 */
public class JacksonKitDemo {

    public static void main(String[] args) {

        List a = new ArrayList();
        a.add(1);
        a.add(2);
        a.add(3);
        String json = JacksonKit.toJson(a);


        System.out.println(json);

        List<Map<String, Object>> hashMaps = JacksonKit.toMapList("[{\"name\":\"zcq\", \"age\":1},{\"name\":\"zcq1\", \"age\":11, \"addr\":\"bj\"}]");
        hashMaps.forEach(map -> {
            String age = map.get("age").toString();
            System.out.println(age.getClass() + "map" + map);
        });
        System.out.println(hashMaps);
    }
}
