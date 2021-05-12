package fm.liveswitch;

import fm.liveswitch.stun.Message;

class IceSendResponseArgs {
    private TransportAddress _address;
    private IAction1<IceSendResponseCompleteArgs> _onComplete;
    private IAction1<IceSendResponseFailureArgs> _onFailure;
    private IAction1<IceSendResponseSuccessArgs> _onSuccess;
    private Message _response;
    private TransportAddress _turnRelay;

    public TransportAddress getAddress() {
        return this._address;
    }

    public IAction1<IceSendResponseCompleteArgs> getOnComplete() {
        return this._onComplete;
    }

    public IAction1<IceSendResponseFailureArgs> getOnFailure() {
        return this._onFailure;
    }

    public IAction1<IceSendResponseSuccessArgs> getOnSuccess() {
        return this._onSuccess;
    }

    public Message getResponse() {
        return this._response;
    }

    public TransportAddress getTurnRelay() {
        return this._turnRelay;
    }

    public IceSendResponseArgs(Message message, TransportAddress transportAddress) {
        if (message == null) {
            throw new RuntimeException(new Exception("response cannot be null."));
        } else if (transportAddress != null) {
            setResponse(message);
            setAddress(transportAddress);
        } else {
            throw new RuntimeException(new Exception("address cannot be null."));
        }
    }

    public void setAddress(TransportAddress transportAddress) {
        this._address = transportAddress;
    }

    public void setOnComplete(IAction1<IceSendResponseCompleteArgs> iAction1) {
        this._onComplete = iAction1;
    }

    public void setOnFailure(IAction1<IceSendResponseFailureArgs> iAction1) {
        this._onFailure = iAction1;
    }

    public void setOnSuccess(IAction1<IceSendResponseSuccessArgs> iAction1) {
        this._onSuccess = iAction1;
    }

    public void setResponse(Message message) {
        this._response = message;
    }

    public void setTurnRelay(TransportAddress transportAddress) {
        this._turnRelay = transportAddress;
    }
}
