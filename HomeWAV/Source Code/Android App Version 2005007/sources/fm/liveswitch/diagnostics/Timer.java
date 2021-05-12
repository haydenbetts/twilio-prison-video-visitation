package fm.liveswitch.diagnostics;

import fm.liveswitch.ManagedStopwatch;

public abstract class Timer {
    private String _tag;
    private ManagedStopwatch _watch;

    public abstract boolean getHasData();

    public abstract String getStats();

    public void destroy() {
        getWatch().stop();
        Timers.removeTimer(this);
    }

    public String getTag() {
        return this._tag;
    }

    /* access modifiers changed from: protected */
    public ManagedStopwatch getWatch() {
        return this._watch;
    }

    private void setTag(String str) {
        this._tag = str;
    }

    private void setWatch(ManagedStopwatch managedStopwatch) {
        this._watch = managedStopwatch;
    }

    Timer(String str) {
        setTag(str);
        setWatch(new ManagedStopwatch());
        getWatch().start();
    }
}
