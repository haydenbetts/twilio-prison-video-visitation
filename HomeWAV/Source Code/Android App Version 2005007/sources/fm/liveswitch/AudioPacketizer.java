package fm.liveswitch;

public abstract class AudioPacketizer extends AudioPipe {
    private AtomicLong __framesSent = new AtomicLong();

    /* access modifiers changed from: protected */
    public boolean getAllowDiagnosticTimer() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean getCanPauseBitrate() {
        return true;
    }

    public AudioPacketizer(AudioFormat audioFormat) {
        super(prepareInputFormat(audioFormat), prepareOutputFormat(audioFormat));
    }

    public AudioPacketizer(IAudioOutput iAudioOutput) {
        super(prepareInputFormat((AudioFormat) iAudioOutput.getOutputFormat()), prepareOutputFormat((AudioFormat) iAudioOutput.getOutputFormat()));
        super.addInput(iAudioOutput);
    }

    public AudioPacketizer(AudioFormat audioFormat, AudioFormat audioFormat2) {
        super(prepareInputFormat(audioFormat), prepareOutputFormat(audioFormat2));
    }

    private static AudioFormat cloneFormat(AudioFormat audioFormat, boolean z, boolean z2) {
        AudioFormat clone = audioFormat.clone();
        clone.setIsPacketized(z);
        clone.setLittleEndian(z2);
        return clone;
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromOutput(MediaTrackStats mediaTrackStats) {
        super.doProcessTrackStatsFromOutput(mediaTrackStats);
        mediaTrackStats.setFramesSent(getFramesSent());
    }

    public long getFramesSent() {
        return this.__framesSent.getValue();
    }

    private static AudioFormat prepareInputFormat(AudioFormat audioFormat) {
        if (audioFormat == null) {
            return null;
        }
        return cloneFormat(audioFormat, false, audioFormat.getLittleEndian());
    }

    private static AudioFormat prepareOutputFormat(AudioFormat audioFormat) {
        if (audioFormat == null) {
            return null;
        }
        return cloneFormat(audioFormat, true, false);
    }

    public boolean processFrame(AudioFrame audioFrame) {
        this.__framesSent.increment();
        return super.processFrame(audioFrame);
    }
}
