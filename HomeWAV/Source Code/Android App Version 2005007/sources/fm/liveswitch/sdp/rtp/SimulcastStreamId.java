package fm.liveswitch.sdp.rtp;

public class SimulcastStreamId {
    private String _id;
    private boolean _paused;

    public String getId() {
        return this._id;
    }

    public boolean getPaused() {
        return this._paused;
    }

    private void setId(String str) {
        this._id = str;
    }

    private void setPaused(boolean z) {
        this._paused = z;
    }

    public SimulcastStreamId(String str) {
        this(str, false);
    }

    public SimulcastStreamId(String str, boolean z) {
        if (str != null) {
            setId(str);
            setPaused(z);
            return;
        }
        throw new RuntimeException(new Exception("Simulcast stream 'ID' cannot be null."));
    }
}
