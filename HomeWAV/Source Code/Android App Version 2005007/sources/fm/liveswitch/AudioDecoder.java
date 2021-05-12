package fm.liveswitch;

public abstract class AudioDecoder extends AudioPipe {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(AudioDecoder.class);
    private int __decoderLastDuration = -1;
    private long __decoderLastTimestamp = -1;
    private boolean __firstFrameDecoded = false;
    private AtomicLong __framesDecoded = new AtomicLong();
    private boolean _disablePlc;
    private int _missingDuration;

    public int getMaxInputBitrate() {
        return -1;
    }

    public int getMaxOutputBitrate() {
        return -1;
    }

    public int getMinInputBitrate() {
        return -1;
    }

    public int getMinOutputBitrate() {
        return -1;
    }

    public AudioDecoder(AudioFormat audioFormat, AudioFormat audioFormat2) {
        super(audioFormat, audioFormat2);
        setDisablePlc(!audioFormat2.getIsPcm());
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromInput(MediaTrackStats mediaTrackStats) {
        super.doProcessTrackStatsFromInput(mediaTrackStats);
        mediaTrackStats.setFramesDecoded(getFramesDecoded());
    }

    /* access modifiers changed from: protected */
    public AudioBuffer generatePlc(int i) {
        AudioBuffer audioBuffer = new AudioBuffer(__dataBufferPool.take(SoundUtility.calculateDataLength(i, super.getConfig()), ((AudioFormat) super.getOutputFormat()).getLittleEndian()), (AudioFormat) super.getOutputFormat());
        audioBuffer.setGeneratedByPlc(true);
        return audioBuffer;
    }

    public boolean getDisablePlc() {
        return this._disablePlc;
    }

    public long getFramesDecoded() {
        return this.__framesDecoded.getValue();
    }

    /* access modifiers changed from: protected */
    public int getMissingDuration() {
        return this._missingDuration;
    }

    public boolean processFrame(AudioFrame audioFrame) {
        if (!(!((AudioFormat) super.getOutputFormat()).getIsPcm() || this.__decoderLastTimestamp == -1 || this.__decoderLastDuration == -1)) {
            setMissingDuration(((int) (((audioFrame.getTimestamp() - this.__decoderLastTimestamp) * 1000) / ((long) super.getConfig().getClockRate()))) - this.__decoderLastDuration);
        }
        return super.processFrame(audioFrame);
    }

    /* access modifiers changed from: protected */
    public void raiseFrame(AudioFrame audioFrame) {
        this.__framesDecoded.increment();
        if (!this.__firstFrameDecoded) {
            this.__firstFrameDecoded = true;
            StatControlFrame statControlFrame = new StatControlFrame();
            statControlFrame.setType(StatControlFrameType.FrameDecoded);
            statControlFrame.setTimestamp(ManagedStopwatch.getTimestamp());
            super.raiseControlFrame(statControlFrame);
        }
        if (((AudioFormat) super.getOutputFormat()).getIsPcm()) {
            this.__decoderLastTimestamp = audioFrame.getTimestamp();
            this.__decoderLastDuration = audioFrame.getDuration();
        }
        if (!getDisablePlc() && getMissingDuration() > 0) {
            AudioFrame clone = audioFrame.clone();
            clone.removeBuffers();
            AudioBuffer generatePlc = generatePlc(getMissingDuration());
            clone.addBuffer(generatePlc);
            clone.setDuration(getMissingDuration());
            super.raiseFrame(clone);
            generatePlc.free();
        }
        super.raiseFrame(audioFrame);
    }

    public void setDisablePlc(boolean z) {
        this._disablePlc = z;
    }

    /* access modifiers changed from: protected */
    public void setMissingDuration(int i) {
        this._missingDuration = i;
    }
}
