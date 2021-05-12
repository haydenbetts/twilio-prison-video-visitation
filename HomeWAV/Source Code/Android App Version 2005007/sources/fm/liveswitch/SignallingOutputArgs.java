package fm.liveswitch;

import java.util.Date;

abstract class SignallingOutputArgs extends SignallingExtensible {
    private NullableDate __timestamp = new NullableDate();
    private SignallingClient _client;

    public SignallingClient getClient() {
        return this._client;
    }

    public NullableDate getTimestamp() {
        return this.__timestamp;
    }

    public void setClient(SignallingClient signallingClient) {
        this._client = signallingClient;
    }

    public void setTimestamp(NullableDate nullableDate) {
        if (nullableDate.getHasValue()) {
            this.__timestamp = new NullableDate(nullableDate.getValue());
        } else {
            this.__timestamp = new NullableDate((Date) null);
        }
    }

    protected SignallingOutputArgs() {
    }
}
