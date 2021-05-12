package fm.liveswitch;

import fm.liveswitch.stun.Message;

class IceSendRequestSuccessArgs {
    private ScheduledItem _item;
    private boolean _relayed;
    private TransportAddress _remoteAddress;
    private long _requestDispatchTimestamp;
    private Message _response;

    public ScheduledItem getItem() {
        return this._item;
    }

    public boolean getRelayed() {
        return this._relayed;
    }

    public TransportAddress getRemoteAddress() {
        return this._remoteAddress;
    }

    public long getRequestDispatchTimestamp() {
        return this._requestDispatchTimestamp;
    }

    public Message getResponse() {
        return this._response;
    }

    public void setItem(ScheduledItem scheduledItem) {
        this._item = scheduledItem;
    }

    public void setRelayed(boolean z) {
        this._relayed = z;
    }

    /* access modifiers changed from: package-private */
    public void setRemoteAddress(TransportAddress transportAddress) {
        this._remoteAddress = transportAddress;
    }

    public void setRequestDispatchTimestamp(long j) {
        this._requestDispatchTimestamp = j;
    }

    /* access modifiers changed from: package-private */
    public void setResponse(Message message) {
        this._response = message;
    }
}
