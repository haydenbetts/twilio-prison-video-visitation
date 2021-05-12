package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;

class SynchronizeQueue<TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>> implements ISynchronizer {
    private static ILog __log = Log.getLogger(SynchronizeQueue.class);
    private boolean __active;
    private volatile boolean __destroying;
    private volatile boolean __discarding;
    private ManagedConcurrentQueue<TFrame> __inputQueue = new ManagedConcurrentQueue<>();
    private AtomicLong __inputQueueFootprint = new AtomicLong();
    private volatile boolean __masterReleasing;
    private long __masterSystemTimestamp = -1;
    private DispatchQueue<TFrame> __outputQueue;
    /* access modifiers changed from: private */
    public IAction1<TFrame> __raiseFrame;
    private volatile boolean __selfReleasing;
    private boolean _master;
    private long _maxData;
    private int _maxDelay;
    private ISynchronizer[] _slaves;
    private StreamType _type;

    public void activate(boolean z, ISynchronizer[] iSynchronizerArr) {
        if (z) {
            setSlaves(iSynchronizerArr);
            setMaster(true);
        } else {
            this.__outputQueue = new DispatchQueue<>(new IAction1<TFrame>() {
                public void invoke(TFrame tframe) {
                    try {
                        SynchronizeQueue.this.__raiseFrame.invoke(tframe);
                    } finally {
                        tframe.free();
                    }
                }
            });
        }
        this.__active = true;
    }

    private long dataAfterAddingFootprint(int i) {
        return this.__inputQueueFootprint.getValue() + ((long) i);
    }

    private int delayAfterAddingSystemTimestamp(long j) {
        long headSystemTimestamp = getHeadSystemTimestamp();
        if (headSystemTimestamp == -1) {
            return 0;
        }
        return (int) ((j - headSystemTimestamp) / ((long) Constants.getTicksPerMillisecond()));
    }

    public void destroy() {
        this.__destroying = true;
        while (true) {
            if (!this.__masterReleasing && !this.__selfReleasing && !this.__discarding) {
                break;
            }
            ManagedThread.sleep(10);
        }
        do {
        } while (tryDiscardInputFrame());
        DispatchQueue<TFrame> dispatchQueue = this.__outputQueue;
        if (dispatchQueue != null) {
            dispatchQueue.waitForDrain();
        }
    }

    public boolean getActive() {
        return this.__active;
    }

    public int getCount() {
        return this.__inputQueue.getCount();
    }

    public long getHeadSystemTimestamp() {
        Holder holder = new Holder(null);
        boolean tryPeek = this.__inputQueue.tryPeek(holder);
        MediaFrame mediaFrame = (MediaFrame) holder.getValue();
        if (tryPeek) {
            return mediaFrame.getSystemTimestamp();
        }
        return -1;
    }

    public boolean getMaster() {
        return this._master;
    }

    public long getMasterSystemTimestamp() {
        return this.__masterSystemTimestamp;
    }

    public long getMaxData() {
        return this._maxData;
    }

    public int getMaxDelay() {
        return this._maxDelay;
    }

    public ISynchronizer[] getSlaves() {
        return this._slaves;
    }

    public StreamType getType() {
        return this._type;
    }

    private void maybeDiscardOldData(int i, long j) {
        int i2 = 0;
        if (getMaxData() > 0) {
            int i3 = 0;
            while (dataAfterAddingFootprint(i) > getMaxData()) {
                if (tryDiscardInputFrame()) {
                    i3++;
                }
            }
            if (i3 > 0 && __log.getIsVerboseEnabled()) {
                __log.verbose(StringExtensions.format("Discarding {0} old frame(s). New frame would exceed data limit ({1} bytes).", IntegerExtensions.toString(Integer.valueOf(i3)), LongExtensions.toString(Long.valueOf(getMaxData()))));
            }
        }
        if (getMaxDelay() > 0) {
            while (delayAfterAddingSystemTimestamp(j) >= getMaxDelay()) {
                if (tryDiscardInputFrame()) {
                    i2++;
                }
            }
            if (i2 > 0 && __log.getIsVerboseEnabled()) {
                __log.verbose(StringExtensions.format("Discarding {0} old frame(s). New frame would exceed delay limit ({1}ms).", IntegerExtensions.toString(Integer.valueOf(i2)), IntegerExtensions.toString(Integer.valueOf(getMaxDelay()))));
            }
        }
    }

