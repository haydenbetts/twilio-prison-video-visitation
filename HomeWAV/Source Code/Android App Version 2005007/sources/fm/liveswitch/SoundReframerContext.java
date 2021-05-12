package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SoundReframerContext {
    private static ILog __log = Log.getLogger(SoundReframerContext.class);
    private long __autoBaseSystemTimestamp = -1;
    private long __autoBaseTimestamp = -1;
    private boolean __autoTimestamping = false;
    private long __endSystemTimestamp = -1;
    private long __endTimestamp = -1;
    private int __frameDataLength = -1;
    private int __frameSystemTimestampDelta = -1;
    private int __frameTimestampDelta = -1;
    private long __lastAutoTimestamp = -1;
    private long __nextAutoTimestamp = 0;
    /* access modifiers changed from: private */
    public List<IAction1<AudioFrame>> __onFrame = new ArrayList();
    private DataBuffer __pendingData = null;
    private int __pendingDataOffset = 0;
    private int __timestampResetInterval = 1000;
    private boolean __timestampWarned = false;
    private AudioConfig _config;
    private boolean _disableTimestampReset;
    private boolean _forceTimestampReset;
    private int _frameDuration;
    private IAction1<AudioFrame> _onFrame = null;

    public void addOnFrame(IAction1<AudioFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onFrame == null) {
                this._onFrame = new IAction1<AudioFrame>() {
                    public void invoke(AudioFrame audioFrame) {
                        Iterator it = new ArrayList(SoundReframerContext.this.__onFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(audioFrame);
                        }
                    }
                };
            }
            this.__onFrame.add(iAction1);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0076 A[Catch:{ all -> 0x00ae }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long autoTimestamp(int r13) {
        /*
            r12 = this;
            r0 = 1
            r12.__autoTimestamping = r0
            r1 = 0
            boolean r2 = r12.getForceTimestampReset()     // Catch:{ all -> 0x00ae }
            long r3 = fm.liveswitch.ManagedStopwatch.getTimestamp()     // Catch:{ all -> 0x00ae }
            long r5 = r12.__autoBaseSystemTimestamp     // Catch:{ all -> 0x00ae }
            r7 = -1
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0018
            r12.__autoTimestamping = r0     // Catch:{ all -> 0x00ae }
            r12.__autoBaseSystemTimestamp = r3     // Catch:{ all -> 0x00ae }
        L_0x0018:
            long r5 = r12.__nextAutoTimestamp     // Catch:{ all -> 0x00ae }
            boolean r7 = r12.getDisableTimestampReset()     // Catch:{ all -> 0x00ae }
            if (r7 != 0) goto L_0x0099
            long r7 = r12.__autoBaseSystemTimestamp     // Catch:{ all -> 0x00ae }
            long r3 = r3 - r7
            int r7 = fm.liveswitch.Constants.getTicksPerMillisecond()     // Catch:{ all -> 0x00ae }
            long r7 = (long) r7     // Catch:{ all -> 0x00ae }
            long r3 = r3 / r7
            int r7 = fm.liveswitch.Constants.getMillisecondsPerSecond()     // Catch:{ all -> 0x00ae }
            long r7 = (long) r7     // Catch:{ all -> 0x00ae }
            long r7 = r7 * r5
            fm.liveswitch.AudioConfig r9 = r12.getConfig()     // Catch:{ all -> 0x00ae }
            int r9 = r9.getClockRate()     // Catch:{ all -> 0x00ae }
            long r9 = (long) r9     // Catch:{ all -> 0x00ae }
            long r7 = r7 / r9
            long r7 = r3 - r7
            if (r2 == 0) goto L_0x004e
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x00ae }
            boolean r0 = r0.getIsDebugEnabled()     // Catch:{ all -> 0x00ae }
            if (r0 == 0) goto L_0x0073
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x00ae }
            java.lang.String r7 = "Audio timestamp reset requested."
            r0.debug(r7)     // Catch:{ all -> 0x00ae }
            goto L_0x0073
        L_0x004e:
            int r9 = r12.getTimestampResetInterval()     // Catch:{ all -> 0x00ae }
            long r9 = (long) r9     // Catch:{ all -> 0x00ae }
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x0073
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x00ae }
            boolean r2 = r2.getIsDebugEnabled()     // Catch:{ all -> 0x00ae }
            if (r2 == 0) goto L_0x0074
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x00ae }
            java.lang.String r9 = "Audio gap detected ({0} ms). Synchronizing timestamp with wall clock."
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x00ae }
            java.lang.String r7 = fm.liveswitch.LongExtensions.toString(r7)     // Catch:{ all -> 0x00ae }
            java.lang.String r7 = fm.liveswitch.StringExtensions.format((java.lang.String) r9, (java.lang.Object) r7)     // Catch:{ all -> 0x00ae }
            r2.debug(r7)     // Catch:{ all -> 0x00ae }
            goto L_0x0074
        L_0x0073:
            r0 = r2
        L_0x0074:
            if (r0 == 0) goto L_0x0099
            fm.liveswitch.AudioConfig r0 = r12.getConfig()     // Catch:{ all -> 0x00ae }
            int r0 = r0.getClockRate()     // Catch:{ all -> 0x00ae }
            long r5 = (long) r0     // Catch:{ all -> 0x00ae }
            long r3 = r3 * r5
            int r0 = fm.liveswitch.Constants.getMillisecondsPerSecond()     // Catch:{ all -> 0x00ae }
            long r5 = (long) r0     // Catch:{ all -> 0x00ae }
            long r3 = r3 / r5
            int r0 = r12.__frameTimestampDelta     // Catch:{ all -> 0x00ae }
            long r5 = (long) r0     // Catch:{ all -> 0x00ae }
            long r5 = r3 % r5
            long r3 = r3 - r5
            r5 = r3
        L_0x008e:
            long r2 = r12.__lastAutoTimestamp     // Catch:{ all -> 0x00ae }
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x0099
            int r0 = r12.__frameTimestampDelta     // Catch:{ all -> 0x00ae }
            long r2 = (long) r0     // Catch:{ all -> 0x00ae }
            long r5 = r5 + r2
            goto L_0x008e
        L_0x0099:
            long r2 = (long) r13     // Catch:{ all -> 0x00ae }
            long r2 = r2 + r5
            r12.__nextAutoTimestamp = r2     // Catch:{ all -> 0x00ae }
            r12.__lastAutoTimestamp = r5     // Catch:{ all -> 0x00ae }
        L_0x009f:
            r2 = 4294967296(0x100000000, double:2.121995791E-314)
            int r13 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r13 < 0) goto L_0x00aa
            long r5 = r5 - r2
            goto L_0x009f
        L_0x00aa:
            r12.setForceTimestampReset(r1)
            return r5
        L_0x00ae:
            r13 = move-exception
            r12.setForceTimestampReset(r1)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SoundReframerContext.autoTimestamp(int):long");
    }

    private void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer, DataBuffer dataBuffer) {
        if (audioFrame.getDuration() == getFrameDuration() && this.__pendingDataOffset == 0) {
            long j = this.__endTimestamp;
            long j2 = this.__endSystemTimestamp;
            audioFrame.setTimestamp(j);
            audioFrame.setSystemTimestamp(j2);
            raise(audioFrame);
            return;
        }
        long calculateTimestampDeltaFromDataLength = (long) SoundUtility.calculateTimestampDeltaFromDataLength(this.__pendingDataOffset, getConfig().getChannelCount());
        long j3 = this.__endTimestamp - calculateTimestampDeltaFromDataLength;
        long calculateSystemTimestampDeltaFromTimestampDelta = this.__endSystemTimestamp - SoundUtility.calculateSystemTimestampDeltaFromTimestampDelta(calculateTimestampDeltaFromDataLength, getConfig().getClockRate());
        int i = 0;
        while (i < dataBuffer.getLength()) {
            int min = MathAssistant.min(dataBuffer.getLength() - i, this.__pendingData.getLength() - this.__pendingDataOffset);
            this.__pendingData.write(dataBuffer.subset(i, min), this.__pendingDataOffset);
            this.__pendingDataOffset += min;
            i += min;
            if (this.__pendingData.getLength() == this.__pendingDataOffset) {
                AudioFrame clone = audioFrame.clone();
                clone.setDuration(getFrameDuration());
                clone.setTimestamp(j3);
                clone.setSystemTimestamp(calculateSystemTimestampDeltaFromTimestampDelta);
                clone.removeBuffers();
                clone.addBuffer(new AudioBuffer(this.__pendingData, (AudioFormat) audioBuffer.getFormat()));
                j3 += (long) this.__frameTimestampDelta;
                calculateSystemTimestampDeltaFromTimestampDelta += (long) this.__frameSystemTimestampDelta;
                raise(clone);
                this.__pendingDataOffset = 0;
            }
        }
    }

    public AudioConfig getConfig() {
        return this._config;
    }

    public boolean getDisableTimestampReset() {
        return this._disableTimestampReset;
    }

    public boolean getForceTimestampReset() {
        return this._forceTimestampReset;
    }

    public int getFrameDuration() {
        return this._frameDuration;
    }

    public int getTimestampResetInterval() {
        return this.__timestampResetInterval;
    }

    public void processFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        int calculateDurationFromTimestampDelta;
        DataBuffer dataBuffer = audioBuffer.getDataBuffer();
        int calculateTimestampDeltaFromDataLength = SoundUtility.calculateTimestampDeltaFromDataLength(dataBuffer.getLength(), getConfig().getChannelCount());
        if (audioFrame.getTimestamp() == -1 || this.__autoTimestamping) {
            audioFrame.setTimestamp(autoTimestamp(calculateTimestampDeltaFromDataLength));
            if (this.__autoBaseTimestamp == -1) {
                this.__autoBaseTimestamp = audioFrame.getTimestamp();
            }
            audioFrame.setSystemTimestamp(MediaFrame.calculateSystemTimestamp(this.__autoBaseSystemTimestamp, audioFrame.getTimestamp(), getConfig().getClockRate(), this.__autoBaseTimestamp));
            if (!this.__timestampWarned) {
                this.__timestampWarned = true;
                __log.debug("Audio frames are being processed without timestamps. This can result in unexpected delay and synchronization issues with remote playback. Enabling auto-timestamping in an attempt to compensate.");
            }
        }
        if (this.__endTimestamp == -1) {
            this.__endTimestamp = audioFrame.getTimestamp();
            long systemTimestamp = audioFrame.getSystemTimestamp();
            this.__endSystemTimestamp = systemTimestamp;
            if (systemTimestamp == -1) {
                long timestamp = ManagedStopwatch.getTimestamp();
                this.__endSystemTimestamp = timestamp;
                __log.debug(StringExtensions.format("Audio frames are being processed without system timestamps. This can result in unexpected delay and synchronization issues with remote playback. Assuming base system timestamp of {0}.", (Object) LongExtensions.toString(Long.valueOf(timestamp))));
            }
        }
        int timestamp2 = (int) (audioFrame.getTimestamp() - this.__endTimestamp);
        if (timestamp2 > 0) {
            int calculateDataLengthFromTimestampDelta = SoundUtility.calculateDataLengthFromTimestampDelta(timestamp2, getConfig().getChannelCount());
            if (SoundUtility.calculateTimestampDeltaFromDataLength(this.__pendingDataOffset, getConfig().getChannelCount()) + timestamp2 < this.__frameTimestampDelta) {
                this.__pendingData.set((byte) 0, this.__pendingDataOffset, calculateDataLengthFromTimestampDelta);
                this.__pendingDataOffset += calculateDataLengthFromTimestampDelta;
            } else {
                int length = (this.__pendingDataOffset + calculateDataLengthFromTimestampDelta) % this.__pendingData.getLength();
                this.__pendingDataOffset = length;
                this.__pendingData.set((byte) 0, 0, length);
            }
            long j = (long) timestamp2;
            this.__endTimestamp += j;
            this.__endSystemTimestamp += SoundUtility.calculateSystemTimestampDeltaFromTimestampDelta(j, getConfig().getClockRate());
            if (__log.getIsDebugEnabled() && !this.__autoTimestamping && (calculateDurationFromTimestampDelta = SoundUtility.calculateDurationFromTimestampDelta(timestamp2, getConfig().getClockRate())) > getFrameDuration()) {
                __log.debug(StringExtensions.format("Audio gap detected ({0}ms).", (Object) IntegerExtensions.toString(Integer.valueOf(calculateDurationFromTimestampDelta))));
            }
        }
        doProcessFrame(audioFrame, audioBuffer, dataBuffer);
        long j2 = (long) calculateTimestampDeltaFromDataLength;
        this.__endTimestamp += j2;
        this.__endSystemTimestamp += SoundUtility.calculateSystemTimestampDeltaFromTimestampDelta(j2, getConfig().getClockRate());
    }

    private void raise(AudioFrame audioFrame) {
        IAction1<AudioFrame> iAction1 = this._onFrame;
        if (iAction1 != null) {
            iAction1.invoke(audioFrame);
        }
    }

    public void removeOnFrame(IAction1<AudioFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onFrame.remove(iAction1);
        if (this.__onFrame.size() == 0) {
            this._onFrame = null;
        }
    }

    private void setConfig(AudioConfig audioConfig) {
        this._config = audioConfig;
    }

    public void setDisableTimestampReset(boolean z) {
        this._disableTimestampReset = z;
    }

    public void setForceTimestampReset(boolean z) {
        this._forceTimestampReset = z;
    }

    private void setFrameDuration(int i) {
        this._frameDuration = i;
    }

    public void setTimestampResetInterval(int i) {
        this.__timestampResetInterval = i;
    }

    public SoundReframerContext(int i, AudioConfig audioConfig) {
        setFrameDuration(i <= 0 ? 20 : i);
        setConfig(audioConfig);
        this.__frameDataLength = (((getConfig().getClockRate() * getConfig().getChannelCount()) * 2) * getFrameDuration()) / Constants.getMillisecondsPerSecond();
        this.__frameTimestampDelta = (getFrameDuration() * getConfig().getClockRate()) / Constants.getMillisecondsPerSecond();
        this.__frameSystemTimestampDelta = getFrameDuration() * Constants.getTicksPerMillisecond();
        this.__pendingData = DataBuffer.allocate(this.__frameDataLength, true);
    }
}
