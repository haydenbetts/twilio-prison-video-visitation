package fm.liveswitch;

import fm.liveswitch.g722.Decoder;
import fm.liveswitch.g722.Depacketizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class RtcRemoteMedia<TView> extends RemoteMedia implements IViewSinkableMedia<TView, ViewSink<TView>>, IViewableMedia<TView> {
    private static ILog __log = Log.getLogger(RtcRemoteMedia.class);
    private ArrayList<AudioSink> __audioRecorders;
    private AudioConfig __g722Config;
    private Object __initializeLock;
    private boolean __initialized;
    /* access modifiers changed from: private */
    public List<IAction1<AudioPipe>> __onActiveAudioConverterChange;
    /* access modifiers changed from: private */
    public List<IAction1<AudioDecoder>> __onActiveAudioDecoderChange;
    /* access modifiers changed from: private */
    public List<IAction1<AudioPipe>> __onActiveAudioDepacketizerChange;
    /* access modifiers changed from: private */
    public List<IAction1<AudioSink>> __onActiveAudioSinkChange;
    /* access modifiers changed from: private */
    public List<IAction1<AudioSynchronizer>> __onActiveAudioSynchronizerChange;
    /* access modifiers changed from: private */
    public List<IAction1<VideoPipe>> __onActiveVideoConverterChange;
    /* access modifiers changed from: private */
    public List<IAction1<VideoDecoder>> __onActiveVideoDecoderChange;
    /* access modifiers changed from: private */
    public List<IAction1<VideoPipe>> __onActiveVideoDepacketizerChange;
    /* access modifiers changed from: private */
    public List<IAction1<VideoSynchronizer>> __onActiveVideoSynchronizerChange;
    private AudioConfig __opusConfig;
    private AudioConfig __pcmaConfig;
    private AudioConfig __pcmuConfig;
    private ArrayList<VideoSink> __videoRecorders;
    private AudioPipe _activeAudioConverter;
    private AudioDecoder _activeAudioDecoder;
    private AudioPipe _activeAudioDepacketizer;
    private AudioSink _activeAudioSink;
    private AudioSynchronizer _activeAudioSynchronizer;
    private VideoPipe _activeVideoConverter;
    private VideoDecoder _activeVideoDecoder;
    private VideoPipe _activeVideoDepacketizer;
    private VideoSynchronizer _activeVideoSynchronizer;
    private AecContext _aecContext;
    private boolean _audioDisabled;
    private Object _audioRecordingLock;
    private AudioPipe _g722Converter;
    private AudioDecoder _g722Decoder;
    private AudioPipe _g722Depacketizer;
    private boolean _g722Disabled;
    private AudioSink _g722Sink;
    private AudioSynchronizer _g722Synchronizer;
    private VideoPipe[] _h264Converters;
    private VideoDecoder[] _h264Decoders;
    private VideoPipe[][] _h264DepacketizersArray;
    private boolean _h264Disabled;
    private VideoSynchronizer[] _h264Synchronizers;
    /* access modifiers changed from: private */
    public IAction1<AudioPipe> _onActiveAudioConverterChange;
    /* access modifiers changed from: private */
    public IAction1<AudioDecoder> _onActiveAudioDecoderChange;
    /* access modifiers changed from: private */
    public IAction1<AudioPipe> _onActiveAudioDepacketizerChange;
    /* access modifiers changed from: private */
    public IAction1<AudioSink> _onActiveAudioSinkChange;
    /* access modifiers changed from: private */
    public IAction1<AudioSynchronizer> _onActiveAudioSynchronizerChange;
    /* access modifiers changed from: private */
    public IAction1<VideoPipe> _onActiveVideoConverterChange;
    /* access modifiers changed from: private */
    public IAction1<VideoDecoder> _onActiveVideoDecoderChange;
    /* access modifiers changed from: private */
    public IAction1<VideoPipe> _onActiveVideoDepacketizerChange;
    /* access modifiers changed from: private */
    public IAction1<VideoSynchronizer> _onActiveVideoSynchronizerChange;
    private AudioPipe _opusConverter;
    private AudioDecoder _opusDecoder;
    private AudioPipe _opusDepacketizer;
    private boolean _opusDisabled;
    private AudioSink _opusSink;
    private AudioSynchronizer _opusSynchronizer;
    private AudioPipe _pcmaConverter;
    private AudioDecoder _pcmaDecoder;
    private AudioPipe _pcmaDepacketizer;
    private boolean _pcmaDisabled;
    private AudioSink _pcmaSink;
    private AudioSynchronizer _pcmaSynchronizer;
    private AudioPipe _pcmuConverter;
    private AudioDecoder _pcmuDecoder;
    private AudioPipe _pcmuDepacketizer;
    private boolean _pcmuDisabled;
    private AudioSink _pcmuSink;
    private AudioSynchronizer _pcmuSynchronizer;
    private boolean _videoDisabled;
    private Object _videoRecordingLock;
    private ViewSink<TView> _viewSink;
    private VideoPipe _vp8Converter;
    private VideoDecoder _vp8Decoder;
    private VideoPipe _vp8Depacketizer;
    private boolean _vp8Disabled;
    private VideoSynchronizer _vp8Synchronizer;
    private VideoPipe _vp9Converter;
    private VideoDecoder _vp9Decoder;
    private VideoPipe _vp9Depacketizer;
    private boolean _vp9Disabled;
    private VideoSynchronizer _vp9Synchronizer;

    /* access modifiers changed from: protected */
    public abstract AudioSink createAudioRecorder(AudioFormat audioFormat);

    /* access modifiers changed from: protected */
    public abstract AudioSink createAudioSink(AudioConfig audioConfig);

    /* access modifiers changed from: protected */
    public abstract VideoDecoder createH264Decoder();

    /* access modifiers changed from: protected */
    public VideoDecoder[] createH264Decoders() {
        return null;
    }

    /* access modifiers changed from: protected */
    public VideoPipe[] createH264Depacketizers() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract VideoPipe createImageConverter(VideoFormat videoFormat);

    /* access modifiers changed from: protected */
    public abstract AudioDecoder createOpusDecoder(AudioConfig audioConfig);

    /* access modifiers changed from: protected */
    public abstract VideoSink createVideoRecorder(VideoFormat videoFormat);

    /* access modifiers changed from: protected */
    public abstract ViewSink<TView> createViewSink();

    /* access modifiers changed from: protected */
    public abstract VideoDecoder createVp8Decoder();

    /* access modifiers changed from: protected */
    public abstract VideoDecoder createVp9Decoder();

    public void addOnActiveAudioConverterChange(IAction1<AudioPipe> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveAudioConverterChange == null) {
                this._onActiveAudioConverterChange = new IAction1<AudioPipe>() {
                    public void invoke(AudioPipe audioPipe) {
                        Iterator it = new ArrayList(RtcRemoteMedia.this.__onActiveAudioConverterChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(audioPipe);
                        }
                    }
                };
            }
            this.__onActiveAudioConverterChange.add(iAction1);
        }
    }

    public void addOnActiveAudioDecoderChange(IAction1<AudioDecoder> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveAudioDecoderChange == null) {
                this._onActiveAudioDecoderChange = new IAction1<AudioDecoder>() {
                    public void invoke(AudioDecoder audioDecoder) {
                        Iterator it = new ArrayList(RtcRemoteMedia.this.__onActiveAudioDecoderChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(audioDecoder);
                        }
                    }
                };
            }
            this.__onActiveAudioDecoderChange.add(iAction1);
        }
    }

    public void addOnActiveAudioDepacketizerChange(IAction1<AudioPipe> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveAudioDepacketizerChange == null) {
                this._onActiveAudioDepacketizerChange = new IAction1<AudioPipe>() {
                    public void invoke(AudioPipe audioPipe) {
                        Iterator it = new ArrayList(RtcRemoteMedia.this.__onActiveAudioDepacketizerChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(audioPipe);
                        }
                    }
                };
            }
            this.__onActiveAudioDepacketizerChange.add(iAction1);
        }
    }

    public void addOnActiveAudioSinkChange(IAction1<AudioSink> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveAudioSinkChange == null) {
                this._onActiveAudioSinkChange = new IAction1<AudioSink>() {
                    public void invoke(AudioSink audioSink) {
                        Iterator it = new ArrayList(RtcRemoteMedia.this.__onActiveAudioSinkChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(audioSink);
                        }
                    }
                };
            }
            this.__onActiveAudioSinkChange.add(iAction1);
        }
    }

    public void addOnActiveAudioSynchronizerChange(IAction1<AudioSynchronizer> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveAudioSynchronizerChange == null) {
                this._onActiveAudioSynchronizerChange = new IAction1<AudioSynchronizer>() {
                    public void invoke(AudioSynchronizer audioSynchronizer) {
                        Iterator it = new ArrayList(RtcRemoteMedia.this.__onActiveAudioSynchronizerChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(audioSynchronizer);
                        }
                    }
                };
            }
            this.__onActiveAudioSynchronizerChange.add(iAction1);
        }
    }

    public void addOnActiveVideoConverterChange(IAction1<VideoPipe> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveVideoConverterChange == null) {
                this._onActiveVideoConverterChange = new IAction1<VideoPipe>() {
                    public void invoke(VideoPipe videoPipe) {
                        Iterator it = new ArrayList(RtcRemoteMedia.this.__onActiveVideoConverterChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(videoPipe);
                        }
                    }
                };
            }
            this.__onActiveVideoConverterChange.add(iAction1);
        }
    }

    public void addOnActiveVideoDecoderChange(IAction1<VideoDecoder> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveVideoDecoderChange == null) {
                this._onActiveVideoDecoderChange = new IAction1<VideoDecoder>() {
                    public void invoke(VideoDecoder videoDecoder) {
                        Iterator it = new ArrayList(RtcRemoteMedia.this.__onActiveVideoDecoderChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(videoDecoder);
                        }
                    }
                };
            }
            this.__onActiveVideoDecoderChange.add(iAction1);
        }
    }

    public void addOnActiveVideoDepacketizerChange(IAction1<VideoPipe> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveVideoDepacketizerChange == null) {
                this._onActiveVideoDepacketizerChange = new IAction1<VideoPipe>() {
                    public void invoke(VideoPipe videoPipe) {
                        Iterator it = new ArrayList(RtcRemoteMedia.this.__onActiveVideoDepacketizerChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(videoPipe);
                        }
                    }
                };
            }
            this.__onActiveVideoDepacketizerChange.add(iAction1);
        }
    }

    public void addOnActiveVideoSynchronizerChange(IAction1<VideoSynchronizer> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveVideoSynchronizerChange == null) {
                this._onActiveVideoSynchronizerChange = new IAction1<VideoSynchronizer>() {
                    public void invoke(VideoSynchronizer videoSynchronizer) {
                        Iterator it = new ArrayList(RtcRemoteMedia.this.__onActiveVideoSynchronizerChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(videoSynchronizer);
                        }
                    }
                };
            }
            this.__onActiveVideoSynchronizerChange.add(iAction1);
        }
    }

    /* access modifiers changed from: protected */
    public AudioSynchronizer createAudioSynchronizer(AudioFormat audioFormat) {
        return new AudioSynchronizer(audioFormat);
    }

    /* JADX WARNING: type inference failed for: r6v6, types: [fm.liveswitch.MediaTrack] */
    /* JADX WARNING: type inference failed for: r6v9, types: [fm.liveswitch.MediaTrack] */
    /* JADX WARNING: type inference failed for: r6v11, types: [fm.liveswitch.MediaTrack] */
    /* JADX WARNING: type inference failed for: r6v13, types: [fm.liveswitch.MediaTrack] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.AudioTrack createAudioTrack(final fm.liveswitch.AudioPipe r5, final fm.liveswitch.AudioDecoder r6, final fm.liveswitch.AudioPipe r7, final fm.liveswitch.AudioSynchronizer r8, final fm.liveswitch.AudioSink r9) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x00a7
            fm.liveswitch.AudioTrack r0 = new fm.liveswitch.AudioTrack
            r0.<init>((fm.liveswitch.IAudioElement) r5)
            fm.liveswitch.RtcRemoteMedia$10 r1 = new fm.liveswitch.RtcRemoteMedia$10
            r1.<init>(r5)
            r5.addOnProcessFrame(r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            fm.liveswitch.MediaFormat r5 = r5.getOutputFormat()
            fm.liveswitch.AudioFormat r5 = (fm.liveswitch.AudioFormat) r5
            fm.liveswitch.AudioSink r5 = r4.getAudioRecorder(r5)
            if (r5 == 0) goto L_0x0028
            fm.liveswitch.AudioTrack r2 = new fm.liveswitch.AudioTrack
            r2.<init>((fm.liveswitch.IAudioElement) r5)
            r1.add(r2)
        L_0x0028:
            r5 = 0
            if (r6 == 0) goto L_0x0098
            fm.liveswitch.AudioTrack r2 = new fm.liveswitch.AudioTrack
            r2.<init>((fm.liveswitch.IAudioElement) r6)
            fm.liveswitch.RtcRemoteMedia$11 r3 = new fm.liveswitch.RtcRemoteMedia$11
            r3.<init>(r6)
            r6.addOnProcessFrame(r3)
            boolean r6 = r4.getAecDisabled()
            if (r6 != 0) goto L_0x004d
            fm.liveswitch.MediaTrack r6 = r2.next(r7)
            r2 = r6
            fm.liveswitch.AudioTrack r2 = (fm.liveswitch.AudioTrack) r2
            fm.liveswitch.RtcRemoteMedia$12 r6 = new fm.liveswitch.RtcRemoteMedia$12
            r6.<init>(r7)
            r7.addOnProcessFrame(r6)
        L_0x004d:
            if (r8 == 0) goto L_0x005e
            fm.liveswitch.MediaTrack r6 = r2.next(r8)
            r2 = r6
            fm.liveswitch.AudioTrack r2 = (fm.liveswitch.AudioTrack) r2
            fm.liveswitch.RtcRemoteMedia$13 r6 = new fm.liveswitch.RtcRemoteMedia$13
            r6.<init>(r8)
            r8.addOnProcessFrame(r6)
        L_0x005e:
            boolean r6 = r4.getAecDisabled()
            if (r6 == 0) goto L_0x0076
            if (r9 == 0) goto L_0x0095
            fm.liveswitch.MediaTrack r6 = r2.next(r9)
            r2 = r6
            fm.liveswitch.AudioTrack r2 = (fm.liveswitch.AudioTrack) r2
            fm.liveswitch.RtcRemoteMedia$14 r6 = new fm.liveswitch.RtcRemoteMedia$14
            r6.<init>(r9)
            r9.addOnProcessFrame(r6)
            goto L_0x0095
        L_0x0076:
            r6 = 1
            fm.liveswitch.AudioTrack[] r6 = new fm.liveswitch.AudioTrack[r6]
            fm.liveswitch.AecContext r7 = r4.getAecContext()
            fm.liveswitch.AudioTrack r7 = r7.getOutputTrack()
            r6[r5] = r7
            fm.liveswitch.MediaTrack r6 = r2.next((TTrack[]) r6)
            r2 = r6
            fm.liveswitch.AudioTrack r2 = (fm.liveswitch.AudioTrack) r2
            fm.liveswitch.AecContext r6 = r4.getAecContext()
            fm.liveswitch.AudioSink r6 = r6.getOutputMixerSink()
            r4.setActiveAudioSink(r6)
        L_0x0095:
            r1.add(r2)
        L_0x0098:
            fm.liveswitch.AudioTrack[] r5 = new fm.liveswitch.AudioTrack[r5]
            java.lang.Object[] r5 = r1.toArray(r5)
            fm.liveswitch.MediaTrack[] r5 = (fm.liveswitch.MediaTrack[]) r5
            fm.liveswitch.MediaTrack r5 = r0.next((TTrack[]) r5)
            fm.liveswitch.AudioTrack r5 = (fm.liveswitch.AudioTrack) r5
            return r5
        L_0x00a7:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.Exception r6 = new java.lang.Exception
            java.lang.String r7 = "Can't create remote audio track. No depacketizer."
            r6.<init>(r7)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtcRemoteMedia.createAudioTrack(fm.liveswitch.AudioPipe, fm.liveswitch.AudioDecoder, fm.liveswitch.AudioPipe, fm.liveswitch.AudioSynchronizer, fm.liveswitch.AudioSink):fm.liveswitch.AudioTrack");
    }

    /* access modifiers changed from: protected */
    public AudioDecoder createG722Decoder(AudioConfig audioConfig) {
        return new Decoder(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioPipe createG722Depacketizer(AudioConfig audioConfig) {
        return new Depacketizer(audioConfig);
    }

    /* access modifiers changed from: protected */
    public VideoPipe createH264Depacketizer() {
        return new fm.liveswitch.h264.Depacketizer();
    }

    /* access modifiers changed from: protected */
    public AudioPipe createOpusDepacketizer(AudioConfig audioConfig) {
        return new fm.liveswitch.opus.Depacketizer(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioDecoder createPcmaDecoder(AudioConfig audioConfig) {
        return new fm.liveswitch.pcma.Decoder(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioPipe createPcmaDepacketizer(AudioConfig audioConfig) {
        return new fm.liveswitch.pcma.Depacketizer(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioDecoder createPcmuDecoder(AudioConfig audioConfig) {
        return new fm.liveswitch.pcmu.Decoder(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioPipe createPcmuDepacketizer(AudioConfig audioConfig) {
        return new fm.liveswitch.pcmu.Depacketizer(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioPipe createSoundConverter(AudioConfig audioConfig) {
        return new SoundConverter(audioConfig);
    }

    private VideoTrack createVideoBranch(final VideoPipe videoPipe) {
        videoPipe.addOnProcessFrame(new IAction1<VideoFrame>() {
            public void invoke(VideoFrame videoFrame) {
                if (!Global.equals(RtcRemoteMedia.this.getActiveVideoDepacketizer(), videoPipe)) {
                    RtcRemoteMedia.this.setActiveVideoDepacketizer(videoPipe);
                    IAction1 access$2000 = RtcRemoteMedia.this._onActiveVideoDepacketizerChange;
                    if (access$2000 != null) {
                        access$2000.invoke(videoPipe);
                    }
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        VideoSink videoRecorder = getVideoRecorder((VideoFormat) videoPipe.getOutputFormat());
        if (videoRecorder != null) {
            arrayList.add(new VideoTrack((IVideoElement) videoRecorder));
        }
        arrayList.add(new VideoTrack((IVideoElement) new IdentityVideoPipe((VideoFormat) videoPipe.getOutputFormat())));
        return (VideoTrack) new VideoTrack((IVideoElement) videoPipe).next((TTrack[]) (MediaTrack[]) arrayList.toArray(new VideoTrack[0]));
    }

    /* access modifiers changed from: protected */
    public VideoSynchronizer createVideoSynchronizer(VideoFormat videoFormat) {
        return new VideoSynchronizer(videoFormat);
    }

    /* JADX WARNING: type inference failed for: r8v3, types: [fm.liveswitch.MediaTrack] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.VideoTrack createVideoTrack(fm.liveswitch.VideoPipe[] r6, final fm.liveswitch.VideoDecoder r7, final fm.liveswitch.VideoPipe r8, final fm.liveswitch.VideoSynchronizer r9) {
        /*
            r5 = this;
            if (r6 == 0) goto L_0x0079
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r6)
            if (r0 == 0) goto L_0x0079
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = r6.length
            r2 = 0
            r3 = 0
        L_0x0010:
            if (r3 >= r1) goto L_0x001e
            r4 = r6[r3]
            fm.liveswitch.VideoTrack r4 = r5.createVideoBranch(r4)
            r0.add(r4)
            int r3 = r3 + 1
            goto L_0x0010
        L_0x001e:
            fm.liveswitch.VideoTrack r6 = new fm.liveswitch.VideoTrack
            fm.liveswitch.VideoTrack[] r1 = new fm.liveswitch.VideoTrack[r2]
            java.lang.Object[] r0 = r0.toArray(r1)
            fm.liveswitch.VideoTrack[] r0 = (fm.liveswitch.VideoTrack[]) r0
            r6.<init>((fm.liveswitch.VideoTrack[]) r0)
            if (r7 != 0) goto L_0x002e
            return r6
        L_0x002e:
            fm.liveswitch.VideoTrack r0 = new fm.liveswitch.VideoTrack
            r0.<init>((fm.liveswitch.IVideoElement) r7)
            fm.liveswitch.RtcRemoteMedia$16 r1 = new fm.liveswitch.RtcRemoteMedia$16
            r1.<init>(r7)
            r7.addOnProcessFrame(r1)
            if (r8 == 0) goto L_0x004b
            fm.liveswitch.MediaTrack r0 = r0.next(r8)
            fm.liveswitch.VideoTrack r0 = (fm.liveswitch.VideoTrack) r0
            fm.liveswitch.RtcRemoteMedia$17 r1 = new fm.liveswitch.RtcRemoteMedia$17
            r1.<init>(r8)
            r8.addOnProcessFrame(r1)
        L_0x004b:
            if (r9 == 0) goto L_0x005c
            fm.liveswitch.MediaTrack r8 = r0.next(r9)
            r0 = r8
            fm.liveswitch.VideoTrack r0 = (fm.liveswitch.VideoTrack) r0
            fm.liveswitch.RtcRemoteMedia$18 r8 = new fm.liveswitch.RtcRemoteMedia$18
            r8.<init>(r9)
            r9.addOnProcessFrame(r8)
        L_0x005c:
            fm.liveswitch.IdentityVideoPipe r8 = new fm.liveswitch.IdentityVideoPipe
            fm.liveswitch.MediaFormat r7 = r7.getInputFormat()
            fm.liveswitch.VideoFormat r7 = (fm.liveswitch.VideoFormat) r7
            r8.<init>(r7)
            fm.liveswitch.MediaTrack r6 = r6.next(r8)
            fm.liveswitch.VideoTrack r6 = (fm.liveswitch.VideoTrack) r6
            r7 = 1
            fm.liveswitch.VideoTrack[] r7 = new fm.liveswitch.VideoTrack[r7]
            r7[r2] = r0
            fm.liveswitch.MediaTrack r6 = r6.next((TTrack[]) r7)
            fm.liveswitch.VideoTrack r6 = (fm.liveswitch.VideoTrack) r6
            return r6
        L_0x0079:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.Exception r7 = new java.lang.Exception
            java.lang.String r8 = "Can't create remote video track. No depacketizer."
            r7.<init>(r8)
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtcRemoteMedia.createVideoTrack(fm.liveswitch.VideoPipe[], fm.liveswitch.VideoDecoder, fm.liveswitch.VideoPipe, fm.liveswitch.VideoSynchronizer):fm.liveswitch.VideoTrack");
    }

    /* access modifiers changed from: protected */
    public VideoPipe createVp8Depacketizer() {
        return new fm.liveswitch.vp8.Depacketizer();
    }

    /* access modifiers changed from: protected */
    public VideoPipe createVp9Depacketizer() {
        return new fm.liveswitch.vp9.Depacketizer();
    }

    public void destroy() {
        if (!getAecDisabled() && super.getAudioTrack() != null) {
            for (IAudioOutput removeOutput : (IAudioOutput[]) ((AudioTrack) super.getAudioTrack()).getOutputs()) {
                removeOutput.removeOutput(getAecContext().getOutputMixer());
            }
        }
        Iterator<AudioSink> it = this.__audioRecorders.iterator();
        while (it.hasNext()) {
            AudioSink next = it.next();
            if (!next.getPersistent()) {
                next.destroy();
            }
        }
        Iterator<VideoSink> it2 = this.__videoRecorders.iterator();
        while (it2.hasNext()) {
            VideoSink next2 = it2.next();
            if (!next2.getPersistent()) {
                next2.destroy();
            }
        }
        super.destroy();
    }

    public AudioPipe getActiveAudioConverter() {
        return this._activeAudioConverter;
    }

    public AudioDecoder getActiveAudioDecoder() {
        return this._activeAudioDecoder;
    }

    public AudioPipe getActiveAudioDepacketizer() {
        return this._activeAudioDepacketizer;
    }

    public AudioSink getActiveAudioSink() {
        return this._activeAudioSink;
    }

    public AudioSynchronizer getActiveAudioSynchronizer() {
        return this._activeAudioSynchronizer;
    }

    public VideoPipe getActiveVideoConverter() {
        return this._activeVideoConverter;
    }

    public VideoDecoder getActiveVideoDecoder() {
        return this._activeVideoDecoder;
    }

    public VideoPipe getActiveVideoDepacketizer() {
        return this._activeVideoDepacketizer;
    }

    public VideoSynchronizer getActiveVideoSynchronizer() {
        return this._activeVideoSynchronizer;
    }

    public AecContext getAecContext() {
        return this._aecContext;
    }

    public boolean getAecDisabled() {
        return getAecContext() == null || getAudioDisabled();
    }

    public boolean getAudioDisabled() {
        return this._audioDisabled;
    }

    public IAudioInput[] getAudioInputs() {
        if (super.getAudioTrack() == null) {
            return null;
        }
        return (IAudioInput[]) ((AudioTrack) super.getAudioTrack()).getInputs();
    }

    private AudioSink getAudioRecorder(AudioFormat audioFormat) {
        AudioSink createAudioRecorder = createAudioRecorder(audioFormat);
        if (createAudioRecorder != null) {
            createAudioRecorder.setDeactivated(true);
            this.__audioRecorders.add(createAudioRecorder);
        }
        return createAudioRecorder;
    }

    public AudioPipe getG722Converter() {
        return this._g722Converter;
    }

    public AudioDecoder getG722Decoder() {
        return this._g722Decoder;
    }

    public AudioPipe getG722Depacketizer() {
        return this._g722Depacketizer;
    }

    public boolean getG722Disabled() {
        return this._g722Disabled;
    }

    public AudioSink getG722Sink() {
        return this._g722Sink;
    }

    public AudioSynchronizer getG722Synchronizer() {
        return this._g722Synchronizer;
    }

    public VideoPipe getH264Converter() {
        return (VideoPipe) Utility.firstOrDefault((T[]) getH264Converters());
    }

    public VideoPipe[] getH264Converters() {
        return this._h264Converters;
    }

    public VideoDecoder getH264Decoder() {
        return (VideoDecoder) Utility.firstOrDefault((T[]) getH264Decoders());
    }

    public VideoDecoder[] getH264Decoders() {
        return this._h264Decoders;
    }

    public VideoPipe getH264Depacketizer() {
        return (VideoPipe) Utility.firstOrDefault((T[]) getH264Depacketizers());
    }

    public VideoPipe[] getH264Depacketizers() {
        return (VideoPipe[]) Utility.firstOrDefault((T[]) getH264DepacketizersArray());
    }

    public VideoPipe[][] getH264DepacketizersArray() {
        return this._h264DepacketizersArray;
    }

    public boolean getH264Disabled() {
        return this._h264Disabled;
    }

    public VideoSynchronizer getH264Synchronizer() {
        return (VideoSynchronizer) Utility.firstOrDefault((T[]) getH264Synchronizers());
    }

    public VideoSynchronizer[] getH264Synchronizers() {
        return this._h264Synchronizers;
    }

    public AudioPipe getOpusConverter() {
        return this._opusConverter;
    }

    public AudioDecoder getOpusDecoder() {
        return this._opusDecoder;
    }

    public AudioPipe getOpusDepacketizer() {
        return this._opusDepacketizer;
    }

    public boolean getOpusDisabled() {
        return this._opusDisabled;
    }

    public AudioSink getOpusSink() {
        return this._opusSink;
    }

    public AudioSynchronizer getOpusSynchronizer() {
        return this._opusSynchronizer;
    }

    public AudioPipe getPcmaConverter() {
        return this._pcmaConverter;
    }

    public AudioDecoder getPcmaDecoder() {
        return this._pcmaDecoder;
    }

    public AudioPipe getPcmaDepacketizer() {
        return this._pcmaDepacketizer;
    }

    public boolean getPcmaDisabled() {
        return this._pcmaDisabled;
    }

    public AudioSink getPcmaSink() {
        return this._pcmaSink;
    }

    public AudioSynchronizer getPcmaSynchronizer() {
        return this._pcmaSynchronizer;
    }

    public AudioPipe getPcmuConverter() {
        return this._pcmuConverter;
    }

    public AudioDecoder getPcmuDecoder() {
        return this._pcmuDecoder;
    }

    public AudioPipe getPcmuDepacketizer() {
        return this._pcmuDepacketizer;
    }

    public boolean getPcmuDisabled() {
        return this._pcmuDisabled;
    }

    public AudioSink getPcmuSink() {
        return this._pcmuSink;
    }

    public AudioSynchronizer getPcmuSynchronizer() {
        return this._pcmuSynchronizer;
    }

    public boolean getVideoDisabled() {
        return this._videoDisabled;
    }

    public IVideoInput[] getVideoInputs() {
        if (super.getVideoTrack() == null) {
            return null;
        }
        return (IVideoInput[]) ((VideoTrack) super.getVideoTrack()).getInputs();
    }

    private VideoSink getVideoRecorder(VideoFormat videoFormat) {
        VideoSink createVideoRecorder = createVideoRecorder(videoFormat);
        if (createVideoRecorder != null) {
            createVideoRecorder.setDeactivated(true);
            this.__videoRecorders.add(createVideoRecorder);
        }
        return createVideoRecorder;
    }

    public TView getView() {
        if (getViewSink() == null) {
            return null;
        }
        return getViewSink().getView();
    }

    public ViewSink<TView> getViewSink() {
        return this._viewSink;
    }

    public VideoPipe getVp8Converter() {
        return this._vp8Converter;
    }

    public VideoDecoder getVp8Decoder() {
        return this._vp8Decoder;
    }

    public VideoPipe getVp8Depacketizer() {
        return this._vp8Depacketizer;
    }

    public boolean getVp8Disabled() {
        return this._vp8Disabled;
    }

    public VideoSynchronizer getVp8Synchronizer() {
        return this._vp8Synchronizer;
    }

    public VideoPipe getVp9Converter() {
        return this._vp9Converter;
    }

    public VideoDecoder getVp9Decoder() {
        return this._vp9Decoder;
    }

    public VideoPipe getVp9Depacketizer() {
        return this._vp9Depacketizer;
    }

    public boolean getVp9Disabled() {
        return this._vp9Disabled;
    }

    public VideoSynchronizer getVp9Synchronizer() {
        return this._vp9Synchronizer;
    }

    public boolean initialize() {
        return initialize((RtcAudioTrackConfig) null, (RtcVideoTrackConfig) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        setOpusDisabled(r12.getOpusDisabled());
        setG722Disabled(r12.getG722Disabled());
        setPcmuDisabled(r12.getPcmuDisabled());
        setPcmaDisabled(r12.getPcmaDisabled());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
        if (r13 == null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        setVp8Disabled(r13.getVp8Disabled());
        setH264Disabled(r13.getH264Disabled());
        setVp9Disabled(r13.getVp9Disabled());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        if (getAudioDisabled() != false) goto L_0x02fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0049, code lost:
        r12 = new fm.liveswitch.AudioTrack();
        r13 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0057, code lost:
        if (getOpusDisabled() != false) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        setOpusDecoder(createOpusDecoder(r11.__opusConfig));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x05a5, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x05a6, code lost:
        __log.error("Error occured while initializing remote media.", r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x05b2, code lost:
        throw new java.lang.RuntimeException(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0063, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        __log.error("Could not create remote Opus decoder.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (r12 == null) goto L_0x002c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0320 A[SYNTHETIC, Splitter:B:103:0x0320] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0336 A[Catch:{ Exception -> 0x05a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x03b2 A[SYNTHETIC, Splitter:B:125:0x03b2] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x03d7 A[Catch:{ Exception -> 0x05a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x04e9 A[SYNTHETIC, Splitter:B:171:0x04e9] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x04ff A[Catch:{ Exception -> 0x05a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x057b A[Catch:{ Exception -> 0x05a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x058a A[Catch:{ Exception -> 0x05a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0597 A[Catch:{ Exception -> 0x05a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0113 A[Catch:{ Exception -> 0x05a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x019d A[SYNTHETIC, Splitter:B:54:0x019d] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01b5 A[Catch:{ Exception -> 0x05a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x023f A[SYNTHETIC, Splitter:B:72:0x023f] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0257 A[Catch:{ Exception -> 0x05a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x02e1 A[Catch:{ Exception -> 0x05a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x02f0 A[Catch:{ Exception -> 0x05a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0300 A[Catch:{ Exception -> 0x05a5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean initialize(fm.liveswitch.RtcAudioTrackConfig r12, fm.liveswitch.RtcVideoTrackConfig r13) {
        /*
            r11 = this;
            java.lang.Object r0 = r11.__initializeLock
            monitor-enter(r0)
            boolean r1 = r11.__initialized     // Catch:{ all -> 0x05b3 }
            r2 = 0
            if (r1 == 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x05b3 }
            return r2
        L_0x000a:
            r1 = 1
            r11.__initialized = r1     // Catch:{ all -> 0x05b3 }
            monitor-exit(r0)     // Catch:{ all -> 0x05b3 }
            if (r12 == 0) goto L_0x002c
            boolean r0 = r12.getOpusDisabled()
            r11.setOpusDisabled(r0)
            boolean r0 = r12.getG722Disabled()
            r11.setG722Disabled(r0)
            boolean r0 = r12.getPcmuDisabled()
            r11.setPcmuDisabled(r0)
            boolean r12 = r12.getPcmaDisabled()
            r11.setPcmaDisabled(r12)
        L_0x002c:
            if (r13 == 0) goto L_0x0043
            boolean r12 = r13.getVp8Disabled()
            r11.setVp8Disabled(r12)
            boolean r12 = r13.getH264Disabled()
            r11.setH264Disabled(r12)
            boolean r12 = r13.getVp9Disabled()
            r11.setVp9Disabled(r12)
        L_0x0043:
            boolean r12 = r11.getAudioDisabled()     // Catch:{ Exception -> 0x05a5 }
            if (r12 != 0) goto L_0x02fa
            fm.liveswitch.AudioTrack r12 = new fm.liveswitch.AudioTrack     // Catch:{ Exception -> 0x05a5 }
            r12.<init>()     // Catch:{ Exception -> 0x05a5 }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ Exception -> 0x05a5 }
            r13.<init>()     // Catch:{ Exception -> 0x05a5 }
            boolean r0 = r11.getOpusDisabled()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x00f5
            fm.liveswitch.AudioConfig r0 = r11.__opusConfig     // Catch:{ Exception -> 0x0063 }
            fm.liveswitch.AudioDecoder r0 = r11.createOpusDecoder(r0)     // Catch:{ Exception -> 0x0063 }
            r11.setOpusDecoder(r0)     // Catch:{ Exception -> 0x0063 }
            goto L_0x006b
        L_0x0063:
            r0 = move-exception
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r4 = "Could not create remote Opus decoder."
            r3.error((java.lang.String) r4, (java.lang.Exception) r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x006b:
            fm.liveswitch.AudioDecoder r0 = r11.getOpusDecoder()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x00f5
            fm.liveswitch.AudioDecoder r0 = r11.getOpusDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getInputConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r0 = r11.createOpusDepacketizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setOpusDepacketizer(r0)     // Catch:{ Exception -> 0x05a5 }
            boolean r0 = r11.getAecDisabled()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x0096
            fm.liveswitch.AudioDecoder r0 = r11.getOpusDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getOutputConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r0 = r11.createAudioSink(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setOpusSink(r0)     // Catch:{ Exception -> 0x05a5 }
            goto L_0x00b0
        L_0x0096:
            fm.liveswitch.AecContext r0 = r11.getAecContext()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r0 = r11.createSoundConverter(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setOpusConverter(r0)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AecContext r0 = r11.getAecContext()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r0 = r0.getOutputMixerSink()     // Catch:{ Exception -> 0x05a5 }
            r11.setOpusSink(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x00b0:
            fm.liveswitch.AudioPipe r0 = r11.getOpusConverter()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x00c8
            fm.liveswitch.AudioDecoder r0 = r11.getOpusDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioFormat r0 = (fm.liveswitch.AudioFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r0 = r11.createAudioSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setOpusSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            goto L_0x00d9
        L_0x00c8:
            fm.liveswitch.AudioPipe r0 = r11.getOpusConverter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioFormat r0 = (fm.liveswitch.AudioFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r0 = r11.createAudioSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setOpusSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x00d9:
            fm.liveswitch.AudioPipe r4 = r11.getOpusDepacketizer()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioDecoder r5 = r11.getOpusDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r6 = r11.getOpusConverter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r7 = r11.getOpusSynchronizer()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r8 = r11.getOpusSink()     // Catch:{ Exception -> 0x05a5 }
            r3 = r11
            fm.liveswitch.AudioTrack r0 = r3.createAudioTrack(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x05a5 }
            r13.add(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x00f5:
            boolean r0 = r11.getG722Disabled()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x0197
            fm.liveswitch.AudioConfig r0 = r11.__g722Config     // Catch:{ Exception -> 0x0105 }
            fm.liveswitch.AudioDecoder r0 = r11.createG722Decoder(r0)     // Catch:{ Exception -> 0x0105 }
            r11.setG722Decoder(r0)     // Catch:{ Exception -> 0x0105 }
            goto L_0x010d
        L_0x0105:
            r0 = move-exception
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r4 = "Could not create remote G.722 decoder."
            r3.error((java.lang.String) r4, (java.lang.Exception) r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x010d:
            fm.liveswitch.AudioDecoder r0 = r11.getG722Decoder()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x0197
            fm.liveswitch.AudioDecoder r0 = r11.getG722Decoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getInputConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r0 = r11.createG722Depacketizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setG722Depacketizer(r0)     // Catch:{ Exception -> 0x05a5 }
            boolean r0 = r11.getAecDisabled()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x0138
            fm.liveswitch.AudioDecoder r0 = r11.getG722Decoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getOutputConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r0 = r11.createAudioSink(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setG722Sink(r0)     // Catch:{ Exception -> 0x05a5 }
            goto L_0x0152
        L_0x0138:
            fm.liveswitch.AecContext r0 = r11.getAecContext()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r0 = r11.createSoundConverter(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setG722Converter(r0)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AecContext r0 = r11.getAecContext()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r0 = r0.getOutputMixerSink()     // Catch:{ Exception -> 0x05a5 }
            r11.setG722Sink(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x0152:
            fm.liveswitch.AudioPipe r0 = r11.getG722Converter()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x016a
            fm.liveswitch.AudioDecoder r0 = r11.getG722Decoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioFormat r0 = (fm.liveswitch.AudioFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r0 = r11.createAudioSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setG722Synchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            goto L_0x017b
        L_0x016a:
            fm.liveswitch.AudioPipe r0 = r11.getG722Converter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioFormat r0 = (fm.liveswitch.AudioFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r0 = r11.createAudioSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setG722Synchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x017b:
            fm.liveswitch.AudioPipe r4 = r11.getG722Depacketizer()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioDecoder r5 = r11.getG722Decoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r6 = r11.getG722Converter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r7 = r11.getG722Synchronizer()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r8 = r11.getG722Sink()     // Catch:{ Exception -> 0x05a5 }
            r3 = r11
            fm.liveswitch.AudioTrack r0 = r3.createAudioTrack(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x05a5 }
            r13.add(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x0197:
            boolean r0 = r11.getPcmuDisabled()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x0239
            fm.liveswitch.AudioConfig r0 = r11.__pcmuConfig     // Catch:{ Exception -> 0x01a7 }
            fm.liveswitch.AudioDecoder r0 = r11.createPcmuDecoder(r0)     // Catch:{ Exception -> 0x01a7 }
            r11.setPcmuDecoder(r0)     // Catch:{ Exception -> 0x01a7 }
            goto L_0x01af
        L_0x01a7:
            r0 = move-exception
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r4 = "Could not create remote PCMU decoder."
            r3.error((java.lang.String) r4, (java.lang.Exception) r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x01af:
            fm.liveswitch.AudioDecoder r0 = r11.getPcmuDecoder()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x0239
            fm.liveswitch.AudioDecoder r0 = r11.getPcmuDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getInputConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r0 = r11.createPcmuDepacketizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmuDepacketizer(r0)     // Catch:{ Exception -> 0x05a5 }
            boolean r0 = r11.getAecDisabled()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x01da
            fm.liveswitch.AudioDecoder r0 = r11.getPcmuDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getOutputConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r0 = r11.createAudioSink(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmuSink(r0)     // Catch:{ Exception -> 0x05a5 }
            goto L_0x01f4
        L_0x01da:
            fm.liveswitch.AecContext r0 = r11.getAecContext()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r0 = r11.createSoundConverter(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmuConverter(r0)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AecContext r0 = r11.getAecContext()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r0 = r0.getOutputMixerSink()     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmuSink(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x01f4:
            fm.liveswitch.AudioPipe r0 = r11.getPcmuConverter()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x020c
            fm.liveswitch.AudioDecoder r0 = r11.getPcmuDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioFormat r0 = (fm.liveswitch.AudioFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r0 = r11.createAudioSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmuSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            goto L_0x021d
        L_0x020c:
            fm.liveswitch.AudioPipe r0 = r11.getPcmuConverter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioFormat r0 = (fm.liveswitch.AudioFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r0 = r11.createAudioSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmuSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x021d:
            fm.liveswitch.AudioPipe r4 = r11.getPcmuDepacketizer()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioDecoder r5 = r11.getPcmuDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r6 = r11.getPcmuConverter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r7 = r11.getPcmuSynchronizer()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r8 = r11.getPcmuSink()     // Catch:{ Exception -> 0x05a5 }
            r3 = r11
            fm.liveswitch.AudioTrack r0 = r3.createAudioTrack(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x05a5 }
            r13.add(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x0239:
            boolean r0 = r11.getPcmaDisabled()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x02db
            fm.liveswitch.AudioConfig r0 = r11.__pcmaConfig     // Catch:{ Exception -> 0x0249 }
            fm.liveswitch.AudioDecoder r0 = r11.createPcmaDecoder(r0)     // Catch:{ Exception -> 0x0249 }
            r11.setPcmaDecoder(r0)     // Catch:{ Exception -> 0x0249 }
            goto L_0x0251
        L_0x0249:
            r0 = move-exception
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r4 = "Could not create remote PCMA decoder."
            r3.error((java.lang.String) r4, (java.lang.Exception) r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x0251:
            fm.liveswitch.AudioDecoder r0 = r11.getPcmaDecoder()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x02db
            fm.liveswitch.AudioDecoder r0 = r11.getPcmaDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getInputConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r0 = r11.createPcmaDepacketizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmaDepacketizer(r0)     // Catch:{ Exception -> 0x05a5 }
            boolean r0 = r11.getAecDisabled()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x027c
            fm.liveswitch.AudioDecoder r0 = r11.getPcmaDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getOutputConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r0 = r11.createAudioSink(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmaSink(r0)     // Catch:{ Exception -> 0x05a5 }
            goto L_0x0296
        L_0x027c:
            fm.liveswitch.AecContext r0 = r11.getAecContext()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioConfig r0 = r0.getConfig()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r0 = r11.createSoundConverter(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmaConverter(r0)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AecContext r0 = r11.getAecContext()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r0 = r0.getOutputMixerSink()     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmaSink(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x0296:
            fm.liveswitch.AudioPipe r0 = r11.getPcmaConverter()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x02ae
            fm.liveswitch.AudioDecoder r0 = r11.getPcmaDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioFormat r0 = (fm.liveswitch.AudioFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r0 = r11.createAudioSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmaSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            goto L_0x02bf
        L_0x02ae:
            fm.liveswitch.AudioPipe r0 = r11.getPcmaConverter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioFormat r0 = (fm.liveswitch.AudioFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r0 = r11.createAudioSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setPcmaSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x02bf:
            fm.liveswitch.AudioPipe r4 = r11.getPcmaDepacketizer()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioDecoder r5 = r11.getPcmaDecoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioPipe r6 = r11.getPcmaConverter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSynchronizer r7 = r11.getPcmaSynchronizer()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioSink r8 = r11.getPcmaSink()     // Catch:{ Exception -> 0x05a5 }
            r3 = r11
            fm.liveswitch.AudioTrack r0 = r3.createAudioTrack(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x05a5 }
            r13.add(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x02db:
            int r0 = fm.liveswitch.ArrayListExtensions.getCount(r13)     // Catch:{ Exception -> 0x05a5 }
            if (r0 <= 0) goto L_0x02f0
            fm.liveswitch.AudioTrack[] r0 = new fm.liveswitch.AudioTrack[r2]     // Catch:{ Exception -> 0x05a5 }
            java.lang.Object[] r13 = r13.toArray(r0)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaTrack[] r13 = (fm.liveswitch.MediaTrack[]) r13     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaTrack r12 = r12.next((TTrack[]) r13)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.AudioTrack r12 = (fm.liveswitch.AudioTrack) r12     // Catch:{ Exception -> 0x05a5 }
            goto L_0x02f7
        L_0x02f0:
            fm.liveswitch.ILog r13 = __log     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r0 = "Could not initialize remote media. No audio decoders initialized. Check the logs for more detail."
            r13.error(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x02f7:
            r11.addAudioTrack(r12)     // Catch:{ Exception -> 0x05a5 }
        L_0x02fa:
            boolean r12 = r11.getVideoDisabled()     // Catch:{ Exception -> 0x05a5 }
            if (r12 != 0) goto L_0x05a4
            fm.liveswitch.VideoTrack r12 = new fm.liveswitch.VideoTrack     // Catch:{ Exception -> 0x05a5 }
            r12.<init>()     // Catch:{ Exception -> 0x05a5 }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ Exception -> 0x05a5 }
            r13.<init>()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.ViewSink r0 = r11.createViewSink()     // Catch:{ Exception -> 0x0312 }
            r11.setViewSink(r0)     // Catch:{ Exception -> 0x0312 }
            goto L_0x031a
        L_0x0312:
            r0 = move-exception
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r4 = "Could not create remote view sink."
            r3.error((java.lang.String) r4, (java.lang.Exception) r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x031a:
            boolean r0 = r11.getVp8Disabled()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x03ac
            fm.liveswitch.VideoDecoder r0 = r11.createVp8Decoder()     // Catch:{ Exception -> 0x0328 }
            r11.setVp8Decoder(r0)     // Catch:{ Exception -> 0x0328 }
            goto L_0x0330
        L_0x0328:
            r0 = move-exception
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r4 = "Could not create remote VP8 decoder."
            r3.error((java.lang.String) r4, (java.lang.Exception) r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x0330:
            fm.liveswitch.VideoDecoder r0 = r11.getVp8Decoder()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x03ac
            fm.liveswitch.VideoPipe r0 = r11.createVp8Depacketizer()     // Catch:{ Exception -> 0x05a5 }
            r11.setVp8Depacketizer(r0)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.ViewSink r0 = r11.getViewSink()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x0368
            fm.liveswitch.ViewSink r0 = r11.getViewSink()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getInputFormat()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x0358
            fm.liveswitch.ViewSink r0 = r11.getViewSink()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getInputFormat()     // Catch:{ Exception -> 0x05a5 }
        L_0x0355:
            fm.liveswitch.VideoFormat r0 = (fm.liveswitch.VideoFormat) r0     // Catch:{ Exception -> 0x05a5 }
            goto L_0x0361
        L_0x0358:
            fm.liveswitch.VideoDecoder r0 = r11.getVp8Decoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            goto L_0x0355
        L_0x0361:
            fm.liveswitch.VideoPipe r0 = r11.createImageConverter(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setVp8Converter(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x0368:
            fm.liveswitch.VideoPipe r0 = r11.getVp8Converter()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x0380
            fm.liveswitch.VideoDecoder r0 = r11.getVp8Decoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoFormat r0 = (fm.liveswitch.VideoFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoSynchronizer r0 = r11.createVideoSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setVp8Synchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            goto L_0x0391
        L_0x0380:
            fm.liveswitch.VideoPipe r0 = r11.getVp8Converter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoFormat r0 = (fm.liveswitch.VideoFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoSynchronizer r0 = r11.createVideoSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setVp8Synchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x0391:
            fm.liveswitch.VideoPipe[] r0 = new fm.liveswitch.VideoPipe[r1]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoPipe r3 = r11.getVp8Depacketizer()     // Catch:{ Exception -> 0x05a5 }
            r0[r2] = r3     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoDecoder r3 = r11.getVp8Decoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoPipe r4 = r11.getVp8Converter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoSynchronizer r5 = r11.getVp8Synchronizer()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoTrack r0 = r11.createVideoTrack(r0, r3, r4, r5)     // Catch:{ Exception -> 0x05a5 }
            r13.add(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x03ac:
            boolean r0 = r11.getH264Disabled()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x04e3
            fm.liveswitch.VideoDecoder[] r0 = r11.createH264Decoders()     // Catch:{ Exception -> 0x03c9 }
            if (r0 == 0) goto L_0x03bd
            fm.liveswitch.VideoDecoder[] r0 = r11.createH264Decoders()     // Catch:{ Exception -> 0x03c9 }
            goto L_0x03c5
        L_0x03bd:
            fm.liveswitch.VideoDecoder[] r0 = new fm.liveswitch.VideoDecoder[r1]     // Catch:{ Exception -> 0x03c9 }
            fm.liveswitch.VideoDecoder r3 = r11.createH264Decoder()     // Catch:{ Exception -> 0x03c9 }
            r0[r2] = r3     // Catch:{ Exception -> 0x03c9 }
        L_0x03c5:
            r11.setH264Decoders(r0)     // Catch:{ Exception -> 0x03c9 }
            goto L_0x03d1
        L_0x03c9:
            r0 = move-exception
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r4 = "Could not create remote H.264 decoder."
            r3.error((java.lang.String) r4, (java.lang.Exception) r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x03d1:
            fm.liveswitch.VideoDecoder[] r0 = r11.getH264Decoders()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x04e3
            fm.liveswitch.VideoDecoder[] r0 = r11.getH264Decoders()     // Catch:{ Exception -> 0x05a5 }
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoPipe[][] r3 = new fm.liveswitch.VideoPipe[r0][]     // Catch:{ Exception -> 0x05a5 }
            r11.setH264DepacketizersArray(r3)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoPipe[] r3 = new fm.liveswitch.VideoPipe[r0]     // Catch:{ Exception -> 0x05a5 }
            r11.setH264Converters(r3)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoSynchronizer[] r3 = new fm.liveswitch.VideoSynchronizer[r0]     // Catch:{ Exception -> 0x05a5 }
            r11.setH264Synchronizers(r3)     // Catch:{ Exception -> 0x05a5 }
            r3 = 0
        L_0x03ef:
            if (r3 >= r0) goto L_0x04e3
            fm.liveswitch.VideoDecoder[] r4 = r11.getH264Decoders()     // Catch:{ Exception -> 0x05a5 }
            r4 = r4[r3]     // Catch:{ Exception -> 0x05a5 }
            if (r4 == 0) goto L_0x04df
            fm.liveswitch.VideoPipe[][] r4 = r11.getH264DepacketizersArray()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoPipe[] r5 = r11.createH264Depacketizers()     // Catch:{ Exception -> 0x05a5 }
            if (r5 == 0) goto L_0x0408
            fm.liveswitch.VideoPipe[] r5 = r11.createH264Depacketizers()     // Catch:{ Exception -> 0x05a5 }
            goto L_0x0410
        L_0x0408:
            fm.liveswitch.VideoPipe[] r5 = new fm.liveswitch.VideoPipe[r1]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoPipe r6 = r11.createH264Depacketizer()     // Catch:{ Exception -> 0x05a5 }
            r5[r2] = r6     // Catch:{ Exception -> 0x05a5 }
        L_0x0410:
            r4[r3] = r5     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.ViewSink r4 = r11.getViewSink()     // Catch:{ Exception -> 0x05a5 }
            if (r4 == 0) goto L_0x0442
            fm.liveswitch.VideoPipe[] r4 = r11.getH264Converters()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.ViewSink r5 = r11.getViewSink()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r5 = r5.getInputFormat()     // Catch:{ Exception -> 0x05a5 }
            if (r5 == 0) goto L_0x0431
            fm.liveswitch.ViewSink r5 = r11.getViewSink()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r5 = r5.getInputFormat()     // Catch:{ Exception -> 0x05a5 }
        L_0x042e:
            fm.liveswitch.VideoFormat r5 = (fm.liveswitch.VideoFormat) r5     // Catch:{ Exception -> 0x05a5 }
            goto L_0x043c
        L_0x0431:
            fm.liveswitch.VideoDecoder[] r5 = r11.getH264Decoders()     // Catch:{ Exception -> 0x05a5 }
            r5 = r5[r3]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r5 = r5.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            goto L_0x042e
        L_0x043c:
            fm.liveswitch.VideoPipe r5 = r11.createImageConverter(r5)     // Catch:{ Exception -> 0x05a5 }
            r4[r3] = r5     // Catch:{ Exception -> 0x05a5 }
        L_0x0442:
            fm.liveswitch.VideoPipe[] r4 = r11.getH264Converters()     // Catch:{ Exception -> 0x05a5 }
            r4 = r4[r3]     // Catch:{ Exception -> 0x05a5 }
            if (r4 != 0) goto L_0x0461
            fm.liveswitch.VideoSynchronizer[] r4 = r11.getH264Synchronizers()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoDecoder[] r5 = r11.getH264Decoders()     // Catch:{ Exception -> 0x05a5 }
            r5 = r5[r3]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r5 = r5.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoFormat r5 = (fm.liveswitch.VideoFormat) r5     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoSynchronizer r5 = r11.createVideoSynchronizer(r5)     // Catch:{ Exception -> 0x05a5 }
            r4[r3] = r5     // Catch:{ Exception -> 0x05a5 }
            goto L_0x0477
        L_0x0461:
            fm.liveswitch.VideoSynchronizer[] r4 = r11.getH264Synchronizers()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoPipe[] r5 = r11.getH264Converters()     // Catch:{ Exception -> 0x05a5 }
            r5 = r5[r3]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r5 = r5.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoFormat r5 = (fm.liveswitch.VideoFormat) r5     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoSynchronizer r5 = r11.createVideoSynchronizer(r5)     // Catch:{ Exception -> 0x05a5 }
            r4[r3] = r5     // Catch:{ Exception -> 0x05a5 }
        L_0x0477:
            fm.liveswitch.VideoDecoder[] r4 = r11.getH264Decoders()     // Catch:{ Exception -> 0x05a5 }
            r4 = r4[r3]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r4 = r4.getInputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoFormat r4 = (fm.liveswitch.VideoFormat) r4     // Catch:{ Exception -> 0x05a5 }
            if (r4 == 0) goto L_0x04c0
            fm.liveswitch.VideoPipe[][] r5 = r11.getH264DepacketizersArray()     // Catch:{ Exception -> 0x05a5 }
            r5 = r5[r3]     // Catch:{ Exception -> 0x05a5 }
            int r6 = r5.length     // Catch:{ Exception -> 0x05a5 }
            r7 = 0
        L_0x048d:
            if (r7 >= r6) goto L_0x04c0
            r8 = r5[r7]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r9 = r8.getInputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoFormat r9 = (fm.liveswitch.VideoFormat) r9     // Catch:{ Exception -> 0x05a5 }
            if (r9 == 0) goto L_0x04a7
            java.lang.String r10 = r4.getProfile()     // Catch:{ Exception -> 0x05a5 }
            r9.setProfile(r10)     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r10 = r4.getLevel()     // Catch:{ Exception -> 0x05a5 }
            r9.setLevel(r10)     // Catch:{ Exception -> 0x05a5 }
        L_0x04a7:
            fm.liveswitch.MediaFormat r8 = r8.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoFormat r8 = (fm.liveswitch.VideoFormat) r8     // Catch:{ Exception -> 0x05a5 }
            if (r8 == 0) goto L_0x04bd
            java.lang.String r9 = r4.getProfile()     // Catch:{ Exception -> 0x05a5 }
            r8.setProfile(r9)     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r9 = r4.getLevel()     // Catch:{ Exception -> 0x05a5 }
            r8.setLevel(r9)     // Catch:{ Exception -> 0x05a5 }
        L_0x04bd:
            int r7 = r7 + 1
            goto L_0x048d
        L_0x04c0:
            fm.liveswitch.VideoPipe[][] r4 = r11.getH264DepacketizersArray()     // Catch:{ Exception -> 0x05a5 }
            r4 = r4[r3]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoDecoder[] r5 = r11.getH264Decoders()     // Catch:{ Exception -> 0x05a5 }
            r5 = r5[r3]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoPipe[] r6 = r11.getH264Converters()     // Catch:{ Exception -> 0x05a5 }
            r6 = r6[r3]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoSynchronizer[] r7 = r11.getH264Synchronizers()     // Catch:{ Exception -> 0x05a5 }
            r7 = r7[r3]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoTrack r4 = r11.createVideoTrack(r4, r5, r6, r7)     // Catch:{ Exception -> 0x05a5 }
            r13.add(r4)     // Catch:{ Exception -> 0x05a5 }
        L_0x04df:
            int r3 = r3 + 1
            goto L_0x03ef
        L_0x04e3:
            boolean r0 = r11.getVp9Disabled()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x0575
            fm.liveswitch.VideoDecoder r0 = r11.createVp9Decoder()     // Catch:{ Exception -> 0x04f1 }
            r11.setVp9Decoder(r0)     // Catch:{ Exception -> 0x04f1 }
            goto L_0x04f9
        L_0x04f1:
            r0 = move-exception
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r4 = "Could not create remote VP9 decoder."
            r3.error((java.lang.String) r4, (java.lang.Exception) r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x04f9:
            fm.liveswitch.VideoDecoder r0 = r11.getVp9Decoder()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x0575
            fm.liveswitch.VideoPipe r0 = r11.createVp9Depacketizer()     // Catch:{ Exception -> 0x05a5 }
            r11.setVp9Depacketizer(r0)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.ViewSink r0 = r11.getViewSink()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x0531
            fm.liveswitch.ViewSink r0 = r11.getViewSink()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getInputFormat()     // Catch:{ Exception -> 0x05a5 }
            if (r0 == 0) goto L_0x0521
            fm.liveswitch.ViewSink r0 = r11.getViewSink()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getInputFormat()     // Catch:{ Exception -> 0x05a5 }
        L_0x051e:
            fm.liveswitch.VideoFormat r0 = (fm.liveswitch.VideoFormat) r0     // Catch:{ Exception -> 0x05a5 }
            goto L_0x052a
        L_0x0521:
            fm.liveswitch.VideoDecoder r0 = r11.getVp9Decoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            goto L_0x051e
        L_0x052a:
            fm.liveswitch.VideoPipe r0 = r11.createImageConverter(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setVp9Converter(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x0531:
            fm.liveswitch.VideoPipe r0 = r11.getVp9Converter()     // Catch:{ Exception -> 0x05a5 }
            if (r0 != 0) goto L_0x0549
            fm.liveswitch.VideoDecoder r0 = r11.getVp9Decoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoFormat r0 = (fm.liveswitch.VideoFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoSynchronizer r0 = r11.createVideoSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setVp9Synchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            goto L_0x055a
        L_0x0549:
            fm.liveswitch.VideoPipe r0 = r11.getVp9Converter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoFormat r0 = (fm.liveswitch.VideoFormat) r0     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoSynchronizer r0 = r11.createVideoSynchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
            r11.setVp9Synchronizer(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x055a:
            fm.liveswitch.VideoPipe[] r0 = new fm.liveswitch.VideoPipe[r1]     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoPipe r3 = r11.getVp9Depacketizer()     // Catch:{ Exception -> 0x05a5 }
            r0[r2] = r3     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoDecoder r3 = r11.getVp9Decoder()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoPipe r4 = r11.getVp9Converter()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoSynchronizer r5 = r11.getVp9Synchronizer()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoTrack r0 = r11.createVideoTrack(r0, r3, r4, r5)     // Catch:{ Exception -> 0x05a5 }
            r13.add(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x0575:
            int r0 = fm.liveswitch.ArrayListExtensions.getCount(r13)     // Catch:{ Exception -> 0x05a5 }
            if (r0 <= 0) goto L_0x058a
            fm.liveswitch.VideoTrack[] r0 = new fm.liveswitch.VideoTrack[r2]     // Catch:{ Exception -> 0x05a5 }
            java.lang.Object[] r13 = r13.toArray(r0)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaTrack[] r13 = (fm.liveswitch.MediaTrack[]) r13     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaTrack r12 = r12.next((TTrack[]) r13)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoTrack r12 = (fm.liveswitch.VideoTrack) r12     // Catch:{ Exception -> 0x05a5 }
            goto L_0x0591
        L_0x058a:
            fm.liveswitch.ILog r13 = __log     // Catch:{ Exception -> 0x05a5 }
            java.lang.String r0 = "Could not initialize remote media. No video decoders initialized. Check the logs for more detail."
            r13.error(r0)     // Catch:{ Exception -> 0x05a5 }
        L_0x0591:
            fm.liveswitch.ViewSink r13 = r11.getViewSink()     // Catch:{ Exception -> 0x05a5 }
            if (r13 == 0) goto L_0x05a1
            fm.liveswitch.ViewSink r13 = r11.getViewSink()     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.MediaTrack r12 = r12.next(r13)     // Catch:{ Exception -> 0x05a5 }
            fm.liveswitch.VideoTrack r12 = (fm.liveswitch.VideoTrack) r12     // Catch:{ Exception -> 0x05a5 }
        L_0x05a1:
            r11.addVideoTrack(r12)     // Catch:{ Exception -> 0x05a5 }
        L_0x05a4:
            return r1
        L_0x05a5:
            r12 = move-exception
            fm.liveswitch.ILog r13 = __log
            java.lang.String r0 = "Error occured while initializing remote media."
            r13.error((java.lang.String) r0, (java.lang.Exception) r12)
            java.lang.RuntimeException r13 = new java.lang.RuntimeException
            r13.<init>(r12)
            throw r13
        L_0x05b3:
            r12 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x05b3 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtcRemoteMedia.initialize(fm.liveswitch.RtcAudioTrackConfig, fm.liveswitch.RtcVideoTrackConfig):boolean");
    }

    public void removeOnActiveAudioConverterChange(IAction1<AudioPipe> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveAudioConverterChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveAudioConverterChange.remove(iAction1);
        if (this.__onActiveAudioConverterChange.size() == 0) {
            this._onActiveAudioConverterChange = null;
        }
    }

    public void removeOnActiveAudioDecoderChange(IAction1<AudioDecoder> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveAudioDecoderChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveAudioDecoderChange.remove(iAction1);
        if (this.__onActiveAudioDecoderChange.size() == 0) {
            this._onActiveAudioDecoderChange = null;
        }
    }

    public void removeOnActiveAudioDepacketizerChange(IAction1<AudioPipe> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveAudioDepacketizerChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveAudioDepacketizerChange.remove(iAction1);
        if (this.__onActiveAudioDepacketizerChange.size() == 0) {
            this._onActiveAudioDepacketizerChange = null;
        }
    }

    public void removeOnActiveAudioSinkChange(IAction1<AudioSink> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveAudioSinkChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveAudioSinkChange.remove(iAction1);
        if (this.__onActiveAudioSinkChange.size() == 0) {
            this._onActiveAudioSinkChange = null;
        }
    }

    public void removeOnActiveAudioSynchronizerChange(IAction1<AudioSynchronizer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveAudioSynchronizerChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveAudioSynchronizerChange.remove(iAction1);
        if (this.__onActiveAudioSynchronizerChange.size() == 0) {
            this._onActiveAudioSynchronizerChange = null;
        }
    }

    public void removeOnActiveVideoConverterChange(IAction1<VideoPipe> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveVideoConverterChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveVideoConverterChange.remove(iAction1);
        if (this.__onActiveVideoConverterChange.size() == 0) {
            this._onActiveVideoConverterChange = null;
        }
    }

    public void removeOnActiveVideoDecoderChange(IAction1<VideoDecoder> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveVideoDecoderChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveVideoDecoderChange.remove(iAction1);
        if (this.__onActiveVideoDecoderChange.size() == 0) {
            this._onActiveVideoDecoderChange = null;
        }
    }

    public void removeOnActiveVideoDepacketizerChange(IAction1<VideoPipe> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveVideoDepacketizerChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveVideoDepacketizerChange.remove(iAction1);
        if (this.__onActiveVideoDepacketizerChange.size() == 0) {
            this._onActiveVideoDepacketizerChange = null;
        }
    }

    public void removeOnActiveVideoSynchronizerChange(IAction1<VideoSynchronizer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveVideoSynchronizerChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveVideoSynchronizerChange.remove(iAction1);
        if (this.__onActiveVideoSynchronizerChange.size() == 0) {
            this._onActiveVideoSynchronizerChange = null;
        }
    }

    public RtcRemoteMedia(boolean z, boolean z2) {
        this(z, z2, (AecContext) null);
    }

    public RtcRemoteMedia(boolean z, boolean z2, AecContext aecContext) {
        this.__onActiveAudioConverterChange = new ArrayList();
        this.__onActiveAudioDecoderChange = new ArrayList();
        this.__onActiveAudioDepacketizerChange = new ArrayList();
        this.__onActiveAudioSinkChange = new ArrayList();
        this.__onActiveAudioSynchronizerChange = new ArrayList();
        this.__onActiveVideoConverterChange = new ArrayList();
        this.__onActiveVideoDecoderChange = new ArrayList();
        this.__onActiveVideoDepacketizerChange = new ArrayList();
        this.__onActiveVideoSynchronizerChange = new ArrayList();
        this._onActiveAudioConverterChange = null;
        this._onActiveAudioDecoderChange = null;
        this._onActiveAudioDepacketizerChange = null;
        this._onActiveAudioSinkChange = null;
        this._onActiveAudioSynchronizerChange = null;
        this._onActiveVideoConverterChange = null;
        this._onActiveVideoDecoderChange = null;
        this._onActiveVideoDepacketizerChange = null;
        this._onActiveVideoSynchronizerChange = null;
        this.__opusConfig = new AudioConfig(48000, 2);
        this.__g722Config = new AudioConfig(16000, 1);
        this.__pcmuConfig = new AudioConfig(8000, 1);
        this.__pcmaConfig = new AudioConfig(8000, 1);
        this.__audioRecorders = new ArrayList<>();
        this.__videoRecorders = new ArrayList<>();
        this.__initializeLock = new Object();
        this.__initialized = false;
        this._audioRecordingLock = new Object();
        this._videoRecordingLock = new Object();
        if (!(aecContext == null || aecContext.getProcessor() == null || Global.equals(aecContext.getProcessor().getState(), MediaPipeState.Initialized))) {
            __log.warn("Remote media received a reference to a destroyed AEC context. AEC will be disabled.");
            aecContext = null;
        }
        setAudioDisabled(z);
        setVideoDisabled(z2);
        setAecContext(aecContext);
    }

    /* access modifiers changed from: private */
    public void setActiveAudioConverter(AudioPipe audioPipe) {
        this._activeAudioConverter = audioPipe;
    }

    /* access modifiers changed from: private */
    public void setActiveAudioDecoder(AudioDecoder audioDecoder) {
        this._activeAudioDecoder = audioDecoder;
    }

    /* access modifiers changed from: private */
    public void setActiveAudioDepacketizer(AudioPipe audioPipe) {
        this._activeAudioDepacketizer = audioPipe;
    }

    /* access modifiers changed from: private */
    public void setActiveAudioSink(AudioSink audioSink) {
        this._activeAudioSink = audioSink;
    }

    /* access modifiers changed from: private */
    public void setActiveAudioSynchronizer(AudioSynchronizer audioSynchronizer) {
        this._activeAudioSynchronizer = audioSynchronizer;
    }

    /* access modifiers changed from: private */
    public void setActiveVideoConverter(VideoPipe videoPipe) {
        this._activeVideoConverter = videoPipe;
    }

    /* access modifiers changed from: private */
    public void setActiveVideoDecoder(VideoDecoder videoDecoder) {
        this._activeVideoDecoder = videoDecoder;
    }

    /* access modifiers changed from: private */
    public void setActiveVideoDepacketizer(VideoPipe videoPipe) {
        this._activeVideoDepacketizer = videoPipe;
    }

    /* access modifiers changed from: private */
    public void setActiveVideoSynchronizer(VideoSynchronizer videoSynchronizer) {
        this._activeVideoSynchronizer = videoSynchronizer;
    }

    private void setAecContext(AecContext aecContext) {
        this._aecContext = aecContext;
    }

    private void setAudioDisabled(boolean z) {
        this._audioDisabled = z;
    }

    private void setG722Converter(AudioPipe audioPipe) {
        this._g722Converter = audioPipe;
    }

    private void setG722Decoder(AudioDecoder audioDecoder) {
        this._g722Decoder = audioDecoder;
    }

    private void setG722Depacketizer(AudioPipe audioPipe) {
        this._g722Depacketizer = audioPipe;
    }

    private void setG722Disabled(boolean z) {
        this._g722Disabled = z;
    }

    private void setG722Sink(AudioSink audioSink) {
        this._g722Sink = audioSink;
    }

    private void setG722Synchronizer(AudioSynchronizer audioSynchronizer) {
        this._g722Synchronizer = audioSynchronizer;
    }

    private void setH264Converters(VideoPipe[] videoPipeArr) {
        this._h264Converters = videoPipeArr;
    }

    private void setH264Decoders(VideoDecoder[] videoDecoderArr) {
        this._h264Decoders = videoDecoderArr;
    }

    private void setH264DepacketizersArray(VideoPipe[][] videoPipeArr) {
        this._h264DepacketizersArray = videoPipeArr;
    }

    private void setH264Disabled(boolean z) {
        this._h264Disabled = z;
    }

    private void setH264Synchronizers(VideoSynchronizer[] videoSynchronizerArr) {
        this._h264Synchronizers = videoSynchronizerArr;
    }

    private void setOpusConverter(AudioPipe audioPipe) {
        this._opusConverter = audioPipe;
    }

    private void setOpusDecoder(AudioDecoder audioDecoder) {
        this._opusDecoder = audioDecoder;
    }

    private void setOpusDepacketizer(AudioPipe audioPipe) {
        this._opusDepacketizer = audioPipe;
    }

    private void setOpusDisabled(boolean z) {
        this._opusDisabled = z;
    }

    private void setOpusSink(AudioSink audioSink) {
        this._opusSink = audioSink;
    }

    private void setOpusSynchronizer(AudioSynchronizer audioSynchronizer) {
        this._opusSynchronizer = audioSynchronizer;
    }

    private void setPcmaConverter(AudioPipe audioPipe) {
        this._pcmaConverter = audioPipe;
    }

    private void setPcmaDecoder(AudioDecoder audioDecoder) {
        this._pcmaDecoder = audioDecoder;
    }

    private void setPcmaDepacketizer(AudioPipe audioPipe) {
        this._pcmaDepacketizer = audioPipe;
    }

    private void setPcmaDisabled(boolean z) {
        this._pcmaDisabled = z;
    }

    private void setPcmaSink(AudioSink audioSink) {
        this._pcmaSink = audioSink;
    }

    private void setPcmaSynchronizer(AudioSynchronizer audioSynchronizer) {
        this._pcmaSynchronizer = audioSynchronizer;
    }

    private void setPcmuConverter(AudioPipe audioPipe) {
        this._pcmuConverter = audioPipe;
    }

    private void setPcmuDecoder(AudioDecoder audioDecoder) {
        this._pcmuDecoder = audioDecoder;
    }

    private void setPcmuDepacketizer(AudioPipe audioPipe) {
        this._pcmuDepacketizer = audioPipe;
    }

    private void setPcmuDisabled(boolean z) {
        this._pcmuDisabled = z;
    }

    private void setPcmuSink(AudioSink audioSink) {
        this._pcmuSink = audioSink;
    }

    private void setPcmuSynchronizer(AudioSynchronizer audioSynchronizer) {
        this._pcmuSynchronizer = audioSynchronizer;
    }

    private void setVideoDisabled(boolean z) {
        this._videoDisabled = z;
    }

    private void setViewSink(ViewSink<TView> viewSink) {
        this._viewSink = viewSink;
    }

    private void setVp8Converter(VideoPipe videoPipe) {
        this._vp8Converter = videoPipe;
    }

    private void setVp8Decoder(VideoDecoder videoDecoder) {
        this._vp8Decoder = videoDecoder;
    }

    private void setVp8Depacketizer(VideoPipe videoPipe) {
        this._vp8Depacketizer = videoPipe;
    }

    private void setVp8Disabled(boolean z) {
        this._vp8Disabled = z;
    }

    private void setVp8Synchronizer(VideoSynchronizer videoSynchronizer) {
        this._vp8Synchronizer = videoSynchronizer;
    }

    private void setVp9Converter(VideoPipe videoPipe) {
        this._vp9Converter = videoPipe;
    }

    private void setVp9Decoder(VideoDecoder videoDecoder) {
        this._vp9Decoder = videoDecoder;
    }

    private void setVp9Depacketizer(VideoPipe videoPipe) {
        this._vp9Depacketizer = videoPipe;
    }

    private void setVp9Disabled(boolean z) {
        this._vp9Disabled = z;
    }

    private void setVp9Synchronizer(VideoSynchronizer videoSynchronizer) {
        this._vp9Synchronizer = videoSynchronizer;
    }

    public boolean toggleAudioRecording() {
        boolean isRecordingAudio;
        boolean z;
        synchronized (this._audioRecordingLock) {
            super.setIsRecordingAudio(!super.getIsRecordingAudio());
            if (getViewSink() != null) {
                ViewSink viewSink = getViewSink();
                if (!super.getIsRecordingVideo()) {
                    if (!super.getIsRecordingAudio()) {
                        z = false;
                        viewSink.setIsRecording(z);
                    }
                }
                z = true;
                viewSink.setIsRecording(z);
            }
            Iterator<AudioSink> it = this.__audioRecorders.iterator();
            while (it.hasNext()) {
                it.next().setDeactivated(!super.getIsRecordingAudio());
            }
            isRecordingAudio = super.getIsRecordingAudio();
        }
        return isRecordingAudio;
    }

    public boolean toggleVideoRecording() {
        boolean isRecordingVideo;
        boolean z;
        synchronized (this._videoRecordingLock) {
            super.setIsRecordingVideo(!super.getIsRecordingVideo());
            if (getViewSink() != null) {
                ViewSink viewSink = getViewSink();
                if (!super.getIsRecordingVideo()) {
                    if (!super.getIsRecordingAudio()) {
                        z = false;
                        viewSink.setIsRecording(z);
                    }
                }
                z = true;
                viewSink.setIsRecording(z);
            }
            Iterator<VideoSink> it = this.__videoRecorders.iterator();
            while (it.hasNext()) {
                it.next().setDeactivated(!super.getIsRecordingVideo());
            }
            isRecordingVideo = super.getIsRecordingVideo();
        }
        return isRecordingVideo;
    }
}
