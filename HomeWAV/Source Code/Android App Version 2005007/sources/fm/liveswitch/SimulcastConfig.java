package fm.liveswitch;

abstract class SimulcastConfig {
    private int __encodingCount;
    private int __preferredBitrate;
    private boolean _disabled;

    public boolean getDisabled() {
        return this._disabled;
    }

    public int getEncodingCount() {
        return this.__encodingCount;
    }

    public int getPreferredBitrate() {
        return this.__preferredBitrate;
    }

    public void setDisabled(boolean z) {
        this._disabled = z;
    }

    public void setEncodingCount(int i) {
        if (i > 0) {
            this.__encodingCount = i;
            return;
        }
        throw new RuntimeException(new Exception("Encoding count must be a positive integer."));
    }

    public void setPreferredBitrate(int i) {
        if (i > 0) {
            this.__preferredBitrate = i;
            return;
        }
        throw new RuntimeException(new Exception("Preferred bitrate must be a positive integer."));
    }

    public SimulcastConfig(int i, int i2) {
        setEncodingCount(i);
        setPreferredBitrate(i2);
    }
}
