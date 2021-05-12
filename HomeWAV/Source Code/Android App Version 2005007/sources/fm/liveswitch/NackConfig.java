package fm.liveswitch;

public class NackConfig {
    private int __receiveBufferLength;
    private int __sendBufferLength;
    private boolean _disableBuffering;

    public boolean getDisableBuffering() {
        return this._disableBuffering;
    }

    public int getReceiveBufferLength() {
        return this.__receiveBufferLength;
    }

    public int getSendBufferLength() {
        return this.__sendBufferLength;
    }

    public NackConfig() {
        this(1000, 1000);
    }

    public NackConfig(int i, int i2) {
        setSendBufferLength(i);
        setReceiveBufferLength(i2);
    }

    /* access modifiers changed from: package-private */
    public void setDisableBuffering(boolean z) {
        this._disableBuffering = z;
    }

    public void setReceiveBufferLength(int i) {
        this.__receiveBufferLength = MathAssistant.max(i, 0);
    }

    public void setSendBufferLength(int i) {
        this.__sendBufferLength = MathAssistant.max(i, 0);
    }
}
