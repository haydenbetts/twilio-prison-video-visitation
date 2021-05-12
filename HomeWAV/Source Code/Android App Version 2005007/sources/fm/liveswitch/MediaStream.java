package fm.liveswitch;

import fm.liveswitch.IMediaElement;
import fm.liveswitch.IMediaInput;
import fm.liveswitch.IMediaInputCollection;
import fm.liveswitch.IMediaOutput;
import fm.liveswitch.IMediaOutputCollection;
import fm.liveswitch.MediaBranch;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFormatCollection;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.MediaPipe;
import fm.liveswitch.MediaSink;
import fm.liveswitch.MediaSource;
import fm.liveswitch.MediaTrack;
import fm.liveswitch.sdp.FormatParametersAttribute;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.Message;
import fm.liveswitch.sdp.rtp.MapAttribute;
import fm.liveswitch.sdp.rtp.Media;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import tvi.webrtc.VideoCodecInfo;

public abstract class MediaStream<TIOutput extends IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIOutputCollection extends IMediaOutputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TIOutputCollection>, TIInput extends IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInputCollection extends IMediaInputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TIInputCollection>, TIElement extends IMediaElement, TSource extends MediaSource<TIOutput, TIInput, TIInputCollection, TSource, TFrame, TBuffer, TBufferCollection, TFormat>, TSink extends MediaSink<TIOutput, TIOutputCollection, TIInput, TSink, TFrame, TBuffer, TBufferCollection, TFormat>, TPipe extends MediaPipe<TIOutput, TIOutputCollection, TIInput, TIInputCollection, TPipe, TFrame, TBuffer, TBufferCollection, TFormat>, TTrack extends MediaTrack<TIOutput, TIOutputCollection, TIInput, TIInputCollection, TIElement, TSource, TSink, TPipe, TTrack, TBranch, TFrame, TBuffer, TBufferCollection, TFormat>, TBranch extends MediaBranch<TIOutput, TIOutputCollection, TIInput, TIInputCollection, TIElement, TSource, TSink, TPipe, TTrack, TBranch, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>, TFormatCollection extends MediaFormatCollection<TFormat, TFormatCollection>> extends MediaStreamBase implements IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IMediaElement, IElement, IMediaStream, IStream, ISynchronizableStream {
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(MediaStream.class);
    private StreamDirection __absoluteSenderTimeLocalDirection = StreamDirection.Unset;
    private BandwidthAdaptationPolicy __bandwidthAdaptationPolicy = BandwidthAdaptationPolicy.Disabled;
    private CcmFirPolicy __ccmFirPolicy = CcmFirPolicy.Negotiated;
    private CcmLrrPolicy __ccmLrrPolicy = CcmLrrPolicy.Negotiated;
    private CcmTmmbnPolicy __ccmTmmbnPolicy = CcmTmmbnPolicy.Negotiated;
    private CcmTmmbrPolicy __ccmTmmbrPolicy = CcmTmmbrPolicy.Negotiated;
    private ArrayList<TFormat> __disabledInputFormats = new ArrayList<>();
    private IDispatchQueue<TFrame> __dispatchQueue;
    /* access modifiers changed from: private */
    public HashMap<Long, Boolean> __encodingDisabled = new HashMap<>();
    /* access modifiers changed from: private */
    public Object __encodingDisabledLock = new Object();
    private int __firSequenceNumber = 0;
    private HashMap<String, MapAttribute> __formatsRegisteredByOtherStreamsByDescription = new HashMap<>();
    private HashMap<String, MapAttribute> __formatsRegisteredByOtherStreamsByPT = new HashMap<>();
    private ArrayList<TFormat> __inputFormats = new ArrayList<>();
    private Object __inputFormatsLock = new Object();
    private TIOutputCollection __inputs;
    private long __lastMaxInputBitrateChangeTimestamp = -1;
    private TFormatCollection __localFormatRegistry;
    private LocalMedia __localMedia;
    private int __maxInputBitrate = -1;
    private long __maxInputBitrateChangeInterval = ((long) Constants.getTicksPerSecond());
    private Size __maxReceiveVideoSize = new Size();
    private Size __maxSendVideoSize = new Size();
    private MediaStreamMediaDescriptionManager<TFormat> __mediaStreamMediaDescriptionManager = new MediaStreamMediaDescriptionManager<>();
    private boolean __monitoringInputEncodings = false;
    private NackConfig __nackConfig;
    private NackPliPolicy __nackPliPolicy = NackPliPolicy.Negotiated;
    private NackPolicy __nackPolicy = NackPolicy.Negotiated;
    /* access modifiers changed from: private */
    public List<IAction1<Stream>> __onBandwidthAdaptationPolicyChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<BitrateNotification>> __onDiscardBitrateNotification = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<BitrateRequest>> __onDiscardBitrateRequest = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame>> __onDiscardOutboundControlFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<SynchronizeContext>> __onMasterSynchronizeContextReady = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onPausedChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onProcessControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onProcessControlFrames = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onProcessFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onProcessedFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onRaiseControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onRaiseControlFrames = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onRaiseFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onRaisedFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onReceiveFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onSendFrame = new ArrayList();
    private TIInputCollection __outputs;
    private boolean __paused = false;
    private ArrayList<TFormat> __payloadTypeRegistry = new ArrayList<>();
    private ProcessFramePolicy __processPolicy = ProcessFramePolicy.Synchronous;
    private RedFecConfig __redFecConfig;
    private RedFecPolicy __redFecPolicy = RedFecPolicy.Negotiated;
    private RembPolicy __rembPolicy = RembPolicy.Disabled;
    private TFormatCollection __remoteFormatRegistry;
    private Object __remoteSynchronizationSourceLock = new Object();
    private RtpHeaderExtensionRegistry __rtpHeaderExtensionRegistry;
    private int __rtpPayloadTypeDynamicOffset = 96;
    private int __rtpPayloadTypeMaxOffset = 127;
    private RtpTransport<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> __rtpTransport;
    private StreamDirection __sdesMidLocalDirection = StreamDirection.Unset;
    private StreamDirection __sdesRepairedRtpStreamIdLocalDirection = StreamDirection.Unset;
    private StreamDirection __sdesRtpStreamIdLocalDirection = StreamDirection.Unset;
    private int __simulcastDraftVersion = -1;
    private SimulcastMode __simulcastMode;
    private boolean __startedOriginalNegotiation = false;
    private boolean __synchronizationInitialized = false;
    private boolean __synchronize = false;
    private boolean __synchronizeMaster = false;
    private MediaHeaderExtensionPolicy _absoluteSenderTimePolicy;
    private boolean _disableAutomaticReports;
    private boolean _injectionAllowed;
    private TFormat _inputFormat;
    private boolean _inputSynchronizationDisabled;
    private JitterConfig _jitterConfig;
    private boolean _legacyReceiver;
    private String _localDescriptionMediaId;
    private String _localDescriptionTrackId;
    private TTrack _localTrack;
    private int _midSendDuration = 15000;
    private long _midSendStartTimestamp = -1;
    private boolean _multiplexingSupported;
    private IAction1<Stream> _onBandwidthAdaptationPolicyChange = null;
    private IAction1<BitrateNotification> _onDiscardBitrateNotification = null;
    private IAction1<BitrateRequest> _onDiscardBitrateRequest = null;
    private IAction1<MediaControlFrame> _onDiscardOutboundControlFrame = null;
    private IAction1<SynchronizeContext> _onMasterSynchronizeContextReady = null;
    private IAction0 _onPausedChange = null;
    private IAction1<MediaControlFrame[]> _onProcessControlFrameResponses = null;
    private IAction1<MediaControlFrame[]> _onProcessControlFrames = null;
    private IAction1<TFrame> _onProcessFrame = null;
    private IAction1<TFrame> _onProcessedFrame = null;
    private IAction1<MediaControlFrame[]> _onRaiseControlFrameResponses = null;
    private IAction1<MediaControlFrame[]> _onRaiseControlFrames = null;
    private IAction1<TFrame> _onRaiseFrame = null;
    private IAction1<TFrame> _onRaisedFrame = null;
    private IAction1<TFrame> _onReceiveFrame = null;
    private IAction1<TFrame> _onSendFrame = null;
    private TFormat _outputFormat;
    private boolean _outputSynchronizable;
    private boolean _outputSynchronizationDisabled;
    private volatile boolean _processingFrame = false;
    private RemoteMedia _remoteMedia;
    private TTrack _remoteTrack;
    private RtpParameters<TFormat, TFormatCollection> _rtpParameters;
    private int _rtpStreamIdSendDuration = 15000;
    private long _rtpStreamIdSendStartTimestamp = -1;
    private MediaHeaderExtensionPolicy _sdesMidPolicy;
    private MediaHeaderExtensionPolicy _sdesRepairedRtpStreamIdPolicy;
    private MediaHeaderExtensionPolicy _sdesRtpStreamIdPolicy;
    private volatile boolean _sendMids = true;
    private volatile boolean _sendRtpStreamIds = true;
    private SynchronizeContext _synchronizeContext;
    private long _systemDelay;

    /* access modifiers changed from: protected */
    public abstract TFormat createFormat(MapAttribute mapAttribute, FormatParametersAttribute formatParametersAttribute);

    /* access modifiers changed from: protected */
    public abstract TFormat createFormat(String str, int i, String str2, int i2);

    /* access modifiers changed from: protected */
    public abstract TIInputCollection createInputCollection(TIOutput tioutput);

    /* access modifiers changed from: protected */
    public abstract TFormatCollection createMediaFormatCollection();

    /* access modifiers changed from: protected */
    public abstract TIOutputCollection createOutputCollection(TIInput tiinput);

    /* access modifiers changed from: protected */
    public abstract TFormat createRedFormat();

    /* access modifiers changed from: protected */
    public abstract TFormat createUlpFecFormat();

    /* access modifiers changed from: protected */
    public abstract TFormat[] formatArrayFromList(ArrayList<TFormat> arrayList);

    public String getInputRtpStreamId() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract boolean getInputSourceMuted(TIOutput tioutput);

    public long getInputSynchronizationSource() {
        return -1;
    }

    public int getMinInputBitrate() {
        return -1;
    }

    public int getMinOutputBitrate() {
        return -1;
    }

    public String getOutputRtpStreamId() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract boolean getOutputSinkMuted(TIInput tiinput);

    public long getOutputSynchronizationSource() {
        return -1;
    }

    public boolean getPersistent() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract TIInput[] inputArrayFromList(ArrayList<TIInput> arrayList);

    /* access modifiers changed from: protected */
    public abstract TIOutput[] outputArrayFromList(ArrayList<TIOutput> arrayList);

    public Error processSdpMediaDescriptionFromInput(MediaDescription mediaDescription, boolean z, boolean z2) {
        return null;
    }

    public Error processSdpMediaDescriptionFromOutput(MediaDescription mediaDescription, boolean z, boolean z2) {
        return null;
    }

    public void processSinkStatsFromInput(MediaSinkStats mediaSinkStats) {
    }

    public void processSourceStatsFromOutput(MediaSourceStats mediaSourceStats) {
    }

    public void processTrackStatsFromInput(MediaTrackStats mediaTrackStats) {
    }

    public void processTrackStatsFromOutput(MediaTrackStats mediaTrackStats) {
    }

    public void setInputRtpStreamId(String str) {
    }

    /* access modifiers changed from: protected */
    public abstract void setInputSourceMuted(TIOutput tioutput, boolean z);

    public void setInputSynchronizationSource(long j) {
    }

    /* access modifiers changed from: protected */
    public abstract void setOutputSinkMuted(TIInput tiinput, boolean z);

    public void addInput(TIOutput tioutput) {
        if (tioutput != null) {
            this.__inputs.add(tioutput);
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot add null input to {0}.", (Object) getLabel())));
    }

    public void addInputs(TIOutput[] tioutputArr) {
        if (tioutputArr != null) {
            for (TIOutput addInput : tioutputArr) {
                addInput(addInput);
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot add null inputs to {0}.", (Object) getLabel())));
    }

    private void addLocalFormat(TFormat tformat) {
        int registeredPayloadType = tformat.getRegisteredPayloadType();
        if (this.__remoteFormatRegistry.getCount() > 0) {
            MediaFormat equivalent = this.__remoteFormatRegistry.getEquivalent(tformat);
            if (equivalent == null) {
                MediaFormat[] compatibles = this.__remoteFormatRegistry.getCompatibles(tformat);
                int length = compatibles.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    MediaFormat mediaFormat = compatibles[i];
                    if (this.__localFormatRegistry.getByPayloadType(IntegerExtensions.toString(Integer.valueOf(mediaFormat.getRegisteredPayloadType()))) == null) {
                        mediaFormat.updateToCompatible(tformat);
                        tformat.updateToCompatible(mediaFormat);
                        equivalent = mediaFormat;
                        break;
                    }
                    i++;
                }
            }
            if (equivalent != null) {
                tformat.setRegisteredPayloadType(equivalent.getRegisteredPayloadType());
                registeredPayloadType = equivalent.getRegisteredPayloadType();
            }
        }
        if (tformat.getRegisteredPayloadType() >= 0) {
            this.__localFormatRegistry.add(tformat);
            if (!payloadTypeIsRegistered(registeredPayloadType) && tformat.getRegisteredPayloadType() >= 0) {
                this.__payloadTypeRegistry.add(tformat);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addOnBandwidthAdaptationPolicyChange(IAction1<Stream> iAction1) {
        if (iAction1 != null) {
            if (this._onBandwidthAdaptationPolicyChange == null) {
                this._onBandwidthAdaptationPolicyChange = new IAction1<Stream>() {
                    public void invoke(Stream stream) {
                        Iterator it = new ArrayList(MediaStream.this.__onBandwidthAdaptationPolicyChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(stream);
                        }
                    }
                };
            }
            this.__onBandwidthAdaptationPolicyChange.add(iAction1);
        }
    }

    public void addOnDiscardBitrateNotification(IAction1<BitrateNotification> iAction1) {
        if (iAction1 != null) {
            if (this._onDiscardBitrateNotification == null) {
                this._onDiscardBitrateNotification = new IAction1<BitrateNotification>() {
                    public void invoke(BitrateNotification bitrateNotification) {
                        Iterator it = new ArrayList(MediaStream.this.__onDiscardBitrateNotification).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(bitrateNotification);
                        }
                    }
                };
            }
            this.__onDiscardBitrateNotification.add(iAction1);
        }
    }

    public void addOnDiscardBitrateRequest(IAction1<BitrateRequest> iAction1) {
        if (iAction1 != null) {
            if (this._onDiscardBitrateRequest == null) {
                this._onDiscardBitrateRequest = new IAction1<BitrateRequest>() {
                    public void invoke(BitrateRequest bitrateRequest) {
                        Iterator it = new ArrayList(MediaStream.this.__onDiscardBitrateRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(bitrateRequest);
                        }
                    }
                };
            }
            this.__onDiscardBitrateRequest.add(iAction1);
        }
    }

    public void addOnDiscardOutboundControlFrame(IAction1<MediaControlFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onDiscardOutboundControlFrame == null) {
                this._onDiscardOutboundControlFrame = new IAction1<MediaControlFrame>() {
                    public void invoke(MediaControlFrame mediaControlFrame) {
                        Iterator it = new ArrayList(MediaStream.this.__onDiscardOutboundControlFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrame);
                        }
                    }
                };
            }
            this.__onDiscardOutboundControlFrame.add(iAction1);
        }
    }

    public void addOnMasterSynchronizeContextReady(IAction1<SynchronizeContext> iAction1) {
        if (iAction1 != null) {
            if (this._onMasterSynchronizeContextReady == null) {
                this._onMasterSynchronizeContextReady = new IAction1<SynchronizeContext>() {
                    public void invoke(SynchronizeContext synchronizeContext) {
                        Iterator it = new ArrayList(MediaStream.this.__onMasterSynchronizeContextReady).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(synchronizeContext);
                        }
                    }
                };
            }
            this.__onMasterSynchronizeContextReady.add(iAction1);
        }
    }

