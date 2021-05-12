package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class ReservationTokenAttribute extends Attribute {
    private DataBuffer _token;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 8;
    }

    public DataBuffer getToken() {
        return this._token;
    }

    public int getTypeValue() {
        return Attribute.getReservationTokenType();
    }

    public static ReservationTokenAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        return new ReservationTokenAttribute(dataBuffer.subset(i, 8));
    }

    public ReservationTokenAttribute(DataBuffer dataBuffer) {
        if (dataBuffer.getLength() == 8) {
            setToken(dataBuffer);
            return;
        }
        throw new RuntimeException(new Exception("token must be 8 bytes."));
    }

    public void setToken(DataBuffer dataBuffer) {
        this._token = dataBuffer;
    }

    public String toString() {
        return StringExtensions.format("RESERVATION-TOKEN {0}", (Object) getToken().toHexString());
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        dataBuffer.write(getToken(), i);
    }
}
