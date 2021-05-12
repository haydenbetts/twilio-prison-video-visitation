package fm.liveswitch.diagnostics;

public class DurationSample {
    private long _beginMillis;
    private long _endMillis;

    public DurationSample(long j) {
        setBeginMillis(j);
        setEndMillis(-1);
    }

    public void end(long j) {
        if (getEndMillis() == -1) {
            setEndMillis(j);
            return;
        }
        throw new RuntimeException(new Exception("Sample has already ended."));
    }

    public long getBeginMillis() {
        return this._beginMillis;
    }

    public long getEndMillis() {
        return this._endMillis;
    }

    private void setBeginMillis(long j) {
        this._beginMillis = j;
    }

    private void setEndMillis(long j) {
        this._endMillis = j;
    }
}
