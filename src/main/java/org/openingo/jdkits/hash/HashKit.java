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

package org.openingo.jdkits.hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Hash工具 HashKit
 *
 * @author Qicz
 */
public final class HashKit {

    private HashKit(){}

    public static final long FNV_OFFSET_BASIS_64 = 0xcbf29ce484222325L;
    public static final long FNV_PRIME_64 = 0x100000001b3L;

    private static final java.security.SecureRandom RANDOM = new java.security.SecureRandom();
    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
    private static final char[] CHAR_ARRAY = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static long fnv1a64(String key) {
        long hash = FNV_OFFSET_BASIS_64;
        for(int i=0, size=key.length(); i<size; i++) {
            hash ^= key.charAt(i);
            hash *= FNV_PRIME_64;
        }
        return hash;
    }

    public static String md5(String srcStr){
        return hash("MD5", srcStr);
    }

    public static String sha1(String srcStr){
        return hash("SHA-1", srcStr);
    }

    public static String sha256(String srcStr){
        return hash("SHA-256", srcStr);
    }

    public static String sha384(String srcStr){
        return hash("SHA-384", srcStr);
    }

    public static String sha512(String srcStr){
        return hash("SHA-512", srcStr);
    }

    public static String hash(String algorithm, String srcStr) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(srcStr.getBytes(StandardCharsets.UTF_8));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toHex(byte[] bytes) {
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            ret.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[aByte & 0x0f]);
        }
        return ret.toString();
    }

    /**
     * md5 128bit 16bytes
     * sha1 160bit 20bytes
     * sha256 256bit 32bytes
     * sha384 384bit 48bytes
     * sha512 512bit 64bytes
     */
    public static String generateSalt(int saltLength) {
        StringBuilder salt = new StringBuilder(saltLength);
        for (int i=0; i<saltLength; i++) {
            salt.append(CHAR_ARRAY[RANDOM.nextInt(CHAR_ARRAY.length)]);
        }
        return salt.toString();
    }

    public static String generateSaltForSha256() {
        return generateSalt(32);
    }

    public static String generateSaltForSha512() {
        return generateSalt(64);
    }

    public static boolean slowEquals(byte[] a, byte[] b) {
        if (a == null || b == null) {
            return false;
        }

        int diff = a.length ^ b.length;
        for(int i=0; i<a.length && i<b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}
