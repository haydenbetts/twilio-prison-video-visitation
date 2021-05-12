package fm.liveswitch;

import fm.liveswitch.h264.ProfileLevelId;
import fm.liveswitch.sdp.FormatParametersAttribute;
import fm.liveswitch.sdp.rtp.MapAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import tvi.webrtc.VideoCodecInfo;

public class VideoStream extends MediaStream<IVideoOutput, IVideoOutputCollection, IVideoInput, IVideoInputCollection, IVideoElement, VideoSource, VideoSink, VideoPipe, VideoTrack, VideoBranch, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat, VideoFormatCollection> implements IVideoStream, IMediaStream, IStream, IVideoInput, IMediaInput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IInput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IVideoOutput, IMediaOutput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IOutput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IVideoElement, IMediaElement, IElement {
    private static ILog __log = Log.getLogger(VideoStream.class);
    /* access modifiers changed from: private */
    public List<IAction1<long[]>> __onDiscardKeyFrameRequest;
    private VideoType __videoType;
    private IAction1<long[]> _onDiscardKeyFrameRequest;
    private volatile boolean _readyToSend;

    public double getMaxInputFrameRate() {
        return -1.0d;
    }

    public double getMaxInputScale() {
        return -1.0d;
    }

    public Size getMaxInputSize() {
        return null;
    }

    public double getMaxOutputFrameRate() {
        return -1.0d;
    }

    public double getMaxOutputScale() {
        return -1.0d;
    }

    public Size getMaxOutputSize() {
        return null;
    }

    public double getMinInputFrameRate() {
        return -1.0d;
    }

    public double getMinInputScale() {
        return -1.0d;
    }

    public Size getMinInputSize() {
        return null;
    }

    public double getMinOutputFrameRate() {
        return -1.0d;
    }

    public double getMinOutputScale() {
        return -1.0d;
    }

    public Size getMinOutputSize() {
        return null;
    }

    public double getTargetOutputFrameRate() {
        return -1.0d;
    }

    public double getTargetOutputScale() {
        return -1.0d;
    }

    public Size getTargetOutputSize() {
        return null;
    }

    public void addOnDiscardKeyFrameRequest(IAction1<long[]> iAction1) {
        if (iAction1 != null) {
            if (this._onDiscardKeyFrameRequest == null) {
                this._onDiscardKeyFrameRequest = new IAction1<long[]>() {
                    public void invoke(long[] jArr) {
                        Iterator it = new ArrayList(VideoStream.this.__onDiscardKeyFrameRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(jArr);
                        }
                    }
                };
            }
            this.__onDiscardKeyFrameRequest.add(iAction1);
        }
    }

    /* access modifiers changed from: protected */
    public VideoFormat createFormat(MapAttribute mapAttribute, FormatParametersAttribute formatParametersAttribute) {
        VideoFormat createFormat = createFormat(mapAttribute.getFormatName(), mapAttribute.getClockRate(), mapAttribute.getFormatParameters(), mapAttribute.getPayloadType());
        if (createFormat.getIsH264()) {
            if (formatParametersAttribute != null) {
                Holder holder = new Holder(null);
                boolean tryGetFormatSpecificParameter = formatParametersAttribute.tryGetFormatSpecificParameter(VideoCodecInfo.H264_FMTP_PACKETIZATION_MODE, holder);
                String str = (String) holder.getValue();
                if (tryGetFormatSpecificParameter) {
                    createFormat.setPacketizationMode(str);
                } else {
                    createFormat.setPacketizationMode("0");
                }
                Holder holder2 = new Holder(null);
                boolean tryGetFormatSpecificParameter2 = formatParametersAttribute.tryGetFormatSpecificParameter(VideoCodecInfo.H264_FMTP_PROFILE_LEVEL_ID, holder2);
                String str2 = (String) holder2.getValue();
                if (!tryGetFormatSpecificParameter2) {
                    createFormat.setProfile(ProfileLevelId.getBaselineLevel10().getProfile());
                    createFormat.setLevel(ProfileLevelId.getBaselineLevel10().getLevel());
                } else if (StringExtensions.getLength(str2) == 6) {
                    createFormat.setProfile(StringExtensions.substring(str2, 0, 4));
                    createFormat.setLevel(StringExtensions.substring(str2, 4, 2));
                } else {
                    __log.warn(StringExtensions.format("Invalid H.264 profile-level-id: {0}", (Object) str2));
                }
                Holder holder3 = new Holder(null);
                boolean tryGetFormatSpecificParameter3 = formatParametersAttribute.tryGetFormatSpecificParameter(VideoCodecInfo.H264_FMTP_LEVEL_ASYMMETRY_ALLOWED, holder3);
                String str3 = (String) holder3.getValue();
                if (tryGetFormatSpecificParameter3) {
                    createFormat.setLevelIsStrict(Global.equals(str3, "0"));
                    return createFormat;
                }
                createFormat.setLevelIsStrict(true);
                return createFormat;
            }
            createFormat.setPacketizationMode("0");
            createFormat.setProfile(ProfileLevelId.getBaselineLevel10().getProfile());
            createFormat.setLevel(ProfileLevelId.getBaselineLevel10().getLevel());
            createFormat.setLevelIsStrict(true);
        }
        return createFormat;
    }

