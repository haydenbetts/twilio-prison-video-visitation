package fm.liveswitch.stun;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.StringExtensions;

public class TransactionTransmitCounterAttribute extends Attribute {
    private int _numRequests;
    private int _numResponses;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 4;
    }

    public int getNumRequests() {
        return this._numRequests;
    }

    public int getNumResponses() {
        return this._numResponses;
    }

    public int getTypeValue() {
        return Attribute.getTransactionTransmitCounterType();
    }

    public static TransactionTransmitCounterAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        return new TransactionTransmitCounterAttribute(dataBuffer.read8(i + 2), dataBuffer.read8(i + 3));
    }

    public void setNumRequests(int i) {
        this._numRequests = i;
    }

    public void setNumResponses(int i) {
        this._numResponses = i;
    }

    public String toString() {
        if (getNumResponses() < 1) {
            return StringExtensions.format("TRANSACTION-TRANSMIT-COUNTER Requests: {0}", (Object) IntegerExtensions.toString(Integer.valueOf(getNumRequests())));
        }
        return StringExtensions.format("TRANSACTION-TRANSMIT-COUNTER Requests: {0}, Responses: {1}", IntegerExtensions.toString(Integer.valueOf(getNumRequests())), IntegerExtensions.toString(Integer.valueOf(getNumResponses())));
    }

    public TransactionTransmitCounterAttribute(int i) {
        this(i, 0);
    }

    public TransactionTransmitCounterAttribute(int i, int i2) {
        setNumRequests(i);
        setNumResponses(i2);
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        dataBuffer.write16(0, i);
        dataBuffer.write8(getNumRequests(), i + 2);
        dataBuffer.write8(getNumResponses(), i + 3);
    }
}
