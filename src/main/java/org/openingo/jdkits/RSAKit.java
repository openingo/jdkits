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

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSAKit
 *
 * @author Qicz
 */
public final class RSAKit {

    private RSAKit(){}

    /**
     * 非对称加密密钥算法
     */
    private static final String KEY_ALGORITHM_RSA = "RSA";

    /**
     * 公钥
     */
    private static final String RSA_PUBLIC_KEY = "RSAPublicKey";

    /**
     * 私钥
     */
    private static final String RSA_PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA密钥长度,默认1024位
     * 密钥长度必须是64的倍数，范围在512至65536位之间。
     */
    private static final int KEY_SIZE = 1024;

    /**
     * Keys Store
     */
    private static Map<String, Key> keysStore;


    /**
     * 初始化密钥
     *
     * @param seed 种子
     * @return Map 密钥Map
     * @throws Exception 异常
     */
    private static void init(byte[] seed) throws Exception {
        // 实例化密钥对生成器
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM_RSA);
        // 初始化密钥对生成器
        keyPairGen.initialize(KEY_SIZE, new SecureRandom(seed));
        // 生成密钥对
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 封装密钥
        keysStore = new HashMap<String, Key>(2);
        keysStore.put(RSA_PUBLIC_KEY, publicKey);
        keysStore.put(RSA_PRIVATE_KEY, privateKey);
    }

    /**
     * 如果keysStore不存在初始化
     * @throws Exception
     */
    private static void initKeysStoreIfNeed() throws Exception {
        if (ValidateKit.isNull(keysStore)) {
            init();
        }
    }

    /**
     * 数据加密
     * @param data
     * @param key
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        int blockSize = cipher.getBlockSize();
        if (blockSize > 0) {
            int outputSize = cipher.getOutputSize(data.length);
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
                    : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0, remainSize = 0;
            while ((remainSize = data.length - i * blockSize) > 0) {
                int inputLen = Math.min(remainSize, blockSize);
                cipher.doFinal(data, i * blockSize, inputLen, raw, i * outputSize);
                i++;
            }
            return raw;
        }
        return cipher.doFinal(data);
    }

    /**
     * 数据解密
     * @param data
     * @param key
     * @return 解密后的byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        // 取得私钥
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);
        int blockSize = cipher.getBlockSize();
        if (blockSize > 0) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;
            while (data.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(data, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        }
        return cipher.doFinal(data);
    }

    /**
     * 初始化密钥
     *
     * @param seed 种子
     *
     * @throws Exception 异常
     */
    public static void init(String seed) throws Exception {
        init(seed.getBytes());
    }

    /**
     * 初始化密钥
     *
     * @throws Exception 异常
     */
    public static void init() throws Exception {
        init(RandomKit.uuidStr());
    }

    /**
     * 取得私钥
     *
     * @return key 私钥
     * @throws Exception 异常
     */
    public static Key getPrivateKey() throws Exception {
        initKeysStoreIfNeed();
        return keysStore.get(RSA_PRIVATE_KEY);
    }

    /**
     * 取得私钥
     *
     * @return byte[] 私钥
     * @throws Exception 异常
     */
    public static byte[] getPrivateKeyByte() throws Exception {
        return getPrivateKey().getEncoded();
    }

    /**
     * 取得公钥
     *
     * @return key 公钥
     * @throws Exception 异常
     */
    public static Key getPublicKey() throws Exception {
        initKeysStoreIfNeed();
        return keysStore.get(RSA_PUBLIC_KEY);
    }

    /**
     * 取得公钥
     *
     * @return byte[] 公钥
     * @throws Exception 异常
     */
    public static byte[] getPublicKeyByte() throws Exception {
        return getPublicKey().getEncoded();
    }

    /**
     * 从以Base64编码的key中获取公钥
     * @param base64EncodedKey
     * @throws Exception
     */
    public static PublicKey getPublicKey(String base64EncodedKey) throws Exception {
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(Base64Kit.decode(base64EncodedKey));
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        return kf.generatePublic(x509);
    }

    /**
     * 从以Base64编码的key中获取私钥
     * @param base64EncodedKey
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String base64EncodedKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64Kit.decode(base64EncodedKey));
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        return kf.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return byte[] 加密数据
     * @throws Exception 异常
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        return encrypt(data, publicKey);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @return byte[] 加密数据
     * @throws Exception 异常
     */
    public static byte[] encryptByPublicKey(byte[] data) throws Exception {
        return encrypt(data, getPublicKey());
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return byte[] 加密数据
     * @throws Exception 异常
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        return encrypt(data, privateKey);
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @return byte[] 加密数据
     * @throws Exception 异常
     */
    public static byte[] encryptByPrivateKey(byte[] data) throws Exception {
        return encrypt(data, getPrivateKey());
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return byte[] 解密数据
     * @throws Exception 异常
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        // 生成公钥
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        return decrypt(data, publicKey);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @return byte[] 解密数据
     * @throws Exception 异常
     */
    public static byte[] decryptByPublicKey(byte[] data) throws Exception {
        return decrypt(data, getPublicKey());
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return byte[] 解密数据
     * @throws Exception 异常
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        return decrypt(data, privateKey);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @return byte[] 解密数据
     * @throws Exception 异常
     */
    public static byte[] decryptByPrivateKey(byte[] data) throws Exception {
        return decrypt(data, getPrivateKey());
    }
}
