package com.urbanairship.util;

import android.content.Context;
import android.util.Base64;
import com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty;
import com.urbanairship.Logger;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

public abstract class UAStringUtil {
    public static String repeat(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i2 < i) {
            sb.append(str);
            i2++;
            if (i2 != i) {
                sb.append(str2);
            }
        }
        return sb.toString();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean equals(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public static String join(Collection<String> collection, String str) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String sha256(String str) {
        if (str == null) {
            return null;
        }
        try {
            return byteToHex(MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA256).digest(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            Logger.error(e, "Failed to encode string: %s", str);
            return null;
        }
    }

    public static byte[] sha256Digest(String str) {
        if (str == null) {
            return null;
        }
        try {
            return MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA256).digest(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            Logger.error(e, "Failed to encode string: %s", str);
            return null;
        }
    }

    public static String byteToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return sb.toString();
    }

    public static byte[] base64Decode(String str) {
        if (isEmpty(str)) {
            return null;
        }
        try {
            return Base64.decode(str, 0);
        } catch (IllegalArgumentException unused) {
            Logger.verbose("Failed to decode string: %s", str);
            return null;
        }
    }

    public static String base64DecodedString(String str) {
        byte[] base64Decode = base64Decode(str);
        if (base64Decode == null) {
            return null;
        }
        try {
            return new String(base64Decode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Logger.error(e, "Failed to create string", new Object[0]);
            return null;
        }
    }

    public static String nullIfEmpty(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return str;
    }

    public static String namedStringResource(Context context, String str, String str2) {
        int identifier = context.getResources().getIdentifier(str, StringTypedProperty.TYPE, context.getApplicationInfo().packageName);
        if (identifier == 0) {
            return str2;
        }
        return context.getString(identifier);
    }
}
