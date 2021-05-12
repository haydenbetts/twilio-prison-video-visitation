package fm.liveswitch;

import com.microsoft.appcenter.http.DefaultHttpClient;

abstract class SignallingMessageTransfer {
    private String _requestArgsKey = "fm.liveswitch.signalling.messageTransfer.requestArgs";

    public abstract SignallingMessageResponseArgs sendMessages(SignallingMessageRequestArgs signallingMessageRequestArgs);

    public abstract void sendMessagesAsync(SignallingMessageRequestArgs signallingMessageRequestArgs, IAction1<SignallingMessageResponseArgs> iAction1);

    public abstract void shutdown();

    public SignallingMessageResponseArgs httpResponseArgsToMessageResponseArgs(HttpResponseArgs httpResponseArgs) {
        SignallingMessageResponseArgs signallingMessageResponseArgs = new SignallingMessageResponseArgs((SignallingMessageRequestArgs) httpResponseArgs.getRequestArgs().getDynamicValue(this._requestArgsKey));
        signallingMessageResponseArgs.setStatusCode(httpResponseArgs.getStatusCode());
        signallingMessageResponseArgs.setTextContent(httpResponseArgs.getTextContent());
        signallingMessageResponseArgs.setBinaryContent(httpResponseArgs.getBinaryContent());
        signallingMessageResponseArgs.setException(httpResponseArgs.getException());
        signallingMessageResponseArgs.setRetries(httpResponseArgs.getRetries());
        signallingMessageResponseArgs.setHeaders(httpResponseArgs.getHeaders());
        if (!StringExtensions.isNullOrEmpty(httpResponseArgs.getTextContent())) {
            signallingMessageResponseArgs.setMessages(SignallingMessage.fromJsonArray(httpResponseArgs.getTextContent()));
            return signallingMessageResponseArgs;
        }
        if (httpResponseArgs.getBinaryContent() != null) {
            signallingMessageResponseArgs.setMessages(SignallingMessage.fromBinaryMultiple(httpResponseArgs.getBinaryContent()));
        }
        return signallingMessageResponseArgs;
    }

    public HttpRequestArgs messageRequestArgsToHttpRequestArgs(SignallingMessageRequestArgs signallingMessageRequestArgs) {
        HttpRequestArgs httpRequestArgs = new HttpRequestArgs();
        httpRequestArgs.setMethod(HttpMethod.Post);
        httpRequestArgs.setOnRequestCreated(signallingMessageRequestArgs.getOnHttpRequestCreated());
        httpRequestArgs.setOnResponseReceived(signallingMessageRequestArgs.getOnHttpResponseReceived());
        httpRequestArgs.setSender(signallingMessageRequestArgs.getSender());
        httpRequestArgs.setTimeout(signallingMessageRequestArgs.getTimeout());
        httpRequestArgs.setMaxRetries(signallingMessageRequestArgs.getMaxRetries());
        httpRequestArgs.setUrl(signallingMessageRequestArgs.getUrl());
        httpRequestArgs.setDynamicProperties(signallingMessageRequestArgs.getDynamicProperties());
        httpRequestArgs.setDynamicValue(this._requestArgsKey, signallingMessageRequestArgs);
        for (String next : HashMapExtensions.getAllKeys(signallingMessageRequestArgs.getHeaders())) {
            HashMapExtensions.set(HashMapExtensions.getItem(httpRequestArgs.getHeaders()), next, HashMapExtensions.getItem(signallingMessageRequestArgs.getHeaders()).get(next));
        }
        httpRequestArgs.setTextContent(SignallingMessage.toJsonArray(signallingMessageRequestArgs.getMessages()));
        HashMapExtensions.set(HashMapExtensions.getItem(httpRequestArgs.getHeaders()), DefaultHttpClient.CONTENT_TYPE_KEY, "application/json");
        if (signallingMessageRequestArgs.getIsBinary()) {
            httpRequestArgs.setBinaryContent(SignallingMessage.toBinaryMultiple(signallingMessageRequestArgs.getMessages()));
            HashMapExtensions.set(HashMapExtensions.getItem(httpRequestArgs.getHeaders()), DefaultHttpClient.CONTENT_TYPE_KEY, "application/octet-stream");
        }
        return httpRequestArgs;
    }

    private static void raiseRequestCreated(SignallingMessageRequestArgs signallingMessageRequestArgs) {
        if (signallingMessageRequestArgs.getOnRequestCreated() != null) {
            SignallingMessageRequestCreatedArgs signallingMessageRequestCreatedArgs = new SignallingMessageRequestCreatedArgs();
            signallingMessageRequestCreatedArgs.setRequests(signallingMessageRequestArgs.getMessages());
            signallingMessageRequestCreatedArgs.setSender(signallingMessageRequestArgs.getSender());
            signallingMessageRequestArgs.getOnRequestCreated().invoke(signallingMessageRequestCreatedArgs);
        }
    }

    /* access modifiers changed from: private */
    public static void raiseResponseReceived(SignallingMessageResponseArgs signallingMessageResponseArgs) {
        if (signallingMessageResponseArgs.getException() == null && signallingMessageResponseArgs.getRequestArgs().getOnResponseReceived() != null) {
            SignallingMessageResponseReceivedArgs signallingMessageResponseReceivedArgs = new SignallingMessageResponseReceivedArgs();
            signallingMessageResponseReceivedArgs.setResponses(signallingMessageResponseArgs.getMessages());
            signallingMessageResponseReceivedArgs.setSender(signallingMessageResponseArgs.getRequestArgs().getSender());
            signallingMessageResponseArgs.getRequestArgs().getOnResponseReceived().invoke(signallingMessageResponseReceivedArgs);
        }
    }

    public SignallingMessageResponseArgs send(SignallingMessageRequestArgs signallingMessageRequestArgs) {
        raiseRequestCreated(signallingMessageRequestArgs);
        try {
            SignallingMessageResponseArgs sendMessages = sendMessages(signallingMessageRequestArgs);
            raiseResponseReceived(sendMessages);
            return sendMessages;
        } catch (Exception e) {
            SignallingMessageResponseArgs signallingMessageResponseArgs = new SignallingMessageResponseArgs(signallingMessageRequestArgs);
            signallingMessageResponseArgs.setException(e);
            return signallingMessageResponseArgs;
        }
    }

    public void sendAsync(SignallingMessageRequestArgs signallingMessageRequestArgs, final IAction1<SignallingMessageResponseArgs> iAction1) {
        raiseRequestCreated(signallingMessageRequestArgs);
        try {
            sendMessagesAsync(signallingMessageRequestArgs, new IAction1<SignallingMessageResponseArgs>() {
                public void invoke(SignallingMessageResponseArgs signallingMessageResponseArgs) {
                    SignallingMessageTransfer.raiseResponseReceived(signallingMessageResponseArgs);
                    iAction1.invoke(signallingMessageResponseArgs);
                }
            });
        } catch (Exception e) {
            SignallingMessageResponseArgs signallingMessageResponseArgs = new SignallingMessageResponseArgs(signallingMessageRequestArgs);
            signallingMessageResponseArgs.setException(e);
            iAction1.invoke(signallingMessageResponseArgs);
        }
    }

    protected SignallingMessageTransfer() {
    }
}
