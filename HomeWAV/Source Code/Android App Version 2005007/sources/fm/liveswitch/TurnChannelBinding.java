package fm.liveswitch;

import java.util.Date;

class TurnChannelBinding {
    private TransportAddress _address;
    private int _channelNumber;
    private Date _expires = new Date();

    public void extendTimeToExpiry() {
        setTimeToExpiry(600);
    }

    public TransportAddress getAddress() {
        return this._address;
    }

    public int getChannelNumber() {
        return this._channelNumber;
    }

    public boolean getIsExpired() {
        return DateExtensions.getTicks(DateExtensions.getUtcNow()) > DateExtensions.getTicks(this._expires);
    }

    public int getTimeToExpiry() {
        return (int) ((DateExtensions.getTicks(this._expires) - DateExtensions.getTicks(DateExtensions.getUtcNow())) / 10000000);
    }

    private void setAddress(TransportAddress transportAddress) {
        this._address = transportAddress;
    }

    private void setChannelNumber(int i) {
        this._channelNumber = i;
    }

    private void setTimeToExpiry(int i) {
        this._expires = DateExtensions.addSeconds(DateExtensions.getUtcNow(), (double) i);
    }

    public String toString() {
        return StringExtensions.format("{0}:{1}", IntegerExtensions.toString(Integer.valueOf(getChannelNumber())), getAddress().toString());
    }

    public TurnChannelBinding(TransportAddress transportAddress, int i) {
        setAddress(transportAddress);
        setChannelNumber(i);
        extendTimeToExpiry();
    }
}
