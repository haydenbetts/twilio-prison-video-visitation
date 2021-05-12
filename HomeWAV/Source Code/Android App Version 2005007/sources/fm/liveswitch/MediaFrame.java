package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.diagnostics.DurationSample;

public abstract class MediaFrame<TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>> extends Dynamic {
    private static ILog __log = Log.getLogger(MediaFrame.class);
    private TBufferCollection __buffers;
    private long[] _contributingSources;
    private boolean _discard;
    private DurationSample _durationSample;
    private String _mid;
    private long _networkSystemTimestamp;
    private String _repairedRtpStreamId;
    private int _rtpSequenceNumber;
    private String _rtpStreamId;
    private long _rtpTimestamp;
    private long _sequenceNumber;
    private long _synchronizationSource;
    private boolean _synchronized;
    private long _systemTimestamp;
    private long _timestamp;
    private long _transportSystemTimestamp;

    public abstract TFrame createInstance();

    /* access modifiers changed from: protected */
    public abstract TBufferCollection createMediaBufferCollection();

    public void addBuffer(TBuffer tbuffer) {
        this.__buffers.add(tbuffer);
    }

    public void addBuffers(TBuffer[] tbufferArr) {
        this.__buffers.addMany(tbufferArr);
    }

    public static long calculateSystemTimestamp(long j, long j2, int i, long j3) {
        return j + (((j2 - j3) * ((long) Constants.getTicksPerSecond())) / ((long) i));
    }

    public static long calculateTimestamp(long j, long j2, int i) {
        return calculateTimestamp(j, j2, i, 0);
    }

    public static long calculateTimestamp(long j, long j2, int i, long j3) {
        return j3 + (((j2 - j) * ((long) i)) / ((long) Constants.getTicksPerSecond()));
    }

