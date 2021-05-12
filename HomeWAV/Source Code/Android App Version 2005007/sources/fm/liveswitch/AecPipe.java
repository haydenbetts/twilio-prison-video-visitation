package fm.liveswitch;

public abstract class AecPipe extends AudioPipe {
    private IAudioInput __speaker;

    /* access modifiers changed from: protected */
    public abstract void processSpeakerFrame(AudioFrame audioFrame);

    public AecPipe(AudioFormat audioFormat) {
        super(audioFormat.clone(), audioFormat.clone());
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        IAudioInput iAudioInput = this.__speaker;
        if (iAudioInput != null) {
            iAudioInput.removeOnProcessFrame(new IActionDelegate1<AudioFrame>() {
                public String getId() {
                    return "fm.liveswitch.AecPipe.speaker_ProcessFrame";
                }

                public void invoke(AudioFrame audioFrame) {
                    AecPipe.this.speaker_ProcessFrame(audioFrame);
                }
            });
            this.__speaker = null;
        }
    }

    public IAudioInput getSpeaker() {
        return this.__speaker;
    }

    public void setSpeaker(IAudioInput iAudioInput) {
        if (!Global.equals(this.__speaker, iAudioInput)) {
            IAudioInput iAudioInput2 = this.__speaker;
            if (iAudioInput2 != null) {
                iAudioInput2.removeOnProcessFrame(new IActionDelegate1<AudioFrame>() {
                    public String getId() {
                        return "fm.liveswitch.AecPipe.speaker_ProcessFrame";
                    }

                    public void invoke(AudioFrame audioFrame) {
                        AecPipe.this.speaker_ProcessFrame(audioFrame);
                    }
                });
            }
            this.__speaker = iAudioInput;
            if (iAudioInput != null) {
                iAudioInput.addOnProcessFrame(new IActionDelegate1<AudioFrame>() {
                    public String getId() {
                        return "fm.liveswitch.AecPipe.speaker_ProcessFrame";
                    }

                    public void invoke(AudioFrame audioFrame) {
                        AecPipe.this.speaker_ProcessFrame(audioFrame);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void speaker_ProcessFrame(AudioFrame audioFrame) {
        processSpeakerFrame(audioFrame);
    }
}