    /* access modifiers changed from: protected */
    public VideoFormat createFormat(String str, int i, String str2, int i2) {
        VideoFormat videoFormat = new VideoFormat(str, i);
        videoFormat.setRegisteredPayloadType(i2);
        videoFormat.setIsPacketized(true);
        for (IVideoOutput outputFormat : (IVideoOutput[]) super.getInputs()) {
            VideoFormat videoFormat2 = (VideoFormat) outputFormat.getOutputFormat();
            if (videoFormat2.isEquivalent(videoFormat)) {
                videoFormat.setIsInjected(videoFormat2.getIsInjected());
            }
        }
        for (IVideoInput inputFormat : (IVideoInput[]) super.getOutputs()) {
            VideoFormat videoFormat3 = (VideoFormat) inputFormat.getInputFormat();
            if (videoFormat3.isEquivalent(videoFormat)) {
                videoFormat.setIsInjected(videoFormat3.getIsInjected());
            }
        }
        return videoFormat;
    }

    /* access modifiers changed from: protected */
    public IVideoInputCollection createInputCollection(IVideoOutput iVideoOutput) {
        return new IVideoInputCollection(iVideoOutput);
    }

    /* access modifiers changed from: protected */
    public VideoFormatCollection createMediaFormatCollection() {
        return new VideoFormatCollection();
    }

    /* access modifiers changed from: protected */
    public IVideoOutputCollection createOutputCollection(IVideoInput iVideoInput) {
        return new IVideoOutputCollection(iVideoInput);
    }

    /* access modifiers changed from: protected */
    public VideoFormat createRedFormat() {
        VideoFormat videoFormat = new VideoFormat(MediaFormat.getRedName());
        videoFormat.setIsPacketized(true);
        return videoFormat;
    }

    /* access modifiers changed from: protected */
    public VideoFormat createUlpFecFormat() {
        VideoFormat videoFormat = new VideoFormat(MediaFormat.getUlpFecName());
        videoFormat.setIsPacketized(true);
        return videoFormat;
    }

    /* access modifiers changed from: protected */
    public VideoFormat[] formatArrayFromList(ArrayList<VideoFormat> arrayList) {
        return (VideoFormat[]) arrayList.toArray(new VideoFormat[0]);
    }

    public boolean getH264Disabled() {
        return super.getCodecDisabled(VideoFormat.getH264Name());
    }

    /* access modifiers changed from: protected */
    public boolean getInputSourceMuted(IVideoOutput iVideoOutput) {
        VideoSource videoSource = (VideoSource) Global.tryCast(iVideoOutput, VideoSource.class);
        if (videoSource != null) {
            return videoSource.getOutputMuted();
        }
        VideoPipe videoPipe = (VideoPipe) Global.tryCast(iVideoOutput, VideoPipe.class);
        return videoPipe != null && super.getInputSourceMuted((TIOutput[]) videoPipe.getInputs());
    }

