package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

abstract class SignallingClientBase extends HttpWebRequestSender {
    private static HashMap<String, String> _headers;
    private String __domainName;
    /* access modifiers changed from: private */
    public List<IAction1<HttpRequestCreatedArgs>> __onHttpRequestCreated = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<HttpResponseReceivedArgs>> __onHttpResponseReceived = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<SignallingMessageRequestCreatedArgs>> __onRequestCreated = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<SignallingMessageResponseReceivedArgs>> __onResponseReceived = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<UnhandledExceptionArgs>> __onUnhandledException = new ArrayList();
    private String __requestUrl;
    private SignallingConcurrencyMode _concurrencyMode;
    private boolean _disableBinary;
    private String _domainKey;
    private IAction1<HttpRequestCreatedArgs> _onHttpRequestCreated = null;
    private IAction1<HttpResponseReceivedArgs> _onHttpResponseReceived = null;
    private IAction1<SignallingMessageRequestCreatedArgs> _onRequestCreated = null;
    private IAction1<SignallingMessageResponseReceivedArgs> _onResponseReceived = null;
    private IAction1<UnhandledExceptionArgs> _onUnhandledException = null;
    private int _requestMaxRetries;
    private int _requestTimeout;

    public void addOnHttpRequestCreated(IAction1<HttpRequestCreatedArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onHttpRequestCreated == null) {
                this._onHttpRequestCreated = new IAction1<HttpRequestCreatedArgs>() {
                    public void invoke(HttpRequestCreatedArgs httpRequestCreatedArgs) {
                        Iterator it = new ArrayList(SignallingClientBase.this.__onHttpRequestCreated).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(httpRequestCreatedArgs);
                        }
                    }
                };
            }
            this.__onHttpRequestCreated.add(iAction1);
        }
    }

    public void addOnHttpResponseReceived(IAction1<HttpResponseReceivedArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onHttpResponseReceived == null) {
                this._onHttpResponseReceived = new IAction1<HttpResponseReceivedArgs>() {
                    public void invoke(HttpResponseReceivedArgs httpResponseReceivedArgs) {
                        Iterator it = new ArrayList(SignallingClientBase.this.__onHttpResponseReceived).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(httpResponseReceivedArgs);
                        }
                    }
                };
            }
            this.__onHttpResponseReceived.add(iAction1);
        }
    }

    public void addOnRequestCreated(IAction1<SignallingMessageRequestCreatedArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onRequestCreated == null) {
                this._onRequestCreated = new IAction1<SignallingMessageRequestCreatedArgs>() {
                    public void invoke(SignallingMessageRequestCreatedArgs signallingMessageRequestCreatedArgs) {
                        Iterator it = new ArrayList(SignallingClientBase.this.__onRequestCreated).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingMessageRequestCreatedArgs);
                        }
                    }
                };
            }
            this.__onRequestCreated.add(iAction1);
        }
    }

    public void addOnResponseReceived(IAction1<SignallingMessageResponseReceivedArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onResponseReceived == null) {
                this._onResponseReceived = new IAction1<SignallingMessageResponseReceivedArgs>() {
                    public void invoke(SignallingMessageResponseReceivedArgs signallingMessageResponseReceivedArgs) {
                        Iterator it = new ArrayList(SignallingClientBase.this.__onResponseReceived).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingMessageResponseReceivedArgs);
                        }
                    }
                };
            }
            this.__onResponseReceived.add(iAction1);
        }
    }

    public void addOnUnhandledException(IAction1<UnhandledExceptionArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onUnhandledException == null) {
                this._onUnhandledException = new IAction1<UnhandledExceptionArgs>() {
                    public void invoke(UnhandledExceptionArgs unhandledExceptionArgs) {
                        Iterator it = new ArrayList(SignallingClientBase.this.__onUnhandledException).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(unhandledExceptionArgs);
                        }
                    }
                };
            }
            this.__onUnhandledException.add(iAction1);
        }
    }

    /* access modifiers changed from: protected */
    public HashMap<String, String> createHeaders() {
        if (!Global.equals(getConcurrencyMode(), SignallingConcurrencyMode.High)) {
            return createHeadersNoCache();
        }
        if (_headers == null) {
            _headers = createHeadersNoCache();
        }
        return _headers;
    }

    private HashMap<String, String> createHeadersNoCache() {
        HashMap<String, String> hashMap = new HashMap<>();
        if (!Global.equals(getDomainName(), SignallingDefaults.getDomainName())) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "X-FM-DomainName", getDomainName());
        }
        if (!Global.equals(getDomainKey(), SignallingDefaults.getDomainKey())) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "X-FM-DomainKey", getDomainKey());
        }
        return hashMap;
    }

    public SignallingConcurrencyMode getConcurrencyMode() {
        return this._concurrencyMode;
    }

    public boolean getDisableBinary() {
        return this._disableBinary;
    }

    public String getDomainKey() {
        return this._domainKey;
    }

    public String getDomainName() {
        return this.__domainName;
    }

    /* access modifiers changed from: protected */
    public String getEmptyResponseMessage(SignallingMessageResponseArgs signallingMessageResponseArgs) {
        String format = StringExtensions.format("Empty response received from the server with status code {0}.", (Object) IntegerExtensions.toString(Integer.valueOf(signallingMessageResponseArgs.getStatusCode())));
        if (signallingMessageResponseArgs.getTextContent() != null) {
            return StringExtensions.format("{0} Response body (text): {1}", format, signallingMessageResponseArgs.getTextContent());
        }
        return signallingMessageResponseArgs.getBinaryContent() != null ? StringExtensions.format("{0} Response body (binary): {1}", format, BitAssistant.getHexString(signallingMessageResponseArgs.getBinaryContent())) : format;
    }

    /* access modifiers changed from: protected */
    public String getInvalidResponseMessage(SignallingMessageResponseArgs signallingMessageResponseArgs) {
        String format = StringExtensions.format("Invalid response received from the server with status code {0}.", (Object) IntegerExtensions.toString(Integer.valueOf(signallingMessageResponseArgs.getStatusCode())));
        if (signallingMessageResponseArgs.getTextContent() != null) {
            return StringExtensions.format("{0} Response body (text): {1}", format, signallingMessageResponseArgs.getTextContent());
        }
        return signallingMessageResponseArgs.getBinaryContent() != null ? StringExtensions.format("{0} Response body (binary): {1}", format, BitAssistant.getHexString(signallingMessageResponseArgs.getBinaryContent())) : format;
    }

    public int getRequestMaxRetries() {
        return this._requestMaxRetries;
    }

    public int getRequestTimeout() {
        return this._requestTimeout;
    }

    public String getRequestUrl() {
        return this.__requestUrl;
    }

    /* access modifiers changed from: package-private */
    public void internalOnHttpRequestCreated(HttpRequestCreatedArgs httpRequestCreatedArgs) {
        raiseBaseEvent(this._onHttpRequestCreated, httpRequestCreatedArgs, "OnHttpRequestCreated");
    }

    /* access modifiers changed from: package-private */
    public void internalOnHttpResponseReceived(HttpResponseReceivedArgs httpResponseReceivedArgs) {
        raiseBaseEvent(this._onHttpResponseReceived, httpResponseReceivedArgs, "OnHttpResponseReceived");
    }

    /* access modifiers changed from: package-private */
    public void internalOnRequestCreated(SignallingMessageRequestCreatedArgs signallingMessageRequestCreatedArgs) {
        raiseBaseEvent(this._onRequestCreated, signallingMessageRequestCreatedArgs, "OnRequestCreated");
    }

    /* access modifiers changed from: package-private */
    public void internalOnResponseReceived(SignallingMessageResponseReceivedArgs signallingMessageResponseReceivedArgs) {
        raiseBaseEvent(this._onResponseReceived, signallingMessageResponseReceivedArgs, "OnResponseReceived");
    }

    private <T> void raiseBaseEvent(IAction1<T> iAction1, T t, String str) {
        if (iAction1 != null) {
            try {
                iAction1.invoke(t);
            } catch (Exception e) {
                if (!raiseUnhandledException(e)) {
                    Unhandled.logException(e, StringExtensions.format("BaseClient -> {0}", (Object) str));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean raiseUnhandledException(Exception exc) {
        IAction1<UnhandledExceptionArgs> iAction1 = this._onUnhandledException;
        if (iAction1 == null) {
            return false;
        }
        UnhandledExceptionArgs unhandledExceptionArgs = new UnhandledExceptionArgs(exc);
        try {
            iAction1.invoke(unhandledExceptionArgs);
        } catch (Exception e) {
            Unhandled.logException(e, "BaseClient -> OnUnhandledException");
        }
        return unhandledExceptionArgs.getHandled();
    }

    public void removeOnHttpRequestCreated(IAction1<HttpRequestCreatedArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onHttpRequestCreated, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onHttpRequestCreated.remove(iAction1);
        if (this.__onHttpRequestCreated.size() == 0) {
            this._onHttpRequestCreated = null;
        }
    }

    public void removeOnHttpResponseReceived(IAction1<HttpResponseReceivedArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onHttpResponseReceived, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onHttpResponseReceived.remove(iAction1);
        if (this.__onHttpResponseReceived.size() == 0) {
            this._onHttpResponseReceived = null;
        }
    }

    public void removeOnRequestCreated(IAction1<SignallingMessageRequestCreatedArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRequestCreated, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRequestCreated.remove(iAction1);
        if (this.__onRequestCreated.size() == 0) {
            this._onRequestCreated = null;
        }
    }

    public void removeOnResponseReceived(IAction1<SignallingMessageResponseReceivedArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onResponseReceived, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onResponseReceived.remove(iAction1);
        if (this.__onResponseReceived.size() == 0) {
            this._onResponseReceived = null;
        }
    }

    public void removeOnUnhandledException(IAction1<UnhandledExceptionArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onUnhandledException, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onUnhandledException.remove(iAction1);
        if (this.__onUnhandledException.size() == 0) {
            this._onUnhandledException = null;
        }
    }

    private static String sanitizeDomainName(String str) {
        if (StringExtensions.startsWith(str, "http://", StringComparison.Ordinal)) {
            str = str.substring(StringExtensions.getLength("http://"));
        } else if (StringExtensions.startsWith(str, "https://", StringComparison.Ordinal)) {
            str = str.substring(StringExtensions.getLength("https://"));
        }
        int indexOf = StringExtensions.indexOf(str, "/", StringComparison.InvariantCulture);
        return indexOf != -1 ? StringExtensions.substring(str, 0, indexOf) : str;
    }

    public void setConcurrencyMode(SignallingConcurrencyMode signallingConcurrencyMode) {
        this._concurrencyMode = signallingConcurrencyMode;
    }

    public void setDisableBinary(boolean z) {
        this._disableBinary = z;
    }

    public void setDomainKey(String str) {
        this._domainKey = str;
    }

    public void setDomainName(String str) {
        if (str == null) {
            str = SignallingDefaults.getDomainName();
        }
        this.__domainName = sanitizeDomainName(str);
    }

    public void setRequestMaxRetries(int i) {
        this._requestMaxRetries = i;
    }

    public void setRequestTimeout(int i) {
        this._requestTimeout = i;
    }

    public void setRequestUrl(String str) {
        if (str != null) {
            this.__requestUrl = HttpTransfer.replaceWildcards(str);
            return;
        }
        throw new RuntimeException(new Exception("Request URL cannot be null."));
    }

    protected SignallingClientBase() {
        setRequestTimeout(15000);
        setDomainKey(SignallingDefaults.getDomainKey());
        setDomainName(SignallingDefaults.getDomainName());
        setConcurrencyMode(SignallingConcurrencyMode.Low);
    }
}
