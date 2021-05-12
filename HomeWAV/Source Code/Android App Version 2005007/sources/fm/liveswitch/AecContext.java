package fm.liveswitch;

public abstract class AecContext {
    private Object _destroyLock = new Object();
    private boolean _destroyed = false;
    private AudioPipe _outputMixer;
    private AudioSink _outputMixerSink;
    private AudioTrack _outputTrack;
    private AecPipe _processor;

    /* access modifiers changed from: protected */
    public abstract AudioSink createOutputMixerSink(AudioConfig audioConfig);

    /* access modifiers changed from: protected */
    public abstract AecPipe createProcessor();

    public AecContext() {
        setProcessor(createProcessor());
        if (getProcessor() != null) {
            setOutputMixerSink(createOutputMixerSink(getProcessor().getConfig()));
            if (getOutputMixerSink() != null) {
                setOutputMixer(createOutputMixer(getProcessor().getConfig()));
                if (getOutputMixer() != null) {
                    setOutputTrack((AudioTrack) new AudioTrack((IAudioElement) getOutputMixer()).next(getOutputMixerSink()));
                    getOutputTrack().setPersistent(true);
                    getProcessor().setSpeaker(getOutputMixerSink());
                    return;
                }
                throw new RuntimeException(new Exception("AEC output mixer cannot be null."));
            }
            throw new RuntimeException(new Exception("AEC output mixer sink cannot be null."));
        }
        throw new RuntimeException(new Exception("AEC processor cannot be null."));
    }

    /* access modifiers changed from: protected */
    public AudioPipe createOutputMixer(AudioConfig audioConfig) {
        return new AudioMixer(audioConfig);
    }

    public boolean destroy() {
        synchronized (this._destroyLock) {
            if (this._destroyed) {
                return false;
            }
            this._destroyed = true;
            getProcessor().destroy();
            getOutputTrack().destroy();
            return true;
        }
    }

    public AudioConfig getConfig() {
        return getProcessor().getConfig();
    }

    public AudioPipe getOutputMixer() {
        return this._outputMixer;
    }

    public AudioSink getOutputMixerSink() {
        return this._outputMixerSink;
    }

    /* access modifiers changed from: package-private */
    public AudioTrack getOutputTrack() {
        return this._outputTrack;
    }

    public AecPipe getProcessor() {
        return this._processor;
    }

    private void setOutputMixer(AudioPipe audioPipe) {
        this._outputMixer = audioPipe;
    }

    private void setOutputMixerSink(AudioSink audioSink) {
        this._outputMixerSink = audioSink;
    }

    private void setOutputTrack(AudioTrack audioTrack) {
        this._outputTrack = audioTrack;
    }

    private void setProcessor(AecPipe aecPipe) {
        this._processor = aecPipe;
    }
}
