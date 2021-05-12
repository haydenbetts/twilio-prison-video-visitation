package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AudioTrack extends MediaTrack<IAudioOutput, IAudioOutputCollection, IAudioInput, IAudioInputCollection, IAudioElement, AudioSource, AudioSink, AudioPipe, AudioTrack, AudioBranch, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat> implements IAudioTrack, IMediaTrack {
    private ArrayList<IAudioElement> __elements;
    private double __gain;
    /* access modifiers changed from: private */
    public List<IAction1<Double>> __onLevel;
    /* access modifiers changed from: private */
    public List<IAction1<AudioBuffer>> __onPcmBuffer;
    private IAudioInput __pcmGainInput;
    private IAudioOutput __pcmGainOutput;
    private IAudioInput __pcmVolumeInput;
    private IAudioOutput __pcmVolumeOutput;
    private double __volume;
    private IAction1<Double> _onLevel;
    private IAction1<AudioBuffer> _onPcmBuffer;

    public String getLabel() {
        return "Audio Track";
    }

    /* access modifiers changed from: protected */
    public void addElement(IAudioElement iAudioElement) {
        if (iAudioElement instanceof AudioBranch) {
            for (AudioTrack addOnPcmBuffer : (AudioTrack[]) ((AudioBranch) iAudioElement).getTracks()) {
                addOnPcmBuffer.addOnPcmBuffer(new IActionDelegate1<AudioBuffer>() {
                    public String getId() {
                        return "fm.liveswitch.AudioTrack.track_OnPcmBuffer";
                    }

                    public void invoke(AudioBuffer audioBuffer) {
                        AudioTrack.this.track_OnPcmBuffer(audioBuffer);
                    }
                });
            }
        } else {
            if (iAudioElement instanceof IAudioInput) {
                IAudioInput iAudioInput = (IAudioInput) iAudioElement;
                AudioFormat audioFormat = (AudioFormat) iAudioInput.getInputFormat();
                if (audioFormat != null && audioFormat.getIsPcm()) {
                    if (this.__pcmGainInput == null && this.__pcmGainOutput == null) {
                        this.__pcmGainInput = iAudioInput;
                        iAudioInput.addOnProcessFrame(new IActionDelegate1<AudioFrame>() {
                            public String getId() {
                                return "fm.liveswitch.AudioTrack.pcmGain_OnProcessFrame";
                            }

                            public void invoke(AudioFrame audioFrame) {
                                AudioTrack.this.pcmGain_OnProcessFrame(audioFrame);
                            }
                        });
                    }
                    IAudioInput iAudioInput2 = this.__pcmVolumeInput;
                    if (iAudioInput2 != null) {
                        iAudioInput2.removeOnProcessFrame(new IActionDelegate1<AudioFrame>() {
                            public String getId() {
                                return "fm.liveswitch.AudioTrack.pcmVolume_OnProcessFrame";
                            }

                            public void invoke(AudioFrame audioFrame) {
                                AudioTrack.this.pcmVolume_OnProcessFrame(audioFrame);
                            }
                        });
                    }
                    IAudioOutput iAudioOutput = this.__pcmVolumeOutput;
                    if (iAudioOutput != null) {
                        iAudioOutput.removeOnRaiseFrame(new IActionDelegate1<AudioFrame>() {
                            public String getId() {
                                return "fm.liveswitch.AudioTrack.pcmVolume_OnProcessFrame";
                            }

                            public void invoke(AudioFrame audioFrame) {
                                AudioTrack.this.pcmVolume_OnProcessFrame(audioFrame);
                            }
                        });
                    }
                    this.__pcmVolumeInput = iAudioInput;
                    iAudioInput.addOnProcessFrame(new IActionDelegate1<AudioFrame>() {
                        public String getId() {
                            return "fm.liveswitch.AudioTrack.pcmVolume_OnProcessFrame";
                        }

                        public void invoke(AudioFrame audioFrame) {
                            AudioTrack.this.pcmVolume_OnProcessFrame(audioFrame);
                        }
                    });
                }
            }
            if (iAudioElement instanceof IAudioOutput) {
                IAudioOutput iAudioOutput2 = (IAudioOutput) iAudioElement;
                AudioFormat audioFormat2 = (AudioFormat) iAudioOutput2.getOutputFormat();
                if (audioFormat2 != null && audioFormat2.getIsPcm()) {
                    if (this.__pcmGainInput == null && this.__pcmGainOutput == null) {
                        this.__pcmGainOutput = iAudioOutput2;
                        iAudioOutput2.addOnRaiseFrame(new IActionDelegate1<AudioFrame>() {
                            public String getId() {
                                return "fm.liveswitch.AudioTrack.pcmGain_OnProcessFrame";
                            }

                            public void invoke(AudioFrame audioFrame) {
                                AudioTrack.this.pcmGain_OnProcessFrame(audioFrame);
                            }
                        });
                    }
                    IAudioInput iAudioInput3 = this.__pcmVolumeInput;
                    if (iAudioInput3 != null) {
                        iAudioInput3.removeOnProcessFrame(new IActionDelegate1<AudioFrame>() {
                            public String getId() {
                                return "fm.liveswitch.AudioTrack.pcmVolume_OnProcessFrame";
                            }

                            public void invoke(AudioFrame audioFrame) {
                                AudioTrack.this.pcmVolume_OnProcessFrame(audioFrame);
                            }
                        });
                    }
                    IAudioOutput iAudioOutput3 = this.__pcmVolumeOutput;
                    if (iAudioOutput3 != null) {
                        iAudioOutput3.removeOnRaiseFrame(new IActionDelegate1<AudioFrame>() {
                            public String getId() {
                                return "fm.liveswitch.AudioTrack.pcmVolume_OnProcessFrame";
                            }

                            public void invoke(AudioFrame audioFrame) {
                                AudioTrack.this.pcmVolume_OnProcessFrame(audioFrame);
                            }
                        });
                    }
                    this.__pcmVolumeOutput = iAudioOutput2;
                    iAudioOutput2.addOnRaiseFrame(new IActionDelegate1<AudioFrame>() {
                        public String getId() {
                            return "fm.liveswitch.AudioTrack.pcmVolume_OnProcessFrame";
                        }

                        public void invoke(AudioFrame audioFrame) {
                            AudioTrack.this.pcmVolume_OnProcessFrame(audioFrame);
                        }
                    });
                }
            }
        }
        if (ArrayListExtensions.getCount(this.__elements) == 0 && (iAudioElement instanceof AudioSource)) {
            ((AudioSource) iAudioElement).addOnStateChange(new IActionDelegate1<AudioSource>() {
                public String getId() {
                    return "fm.liveswitch.AudioTrack.source_OnStateChange";
                }

                public void invoke(AudioSource audioSource) {
                    AudioTrack.this.source_OnStateChange(audioSource);
                }
            });
        }
        this.__elements.add(iAudioElement);
    }

    public void addOnLevel(IAction1<Double> iAction1) {
        if (iAction1 != null) {
            if (this._onLevel == null) {
                this._onLevel = new IAction1<Double>() {
                    public void invoke(Double d) {
                        Iterator it = new ArrayList(AudioTrack.this.__onLevel).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(d);
                        }
                    }
                };
            }
            this.__onLevel.add(iAction1);
        }
    }

    /* access modifiers changed from: package-private */
    public void addOnPcmBuffer(IAction1<AudioBuffer> iAction1) {
        if (iAction1 != null) {
            if (this._onPcmBuffer == null) {
                this._onPcmBuffer = new IAction1<AudioBuffer>() {
                    public void invoke(AudioBuffer audioBuffer) {
                        Iterator it = new ArrayList(AudioTrack.this.__onPcmBuffer).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(audioBuffer);
                        }
                    }
                };
            }
            this.__onPcmBuffer.add(iAction1);
        }
    }

    private void applyGain(AudioBuffer audioBuffer) {
        if (audioBuffer != null) {
            audioBuffer.applyGain(getGain());
        }
    }

    private void applyVolume(AudioBuffer audioBuffer) {
        if (audioBuffer != null) {
            audioBuffer.applyGain(getVolume());
        }
    }

    /* access modifiers changed from: protected */
    public IAudioInput[] arrayFromInputs(ArrayList<IAudioInput> arrayList) {
        return (IAudioInput[]) arrayList.toArray(new IAudioInput[0]);
    }

    /* access modifiers changed from: protected */
    public IAudioOutput[] arrayFromOutputs(ArrayList<IAudioOutput> arrayList) {
        return (IAudioOutput[]) arrayList.toArray(new IAudioOutput[0]);
    }

    /* access modifiers changed from: protected */
    public AudioSink[] arrayFromSinks(ArrayList<AudioSink> arrayList) {
        return (AudioSink[]) arrayList.toArray(new AudioSink[0]);
    }

    public AudioTrack() {
        this.__onLevel = new ArrayList();
        this.__onPcmBuffer = new ArrayList();
        this._onLevel = null;
        this._onPcmBuffer = null;
        this.__gain = 1.0d;
        this.__volume = 1.0d;
        this.__elements = new ArrayList<>();
        initialize((IAudioElement) null);
    }

    public AudioTrack(IAudioElement iAudioElement) {
        this.__onLevel = new ArrayList();
        this.__onPcmBuffer = new ArrayList();
        this._onLevel = null;
        this._onPcmBuffer = null;
        this.__gain = 1.0d;
        this.__volume = 1.0d;
        this.__elements = new ArrayList<>();
        initialize(iAudioElement);
    }

    public AudioTrack(AudioTrack[] audioTrackArr) {
        this.__onLevel = new ArrayList();
        this.__onPcmBuffer = new ArrayList();
        AudioBranch audioBranch = null;
        this._onLevel = null;
        this._onPcmBuffer = null;
        this.__gain = 1.0d;
        this.__volume = 1.0d;
        this.__elements = new ArrayList<>();
        initialize(audioTrackArr != null ? new AudioBranch(audioTrackArr) : audioBranch);
    }

    /* access modifiers changed from: protected */
    public AudioBranch branchFromTracks(AudioTrack[] audioTrackArr) {
        return new AudioBranch(audioTrackArr);
    }

    public AudioConfig getConfig() {
        IAudioInput iAudioInput = this.__pcmGainInput;
        if (iAudioInput != null) {
            return iAudioInput.getConfig();
        }
        IAudioOutput iAudioOutput = this.__pcmGainOutput;
        if (iAudioOutput != null) {
            return iAudioOutput.getConfig();
        }
        IAudioInput iAudioInput2 = this.__pcmVolumeInput;
        if (iAudioInput2 != null) {
            return iAudioInput2.getConfig();
        }
        IAudioOutput iAudioOutput2 = this.__pcmVolumeOutput;
        if (iAudioOutput2 != null) {
            return iAudioOutput2.getConfig();
        }
        return null;
    }

    public IAudioElement[] getElements() {
        return (IAudioElement[]) this.__elements.toArray(new IAudioElement[0]);
    }

    public double getGain() {
        return this.__gain;
    }

    public double getVolume() {
        return this.__volume;
    }

    private void initialize(IAudioElement iAudioElement) {
        if (iAudioElement != null) {
            addElement(iAudioElement);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isBranch(IAudioElement iAudioElement) {
        return iAudioElement instanceof AudioBranch;
    }

    /* access modifiers changed from: protected */
    public boolean isInput(IAudioElement iAudioElement) {
        return iAudioElement instanceof IAudioInput;
    }

    /* access modifiers changed from: protected */
    public boolean isOutput(IAudioElement iAudioElement) {
        return iAudioElement instanceof IAudioOutput;
    }

    /* access modifiers changed from: protected */
    public boolean isPipe(IAudioElement iAudioElement) {
        return iAudioElement instanceof AudioPipe;
    }

    /* access modifiers changed from: protected */
    public boolean isSink(IAudioElement iAudioElement) {
        return iAudioElement instanceof AudioSink;
    }

    /* access modifiers changed from: protected */
    public boolean isSource(IAudioElement iAudioElement) {
        return iAudioElement instanceof AudioSource;
    }

    /* access modifiers changed from: protected */
    public boolean isStream(IAudioElement iAudioElement) {
        return iAudioElement instanceof AudioStream;
    }

    /* access modifiers changed from: private */
    public void pcmGain_OnProcessFrame(AudioFrame audioFrame) {
        AudioBuffer audioBuffer = (AudioBuffer) audioFrame.getBuffer(AudioFormat.getPcmName());
        applyGain(audioBuffer);
        raisePcmBuffer(audioBuffer);
        raiseLevel(audioBuffer);
        if (this.__pcmVolumeInput == null && this.__pcmVolumeOutput == null) {
            applyVolume(audioBuffer);
        }
    }

    /* access modifiers changed from: private */
    public void pcmVolume_OnProcessFrame(AudioFrame audioFrame) {
        AudioBuffer audioBuffer = (AudioBuffer) audioFrame.getBuffer(AudioFormat.getPcmName());
        if (this.__pcmGainInput == null && this.__pcmGainOutput == null) {
            applyGain(audioBuffer);
            raisePcmBuffer(audioBuffer);
            raiseLevel(audioBuffer);
        }
        applyVolume(audioBuffer);
    }

    private void raiseLevel(AudioBuffer audioBuffer) {
        IAction1<Double> iAction1;
        if (audioBuffer != null && (iAction1 = this._onLevel) != null) {
            iAction1.invoke(Double.valueOf(audioBuffer.calculateLevel()));
        }
    }

    private void raisePcmBuffer(AudioBuffer audioBuffer) {
        IAction1<AudioBuffer> iAction1;
        if (audioBuffer != null && (iAction1 = this._onPcmBuffer) != null) {
            iAction1.invoke(audioBuffer);
        }
    }

    public void removeOnLevel(IAction1<Double> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onLevel, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onLevel.remove(iAction1);
        if (this.__onLevel.size() == 0) {
            this._onLevel = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void removeOnPcmBuffer(IAction1<AudioBuffer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onPcmBuffer, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onPcmBuffer.remove(iAction1);
        if (this.__onPcmBuffer.size() == 0) {
            this._onPcmBuffer = null;
        }
    }

    public void setGain(double d) {
        this.__gain = MathAssistant.max(d, 0.0d);
    }

    public void setVolume(double d) {
        this.__volume = MathAssistant.min(MathAssistant.max(d, 0.0d), 1.0d);
    }

    /* access modifiers changed from: private */
    public void source_OnStateChange(AudioSource audioSource) {
        MediaSourceState state = audioSource.getState();
        if (state == MediaSourceState.Started) {
            super.raiseOnStarted();
        } else if (state == MediaSourceState.Stopped) {
            super.raiseOnStopped();
        }
    }

    /* access modifiers changed from: private */
    public void track_OnPcmBuffer(AudioBuffer audioBuffer) {
        if (this.__pcmVolumeInput == null && this.__pcmVolumeOutput == null && this.__pcmGainInput == null && this.__pcmGainOutput == null) {
            applyGain(audioBuffer);
            raisePcmBuffer(audioBuffer);
            raiseLevel(audioBuffer);
            applyVolume(audioBuffer);
        }
    }
}
