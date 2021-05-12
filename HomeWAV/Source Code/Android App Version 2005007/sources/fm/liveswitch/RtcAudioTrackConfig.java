package fm.liveswitch;

public class RtcAudioTrackConfig {
    private boolean _g722Disabled;
    private boolean _opusDisabled;
    private boolean _pcmaDisabled;
    private boolean _pcmuDisabled;

    public boolean getG722Disabled() {
        return this._g722Disabled;
    }

    public boolean getOpusDisabled() {
        return this._opusDisabled;
    }

    public boolean getPcmaDisabled() {
        return this._pcmaDisabled;
    }

    public boolean getPcmuDisabled() {
        return this._pcmuDisabled;
    }

    public void setG722Disabled(boolean z) {
        this._g722Disabled = z;
    }

    public void setOpusDisabled(boolean z) {
        this._opusDisabled = z;
    }

    public void setPcmaDisabled(boolean z) {
        this._pcmaDisabled = z;
    }

    public void setPcmuDisabled(boolean z) {
        this._pcmuDisabled = z;
    }
}
