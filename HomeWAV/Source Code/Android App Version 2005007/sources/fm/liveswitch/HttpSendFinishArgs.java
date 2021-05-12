package fm.liveswitch;

import java.util.HashMap;

public class HttpSendFinishArgs {
    private byte[] _requestBinaryContent;
    private String _requestTextContent;
    private byte[] _responseBinaryContent;
    private HashMap<String, String> _responseHeaders;
    private String _responseTextContent;
    private Object _sender;

    public byte[] getRequestBinaryContent() {
        return this._requestBinaryContent;
    }

    public String getRequestTextContent() {
        return this._requestTextContent;
    }

    public byte[] getResponseBinaryContent() {
        return this._responseBinaryContent;
    }

    public HashMap<String, String> getResponseHeaders() {
        return this._responseHeaders;
    }

    public String getResponseTextContent() {
        return this._responseTextContent;
    }

    public Object getSender() {
        return this._sender;
    }

    /* access modifiers changed from: package-private */
    public void setRequestBinaryContent(byte[] bArr) {
        this._requestBinaryContent = bArr;
    }

    /* access modifiers changed from: package-private */
    public void setRequestTextContent(String str) {
        this._requestTextContent = str;
    }

    /* access modifiers changed from: package-private */
    public void setResponseBinaryContent(byte[] bArr) {
        this._responseBinaryContent = bArr;
    }

    /* access modifiers changed from: package-private */
    public void setResponseHeaders(HashMap<String, String> hashMap) {
        this._responseHeaders = hashMap;
    }

    /* access modifiers changed from: package-private */
    public void setResponseTextContent(String str) {
        this._responseTextContent = str;
    }

    /* access modifiers changed from: package-private */
    public void setSender(Object obj) {
        this._sender = obj;
    }
}