    public void addOnPausedChange(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onPausedChange == null) {
                this._onPausedChange = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(MediaStream.this.__onPausedChange).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onPausedChange.add(iAction0);
        }
    }

    public void addOnProcessControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessControlFrameResponses == null) {
                this._onProcessControlFrameResponses = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaStream.this.__onProcessControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onProcessControlFrameResponses.add(iAction1);
        }
    }

    public void addOnProcessControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessControlFrames == null) {
                this._onProcessControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaStream.this.__onProcessControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onProcessControlFrames.add(iAction1);
        }
    }

    public void addOnProcessedFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessedFrame == null) {
                this._onProcessedFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaStream.this.__onProcessedFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onProcessedFrame.add(iAction1);
        }
    }

    public void addOnProcessFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessFrame == null) {
                this._onProcessFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaStream.this.__onProcessFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onProcessFrame.add(iAction1);
        }
    }

    public void addOnRaiseControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onRaiseControlFrameResponses == null) {
                this._onRaiseControlFrameResponses = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaStream.this.__onRaiseControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onRaiseControlFrameResponses.add(iAction1);
        }
    }

    public void addOnRaiseControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onRaiseControlFrames == null) {
                this._onRaiseControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaStream.this.__onRaiseControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onRaiseControlFrames.add(iAction1);
        }
    }

    public void addOnRaisedFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onRaisedFrame == null) {
                this._onRaisedFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaStream.this.__onRaisedFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onRaisedFrame.add(iAction1);
        }
    }

    public void addOnRaiseFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onRaiseFrame == null) {
                this._onRaiseFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaStream.this.__onRaiseFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onRaiseFrame.add(iAction1);
        }
    }

    public void addOnReceiveFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onReceiveFrame == null) {
                this._onReceiveFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaStream.this.__onReceiveFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onReceiveFrame.add(iAction1);
        }
    }

    public void addOnSendFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onSendFrame == null) {
                this._onSendFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaStream.this.__onSendFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onSendFrame.add(iAction1);
        }
    }

    public void addOutput(TIInput tiinput) {
        if (tiinput != null) {
            this.__outputs.add(tiinput);
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot add null output to {0}.", (Object) getLabel())));
    }

    public void addOutputs(TIInput[] tiinputArr) {
        if (tiinputArr != null) {
            for (TIInput addOutput : tiinputArr) {
                addOutput(addOutput);
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot add null outputs to {0}.", (Object) getLabel())));
    }

    /* access modifiers changed from: private */
    public boolean allInputsDisabled(TIOutput[] tioutputArr) {
        boolean z = true;
        for (TIOutput disabled : tioutputArr) {
            z &= disabled.getDisabled();
        }
        return z;
    }

    private boolean anyInputIsSynchronizable() {
        for (IMediaOutput outputSynchronizable : getInputs()) {
            if (outputSynchronizable.getOutputSynchronizable()) {
                return true;
            }
        }
        return false;
    }

    public Future<Object> changeInputFormat(TFormat tformat) {
        Promise promise = new Promise();
        doChangeInputFormat(tformat, promise);
        return promise;
    }

    /* access modifiers changed from: package-private */
    public void close() {
        super.close();
        setRtpTransport((RtpTransport) null);
    }

    /* access modifiers changed from: package-private */
    public MediaDescription createSdpMediaDescription(Message message, boolean z, boolean z2, boolean z3, BundlePolicy bundlePolicy) {
        boolean z4 = z2;
        boolean z5 = z3;
        this.__startedOriginalNegotiation = true;
        getRtpParameters().setCanonicalName(super.getLocalCanonicalName());
        TransportAddress obtainLikelyTransportAddress = super.obtainLikelyTransportAddress(super.getCoreTransportRtcp() == null ? super.getCoreTransportRtp() : super.getCoreTransportRtcp());
        StreamDirection restrictDirection = restrictDirection(z4, z5);
        for (MediaDescription rtpMapAttributes : message.getMediaDescriptions()) {
            for (MapAttribute mapAttribute : rtpMapAttributes.getRtpMapAttributes()) {
                String integerExtensions = IntegerExtensions.toString(Integer.valueOf(mapAttribute.getPayloadType()));
                String attributeDescription = SessionDescriptionManager.getAttributeDescription(mapAttribute);
                Holder holder = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(this.__formatsRegisteredByOtherStreamsByPT, integerExtensions, holder);
                MapAttribute mapAttribute2 = (MapAttribute) holder.getValue();
                if (tryGetValue) {
                    String attributeDescription2 = SessionDescriptionManager.getAttributeDescription(mapAttribute2);
                    if (!Global.equals(attributeDescription, attributeDescription2)) {
                        throw new RuntimeException(new Exception(StringExtensions.format("Payload type {0} was already registered for format {1}, but a stream has attempted to register another format {2} with that payload type.", integerExtensions, attributeDescription2, attributeDescription)));
                    }
                } else {
                    HashMapExtensions.add(this.__formatsRegisteredByOtherStreamsByPT, integerExtensions, mapAttribute);
                }
                HashMapExtensions.set(HashMapExtensions.getItem(this.__formatsRegisteredByOtherStreamsByDescription), attributeDescription, mapAttribute);
            }
        }
        registerPayloadTypes();
        MediaStreamMediaDescriptionRequirements mediaStreamMediaDescriptionRequirements = new MediaStreamMediaDescriptionRequirements();
        mediaStreamMediaDescriptionRequirements.setMultiplexingSupported(getMultiplexingSupported());
        mediaStreamMediaDescriptionRequirements.setDirection(restrictDirection);
        mediaStreamMediaDescriptionRequirements.setLikelySecondaryTransportAddress(obtainLikelyTransportAddress);
        mediaStreamMediaDescriptionRequirements.setFormats((MediaFormat[]) this.__localFormatRegistry.getValues());
        mediaStreamMediaDescriptionRequirements.setLocalBandwidth(super.getLocalBandwidth());
        mediaStreamMediaDescriptionRequirements.setRembPolicy(this.__rembPolicy);
        mediaStreamMediaDescriptionRequirements.setRedFecPolicy(getRedFecPolicy());
        mediaStreamMediaDescriptionRequirements.setNackPolicy(getNackPolicy());
        mediaStreamMediaDescriptionRequirements.setNackPliPolicy(getNackPliPolicy());
        mediaStreamMediaDescriptionRequirements.setCcmFirPolicy(getCcmFirPolicy());
        mediaStreamMediaDescriptionRequirements.setCcmLrrPolicy(getCcmLrrPolicy());
        mediaStreamMediaDescriptionRequirements.setCcmTmmbrPolicy(getCcmTmmbrPolicy());
        mediaStreamMediaDescriptionRequirements.setCcmTmmbnPolicy(getCcmTmmbnPolicy());
        mediaStreamMediaDescriptionRequirements.setAbsoluteSenderTimePolicy(getAbsoluteSenderTimePolicy());
        mediaStreamMediaDescriptionRequirements.setAbsoluteSenderTimeLocalDirection(getAbsoluteSenderTimeLocalDirection());
        mediaStreamMediaDescriptionRequirements.setSdesMidPolicy(getSdesMidPolicy());
        mediaStreamMediaDescriptionRequirements.setSdesMidLocalDirection(getSdesMidLocalDirection());
        mediaStreamMediaDescriptionRequirements.setSdesRtpStreamIdPolicy(getSdesRtpStreamIdPolicy());
        mediaStreamMediaDescriptionRequirements.setSdesRtpStreamIdLocalDirection(getSdesRtpStreamIdLocalDirection());
        mediaStreamMediaDescriptionRequirements.setSdesRepairedRtpStreamIdPolicy(getSdesRepairedRtpStreamIdPolicy());
        mediaStreamMediaDescriptionRequirements.setSdesRepairedRtpStreamIdLocalDirection(getSdesRepairedRtpStreamIdLocalDirection());
        mediaStreamMediaDescriptionRequirements.setBundlePolicy(bundlePolicy);
        super.populateProperties(mediaStreamMediaDescriptionRequirements);
        MediaDescription createSdpMediaDescription = this.__mediaStreamMediaDescriptionManager.createSdpMediaDescription(mediaStreamMediaDescriptionRequirements, message, z4, z5);
        extractPropertiesFromManager();
        return createSdpMediaDescription;
    }

    public boolean destroy() {
        super.removeOnDirectionChange(new IActionDelegate0() {
            public String getId() {
                return "fm.liveswitch.MediaStream<TIOutput,TIOutputCollection,TIInput,TIInputCollection,TIElement,TSource,TSink,TPipe,TTrack,TBranch,TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.setRtpTransportDirection";
            }

            public void invoke() {
                MediaStream.this.setRtpTransportDirection();
            }
        });
        RtpTransport<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpTransport = this.__rtpTransport;
        if (rtpTransport != null) {
            rtpTransport.removeOnSendControlFrames(this._onProcessControlFrames);
            this.__rtpTransport.removeOnSendControlFrameResponses(this._onProcessControlFrameResponses);
        }
        this.__outputs.destroy();
        this.__inputs.destroy();
        IDispatchQueue<TFrame> iDispatchQueue = this.__dispatchQueue;
        if (iDispatchQueue == null) {
            return true;
        }
        iDispatchQueue.destroy();
        this.__dispatchQueue = null;
        return true;
    }

    public Future<Object> disableInputFormat(TFormat tformat) {
        Promise promise = new Promise();
        doDisableInputFormat(tformat, promise);
        return promise;
    }

    /* access modifiers changed from: private */
    public void doChangeInputFormat(TFormat tformat) {
        synchronized (this.__inputFormatsLock) {
            if (getInputFormat() != tformat) {
                if (tformat != null) {
                    if (!this.__inputFormats.contains(tformat)) {
                        throw new RuntimeException(new Exception("Unrecognized input format."));
                    }
                }
                setInputFormat((MediaFormat) null);
                while (this._processingFrame) {
                    ManagedThread.sleep(1);
                }
                setInputFormat(tformat);
            }
        }
    }

    private void doChangeInputFormat(final TFormat tformat, final Promise<Object> promise) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    MediaStream.this.doChangeInputFormat(tformat);
                    promise.resolve(null);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void doDisableInputFormat(TFormat tformat) {
        synchronized (this.__inputFormatsLock) {
            if (!this.__disabledInputFormats.contains(tformat)) {
                if (this.__inputFormats.contains(tformat)) {
                    this.__inputFormats.remove(tformat);
                    this.__disabledInputFormats.add(tformat);
                    doChangeInputFormat(ArrayListExtensions.getCount(this.__inputFormats) > 0 ? (MediaFormat) ArrayListExtensions.getItem(this.__inputFormats).get(0) : null);
                } else {
                    throw new RuntimeException(new Exception("Unrecognized input format."));
                }
            }
        }
    }

    private void doDisableInputFormat(final TFormat tformat, final Promise<Object> promise) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    MediaStream.this.doDisableInputFormat(tformat);
                    promise.resolve(null);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void doEnableInputFormat(TFormat tformat) {
        synchronized (this.__inputFormatsLock) {
            if (!this.__inputFormats.contains(tformat)) {
                if (this.__disabledInputFormats.contains(tformat)) {
                    this.__disabledInputFormats.remove(tformat);
                    this.__inputFormats.add(tformat);
                } else {
                    throw new RuntimeException(new Exception("Unrecognized input format."));
                }
            }
        }
    }

    private void doEnableInputFormat(final TFormat tformat, final Promise<Object> promise) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    MediaStream.this.doEnableInputFormat(tformat);
                    promise.resolve(null);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
    }

    public Future<Object> enableInputFormat(TFormat tformat) {
        Promise promise = new Promise();
        doEnableInputFormat(tformat, promise);
        return promise;
    }

    private boolean equivalentEncodingExists(EncodingInfo encodingInfo, ArrayList<EncodingInfo> arrayList) {
        Iterator<EncodingInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().isEquivalent(encodingInfo)) {
                return true;
            }
        }
        return false;
    }

    private boolean equivalentFormatExists(FormatInfo formatInfo, ArrayList<FormatInfo> arrayList) {
        Iterator<FormatInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().isEquivalent(formatInfo)) {
                return true;
            }
        }
        return false;
    }

    private boolean equivalentLongExists(long j, ArrayList<Long> arrayList) {
        Iterator<Long> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().longValue() == j) {
                return true;
            }
        }
        return false;
    }

    private void extractPropertiesFromManager() {
        setMultiplexingSupported(this.__mediaStreamMediaDescriptionManager.getMultiplexingSupported());
        if (!Global.equals(this.__mediaStreamMediaDescriptionManager.getLocalDirection(), StreamDirection.Unset)) {
            setLocalDirection(this.__mediaStreamMediaDescriptionManager.getLocalDirection());
        }
        if (!Global.equals(this.__mediaStreamMediaDescriptionManager.getAbsoluteSenderTimeLocalDirection(), StreamDirection.Unset)) {
            setAbsoluteSenderTimeLocalDirection(this.__mediaStreamMediaDescriptionManager.getAbsoluteSenderTimeLocalDirection());
        }
        if (!Global.equals(this.__mediaStreamMediaDescriptionManager.getSdesMidLocalDirection(), StreamDirection.Unset)) {
            setSdesMidLocalDirection(this.__mediaStreamMediaDescriptionManager.getSdesMidLocalDirection());
        }
        if (!Global.equals(this.__mediaStreamMediaDescriptionManager.getSdesRtpStreamIdLocalDirection(), StreamDirection.Unset)) {
            setSdesRtpStreamIdLocalDirection(this.__mediaStreamMediaDescriptionManager.getSdesRtpStreamIdLocalDirection());
        }
        if (!Global.equals(this.__mediaStreamMediaDescriptionManager.getSdesRepairedRtpStreamIdLocalDirection(), StreamDirection.Unset)) {
            setSdesRepairedRtpStreamIdLocalDirection(this.__mediaStreamMediaDescriptionManager.getSdesRepairedRtpStreamIdLocalDirection());
        }
        setNackEnabled(this.__mediaStreamMediaDescriptionManager.getNackEnabled());
        setRtpHeaderExtensionRegistry(this.__mediaStreamMediaDescriptionManager.getRtpHeaderExtensionRegistry());
    }

    private MediaControlFrame[] filterOutboundControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        ArrayList arrayList = new ArrayList();
        for (PliControlFrame pliControlFrame : mediaControlFrameArr) {
            if (getRemoteSupportsNackPli() || !(pliControlFrame instanceof PliControlFrame)) {
                if (getRemoteSupportsCcmFir() || !(pliControlFrame instanceof FirControlFrame)) {
                    if (getRemoteSupportsCcmLrr() || !(pliControlFrame instanceof LrrControlFrame)) {
                        if (!getRemoteSupportsCcmTmmbr() && (pliControlFrame instanceof TmmbrControlFrame)) {
                            IAction1<MediaControlFrame> iAction1 = this._onDiscardOutboundControlFrame;
                            if (iAction1 != null) {
                                iAction1.invoke(pliControlFrame);
                            }
                            if (Global.equals(getCcmTmmbrPolicy(), CcmTmmbrPolicy.Disabled)) {
                                __log.debug("Discarding outbound TMMBR control frame (CCM TMMBR policy is disabled).");
                            } else {
                                __log.debug("Discarding outbound TMMBR control frame (no support on remote).");
                            }
                        } else if (getRemoteSupportsCcmTmmbn() || !(pliControlFrame instanceof TmmbnControlFrame)) {
                            arrayList.add(pliControlFrame);
                        } else {
                            IAction1<MediaControlFrame> iAction12 = this._onDiscardOutboundControlFrame;
                            if (iAction12 != null) {
                                iAction12.invoke(pliControlFrame);
                            }
                            if (Global.equals(getCcmTmmbnPolicy(), CcmTmmbnPolicy.Disabled)) {
                                __log.debug("Discarding outbound TMMBN control frame (CCM TMMBN policy is disabled).");
                            } else {
                                __log.debug("Discarding outbound TMMBN control frame (no support on remote).");
                            }
                        }
                    } else if (getRemoteSupportsCcmFir()) {
                        LrrControlFrame lrrControlFrame = (LrrControlFrame) pliControlFrame;
                        LrrEntry[] entries = lrrControlFrame.getEntries();
                        FirEntry[] firEntryArr = new FirEntry[ArrayExtensions.getLength((Object[]) entries)];
                        for (int i = 0; i < ArrayExtensions.getLength((Object[]) entries); i++) {
                            firEntryArr[i] = new FirEntry(entries[i].getSequenceNumber());
                        }
                        FirControlFrame firControlFrame = new FirControlFrame(firEntryArr);
                        firControlFrame.setMediaSourceSynchronizationSource(lrrControlFrame.getMediaSourceSynchronizationSource());
                        arrayList.add(firControlFrame);
                    } else if (getRemoteSupportsNackPli()) {
                        PliControlFrame pliControlFrame2 = new PliControlFrame();
                        pliControlFrame2.setMediaSourceSynchronizationSource(((LrrControlFrame) pliControlFrame).getMediaSourceSynchronizationSource());
                        arrayList.add(pliControlFrame2);
                    } else {
                        IAction1<MediaControlFrame> iAction13 = this._onDiscardOutboundControlFrame;
                        if (iAction13 != null) {
                            iAction13.invoke(pliControlFrame);
                        }
                        if (Global.equals(getCcmLrrPolicy(), CcmLrrPolicy.Disabled)) {
                            __log.debug("Discarding outbound LRR control frame (CCM LRR policy is disabled).");
                        } else {
                            __log.debug("Discarding outbound LRR control frame (no support on remote).");
                        }
                    }
                } else if (getRemoteSupportsNackPli()) {
                    PliControlFrame pliControlFrame3 = new PliControlFrame();
                    pliControlFrame3.setMediaSourceSynchronizationSource(((FirControlFrame) pliControlFrame).getMediaSourceSynchronizationSource());
                    arrayList.add(pliControlFrame3);
                } else {
                    IAction1<MediaControlFrame> iAction14 = this._onDiscardOutboundControlFrame;
                    if (iAction14 != null) {
                        iAction14.invoke(pliControlFrame);
                    }
                    if (Global.equals(getCcmFirPolicy(), CcmFirPolicy.Disabled)) {
                        __log.debug("Discarding outbound FIR control frame (CCM FIR policy is disabled).");
                    } else {
                        __log.debug("Discarding outbound FIR control frame (no support on remote).");
                    }
                }
            } else if (getRemoteSupportsCcmFir()) {
                FirControlFrame firControlFrame2 = new FirControlFrame(new FirEntry(getCcmSequenceNumber()));
                firControlFrame2.setMediaSourceSynchronizationSource(pliControlFrame.getMediaSourceSynchronizationSource());
                arrayList.add(firControlFrame2);
            } else {
                IAction1<MediaControlFrame> iAction15 = this._onDiscardOutboundControlFrame;
                if (iAction15 != null) {
                    iAction15.invoke(pliControlFrame);
                }
                if (Global.equals(getNackPliPolicy(), NackPliPolicy.Disabled)) {
                    __log.debug("Discarding outbound PLI control frame (NACK PLI policy is disabled).");
                } else {
                    __log.debug("Discarding outbound PLI control frame (no support on remote).");
                }
            }
        }
        return (MediaControlFrame[]) arrayList.toArray(new MediaControlFrame[0]);
    }

    private ISynchronizer[] findSynchronizers(TIInput[] tiinputArr) {
        IVideoOutput iVideoOutput;
        ArrayList arrayList = new ArrayList();
        if (tiinputArr != null) {
            for (TIInput tiinput : tiinputArr) {
                ISynchronizer iSynchronizer = (ISynchronizer) Global.tryCast(tiinput, ISynchronizer.class);
                if (iSynchronizer != null) {
                    arrayList.add(iSynchronizer);
                } else if (Global.equals(super.getType(), StreamType.Audio)) {
                    IAudioOutput iAudioOutput = (IAudioOutput) Global.tryCast(tiinput, IAudioOutput.class);
                    if (iAudioOutput != null) {
                        ArrayListExtensions.addRange(arrayList, (T[]) findSynchronizers((IMediaInput[]) iAudioOutput.getOutputs()));
                    }
                } else if (Global.equals(super.getType(), StreamType.Video) && (iVideoOutput = (IVideoOutput) Global.tryCast(tiinput, IVideoOutput.class)) != null) {
                    ArrayListExtensions.addRange(arrayList, (T[]) findSynchronizers((IMediaInput[]) iVideoOutput.getOutputs()));
                }
            }
        }
        return (ISynchronizer[]) arrayList.toArray(new ISynchronizer[0]);
    }

    public StreamDirection getAbsoluteSenderTimeDirection() {
        return this.__mediaStreamMediaDescriptionManager.getAbsoluteSenderTimeDirection();
    }

    public StreamDirection getAbsoluteSenderTimeLocalDirection() {
        return this.__absoluteSenderTimeLocalDirection;
    }

    /* access modifiers changed from: package-private */
    public MediaHeaderExtensionPolicy getAbsoluteSenderTimePolicy() {
        return this._absoluteSenderTimePolicy;
    }

    public StreamDirection getAbsoluteSenderTimeRemoteDirection() {
        return this.__mediaStreamMediaDescriptionManager.getAbsoluteSenderTimeRemoteDirection();
    }

    public BandwidthAdaptationPolicy getBandwidthAdaptationPolicy() {
        return this.__bandwidthAdaptationPolicy;
    }

    private int getBitrateSum(EncodingInfo[] encodingInfoArr) {
        if (encodingInfoArr == null || ArrayExtensions.getLength((Object[]) encodingInfoArr) == 0) {
            return -1;
        }
        int i = 0;
        for (EncodingInfo bitrate : encodingInfoArr) {
            int bitrate2 = bitrate.getBitrate();
            if (bitrate2 == -1) {
                return -1;
            }
            i += bitrate2;
        }
        return i;
    }

    public boolean getCcmFirEnabled() {
        return this.__mediaStreamMediaDescriptionManager.getCcmFirEnabled();
    }

    public CcmFirPolicy getCcmFirPolicy() {
        return this.__ccmFirPolicy;
    }

    public boolean getCcmLrrEnabled() {
        return this.__mediaStreamMediaDescriptionManager.getCcmLrrEnabled();
    }

    public CcmLrrPolicy getCcmLrrPolicy() {
        return this.__ccmLrrPolicy;
    }

    public int getCcmSequenceNumber() {
        return this.__firSequenceNumber;
    }

    public boolean getCcmTmmbnEnabled() {
        return this.__mediaStreamMediaDescriptionManager.getCcmTmmbnEnabled();
    }

    public CcmTmmbnPolicy getCcmTmmbnPolicy() {
        return this.__ccmTmmbnPolicy;
    }

    public boolean getCcmTmmbrEnabled() {
        return this.__mediaStreamMediaDescriptionManager.getCcmTmmbrEnabled();
    }

    public CcmTmmbrPolicy getCcmTmmbrPolicy() {
        return this.__ccmTmmbrPolicy;
    }

    public TransportInfo getControlTransportInfo() {
        if (super.getCoreTransportRtcp() == null) {
            return null;
        }
        return super.getCoreTransportRtcp().getInfo();
    }

    private int getDefaultInitialBandwidthEstimate() {
        return Global.equals(super.getType(), StreamType.Audio) ? 64 : 2048;
    }

    public long getDefaultLocalSynchronizationSource() {
        RtpTransport rtpTransport = getRtpTransport();
        if (rtpTransport != null) {
            return rtpTransport.getDefaultLocalSynchronizationSource();
        }
        __log.debug("Attempted to access Stream.DefaultLocalSynchronizationSource, but RtpTransport has not yet been assigned to this stream. Access this value only after the local SDP offer or answer has been set for the Connection.");
        return -1;
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getDirectionCapabilities() {
        boolean z = true;
        boolean z2 = ArrayExtensions.getLength((Object[]) getInputs()) > 0;
        if (ArrayExtensions.getLength((Object[]) getOutputs()) <= 0) {
            z = false;
        }
        if (z2 && z) {
            return StreamDirection.SendReceive;
        }
        if (z2) {
            return StreamDirection.SendOnly;
        }
        if (z) {
            return StreamDirection.ReceiveOnly;
        }
        return StreamDirection.Inactive;
    }

    public boolean getDisableAutomaticReports() {
        return this._disableAutomaticReports;
    }

    public TFormat[] getDisabledInputFormats() {
        TFormat[] formatArrayFromList;
        synchronized (this.__inputFormatsLock) {
            formatArrayFromList = formatArrayFromList(this.__disabledInputFormats);
        }
        return formatArrayFromList;
    }

    private int getEncoderTargetOutputBitrate(long j, String str) {
        EncodingInfo[] inputTargetOutputEncodings = getInputTargetOutputEncodings(getInputs((TIOutput[]) getInputs(), j));
        if (inputTargetOutputEncodings == null || ArrayExtensions.getLength((Object[]) inputTargetOutputEncodings) == 0) {
            return -1;
        }
        return inputTargetOutputEncodings[0].getBitrate();
    }

    /* access modifiers changed from: protected */
    public boolean getInjectionAllowed() {
        return this._injectionAllowed;
    }

    public TIOutput getInput() {
        return (IMediaOutput) this.__inputs.getValue();
    }

    private TIOutput getInput(TIOutput[] tioutputArr, long j, TFormat tformat) {
        for (TIOutput tioutput : tioutputArr) {
            if (tioutput.getOutputSynchronizationSource() == j && tformat.isEquivalent(tioutput.getOutputFormat())) {
                return tioutput;
            }
        }
        return null;
    }

    public boolean getInputDeactivated() {
        StreamDirection direction = getDirection();
        return Global.equals(direction, StreamDirection.Unset) || Global.equals(direction, StreamDirection.Inactive) || Global.equals(direction, StreamDirection.ReceiveOnly);
    }

    public TFormat getInputFormat() {
        return this._inputFormat;
    }

    public TFormat[] getInputFormats() {
        TFormat[] formatArrayFromList;
        synchronized (this.__inputFormatsLock) {
            formatArrayFromList = formatArrayFromList(this.__inputFormats);
        }
        return formatArrayFromList;
    }

    public int getInputMaxOutputBitrate() {
        return getBitrateSum(getInputMaxOutputEncodings());
    }

    public EncodingInfo[] getInputMaxOutputEncodings() {
        return getInputMaxOutputEncodings(getInputs());
    }

    private EncodingInfo[] getInputMaxOutputEncodings(TIOutput[] tioutputArr) {
        EncodingInfo maxOutputEncoding;
        boolean z;
        ArrayList arrayList = new ArrayList();
        for (TIOutput tioutput : tioutputArr) {
            MediaFormat outputFormat = tioutput.getOutputFormat();
            if ((outputFormat == null || !outputFormat.getIsInjected()) && (maxOutputEncoding = tioutput.getMaxOutputEncoding()) != null) {
                maxOutputEncoding.setRtpStreamId(getLocalRtpStreamId(tioutput.getOutputSynchronizationSource()));
                if (outputFormat == null || !outputFormat.getIsFixedBitrate()) {
                    z = false;
                } else {
                    z = false;
                    for (int i = 0; i < ArrayListExtensions.getCount(arrayList); i++) {
                        if (!((((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getSynchronizationSource() == -1 || maxOutputEncoding.getSynchronizationSource() == -1 || maxOutputEncoding.getSynchronizationSource() != ((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getSynchronizationSource()) && (((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getRtpStreamId() == null || maxOutputEncoding.getRtpStreamId() == null || !Global.equals(maxOutputEncoding.getRtpStreamId(), ((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getRtpStreamId())))) {
                            z = true;
                        }
                    }
                }
                if (!z && !equivalentEncodingExists(maxOutputEncoding, arrayList)) {
                    arrayList.add(maxOutputEncoding);
                }
            }
        }
        EncodingInfo[] merge = EncodingInfo.merge((EncodingInfo[]) arrayList.toArray(new EncodingInfo[0]));
        if (ArrayExtensions.getLength((Object[]) merge) <= 1 || !Global.equals(getSimulcastMode(), SimulcastMode.Disabled)) {
            return merge;
        }
        return new EncodingInfo[]{merge[0]};
    }

    public int getInputMinOutputBitrate() {
        return getBitrateSum(getInputMinOutputEncodings());
    }

    public EncodingInfo[] getInputMinOutputEncodings() {
        return getInputMinOutputEncodings(getInputs());
    }

    private EncodingInfo[] getInputMinOutputEncodings(TIOutput[] tioutputArr) {
        EncodingInfo minOutputEncoding;
        boolean z;
        ArrayList arrayList = new ArrayList();
        for (TIOutput tioutput : tioutputArr) {
            MediaFormat outputFormat = tioutput.getOutputFormat();
            if ((outputFormat == null || !outputFormat.getIsInjected()) && (minOutputEncoding = tioutput.getMinOutputEncoding()) != null) {
                minOutputEncoding.setRtpStreamId(getLocalRtpStreamId(tioutput.getOutputSynchronizationSource()));
                if (outputFormat == null || !outputFormat.getIsFixedBitrate()) {
                    z = false;
                } else {
                    z = false;
                    for (int i = 0; i < ArrayListExtensions.getCount(arrayList); i++) {
                        if (!((((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getSynchronizationSource() == -1 || minOutputEncoding.getSynchronizationSource() == -1 || minOutputEncoding.getSynchronizationSource() != ((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getSynchronizationSource()) && (((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getRtpStreamId() == null || minOutputEncoding.getRtpStreamId() == null || !Global.equals(minOutputEncoding.getRtpStreamId(), ((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getRtpStreamId())))) {
                            z = true;
                        }
                    }
                }
                if (!z && !equivalentEncodingExists(minOutputEncoding, arrayList)) {
                    arrayList.add(minOutputEncoding);
                }
            }
        }
        EncodingInfo[] merge = EncodingInfo.merge((EncodingInfo[]) arrayList.toArray(new EncodingInfo[0]));
        if (ArrayExtensions.getLength((Object[]) merge) <= 1 || !Global.equals(getSimulcastMode(), SimulcastMode.Disabled)) {
            return merge;
        }
        return new EncodingInfo[]{merge[0]};
    }

    public boolean getInputMuted() {
        return getInputSourceMuted((TIOutput[]) getInputs());
    }

    public TIOutput[] getInputs() {
        return (IMediaOutput[]) this.__inputs.getValues();
    }

    private TIOutput[] getInputs(TIOutput[] tioutputArr, String str) {
        ArrayList arrayList = new ArrayList();
        for (TIOutput tioutput : tioutputArr) {
            if (Global.equals(getLocalRtpStreamId(tioutput.getOutputSynchronizationSource()), str)) {
                arrayList.add(tioutput);
            }
        }
        return outputArrayFromList(arrayList);
    }

    private TIOutput[] getInputs(TIOutput[] tioutputArr, long j) {
        ArrayList arrayList = new ArrayList();
        for (TIOutput tioutput : tioutputArr) {
            if (tioutput.getOutputSynchronizationSource() == j) {
                arrayList.add(tioutput);
            }
        }
        return outputArrayFromList(arrayList);
    }

    /* access modifiers changed from: protected */
    public boolean getInputSourceMuted(TIOutput[] tioutputArr) {
        for (TIOutput inputSourceMuted : tioutputArr) {
            if (getInputSourceMuted(inputSourceMuted)) {
                return true;
            }
        }
        return false;
    }

    public boolean getInputSynchronizationDisabled() {
        return this._inputSynchronizationDisabled;
    }

    public long[] getInputSynchronizationSources() {
        ArrayList arrayList = new ArrayList();
        for (IMediaOutput outputSynchronizationSource : getInputs()) {
            long outputSynchronizationSource2 = outputSynchronizationSource.getOutputSynchronizationSource();
            if (outputSynchronizationSource2 != -1 && !equivalentLongExists(outputSynchronizationSource2, arrayList)) {
                arrayList.add(Long.valueOf(outputSynchronizationSource2));
            }
        }
        return Utility.toLongArray(arrayList);
    }

    public int getInputTargetOutputBitrate() {
        return getBitrateSum(getInputTargetOutputEncodings());
    }

    public EncodingInfo[] getInputTargetOutputEncodings() {
        return getInputTargetOutputEncodings(getInputs());
    }

    private EncodingInfo[] getInputTargetOutputEncodings(TIOutput[] tioutputArr) {
        EncodingInfo targetOutputEncoding;
        boolean z;
        ArrayList arrayList = new ArrayList();
        for (TIOutput tioutput : tioutputArr) {
            MediaFormat outputFormat = tioutput.getOutputFormat();
            if ((outputFormat == null || !outputFormat.getIsInjected()) && (targetOutputEncoding = tioutput.getTargetOutputEncoding()) != null) {
                targetOutputEncoding.setRtpStreamId(getLocalRtpStreamId(tioutput.getOutputSynchronizationSource()));
                if (outputFormat == null || !outputFormat.getIsFixedBitrate()) {
                    z = false;
                } else {
                    z = false;
                    for (int i = 0; i < ArrayListExtensions.getCount(arrayList); i++) {
                        if (!((((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getSynchronizationSource() == -1 || targetOutputEncoding.getSynchronizationSource() == -1 || targetOutputEncoding.getSynchronizationSource() != ((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getSynchronizationSource()) && (((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getRtpStreamId() == null || targetOutputEncoding.getRtpStreamId() == null || !Global.equals(targetOutputEncoding.getRtpStreamId(), ((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getRtpStreamId())))) {
                            z = true;
                        }
                    }
                }
                if (!z && !equivalentEncodingExists(targetOutputEncoding, arrayList)) {
                    arrayList.add(targetOutputEncoding);
                }
            }
        }
        EncodingInfo[] merge = EncodingInfo.merge((EncodingInfo[]) arrayList.toArray(new EncodingInfo[0]));
        if (ArrayExtensions.getLength((Object[]) merge) <= 1 || !Global.equals(getSimulcastMode(), SimulcastMode.Disabled)) {
            return merge;
        }
        return new EncodingInfo[]{merge[0]};
    }

    public JitterConfig getJitterConfig() {
        return this._jitterConfig;
    }

    public boolean getLegacyReceiver() {
        return this._legacyReceiver;
    }

    public String getLocalDescriptionMediaId() {
        return this._localDescriptionMediaId;
    }

    public String getLocalDescriptionTrackId() {
        return this._localDescriptionTrackId;
    }

    public LocalMedia getLocalMedia() {
        return this.__localMedia;
    }

    /* access modifiers changed from: protected */
    public String getLocalRtpStreamId(long j) {
        long[] localSynchronizationSources;
        String[] localRtpStreamIds;
        RtpParameters rtpParameters = getRtpParameters();
        if (rtpParameters == null || (localSynchronizationSources = rtpParameters.getLocalSynchronizationSources()) == null || (localRtpStreamIds = rtpParameters.getLocalRtpStreamIds()) == null || ArrayExtensions.getLength(localSynchronizationSources) != ArrayExtensions.getLength((Object[]) localRtpStreamIds)) {
            return null;
        }
        for (int i = 0; i < ArrayExtensions.getLength(localSynchronizationSources); i++) {
            if (localSynchronizationSources[i] == j) {
                return localRtpStreamIds[i];
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public long getLocalSynchronizationSource(String str) {
        long[] localSynchronizationSources;
        String[] localRtpStreamIds;
        RtpParameters rtpParameters = getRtpParameters();
        if (rtpParameters == null || (localSynchronizationSources = rtpParameters.getLocalSynchronizationSources()) == null || (localRtpStreamIds = rtpParameters.getLocalRtpStreamIds()) == null || ArrayExtensions.getLength(localSynchronizationSources) != ArrayExtensions.getLength((Object[]) localRtpStreamIds)) {
            return -1;
        }
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) localRtpStreamIds); i++) {
            if (Global.equals(localRtpStreamIds[i], str)) {
                return localSynchronizationSources[i];
            }
        }
        return -1;
    }

    public TTrack getLocalTrack() {
        return this._localTrack;
    }

    public int getMaxInputBitrate() {
        return this.__maxInputBitrate;
    }

    public EncodingInfo getMaxInputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setRtpStreamId(getInputRtpStreamId());
        encodingInfo.setSynchronizationSource(getInputSynchronizationSource());
        encodingInfo.setDeactivated(getInputDeactivated());
        encodingInfo.setBitrate(getMaxInputBitrate());
        return encodingInfo;
    }

    public int getMaxOutputBitrate() {
        return ConstraintUtility.min(ConstraintUtility.min(-1, super.getLocalBandwidth()), getMaxReceiveBitrate());
    }

    public EncodingInfo getMaxOutputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setSynchronizationSource(getOutputSynchronizationSource());
        encodingInfo.setDeactivated(getOutputDeactivated());
        encodingInfo.setBitrate(getMaxOutputBitrate());
        return encodingInfo;
    }

    public int getMaxReceiveBitrate() {
        return super.getMaxReceiveBitrate();
    }

    /* access modifiers changed from: package-private */
    public MediaDescriptionManagerBase getMediaDescriptionManager() {
        return this.__mediaStreamMediaDescriptionManager;
    }

    public EncodingInfo getMinInputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setRtpStreamId(getInputRtpStreamId());
        encodingInfo.setSynchronizationSource(getInputSynchronizationSource());
        encodingInfo.setDeactivated(getInputDeactivated());
        encodingInfo.setBitrate(getMinInputBitrate());
        return encodingInfo;
    }

    public EncodingInfo getMinOutputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setSynchronizationSource(getOutputSynchronizationSource());
        encodingInfo.setDeactivated(getOutputDeactivated());
        encodingInfo.setBitrate(getMinOutputBitrate());
        return encodingInfo;
    }

    public boolean getMultiplexed() {
        return super.getCoreTransportRtcp() == null;
    }

    public boolean getMultiplexingSupported() {
        return this._multiplexingSupported;
    }

    public NackConfig getNackConfig() {
        boolean z;
        if (this.__nackConfig == null) {
            if (Global.equals(super.getType(), StreamType.Audio)) {
                z = true;
            } else {
                z = !this.__startedOriginalNegotiation ? Global.equals(getNackPolicy(), NackPolicy.Disabled) : false;
            }
            NackConfig nackConfig = new NackConfig();
            nackConfig.setDisableBuffering(z);
            this.__nackConfig = nackConfig;
            if (z) {
                nackConfig.setReceiveBufferLength(0);
                this.__nackConfig.setSendBufferLength(0);
            }
        }
        return this.__nackConfig;
    }

    public boolean getNackEnabled() {
        return !getNackConfig().getDisableBuffering();
    }

    public boolean getNackPliEnabled() {
        return this.__mediaStreamMediaDescriptionManager.getNackPliEnabled();
    }

    public NackPliPolicy getNackPliPolicy() {
        return this.__nackPliPolicy;
    }

    public NackPolicy getNackPolicy() {
        return this.__nackPolicy;
    }

    private TIInput getOrMapOutput(TIInput[] tiinputArr, long j, String str, TFormat tformat) {
        mapRemoteRtpStreamIdToSynchronizationSource(tiinputArr, j, str);
        TIInput output = getOutput(tiinputArr, j, tformat);
        if (output == null) {
            synchronized (this.__remoteSynchronizationSourceLock) {
                int length = tiinputArr.length;
                int i = 0;
                while (i < length) {
                    TIInput tiinput = tiinputArr[i];
                    if (tiinput.getInputSynchronizationSource() != -1 || !tformat.isCompatible(tiinput.getInputFormat())) {
                        i++;
                    } else {
                        if (str != null && __log.getIsWarnEnabled()) {
                            __log.warn(StringExtensions.format("Remote RTP stream ID {0} and remote synchronization source {1} were not previously mapped.", str, LongExtensions.toString(Long.valueOf(j))));
                        }
                        if (__log.getIsInfoEnabled()) {
                            __log.info(StringExtensions.format("Mapping remote synchronization source {0} to {1}.", LongExtensions.toString(Long.valueOf(j)), tiinput.getInputFormat().toString()));
                        }
                        tiinput.setInputSynchronizationSource(j);
                        return tiinput;
                    }
                }
            }
        }
        return output;
    }

    public TIInput getOutput() {
        return (IMediaInput) this.__outputs.getValue();
    }

    private TIInput getOutput(TIInput[] tiinputArr, long j, TFormat tformat) {
        for (TIInput tiinput : tiinputArr) {
            if (tiinput.getInputSynchronizationSource() == j && tformat.isCompatible(tiinput.getInputFormat())) {
                return tiinput;
            }
        }
        return null;
    }

    public boolean getOutputDeactivated() {
        StreamDirection direction = getDirection();
        return Global.equals(direction, StreamDirection.Unset) || Global.equals(direction, StreamDirection.Inactive) || Global.equals(direction, StreamDirection.SendOnly);
    }

    public TFormat getOutputFormat() {
        return this._outputFormat;
    }

    public int getOutputMaxInputBitrate() {
        return getBitrateSum(getOutputMaxInputEncodings());
    }

    public EncodingInfo[] getOutputMaxInputEncodings() {
        return getOutputMaxInputEncodings(getOutputs());
    }

    private EncodingInfo[] getOutputMaxInputEncodings(TIInput[] tiinputArr) {
        EncodingInfo maxInputEncoding;
        boolean z;
        ArrayList arrayList = new ArrayList();
        for (TIInput tiinput : tiinputArr) {
            MediaFormat inputFormat = tiinput.getInputFormat();
            if ((inputFormat == null || !inputFormat.getIsInjected()) && (maxInputEncoding = tiinput.getMaxInputEncoding()) != null) {
                if (inputFormat == null || !inputFormat.getIsFixedBitrate()) {
                    z = false;
                } else {
                    z = false;
                    for (int i = 0; i < ArrayListExtensions.getCount(arrayList); i++) {
                        if (!((((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getSynchronizationSource() == -1 || maxInputEncoding.getSynchronizationSource() == -1 || maxInputEncoding.getSynchronizationSource() != ((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getSynchronizationSource()) && (((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getRtpStreamId() == null || maxInputEncoding.getRtpStreamId() == null || !Global.equals(maxInputEncoding.getRtpStreamId(), ((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getRtpStreamId())))) {
                            z = true;
                        }
                    }
                }
                if (!z && !equivalentEncodingExists(maxInputEncoding, arrayList)) {
                    arrayList.add(maxInputEncoding);
                }
            }
        }
        EncodingInfo[] merge = EncodingInfo.merge((EncodingInfo[]) arrayList.toArray(new EncodingInfo[0]));
        if (ArrayExtensions.getLength((Object[]) merge) <= 1 || !Global.equals(getSimulcastMode(), SimulcastMode.Disabled)) {
            return merge;
        }
        return new EncodingInfo[]{merge[0]};
    }

    public int getOutputMinInputBitrate() {
        return getBitrateSum(getOutputMinInputEncodings());
    }

    public EncodingInfo[] getOutputMinInputEncodings() {
        return getOutputMinInputEncodings(getOutputs());
    }

    private EncodingInfo[] getOutputMinInputEncodings(TIInput[] tiinputArr) {
        EncodingInfo minInputEncoding;
        boolean z;
        ArrayList arrayList = new ArrayList();
        for (TIInput tiinput : tiinputArr) {
            MediaFormat inputFormat = tiinput.getInputFormat();
            if ((inputFormat == null || !inputFormat.getIsInjected()) && (minInputEncoding = tiinput.getMinInputEncoding()) != null) {
                if (inputFormat == null || !inputFormat.getIsFixedBitrate()) {
                    z = false;
                } else {
                    z = false;
                    for (int i = 0; i < ArrayListExtensions.getCount(arrayList); i++) {
                        if (!((((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getSynchronizationSource() == -1 || minInputEncoding.getSynchronizationSource() == -1 || minInputEncoding.getSynchronizationSource() != ((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getSynchronizationSource()) && (((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getRtpStreamId() == null || minInputEncoding.getRtpStreamId() == null || !Global.equals(minInputEncoding.getRtpStreamId(), ((EncodingInfo) ArrayListExtensions.getItem(arrayList).get(i)).getRtpStreamId())))) {
                            z = true;
                        }
                    }
                }
                if (!z && !equivalentEncodingExists(minInputEncoding, arrayList)) {
                    arrayList.add(minInputEncoding);
                }
            }
        }
        EncodingInfo[] merge = EncodingInfo.merge((EncodingInfo[]) arrayList.toArray(new EncodingInfo[0]));
        if (ArrayExtensions.getLength((Object[]) merge) <= 1 || !Global.equals(getSimulcastMode(), SimulcastMode.Disabled)) {
            return merge;
        }
        return new EncodingInfo[]{merge[0]};
    }

    public boolean getOutputMuted() {
        return getOutputSinkMuted((TIInput[]) getOutputs());
    }

    public TIInput[] getOutputs() {
        return (IMediaInput[]) this.__outputs.getValues();
    }

    private TIInput[] getOutputs(TIInput[] tiinputArr, String str) {
        ArrayList arrayList = new ArrayList();
        for (TIInput tiinput : tiinputArr) {
            if (Global.equals(tiinput.getInputRtpStreamId(), str)) {
                arrayList.add(tiinput);
            }
        }
        return inputArrayFromList(arrayList);
    }

    private TIInput[] getOutputs(TIInput[] tiinputArr, long j) {
        ArrayList arrayList = new ArrayList();
        for (TIInput tiinput : tiinputArr) {
            if (tiinput.getInputSynchronizationSource() == j) {
                arrayList.add(tiinput);
            }
        }
        return inputArrayFromList(arrayList);
    }

    /* access modifiers changed from: protected */
    public boolean getOutputSinkMuted(TIInput[] tiinputArr) {
        for (TIInput outputSinkMuted : tiinputArr) {
            if (getOutputSinkMuted(outputSinkMuted)) {
                return true;
            }
        }
        return false;
    }

    public boolean getOutputSynchronizable() {
        return this._outputSynchronizable;
    }

    public boolean getOutputSynchronizationDisabled() {
        return this._outputSynchronizationDisabled;
    }

    public long[] getOutputSynchronizationSources() {
        ArrayList arrayList = new ArrayList();
        for (IMediaInput inputSynchronizationSource : getOutputs()) {
            long inputSynchronizationSource2 = inputSynchronizationSource.getInputSynchronizationSource();
            if (inputSynchronizationSource2 != -1 && !equivalentLongExists(inputSynchronizationSource2, arrayList)) {
                arrayList.add(Long.valueOf(inputSynchronizationSource2));
            }
        }
        return Utility.toLongArray(arrayList);
    }

    public boolean getOverConstrained() {
        return getOverConstrainedInput() || getOverConstrainedOutput();
    }

    public boolean getOverConstrainedBitrate() {
        return getOverConstrainedInputBitrate() || getOverConstrainedOutputBitrate();
    }

    public boolean getOverConstrainedInput() {
        return getOverConstrainedInputBitrate();
    }

    public boolean getOverConstrainedInputBitrate() {
        return ConstraintUtility.overConstrained(getMinInputBitrate(), getMaxInputBitrate());
    }

    public boolean getOverConstrainedOutput() {
        return getOverConstrainedOutputBitrate();
    }

    public boolean getOverConstrainedOutputBitrate() {
        return ConstraintUtility.overConstrained(getMinOutputBitrate(), getMaxOutputBitrate());
    }

    public boolean getPaused() {
        return this.__paused;
    }

    public String getPipelineJson() {
        return StringExtensions.concat((Object[]) new String[]{"{ ", getPipelineJsonBase(), ", ", getPipelineJsonInputs(), ", ", getPipelineJsonOutputs(), " }"});
    }

    private String getPipelineJsonBase() {
        return StringExtensions.concat((Object[]) new String[]{getPipelineJsonId(), ", ", getPipelineJsonLabel(), ", ", getPipelineJsonTag(), ", ", getPipelineJsonDisabled(), ", ", getPipelineJsonInput(), ", ", getPipelineJsonOutput()});
    }

    private String getPipelineJsonDisabled() {
        return StringExtensions.concat("\"disabled\": ", super.getDisabled() ? "true" : "false");
    }

    public String getPipelineJsonFromInput() {
        return StringExtensions.concat((Object[]) new String[]{"{ ", getPipelineJsonBase(), ", ", getPipelineJsonInputs(), " }"});
    }

    public String getPipelineJsonFromOutput() {
        return StringExtensions.concat((Object[]) new String[]{"{ ", getPipelineJsonBase(), ", ", getPipelineJsonOutputs(), " }"});
    }

    private String getPipelineJsonId() {
        return StringExtensions.concat("\"id\": ", JsonSerializer.serializeString(super.getId()));
    }

    private String getPipelineJsonInput() {
        return StringExtensions.concat("\"inputFormat\": ", getInputFormat() == null ? "null" : JsonSerializer.serializeString(getInputFormat().toString()));
    }

    private String getPipelineJsonInputs() {
        ArrayList arrayList = new ArrayList();
        for (IMediaOutput pipelineJsonFromOutput : getInputs()) {
            arrayList.add(pipelineJsonFromOutput.getPipelineJsonFromOutput());
        }
        return StringExtensions.concat("\"inputs\": [", StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0])), "]");
    }

    private String getPipelineJsonLabel() {
        return StringExtensions.concat("\"label\": ", JsonSerializer.serializeString(getLabel()));
    }

    private String getPipelineJsonOutput() {
        return StringExtensions.concat("\"outputFormat\": ", getOutputFormat() == null ? "null" : JsonSerializer.serializeString(getOutputFormat().toString()));
    }

    private String getPipelineJsonOutputs() {
        ArrayList arrayList = new ArrayList();
        for (IMediaInput pipelineJsonFromInput : getOutputs()) {
            arrayList.add(pipelineJsonFromInput.getPipelineJsonFromInput());
        }
        return StringExtensions.concat("\"outputs\": [", StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0])), "]");
    }

    private String getPipelineJsonTag() {
        return StringExtensions.concat("\"tag\": ", JsonSerializer.serializeString(super.getTag()));
    }

    public long getPipelineSystemDelay(TFormat tformat) {
        for (IMediaInput iMediaInput : getOutputs()) {
            MediaFormat inputFormat = iMediaInput.getInputFormat();
            if (inputFormat != null && inputFormat.isCompatible(tformat)) {
                return getSystemDelay() + iMediaInput.getPipelineSystemDelay(tformat);
            }
        }
        return getSystemDelay();
    }

    /* access modifiers changed from: package-private */
    public TFormat getPreferredFormat() {
        for (TFormat tformat : getRtpParameters().getNegotiatedFormats()) {
            if (!tformat.getIsInjected()) {
                return tformat;
            }
        }
        return null;
    }

    public ProcessFramePolicy getProcessFramePolicy() {
        return this.__processPolicy;
    }

    private EncodingInfo[] getReceiveEncodings(TIInput[] tiinputArr) {
        return getOutputMaxInputEncodings(tiinputArr);
    }

    private FormatInfo[] getReceiveFormats(TIInput[] tiinputArr) {
        ArrayList arrayList = new ArrayList();
        for (TIInput inputFormat : tiinputArr) {
            MediaFormat inputFormat2 = inputFormat.getInputFormat();
            if (inputFormat2 != null) {
                FormatInfo info = inputFormat2.getInfo();
                if (!equivalentFormatExists(info, arrayList)) {
                    arrayList.add(info);
                }
            }
        }
        return (FormatInfo[]) arrayList.toArray(new FormatInfo[0]);
    }

    public RedFecConfig getRedFecConfig() {
        if (this.__redFecConfig == null) {
            boolean z = false;
            if (Global.equals(super.getType(), StreamType.Audio)) {
                z = true;
            } else if (!this.__startedOriginalNegotiation) {
                z = Global.equals(getRedFecPolicy(), RedFecPolicy.Disabled);
            }
            RedFecConfig redFecConfig = new RedFecConfig();
            redFecConfig.setDisabled(z);
            this.__redFecConfig = redFecConfig;
        }
        return this.__redFecConfig;
    }

    public boolean getRedFecEnabled() {
        return !getRedFecConfig().getDisabled();
    }

    public RedFecPolicy getRedFecPolicy() {
        return this.__redFecPolicy;
    }

    private TFormat getRegisteredFormat(int i) {
        Iterator<TFormat> it = this.__payloadTypeRegistry.iterator();
        while (it.hasNext()) {
            TFormat tformat = (MediaFormat) it.next();
            if (tformat.getRegisteredPayloadType() == i) {
                return tformat;
            }
        }
        return null;
    }

    public boolean getRembEnabled() {
        return this.__mediaStreamMediaDescriptionManager.getRembEnabled();
    }

    public RembPolicy getRembPolicy() {
        return this.__rembPolicy;
    }

    public String getRemoteDescriptionMediaId() {
        return this.__mediaStreamMediaDescriptionManager.getRemoteDescriptionMediaId();
    }

    public String getRemoteDescriptionTrackId() {
        return this.__mediaStreamMediaDescriptionManager.getRemoteDescriptionTrackId();
    }

    public EncodingInfo getRemoteEncoding() {
        return super.getRemoteEncoding();
    }

    public RemoteMedia getRemoteMedia() {
        return this._remoteMedia;
    }

    /* access modifiers changed from: protected */
    public String getRemoteRtpStreamId(long j) {
        for (IMediaInput iMediaInput : getOutputs()) {
            if (iMediaInput.getInputSynchronizationSource() == j && iMediaInput.getInputRtpStreamId() != null) {
                return iMediaInput.getInputRtpStreamId();
            }
        }
        return null;
    }

    private boolean getRemoteSupportsCcmFir() {
        return this.__mediaStreamMediaDescriptionManager.getRemoteSupportsCcmFir();
    }

    private boolean getRemoteSupportsCcmLrr() {
        return this.__mediaStreamMediaDescriptionManager.getRemoteSupportsCcmLrr();
    }

    private boolean getRemoteSupportsCcmTmmbn() {
        return this.__mediaStreamMediaDescriptionManager.getRemoteSupportsCcmTmmbn();
    }

    private boolean getRemoteSupportsCcmTmmbr() {
        return this.__mediaStreamMediaDescriptionManager.getRemoteSupportsCcmTmmbr();
    }

    private boolean getRemoteSupportsNackPli() {
        return this.__mediaStreamMediaDescriptionManager.getRemoteSupportsNackPli();
    }

    /* access modifiers changed from: protected */
    public long getRemoteSynchronizationSource(String str) {
        for (IMediaInput iMediaInput : getOutputs()) {
            if (Global.equals(iMediaInput.getInputRtpStreamId(), str) && iMediaInput.getInputSynchronizationSource() != -1) {
                return iMediaInput.getInputSynchronizationSource();
            }
        }
        return -1;
    }

    public TTrack getRemoteTrack() {
        return this._remoteTrack;
    }

    /* access modifiers changed from: package-private */
    public RtpHeaderExtensionRegistry getRtpHeaderExtensionRegistry() {
        return this.__rtpHeaderExtensionRegistry;
    }

    /* access modifiers changed from: package-private */
    public RtpParameters<TFormat, TFormatCollection> getRtpParameters() {
        return this._rtpParameters;
    }

    /* access modifiers changed from: package-private */
    public RtpTransport<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getRtpTransport() {
        return this.__rtpTransport;
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesMidDirection() {
        return this.__mediaStreamMediaDescriptionManager.getSdesMidDirection();
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesMidLocalDirection() {
        return this.__sdesMidLocalDirection;
    }

    /* access modifiers changed from: package-private */
    public MediaHeaderExtensionPolicy getSdesMidPolicy() {
        return this._sdesMidPolicy;
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesMidRemoteDirection() {
        return this.__mediaStreamMediaDescriptionManager.getSdesMidRemoteDirection();
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesRepairedRtpStreamIdDirection() {
        return this.__mediaStreamMediaDescriptionManager.getSdesRepairedRtpStreamIdDirection();
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesRepairedRtpStreamIdLocalDirection() {
        return this.__sdesRepairedRtpStreamIdLocalDirection;
    }

    /* access modifiers changed from: package-private */
    public MediaHeaderExtensionPolicy getSdesRepairedRtpStreamIdPolicy() {
        return this._sdesRepairedRtpStreamIdPolicy;
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesRepairedRtpStreamIdRemoteDirection() {
        return this.__mediaStreamMediaDescriptionManager.getSdesRepairedRtpStreamIdRemoteDirection();
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesRtpStreamIdDirection() {
        return this.__mediaStreamMediaDescriptionManager.getSdesRtpStreamIdDirection();
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesRtpStreamIdLocalDirection() {
        return this.__sdesRtpStreamIdLocalDirection;
    }

    /* access modifiers changed from: package-private */
    public MediaHeaderExtensionPolicy getSdesRtpStreamIdPolicy() {
        return this._sdesRtpStreamIdPolicy;
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesRtpStreamIdRemoteDirection() {
        return this.__mediaStreamMediaDescriptionManager.getSdesRtpStreamIdRemoteDirection();
    }

    private EncodingInfo[] getSendEncodings(TIOutput[] tioutputArr) {
        EncodingInfo[] inputMaxOutputEncodings = getInputMaxOutputEncodings(tioutputArr);
        EncodingInfo[] inputTargetOutputEncodings = getInputTargetOutputEncodings(tioutputArr);
        if (inputMaxOutputEncodings == null && inputTargetOutputEncodings == null) {
            return null;
        }
        if (inputMaxOutputEncodings == null) {
            return inputTargetOutputEncodings;
        }
        if (inputTargetOutputEncodings == null) {
            return inputMaxOutputEncodings;
        }
        EncodingInfo[] encodingInfoArr = new EncodingInfo[ArrayExtensions.getLength((Object[]) inputMaxOutputEncodings)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) inputMaxOutputEncodings); i++) {
            EncodingInfo encodingInfo = inputMaxOutputEncodings[i];
            encodingInfoArr[i] = EncodingInfo.max(encodingInfo, inputTargetOutputEncodings[i]);
            encodingInfoArr[i].setId(encodingInfo.getId());
            encodingInfoArr[i].setDeactivated(encodingInfo.getDeactivated());
            encodingInfoArr[i].setRtpStreamId(encodingInfo.getRtpStreamId());
            encodingInfoArr[i].setSynchronizationSource(encodingInfo.getSynchronizationSource());
        }
        return encodingInfoArr;
    }

    private FormatInfo[] getSendFormats(TIOutput[] tioutputArr) {
        ArrayList arrayList = new ArrayList();
        for (TIOutput outputFormat : tioutputArr) {
            MediaFormat outputFormat2 = outputFormat.getOutputFormat();
            if (outputFormat2 != null) {
                FormatInfo info = outputFormat2.getInfo();
                if (!equivalentFormatExists(info, arrayList)) {
                    arrayList.add(info);
                }
            }
        }
        return (FormatInfo[]) arrayList.toArray(new FormatInfo[0]);
    }

    /* access modifiers changed from: package-private */
    public int getSimulcastDraftVersion() {
        return this.__simulcastDraftVersion;
    }

    public SimulcastMode getSimulcastMode() {
        return this.__simulcastMode;
    }

    /* access modifiers changed from: package-private */
    public MediaStreamStats getStats() {
        RtpParameters rtpParameters;
        MediaFormat negotiatedFormat;
        IMediaInput output;
        char c;
        long[] jArr;
        int i;
        String[] strArr;
        String[] strArr2;
        RtpSender[] rtpSenderArr;
        RtpParameters rtpParameters2;
        MediaFormat negotiatedFormat2;
        int i2;
        char c2;
        long[] jArr2;
        String[] strArr3;
        String[] strArr4;
        MediaStreamStats mediaStreamStats = new MediaStreamStats();
        mediaStreamStats.setId(super.getId());
        mediaStreamStats.setTimestamp(DateExtensions.getUtcNow());
        mediaStreamStats.setDirection(getDirection());
        mediaStreamStats.setMaxSendBitrate(ConstraintUtility.min(getMaxSendBitrate(), super.getRemoteBandwidth()));
        mediaStreamStats.setMaxReceiveBitrate(ConstraintUtility.min(getMaxReceiveBitrate(), super.getLocalBandwidth()));
        mediaStreamStats.setType(super.getType());
        RtpTransport rtpTransport = getRtpTransport();
        if (rtpTransport != null) {
            long j = 0;
            int i3 = -1;
            if (Global.equals(getDirection(), StreamDirection.SendReceive) || Global.equals(getDirection(), StreamDirection.SendOnly)) {
                ArrayList arrayList = new ArrayList();
                RtpSender[] senders = rtpTransport.getSenders();
                int length = senders.length;
                int i4 = 0;
                while (i4 < length) {
                    RtpSender rtpSender = senders[i4];
                    IMediaOutput[] inputs = getInputs();
                    MediaSenderStats stats = rtpSender.getStats();
                    CodecStats codec = stats.getCodec();
                    if (codec == null || codec.getPayloadType() == i3 || (rtpParameters2 = getRtpParameters()) == null || (negotiatedFormat2 = rtpParameters2.getNegotiatedFormat(codec.getPayloadType())) == null) {
                        rtpSenderArr = senders;
                    } else {
                        rtpSenderArr = senders;
                        IMediaOutput input = getInput(inputs, rtpSender.getLocalSynchronizationSource(), negotiatedFormat2);
                        if (input != null) {
                            long localSynchronizationSource = rtpSender.getLocalSynchronizationSource();
                            String localRtpStreamId = rtpSender.getLocalRtpStreamId();
                            String localRepairedRtpStreamId = rtpSender.getLocalRepairedRtpStreamId();
                            MediaTrackStats mediaTrackStats = new MediaTrackStats();
                            mediaTrackStats.setId(input.getId());
                            mediaTrackStats.setTimestamp(DateExtensions.getUtcNow());
                            if (localSynchronizationSource < j) {
                                jArr2 = null;
                                c2 = 0;
                                i2 = 1;
                            } else {
                                i2 = 1;
                                c2 = 0;
                                jArr2 = new long[]{localSynchronizationSource};
                            }
                            mediaTrackStats.setSynchronizationSources(jArr2);
                            if (localRtpStreamId == null) {
                                strArr3 = null;
                            } else {
                                strArr3 = new String[i2];
                                strArr3[c2] = localRtpStreamId;
                            }
                            mediaTrackStats.setRtpStreamIds(strArr3);
                            if (localRepairedRtpStreamId == null) {
                                strArr4 = null;
                            } else {
                                strArr4 = new String[i2];
                                strArr4[c2] = localRepairedRtpStreamId;
                            }
                            mediaTrackStats.setRepairedRtpStreamIds(strArr4);
                            input.processTrackStatsFromOutput(mediaTrackStats);
                            stats.setTrack(mediaTrackStats);
                            MediaSourceStats mediaSourceStats = new MediaSourceStats();
                            input.processSourceStatsFromOutput(mediaSourceStats);
                            stats.setSource(mediaSourceStats);
                        }
                    }
                    arrayList.add(stats);
                    i4++;
                    senders = rtpSenderArr;
                    j = 0;
                    i3 = -1;
                }
                mediaStreamStats.setSenders((MediaSenderStats[]) arrayList.toArray(new MediaSenderStats[0]));
            }
            if (Global.equals(getDirection(), StreamDirection.SendReceive) || Global.equals(getDirection(), StreamDirection.ReceiveOnly)) {
                IMediaInput[] outputs = getOutputs();
                ArrayList arrayList2 = new ArrayList();
                for (RtpReceiver rtpReceiver : rtpTransport.getReceivers()) {
                    MediaReceiverStats stats2 = rtpReceiver.getStats();
                    CodecStats codec2 = stats2.getCodec();
                    if (codec2 != null && codec2.getPayloadType() != -1 && (rtpParameters = getRtpParameters()) != null && (negotiatedFormat = rtpParameters.getNegotiatedFormat(codec2.getPayloadType())) != null && (output = getOutput(outputs, rtpReceiver.getRemoteSynchronizationSource(), negotiatedFormat)) != null) {
                        long remoteSynchronizationSource = rtpReceiver.getRemoteSynchronizationSource();
                        String remoteRtpStreamId = rtpReceiver.getRemoteRtpStreamId();
                        String remoteRepairedRtpStreamId = rtpReceiver.getRemoteRepairedRtpStreamId();
                        MediaTrackStats mediaTrackStats2 = new MediaTrackStats();
                        mediaTrackStats2.setId(output.getId());
                        mediaTrackStats2.setTimestamp(DateExtensions.getUtcNow());
                        if (remoteSynchronizationSource < 0) {
                            i = 1;
                            jArr = null;
                            c = 0;
                        } else {
                            i = 1;
                            c = 0;
                            jArr = new long[]{remoteSynchronizationSource};
                        }
                        mediaTrackStats2.setSynchronizationSources(jArr);
                        if (remoteRtpStreamId == null) {
                            strArr = null;
                        } else {
                            strArr = new String[i];
                            strArr[c] = remoteRtpStreamId;
                        }
                        mediaTrackStats2.setRtpStreamIds(strArr);
                        if (remoteRepairedRtpStreamId == null) {
                            strArr2 = null;
                        } else {
                            strArr2 = new String[i];
                            strArr2[c] = remoteRepairedRtpStreamId;
                        }
                        mediaTrackStats2.setRepairedRtpStreamIds(strArr2);
                        output.processTrackStatsFromInput(mediaTrackStats2);
                        stats2.setTrack(mediaTrackStats2);
                        MediaSinkStats mediaSinkStats = new MediaSinkStats();
                        output.processSinkStatsFromInput(mediaSinkStats);
                        stats2.setSink(mediaSinkStats);
                    }
                    arrayList2.add(stats2);
                }
                mediaStreamStats.setReceivers((MediaReceiverStats[]) arrayList2.toArray(new MediaReceiverStats[0]));
            }
        }
        CoreTransport coreTransportRtp = super.getCoreTransportRtp();
        if (coreTransportRtp != null) {
            mediaStreamStats.setTransport(coreTransportRtp.getStats());
            CoreTransport coreTransportRtcp = super.getCoreTransportRtcp();
            if (coreTransportRtcp != null && !Global.equals(coreTransportRtcp, coreTransportRtp)) {
                mediaStreamStats.getTransport().setRtcpTransport(coreTransportRtcp.getStats());
            }
        }
        return mediaStreamStats;
    }

    public SynchronizeContext getSynchronizeContext() {
        return this._synchronizeContext;
    }

    public ISynchronizer[] getSynchronizers() {
        return findSynchronizers(getOutputs());
    }

    public long getSystemDelay() {
        return this._systemDelay;
    }

    public int getTargetOutputBitrate() {
        return getMaxOutputBitrate();
    }

    public EncodingInfo getTargetOutputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setSynchronizationSource(getOutputSynchronizationSource());
        encodingInfo.setDeactivated(getOutputDeactivated());
        encodingInfo.setBitrate(getTargetOutputBitrate());
        return encodingInfo;
    }

    public TransportInfo getTransportInfo() {
        if (super.getCoreTransportRtp() == null) {
            return null;
        }
        return super.getCoreTransportRtp().getInfo();
    }

    public void incrementCcmSequenceNumber() {
        this.__firSequenceNumber = (this.__firSequenceNumber + 1) % 256;
    }

    private void mapRemoteRtpStreamIdToSynchronizationSource(TIInput[] tiinputArr, long j, String str) {
        if (str != null) {
            for (TIInput tiinput : tiinputArr) {
                if (tiinput.getInputSynchronizationSource() == -1 && Global.equals(tiinput.getInputRtpStreamId(), str)) {
                    if (__log.getIsInfoEnabled()) {
                        __log.info(StringExtensions.format("Mapping remote RTP stream ID {0} and remote synchronization source {1} to {2}.", str, LongExtensions.toString(Long.valueOf(j)), tiinput.getInputFormat().toString()));
                    }
                    tiinput.setInputSynchronizationSource(j);
                }
            }
        }
    }

    public MediaStream(StreamType streamType, JitterConfig jitterConfig) {
        super(streamType);
        setMultiplexingSupported(true);
        setSdesRtpStreamIdPolicy(MediaHeaderExtensionPolicy.Disabled);
        setSdesRepairedRtpStreamIdPolicy(MediaHeaderExtensionPolicy.Disabled);
        setSimulcastMode(SimulcastMode.Disabled);
        setSdesMidPolicy(MediaHeaderExtensionPolicy.Disabled);
        setJitterConfig(jitterConfig);
        setRtpParameters(new RtpParameters(createMediaFormatCollection()));
        getRtpParameters().setRedFormat(createRedFormat());
        getRtpParameters().setUlpFecFormat(createUlpFecFormat());
        this.__outputs = createInputCollection(this);
        this.__inputs = createOutputCollection(this);
        this.__localFormatRegistry = createMediaFormatCollection();
        this.__remoteFormatRegistry = createMediaFormatCollection();
        setOutputSynchronizationDisabled(true);
        addOnDiscardOutboundControlFrame(new IActionDelegate1<MediaControlFrame>() {
            public String getId() {
                return "fm.liveswitch.MediaStream<TIOutput,TIOutputCollection,TIInput,TIInputCollection,TIElement,TSource,TSink,TPipe,TTrack,TBranch,TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.mediaStream_OnDiscardOutboundControlFrame";
            }

            public void invoke(MediaControlFrame mediaControlFrame) {
                MediaStream.this.mediaStream_OnDiscardOutboundControlFrame(mediaControlFrame);
            }
        });
    }

    /* access modifiers changed from: private */
    public void mediaStream_OnDiscardOutboundControlFrame(MediaControlFrame mediaControlFrame) {
        TmmbnEntry entry;
        if (mediaControlFrame instanceof TmmbrControlFrame) {
            TmmbrEntry entry2 = ((TmmbrControlFrame) mediaControlFrame).getEntry();
            if (entry2 != null) {
                long synchronizationSource = entry2.getSynchronizationSource();
                IAction1<BitrateRequest> iAction1 = this._onDiscardBitrateRequest;
                if (iAction1 != null) {
                    BitrateRequest bitrateRequest = new BitrateRequest();
                    bitrateRequest.setBitrate(entry2.getNormalizedMaximumBitrate());
                    bitrateRequest.setRtpStreamId(getRemoteRtpStreamId(synchronizationSource));
                    bitrateRequest.setMediaDescriptionId(super.getMediaStreamIdentification() != null ? super.getMediaStreamIdentification() : StringExtensions.toLower(super.getType().toString()));
                    bitrateRequest.setSynchronizationSource(synchronizationSource);
                    bitrateRequest.setSenderSynchronizationSource(getDefaultLocalSynchronizationSource());
                    iAction1.invoke(bitrateRequest);
                }
            }
        } else if ((mediaControlFrame instanceof TmmbnControlFrame) && (entry = ((TmmbnControlFrame) mediaControlFrame).getEntry()) != null) {
            long synchronizationSource2 = entry.getSynchronizationSource();
            IAction1<BitrateNotification> iAction12 = this._onDiscardBitrateNotification;
            if (iAction12 != null) {
                BitrateNotification bitrateNotification = new BitrateNotification();
                bitrateNotification.setBitrate(entry.getNormalizedMaximumBitrate());
                bitrateNotification.setRtpStreamId(getLocalRtpStreamId(synchronizationSource2));
                bitrateNotification.setMediaDescriptionId(super.getMediaStreamIdentification() != null ? super.getMediaStreamIdentification() : StringExtensions.toLower(super.getType().toString()));
                bitrateNotification.setSynchronizationSource(synchronizationSource2);
                iAction12.invoke(bitrateNotification);
            }
        }
    }

    private void monitorInputEncoding(final TIOutput[] tioutputArr, final long j) {
        synchronized (this.__encodingDisabledLock) {
            HashMapExtensions.set(HashMapExtensions.getItem(this.__encodingDisabled), Long.valueOf(j), Boolean.valueOf(allInputsDisabled(tioutputArr)));
        }
        AnonymousClass22 r2 = null;
        for (TIOutput tioutput : tioutputArr) {
            if (r2 == null) {
                r2 = new IAction0() {
                    public void invoke() {
                        boolean z;
                        if (!MediaStream.this.getIsTerminatingOrTerminated()) {
                            boolean access$2100 = MediaStream.this.allInputsDisabled(tioutputArr);
                            synchronized (MediaStream.this.__encodingDisabledLock) {
                                if (!Global.equals(Boolean.valueOf(access$2100), HashMapExtensions.getItem(MediaStream.this.__encodingDisabled).get(Long.valueOf(j)))) {
                                    HashMapExtensions.set(HashMapExtensions.getItem(MediaStream.this.__encodingDisabled), Long.valueOf(j), Boolean.valueOf(access$2100));
                                    z = true;
                                } else {
                                    z = false;
                                }
                            }
                            if (z) {
                                EncodingInfo maxOutputEncoding = tioutputArr[0].getMaxOutputEncoding();
                                maxOutputEncoding.setRtpStreamId(MediaStream.this.getLocalRtpStreamId(j));
                                if (access$2100) {
                                    MediaStream.this.raiseLocalEncodingDisabled(maxOutputEncoding);
                                    if (maxOutputEncoding.getRtpStreamId() == null) {
                                        MediaStream.__log.debug(StringExtensions.format("{0} stream encoding with SSRC {1} has paused.", MediaStream.this.getType().toString(), LongExtensions.toString(Long.valueOf(maxOutputEncoding.getSynchronizationSource()))));
                                    } else {
                                        MediaStream.__log.debug(StringExtensions.format("{0} stream encoding with SSRC {1} and RTP stream ID {2} has paused.", MediaStream.this.getType().toString(), LongExtensions.toString(Long.valueOf(maxOutputEncoding.getSynchronizationSource())), maxOutputEncoding.getRtpStreamId()));
                                    }
                                    MediaStream.this.processControlFrameResponse(TmmbnControlFrame.normalized(0, j));
                                } else {
                                    MediaStream.this.raiseLocalEncodingEnabled(maxOutputEncoding);
                                    if (maxOutputEncoding.getRtpStreamId() == null) {
                                        MediaStream.__log.debug(StringExtensions.format("{0} stream encoding with SSRC {1} has resumed.", MediaStream.this.getType().toString(), LongExtensions.toString(Long.valueOf(maxOutputEncoding.getSynchronizationSource()))));
                                    } else {
                                        MediaStream.__log.debug(StringExtensions.format("{0} stream encoding with SSRC {1} and RTP stream ID {2} has resumed.", MediaStream.this.getType().toString(), LongExtensions.toString(Long.valueOf(maxOutputEncoding.getSynchronizationSource())), maxOutputEncoding.getRtpStreamId()));
                                    }
                                    MediaStream.this.processControlFrameResponse(TmmbnControlFrame.normalized(maxOutputEncoding.getBitrate(), j));
                                }
                            }
                            MediaStream mediaStream = MediaStream.this;
                            mediaStream.setPaused(mediaStream.allInputsDisabled(mediaStream.getInputs()));
                        }
                    }
                };
            }
            tioutput.addOnDisabledChange(r2);
        }
    }

    private TFormatCollection obtainInputFormats() {
        TFormatCollection createMediaFormatCollection = createMediaFormatCollection();
        for (IMediaOutput iMediaOutput : getInputs()) {
            if (!createMediaFormatCollection.hasEquivalent(iMediaOutput.getOutputFormat())) {
                createMediaFormatCollection.add(iMediaOutput.getOutputFormat().clone());
            }
        }
        return createMediaFormatCollection;
    }

    private TFormatCollection obtainInputOutputFormatsIntersection() {
        TFormatCollection createMediaFormatCollection = createMediaFormatCollection();
        for (IMediaOutput iMediaOutput : getInputs()) {
            if (!createMediaFormatCollection.hasEquivalent(iMediaOutput.getOutputFormat())) {
                createMediaFormatCollection.add(iMediaOutput.getOutputFormat().clone());
            }
        }
        for (IMediaInput iMediaInput : getOutputs()) {
            if (!createMediaFormatCollection.hasEquivalent(iMediaInput.getInputFormat())) {
                createMediaFormatCollection.add(iMediaInput.getInputFormat().clone());
            }
        }
        return createMediaFormatCollection;
    }

    private TFormatCollection obtainLocalRemoteFormatsUnion() {
        TFormatCollection createMediaFormatCollection = createMediaFormatCollection();
        for (MediaFormat mediaFormat : (MediaFormat[]) this.__remoteFormatRegistry.getValues()) {
            MediaFormat equivalent = this.__localFormatRegistry.getEquivalent(mediaFormat);
            if (equivalent == null) {
                MediaFormat[] compatibles = this.__localFormatRegistry.getCompatibles(mediaFormat);
                int length = compatibles.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    MediaFormat mediaFormat2 = compatibles[i];
                    if (this.__remoteFormatRegistry.getByPayloadType(IntegerExtensions.toString(Integer.valueOf(mediaFormat2.getRegisteredPayloadType()))) != null) {
                        mediaFormat2.updateToCompatible(mediaFormat);
                        mediaFormat.updateToCompatible(mediaFormat2);
                        equivalent = mediaFormat2;
                        break;
                    }
                    i++;
                }
            }
            if (equivalent != null) {
                createMediaFormatCollection.add(equivalent);
            }
        }
        return createMediaFormatCollection;
    }

    private TFormatCollection obtainOutputFormats() {
        TFormatCollection createMediaFormatCollection = createMediaFormatCollection();
        for (IMediaInput iMediaInput : getOutputs()) {
            if (!createMediaFormatCollection.hasEquivalent(iMediaInput.getInputFormat())) {
                createMediaFormatCollection.add(iMediaInput.getInputFormat().clone());
            }
        }
        return createMediaFormatCollection;
    }

    private boolean payloadTypeIsRegistered(int i) {
        return getRegisteredFormat(i) != null;
    }

    /* access modifiers changed from: protected */
    public void populateInfo(MediaStreamInfo mediaStreamInfo) {
        IMediaOutput[] inputs = getInputs();
        if (ArrayExtensions.getLength((Object[]) inputs) > 0) {
            mediaStreamInfo.setSendFormats(getSendFormats(inputs));
            mediaStreamInfo.setSendEncodings(getSendEncodings(inputs));
        }
        IMediaInput[] outputs = getOutputs();
        if (ArrayExtensions.getLength((Object[]) outputs) > 0) {
            mediaStreamInfo.setReceiveFormats(getReceiveFormats(outputs));
            mediaStreamInfo.setReceiveEncodings(getReceiveEncodings(outputs));
        }
        Size size = this.__maxSendVideoSize;
        Size size2 = this.__maxReceiveVideoSize;
        if (size.getWidth() * size.getHeight() <= size2.getWidth() * size.getHeight()) {
            size = size2;
        }
        mediaStreamInfo.setMaxFrameWidth(size.getWidth());
        mediaStreamInfo.setMaxFrameHeight(size.getHeight());
        mediaStreamInfo.setLocalBandwidth(super.getLocalBandwidth());
        mediaStreamInfo.setRemoteBandwidth(super.getRemoteBandwidth());
    }

    /* access modifiers changed from: protected */
    public void processBundledStateChanged(boolean z) {
        super.processBundledStateChanged(z);
        RtpTransport rtpTransport = getRtpTransport();
        if (rtpTransport != null && z) {
            rtpTransport.setRtpTransport(super.getBundleCoreTransport().getIceTransport());
        }
    }

    /* access modifiers changed from: protected */
    public void processCachedSettings() {
        super.processCachedSettings();
        if (super.getRenegotiationPending() && !super.getBundled()) {
            setLocalDirection(super.getPendingLocalDirection());
        }
    }

    public void processControlFrame(MediaControlFrame mediaControlFrame) {
        processControlFrames(new MediaControlFrame[]{mediaControlFrame});
    }

    public void processControlFrameResponse(MediaControlFrame mediaControlFrame) {
        processControlFrameResponses(new MediaControlFrame[]{mediaControlFrame});
    }

    public void processControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        RtpTransport rtpTransport;
        RtpTransport rtpTransport2;
        if (Global.equals(getDirection(), StreamDirection.SendReceive) || Global.equals(getDirection(), StreamDirection.SendOnly)) {
            for (MediaControlFrame tryCast : mediaControlFrameArr) {
                TmmbnControlFrame tmmbnControlFrame = (TmmbnControlFrame) Global.tryCast(tryCast, TmmbnControlFrame.class);
                if (tmmbnControlFrame != null) {
                    for (TmmbnEntry tmmbnEntry : tmmbnControlFrame.getEntries()) {
                        if (tmmbnEntry.getNormalizedMaximumBitrate() == 0 && (rtpTransport2 = getRtpTransport()) != null && Global.equals(rtpTransport2.getState(), MediaTransportState.Started)) {
                            rtpTransport2.resetOutboundCurrentBitrate(tmmbnEntry.getSynchronizationSource());
                        }
                    }
                }
            }
            if (!getRemoteSupportsCcmTmmbn()) {
                mediaControlFrameArr = filterOutboundControlFrames(mediaControlFrameArr);
            }
            if (ArrayExtensions.getLength((Object[]) mediaControlFrameArr) > 0 && (rtpTransport = getRtpTransport()) != null && Global.equals(rtpTransport.getState(), MediaTransportState.Started)) {
                rtpTransport.sendControlFrameResponses(mediaControlFrameArr);
            }
        }
    }

    public void processControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        RtpTransport rtpTransport;
        int i;
        if (Global.equals(getDirection(), StreamDirection.SendReceive) || Global.equals(getDirection(), StreamDirection.ReceiveOnly)) {
            int length = mediaControlFrameArr.length;
            int i2 = 0;
            while (i2 < length) {
                StatControlFrame statControlFrame = (StatControlFrame) Global.tryCast(mediaControlFrameArr[i2], StatControlFrame.class);
                if (statControlFrame == null || !Global.equals(statControlFrame.getType(), StatControlFrameType.FrameDecoded)) {
                    i2++;
                } else {
                    long connectedTimestamp = super.getConnectedTimestamp();
                    if (connectedTimestamp == -1) {
                        i = 0;
                    } else {
                        i = (int) ((statControlFrame.getTimestamp() - connectedTimestamp) / ((long) Constants.getTicksPerMillisecond()));
                    }
                    __log.info(StringExtensions.format("{0} stream {1} for connection {2} took {3}ms to receive first frame.", new Object[]{super.getType().toString(), super.getId(), super.getConnectionId(), IntegerExtensions.toString(Integer.valueOf(i))}));
                    return;
                }
            }
            if (!getRemoteSupportsNackPli() || !getRemoteSupportsCcmFir() || !getRemoteSupportsCcmLrr() || !getRemoteSupportsCcmTmmbr()) {
                mediaControlFrameArr = filterOutboundControlFrames(mediaControlFrameArr);
            }
            if (ArrayExtensions.getLength((Object[]) mediaControlFrameArr) > 0 && (rtpTransport = getRtpTransport()) != null && Global.equals(rtpTransport.getState(), MediaTransportState.Started)) {
                rtpTransport.sendControlFrames(mediaControlFrameArr);
            }
        }
    }

    public boolean processFrame(TFrame tframe) {
        if (Global.equals(getProcessFramePolicy(), ProcessFramePolicy.Synchronous)) {
            return processFrameSync(tframe);
        }
        if (!Global.equals(getProcessFramePolicy(), ProcessFramePolicy.Asynchronous)) {
            return false;
        }
        for (MediaBuffer keep : tframe.getBuffers()) {
            keep.keep();
        }
        this.__dispatchQueue.enqueue(tframe);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean processFrameSync(TFrame tframe) {
        VideoFrame videoFrame;
        if (!Global.equals(getDirection(), StreamDirection.SendReceive) && !Global.equals(getDirection(), StreamDirection.SendOnly)) {
            return false;
        }
        if (!this.__monitoringInputEncodings) {
            for (long j : getInputSynchronizationSources()) {
                monitorInputEncoding(getInputs((TIOutput[]) getInputs(), j), j);
            }
            this.__monitoringInputEncodings = true;
        }
        tryUpdateMaxInputBitrate();
        setPaused(false);
        IAction1<TFrame> iAction1 = this._onProcessFrame;
        if (iAction1 != null) {
            iAction1.invoke(tframe);
        }
        if (tframe.getDiscard()) {
            return false;
        }
        if (Global.equals(super.getType(), StreamType.Video) && (videoFrame = (VideoFrame) Global.tryCast(tframe, VideoFrame.class)) != null) {
            updateMaxVideoSize(videoFrame, true);
        }
        try {
            this._processingFrame = true;
            RtpTransport rtpTransport = getRtpTransport();
            if (rtpTransport == null || !Global.equals(rtpTransport.getState(), MediaTransportState.Started)) {
                IAction1<TFrame> iAction12 = this._onProcessedFrame;
                if (iAction12 != null) {
                    try {
                        iAction12.invoke(tframe);
                    } catch (Exception e) {
                        __log.error(StringExtensions.format("Exception occurred while raising processed frame to the application code for media stream {0}.", (Object) super.getId()), e);
                    }
                }
                this._processingFrame = false;
                return false;
            }
            MediaFrame clone = tframe.clone();
            clone.setTimestamp(tframe.getTimestamp());
            clone.removeBuffers();
            int length = ArrayExtensions.getLength((Object[]) tframe.getBuffers()) - 1;
            while (true) {
                if (length < 0) {
                    break;
                }
                MediaBuffer mediaBuffer = tframe.getBuffers()[length];
                if (mediaBuffer.getFormat().getIsPacketized()) {
                    clone.addBuffer(mediaBuffer);
                    break;
                }
                length--;
            }
            if (ArrayExtensions.getLength((Object[]) clone.getBuffers()) == 0) {
                __log.error("No packetized formats in frame for stream to send.");
                IAction1<TFrame> iAction13 = this._onProcessedFrame;
                if (iAction13 != null) {
                    try {
                        iAction13.invoke(tframe);
                    } catch (Exception e2) {
                        __log.error(StringExtensions.format("Exception occurred while raising processed frame to the application code for media stream {0}.", (Object) super.getId()), e2);
                    }
                }
                this._processingFrame = false;
                return false;
            }
            if (this._sendRtpStreamIds) {
                String localRtpStreamId = getLocalRtpStreamId(clone.getSynchronizationSource());
                if (localRtpStreamId == null) {
                    this._sendRtpStreamIds = false;
                } else if (this._rtpStreamIdSendDuration == -1) {
                    clone.setRtpStreamId(localRtpStreamId);
                } else {
                    long timestamp = ManagedStopwatch.getTimestamp();
                    if (this._rtpStreamIdSendStartTimestamp == -1) {
                        this._rtpStreamIdSendStartTimestamp = timestamp;
                    }
                    if ((timestamp - this._rtpStreamIdSendStartTimestamp) / ((long) Constants.getTicksPerMillisecond()) > ((long) this._rtpStreamIdSendDuration)) {
                        this._sendRtpStreamIds = false;
                    } else {
                        clone.setRtpStreamId(localRtpStreamId);
                    }
                }
            }
            if (!this._sendRtpStreamIds) {
                clone.setRtpStreamId((String) null);
            }
            if (this._sendMids) {
                String mediaStreamIdentification = super.getMediaStreamIdentification();
                if (mediaStreamIdentification == null) {
                    this._sendMids = false;
                } else {
                    if (!Global.equals(getSdesMidDirection(), StreamDirection.SendReceive)) {
                        if (!Global.equals(getSdesMidDirection(), StreamDirection.SendOnly)) {
                            this._sendMids = false;
                        }
                    }
                    if (this._midSendDuration == -1) {
                        clone.setMid(mediaStreamIdentification);
                    } else {
                        long timestamp2 = ManagedStopwatch.getTimestamp();
                        if (this._midSendStartTimestamp == -1) {
                            this._midSendStartTimestamp = timestamp2;
                        }
                        if ((timestamp2 - this._midSendStartTimestamp) / ((long) Constants.getTicksPerMillisecond()) > ((long) this._midSendDuration)) {
                            this._sendMids = false;
                        } else {
                            clone.setMid(mediaStreamIdentification);
                        }
                    }
                }
            }
            if (!this._sendMids) {
                clone.setMid((String) null);
            }
            IAction1<TFrame> iAction14 = this._onSendFrame;
            if (iAction14 != null) {
                iAction14.invoke(clone);
            }
            rtpTransport.sendFrame(clone);
            setInjectionAllowed(true);
            IAction1<TFrame> iAction15 = this._onProcessedFrame;
            if (iAction15 != null) {
                try {
                    iAction15.invoke(tframe);
                } catch (Exception e3) {
                    __log.error(StringExtensions.format("Exception occurred while raising processed frame to the application code for media stream {0}.", (Object) super.getId()), e3);
                }
            }
            this._processingFrame = false;
            return true;
        } catch (Exception e4) {
            __log.error(StringExtensions.format("Exception occurred while raising sent frame to the application code for media stream {0}.", (Object) super.getId()), e4);
        } catch (Throwable th) {
            IAction1<TFrame> iAction16 = this._onProcessedFrame;
            if (iAction16 != null) {
                try {
                    iAction16.invoke(tframe);
                } catch (Exception e5) {
                    __log.error(StringExtensions.format("Exception occurred while raising processed frame to the application code for media stream {0}.", (Object) super.getId()), e5);
                }
            }
            this._processingFrame = false;
            throw th;
        }
    }

    private void processMaxReceiveBitrateUpdate(int i) {
        ArrayList arrayList = new ArrayList();
        for (IMediaInput inputSynchronizationSource : getOutputs()) {
            long inputSynchronizationSource2 = inputSynchronizationSource.getInputSynchronizationSource();
            if (inputSynchronizationSource2 != -1 && !arrayList.contains(Long.valueOf(inputSynchronizationSource2))) {
                processControlFrame(TmmbrControlFrame.normalized(i, inputSynchronizationSource2));
            }
        }
    }

    /* access modifiers changed from: private */
    public void processRtpTransportStateChange(IMediaTransport iMediaTransport) {
        if (Global.equals(iMediaTransport.getState(), MediaTransportState.Starting) && Global.equals(getSimulcastMode(), SimulcastMode.Disabled)) {
            long[] inputSynchronizationSources = getInputSynchronizationSources();
            if (ArrayExtensions.getLength(inputSynchronizationSources) > 1) {
                for (int i = 1; i < ArrayExtensions.getLength(inputSynchronizationSources); i++) {
                    raiseControlFrame(TmmbrControlFrame.normalized(0, inputSynchronizationSources[i]));
                }
            }
        }
        if (Global.equals(iMediaTransport.getState(), MediaTransportState.Started)) {
            super.raiseDisabledChange();
            setInputFormat();
        }
    }

    /* JADX WARNING: type inference failed for: r9v2, types: [fm.liveswitch.MediaFormat] */
    /* JADX WARNING: type inference failed for: r9v6 */
    /* JADX WARNING: type inference failed for: r9v9 */
    /* JADX WARNING: type inference failed for: r9v10 */
    /* JADX WARNING: type inference failed for: r9v11 */
    /* JADX WARNING: type inference failed for: r9v12 */
    /* JADX WARNING: type inference failed for: r9v13 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.Error processSdpMediaDescription(fm.liveswitch.sdp.Message r17, fm.liveswitch.sdp.MediaDescription r18, int r19, boolean r20, boolean r21, boolean r22) {
        /*
            r16 = this;
            r7 = r16
            r0 = r18
            r15 = r20
            r6 = r21
            fm.liveswitch.MediaStreamMediaDescriptionRequirements r9 = new fm.liveswitch.MediaStreamMediaDescriptionRequirements
            r9.<init>()
            fm.liveswitch.StreamDirection r1 = r16.getAbsoluteSenderTimeLocalDirection()
            r9.setAbsoluteSenderTimeLocalDirection(r1)
            fm.liveswitch.StreamDirection r1 = r16.getSdesMidLocalDirection()
            r9.setSdesMidLocalDirection(r1)
            fm.liveswitch.StreamDirection r1 = r16.getSdesRtpStreamIdLocalDirection()
            r9.setSdesRtpStreamIdLocalDirection(r1)
            fm.liveswitch.StreamDirection r1 = r16.getSdesRepairedRtpStreamIdLocalDirection()
            r9.setSdesRepairedRtpStreamIdLocalDirection(r1)
            boolean r1 = r16.getMultiplexingSupported()
            r9.setMultiplexingSupported(r1)
            fm.liveswitch.MediaHeaderExtensionPolicy r1 = r16.getAbsoluteSenderTimePolicy()
            r9.setAbsoluteSenderTimePolicy(r1)
            fm.liveswitch.MediaHeaderExtensionPolicy r1 = r16.getSdesMidPolicy()
            r9.setSdesMidPolicy(r1)
            fm.liveswitch.MediaHeaderExtensionPolicy r1 = r16.getSdesRtpStreamIdPolicy()
            r9.setSdesRtpStreamIdPolicy(r1)
            fm.liveswitch.MediaHeaderExtensionPolicy r1 = r16.getSdesRepairedRtpStreamIdPolicy()
            r9.setSdesRepairedRtpStreamIdPolicy(r1)
            fm.liveswitch.RembPolicy r1 = r7.__rembPolicy
            r9.setRembPolicy(r1)
            fm.liveswitch.NackPolicy r1 = r16.getNackPolicy()
            r9.setNackPolicy(r1)
            fm.liveswitch.NackPliPolicy r1 = r16.getNackPliPolicy()
            r9.setNackPliPolicy(r1)
            fm.liveswitch.CcmFirPolicy r1 = r16.getCcmFirPolicy()
            r9.setCcmFirPolicy(r1)
            fm.liveswitch.CcmLrrPolicy r1 = r16.getCcmLrrPolicy()
            r9.setCcmLrrPolicy(r1)
            fm.liveswitch.CcmTmmbrPolicy r1 = r16.getCcmTmmbrPolicy()
            r9.setCcmTmmbrPolicy(r1)
            fm.liveswitch.CcmTmmbnPolicy r1 = r16.getCcmTmmbnPolicy()
            r9.setCcmTmmbnPolicy(r1)
            fm.liveswitch.RedFecPolicy r1 = r16.getRedFecPolicy()
            r9.setRedFecPolicy(r1)
            fm.liveswitch.sdp.Media r1 = r18.getMedia()
            java.lang.String r1 = r1.getFormatDescription()
            int[] r1 = fm.liveswitch.sdp.rtp.Media.getPayloadTypes(r1)
            r5 = 0
            r2 = 0
            if (r15 == 0) goto L_0x0133
            r16.updateLocalDescriptionMediaId()
            r16.updateLocalDescriptionTrackId()
            fm.liveswitch.IMediaOutput[] r3 = r16.getInputs()
            int r4 = r3.length
            r8 = 0
        L_0x009e:
            if (r8 >= r4) goto L_0x00a8
            r10 = r3[r8]
            r10.processSdpMediaDescriptionFromOutput(r0, r6, r15)
            int r8 = r8 + 1
            goto L_0x009e
        L_0x00a8:
            fm.liveswitch.IMediaInput[] r3 = r16.getOutputs()
            int r4 = r3.length
            r8 = 0
        L_0x00ae:
            if (r8 >= r4) goto L_0x00b8
            r10 = r3[r8]
            r10.processSdpMediaDescriptionFromInput(r0, r6, r15)
            int r8 = r8 + 1
            goto L_0x00ae
        L_0x00b8:
            fm.liveswitch.sdp.rtp.MapAttribute[] r3 = r18.getRtpMapAttributes()
            int r4 = r3.length
            r8 = 0
        L_0x00be:
            if (r8 >= r4) goto L_0x00ea
            r10 = r3[r8]
            fm.liveswitch.sdp.FormatParametersAttribute r11 = r10.getRelatedFormatParametersAttribute()
            fm.liveswitch.MediaFormat r11 = r7.createFormat(r10, r11)
            boolean r11 = r7.validateMapAttribute(r11, r15)
            if (r11 != 0) goto L_0x00e7
            java.lang.String r0 = "Invalid local map attribute {0}."
            java.lang.String r1 = r10.toString()
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r0, (java.lang.Object) r1)
            fm.liveswitch.Error r1 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r2 = fm.liveswitch.ErrorCode.LocalDescriptionError
            java.lang.Exception r3 = new java.lang.Exception
            r3.<init>(r0)
            r1.<init>((fm.liveswitch.ErrorCode) r2, (java.lang.Exception) r3)
            return r1
        L_0x00e7:
            int r8 = r8 + 1
            goto L_0x00be
        L_0x00ea:
            fm.liveswitch.IMediaOutput[] r3 = r16.getInputs()
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r3)
            if (r4 <= 0) goto L_0x00f9
            fm.liveswitch.EncodingInfo[] r3 = r7.getSendEncodings(r3)
            goto L_0x00fa
        L_0x00f9:
            r3 = r5
        L_0x00fa:
            java.lang.String r4 = super.getLocalCanonicalName()
            r9.setCanonicalName(r4)
            java.lang.String r4 = r16.getLocalDescriptionMediaId()
            r9.setLocalDescriptionMediaId(r4)
            java.lang.String r4 = r16.getLocalDescriptionTrackId()
            r9.setLocalDescriptionTrackId(r4)
            fm.liveswitch.SimulcastMode r4 = r16.getSimulcastMode()
            r9.setSimulcastMode(r4)
            int r4 = r16.getSimulcastDraftVersion()
            r9.setSimulcastDraftVersion(r4)
            r9.setSendEncodings(r3)
            fm.liveswitch.EncodingInfo r3 = r16.getRemoteEncoding()
            if (r3 == 0) goto L_0x01b0
            r4 = 1
            fm.liveswitch.EncodingInfo[] r8 = new fm.liveswitch.EncodingInfo[r4]
            r8[r2] = r3
            r9.setReceiveEncodings(r8)
            r9.setMaxReceiveEncodings(r4)
            goto L_0x01b0
        L_0x0133:
            TFormatCollection r3 = r7.__remoteFormatRegistry
            r3.removeAll()
            fm.liveswitch.sdp.rtp.MapAttribute[] r3 = r18.getRtpMapAttributes()
            int r4 = r3.length
            r8 = 0
        L_0x013e:
            if (r8 >= r4) goto L_0x0158
            r10 = r3[r8]
            fm.liveswitch.sdp.FormatParametersAttribute r11 = r10.getRelatedFormatParametersAttribute()
            fm.liveswitch.MediaFormat r10 = r7.createFormat(r10, r11)
            boolean r11 = r7.validateMapAttribute(r10, r15)
            if (r11 == 0) goto L_0x0155
            TFormatCollection r11 = r7.__remoteFormatRegistry
            r11.add(r10)
        L_0x0155:
            int r8 = r8 + 1
            goto L_0x013e
        L_0x0158:
            fm.liveswitch.sdp.Media r3 = r18.getMedia()
            java.lang.String r3 = r3.getFormatDescription()
            int[] r3 = fm.liveswitch.sdp.rtp.Media.getPayloadTypes(r3)
            if (r3 == 0) goto L_0x0190
            int r4 = r3.length
            r8 = 0
        L_0x0168:
            if (r8 >= r4) goto L_0x0190
            r10 = r3[r8]
            fm.liveswitch.sdp.rtp.MapAttribute r10 = fm.liveswitch.sdp.rtp.MapAttribute.getIanaMapAttribute(r10)
            if (r10 == 0) goto L_0x018d
            fm.liveswitch.sdp.FormatParametersAttribute r11 = r10.getRelatedFormatParametersAttribute()
            fm.liveswitch.MediaFormat r10 = r7.createFormat(r10, r11)
            TFormatCollection r11 = r7.__remoteFormatRegistry
            fm.liveswitch.MediaFormat r11 = r11.getEquivalent(r10)
            if (r11 != 0) goto L_0x018d
            boolean r11 = r7.validateMapAttribute(r10, r15)
            if (r11 == 0) goto L_0x018d
            TFormatCollection r11 = r7.__remoteFormatRegistry
            r11.add(r10)
        L_0x018d:
            int r8 = r8 + 1
            goto L_0x0168
        L_0x0190:
            fm.liveswitch.IMediaOutput[] r3 = r16.getInputs()
            int r4 = r3.length
            r8 = 0
        L_0x0196:
            if (r8 >= r4) goto L_0x01a0
            r10 = r3[r8]
            r10.processSdpMediaDescriptionFromOutput(r0, r6, r15)
            int r8 = r8 + 1
            goto L_0x0196
        L_0x01a0:
            fm.liveswitch.IMediaInput[] r3 = r16.getOutputs()
            int r4 = r3.length
            r8 = 0
        L_0x01a6:
            if (r8 >= r4) goto L_0x01b0
            r10 = r3[r8]
            r10.processSdpMediaDescriptionFromInput(r0, r6, r15)
            int r8 = r8 + 1
            goto L_0x01a6
        L_0x01b0:
            super.populateProperties(r9)
            fm.liveswitch.MediaStreamMediaDescriptionManager<TFormat> r8 = r7.__mediaStreamMediaDescriptionManager
            r10 = r17
            r11 = r19
            r12 = r20
            r13 = r22
            r14 = r21
            fm.liveswitch.Error r8 = r8.processSdpMediaDescription(r9, r10, r11, r12, r13, r14)
            r16.extractPropertiesFromManager()
            fm.liveswitch.MediaStreamMediaDescriptionManager<TFormat> r3 = r7.__mediaStreamMediaDescriptionManager
            fm.liveswitch.StreamDirection r3 = r3.getRemoteDirection()
            fm.liveswitch.StreamDirection r4 = fm.liveswitch.StreamDirection.Unset
            boolean r3 = fm.liveswitch.Global.equals(r3, r4)
            if (r3 != 0) goto L_0x01dd
            fm.liveswitch.MediaStreamMediaDescriptionManager<TFormat> r3 = r7.__mediaStreamMediaDescriptionManager
            fm.liveswitch.StreamDirection r3 = r3.getRemoteDirection()
            r7.setRemoteDirection(r3)
        L_0x01dd:
            fm.liveswitch.MediaStreamMediaDescriptionManager<TFormat> r3 = r7.__mediaStreamMediaDescriptionManager
            int r3 = r3.getRemoteBandwidth()
            super.setRemoteBandwidth(r3)
            fm.liveswitch.MediaStreamMediaDescriptionManager<TFormat> r3 = r7.__mediaStreamMediaDescriptionManager
            long[] r9 = r3.getLocalSynchronizationSources()
            fm.liveswitch.MediaStreamMediaDescriptionManager<TFormat> r3 = r7.__mediaStreamMediaDescriptionManager
            long[] r10 = r3.getRemoteSynchronizationSources()
            fm.liveswitch.MediaStreamMediaDescriptionManager<TFormat> r3 = r7.__mediaStreamMediaDescriptionManager
            java.lang.String[] r11 = r3.getLocalSendRtpStreamIds()
            fm.liveswitch.MediaStreamMediaDescriptionManager<TFormat> r3 = r7.__mediaStreamMediaDescriptionManager
            java.lang.String[] r12 = r3.getRemoteSendRtpStreamIds()
            fm.liveswitch.CoreTransport r3 = super.getCoreTransportRtp()
            fm.liveswitch.RtpTransport r4 = r16.getRtpTransport()
            if (r3 == 0) goto L_0x0215
            if (r4 == 0) goto L_0x0215
            fm.liveswitch.BundleTransport r3 = r3.getBundleTransport()
            fm.liveswitch.RtpListener r13 = r4.getListener()
            r3.addRtpListener(r13, r9, r10, r1)
        L_0x0215:
            fm.liveswitch.CoreTransport r3 = super.getCoreTransportRtcp()
            if (r3 == 0) goto L_0x0228
            if (r4 == 0) goto L_0x0228
            fm.liveswitch.BundleTransport r3 = r3.getBundleTransport()
            fm.liveswitch.RtpListener r13 = r4.getListener()
            r3.addRtpListener(r13, r9, r10, r1)
        L_0x0228:
            fm.liveswitch.CoreTransport r3 = super.getBundleCoreTransport()
            if (r3 == 0) goto L_0x023b
            if (r3 == 0) goto L_0x023b
            fm.liveswitch.BundleTransport r3 = r3.getBundleTransport()
            fm.liveswitch.RtpListener r4 = r4.getListener()
            r3.addRtpListener(r4, r9, r10, r1)
        L_0x023b:
            if (r15 == 0) goto L_0x0294
            int r1 = fm.liveswitch.ArrayExtensions.getLength((long[]) r9)
            if (r1 <= 0) goto L_0x0273
            fm.liveswitch.IMediaOutput[] r10 = r16.getInputs()
            r12 = 0
        L_0x0248:
            int r1 = fm.liveswitch.ArrayExtensions.getLength((long[]) r9)
            if (r12 >= r1) goto L_0x026f
            r3 = r9[r12]
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r11)
            if (r12 >= r1) goto L_0x025a
            r1 = r11[r12]
            r13 = r1
            goto L_0x025b
        L_0x025a:
            r13 = r5
        L_0x025b:
            r14 = 0
            r1 = r16
            r2 = r10
            r17 = r9
            r9 = r5
            r5 = r13
            r13 = r6
            r6 = r14
            r1.updateLocalRtpParameters(r2, r3, r5, r6)
            int r12 = r12 + 1
            r5 = r9
            r6 = r13
            r9 = r17
            goto L_0x0248
        L_0x026f:
            r9 = r5
            r13 = r6
            goto L_0x02dd
        L_0x0273:
            r9 = r5
            r13 = r6
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r11)
            if (r1 <= 0) goto L_0x02dd
            fm.liveswitch.IMediaOutput[] r10 = r16.getInputs()
            r12 = 0
        L_0x0280:
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r11)
            if (r12 >= r1) goto L_0x02dd
            r3 = -1
            r5 = r11[r12]
            r6 = 0
            r1 = r16
            r2 = r10
            r1.updateLocalRtpParameters(r2, r3, r5, r6)
            int r12 = r12 + 1
            goto L_0x0280
        L_0x0294:
            r9 = r5
            r13 = r6
            int r1 = fm.liveswitch.ArrayExtensions.getLength((long[]) r10)
            if (r1 <= 0) goto L_0x02be
            fm.liveswitch.IMediaInput[] r11 = r16.getOutputs()
            r14 = 0
        L_0x02a1:
            int r1 = fm.liveswitch.ArrayExtensions.getLength((long[]) r10)
            if (r14 >= r1) goto L_0x02dd
            r3 = r10[r14]
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r12)
            if (r14 >= r1) goto L_0x02b3
            r1 = r12[r14]
            r5 = r1
            goto L_0x02b4
        L_0x02b3:
            r5 = r9
        L_0x02b4:
            r6 = 0
            r1 = r16
            r2 = r11
            r1.updateRemoteRtpParameters(r2, r3, r5, r6)
            int r14 = r14 + 1
            goto L_0x02a1
        L_0x02be:
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r12)
            if (r1 <= 0) goto L_0x02dd
            fm.liveswitch.IMediaInput[] r10 = r16.getOutputs()
            r11 = 0
        L_0x02c9:
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r12)
            if (r11 >= r1) goto L_0x02dd
            r3 = -1
            r5 = r12[r11]
            r6 = 0
            r1 = r16
            r2 = r10
            r1.updateRemoteRtpParameters(r2, r3, r5, r6)
            int r11 = r11 + 1
            goto L_0x02c9
        L_0x02dd:
            r7.setNegotiatedFormats(r0)
            java.util.ArrayList<TFormat> r1 = r7.__inputFormats
            monitor-enter(r1)
            java.util.ArrayList<TFormat> r2 = r7.__inputFormats     // Catch:{ all -> 0x0360 }
            fm.liveswitch.RtpParameters r3 = r16.getRtpParameters()     // Catch:{ all -> 0x0360 }
            fm.liveswitch.MediaFormat[] r3 = r3.getNegotiatedFormats()     // Catch:{ all -> 0x0360 }
            fm.liveswitch.ArrayListExtensions.addRange(r2, (T[]) r3)     // Catch:{ all -> 0x0360 }
            monitor-exit(r1)     // Catch:{ all -> 0x0360 }
            if (r13 == 0) goto L_0x02fa
            if (r15 == 0) goto L_0x02fa
            fm.liveswitch.StreamDirection r1 = r16.getLocalDirection()
            goto L_0x02fe
        L_0x02fa:
            fm.liveswitch.StreamDirection r1 = r16.getDirection()
        L_0x02fe:
            fm.liveswitch.StreamDirection r2 = fm.liveswitch.StreamDirection.ReceiveOnly
            boolean r2 = fm.liveswitch.Global.equals(r1, r2)
            if (r2 == 0) goto L_0x0313
            r7.setInputFormat(r9)
            fm.liveswitch.MediaFormat r1 = r16.getPreferredFormat()
            r7.setOutputFormat(r1)
        L_0x0310:
            r1 = r22
            goto L_0x0357
        L_0x0313:
            fm.liveswitch.StreamDirection r2 = fm.liveswitch.StreamDirection.SendOnly
            boolean r2 = fm.liveswitch.Global.equals(r1, r2)
            if (r2 == 0) goto L_0x0326
            fm.liveswitch.MediaFormat r1 = r16.getPreferredFormat()
            r7.setInputFormat(r1)
            r7.setOutputFormat(r9)
            goto L_0x0310
        L_0x0326:
            fm.liveswitch.StreamDirection r2 = fm.liveswitch.StreamDirection.SendReceive
            boolean r2 = fm.liveswitch.Global.equals(r1, r2)
            if (r2 == 0) goto L_0x033d
            fm.liveswitch.MediaFormat r1 = r16.getPreferredFormat()
            r7.setInputFormat(r1)
            fm.liveswitch.MediaFormat r1 = r16.getPreferredFormat()
            r7.setOutputFormat(r1)
            goto L_0x0310
        L_0x033d:
            fm.liveswitch.StreamDirection r2 = fm.liveswitch.StreamDirection.Inactive
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)
            if (r1 == 0) goto L_0x034c
            r7.setInputFormat(r9)
            r7.setOutputFormat(r9)
            goto L_0x0310
        L_0x034c:
            fm.liveswitch.MediaFormat r1 = r16.getPreferredFormat()
            r7.setInputFormat(r1)
            r7.setOutputFormat(r9)
            goto L_0x0310
        L_0x0357:
            super.updateProperties(r1, r13, r15)
            if (r15 != 0) goto L_0x035f
            super.extractCanonicalName(r0, r15)
        L_0x035f:
            return r8
        L_0x0360:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0360 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaStream.processSdpMediaDescription(fm.liveswitch.sdp.Message, fm.liveswitch.sdp.MediaDescription, int, boolean, boolean, boolean):fm.liveswitch.Error");
    }

    /* access modifiers changed from: protected */
    public void processUpdateToMediaStreamIdentification(String str) {
        super.processUpdateToMediaStreamIdentification(str);
        RtpTransport rtpTransport = getRtpTransport();
        if (rtpTransport != null) {
            rtpTransport.setMid(super.getMediaStreamIdentification());
            RtpListener listener = rtpTransport.getListener();
            CoreTransport coreTransportRtp = super.getCoreTransportRtp();
            if (coreTransportRtp != null) {
                coreTransportRtp.getBundleTransport().addRtpListener(listener, super.getMediaStreamIdentification());
                if (str != null) {
                    coreTransportRtp.getBundleTransport().removeRtpListener(str);
                }
            }
            CoreTransport coreTransportRtcp = super.getCoreTransportRtcp();
            if (coreTransportRtcp != null) {
                coreTransportRtcp.getBundleTransport().addRtpListener(listener, super.getMediaStreamIdentification());
                if (str != null) {
                    coreTransportRtcp.getBundleTransport().removeRtpListener(str);
                }
            }
            CoreTransport bundleCoreTransport = super.getBundleCoreTransport();
            if (bundleCoreTransport != null) {
                bundleCoreTransport.getBundleTransport().addRtpListener(listener, super.getMediaStreamIdentification());
                if (str != null) {
                    bundleCoreTransport.getBundleTransport().removeRtpListener(str);
                }
            }
        }
    }

    public boolean raiseBitrateNotification(BitrateNotification bitrateNotification) {
        String rtpStreamId = bitrateNotification.getRtpStreamId();
        long synchronizationSource = bitrateNotification.getSynchronizationSource();
        int i = (synchronizationSource > -1 ? 1 : (synchronizationSource == -1 ? 0 : -1));
        if (i == 0 && rtpStreamId == null) {
            return false;
        }
        if (i == 0) {
            synchronizationSource = getRemoteSynchronizationSource(rtpStreamId);
        }
        if (synchronizationSource == -1) {
            return false;
        }
        TmmbnControlFrame normalized = TmmbnControlFrame.normalized(bitrateNotification.getBitrate(), synchronizationSource, synchronizationSource);
        if (rtpStreamId == null) {
            raiseControlFrameResponse(normalized);
        } else {
            raiseControlFrameResponses(new MediaControlFrame[]{new SdesControlFrame(new SdesChunk(synchronizationSource, new SdesItem(SdesItemType.getRtpStreamId(), rtpStreamId))), normalized});
        }
        return true;
    }

    public boolean raiseBitrateRequest(BitrateRequest bitrateRequest) {
        long[] localSynchronizationSources;
        String rtpStreamId = bitrateRequest.getRtpStreamId();
        long synchronizationSource = bitrateRequest.getSynchronizationSource();
        if (synchronizationSource == -1 && rtpStreamId != null) {
            synchronizationSource = getLocalSynchronizationSource(rtpStreamId);
        }
        long senderSynchronizationSource = bitrateRequest.getSenderSynchronizationSource();
        if (senderSynchronizationSource == -1) {
            senderSynchronizationSource = synchronizationSource;
        }
        if (synchronizationSource == -1) {
            RtpParameters rtpParameters = getRtpParameters();
            if (rtpParameters == null || (localSynchronizationSources = rtpParameters.getLocalSynchronizationSources()) == null) {
                return true;
            }
            for (long normalized : localSynchronizationSources) {
                raiseControlFrame(TmmbrControlFrame.normalized(bitrateRequest.getBitrate(), normalized, senderSynchronizationSource));
            }
            return true;
        }
        raiseControlFrame(TmmbrControlFrame.normalized(bitrateRequest.getBitrate(), synchronizationSource, senderSynchronizationSource));
        return true;
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrame(MediaControlFrame mediaControlFrame) {
        raiseControlFrames(new MediaControlFrame[]{mediaControlFrame});
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrameResponse(MediaControlFrame mediaControlFrame) {
        raiseControlFrameResponses(new MediaControlFrame[]{mediaControlFrame});
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        RtpTransport rtpTransport;
        SdesChunk[] chunks;
        MediaControlFrame[] mediaControlFrameArr2 = mediaControlFrameArr;
        for (MediaControlFrame streamId : mediaControlFrameArr2) {
            streamId.setStreamId(super.getId());
        }
        for (MediaControlFrame mediaControlFrame : mediaControlFrameArr2) {
            SdesControlFrame sdesControlFrame = (SdesControlFrame) Global.tryCast(mediaControlFrame, SdesControlFrame.class);
            if (!(sdesControlFrame == null || (chunks = sdesControlFrame.getChunks()) == null)) {
                for (SdesChunk sdesChunk : chunks) {
                    SdesItem[] sourceDescriptionItems = sdesChunk.getSourceDescriptionItems();
                    if (sourceDescriptionItems != null) {
                        for (SdesItem sdesItem : sourceDescriptionItems) {
                            if (sdesItem.getType() == SdesItemType.getRtpStreamId()) {
                                String text = sdesItem.getText();
                                mapRemoteRtpStreamIdToSynchronizationSource(getOutputs((TIInput[]) getOutputs(), text), sdesChunk.getSynchronizationSource(), text);
                            }
                        }
                    }
                }
            }
            TmmbnControlFrame tmmbnControlFrame = (TmmbnControlFrame) Global.tryCast(mediaControlFrame, TmmbnControlFrame.class);
            if (tmmbnControlFrame != null) {
                for (TmmbnEntry tmmbnEntry : tmmbnControlFrame.getEntries()) {
                    if (tmmbnEntry.getNormalizedMaximumBitrate() == 0 && (rtpTransport = getRtpTransport()) != null && Global.equals(rtpTransport.getState(), MediaTransportState.Started)) {
                        rtpTransport.resetInboundCurrentBitrate(tmmbnEntry.getSynchronizationSource());
                    }
                }
            }
        }
        IAction1<MediaControlFrame[]> iAction1 = this._onRaiseControlFrameResponses;
        if (iAction1 != null) {
            try {
                iAction1.invoke(mediaControlFrameArr2);
            } catch (Exception e) {
                __log.error(StringExtensions.format("Exception occurred while raising control frame responses to the application code for media stream {0}.", (Object) super.getId()), e);
            }
        }
        for (IMediaInput processControlFrameResponses : getOutputs()) {
            processControlFrameResponses.processControlFrameResponses(mediaControlFrameArr2);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        int i;
        IMediaOutput[] iMediaOutputArr;
        MediaControlFrame[] mediaControlFrameArr2 = mediaControlFrameArr;
        for (MediaControlFrame streamId : mediaControlFrameArr2) {
            streamId.setStreamId(super.getId());
        }
        IAction1<MediaControlFrame[]> iAction1 = this._onRaiseControlFrames;
        if (iAction1 != null) {
            try {
                iAction1.invoke(mediaControlFrameArr2);
            } catch (Exception e) {
                __log.error(StringExtensions.format("Exception occurred while raising control frames to the application code for media stream {0}.", (Object) super.getId()), e);
            }
        }
        IMediaOutput[] inputs = getInputs();
        int length = inputs.length;
        int i2 = 0;
        while (i2 < length) {
            IMediaOutput iMediaOutput = inputs[i2];
            if (__log.getIsVerboseEnabled()) {
                int length2 = mediaControlFrameArr2.length;
                int i3 = 0;
                while (i3 < length2) {
                    MediaControlFrame mediaControlFrame = mediaControlFrameArr2[i3];
                    if (mediaControlFrame instanceof ReportControlFrame) {
                        ReportBlock[] reportBlocks = ((ReportControlFrame) mediaControlFrame).getReportBlocks();
                        int length3 = reportBlocks.length;
                        int i4 = 0;
                        while (i4 < length3) {
                            ReportBlock reportBlock = reportBlocks[i4];
                            long synchronizationSource = reportBlock.getSynchronizationSource();
                            if (synchronizationSource == iMediaOutput.getOutputSynchronizationSource()) {
                                iMediaOutputArr = inputs;
                                i = length;
                                __log.verbose(StringExtensions.concat((Object[]) new String[]{"Feedback report (", iMediaOutput.getOutputFormat().getFullName(), "): ", Utility.formatDoubleAsPercent(reportBlock.getPercentLost(), 2), "% packet loss (", IntegerExtensions.toString(Integer.valueOf(reportBlock.getCumulativeNumberOfPacketsLost())), " cumulative packets lost) for SSRC ", LongExtensions.toString(Long.valueOf(synchronizationSource)), "."}));
                            } else {
                                iMediaOutputArr = inputs;
                                i = length;
                            }
                            i4++;
                            inputs = iMediaOutputArr;
                            length = i;
                        }
                    }
                    i3++;
                    inputs = inputs;
                    length = length;
                }
            }
            iMediaOutput.processControlFrames(mediaControlFrameArr2);
            i2++;
            inputs = inputs;
            length = length;
        }
    }

    /* access modifiers changed from: protected */
    public void raiseFrame(TFrame tframe) {
        VideoFrame videoFrame;
        if (this.__synchronize) {
            if (getSynchronizeContext() == null && this.__synchronizeMaster && tframe.getSystemTimestamp() != -1) {
                setSynchronizeContext(new SynchronizeContext(tframe.getSystemTimestamp()));
                IAction1<SynchronizeContext> iAction1 = this._onMasterSynchronizeContextReady;
                if (iAction1 != null) {
                    try {
                        iAction1.invoke(getSynchronizeContext());
                    } catch (Exception e) {
                        __log.error(StringExtensions.format("Exception occurred while raising master synchronize context to the application code for media stream {0}.", (Object) super.getId()), e);
                    }
                }
            }
            if (!(getSynchronizeContext() == null || tframe.getSystemTimestamp() == -1)) {
                tframe.setSynchronized(true);
            }
        }
        IAction1<TFrame> iAction12 = this._onRaiseFrame;
        if (iAction12 != null) {
            iAction12.invoke(tframe);
        }
        try {
            long synchronizationSource = tframe.getSynchronizationSource();
            IMediaInput[] outputs = getOutputs();
            if (ArrayExtensions.getLength((Object[]) outputs) > 0) {
                IMediaInput orMapOutput = getOrMapOutput(outputs, synchronizationSource, tframe.getRtpStreamId(), tframe.getLastBuffer().getFormat());
                if (orMapOutput == null) {
                    __log.warn(StringExtensions.format("Discarding inbound {1} frame for SSRC {0}. No outputs available.", LongExtensions.toString(Long.valueOf(synchronizationSource)), StringExtensions.toLower(super.getType().toString())));
                } else if (orMapOutput.getDisabled()) {
                    __log.warn(StringExtensions.format("Discarding inbound {1} frame for SSRC {0}. Output is disabled.", LongExtensions.toString(Long.valueOf(synchronizationSource)), StringExtensions.toLower(super.getType().toString())));
                } else if (!orMapOutput.processFrame(tframe) && __log.getIsVerboseEnabled()) {
                    __log.verbose(StringExtensions.format("Discarding inbound {1} frame for SSRC {0}. Processing failed.", LongExtensions.toString(Long.valueOf(synchronizationSource)), StringExtensions.toLower(super.getType().toString())));
                }
            }
        } finally {
            if (Global.equals(super.getType(), StreamType.Video) && (videoFrame = (VideoFrame) Global.tryCast(tframe, VideoFrame.class)) != null) {
                updateMaxVideoSize(videoFrame, false);
            }
            IAction1<TFrame> iAction13 = this._onRaisedFrame;
            if (iAction13 != null) {
                try {
                    iAction13.invoke(tframe);
                } catch (Exception e2) {
                    __log.error(StringExtensions.format("Exception occurred while raising frame to the application code for media stream {0}.", (Object) super.getId()), e2);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void receiveControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        if (Global.equals(getDirection(), StreamDirection.SendReceive) || Global.equals(getDirection(), StreamDirection.SendOnly)) {
            raiseControlFrames(mediaControlFrameArr);
        }
    }

    /* access modifiers changed from: package-private */
    public void receiveFrame(TFrame tframe) {
        if (Global.equals(getDirection(), StreamDirection.SendReceive) || Global.equals(getDirection(), StreamDirection.ReceiveOnly)) {
            IAction1<TFrame> iAction1 = this._onReceiveFrame;
            if (iAction1 != null) {
                try {
                    iAction1.invoke(tframe);
                } catch (Exception e) {
                    __log.error(StringExtensions.format("Exception occurred while raising received frame to the application code for media stream {0}.", (Object) super.getId()), e);
                }
            }
            raiseFrame(tframe);
        }
    }

    private void registerPayloadTypes() {
        MediaFormatCollection mediaFormatCollection;
        if (this.__remoteFormatRegistry.getCount() == 0) {
            this.__payloadTypeRegistry.clear();
        }
        this.__localFormatRegistry.removeAll();
        StreamDirection directionCapabilities = getDirectionCapabilities();
        if (Global.equals(directionCapabilities, StreamDirection.SendOnly)) {
            mediaFormatCollection = obtainInputFormats();
        } else if (Global.equals(directionCapabilities, StreamDirection.ReceiveOnly)) {
            mediaFormatCollection = obtainOutputFormats();
        } else {
            mediaFormatCollection = obtainInputOutputFormatsIntersection();
        }
        int i = 0;
        for (int i2 = 0; i2 < mediaFormatCollection.getCount(); i2++) {
            MediaFormat mediaFormat = ((MediaFormat[]) mediaFormatCollection.getValues())[i2];
            int i3 = -1;
            if (this.__remoteFormatRegistry.getCount() == 0) {
                MapAttribute mapAttribute = new MapAttribute(-1, mediaFormat.getName(), mediaFormat.getClockRate(), mediaFormat.getParameters());
                if (StringExtensions.isEqual(mediaFormat.getName(), VideoFormat.getH264Name(), StringComparison.OrdinalIgnoreCase)) {
                    FormatParametersAttribute formatParametersAttribute = new FormatParametersAttribute(mediaFormat.getRegisteredPayloadType());
                    if (!(mediaFormat.getProfile() == null || mediaFormat.getLevel() == null)) {
                        formatParametersAttribute.setFormatSpecificParameter(VideoCodecInfo.H264_FMTP_PROFILE_LEVEL_ID, StringExtensions.join("", new String[]{mediaFormat.getProfile(), mediaFormat.getLevel()}));
                    }
                    if (!mediaFormat.getLevelIsStrict()) {
                        formatParametersAttribute.setFormatSpecificParameter(VideoCodecInfo.H264_FMTP_LEVEL_ASYMMETRY_ALLOWED, "1");
                    }
                    if (mediaFormat.getPacketizationMode() != null) {
                        formatParametersAttribute.setFormatSpecificParameter(VideoCodecInfo.H264_FMTP_PACKETIZATION_MODE, mediaFormat.getPacketizationMode());
                    }
                    mapAttribute.setRelatedFormatParametersAttribute(formatParametersAttribute);
                }
                if (mediaFormat.getStaticPayloadType() >= 0) {
                    String integerExtensions = IntegerExtensions.toString(Integer.valueOf(mediaFormat.getStaticPayloadType()));
                    Holder holder = new Holder(null);
                    boolean tryGetValue = HashMapExtensions.tryGetValue(this.__formatsRegisteredByOtherStreamsByPT, integerExtensions, holder);
                    MapAttribute mapAttribute2 = (MapAttribute) holder.getValue();
                    if (!tryGetValue) {
                        i3 = mediaFormat.getStaticPayloadType();
                    } else if (Global.equals(SessionDescriptionManager.getAttributeDescription(mapAttribute), SessionDescriptionManager.getAttributeDescription(mapAttribute2))) {
                        i3 = mediaFormat.getStaticPayloadType();
                    } else {
                        __log.error(StringExtensions.format("Payload type {0} for {1} is already registered by another stream.", integerExtensions, mediaFormat.getFullName()));
                    }
                } else {
                    int i4 = this.__rtpPayloadTypeDynamicOffset;
                    this.__rtpPayloadTypeDynamicOffset = i4 + 1;
                    Holder holder2 = new Holder(null);
                    boolean tryGetValue2 = HashMapExtensions.tryGetValue(this.__formatsRegisteredByOtherStreamsByDescription, SessionDescriptionManager.getAttributeDescription(mapAttribute), holder2);
                    MapAttribute mapAttribute3 = (MapAttribute) holder2.getValue();
                    if (tryGetValue2) {
                        i3 = mapAttribute3.getPayloadType();
                    } else {
                        while (this.__formatsRegisteredByOtherStreamsByPT.containsKey(IntegerExtensions.toString(Integer.valueOf(i4)))) {
                            i4 = this.__rtpPayloadTypeDynamicOffset;
                            this.__rtpPayloadTypeDynamicOffset = i4 + 1;
                        }
                        if (i4 > this.__rtpPayloadTypeMaxOffset) {
                            __log.error(StringExtensions.format("Cannot add format {0}. Maximum dynamic payload types exceeded.", (Object) mediaFormat.toString()));
                        } else {
                            mapAttribute.setPayloadType(i4);
                            HashMapExtensions.add(this.__formatsRegisteredByOtherStreamsByPT, IntegerExtensions.toString(Integer.valueOf(i4)), mapAttribute);
                            HashMapExtensions.add(this.__formatsRegisteredByOtherStreamsByDescription, SessionDescriptionManager.getAttributeDescription(mapAttribute), mapAttribute);
                            i3 = i4;
                        }
                    }
                }
            }
            mediaFormat.setRegisteredPayloadType(i3);
            addLocalFormat(mediaFormat);
            if (mediaFormat.getRegisteredPayloadType() >= 0 && i == 0) {
                i = mediaFormat.getClockRate();
            }
        }
        if (Global.equals(getRedFecPolicy(), RedFecPolicy.Negotiated) && i > 0) {
            if (Global.equals(super.getType(), StreamType.Video)) {
                int i5 = this.__rtpPayloadTypeDynamicOffset;
                this.__rtpPayloadTypeDynamicOffset = i5 + 1;
                addLocalFormat(createFormat(MediaFormat.getRedName(), i, (String) null, i5));
                int i6 = this.__rtpPayloadTypeDynamicOffset;
                this.__rtpPayloadTypeDynamicOffset = i6 + 1;
                addLocalFormat(createFormat(MediaFormat.getUlpFecName(), i, (String) null, i6));
                return;
            }
            __log.debug("FEC is only supported for video streams.");
        }
    }

    public boolean removeInput(TIOutput tioutput) {
        if (tioutput != null) {
            return this.__inputs.remove(tioutput);
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot remove null input from {0}.", (Object) getLabel())));
    }

    public void removeInputs() {
        removeInputs((TIOutput[]) getInputs());
    }

    public void removeInputs(TIOutput[] tioutputArr) {
        if (tioutputArr != null) {
            for (TIOutput removeInput : tioutputArr) {
                removeInput(removeInput);
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot remove null inputs from {0}.", (Object) getLabel())));
    }

    /* access modifiers changed from: package-private */
    public void removeOnBandwidthAdaptationPolicyChange(IAction1<Stream> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onBandwidthAdaptationPolicyChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onBandwidthAdaptationPolicyChange.remove(iAction1);
        if (this.__onBandwidthAdaptationPolicyChange.size() == 0) {
            this._onBandwidthAdaptationPolicyChange = null;
        }
    }

    public void removeOnDiscardBitrateNotification(IAction1<BitrateNotification> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onDiscardBitrateNotification, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onDiscardBitrateNotification.remove(iAction1);
        if (this.__onDiscardBitrateNotification.size() == 0) {
            this._onDiscardBitrateNotification = null;
        }
    }

    public void removeOnDiscardBitrateRequest(IAction1<BitrateRequest> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onDiscardBitrateRequest, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onDiscardBitrateRequest.remove(iAction1);
        if (this.__onDiscardBitrateRequest.size() == 0) {
            this._onDiscardBitrateRequest = null;
        }
    }

    public void removeOnDiscardOutboundControlFrame(IAction1<MediaControlFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onDiscardOutboundControlFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onDiscardOutboundControlFrame.remove(iAction1);
        if (this.__onDiscardOutboundControlFrame.size() == 0) {
            this._onDiscardOutboundControlFrame = null;
        }
    }

    public void removeOnMasterSynchronizeContextReady(IAction1<SynchronizeContext> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onMasterSynchronizeContextReady, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onMasterSynchronizeContextReady.remove(iAction1);
        if (this.__onMasterSynchronizeContextReady.size() == 0) {
            this._onMasterSynchronizeContextReady = null;
        }
    }

    public void removeOnPausedChange(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onPausedChange, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onPausedChange.remove(iAction0);
        if (this.__onPausedChange.size() == 0) {
            this._onPausedChange = null;
        }
    }

    public void removeOnProcessControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onProcessControlFrameResponses, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onProcessControlFrameResponses.remove(iAction1);
        if (this.__onProcessControlFrameResponses.size() == 0) {
            this._onProcessControlFrameResponses = null;
        }
    }

    public void removeOnProcessControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onProcessControlFrames, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onProcessControlFrames.remove(iAction1);
        if (this.__onProcessControlFrames.size() == 0) {
            this._onProcessControlFrames = null;
        }
    }

    public void removeOnProcessedFrame(IAction1<TFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onProcessedFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onProcessedFrame.remove(iAction1);
        if (this.__onProcessedFrame.size() == 0) {
            this._onProcessedFrame = null;
        }
    }

    public void removeOnProcessFrame(IAction1<TFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onProcessFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onProcessFrame.remove(iAction1);
        if (this.__onProcessFrame.size() == 0) {
            this._onProcessFrame = null;
        }
    }

    public void removeOnRaiseControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRaiseControlFrameResponses, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRaiseControlFrameResponses.remove(iAction1);
        if (this.__onRaiseControlFrameResponses.size() == 0) {
            this._onRaiseControlFrameResponses = null;
        }
    }

    public void removeOnRaiseControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRaiseControlFrames, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRaiseControlFrames.remove(iAction1);
        if (this.__onRaiseControlFrames.size() == 0) {
            this._onRaiseControlFrames = null;
        }
    }

    public void removeOnRaisedFrame(IAction1<TFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRaisedFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRaisedFrame.remove(iAction1);
        if (this.__onRaisedFrame.size() == 0) {
            this._onRaisedFrame = null;
        }
    }

    public void removeOnRaiseFrame(IAction1<TFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRaiseFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRaiseFrame.remove(iAction1);
        if (this.__onRaiseFrame.size() == 0) {
            this._onRaiseFrame = null;
        }
    }

    public void removeOnReceiveFrame(IAction1<TFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceiveFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceiveFrame.remove(iAction1);
        if (this.__onReceiveFrame.size() == 0) {
            this._onReceiveFrame = null;
        }
    }

    public void removeOnSendFrame(IAction1<TFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onSendFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onSendFrame.remove(iAction1);
        if (this.__onSendFrame.size() == 0) {
            this._onSendFrame = null;
        }
    }

    public boolean removeOutput(TIInput tiinput) {
        if (tiinput != null) {
            return this.__outputs.remove(tiinput);
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot remove null output from {0}.", (Object) getLabel())));
    }

    public void removeOutputs() {
        removeOutputs((TIInput[]) getOutputs());
    }

    public void removeOutputs(TIInput[] tiinputArr) {
        if (tiinputArr != null) {
            for (TIInput removeOutput : tiinputArr) {
                removeOutput(removeOutput);
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot remove null outputs from {0}.", (Object) getLabel())));
    }

    /* access modifiers changed from: package-private */
    public void resetInboundBandwidthEstimate(int i) {
        if (Global.equals(getBandwidthAdaptationPolicy(), BandwidthAdaptationPolicy.Enabled) && i != -1) {
            resetInboundBandwidthEstimate(i, getRtpParameters());
            resetInboundBandwidthEstimate(i, getRtpTransport());
        }
    }

    private boolean resetInboundBandwidthEstimate(int i, RtpParameters<TFormat, TFormatCollection> rtpParameters) {
        String str;
        if (rtpParameters == null) {
            return false;
        }
        long[] remoteSynchronizationSources = rtpParameters.getRemoteSynchronizationSources();
        if (ArrayExtensions.getLength(remoteSynchronizationSources) != 1) {
            return false;
        }
        long j = remoteSynchronizationSources[0];
        if (j == -1) {
            String[] remoteRtpStreamIds = rtpParameters.getRemoteRtpStreamIds();
            if (ArrayExtensions.getLength((Object[]) remoteRtpStreamIds) != 1 || (str = remoteRtpStreamIds[0]) == null) {
                return false;
            }
            String concat = StringExtensions.concat("RID:", str);
            int initialReceiveBandwidthEstimate = rtpParameters.getInitialReceiveBandwidthEstimate(str);
            if (initialReceiveBandwidthEstimate != -1 && initialReceiveBandwidthEstimate >= i) {
                __log.debug(concat, StringExtensions.format("Remote encoding has changed. Initial receive bandwidth estimate ({0}kbps) will not reset to {1}kbps.", IntegerExtensions.toString(Integer.valueOf(initialReceiveBandwidthEstimate)), IntegerExtensions.toString(Integer.valueOf(i))));
            } else if (rtpParameters.updateInitialReceiveBandwidthEstimate(str, i)) {
                __log.debug(concat, StringExtensions.format("Remote encoding has changed. Initial receive bandwidth estimate ({0}kbps) has reset to {1}kbps.", IntegerExtensions.toString(Integer.valueOf(initialReceiveBandwidthEstimate)), IntegerExtensions.toString(Integer.valueOf(i))));
            } else {
                __log.warn(concat, StringExtensions.format("Remote encoding has changed. Could not reset initial receive bandwidth estimate ({0}kbps) to {1}kbps.", IntegerExtensions.toString(Integer.valueOf(initialReceiveBandwidthEstimate)), IntegerExtensions.toString(Integer.valueOf(i))));
            }
        } else {
            String concat2 = StringExtensions.concat("SSRC:", LongExtensions.toString(Long.valueOf(j)));
            int initialReceiveBandwidthEstimate2 = rtpParameters.getInitialReceiveBandwidthEstimate(j);
            if (initialReceiveBandwidthEstimate2 != -1 && initialReceiveBandwidthEstimate2 >= i) {
                __log.debug(concat2, StringExtensions.format("Remote encoding has changed. Initial receive bandwidth estimate ({0}kbps) will not reset to {1}kbps.", IntegerExtensions.toString(Integer.valueOf(initialReceiveBandwidthEstimate2)), IntegerExtensions.toString(Integer.valueOf(i))));
            } else if (rtpParameters.updateInitialReceiveBandwidthEstimate(j, i)) {
                __log.debug(concat2, StringExtensions.format("Remote encoding has changed. Initial receive bandwidth estimate ({0}kbps) has reset to {1}kbps.", IntegerExtensions.toString(Integer.valueOf(initialReceiveBandwidthEstimate2)), IntegerExtensions.toString(Integer.valueOf(i))));
            } else {
                __log.warn(concat2, StringExtensions.format("Remote encoding has changed. Could not reset initial receive bandwidth estimate ({0}kbps) to {1}kbps.", IntegerExtensions.toString(Integer.valueOf(initialReceiveBandwidthEstimate2)), IntegerExtensions.toString(Integer.valueOf(i))));
            }
        }
        return true;
    }

    private boolean resetInboundBandwidthEstimate(int i, RtpTransport<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpTransport) {
        if (rtpTransport == null) {
            return false;
        }
        double inboundBandwidthEstimate = rtpTransport.getInboundBandwidthEstimate();
        LongHolder longHolder = new LongHolder(-1);
        Holder holder = new Holder(null);
        boolean resetInboundBandwidthEstimate = rtpTransport.resetInboundBandwidthEstimate((double) i, longHolder, holder);
        long value = longHolder.getValue();
        String str = (String) holder.getValue();
        if (!resetInboundBandwidthEstimate) {
            return true;
        }
        __log.debug(value == -1 ? StringExtensions.concat("RID:", str) : StringExtensions.concat("SSRC:", LongExtensions.toString(Long.valueOf(value))), StringExtensions.format("Remote encoding has changed. Inbound bandwidth estimate ({0}kbps) is resetting to {1}kbps.", DoubleExtensions.toString(Double.valueOf(inboundBandwidthEstimate)), IntegerExtensions.toString(Integer.valueOf(i))));
        return true;
    }

    private StreamDirection restrictDirection(boolean z, boolean z2) {
        StreamDirection direction = (!z || !super.getRenegotiationPending()) ? getDirection() : super.getPendingLocalDirection();
        if (z2) {
            return direction;
        }
        if (Global.equals(getLocalDirection(), StreamDirection.Unset)) {
            setLocalDirection(getDirectionCapabilities());
        } else if (Global.equals(getDirectionCapabilities(), StreamDirection.ReceiveOnly)) {
            if (Global.equals(getLocalDirection(), StreamDirection.SendOnly)) {
                setLocalDirection(StreamDirection.Inactive);
            }
        } else if (Global.equals(getDirectionCapabilities(), StreamDirection.SendOnly)) {
            if (Global.equals(getLocalDirection(), StreamDirection.ReceiveOnly)) {
                setLocalDirection(StreamDirection.Inactive);
            }
        } else if (Global.equals(getDirectionCapabilities(), StreamDirection.Inactive)) {
            setLocalDirection(StreamDirection.Inactive);
        }
        if (z) {
            return getLocalDirection();
        }
        return getDirection();
    }

    /* access modifiers changed from: package-private */
    public void setAbsoluteSenderTimeLocalDirection(StreamDirection streamDirection) {
        this.__absoluteSenderTimeLocalDirection = streamDirection;
    }

    /* access modifiers changed from: package-private */
    public void setAbsoluteSenderTimePolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this._absoluteSenderTimePolicy = mediaHeaderExtensionPolicy;
    }

    public void setBandwidthAdaptationPolicy(BandwidthAdaptationPolicy bandwidthAdaptationPolicy) {
        IAction1<Stream> iAction1;
        this.__bandwidthAdaptationPolicy = bandwidthAdaptationPolicy;
        if (Global.equals(bandwidthAdaptationPolicy, BandwidthAdaptationPolicy.Enabled)) {
            setAbsoluteSenderTimeLocalDirection(StreamDirection.SendOnly);
            setAbsoluteSenderTimePolicy(MediaHeaderExtensionPolicy.Negotiated);
            setRembPolicy(RembPolicy.Negotiated);
        } else if (Global.equals(bandwidthAdaptationPolicy, BandwidthAdaptationPolicy.Disabled)) {
            setAbsoluteSenderTimePolicy(MediaHeaderExtensionPolicy.Disabled);
            setRembPolicy(RembPolicy.Disabled);
        }
        if (!Global.equals(this.__bandwidthAdaptationPolicy, bandwidthAdaptationPolicy) && (iAction1 = this._onBandwidthAdaptationPolicyChange) != null) {
            try {
                iAction1.invoke(this);
            } catch (Exception e) {
                __log.error(StringExtensions.format("Exception occurred while raising bandwidth adaptation policy change to the application code for media stream {0}.", (Object) super.getId()), e);
            }
        }
    }

    public void setCcmFirPolicy(CcmFirPolicy ccmFirPolicy) {
        if (!Global.equals(this.__ccmFirPolicy, CcmFirPolicy.Disabled) || !Global.equals(ccmFirPolicy, CcmFirPolicy.Negotiated)) {
            if (Global.equals(this.__ccmFirPolicy, CcmFirPolicy.Negotiated) && Global.equals(ccmFirPolicy, CcmFirPolicy.Disabled)) {
                this.__ccmFirPolicy = CcmFirPolicy.Disabled;
            }
        } else if (!this.__startedOriginalNegotiation) {
            this.__ccmFirPolicy = CcmFirPolicy.Negotiated;
        } else {
            Log.error("Cannot set CCM FIR policy to Negotiated after the original session description negotiation occurred.");
        }
    }

    public void setCcmLrrPolicy(CcmLrrPolicy ccmLrrPolicy) {
        if (!Global.equals(this.__ccmLrrPolicy, CcmLrrPolicy.Disabled) || !Global.equals(ccmLrrPolicy, CcmLrrPolicy.Negotiated)) {
            if (Global.equals(this.__ccmLrrPolicy, CcmLrrPolicy.Negotiated) && Global.equals(ccmLrrPolicy, CcmLrrPolicy.Disabled)) {
                this.__ccmLrrPolicy = CcmLrrPolicy.Disabled;
            }
        } else if (!this.__startedOriginalNegotiation) {
            this.__ccmLrrPolicy = CcmLrrPolicy.Negotiated;
        } else {
            Log.error("Cannot set CCM LRR policy to Negotiated after the original session description negotiation occurred.");
        }
    }

    public void setCcmTmmbnPolicy(CcmTmmbnPolicy ccmTmmbnPolicy) {
        if (!Global.equals(this.__ccmTmmbnPolicy, CcmTmmbnPolicy.Disabled) || !Global.equals(ccmTmmbnPolicy, CcmTmmbnPolicy.Negotiated)) {
            if (Global.equals(this.__ccmTmmbnPolicy, CcmTmmbnPolicy.Negotiated) && Global.equals(ccmTmmbnPolicy, CcmTmmbnPolicy.Disabled)) {
                this.__ccmTmmbnPolicy = CcmTmmbnPolicy.Disabled;
            }
        } else if (!this.__startedOriginalNegotiation) {
            this.__ccmTmmbnPolicy = CcmTmmbnPolicy.Negotiated;
        } else {
            Log.error("Cannot set CCM TMMBN policy to Negotiated after the original session description negotiation occurred.");
        }
    }

    public void setCcmTmmbrPolicy(CcmTmmbrPolicy ccmTmmbrPolicy) {
        if (!Global.equals(this.__ccmTmmbrPolicy, CcmTmmbrPolicy.Disabled) || !Global.equals(ccmTmmbrPolicy, CcmTmmbrPolicy.Negotiated)) {
            if (Global.equals(this.__ccmTmmbrPolicy, CcmTmmbrPolicy.Negotiated) && Global.equals(ccmTmmbrPolicy, CcmTmmbrPolicy.Disabled)) {
                this.__ccmTmmbrPolicy = CcmTmmbrPolicy.Disabled;
            }
        } else if (!this.__startedOriginalNegotiation) {
            this.__ccmTmmbrPolicy = CcmTmmbrPolicy.Negotiated;
        } else {
            Log.error("Cannot set CCM TMMBR policy to Negotiated after the original session description negotiation occurred.");
        }
    }

    public void setDisableAutomaticReports(boolean z) {
        this._disableAutomaticReports = z;
    }

    private void setInjectionAllowed(boolean z) {
        this._injectionAllowed = z;
    }

    private void setInputFormat() {
        if (Global.equals(getDirection(), StreamDirection.ReceiveOnly)) {
            setInputFormat((MediaFormat) null);
        } else if (Global.equals(getDirection(), StreamDirection.SendOnly)) {
            setInputFormat(getPreferredFormat());
        } else if (Global.equals(getDirection(), StreamDirection.SendReceive)) {
            setInputFormat(getPreferredFormat());
        } else {
            setInputFormat((MediaFormat) null);
        }
    }

    private void setInputFormat(TFormat tformat) {
        this._inputFormat = tformat;
    }

    public void setInputMuted(boolean z) {
        setInputSourceMuted((TIOutput[]) getInputs(), z);
    }

    /* access modifiers changed from: protected */
    public void setInputSourceMuted(TIOutput[] tioutputArr, boolean z) {
        for (TIOutput inputSourceMuted : tioutputArr) {
            setInputSourceMuted(inputSourceMuted, z);
        }
    }

    public void setInputSynchronizationDisabled(boolean z) {
        this._inputSynchronizationDisabled = z;
    }

    private void setJitterConfig(JitterConfig jitterConfig) {
        this._jitterConfig = jitterConfig;
    }

    public void setLegacyReceiver(boolean z) {
        this._legacyReceiver = z;
    }

    public void setLocalDescriptionMediaId(String str) {
        this._localDescriptionMediaId = str;
    }

    public void setLocalDescriptionTrackId(String str) {
        this._localDescriptionTrackId = str;
    }

    /* access modifiers changed from: protected */
    public void setLocalMedia(LocalMedia localMedia) {
        this.__localMedia = localMedia;
        if (localMedia != null) {
            super.setLocalCanonicalName(localMedia.getId());
        }
    }

    /* access modifiers changed from: protected */
    public void setLocalTrack(TTrack ttrack) {
        this._localTrack = ttrack;
    }

    public void setMaxReceiveBitrate(int i) {
        int maxReceiveBitrate = super.getMaxReceiveBitrate();
        super.setMaxReceiveBitrate(i);
        int maxReceiveBitrate2 = super.getMaxReceiveBitrate();
        if (maxReceiveBitrate != maxReceiveBitrate2) {
            processMaxReceiveBitrateUpdate(maxReceiveBitrate2);
        }
    }

    /* access modifiers changed from: package-private */
    public void setMultiplexingSupported(boolean z) {
        this._multiplexingSupported = z;
    }

    private void setNackConfig(NackConfig nackConfig) {
        this.__nackConfig = nackConfig;
    }

    private void setNackEnabled(boolean z) {
        getNackConfig().setDisableBuffering(!z);
    }

    public void setNackPliPolicy(NackPliPolicy nackPliPolicy) {
        if (!Global.equals(this.__nackPliPolicy, NackPliPolicy.Disabled) || !Global.equals(nackPliPolicy, NackPliPolicy.Negotiated)) {
            if (Global.equals(this.__nackPliPolicy, NackPliPolicy.Negotiated) && Global.equals(nackPliPolicy, NackPliPolicy.Disabled)) {
                this.__nackPliPolicy = NackPliPolicy.Disabled;
            }
        } else if (!this.__startedOriginalNegotiation) {
            this.__nackPliPolicy = NackPliPolicy.Negotiated;
        } else {
            Log.error("Cannot set NACK PLI policy to Negotiated after the original session description negotiation occurred.");
        }
    }

    public void setNackPolicy(NackPolicy nackPolicy) {
        if (!Global.equals(this.__nackPolicy, NackPolicy.Disabled) || !Global.equals(nackPolicy, NackPolicy.Negotiated)) {
            if (Global.equals(this.__nackPolicy, NackPolicy.Negotiated) && Global.equals(nackPolicy, NackPolicy.Disabled)) {
                this.__nackPolicy = NackPolicy.Disabled;
                setNackEnabled(false);
            }
        } else if (!this.__startedOriginalNegotiation) {
            this.__nackPolicy = NackPolicy.Negotiated;
        } else {
            Log.error("Cannot set NACK policy to Negotiated after the original session description negotiation occurred.");
        }
    }

    private void setNegotiatedFormats(MediaDescription mediaDescription) {
        int[] payloadTypes;
        MediaFormat equivalent;
        String formatDescription = mediaDescription.getMedia().getFormatDescription();
        if (!StringExtensions.isNullOrEmpty(formatDescription) && (payloadTypes = Media.getPayloadTypes(formatDescription)) != null) {
            MediaFormatCollection obtainLocalRemoteFormatsUnion = obtainLocalRemoteFormatsUnion();
            for (int i : payloadTypes) {
                MediaFormat registeredFormat = getRegisteredFormat(i);
                if (!(registeredFormat == null || (equivalent = obtainLocalRemoteFormatsUnion.getEquivalent(registeredFormat)) == null)) {
                    getRtpParameters().setNegotiatedFormat(i, equivalent);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setOutputFormat(TFormat tformat) {
        this._outputFormat = tformat;
    }

    public void setOutputMuted(boolean z) {
        setOutputSinkMuted((TIInput[]) getOutputs(), z);
    }

    /* access modifiers changed from: protected */
    public void setOutputSinkMuted(TIInput[] tiinputArr, boolean z) {
        for (TIInput outputSinkMuted : tiinputArr) {
            setOutputSinkMuted(outputSinkMuted, z);
        }
    }

    /* access modifiers changed from: package-private */
    public void setOutputSynchronizable(boolean z) {
        this._outputSynchronizable = z;
    }

    public void setOutputSynchronizationDisabled(boolean z) {
        this._outputSynchronizationDisabled = z;
    }

    /* access modifiers changed from: private */
    public void setPaused(boolean z) {
        if (!Global.equals(Boolean.valueOf(this.__paused), Boolean.valueOf(z))) {
            this.__paused = z;
            IAction0 iAction0 = this._onPausedChange;
            if (iAction0 != null) {
                iAction0.invoke();
            }
        }
    }

    public void setProcessFramePolicy(ProcessFramePolicy processFramePolicy) {
        if (Global.equals(processFramePolicy, ProcessFramePolicy.Asynchronous) && this.__dispatchQueue == null) {
            this.__dispatchQueue = new DispatchQueue(new IAction1<TFrame>() {
                /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: 
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                    	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                    */
                public void invoke(TFrame r5) {
                    /*
                        r4 = this;
                        r0 = 0
                        fm.liveswitch.MediaStream r1 = fm.liveswitch.MediaStream.this     // Catch:{ all -> 0x0016 }
                        boolean unused = r1.processFrameSync(r5)     // Catch:{ all -> 0x0016 }
                        fm.liveswitch.MediaBuffer[] r5 = r5.getBuffers()
                        int r1 = r5.length
                    L_0x000b:
                        if (r0 >= r1) goto L_0x0015
                        r2 = r5[r0]
                        r2.free()
                        int r0 = r0 + 1
                        goto L_0x000b
                    L_0x0015:
                        return
                    L_0x0016:
                        r1 = move-exception
                        fm.liveswitch.MediaBuffer[] r5 = r5.getBuffers()
                        int r2 = r5.length
                    L_0x001c:
                        if (r0 >= r2) goto L_0x0026
                        r3 = r5[r0]
                        r3.free()
                        int r0 = r0 + 1
                        goto L_0x001c
                    L_0x0026:
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaStream.AnonymousClass23.invoke(fm.liveswitch.MediaFrame):void");
                }
            });
        }
        this.__processPolicy = processFramePolicy;
    }

    /* access modifiers changed from: package-private */
    public void setProtectionParameters(SrtpProtectionParameters srtpProtectionParameters, SrtpProtectionParameters srtpProtectionParameters2) {
        CoreTransport coreTransportRtp = super.getCoreTransportRtp();
        if (coreTransportRtp != null) {
            coreTransportRtp.getBundleTransport().setProtectionParameters(srtpProtectionParameters, srtpProtectionParameters2);
        }
        CoreTransport coreTransportRtcp = super.getCoreTransportRtcp();
        if (coreTransportRtcp != null) {
            coreTransportRtcp.getBundleTransport().setProtectionParameters(srtpProtectionParameters, srtpProtectionParameters2);
        }
        CoreTransport bundleCoreTransport = super.getBundleCoreTransport();
        if (bundleCoreTransport != null) {
            bundleCoreTransport.getBundleTransport().setProtectionParameters(srtpProtectionParameters, srtpProtectionParameters2);
        }
    }

    private void setRedFecConfig(RedFecConfig redFecConfig) {
        this.__redFecConfig = redFecConfig;
    }

    private void setRedFecEnabled(boolean z) {
        if (!Global.equals(super.getType(), StreamType.Audio) || !z) {
            getRedFecConfig().setDisabled(!z);
            return;
        }
        throw new RuntimeException(new Exception("RED FEC is not supported for Audio streams."));
    }

    public void setRedFecPolicy(RedFecPolicy redFecPolicy) {
        if (!Global.equals(this.__redFecPolicy, RedFecPolicy.Disabled) || Global.equals(redFecPolicy, RedFecPolicy.Disabled) || !this.__startedOriginalNegotiation) {
            this.__redFecPolicy = redFecPolicy;
        } else {
            Log.error("Cannot set RED/FEC policy to Negotiated after the original session description negotiation has commenced.");
        }
    }

    /* access modifiers changed from: package-private */
    public void setRembPolicy(RembPolicy rembPolicy) {
        if (!Global.equals(this.__rembPolicy, RembPolicy.Disabled) || Global.equals(rembPolicy, RembPolicy.Disabled) || !this.__startedOriginalNegotiation) {
            this.__rembPolicy = rembPolicy;
        } else {
            Log.error("Cannot set Remb policy to Negotiated after the original session description negotiation has commenced.");
        }
    }

    public void setRemoteEncoding(EncodingInfo encodingInfo) {
        if (!Global.equals(encodingInfo, super.getRemoteEncoding())) {
            if (encodingInfo != null) {
                resetInboundBandwidthEstimate(encodingInfo.getBitrate());
            }
            super.setRemoteEncoding(encodingInfo);
        }
    }

    /* access modifiers changed from: protected */
    public void setRemoteMedia(RemoteMedia remoteMedia) {
        this._remoteMedia = remoteMedia;
    }

    /* access modifiers changed from: protected */
    public void setRemoteTrack(TTrack ttrack) {
        this._remoteTrack = ttrack;
    }

    /* access modifiers changed from: package-private */
    public void setRtpHeaderExtensionRegistry(RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry) {
        this.__rtpHeaderExtensionRegistry = rtpHeaderExtensionRegistry;
        CoreTransport coreTransportRtp = super.getCoreTransportRtp();
        CoreTransport coreTransportRtcp = super.getCoreTransportRtcp();
        if (coreTransportRtp != null) {
            coreTransportRtp.getBundleTransport().setHeaderExtensionRegistry(rtpHeaderExtensionRegistry);
        }
        if (coreTransportRtcp != null) {
            coreTransportRtcp.getBundleTransport().setHeaderExtensionRegistry(rtpHeaderExtensionRegistry);
        }
    }

    private void setRtpParameters(RtpParameters<TFormat, TFormatCollection> rtpParameters) {
        this._rtpParameters = rtpParameters;
    }

    /* access modifiers changed from: package-private */
    public void setRtpTransport(RtpTransport<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpTransport) {
        if (!Global.equals(this.__rtpTransport, rtpTransport)) {
            RtpTransport<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpTransport2 = this.__rtpTransport;
            if (rtpTransport2 != null) {
                rtpTransport2.removeOnSendControlFrames(this._onProcessControlFrames);
                this.__rtpTransport.removeOnSendControlFrameResponses(this._onProcessControlFrameResponses);
                this.__rtpTransport.removeOnStateChange(new IActionDelegate1<IMediaTransport>() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<TIOutput,TIOutputCollection,TIInput,TIInputCollection,TIElement,TSource,TSink,TPipe,TTrack,TBranch,TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.processRtpTransportStateChange";
                    }

                    public void invoke(IMediaTransport iMediaTransport) {
                        MediaStream.this.processRtpTransportStateChange(iMediaTransport);
                    }
                });
            }
            this.__rtpTransport = rtpTransport;
            if (rtpTransport != null) {
                setRtpTransportDirection();
                super.removeOnDirectionChange(new IActionDelegate0() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<TIOutput,TIOutputCollection,TIInput,TIInputCollection,TIElement,TSource,TSink,TPipe,TTrack,TBranch,TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.setRtpTransportDirection";
                    }

                    public void invoke() {
                        MediaStream.this.setRtpTransportDirection();
                    }
                });
                super.addOnDirectionChange(new IActionDelegate0() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<TIOutput,TIOutputCollection,TIInput,TIInputCollection,TIElement,TSource,TSink,TPipe,TTrack,TBranch,TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.setRtpTransportDirection";
                    }

                    public void invoke() {
                        MediaStream.this.setRtpTransportDirection();
                    }
                });
                this.__rtpTransport.removeOnSendControlFrames(this._onProcessControlFrames);
                this.__rtpTransport.removeOnSendControlFrameResponses(this._onProcessControlFrameResponses);
                this.__rtpTransport.addOnSendControlFrames(this._onProcessControlFrames);
                this.__rtpTransport.addOnSendControlFrameResponses(this._onProcessControlFrameResponses);
                this.__rtpTransport.removeOnStateChange(new IActionDelegate1<IMediaTransport>() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<TIOutput,TIOutputCollection,TIInput,TIInputCollection,TIElement,TSource,TSink,TPipe,TTrack,TBranch,TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.processRtpTransportStateChange";
                    }

                    public void invoke(IMediaTransport iMediaTransport) {
                        MediaStream.this.processRtpTransportStateChange(iMediaTransport);
                    }
                });
                this.__rtpTransport.addOnStateChange(new IActionDelegate1<IMediaTransport>() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<TIOutput,TIOutputCollection,TIInput,TIInputCollection,TIElement,TSource,TSink,TPipe,TTrack,TBranch,TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.processRtpTransportStateChange";
                    }

                    public void invoke(IMediaTransport iMediaTransport) {
                        MediaStream.this.processRtpTransportStateChange(iMediaTransport);
                    }
                });
                this.__rtpTransport.setMid(super.getMediaStreamIdentification());
            }
        }
    }

    /* access modifiers changed from: private */
    public void setRtpTransportDirection() {
        RtpTransport<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpTransport = this.__rtpTransport;
        if (rtpTransport != null) {
            rtpTransport.setDirection(getDirection());
        }
    }

    /* access modifiers changed from: package-private */
    public void setSdesMidLocalDirection(StreamDirection streamDirection) {
        this.__sdesMidLocalDirection = streamDirection;
    }

    /* access modifiers changed from: package-private */
    public void setSdesMidPolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this._sdesMidPolicy = mediaHeaderExtensionPolicy;
    }

    /* access modifiers changed from: package-private */
    public void setSdesRepairedRtpStreamIdLocalDirection(StreamDirection streamDirection) {
        this.__sdesRepairedRtpStreamIdLocalDirection = streamDirection;
    }

    /* access modifiers changed from: package-private */
    public void setSdesRepairedRtpStreamIdPolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this._sdesRepairedRtpStreamIdPolicy = mediaHeaderExtensionPolicy;
    }

    /* access modifiers changed from: package-private */
    public void setSdesRtpStreamIdLocalDirection(StreamDirection streamDirection) {
        this.__sdesRtpStreamIdLocalDirection = streamDirection;
    }

    /* access modifiers changed from: package-private */
    public void setSdesRtpStreamIdPolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this._sdesRtpStreamIdPolicy = mediaHeaderExtensionPolicy;
    }

    /* access modifiers changed from: package-private */
    public void setSimulcastDraftVersion(int i) {
        this.__simulcastDraftVersion = i;
    }

    public void setSimulcastMode(SimulcastMode simulcastMode) {
        this.__simulcastMode = simulcastMode;
        if (Global.equals(simulcastMode, SimulcastMode.RtpStreamId)) {
            setSdesRtpStreamIdPolicy(MediaHeaderExtensionPolicy.Negotiated);
            setSdesRepairedRtpStreamIdPolicy(MediaHeaderExtensionPolicy.Negotiated);
            return;
        }
        setSdesRtpStreamIdPolicy(MediaHeaderExtensionPolicy.Disabled);
        setSdesRepairedRtpStreamIdPolicy(MediaHeaderExtensionPolicy.Disabled);
    }

    public void setSynchronizeContext(SynchronizeContext synchronizeContext) {
        this._synchronizeContext = synchronizeContext;
    }

    /* access modifiers changed from: protected */
    public void setSystemDelay(long j) {
        this._systemDelay = j;
    }

    public void synchronize(boolean z) {
        if (!this.__synchronizationInitialized) {
            this.__synchronizationInitialized = true;
            this.__synchronize = true;
            this.__synchronizeMaster = z;
            if (__log.getIsDebugEnabled()) {
                __log.debug(StringExtensions.format("{0} stream {1} for connection {2} initialized synchronization.", super.getType().toString(), super.getId(), super.getConnectionId()));
                return;
            }
            return;
        }
        throw new RuntimeException(new Exception("Synchronization has already been initialized."));
    }

    private void tryUpdateMaxInputBitrate() {
        tryUpdateMaxInputBitrate(false);
    }

    /* access modifiers changed from: package-private */
    public void tryUpdateMaxInputBitrate(boolean z) {
        RtpTransport rtpTransport;
        long timestamp = ManagedStopwatch.getTimestamp();
        long j = this.__lastMaxInputBitrateChangeTimestamp;
        if (j == -1 || timestamp - j > this.__maxInputBitrateChangeInterval || z) {
            this.__lastMaxInputBitrateChangeTimestamp = timestamp;
            int min = ConstraintUtility.min(ConstraintUtility.min(-1, super.getRemoteBandwidth()), getMaxSendBitrate());
            if (Global.equals(getBandwidthAdaptationPolicy(), BandwidthAdaptationPolicy.Enabled) && (rtpTransport = getRtpTransport()) != null) {
                min = ConstraintUtility.min(ConstraintUtility.min(min, (int) rtpTransport.getRequestedOutboundBitrate()), (int) rtpTransport.getOutboundBandwidthEstimate());
            }
            IMediaOutput[] inputs = getInputs();
            EncodingInfo[] inputMinOutputEncodings = getInputMinOutputEncodings(inputs);
            EncodingInfo[] inputMaxOutputEncodings = getInputMaxOutputEncodings(inputs);
            if (min != -1) {
                for (EncodingInfo bitrate : inputMinOutputEncodings) {
                    min = ConstraintUtility.max(min, bitrate.getBitrate());
                }
            }
            int i = this.__maxInputBitrate;
            this.__maxInputBitrate = min;
            if (i != min) {
                updateEncoderBitrates(min, inputMinOutputEncodings, inputMaxOutputEncodings);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x01ed  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int updateEncoderBitrates(int r12, fm.liveswitch.EncodingInfo r13, fm.liveswitch.EncodingInfo r14, int r15) {
        /*
            r11 = this;
            int r0 = r13.getBitrate()
            int r1 = r14.getBitrate()
            r2 = -1
            r3 = 1
            if (r0 != r2) goto L_0x000d
            r0 = 1
        L_0x000d:
            long r4 = r13.getSynchronizationSource()
            java.lang.String r13 = r11.getLocalRtpStreamId(r4)
            r6 = 0
            if (r12 != r2) goto L_0x0033
            fm.liveswitch.ILog r12 = __log
            java.lang.Long r14 = java.lang.Long.valueOf(r4)
            java.lang.String r14 = fm.liveswitch.LongExtensions.toString(r14)
            java.lang.String r15 = "Available bitrate is unrestricted. Removing restrictions on encoding (SSRC {0}, RID {1})."
            java.lang.String r13 = fm.liveswitch.StringExtensions.format(r15, r14, r13)
            r12.debug(r13)
            fm.liveswitch.TmmbrControlFrame r12 = fm.liveswitch.TmmbrControlFrame.normalized(r2, r4, r4)
            r11.raiseControlFrame(r12)
            return r6
        L_0x0033:
            r7 = 3
            r8 = 2
            r9 = 4
            if (r12 >= r0) goto L_0x00a4
            if (r15 != 0) goto L_0x006f
            fm.liveswitch.ILog r14 = __log
            java.lang.Object[] r15 = new java.lang.Object[r9]
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = fm.liveswitch.IntegerExtensions.toString(r1)
            r15[r6] = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r12)
            java.lang.String r1 = fm.liveswitch.IntegerExtensions.toString(r1)
            r15[r3] = r1
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            java.lang.String r1 = fm.liveswitch.LongExtensions.toString(r1)
            r15[r8] = r1
            r15[r7] = r13
            java.lang.String r13 = "Minimum output bitrate ({0}kbps) of primary encoding (SSRC {2}, RID {3}) is greater than estimated available bitrate ({1}kbps). Temporarily reducing bitrate to minimum."
            java.lang.String r13 = fm.liveswitch.StringExtensions.format((java.lang.String) r13, (java.lang.Object[]) r15)
            r14.debug(r13)
            fm.liveswitch.TmmbrControlFrame r13 = fm.liveswitch.TmmbrControlFrame.normalized(r0, r4, r4)
            r11.raiseControlFrame(r13)
            return r12
        L_0x006f:
            fm.liveswitch.ILog r14 = __log
            java.lang.Object[] r15 = new java.lang.Object[r9]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r15[r6] = r0
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            java.lang.String r12 = fm.liveswitch.IntegerExtensions.toString(r12)
            r15[r3] = r12
            java.lang.Long r12 = java.lang.Long.valueOf(r4)
            java.lang.String r12 = fm.liveswitch.LongExtensions.toString(r12)
            r15[r8] = r12
            r15[r7] = r13
            java.lang.String r12 = "Minimum output bitrate ({0}kbps) of secondary encoding (SSRC {2}, RID {3}) is greater than estimated available bitrate ({1}kbps). Temporarily deactivating encoding."
            java.lang.String r12 = fm.liveswitch.StringExtensions.format((java.lang.String) r12, (java.lang.Object[]) r15)
            r14.debug(r12)
            fm.liveswitch.TmmbrControlFrame r12 = fm.liveswitch.TmmbrControlFrame.normalized(r6, r4, r4)
            r11.raiseControlFrame(r12)
            return r6
        L_0x00a4:
            if (r1 == r2) goto L_0x00dd
            if (r12 >= r1) goto L_0x00dd
            fm.liveswitch.ILog r14 = __log
            java.lang.Object[] r15 = new java.lang.Object[r9]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r15[r6] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r12)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r15[r3] = r0
            java.lang.Long r0 = java.lang.Long.valueOf(r4)
            java.lang.String r0 = fm.liveswitch.LongExtensions.toString(r0)
            r15[r8] = r0
            r15[r7] = r13
            java.lang.String r13 = "Maximum output bitrate ({0}kbps) of encoding (SSRC {2}, RID {3}) is greater than estimated available bitrate ({1}kbps). Temporarily reducing bitrate to available."
            java.lang.String r13 = fm.liveswitch.StringExtensions.format((java.lang.String) r13, (java.lang.Object[]) r15)
            r14.debug(r13)
            fm.liveswitch.TmmbrControlFrame r13 = fm.liveswitch.TmmbrControlFrame.normalized(r12, r4, r4)
            r11.raiseControlFrame(r13)
            return r12
        L_0x00dd:
            if (r12 != r1) goto L_0x010d
            fm.liveswitch.ILog r14 = __log
            java.lang.Object[] r15 = new java.lang.Object[r9]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r15[r6] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r12)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r15[r3] = r0
            java.lang.Long r0 = java.lang.Long.valueOf(r4)
            java.lang.String r0 = fm.liveswitch.LongExtensions.toString(r0)
            r15[r8] = r0
            r15[r7] = r13
            java.lang.String r13 = "Maximum output bitrate ({0}kbps) of encoding (SSRC {2}, RID {3}) is equal to estimated available bitrate ({1}kbps)."
            java.lang.String r13 = fm.liveswitch.StringExtensions.format((java.lang.String) r13, (java.lang.Object[]) r15)
            r14.verbose(r13)
            return r12
        L_0x010d:
            if (r1 != r2) goto L_0x0138
            int r14 = r11.getEncoderTargetOutputBitrate(r4, r13)
            if (r14 != r2) goto L_0x021b
            fm.liveswitch.ILog r14 = __log
            java.lang.Integer r15 = java.lang.Integer.valueOf(r12)
            java.lang.String r15 = fm.liveswitch.IntegerExtensions.toString(r15)
            java.lang.Long r0 = java.lang.Long.valueOf(r4)
            java.lang.String r0 = fm.liveswitch.LongExtensions.toString(r0)
            java.lang.String r1 = "Maximum and target output bitrate of encoding (SSRC {1}, RID {2}) are unrestricted and should use all available bitrate ({0}kbps). Temporarily setting bitrate to available."
            java.lang.String r13 = fm.liveswitch.StringExtensions.format(r1, r15, r0, r13)
            r14.debug(r13)
            fm.liveswitch.TmmbrControlFrame r13 = fm.liveswitch.TmmbrControlFrame.normalized(r12, r4, r4)
            r11.raiseControlFrame(r13)
            return r12
        L_0x0138:
            int r15 = r11.getEncoderTargetOutputBitrate(r4, r13)
            if (r15 != r1) goto L_0x01ea
            boolean r14 = r14.getDeactivated()
            if (r14 == 0) goto L_0x0172
            fm.liveswitch.ILog r14 = __log
            java.lang.Object[] r0 = new java.lang.Object[r9]
            java.lang.Integer r10 = java.lang.Integer.valueOf(r1)
            java.lang.String r10 = fm.liveswitch.IntegerExtensions.toString(r10)
            r0[r6] = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r12)
            java.lang.String r10 = fm.liveswitch.IntegerExtensions.toString(r10)
            r0[r3] = r10
            java.lang.Long r10 = java.lang.Long.valueOf(r4)
            java.lang.String r10 = fm.liveswitch.LongExtensions.toString(r10)
            r0[r8] = r10
            r0[r7] = r13
            java.lang.String r10 = "Maximum output bitrate ({0}kbps) of encoding (SSRC {2}, RID {3}) is less than estimated available bitrate ({1}kbps). Encoding is inactive so leaving at maximum."
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r10, (java.lang.Object[]) r0)
            r14.verbose(r0)
            goto L_0x01ea
        L_0x0172:
            fm.liveswitch.ILog r14 = __log
            java.lang.Object[] r15 = new java.lang.Object[r9]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r15[r6] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r12)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r15[r3] = r0
            java.lang.Long r0 = java.lang.Long.valueOf(r4)
            java.lang.String r0 = fm.liveswitch.LongExtensions.toString(r0)
            r15[r8] = r0
            r15[r7] = r13
            java.lang.String r0 = "Maximum output bitrate ({0}kbps) of encoding (SSRC {2}, RID {3}) is less than estimated available bitrate ({1}kbps). Temporarily increasing bitrate to available to determine target."
            java.lang.String r15 = fm.liveswitch.StringExtensions.format((java.lang.String) r0, (java.lang.Object[]) r15)
            r14.verbose(r15)
            fm.liveswitch.TmmbrControlFrame r14 = fm.liveswitch.TmmbrControlFrame.normalized(r12, r4)
            r11.raiseControlFrame(r14)
            int r14 = r11.getEncoderTargetOutputBitrate(r4, r13)
            fm.liveswitch.ILog r15 = __log
            r0 = 5
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.Integer r10 = java.lang.Integer.valueOf(r1)
            java.lang.String r10 = fm.liveswitch.IntegerExtensions.toString(r10)
            r0[r6] = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r12)
            java.lang.String r10 = fm.liveswitch.IntegerExtensions.toString(r10)
            r0[r3] = r10
            java.lang.Long r10 = java.lang.Long.valueOf(r4)
            java.lang.String r10 = fm.liveswitch.LongExtensions.toString(r10)
            r0[r8] = r10
            r0[r7] = r13
            java.lang.Integer r10 = java.lang.Integer.valueOf(r14)
            java.lang.String r10 = fm.liveswitch.IntegerExtensions.toString(r10)
            r0[r9] = r10
            java.lang.String r10 = "Maximum output bitrate ({0}kbps) of encoding (SSRC {2}, RID {3}) is less than estimated available bitrate ({1}kbps). Target output bitrate determined to be {4}kbps. Maximum output bitrate restored to {0}kbps."
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r10, (java.lang.Object[]) r0)
            r15.verbose(r0)
            fm.liveswitch.TmmbrControlFrame r15 = fm.liveswitch.TmmbrControlFrame.normalized(r1, r4)
            r11.raiseControlFrame(r15)
            goto L_0x01eb
        L_0x01ea:
            r14 = r15
        L_0x01eb:
            if (r14 != r2) goto L_0x021b
            fm.liveswitch.ILog r14 = __log
            java.lang.Object[] r15 = new java.lang.Object[r9]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r15[r6] = r0
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            java.lang.String r12 = fm.liveswitch.IntegerExtensions.toString(r12)
            r15[r3] = r12
            java.lang.Long r12 = java.lang.Long.valueOf(r4)
            java.lang.String r12 = fm.liveswitch.LongExtensions.toString(r12)
            r15[r8] = r12
            r15[r7] = r13
            java.lang.String r12 = "Maximum output bitrate ({0}kbps) of encoding (SSRC {2}, RID {3}) is less than or equal to available bitrate ({1}kbps) and there is no target output bitrate."
            java.lang.String r12 = fm.liveswitch.StringExtensions.format((java.lang.String) r12, (java.lang.Object[]) r15)
            r14.verbose(r12)
            return r1
        L_0x021b:
            if (r14 != r1) goto L_0x024b
            fm.liveswitch.ILog r12 = __log
            java.lang.Object[] r15 = new java.lang.Object[r9]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r14)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r15[r6] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r15[r3] = r0
            java.lang.Long r0 = java.lang.Long.valueOf(r4)
            java.lang.String r0 = fm.liveswitch.LongExtensions.toString(r0)
            r15[r8] = r0
            r15[r7] = r13
            java.lang.String r13 = "Target output bitrate ({0}kbps) of encoding (SSRC {2}, RID {3}) is equal to maximum output bitrate ({1}kbps)."
            java.lang.String r13 = fm.liveswitch.StringExtensions.format((java.lang.String) r13, (java.lang.Object[]) r15)
            r12.verbose(r13)
            return r14
        L_0x024b:
            if (r14 >= r12) goto L_0x0282
            fm.liveswitch.ILog r15 = __log
            java.lang.Object[] r0 = new java.lang.Object[r9]
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            java.lang.String r1 = fm.liveswitch.IntegerExtensions.toString(r1)
            r0[r6] = r1
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            java.lang.String r12 = fm.liveswitch.IntegerExtensions.toString(r12)
            r0[r3] = r12
            java.lang.Long r12 = java.lang.Long.valueOf(r4)
            java.lang.String r12 = fm.liveswitch.LongExtensions.toString(r12)
            r0[r8] = r12
            r0[r7] = r13
            java.lang.String r12 = "Target output bitrate ({0}kbps) of encoding (SSRC {2}, RID {3}) is less than estimated available bitrate ({1}kbps). Temporarily setting bitrate to target."
            java.lang.String r12 = fm.liveswitch.StringExtensions.format((java.lang.String) r12, (java.lang.Object[]) r0)
            r15.debug(r12)
            fm.liveswitch.TmmbrControlFrame r12 = fm.liveswitch.TmmbrControlFrame.normalized(r14, r4, r4)
            r11.raiseControlFrame(r12)
            return r14
        L_0x0282:
            if (r14 <= r12) goto L_0x02b9
            fm.liveswitch.ILog r15 = __log
            java.lang.Object[] r0 = new java.lang.Object[r9]
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            java.lang.String r14 = fm.liveswitch.IntegerExtensions.toString(r14)
            r0[r6] = r14
            java.lang.Integer r14 = java.lang.Integer.valueOf(r12)
            java.lang.String r14 = fm.liveswitch.IntegerExtensions.toString(r14)
            r0[r3] = r14
            java.lang.Long r14 = java.lang.Long.valueOf(r4)
            java.lang.String r14 = fm.liveswitch.LongExtensions.toString(r14)
            r0[r8] = r14
            r0[r7] = r13
            java.lang.String r13 = "Target output bitrate ({0}kbps) of encoding (SSRC {2}, RID {3}) is greater than the estimated available bitrate ({1}kbps). Temporarily setting bitrate to available."
            java.lang.String r13 = fm.liveswitch.StringExtensions.format((java.lang.String) r13, (java.lang.Object[]) r0)
            r15.debug(r13)
            fm.liveswitch.TmmbrControlFrame r13 = fm.liveswitch.TmmbrControlFrame.normalized(r12, r4, r4)
            r11.raiseControlFrame(r13)
            return r12
        L_0x02b9:
            fm.liveswitch.ILog r15 = __log
            java.lang.Object[] r0 = new java.lang.Object[r9]
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            java.lang.String r1 = fm.liveswitch.IntegerExtensions.toString(r1)
            r0[r6] = r1
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            java.lang.String r12 = fm.liveswitch.IntegerExtensions.toString(r12)
            r0[r3] = r12
            java.lang.Long r12 = java.lang.Long.valueOf(r4)
            java.lang.String r12 = fm.liveswitch.LongExtensions.toString(r12)
            r0[r8] = r12
            r0[r7] = r13
            java.lang.String r12 = "Target output bitrate ({0}kbps) of encoding (SSRC {2}, RID {3}) is equal to estimated available bitrate ({1}kbps). Temporarily setting bitrate to target."
            java.lang.String r12 = fm.liveswitch.StringExtensions.format((java.lang.String) r12, (java.lang.Object[]) r0)
            r15.debug(r12)
            fm.liveswitch.TmmbrControlFrame r12 = fm.liveswitch.TmmbrControlFrame.normalized(r14, r4, r4)
            r11.raiseControlFrame(r12)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaStream.updateEncoderBitrates(int, fm.liveswitch.EncodingInfo, fm.liveswitch.EncodingInfo, int):int");
    }

    private boolean updateEncoderBitrates(int i, EncodingInfo[] encodingInfoArr, EncodingInfo[] encodingInfoArr2) {
        if (encodingInfoArr == null || encodingInfoArr2 == null || ArrayExtensions.getLength((Object[]) encodingInfoArr) == 0 || ArrayExtensions.getLength((Object[]) encodingInfoArr2) == 0 || ArrayExtensions.getLength((Object[]) encodingInfoArr) != ArrayExtensions.getLength((Object[]) encodingInfoArr2)) {
            return false;
        }
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) encodingInfoArr); i2++) {
            i -= updateEncoderBitrates(i, encodingInfoArr[i2], encodingInfoArr2[i2], i2);
        }
        return true;
    }

    private void updateLocalDescriptionMediaId() {
        if (!StringExtensions.isNullOrEmpty(getLocalDescriptionMediaId())) {
            return;
        }
        if (!anyInputIsSynchronizable() || getInputSynchronizationDisabled()) {
            setLocalDescriptionMediaId(Utility.generateId());
        } else if (getLocalMedia() == null) {
            setLocalDescriptionMediaId("bG9jYWwtbWVkaWE");
        } else {
            setLocalDescriptionMediaId(getLocalMedia().getId());
        }
    }

    private void updateLocalDescriptionTrackId() {
        if (!StringExtensions.isNullOrEmpty(getLocalDescriptionTrackId())) {
            return;
        }
        if (getLocalTrack() != null) {
            setLocalDescriptionTrackId(getLocalTrack().getId());
        } else if (Global.equals(super.getType(), StreamType.Audio)) {
            setLocalDescriptionTrackId("YXVkaW8tdHJhY2s");
        } else {
            setLocalDescriptionTrackId("dmlkZW8tdHJhY2s");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateLocalRtpParameters(TIOutput[] r9, long r10, java.lang.String r12, java.lang.String r13) {
        /*
            r8 = this;
            r0 = -1
            if (r9 == 0) goto L_0x0050
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r9)
            if (r1 <= 0) goto L_0x0050
            r1 = -1
            int r3 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x0014
            fm.liveswitch.IMediaOutput[] r9 = r8.getInputs((TIOutput[]) r9, (java.lang.String) r12)
            goto L_0x0018
        L_0x0014:
            fm.liveswitch.IMediaOutput[] r9 = r8.getInputs((TIOutput[]) r9, (long) r10)
        L_0x0018:
            if (r9 == 0) goto L_0x0050
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r9)
            if (r1 <= 0) goto L_0x0050
            fm.liveswitch.EncodingInfo[] r1 = r8.getInputMaxOutputEncodings(r9)
            r2 = 0
            if (r1 == 0) goto L_0x0038
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)
            if (r3 <= 0) goto L_0x0038
            r1 = r1[r2]
            int r1 = r1.getBitrate()
            int r1 = fm.liveswitch.ConstraintUtility.min((int) r0, (int) r1)
            goto L_0x0039
        L_0x0038:
            r1 = -1
        L_0x0039:
            fm.liveswitch.EncodingInfo[] r9 = r8.getInputTargetOutputEncodings(r9)
            if (r9 == 0) goto L_0x0051
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r9)
            if (r3 <= 0) goto L_0x0051
            r9 = r9[r2]
            int r9 = r9.getBitrate()
            int r1 = fm.liveswitch.ConstraintUtility.min((int) r1, (int) r9)
            goto L_0x0051
        L_0x0050:
            r1 = -1
        L_0x0051:
            if (r1 != r0) goto L_0x0057
            int r1 = r8.getDefaultInitialBandwidthEstimate()
        L_0x0057:
            int r9 = super.getRemoteBandwidth()
            if (r9 <= 0) goto L_0x0061
            int r1 = fm.liveswitch.ConstraintUtility.min((int) r1, (int) r9)
        L_0x0061:
            r7 = r1
            fm.liveswitch.RtpParameters r2 = r8.getRtpParameters()
            r3 = r10
            r5 = r12
            r6 = r13
            r2.addLocalSynchronizationSource(r3, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaStream.updateLocalRtpParameters(fm.liveswitch.IMediaOutput[], long, java.lang.String, java.lang.String):void");
    }

    private void updateMaxVideoSize(VideoFrame videoFrame, boolean z) {
        try {
            for (VideoBuffer videoBuffer : (VideoBuffer[]) videoFrame.getBuffers()) {
                if (videoBuffer != null && videoBuffer.getWidth() > 0 && videoBuffer.getHeight() > 0) {
                    int width = videoBuffer.getWidth();
                    int height = videoBuffer.getHeight();
                    if (width < height) {
                        int i = width;
                        width = height;
                        height = i;
                    }
                    if (z) {
                        Size size = this.__maxSendVideoSize;
                        if (size.getWidth() * size.getHeight() < width * height) {
                            this.__maxSendVideoSize = new Size(width, height);
                        }
                    } else {
                        Size size2 = this.__maxReceiveVideoSize;
                        if (size2.getWidth() * size2.getHeight() < width * height) {
                            this.__maxReceiveVideoSize = new Size(width, height);
                        }
                    }
                }
            }
        } catch (Exception e) {
            __log.error("Failed to update max video size.", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateRemoteRtpParameters(TIInput[] r7, long r8, java.lang.String r10, java.lang.String r11) {
        /*
            r6 = this;
            r0 = -1
            if (r7 == 0) goto L_0x0038
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r7)
            if (r1 <= 0) goto L_0x0038
            r1 = -1
            int r3 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x0014
            fm.liveswitch.IMediaInput[] r7 = r6.getOutputs((TIInput[]) r7, (java.lang.String) r10)
            goto L_0x0018
        L_0x0014:
            fm.liveswitch.IMediaInput[] r7 = r6.getOutputs((TIInput[]) r7, (long) r8)
        L_0x0018:
            if (r7 == 0) goto L_0x0038
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r7)
            if (r1 <= 0) goto L_0x0038
            fm.liveswitch.EncodingInfo[] r7 = r6.getOutputMaxInputEncodings(r7)
            if (r7 == 0) goto L_0x0038
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r7)
            if (r1 <= 0) goto L_0x0038
            r1 = 0
            r7 = r7[r1]
            int r7 = r7.getBitrate()
            int r7 = fm.liveswitch.ConstraintUtility.min((int) r0, (int) r7)
            goto L_0x0039
        L_0x0038:
            r7 = -1
        L_0x0039:
            if (r7 != r0) goto L_0x0045
            fm.liveswitch.EncodingInfo r1 = r6.getRemoteEncoding()
            if (r1 == 0) goto L_0x0045
            int r7 = r1.getBitrate()
        L_0x0045:
            if (r7 != r0) goto L_0x004b
            int r7 = r6.getDefaultInitialBandwidthEstimate()
        L_0x004b:
            int r0 = super.getLocalBandwidth()
            if (r0 <= 0) goto L_0x0055
            int r7 = fm.liveswitch.ConstraintUtility.min((int) r7, (int) r0)
        L_0x0055:
            r5 = r7
            fm.liveswitch.RtpParameters r0 = r6.getRtpParameters()
            r1 = r8
            r3 = r10
            r4 = r11
            r0.addRemoteSynchronizationSource(r1, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaStream.updateRemoteRtpParameters(fm.liveswitch.IMediaInput[], long, java.lang.String, java.lang.String):void");
    }

    private boolean validateMapAttribute(TFormat tformat, boolean z) {
        if (tformat == null) {
            return false;
        }
        int registeredPayloadType = tformat.getRegisteredPayloadType();
        MediaFormat registeredFormat = getRegisteredFormat(registeredPayloadType);
        if ((registeredFormat != null && registeredFormat.isCompatible(tformat) && registeredFormat.getRegisteredPayloadType() == registeredPayloadType) || this.__localFormatRegistry.getCount() <= 0) {
            return true;
        }
        MediaFormat compatible = this.__localFormatRegistry.getCompatible(tformat);
        if (compatible == null) {
            return false;
        }
        if (z) {
            compatible.setRegisteredPayloadType(tformat.getRegisteredPayloadType());
        } else {
            this.__remoteFormatRegistry.add(compatible);
        }
        return true;
    }
}
