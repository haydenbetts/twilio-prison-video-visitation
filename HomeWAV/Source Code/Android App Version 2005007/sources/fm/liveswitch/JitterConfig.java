package fm.liveswitch;

public class JitterConfig {
    private int __bufferLength = 100;
    private boolean _disableBuffering;

    public int getBufferLength() {
        return this.__bufferLength;
    }

    public boolean getDisableBuffering() {
        return this._disableBuffering;
    }

    public JitterConfig() {
    }

    public JitterConfig(int i) {
        setBufferLength(i);
    }

    public void setBufferLength(int i) {
        this.__bufferLength = MathAssistant.max(i, 0);
    }

    public void setDisableBuffering(boolean z) {
        this._disableBuffering = z;
    }
}
