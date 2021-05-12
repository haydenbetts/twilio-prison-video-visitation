package org.apache.cordova;

import android.net.Uri;
import com.ionicframework.cordova.webview.WebViewLocalServer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Whitelist {
    public static final String TAG = "Whitelist";
    private ArrayList<URLPattern> whiteList = new ArrayList<>();

    private static class URLPattern {
        public Pattern host;
        public Pattern path;
        public Integer port;
        public Pattern scheme;

        private String regexFromPattern(String str, boolean z) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt == '*' && z) {
                    sb.append(".");
                } else if ("\\.[]{}()^$?+|".indexOf(charAt) > -1) {
                    sb.append('\\');
                }
                sb.append(charAt);
            }
            return sb.toString();
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0026 A[Catch:{ NumberFormatException -> 0x008f }] */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0029 A[Catch:{ NumberFormatException -> 0x008f }] */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x005d A[Catch:{ NumberFormatException -> 0x008f }] */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0077 A[Catch:{ NumberFormatException -> 0x008f }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public URLPattern(java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) throws java.net.MalformedURLException {
            /*
                r4 = this;
                r4.<init>()
                r0 = 0
                r1 = 2
                r2 = 0
                if (r5 == 0) goto L_0x001c
                java.lang.String r3 = "*"
                boolean r3 = r3.equals(r5)     // Catch:{ NumberFormatException -> 0x008f }
                if (r3 == 0) goto L_0x0011
                goto L_0x001c
            L_0x0011:
                java.lang.String r5 = r4.regexFromPattern(r5, r0)     // Catch:{ NumberFormatException -> 0x008f }
                java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5, r1)     // Catch:{ NumberFormatException -> 0x008f }
                r4.scheme = r5     // Catch:{ NumberFormatException -> 0x008f }
                goto L_0x001e
            L_0x001c:
                r4.scheme = r2     // Catch:{ NumberFormatException -> 0x008f }
            L_0x001e:
                java.lang.String r5 = "*"
                boolean r5 = r5.equals(r6)     // Catch:{ NumberFormatException -> 0x008f }
                if (r5 == 0) goto L_0x0029
                r4.host = r2     // Catch:{ NumberFormatException -> 0x008f }
                goto L_0x005b
            L_0x0029:
                java.lang.String r5 = "*."
                boolean r5 = r6.startsWith(r5)     // Catch:{ NumberFormatException -> 0x008f }
                if (r5 == 0) goto L_0x0051
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x008f }
                r5.<init>()     // Catch:{ NumberFormatException -> 0x008f }
                java.lang.String r3 = "([a-z0-9.-]*\\.)?"
                r5.append(r3)     // Catch:{ NumberFormatException -> 0x008f }
                java.lang.String r6 = r6.substring(r1)     // Catch:{ NumberFormatException -> 0x008f }
                java.lang.String r6 = r4.regexFromPattern(r6, r0)     // Catch:{ NumberFormatException -> 0x008f }
                r5.append(r6)     // Catch:{ NumberFormatException -> 0x008f }
                java.lang.String r5 = r5.toString()     // Catch:{ NumberFormatException -> 0x008f }
                java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5, r1)     // Catch:{ NumberFormatException -> 0x008f }
                r4.host = r5     // Catch:{ NumberFormatException -> 0x008f }
                goto L_0x005b
            L_0x0051:
                java.lang.String r5 = r4.regexFromPattern(r6, r0)     // Catch:{ NumberFormatException -> 0x008f }
                java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5, r1)     // Catch:{ NumberFormatException -> 0x008f }
                r4.host = r5     // Catch:{ NumberFormatException -> 0x008f }
            L_0x005b:
                if (r7 == 0) goto L_0x0073
                java.lang.String r5 = "*"
                boolean r5 = r5.equals(r7)     // Catch:{ NumberFormatException -> 0x008f }
                if (r5 == 0) goto L_0x0066
                goto L_0x0073
            L_0x0066:
                r5 = 10
                int r5 = java.lang.Integer.parseInt(r7, r5)     // Catch:{ NumberFormatException -> 0x008f }
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ NumberFormatException -> 0x008f }
                r4.port = r5     // Catch:{ NumberFormatException -> 0x008f }
                goto L_0x0075
            L_0x0073:
                r4.port = r2     // Catch:{ NumberFormatException -> 0x008f }
            L_0x0075:
                if (r8 == 0) goto L_0x008c
                java.lang.String r5 = "/*"
                boolean r5 = r5.equals(r8)     // Catch:{ NumberFormatException -> 0x008f }
                if (r5 == 0) goto L_0x0080
                goto L_0x008c
            L_0x0080:
                r5 = 1
                java.lang.String r5 = r4.regexFromPattern(r8, r5)     // Catch:{ NumberFormatException -> 0x008f }
                java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)     // Catch:{ NumberFormatException -> 0x008f }
                r4.path = r5     // Catch:{ NumberFormatException -> 0x008f }
                goto L_0x008e
            L_0x008c:
                r4.path = r2     // Catch:{ NumberFormatException -> 0x008f }
            L_0x008e:
                return
            L_0x008f:
                java.net.MalformedURLException r5 = new java.net.MalformedURLException
                java.lang.String r6 = "Port must be a number"
                r5.<init>(r6)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.Whitelist.URLPattern.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
        }

        public boolean matches(Uri uri) {
            try {
                if (this.scheme != null && !this.scheme.matcher(uri.getScheme()).matches()) {
                    return false;
                }
                if (this.host != null && !this.host.matcher(uri.getHost()).matches()) {
                    return false;
                }
                if (this.port != null && !this.port.equals(Integer.valueOf(uri.getPort()))) {
                    return false;
                }
                if (this.path == null || this.path.matcher(uri.getPath()).matches()) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                LOG.d(Whitelist.TAG, e.toString());
                return false;
            }
        }
    }

    public void addWhiteListEntry(String str, boolean z) {
        if (this.whiteList != null) {
            try {
                if (str.compareTo("*") == 0) {
                    LOG.d(TAG, "Unlimited access to network resources");
                    this.whiteList = null;
                    return;
                }
                Matcher matcher = Pattern.compile("^((\\*|[A-Za-z-]+):(//)?)?(\\*|((\\*\\.)?[^*/:]+))?(:(\\d+))?(/.*)?").matcher(str);
                if (matcher.matches()) {
                    String group = matcher.group(2);
                    String group2 = matcher.group(4);
                    if (("file".equals(group) || "content".equals(group)) && group2 == null) {
                        group2 = "*";
                    }
                    String group3 = matcher.group(8);
                    String group4 = matcher.group(9);
                    if (group == null) {
                        this.whiteList.add(new URLPattern(WebViewLocalServer.httpScheme, group2, group3, group4));
                        this.whiteList.add(new URLPattern(WebViewLocalServer.httpsScheme, group2, group3, group4));
                        return;
                    }
                    this.whiteList.add(new URLPattern(group, group2, group3, group4));
                }
            } catch (Exception unused) {
                LOG.d(TAG, "Failed to add origin %s", str);
            }
        }
    }

    public boolean isUrlWhiteListed(String str) {
        if (this.whiteList == null) {
            return true;
        }
        Uri parse = Uri.parse(str);
        Iterator<URLPattern> it = this.whiteList.iterator();
        while (it.hasNext()) {
            if (it.next().matches(parse)) {
                return true;
            }
        }
        return false;
    }
}
