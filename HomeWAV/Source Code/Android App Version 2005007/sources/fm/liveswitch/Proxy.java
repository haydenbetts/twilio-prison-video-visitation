package fm.liveswitch;

import java.net.URI;

public class Proxy {
    private static ProxyCredentialsCache __proxyCredentialsCache = new ProxyCredentialsCache();

    private static Future<ProxyAuthCheckResult> authCheck(final Promise<ProxyAuthCheckResult> promise, final String str) {
        HttpRequestArgs httpRequestArgs = new HttpRequestArgs();
        httpRequestArgs.setMethod(HttpMethod.Get);
        httpRequestArgs.setUrl(str);
        HttpTransferFactory.getHttpTransfer().sendAsync(httpRequestArgs, new IAction1<HttpResponseArgs>() {
            public void invoke(HttpResponseArgs httpResponseArgs) {
                URI uri;
                if (httpResponseArgs.getException() != null) {
                    promise.reject(httpResponseArgs.getException());
                } else if (httpResponseArgs.getStatusCode() == 407) {
                    try {
                        uri = new URI(str);
                    } catch (Exception unused) {
                        uri = null;
                    }
                    if (uri == null) {
                        promise.resolve(new ProxyAuthCheckResult(true, (String[]) null));
                        return;
                    }
                    Dns.resolve(UriExtensions.getDnsSafeHost(uri)).then(new IAction1<String[]>() {
                        public void invoke(String[] strArr) {
                            promise.resolve(new ProxyAuthCheckResult(true, strArr));
                        }
                    }, (IAction1<Exception>) new IAction1<Exception>() {
                        public void invoke(Exception exc) {
                            promise.resolve(new ProxyAuthCheckResult(true, (String[]) null));
                        }
                    });
                } else {
                    promise.resolve(new ProxyAuthCheckResult(false, (String[]) null));
                }
            }
        });
        return promise;
    }

    public static Future<ProxyAuthCheckResult> authCheck(String str) {
        return authCheck(new Promise(), str);
    }

    public static String exportCredentials() {
        return __proxyCredentialsCache.toJson();
    }

    public static ProxyCredentials getCredentials(String str) {
        return __proxyCredentialsCache.getCredentials(str);
    }

    public static void importCredentials(String str) {
        __proxyCredentialsCache = ProxyCredentialsCache.fromJson(str) != null ? ProxyCredentialsCache.fromJson(str) : __proxyCredentialsCache;
    }

    public static void setCredentials(String str, ProxyCredentials proxyCredentials) {
        __proxyCredentialsCache.setCredentials(str, proxyCredentials);
    }

    public static void setCredentials(String[] strArr, ProxyCredentials proxyCredentials) {
        __proxyCredentialsCache.setCredentials(strArr, proxyCredentials);
    }
}
