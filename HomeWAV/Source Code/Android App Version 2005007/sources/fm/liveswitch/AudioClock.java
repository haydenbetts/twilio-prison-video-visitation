package fm.liveswitch;

public class AudioClock {
    private IAction2<Integer, Integer> __raiseCallback;
    private ManagedThread __thread;
    private int _blockAlign;
    private int _channelCount;
    private int _clockRate;
    private boolean _running;
    private int _strictDuration;

    public AudioClock(int i, int i2, int i3, IAction2<Integer, Integer> iAction2) {
        setClockRate(i);
        setChannelCount(i2);
        setBlockAlign(i3);
        setStrictDuration(0);
        this.__raiseCallback = iAction2;
    }

    public AudioClock(int i, int i2, IAction2<Integer, Integer> iAction2) {
        this(i, i2, i2 * 2, iAction2);
    }

    public int getBlockAlign() {
        return this._blockAlign;
    }

    public int getChannelCount() {
        return this._channelCount;
    }

    public int getClockRate() {
        return this._clockRate;
    }

    public boolean getRunning() {
        return this._running;
    }

    public int getStrictDuration() {
        return this._strictDuration;
    }

    /* access modifiers changed from: private */
    public void innerLoop(ManagedThread managedThread) {
        boolean z = getStrictDuration() > 0;
        ManagedStopwatch managedStopwatch = new ManagedStopwatch();
        managedStopwatch.start();
        while (true) {
            long j = 0;
            while (getRunning()) {
                int elapsedMilliseconds = (int) (managedStopwatch.getElapsedMilliseconds() - j);
                if (elapsedMilliseconds > 10000) {
                    managedStopwatch.restart();
                } else if (elapsedMilliseconds > 0) {
                    if (!z || elapsedMilliseconds < getStrictDuration()) {
                        this.__raiseCallback.invoke(Integer.valueOf((((((((getClockRate() * getChannelCount()) * 2) * elapsedMilliseconds) / 1000) + getBlockAlign()) - 1) / getBlockAlign()) * getBlockAlign()), Integer.valueOf(elapsedMilliseconds));
                        j += (long) elapsedMilliseconds;
                    } else {
                        int strictDuration = elapsedMilliseconds / getStrictDuration();
                        int clockRate = (((((((getClockRate() * getChannelCount()) * 2) * getStrictDuration()) / 1000) + getBlockAlign()) - 1) / getBlockAlign()) * getBlockAlign();
                        for (int i = 0; i < strictDuration; i++) {
                            this.__raiseCallback.invoke(Integer.valueOf(clockRate), Integer.valueOf(getStrictDuration()));
                            j += (long) getStrictDuration();
                        }
                    }
                    ManagedThread.sleep(z ? getStrictDuration() : 20);
                }
            }
            return;
        }
    }

    private void setBlockAlign(int i) {
        this._blockAlign = i;
    }

    private void setChannelCount(int i) {
        this._channelCount = i;
    }

    private void setClockRate(int i) {
        this._clockRate = i;
    }

    private void setRunning(boolean z) {
        this._running = z;
    }

    public void setStrictDuration(int i) {
        this._strictDuration = i;
    }

    public void start() {
        if (!getRunning()) {
            setRunning(true);
            ManagedThread managedThread = new ManagedThread(new IActionDelegate1<ManagedThread>() {
                public String getId() {
                    return "fm.liveswitch.AudioClock.innerLoop";
                }

                public void invoke(ManagedThread managedThread) {
                    AudioClock.this.innerLoop(managedThread);
                }
            });
            this.__thread = managedThread;
            managedThread.start();
            return;
        }
        throw new RuntimeException(new Exception("Already running."));
    }

    public void stop() {
        setRunning(false);
        this.__thread = null;
    }
}
