package com.urbanairship.util;

public class ColorUtils {
    public static String convertToString(int i) {
        StringBuilder sb = new StringBuilder("#");
        sb.append(Integer.toHexString(i));
        while (sb.length() < 9) {
            sb.append("0");
        }
        return sb.toString();
    }
}