    public EncodingInfo getMaxInputEncoding() {
        EncodingInfo maxInputEncoding = super.getMaxInputEncoding();
        if (maxInputEncoding != null) {
            maxInputEncoding.setScale(getMaxInputScale());
            maxInputEncoding.setFrameRate(getMaxInputFrameRate());
            maxInputEncoding.setSize(getMaxInputSize());
        }
        return maxInputEncoding;
    }

    public int getMaxInputHeight() {
        return ConstraintUtility.getHeight(getMaxInputSize());
    }

    public int getMaxInputWidth() {
        return ConstraintUtility.getWidth(getMaxInputSize());
    }

    public EncodingInfo getMaxOutputEncoding() {
        EncodingInfo maxOutputEncoding = super.getMaxOutputEncoding();
        if (maxOutputEncoding != null) {
            maxOutputEncoding.setScale(getMaxOutputScale());
            maxOutputEncoding.setFrameRate(getMaxOutputFrameRate());
            maxOutputEncoding.setSize(getMaxOutputSize());
        }
        return maxOutputEncoding;
    }

    public int getMaxOutputHeight() {
        return ConstraintUtility.getHeight(getMaxOutputSize());
    }

    public int getMaxOutputWidth() {
        return ConstraintUtility.getWidth(getMaxOutputSize());
    }

    public EncodingInfo getMinInputEncoding() {
        EncodingInfo minInputEncoding = super.getMinInputEncoding();
        if (minInputEncoding != null) {
            minInputEncoding.setScale(getMinInputScale());
            minInputEncoding.setFrameRate(getMinInputFrameRate());
            minInputEncoding.setSize(getMinInputSize());
        }
        return minInputEncoding;
    }

    public int getMinInputHeight() {
        return ConstraintUtility.getHeight(getMinInputSize());
    }

    public int getMinInputWidth() {
        return ConstraintUtility.getWidth(getMinInputSize());
    }

    public EncodingInfo getMinOutputEncoding() {
        EncodingInfo minOutputEncoding = super.getMinOutputEncoding();
        if (minOutputEncoding != null) {
            minOutputEncoding.setScale(getMinOutputScale());
            minOutputEncoding.setFrameRate(getMinOutputFrameRate());
            minOutputEncoding.setSize(getMinOutputSize());
        }
        return minOutputEncoding;
    }

    public int getMinOutputHeight() {
        return ConstraintUtility.getHeight(getMinOutputSize());
    }

    public int getMinOutputWidth() {
        return ConstraintUtility.getWidth(getMinOutputSize());
    }

    /* access modifiers changed from: protected */
    public boolean getOutputSinkMuted(IVideoInput iVideoInput) {
        VideoSink videoSink = (VideoSink) Global.tryCast(iVideoInput, VideoSink.class);
        if (videoSink != null) {
            return videoSink.getInputMuted();
        }
        VideoPipe videoPipe = (VideoPipe) Global.tryCast(iVideoInput, VideoPipe.class);
        return videoPipe != null && super.getOutputSinkMuted((TIInput[]) videoPipe.getOutputs());
    }

    public boolean getOverConstrainedFrameRate() {
        return getOverConstrainedInputFrameRate() || getOverConstrainedOutputFrameRate();
    }

    public boolean getOverConstrainedInput() {
        return super.getOverConstrainedInput() || getOverConstrainedInputScale() || getOverConstrainedInputFrameRate() || getOverConstrainedInputSize();
    }

    public boolean getOverConstrainedInputFrameRate() {
        return ConstraintUtility.overConstrained(getMinInputFrameRate(), getMaxInputFrameRate());
    }

    public boolean getOverConstrainedInputHeight() {
        return ConstraintUtility.overConstrained(getMinInputHeight(), getMaxInputHeight());
    }

