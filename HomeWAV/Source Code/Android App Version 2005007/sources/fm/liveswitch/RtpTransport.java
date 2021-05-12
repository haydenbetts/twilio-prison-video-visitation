package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFormatCollection;
import fm.liveswitch.MediaFrame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class RtpTransport<TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>, TFormatCollection extends MediaFormatCollection<TFormat, TFormatCollection>> extends MediaTransport<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> {
    private static ILog __log = Log.getLogger(RtpTransport.class);
    private RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> __defaultSender;
    private long __defaultSynchronizationSource = Utility.generateSynchronizationSource();
    private StreamDirection __direction = StreamDirection.Inactive;
    private IFunction0<Long> __getInboundRtcpTransportSystemTimestamp = new IFunctionDelegate0<Long>() {
        public String getId() {
            return "fm.liveswitch.ManagedStopwatch.getTimestamp";
        }

        public Long invoke() {
            return Long.valueOf(ManagedStopwatch.getTimestamp());
        }
    };
    private IFunction0<Long> __getInboundRtpTransportSystemTimestamp = new IFunctionDelegate0<Long>() {
        public String getId() {
            return "fm.liveswitch.ManagedStopwatch.getTimestamp";
        }

        public Long invoke() {
            return Long.valueOf(ManagedStopwatch.getTimestamp());
        }
    };
    private IFunction0<Long> __getOutboundRtcpTransportSystemTimestamp = new IFunctionDelegate0<Long>() {
        public String getId() {
            return "fm.liveswitch.ManagedStopwatch.getTimestamp";
        }

        public Long invoke() {
            return Long.valueOf(ManagedStopwatch.getTimestamp());
        }
    };
    private long __lastRembSentSystemTimestamp = -1;
    private long __lastReportSentSystemTimestamp = -1;
    private RtpListener __listener = null;
    private String __logScope;
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onSendControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onSendControlFrames = new ArrayList();
    private RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection>[] __receivers = new RtpReceiver[0];
    private Object __receiversLock = new Object();
    private double __requestedInboundBitrate = -1.0d;
    private double __requestedOutboundBitrate = -1.0d;
    private Transport __rtcpTransport;
    private Transport __rtpTransport;
    private RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection>[] __senders = new RtpSender[0];
    private Object __sendersLock = new Object();
    private int __testRoundTripTime = -1;
    private boolean _disableAutomaticReports;
    private HexDump _hexDump;
    private boolean _includeMidSdes;
    private JitterConfig _jitterConfig;
    private boolean _legacyReceiver;
    private SrtpProtectionParameters _localProtectionParameters;
    private String _mid;
    private NackConfig _nackConfig;
    private IAction1<MediaControlFrame[]> _onSendControlFrameResponses = null;
    private IAction1<MediaControlFrame[]> _onSendControlFrames = null;
    private RedFecConfig _redFecConfig;
    private boolean _rembEnabled;
    private SrtpProtectionParameters _remoteProtectionParameters;
    private SimulcastMode _simulcastMode;
    private IFunction1<DataBuffer, DataBuffer> _testReceivedRtpBuffer;
    private IFunction1<DataBuffer, DataBuffer> _testSendingRtpBuffer;
    private StreamType _type;

    public abstract TFrame createFrame(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer, TFormat tformat);

    public abstract TFrame[] createFrameArray(int i);

    public void addOnSendControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onSendControlFrameResponses == null) {
                this._onSendControlFrameResponses = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(RtpTransport.this.__onSendControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onSendControlFrameResponses.add(iAction1);
        }
    }

    public void addOnSendControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onSendControlFrames == null) {
                this._onSendControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(RtpTransport.this.__onSendControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onSendControlFrames.add(iAction1);
        }
    }

    public boolean canSendRemb(long j) {
        if (!getRembEnabled()) {
            return false;
        }
        long j2 = this.__lastRembSentSystemTimestamp;
        if (j2 == -1 || j - j2 >= ((long) getRembIntervalTicks())) {
            return true;
        }
        return false;
    }

    public boolean canSendReport(long j) {
        int reportIntervalTicks = getReportIntervalTicks();
        if (hasUnreportedLoss()) {
            double min = ConstraintUtility.min(getInboundBandwidthEstimate(), getOutboundBandwidthEstimate());
            if (min == -1.0d) {
                reportIntervalTicks = MathAssistant.min(reportIntervalTicks, Constants.getTicksPerSecond());
            } else {
                reportIntervalTicks = MathAssistant.min(reportIntervalTicks, (int) ((360.0d / min) * ((double) Constants.getTicksPerSecond())));
            }
        }
        long j2 = this.__lastReportSentSystemTimestamp;
        return j2 == -1 || j - j2 >= ((long) reportIntervalTicks);
    }

    private RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> createReceiver(long j, String str, String str2, int i) {
        StreamType type = getType();
        NackConfig nackConfig = getNackConfig();
        RedFecConfig redFecConfig = getRedFecConfig();
        JitterConfig jitterConfig = getJitterConfig();
        boolean legacyReceiver = getLegacyReceiver();
        RtpIParameters parameters = super.getParameters();
        RtpIFormatParameters formatParameters = super.getFormatParameters();
        RtpIExtensionParameters extensionParameters = super.getExtensionParameters();
        RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpReceiver = r1;
        SrtpProtectionParameters localProtectionParameters = getLocalProtectionParameters();
        SrtpProtectionParameters remoteProtectionParameters = getRemoteProtectionParameters();
        HexDump hexDump = getHexDump();
        AnonymousClass3 r18 = r1;
        AnonymousClass3 r1 = new IFunctionDelegate1<Integer, TFrame[]>() {
            public String getId() {
                return "fm.liveswitch.RtpTransport<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.createFrameArray";
            }

            public TFrame[] invoke(Integer num) {
                return RtpTransport.this.createFrameArray(num.intValue());
            }
        };
        RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpReceiver2 = new RtpReceiver<>(type, j, str, str2, i, nackConfig, redFecConfig, jitterConfig, legacyReceiver, parameters, formatParameters, extensionParameters, localProtectionParameters, remoteProtectionParameters, hexDump, r18);
        RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpReceiver3 = rtpReceiver;
        rtpReceiver3._createFrame = new IFunctionDelegate3<RtpPacketHeader, DataBuffer, TFormat, TFrame>() {
            public String getId() {
                return "fm.liveswitch.RtpTransport<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.createFrame";
            }

            public TFrame invoke(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer, TFormat tformat) {
                return RtpTransport.this.createFrame(rtpPacketHeader, dataBuffer, tformat);
            }
        };
        rtpReceiver3.setReceiveFrame(new IActionDelegate1<TFrame>() {
            public String getId() {
                return "fm.liveswitch.MediaTransport<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.receiveFrame";
            }

            public void invoke(TFrame tframe) {
                RtpTransport.this.receiveFrame(tframe);
            }
        });
        rtpReceiver3.setSendControlFrames(new IFunctionDelegate1<MediaControlFrame[], Integer>() {
            public String getId() {
                return "fm.liveswitch.MediaTransport<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.sendControlFrames";
            }

            public Integer invoke(MediaControlFrame[] mediaControlFrameArr) {
                return Integer.valueOf(RtpTransport.this.sendControlFrames(mediaControlFrameArr));
            }
        });
        rtpReceiver3.setTestReceivedRtpBuffer(getTestReceivedRtpBuffer());
        return rtpReceiver3;
    }

    private RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> createSender(long j, String str, String str2, int i) {
        RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpSender = r0;
        RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpSender2 = new RtpSender<>(getType(), j, str, str2, i, getNackConfig(), getRedFecConfig(), super.getParameters(), super.getFormatParameters(), super.getExtensionParameters(), getLocalProtectionParameters(), getRemoteProtectionParameters(), getHexDump(), getRtpTransport(), getRtcpTransport());
        RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpSender3 = rtpSender;
        rtpSender3.setTestSendingRtpBuffer(getTestSendingRtpBuffer());
        return rtpSender3;
    }

    private void destroyReceiver(RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpReceiver) {
        rtpReceiver._createFrame = null;
        rtpReceiver.setReceiveFrame((IAction1<TFrame>) null);
        rtpReceiver.setSendControlFrames((IFunction1<MediaControlFrame[], Integer>) null);
        rtpReceiver.setTestReceivedRtpBuffer((IFunction1<DataBuffer, DataBuffer>) null);
        rtpReceiver.destroy();
    }

    private void destroySender(RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpSender) {
        rtpSender.setTestSendingRtpBuffer((IFunction1<DataBuffer, DataBuffer>) null);
        rtpSender.destroy();
    }

    public int doSendControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        IAction1<MediaControlFrame[]> iAction1;
        RtpOutboundRtcp rtpOutboundRtcp = new RtpOutboundRtcp();
        rtpOutboundRtcp.setFrames(mediaControlFrameArr);
        rtpOutboundRtcp.setTransportSystemTimestamp(getGetOutboundRtcpTransportSystemTimestamp().invoke().longValue());
        int doSendOutboundRtcp = doSendOutboundRtcp(rtpOutboundRtcp);
        if (doSendOutboundRtcp == MediaTransport.getSendFrameSuccess() && (iAction1 = this._onSendControlFrameResponses) != null) {
            iAction1.invoke(rtpOutboundRtcp.getFrames());
        }
        return doSendOutboundRtcp;
    }

    public int doSendControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        IAction1<MediaControlFrame[]> iAction1;
        RtpOutboundRtcp rtpOutboundRtcp = new RtpOutboundRtcp();
        rtpOutboundRtcp.setFrames(mediaControlFrameArr);
        rtpOutboundRtcp.setTransportSystemTimestamp(getGetOutboundRtcpTransportSystemTimestamp().invoke().longValue());
        int doSendOutboundRtcp = doSendOutboundRtcp(rtpOutboundRtcp);
        if (doSendOutboundRtcp == MediaTransport.getSendFrameSuccess() && (iAction1 = this._onSendControlFrames) != null) {
            iAction1.invoke(rtpOutboundRtcp.getFrames());
        }
        return doSendOutboundRtcp;
    }

    public int doSendFrame(TFrame tframe) {
        long synchronizationSource = tframe.getSynchronizationSource();
        RtpSender sender = getSender(synchronizationSource, tframe.getRtpStreamId(), tframe.getRepairedRtpStreamId(), -1, true);
        MediaBuffer buffer = tframe.getBuffer(true);
        if (buffer == null) {
            __log.error(this.__logScope, StringExtensions.format("Discarding outbound frame for SSRC {0}. No packetized buffers.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource))));
            return MediaTransport.getSendFrameErrorNoPacketizedBuffers();
        }
        int sendFrame = sender.sendFrame(tframe, buffer);
        if (sendFrame == MediaTransport.getSendFrameSuccess() && !buffer.getFormat().getIsInjected()) {
            sendReport();
        }
        return sendFrame;
    }

    private int doSendOutboundRtcp(RtpOutboundRtcp rtpOutboundRtcp) {
        RembControlFrame rembControlFrame;
        if (canSendRemb(rtpOutboundRtcp.getTransportSystemTimestamp()) && (rembControlFrame = getRembControlFrame()) != null) {
            rtpOutboundRtcp.setFrames(MediaControlFrame.addControlFrame(rtpOutboundRtcp.getFrames(), rembControlFrame, 0));
            this.__lastRembSentSystemTimestamp = rtpOutboundRtcp.getTransportSystemTimestamp();
        }
        processOutboundRtcp(rtpOutboundRtcp);
        if (ArrayExtensions.getLength((Object[]) rtpOutboundRtcp.getFrames()) == 0 && !canSendReport(rtpOutboundRtcp.getTransportSystemTimestamp())) {
            return MediaTransport.getSendFrameErrorRateLimited();
        }
        long firstLocalSynchronizationSource = getFirstLocalSynchronizationSource();
        if (firstLocalSynchronizationSource == 0) {
            firstLocalSynchronizationSource = getDefaultLocalSynchronizationSource();
        }
        if (ArrayExtensions.getLength((Object[]) rtpOutboundRtcp.getFrames()) != 1 || canSendReport(rtpOutboundRtcp.getTransportSystemTimestamp())) {
            ReportControlFrame[] reportControlFrames = getReportControlFrames(rtpOutboundRtcp.getTransportSystemTimestamp());
            rtpOutboundRtcp.setFrames(MediaControlFrame.addControlFrames(rtpOutboundRtcp.getFrames(), reportControlFrames, 0));
            this.__lastReportSentSystemTimestamp = rtpOutboundRtcp.getTransportSystemTimestamp();
            rtpOutboundRtcp.setFrames(MediaControlFrame.addControlFrame(rtpOutboundRtcp.getFrames(), getSdesControlFrame(), ArrayExtensions.getLength((Object[]) reportControlFrames)));
            firstLocalSynchronizationSource = reportControlFrames[0].getSynchronizationSource();
        }
        RtpSender sender = getSender(firstLocalSynchronizationSource);
        if (sender == null) {
            return MediaTransport.getSendFrameErrorUnknownSynchronizationSource();
        }
        IAction1<MediaControlFrame[]> iAction1 = this._onSendControlFrames;
        if (iAction1 != null) {
            iAction1.invoke(rtpOutboundRtcp.getFrames());
        }
        return sender.sendControlFrames(rtpOutboundRtcp.getFrames());
    }

    public boolean doStart() {
        if (super.getParameters() == null) {
            __log.error(this.__logScope, "Cannot start. Parameters are not set.");
            return false;
        } else if (getLocalProtectionParameters() == null || getRemoteProtectionParameters() == null) {
            __log.error(this.__logScope, "Cannot start. Protection parameters are not set.");
            return false;
        } else if (getRtpTransport().getIsClosed()) {
            __log.error(this.__logScope, "Cannot start. Inner RTP transport is closed.");
            return false;
        } else if (getRtcpTransport().getIsClosed()) {
            __log.error(this.__logScope, "Cannot start. Inner RTCP transport is closed.");
            return false;
        } else if (getListener() == null) {
            __log.error(this.__logScope, "Cannot start. Listener is not set.");
            return false;
        } else {
            this.__defaultSender = createSender(getDefaultLocalSynchronizationSource(), (String) null, (String) null, -1);
            long[] localSynchronizationSources = super.getParameters().getLocalSynchronizationSources();
            String[] localRtpStreamIds = super.getParameters().getLocalRtpStreamIds();
            String[] localRepairedRtpStreamIds = super.getParameters().getLocalRepairedRtpStreamIds();
            int[] initialSendBandwidthEstimates = super.getParameters().getInitialSendBandwidthEstimates();
            for (int i = 0; i < ArrayExtensions.getLength(localSynchronizationSources); i++) {
                getOrAddSenderWithLock(localSynchronizationSources[i], localRtpStreamIds[i], localRepairedRtpStreamIds[i], initialSendBandwidthEstimates[i]);
            }
            long[] remoteSynchronizationSources = super.getParameters().getRemoteSynchronizationSources();
            String[] remoteRtpStreamIds = super.getParameters().getRemoteRtpStreamIds();
            String[] remoteRepairedRtpStreamIds = super.getParameters().getRemoteRepairedRtpStreamIds();
            int[] initialReceiveBandwidthEstimates = super.getParameters().getInitialReceiveBandwidthEstimates();
            for (int i2 = 0; i2 < ArrayExtensions.getLength(remoteSynchronizationSources); i2++) {
                getOrAddReceiverWithLock(remoteSynchronizationSources[i2], remoteRtpStreamIds[i2], remoteRepairedRtpStreamIds[i2], initialReceiveBandwidthEstimates[i2]);
            }
            getListener().removeOnReceiveRtp(new IActionDelegate1<RtpInboundRtp>() {
                public String getId() {
                    return "fm.liveswitch.RtpTransport<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.listener_OnReceiveRtp";
                }

                public void invoke(RtpInboundRtp rtpInboundRtp) {
                    RtpTransport.this.listener_OnReceiveRtp(rtpInboundRtp);
                }
            });
            getListener().addOnReceiveRtp(new IActionDelegate1<RtpInboundRtp>() {
                public String getId() {
                    return "fm.liveswitch.RtpTransport<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.listener_OnReceiveRtp";
                }

                public void invoke(RtpInboundRtp rtpInboundRtp) {
                    RtpTransport.this.listener_OnReceiveRtp(rtpInboundRtp);
                }
            });
            getListener().removeOnReceiveRtcp(new IActionDelegate1<RtpInboundRtcp>() {
                public String getId() {
                    return "fm.liveswitch.RtpTransport<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.listener_OnReceiveRtcp";
                }

                public void invoke(RtpInboundRtcp rtpInboundRtcp) {
                    RtpTransport.this.listener_OnReceiveRtcp(rtpInboundRtcp);
                }
            });
            getListener().addOnReceiveRtcp(new IActionDelegate1<RtpInboundRtcp>() {
                public String getId() {
                    return "fm.liveswitch.RtpTransport<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.listener_OnReceiveRtcp";
                }

                public void invoke(RtpInboundRtcp rtpInboundRtcp) {
                    RtpTransport.this.listener_OnReceiveRtcp(rtpInboundRtcp);
                }
            });
            return true;
        }
    }

    public boolean doStop() {
        getListener().removeOnReceiveRtcp(new IActionDelegate1<RtpInboundRtcp>() {
            public String getId() {
                return "fm.liveswitch.RtpTransport<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.listener_OnReceiveRtcp";
            }

            public void invoke(RtpInboundRtcp rtpInboundRtcp) {
                RtpTransport.this.listener_OnReceiveRtcp(rtpInboundRtcp);
            }
        });
        getListener().removeOnReceiveRtp(new IActionDelegate1<RtpInboundRtp>() {
            public String getId() {
                return "fm.liveswitch.RtpTransport<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.listener_OnReceiveRtp";
            }

            public void invoke(RtpInboundRtp rtpInboundRtp) {
                RtpTransport.this.listener_OnReceiveRtp(rtpInboundRtp);
            }
        });
        for (RtpReceiver remoteSynchronizationSource : getReceivers()) {
            removeReceiver(remoteSynchronizationSource.getRemoteSynchronizationSource());
        }
        for (RtpSender localSynchronizationSource : getSenders()) {
            removeSender(localSynchronizationSource.getLocalSynchronizationSource());
        }
        destroySender(this.__defaultSender);
        return true;
    }

    private int getCcmIncrementIntervalTicks() {
        return getCcmIntervalTicks() * 2;
    }

    private int getCcmIntervalTicks() {
        int roundTripTime = getRoundTripTime();
        if (roundTripTime >= 0) {
            return roundTripTime * Constants.getTicksPerMillisecond();
        }
        return Constants.getTicksPerMillisecond() * 500;
    }

    public long getDefaultLocalSynchronizationSource() {
        return this.__defaultSynchronizationSource;
    }

    private SdesChunk getDefaultSdesChunk() {
        long firstLocalSynchronizationSource = getFirstLocalSynchronizationSource();
        if (firstLocalSynchronizationSource == 0) {
            firstLocalSynchronizationSource = getDefaultLocalSynchronizationSource();
        }
        SdesItem sdesItem = new SdesItem(SdesItemType.getCanonicalName(), super.getParameters().getCanonicalName());
        String mid = getIncludeMidSdes() ? getMid() : null;
        if (mid != null) {
            return new SdesChunk(firstLocalSynchronizationSource, new SdesItem[]{sdesItem, new SdesItem(SdesItemType.getMid(), mid)});
        }
        return new SdesChunk(firstLocalSynchronizationSource, new SdesItem[]{sdesItem});
    }

    public StreamDirection getDirection() {
        return this.__direction;
    }

    public boolean getDisableAutomaticReports() {
        return this._disableAutomaticReports;
    }

    public long getFirstLocalSynchronizationSource() {
        RtpSender firstSender = getFirstSender();
        if (firstSender != null) {
            return firstSender.getLocalSynchronizationSource();
        }
        return 0;
    }

    public RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getFirstReceiver() {
        return (RtpReceiver) Utility.firstOrDefault((T[]) getReceivers());
    }

    public long getFirstRemoteSynchronizationSource() {
        RtpReceiver firstReceiver = getFirstReceiver();
        if (firstReceiver != null) {
            return firstReceiver.getRemoteSynchronizationSource();
        }
        return 0;
    }

    public RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getFirstSender() {
        return (RtpSender) Utility.firstOrDefault((T[]) getSenders());
    }

    public IFunction0<Long> getGetInboundRtcpTransportSystemTimestamp() {
        return this.__getInboundRtcpTransportSystemTimestamp;
    }

    public IFunction0<Long> getGetInboundRtpTransportSystemTimestamp() {
        return this.__getInboundRtpTransportSystemTimestamp;
    }

    public IFunction0<Long> getGetOutboundRtcpTransportSystemTimestamp() {
        return this.__getOutboundRtcpTransportSystemTimestamp;
    }

    public HexDump getHexDump() {
        return this._hexDump;
    }

    public double getInboundBandwidthEstimate() {
        return getLossBasedInboundBandwidthEstimate();
    }

    public boolean getIncludeMidSdes() {
        return this._includeMidSdes;
    }

    public double getInitialInboundBandwidthEstimate() {
        return getInitialLossBasedInboundBandwidthEstimate();
    }

    public double getInitialLossBasedInboundBandwidthEstimate() {
        double d;
        if (Global.equals(getSimulcastMode(), SimulcastMode.Disabled)) {
            RtpReceiver[] receivers = getReceivers();
            if (ArrayExtensions.getLength((Object[]) receivers) > 0) {
                double initialLossBasedBandwidthEstimate = receivers[0].getInitialLossBasedBandwidthEstimate();
                if (initialLossBasedBandwidthEstimate != -1.0d) {
                    d = initialLossBasedBandwidthEstimate + 0.0d;
                }
            }
            d = 0.0d;
        } else {
            double d2 = 0.0d;
            for (RtpReceiver initialLossBasedBandwidthEstimate2 : getReceivers()) {
                double initialLossBasedBandwidthEstimate3 = initialLossBasedBandwidthEstimate2.getInitialLossBasedBandwidthEstimate();
                if (initialLossBasedBandwidthEstimate3 != -1.0d) {
                    d2 += initialLossBasedBandwidthEstimate3;
                }
            }
            d = d2;
        }
        if (d == 0.0d) {
            return -1.0d;
        }
        return d;
    }

    public double getInitialLossBasedOutboundBandwidthEstimate() {
        double d;
        if (Global.equals(getSimulcastMode(), SimulcastMode.Disabled)) {
            RtpSender[] senders = getSenders();
            if (ArrayExtensions.getLength((Object[]) senders) > 0) {
                double initialLossBasedBandwidthEstimate = senders[0].getInitialLossBasedBandwidthEstimate();
                if (initialLossBasedBandwidthEstimate != -1.0d) {
                    d = initialLossBasedBandwidthEstimate + 0.0d;
                }
            }
            d = 0.0d;
        } else {
            double d2 = 0.0d;
            for (RtpSender initialLossBasedBandwidthEstimate2 : getSenders()) {
                double initialLossBasedBandwidthEstimate3 = initialLossBasedBandwidthEstimate2.getInitialLossBasedBandwidthEstimate();
                if (initialLossBasedBandwidthEstimate3 != -1.0d) {
                    d2 += initialLossBasedBandwidthEstimate3;
                }
            }
            d = d2;
        }
        if (d == 0.0d) {
            return -1.0d;
        }
        return d;
    }

    public double getInitialOutboundBandwidthEstimate() {
        return getInitialLossBasedOutboundBandwidthEstimate();
    }

    public JitterConfig getJitterConfig() {
        return this._jitterConfig;
    }

    public boolean getLegacyReceiver() {
        return this._legacyReceiver;
    }

    public RtpListener getListener() {
        return this.__listener;
    }

    public SrtpProtectionParameters getLocalProtectionParameters() {
        return this._localProtectionParameters;
    }

    public double getLossBasedInboundBandwidthEstimate() {
        double d;
        if (Global.equals(getSimulcastMode(), SimulcastMode.Disabled)) {
            RtpReceiver[] receivers = getReceivers();
            if (ArrayExtensions.getLength((Object[]) receivers) > 0) {
                double lossBasedBandwidthEstimate = receivers[0].getLossBasedBandwidthEstimate();
                if (lossBasedBandwidthEstimate != -1.0d) {
                    d = lossBasedBandwidthEstimate + 0.0d;
                }
            }
            d = 0.0d;
        } else {
            double d2 = 0.0d;
            for (RtpReceiver lossBasedBandwidthEstimate2 : getReceivers()) {
                double lossBasedBandwidthEstimate3 = lossBasedBandwidthEstimate2.getLossBasedBandwidthEstimate();
                if (lossBasedBandwidthEstimate3 != -1.0d) {
                    d2 += lossBasedBandwidthEstimate3;
                }
            }
            d = d2;
        }
        if (d == 0.0d) {
            return -1.0d;
        }
        return d;
    }

    public double getLossBasedOutboundBandwidthEstimate() {
        double d;
        if (Global.equals(getSimulcastMode(), SimulcastMode.Disabled)) {
            RtpSender[] senders = getSenders();
            if (ArrayExtensions.getLength((Object[]) senders) > 0) {
                double lossBasedBandwidthEstimate = senders[0].getLossBasedBandwidthEstimate();
                if (lossBasedBandwidthEstimate != -1.0d) {
                    d = lossBasedBandwidthEstimate + 0.0d;
                }
            }
            d = 0.0d;
        } else {
            double d2 = 0.0d;
            for (RtpSender lossBasedBandwidthEstimate2 : getSenders()) {
                double lossBasedBandwidthEstimate3 = lossBasedBandwidthEstimate2.getLossBasedBandwidthEstimate();
                if (lossBasedBandwidthEstimate3 != -1.0d) {
                    d2 += lossBasedBandwidthEstimate3;
                }
            }
            d = d2;
        }
        if (d == 0.0d) {
            return -1.0d;
        }
        return d;
    }

    public String getMid() {
        return this._mid;
    }

    public NackConfig getNackConfig() {
        return this._nackConfig;
    }

    public long getOctetsReceivedRtp() {
        long j = 0;
        for (RtpReceiver octetsReceivedRtp : getReceivers()) {
            j += octetsReceivedRtp.getOctetsReceivedRtp();
        }
        return j;
    }

    public long getOctetsSentRtp() {
        long j = 0;
        for (RtpSender octetsSentRtp : getSenders()) {
            j += octetsSentRtp.getOctetsSentRtp();
        }
        return j;
    }

    private RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getOrAddReceiverWithLock(long j, String str, String str2, int i) {
        RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpReceiver;
        synchronized (this.__receiversLock) {
            RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpReceiver2 = null;
            if (j != -1) {
                try {
                    rtpReceiver = getReceiver(j);
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                rtpReceiver = null;
            }
            if (rtpReceiver == null) {
                if (str != null) {
                    rtpReceiver2 = getReceiver(str);
                }
                if (rtpReceiver2 == null) {
                    rtpReceiver = createReceiver(j, str, str2, i);
                    ArrayList arrayList = new ArrayList();
                    for (RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> add : this.__receivers) {
                        arrayList.add(add);
                    }
                    arrayList.add(rtpReceiver);
                    this.__receivers = (RtpReceiver[]) arrayList.toArray(new RtpReceiver[0]);
                } else {
                    rtpReceiver = rtpReceiver2;
                }
            }
        }
        return rtpReceiver;
    }

    private RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getOrAddSenderWithLock(long j, String str, String str2, int i) {
        RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpSender;
        synchronized (this.__sendersLock) {
            RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpSender2 = null;
            if (j != -1) {
                try {
                    rtpSender = getSender(j);
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                rtpSender = null;
            }
            if (rtpSender == null) {
                if (str != null) {
                    rtpSender2 = getSender(str);
                }
                if (rtpSender2 == null) {
                    rtpSender = createSender(j, str, str2, i);
                    ArrayList arrayList = new ArrayList();
                    for (RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> add : this.__senders) {
                        arrayList.add(add);
                    }
                    arrayList.add(rtpSender);
                    this.__senders = (RtpSender[]) arrayList.toArray(new RtpSender[0]);
                } else {
                    rtpSender = rtpSender2;
                }
            }
        }
        return rtpSender;
    }

    public double getOutboundBandwidthEstimate() {
        return getLossBasedOutboundBandwidthEstimate();
    }

    private int getPliIntervalTicks() {
        int roundTripTime = getRoundTripTime();
        if (roundTripTime >= 0) {
            return roundTripTime * 2 * Constants.getTicksPerMillisecond();
        }
        return Constants.getTicksPerMillisecond() * 1000;
    }

    private RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getReceiver(String str) {
        for (RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpReceiver : getReceivers()) {
            if (Global.equals(rtpReceiver.getRemoteRtpStreamId(), str)) {
                return rtpReceiver;
            }
        }
        return null;
    }

    private RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getReceiver(long j) {
        return getReceiver(j, (IRtpHeaderExtension) null, false);
    }

    private RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getReceiver(long j, IRtpHeaderExtension iRtpHeaderExtension, boolean z) {
        int i;
        String str;
        String str2;
        RtpHeaderExtension parseRawHeaderExtension;
        RtpHeaderSdesRtpStreamId sdesRtpStreamId;
        for (RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpReceiver : getReceivers()) {
            if (rtpReceiver.getRemoteSynchronizationSource() == j) {
                return rtpReceiver;
            }
        }
        if (!z) {
            return null;
        }
        RtpRawHeaderExtension rtpRawHeaderExtension = (RtpRawHeaderExtension) Global.tryCast(iRtpHeaderExtension, RtpRawHeaderExtension.class);
        RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry = super.getExtensionParameters().getRtpHeaderExtensionRegistry();
        if (rtpRawHeaderExtension == null || rtpHeaderExtensionRegistry == null || rtpHeaderExtensionRegistry.getNumberOfNegotiatedExtensions() <= 0 || (parseRawHeaderExtension = RtpHeaderExtension.parseRawHeaderExtension(rtpRawHeaderExtension, rtpHeaderExtensionRegistry)) == null || (sdesRtpStreamId = parseRawHeaderExtension.getSdesRtpStreamId()) == null) {
            str2 = null;
            str = null;
            i = -1;
        } else {
            String text = sdesRtpStreamId.getText();
            str = super.getParameters().getRemoteRepairedRtpStreamId(text);
            i = super.getParameters().getInitialReceiveBandwidthEstimate(text);
            str2 = text;
        }
        return getOrAddReceiverWithLock(j, str2, str, i);
    }

    public RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection>[] getReceivers() {
        return this.__receivers;
    }

    public RedFecConfig getRedFecConfig() {
        return this._redFecConfig;
    }

    private RembControlFrame getRembControlFrame() {
        double d;
        if (Global.equals(getDirection(), StreamDirection.SendOnly) || Global.equals(getDirection(), StreamDirection.Inactive)) {
            return null;
        }
        RtpReceiver[] receivers = getReceivers();
        ArrayList arrayList = new ArrayList();
        for (RtpReceiver remoteSynchronizationSource : receivers) {
            long remoteSynchronizationSource2 = remoteSynchronizationSource.getRemoteSynchronizationSource();
            if (remoteSynchronizationSource2 != -1) {
                arrayList.add(Long.valueOf(remoteSynchronizationSource2));
            }
        }
        if (ArrayListExtensions.getCount(arrayList) == 0) {
            return null;
        }
        if (Global.equals(getSimulcastMode(), SimulcastMode.Disabled) || ArrayExtensions.getLength((Object[]) receivers) == 1) {
            d = getInboundBandwidthEstimate();
        } else {
            d = getInitialInboundBandwidthEstimate() * 1.5d;
        }
        if (d == -1.0d) {
            return null;
        }
        this.__requestedInboundBitrate = d;
        return new RembControlFrame(((long) d) * 1000, Utility.toLongArray(arrayList));
    }

    public boolean getRembEnabled() {
        return this._rembEnabled;
    }

    private int getRembIntervalTicks() {
        return Constants.getTicksPerSecond();
    }

    public SrtpProtectionParameters getRemoteProtectionParameters() {
        return this._remoteProtectionParameters;
    }

    private ReportControlFrame[] getReportControlFrames(long j) {
        ArrayList arrayList = new ArrayList();
        for (RtpReceiver reportBlock : getReceivers()) {
            ReportBlock reportBlock2 = reportBlock.getReportBlock(j);
            if (reportBlock2 != null) {
                arrayList.add(reportBlock2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (RtpSender sRControlFrame : getSenders()) {
            SRControlFrame sRControlFrame2 = sRControlFrame.getSRControlFrame((ReportBlock[]) arrayList.toArray(new ReportBlock[0]), j);
            if (sRControlFrame2 != null) {
                arrayList2.add(sRControlFrame2);
                arrayList.clear();
            }
        }
        if (__log.getIsVerboseEnabled() && ArrayListExtensions.getCount(arrayList2) > 0) {
            String[] strArr = new String[ArrayListExtensions.getCount(arrayList2)];
            Iterator it = arrayList2.iterator();
            int i = 0;
            while (it.hasNext()) {
                strArr[i] = LongExtensions.toString(Long.valueOf(((ReportControlFrame) it.next()).getSynchronizationSource()));
                i++;
            }
            __log.verbose(this.__logScope, StringExtensions.format("Generated SR control frame(s) for SSRCs {0} with {1} report block(s).", StringExtensions.join("/", strArr), IntegerExtensions.toString(Integer.valueOf(ArrayExtensions.getLength((Object[]) ((ReportControlFrame) ArrayListExtensions.getItem(arrayList2).get(0)).getReportBlocks())))));
        }
        if (ArrayListExtensions.getCount(arrayList2) == 0) {
            long firstLocalSynchronizationSource = getFirstLocalSynchronizationSource();
            if (firstLocalSynchronizationSource == 0) {
                firstLocalSynchronizationSource = getDefaultLocalSynchronizationSource();
            }
            RRControlFrame rRControlFrame = new RRControlFrame((ReportBlock[]) arrayList.toArray(new ReportBlock[0]));
            rRControlFrame.setSynchronizationSource(firstLocalSynchronizationSource);
            arrayList2.add(rRControlFrame);
            if (__log.getIsVerboseEnabled()) {
                if (firstLocalSynchronizationSource == getDefaultLocalSynchronizationSource()) {
                    __log.verbose(this.__logScope, StringExtensions.format("Generated RR control frame for default SSRC {0} with {1} report block(s).", LongExtensions.toString(Long.valueOf(firstLocalSynchronizationSource)), IntegerExtensions.toString(Integer.valueOf(ArrayListExtensions.getCount(arrayList)))));
                } else {
                    __log.verbose(this.__logScope, StringExtensions.format("Generated RR control frame for first SSRC {0} with {1} report block(s).", LongExtensions.toString(Long.valueOf(firstLocalSynchronizationSource)), IntegerExtensions.toString(Integer.valueOf(ArrayListExtensions.getCount(arrayList)))));
                }
            }
        }
        return (ReportControlFrame[]) arrayList2.toArray(new ReportControlFrame[0]);
    }

    private int getReportIntervalTicks() {
        return Constants.getTicksPerSecond() * 5;
    }

    public double getRequestedInboundBitrate() {
        return this.__requestedInboundBitrate;
    }

    public double getRequestedOutboundBitrate() {
        return this.__requestedOutboundBitrate;
    }

    public int getRoundTripTime() {
        int testRoundTripTime = getTestRoundTripTime();
        if (testRoundTripTime >= 0) {
            return testRoundTripTime;
        }
        for (RtpSender roundTripTime : getSenders()) {
            int roundTripTime2 = roundTripTime.getRoundTripTime();
            if (roundTripTime2 >= 0) {
                return roundTripTime2;
            }
        }
        Transport rtpTransport = getRtpTransport();
        if (rtpTransport != null) {
            return rtpTransport.getRoundTripTime();
        }
        return -1;
    }

    public Transport getRtcpTransport() {
        return this.__rtcpTransport;
    }

    public Transport getRtpTransport() {
        return this.__rtpTransport;
    }

    public SdesControlFrame getSdesControlFrame() {
        ArrayList arrayList = new ArrayList();
        for (RtpSender sdesChunk : getSenders()) {
            arrayList.add(sdesChunk.getSdesChunk(getIncludeMidSdes() ? getMid() : null));
        }
        if (ArrayListExtensions.getCount(arrayList) == 0) {
            arrayList.add(getDefaultSdesChunk());
        }
        return new SdesControlFrame((SdesChunk[]) arrayList.toArray(new SdesChunk[0]));
    }

    private RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getSender(String str) {
        for (RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpSender : getSenders()) {
            if (Global.equals(rtpSender.getLocalRtpStreamId(), str)) {
                return rtpSender;
            }
        }
        return null;
    }

    private RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getSender(long j) {
        return getSender(j, (String) null, (String) null, -1, false);
    }

    private RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getSender(long j, String str, String str2, int i, boolean z) {
        if (j == getDefaultLocalSynchronizationSource()) {
            return this.__defaultSender;
        }
        for (RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> rtpSender : getSenders()) {
            if (rtpSender.getLocalSynchronizationSource() == j) {
                return rtpSender;
            }
        }
        if (z) {
            return getOrAddSenderWithLock(j, str, str2, i);
        }
        return null;
    }

    public RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection>[] getSenders() {
        return this.__senders;
    }

    public SimulcastMode getSimulcastMode() {
        return this._simulcastMode;
    }

    public long getSingleLocalSynchronizationSource() {
        RtpSender singleSender = getSingleSender();
        if (singleSender != null) {
            return singleSender.getLocalSynchronizationSource();
        }
        return 0;
    }

    public RtpReceiver<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getSingleReceiver() {
        return (RtpReceiver) Utility.singleOrDefault((T[]) getReceivers());
    }

    public long getSingleRemoteSynchronizationSource() {
        RtpReceiver singleReceiver = getSingleReceiver();
        if (singleReceiver != null) {
            return singleReceiver.getRemoteSynchronizationSource();
        }
        return 0;
    }

    public RtpSender<TFrame, TBuffer, TBufferCollection, TFormat, TFormatCollection> getSingleSender() {
        return (RtpSender) Utility.singleOrDefault((T[]) getSenders());
    }

    public IFunction1<DataBuffer, DataBuffer> getTestReceivedRtpBuffer() {
        return this._testReceivedRtpBuffer;
    }

    public int getTestRoundTripTime() {
        return this.__testRoundTripTime;
    }

    public IFunction1<DataBuffer, DataBuffer> getTestSendingRtpBuffer() {
        return this._testSendingRtpBuffer;
    }

    public StreamType getType() {
        return this._type;
    }

    private boolean hasUnreportedLoss() {
        for (RtpReceiver hasUnreportedLoss : getReceivers()) {
            if (hasUnreportedLoss.hasUnreportedLoss()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x002c, code lost:
        r0 = (fm.liveswitch.SRControlFrame) fm.liveswitch.Global.tryCast(r5.getFrames()[0], fm.liveswitch.SRControlFrame.class);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void listener_OnReceiveRtcp(fm.liveswitch.RtpInboundRtcp r5) {
        /*
            r4 = this;
            fm.liveswitch.IFunction0 r0 = r4.getGetInboundRtcpTransportSystemTimestamp()
            java.lang.Object r0 = r0.invoke()
            java.lang.Long r0 = (java.lang.Long) r0
            long r0 = r0.longValue()
            r5.setTransportSystemTimestamp(r0)
            fm.liveswitch.MediaControlFrame[] r0 = r5.getFrames()
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)
            if (r0 <= 0) goto L_0x0057
            fm.liveswitch.MediaControlFrame[] r0 = r5.getFrames()
            r1 = 0
            r0 = r0[r1]
            int r0 = r0.getPayloadType()
            int r2 = fm.liveswitch.SRControlFrame.getRegisteredPayloadType()
            if (r0 != r2) goto L_0x0057
            fm.liveswitch.MediaControlFrame[] r0 = r5.getFrames()
            r0 = r0[r1]
            java.lang.Class<fm.liveswitch.SRControlFrame> r1 = fm.liveswitch.SRControlFrame.class
            java.lang.Object r0 = fm.liveswitch.Global.tryCast(r0, r1)
            fm.liveswitch.SRControlFrame r0 = (fm.liveswitch.SRControlFrame) r0
            java.lang.String r1 = "FM.IceLink.BundleTransport.RtcpPacketLengthKey"
            java.lang.Object r1 = r0.getDynamicValue(r1)
            if (r1 == 0) goto L_0x0057
            fm.liveswitch.IntegerHolder r1 = (fm.liveswitch.IntegerHolder) r1
            fm.liveswitch.IntegerHolder r1 = (fm.liveswitch.IntegerHolder) r1
            int r1 = r1.getValue()
            long r2 = r0.getSynchronizationSource()
            fm.liveswitch.RtpReceiver r0 = r4.getReceiver((long) r2)
            if (r0 == 0) goto L_0x0057
            r0.updateRtcpReceiverStatistics(r1)
        L_0x0057:
            r4.processInboundRtcp(r5)
            fm.liveswitch.MediaControlFrame[] r5 = r5.getFrames()
            r4.receiveControlFrames(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtpTransport.listener_OnReceiveRtcp(fm.liveswitch.RtpInboundRtcp):void");
    }

    /* access modifiers changed from: private */
    public void listener_OnReceiveRtp(RtpInboundRtp rtpInboundRtp) {
        rtpInboundRtp.setTransportSystemTimestamp(getGetInboundRtpTransportSystemTimestamp().invoke().longValue());
        if (getReceiver(rtpInboundRtp.getHeader().getSynchronizationSource(), rtpInboundRtp.getHeader().getHeaderExtension(), true).receiveRtp(rtpInboundRtp, getRoundTripTime())) {
            sendReport();
        }
    }

    private void processInboundRtcp(RtpInboundRtcp rtpInboundRtcp) {
        int i;
        MediaControlFrame[] mediaControlFrameArr = new MediaControlFrame[0];
        int i2 = 0;
        int i3 = 0;
        while (i2 < ArrayExtensions.getLength((Object[]) rtpInboundRtcp.getFrames())) {
            MediaControlFrame mediaControlFrame = rtpInboundRtcp.getFrames()[i2];
            if (mediaControlFrame.getPayloadType() == SRControlFrame.getRegisteredPayloadType()) {
                SRControlFrame sRControlFrame = (SRControlFrame) mediaControlFrame;
                long synchronizationSource = sRControlFrame.getSynchronizationSource();
                RtpReceiver receiver = getReceiver(synchronizationSource);
                if (receiver != null) {
                    receiver.processSRControlFrame(sRControlFrame, rtpInboundRtcp.getTransportSystemTimestamp());
                } else {
                    __log.warn(this.__logScope, StringExtensions.format("Discarding inbound SR control frame for SSRC {0}. Unrecognized remote synchronization source.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource))));
                }
                for (ReportBlock reportBlock : sRControlFrame.getReportBlocks()) {
                    long synchronizationSource2 = reportBlock.getSynchronizationSource();
                    RtpSender sender = getSender(synchronizationSource2);
                    if (sender != null) {
                        sender.processReportBlock(reportBlock, rtpInboundRtcp.getTransportSystemTimestamp());
                    } else {
                        __log.warn(this.__logScope, StringExtensions.format("Discarding inbound SR report block for the local sender with SSRC {0} from the remote receiver with SSRC {1}. Unrecognized local synchronization source.", LongExtensions.toString(Long.valueOf(synchronizationSource2)), LongExtensions.toString(Long.valueOf(sRControlFrame.getSynchronizationSource()))));
                    }
                }
            } else if (mediaControlFrame.getPayloadType() == RRControlFrame.getRegisteredPayloadType()) {
                for (ReportBlock reportBlock2 : ((RRControlFrame) mediaControlFrame).getReportBlocks()) {
                    long synchronizationSource3 = reportBlock2.getSynchronizationSource();
                    RtpSender sender2 = getSender(synchronizationSource3);
                    if (sender2 != null) {
                        sender2.processReportBlock(reportBlock2, rtpInboundRtcp.getTransportSystemTimestamp());
                    } else {
                        __log.warn(this.__logScope, StringExtensions.format("Discarding inbound RR report block for SSRC {0}. Unrecognized local synchronization source.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource3))));
                    }
                }
            } else {
                if (mediaControlFrame.getPayloadType() == PayloadSpecificControlFrame.getRegisteredPayloadType()) {
                    PayloadSpecificControlFrame payloadSpecificControlFrame = (PayloadSpecificControlFrame) mediaControlFrame;
                    long mediaSourceSynchronizationSource = payloadSpecificControlFrame.getMediaSourceSynchronizationSource();
                    RtpSender sender3 = getSender(mediaSourceSynchronizationSource);
                    if (payloadSpecificControlFrame.getFeedbackMessageType() == PliControlFrame.getRegisteredFeedbackMessageType()) {
                        if (sender3 == null) {
                            __log.warn(this.__logScope, StringExtensions.format("Discarding inbound PLI control frame for SSRC {0}. Unrecognized local synchronization source.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                            i = i2 - 1;
                            rtpInboundRtcp.setFrames(MediaControlFrame.removeControlFrame(rtpInboundRtcp.getFrames(), i2));
                        } else if (!sender3.canReceivePli(getPliIntervalTicks(), rtpInboundRtcp.getTransportSystemTimestamp())) {
                            if (__log.getIsVerboseEnabled()) {
                                __log.verbose(this.__logScope, StringExtensions.format("Discarding inbound PLI control frame for SSRC {0}. Rate limit exceeded.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                            }
                            i = i2 - 1;
                            rtpInboundRtcp.setFrames(MediaControlFrame.removeControlFrame(rtpInboundRtcp.getFrames(), i2));
                        } else if (__log.getIsDebugEnabled()) {
                            __log.debug(this.__logScope, StringExtensions.format("Receiving PLI control frame for SSRC {0}.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                        }
                    } else if (payloadSpecificControlFrame.getFeedbackMessageType() == FirControlFrame.getRegisteredFeedbackMessageType()) {
                        int sequenceNumber = ((FirControlFrame) payloadSpecificControlFrame).getEntry().getSequenceNumber();
                        if (sender3 == null) {
                            __log.warn(StringExtensions.format("Discarding inbound FIR control frame for SSRC {0}. Unrecognized local synchronization source.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                            i = i2 - 1;
                            rtpInboundRtcp.setFrames(MediaControlFrame.removeControlFrame(rtpInboundRtcp.getFrames(), i2));
                        } else if (!sender3.canReceiveFir(sequenceNumber)) {
                            if (__log.getIsVerboseEnabled()) {
                                __log.verbose(this.__logScope, StringExtensions.format("Discarding inbound FIR control frame for SSRC {0} with sequence number {1}. Rate limit exceeded.", LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource)), IntegerExtensions.toString(Integer.valueOf(sequenceNumber))));
                            }
                            i = i2 - 1;
                            rtpInboundRtcp.setFrames(MediaControlFrame.removeControlFrame(rtpInboundRtcp.getFrames(), i2));
                        } else if (__log.getIsDebugEnabled()) {
                            __log.debug(this.__logScope, StringExtensions.format("Receiving FIR control frame for SSRC {0} with sequence number {1}.", LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource)), IntegerExtensions.toString(Integer.valueOf(sequenceNumber))));
                        }
                    } else if (payloadSpecificControlFrame.getFeedbackMessageType() == LrrControlFrame.getRegisteredFeedbackMessageType()) {
                        int sequenceNumber2 = ((LrrControlFrame) payloadSpecificControlFrame).getEntry().getSequenceNumber();
                        if (sender3 == null) {
                            __log.warn(StringExtensions.format("Discarding inbound LRR control frame for SSRC {0}. Unrecognized local synchronization source.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                            i = i2 - 1;
                            rtpInboundRtcp.setFrames(MediaControlFrame.removeControlFrame(rtpInboundRtcp.getFrames(), i2));
                        } else if (!sender3.canReceiveLrr(sequenceNumber2)) {
                            if (__log.getIsVerboseEnabled()) {
                                __log.verbose(this.__logScope, StringExtensions.format("Discarding inbound LRR control frame for SSRC {0} with sequence number {1}. Rate limit exceeded.", LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource)), IntegerExtensions.toString(Integer.valueOf(sequenceNumber2))));
                            }
                            i = i2 - 1;
                            rtpInboundRtcp.setFrames(MediaControlFrame.removeControlFrame(rtpInboundRtcp.getFrames(), i2));
                        } else if (__log.getIsDebugEnabled()) {
                            __log.debug(this.__logScope, StringExtensions.format("Receiving LRR control frame for SSRC {0} with sequence number {1}.", LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource)), IntegerExtensions.toString(Integer.valueOf(sequenceNumber2))));
                        }
                    } else if (payloadSpecificControlFrame.getFeedbackMessageType() == RembControlFrame.getRegisteredFeedbackMessageType()) {
                        this.__requestedOutboundBitrate = (double) ((int) (((RembControlFrame) payloadSpecificControlFrame).getBitrate() / 1000));
                    } else if (sender3 == null) {
                        __log.warn(this.__logScope, StringExtensions.format("Discarding inbound payload-specific control frame for SSRC {0} with feedback message type {1}. Unrecognized local synchronization source.", LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource)), IntegerExtensions.toString(Integer.valueOf(payloadSpecificControlFrame.getFeedbackMessageType()))));
                    }
                } else if (mediaControlFrame.getPayloadType() == RtpControlFrame.getRegisteredPayloadType()) {
                    RtpControlFrame rtpControlFrame = (RtpControlFrame) mediaControlFrame;
                    if (rtpControlFrame.getFeedbackMessageType() == TmmbrControlFrame.getRegisteredFeedbackMessageType()) {
                        TmmbrControlFrame tmmbrControlFrame = (TmmbrControlFrame) mediaControlFrame;
                        for (TmmbrEntry tmmbrEntry : tmmbrControlFrame.getEntries()) {
                            long synchronizationSource4 = tmmbrEntry.getSynchronizationSource();
                            if (getSender(synchronizationSource4) == null) {
                                __log.warn(this.__logScope, StringExtensions.format("Discarding inbound TMMBR control frame entry for SSRC {0}. Unrecognized local synchronization source.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource4))));
                            } else {
                                TmmbrControlFrame tmmbrControlFrame2 = new TmmbrControlFrame(tmmbrEntry);
                                tmmbrControlFrame2.setPacketSenderSynchronizationSource(tmmbrControlFrame.getPacketSenderSynchronizationSource());
                                mediaControlFrameArr = MediaControlFrame.addControlFrame(mediaControlFrameArr, tmmbrControlFrame2, i3);
                                i3++;
                            }
                        }
                        i = i2 - 1;
                        rtpInboundRtcp.setFrames(MediaControlFrame.removeControlFrame(rtpInboundRtcp.getFrames(), i2));
                    } else if (rtpControlFrame.getFeedbackMessageType() == TmmbnControlFrame.getRegisteredFeedbackMessageType()) {
                        TmmbnControlFrame tmmbnControlFrame = (TmmbnControlFrame) mediaControlFrame;
                        for (TmmbnEntry tmmbnEntry : tmmbnControlFrame.getEntries()) {
                            long synchronizationSource5 = tmmbnEntry.getSynchronizationSource();
                            if (getSender(synchronizationSource5) == null) {
                                __log.warn(this.__logScope, StringExtensions.format("Discarding inbound TMMBN control frame entry for SSRC {0}. Unrecognized local synchronization source.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource5))));
                            } else {
                                TmmbnControlFrame tmmbnControlFrame2 = new TmmbnControlFrame(tmmbnEntry);
                                tmmbnControlFrame2.setPacketSenderSynchronizationSource(tmmbnControlFrame.getPacketSenderSynchronizationSource());
                                mediaControlFrameArr = MediaControlFrame.addControlFrame(mediaControlFrameArr, tmmbnControlFrame2, i3);
                                i3++;
                            }
                        }
                        i = i2 - 1;
                        rtpInboundRtcp.setFrames(MediaControlFrame.removeControlFrame(rtpInboundRtcp.getFrames(), i2));
                    } else {
                        long mediaSourceSynchronizationSource2 = rtpControlFrame.getMediaSourceSynchronizationSource();
                        RtpSender sender4 = getSender(mediaSourceSynchronizationSource2);
                        if (rtpControlFrame.getFeedbackMessageType() == GenericNackControlFrame.getRegisteredFeedbackMessageType()) {
                            if (sender4 == null) {
                                __log.warn(this.__logScope, StringExtensions.format("Discarding inbound generic NACK control frame for SSRC {0}. Unrecognized local synchronization source.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource2))));
                                i = i2 - 1;
                                rtpInboundRtcp.setFrames(MediaControlFrame.removeControlFrame(rtpInboundRtcp.getFrames(), i2));
                            } else {
                                sender4.processGenericNackControlFrame((GenericNackControlFrame) rtpControlFrame);
                            }
                        } else if (sender4 == null) {
                            __log.warn(this.__logScope, StringExtensions.format("Discarding inbound RTP control frame for SSRC {0} with feedback message type {1}. Unrecognized local synchronization source.", LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource2)), IntegerExtensions.toString(Integer.valueOf(rtpControlFrame.getFeedbackMessageType()))));
                            i = i2 - 1;
                            rtpInboundRtcp.setFrames(MediaControlFrame.removeControlFrame(rtpInboundRtcp.getFrames(), i2));
                        }
                    }
                }
                i2 = i;
            }
            i2++;
        }
        if (ArrayExtensions.getLength((Object[]) mediaControlFrameArr) > 0) {
            rtpInboundRtcp.setFrames(MediaControlFrame.addControlFrames(rtpInboundRtcp.getFrames(), mediaControlFrameArr, ArrayExtensions.getLength((Object[]) rtpInboundRtcp.getFrames())));
        }
    }

    private void processOutboundRtcp(RtpOutboundRtcp rtpOutboundRtcp) {
        int i;
        RtpOutboundRtcp rtpOutboundRtcp2 = rtpOutboundRtcp;
        int i2 = 0;
        while (i2 < ArrayExtensions.getLength((Object[]) rtpOutboundRtcp.getFrames())) {
            MediaControlFrame mediaControlFrame = rtpOutboundRtcp.getFrames()[i2];
            if (mediaControlFrame.getPayloadType() == SRControlFrame.getRegisteredPayloadType()) {
                __log.warn(this.__logScope, StringExtensions.format("Discarding outbound SR control frame. SR control frames are generated internally.", new Object[0]));
                i = i2 - 1;
                rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
            } else if (mediaControlFrame.getPayloadType() == RRControlFrame.getRegisteredPayloadType()) {
                __log.warn(this.__logScope, StringExtensions.format("Discarding outbound RR control frame. RR control frames are generated internally.", new Object[0]));
                i = i2 - 1;
                rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
            } else if (mediaControlFrame.getPayloadType() == SdesControlFrame.getRegisteredPayloadType()) {
                __log.warn(this.__logScope, StringExtensions.format("Discarding outbound SDES control frame. SDES control frames are generated internally.", new Object[0]));
                i = i2 - 1;
                rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
            } else {
                RtpReceiver rtpReceiver = null;
                if (mediaControlFrame.getPayloadType() == PayloadSpecificControlFrame.getRegisteredPayloadType()) {
                    PayloadSpecificControlFrame payloadSpecificControlFrame = (PayloadSpecificControlFrame) mediaControlFrame;
                    if (payloadSpecificControlFrame.getPacketSenderSynchronizationSource() == 0) {
                        payloadSpecificControlFrame.setPacketSenderSynchronizationSource(getFirstLocalSynchronizationSource());
                    }
                    if (payloadSpecificControlFrame.getPacketSenderSynchronizationSource() == 0) {
                        payloadSpecificControlFrame.setPacketSenderSynchronizationSource(getDefaultLocalSynchronizationSource());
                    }
                    if (payloadSpecificControlFrame.getFeedbackMessageType() == RembControlFrame.getRegisteredFeedbackMessageType()) {
                        RembControlFrame rembControlFrame = (RembControlFrame) payloadSpecificControlFrame;
                        rembControlFrame.setMediaSourceSynchronizationSource(0);
                        long[] ssrcEntries = rembControlFrame.getSsrcEntries();
                        int length = ssrcEntries.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length) {
                                break;
                            }
                            long j = ssrcEntries[i3];
                            if (getReceiver(j) == null) {
                                __log.warn(this.__logScope, StringExtensions.format("Discarding outbound REMB control frame with SSRC entry {0}. Unrecognized remote synchronization source.", (Object) LongExtensions.toString(Long.valueOf(j))));
                                i = i2 - 1;
                                rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                                break;
                            }
                            i3++;
                        }
                    } else {
                        if (payloadSpecificControlFrame.getMediaSourceSynchronizationSource() == 0) {
                            payloadSpecificControlFrame.setMediaSourceSynchronizationSource(getSingleRemoteSynchronizationSource());
                        }
                        long mediaSourceSynchronizationSource = payloadSpecificControlFrame.getMediaSourceSynchronizationSource();
                        if (mediaSourceSynchronizationSource > 0) {
                            rtpReceiver = getReceiver(mediaSourceSynchronizationSource);
                        }
                        if (payloadSpecificControlFrame.getFeedbackMessageType() == PliControlFrame.getRegisteredFeedbackMessageType()) {
                            if (rtpReceiver == null) {
                                __log.warn(this.__logScope, StringExtensions.format("Discarding outbound PLI control frame for SSRC {0}. Unrecognized remote synchronization source.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                                i = i2 - 1;
                                rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                            } else if (rtpReceiver.canSendPli(getPliIntervalTicks(), rtpOutboundRtcp.getTransportSystemTimestamp())) {
                                if (__log.getIsDebugEnabled()) {
                                    __log.debug(this.__logScope, StringExtensions.format("Sending PLI control frame for SSRC {0}.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                                }
                                i2++;
                            } else {
                                if (__log.getIsVerboseEnabled()) {
                                    __log.verbose(this.__logScope, StringExtensions.format("Discarding outbound PLI control frame for SSRC {0}. Rate limit exceeded.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                                }
                                i = i2 - 1;
                                rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                            }
                        } else if (payloadSpecificControlFrame.getFeedbackMessageType() == FirControlFrame.getRegisteredFeedbackMessageType()) {
                            if (rtpReceiver == null) {
                                __log.warn(this.__logScope, StringExtensions.format("Discarding outbound FIR control frame for SSRC {0}. Unrecognized remote synchronization source.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                                i = i2 - 1;
                                rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                            } else if (rtpReceiver.canSendFir(getCcmIntervalTicks(), getCcmIncrementIntervalTicks(), rtpOutboundRtcp.getTransportSystemTimestamp())) {
                                int ccmSendSequenceNumber = rtpReceiver.getCcmSendSequenceNumber();
                                for (FirEntry firEntry : ((FirControlFrame) payloadSpecificControlFrame).getEntries()) {
                                    if (firEntry.getSynchronizationSource() == 0) {
                                        firEntry.setSynchronizationSource(rtpReceiver.getRemoteSynchronizationSource());
                                    }
                                    firEntry.setSequenceNumber(ccmSendSequenceNumber);
                                }
                                if (__log.getIsDebugEnabled()) {
                                    __log.debug(this.__logScope, StringExtensions.format("Sending FIR control frame for SSRC {0} with sequence number {1}.", LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource)), IntegerExtensions.toString(Integer.valueOf(ccmSendSequenceNumber))));
                                }
                                i2++;
                            } else {
                                if (__log.getIsVerboseEnabled()) {
                                    __log.verbose(this.__logScope, StringExtensions.format("Discarding outbound FIR control frame for SSRC {0}. Rate limit exceeded.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                                }
                                i = i2 - 1;
                                rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                            }
                        } else if (payloadSpecificControlFrame.getFeedbackMessageType() == LrrControlFrame.getRegisteredFeedbackMessageType()) {
                            if (rtpReceiver == null) {
                                __log.warn(this.__logScope, StringExtensions.format("Discarding outbound LRR control frame for SSRC {0}. Unrecognized remote synchronization source.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                                i = i2 - 1;
                                rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                            } else if (rtpReceiver.canSendLrr(getCcmIntervalTicks(), getCcmIncrementIntervalTicks(), rtpOutboundRtcp.getTransportSystemTimestamp())) {
                                int ccmSendSequenceNumber2 = rtpReceiver.getCcmSendSequenceNumber();
                                for (LrrEntry lrrEntry : ((LrrControlFrame) payloadSpecificControlFrame).getEntries()) {
                                    if (lrrEntry.getSynchronizationSource() == 0) {
                                        lrrEntry.setSynchronizationSource(rtpReceiver.getRemoteSynchronizationSource());
                                    }
                                    lrrEntry.setSequenceNumber(ccmSendSequenceNumber2);
                                    lrrEntry.setPayloadType(rtpReceiver.getCurrentPayloadType());
                                }
                                if (__log.getIsDebugEnabled()) {
                                    __log.debug(this.__logScope, StringExtensions.format("Sending LRR control frame for SSRC {0} with sequence number {1}.", LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource)), IntegerExtensions.toString(Integer.valueOf(ccmSendSequenceNumber2))));
                                }
                                i2++;
                            } else {
                                if (__log.getIsVerboseEnabled()) {
                                    __log.verbose(this.__logScope, StringExtensions.format("Discarding outbound LRR control frame for SSRC {0}. Rate limit exceeded.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource))));
                                }
                                i = i2 - 1;
                                rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                            }
                        } else if (rtpReceiver == null) {
                            __log.warn(this.__logScope, StringExtensions.format("Discarding outbound payload-specific control frame for SSRC {0} with feedback message type {1}. Unrecognized remote synchronization source.", LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource)), IntegerExtensions.toString(Integer.valueOf(payloadSpecificControlFrame.getFeedbackMessageType()))));
                            i = i2 - 1;
                            rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                        } else {
                            i2++;
                        }
                    }
                } else {
                    if (mediaControlFrame.getPayloadType() == RtpControlFrame.getRegisteredPayloadType()) {
                        RtpControlFrame rtpControlFrame = (RtpControlFrame) mediaControlFrame;
                        if (rtpControlFrame.getPacketSenderSynchronizationSource() == 0) {
                            rtpControlFrame.setPacketSenderSynchronizationSource(getFirstLocalSynchronizationSource());
                        }
                        if (rtpControlFrame.getPacketSenderSynchronizationSource() == 0) {
                            rtpControlFrame.setPacketSenderSynchronizationSource(getDefaultLocalSynchronizationSource());
                        }
                        if (rtpControlFrame.getFeedbackMessageType() == TmmbrControlFrame.getRegisteredFeedbackMessageType()) {
                            TmmbrControlFrame tmmbrControlFrame = (TmmbrControlFrame) mediaControlFrame;
                            tmmbrControlFrame.setMediaSourceSynchronizationSource(0);
                            TmmbrEntry[] entries = tmmbrControlFrame.getEntries();
                            int length2 = entries.length;
                            int i4 = 0;
                            while (true) {
                                if (i4 >= length2) {
                                    break;
                                }
                                long synchronizationSource = entries[i4].getSynchronizationSource();
                                if (getReceiver(synchronizationSource) == null) {
                                    __log.warn(this.__logScope, StringExtensions.format("Discarding outbound TMMBR control frame with SSRC entry {0}. Unrecognized remote synchronization source.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource))));
                                    i = i2 - 1;
                                    rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                                    break;
                                }
                                i4++;
                            }
                        } else if (rtpControlFrame.getFeedbackMessageType() == TmmbnControlFrame.getRegisteredFeedbackMessageType()) {
                            TmmbnControlFrame tmmbnControlFrame = (TmmbnControlFrame) mediaControlFrame;
                            tmmbnControlFrame.setMediaSourceSynchronizationSource(0);
                            TmmbnEntry[] entries2 = tmmbnControlFrame.getEntries();
                            int length3 = entries2.length;
                            int i5 = 0;
                            while (true) {
                                if (i5 >= length3) {
                                    break;
                                }
                                long synchronizationSource2 = entries2[i5].getSynchronizationSource();
                                if (getReceiver(synchronizationSource2) == null) {
                                    __log.warn(this.__logScope, StringExtensions.format("Discarding outbound TMMBN control frame with SSRC entry {0}. Unrecognized remote synchronization source.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource2))));
                                    i = i2 - 1;
                                    rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                                    break;
                                }
                                i5++;
                            }
                        } else {
                            if (rtpControlFrame.getMediaSourceSynchronizationSource() == 0) {
                                rtpControlFrame.setMediaSourceSynchronizationSource(getSingleRemoteSynchronizationSource());
                            }
                            long mediaSourceSynchronizationSource2 = rtpControlFrame.getMediaSourceSynchronizationSource();
                            if (mediaSourceSynchronizationSource2 > 0) {
                                rtpReceiver = getReceiver(mediaSourceSynchronizationSource2);
                            }
                            if (rtpControlFrame.getFeedbackMessageType() == GenericNackControlFrame.getRegisteredFeedbackMessageType()) {
                                if (rtpReceiver == null) {
                                    __log.warn(this.__logScope, StringExtensions.format("Discarding outbound generic NACK control frame for SSRC {0}. Unrecognized remote synchronization source.", (Object) LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource2))));
                                    i = i2 - 1;
                                    rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                                }
                            } else if (rtpReceiver == null) {
                                __log.warn(this.__logScope, StringExtensions.format("Discarding outbound RTP control frame for SSRC {0} with feedback message type {1}. Unrecognized remote synchronization source.", LongExtensions.toString(Long.valueOf(mediaSourceSynchronizationSource2)), IntegerExtensions.toString(Integer.valueOf(rtpControlFrame.getFeedbackMessageType()))));
                                i = i2 - 1;
                                rtpOutboundRtcp2.setFrames(MediaControlFrame.removeControlFrame(rtpOutboundRtcp.getFrames(), i2));
                            }
                        }
                    }
                    i2++;
                }
            }
            i2 = i;
            i2++;
        }
    }

    public void removeOnSendControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onSendControlFrameResponses, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onSendControlFrameResponses.remove(iAction1);
        if (this.__onSendControlFrameResponses.size() == 0) {
            this._onSendControlFrameResponses = null;
        }
    }

    public void removeOnSendControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onSendControlFrames, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onSendControlFrames.remove(iAction1);
        if (this.__onSendControlFrames.size() == 0) {
            this._onSendControlFrames = null;
        }
    }

    private boolean removeReceiver(long j) {
        synchronized (this.__receiversLock) {
            RtpReceiver receiver = getReceiver(j);
            if (receiver == null) {
                return false;
            }
            destroyReceiver(receiver);
            return true;
        }
    }

    private boolean removeSender(long j) {
        synchronized (this.__sendersLock) {
            RtpSender sender = getSender(j);
            if (sender == null) {
                return false;
            }
            destroySender(sender);
            return true;
        }
    }

    public boolean resetInboundBandwidthEstimate(double d, LongHolder longHolder, Holder<String> holder) {
        RtpReceiver[] receivers = getReceivers();
        if (receivers == null || ArrayExtensions.getLength((Object[]) receivers) == 0) {
            longHolder.setValue(-1);
            holder.setValue(null);
            return false;
        } else if (ArrayExtensions.getLength((Object[]) receivers) <= 1) {
            RtpReceiver rtpReceiver = receivers[0];
            rtpReceiver.resetBandwidthEstimate(d);
            longHolder.setValue(rtpReceiver.getRemoteSynchronizationSource());
            holder.setValue(rtpReceiver.getRemoteRtpStreamId());
            return true;
        } else {
            throw new RuntimeException(new Exception("Resetting the inbound bandwidth estimate is only possible for single-receiver transports."));
        }
    }

    public void resetInboundCurrentBitrate(long j) {
        RtpReceiver receiver = getReceiver(j);
        if (receiver != null) {
            receiver.resetCurrentBitrate();
        }
    }

    public boolean resetOutboundBandwidthEstimate(double d, LongHolder longHolder, Holder<String> holder) {
        RtpSender[] senders = getSenders();
        if (senders == null || ArrayExtensions.getLength((Object[]) senders) == 0) {
            longHolder.setValue(-1);
            holder.setValue(null);
            return false;
        } else if (ArrayExtensions.getLength((Object[]) senders) <= 1) {
            RtpSender rtpSender = senders[0];
            rtpSender.resetBandwidthEstimate(d);
            longHolder.setValue(rtpSender.getLocalSynchronizationSource());
            holder.setValue(rtpSender.getLocalRtpStreamId());
            return true;
        } else {
            throw new RuntimeException(new Exception("Resetting the outbound bandwidth estimate is only possible for single-sender transports."));
        }
    }

    public void resetOutboundCurrentBitrate(long j) {
        RtpSender sender = getSender(j);
        if (sender != null) {
            sender.resetCurrentBitrate();
        }
    }

    public RtpTransport(Object obj, StreamType streamType, SimulcastMode simulcastMode, NackConfig nackConfig, RedFecConfig redFecConfig, JitterConfig jitterConfig, boolean z, HexDump hexDump, Transport transport, Transport transport2) {
        super(obj);
        setType(streamType);
        setSimulcastMode(simulcastMode);
        setNackConfig(nackConfig);
        setRedFecConfig(redFecConfig);
        setJitterConfig(jitterConfig);
        setDisableAutomaticReports(z);
        setHexDump(hexDump);
        setRtpTransport(transport);
        setRtcpTransport(transport2);
        this.__logScope = StringExtensions.format("{0}-{1}", StringExtensions.toLower(getType().toString()), super.getId().toString());
    }

    private void sendReport() {
        try {
            if (!getDisableAutomaticReports()) {
                super.sendControlFrames(new MediaControlFrame[0]);
            }
        } catch (Exception e) {
            __log.error(this.__logScope, "Could not send SR/RR control frame.", e);
        }
    }

    public void setDirection(StreamDirection streamDirection) {
        this.__direction = streamDirection;
    }

    public void setDisableAutomaticReports(boolean z) {
        this._disableAutomaticReports = z;
    }

    public void setGetInboundRtcpTransportSystemTimestamp(IFunction0<Long> iFunction0) {
        if (iFunction0 != null) {
            this.__getInboundRtcpTransportSystemTimestamp = iFunction0;
        }
    }

    public void setGetInboundRtpTransportSystemTimestamp(IFunction0<Long> iFunction0) {
        if (iFunction0 != null) {
            this.__getInboundRtpTransportSystemTimestamp = iFunction0;
        }
    }

    public void setGetOutboundRtcpTransportSystemTimestamp(IFunction0<Long> iFunction0) {
        if (iFunction0 != null) {
            this.__getOutboundRtcpTransportSystemTimestamp = iFunction0;
        }
    }

    private void setHexDump(HexDump hexDump) {
        this._hexDump = hexDump;
    }

    public void setIncludeMidSdes(boolean z) {
        this._includeMidSdes = z;
    }

    private void setJitterConfig(JitterConfig jitterConfig) {
        this._jitterConfig = jitterConfig;
    }

    public void setLegacyReceiver(boolean z) {
        this._legacyReceiver = z;
    }

    public void setListener(RtpListener rtpListener) {
        if (this.__listener == null || !Global.equals(this.__state, MediaTransportState.Started)) {
            this.__listener = rtpListener;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set new Listener when one has already been set and the transport has already been started."));
    }

    private void setLocalProtectionParameters(SrtpProtectionParameters srtpProtectionParameters) {
        this._localProtectionParameters = srtpProtectionParameters;
    }

    public void setMid(String str) {
        this._mid = str;
    }

    private void setNackConfig(NackConfig nackConfig) {
        this._nackConfig = nackConfig;
    }

    public boolean setProtectionParameters(SrtpProtectionParameters srtpProtectionParameters, SrtpProtectionParameters srtpProtectionParameters2) {
        if (!Global.equals(srtpProtectionParameters.getProtectionProfileString(), srtpProtectionParameters2.getProtectionProfileString())) {
            __log.error(this.__logScope, "Cannot set protection parameters. Protection profiles do not match.");
            return false;
        }
        setLocalProtectionParameters(srtpProtectionParameters);
        setRemoteProtectionParameters(srtpProtectionParameters2);
        return true;
    }

    private void setRedFecConfig(RedFecConfig redFecConfig) {
        this._redFecConfig = redFecConfig;
    }

    public void setRembEnabled(boolean z) {
        this._rembEnabled = z;
    }

    private void setRemoteProtectionParameters(SrtpProtectionParameters srtpProtectionParameters) {
        this._remoteProtectionParameters = srtpProtectionParameters;
    }

    /* access modifiers changed from: package-private */
    public void setRtcpTransport(Transport transport) {
        synchronized (this.__sendersLock) {
            this.__rtcpTransport = transport;
            updateSendingSecondaryIceTransport(transport);
        }
    }

    /* access modifiers changed from: package-private */
    public void setRtpTransport(Transport transport) {
        synchronized (this.__sendersLock) {
            this.__rtpTransport = transport;
            updateSendingPrimaryIceTransport(transport);
        }
    }

    private void setSimulcastMode(SimulcastMode simulcastMode) {
        this._simulcastMode = simulcastMode;
    }

    public void setTestReceivedRtpBuffer(IFunction1<DataBuffer, DataBuffer> iFunction1) {
        this._testReceivedRtpBuffer = iFunction1;
    }

    public void setTestRoundTripTime(int i) {
        this.__testRoundTripTime = i;
    }

    public void setTestSendingRtpBuffer(IFunction1<DataBuffer, DataBuffer> iFunction1) {
        this._testSendingRtpBuffer = iFunction1;
    }

    private void setType(StreamType streamType) {
        this._type = streamType;
    }

    private void updateSendingPrimaryIceTransport(Transport transport) {
        RtpSender[] senders = getSenders();
        if (senders != null) {
            for (RtpSender rtpTransport : senders) {
                rtpTransport.setRtpTransport(transport);
            }
        }
    }

    private void updateSendingSecondaryIceTransport(Transport transport) {
        RtpSender[] senders = getSenders();
        if (senders != null) {
            for (RtpSender rtcpTransport : senders) {
                rtcpTransport.setRtcpTransport(transport);
            }
        }
    }
}
