package com.teemo.myapplication.demo;

import android.util.Base64;

import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by mafuxin on 2018/5/23.
 */

public class GenerateSign {

    public static String appSign(String apiKey, String secret, long currtTime, long expireTime) {
        try {
            int rdm = Math.abs(new Random().nextInt());

            String plainText = String.format("a=%s&b=%d&c=%d&d=%d", apiKey, expireTime, currtTime,rdm);
            byte[] hmacDigest = HmacSha1(plainText, secret);
            byte[] signContent = new byte[hmacDigest.length + plainText.getBytes().length];
            System.arraycopy(hmacDigest, 0, signContent, 0, hmacDigest.length);
            System.arraycopy(plainText.getBytes(), 0, signContent, hmacDigest.length,
                    plainText.getBytes().length);
            return Base64Encode(signContent).replaceAll("[\\s*\t\n\r]", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成base64编码
     *
     * @param binaryData
     * @return
     */
    public static String Base64Encode(byte[] binaryData) {
        String encodedstr = Base64.encodeToString(binaryData, Base64.DEFAULT);
        return encodedstr;
    }

    /**
     * 生成hmacsha1签名
     *
     * @param binaryData
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] HmacSha1(byte[] binaryData, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
        mac.init(secretKey);
        byte[] HmacSha1Digest = mac.doFinal(binaryData);
        return HmacSha1Digest;
    }

    /**
     * 生成hmacsha1签名
     *
     * @param plainText
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] HmacSha1(String plainText, String key) throws Exception {
        return HmacSha1(plainText.getBytes(), key);
    }
}
