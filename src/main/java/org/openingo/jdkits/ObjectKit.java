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

import java.io.*;
import java.util.Map;

/**
 * Object Kit
 *
 * @author Qicz
 */
public final class ObjectKit {

    private ObjectKit(){}

    /**
     * 对象转数组
     * @param obj
     * @return object byte[]
     */
    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * byte数组转对象
     * @param bytes
     */
    public static <T> T toObject(byte[] bytes) {
        T obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = (T)ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    /**
     * 对象转数组
     * @param obj
     * @return object byte[]
     */
    public static byte[] toCompressByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CompressKit.compress(bytes);
    }

    /**
     * byte数组转对象
     * @param bytes
     */
    public static <T> T toDecompressObject(byte[] bytes) {
        T obj = null;
        try {
            byte[] decompress = CompressKit.decompress(bytes);
            ByteArrayInputStream bis = new ByteArrayInputStream (decompress);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = (T)ois.readObject();
            ois.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 对象转数组
     * @param obj
     * @param password 加密密码
     * @return object byte[]
     */
    public static byte[] toCompressByteArray(Object obj, String password) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CompressKit.compress(bytes, password);
    }

    /**
     * byte数组转对象
     * @param bytes
     * @param password 加密密码
     */
    public static <T> T toDecompressObject(byte[] bytes, String password) {
        T obj = null;
        try {
            byte[] decompress = CompressKit.decompress(bytes, password);
            ByteArrayInputStream bis = new ByteArrayInputStream (decompress);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = (T)ois.readObject();
            ois.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * object 转化为 map，借助jackson
     * @param obj
     * @return
     */
    public static Map toMap(Object obj) {
        return JacksonKit.toMap(JacksonKit.toJson(obj));
    }

    /**
     * Clone 一个对象
     * @param obj
     * @param <T>
     * @return
     */
    public <T extends Serializable> T clone(T obj) {
        return SerializationKit.clone(obj);
    }
}
