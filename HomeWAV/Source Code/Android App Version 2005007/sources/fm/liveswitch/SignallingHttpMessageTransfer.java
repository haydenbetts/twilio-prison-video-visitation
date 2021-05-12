package fm.liveswitch;

class SignallingHttpMessageTransfer extends SignallingMessageTransfer {
    private HttpTransfer _httpTransfer = HttpTransferFactory.getHttpTransfer();

    public SignallingMessageResponseArgs sendMessages(SignallingMessageRequestArgs signallingMessageRequestArgs) {
        return super.httpResponseArgsToMessageResponseArgs(this._httpTransfer.send(super.messageRequestArgsToHttpRequestArgs(signallingMessageRequestArgs)));
    }

    public void sendMessagesAsync(SignallingMessageRequestArgs signallingMessageRequestArgs, final IAction1<SignallingMessageResponseArgs> iAction1) {
        this._httpTransfer.sendAsync(super.messageRequestArgsToHttpRequestArgs(signallingMessageRequestArgs), new IAction1<HttpResponseArgs>() {
            public void invoke(HttpResponseArgs httpResponseArgs) {
                iAction1.invoke(SignallingHttpMessageTransfer.this.httpResponseArgsToMessageResponseArgs(httpResponseArgs));
            }
        });
    }

    public void shutdown() {
        try {
            this._httpTransfer.shutdown();
        } catch (Exception unused) {
        }
    }
}
