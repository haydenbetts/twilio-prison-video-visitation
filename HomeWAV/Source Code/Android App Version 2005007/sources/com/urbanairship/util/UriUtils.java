package com.urbanairship.util;

import android.net.Uri;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UriUtils {
    public static Map<String, List<String>> getQueryParameters(Uri uri) {
        HashMap hashMap = new HashMap();
        String encodedQuery = uri.getEncodedQuery();
        if (UAStringUtil.isEmpty(encodedQuery)) {
            return hashMap;
        }
        for (String split : encodedQuery.split("&")) {
            String[] split2 = split.split("=");
            String str = null;
            String decode = split2.length >= 1 ? Uri.decode(split2[0]) : null;
            if (split2.length >= 2) {
                str = Uri.decode(split2[1]);
            }
            if (!UAStringUtil.isEmpty(decode)) {
                List list = (List) hashMap.get(decode);
                if (list == null) {
                    list = new ArrayList();
                    hashMap.put(decode, list);
                }
                list.add(str);
            }
        }
        return hashMap;
    }

    public static Uri parse(Object obj) {
        if ((obj instanceof String) || (obj instanceof Uri) || (obj instanceof URL)) {
            return Uri.parse(String.valueOf(obj));
        }
        return null;
    }
}
