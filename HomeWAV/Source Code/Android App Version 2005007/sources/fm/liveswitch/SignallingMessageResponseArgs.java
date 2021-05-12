package fm.liveswitch;

import java.util.HashMap;

class SignallingMessageResponseArgs extends Dynamic {
    private byte[] _binaryContent;
    private Exception _exception;
    private HashMap<String, String> _headers;
    private SignallingMessage[] _messages;
    private SignallingMessageRequestArgs _requestArgs;
    private int _retries;
    private int _statusCode;
    private String _textContent;

    public byte[] getBinaryContent() {
        return this._binaryContent;
    }

    public Exception getException() {
        return this._exception;
    }

    public HashMap<String, String> getHeaders() {
        return this._headers;
    }

    public SignallingMessage[] getMessages() {
        return this._messages;
    }

    public SignallingMessageRequestArgs getRequestArgs() {
        return this._requestArgs;
    }

    public int getRetries() {
        return this._retries;
    }

    public int getStatusCode() {
        return this._statusCode;
    }

    public String getTextContent() {
        return this._textContent;
    }

    public void setBinaryContent(byte[] bArr) {
        this._binaryContent = bArr;
    }

    public void setException(Exception exc) {
        this._exception = exc;
    }

    public void setHeaders(HashMap<String, String> hashMap) {
        this._headers = hashMap;
    }

    public void setMessages(SignallingMessage[] signallingMessageArr) {
        this._messages = signallingMessageArr;
    }

    public void setRequestArgs(SignallingMessageRequestArgs signallingMessageRequestArgs) {
        this._requestArgs = signallingMessageRequestArgs;
    }

    public void setRetries(int i) {
        this._retries = i;
    }

    public void setStatusCode(int i) {
        this._statusCode = i;
    }

    public void setTextContent(String str) {
        this._textContent = str;
    }

    public SignallingMessageResponseArgs(SignallingMessageRequestArgs signallingMessageRequestArgs) {
        setRequestArgs(signallingMessageRequestArgs);
    }
}
