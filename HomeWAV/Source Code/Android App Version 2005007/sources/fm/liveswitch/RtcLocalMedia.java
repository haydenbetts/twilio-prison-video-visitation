package fm.liveswitch;

import fm.liveswitch.g722.Encoder;
import fm.liveswitch.g722.Packetizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class RtcLocalMedia<TView> extends LocalMedia implements IViewSinkableMedia<TView, ViewSink<TView>>, IViewableMedia<TView> {
    private static ILog __log = Log.getLogger(RtcLocalMedia.class);
    private AudioEncodingConfig[] __audioEncodings;
    /* access modifiers changed from: private */
    public ArrayList<AudioSink> __audioRecorders;
    private AudioConfig __g722Config;
    private Object __initializeLock;
    private boolean __initialized;
    /* access modifiers changed from: private */
    public List<IAction1<AudioPipe>> __onActiveAudioConverterChange;
    /* access modifiers changed from: private */
    public List<IAction1<AudioEncoder>> __onActiveAudioEncoderChange;
    /* access modifiers changed from: private */
    public List<IAction1<AudioPipe>> __onActiveAudioPacketizerChange;
    /* access modifiers changed from: private */
    public List<IAction1<VideoPipe>> __onActiveVideoControllerChange;
    /* access modifiers changed from: private */
    public List<IAction1<VideoPipe>> __onActiveVideoConverterChange;
    /* access modifiers changed from: private */
    public List<IAction1<VideoEncoder>> __onActiveVideoEncoderChange;
    /* access modifiers changed from: private */
    public List<IAction1<VideoPipe>> __onActiveVideoPacketizerChange;
    private AudioConfig __opusConfig;
    private AudioConfig __pcmaConfig;
    private AudioConfig __pcmuConfig;
    private double __videoBitsPerPixel;
    private VideoDegradationPreference __videoDegradationPreference;
    private VideoEncodingConfig[] __videoEncodings;
    /* access modifiers changed from: private */
    public ArrayList<VideoSink> __videoRecorders;
    private AudioPipe _activeAudioConverter;
    private AudioEncoder _activeAudioEncoder;
    private AudioPipe _activeAudioPacketizer;
    private VideoPipe _activeVideoController;
    private VideoPipe _activeVideoConverter;
    private VideoEncoder _activeVideoEncoder;
    private VideoPipe _activeVideoPacketizer;
    private AecContext _aecContext;
    private boolean _audioDisabled;
    private Object _audioRecordingLock;
    private boolean _automaticVideoDegradation;
    private AudioPipe _g722Converter;
    private boolean _g722Disabled;
    private AudioEncoder _g722Encoder;
    private AudioPipe _g722Packetizer;
    private VideoPipe[] _h264Controllers;
    private VideoPipe[][] _h264ConvertersArray;
    private boolean _h264Disabled;
    private VideoEncoder[][] _h264EncodersArray;
    private VideoPipe[][][] _h264PacketizersArrayArray;
    /* access modifiers changed from: private */
    public IAction1<AudioPipe> _onActiveAudioConverterChange;
    /* access modifiers changed from: private */
    public IAction1<AudioEncoder> _onActiveAudioEncoderChange;
    /* access modifiers changed from: private */
    public IAction1<AudioPipe> _onActiveAudioPacketizerChange;
    /* access modifiers changed from: private */
    public IAction1<VideoPipe> _onActiveVideoControllerChange;
    /* access modifiers changed from: private */
    public IAction1<VideoPipe> _onActiveVideoConverterChange;
    /* access modifiers changed from: private */
    public IAction1<VideoEncoder> _onActiveVideoEncoderChange;
    /* access modifiers changed from: private */
    public IAction1<VideoPipe> _onActiveVideoPacketizerChange;
    private AudioPipe[] _opusConverters;
    private boolean _opusDisabled;
    private AudioEncoder[] _opusEncoders;
    private AudioPipe[] _opusPacketizers;
    private AudioPipe _pcmaConverter;
    private boolean _pcmaDisabled;
    private AudioEncoder _pcmaEncoder;
    private AudioPipe _pcmaPacketizer;
    private AudioPipe _pcmuConverter;
    private boolean _pcmuDisabled;
    private AudioEncoder _pcmuEncoder;
    private AudioPipe _pcmuPacketizer;
    private boolean _videoDisabled;
    private Object _videoRecordingLock;
    private ViewSink<TView> _viewSink;
    private VideoPipe[] _vp8Controllers;
    private VideoPipe[] _vp8Converters;
    private boolean _vp8Disabled;
    private VideoEncoder[] _vp8Encoders;
    private VideoPipe[] _vp8Packetizers;
    private VideoPipe[] _vp9Controllers;
    private VideoPipe[] _vp9Converters;
    private boolean _vp9Disabled;
    private VideoEncoder[] _vp9Encoders;
    private VideoPipe[] _vp9Packetizers;

    /* access modifiers changed from: protected */
    public abstract AudioSink createAudioRecorder(AudioFormat audioFormat);

    /* access modifiers changed from: protected */
    public abstract AudioSource createAudioSource(AudioConfig audioConfig);

    /* access modifiers changed from: protected */
    public abstract VideoEncoder createH264Encoder();

    /* access modifiers changed from: protected */
    public VideoEncoder[] createH264Encoders() {
        return null;
    }

    /* access modifiers changed from: protected */
    public VideoPipe[] createH264Packetizers() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract VideoPipe createImageConverter(VideoFormat videoFormat);

    /* access modifiers changed from: protected */
    public abstract AudioEncoder createOpusEncoder(AudioConfig audioConfig);

    /* access modifiers changed from: protected */
    public abstract VideoSink createVideoRecorder(VideoFormat videoFormat);

    /* access modifiers changed from: protected */
    public abstract VideoSource createVideoSource();

    /* access modifiers changed from: protected */
    public abstract ViewSink<TView> createViewSink();

    /* access modifiers changed from: protected */
    public abstract VideoEncoder createVp8Encoder();

    /* access modifiers changed from: protected */
    public abstract VideoEncoder createVp9Encoder();

    public void addOnActiveAudioConverterChange(IAction1<AudioPipe> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveAudioConverterChange == null) {
                this._onActiveAudioConverterChange = new IAction1<AudioPipe>() {
                    public void invoke(AudioPipe audioPipe) {
                        Iterator it = new ArrayList(RtcLocalMedia.this.__onActiveAudioConverterChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(audioPipe);
                        }
                    }
                };
            }
            this.__onActiveAudioConverterChange.add(iAction1);
        }
    }

    public void addOnActiveAudioEncoderChange(IAction1<AudioEncoder> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveAudioEncoderChange == null) {
                this._onActiveAudioEncoderChange = new IAction1<AudioEncoder>() {
                    public void invoke(AudioEncoder audioEncoder) {
                        Iterator it = new ArrayList(RtcLocalMedia.this.__onActiveAudioEncoderChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(audioEncoder);
                        }
                    }
                };
            }
            this.__onActiveAudioEncoderChange.add(iAction1);
        }
    }

    public void addOnActiveAudioPacketizerChange(IAction1<AudioPipe> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveAudioPacketizerChange == null) {
                this._onActiveAudioPacketizerChange = new IAction1<AudioPipe>() {
                    public void invoke(AudioPipe audioPipe) {
                        Iterator it = new ArrayList(RtcLocalMedia.this.__onActiveAudioPacketizerChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(audioPipe);
                        }
                    }
                };
            }
            this.__onActiveAudioPacketizerChange.add(iAction1);
        }
    }

    public void addOnActiveVideoControllerChange(IAction1<VideoPipe> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveVideoControllerChange == null) {
                this._onActiveVideoControllerChange = new IAction1<VideoPipe>() {
                    public void invoke(VideoPipe videoPipe) {
                        Iterator it = new ArrayList(RtcLocalMedia.this.__onActiveVideoControllerChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(videoPipe);
                        }
                    }
                };
            }
            this.__onActiveVideoControllerChange.add(iAction1);
        }
    }

    public void addOnActiveVideoConverterChange(IAction1<VideoPipe> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveVideoConverterChange == null) {
                this._onActiveVideoConverterChange = new IAction1<VideoPipe>() {
                    public void invoke(VideoPipe videoPipe) {
                        Iterator it = new ArrayList(RtcLocalMedia.this.__onActiveVideoConverterChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(videoPipe);
                        }
                    }
                };
            }
            this.__onActiveVideoConverterChange.add(iAction1);
        }
    }

    public void addOnActiveVideoEncoderChange(IAction1<VideoEncoder> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveVideoEncoderChange == null) {
                this._onActiveVideoEncoderChange = new IAction1<VideoEncoder>() {
                    public void invoke(VideoEncoder videoEncoder) {
                        Iterator it = new ArrayList(RtcLocalMedia.this.__onActiveVideoEncoderChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(videoEncoder);
                        }
                    }
                };
            }
            this.__onActiveVideoEncoderChange.add(iAction1);
        }
    }

    public void addOnActiveVideoPacketizerChange(IAction1<VideoPipe> iAction1) {
        if (iAction1 != null) {
            if (this._onActiveVideoPacketizerChange == null) {
                this._onActiveVideoPacketizerChange = new IAction1<VideoPipe>() {
                    public void invoke(VideoPipe videoPipe) {
                        Iterator it = new ArrayList(RtcLocalMedia.this.__onActiveVideoPacketizerChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(videoPipe);
                        }
                    }
                };
            }
            this.__onActiveVideoPacketizerChange.add(iAction1);
        }
    }

    private void applyAudioEncodings(AudioEncodingConfig[] audioEncodingConfigArr) {
        applyAudioEncodings(audioEncodingConfigArr, getOpusConverters(), getOpusEncoders(), getOpusPacketizers());
        applyAudioEncodings(audioEncodingConfigArr, new AudioPipe[]{getG722Converter()}, new AudioEncoder[]{getG722Encoder()}, new AudioPipe[]{getG722Packetizer()});
        applyAudioEncodings(audioEncodingConfigArr, new AudioPipe[]{getPcmuConverter()}, new AudioEncoder[]{getPcmuEncoder()}, new AudioPipe[]{getPcmuPacketizer()});
        applyAudioEncodings(audioEncodingConfigArr, new AudioPipe[]{getPcmaConverter()}, new AudioEncoder[]{getPcmaEncoder()}, new AudioPipe[]{getPcmaPacketizer()});
    }

    private void applyAudioEncodings(AudioEncodingConfig[] audioEncodingConfigArr, AudioPipe[] audioPipeArr, AudioEncoder[] audioEncoderArr, AudioPipe[] audioPipeArr2) {
        int bitrate;
        if (audioPipeArr != null) {
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) audioPipeArr); i++) {
                AudioPipe audioPipe = audioPipeArr[i];
                if (audioPipe != null) {
                    long synchronizationSource = audioEncodingConfigArr[i].getSynchronizationSource();
                    if (synchronizationSource >= 0 && audioPipe.getOutputSynchronizationSource() < 0) {
                        audioPipe.setOutputSynchronizationSource(synchronizationSource);
                    }
                }
            }
        }
        if (audioEncoderArr != null) {
            for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) audioEncoderArr); i2++) {
                AudioEncoder audioEncoder = audioEncoderArr[i2];
                if (audioEncoder != null && !((AudioFormat) audioEncoder.getOutputFormat()).getIsFixedBitrate() && (bitrate = audioEncodingConfigArr[i2].getBitrate()) > 0) {
                    try {
                        audioEncoder.setMaxBitrate(bitrate);
                        audioEncoder.setTargetBitrate(bitrate);
                    } catch (Exception e) {
                        __log.error(StringExtensions.format("Could not apply {0} target bitrate of {1}kbps.", audioEncoder.getLabel(), IntegerExtensions.toString(Integer.valueOf(bitrate))), e);
                    }
                }
            }
        }
        if (audioPipeArr2 != null) {
            for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) audioPipeArr2); i3++) {
                AudioPipe audioPipe2 = audioPipeArr2[i3];
                if (audioPipe2 != null) {
                    audioPipe2.setDeactivated(audioEncodingConfigArr[i3].getDeactivated());
                }
            }
        }
    }

    private void applyVideoEncodings(VideoEncodingConfig[] videoEncodingConfigArr) {
        VideoPipe[][] videoPipeArr = new VideoPipe[ArrayExtensions.getLength((Object[]) videoEncodingConfigArr)][];
        VideoEncoder[][] videoEncoderArr = new VideoEncoder[ArrayExtensions.getLength((Object[]) videoEncodingConfigArr)][];
        VideoPipe[][][] videoPipeArr2 = new VideoPipe[ArrayExtensions.getLength((Object[]) videoEncodingConfigArr)][][];
        int i = 0;
        while (true) {
            VideoPipe videoPipe = null;
            if (i >= ArrayExtensions.getLength((Object[]) videoEncodingConfigArr)) {
                break;
            }
            VideoPipe[] videoPipeArr3 = new VideoPipe[1];
            videoPipeArr3[0] = getVp8Converters() == null ? null : getVp8Converters()[i];
            videoPipeArr[i] = videoPipeArr3;
            VideoEncoder[] videoEncoderArr2 = new VideoEncoder[1];
            videoEncoderArr2[0] = getVp8Encoders() == null ? null : getVp8Encoders()[i];
            videoEncoderArr[i] = videoEncoderArr2;
            VideoPipe[][] videoPipeArr4 = new VideoPipe[1][];
            VideoPipe[] videoPipeArr5 = new VideoPipe[1];
            if (getVp8Packetizers() != null) {
                videoPipe = getVp8Packetizers()[i];
            }
            videoPipeArr5[0] = videoPipe;
            videoPipeArr4[0] = videoPipeArr5;
            videoPipeArr2[i] = videoPipeArr4;
            i++;
        }
        VideoPipe[][] videoPipeArr6 = new VideoPipe[ArrayExtensions.getLength((Object[]) videoEncodingConfigArr)][];
        VideoEncoder[][] videoEncoderArr3 = new VideoEncoder[ArrayExtensions.getLength((Object[]) videoEncodingConfigArr)][];
        VideoPipe[][][] videoPipeArr7 = new VideoPipe[ArrayExtensions.getLength((Object[]) videoEncodingConfigArr)][][];
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) videoEncodingConfigArr); i2++) {
            VideoPipe[] videoPipeArr8 = new VideoPipe[1];
            videoPipeArr8[0] = getVp9Converters() == null ? null : getVp9Converters()[i2];
            videoPipeArr6[i2] = videoPipeArr8;
            VideoEncoder[] videoEncoderArr4 = new VideoEncoder[1];
            videoEncoderArr4[0] = getVp9Encoders() == null ? null : getVp9Encoders()[i2];
            videoEncoderArr3[i2] = videoEncoderArr4;
            VideoPipe[][] videoPipeArr9 = new VideoPipe[1][];
            VideoPipe[] videoPipeArr10 = new VideoPipe[1];
            videoPipeArr10[0] = getVp9Packetizers() == null ? null : getVp9Packetizers()[i2];
            videoPipeArr9[0] = videoPipeArr10;
            videoPipeArr7[i2] = videoPipeArr9;
        }
        applyVideoEncodings(videoEncodingConfigArr, getVp8Controllers(), videoPipeArr, videoEncoderArr, videoPipeArr2);
        applyVideoEncodings(videoEncodingConfigArr, getH264Controllers(), getH264ConvertersArray(), getH264EncodersArray(), getH264PacketizersArrayArray());
        applyVideoEncodings(videoEncodingConfigArr, getVp9Controllers(), videoPipeArr6, videoEncoderArr3, videoPipeArr7);
    }

    private void applyVideoEncodings(VideoEncodingConfig[] videoEncodingConfigArr, VideoPipe[] videoPipeArr, VideoPipe[][] videoPipeArr2, VideoEncoder[][] videoEncoderArr, VideoPipe[][][] videoPipeArr3) {
        int bitrate;
        if (videoPipeArr != null) {
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) videoPipeArr); i++) {
                FrameRatePipe frameRatePipe = (FrameRatePipe) Global.tryCast(videoPipeArr[i], FrameRatePipe.class);
                if (frameRatePipe != null) {
                    long synchronizationSource = videoEncodingConfigArr[i].getSynchronizationSource();
                    if (synchronizationSource >= 0 && frameRatePipe.getOutputSynchronizationSource() < 0) {
                        frameRatePipe.setOutputSynchronizationSource(synchronizationSource);
                    }
                    double frameRate = videoEncodingConfigArr[i].getFrameRate();
                    if (frameRate > 0.0d) {
                        try {
                            frameRatePipe.setMaxFrameRate(frameRate);
                            frameRatePipe.setTargetFrameRate(frameRate);
                        } catch (Exception e) {
                            __log.error(StringExtensions.format("Could not apply {0} target frame-rate of {1}fps.", frameRatePipe.getLabel(), DoubleExtensions.toString(Double.valueOf(frameRate))), e);
                        }
                    }
                } else if (videoPipeArr[i] != null) {
                    __log.warn(StringExtensions.format("Could not cast {0} to FrameRatePipe. Update this class to inherit from FrameRatePipe to support frame-rate updates.", (Object) frameRatePipe.getLabel()));
                }
            }
        }
        if (videoPipeArr2 != null) {
            for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) videoPipeArr2); i2++) {
                VideoPipe[] videoPipeArr4 = videoPipeArr2[i2];
                if (videoPipeArr4 != null) {
                    for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) videoPipeArr4); i3++) {
                        ImageScalePipe imageScalePipe = (ImageScalePipe) Global.tryCast(videoPipeArr4[i3], ImageScalePipe.class);
                        if (imageScalePipe != null) {
                            double scale = videoEncodingConfigArr[i2].getScale();
                            if (scale > 0.0d) {
                                try {
                                    imageScalePipe.setMaxScale(scale);
                                    imageScalePipe.setTargetScale(scale);
                                } catch (Exception e2) {
                                    __log.error(StringExtensions.format("Could not apply {0} target scale of {1}.", imageScalePipe.getLabel(), DoubleExtensions.toString(Double.valueOf(scale))), e2);
                                }
                            }
                        } else if (videoPipeArr4[i3] != null) {
                            __log.warn(StringExtensions.format("Could not cast {0} to ImageScalePipe. Update this class to inherit from ImageScalePipe to support image scale updates.", (Object) imageScalePipe.getLabel()));
                        }
                    }
                }
            }
        }
        if (videoEncoderArr != null) {
            for (int i4 = 0; i4 < ArrayExtensions.getLength((Object[]) videoEncoderArr); i4++) {
                VideoEncoder[] videoEncoderArr2 = videoEncoderArr[i4];
                if (videoEncoderArr2 != null) {
                    for (int i5 = 0; i5 < ArrayExtensions.getLength((Object[]) videoEncoderArr2); i5++) {
                        VideoEncoder videoEncoder = videoEncoderArr2[i5];
                        if (videoEncoder != null && !((VideoFormat) videoEncoder.getOutputFormat()).getIsFixedBitrate() && (bitrate = videoEncodingConfigArr[i4].getBitrate()) > 0) {
                            try {
                                videoEncoder.setMaxBitrate(bitrate);
                                videoEncoder.setTargetBitrate(bitrate);
                            } catch (Exception e3) {
                                __log.error(StringExtensions.format("Could not apply {0} target bitrate of {1}kbps.", videoEncoder.getLabel(), IntegerExtensions.toString(Integer.valueOf(bitrate))), e3);
                            }
                        }
                    }
                }
            }
        }
        if (videoPipeArr3 != null) {
            for (int i6 = 0; i6 < ArrayExtensions.getLength((Object[]) videoPipeArr3); i6++) {
                VideoPipe[][] videoPipeArr5 = videoPipeArr3[i6];
                if (videoPipeArr5 != null) {
                    for (VideoPipe[] videoPipeArr6 : videoPipeArr5) {
                        if (videoPipeArr6 != null) {
                            for (int i7 = 0; i7 < ArrayExtensions.getLength((Object[]) videoPipeArr6); i7++) {
                                VideoPipe videoPipe = videoPipeArr6[i7];
                                if (videoPipe != null) {
                                    videoPipe.setDeactivated(videoEncodingConfigArr[i6].getDeactivated());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void attachAudioRecorderSourceEvents(final IAudioOutput iAudioOutput) {
        if (iAudioOutput != null) {
            iAudioOutput.addOnDisabledChange(new IAction0() {
                public void invoke() {
                    if (RtcLocalMedia.this.getIsRecordingAudio() && !iAudioOutput.getDisabled()) {
                        Iterator it = RtcLocalMedia.this.__audioRecorders.iterator();
                        while (it.hasNext()) {
                            AudioSink audioSink = (AudioSink) it.next();
                            audioSink.setDeactivated(!RtcLocalMedia.audioSourceHasSink(iAudioOutput, audioSink));
                        }
                    }
                }
            });
        }
    }

    private void attachVideoRecorderSourceEvents(final IVideoOutput iVideoOutput) {
        if (iVideoOutput != null) {
            iVideoOutput.addOnDisabledChange(new IAction0() {
                public void invoke() {
                    if (RtcLocalMedia.this.getIsRecordingVideo() && !iVideoOutput.getDisabled()) {
                        Iterator it = RtcLocalMedia.this.__videoRecorders.iterator();
                        while (it.hasNext()) {
                            VideoSink videoSink = (VideoSink) it.next();
                            videoSink.setDeactivated(!RtcLocalMedia.videoSourceHasSink(iVideoOutput, videoSink));
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static boolean audioSourceHasSink(IAudioOutput iAudioOutput, IAudioInput iAudioInput) {
        for (IAudioInput equals : (IAudioInput[]) iAudioOutput.getOutputs()) {
            if (Global.equals(equals, iAudioInput)) {
                return true;
            }
        }
        return false;
    }

    private boolean constrainVideo(final FrameRatePipe frameRatePipe, final ImageScalePipe imageScalePipe, final VideoEncoder videoEncoder) {
        if (frameRatePipe == null || imageScalePipe == null) {
            return false;
        }
        videoEncoder.addOnBitrateChange(new IAction0() {
            public void invoke() {
                VideoSource videoSource;
                int bitrate;
                Size max;
                if (RtcLocalMedia.this.getAutomaticVideoDegradation() && (videoSource = RtcLocalMedia.this.getVideoSource()) != null && (bitrate = videoEncoder.getBitrate()) > 0 && (max = ConstraintUtility.max(videoSource.getMaxOutputSize(), videoSource.getTargetOutputSize())) != null && max.getWidth() > 0 && max.getHeight() > 0) {
                    double max2 = ConstraintUtility.max(frameRatePipe.getMaxOutputFrameRate(), frameRatePipe.getTargetOutputFrameRate());
                    if (max2 == -1.0d) {
                        max2 = ConstraintUtility.max(videoSource.getMaxOutputFrameRate(), videoSource.getTargetOutputFrameRate());
                    }
                    if (max2 == -1.0d) {
                        max2 = 30.0d;
                    }
                    VideoEncodingConfig encodingConfig = VideoUtility.getEncodingConfig(VideoUtility.processDegradationPreference(RtcLocalMedia.this.getVideoDegradationPreference(), videoSource.getVideoType()), ((double) VideoUtility.getPixelCount(bitrate, max2, RtcLocalMedia.this.getVideoBitsPerPixel())) / ((double) (max.getWidth() * max.getHeight())), max2);
                    if (encodingConfig.getFrameRate() > 0.0d) {
                        frameRatePipe.setTargetFrameRate(MathAssistant.min(encodingConfig.getFrameRate(), max2));
                    }
                    if (encodingConfig.getScale() > 0.0d) {
                        imageScalePipe.setTargetScale(MathAssistant.min(encodingConfig.getScale(), 1.0d));
                    }
                }
            }
        });
        return true;
    }

    private void createAudioBranch(final AudioPipe audioPipe, final AudioEncoder audioEncoder, final AudioPipe audioPipe2, ArrayList<AudioTrack> arrayList, boolean z) {
        AudioSink audioRecorder;
        AudioTrack audioTrack = (AudioTrack) new AudioTrack((IAudioElement) audioPipe).next(audioEncoder);
        audioPipe.addOnProcessFrame(new IAction1<AudioFrame>() {
            public void invoke(AudioFrame audioFrame) {
                if (!Global.equals(RtcLocalMedia.this.getActiveAudioConverter(), audioPipe)) {
                    RtcLocalMedia.this.setActiveAudioConverter(audioPipe);
                    IAction1 access$1200 = RtcLocalMedia.this._onActiveAudioConverterChange;
                    if (access$1200 != null) {
                        access$1200.invoke(audioPipe);
                    }
                }
            }
        });
        audioEncoder.addOnProcessFrame(new IAction1<AudioFrame>() {
            public void invoke(AudioFrame audioFrame) {
                if (!Global.equals(RtcLocalMedia.this.getActiveAudioEncoder(), audioEncoder)) {
                    RtcLocalMedia.this.setActiveAudioEncoder(audioEncoder);
                    IAction1 access$1400 = RtcLocalMedia.this._onActiveAudioEncoderChange;
                    if (access$1400 != null) {
                        access$1400.invoke(audioEncoder);
                    }
                }
            }
        });
        ArrayList arrayList2 = new ArrayList();
        if (z && (audioRecorder = getAudioRecorder((AudioFormat) audioEncoder.getOutputFormat())) != null) {
            arrayList2.add(new AudioTrack((IAudioElement) audioRecorder));
        }
        AudioTrack audioTrack2 = new AudioTrack((IAudioElement) audioPipe2);
        audioPipe2.addOnProcessFrame(new IAction1<AudioFrame>() {
            public void invoke(AudioFrame audioFrame) {
                if (!Global.equals(RtcLocalMedia.this.getActiveAudioPacketizer(), audioPipe2)) {
                    RtcLocalMedia.this.setActiveAudioPacketizer(audioPipe2);
                    IAction1 access$1600 = RtcLocalMedia.this._onActiveAudioPacketizerChange;
                    if (access$1600 != null) {
                        access$1600.invoke(audioPipe2);
                    }
                }
            }
        });
        arrayList2.add(audioTrack2);
        audioTrack.next((TTrack[]) (MediaTrack[]) arrayList2.toArray(new AudioTrack[0]));
        arrayList.add(audioTrack);
    }

    private AudioTrack[] createAudioTracks(AudioPipe[] audioPipeArr, AudioEncoder[] audioEncoderArr, AudioPipe[] audioPipeArr2) {
        if (audioPipeArr == null || ArrayExtensions.getLength((Object[]) audioPipeArr) == 0) {
            throw new RuntimeException(new Exception("Can't create local audio track. No converters."));
        } else if (audioEncoderArr == null || ArrayExtensions.getLength((Object[]) audioEncoderArr) == 0) {
            throw new RuntimeException(new Exception("Can't create local audio track. No encoders."));
        } else if (audioPipeArr2 == null || ArrayExtensions.getLength((Object[]) audioPipeArr2) == 0) {
            throw new RuntimeException(new Exception("Can't create local audio track. No packetizers."));
        } else if (ArrayExtensions.getLength((Object[]) audioPipeArr) != ArrayExtensions.getLength((Object[]) audioEncoderArr)) {
            throw new RuntimeException(new Exception("Can't create local audio track. Converter count must match encoder count."));
        } else if (ArrayExtensions.getLength((Object[]) audioEncoderArr) == ArrayExtensions.getLength((Object[]) audioPipeArr2)) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < ArrayExtensions.getLength((Object[]) audioEncoderArr)) {
                createAudioBranch(audioPipeArr[i], audioEncoderArr[i], audioPipeArr2[i], arrayList, i == 0);
                i++;
            }
            return (AudioTrack[]) arrayList.toArray(new AudioTrack[0]);
        } else {
            throw new RuntimeException(new Exception("Can't create local audio track. Encoder count must match packetizer count."));
        }
    }

    /* access modifiers changed from: protected */
    public VideoPipe createFrameRateController(VideoFormat videoFormat) {
        return new FrameRateController(videoFormat);
    }

    /* access modifiers changed from: protected */
    public AudioEncoder createG722Encoder(AudioConfig audioConfig) {
        return new Encoder(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioPipe createG722Packetizer(AudioConfig audioConfig) {
        return new Packetizer(audioConfig);
    }

    /* access modifiers changed from: protected */
    public VideoPipe createH264Packetizer() {
        return new fm.liveswitch.h264.Packetizer();
    }

    /* access modifiers changed from: protected */
    public AudioPipe createOpusPacketizer(AudioConfig audioConfig) {
        return new fm.liveswitch.opus.Packetizer(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioEncoder createPcmaEncoder(AudioConfig audioConfig) {
        return new fm.liveswitch.pcma.Encoder(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioPipe createPcmaPacketizer(AudioConfig audioConfig) {
        return new fm.liveswitch.pcma.Packetizer(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioEncoder createPcmuEncoder(AudioConfig audioConfig) {
        return new fm.liveswitch.pcmu.Encoder(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioPipe createPcmuPacketizer(AudioConfig audioConfig) {
        return new fm.liveswitch.pcmu.Packetizer(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioPipe createSoundConverter(AudioConfig audioConfig) {
        return new SoundConverter(audioConfig);
    }

    private VideoTrack createVideoBranch(final VideoPipe videoPipe, VideoPipe[] videoPipeArr, VideoEncoder[] videoEncoderArr, VideoPipe[][] videoPipeArr2, boolean z) {
        videoPipe.addOnProcessFrame(new IAction1<VideoFrame>() {
            public void invoke(VideoFrame videoFrame) {
                if (!Global.equals(RtcLocalMedia.this.getActiveVideoController(), videoPipe)) {
                    RtcLocalMedia.this.setActiveVideoController(videoPipe);
                    IAction1 access$1800 = RtcLocalMedia.this._onActiveVideoControllerChange;
                    if (access$1800 != null) {
                        access$1800.invoke(videoPipe);
                    }
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < ArrayExtensions.getLength((Object[]) videoPipeArr)) {
            VideoEncoder videoEncoder = videoEncoderArr[i];
            VideoPipe videoPipe2 = videoPipeArr[i];
            constrainVideo((FrameRatePipe) Global.tryCast(videoPipe, FrameRatePipe.class), (ImageScalePipe) Global.tryCast(videoPipe2, ImageScalePipe.class), videoEncoder);
            arrayList.add(createVideoBranch(videoPipe2, videoEncoder, videoPipeArr2[i], z && i == 0));
            i++;
        }
        return (VideoTrack) new VideoTrack((IVideoElement) videoPipe).next((TTrack[]) (MediaTrack[]) arrayList.toArray(new VideoTrack[0]));
    }

    private VideoTrack createVideoBranch(final VideoPipe videoPipe, final VideoEncoder videoEncoder, VideoPipe[] videoPipeArr, boolean z) {
        VideoSink videoRecorder;
        videoPipe.addOnProcessFrame(new IAction1<VideoFrame>() {
            public void invoke(VideoFrame videoFrame) {
                if (!Global.equals(RtcLocalMedia.this.getActiveVideoConverter(), videoPipe)) {
                    RtcLocalMedia.this.setActiveVideoConverter(videoPipe);
                    IAction1 access$2000 = RtcLocalMedia.this._onActiveVideoConverterChange;
                    if (access$2000 != null) {
                        access$2000.invoke(videoPipe);
                    }
                }
            }
        });
        videoEncoder.addOnProcessFrame(new IAction1<VideoFrame>() {
            public void invoke(VideoFrame videoFrame) {
                if (!Global.equals(RtcLocalMedia.this.getActiveVideoEncoder(), videoEncoder)) {
                    RtcLocalMedia.this.setActiveVideoEncoder(videoEncoder);
                    IAction1 access$2200 = RtcLocalMedia.this._onActiveVideoEncoderChange;
                    if (access$2200 != null) {
                        access$2200.invoke(videoEncoder);
                    }
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        if (z && (videoRecorder = getVideoRecorder((VideoFormat) videoEncoder.getOutputFormat())) != null) {
            arrayList.add(new VideoTrack((IVideoElement) videoRecorder));
        }
        for (VideoPipe createVideoBranch : videoPipeArr) {
            arrayList.add(createVideoBranch(createVideoBranch));
        }
        return (VideoTrack) ((VideoTrack) new VideoTrack((IVideoElement) videoPipe).next(videoEncoder)).next((TTrack[]) (MediaTrack[]) arrayList.toArray(new VideoTrack[0]));
    }

    private VideoTrack createVideoBranch(final VideoPipe videoPipe) {
        videoPipe.addOnProcessFrame(new IAction1<VideoFrame>() {
            public void invoke(VideoFrame videoFrame) {
                if (!Global.equals(RtcLocalMedia.this.getActiveVideoPacketizer(), videoPipe)) {
                    RtcLocalMedia.this.setActiveVideoPacketizer(videoPipe);
                    IAction1 access$2400 = RtcLocalMedia.this._onActiveVideoPacketizerChange;
                    if (access$2400 != null) {
                        access$2400.invoke(videoPipe);
                    }
                }
            }
        });
        return new VideoTrack((IVideoElement) videoPipe);
    }

    private VideoTrack[] createVideoTracks(VideoPipe[] videoPipeArr, VideoPipe[][] videoPipeArr2, VideoEncoder[][] videoEncoderArr, VideoPipe[][][] videoPipeArr3) {
        if (videoPipeArr == null || ArrayExtensions.getLength((Object[]) videoPipeArr) == 0) {
            throw new RuntimeException(new Exception("Can't create local video track. No converters."));
        } else if (videoPipeArr2 == null || ArrayExtensions.getLength((Object[]) videoPipeArr2) == 0) {
            throw new RuntimeException(new Exception("Can't create local video track. No converters."));
        } else if (videoEncoderArr == null || ArrayExtensions.getLength((Object[]) videoEncoderArr) == 0) {
            throw new RuntimeException(new Exception("Can't create local video track. No encoders."));
        } else if (videoPipeArr3 == null || ArrayExtensions.getLength((Object[]) videoPipeArr3) == 0) {
            throw new RuntimeException(new Exception("Can't create local video track. No packetizers."));
        } else if (ArrayExtensions.getLength((Object[]) videoPipeArr) != ArrayExtensions.getLength((Object[]) videoPipeArr2)) {
            throw new RuntimeException(new Exception("Can't create local video track. Controller count must match converter array count."));
        } else if (ArrayExtensions.getLength((Object[]) videoPipeArr) != ArrayExtensions.getLength((Object[]) videoEncoderArr)) {
            throw new RuntimeException(new Exception("Can't create local video track. Controller count must match encoder array count."));
        } else if (ArrayExtensions.getLength((Object[]) videoPipeArr) == ArrayExtensions.getLength((Object[]) videoPipeArr3)) {
            int i = 0;
            while (i < ArrayExtensions.getLength((Object[]) videoPipeArr)) {
                VideoPipe[] videoPipeArr4 = videoPipeArr2[i];
                VideoEncoder[] videoEncoderArr2 = videoEncoderArr[i];
                VideoPipe[][] videoPipeArr5 = videoPipeArr3[i];
                if (videoPipeArr4 == null || ArrayExtensions.getLength((Object[]) videoPipeArr4) == 0) {
                    throw new RuntimeException(new Exception("Can't create local video track. No converters."));
                } else if (videoEncoderArr2 == null || ArrayExtensions.getLength((Object[]) videoEncoderArr2) == 0) {
                    throw new RuntimeException(new Exception("Can't create local video track. No encoders."));
                } else if (videoPipeArr5 == null || ArrayExtensions.getLength((Object[]) videoPipeArr5) == 0) {
                    throw new RuntimeException(new Exception("Can't create local video track. No packetizers."));
                } else if (ArrayExtensions.getLength((Object[]) videoPipeArr4) != ArrayExtensions.getLength((Object[]) videoEncoderArr2)) {
                    throw new RuntimeException(new Exception("Can't create local video track. Converter count must match encoder count."));
                } else if (ArrayExtensions.getLength((Object[]) videoPipeArr4) == ArrayExtensions.getLength((Object[]) videoPipeArr5)) {
                    for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) videoPipeArr4); i2++) {
                        VideoPipe[] videoPipeArr6 = videoPipeArr5[i2];
                        if (videoPipeArr6 == null || ArrayExtensions.getLength((Object[]) videoPipeArr6) == 0) {
                            throw new RuntimeException(new Exception("Can't create local video track. No packetizers."));
                        }
                    }
                    i++;
                } else {
                    throw new RuntimeException(new Exception("Can't create local video track. Converter count must match packetizer array count."));
                }
            }
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            while (i3 < ArrayExtensions.getLength((Object[]) videoPipeArr)) {
                arrayList.add(createVideoBranch(videoPipeArr[i3], videoPipeArr2[i3], videoEncoderArr[i3], videoPipeArr3[i3], i3 == 0));
                i3++;
            }
            return (VideoTrack[]) arrayList.toArray(new VideoTrack[0]);
        } else {
            throw new RuntimeException(new Exception("Can't create local video track. Controller count must match packetizer array collection count."));
        }
    }

    /* access modifiers changed from: protected */
    public VideoPipe createVp8Packetizer() {
        return new fm.liveswitch.vp8.Packetizer();
    }

    /* access modifiers changed from: protected */
    public VideoPipe createVp9Packetizer() {
        return new fm.liveswitch.vp9.Packetizer();
    }

    public void destroy() {
        if (!getAecDisabled()) {
            getAecContext().destroy();
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

    /* access modifiers changed from: protected */
    public AudioEncodingConfig[] doGetAudioEncodings() {
        return this.__audioEncodings;
    }

    /* access modifiers changed from: protected */
    public VideoEncodingConfig[] doGetVideoEncodings() {
        return this.__videoEncodings;
    }

    /* access modifiers changed from: protected */
    public void doSetAudioEncodings(AudioEncodingConfig[] audioEncodingConfigArr) {
        this.__audioEncodings = audioEncodingConfigArr;
        applyAudioEncodings(audioEncodingConfigArr);
    }

    /* access modifiers changed from: protected */
    public void doSetVideoEncodings(VideoEncodingConfig[] videoEncodingConfigArr) {
        this.__videoEncodings = videoEncodingConfigArr;
        applyVideoEncodings(videoEncodingConfigArr);
    }

    public AudioPipe getActiveAudioConverter() {
        return this._activeAudioConverter;
    }

    public AudioEncoder getActiveAudioEncoder() {
        return this._activeAudioEncoder;
    }

    public AudioPipe getActiveAudioPacketizer() {
        return this._activeAudioPacketizer;
    }

    public VideoPipe getActiveVideoController() {
        return this._activeVideoController;
    }

    public VideoPipe getActiveVideoConverter() {
        return this._activeVideoConverter;
    }

    public VideoEncoder getActiveVideoEncoder() {
        return this._activeVideoEncoder;
    }

    public VideoPipe getActiveVideoPacketizer() {
        return this._activeVideoPacketizer;
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

    public IAudioOutput[] getAudioOutputs() {
        if (super.getAudioTrack() == null) {
            return null;
        }
        return (IAudioOutput[]) ((AudioTrack) super.getAudioTrack()).getOutputs();
    }

    private AudioSink getAudioRecorder(AudioFormat audioFormat) {
        AudioSink createAudioRecorder = createAudioRecorder(audioFormat);
        if (createAudioRecorder != null) {
            createAudioRecorder.setDeactivated(true);
            this.__audioRecorders.add(createAudioRecorder);
        }
        return createAudioRecorder;
    }

    public boolean getAutomaticVideoDegradation() {
        return this._automaticVideoDegradation;
    }

    public AudioPipe getG722Converter() {
        return this._g722Converter;
    }

    public boolean getG722Disabled() {
        return this._g722Disabled;
    }

    public AudioEncoder getG722Encoder() {
        return this._g722Encoder;
    }

    public AudioPipe getG722Packetizer() {
        return this._g722Packetizer;
    }

    public VideoPipe getH264Controller() {
        return (VideoPipe) Utility.firstOrDefault((T[]) getH264Controllers());
    }

    public VideoPipe[] getH264Controllers() {
        return this._h264Controllers;
    }

    public VideoPipe getH264Converter() {
        return (VideoPipe) Utility.firstOrDefault((T[]) getH264Converters());
    }

    public VideoPipe[] getH264Converters() {
        return (VideoPipe[]) Utility.firstOrDefault((T[]) getH264ConvertersArray());
    }

    public VideoPipe[][] getH264ConvertersArray() {
        return this._h264ConvertersArray;
    }

    public boolean getH264Disabled() {
        return this._h264Disabled;
    }

    public VideoEncoder getH264Encoder() {
        return (VideoEncoder) Utility.firstOrDefault((T[]) getH264Encoders());
    }

    public VideoEncoder[] getH264Encoders() {
        return (VideoEncoder[]) Utility.firstOrDefault((T[]) getH264EncodersArray());
    }

    public VideoEncoder[][] getH264EncodersArray() {
        return this._h264EncodersArray;
    }

    public VideoPipe getH264Packetizer() {
        return (VideoPipe) Utility.firstOrDefault((T[]) getH264Packetizers());
    }

    public VideoPipe[] getH264Packetizers() {
        return (VideoPipe[]) Utility.firstOrDefault((T[]) getH264PacketizersArray());
    }

    public VideoPipe[][] getH264PacketizersArray() {
        return (VideoPipe[][]) Utility.firstOrDefault((T[]) getH264PacketizersArrayArray());
    }

    public VideoPipe[][][] getH264PacketizersArrayArray() {
        return this._h264PacketizersArrayArray;
    }

    public AudioPipe getOpusConverter() {
        return (AudioPipe) Utility.firstOrDefault((T[]) getOpusConverters());
    }

    public AudioPipe[] getOpusConverters() {
        return this._opusConverters;
    }

    public boolean getOpusDisabled() {
        return this._opusDisabled;
    }

    public AudioEncoder getOpusEncoder() {
        return (AudioEncoder) Utility.firstOrDefault((T[]) getOpusEncoders());
    }

    public AudioEncoder[] getOpusEncoders() {
        return this._opusEncoders;
    }

    public AudioPipe getOpusPacketizer() {
        return (AudioPipe) Utility.firstOrDefault((T[]) getOpusPacketizers());
    }

    public AudioPipe[] getOpusPacketizers() {
        return this._opusPacketizers;
    }

    public AudioPipe getPcmaConverter() {
        return this._pcmaConverter;
    }

    public boolean getPcmaDisabled() {
        return this._pcmaDisabled;
    }

    public AudioEncoder getPcmaEncoder() {
        return this._pcmaEncoder;
    }

    public AudioPipe getPcmaPacketizer() {
        return this._pcmaPacketizer;
    }

    public AudioPipe getPcmuConverter() {
        return this._pcmuConverter;
    }

    public boolean getPcmuDisabled() {
        return this._pcmuDisabled;
    }

    public AudioEncoder getPcmuEncoder() {
        return this._pcmuEncoder;
    }

    public AudioPipe getPcmuPacketizer() {
        return this._pcmuPacketizer;
    }

    public double getVideoBitsPerPixel() {
        return this.__videoBitsPerPixel;
    }

    public VideoDegradationPreference getVideoDegradationPreference() {
        return this.__videoDegradationPreference;
    }

    public boolean getVideoDisabled() {
        return this._videoDisabled;
    }

    public IVideoOutput[] getVideoOutputs() {
        if (super.getVideoTrack() == null) {
            return null;
        }
        return (IVideoOutput[]) ((VideoTrack) super.getVideoTrack()).getOutputs();
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

    public VideoPipe getVp8Controller() {
        return (VideoPipe) Utility.firstOrDefault((T[]) getVp8Controllers());
    }

    public VideoPipe[] getVp8Controllers() {
        return this._vp8Controllers;
    }

    public VideoPipe getVp8Converter() {
        return (VideoPipe) Utility.firstOrDefault((T[]) getVp8Converters());
    }

    public VideoPipe[] getVp8Converters() {
        return this._vp8Converters;
    }

    public boolean getVp8Disabled() {
        return this._vp8Disabled;
    }

    public VideoEncoder getVp8Encoder() {
        return (VideoEncoder) Utility.firstOrDefault((T[]) getVp8Encoders());
    }

    public VideoEncoder[] getVp8Encoders() {
        return this._vp8Encoders;
    }

    public VideoPipe getVp8Packetizer() {
        return (VideoPipe) Utility.firstOrDefault((T[]) getVp8Packetizers());
    }

    public VideoPipe[] getVp8Packetizers() {
        return this._vp8Packetizers;
    }

    public VideoPipe getVp9Controller() {
        return (VideoPipe) Utility.firstOrDefault((T[]) getVp9Controllers());
    }

    public VideoPipe[] getVp9Controllers() {
        return this._vp9Controllers;
    }

    public VideoPipe getVp9Converter() {
        return (VideoPipe) Utility.firstOrDefault((T[]) getVp9Converters());
    }

    public VideoPipe[] getVp9Converters() {
        return this._vp9Converters;
    }

    public boolean getVp9Disabled() {
        return this._vp9Disabled;
    }

    public VideoEncoder getVp9Encoder() {
        return (VideoEncoder) Utility.firstOrDefault((T[]) getVp9Encoders());
    }

    public VideoEncoder[] getVp9Encoders() {
        return this._vp9Encoders;
    }

    public VideoPipe getVp9Packetizer() {
        return (VideoPipe) Utility.firstOrDefault((T[]) getVp9Packetizers());
    }

    public VideoPipe[] getVp9Packetizers() {
        return this._vp9Packetizers;
    }

    public boolean initialize() {
        return initialize((RtcAudioTrackConfig) null, (RtcVideoTrackConfig) null);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r1v150, types: [fm.liveswitch.MediaTrack] */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        setOpusDisabled(r19.getOpusDisabled());
        setG722Disabled(r19.getG722Disabled());
        setPcmuDisabled(r19.getPcmuDisabled());
        setPcmaDisabled(r19.getPcmaDisabled());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        if (r20 == null) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        setVp8Disabled(r20.getVp8Disabled());
        setH264Disabled(r20.getH264Disabled());
        setVp9Disabled(r20.getVp9Disabled());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r10 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004a, code lost:
        if (getAudioDisabled() != false) goto L_0x0322;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
        r1 = createAudioSource(r7.__opusConfig);
        super.lockAudioEncodings();
        r2 = super.getAudioEncodings();
        r3 = r2.length;
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005b, code lost:
        if (r4 >= r3) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005d, code lost:
        r2[r4].setSynchronizationSource(fm.liveswitch.Utility.generateSynchronizationSource());
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
        r2 = new fm.liveswitch.AudioTrack((fm.liveswitch.IAudioElement) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0072, code lost:
        if (getAecDisabled() != false) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0074, code lost:
        r2 = ((fm.liveswitch.AudioTrack) r2.next(createSoundConverter(getAecContext().getProcessor().getConfig()))).next(getAecContext().getProcessor());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0099, code lost:
        r1 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a2, code lost:
        if (getOpusDisabled() != false) goto L_0x018c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a4, code lost:
        setOpusConverters(new fm.liveswitch.AudioPipe[fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) super.getAudioEncodings())]);
        setOpusEncoders(new fm.liveswitch.AudioEncoder[fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) super.getAudioEncodings())]);
        setOpusPacketizers(new fm.liveswitch.AudioPipe[fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) super.getAudioEncodings())]);
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00d4, code lost:
        if (r3 >= fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) super.getAudioEncodings())) goto L_0x011f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        getOpusEncoders()[r3] = createOpusEncoder(r7.__opusConfig);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x097d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x097e, code lost:
        r1 = r0;
        __log.error("Error initializing local media.", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x098b, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00e3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        __log.error("Could not create local Opus encoder.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0127, code lost:
        if (fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) getOpusEncoders()) <= 0) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x012f, code lost:
        if (getOpusEncoders()[0] == null) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0131, code lost:
        fm.liveswitch.ArrayListExtensions.addRange(r1, (T[]) createAudioTracks(getOpusConverters(), getOpusEncoders(), getOpusPacketizers()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0144, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x014d, code lost:
        if (r3 >= fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) super.getAudioEncodings())) goto L_0x0179;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0155, code lost:
        if (getOpusEncoders()[r3] == null) goto L_0x0176;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0161, code lost:
        if (super.getAudioEncodings()[r3].getBitrate() != -1) goto L_0x0176;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0163, code lost:
        super.getAudioEncodings()[r3].setBitrate(getOpusEncoders()[r3].getTargetOutputBitrate());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0176, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0179, code lost:
        applyAudioEncodings(super.getAudioEncodings(), getOpusConverters(), getOpusEncoders(), getOpusPacketizers());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0190, code lost:
        if (getG722Disabled() != false) goto L_0x0207;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        setG722Encoder(createG722Encoder(r7.__g722Config));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x019c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        __log.error("Could not create local G.722 encoder.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        if (r19 == null) goto L_0x002e;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x038b A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x03c0 A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x041b A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0552 A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x055a A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x05c4 A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x078c A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x07e7 A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x091f A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x0936 A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x044b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x0689 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0817 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0226 A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0288 A[SYNTHETIC, Splitter:B:78:0x0288] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x02a1 A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0303 A[Catch:{ Exception -> 0x097d }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0315 A[Catch:{ Exception -> 0x097d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean initialize(fm.liveswitch.RtcAudioTrackConfig r19, fm.liveswitch.RtcVideoTrackConfig r20) {
        /*
            r18 = this;
            r7 = r18
            java.lang.Object r1 = r7.__initializeLock
            monitor-enter(r1)
            boolean r2 = r7.__initialized     // Catch:{ all -> 0x098c }
            r8 = 0
            if (r2 == 0) goto L_0x000c
            monitor-exit(r1)     // Catch:{ all -> 0x098c }
            return r8
        L_0x000c:
            r9 = 1
            r7.__initialized = r9     // Catch:{ all -> 0x098c }
            monitor-exit(r1)     // Catch:{ all -> 0x098c }
            if (r19 == 0) goto L_0x002e
            boolean r1 = r19.getOpusDisabled()
            r7.setOpusDisabled(r1)
            boolean r1 = r19.getG722Disabled()
            r7.setG722Disabled(r1)
            boolean r1 = r19.getPcmuDisabled()
            r7.setPcmuDisabled(r1)
            boolean r1 = r19.getPcmaDisabled()
            r7.setPcmaDisabled(r1)
        L_0x002e:
            if (r20 == 0) goto L_0x0045
            boolean r1 = r20.getVp8Disabled()
            r7.setVp8Disabled(r1)
            boolean r1 = r20.getH264Disabled()
            r7.setH264Disabled(r1)
            boolean r1 = r20.getVp9Disabled()
            r7.setVp9Disabled(r1)
        L_0x0045:
            boolean r1 = r18.getAudioDisabled()     // Catch:{ Exception -> 0x097d }
            r10 = -1
            if (r1 != 0) goto L_0x0322
            fm.liveswitch.AudioConfig r1 = r7.__opusConfig     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioSource r1 = r7.createAudioSource(r1)     // Catch:{ Exception -> 0x097d }
            super.lockAudioEncodings()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncodingConfig[] r2 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            int r3 = r2.length     // Catch:{ Exception -> 0x097d }
            r4 = 0
        L_0x005b:
            if (r4 >= r3) goto L_0x0069
            r5 = r2[r4]     // Catch:{ Exception -> 0x097d }
            long r11 = fm.liveswitch.Utility.generateSynchronizationSource()     // Catch:{ Exception -> 0x097d }
            r5.setSynchronizationSource(r11)     // Catch:{ Exception -> 0x097d }
            int r4 = r4 + 1
            goto L_0x005b
        L_0x0069:
            fm.liveswitch.AudioTrack r2 = new fm.liveswitch.AudioTrack     // Catch:{ Exception -> 0x097d }
            r2.<init>((fm.liveswitch.IAudioElement) r1)     // Catch:{ Exception -> 0x097d }
            boolean r1 = r18.getAecDisabled()     // Catch:{ Exception -> 0x097d }
            if (r1 != 0) goto L_0x0099
            fm.liveswitch.AecContext r1 = r18.getAecContext()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AecPipe r1 = r1.getProcessor()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioConfig r1 = r1.getConfig()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r1 = r7.createSoundConverter(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaTrack r1 = r2.next(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioTrack r1 = (fm.liveswitch.AudioTrack) r1     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AecContext r2 = r18.getAecContext()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AecPipe r2 = r2.getProcessor()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaTrack r1 = r1.next(r2)     // Catch:{ Exception -> 0x097d }
            r2 = r1
            fm.liveswitch.AudioTrack r2 = (fm.liveswitch.AudioTrack) r2     // Catch:{ Exception -> 0x097d }
        L_0x0099:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Exception -> 0x097d }
            r1.<init>()     // Catch:{ Exception -> 0x097d }
            boolean r3 = r18.getOpusDisabled()     // Catch:{ Exception -> 0x097d }
            if (r3 != 0) goto L_0x018c
            fm.liveswitch.AudioEncodingConfig[] r3 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r3 = new fm.liveswitch.AudioPipe[r3]     // Catch:{ Exception -> 0x097d }
            r7.setOpusConverters(r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncodingConfig[] r3 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r3 = new fm.liveswitch.AudioEncoder[r3]     // Catch:{ Exception -> 0x097d }
            r7.setOpusEncoders(r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncodingConfig[] r3 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r3 = new fm.liveswitch.AudioPipe[r3]     // Catch:{ Exception -> 0x097d }
            r7.setOpusPacketizers(r3)     // Catch:{ Exception -> 0x097d }
            r3 = 0
        L_0x00cc:
            fm.liveswitch.AudioEncodingConfig[] r4 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r4)     // Catch:{ Exception -> 0x097d }
            if (r3 >= r4) goto L_0x011f
            fm.liveswitch.AudioEncoder[] r4 = r18.getOpusEncoders()     // Catch:{ Exception -> 0x00e3 }
            fm.liveswitch.AudioConfig r5 = r7.__opusConfig     // Catch:{ Exception -> 0x00e3 }
            fm.liveswitch.AudioEncoder r5 = r7.createOpusEncoder(r5)     // Catch:{ Exception -> 0x00e3 }
            r4[r3] = r5     // Catch:{ Exception -> 0x00e3 }
            goto L_0x00ec
        L_0x00e3:
            r0 = move-exception
            r4 = r0
            fm.liveswitch.ILog r5 = __log     // Catch:{ Exception -> 0x097d }
            java.lang.String r6 = "Could not create local Opus encoder."
            r5.error((java.lang.String) r6, (java.lang.Exception) r4)     // Catch:{ Exception -> 0x097d }
        L_0x00ec:
            fm.liveswitch.AudioEncoder[] r4 = r18.getOpusEncoders()     // Catch:{ Exception -> 0x097d }
            r4 = r4[r3]     // Catch:{ Exception -> 0x097d }
            if (r4 == 0) goto L_0x011c
            fm.liveswitch.AudioPipe[] r4 = r18.getOpusConverters()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r5 = r18.getOpusEncoders()     // Catch:{ Exception -> 0x097d }
            r5 = r5[r3]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioConfig r5 = r5.getInputConfig()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r5 = r7.createSoundConverter(r5)     // Catch:{ Exception -> 0x097d }
            r4[r3] = r5     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r4 = r18.getOpusPacketizers()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r5 = r18.getOpusEncoders()     // Catch:{ Exception -> 0x097d }
            r5 = r5[r3]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioConfig r5 = r5.getOutputConfig()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r5 = r7.createOpusPacketizer(r5)     // Catch:{ Exception -> 0x097d }
            r4[r3] = r5     // Catch:{ Exception -> 0x097d }
        L_0x011c:
            int r3 = r3 + 1
            goto L_0x00cc
        L_0x011f:
            fm.liveswitch.AudioEncoder[] r3 = r18.getOpusEncoders()     // Catch:{ Exception -> 0x097d }
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r3)     // Catch:{ Exception -> 0x097d }
            if (r3 <= 0) goto L_0x0144
            fm.liveswitch.AudioEncoder[] r3 = r18.getOpusEncoders()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r8]     // Catch:{ Exception -> 0x097d }
            if (r3 == 0) goto L_0x0144
            fm.liveswitch.AudioPipe[] r3 = r18.getOpusConverters()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r4 = r18.getOpusEncoders()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r5 = r18.getOpusPacketizers()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioTrack[] r3 = r7.createAudioTracks(r3, r4, r5)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.ArrayListExtensions.addRange(r1, (T[]) r3)     // Catch:{ Exception -> 0x097d }
        L_0x0144:
            r3 = 0
        L_0x0145:
            fm.liveswitch.AudioEncodingConfig[] r4 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r4)     // Catch:{ Exception -> 0x097d }
            if (r3 >= r4) goto L_0x0179
            fm.liveswitch.AudioEncoder[] r4 = r18.getOpusEncoders()     // Catch:{ Exception -> 0x097d }
            r4 = r4[r3]     // Catch:{ Exception -> 0x097d }
            if (r4 == 0) goto L_0x0176
            fm.liveswitch.AudioEncodingConfig[] r4 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            r4 = r4[r3]     // Catch:{ Exception -> 0x097d }
            int r4 = r4.getBitrate()     // Catch:{ Exception -> 0x097d }
            if (r4 != r10) goto L_0x0176
            fm.liveswitch.AudioEncodingConfig[] r4 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            r4 = r4[r3]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r5 = r18.getOpusEncoders()     // Catch:{ Exception -> 0x097d }
            r5 = r5[r3]     // Catch:{ Exception -> 0x097d }
            int r5 = r5.getTargetOutputBitrate()     // Catch:{ Exception -> 0x097d }
            r4.setBitrate(r5)     // Catch:{ Exception -> 0x097d }
        L_0x0176:
            int r3 = r3 + 1
            goto L_0x0145
        L_0x0179:
            fm.liveswitch.AudioEncodingConfig[] r3 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r4 = r18.getOpusConverters()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r5 = r18.getOpusEncoders()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r6 = r18.getOpusPacketizers()     // Catch:{ Exception -> 0x097d }
            r7.applyAudioEncodings(r3, r4, r5, r6)     // Catch:{ Exception -> 0x097d }
        L_0x018c:
            boolean r3 = r18.getG722Disabled()     // Catch:{ Exception -> 0x097d }
            if (r3 != 0) goto L_0x0207
            fm.liveswitch.AudioConfig r3 = r7.__g722Config     // Catch:{ Exception -> 0x019c }
            fm.liveswitch.AudioEncoder r3 = r7.createG722Encoder(r3)     // Catch:{ Exception -> 0x019c }
            r7.setG722Encoder(r3)     // Catch:{ Exception -> 0x019c }
            goto L_0x01a5
        L_0x019c:
            r0 = move-exception
            r3 = r0
            fm.liveswitch.ILog r4 = __log     // Catch:{ Exception -> 0x097d }
            java.lang.String r5 = "Could not create local G.722 encoder."
            r4.error((java.lang.String) r5, (java.lang.Exception) r3)     // Catch:{ Exception -> 0x097d }
        L_0x01a5:
            fm.liveswitch.AudioEncoder r3 = r18.getG722Encoder()     // Catch:{ Exception -> 0x097d }
            if (r3 == 0) goto L_0x01e8
            fm.liveswitch.AudioEncoder r3 = r18.getG722Encoder()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioConfig r3 = r3.getInputConfig()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r3 = r7.createSoundConverter(r3)     // Catch:{ Exception -> 0x097d }
            r7.setG722Converter(r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder r3 = r18.getG722Encoder()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioConfig r3 = r3.getOutputConfig()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r3 = r7.createG722Packetizer(r3)     // Catch:{ Exception -> 0x097d }
            r7.setG722Packetizer(r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r3 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r4 = r18.getG722Converter()     // Catch:{ Exception -> 0x097d }
            r3[r8] = r4     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r4 = new fm.liveswitch.AudioEncoder[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder r5 = r18.getG722Encoder()     // Catch:{ Exception -> 0x097d }
            r4[r8] = r5     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r5 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r6 = r18.getG722Packetizer()     // Catch:{ Exception -> 0x097d }
            r5[r8] = r6     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioTrack[] r3 = r7.createAudioTracks(r3, r4, r5)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.ArrayListExtensions.addRange(r1, (T[]) r3)     // Catch:{ Exception -> 0x097d }
        L_0x01e8:
            fm.liveswitch.AudioEncodingConfig[] r3 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r4 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r5 = r18.getG722Converter()     // Catch:{ Exception -> 0x097d }
            r4[r8] = r5     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r5 = new fm.liveswitch.AudioEncoder[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder r6 = r18.getG722Encoder()     // Catch:{ Exception -> 0x097d }
            r5[r8] = r6     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r6 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r11 = r18.getG722Packetizer()     // Catch:{ Exception -> 0x097d }
            r6[r8] = r11     // Catch:{ Exception -> 0x097d }
            r7.applyAudioEncodings(r3, r4, r5, r6)     // Catch:{ Exception -> 0x097d }
        L_0x0207:
            boolean r3 = r18.getPcmuDisabled()     // Catch:{ Exception -> 0x097d }
            if (r3 != 0) goto L_0x0282
            fm.liveswitch.AudioConfig r3 = r7.__pcmuConfig     // Catch:{ Exception -> 0x0217 }
            fm.liveswitch.AudioEncoder r3 = r7.createPcmuEncoder(r3)     // Catch:{ Exception -> 0x0217 }
            r7.setPcmuEncoder(r3)     // Catch:{ Exception -> 0x0217 }
            goto L_0x0220
        L_0x0217:
            r0 = move-exception
            r3 = r0
            fm.liveswitch.ILog r4 = __log     // Catch:{ Exception -> 0x097d }
            java.lang.String r5 = "Could not create local PCMU encoder."
            r4.error((java.lang.String) r5, (java.lang.Exception) r3)     // Catch:{ Exception -> 0x097d }
        L_0x0220:
            fm.liveswitch.AudioEncoder r3 = r18.getPcmuEncoder()     // Catch:{ Exception -> 0x097d }
            if (r3 == 0) goto L_0x0263
            fm.liveswitch.AudioEncoder r3 = r18.getPcmuEncoder()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioConfig r3 = r3.getInputConfig()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r3 = r7.createSoundConverter(r3)     // Catch:{ Exception -> 0x097d }
            r7.setPcmuConverter(r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder r3 = r18.getPcmuEncoder()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioConfig r3 = r3.getOutputConfig()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r3 = r7.createPcmuPacketizer(r3)     // Catch:{ Exception -> 0x097d }
            r7.setPcmuPacketizer(r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r3 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r4 = r18.getPcmuConverter()     // Catch:{ Exception -> 0x097d }
            r3[r8] = r4     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r4 = new fm.liveswitch.AudioEncoder[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder r5 = r18.getPcmuEncoder()     // Catch:{ Exception -> 0x097d }
            r4[r8] = r5     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r5 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r6 = r18.getPcmuPacketizer()     // Catch:{ Exception -> 0x097d }
            r5[r8] = r6     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioTrack[] r3 = r7.createAudioTracks(r3, r4, r5)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.ArrayListExtensions.addRange(r1, (T[]) r3)     // Catch:{ Exception -> 0x097d }
        L_0x0263:
            fm.liveswitch.AudioEncodingConfig[] r3 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r4 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r5 = r18.getPcmuConverter()     // Catch:{ Exception -> 0x097d }
            r4[r8] = r5     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r5 = new fm.liveswitch.AudioEncoder[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder r6 = r18.getPcmuEncoder()     // Catch:{ Exception -> 0x097d }
            r5[r8] = r6     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r6 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r11 = r18.getPcmuPacketizer()     // Catch:{ Exception -> 0x097d }
            r6[r8] = r11     // Catch:{ Exception -> 0x097d }
            r7.applyAudioEncodings(r3, r4, r5, r6)     // Catch:{ Exception -> 0x097d }
        L_0x0282:
            boolean r3 = r18.getPcmaDisabled()     // Catch:{ Exception -> 0x097d }
            if (r3 != 0) goto L_0x02fd
            fm.liveswitch.AudioConfig r3 = r7.__pcmaConfig     // Catch:{ Exception -> 0x0292 }
            fm.liveswitch.AudioEncoder r3 = r7.createPcmaEncoder(r3)     // Catch:{ Exception -> 0x0292 }
            r7.setPcmaEncoder(r3)     // Catch:{ Exception -> 0x0292 }
            goto L_0x029b
        L_0x0292:
            r0 = move-exception
            r3 = r0
            fm.liveswitch.ILog r4 = __log     // Catch:{ Exception -> 0x097d }
            java.lang.String r5 = "Could not create local PCMA encoder."
            r4.error((java.lang.String) r5, (java.lang.Exception) r3)     // Catch:{ Exception -> 0x097d }
        L_0x029b:
            fm.liveswitch.AudioEncoder r3 = r18.getPcmaEncoder()     // Catch:{ Exception -> 0x097d }
            if (r3 == 0) goto L_0x02de
            fm.liveswitch.AudioEncoder r3 = r18.getPcmaEncoder()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioConfig r3 = r3.getInputConfig()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r3 = r7.createSoundConverter(r3)     // Catch:{ Exception -> 0x097d }
            r7.setPcmaConverter(r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder r3 = r18.getPcmaEncoder()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioConfig r3 = r3.getOutputConfig()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r3 = r7.createPcmaPacketizer(r3)     // Catch:{ Exception -> 0x097d }
            r7.setPcmaPacketizer(r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r3 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r4 = r18.getPcmaConverter()     // Catch:{ Exception -> 0x097d }
            r3[r8] = r4     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r4 = new fm.liveswitch.AudioEncoder[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder r5 = r18.getPcmaEncoder()     // Catch:{ Exception -> 0x097d }
            r4[r8] = r5     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r5 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r6 = r18.getPcmaPacketizer()     // Catch:{ Exception -> 0x097d }
            r5[r8] = r6     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioTrack[] r3 = r7.createAudioTracks(r3, r4, r5)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.ArrayListExtensions.addRange(r1, (T[]) r3)     // Catch:{ Exception -> 0x097d }
        L_0x02de:
            fm.liveswitch.AudioEncodingConfig[] r3 = super.getAudioEncodings()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r4 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r5 = r18.getPcmaConverter()     // Catch:{ Exception -> 0x097d }
            r4[r8] = r5     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder[] r5 = new fm.liveswitch.AudioEncoder[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioEncoder r6 = r18.getPcmaEncoder()     // Catch:{ Exception -> 0x097d }
            r5[r8] = r6     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe[] r6 = new fm.liveswitch.AudioPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioPipe r11 = r18.getPcmaPacketizer()     // Catch:{ Exception -> 0x097d }
            r6[r8] = r11     // Catch:{ Exception -> 0x097d }
            r7.applyAudioEncodings(r3, r4, r5, r6)     // Catch:{ Exception -> 0x097d }
        L_0x02fd:
            int r3 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ Exception -> 0x097d }
            if (r3 <= 0) goto L_0x0315
            fm.liveswitch.AudioTrack[] r3 = new fm.liveswitch.AudioTrack[r8]     // Catch:{ Exception -> 0x097d }
            java.lang.Object[] r1 = r1.toArray(r3)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaTrack[] r1 = (fm.liveswitch.MediaTrack[]) r1     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaTrack r1 = r2.next((TTrack[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioTrack r1 = (fm.liveswitch.AudioTrack) r1     // Catch:{ Exception -> 0x097d }
            r7.addAudioTrack(r1)     // Catch:{ Exception -> 0x097d }
            goto L_0x0322
        L_0x0315:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x097d }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ Exception -> 0x097d }
            java.lang.String r3 = "Could not initialize local media. No audio encoders initialized. Check the logs for more detail."
            r2.<init>(r3)     // Catch:{ Exception -> 0x097d }
            r1.<init>(r2)     // Catch:{ Exception -> 0x097d }
            throw r1     // Catch:{ Exception -> 0x097d }
        L_0x0322:
            boolean r1 = r18.getVideoDisabled()     // Catch:{ Exception -> 0x097d }
            if (r1 != 0) goto L_0x0943
            fm.liveswitch.VideoSource r11 = r18.createVideoSource()     // Catch:{ Exception -> 0x097d }
            if (r11 != 0) goto L_0x0332
            super.lockVideoEncodings()     // Catch:{ Exception -> 0x097d }
            goto L_0x0355
        L_0x0332:
            fm.liveswitch.EncodingInfo r1 = r11.getMaxOutputEncoding()     // Catch:{ Exception -> 0x097d }
            if (r1 != 0) goto L_0x0340
            fm.liveswitch.VideoType r1 = r11.getVideoType()     // Catch:{ Exception -> 0x097d }
            super.lockVideoEncodings(r1)     // Catch:{ Exception -> 0x097d }
            goto L_0x0355
        L_0x0340:
            fm.liveswitch.VideoType r2 = r11.getVideoType()     // Catch:{ Exception -> 0x097d }
            int r3 = r1.getScaledWidth()     // Catch:{ Exception -> 0x097d }
            int r4 = r1.getScaledHeight()     // Catch:{ Exception -> 0x097d }
            double r5 = r1.getFrameRate()     // Catch:{ Exception -> 0x097d }
            r1 = r18
            super.lockVideoEncodings(r2, r3, r4, r5)     // Catch:{ Exception -> 0x097d }
        L_0x0355:
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r2 = r1.length     // Catch:{ Exception -> 0x097d }
            r3 = 0
        L_0x035b:
            if (r3 >= r2) goto L_0x0369
            r4 = r1[r3]     // Catch:{ Exception -> 0x097d }
            long r5 = fm.liveswitch.Utility.generateSynchronizationSource()     // Catch:{ Exception -> 0x097d }
            r4.setSynchronizationSource(r5)     // Catch:{ Exception -> 0x097d }
            int r3 = r3 + 1
            goto L_0x035b
        L_0x0369:
            fm.liveswitch.VideoTrack r12 = new fm.liveswitch.VideoTrack     // Catch:{ Exception -> 0x097d }
            r12.<init>((fm.liveswitch.IVideoElement) r11)     // Catch:{ Exception -> 0x097d }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ Exception -> 0x097d }
            r13.<init>()     // Catch:{ Exception -> 0x097d }
            r1 = 0
            fm.liveswitch.ViewSink r2 = r18.createViewSink()     // Catch:{ Exception -> 0x037c }
            r7.setViewSink(r2)     // Catch:{ Exception -> 0x037c }
            goto L_0x0385
        L_0x037c:
            r0 = move-exception
            r2 = r0
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x097d }
            java.lang.String r4 = "Could not create local view sink."
            r3.error((java.lang.String) r4, (java.lang.Exception) r2)     // Catch:{ Exception -> 0x097d }
        L_0x0385:
            fm.liveswitch.ViewSink r2 = r18.getViewSink()     // Catch:{ Exception -> 0x097d }
            if (r2 == 0) goto L_0x03b7
            fm.liveswitch.ViewSink r1 = r18.getViewSink()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaFormat r1 = r1.getInputFormat()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoFormat r1 = (fm.liveswitch.VideoFormat) r1     // Catch:{ Exception -> 0x097d }
            if (r1 != 0) goto L_0x03a4
            if (r11 == 0) goto L_0x03a0
            fm.liveswitch.MediaFormat r1 = r11.getOutputFormat()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoFormat r1 = (fm.liveswitch.VideoFormat) r1     // Catch:{ Exception -> 0x097d }
            goto L_0x03a4
        L_0x03a0:
            fm.liveswitch.VideoFormat r1 = fm.liveswitch.VideoFormat.getI420()     // Catch:{ Exception -> 0x097d }
        L_0x03a4:
            fm.liveswitch.VideoTrack r2 = new fm.liveswitch.VideoTrack     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe r1 = r7.createImageConverter(r1)     // Catch:{ Exception -> 0x097d }
            r2.<init>((fm.liveswitch.IVideoElement) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.ViewSink r1 = r18.getViewSink()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaTrack r1 = r2.next(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoTrack r1 = (fm.liveswitch.VideoTrack) r1     // Catch:{ Exception -> 0x097d }
        L_0x03b7:
            r14 = r1
            boolean r1 = r18.getVp8Disabled()     // Catch:{ Exception -> 0x097d }
            r15 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            if (r1 != 0) goto L_0x0552
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r1 = new fm.liveswitch.VideoPipe[r1]     // Catch:{ Exception -> 0x097d }
            r7.setVp8Controllers(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r1 = new fm.liveswitch.VideoPipe[r1]     // Catch:{ Exception -> 0x097d }
            r7.setVp8Converters(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r1 = new fm.liveswitch.VideoEncoder[r1]     // Catch:{ Exception -> 0x097d }
            r7.setVp8Encoders(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r1 = new fm.liveswitch.VideoPipe[r1]     // Catch:{ Exception -> 0x097d }
            r7.setVp8Packetizers(r1)     // Catch:{ Exception -> 0x097d }
            r1 = 0
        L_0x03f5:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r2)     // Catch:{ Exception -> 0x097d }
            if (r1 >= r2) goto L_0x044e
            fm.liveswitch.VideoEncoder[] r2 = r18.getVp8Encoders()     // Catch:{ Exception -> 0x040a }
            fm.liveswitch.VideoEncoder r3 = r18.createVp8Encoder()     // Catch:{ Exception -> 0x040a }
            r2[r1] = r3     // Catch:{ Exception -> 0x040a }
            goto L_0x0413
        L_0x040a:
            r0 = move-exception
            r2 = r0
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x097d }
            java.lang.String r4 = "Could not create local VP8 encoder."
            r3.error((java.lang.String) r4, (java.lang.Exception) r2)     // Catch:{ Exception -> 0x097d }
        L_0x0413:
            fm.liveswitch.VideoEncoder[] r2 = r18.getVp8Encoders()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            if (r2 == 0) goto L_0x044b
            fm.liveswitch.VideoPipe[] r2 = r18.getVp8Controllers()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaFormat r3 = r11.getOutputFormat()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoFormat r3 = (fm.liveswitch.VideoFormat) r3     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe r3 = r7.createFrameRateController(r3)     // Catch:{ Exception -> 0x097d }
            r2[r1] = r3     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r2 = r18.getVp8Converters()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r3 = r18.getVp8Encoders()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaFormat r3 = r3.getInputFormat()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoFormat r3 = (fm.liveswitch.VideoFormat) r3     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe r3 = r7.createImageConverter(r3)     // Catch:{ Exception -> 0x097d }
            r2[r1] = r3     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r2 = r18.getVp8Packetizers()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe r3 = r18.createVp8Packetizer()     // Catch:{ Exception -> 0x097d }
            r2[r1] = r3     // Catch:{ Exception -> 0x097d }
        L_0x044b:
            int r1 = r1 + 1
            goto L_0x03f5
        L_0x044e:
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][] r4 = new fm.liveswitch.VideoPipe[r1][]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[][] r5 = new fm.liveswitch.VideoEncoder[r1][]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][][] r6 = new fm.liveswitch.VideoPipe[r1][][]     // Catch:{ Exception -> 0x097d }
            r1 = 0
        L_0x046d:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r2)     // Catch:{ Exception -> 0x097d }
            if (r1 >= r2) goto L_0x04a2
            fm.liveswitch.VideoPipe[] r2 = new fm.liveswitch.VideoPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r3 = r18.getVp8Converters()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            r2[r8] = r3     // Catch:{ Exception -> 0x097d }
            r4[r1] = r2     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r2 = new fm.liveswitch.VideoEncoder[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r3 = r18.getVp8Encoders()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            r2[r8] = r3     // Catch:{ Exception -> 0x097d }
            r5[r1] = r2     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][] r2 = new fm.liveswitch.VideoPipe[r9][]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r3 = new fm.liveswitch.VideoPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r17 = r18.getVp8Packetizers()     // Catch:{ Exception -> 0x097d }
            r17 = r17[r1]     // Catch:{ Exception -> 0x097d }
            r3[r8] = r17     // Catch:{ Exception -> 0x097d }
            r2[r8] = r3     // Catch:{ Exception -> 0x097d }
            r6[r1] = r2     // Catch:{ Exception -> 0x097d }
            int r1 = r1 + 1
            goto L_0x046d
        L_0x04a2:
            fm.liveswitch.VideoEncoder[] r1 = r18.getVp8Encoders()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            if (r1 <= 0) goto L_0x04bf
            fm.liveswitch.VideoEncoder[] r1 = r18.getVp8Encoders()     // Catch:{ Exception -> 0x097d }
            r1 = r1[r8]     // Catch:{ Exception -> 0x097d }
            if (r1 == 0) goto L_0x04bf
            fm.liveswitch.VideoPipe[] r1 = r18.getVp8Controllers()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoTrack[] r1 = r7.createVideoTracks(r1, r4, r5, r6)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.ArrayListExtensions.addRange(r13, (T[]) r1)     // Catch:{ Exception -> 0x097d }
        L_0x04bf:
            r1 = 0
        L_0x04c0:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r2)     // Catch:{ Exception -> 0x097d }
            if (r1 >= r2) goto L_0x0542
            fm.liveswitch.VideoEncoder[] r2 = r18.getVp8Encoders()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            if (r2 == 0) goto L_0x0539
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            int r2 = r2.getBitrate()     // Catch:{ Exception -> 0x097d }
            if (r2 != r10) goto L_0x04f1
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r3 = r18.getVp8Encoders()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            int r3 = r3.getTargetOutputBitrate()     // Catch:{ Exception -> 0x097d }
            r2.setBitrate(r3)     // Catch:{ Exception -> 0x097d }
        L_0x04f1:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            double r2 = r2.getFrameRate()     // Catch:{ Exception -> 0x097d }
            int r17 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r17 != 0) goto L_0x0515
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r3 = r18.getVp8Encoders()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            r20 = r11
            double r10 = r3.getTargetOutputFrameRate()     // Catch:{ Exception -> 0x097d }
            r2.setFrameRate(r10)     // Catch:{ Exception -> 0x097d }
            goto L_0x0517
        L_0x0515:
            r20 = r11
        L_0x0517:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            double r2 = r2.getScale()     // Catch:{ Exception -> 0x097d }
            int r10 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r10 != 0) goto L_0x053b
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r3 = r18.getVp8Encoders()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            double r10 = r3.getTargetOutputScale()     // Catch:{ Exception -> 0x097d }
            r2.setScale(r10)     // Catch:{ Exception -> 0x097d }
            goto L_0x053b
        L_0x0539:
            r20 = r11
        L_0x053b:
            int r1 = r1 + 1
            r11 = r20
            r10 = -1
            goto L_0x04c0
        L_0x0542:
            r20 = r11
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r3 = r18.getVp8Controllers()     // Catch:{ Exception -> 0x097d }
            r1 = r18
            r1.applyVideoEncodings(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x097d }
            goto L_0x0554
        L_0x0552:
            r20 = r11
        L_0x0554:
            boolean r1 = r18.getH264Disabled()     // Catch:{ Exception -> 0x097d }
            if (r1 != 0) goto L_0x0786
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r1 = new fm.liveswitch.VideoPipe[r1]     // Catch:{ Exception -> 0x097d }
            r7.setH264Controllers(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][] r1 = new fm.liveswitch.VideoPipe[r1][]     // Catch:{ Exception -> 0x097d }
            r7.setH264ConvertersArray(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[][] r1 = new fm.liveswitch.VideoEncoder[r1][]     // Catch:{ Exception -> 0x097d }
            r7.setH264EncodersArray(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][][] r1 = new fm.liveswitch.VideoPipe[r1][][]     // Catch:{ Exception -> 0x097d }
            r7.setH264PacketizersArrayArray(r1)     // Catch:{ Exception -> 0x097d }
            r1 = 0
        L_0x058f:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r2)     // Catch:{ Exception -> 0x097d }
            if (r1 >= r2) goto L_0x0690
            fm.liveswitch.VideoEncoder[][] r2 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x05b3 }
            fm.liveswitch.VideoEncoder[] r3 = r18.createH264Encoders()     // Catch:{ Exception -> 0x05b3 }
            if (r3 == 0) goto L_0x05a8
            fm.liveswitch.VideoEncoder[] r3 = r18.createH264Encoders()     // Catch:{ Exception -> 0x05b3 }
            goto L_0x05b0
        L_0x05a8:
            fm.liveswitch.VideoEncoder[] r3 = new fm.liveswitch.VideoEncoder[r9]     // Catch:{ Exception -> 0x05b3 }
            fm.liveswitch.VideoEncoder r4 = r18.createH264Encoder()     // Catch:{ Exception -> 0x05b3 }
            r3[r8] = r4     // Catch:{ Exception -> 0x05b3 }
        L_0x05b0:
            r2[r1] = r3     // Catch:{ Exception -> 0x05b3 }
            goto L_0x05bc
        L_0x05b3:
            r0 = move-exception
            r2 = r0
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x097d }
            java.lang.String r4 = "Could not create local H.264 encoder."
            r3.error((java.lang.String) r4, (java.lang.Exception) r2)     // Catch:{ Exception -> 0x097d }
        L_0x05bc:
            fm.liveswitch.VideoEncoder[][] r2 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            if (r2 == 0) goto L_0x0689
            fm.liveswitch.VideoEncoder[][] r2 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r2)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r3 = r18.getH264Controllers()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaFormat r4 = r20.getOutputFormat()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoFormat r4 = (fm.liveswitch.VideoFormat) r4     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe r4 = r7.createFrameRateController(r4)     // Catch:{ Exception -> 0x097d }
            r3[r1] = r4     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][] r3 = r18.getH264ConvertersArray()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r4 = new fm.liveswitch.VideoPipe[r2]     // Catch:{ Exception -> 0x097d }
            r3[r1] = r4     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][][] r3 = r18.getH264PacketizersArrayArray()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][] r4 = new fm.liveswitch.VideoPipe[r2][]     // Catch:{ Exception -> 0x097d }
            r3[r1] = r4     // Catch:{ Exception -> 0x097d }
            r3 = 0
        L_0x05ef:
            if (r3 >= r2) goto L_0x0689
            fm.liveswitch.VideoEncoder[][] r4 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r4 = r4[r1]     // Catch:{ Exception -> 0x097d }
            r4 = r4[r3]     // Catch:{ Exception -> 0x097d }
            if (r4 == 0) goto L_0x0682
            fm.liveswitch.VideoPipe[][] r4 = r18.getH264ConvertersArray()     // Catch:{ Exception -> 0x097d }
            r4 = r4[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[][] r5 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r5 = r5[r1]     // Catch:{ Exception -> 0x097d }
            r5 = r5[r3]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaFormat r5 = r5.getInputFormat()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoFormat r5 = (fm.liveswitch.VideoFormat) r5     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe r5 = r7.createImageConverter(r5)     // Catch:{ Exception -> 0x097d }
            r4[r3] = r5     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][][] r4 = r18.getH264PacketizersArrayArray()     // Catch:{ Exception -> 0x097d }
            r4 = r4[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r5 = r18.createH264Packetizers()     // Catch:{ Exception -> 0x097d }
            if (r5 == 0) goto L_0x0626
            fm.liveswitch.VideoPipe[] r5 = r18.createH264Packetizers()     // Catch:{ Exception -> 0x097d }
            goto L_0x062e
        L_0x0626:
            fm.liveswitch.VideoPipe[] r5 = new fm.liveswitch.VideoPipe[r9]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe r6 = r18.createH264Packetizer()     // Catch:{ Exception -> 0x097d }
            r5[r8] = r6     // Catch:{ Exception -> 0x097d }
        L_0x062e:
            r4[r3] = r5     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[][] r4 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r4 = r4[r1]     // Catch:{ Exception -> 0x097d }
            r4 = r4[r3]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaFormat r4 = r4.getOutputFormat()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoFormat r4 = (fm.liveswitch.VideoFormat) r4     // Catch:{ Exception -> 0x097d }
            if (r4 == 0) goto L_0x0682
            fm.liveswitch.VideoPipe[][][] r5 = r18.getH264PacketizersArrayArray()     // Catch:{ Exception -> 0x097d }
            r5 = r5[r1]     // Catch:{ Exception -> 0x097d }
            r5 = r5[r3]     // Catch:{ Exception -> 0x097d }
            int r6 = r5.length     // Catch:{ Exception -> 0x097d }
            r10 = 0
        L_0x064a:
            if (r10 >= r6) goto L_0x0682
            r11 = r5[r10]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaFormat r17 = r11.getInputFormat()     // Catch:{ Exception -> 0x097d }
            r9 = r17
            fm.liveswitch.VideoFormat r9 = (fm.liveswitch.VideoFormat) r9     // Catch:{ Exception -> 0x097d }
            if (r9 == 0) goto L_0x0666
            java.lang.String r15 = r4.getProfile()     // Catch:{ Exception -> 0x097d }
            r9.setProfile(r15)     // Catch:{ Exception -> 0x097d }
            java.lang.String r15 = r4.getLevel()     // Catch:{ Exception -> 0x097d }
            r9.setLevel(r15)     // Catch:{ Exception -> 0x097d }
        L_0x0666:
            fm.liveswitch.MediaFormat r9 = r11.getOutputFormat()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoFormat r9 = (fm.liveswitch.VideoFormat) r9     // Catch:{ Exception -> 0x097d }
            if (r9 == 0) goto L_0x067c
            java.lang.String r11 = r4.getProfile()     // Catch:{ Exception -> 0x097d }
            r9.setProfile(r11)     // Catch:{ Exception -> 0x097d }
            java.lang.String r11 = r4.getLevel()     // Catch:{ Exception -> 0x097d }
            r9.setLevel(r11)     // Catch:{ Exception -> 0x097d }
        L_0x067c:
            int r10 = r10 + 1
            r9 = 1
            r15 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            goto L_0x064a
        L_0x0682:
            int r3 = r3 + 1
            r9 = 1
            r15 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            goto L_0x05ef
        L_0x0689:
            int r1 = r1 + 1
            r9 = 1
            r15 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            goto L_0x058f
        L_0x0690:
            fm.liveswitch.VideoEncoder[][] r1 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            if (r1 <= 0) goto L_0x06cf
            fm.liveswitch.VideoEncoder[][] r1 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r1 = r1[r8]     // Catch:{ Exception -> 0x097d }
            if (r1 == 0) goto L_0x06cf
            fm.liveswitch.VideoEncoder[][] r1 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r1 = r1[r8]     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            if (r1 <= 0) goto L_0x06cf
            fm.liveswitch.VideoEncoder[][] r1 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r1 = r1[r8]     // Catch:{ Exception -> 0x097d }
            r1 = r1[r8]     // Catch:{ Exception -> 0x097d }
            if (r1 == 0) goto L_0x06cf
            fm.liveswitch.VideoPipe[] r1 = r18.getH264Controllers()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][] r2 = r18.getH264ConvertersArray()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[][] r3 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][][] r4 = r18.getH264PacketizersArrayArray()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoTrack[] r1 = r7.createVideoTracks(r1, r2, r3, r4)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.ArrayListExtensions.addRange(r13, (T[]) r1)     // Catch:{ Exception -> 0x097d }
        L_0x06cf:
            r1 = 0
        L_0x06d0:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r2)     // Catch:{ Exception -> 0x097d }
            if (r1 >= r2) goto L_0x076d
            fm.liveswitch.VideoEncoder[][] r2 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            if (r2 == 0) goto L_0x0769
            r2 = 0
        L_0x06e3:
            fm.liveswitch.VideoEncoder[][] r3 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r3)     // Catch:{ Exception -> 0x097d }
            if (r2 >= r3) goto L_0x0769
            fm.liveswitch.VideoEncoder[][] r3 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            r3 = r3[r2]     // Catch:{ Exception -> 0x097d }
            if (r3 == 0) goto L_0x0765
            fm.liveswitch.VideoEncodingConfig[] r3 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            int r3 = r3.getBitrate()     // Catch:{ Exception -> 0x097d }
            r4 = -1
            if (r3 != r4) goto L_0x071b
            fm.liveswitch.VideoEncodingConfig[] r3 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[][] r4 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r4 = r4[r1]     // Catch:{ Exception -> 0x097d }
            r4 = r4[r2]     // Catch:{ Exception -> 0x097d }
            int r4 = r4.getTargetOutputBitrate()     // Catch:{ Exception -> 0x097d }
            r3.setBitrate(r4)     // Catch:{ Exception -> 0x097d }
        L_0x071b:
            fm.liveswitch.VideoEncodingConfig[] r3 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            double r3 = r3.getFrameRate()     // Catch:{ Exception -> 0x097d }
            r5 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r9 != 0) goto L_0x0740
            fm.liveswitch.VideoEncodingConfig[] r3 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[][] r4 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r4 = r4[r1]     // Catch:{ Exception -> 0x097d }
            r4 = r4[r2]     // Catch:{ Exception -> 0x097d }
            double r4 = r4.getTargetOutputFrameRate()     // Catch:{ Exception -> 0x097d }
            r3.setFrameRate(r4)     // Catch:{ Exception -> 0x097d }
        L_0x0740:
            fm.liveswitch.VideoEncodingConfig[] r3 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            double r3 = r3.getScale()     // Catch:{ Exception -> 0x097d }
            r5 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r9 != 0) goto L_0x0765
            fm.liveswitch.VideoEncodingConfig[] r3 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[][] r4 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            r4 = r4[r1]     // Catch:{ Exception -> 0x097d }
            r4 = r4[r2]     // Catch:{ Exception -> 0x097d }
            double r4 = r4.getTargetOutputScale()     // Catch:{ Exception -> 0x097d }
            r3.setScale(r4)     // Catch:{ Exception -> 0x097d }
        L_0x0765:
            int r2 = r2 + 1
            goto L_0x06e3
        L_0x0769:
            int r1 = r1 + 1
            goto L_0x06d0
        L_0x076d:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r3 = r18.getH264Controllers()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][] r4 = r18.getH264ConvertersArray()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[][] r5 = r18.getH264EncodersArray()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][][] r6 = r18.getH264PacketizersArrayArray()     // Catch:{ Exception -> 0x097d }
            r1 = r18
            r1.applyVideoEncodings(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x097d }
        L_0x0786:
            boolean r1 = r18.getVp9Disabled()     // Catch:{ Exception -> 0x097d }
            if (r1 != 0) goto L_0x0919
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r1 = new fm.liveswitch.VideoPipe[r1]     // Catch:{ Exception -> 0x097d }
            r7.setVp9Controllers(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r1 = new fm.liveswitch.VideoPipe[r1]     // Catch:{ Exception -> 0x097d }
            r7.setVp9Converters(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r1 = new fm.liveswitch.VideoEncoder[r1]     // Catch:{ Exception -> 0x097d }
            r7.setVp9Encoders(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r1 = new fm.liveswitch.VideoPipe[r1]     // Catch:{ Exception -> 0x097d }
            r7.setVp9Packetizers(r1)     // Catch:{ Exception -> 0x097d }
            r1 = 0
        L_0x07c1:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r2)     // Catch:{ Exception -> 0x097d }
            if (r1 >= r2) goto L_0x081a
            fm.liveswitch.VideoEncoder[] r2 = r18.getVp9Encoders()     // Catch:{ Exception -> 0x07d6 }
            fm.liveswitch.VideoEncoder r3 = r18.createVp9Encoder()     // Catch:{ Exception -> 0x07d6 }
            r2[r1] = r3     // Catch:{ Exception -> 0x07d6 }
            goto L_0x07df
        L_0x07d6:
            r0 = move-exception
            r2 = r0
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x097d }
            java.lang.String r4 = "Could not create local VP9 encoder."
            r3.error((java.lang.String) r4, (java.lang.Exception) r2)     // Catch:{ Exception -> 0x097d }
        L_0x07df:
            fm.liveswitch.VideoEncoder[] r2 = r18.getVp9Encoders()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            if (r2 == 0) goto L_0x0817
            fm.liveswitch.VideoPipe[] r2 = r18.getVp9Controllers()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaFormat r3 = r20.getOutputFormat()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoFormat r3 = (fm.liveswitch.VideoFormat) r3     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe r3 = r7.createFrameRateController(r3)     // Catch:{ Exception -> 0x097d }
            r2[r1] = r3     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r2 = r18.getVp9Converters()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r3 = r18.getVp9Encoders()     // Catch:{ Exception -> 0x097d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaFormat r3 = r3.getInputFormat()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoFormat r3 = (fm.liveswitch.VideoFormat) r3     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe r3 = r7.createImageConverter(r3)     // Catch:{ Exception -> 0x097d }
            r2[r1] = r3     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r2 = r18.getVp9Packetizers()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe r3 = r18.createVp9Packetizer()     // Catch:{ Exception -> 0x097d }
            r2[r1] = r3     // Catch:{ Exception -> 0x097d }
        L_0x0817:
            int r1 = r1 + 1
            goto L_0x07c1
        L_0x081a:
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][] r4 = new fm.liveswitch.VideoPipe[r1][]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[][] r5 = new fm.liveswitch.VideoEncoder[r1][]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncodingConfig[] r1 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][][] r6 = new fm.liveswitch.VideoPipe[r1][][]     // Catch:{ Exception -> 0x097d }
            r1 = 0
        L_0x0839:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r2)     // Catch:{ Exception -> 0x097d }
            if (r1 >= r2) goto L_0x086f
            r2 = 1
            fm.liveswitch.VideoPipe[] r3 = new fm.liveswitch.VideoPipe[r2]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r9 = r18.getVp9Converters()     // Catch:{ Exception -> 0x097d }
            r9 = r9[r1]     // Catch:{ Exception -> 0x097d }
            r3[r8] = r9     // Catch:{ Exception -> 0x097d }
            r4[r1] = r3     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r3 = new fm.liveswitch.VideoEncoder[r2]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r9 = r18.getVp9Encoders()     // Catch:{ Exception -> 0x097d }
            r9 = r9[r1]     // Catch:{ Exception -> 0x097d }
            r3[r8] = r9     // Catch:{ Exception -> 0x097d }
            r5[r1] = r3     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[][] r3 = new fm.liveswitch.VideoPipe[r2][]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r9 = new fm.liveswitch.VideoPipe[r2]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r2 = r18.getVp9Packetizers()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            r9[r8] = r2     // Catch:{ Exception -> 0x097d }
            r3[r8] = r9     // Catch:{ Exception -> 0x097d }
            r6[r1] = r3     // Catch:{ Exception -> 0x097d }
            int r1 = r1 + 1
            goto L_0x0839
        L_0x086f:
            fm.liveswitch.VideoEncoder[] r1 = r18.getVp9Encoders()     // Catch:{ Exception -> 0x097d }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x097d }
            if (r1 <= 0) goto L_0x088c
            fm.liveswitch.VideoEncoder[] r1 = r18.getVp9Encoders()     // Catch:{ Exception -> 0x097d }
            r1 = r1[r8]     // Catch:{ Exception -> 0x097d }
            if (r1 == 0) goto L_0x088c
            fm.liveswitch.VideoPipe[] r1 = r18.getVp9Controllers()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoTrack[] r1 = r7.createVideoTracks(r1, r4, r5, r6)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.ArrayListExtensions.addRange(r13, (T[]) r1)     // Catch:{ Exception -> 0x097d }
        L_0x088c:
            r1 = 0
        L_0x088d:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r2)     // Catch:{ Exception -> 0x097d }
            if (r1 >= r2) goto L_0x090c
            fm.liveswitch.VideoEncoder[] r2 = r18.getVp9Encoders()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            if (r2 == 0) goto L_0x0906
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            int r2 = r2.getBitrate()     // Catch:{ Exception -> 0x097d }
            r3 = -1
            if (r2 != r3) goto L_0x08bf
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r9 = r18.getVp9Encoders()     // Catch:{ Exception -> 0x097d }
            r9 = r9[r1]     // Catch:{ Exception -> 0x097d }
            int r9 = r9.getTargetOutputBitrate()     // Catch:{ Exception -> 0x097d }
            r2.setBitrate(r9)     // Catch:{ Exception -> 0x097d }
        L_0x08bf:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            double r9 = r2.getFrameRate()     // Catch:{ Exception -> 0x097d }
            r15 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r2 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r2 != 0) goto L_0x08e2
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r9 = r18.getVp9Encoders()     // Catch:{ Exception -> 0x097d }
            r9 = r9[r1]     // Catch:{ Exception -> 0x097d }
            double r9 = r9.getTargetOutputFrameRate()     // Catch:{ Exception -> 0x097d }
            r2.setFrameRate(r9)     // Catch:{ Exception -> 0x097d }
        L_0x08e2:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            double r9 = r2.getScale()     // Catch:{ Exception -> 0x097d }
            r15 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r2 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r2 != 0) goto L_0x0909
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            r2 = r2[r1]     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoEncoder[] r9 = r18.getVp9Encoders()     // Catch:{ Exception -> 0x097d }
            r9 = r9[r1]     // Catch:{ Exception -> 0x097d }
            double r9 = r9.getTargetOutputScale()     // Catch:{ Exception -> 0x097d }
            r2.setScale(r9)     // Catch:{ Exception -> 0x097d }
            goto L_0x0909
        L_0x0906:
            r3 = -1
            r15 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x0909:
            int r1 = r1 + 1
            goto L_0x088d
        L_0x090c:
            fm.liveswitch.VideoEncodingConfig[] r2 = super.getVideoEncodings()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoPipe[] r3 = r18.getVp9Controllers()     // Catch:{ Exception -> 0x097d }
            r1 = r18
            r1.applyVideoEncodings(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x097d }
        L_0x0919:
            int r1 = fm.liveswitch.ArrayListExtensions.getCount(r13)     // Catch:{ Exception -> 0x097d }
            if (r1 <= 0) goto L_0x0936
            if (r14 == 0) goto L_0x0924
            fm.liveswitch.ArrayListExtensions.insert(r13, r8, r14)     // Catch:{ Exception -> 0x097d }
        L_0x0924:
            fm.liveswitch.VideoTrack[] r1 = new fm.liveswitch.VideoTrack[r8]     // Catch:{ Exception -> 0x097d }
            java.lang.Object[] r1 = r13.toArray(r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaTrack[] r1 = (fm.liveswitch.MediaTrack[]) r1     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.MediaTrack r1 = r12.next((TTrack[]) r1)     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoTrack r1 = (fm.liveswitch.VideoTrack) r1     // Catch:{ Exception -> 0x097d }
            r7.addVideoTrack(r1)     // Catch:{ Exception -> 0x097d }
            goto L_0x0943
        L_0x0936:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x097d }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ Exception -> 0x097d }
            java.lang.String r3 = "Could not initialize local media. No video encoders initialized. Check the logs for more detail."
            r2.<init>(r3)     // Catch:{ Exception -> 0x097d }
            r1.<init>(r2)     // Catch:{ Exception -> 0x097d }
            throw r1     // Catch:{ Exception -> 0x097d }
        L_0x0943:
            java.util.ArrayList<fm.liveswitch.AudioSink> r1 = r7.__audioRecorders     // Catch:{ Exception -> 0x097d }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x097d }
        L_0x0949:
            boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x097d }
            if (r2 == 0) goto L_0x095f
            java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.AudioSink r2 = (fm.liveswitch.AudioSink) r2     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.IMediaOutput r2 = r2.getInput()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.IAudioOutput r2 = (fm.liveswitch.IAudioOutput) r2     // Catch:{ Exception -> 0x097d }
            r7.attachAudioRecorderSourceEvents(r2)     // Catch:{ Exception -> 0x097d }
            goto L_0x0949
        L_0x095f:
            java.util.ArrayList<fm.liveswitch.VideoSink> r1 = r7.__videoRecorders     // Catch:{ Exception -> 0x097d }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x097d }
        L_0x0965:
            boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x097d }
            if (r2 == 0) goto L_0x097b
            java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.VideoSink r2 = (fm.liveswitch.VideoSink) r2     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.IMediaOutput r2 = r2.getInput()     // Catch:{ Exception -> 0x097d }
            fm.liveswitch.IVideoOutput r2 = (fm.liveswitch.IVideoOutput) r2     // Catch:{ Exception -> 0x097d }
            r7.attachVideoRecorderSourceEvents(r2)     // Catch:{ Exception -> 0x097d }
            goto L_0x0965
        L_0x097b:
            r1 = 1
            return r1
        L_0x097d:
            r0 = move-exception
            r1 = r0
            fm.liveswitch.ILog r2 = __log
            java.lang.String r3 = "Error initializing local media."
            r2.error((java.lang.String) r3, (java.lang.Exception) r1)
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            r2.<init>(r1)
            throw r2
        L_0x098c:
            r0 = move-exception
            r2 = r0
            monitor-exit(r1)     // Catch:{ all -> 0x098c }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtcLocalMedia.initialize(fm.liveswitch.RtcAudioTrackConfig, fm.liveswitch.RtcVideoTrackConfig):boolean");
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

    public void removeOnActiveAudioEncoderChange(IAction1<AudioEncoder> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveAudioEncoderChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveAudioEncoderChange.remove(iAction1);
        if (this.__onActiveAudioEncoderChange.size() == 0) {
            this._onActiveAudioEncoderChange = null;
        }
    }

    public void removeOnActiveAudioPacketizerChange(IAction1<AudioPipe> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveAudioPacketizerChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveAudioPacketizerChange.remove(iAction1);
        if (this.__onActiveAudioPacketizerChange.size() == 0) {
            this._onActiveAudioPacketizerChange = null;
        }
    }

    public void removeOnActiveVideoControllerChange(IAction1<VideoPipe> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveVideoControllerChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveVideoControllerChange.remove(iAction1);
        if (this.__onActiveVideoControllerChange.size() == 0) {
            this._onActiveVideoControllerChange = null;
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

    public void removeOnActiveVideoEncoderChange(IAction1<VideoEncoder> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveVideoEncoderChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveVideoEncoderChange.remove(iAction1);
        if (this.__onActiveVideoEncoderChange.size() == 0) {
            this._onActiveVideoEncoderChange = null;
        }
    }

    public void removeOnActiveVideoPacketizerChange(IAction1<VideoPipe> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onActiveVideoPacketizerChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onActiveVideoPacketizerChange.remove(iAction1);
        if (this.__onActiveVideoPacketizerChange.size() == 0) {
            this._onActiveVideoPacketizerChange = null;
        }
    }

    public RtcLocalMedia() {
        this(false, false);
    }

    public RtcLocalMedia(boolean z, boolean z2) {
        this(z, z2, (AecContext) null);
    }

    public RtcLocalMedia(boolean z, boolean z2, AecContext aecContext) {
        this.__onActiveAudioConverterChange = new ArrayList();
        this.__onActiveAudioEncoderChange = new ArrayList();
        this.__onActiveAudioPacketizerChange = new ArrayList();
        this.__onActiveVideoControllerChange = new ArrayList();
        this.__onActiveVideoConverterChange = new ArrayList();
        this.__onActiveVideoEncoderChange = new ArrayList();
        this.__onActiveVideoPacketizerChange = new ArrayList();
        this._onActiveAudioConverterChange = null;
        this._onActiveAudioEncoderChange = null;
        this._onActiveAudioPacketizerChange = null;
        this._onActiveVideoControllerChange = null;
        this._onActiveVideoConverterChange = null;
        this._onActiveVideoEncoderChange = null;
        this._onActiveVideoPacketizerChange = null;
        this.__opusConfig = new AudioConfig(48000, 2);
        this.__g722Config = new AudioConfig(16000, 1);
        this.__pcmuConfig = new AudioConfig(8000, 1);
        this.__pcmaConfig = new AudioConfig(8000, 1);
        this.__audioRecorders = new ArrayList<>();
        this.__videoRecorders = new ArrayList<>();
        this.__audioEncodings = new AudioEncodingConfig[0];
        this.__videoEncodings = new VideoEncodingConfig[0];
        this.__videoDegradationPreference = VideoDegradationPreference.Automatic;
        this.__videoBitsPerPixel = LocalMediaBase.getDefaultVideoBitsPerPixel();
        this.__initializeLock = new Object();
        this.__initialized = false;
        this._audioRecordingLock = new Object();
        this._videoRecordingLock = new Object();
        if (!(aecContext == null || aecContext.getProcessor() == null || Global.equals(aecContext.getProcessor().getState(), MediaPipeState.Initialized))) {
            __log.warn("Local media received a reference to a destroyed AEC context. AEC will be disabled.");
            aecContext = null;
        }
        setAudioDisabled(z);
        setVideoDisabled(z2);
        setAecContext(aecContext);
        setAutomaticVideoDegradation(false);
    }

    /* access modifiers changed from: private */
    public void setActiveAudioConverter(AudioPipe audioPipe) {
        this._activeAudioConverter = audioPipe;
    }

    /* access modifiers changed from: private */
    public void setActiveAudioEncoder(AudioEncoder audioEncoder) {
        this._activeAudioEncoder = audioEncoder;
    }

    /* access modifiers changed from: private */
    public void setActiveAudioPacketizer(AudioPipe audioPipe) {
        this._activeAudioPacketizer = audioPipe;
    }

    /* access modifiers changed from: private */
    public void setActiveVideoController(VideoPipe videoPipe) {
        this._activeVideoController = videoPipe;
    }

    /* access modifiers changed from: private */
    public void setActiveVideoConverter(VideoPipe videoPipe) {
        this._activeVideoConverter = videoPipe;
    }

    /* access modifiers changed from: private */
    public void setActiveVideoEncoder(VideoEncoder videoEncoder) {
        this._activeVideoEncoder = videoEncoder;
    }

    /* access modifiers changed from: private */
    public void setActiveVideoPacketizer(VideoPipe videoPipe) {
        this._activeVideoPacketizer = videoPipe;
    }

    private void setAecContext(AecContext aecContext) {
        this._aecContext = aecContext;
    }

    private void setAudioDisabled(boolean z) {
        this._audioDisabled = z;
    }

    public void setAutomaticVideoDegradation(boolean z) {
        this._automaticVideoDegradation = z;
    }

    private void setG722Converter(AudioPipe audioPipe) {
        this._g722Converter = audioPipe;
    }

    private void setG722Disabled(boolean z) {
        this._g722Disabled = z;
    }

    private void setG722Encoder(AudioEncoder audioEncoder) {
        this._g722Encoder = audioEncoder;
    }

    private void setG722Packetizer(AudioPipe audioPipe) {
        this._g722Packetizer = audioPipe;
    }

    private void setH264Controllers(VideoPipe[] videoPipeArr) {
        this._h264Controllers = videoPipeArr;
    }

    private void setH264ConvertersArray(VideoPipe[][] videoPipeArr) {
        this._h264ConvertersArray = videoPipeArr;
    }

    private void setH264Disabled(boolean z) {
        this._h264Disabled = z;
    }

    private void setH264EncodersArray(VideoEncoder[][] videoEncoderArr) {
        this._h264EncodersArray = videoEncoderArr;
    }

    private void setH264PacketizersArrayArray(VideoPipe[][][] videoPipeArr) {
        this._h264PacketizersArrayArray = videoPipeArr;
    }

    private void setOpusConverters(AudioPipe[] audioPipeArr) {
        this._opusConverters = audioPipeArr;
    }

    private void setOpusDisabled(boolean z) {
        this._opusDisabled = z;
    }

    private void setOpusEncoders(AudioEncoder[] audioEncoderArr) {
        this._opusEncoders = audioEncoderArr;
    }

    private void setOpusPacketizers(AudioPipe[] audioPipeArr) {
        this._opusPacketizers = audioPipeArr;
    }

    private void setPcmaConverter(AudioPipe audioPipe) {
        this._pcmaConverter = audioPipe;
    }

    private void setPcmaDisabled(boolean z) {
        this._pcmaDisabled = z;
    }

    private void setPcmaEncoder(AudioEncoder audioEncoder) {
        this._pcmaEncoder = audioEncoder;
    }

    private void setPcmaPacketizer(AudioPipe audioPipe) {
        this._pcmaPacketizer = audioPipe;
    }

    private void setPcmuConverter(AudioPipe audioPipe) {
        this._pcmuConverter = audioPipe;
    }

    private void setPcmuDisabled(boolean z) {
        this._pcmuDisabled = z;
    }

    private void setPcmuEncoder(AudioEncoder audioEncoder) {
        this._pcmuEncoder = audioEncoder;
    }

    private void setPcmuPacketizer(AudioPipe audioPipe) {
        this._pcmuPacketizer = audioPipe;
    }

    public void setVideoBitsPerPixel(double d) {
        if (d >= 0.0d) {
            this.__videoBitsPerPixel = d;
            return;
        }
        throw new RuntimeException(new Exception("Video bits-per-pixel must be greater than or equal to zero."));
    }

    public void setVideoDegradationPreference(VideoDegradationPreference videoDegradationPreference) {
        this.__videoDegradationPreference = videoDegradationPreference;
    }

    private void setVideoDisabled(boolean z) {
        this._videoDisabled = z;
    }

    private void setViewSink(ViewSink<TView> viewSink) {
        this._viewSink = viewSink;
    }

    private void setVp8Controllers(VideoPipe[] videoPipeArr) {
        this._vp8Controllers = videoPipeArr;
    }

    private void setVp8Converters(VideoPipe[] videoPipeArr) {
        this._vp8Converters = videoPipeArr;
    }

    private void setVp8Disabled(boolean z) {
        this._vp8Disabled = z;
    }

    private void setVp8Encoders(VideoEncoder[] videoEncoderArr) {
        this._vp8Encoders = videoEncoderArr;
    }

    private void setVp8Packetizers(VideoPipe[] videoPipeArr) {
        this._vp8Packetizers = videoPipeArr;
    }

    private void setVp9Controllers(VideoPipe[] videoPipeArr) {
        this._vp9Controllers = videoPipeArr;
    }

    private void setVp9Converters(VideoPipe[] videoPipeArr) {
        this._vp9Converters = videoPipeArr;
    }

    private void setVp9Disabled(boolean z) {
        this._vp9Disabled = z;
    }

    private void setVp9Encoders(VideoEncoder[] videoEncoderArr) {
        this._vp9Encoders = videoEncoderArr;
    }

    private void setVp9Packetizers(VideoPipe[] videoPipeArr) {
        this._vp9Packetizers = videoPipeArr;
    }

    public boolean toggleAudioRecording() {
        boolean isRecordingAudio;
        boolean z;
        synchronized (this._audioRecordingLock) {
            boolean z2 = true;
            super.setIsRecordingAudio(!super.getIsRecordingAudio());
            if (getViewSink() != null) {
                ViewSink viewSink = getViewSink();
                if (!super.getIsRecordingAudio()) {
                    if (!super.getIsRecordingVideo()) {
                        z = false;
                        viewSink.setIsRecording(z);
                    }
                }
                z = true;
                viewSink.setIsRecording(z);
            }
            if (super.getAudioTrack() == null || ArrayExtensions.getLength((Object[]) ((AudioTrack) super.getAudioTrack()).getOutputs()) <= 0 || ((IAudioOutput[]) ((AudioTrack) super.getAudioTrack()).getOutputs())[0].getOutput() == null) {
                if (ArrayListExtensions.getCount(this.__audioRecorders) > 0) {
                    AudioSink audioSink = (AudioSink) ArrayListExtensions.getItem(this.__audioRecorders).get(0);
                    if (super.getIsRecordingAudio()) {
                        z2 = false;
                    }
                    audioSink.setDeactivated(z2);
                }
            } else if (super.getIsRecordingAudio()) {
                Iterator<AudioSink> it = this.__audioRecorders.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AudioSink next = it.next();
                    IAudioOutput iAudioOutput = (IAudioOutput) next.getInput();
                    if (iAudioOutput != null && !iAudioOutput.getDisabled()) {
                        next.setDeactivated(false);
                        break;
                    }
                }
            } else {
                Iterator<AudioSink> it2 = this.__audioRecorders.iterator();
                while (it2.hasNext()) {
                    it2.next().setDeactivated(true);
                }
            }
            isRecordingAudio = super.getIsRecordingAudio();
        }
        return isRecordingAudio;
    }

    public boolean toggleVideoRecording() {
        boolean isRecordingVideo;
        boolean z;
        synchronized (this._videoRecordingLock) {
            boolean z2 = true;
            super.setIsRecordingVideo(!super.getIsRecordingVideo());
            if (getViewSink() != null) {
                ViewSink viewSink = getViewSink();
                if (!super.getIsRecordingAudio()) {
                    if (!super.getIsRecordingVideo()) {
                        z = false;
                        viewSink.setIsRecording(z);
                    }
                }
                z = true;
                viewSink.setIsRecording(z);
            }
            if (super.getVideoTrack() == null || ArrayExtensions.getLength((Object[]) ((VideoTrack) super.getVideoTrack()).getOutputs()) <= 0 || ((IVideoOutput[]) ((VideoTrack) super.getVideoTrack()).getOutputs())[0].getOutput() == null) {
                if (ArrayListExtensions.getCount(this.__videoRecorders) > 0) {
                    VideoSink videoSink = (VideoSink) ArrayListExtensions.getItem(this.__videoRecorders).get(0);
                    if (super.getIsRecordingVideo()) {
                        z2 = false;
                    }
                    videoSink.setDeactivated(z2);
                }
            } else if (super.getIsRecordingVideo()) {
                Iterator<VideoSink> it = this.__videoRecorders.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    VideoSink next = it.next();
                    IVideoOutput iVideoOutput = (IVideoOutput) next.getInput();
                    if (iVideoOutput != null && !iVideoOutput.getDisabled()) {
                        next.setDeactivated(false);
                        break;
                    }
                }
            } else {
                Iterator<VideoSink> it2 = this.__videoRecorders.iterator();
                while (it2.hasNext()) {
                    it2.next().setDeactivated(true);
                }
            }
            isRecordingVideo = super.getIsRecordingVideo();
        }
        return isRecordingVideo;
    }

    /* access modifiers changed from: private */
    public static boolean videoSourceHasSink(IVideoOutput iVideoOutput, IVideoInput iVideoInput) {
        for (IVideoInput equals : (IVideoInput[]) iVideoOutput.getOutputs()) {
            if (Global.equals(equals, iVideoInput)) {
                return true;
            }
        }
        return false;
    }
}