    public TFrame clone() {
        TFrame createInstance = createInstance();
        createInstance.setDynamicProperties(super.getDynamicProperties());
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) getBuffers()); i++) {
            createInstance.addBuffer(getBuffers()[i].clone());
        }
        createInstance.setSynchronizationSource(getSynchronizationSource());
        createInstance.setContributingSources(Utility.cloneLongArray(getContributingSources()));
        createInstance.setSystemTimestamp(getSystemTimestamp());
        createInstance.setTimestamp(getTimestamp());
        createInstance.setNetworkSystemTimestamp(getNetworkSystemTimestamp());
        createInstance.setTransportSystemTimestamp(getTransportSystemTimestamp());
        createInstance.setRtpTimestamp(getRtpTimestamp());
        createInstance.setSequenceNumber(getSequenceNumber());
        createInstance.setRtpSequenceNumber(getRtpSequenceNumber());
        createInstance.setSynchronized(getSynchronized());
        createInstance.setMid(getMid());
        createInstance.setRtpStreamId(getRtpStreamId());
        createInstance.setRepairedRtpStreamId(getRepairedRtpStreamId());
        createInstance.setDurationSample(getDurationSample());
        createInstance.setDiscard(getDiscard());
        return createInstance;
    }

    public TFrame free() {
        for (MediaBuffer free : getBuffers()) {
            free.free();
        }
        return this;
    }

    public TBuffer getBuffer() {
        return (MediaBuffer) this.__buffers.getValue();
    }

    public TBuffer getBuffer(TFormat tformat) {
        TBuffer[] buffers = getBuffers();
        for (int length = ArrayExtensions.getLength((Object[]) buffers) - 1; length >= 0; length--) {
            TBuffer tbuffer = buffers[length];
            if (tbuffer.getFormat().isCompatible(tformat)) {
                return tbuffer;
            }
        }
        return null;
    }

    public TBuffer getBuffer(TFormat tformat, String str) {
        TBuffer[] buffers = getBuffers();
        for (int length = ArrayExtensions.getLength((Object[]) buffers) - 1; length >= 0; length--) {
            TBuffer tbuffer = buffers[length];
            if (Global.equals(tbuffer.getSourceId(), str) && tbuffer.getFormat().isCompatible(tformat)) {
                return tbuffer;
            }
        }
        return null;
    }

    public TBuffer getBuffer(String str) {
        TBuffer[] buffers = getBuffers();
        for (int length = ArrayExtensions.getLength((Object[]) buffers) - 1; length >= 0; length--) {
            TBuffer tbuffer = buffers[length];
            if (StringExtensions.isEqual(tbuffer.getFormat().getName(), str, StringComparison.OrdinalIgnoreCase)) {
                return tbuffer;
            }
        }
        return null;
    }

    public TBuffer getBuffer(boolean z) {
        TBuffer[] buffers = getBuffers();
        for (int length = ArrayExtensions.getLength((Object[]) buffers) - 1; length >= 0; length--) {
            TBuffer tbuffer = buffers[length];
            if (Global.equals(Boolean.valueOf(tbuffer.getFormat().getIsPacketized()), Boolean.valueOf(z))) {
                return tbuffer;
            }
        }
        return null;
    }

    public TBuffer getBuffer(boolean z, boolean z2) {
        TBuffer[] buffers = getBuffers();
        for (int length = ArrayExtensions.getLength((Object[]) buffers) - 1; length >= 0; length--) {
            TBuffer tbuffer = buffers[length];
            if (Global.equals(Boolean.valueOf(tbuffer.getFormat().getIsPacketized()), Boolean.valueOf(z)) && Global.equals(Boolean.valueOf(tbuffer.getFormat().getIsEncrypted()), Boolean.valueOf(z2))) {
                return tbuffer;
            }
        }
        return null;
    }

    public TBuffer[] getBuffers() {
        return (MediaBuffer[]) this.__buffers.getValues();
    }

    public long[] getContributingSources() {
        return this._contributingSources;
    }

    public boolean getDiscard() {
        return this._discard;
    }

    /* access modifiers changed from: package-private */
    public DurationSample getDurationSample() {
        return this._durationSample;
    }

    public int getFootprint() {
        int i = 0;
        for (MediaBuffer footprint : getBuffers()) {
            i += footprint.getFootprint();
        }
        return i;
    }

    public TBuffer getLastBuffer() {
        return ((MediaBuffer[]) this.__buffers.getValues())[ArrayExtensions.getLength(this.__buffers.getValues()) - 1];
    }

    public String getMid() {
        return this._mid;
    }

    public long getNetworkSystemTimestamp() {
        return this._networkSystemTimestamp;
    }

    public long getNtpTimestampTicks() {
        __log.warn("Getting the value of NtpTimestampTicks is deprecated. Use SystemTimestamp instead.");
        return getSystemTimestamp();
    }

    public String getRepairedRtpStreamId() {
        return this._repairedRtpStreamId;
    }

    public int getRtpSequenceNumber() {
        return this._rtpSequenceNumber;
    }

    public String getRtpStreamId() {
        return this._rtpStreamId;
    }

    public long getRtpTimestamp() {
        return this._rtpTimestamp;
    }

    public long getSequenceNumber() {
        return this._sequenceNumber;
    }

    public long getSynchronizationSource() {
        return this._synchronizationSource;
    }

    public boolean getSynchronized() {
        return this._synchronized;
    }

    public long getSystemTimestamp() {
        return this._systemTimestamp;
    }

    public long getTimestamp() {
        return this._timestamp;
    }

    public long getTransportSystemTimestamp() {
        return this._transportSystemTimestamp;
    }

    public boolean hasBuffer(TFormat tformat) {
        return getBuffer(tformat) != null;
    }

    public boolean hasBuffer(TFormat tformat, String str) {
        return getBuffer(tformat, str) != null;
    }

    private void initialize() {
        setSynchronizationSource(-1);
        setSystemTimestamp(-1);
        setTimestamp(-1);
        setNetworkSystemTimestamp(-1);
        setTransportSystemTimestamp(-1);
        setRtpTimestamp(-1);
        setSequenceNumber(-1);
        setRtpSequenceNumber(-1);
        this.__buffers = createMediaBufferCollection();
    }

    public TFrame keep() {
        for (MediaBuffer keep : getBuffers()) {
            keep.keep();
        }
        return this;
    }

    public MediaFrame() {
        initialize();
    }

    public MediaFrame(TBuffer tbuffer) {
        initialize();
        addBuffer(tbuffer);
    }

    public MediaFrame(TBuffer[] tbufferArr) {
        initialize();
        addBuffers(tbufferArr);
    }

    public void removeBuffer(TBuffer tbuffer) {
        this.__buffers.remove(tbuffer);
    }

    public void removeBuffers() {
        this.__buffers.removeAll();
    }

    public void removeBuffers(TBuffer[] tbufferArr) {
        this.__buffers.removeMany(tbufferArr);
    }

    public void setBuffer(TBuffer tbuffer) {
        this.__buffers.setValue(tbuffer);
    }

    public void setBuffers(TBuffer[] tbufferArr) {
        this.__buffers.replace(tbufferArr);
    }

    public void setContributingSources(long[] jArr) {
        this._contributingSources = jArr;
    }

    public void setDiscard(boolean z) {
        this._discard = z;
    }

    /* access modifiers changed from: package-private */
    public void setDurationSample(DurationSample durationSample) {
        this._durationSample = durationSample;
    }

    public void setMid(String str) {
        this._mid = str;
    }

    public void setNetworkSystemTimestamp(long j) {
        this._networkSystemTimestamp = j;
    }

    public void setNtpTimestampTicks(long j) {
        __log.warn("Setting the value of NtpTimestampTicks is deprecated. Use SystemTimestamp instead.");
        setSystemTimestamp(j);
    }

    public void setRepairedRtpStreamId(String str) {
        this._repairedRtpStreamId = str;
    }

    public void setRtpSequenceNumber(int i) {
        this._rtpSequenceNumber = i;
    }

    public void setRtpStreamId(String str) {
        this._rtpStreamId = str;
    }

    public void setRtpTimestamp(long j) {
        this._rtpTimestamp = j;
    }

    public void setSequenceNumber(long j) {
        this._sequenceNumber = j;
    }

    public void setSynchronizationSource(long j) {
        this._synchronizationSource = j;
    }

    public void setSynchronized(boolean z) {
        this._synchronized = z;
    }

    public void setSystemTimestamp(long j) {
        this._systemTimestamp = j;
    }

    public void setTimestamp(long j) {
        this._timestamp = j;
    }

    public void setTransportSystemTimestamp(long j) {
        this._transportSystemTimestamp = j;
    }

    public void updateTimestamp(long j, long j2, int i, int i2) {
        long j3 = (long) i2;
        long j4 = (long) i;
        setTimestamp(j + (((getTimestamp() - j) * j3) / j4));
        if (getRtpTimestamp() != -1 && j2 != -1) {
            setRtpTimestamp((j2 + (((getRtpTimestamp() - j2) * j3) / j4)) % 4294967296L);
        }
    }
}
