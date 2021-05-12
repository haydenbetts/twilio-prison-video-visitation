package fm.liveswitch;

import fm.liveswitch.dtmf.Mode;
import fm.liveswitch.dtmf.Receiver;
import fm.liveswitch.dtmf.Sender;
import fm.liveswitch.dtmf.Tone;
import fm.liveswitch.sdp.FormatParametersAttribute;
import fm.liveswitch.sdp.rtp.MapAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AudioStream extends MediaStream<IAudioOutput, IAudioOutputCollection, IAudioInput, IAudioInputCollection, IAudioElement, AudioSource, AudioSink, AudioPipe, AudioTrack, AudioBranch, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat, AudioFormatCollection> implements IAudioStream, IMediaStream, IStream, IAudioInput, IMediaInput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IInput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IAudioOutput, IMediaOutput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IOutput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IAudioElement, IMediaElement, IElement {
    private HashMap<Integer, Receiver> __dtmfReceivers;
    private HashMap<Integer, Sender> __dtmfSenders;
    private double __gain;
    /* access modifiers changed from: private */
    public List<IAction1<Tone[]>> __onDiscardOutboundDtmfTones;
    /* access modifiers changed from: private */
    public List<IAction1<Tone>> __onReceiveDtmfTone;
    /* access modifiers changed from: private */
    public List<IAction1<Tone>> __onReceiveDtmfToneChange;
    /* access modifiers changed from: private */
    public List<IAction1<Tone>> __onSendDtmfTone;
    /* access modifiers changed from: private */
    public List<IAction1<Tone>> __onSendDtmfToneChange;
    private double __volume;
    private boolean _discardOutboundDtmfTones;
    private Mode _dtmfMode;
    private IAction1<Tone[]> _onDiscardOutboundDtmfTones;
    private IAction1<Tone> _onReceiveDtmfTone;
    private IAction1<Tone> _onReceiveDtmfToneChange;
    private IAction1<Tone> _onSendDtmfTone;
    private IAction1<Tone> _onSendDtmfToneChange;

    /* access modifiers changed from: protected */
    public AudioFormat createRedFormat() {
        return null;
    }

    /* access modifiers changed from: protected */
    public AudioFormat createUlpFecFormat() {
        return null;
    }

    public void addOnDiscardOutboundDtmfTones(IAction1<Tone[]> iAction1) {
        if (iAction1 != null) {
            if (this._onDiscardOutboundDtmfTones == null) {
                this._onDiscardOutboundDtmfTones = new IAction1<Tone[]>() {
                    public void invoke(Tone[] toneArr) {
                        Iterator it = new ArrayList(AudioStream.this.__onDiscardOutboundDtmfTones).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(toneArr);
                        }
                    }
                };
            }
            this.__onDiscardOutboundDtmfTones.add(iAction1);
        }
    }

    public void addOnReceiveDtmfTone(IAction1<Tone> iAction1) {
        if (iAction1 != null) {
            if (this._onReceiveDtmfTone == null) {
                this._onReceiveDtmfTone = new IAction1<Tone>() {
                    public void invoke(Tone tone) {
                        Iterator it = new ArrayList(AudioStream.this.__onReceiveDtmfTone).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tone);
                        }
                    }
                };
            }
            this.__onReceiveDtmfTone.add(iAction1);
        }
    }

    public void addOnReceiveDtmfToneChange(IAction1<Tone> iAction1) {
        if (iAction1 != null) {
            if (this._onReceiveDtmfToneChange == null) {
                this._onReceiveDtmfToneChange = new IAction1<Tone>() {
                    public void invoke(Tone tone) {
                        Iterator it = new ArrayList(AudioStream.this.__onReceiveDtmfToneChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tone);
                        }
                    }
                };
            }
            this.__onReceiveDtmfToneChange.add(iAction1);
        }
    }

    public void addOnSendDtmfTone(IAction1<Tone> iAction1) {
        if (iAction1 != null) {
            if (this._onSendDtmfTone == null) {
                this._onSendDtmfTone = new IAction1<Tone>() {
                    public void invoke(Tone tone) {
                        Iterator it = new ArrayList(AudioStream.this.__onSendDtmfTone).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tone);
                        }
                    }
                };
            }
            this.__onSendDtmfTone.add(iAction1);
        }
    }

    public void addOnSendDtmfToneChange(IAction1<Tone> iAction1) {
        if (iAction1 != null) {
            if (this._onSendDtmfToneChange == null) {
                this._onSendDtmfToneChange = new IAction1<Tone>() {
                    public void invoke(Tone tone) {
                        Iterator it = new ArrayList(AudioStream.this.__onSendDtmfToneChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tone);
                        }
                    }
                };
            }
            this.__onSendDtmfToneChange.add(iAction1);
        }
    }

    public AudioStream() {
        this(false);
    }

    public AudioStream(boolean z) {
        this(new IAudioOutput[0], z);
    }

    public AudioStream(IAudioOutput iAudioOutput) {
        this(iAudioOutput, false);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AudioStream(fm.liveswitch.IAudioOutput r3, boolean r4) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000b
        L_0x0004:
            r0 = 1
            fm.liveswitch.IAudioOutput[] r0 = new fm.liveswitch.IAudioOutput[r0]
            r1 = 0
            r0[r1] = r3
            r3 = r0
        L_0x000b:
            r2.<init>((fm.liveswitch.IAudioOutput[]) r3, (boolean) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.AudioStream.<init>(fm.liveswitch.IAudioOutput, boolean):void");
    }

    public AudioStream(IAudioOutput iAudioOutput, IAudioInput iAudioInput) {
        this(iAudioOutput, iAudioInput, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AudioStream(IAudioOutput iAudioOutput, IAudioInput iAudioInput, boolean z) {
        this(iAudioOutput == null ? null : new IAudioOutput[]{iAudioOutput}, iAudioInput != null ? new IAudioInput[]{iAudioInput} : null, z);
    }

    public AudioStream(IAudioOutput[] iAudioOutputArr) {
        this(iAudioOutputArr, false);
    }

    public AudioStream(IAudioOutput[] iAudioOutputArr, boolean z) {
        this(iAudioOutputArr, (IAudioInput[]) null, z);
    }

    public AudioStream(IAudioOutput[] iAudioOutputArr, IAudioInput[] iAudioInputArr) {
        this(iAudioOutputArr, iAudioInputArr, false);
    }

    public AudioStream(IAudioOutput[] iAudioOutputArr, IAudioInput[] iAudioInputArr, boolean z) {
        super(StreamType.Audio, new JitterConfig());
        this.__onDiscardOutboundDtmfTones = new ArrayList();
        this.__onReceiveDtmfTone = new ArrayList();
        this.__onReceiveDtmfToneChange = new ArrayList();
        this.__onSendDtmfTone = new ArrayList();
        this.__onSendDtmfToneChange = new ArrayList();
        this._onDiscardOutboundDtmfTones = null;
        this._onReceiveDtmfTone = null;
        this._onReceiveDtmfToneChange = null;
        this._onSendDtmfTone = null;
        this._onSendDtmfToneChange = null;
        this.__gain = 1.0d;
        this.__volume = 1.0d;
        this.__dtmfSenders = new HashMap<>();
        this.__dtmfReceivers = new HashMap<>();
        if (iAudioOutputArr == null && iAudioInputArr == null) {
            throw new RuntimeException(new Exception("Cannot initialize audio stream if no inputs and no outputs are provided."));
        }
        super.setRedFecPolicy(RedFecPolicy.Disabled);
        super.setNackPolicy(NackPolicy.Disabled);
        super.setNackPliPolicy(NackPliPolicy.Disabled);
        super.setCcmFirPolicy(CcmFirPolicy.Disabled);
        super.setCcmLrrPolicy(CcmLrrPolicy.Disabled);
        super.setCcmTmmbrPolicy(CcmTmmbrPolicy.Disabled);
        super.setCcmTmmbnPolicy(CcmTmmbnPolicy.Disabled);
        super.setBandwidthAdaptationPolicy(BandwidthAdaptationPolicy.Disabled);
        if (iAudioOutputArr != null) {
            super.addInputs((TIOutput[]) iAudioOutputArr);
        }
        if (iAudioInputArr != null) {
            super.addOutputs((TIInput[]) iAudioInputArr);
        }
        initializeDtmf();
    }

    public AudioStream(LocalMedia localMedia) {
        this(localMedia, false);
    }

    public AudioStream(LocalMedia localMedia, boolean z) {
        this(localMedia, (RemoteMedia) null, z);
    }

    public AudioStream(LocalMedia localMedia, RemoteMedia remoteMedia) {
        this(localMedia, remoteMedia, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AudioStream(LocalMedia localMedia, RemoteMedia remoteMedia, boolean z) {
        this(localMedia == null ? null : (AudioTrack) localMedia.getAudioTrack(), remoteMedia != null ? (AudioTrack) remoteMedia.getAudioTrack() : null, z);
        super.setLocalMedia(localMedia);
        super.setRemoteMedia(remoteMedia);
    }

    public AudioStream(AudioTrack audioTrack) {
        this(audioTrack, false);
    }

    public AudioStream(AudioTrack audioTrack, boolean z) {
        this(audioTrack, (AudioTrack) null, z);
    }

    public AudioStream(AudioTrack audioTrack, AudioTrack audioTrack2) {
        this(audioTrack, audioTrack2, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AudioStream(AudioTrack audioTrack, AudioTrack audioTrack2, boolean z) {
        this(audioTrack == null ? null : (IAudioOutput[]) audioTrack.getOutputs(), audioTrack2 != null ? (IAudioInput[]) audioTrack2.getInputs() : null, z);
        super.setLocalTrack(audioTrack);
        super.setRemoteTrack(audioTrack2);
    }

    public AudioStream(IAudioInput iAudioInput) {
        this(iAudioInput, false);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AudioStream(fm.liveswitch.IAudioInput r3, boolean r4) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000b
        L_0x0004:
            r0 = 1
            fm.liveswitch.IAudioInput[] r0 = new fm.liveswitch.IAudioInput[r0]
            r1 = 0
            r0[r1] = r3
            r3 = r0
        L_0x000b:
            r2.<init>((fm.liveswitch.IAudioInput[]) r3, (boolean) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.AudioStream.<init>(fm.liveswitch.IAudioInput, boolean):void");
    }

    public AudioStream(IAudioInput[] iAudioInputArr) {
        this(iAudioInputArr, false);
    }

    public AudioStream(IAudioInput[] iAudioInputArr, boolean z) {
        this((IAudioOutput[]) null, iAudioInputArr, z);
    }

    public AudioStream(RemoteMedia remoteMedia) {
        this(remoteMedia, false);
    }

    public AudioStream(RemoteMedia remoteMedia, boolean z) {
        this((LocalMedia) null, remoteMedia, z);
    }

    /* access modifiers changed from: private */
    public void audioStream_OnProcessFrame(AudioFrame audioFrame) {
        Sender dtmfSender;
        if (!((AudioFormat) ((AudioBuffer) audioFrame.getLastBuffer()).getFormat()).getIsInjected() && super.getInjectionAllowed() && (dtmfSender = getDtmfSender(audioFrame)) != null && dtmfSender.hasTone() && dtmfSender.raiseTone(audioFrame.getDuration(), audioFrame.getTimestamp(), audioFrame.getSynchronizationSource()) && Global.equals(getDtmfMode(), Mode.Replace)) {
            audioFrame.setDiscard(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void close() {
        super.close();
        HashMap<Integer, Receiver> hashMap = this.__dtmfReceivers;
        if (hashMap != null) {
            for (Receiver next : HashMapExtensions.getValues(hashMap)) {
                super.removeOutput(next);
                next.destroy();
                next.removeOnTone(new IActionDelegate1<Tone>() {
                    public String getId() {
                        return "fm.liveswitch.AudioStream.dtmfReceiver_OnTone";
                    }

                    public void invoke(Tone tone) {
                        AudioStream.this.dtmfReceiver_OnTone(tone);
                    }
                });
                next.removeOnToneChange(new IActionDelegate1<Tone>() {
                    public String getId() {
                        return "fm.liveswitch.AudioStream.dtmfReceiver_OnToneChange";
                    }

                    public void invoke(Tone tone) {
                        AudioStream.this.dtmfReceiver_OnToneChange(tone);
                    }
                });
            }
            hashMap.clear();
        }
        HashMap<Integer, Sender> hashMap2 = this.__dtmfSenders;
        if (hashMap2 != null) {
            for (Sender next2 : HashMapExtensions.getValues(hashMap2)) {
                super.removeOutput(next2);
                next2.destroy();
                next2.removeOnTone(new IActionDelegate1<Tone>() {
                    public String getId() {
                        return "fm.liveswitch.AudioStream.dtmfSender_OnTone";
                    }

                    public void invoke(Tone tone) {
                        AudioStream.this.dtmfSender_OnTone(tone);
                    }
                });
                next2.removeOnToneChange(new IActionDelegate1<Tone>() {
                    public String getId() {
                        return "fm.liveswitch.AudioStream.dtmfSender_OnToneChange";
                    }

                    public void invoke(Tone tone) {
                        AudioStream.this.dtmfSender_OnToneChange(tone);
                    }
                });
            }
            hashMap2.clear();
        }
        super.removeOnProcessFrame(new IActionDelegate1<AudioFrame>() {
            public String getId() {
                return "fm.liveswitch.AudioStream.audioStream_OnProcessFrame";
            }

            public void invoke(AudioFrame audioFrame) {
                AudioStream.this.audioStream_OnProcessFrame(audioFrame);
            }
        });
    }

    /* access modifiers changed from: protected */
    public AudioFormat createFormat(MapAttribute mapAttribute, FormatParametersAttribute formatParametersAttribute) {
        return createFormat(mapAttribute.getFormatName(), mapAttribute.getClockRate(), mapAttribute.getFormatParameters(), mapAttribute.getPayloadType());
    }

    /* access modifiers changed from: protected */
    public AudioFormat createFormat(String str, int i, String str2, int i2) {
        IntegerHolder integerHolder = new IntegerHolder(1);
        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(str2, integerHolder);
        int value = integerHolder.getValue();
        if (!StringExtensions.isNullOrEmpty(str2) && !tryParseIntegerValue) {
            return null;
        }
        AudioFormat audioFormat = new AudioFormat(str, i, value);
        audioFormat.setRegisteredPayloadType(i2);
        audioFormat.setIsPacketized(true);
        for (IAudioOutput outputFormat : (IAudioOutput[]) super.getInputs()) {
            AudioFormat audioFormat2 = (AudioFormat) outputFormat.getOutputFormat();
            if (audioFormat2.isEquivalent(audioFormat)) {
                audioFormat.setIsInjected(audioFormat2.getIsInjected());
            }
        }
        for (IAudioInput inputFormat : (IAudioInput[]) super.getOutputs()) {
            AudioFormat audioFormat3 = (AudioFormat) inputFormat.getInputFormat();
            if (audioFormat3.isEquivalent(audioFormat)) {
                audioFormat.setIsInjected(audioFormat3.getIsInjected());
            }
        }
        return audioFormat;
    }

    /* access modifiers changed from: protected */
    public IAudioInputCollection createInputCollection(IAudioOutput iAudioOutput) {
        return new IAudioInputCollection(iAudioOutput);
    }

    /* access modifiers changed from: protected */
    public AudioFormatCollection createMediaFormatCollection() {
        return new AudioFormatCollection();
    }

    /* access modifiers changed from: protected */
    public IAudioOutputCollection createOutputCollection(IAudioInput iAudioInput) {
        return new IAudioOutputCollection(iAudioInput);
    }

    /* access modifiers changed from: private */
    public void dtmfReceiver_OnTone(Tone tone) {
        IAction1<Tone> iAction1 = this._onReceiveDtmfTone;
        if (iAction1 != null) {
            iAction1.invoke(tone);
        }
    }

    /* access modifiers changed from: private */
    public void dtmfReceiver_OnToneChange(Tone tone) {
        IAction1<Tone> iAction1 = this._onReceiveDtmfToneChange;
        if (iAction1 != null) {
            iAction1.invoke(tone);
        }
    }

    /* access modifiers changed from: private */
    public void dtmfSender_OnTone(Tone tone) {
        IAction1<Tone> iAction1 = this._onSendDtmfTone;
        if (iAction1 != null) {
            iAction1.invoke(tone);
        }
    }

    /* access modifiers changed from: private */
    public void dtmfSender_OnToneChange(Tone tone) {
        IAction1<Tone> iAction1 = this._onSendDtmfToneChange;
        if (iAction1 != null) {
            iAction1.invoke(tone);
        }
    }

    /* access modifiers changed from: protected */
    public AudioFormat[] formatArrayFromList(ArrayList<AudioFormat> arrayList) {
        return (AudioFormat[]) arrayList.toArray(new AudioFormat[0]);
    }

    public AudioConfig getConfig() {
        return getOutputConfig();
    }

    private Sender getCurrentDtmfSender() {
        return getDtmfSender((AudioFormat) super.getInputFormat());
    }

    public boolean getDiscardOutboundDtmfTones() {
        return this._discardOutboundDtmfTones;
    }

    public Mode getDtmfMode() {
        return this._dtmfMode;
    }

    private Sender getDtmfSender(AudioBuffer audioBuffer) {
        if (audioBuffer == null) {
            return null;
        }
        return getDtmfSender((AudioFormat) audioBuffer.getFormat());
    }

    private Sender getDtmfSender(int i) {
        Holder holder = new Holder(null);
        HashMapExtensions.tryGetValue(this.__dtmfSenders, Integer.valueOf(i), holder);
        return (Sender) holder.getValue();
    }

    private Sender getDtmfSender(AudioFormat audioFormat) {
        if (audioFormat == null) {
            return null;
        }
        return getDtmfSender(audioFormat.getClockRate());
    }

    private Sender getDtmfSender(AudioFrame audioFrame) {
        if (audioFrame == null) {
            return null;
        }
        return getDtmfSender((AudioBuffer) audioFrame.getBuffer(true));
    }

    public boolean getG722Disabled() {
        return super.getCodecDisabled(AudioFormat.getG722Name());
    }

    public double getGain() {
        return this.__gain;
    }

    public AudioConfig getInputConfig() {
        AudioFormat audioFormat = (AudioFormat) super.getInputFormat();
        if (audioFormat != null) {
            return audioFormat.getConfig();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean getInputSourceMuted(IAudioOutput iAudioOutput) {
        AudioSource audioSource = (AudioSource) Global.tryCast(iAudioOutput, AudioSource.class);
        if (audioSource != null) {
            return audioSource.getOutputMuted();
        }
        AudioPipe audioPipe = (AudioPipe) Global.tryCast(iAudioOutput, AudioPipe.class);
        return audioPipe != null && super.getInputSourceMuted((TIOutput[]) audioPipe.getInputs());
    }

    public boolean getOpusDisabled() {
        return super.getCodecDisabled(AudioFormat.getOpusName());
    }

    public AudioConfig getOutputConfig() {
        AudioFormat audioFormat = (AudioFormat) super.getOutputFormat();
        if (audioFormat != null) {
            return audioFormat.getConfig();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean getOutputSinkMuted(IAudioInput iAudioInput) {
        AudioSink audioSink = (AudioSink) Global.tryCast(iAudioInput, AudioSink.class);
        if (audioSink != null) {
            return audioSink.getInputMuted();
        }
        AudioPipe audioPipe = (AudioPipe) Global.tryCast(iAudioInput, AudioPipe.class);
        return audioPipe != null && super.getOutputSinkMuted((TIInput[]) audioPipe.getOutputs());
    }

    public boolean getPcmaDisabled() {
        return super.getCodecDisabled(AudioFormat.getPcmaName());
    }

    public boolean getPcmuDisabled() {
        return super.getCodecDisabled(AudioFormat.getPcmuName());
    }

    public double getVolume() {
        return this.__volume;
    }

    private void initializeDtmf() {
        setDtmfMode(Mode.Augment);
        for (IAudioOutput outputFormat : (IAudioOutput[]) super.getInputs()) {
            AudioFormat audioFormat = (AudioFormat) outputFormat.getOutputFormat();
            if (audioFormat != null && !audioFormat.getIsInjected()) {
                initializeDtmfSender(audioFormat.getClockRate());
            }
        }
        for (IAudioInput inputFormat : (IAudioInput[]) super.getOutputs()) {
            AudioFormat audioFormat2 = (AudioFormat) inputFormat.getInputFormat();
            if (audioFormat2 != null && !audioFormat2.getIsInjected()) {
                initializeDtmfReceiver(audioFormat2.getClockRate());
            }
        }
        super.removeOnProcessFrame(new IActionDelegate1<AudioFrame>() {
            public String getId() {
                return "fm.liveswitch.AudioStream.audioStream_OnProcessFrame";
            }

            public void invoke(AudioFrame audioFrame) {
                AudioStream.this.audioStream_OnProcessFrame(audioFrame);
            }
        });
        super.addOnProcessFrame(new IActionDelegate1<AudioFrame>() {
            public String getId() {
                return "fm.liveswitch.AudioStream.audioStream_OnProcessFrame";
            }

            public void invoke(AudioFrame audioFrame) {
                AudioStream.this.audioStream_OnProcessFrame(audioFrame);
            }
        });
    }

    private void initializeDtmfReceiver(int i) {
        if (!this.__dtmfReceivers.containsKey(Integer.valueOf(i))) {
            Receiver receiver = new Receiver(i);
            receiver.removeOnTone(new IActionDelegate1<Tone>() {
                public String getId() {
                    return "fm.liveswitch.AudioStream.dtmfReceiver_OnTone";
                }

                public void invoke(Tone tone) {
                    AudioStream.this.dtmfReceiver_OnTone(tone);
                }
            });
            receiver.addOnTone(new IActionDelegate1<Tone>() {
                public String getId() {
                    return "fm.liveswitch.AudioStream.dtmfReceiver_OnTone";
                }

                public void invoke(Tone tone) {
                    AudioStream.this.dtmfReceiver_OnTone(tone);
                }
            });
            receiver.removeOnToneChange(new IActionDelegate1<Tone>() {
                public String getId() {
                    return "fm.liveswitch.AudioStream.dtmfReceiver_OnToneChange";
                }

                public void invoke(Tone tone) {
                    AudioStream.this.dtmfReceiver_OnToneChange(tone);
                }
            });
            receiver.addOnToneChange(new IActionDelegate1<Tone>() {
                public String getId() {
                    return "fm.liveswitch.AudioStream.dtmfReceiver_OnToneChange";
                }

                public void invoke(Tone tone) {
                    AudioStream.this.dtmfReceiver_OnToneChange(tone);
                }
            });
            super.addOutput(receiver);
            HashMapExtensions.set(HashMapExtensions.getItem(this.__dtmfReceivers), Integer.valueOf(i), receiver);
        }
    }

    private void initializeDtmfSender(int i) {
        if (!this.__dtmfSenders.containsKey(Integer.valueOf(i))) {
            Sender sender = new Sender(i);
            sender.removeOnTone(new IActionDelegate1<Tone>() {
                public String getId() {
                    return "fm.liveswitch.AudioStream.dtmfSender_OnTone";
                }

                public void invoke(Tone tone) {
                    AudioStream.this.dtmfSender_OnTone(tone);
                }
            });
            sender.removeOnToneChange(new IActionDelegate1<Tone>() {
                public String getId() {
                    return "fm.liveswitch.AudioStream.dtmfSender_OnToneChange";
                }

                public void invoke(Tone tone) {
                    AudioStream.this.dtmfSender_OnToneChange(tone);
                }
            });
            sender.addOnTone(new IActionDelegate1<Tone>() {
                public String getId() {
                    return "fm.liveswitch.AudioStream.dtmfSender_OnTone";
                }

                public void invoke(Tone tone) {
                    AudioStream.this.dtmfSender_OnTone(tone);
                }
            });
            sender.addOnToneChange(new IActionDelegate1<Tone>() {
                public String getId() {
                    return "fm.liveswitch.AudioStream.dtmfSender_OnToneChange";
                }

                public void invoke(Tone tone) {
                    AudioStream.this.dtmfSender_OnToneChange(tone);
                }
            });
            super.addInput(sender);
            HashMapExtensions.set(HashMapExtensions.getItem(this.__dtmfSenders), Integer.valueOf(i), sender);
        }
    }

    /* access modifiers changed from: protected */
    public IAudioInput[] inputArrayFromList(ArrayList<IAudioInput> arrayList) {
        return (IAudioInput[]) arrayList.toArray(new IAudioInput[0]);
    }

    public boolean insertDtmfTone(Tone tone) {
        return insertDtmfTones(new Tone[]{tone});
    }

    public boolean insertDtmfTones(Tone[] toneArr) {
        Sender currentDtmfSender = getDiscardOutboundDtmfTones() ? null : getCurrentDtmfSender();
        if (currentDtmfSender == null) {
            raiseOnDiscardOutboundDtmfTones(toneArr);
            return false;
        }
        currentDtmfSender.insertTones(toneArr);
        return true;
    }

    /* access modifiers changed from: protected */
    public IAudioOutput[] outputArrayFromList(ArrayList<IAudioOutput> arrayList) {
        return (IAudioOutput[]) arrayList.toArray(new IAudioOutput[0]);
    }

    private void raiseOnDiscardOutboundDtmfTones(Tone[] toneArr) {
        IAction1<Tone[]> iAction1 = this._onDiscardOutboundDtmfTones;
        if (iAction1 != null) {
            iAction1.invoke(toneArr);
        }
    }

    public void removeOnDiscardOutboundDtmfTones(IAction1<Tone[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onDiscardOutboundDtmfTones, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onDiscardOutboundDtmfTones.remove(iAction1);
        if (this.__onDiscardOutboundDtmfTones.size() == 0) {
            this._onDiscardOutboundDtmfTones = null;
        }
    }

    public void removeOnReceiveDtmfTone(IAction1<Tone> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceiveDtmfTone, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceiveDtmfTone.remove(iAction1);
        if (this.__onReceiveDtmfTone.size() == 0) {
            this._onReceiveDtmfTone = null;
        }
    }

    public void removeOnReceiveDtmfToneChange(IAction1<Tone> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceiveDtmfToneChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceiveDtmfToneChange.remove(iAction1);
        if (this.__onReceiveDtmfToneChange.size() == 0) {
            this._onReceiveDtmfToneChange = null;
        }
    }

    public void removeOnSendDtmfTone(IAction1<Tone> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onSendDtmfTone, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onSendDtmfTone.remove(iAction1);
        if (this.__onSendDtmfTone.size() == 0) {
            this._onSendDtmfTone = null;
        }
    }

    public void removeOnSendDtmfToneChange(IAction1<Tone> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onSendDtmfToneChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onSendDtmfToneChange.remove(iAction1);
        if (this.__onSendDtmfToneChange.size() == 0) {
            this._onSendDtmfToneChange = null;
        }
    }

    public void setDiscardOutboundDtmfTones(boolean z) {
        this._discardOutboundDtmfTones = z;
    }

    public void setDtmfMode(Mode mode) {
        this._dtmfMode = mode;
    }

    public void setG722Disabled(boolean z) {
        super.setCodecDisabled(AudioFormat.getG722Name(), z);
    }

    public void setGain(double d) {
        this.__gain = MathAssistant.max(d, 0.0d);
    }

    /* access modifiers changed from: protected */
    public void setInputSourceMuted(IAudioOutput iAudioOutput, boolean z) {
        AudioSource audioSource = (AudioSource) Global.tryCast(iAudioOutput, AudioSource.class);
        if (audioSource != null) {
            audioSource.setOutputMuted(z);
            return;
        }
        AudioPipe audioPipe = (AudioPipe) Global.tryCast(iAudioOutput, AudioPipe.class);
        if (audioPipe != null) {
            super.setInputSourceMuted((TIOutput[]) audioPipe.getInputs(), z);
        }
    }

    public void setOpusDisabled(boolean z) {
        super.setCodecDisabled(AudioFormat.getOpusName(), z);
    }

    /* access modifiers changed from: protected */
    public void setOutputSinkMuted(IAudioInput iAudioInput, boolean z) {
        AudioSink audioSink = (AudioSink) Global.tryCast(iAudioInput, AudioSink.class);
        if (audioSink != null) {
            audioSink.setInputMuted(z);
            return;
        }
        AudioPipe audioPipe = (AudioPipe) Global.tryCast(iAudioInput, AudioPipe.class);
        if (audioPipe != null) {
            super.setOutputSinkMuted((TIInput[]) audioPipe.getOutputs(), z);
        }
    }

    public void setPcmaDisabled(boolean z) {
        super.setCodecDisabled(AudioFormat.getPcmaName(), z);
    }

    public void setPcmuDisabled(boolean z) {
        super.setCodecDisabled(AudioFormat.getPcmuName(), z);
    }

    public void setVolume(double d) {
        this.__volume = MathAssistant.min(MathAssistant.max(d, 0.0d), 1.0d);
    }
}
