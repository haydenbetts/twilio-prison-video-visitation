package fm.liveswitch;

public class RtcVideoTrackConfig {
    private boolean _h264Disabled;
    private boolean _vp8Disabled;
    private boolean _vp9Disabled;

    public boolean getH264Disabled() {
        return this._h264Disabled;
    }

    public boolean getVp8Disabled() {
        return this._vp8Disabled;
    }

    public boolean getVp9Disabled() {
        return this._vp9Disabled;
    }

    public void setH264Disabled(boolean z) {
        this._h264Disabled = z;
    }

    public void setVp8Disabled(boolean z) {
        this._vp8Disabled = z;
    }

    public void setVp9Disabled(boolean z) {
        this._vp9Disabled = z;
    }
}
