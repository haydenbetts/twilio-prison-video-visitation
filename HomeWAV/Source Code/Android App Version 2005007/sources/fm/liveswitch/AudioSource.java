package fm.liveswitch;

public abstract class AudioSource extends MediaSource<IAudioOutput, IAudioInput, IAudioInputCollection, AudioSource, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat> implements IAudioOutput, IMediaOutput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IOutput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IAudioElement, IMediaElement, IElement {
    private long __baseSystemTimestamp;
    private long __baseTimestamp;
    private SoundReframerContext __reframerContext;
    private double __volume;

    public AudioSource(AudioFormat audioFormat) {
        this(audioFormat, 20);
    }

    public AudioSource(AudioFormat audioFormat, int i) {
        super(audioFormat);
        this.__volume = 1.0d;
        this.__baseTimestamp = -1;
        this.__baseSystemTimestamp = -1;
        SoundReframerContext soundReframerContext = new SoundReframerContext(i <= 0 ? 20 : i, audioFormat.getConfig());
        this.__reframerContext = soundReframerContext;
        soundReframerContext.addOnFrame(new IActionDelegate1<AudioFrame>() {
            public String getId() {
                return "fm.liveswitch.AudioSource.doRaiseFrame";
            }

            public void invoke(AudioFrame audioFrame) {
                AudioSource.this.doRaiseFrame(audioFrame);
            }
        });
    }

    public static int calculateDuration(int i, int i2, int i3) {
        return SoundUtility.calculateDuration(i, i2, i3);
    }

    public static int calculateDuration(int i, AudioConfig audioConfig) {
        return SoundUtility.calculateDuration(i, audioConfig);
    }

    public int calculateDuration(int i) {
        return SoundUtility.calculateDuration(i, ((AudioFormat) super.getOutputFormat()).getConfig());
    }

    public static int calculateDurationFloat(int i, int i2, int i3) {
        return SoundUtility.calculateDurationFloat(i, i2, i3);
    }

    public int calculateDurationFloat(int i) {
        return SoundUtility.calculateDurationFloat(i, ((AudioFormat) super.getOutputFormat()).getConfig());
    }

    /* access modifiers changed from: protected */
    public IAudioInputCollection createInputCollection(IAudioOutput iAudioOutput) {
        return new IAudioInputCollection(iAudioOutput);
    }

    /* access modifiers changed from: private */
    public void doRaiseFrame(AudioFrame audioFrame) {
        AudioBuffer audioBuffer;
        if (!(!((AudioFormat) super.getOutputFormat()).getIsPcm() || (audioBuffer = (AudioBuffer) audioFrame.getBuffer(AudioFormat.getPcmName())) == null || getVolume() == 1.0d)) {
            audioBuffer.applyGain(getVolume());
        }
        super.raiseFrame(audioFrame);
    }

    public AudioConfig getConfig() {
        AudioFormat audioFormat = (AudioFormat) super.getOutputFormat();
        if (audioFormat != null) {
            return audioFormat.getConfig();
        }
        return null;
    }

    public boolean getDisableTimestampReset() {
        return this.__reframerContext.getDisableTimestampReset();
    }

    public boolean getForceTimestampReset() {
        return this.__reframerContext.getForceTimestampReset();
    }

    public int getFrameDuration() {
        return this.__reframerContext.getFrameDuration();
    }

    public int getTimestampResetInterval() {
        return this.__reframerContext.getTimestampResetInterval();
    }

    public double getVolume() {
        return this.__volume;
    }

    /* access modifiers changed from: protected */
    public void raiseFrame(AudioFrame audioFrame) {
        trySetSystemTimestamp(audioFrame);
        trySetTimestamp(audioFrame);
        AudioBuffer audioBuffer = (AudioBuffer) audioFrame.getLastBuffer();
        if (!Global.equals(((AudioFormat) audioBuffer.getFormat()).getName(), AudioFormat.getPcmName())) {
            audioBuffer = null;
        }
        if (audioBuffer != null) {
            this.__reframerContext.processFrame(audioFrame, audioBuffer);
        } else if (audioFrame.getTimestamp() != -1) {
            doRaiseFrame(audioFrame);
        } else {
            throw new RuntimeException(new Exception("Encoded audio requires a timestamp to be specified."));
        }
    }

    public void setDisableTimestampReset(boolean z) {
        this.__reframerContext.setDisableTimestampReset(z);
    }

    public void setForceTimestampReset(boolean z) {
        this.__reframerContext.setForceTimestampReset(z);
    }

    public void setTimestampResetInterval(int i) {
        this.__reframerContext.setTimestampResetInterval(i);
    }

    public void setVolume(double d) {
        this.__volume = MathAssistant.min(MathAssistant.max(d, 0.0d), 1.0d);
    }

    /* access modifiers changed from: protected */
    public boolean trySetSystemTimestamp(AudioFrame audioFrame) {
        if (audioFrame.getTimestamp() != -1 && audioFrame.getSystemTimestamp() == -1) {
            if (this.__baseSystemTimestamp == -1) {
                this.__baseSystemTimestamp = ManagedStopwatch.getTimestamp();
                this.__baseTimestamp = audioFrame.getTimestamp();
            }
            audioFrame.setSystemTimestamp(MediaFrame.calculateSystemTimestamp(this.__baseSystemTimestamp, audioFrame.getTimestamp(), ((AudioFormat) super.getOutputFormat()).getClockRate(), this.__baseTimestamp));
        }
        return audioFrame.getSystemTimestamp() >= 0;
    }

    /* access modifiers changed from: protected */
    public boolean trySetTimestamp(AudioFrame audioFrame) {
        if (audioFrame.getSystemTimestamp() != -1 && audioFrame.getTimestamp() == -1) {
            if (this.__baseSystemTimestamp == -1) {
                this.__baseSystemTimestamp = audioFrame.getSystemTimestamp();
            }
            audioFrame.setTimestamp(MediaFrame.calculateTimestamp(this.__baseSystemTimestamp, audioFrame.getSystemTimestamp(), ((AudioFormat) super.getOutputFormat()).getClockRate()));
        }
        return audioFrame.getTimestamp() >= 0;
    }
}
