package com.pusher.client.util.internal;

import java.util.Arrays;

public class Base64 {
    private static final char[] CHAR_INDEX_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static int[] charToIndexSparseMappingArray;

    static {
        int[] iArr = new int[128];
        charToIndexSparseMappingArray = iArr;
        Arrays.fill(iArr, -1);
        int i = 0;
        while (true) {
            char[] cArr = CHAR_INDEX_TABLE;
            if (i < cArr.length) {
                charToIndexSparseMappingArray[cArr[i]] = i;
                i++;
            } else {
                return;
            }
        }
    }

    private static int toInt(char c) {
        int i = charToIndexSparseMappingArray[c];
        if (i != -1) {
            return i;
        }
        throw new IllegalArgumentException("invalid char: " + c);
    }

    public static byte[] decode(String str) {
        int i = 0;
        int length = ((str.length() * 3) / 4) - (str.endsWith("==") ? 2 : str.endsWith("=") ? 1 : 0);
        byte[] bArr = new byte[length];
        int i2 = 0;
        while (i < str.length()) {
            int i3 = toInt(str.charAt(i));
            int i4 = toInt(str.charAt(i + 1));
            int i5 = i2 + 1;
            bArr[i2] = (byte) (((i3 << 2) | (i4 >> 4)) & 255);
            if (i5 >= length) {
                return bArr;
            }
            int i6 = toInt(str.charAt(i + 2));
            int i7 = i5 + 1;
            bArr[i5] = (byte) (((i4 << 4) | (i6 >> 2)) & 255);
            if (i7 >= length) {
                return bArr;
            }
            bArr[i7] = (byte) (((i6 << 6) | toInt(str.charAt(i + 3))) & 255);
            i += 4;
            i2 = i7 + 1;
        }
        return bArr;
    }
}
