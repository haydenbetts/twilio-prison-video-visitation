package fm.liveswitch;

abstract class SignallingPublisherArgs {
    private SignallingPublisher _publisher;

    public SignallingPublisher getPublisher() {
        return this._publisher;
    }

    public void setPublisher(SignallingPublisher signallingPublisher) {
        this._publisher = signallingPublisher;
    }

    protected SignallingPublisherArgs() {
    }
}