    public void processFrame(TFrame tframe, long j) {
        if (this.__active) {
            long systemTimestamp = tframe.getSystemTimestamp() - j;
            if (getMaster()) {
                this.__raiseFrame.invoke(tframe);
                this.__masterSystemTimestamp = systemTimestamp;
                for (ISynchronizer masterSystemTimestamp : getSlaves()) {
                    masterSystemTimestamp.setMasterSystemTimestamp(getMasterSystemTimestamp());
                }
            } else if (!tframe.getSynchronized() || systemTimestamp == -1) {
                this.__outputQueue.enqueue(tframe.keep());
            } else {
                maybeDiscardOldData(tframe.getFootprint(), systemTimestamp);
                this.__inputQueueFootprint.add((long) tframe.getFootprint());
                this.__inputQueue.enqueue(tframe.keep());
                do {
                } while (tryRaiseInputFrame(false));
            }
        } else {
            this.__raiseFrame.invoke(tframe);
        }
    }

    private void setMaster(boolean z) {
        this._master = z;
    }

    public void setMasterSystemTimestamp(long j) {
        this.__masterSystemTimestamp = j;
        do {
        } while (tryRaiseInputFrame(true));
    }

    public void setMaxData(long j) {
        this._maxData = j;
    }

    public void setMaxDelay(int i) {
        this._maxDelay = i;
    }

    private void setSlaves(ISynchronizer[] iSynchronizerArr) {
        this._slaves = iSynchronizerArr;
    }

    private void setType(StreamType streamType) {
        this._type = streamType;
    }

    public SynchronizeQueue(StreamType streamType, IAction1<TFrame> iAction1) {
        setType(streamType);
        this.__raiseFrame = iAction1;
        setMaxDelay(5000);
        setMaxData(0);
    }

    private boolean tryDiscardInputFrame() {
        MediaFrame mediaFrame;
        if (!this.__destroying) {
            this.__discarding = true;
            try {
                if (!this.__destroying) {
                    Holder holder = new Holder(null);
                    boolean tryDequeue = this.__inputQueue.tryDequeue(holder);
                    mediaFrame = (MediaFrame) holder.getValue();
                    if (tryDequeue) {
                        this.__inputQueueFootprint.subtract((long) mediaFrame.getFootprint());
                        mediaFrame.free();
                        this.__discarding = false;
                        return true;
                    }
                }
                this.__discarding = false;
            } catch (Throwable th) {
                this.__discarding = false;
                throw th;
            }
        }
        return false;
    }

    private boolean tryRaiseInputFrame(boolean z) {
        if (!this.__destroying) {
            if (z) {
                this.__masterReleasing = true;
            } else {
                this.__selfReleasing = true;
            }
            try {
                if (!this.__destroying) {
                    long masterSystemTimestamp = getMasterSystemTimestamp();
                    if (masterSystemTimestamp != -1 && getHeadSystemTimestamp() <= masterSystemTimestamp) {
                        Holder holder = new Holder(null);
                        boolean tryDequeue = this.__inputQueue.tryDequeue(holder);
                        MediaFrame mediaFrame = (MediaFrame) holder.getValue();
                        if (tryDequeue) {
                            this.__inputQueueFootprint.subtract((long) mediaFrame.getFootprint());
                            this.__outputQueue.enqueue(mediaFrame);
                            if (!z) {
                                this.__selfReleasing = false;
                            }
                            return true;
                        }
                    }
                }
                if (z) {
                    this.__masterReleasing = false;
                } else {
                    this.__selfReleasing = false;
                }
            } finally {
                if (z) {
                    this.__masterReleasing = false;
                } else {
                    this.__selfReleasing = false;
                }
            }
        }
        return false;
    }
}
