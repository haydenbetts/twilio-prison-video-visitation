package fm.liveswitch;

import com.microsoft.appcenter.http.DefaultHttpClient;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpWebRequestTransfer extends HttpTransfer {
    private ExecutorService exec = Executors.newCachedThreadPool();

    public static String getPlatformCode() {
        return "java";
    }

    private static HttpURLConnection createRequest(HttpRequestArgs httpRequestArgs) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(httpRequestArgs.getUrl()).openConnection();
            httpURLConnection.setRequestMethod(DefaultHttpClient.METHOD_POST);
            switch (AnonymousClass4.$SwitchMap$fm$liveswitch$HttpMethod[httpRequestArgs.getMethod().ordinal()]) {
                case 1:
                    httpURLConnection.setRequestMethod(DefaultHttpClient.METHOD_GET);
                    break;
                case 2:
                    httpURLConnection.setRequestMethod("HEAD");
                    break;
                case 3:
                    httpURLConnection.setRequestMethod(DefaultHttpClient.METHOD_POST);
                    break;
                case 4:
                    httpURLConnection.setRequestMethod("PUT");
                    break;
                case 5:
                    httpURLConnection.setRequestMethod("PATCH");
                    break;
                case 6:
                    httpURLConnection.setRequestMethod(DefaultHttpClient.METHOD_DELETE);
                    break;
            }
            for (Map.Entry next : httpRequestArgs.getHeaders().entrySet()) {
                httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
            }
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip,deflate");
            httpURLConnection.setConnectTimeout(httpRequestArgs.getTimeout());
            httpURLConnection.setReadTimeout(httpRequestArgs.getTimeout());
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            if (httpRequestArgs.getBinaryContent() != null) {
                httpURLConnection.setDoOutput(true);
            }
            if (httpRequestArgs.getOnRequestCreated() != null) {
                HttpRequestCreatedArgs httpRequestCreatedArgs = new HttpRequestCreatedArgs();
                httpRequestCreatedArgs.setSender(httpRequestArgs.getSender());
                httpRequestCreatedArgs.setRequest(httpURLConnection);
                httpRequestCreatedArgs.setRequestArgs(httpRequestArgs);
                httpRequestArgs.getOnRequestCreated().invoke(httpRequestCreatedArgs);
            }
            return httpURLConnection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: fm.liveswitch.HttpWebRequestTransfer$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$fm$liveswitch$HttpMethod;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                fm.liveswitch.HttpMethod[] r0 = fm.liveswitch.HttpMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$fm$liveswitch$HttpMethod = r0
                fm.liveswitch.HttpMethod r1 = fm.liveswitch.HttpMethod.Get     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$fm$liveswitch$HttpMethod     // Catch:{ NoSuchFieldError -> 0x001d }
                fm.liveswitch.HttpMethod r1 = fm.liveswitch.HttpMethod.Head     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$fm$liveswitch$HttpMethod     // Catch:{ NoSuchFieldError -> 0x0028 }
                fm.liveswitch.HttpMethod r1 = fm.liveswitch.HttpMethod.Post     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$fm$liveswitch$HttpMethod     // Catch:{ NoSuchFieldError -> 0x0033 }
                fm.liveswitch.HttpMethod r1 = fm.liveswitch.HttpMethod.Put     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$fm$liveswitch$HttpMethod     // Catch:{ NoSuchFieldError -> 0x003e }
                fm.liveswitch.HttpMethod r1 = fm.liveswitch.HttpMethod.Patch     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$fm$liveswitch$HttpMethod     // Catch:{ NoSuchFieldError -> 0x0049 }
                fm.liveswitch.HttpMethod r1 = fm.liveswitch.HttpMethod.Delete     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.HttpWebRequestTransfer.AnonymousClass4.<clinit>():void");
        }
    }

    public void shutdown() {
        this.exec.shutdown();
    }

    public HttpResponseArgs sendBinary(HttpRequestArgs httpRequestArgs) {
        HttpURLConnection httpURLConnection;
        RuntimeException runtimeException;
        boolean z;
        HttpResponseArgs httpResponseArgs = new HttpResponseArgs(httpRequestArgs);
        HttpURLConnection createRequest = createRequest(httpRequestArgs);
        ManagedCondition managedCondition = new ManagedCondition();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean[] zArr = {false};
        RuntimeException[] runtimeExceptionArr = {null};
        synchronized (managedCondition) {
            final HttpRequestArgs httpRequestArgs2 = httpRequestArgs;
            final HttpURLConnection httpURLConnection2 = createRequest;
            final HttpResponseArgs httpResponseArgs2 = httpResponseArgs;
            final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            AnonymousClass1 r0 = r1;
            final ManagedCondition managedCondition2 = managedCondition;
            httpURLConnection = createRequest;
            ExecutorService executorService = this.exec;
            final boolean[] zArr2 = zArr;
            final RuntimeException[] runtimeExceptionArr2 = runtimeExceptionArr;
            AnonymousClass1 r1 = new Runnable() {
                /* JADX WARNING: Can't wrap try/catch for region: R(2:7|8) */
                /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
                    r3 = r4.getErrorStream();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
                    r3 = null;
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r8 = this;
                        r0 = 1
                        r1 = 0
                        r2 = 0
                        fm.liveswitch.HttpRequestArgs r3 = r3     // Catch:{ Exception -> 0x009a }
                        byte[] r3 = r3.getBinaryContent()     // Catch:{ Exception -> 0x009a }
                        if (r3 == 0) goto L_0x001d
                        java.net.HttpURLConnection r3 = r4     // Catch:{ Exception -> 0x009a }
                        java.io.OutputStream r3 = r3.getOutputStream()     // Catch:{ Exception -> 0x009a }
                        fm.liveswitch.HttpRequestArgs r4 = r3     // Catch:{ Exception -> 0x009a }
                        byte[] r4 = r4.getBinaryContent()     // Catch:{ Exception -> 0x009a }
                        r3.write(r4)     // Catch:{ Exception -> 0x009a }
                        r3.flush()     // Catch:{ Exception -> 0x009a }
                    L_0x001d:
                        java.net.HttpURLConnection r3 = r4     // Catch:{ IOException -> 0x0024 }
                        java.io.InputStream r3 = r3.getInputStream()     // Catch:{ IOException -> 0x0024 }
                        goto L_0x002c
                    L_0x0024:
                        java.net.HttpURLConnection r3 = r4     // Catch:{ Exception -> 0x002b }
                        java.io.InputStream r3 = r3.getErrorStream()     // Catch:{ Exception -> 0x002b }
                        goto L_0x002c
                    L_0x002b:
                        r3 = r1
                    L_0x002c:
                        if (r3 == 0) goto L_0x0056
                        java.net.HttpURLConnection r4 = r4     // Catch:{ Exception -> 0x009a }
                        java.lang.String r4 = r4.getContentEncoding()     // Catch:{ Exception -> 0x009a }
                        java.lang.String r5 = "gzip"
                        boolean r5 = r5.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x009a }
                        if (r5 == 0) goto L_0x0043
                        java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ Exception -> 0x009a }
                        r4.<init>(r3)     // Catch:{ Exception -> 0x009a }
                        r3 = r4
                        goto L_0x0056
                    L_0x0043:
                        java.lang.String r5 = "deflate"
                        boolean r4 = r5.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x009a }
                        if (r4 == 0) goto L_0x0056
                        java.util.zip.Inflater r4 = new java.util.zip.Inflater     // Catch:{ Exception -> 0x009a }
                        r4.<init>(r0)     // Catch:{ Exception -> 0x009a }
                        java.util.zip.InflaterInputStream r5 = new java.util.zip.InflaterInputStream     // Catch:{ Exception -> 0x009a }
                        r5.<init>(r3, r4)     // Catch:{ Exception -> 0x009a }
                        r3 = r5
                    L_0x0056:
                        fm.liveswitch.HttpResponseArgs r4 = r5     // Catch:{ Exception -> 0x009a }
                        java.net.HttpURLConnection r5 = r4     // Catch:{ Exception -> 0x009a }
                        int r5 = r5.getResponseCode()     // Catch:{ Exception -> 0x009a }
                        r4.setStatusCode(r5)     // Catch:{ Exception -> 0x009a }
                        r4 = 0
                    L_0x0062:
                        java.net.HttpURLConnection r5 = r4     // Catch:{ Exception -> 0x009a }
                        java.lang.String r5 = r5.getHeaderFieldKey(r4)     // Catch:{ Exception -> 0x009a }
                        java.net.HttpURLConnection r6 = r4     // Catch:{ Exception -> 0x009a }
                        java.lang.String r6 = r6.getHeaderField(r4)     // Catch:{ Exception -> 0x009a }
                        if (r5 != 0) goto L_0x008c
                        if (r6 != 0) goto L_0x008c
                        if (r3 == 0) goto L_0x008a
                        r4 = 16384(0x4000, float:2.2959E-41)
                        byte[] r5 = new byte[r4]     // Catch:{ Exception -> 0x009a }
                    L_0x0078:
                        int r6 = r3.read(r5, r2, r4)     // Catch:{ Exception -> 0x009a }
                        r7 = -1
                        if (r6 == r7) goto L_0x0085
                        java.io.ByteArrayOutputStream r7 = r6     // Catch:{ Exception -> 0x009a }
                        r7.write(r5, r2, r6)     // Catch:{ Exception -> 0x009a }
                        goto L_0x0078
                    L_0x0085:
                        java.io.ByteArrayOutputStream r3 = r6     // Catch:{ Exception -> 0x009a }
                        r3.flush()     // Catch:{ Exception -> 0x009a }
                    L_0x008a:
                        r3 = r1
                        goto L_0x009b
                    L_0x008c:
                        if (r5 == 0) goto L_0x0097
                        fm.liveswitch.HttpResponseArgs r7 = r5     // Catch:{ Exception -> 0x009a }
                        java.util.HashMap r7 = r7.getHeaders()     // Catch:{ Exception -> 0x009a }
                        r7.put(r5, r6)     // Catch:{ Exception -> 0x009a }
                    L_0x0097:
                        int r4 = r4 + 1
                        goto L_0x0062
                    L_0x009a:
                        r3 = move-exception
                    L_0x009b:
                        fm.liveswitch.ManagedCondition r4 = r7
                        monitor-enter(r4)
                        boolean[] r5 = r8     // Catch:{ all -> 0x00b5 }
                        r5[r2] = r0     // Catch:{ all -> 0x00b5 }
                        java.lang.RuntimeException[] r0 = r9     // Catch:{ all -> 0x00b5 }
                        if (r3 != 0) goto L_0x00a7
                        goto L_0x00ac
                    L_0x00a7:
                        java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x00b5 }
                        r1.<init>(r3)     // Catch:{ all -> 0x00b5 }
                    L_0x00ac:
                        r0[r2] = r1     // Catch:{ all -> 0x00b5 }
                        fm.liveswitch.ManagedCondition r0 = r7     // Catch:{ all -> 0x00b5 }
                        r0.pulse()     // Catch:{ all -> 0x00b5 }
                        monitor-exit(r4)     // Catch:{ all -> 0x00b5 }
                        return
                    L_0x00b5:
                        r0 = move-exception
                        monitor-exit(r4)     // Catch:{ all -> 0x00b5 }
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.HttpWebRequestTransfer.AnonymousClass1.run():void");
                }
            };
            executorService.submit(r0);
            managedCondition.halt(httpRequestArgs.getTimeout());
            runtimeException = runtimeExceptionArr[0];
            z = zArr[0];
        }
        if (runtimeException == null) {
            if (z) {
                httpResponseArgs.setBinaryContent(byteArrayOutputStream.toByteArray());
            }
            if (httpRequestArgs.getOnResponseReceived() != null) {
                HttpResponseReceivedArgs httpResponseReceivedArgs = new HttpResponseReceivedArgs();
                httpResponseReceivedArgs.setSender(httpRequestArgs.getSender());
                httpResponseReceivedArgs.setResponse(httpURLConnection);
                httpResponseReceivedArgs.setRequestArgs(httpRequestArgs);
                httpRequestArgs.getOnResponseReceived().invoke(httpResponseReceivedArgs);
            }
            return httpResponseArgs;
        }
        throw runtimeException;
    }

    public void sendBinaryAsync(final HttpRequestArgs httpRequestArgs, final IAction1<HttpResponseArgs> iAction1) {
        this.exec.submit(new Runnable() {
            public void run() {
                try {
                    iAction1.invoke(this.sendBinary(httpRequestArgs));
                } catch (Exception e) {
                    HttpResponseArgs httpResponseArgs = new HttpResponseArgs(httpRequestArgs);
                    httpResponseArgs.setException(e);
                    iAction1.invoke(httpResponseArgs);
                }
            }
        });
    }

    public HttpResponseArgs sendText(HttpRequestArgs httpRequestArgs) {
        if (httpRequestArgs.getTextContent() != null) {
            httpRequestArgs.setBinaryContent(Encoding.getUtf8().getBytes(httpRequestArgs.getTextContent()));
        }
        HttpResponseArgs sendBinary = sendBinary(httpRequestArgs);
        String contentType = getContentType(sendBinary);
        if (!(sendBinary.getBinaryContent() == null || contentType == null || contentType.equals("application/octet-stream"))) {
            try {
                sendBinary.setTextContent(Encoding.getUtf8().getString(sendBinary.getBinaryContent(), 0, sendBinary.getBinaryContent().length));
            } catch (Exception unused) {
            }
        }
        return sendBinary;
    }

    public void sendTextAsync(HttpRequestArgs httpRequestArgs, final IAction1<HttpResponseArgs> iAction1) {
        if (httpRequestArgs.getTextContent() != null) {
            httpRequestArgs.setBinaryContent(Encoding.getUtf8().getBytes(httpRequestArgs.getTextContent()));
        }
        sendBinaryAsync(httpRequestArgs, new IAction1<HttpResponseArgs>() {
            public void invoke(HttpResponseArgs httpResponseArgs) {
                String access$000 = HttpWebRequestTransfer.this.getContentType(httpResponseArgs);
                if (!(httpResponseArgs.getBinaryContent() == null || access$000 == null || access$000.equals("application/octet-stream"))) {
                    try {
                        httpResponseArgs.setTextContent(Encoding.getUtf8().getString(httpResponseArgs.getBinaryContent(), 0, httpResponseArgs.getBinaryContent().length));
                    } catch (Exception unused) {
                    }
                }
                iAction1.invoke(httpResponseArgs);
            }
        });
    }

    /* access modifiers changed from: private */
    public String getContentType(HttpResponseArgs httpResponseArgs) {
        for (String next : httpResponseArgs.getHeaders().keySet()) {
            if (next.equalsIgnoreCase("content-type")) {
                return httpResponseArgs.getHeaders().get(next);
            }
        }
        return null;
    }
}
