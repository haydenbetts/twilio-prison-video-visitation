package fm.liveswitch;

import fm.liveswitch.MediaConfig;

public abstract class MediaConfig<TConfig extends MediaConfig<TConfig>> {
    private int _clockRate;

    public int getClockRate() {
        return this._clockRate;
    }

    public boolean isEquivalent(TConfig tconfig) {
        return tconfig != null && getClockRate() == tconfig.getClockRate();
    }

    public MediaConfig(int i) {
        setClockRate(i);
    }

    private void setClockRate(int i) {
        this._clockRate = i;
    }
}
