package com.paypal.android.sdk.onetouch.core.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLEncoderHelper {
    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "unable_to_encode:" + str;
        }
    }
}
