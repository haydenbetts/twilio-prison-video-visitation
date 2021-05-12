package com.urbanairship.http;

import android.os.Build;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.channel.ChannelRegistrationPayload;
import com.urbanairship.json.JsonSerializable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Request {
    private static final ResponseParser<Void> EMPTY_RESPONSE_PARSER = new ResponseParser<Void>() {
        public Void parseResponse(int i, Map<String, List<String>> map, String str) {
            return null;
        }
    };
    private static final int NETWORK_TIMEOUT_MS = 60000;
    private static final String USER_AGENT_FORMAT = "%s (%s; %s; UrbanAirshipLib-%s/%s; %s; %s)";
    protected String body;
    protected boolean compressRequestBody;
    protected String contentType;
    protected long ifModifiedSince;
    protected String password;
    protected String requestMethod;
    protected final Map<String, String> responseProperties;
    protected URL url;
    protected String user;

    public Request(String str, URL url2) {
        this();
        this.requestMethod = str;
        this.url = url2;
    }

    public Request() {
        this.ifModifiedSince = 0;
        this.compressRequestBody = false;
        HashMap hashMap = new HashMap();
        this.responseProperties = hashMap;
        hashMap.put("User-Agent", getUrbanAirshipUserAgent());
    }

    public Request setOperation(String str, URL url2) {
        this.requestMethod = str;
        this.url = url2;
        return this;
    }

    public Request setCredentials(String str, String str2) {
        this.user = str;
        this.password = str2;
        return this;
    }

    public Request setRequestBody(JsonSerializable jsonSerializable) {
        return setRequestBody(jsonSerializable.toJsonValue().toString(), "application/json");
    }

    public Request setRequestBody(String str, String str2) {
        this.body = str;
        this.contentType = str2;
        return this;
    }

    public Request setIfModifiedSince(long j) {
        this.ifModifiedSince = j;
        return this;
    }

    public Request addHeaders(Map<String, String> map) {
        this.responseProperties.putAll(map);
        return this;
    }

    public Request setHeader(String str, String str2) {
        if (str2 == null) {
            this.responseProperties.remove(str);
        } else {
            this.responseProperties.put(str, str2);
        }
        return this;
    }

    public Request setAirshipJsonAcceptsHeader() {
        return setHeader("Accept", "application/vnd.urbanairship+json; version=3;");
    }

    public Request setCompressRequestBody(boolean z) {
        this.compressRequestBody = z;
        return this;
    }

    public Response<Void> safeExecute() {
        try {
            return execute(EMPTY_RESPONSE_PARSER);
        } catch (RequestException e) {
            Logger.debug(e, "Request failed.", new Object[0]);
            return null;
        }
    }

    public Response<Void> execute() throws RequestException {
        return execute(EMPTY_RESPONSE_PARSER);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:35|36) */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r5 = readEntireStream(r4.getErrorStream());
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0111 */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x015a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.urbanairship.http.Response<T> execute(com.urbanairship.http.ResponseParser<T> r10) throws com.urbanairship.http.RequestException {
        /*
            r9 = this;
            java.net.URL r0 = r9.url
            if (r0 == 0) goto L_0x0166
            java.lang.String r0 = r9.requestMethod
            if (r0 == 0) goto L_0x015e
            r0 = 0
            r1 = 2
            r2 = 1
            r3 = 0
            android.content.Context r4 = com.urbanairship.UAirship.getApplicationContext()     // Catch:{ Exception -> 0x013f }
            java.net.URL r5 = r9.url     // Catch:{ Exception -> 0x013f }
            java.net.URLConnection r4 = com.urbanairship.util.ConnectionUtils.openSecureConnection(r4, r5)     // Catch:{ Exception -> 0x013f }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ Exception -> 0x013f }
            java.lang.String r0 = r9.requestMethod     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r4.setRequestMethod(r0)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r0 = 60000(0xea60, float:8.4078E-41)
            r4.setConnectTimeout(r0)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r0 = r9.body     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            if (r0 == 0) goto L_0x0031
            r4.setDoOutput(r2)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r0 = "Content-Type"
            java.lang.String r5 = r9.contentType     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r4.setRequestProperty(r0, r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
        L_0x0031:
            r4.setDoInput(r2)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r4.setUseCaches(r3)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r4.setAllowUserInteraction(r3)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            long r5 = r9.ifModifiedSince     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r7 = 0
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x0045
            r4.setIfModifiedSince(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
        L_0x0045:
            java.util.Map<java.lang.String, java.lang.String> r0 = r9.responseProperties     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.util.Set r0 = r0.keySet()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
        L_0x004f:
            boolean r5 = r0.hasNext()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            if (r5 == 0) goto L_0x0067
            java.lang.Object r5 = r0.next()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.util.Map<java.lang.String, java.lang.String> r6 = r9.responseProperties     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r4.setRequestProperty(r5, r6)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            goto L_0x004f
        L_0x0067:
            java.lang.String r0 = r9.user     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            boolean r0 = com.urbanairship.util.UAStringUtil.isEmpty(r0)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            if (r0 != 0) goto L_0x00ad
            java.lang.String r0 = r9.password     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            boolean r0 = com.urbanairship.util.UAStringUtil.isEmpty(r0)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            if (r0 != 0) goto L_0x00ad
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r0.<init>()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r5 = r9.user     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r0.append(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r5 = ":"
            r0.append(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r5 = r9.password     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r0.append(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r5 = "Authorization"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r6.<init>()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r7 = "Basic "
            r6.append(r7)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            byte[] r0 = r0.getBytes()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r1)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r6.append(r0)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r4.setRequestProperty(r5, r0)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
        L_0x00ad:
            java.lang.String r0 = r9.body     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            if (r0 == 0) goto L_0x00ef
            boolean r0 = r9.compressRequestBody     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r5 = "UTF-8"
            if (r0 == 0) goto L_0x00db
            java.lang.String r0 = "Content-Encoding"
            java.lang.String r6 = "gzip"
            r4.setRequestProperty(r0, r6)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.io.OutputStream r0 = r4.getOutputStream()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.util.zip.GZIPOutputStream r6 = new java.util.zip.GZIPOutputStream     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r6.<init>(r0)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.io.OutputStreamWriter r7 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r7.<init>(r6, r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r5 = r9.body     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r7.write(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r7.close()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r6.close()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r0.close()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            goto L_0x00ef
        L_0x00db:
            java.io.OutputStream r0 = r4.getOutputStream()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.io.OutputStreamWriter r6 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r6.<init>(r0, r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r5 = r9.body     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r6.write(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r6.close()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r0.close()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
        L_0x00ef:
            com.urbanairship.http.Response$Builder r0 = new com.urbanairship.http.Response$Builder     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            int r5 = r4.getResponseCode()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            r0.<init>(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.util.Map r5 = r4.getHeaderFields()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            com.urbanairship.http.Response$Builder r0 = r0.setResponseHeaders(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            long r5 = r4.getLastModified()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            com.urbanairship.http.Response$Builder r0 = r0.setLastModified(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ IOException -> 0x0111 }
            java.lang.String r5 = r9.readEntireStream(r5)     // Catch:{ IOException -> 0x0111 }
            goto L_0x0119
        L_0x0111:
            java.io.InputStream r5 = r4.getErrorStream()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r5 = r9.readEntireStream(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
        L_0x0119:
            int r6 = r4.getResponseCode()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.util.Map r7 = r4.getHeaderFields()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            java.lang.Object r10 = r10.parseResponse(r6, r7, r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            com.urbanairship.http.Response$Builder r10 = r0.setResult(r10)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            com.urbanairship.http.Response$Builder r10 = r10.setResponseBody(r5)     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            com.urbanairship.http.Response r10 = r10.build()     // Catch:{ Exception -> 0x013a, all -> 0x0137 }
            if (r4 == 0) goto L_0x0136
            r4.disconnect()
        L_0x0136:
            return r10
        L_0x0137:
            r10 = move-exception
            r0 = r4
            goto L_0x0158
        L_0x013a:
            r10 = move-exception
            r0 = r4
            goto L_0x0140
        L_0x013d:
            r10 = move-exception
            goto L_0x0158
        L_0x013f:
            r10 = move-exception
        L_0x0140:
            com.urbanairship.http.RequestException r4 = new com.urbanairship.http.RequestException     // Catch:{ all -> 0x013d }
            java.util.Locale r5 = java.util.Locale.ROOT     // Catch:{ all -> 0x013d }
            java.lang.String r6 = "Request failed URL: %s method: %s"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x013d }
            java.net.URL r7 = r9.url     // Catch:{ all -> 0x013d }
            r1[r3] = r7     // Catch:{ all -> 0x013d }
            java.lang.String r3 = r9.requestMethod     // Catch:{ all -> 0x013d }
            r1[r2] = r3     // Catch:{ all -> 0x013d }
            java.lang.String r1 = java.lang.String.format(r5, r6, r1)     // Catch:{ all -> 0x013d }
            r4.<init>(r1, r10)     // Catch:{ all -> 0x013d }
            throw r4     // Catch:{ all -> 0x013d }
        L_0x0158:
            if (r0 == 0) goto L_0x015d
            r0.disconnect()
        L_0x015d:
            throw r10
        L_0x015e:
            com.urbanairship.http.RequestException r10 = new com.urbanairship.http.RequestException
            java.lang.String r0 = "Unable to perform request: missing request method"
            r10.<init>(r0)
            throw r10
        L_0x0166:
            com.urbanairship.http.RequestException r10 = new com.urbanairship.http.RequestException
            java.lang.String r0 = "Unable to perform request: missing URL"
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.http.Request.execute(com.urbanairship.http.ResponseParser):com.urbanairship.http.Response");
    }

    public String getUrbanAirshipUserAgent() {
        return String.format(Locale.US, USER_AGENT_FORMAT, new Object[]{UAirship.getPackageName(), Build.MODEL, Build.VERSION.RELEASE, UAirship.shared().getPlatformType() == 1 ? ChannelRegistrationPayload.AMAZON_DEVICE_TYPE : ChannelRegistrationPayload.ANDROID_DEVICE_TYPE, UAirship.getVersion(), UAirship.shared().getAirshipConfigOptions().appKey, UAirship.shared().getLocale()});
    }

    private String readEntireStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\n");
            } finally {
                try {
                    inputStream.close();
                    bufferedReader.close();
                } catch (Exception e) {
                    Logger.error(e, "Failed to close streams", new Object[0]);
                }
            }
        }
        bufferedReader.close();
        return sb.toString();
    }
}
