package fm.liveswitch;

import fm.liveswitch.pcm.Format;

public class SoundReframer extends AudioPipe {
    private SoundReframerContext __reframerContext;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "Sound Reframer";
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        this.__reframerContext.processFrame(audioFrame, audioBuffer);
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

    public void setDisableTimestampReset(boolean z) {
        this.__reframerContext.setDisableTimestampReset(z);
    }

    public void setForceTimestampReset(boolean z) {
        this.__reframerContext.setForceTimestampReset(z);
    }

    public void setTimestampResetInterval(int i) {
        this.__reframerContext.setTimestampResetInterval(i);
    }

    public SoundReframer(AudioConfig audioConfig) {
        this(audioConfig, 0);
    }

    public SoundReframer(AudioConfig audioConfig, int i) {
        super(new Format(audioConfig), new Format(audioConfig));
        if (((AudioFormat) super.getInputFormat()).getIsPcm()) {
            SoundReframerContext soundReframerContext = new SoundReframerContext(i <= 0 ? 20 : i, audioConfig);
            this.__reframerContext = soundReframerContext;
            soundReframerContext.addOnFrame(new IActionDelegate1<AudioFrame>() {
                public String getId() {
                    return "fm.liveswitch.MediaPipe<fm.liveswitch.IAudioOutput,fm.liveswitch.IAudioOutputCollection,fm.liveswitch.IAudioInput,fm.liveswitch.IAudioInputCollection,fm.liveswitch.AudioPipe,fm.liveswitch.AudioFrame,fm.liveswitch.AudioBuffer,fm.liveswitch.AudioBufferCollection,fm.liveswitch.AudioFormat>.raiseFrame";
                }

                public void invoke(AudioFrame audioFrame) {
                    SoundReframer.this.raiseFrame(audioFrame);
                }
            });
            return;
        }
        throw new RuntimeException(new Exception("Input format must be PCM."));
    }

    public SoundReframer(IAudioOutput iAudioOutput, int i) {
        this(iAudioOutput.getConfig(), i);
        super.addInput(iAudioOutput);
    }
}
