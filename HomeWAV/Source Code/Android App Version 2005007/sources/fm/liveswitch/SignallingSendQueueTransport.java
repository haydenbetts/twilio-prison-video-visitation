package fm.liveswitch;

class SignallingSendQueueTransport implements ISendQueueTransport {
    private SignallingClient __client;

    private void doSend(final SendItem sendItem, final Promise<SendItem> promise) {
        Message message;
        SignallingClient signallingClient = this.__client;
        if (signallingClient != null && sendItem != null && (message = sendItem.getMessage()) != null) {
            SignallingServiceArgs signallingServiceArgs = new SignallingServiceArgs();
            signallingServiceArgs.setChannel("/message");
            signallingServiceArgs.setDataJson(message.toJson());
            signallingServiceArgs.setOnSuccess(new IAction1<SignallingServiceSuccessArgs>() {
                public void invoke(SignallingServiceSuccessArgs signallingServiceSuccessArgs) {
                    sendItem.setRetry(false);
                    Message message = null;
                    sendItem.setException((Exception) null);
                    String dataJson = signallingServiceSuccessArgs.getDataJson();
                    SendItem sendItem = sendItem;
                    if (dataJson != null) {
                        message = Message.fromJson(dataJson);
                    }
                    sendItem.setMessage(message);
                    promise.resolve(sendItem);
                }
            });
            signallingServiceArgs.setOnFailure(new IAction1<SignallingServiceFailureArgs>() {
                public void invoke(SignallingServiceFailureArgs signallingServiceFailureArgs) {
                    sendItem.setException(signallingServiceFailureArgs.getException());
                    if (Global.equals(signallingServiceFailureArgs.getSource(), FailureSource.Network)) {
                        sendItem.setRetry(true);
                        promise.resolve(sendItem);
                        return;
                    }
                    promise.reject(signallingServiceFailureArgs.getException());
                }
            });
            signallingClient.service(signallingServiceArgs);
        }
    }

    public void endBatch() {
        this.__client.endBatch();
    }

    public boolean getIsConnected() {
        return Global.equals(this.__client.getState(), SignallingClientState.Connected);
    }

    public Future<SendItem> send(SendItem sendItem) {
        Promise promise = new Promise();
        doSend(sendItem, promise);
        return promise;
    }

    public SignallingSendQueueTransport(SignallingClient signallingClient) {
        this.__client = signallingClient;
    }

    public void startBatch() {
        this.__client.startBatch();
    }
}
