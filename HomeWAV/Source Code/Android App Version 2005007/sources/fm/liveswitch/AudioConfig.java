package fm.liveswitch;

public class AudioConfig extends MediaConfig<AudioConfig> {
    private int _channelCount;

    public AudioConfig(int i, int i2) {
        super(i);
        setChannelCount(i2);
    }

    public int getChannelCount() {
        return this._channelCount;
    }

    public boolean isEquivalent(AudioConfig audioConfig) {
        if (super.isEquivalent(audioConfig) && getChannelCount() == audioConfig.getChannelCount()) {
            return true;
        }
        return false;
    }

    private void setChannelCount(int i) {
        this._channelCount = i;
    }

    public String toString() {
        return StringExtensions.format("Clock Rate: {0}, Channel Count: {1}", IntegerExtensions.toString(Integer.valueOf(super.getClockRate())), IntegerExtensions.toString(Integer.valueOf(getChannelCount())));
    }
}
