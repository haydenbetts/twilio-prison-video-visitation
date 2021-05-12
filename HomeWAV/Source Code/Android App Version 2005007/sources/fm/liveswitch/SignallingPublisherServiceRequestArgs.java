package fm.liveswitch;

class SignallingPublisherServiceRequestArgs extends SignallingPublisherRequestArgsGeneric<SignallingMessage> {
    public SignallingPublisherServiceRequestArgs(SignallingMessage[] signallingMessageArr) {
        super(signallingMessageArr);
    }
}