    public boolean getOverConstrainedInputScale() {
        return ConstraintUtility.overConstrained(getMinInputScale(), getMaxInputScale());
    }

    public boolean getOverConstrainedInputSize() {
        return ConstraintUtility.overConstrained(getMinInputSize(), getMaxInputSize());
    }

    public boolean getOverConstrainedInputWidth() {
        return ConstraintUtility.overConstrained(getMinInputWidth(), getMaxInputWidth());
    }

    public boolean getOverConstrainedOutput() {
        return super.getOverConstrainedOutput() || getOverConstrainedOutputScale() || getOverConstrainedOutputFrameRate() || getOverConstrainedOutputSize();
    }

    public boolean getOverConstrainedOutputFrameRate() {
        return ConstraintUtility.overConstrained(getMinOutputFrameRate(), getMaxOutputFrameRate());
    }

    public boolean getOverConstrainedOutputHeight() {
        return ConstraintUtility.overConstrained(getMinOutputHeight(), getMaxOutputHeight());
    }

    public boolean getOverConstrainedOutputScale() {
        return ConstraintUtility.overConstrained(getMinOutputScale(), getMaxOutputScale());
    }

    public boolean getOverConstrainedOutputSize() {
        return ConstraintUtility.overConstrained(getMinOutputSize(), getMaxOutputSize());
    }

    public boolean getOverConstrainedOutputWidth() {
        return ConstraintUtility.overConstrained(getMinOutputWidth(), getMaxOutputWidth());
    }

    public boolean getOverConstrainedScale() {
        return getOverConstrainedInputScale() || getOverConstrainedOutputScale();
    }

    public boolean getOverConstrainedSize() {
        return getOverConstrainedInputSize() || getOverConstrainedOutputSize();
    }

    public EncodingInfo getTargetOutputEncoding() {
        EncodingInfo targetOutputEncoding = super.getTargetOutputEncoding();
        if (targetOutputEncoding != null) {
            targetOutputEncoding.setScale(getTargetOutputScale());
            targetOutputEncoding.setFrameRate(getTargetOutputFrameRate());
            targetOutputEncoding.setSize(getTargetOutputSize());
        }
        return targetOutputEncoding;
    }

    public int getTargetOutputHeight() {
        return ConstraintUtility.getHeight(getTargetOutputSize());
    }

    public int getTargetOutputWidth() {
        return ConstraintUtility.getWidth(getTargetOutputSize());
    }

    public VideoType getVideoType() {
        return this.__videoType;
    }

    public boolean getVp8Disabled() {
        return super.getCodecDisabled(VideoFormat.getVp8Name());
    }

    public boolean getVp9Disabled() {
        return super.getCodecDisabled(VideoFormat.getVp9Name());
    }

    /* access modifiers changed from: protected */
    public IVideoInput[] inputArrayFromList(ArrayList<IVideoInput> arrayList) {
        return (IVideoInput[]) arrayList.toArray(new IVideoInput[0]);
    }

    /* access modifiers changed from: protected */
    public IVideoOutput[] outputArrayFromList(ArrayList<IVideoOutput> arrayList) {
        return (IVideoOutput[]) arrayList.toArray(new IVideoOutput[0]);
    }

    public boolean processFrame(VideoFrame videoFrame) {
        if (!this._readyToSend) {
            this._readyToSend = ((VideoBuffer) videoFrame.getLastBuffer()).getIsKeyFrame();
        }
        if (this._readyToSend) {
            return super.processFrame(videoFrame);
        }
        FirControlFrame firControlFrame = new FirControlFrame(new FirEntry(super.getCcmSequenceNumber()));
        firControlFrame.setMediaSourceSynchronizationSource(videoFrame.getSynchronizationSource());
        super.raiseControlFrame(firControlFrame);
        return false;
    }

