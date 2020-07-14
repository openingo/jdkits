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

import org.openingo.jdkits.encryption.RSAKit;

/**
 * RSAKitDemo
 *
 * @author Qicz
 */
public class RSAKitDemo {

    public static void main(String[] args) throws Exception {
        RSAKit.init();

        byte[] originData = "this is origin data.".getBytes();
        byte[] publicKeyByte = RSAKit.getPublicKeyByte();
        byte[] privateKeyByte = RSAKit.getPrivateKeyByte();

        byte[] encryptDataByPublicKey = RSAKit.encryptByPublicKey(originData, publicKeyByte);
        byte[] encryptDataByPrivateKey = RSAKit.encryptByPrivateKey(originData, privateKeyByte);

        // 私钥加密，公钥解密
        byte[] decryptDataByPublicKey = RSAKit.decryptByPublicKey(encryptDataByPrivateKey, publicKeyByte);
        System.out.println(new String(decryptDataByPublicKey));

        // 公钥加密，私钥解密
        byte[] decryptDataByPrivateKey = RSAKit.decryptByPrivateKey(encryptDataByPublicKey, privateKeyByte);
        System.out.println(new String(decryptDataByPrivateKey));
    }
}
