package com.example.util;

import java.util.Random;

public class SaltUtils {
    /**
     * 生成salt的静态方法
     * @param n
     * @return
     */
    public static String getSalt(int n){

        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()".toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            builder.append(aChar);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt(3);
        System.out.println(salt);
    }
}