    public void raiseKeyFrameRequest(long[] jArr) {
        PliControlFrame[] pliControlFrameArr = new PliControlFrame[ArrayExtensions.getLength(jArr)];
        for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
            PliControlFrame pliControlFrame = new PliControlFrame();
            pliControlFrame.setMediaSourceSynchronizationSource(jArr[i]);
            pliControlFrameArr[i] = pliControlFrame;
        }
        super.raiseControlFrames(pliControlFrameArr);
    }

    public void removeOnDiscardKeyFrameRequest(IAction1<long[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onDiscardKeyFrameRequest, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onDiscardKeyFrameRequest.remove(iAction1);
        if (this.__onDiscardKeyFrameRequest.size() == 0) {
            this._onDiscardKeyFrameRequest = null;
        }
    }

    public void setH264Disabled(boolean z) {
        super.setCodecDisabled(VideoFormat.getH264Name(), z);
    }

    /* access modifiers changed from: protected */
    public void setInputSourceMuted(IVideoOutput iVideoOutput, boolean z) {
        VideoSource videoSource = (VideoSource) Global.tryCast(iVideoOutput, VideoSource.class);
        if (videoSource != null) {
            videoSource.setOutputMuted(z);
            return;
        }
        VideoPipe videoPipe = (VideoPipe) Global.tryCast(iVideoOutput, VideoPipe.class);
        if (videoPipe != null) {
            super.setInputSourceMuted((TIOutput[]) videoPipe.getInputs(), z);
        }
    }

    /* access modifiers changed from: protected */
    public void setOutputSinkMuted(IVideoInput iVideoInput, boolean z) {
        VideoSink videoSink = (VideoSink) Global.tryCast(iVideoInput, VideoSink.class);
        if (videoSink != null) {
            videoSink.setInputMuted(z);
            return;
        }
        VideoPipe videoPipe = (VideoPipe) Global.tryCast(iVideoInput, VideoPipe.class);
        if (videoPipe != null) {
            super.setOutputSinkMuted((TIInput[]) videoPipe.getOutputs(), z);
        }
    }

    public void setVp8Disabled(boolean z) {
        super.setCodecDisabled(VideoFormat.getVp8Name(), z);
    }

    public void setVp9Disabled(boolean z) {
        super.setCodecDisabled(VideoFormat.getVp9Name(), z);
    }

    public VideoStream() {
        this(new IVideoOutput[0]);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VideoStream(fm.liveswitch.IVideoOutput r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000b
        L_0x0004:
            r0 = 1
            fm.liveswitch.IVideoOutput[] r0 = new fm.liveswitch.IVideoOutput[r0]
            r1 = 0
            r0[r1] = r3
            r3 = r0
        L_0x000b:
            r2.<init>((fm.liveswitch.IVideoOutput[]) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.VideoStream.<init>(fm.liveswitch.IVideoOutput):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VideoStream(IVideoOutput iVideoOutput, IVideoInput iVideoInput) {
        this(iVideoOutput == null ? null : new IVideoOutput[]{iVideoOutput}, iVideoInput != null ? new IVideoInput[]{iVideoInput} : null);
    }

    public VideoStream(IVideoOutput[] iVideoOutputArr) {
        this(iVideoOutputArr, (IVideoInput[]) null);
    }

    public VideoStream(IVideoOutput[] iVideoOutputArr, IVideoInput[] iVideoInputArr) {
        super(StreamType.Video, new JitterConfig());
        this.__onDiscardKeyFrameRequest = new ArrayList();
        this._onDiscardKeyFrameRequest = null;
        this._readyToSend = false;
        this.__videoType = VideoType.Unknown;
        if (iVideoOutputArr == null && iVideoInputArr == null) {
            throw new RuntimeException(new Exception("Cannot initialize video stream if no inputs and no outputs are provided."));
        }
        super.setRedFecPolicy(RedFecPolicy.Disabled);
        super.setNackPolicy(NackPolicy.Negotiated);
        super.setNackPliPolicy(NackPliPolicy.Negotiated);
        super.setCcmFirPolicy(CcmFirPolicy.Negotiated);
        super.setCcmLrrPolicy(CcmLrrPolicy.Disabled);
        super.setCcmTmmbrPolicy(CcmTmmbrPolicy.Disabled);
        super.setCcmTmmbnPolicy(CcmTmmbnPolicy.Disabled);
        super.setBandwidthAdaptationPolicy(BandwidthAdaptationPolicy.Enabled);
        if (iVideoOutputArr != null) {
            super.addInputs((TIOutput[]) iVideoOutputArr);
        }
        if (iVideoInputArr != null) {
            super.addOutputs((TIInput[]) iVideoInputArr);
        }
        super.addOnDiscardOutboundControlFrame(new IActionDelegate1<MediaControlFrame>() {
            public String getId() {
                return "fm.liveswitch.VideoStream.videoStream_OnDiscardOutboundControlFrame";
            }

            public void invoke(MediaControlFrame mediaControlFrame) {
                VideoStream.this.videoStream_OnDiscardOutboundControlFrame(mediaControlFrame);
            }
        });
    }

    public VideoStream(LocalMedia localMedia) {
        this(localMedia, (RemoteMedia) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VideoStream(LocalMedia localMedia, RemoteMedia remoteMedia) {
        this(localMedia == null ? null : (VideoTrack) localMedia.getVideoTrack(), remoteMedia != null ? (VideoTrack) remoteMedia.getVideoTrack() : null);
        super.setLocalMedia(localMedia);
        super.setRemoteMedia(remoteMedia);
    }

    public VideoStream(VideoTrack videoTrack) {
        this(videoTrack, (VideoTrack) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VideoStream(VideoTrack videoTrack, VideoTrack videoTrack2) {
        this(videoTrack == null ? null : (IVideoOutput[]) videoTrack.getOutputs(), videoTrack2 != null ? (IVideoInput[]) videoTrack2.getInputs() : null);
        super.setLocalTrack(videoTrack);
        super.setRemoteTrack(videoTrack2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VideoStream(fm.liveswitch.IVideoInput r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000b
        L_0x0004:
            r0 = 1
            fm.liveswitch.IVideoInput[] r0 = new fm.liveswitch.IVideoInput[r0]
            r1 = 0
            r0[r1] = r3
            r3 = r0
        L_0x000b:
            r2.<init>((fm.liveswitch.IVideoInput[]) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.VideoStream.<init>(fm.liveswitch.IVideoInput):void");
    }

    public VideoStream(IVideoInput[] iVideoInputArr) {
        this((IVideoOutput[]) null, iVideoInputArr);
    }

    public VideoStream(RemoteMedia remoteMedia) {
        this((LocalMedia) null, remoteMedia);
    }

    /* access modifiers changed from: private */
    public void videoStream_OnDiscardOutboundControlFrame(MediaControlFrame mediaControlFrame) {
        if (mediaControlFrame instanceof PliControlFrame) {
            PliControlFrame pliControlFrame = (PliControlFrame) mediaControlFrame;
            IAction1<long[]> iAction1 = this._onDiscardKeyFrameRequest;
            if (iAction1 != null) {
                iAction1.invoke(new long[]{pliControlFrame.getMediaSourceSynchronizationSource()});
            }
        } else if (mediaControlFrame instanceof FirControlFrame) {
            FirControlFrame firControlFrame = (FirControlFrame) mediaControlFrame;
            IAction1<long[]> iAction12 = this._onDiscardKeyFrameRequest;
            if (iAction12 != null) {
                iAction12.invoke(new long[]{firControlFrame.getMediaSourceSynchronizationSource()});
            }
        } else if (mediaControlFrame instanceof LrrControlFrame) {
            LrrControlFrame lrrControlFrame = (LrrControlFrame) mediaControlFrame;
            IAction1<long[]> iAction13 = this._onDiscardKeyFrameRequest;
            if (iAction13 != null) {
                iAction13.invoke(new long[]{lrrControlFrame.getMediaSourceSynchronizationSource()});
            }
        }
    }
}
