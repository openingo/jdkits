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

package org.openingo.jdkits.encryption;

import org.openingo.jdkits.hash.HashKit;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Aes 工具
 *
 * @author Qicz
 */
public final class AesKit {

    private AesKit(){}

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static String genAesKey(){
        return HashKit.generateSalt(32);
    }

    public static byte[] encrypt(byte[] content, String aesTextKey) {
        return encrypt(content, aesTextKey.getBytes(UTF_8));
    }

    public static byte[] encrypt(String content, String aesTextKey) {
        return encrypt(content.getBytes(UTF_8), aesTextKey.getBytes(UTF_8));
    }

    public static byte[] encrypt(String content, String charsetName, String aesTextKey){
        return encrypt(content.getBytes(Charset.forName(charsetName)), aesTextKey.getBytes(UTF_8));
    }

    public static byte[] decrypt(byte[] content, String aesTextKey) {
        return decrypt(content, aesTextKey.getBytes(UTF_8));
    }

    public static String decryptToStr(byte[] content, String aesTextKey) {
        return new String(decrypt(content, aesTextKey.getBytes(UTF_8)), UTF_8);
    }

    public static String decryptToStr(byte[] content, String aesTextKey, String charsetName) {
        return new String(decrypt(content, aesTextKey.getBytes(UTF_8)), Charset.forName(charsetName));
    }

    public static byte[] encrypt(byte[] content, byte[] aesKey){
        try {
            if(aesKey.length != 32){
                throw new RuntimeException("IllegalAesKey, aesKey's length must be 32");
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            return cipher.doFinal(PKCS7Encoder.encode(content));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public static byte[] decrypt(byte[] encrypted, byte[] aesKey){
        try {
            if(aesKey.length != 32){
                throw new RuntimeException("IllegalAesKey, aesKey's length must be 32");
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);
            return PKCS7Encoder.decode(cipher.doFinal(encrypted));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    /**
     * 提供基于PKCS7算法的加解密接口.
     */
    static class PKCS7Encoder {
        static int BLOCK_SIZE = 32;

        static byte[] encode(byte[] src) {
            int count = src.length;
            // 计算需要填充的位数
            int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
            if (amountToPad == 0) {
                amountToPad = BLOCK_SIZE;
            }
            // 获得补位所用的字符
            byte pad = (byte) (amountToPad & 0xFF);
            byte[] pads = new byte[amountToPad];
            for (int index = 0; index < amountToPad; index++) {
                pads[index] = pad;
            }
            int length = count + amountToPad;
            byte[] dest = new byte[length];
            System.arraycopy(src, 0, dest, 0, count);
            System.arraycopy(pads, 0, dest, count, amountToPad);
            return dest;
        }

        static byte[] decode(byte[] decrypted) {
            int pad = (int) decrypted[decrypted.length - 1];
            if (pad < 1 || pad > 32) {
                pad = 0;
            }
            if(pad > 0){
                return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
            }
            return decrypted;
        }
    }
}
