package fm.liveswitch;

public abstract class AudioDepacketizer extends AudioPipe {
    private AtomicLong __framesReceived = new AtomicLong();

    /* access modifiers changed from: protected */
    public boolean getAllowDiagnosticTimer() {
        return false;
    }

    public AudioDepacketizer(AudioFormat audioFormat) {
        super(prepareInputFormat(audioFormat), prepareOutputFormat(audioFormat));
    }

    public AudioDepacketizer(IAudioOutput iAudioOutput) {
        super(prepareInputFormat((AudioFormat) iAudioOutput.getOutputFormat()), prepareOutputFormat((AudioFormat) iAudioOutput.getOutputFormat()));
        super.addInput(iAudioOutput);
    }

    public AudioDepacketizer(AudioFormat audioFormat, AudioFormat audioFormat2) {
        super(prepareInputFormat(audioFormat), prepareOutputFormat(audioFormat2));
    }

    private static AudioFormat cloneFormat(AudioFormat audioFormat, boolean z, boolean z2) {
        AudioFormat clone = audioFormat.clone();
        clone.setIsPacketized(z);
        clone.setLittleEndian(z2);
        return clone;
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromInput(MediaTrackStats mediaTrackStats) {
        super.doProcessTrackStatsFromInput(mediaTrackStats);
        mediaTrackStats.setFramesReceived(getFramesReceived());
    }

    public long getFramesReceived() {
        return this.__framesReceived.getValue();
    }

    private static AudioFormat prepareInputFormat(AudioFormat audioFormat) {
        if (audioFormat == null) {
            return null;
        }
        return cloneFormat(audioFormat, true, false);
    }

    private static AudioFormat prepareOutputFormat(AudioFormat audioFormat) {
        if (audioFormat == null) {
            return null;
        }
        return cloneFormat(audioFormat, false, audioFormat.getLittleEndian());
    }

    /* access modifiers changed from: protected */
    public void raiseFrame(AudioFrame audioFrame) {
        this.__framesReceived.increment();
        super.raiseFrame(audioFrame);
    }
}
