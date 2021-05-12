package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Marker;

public abstract class HttpTransfer {
    private static ILog __log = Log.getLogger(HttpTransfer.class);
    /* access modifiers changed from: private */
    public static List<IAction1<HttpSendFinishArgs>> __onSendFinish = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction1<HttpSendStartArgs>> __onSendStart = new ArrayList();
    private static String __wildcardCharacters = "abcdefghijklmnopqrstuvwxyz";
    private static IAction1<HttpSendFinishArgs> _onSendFinish = null;
    private static IAction1<HttpSendStartArgs> _onSendStart = null;

    public abstract HttpResponseArgs sendBinary(HttpRequestArgs httpRequestArgs);

    public abstract void sendBinaryAsync(HttpRequestArgs httpRequestArgs, IAction1<HttpResponseArgs> iAction1);

    public abstract HttpResponseArgs sendText(HttpRequestArgs httpRequestArgs);

    public abstract void sendTextAsync(HttpRequestArgs httpRequestArgs, IAction1<HttpResponseArgs> iAction1);

    public abstract void shutdown();

    public static void addOnSendFinish(IAction1<HttpSendFinishArgs> iAction1) {
        if (iAction1 != null) {
            if (_onSendFinish == null) {
                _onSendFinish = new IAction1<HttpSendFinishArgs>() {
                    public void invoke(HttpSendFinishArgs httpSendFinishArgs) {
                        Iterator it = new ArrayList(HttpTransfer.__onSendFinish).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(httpSendFinishArgs);
                        }
                    }
                };
            }
            __onSendFinish.add(iAction1);
        }
    }

    public static void addOnSendStart(IAction1<HttpSendStartArgs> iAction1) {
        if (iAction1 != null) {
            if (_onSendStart == null) {
                _onSendStart = new IAction1<HttpSendStartArgs>() {
                    public void invoke(HttpSendStartArgs httpSendStartArgs) {
                        Iterator it = new ArrayList(HttpTransfer.__onSendStart).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(httpSendStartArgs);
                        }
                    }
                };
            }
            __onSendStart.add(iAction1);
        }
    }

    public static String addQueryToUrl(String str, String str2) {
        return addQueryToUrl(str, str2, (String) null);
    }

    public static String addQueryToUrl(String str, String str2, String str3) {
        if (StringExtensions.isNullOrEmpty(str2)) {
            return str;
        }
        if (str3 == null) {
            str3 = StringExtensions.empty;
        }
        String escapeDataString = UriExtensions.escapeDataString(str2);
        String escapeDataString2 = UriExtensions.escapeDataString(str3);
        String[] strArr = new String[5];
        strArr[0] = str;
        String str4 = "?";
        if (StringExtensions.indexOf(str, str4, StringComparison.OrdinalIgnoreCase) != -1) {
            str4 = "&";
        }
        strArr[1] = str4;
        strArr[2] = escapeDataString;
        strArr[3] = "=";
        strArr[4] = escapeDataString2;
        return StringExtensions.concat((Object[]) strArr);
    }

    private HttpResponseArgs doSend(HttpRequestArgs httpRequestArgs) {
        HttpResponseArgs httpResponseArgs;
        startRequest(httpRequestArgs);
        try {
            if (httpRequestArgs.getBinaryContent() != null) {
                httpResponseArgs = sendBinary(httpRequestArgs);
            } else {
                httpResponseArgs = sendText(httpRequestArgs);
            }
        } catch (Exception e) {
            HttpResponseArgs httpResponseArgs2 = new HttpResponseArgs(httpRequestArgs);
            httpResponseArgs2.setException(e);
            httpResponseArgs = httpResponseArgs2;
        }
        finishRequest(httpResponseArgs);
        return httpResponseArgs;
    }

    private void doSendAsync(HttpRequestArgs httpRequestArgs, IAction1<HttpResponseArgs> iAction1, int i, String str) {
        startRequest(httpRequestArgs);
        try {
            if (httpRequestArgs.getBinaryContent() != null) {
                final HttpRequestArgs httpRequestArgs2 = httpRequestArgs;
                final IAction1<HttpResponseArgs> iAction12 = iAction1;
                final int i2 = i;
                final String str2 = str;
                sendBinaryAsync(httpRequestArgs, new IAction1<HttpResponseArgs>() {
                    public void invoke(HttpResponseArgs httpResponseArgs) {
                        HttpTransfer.this.finishRequest(httpResponseArgs);
                        HttpTransfer.this.sendAsyncCallback(httpRequestArgs2, httpResponseArgs, iAction12, i2, str2);
                    }
                });
                return;
            }
            final HttpRequestArgs httpRequestArgs3 = httpRequestArgs;
            final IAction1<HttpResponseArgs> iAction13 = iAction1;
            final int i3 = i;
            final String str3 = str;
            sendTextAsync(httpRequestArgs, new IAction1<HttpResponseArgs>() {
                public void invoke(HttpResponseArgs httpResponseArgs) {
                    HttpTransfer.this.finishRequest(httpResponseArgs);
                    HttpTransfer.this.sendAsyncCallback(httpRequestArgs3, httpResponseArgs, iAction13, i3, str3);
                }
            });
        } catch (Exception e) {
            HttpResponseArgs httpResponseArgs = new HttpResponseArgs(httpRequestArgs);
            httpResponseArgs.setException(e);
            sendAsyncCallback(httpRequestArgs, httpResponseArgs, iAction1, i, str);
        }
    }

    /* access modifiers changed from: private */
    public void finishRequest(HttpResponseArgs httpResponseArgs) {
        raiseOnSendFinish(httpResponseArgs);
    }

    public static String getRandomWildcardCharacter() {
        String wildcardCharacters = getWildcardCharacters();
        return StringExtensions.substring(wildcardCharacters, new Randomizer().next(StringExtensions.getLength(wildcardCharacters)), 1);
    }

    public static String getWildcardCharacters() {
        return __wildcardCharacters;
    }

    protected HttpTransfer() {
    }

    private void raiseOnSendFinish(HttpResponseArgs httpResponseArgs) {
        IAction1<HttpSendFinishArgs> iAction1 = _onSendFinish;
        if (iAction1 != null) {
            HttpSendFinishArgs httpSendFinishArgs = new HttpSendFinishArgs();
            httpSendFinishArgs.setSender(httpResponseArgs.getRequestArgs().getSender());
            httpSendFinishArgs.setRequestBinaryContent(httpResponseArgs.getRequestArgs().getBinaryContent());
            httpSendFinishArgs.setRequestTextContent(httpResponseArgs.getRequestArgs().getTextContent());
            httpSendFinishArgs.setResponseBinaryContent(httpResponseArgs.getBinaryContent());
            httpSendFinishArgs.setResponseTextContent(httpResponseArgs.getTextContent());
            httpSendFinishArgs.setResponseHeaders(httpResponseArgs.getHeaders());
            iAction1.invoke(httpSendFinishArgs);
        }
    }

    private void raiseOnSendStart(HttpRequestArgs httpRequestArgs) {
        IAction1<HttpSendStartArgs> iAction1 = _onSendStart;
        if (iAction1 != null) {
            HttpSendStartArgs httpSendStartArgs = new HttpSendStartArgs();
            httpSendStartArgs.setSender(httpRequestArgs.getSender());
            httpSendStartArgs.setRequestBinaryContent(httpRequestArgs.getBinaryContent());
            httpSendStartArgs.setRequestTextContent(httpRequestArgs.getTextContent());
            iAction1.invoke(httpSendStartArgs);
        }
    }

    public static void removeOnSendFinish(IAction1<HttpSendFinishArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(__onSendFinish, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        __onSendFinish.remove(iAction1);
        if (__onSendFinish.size() == 0) {
            _onSendFinish = null;
        }
    }

    public static void removeOnSendStart(IAction1<HttpSendStartArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(__onSendStart, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        __onSendStart.remove(iAction1);
        if (__onSendStart.size() == 0) {
            _onSendStart = null;
        }
    }

    public static String replaceWildcards(String str) {
        return str.replace(Marker.ANY_MARKER, getRandomWildcardCharacter());
    }

    public HttpResponseArgs send(HttpRequestArgs httpRequestArgs) {
        HttpResponseArgs doSend = doSend(httpRequestArgs);
        int i = 0;
        String str = null;
        while (doSend.getException() != null && i < httpRequestArgs.getMaxRetries()) {
            i++;
            str = Guid.newGuid().toString().replace("-", "");
            __log.warn(str, StringExtensions.format("Synchronous HTTP request failed, but retrying... (attempt #{0}).", (Object) IntegerExtensions.toString(Integer.valueOf(i))), doSend.getException());
            doSend = doSend(httpRequestArgs);
        }
        if (!(doSend.getException() == null || str == null)) {
            doSend.setRetries(i);
            __log.warn(str, "Synchronous HTTP request failed, and retries exhausted.", doSend.getException());
        }
        return doSend;
    }

    public void sendAsync(HttpRequestArgs httpRequestArgs, IAction1<HttpResponseArgs> iAction1) {
        doSendAsync(httpRequestArgs, iAction1, 0, (String) null);
    }

    /* access modifiers changed from: private */
    public void sendAsyncCallback(HttpRequestArgs httpRequestArgs, HttpResponseArgs httpResponseArgs, IAction1<HttpResponseArgs> iAction1, int i, String str) {
        if (httpResponseArgs.getException() == null || i >= httpRequestArgs.getMaxRetries()) {
            if (!(httpResponseArgs.getException() == null || str == null)) {
                httpResponseArgs.setRetries(i);
                __log.warn(str, "Asynchronous HTTP request failed, and retries exhausted.", httpResponseArgs.getException());
            }
            iAction1.invoke(httpResponseArgs);
            return;
        }
        int i2 = i + 1;
        String replace = Guid.newGuid().toString().replace("-", "");
        __log.warn(replace, StringExtensions.format("Asynchronous HTTP request failed, but retrying... (attempt #{0}).", (Object) IntegerExtensions.toString(Integer.valueOf(i2))), httpResponseArgs.getException());
        doSendAsync(httpRequestArgs, iAction1, i2, replace);
    }

    public static void setWildcardCharacters(String str) {
        __wildcardCharacters = str;
    }

    private void startRequest(HttpRequestArgs httpRequestArgs) {
        raiseOnSendStart(httpRequestArgs);
    }
}
