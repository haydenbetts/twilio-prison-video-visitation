package com.ionicframework.cordova.webview;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class UriMatcher {
    private static final int EXACT = 0;
    static final Pattern PATH_SPLIT_PATTERN = Pattern.compile("/");
    private static final int REST = 2;
    private static final int TEXT = 1;
    private ArrayList<UriMatcher> mChildren;
    private Object mCode;
    private String mText;
    private int mWhich;

    public UriMatcher(Object obj) {
        this.mCode = obj;
        this.mWhich = -1;
        this.mChildren = new ArrayList<>();
        this.mText = null;
    }

    private UriMatcher() {
        this.mCode = null;
        this.mWhich = -1;
        this.mChildren = new ArrayList<>();
        this.mText = null;
    }

    public void addURI(String str, String str2, String str3, Object obj) {
        UriMatcher uriMatcher;
        String str4 = str3;
        Object obj2 = obj;
        if (obj2 != null) {
            String[] strArr = null;
            if (str4 != null) {
                if (str3.length() > 0 && str4.charAt(0) == '/') {
                    str4 = str4.substring(1);
                }
                strArr = PATH_SPLIT_PATTERN.split(str4);
            }
            int length = strArr != null ? strArr.length : 0;
            UriMatcher uriMatcher2 = this;
            int i = -2;
            while (i < length) {
                String str5 = i == -2 ? str : i == -1 ? str2 : strArr[i];
                ArrayList<UriMatcher> arrayList = uriMatcher2.mChildren;
                int size = arrayList.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        uriMatcher = uriMatcher2;
                        break;
                    }
                    uriMatcher = arrayList.get(i2);
                    if (str5.equals(uriMatcher.mText)) {
                        break;
                    }
                    i2++;
                }
                if (i2 == size) {
                    uriMatcher2 = new UriMatcher();
                    if (str5.equals("**")) {
                        uriMatcher2.mWhich = 2;
                    } else if (str5.equals("*")) {
                        uriMatcher2.mWhich = 1;
                    } else {
                        uriMatcher2.mWhich = 0;
                    }
                    uriMatcher2.mText = str5;
                    uriMatcher.mChildren.add(uriMatcher2);
                } else {
                    uriMatcher2 = uriMatcher;
                }
                i++;
            }
            uriMatcher2.mCode = obj2;
            return;
        }
        throw new IllegalArgumentException("Code can't be null");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
        if (r10.mText.equals(r5) != false) goto L_0x0052;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object match(android.net.Uri r13) {
        /*
            r12 = this;
            java.util.List r0 = r13.getPathSegments()
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0013
            java.lang.String r2 = r13.getAuthority()
            if (r2 != 0) goto L_0x0013
            java.lang.Object r13 = r12.mCode
            return r13
        L_0x0013:
            r2 = -2
            r4 = r12
            r3 = -2
        L_0x0016:
            if (r3 >= r1) goto L_0x0061
            if (r3 != r2) goto L_0x001f
            java.lang.String r5 = r13.getScheme()
            goto L_0x002d
        L_0x001f:
            r5 = -1
            if (r3 != r5) goto L_0x0027
            java.lang.String r5 = r13.getAuthority()
            goto L_0x002d
        L_0x0027:
            java.lang.Object r5 = r0.get(r3)
            java.lang.String r5 = (java.lang.String) r5
        L_0x002d:
            java.util.ArrayList<com.ionicframework.cordova.webview.UriMatcher> r6 = r4.mChildren
            if (r6 != 0) goto L_0x0032
            goto L_0x0061
        L_0x0032:
            int r4 = r6.size()
            r7 = 0
            r8 = 0
            r9 = r8
        L_0x0039:
            if (r7 >= r4) goto L_0x005a
            java.lang.Object r10 = r6.get(r7)
            com.ionicframework.cordova.webview.UriMatcher r10 = (com.ionicframework.cordova.webview.UriMatcher) r10
            int r11 = r10.mWhich
            switch(r11) {
                case 0: goto L_0x004a;
                case 1: goto L_0x0052;
                case 2: goto L_0x0047;
                default: goto L_0x0046;
            }
        L_0x0046:
            goto L_0x0053
        L_0x0047:
            java.lang.Object r13 = r10.mCode
            return r13
        L_0x004a:
            java.lang.String r11 = r10.mText
            boolean r11 = r11.equals(r5)
            if (r11 == 0) goto L_0x0053
        L_0x0052:
            r9 = r10
        L_0x0053:
            if (r9 == 0) goto L_0x0057
            r4 = r9
            goto L_0x005b
        L_0x0057:
            int r7 = r7 + 1
            goto L_0x0039
        L_0x005a:
            r4 = r9
        L_0x005b:
            if (r4 != 0) goto L_0x005e
            return r8
        L_0x005e:
            int r3 = r3 + 1
            goto L_0x0016
        L_0x0061:
            java.lang.Object r13 = r4.mCode
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ionicframework.cordova.webview.UriMatcher.match(android.net.Uri):java.lang.Object");
    }
}
