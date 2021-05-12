package fm.liveswitch;

public class HttpSendStartArgs {
    private byte[] _requestBinaryContent;
    private String _requestTextContent;
    private Object _sender;

    public byte[] getRequestBinaryContent() {
        return this._requestBinaryContent;
    }

    public String getRequestTextContent() {
        return this._requestTextContent;
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
    public void setSender(Object obj) {
        this._sender = obj;
    }
}
