package fm.liveswitch;

import com.google.android.gms.common.internal.ImagesContract;
import fm.liveswitch.sdp.BundleGroup;
import fm.liveswitch.sdp.MediaStreamIdAttribute;
import fm.liveswitch.sdp.ice.CandidateAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class Connection extends ConnectionBase<Connection, Stream, AudioStream, VideoStream, DataStream, DataChannel> {
    private static ILog __log = Log.getLogger(Connection.class);
    private static DtlsCertificate[] _defaultLocalDtlsCertificates;
    private boolean __activeIceKeepAliveEnabled = true;
    private HashMap<String, BundleTransport> __bundleTransports = new HashMap<>();
    private ArrayList<Candidate> __cachedLocalCandidates = new ArrayList<>();
    private ArrayList<Stream> __cachedNewStreams = new ArrayList<>();
    private DtlsCipherSuite[] __cipherSuites = {DtlsCipherSuite.EcdheEcdsaAes128GcmSha256, DtlsCipherSuite.EcdheRsaAes128GcmSha256};
    private HashMap<String, CoreTransport> __coreTransportForDtlsTransport = new HashMap<>();
    private HashMap<String, CoreTransport> __coreTransportForIceTransport = new HashMap<>();
    private HashMap<String, ArrayList<CoreTransport>> __coreTransportsForGatherer = new HashMap<>();
    private boolean __createAnswer = false;
    private boolean __createOffer = false;
    private Object __dtlsCertificatesLock = new Object();
    private DtlsProtocolVersion __dtlsClientVersion = DtlsProtocolVersion.Dtls12;
    private DtlsProtocolVersion __dtlsServerMaxVersion = DtlsProtocolVersion.Dtls12;
    private DtlsProtocolVersion __dtlsServerMinVersion = DtlsProtocolVersion.Dtls10;
    private HashMap<String, DtlsTransport> __dtlsTransports = new HashMap<>();
    private ScheduledItem __establishConnectionTimeoutSI;
    private HashMap<String, IceGatherer> __gatherers = new HashMap<>();
    private IceGatheringState __gatheringState = IceGatheringState.New;
    private HexDump __hexDump;
    private IceConnectionState __iceConnectionState = IceConnectionState.New;
    private IcePolicy __icePolicy = IcePolicy.Required;
    private long __iceTieBreaker = -1;
    private HashMap<String, IceTransport> __iceTransports = new HashMap<>();
    private boolean __isOfferer;
    private int __keepAliveInterval = 1000;
    private DtlsCertificate[] __localDtlsCertificates;
    private Object __localDtlsFingerprintLock = new Object();
    private DtlsFingerprint[] __localDtlsFingerprints;
    private IceParameters __localIceParameters = null;
    private ScheduledItem __logRTT = null;
    private DtlsRole __preferredDtlsRole = DtlsRole.Auto;
    private ArrayList<CoreTransport> __primaryCoreTransports = new ArrayList<>();
    private Promise<SessionDescription> __promiseToBeResolved = null;
    private Scheduler __scheduler;
    private ArrayList<CoreTransport> __secondaryCoreTransports = new ArrayList<>();
    private SessionDescriptionManager __sessionDescriptionManager;
    private HashMap<String, AudioStream> __streamForAudioTransport = new HashMap<>();
    private HashMap<String, DataStream> __streamForReliableDataTransport = new HashMap<>();
    private HashMap<String, DataStream> __streamForSctpTransport = new HashMap<>();
    private HashMap<String, VideoStream> __streamForVideoTransport = new HashMap<>();
    private StreamCollection __streams = new StreamCollection();
    private HashMap<String, ArrayList<Stream>> __streamsForDtlsTransport = new HashMap<>();
    private HashMap<String, ArrayList<Stream>> __streamsForIceTransport = new HashMap<>();
    private int __testRoundTripTime = -1;
    private ScheduledItem __verifyGatherersDownScheduledItem;
    private VirtualAdapter __virtualAdapter;
    private IFunction1<DatagramSocketCreateArgs, DatagramSocket> _createDatagramSocket;
    private IFunction1<StreamSocketCreateArgs, StreamSocket> _createStreamSocket;
    private IFunction0<Long> _getInboundRtcpTransportSystemTimestamp;
    private IFunction0<Long> _getInboundRtpTransportSystemTimestamp;
    private IFunction0<Long> _getOutboundRtcpTransportSystemTimestamp;
    private boolean _hexDumpEnabled;
    private String _hexDumpPath;
    private AddressType[] _iceAddressTypes;
    private IcePortRange _icePortRange;
    private IceRole _iceRole;
    private MultiplexPolicy _multiplexPolicy;
    private IFunction1<StreamDescription, Stream> _onRemoteAddStream;
    private IAction1<Stream> _onRemoteRemoveStream;
    private String[] _privateIPAddresses;
    private String[] _publicIPAddresses;
    private int _stunBindingRequestLimit;
    private int _stunRequestTimeout;
    private int _tcpConnectTimeout;
    private IFunction1<DataBuffer, DataBuffer> _testReceivedRtpBuffer;
    private IFunction1<DataBuffer, DataBuffer> _testSendingRtpBuffer;
    private int _turnAllocateRequestLimit;
    private boolean _verboseDebugMessages = false;

    /* access modifiers changed from: private */
    public void processActiveCandidatePairChange(IceTransport iceTransport, IceCandidatePair iceCandidatePair) {
    }

    /* access modifiers changed from: protected */
    public Connection getInstance() {
        return this;
    }

    private <T> void addToDictionaryList(HashMap<String, ArrayList<T>> hashMap, String str, T t) {
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap, str, holder);
        ArrayList arrayList = (ArrayList) holder.getValue();
        if (!tryGetValue) {
            arrayList = new ArrayList();
        }
        if (!arrayList.contains(t)) {
            arrayList.add(t);
        }
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), str, arrayList);
    }

    private Error assignCoreTransportsToStream(Stream stream, CoreTransport coreTransport, CoreTransport coreTransport2, CoreTransport coreTransport3) {
        Error doAssignCoreTransportsToStream;
        synchronized (this._connectionLock) {
            doAssignCoreTransportsToStream = doAssignCoreTransportsToStream(stream, coreTransport, coreTransport2, coreTransport3);
        }
        return doAssignCoreTransportsToStream;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean beginConnectingToPeer() {
        /*
            r5 = this;
            java.lang.Object r0 = r5._connectionLock
            monitor-enter(r0)
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x0055 }
            fm.liveswitch.ConnectionState r2 = fm.liveswitch.ConnectionState.Initializing     // Catch:{ all -> 0x0055 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0055 }
            r2 = 0
            if (r1 != 0) goto L_0x0012
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return r2
        L_0x0012:
            fm.liveswitch.ConnectionState r1 = fm.liveswitch.ConnectionState.Connecting     // Catch:{ all -> 0x0055 }
            super.setState(r1)     // Catch:{ all -> 0x0055 }
            java.util.ArrayList<fm.liveswitch.CoreTransport> r1 = r5.__primaryCoreTransports     // Catch:{ all -> 0x0055 }
            java.util.ArrayList<fm.liveswitch.CoreTransport> r3 = r5.__secondaryCoreTransports     // Catch:{ all -> 0x0055 }
            fm.liveswitch.ArrayListExtensions.addRange(r1, r3)     // Catch:{ all -> 0x0055 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0055 }
        L_0x0022:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0055 }
            if (r3 == 0) goto L_0x0052
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0055 }
            fm.liveswitch.CoreTransport r3 = (fm.liveswitch.CoreTransport) r3     // Catch:{ all -> 0x0055 }
            fm.liveswitch.Error r3 = r5.startStreamCore(r3)     // Catch:{ all -> 0x0055 }
            if (r3 == 0) goto L_0x0022
            fm.liveswitch.ConnectionState r1 = fm.liveswitch.ConnectionState.Failing     // Catch:{ all -> 0x0055 }
            boolean r1 = r5.setState(r1, r3)     // Catch:{ all -> 0x0055 }
            if (r1 == 0) goto L_0x0050
            fm.liveswitch.ILog r1 = __log     // Catch:{ all -> 0x0055 }
            java.lang.String r4 = "Cannot start internal transport core: {0}"
            java.lang.String r3 = r3.getDescription()     // Catch:{ all -> 0x0055 }
            java.lang.String r3 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object) r3)     // Catch:{ all -> 0x0055 }
            r1.error(r3)     // Catch:{ all -> 0x0055 }
            fm.liveswitch.ConnectionState r1 = fm.liveswitch.ConnectionState.Failed     // Catch:{ all -> 0x0055 }
            super.setState(r1)     // Catch:{ all -> 0x0055 }
        L_0x0050:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return r2
        L_0x0052:
            r1 = 1
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return r1
        L_0x0055:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.beginConnectingToPeer():boolean");
    }

    private Error buildCoreTransport(IceGatherer iceGatherer, IceGatherer iceGatherer2, Holder<CoreTransport> holder, Holder<CoreTransport> holder2, boolean z, boolean z2, int i) {
        Error doBuildCoreTransport;
        synchronized (this._connectionLock) {
            doBuildCoreTransport = doBuildCoreTransport(iceGatherer, iceGatherer2, holder, holder2, z, z2, i);
        }
        return doBuildCoreTransport;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.Error buildDataStream(fm.liveswitch.DataStream r13, fm.liveswitch.CoreTransport r14, fm.liveswitch.CoreTransport r15) {
        /*
            r12 = this;
            java.lang.Object r0 = r12._connectionLock
            monitor-enter(r0)
            r1 = 0
            fm.liveswitch.Error r2 = r12.assignCoreTransportsToStream(r13, r14, r1, r15)     // Catch:{ all -> 0x00ee }
            if (r2 != 0) goto L_0x00ec
            fm.liveswitch.ErrorCode r2 = fm.liveswitch.ErrorCode.ConnectionInternalError     // Catch:{ all -> 0x00ee }
            fm.liveswitch.BundleTransport r6 = r14.getBundleTransport()     // Catch:{ all -> 0x00ee }
            fm.liveswitch.BundleTransport r7 = r15.getBundleTransport()     // Catch:{ all -> 0x00ee }
            r14 = 0
            if (r6 == 0) goto L_0x0084
            if (r7 != 0) goto L_0x001a
            goto L_0x0084
        L_0x001a:
            fm.liveswitch.SctpTransport r15 = new fm.liveswitch.SctpTransport     // Catch:{ all -> 0x00ee }
            java.lang.Object r4 = r12._connectionLock     // Catch:{ all -> 0x00ee }
            fm.liveswitch.Scheduler r5 = r12.__scheduler     // Catch:{ all -> 0x00ee }
            r8 = 65535(0xffff, float:9.1834E-41)
            r9 = 65535(0xffff, float:9.1834E-41)
            r10 = 5000(0x1388, double:2.4703E-320)
            r3 = r15
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x00ee }
            r13.setSctpTransport(r15)     // Catch:{ all -> 0x00ee }
            fm.liveswitch.DataChannel[] r3 = r13.getChannels()     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x003b
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r3)     // Catch:{ all -> 0x00ee }
            if (r4 != 0) goto L_0x0067
        L_0x003b:
            if (r3 != 0) goto L_0x003f
            fm.liveswitch.DataChannel[] r3 = new fm.liveswitch.DataChannel[r14]     // Catch:{ all -> 0x00ee }
        L_0x003f:
            fm.liveswitch.Connection$1 r4 = new fm.liveswitch.Connection$1     // Catch:{ all -> 0x00ee }
            r4.<init>()     // Catch:{ all -> 0x00ee }
            r13.removeOnChannel(r4)     // Catch:{ all -> 0x00ee }
            fm.liveswitch.Connection$2 r4 = new fm.liveswitch.Connection$2     // Catch:{ all -> 0x00ee }
            r4.<init>()     // Catch:{ all -> 0x00ee }
            r13.addOnChannel(r4)     // Catch:{ all -> 0x00ee }
            fm.liveswitch.SctpTransport r4 = r13.getSctpTransport()     // Catch:{ all -> 0x00ee }
            fm.liveswitch.Connection$3 r5 = new fm.liveswitch.Connection$3     // Catch:{ all -> 0x00ee }
            r5.<init>()     // Catch:{ all -> 0x00ee }
            r4.removeOnStateChange(r5)     // Catch:{ all -> 0x00ee }
            fm.liveswitch.SctpTransport r4 = r13.getSctpTransport()     // Catch:{ all -> 0x00ee }
            fm.liveswitch.Connection$4 r5 = new fm.liveswitch.Connection$4     // Catch:{ all -> 0x00ee }
            r5.<init>()     // Catch:{ all -> 0x00ee }
            r4.addOnStateChange(r5)     // Catch:{ all -> 0x00ee }
        L_0x0067:
            java.util.HashMap<java.lang.String, fm.liveswitch.DataStream> r4 = r12.__streamForSctpTransport     // Catch:{ all -> 0x00ee }
            java.lang.String r5 = r15.getId()     // Catch:{ all -> 0x00ee }
            fm.liveswitch.HashMapExtensions.add(r4, r5, r13)     // Catch:{ all -> 0x00ee }
            fm.liveswitch.Connection$5 r4 = new fm.liveswitch.Connection$5     // Catch:{ all -> 0x00ee }
            r4.<init>()     // Catch:{ all -> 0x00ee }
            r15.removeOnStateChange(r4)     // Catch:{ all -> 0x00ee }
            fm.liveswitch.Connection$6 r4 = new fm.liveswitch.Connection$6     // Catch:{ all -> 0x00ee }
            r4.<init>()     // Catch:{ all -> 0x00ee }
            r15.addOnStateChange(r4)     // Catch:{ all -> 0x00ee }
            r4 = r3
            r3 = r15
            r15 = r1
            goto L_0x0088
        L_0x0084:
            java.lang.String r15 = "Core not prepared prior to building data stream architecture. No Bundle Transport present."
            r3 = r1
            r4 = r3
        L_0x0088:
            if (r15 == 0) goto L_0x009b
            fm.liveswitch.Error r13 = new fm.liveswitch.Error     // Catch:{ all -> 0x00ee }
            java.lang.Exception r14 = new java.lang.Exception     // Catch:{ all -> 0x00ee }
            r14.<init>(r15)     // Catch:{ all -> 0x00ee }
            r13.<init>((fm.liveswitch.ErrorCode) r2, (java.lang.Exception) r14)     // Catch:{ all -> 0x00ee }
            fm.liveswitch.TransportType r14 = fm.liveswitch.TransportType.ReliableDataTransport     // Catch:{ all -> 0x00ee }
            r12.shutdownOnFailure(r13, r14, r1)     // Catch:{ all -> 0x00ee }
            monitor-exit(r0)     // Catch:{ all -> 0x00ee }
            return r13
        L_0x009b:
            int r15 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r4)     // Catch:{ all -> 0x00ee }
            fm.liveswitch.ReliableChannel[] r15 = new fm.liveswitch.ReliableChannel[r15]     // Catch:{ all -> 0x00ee }
            r2 = 0
        L_0x00a2:
            int r5 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r15)     // Catch:{ all -> 0x00ee }
            if (r2 >= r5) goto L_0x00c7
            r5 = r4[r2]     // Catch:{ all -> 0x00ee }
            fm.liveswitch.ReliableChannel r5 = r5.getInnerDataChannel()     // Catch:{ all -> 0x00ee }
            r15[r2] = r5     // Catch:{ all -> 0x00ee }
            r5 = r4[r2]     // Catch:{ all -> 0x00ee }
            fm.liveswitch.Connection$7 r6 = new fm.liveswitch.Connection$7     // Catch:{ all -> 0x00ee }
            r6.<init>()     // Catch:{ all -> 0x00ee }
            r5.removeOnStateChange(r6)     // Catch:{ all -> 0x00ee }
            r5 = r4[r2]     // Catch:{ all -> 0x00ee }
            fm.liveswitch.Connection$8 r6 = new fm.liveswitch.Connection$8     // Catch:{ all -> 0x00ee }
            r6.<init>()     // Catch:{ all -> 0x00ee }
            r5.addOnStateChange(r6)     // Catch:{ all -> 0x00ee }
            int r2 = r2 + 1
            goto L_0x00a2
        L_0x00c7:
            fm.liveswitch.ReliableTransport r2 = new fm.liveswitch.ReliableTransport     // Catch:{ all -> 0x00ee }
            java.lang.Object r4 = r12._connectionLock     // Catch:{ all -> 0x00ee }
            r2.<init>(r4, r3, r15, r14)     // Catch:{ all -> 0x00ee }
            r13.setReliableDataTransport(r2)     // Catch:{ all -> 0x00ee }
            java.util.HashMap<java.lang.String, fm.liveswitch.DataStream> r14 = r12.__streamForReliableDataTransport     // Catch:{ all -> 0x00ee }
            java.lang.String r15 = r2.getId()     // Catch:{ all -> 0x00ee }
            fm.liveswitch.HashMapExtensions.add(r14, r15, r13)     // Catch:{ all -> 0x00ee }
            fm.liveswitch.Connection$9 r13 = new fm.liveswitch.Connection$9     // Catch:{ all -> 0x00ee }
            r13.<init>()     // Catch:{ all -> 0x00ee }
            r2.removeOnStateChange(r13)     // Catch:{ all -> 0x00ee }
            fm.liveswitch.Connection$10 r13 = new fm.liveswitch.Connection$10     // Catch:{ all -> 0x00ee }
            r13.<init>()     // Catch:{ all -> 0x00ee }
            r2.addOnStateChange(r13)     // Catch:{ all -> 0x00ee }
            monitor-exit(r0)     // Catch:{ all -> 0x00ee }
            return r1
        L_0x00ec:
            monitor-exit(r0)     // Catch:{ all -> 0x00ee }
            return r2
        L_0x00ee:
            r13 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ee }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.buildDataStream(fm.liveswitch.DataStream, fm.liveswitch.CoreTransport, fm.liveswitch.CoreTransport):fm.liveswitch.Error");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x01d4, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.Error buildMediaStream(fm.liveswitch.Stream r17, fm.liveswitch.CoreTransport r18, fm.liveswitch.CoreTransport r19, fm.liveswitch.CoreTransport r20) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            java.lang.Object r2 = r1._connectionLock
            monitor-enter(r2)
            fm.liveswitch.Error r3 = r16.assignCoreTransportsToStream(r17, r18, r19, r20)     // Catch:{ all -> 0x01d7 }
            if (r19 != 0) goto L_0x0012
            fm.liveswitch.IceTransport r4 = r18.getIceTransport()     // Catch:{ all -> 0x01d7 }
            goto L_0x0016
        L_0x0012:
            fm.liveswitch.IceTransport r4 = r19.getIceTransport()     // Catch:{ all -> 0x01d7 }
        L_0x0016:
            r14 = r4
            if (r3 != 0) goto L_0x01d5
            boolean r3 = r0 instanceof fm.liveswitch.VideoStream     // Catch:{ all -> 0x01d7 }
            r4 = 0
            if (r3 == 0) goto L_0x00e7
            r3 = r0
            fm.liveswitch.VideoStream r3 = (fm.liveswitch.VideoStream) r3     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.VideoStream r3 = (fm.liveswitch.VideoStream) r3     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpVideoTransport r15 = new fm.liveswitch.RtpVideoTransport     // Catch:{ all -> 0x01d7 }
            java.lang.Object r6 = r1._connectionLock     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.SimulcastMode r7 = r3.getSimulcastMode()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.NackConfig r8 = r3.getNackConfig()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RedFecConfig r9 = r3.getRedFecConfig()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.JitterConfig r10 = r3.getJitterConfig()     // Catch:{ all -> 0x01d7 }
            boolean r11 = r3.getDisableAutomaticReports()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.HexDump r12 = r1.__hexDump     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IceTransport r13 = r18.getIceTransport()     // Catch:{ all -> 0x01d7 }
            r5 = r15
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x01d7 }
            r3.setRtpTransport(r15)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            boolean r6 = r3.getLegacyReceiver()     // Catch:{ all -> 0x01d7 }
            r5.setLegacyReceiver(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            int r6 = r16.getTestRoundTripTime()     // Catch:{ all -> 0x01d7 }
            r5.setTestRoundTripTime(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IFunction1 r6 = r16.getTestReceivedRtpBuffer()     // Catch:{ all -> 0x01d7 }
            r5.setTestReceivedRtpBuffer(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IFunction1 r6 = r16.getTestSendingRtpBuffer()     // Catch:{ all -> 0x01d7 }
            r5.setTestSendingRtpBuffer(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IFunction0 r6 = r16.getGetInboundRtpTransportSystemTimestamp()     // Catch:{ all -> 0x01d7 }
            r5.setGetInboundRtpTransportSystemTimestamp(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IFunction0 r6 = r16.getGetInboundRtcpTransportSystemTimestamp()     // Catch:{ all -> 0x01d7 }
            r5.setGetInboundRtcpTransportSystemTimestamp(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IFunction0 r6 = r16.getGetOutboundRtcpTransportSystemTimestamp()     // Catch:{ all -> 0x01d7 }
            r5.setGetOutboundRtcpTransportSystemTimestamp(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpListener r5 = new fm.liveswitch.RtpListener     // Catch:{ all -> 0x01d7 }
            java.lang.Object r6 = r1._connectionLock     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r7 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpVideoTransport r7 = (fm.liveswitch.RtpVideoTransport) r7     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpVideoTransport r7 = (fm.liveswitch.RtpVideoTransport) r7     // Catch:{ all -> 0x01d7 }
            r5.<init>((java.lang.Object) r6, (fm.liveswitch.RtpVideoTransport) r7)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r6 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            r6.setListener(r5)     // Catch:{ all -> 0x01d7 }
            java.util.HashMap<java.lang.String, fm.liveswitch.VideoStream> r6 = r1.__streamForVideoTransport     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r7 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            java.lang.String r7 = r7.getId()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.HashMapExtensions.add(r6, r7, r3)     // Catch:{ all -> 0x01d7 }
            java.util.HashMap<java.lang.String, fm.liveswitch.VideoStream> r6 = r1.__streamForVideoTransport     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r7 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpListener r7 = r7.getListener()     // Catch:{ all -> 0x01d7 }
            java.lang.String r7 = r7.getId()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.HashMapExtensions.add(r6, r7, r3)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r6 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.Connection$11 r7 = new fm.liveswitch.Connection$11     // Catch:{ all -> 0x01d7 }
            r7.<init>()     // Catch:{ all -> 0x01d7 }
            r6.addOnStateChange(r7)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r3 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpListener r3 = r3.getListener()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.Connection$12 r6 = new fm.liveswitch.Connection$12     // Catch:{ all -> 0x01d7 }
            r6.<init>()     // Catch:{ all -> 0x01d7 }
            r3.addOnStateChange(r6)     // Catch:{ all -> 0x01d7 }
            goto L_0x01b4
        L_0x00e7:
            boolean r3 = r0 instanceof fm.liveswitch.AudioStream     // Catch:{ all -> 0x01d7 }
            if (r3 == 0) goto L_0x01b3
            r3 = r0
            fm.liveswitch.AudioStream r3 = (fm.liveswitch.AudioStream) r3     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.AudioStream r3 = (fm.liveswitch.AudioStream) r3     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpAudioTransport r15 = new fm.liveswitch.RtpAudioTransport     // Catch:{ all -> 0x01d7 }
            java.lang.Object r6 = r1._connectionLock     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.SimulcastMode r7 = r3.getSimulcastMode()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.NackConfig r8 = r3.getNackConfig()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RedFecConfig r9 = r3.getRedFecConfig()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.JitterConfig r10 = r3.getJitterConfig()     // Catch:{ all -> 0x01d7 }
            boolean r11 = r3.getDisableAutomaticReports()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.HexDump r12 = r1.__hexDump     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IceTransport r13 = r18.getIceTransport()     // Catch:{ all -> 0x01d7 }
            r5 = r15
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x01d7 }
            r3.setRtpTransport(r15)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            boolean r6 = r3.getLegacyReceiver()     // Catch:{ all -> 0x01d7 }
            r5.setLegacyReceiver(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            int r6 = r16.getTestRoundTripTime()     // Catch:{ all -> 0x01d7 }
            r5.setTestRoundTripTime(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IFunction1 r6 = r16.getTestReceivedRtpBuffer()     // Catch:{ all -> 0x01d7 }
            r5.setTestReceivedRtpBuffer(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IFunction1 r6 = r16.getTestSendingRtpBuffer()     // Catch:{ all -> 0x01d7 }
            r5.setTestSendingRtpBuffer(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IFunction0 r6 = r16.getGetInboundRtpTransportSystemTimestamp()     // Catch:{ all -> 0x01d7 }
            r5.setGetInboundRtpTransportSystemTimestamp(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IFunction0 r6 = r16.getGetInboundRtcpTransportSystemTimestamp()     // Catch:{ all -> 0x01d7 }
            r5.setGetInboundRtcpTransportSystemTimestamp(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r5 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.IFunction0 r6 = r16.getGetOutboundRtcpTransportSystemTimestamp()     // Catch:{ all -> 0x01d7 }
            r5.setGetOutboundRtcpTransportSystemTimestamp(r6)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpListener r5 = new fm.liveswitch.RtpListener     // Catch:{ all -> 0x01d7 }
            java.lang.Object r6 = r1._connectionLock     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r7 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpAudioTransport r7 = (fm.liveswitch.RtpAudioTransport) r7     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpAudioTransport r7 = (fm.liveswitch.RtpAudioTransport) r7     // Catch:{ all -> 0x01d7 }
            r5.<init>((java.lang.Object) r6, (fm.liveswitch.RtpAudioTransport) r7)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r6 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            r6.setListener(r5)     // Catch:{ all -> 0x01d7 }
            java.util.HashMap<java.lang.String, fm.liveswitch.AudioStream> r6 = r1.__streamForAudioTransport     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r7 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            java.lang.String r7 = r7.getId()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.HashMapExtensions.add(r6, r7, r3)     // Catch:{ all -> 0x01d7 }
            java.util.HashMap<java.lang.String, fm.liveswitch.AudioStream> r6 = r1.__streamForAudioTransport     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r7 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpListener r7 = r7.getListener()     // Catch:{ all -> 0x01d7 }
            java.lang.String r7 = r7.getId()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.HashMapExtensions.add(r6, r7, r3)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r6 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.Connection$13 r7 = new fm.liveswitch.Connection$13     // Catch:{ all -> 0x01d7 }
            r7.<init>()     // Catch:{ all -> 0x01d7 }
            r6.addOnStateChange(r7)     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpTransport r3 = r3.getRtpTransport()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.RtpListener r3 = r3.getListener()     // Catch:{ all -> 0x01d7 }
            fm.liveswitch.Connection$14 r6 = new fm.liveswitch.Connection$14     // Catch:{ all -> 0x01d7 }
            r6.<init>()     // Catch:{ all -> 0x01d7 }
            r3.addOnStateChange(r6)     // Catch:{ all -> 0x01d7 }
            goto L_0x01b4
        L_0x01b3:
            r5 = r4
        L_0x01b4:
            java.lang.String r0 = r17.getMediaStreamIdentification()     // Catch:{ all -> 0x01d7 }
            if (r0 == 0) goto L_0x01d3
            fm.liveswitch.BundleTransport r3 = r18.getBundleTransport()     // Catch:{ all -> 0x01d7 }
            r3.addRtpListener(r5, r0)     // Catch:{ all -> 0x01d7 }
            if (r19 == 0) goto L_0x01ca
            fm.liveswitch.BundleTransport r3 = r19.getBundleTransport()     // Catch:{ all -> 0x01d7 }
            r3.addRtpListener(r5, r0)     // Catch:{ all -> 0x01d7 }
        L_0x01ca:
            if (r20 == 0) goto L_0x01d3
            fm.liveswitch.BundleTransport r3 = r20.getBundleTransport()     // Catch:{ all -> 0x01d7 }
            r3.addRtpListener(r5, r0)     // Catch:{ all -> 0x01d7 }
        L_0x01d3:
            monitor-exit(r2)     // Catch:{ all -> 0x01d7 }
            return r4
        L_0x01d5:
            monitor-exit(r2)     // Catch:{ all -> 0x01d7 }
            return r3
        L_0x01d7:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x01d7 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.buildMediaStream(fm.liveswitch.Stream, fm.liveswitch.CoreTransport, fm.liveswitch.CoreTransport, fm.liveswitch.CoreTransport):fm.liveswitch.Error");
    }

    private Error buildStreamTransports(BundleGroup[] bundleGroupArr) {
        Error doBuildStreamTransports;
        synchronized (this._connectionLock) {
            doBuildStreamTransports = doBuildStreamTransports(bundleGroupArr);
        }
        return doBuildStreamTransports;
    }

    private void clearLocalCachedCandidates(int i, int i2) {
        for (Candidate candidate : (Candidate[]) this.__cachedLocalCandidates.toArray(new Candidate[0])) {
            if (candidate.getSdpMediaIndex() == i && candidate.getSdpCandidateAttribute().getComponentId() == i2) {
                this.__cachedLocalCandidates.remove(candidate);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0066, code lost:
        r0 = getStreams();
        r3 = r0.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006c, code lost:
        if (r2 >= r3) goto L_0x017a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006e, code lost:
        r5 = r0[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007a, code lost:
        if (fm.liveswitch.Global.equals(r5.getType(), fm.liveswitch.StreamType.Application) == false) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007c, code lost:
        r4 = ((fm.liveswitch.DataStream) r5).getReliableDataTransport();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0085, code lost:
        if (r4 == null) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0087, code lost:
        r4.stop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008c, code lost:
        shutdownOnFailure(new fm.liveswitch.Error(fm.liveswitch.ErrorCode.ConnectionInvalidArchitecture, new java.lang.Exception(fm.liveswitch.StringExtensions.format("Connection cannot shut down Reliable Data Transport for data stream {0}, because it is not assigned. Proceeding with shutting down subsequent transports.", (java.lang.Object) r5.getId()))), fm.liveswitch.TransportType.Unset, fm.liveswitch.StringExtensions.empty);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b5, code lost:
        if (fm.liveswitch.Global.equals(r5.getType(), fm.liveswitch.StreamType.Audio) == false) goto L_0x0111;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b7, code lost:
        r6 = (fm.liveswitch.AudioStream) r5;
        r7 = r6.getRtpTransport();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c0, code lost:
        if (r7 == null) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c2, code lost:
        r7.stop();
        r7 = r7.getListener();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c9, code lost:
        if (r7 == null) goto L_0x00cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00cb, code lost:
        r7.stop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00cf, code lost:
        shutdownOnFailure(new fm.liveswitch.Error(fm.liveswitch.ErrorCode.ConnectionInvalidArchitecture, new java.lang.Exception(fm.liveswitch.StringExtensions.format("Connection cannot shut down RTP listener for audio stream {0}, because it is not assigned. Proceeding with shutting down subsequent transports.", (java.lang.Object) r5.getId()))), fm.liveswitch.TransportType.Unset, fm.liveswitch.StringExtensions.empty);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ed, code lost:
        shutdownOnFailure(new fm.liveswitch.Error(fm.liveswitch.ErrorCode.ConnectionInvalidArchitecture, new java.lang.Exception(fm.liveswitch.StringExtensions.format("Connection cannot shut down RTP Transport for audio stream {0}, because it is not assigned. Proceeding with shutting down subsequent transports.", (java.lang.Object) r5.getId()))), fm.liveswitch.TransportType.Unset, fm.liveswitch.StringExtensions.empty);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x010a, code lost:
        r6.setDisabled(true);
        r6.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x011b, code lost:
        if (fm.liveswitch.Global.equals(r5.getType(), fm.liveswitch.StreamType.Video) == false) goto L_0x0176;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x011d, code lost:
        r6 = (fm.liveswitch.VideoStream) r5;
        r7 = r6.getRtpTransport();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0126, code lost:
        if (r7 == null) goto L_0x0153;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0128, code lost:
        r7.stop();
        r7 = r7.getListener();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x012f, code lost:
        if (r7 == null) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0131, code lost:
        r7.stop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0135, code lost:
        shutdownOnFailure(new fm.liveswitch.Error(fm.liveswitch.ErrorCode.ConnectionInvalidArchitecture, new java.lang.Exception(fm.liveswitch.StringExtensions.format("Connection cannot shut down RTP listener for video stream {0}, because it is not assigned.Proceeding with shutting down subsequent transports.", (java.lang.Object) r5.getId()))), fm.liveswitch.TransportType.Unset, fm.liveswitch.StringExtensions.empty);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0153, code lost:
        shutdownOnFailure(new fm.liveswitch.Error(fm.liveswitch.ErrorCode.ConnectionInvalidArchitecture, new java.lang.Exception(fm.liveswitch.StringExtensions.format("Connection cannot shut down RTP Transport for video stream {0}, because it is not assigned.Proceeding with shutting down subsequent transports.", (java.lang.Object) r5.getId()))), fm.liveswitch.TransportType.Unset, fm.liveswitch.StringExtensions.empty);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0170, code lost:
        r6.setDisabled(true);
        r6.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0176, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x017a, code lost:
        r2 = r11._connectionLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x017c, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x017d, code lost:
        if (r1 != false) goto L_0x0184;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        super.setState(fm.liveswitch.ConnectionState.Closed);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0184, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0185, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean close() {
        /*
            r11 = this;
            java.lang.Object r0 = r11._connectionLock
            monitor-enter(r0)
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x018b }
            fm.liveswitch.ConnectionState r2 = fm.liveswitch.ConnectionState.Closing     // Catch:{ all -> 0x018b }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x018b }
            r2 = 0
            if (r1 != 0) goto L_0x0189
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x018b }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Closed     // Catch:{ all -> 0x018b }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x018b }
            if (r1 != 0) goto L_0x0189
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x018b }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Failing     // Catch:{ all -> 0x018b }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x018b }
            if (r1 != 0) goto L_0x0189
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x018b }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Failed     // Catch:{ all -> 0x018b }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x018b }
            if (r1 == 0) goto L_0x0036
            goto L_0x0189
        L_0x0036:
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x018b }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.New     // Catch:{ all -> 0x018b }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x018b }
            if (r1 == 0) goto L_0x0051
            fm.liveswitch.ConnectionState r1 = fm.liveswitch.ConnectionState.Closing     // Catch:{ all -> 0x018b }
            boolean r1 = super.setState(r1)     // Catch:{ all -> 0x018b }
            if (r1 == 0) goto L_0x004f
            fm.liveswitch.ConnectionState r1 = fm.liveswitch.ConnectionState.Closed     // Catch:{ all -> 0x018b }
            super.setState(r1)     // Catch:{ all -> 0x018b }
        L_0x004f:
            monitor-exit(r0)     // Catch:{ all -> 0x018b }
            return r2
        L_0x0051:
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x018b }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Connected     // Catch:{ all -> 0x018b }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x018b }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Closing     // Catch:{ all -> 0x018b }
            boolean r3 = super.setState(r3)     // Catch:{ all -> 0x018b }
            if (r3 != 0) goto L_0x0065
            monitor-exit(r0)     // Catch:{ all -> 0x018b }
            return r2
        L_0x0065:
            monitor-exit(r0)     // Catch:{ all -> 0x018b }
            fm.liveswitch.Stream[] r0 = r11.getStreams()
            int r3 = r0.length
        L_0x006b:
            r4 = 1
            if (r2 >= r3) goto L_0x017a
            r5 = r0[r2]
            fm.liveswitch.StreamType r6 = r5.getType()
            fm.liveswitch.StreamType r7 = fm.liveswitch.StreamType.Application
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            if (r6 == 0) goto L_0x00ab
            r4 = r5
            fm.liveswitch.DataStream r4 = (fm.liveswitch.DataStream) r4
            fm.liveswitch.DataStream r4 = (fm.liveswitch.DataStream) r4
            fm.liveswitch.ReliableTransport r4 = r4.getReliableDataTransport()
            if (r4 == 0) goto L_0x008c
            r4.stop()
            goto L_0x0176
        L_0x008c:
            fm.liveswitch.Error r4 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r6 = fm.liveswitch.ErrorCode.ConnectionInvalidArchitecture
            java.lang.Exception r7 = new java.lang.Exception
            java.lang.String r8 = "Connection cannot shut down Reliable Data Transport for data stream {0}, because it is not assigned. Proceeding with shutting down subsequent transports."
            java.lang.String r5 = r5.getId()
            java.lang.String r5 = fm.liveswitch.StringExtensions.format((java.lang.String) r8, (java.lang.Object) r5)
            r7.<init>(r5)
            r4.<init>((fm.liveswitch.ErrorCode) r6, (java.lang.Exception) r7)
            fm.liveswitch.TransportType r5 = fm.liveswitch.TransportType.Unset
            java.lang.String r6 = fm.liveswitch.StringExtensions.empty
            r11.shutdownOnFailure(r4, r5, r6)
            goto L_0x0176
        L_0x00ab:
            fm.liveswitch.StreamType r6 = r5.getType()
            fm.liveswitch.StreamType r7 = fm.liveswitch.StreamType.Audio
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            if (r6 == 0) goto L_0x0111
            r6 = r5
            fm.liveswitch.AudioStream r6 = (fm.liveswitch.AudioStream) r6
            fm.liveswitch.AudioStream r6 = (fm.liveswitch.AudioStream) r6
            fm.liveswitch.RtpTransport r7 = r6.getRtpTransport()
            if (r7 == 0) goto L_0x00ed
            r7.stop()
            fm.liveswitch.RtpListener r7 = r7.getListener()
            if (r7 == 0) goto L_0x00cf
            r7.stop()
            goto L_0x010a
        L_0x00cf:
            fm.liveswitch.Error r7 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r8 = fm.liveswitch.ErrorCode.ConnectionInvalidArchitecture
            java.lang.Exception r9 = new java.lang.Exception
            java.lang.String r10 = "Connection cannot shut down RTP listener for audio stream {0}, because it is not assigned. Proceeding with shutting down subsequent transports."
            java.lang.String r5 = r5.getId()
            java.lang.String r5 = fm.liveswitch.StringExtensions.format((java.lang.String) r10, (java.lang.Object) r5)
            r9.<init>(r5)
            r7.<init>((fm.liveswitch.ErrorCode) r8, (java.lang.Exception) r9)
            fm.liveswitch.TransportType r5 = fm.liveswitch.TransportType.Unset
            java.lang.String r8 = fm.liveswitch.StringExtensions.empty
            r11.shutdownOnFailure(r7, r5, r8)
            goto L_0x010a
        L_0x00ed:
            fm.liveswitch.Error r7 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r8 = fm.liveswitch.ErrorCode.ConnectionInvalidArchitecture
            java.lang.Exception r9 = new java.lang.Exception
            java.lang.String r10 = "Connection cannot shut down RTP Transport for audio stream {0}, because it is not assigned. Proceeding with shutting down subsequent transports."
            java.lang.String r5 = r5.getId()
            java.lang.String r5 = fm.liveswitch.StringExtensions.format((java.lang.String) r10, (java.lang.Object) r5)
            r9.<init>(r5)
            r7.<init>((fm.liveswitch.ErrorCode) r8, (java.lang.Exception) r9)
            fm.liveswitch.TransportType r5 = fm.liveswitch.TransportType.Unset
            java.lang.String r8 = fm.liveswitch.StringExtensions.empty
            r11.shutdownOnFailure(r7, r5, r8)
        L_0x010a:
            r6.setDisabled(r4)
            r6.destroy()
            goto L_0x0176
        L_0x0111:
            fm.liveswitch.StreamType r6 = r5.getType()
            fm.liveswitch.StreamType r7 = fm.liveswitch.StreamType.Video
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            if (r6 == 0) goto L_0x0176
            r6 = r5
            fm.liveswitch.VideoStream r6 = (fm.liveswitch.VideoStream) r6
            fm.liveswitch.VideoStream r6 = (fm.liveswitch.VideoStream) r6
            fm.liveswitch.RtpTransport r7 = r6.getRtpTransport()
            if (r7 == 0) goto L_0x0153
            r7.stop()
            fm.liveswitch.RtpListener r7 = r7.getListener()
            if (r7 == 0) goto L_0x0135
            r7.stop()
            goto L_0x0170
        L_0x0135:
            fm.liveswitch.Error r7 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r8 = fm.liveswitch.ErrorCode.ConnectionInvalidArchitecture
            java.lang.Exception r9 = new java.lang.Exception
            java.lang.String r10 = "Connection cannot shut down RTP listener for video stream {0}, because it is not assigned.Proceeding with shutting down subsequent transports."
            java.lang.String r5 = r5.getId()
            java.lang.String r5 = fm.liveswitch.StringExtensions.format((java.lang.String) r10, (java.lang.Object) r5)
            r9.<init>(r5)
            r7.<init>((fm.liveswitch.ErrorCode) r8, (java.lang.Exception) r9)
            fm.liveswitch.TransportType r5 = fm.liveswitch.TransportType.Unset
            java.lang.String r8 = fm.liveswitch.StringExtensions.empty
            r11.shutdownOnFailure(r7, r5, r8)
            goto L_0x0170
        L_0x0153:
            fm.liveswitch.Error r7 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r8 = fm.liveswitch.ErrorCode.ConnectionInvalidArchitecture
            java.lang.Exception r9 = new java.lang.Exception
            java.lang.String r10 = "Connection cannot shut down RTP Transport for video stream {0}, because it is not assigned.Proceeding with shutting down subsequent transports."
            java.lang.String r5 = r5.getId()
            java.lang.String r5 = fm.liveswitch.StringExtensions.format((java.lang.String) r10, (java.lang.Object) r5)
            r9.<init>(r5)
            r7.<init>((fm.liveswitch.ErrorCode) r8, (java.lang.Exception) r9)
            fm.liveswitch.TransportType r5 = fm.liveswitch.TransportType.Unset
            java.lang.String r8 = fm.liveswitch.StringExtensions.empty
            r11.shutdownOnFailure(r7, r5, r8)
        L_0x0170:
            r6.setDisabled(r4)
            r6.destroy()
        L_0x0176:
            int r2 = r2 + 1
            goto L_0x006b
        L_0x017a:
            java.lang.Object r2 = r11._connectionLock
            monitor-enter(r2)
            if (r1 != 0) goto L_0x0184
            fm.liveswitch.ConnectionState r0 = fm.liveswitch.ConnectionState.Closed     // Catch:{ all -> 0x0186 }
            super.setState(r0)     // Catch:{ all -> 0x0186 }
        L_0x0184:
            monitor-exit(r2)     // Catch:{ all -> 0x0186 }
            return r4
        L_0x0186:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0186 }
            throw r0
        L_0x0189:
            monitor-exit(r0)     // Catch:{ all -> 0x018b }
            return r2
        L_0x018b:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x018b }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.close():boolean");
    }

    private Error commenceGathering(CoreTransport coreTransport) {
        synchronized (this._connectionLock) {
            IceGatherer gatherer = coreTransport.getGatherer();
            if (gatherer == null) {
                Error error = new Error(ErrorCode.ConnectionInternalError, new Exception("Core transport not prepared prior to commencing to gather."));
                return error;
            }
            try {
                gatherer.start();
                return null;
            } catch (Exception e) {
                return new Error(ErrorCode.ConnectionTransportStartError, e);
            }
        }
    }

    public Connection(Object obj, Stream[] streamArr) {
        super(obj);
        initialize(streamArr);
    }

    /* access modifiers changed from: package-private */
    public Future<Object> connectTo(Connection connection) {
        Promise promise = new Promise();
        try {
            doConnectTo(promise, this, connection);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    private void createAnswer(SessionDescriptionRequirements sessionDescriptionRequirements, Promise<SessionDescription> promise) {
        this.__isOfferer = false;
        this.__sessionDescriptionManager.createAnswer(sessionDescriptionRequirements, promise);
    }

    private IceGatherOptions createIceGatherOptions(int i) {
        IceGatherOptions iceGatherOptions = new IceGatherOptions(super.getIceGatherPolicy(), super.getIceServers(), getIcePortRange(), getIceAddressTypes(), getStunRequestTimeout(), getTcpConnectTimeout(), getTurnAllocateRequestLimit(), getStunBindingRequestLimit());
        iceGatherOptions.setStreamIndex(i);
        iceGatherOptions.setPublicIPAddresses(getPublicIPAddresses());
        iceGatherOptions.setPrivateIPAddresses(getPrivateIPAddresses());
        iceGatherOptions.setCreateDatagramSocket(getCreateDatagramSocket());
        iceGatherOptions.setCreateStreamSocket(getCreateStreamSocket());
        return iceGatherOptions;
    }

    private void createOffer(SessionDescriptionRequirements sessionDescriptionRequirements, Promise<SessionDescription> promise) {
        this.__isOfferer = true;
        this.__sessionDescriptionManager.createOffer(sessionDescriptionRequirements, promise);
    }

    /* access modifiers changed from: private */
    public DatagramSocket createVirtualDatagramSocket(DatagramSocketCreateArgs datagramSocketCreateArgs) {
        return new VirtualUdpSocket(this.__virtualAdapter, datagramSocketCreateArgs.getIPv6());
    }

    /* access modifiers changed from: private */
    public StreamSocket createVirtualStreamSocket(StreamSocketCreateArgs streamSocketCreateArgs) {
        return new VirtualTcpSocket(this.__virtualAdapter, streamSocketCreateArgs.getServer(), streamSocketCreateArgs.getIPv6(), streamSocketCreateArgs.getSecure());
    }

    private void disableCoreTransport(CoreTransport coreTransport) {
        BundleTransport bundleTransport = coreTransport.getBundleTransport();
        DtlsTransport dtlsTransport = coreTransport.getDtlsTransport();
        IceTransport iceTransport = coreTransport.getIceTransport();
        IceGatherer gatherer = coreTransport.getGatherer();
        if (dtlsTransport != null) {
            dtlsTransport.setClosingShouldNotTriggerGlobalNonGracefulShutdown(true);
        }
        if (iceTransport != null) {
            iceTransport.setClosingShouldNotTriggerGlobalNonGracefulShutdown(true);
        }
        if (gatherer != null) {
            gatherer.setClosingShouldNotTriggerGlobalNonGracefulShutdown(true);
        }
        if (bundleTransport != null) {
            bundleTransport.stop();
        }
        if (dtlsTransport != null) {
            dtlsTransport.stop();
        } else if (iceTransport != null) {
            iceTransport.stop();
        } else if (gatherer != null) {
            gatherer.stop();
        }
    }

    private void disablePrimaryComponent(CoreTransport coreTransport, CoreTransport coreTransport2, int i) {
        String str;
        if (i != -1) {
            clearLocalCachedCandidates(i, 1);
        }
        if (coreTransport != null) {
            String str2 = "";
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__streamsForIceTransport, coreTransport.getIceTransport().getId(), holder);
            ArrayList arrayList = (ArrayList) holder.getValue();
            if (tryGetValue) {
                int count = ArrayListExtensions.getCount(arrayList);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    str2 = StringExtensions.concat(str2, ((Stream) it.next()).getId(), " ");
                }
                if (count == 1) {
                    str = StringExtensions.concat("stream ", str2, "is");
                } else {
                    str = StringExtensions.concat("streams ", str2, "are");
                }
                str2 = str;
                if (coreTransport2 != null) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        Stream stream = (Stream) it2.next();
                        DtlsTransport dtlsTransport = coreTransport2.getDtlsTransport();
                        IceTransport iceTransport = coreTransport2.getIceTransport();
                        if (iceTransport != null) {
                            addToDictionaryList(this.__streamsForIceTransport, iceTransport.getId(), stream);
                        }
                        if (dtlsTransport != null) {
                            addToDictionaryList(this.__streamsForDtlsTransport, dtlsTransport.getId(), stream);
                        }
                    }
                }
            }
            __log.debug(StringExtensions.format("Core Transport {0} will be disabled because {1} now bundled with other stream.", coreTransport.getId(), str2));
            this.__primaryCoreTransports.remove(coreTransport);
            disableCoreTransport(coreTransport);
        }
    }

    private void disableSecondaryComponent(Stream stream, int i) {
        clearLocalCachedCandidates(i, 2);
        stream.eraseLocalCandidatesForComponent(2);
        CoreTransport coreTransportRtcp = stream.getCoreTransportRtcp();
        CoreTransport coreTransportRtp = stream.getCoreTransportRtp();
        if (Global.equals(stream.getType(), StreamType.Audio)) {
            RtpTransport rtpTransport = ((AudioStream) stream).getRtpTransport();
            if (rtpTransport != null) {
                rtpTransport.setRtcpTransport(rtpTransport.getRtpTransport());
            }
        } else {
            RtpTransport rtpTransport2 = ((VideoStream) stream).getRtpTransport();
            if (rtpTransport2 != null) {
                rtpTransport2.setRtcpTransport(rtpTransport2.getRtpTransport());
            }
        }
        if (coreTransportRtp != null) {
            IceTransport iceTransport = coreTransportRtp.getIceTransport();
            IceGatherer gatherer = coreTransportRtp.getGatherer();
            iceTransport.removeRtcpTransport();
            gatherer.removeRtcpGatherer();
        }
        if (coreTransportRtcp != null) {
            __log.debug(StringExtensions.format("Multiplexing is supported for stream {0} of type {1}. Secondary (Rtcp) component will be disabled and related sockets will be closed.", stream.getId(), Global.equals(stream.getType(), StreamType.Audio) ? "Audio" : Global.equals(stream.getType(), StreamType.Video) ? "Video" : "Data"));
            stream.setCoreTransportRtcp((CoreTransport) null);
            this.__secondaryCoreTransports.remove(coreTransportRtcp);
            disableCoreTransport(coreTransportRtcp);
        }
    }

    /* access modifiers changed from: protected */
    public void doAddRemoteCandidate(Promise<Candidate> promise, Candidate candidate) {
        boolean bundled;
        int sdpMediaIndex = candidate.getSdpMediaIndex();
        CandidateAttribute sdpCandidateAttribute = candidate.getSdpCandidateAttribute();
        if (sdpMediaIndex < ArrayExtensions.getLength((Object[]) getStreams())) {
            String connectionAddress = sdpCandidateAttribute.getConnectionAddress();
            if (connectionAddress == null || !connectionAddress.endsWith(".local")) {
                Stream stream = getStreams()[sdpMediaIndex];
                CoreTransport coreTransportRtp = sdpCandidateAttribute.getComponentId() == 1 ? stream.getCoreTransportRtp() : stream.getCoreTransportRtcp();
                synchronized (this._connectionLock) {
                    bundled = stream.getBundled();
                }
                if (coreTransportRtp == null || bundled) {
                    promise.resolve(null);
                    return;
                }
                IceCandidate fromSdpCandidateAttribute = IceCandidate.fromSdpCandidateAttribute(this._connectionLock, sdpCandidateAttribute);
                IceTransport iceTransport = coreTransportRtp.getIceTransport();
                if (iceTransport != null) {
                    iceTransport.addRemoteCandidate(fromSdpCandidateAttribute);
                    promise.resolve(candidate);
                    return;
                }
                throw new RuntimeException(new Exception(StringExtensions.format("Could not add SDP candidate '{0}' for {1} stream. No Ice transport has been prepared.", sdpCandidateAttribute.toString(), stream.getType().toString(), IntegerExtensions.toString(Integer.valueOf(sdpCandidateAttribute.getComponentId())))));
            }
            throw new RuntimeException(new Exception("mDNS candidates are not supported."));
        }
        throw new RuntimeException(new Exception("Candidate received with invalid media index."));
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0086 A[Catch:{ Exception -> 0x019b }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0088 A[Catch:{ Exception -> 0x019b }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a5 A[Catch:{ Exception -> 0x019b }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00dd A[Catch:{ Exception -> 0x019b }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00df A[Catch:{ Exception -> 0x019b }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ec A[Catch:{ Exception -> 0x019b }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ed A[Catch:{ Exception -> 0x019b }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00fe A[Catch:{ Exception -> 0x019b }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x013b A[Catch:{ Exception -> 0x019b }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.Error doAssignCoreTransportsToStream(fm.liveswitch.Stream r19, fm.liveswitch.CoreTransport r20, fm.liveswitch.CoreTransport r21, fm.liveswitch.CoreTransport r22) {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            r0 = r21
            r3 = r22
            r2.setBundleCoreTransport(r3)     // Catch:{ Exception -> 0x019b }
            r19.setCoreTransportRtp(r20)     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.IceTransport r5 = r20.getIceTransport()     // Catch:{ Exception -> 0x019b }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x019b }
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.Stream>> r6 = r1.__streamsForIceTransport     // Catch:{ Exception -> 0x019b }
            r1.addToDictionaryList(r6, r5, r2)     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.StreamType r6 = r19.getType()     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.StreamType r7 = fm.liveswitch.StreamType.Audio     // Catch:{ Exception -> 0x019b }
            boolean r7 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ Exception -> 0x019b }
            java.lang.String r8 = ""
            java.lang.String r11 = "Data"
            r12 = 1
            r13 = 0
            r14 = 4
            java.lang.String r15 = "Audio"
            java.lang.String r16 = "Video"
            if (r7 != 0) goto L_0x0057
            fm.liveswitch.StreamType r7 = r19.getType()     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.StreamType r4 = fm.liveswitch.StreamType.Video     // Catch:{ Exception -> 0x019b }
            boolean r4 = fm.liveswitch.Global.equals(r7, r4)     // Catch:{ Exception -> 0x019b }
            if (r4 == 0) goto L_0x003f
            goto L_0x0057
        L_0x003f:
            fm.liveswitch.StreamType r4 = fm.liveswitch.StreamType.Application     // Catch:{ Exception -> 0x019b }
            boolean r4 = fm.liveswitch.Global.equals(r6, r4)     // Catch:{ Exception -> 0x019b }
            if (r4 == 0) goto L_0x0084
            fm.liveswitch.ILog r4 = __log     // Catch:{ Exception -> 0x019b }
            java.lang.String r7 = "Assigned IceTransport with ID {0} to a {1} stream with ID {2}."
            java.lang.String r9 = r19.getId()     // Catch:{ Exception -> 0x019b }
            java.lang.String r7 = fm.liveswitch.StringExtensions.format(r7, r5, r11, r9)     // Catch:{ Exception -> 0x019b }
            r4.debug(r7)     // Catch:{ Exception -> 0x019b }
            goto L_0x0084
        L_0x0057:
            java.lang.String r4 = "RTP component "
            fm.liveswitch.ILog r7 = __log     // Catch:{ Exception -> 0x019b }
            java.lang.String r9 = "Assigned {3}IceTransport with ID {0} to {1} stream with ID {2}."
            java.lang.Object[] r10 = new java.lang.Object[r14]     // Catch:{ Exception -> 0x019b }
            r10[r13] = r5     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.StreamType r13 = fm.liveswitch.StreamType.Audio     // Catch:{ Exception -> 0x019b }
            boolean r13 = fm.liveswitch.Global.equals(r6, r13)     // Catch:{ Exception -> 0x019b }
            if (r13 == 0) goto L_0x006b
            r13 = r15
            goto L_0x006d
        L_0x006b:
            r13 = r16
        L_0x006d:
            r10[r12] = r13     // Catch:{ Exception -> 0x019b }
            java.lang.String r13 = r19.getId()     // Catch:{ Exception -> 0x019b }
            r17 = 2
            r10[r17] = r13     // Catch:{ Exception -> 0x019b }
            if (r0 != 0) goto L_0x007a
            r4 = r8
        L_0x007a:
            r13 = 3
            r10[r13] = r4     // Catch:{ Exception -> 0x019b }
            java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r9, (java.lang.Object[]) r10)     // Catch:{ Exception -> 0x019b }
            r7.debug(r4)     // Catch:{ Exception -> 0x019b }
        L_0x0084:
            if (r3 != 0) goto L_0x0088
            r4 = 0
            goto L_0x0090
        L_0x0088:
            fm.liveswitch.IceTransport r4 = r22.getIceTransport()     // Catch:{ Exception -> 0x019b }
            java.lang.String r4 = r4.getId()     // Catch:{ Exception -> 0x019b }
        L_0x0090:
            if (r4 == 0) goto L_0x00f8
            boolean r7 = fm.liveswitch.Global.equals(r5, r4)     // Catch:{ Exception -> 0x019b }
            if (r7 != 0) goto L_0x00f8
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.Stream>> r7 = r1.__streamsForIceTransport     // Catch:{ Exception -> 0x019b }
            r1.addToDictionaryList(r7, r4, r2)     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.StreamType r7 = fm.liveswitch.StreamType.Audio     // Catch:{ Exception -> 0x019b }
            boolean r7 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ Exception -> 0x019b }
            if (r7 != 0) goto L_0x00ca
            fm.liveswitch.StreamType r7 = r19.getType()     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.StreamType r9 = fm.liveswitch.StreamType.Video     // Catch:{ Exception -> 0x019b }
            boolean r7 = fm.liveswitch.Global.equals(r7, r9)     // Catch:{ Exception -> 0x019b }
            if (r7 == 0) goto L_0x00b2
            goto L_0x00ca
        L_0x00b2:
            fm.liveswitch.StreamType r4 = fm.liveswitch.StreamType.Application     // Catch:{ Exception -> 0x019b }
            boolean r4 = fm.liveswitch.Global.equals(r6, r4)     // Catch:{ Exception -> 0x019b }
            if (r4 == 0) goto L_0x00f8
            fm.liveswitch.ILog r4 = __log     // Catch:{ Exception -> 0x019b }
            java.lang.String r7 = "If stream bundling is negotiated, {1} stream with ID {2} will use IceTransport with ID {0}."
            java.lang.String r8 = r19.getId()     // Catch:{ Exception -> 0x019b }
            java.lang.String r5 = fm.liveswitch.StringExtensions.format(r7, r5, r11, r8)     // Catch:{ Exception -> 0x019b }
            r4.debug(r5)     // Catch:{ Exception -> 0x019b }
            goto L_0x00f8
        L_0x00ca:
            java.lang.String r5 = "RTP and RTCP components"
            fm.liveswitch.ILog r7 = __log     // Catch:{ Exception -> 0x019b }
            java.lang.String r9 = "If stream bundling is negotiated, {1} stream with ID {2} will use {3}IceTransport with ID {0}."
            java.lang.Object[] r10 = new java.lang.Object[r14]     // Catch:{ Exception -> 0x019b }
            r11 = 0
            r10[r11] = r4     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.StreamType r4 = fm.liveswitch.StreamType.Audio     // Catch:{ Exception -> 0x019b }
            boolean r4 = fm.liveswitch.Global.equals(r6, r4)     // Catch:{ Exception -> 0x019b }
            if (r4 == 0) goto L_0x00df
            r4 = r15
            goto L_0x00e1
        L_0x00df:
            r4 = r16
        L_0x00e1:
            r10[r12] = r4     // Catch:{ Exception -> 0x019b }
            java.lang.String r4 = r19.getId()     // Catch:{ Exception -> 0x019b }
            r11 = 2
            r10[r11] = r4     // Catch:{ Exception -> 0x019b }
            if (r0 != 0) goto L_0x00ed
            goto L_0x00ee
        L_0x00ed:
            r8 = r5
        L_0x00ee:
            r4 = 3
            r10[r4] = r8     // Catch:{ Exception -> 0x019b }
            java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r9, (java.lang.Object[]) r10)     // Catch:{ Exception -> 0x019b }
            r7.debug(r4)     // Catch:{ Exception -> 0x019b }
        L_0x00f8:
            boolean r4 = r19.getUseDtls()     // Catch:{ Exception -> 0x019b }
            if (r4 == 0) goto L_0x0139
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.Stream>> r4 = r1.__streamsForDtlsTransport     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.DtlsTransport r5 = r20.getDtlsTransport()     // Catch:{ Exception -> 0x019b }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x019b }
            r1.addToDictionaryList(r4, r5, r2)     // Catch:{ Exception -> 0x019b }
            if (r3 != 0) goto L_0x010f
            r3 = 0
            goto L_0x0117
        L_0x010f:
            fm.liveswitch.DtlsTransport r3 = r22.getDtlsTransport()     // Catch:{ Exception -> 0x019b }
            java.lang.String r3 = r3.getId()     // Catch:{ Exception -> 0x019b }
        L_0x0117:
            if (r3 == 0) goto L_0x012c
            fm.liveswitch.DtlsTransport r4 = r20.getDtlsTransport()     // Catch:{ Exception -> 0x019b }
            java.lang.String r4 = r4.getId()     // Catch:{ Exception -> 0x019b }
            boolean r4 = fm.liveswitch.Global.equals(r4, r3)     // Catch:{ Exception -> 0x019b }
            if (r4 != 0) goto L_0x012c
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.Stream>> r4 = r1.__streamsForDtlsTransport     // Catch:{ Exception -> 0x019b }
            r1.addToDictionaryList(r4, r3, r2)     // Catch:{ Exception -> 0x019b }
        L_0x012c:
            fm.liveswitch.DtlsTransport r3 = r20.getDtlsTransport()     // Catch:{ Exception -> 0x019b }
            if (r3 == 0) goto L_0x0139
            fm.liveswitch.EncryptionMode[] r4 = r19.getEncryptionModes()     // Catch:{ Exception -> 0x019b }
            r3.setEncryptionModes(r4)     // Catch:{ Exception -> 0x019b }
        L_0x0139:
            if (r0 == 0) goto L_0x0199
            fm.liveswitch.IceTransport r3 = r21.getIceTransport()     // Catch:{ Exception -> 0x019b }
            java.lang.String r3 = r3.getId()     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.StreamType r4 = fm.liveswitch.StreamType.Audio     // Catch:{ Exception -> 0x019b }
            boolean r4 = fm.liveswitch.Global.equals(r6, r4)     // Catch:{ Exception -> 0x019b }
            if (r4 != 0) goto L_0x0157
            fm.liveswitch.StreamType r4 = r19.getType()     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.StreamType r5 = fm.liveswitch.StreamType.Video     // Catch:{ Exception -> 0x019b }
            boolean r4 = fm.liveswitch.Global.equals(r4, r5)     // Catch:{ Exception -> 0x019b }
            if (r4 == 0) goto L_0x0171
        L_0x0157:
            fm.liveswitch.ILog r4 = __log     // Catch:{ Exception -> 0x019b }
            java.lang.String r5 = "Assigned RTCP component IceTransport with ID {0} to {1} stream with ID {2}."
            fm.liveswitch.StreamType r7 = fm.liveswitch.StreamType.Audio     // Catch:{ Exception -> 0x019b }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ Exception -> 0x019b }
            if (r6 == 0) goto L_0x0164
            goto L_0x0166
        L_0x0164:
            r15 = r16
        L_0x0166:
            java.lang.String r6 = r19.getId()     // Catch:{ Exception -> 0x019b }
            java.lang.String r5 = fm.liveswitch.StringExtensions.format(r5, r3, r15, r6)     // Catch:{ Exception -> 0x019b }
            r4.debug(r5)     // Catch:{ Exception -> 0x019b }
        L_0x0171:
            r2.setCoreTransportRtcp(r0)     // Catch:{ Exception -> 0x019b }
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.Stream>> r4 = r1.__streamsForIceTransport     // Catch:{ Exception -> 0x019b }
            r1.addToDictionaryList(r4, r3, r2)     // Catch:{ Exception -> 0x019b }
            boolean r3 = r19.getUseDtls()     // Catch:{ Exception -> 0x019b }
            if (r3 == 0) goto L_0x0199
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.Stream>> r3 = r1.__streamsForDtlsTransport     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.DtlsTransport r4 = r21.getDtlsTransport()     // Catch:{ Exception -> 0x019b }
            java.lang.String r4 = r4.getId()     // Catch:{ Exception -> 0x019b }
            r1.addToDictionaryList(r3, r4, r2)     // Catch:{ Exception -> 0x019b }
            fm.liveswitch.DtlsTransport r0 = r21.getDtlsTransport()     // Catch:{ Exception -> 0x019b }
            if (r0 == 0) goto L_0x0199
            fm.liveswitch.EncryptionMode[] r3 = r19.getEncryptionModes()     // Catch:{ Exception -> 0x019b }
            r0.setEncryptionModes(r3)     // Catch:{ Exception -> 0x019b }
        L_0x0199:
            r2 = 0
            return r2
        L_0x019b:
            r0 = move-exception
            java.lang.String r2 = r19.getId()
            java.lang.String r3 = "Could not assign core transport for stream {0}."
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r2)
            fm.liveswitch.ILog r3 = __log
            r3.error((java.lang.String) r2, (java.lang.Exception) r0)
            fm.liveswitch.Error r2 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r3 = fm.liveswitch.ErrorCode.ConnectionInternalError
            r2.<init>((fm.liveswitch.ErrorCode) r3, (java.lang.Exception) r0)
            r2.setException(r0)
            fm.liveswitch.TransportType r0 = fm.liveswitch.TransportType.Unset
            r3 = 0
            r1.shutdownOnFailure(r2, r0, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.doAssignCoreTransportsToStream(fm.liveswitch.Stream, fm.liveswitch.CoreTransport, fm.liveswitch.CoreTransport, fm.liveswitch.CoreTransport):fm.liveswitch.Error");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0075 A[Catch:{ Exception -> 0x042c }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0078 A[Catch:{ Exception -> 0x042c }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c5 A[Catch:{ Exception -> 0x042c }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0104 A[Catch:{ Exception -> 0x042c }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0184 A[Catch:{ Exception -> 0x042c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.Error doBuildCoreTransport(fm.liveswitch.IceGatherer r21, fm.liveswitch.IceGatherer r22, fm.liveswitch.Holder<fm.liveswitch.CoreTransport> r23, fm.liveswitch.Holder<fm.liveswitch.CoreTransport> r24, boolean r25, boolean r26, int r27) {
        /*
            r20 = this;
            r1 = r20
            r2 = r23
            r3 = r24
            r4 = 0
            r2.setValue(r4)     // Catch:{ Exception -> 0x042c }
            r3.setValue(r4)     // Catch:{ Exception -> 0x042c }
            if (r21 != 0) goto L_0x0219
            fm.liveswitch.IceGatherer r9 = new fm.liveswitch.IceGatherer     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r1._connectionLock     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Scheduler r5 = r1.__scheduler     // Catch:{ Exception -> 0x042c }
            r6 = r27
            fm.liveswitch.IceGatherOptions r6 = r1.createIceGatherOptions(r6)     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceParameters r7 = r20.getLocalIceParameters()     // Catch:{ Exception -> 0x042c }
            r9.<init>(r0, r5, r6, r7)     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherOptions r0 = r9.getOptions()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceServer[] r5 = r0.getServers()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherPolicy r0 = r0.getPolicy()     // Catch:{ Exception -> 0x042c }
            if (r5 == 0) goto L_0x006b
            int r6 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r5)     // Catch:{ Exception -> 0x042c }
            if (r6 != 0) goto L_0x0037
            goto L_0x006b
        L_0x0037:
            int r6 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r5)     // Catch:{ Exception -> 0x042c }
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ Exception -> 0x042c }
            r7 = 0
        L_0x003e:
            int r8 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r5)     // Catch:{ Exception -> 0x042c }
            if (r7 >= r8) goto L_0x0054
            r8 = r5[r7]     // Catch:{ Exception -> 0x042c }
            java.lang.String r8 = r8.getUrl()     // Catch:{ Exception -> 0x042c }
            if (r8 == 0) goto L_0x004d
            goto L_0x004f
        L_0x004d:
            java.lang.String r8 = ""
        L_0x004f:
            r6[r7] = r8     // Catch:{ Exception -> 0x042c }
            int r7 = r7 + 1
            goto L_0x003e
        L_0x0054:
            int r5 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r6)     // Catch:{ Exception -> 0x042c }
            r7 = 1
            if (r5 != r7) goto L_0x005e
            java.lang.String r5 = "server: "
            goto L_0x0060
        L_0x005e:
            java.lang.String r5 = "servers: "
        L_0x0060:
            java.lang.String r7 = ", "
            java.lang.String r6 = fm.liveswitch.StringExtensions.join(r7, r6)     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = fm.liveswitch.StringExtensions.concat(r5, r6)     // Catch:{ Exception -> 0x042c }
            goto L_0x006d
        L_0x006b:
            java.lang.String r5 = "no stun or turn servers"
        L_0x006d:
            fm.liveswitch.IceGatherPolicy r6 = fm.liveswitch.IceGatherPolicy.All     // Catch:{ Exception -> 0x042c }
            boolean r6 = fm.liveswitch.Global.equals(r0, r6)     // Catch:{ Exception -> 0x042c }
            if (r6 == 0) goto L_0x0078
            java.lang.String r0 = "All"
            goto L_0x0085
        L_0x0078:
            fm.liveswitch.IceGatherPolicy r6 = fm.liveswitch.IceGatherPolicy.NoHost     // Catch:{ Exception -> 0x042c }
            boolean r0 = fm.liveswitch.Global.equals(r0, r6)     // Catch:{ Exception -> 0x042c }
            if (r0 == 0) goto L_0x0083
            java.lang.String r0 = "No host"
            goto L_0x0085
        L_0x0083:
            java.lang.String r0 = "Relay only"
        L_0x0085:
            fm.liveswitch.ILog r6 = __log     // Catch:{ Exception -> 0x042c }
            java.lang.String r7 = "Prepared Gatherer {0} with gather policy {1} and {2}."
            java.lang.String r8 = r9.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.String r0 = fm.liveswitch.StringExtensions.format(r7, r8, r0, r5)     // Catch:{ Exception -> 0x042c }
            r6.debug(r0)     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r0 = new fm.liveswitch.IceTransport     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r1._connectionLock     // Catch:{ Exception -> 0x042c }
            java.lang.String r6 = super.getId()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Scheduler r7 = r1.__scheduler     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransportOptions r8 = r20.getIceTransportOptions()     // Catch:{ Exception -> 0x042c }
            r0.<init>(r5, r6, r7, r8)     // Catch:{ Exception -> 0x042c }
            boolean r5 = r20.getActiveIceKeepAliveEnabled()     // Catch:{ Exception -> 0x042c }
            r0.setActiveIceKeepAliveEnabled(r5)     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IcePolicy r5 = r20.getIcePolicy()     // Catch:{ Exception -> 0x042c }
            r0.setIcePolicy(r5)     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.BundleTransport r8 = new fm.liveswitch.BundleTransport     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HexDump r5 = r1.__hexDump     // Catch:{ Exception -> 0x042c }
            r8.<init>(r0, r4, r5)     // Catch:{ Exception -> 0x042c }
            java.util.HashMap<java.lang.String, fm.liveswitch.BundleTransport> r5 = r1.__bundleTransports     // Catch:{ Exception -> 0x042c }
            java.lang.String r6 = r8.getId()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HashMapExtensions.add(r5, r6, r8)     // Catch:{ Exception -> 0x042c }
            if (r26 == 0) goto L_0x0104
            fm.liveswitch.DtlsTransport r7 = new fm.liveswitch.DtlsTransport     // Catch:{ Exception -> 0x042c }
            java.lang.Object r11 = r1._connectionLock     // Catch:{ Exception -> 0x042c }
            java.lang.String r12 = super.getId()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsCertificate[] r14 = r20.getLocalDtlsCertificates()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsCipherSuite[] r15 = r1.__cipherSuites     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsProtocolVersion r5 = r1.__dtlsServerMinVersion     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsProtocolVersion r6 = r1.__dtlsServerMaxVersion     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsProtocolVersion r13 = r1.__dtlsClientVersion     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsRole r10 = r1.__preferredDtlsRole     // Catch:{ Exception -> 0x042c }
            r19 = r10
            r10 = r7
            r18 = r13
            r13 = r0
            r16 = r5
            r17 = r6
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ Exception -> 0x042c }
            r8.setDtlsTransport(r7)     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r13 = new fm.liveswitch.CoreTransport     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r1._connectionLock     // Catch:{ Exception -> 0x042c }
            java.lang.String r10 = super.getId()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Scheduler r11 = r1.__scheduler     // Catch:{ Exception -> 0x042c }
            r5 = r13
            r12 = r7
            r7 = r10
            r14 = r8
            r8 = r11
            r10 = r0
            r11 = r12
            r12 = r14
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x042c }
            r2.setValue(r13)     // Catch:{ Exception -> 0x042c }
            goto L_0x0119
        L_0x0104:
            r14 = r8
            fm.liveswitch.CoreTransport r13 = new fm.liveswitch.CoreTransport     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r1._connectionLock     // Catch:{ Exception -> 0x042c }
            java.lang.String r7 = super.getId()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Scheduler r8 = r1.__scheduler     // Catch:{ Exception -> 0x042c }
            r11 = 0
            r5 = r13
            r10 = r0
            r12 = r14
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x042c }
            r2.setValue(r13)     // Catch:{ Exception -> 0x042c }
        L_0x0119:
            java.util.HashMap<java.lang.String, fm.liveswitch.IceGatherer> r0 = r1.__gatherers     // Catch:{ Exception -> 0x042c }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r5 = r5.getGatherer()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r6 = (fm.liveswitch.CoreTransport) r6     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r6 = r6.getGatherer()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HashMapExtensions.set(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r0 = r0.getGatherer()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$15 r5 = new fm.liveswitch.Connection$15     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.removeOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r0 = r0.getGatherer()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$16 r5 = new fm.liveswitch.Connection$16     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.addOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r0 = r0.getGatherer()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$17 r5 = new fm.liveswitch.Connection$17     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.removeOnLocalCandidate(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r0 = r0.getGatherer()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$18 r5 = new fm.liveswitch.Connection$18     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.addOnLocalCandidate(r5)     // Catch:{ Exception -> 0x042c }
            if (r25 != 0) goto L_0x0219
            if (r26 == 0) goto L_0x01a3
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            r5 = r0
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsCertificate[] r6 = r20.getLocalDtlsCertificates()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsCipherSuite[] r7 = r1.__cipherSuites     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsProtocolVersion r8 = r1.__dtlsServerMinVersion     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsProtocolVersion r9 = r1.__dtlsServerMaxVersion     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsProtocolVersion r10 = r1.__dtlsClientVersion     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsRole r11 = r1.__preferredDtlsRole     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = r5.createComplementaryCoreTransport(r6, r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x042c }
            r3.setValue(r0)     // Catch:{ Exception -> 0x042c }
            goto L_0x01b0
        L_0x01a3:
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = r0.createComplementaryCoreTransport()     // Catch:{ Exception -> 0x042c }
            r3.setValue(r0)     // Catch:{ Exception -> 0x042c }
        L_0x01b0:
            java.util.HashMap<java.lang.String, fm.liveswitch.IceGatherer> r0 = r1.__gatherers     // Catch:{ Exception -> 0x042c }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r5 = r5.getGatherer()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r6 = (fm.liveswitch.CoreTransport) r6     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r6 = r6.getGatherer()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HashMapExtensions.set(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r0 = r0.getGatherer()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$19 r5 = new fm.liveswitch.Connection$19     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.removeOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r0 = r0.getGatherer()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$20 r5 = new fm.liveswitch.Connection$20     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.addOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r0 = r0.getGatherer()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$21 r5 = new fm.liveswitch.Connection$21     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.removeOnLocalCandidate(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r0 = r0.getGatherer()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$22 r5 = new fm.liveswitch.Connection$22     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.addOnLocalCandidate(r5)     // Catch:{ Exception -> 0x042c }
        L_0x0219:
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.CoreTransport>> r0 = r1.__coreTransportsForGatherer     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r5 = r5.getGatherer()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            r1.addToDictionaryList(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.util.HashMap<java.lang.String, fm.liveswitch.IceTransport> r0 = r1.__iceTransports     // Catch:{ Exception -> 0x042c }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r5 = r5.getIceTransport()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r6 = (fm.liveswitch.CoreTransport) r6     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r6 = r6.getIceTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HashMapExtensions.set(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.util.HashMap<java.lang.String, fm.liveswitch.CoreTransport> r0 = r1.__coreTransportForIceTransport     // Catch:{ Exception -> 0x042c }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r5 = r5.getIceTransport()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HashMapExtensions.set(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r0 = r0.getIceTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$23 r5 = new fm.liveswitch.Connection$23     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.removeOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r0 = r0.getIceTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$24 r5 = new fm.liveswitch.Connection$24     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.addOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r0 = r0.getIceTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$25 r5 = new fm.liveswitch.Connection$25     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.removeOnActiveCandidatePairChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r0 = r0.getIceTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$26 r5 = new fm.liveswitch.Connection$26     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.addOnActiveCandidatePairChange(r5)     // Catch:{ Exception -> 0x042c }
            if (r26 == 0) goto L_0x0316
            java.util.HashMap<java.lang.String, fm.liveswitch.DtlsTransport> r0 = r1.__dtlsTransports     // Catch:{ Exception -> 0x042c }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsTransport r5 = r5.getDtlsTransport()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r6 = (fm.liveswitch.CoreTransport) r6     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsTransport r6 = r6.getDtlsTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HashMapExtensions.set(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.util.HashMap<java.lang.String, fm.liveswitch.CoreTransport> r0 = r1.__coreTransportForDtlsTransport     // Catch:{ Exception -> 0x042c }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsTransport r5 = r5.getDtlsTransport()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HashMapExtensions.set(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsTransport r0 = r0.getDtlsTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$27 r5 = new fm.liveswitch.Connection$27     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.removeOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsTransport r0 = r0.getDtlsTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$28 r5 = new fm.liveswitch.Connection$28     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.addOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
        L_0x0316:
            java.util.ArrayList<fm.liveswitch.CoreTransport> r0 = r1.__primaryCoreTransports     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r23.getValue()     // Catch:{ Exception -> 0x042c }
            r0.add(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            if (r0 == 0) goto L_0x042b
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.CoreTransport>> r0 = r1.__coreTransportsForGatherer     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceGatherer r5 = r5.getGatherer()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            r1.addToDictionaryList(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.util.HashMap<java.lang.String, fm.liveswitch.IceTransport> r0 = r1.__iceTransports     // Catch:{ Exception -> 0x042c }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r5 = r5.getIceTransport()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r6 = (fm.liveswitch.CoreTransport) r6     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r6 = r6.getIceTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HashMapExtensions.set(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.util.HashMap<java.lang.String, fm.liveswitch.CoreTransport> r0 = r1.__coreTransportForIceTransport     // Catch:{ Exception -> 0x042c }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r5 = r5.getIceTransport()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HashMapExtensions.set(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r0 = r0.getIceTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$29 r5 = new fm.liveswitch.Connection$29     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.removeOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r0 = r0.getIceTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$30 r5 = new fm.liveswitch.Connection$30     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.addOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r0 = r0.getIceTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$31 r5 = new fm.liveswitch.Connection$31     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.removeOnActiveCandidatePairChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.IceTransport r0 = r0.getIceTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$32 r5 = new fm.liveswitch.Connection$32     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.addOnActiveCandidatePairChange(r5)     // Catch:{ Exception -> 0x042c }
            if (r26 == 0) goto L_0x0422
            java.util.HashMap<java.lang.String, fm.liveswitch.DtlsTransport> r0 = r1.__dtlsTransports     // Catch:{ Exception -> 0x042c }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsTransport r5 = r5.getDtlsTransport()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r6 = (fm.liveswitch.CoreTransport) r6     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsTransport r6 = r6.getDtlsTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HashMapExtensions.set(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.util.HashMap<java.lang.String, fm.liveswitch.CoreTransport> r0 = r1.__coreTransportForDtlsTransport     // Catch:{ Exception -> 0x042c }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r5 = (fm.liveswitch.CoreTransport) r5     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsTransport r5 = r5.getDtlsTransport()     // Catch:{ Exception -> 0x042c }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x042c }
            java.lang.Object r6 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.HashMapExtensions.set(r0, r5, r6)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsTransport r0 = r0.getDtlsTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$33 r5 = new fm.liveswitch.Connection$33     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.removeOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
            java.lang.Object r0 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.CoreTransport r0 = (fm.liveswitch.CoreTransport) r0     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.DtlsTransport r0 = r0.getDtlsTransport()     // Catch:{ Exception -> 0x042c }
            fm.liveswitch.Connection$34 r5 = new fm.liveswitch.Connection$34     // Catch:{ Exception -> 0x042c }
            r5.<init>()     // Catch:{ Exception -> 0x042c }
            r0.addOnStateChange(r5)     // Catch:{ Exception -> 0x042c }
        L_0x0422:
            java.util.ArrayList<fm.liveswitch.CoreTransport> r0 = r1.__secondaryCoreTransports     // Catch:{ Exception -> 0x042c }
            java.lang.Object r5 = r24.getValue()     // Catch:{ Exception -> 0x042c }
            r0.add(r5)     // Catch:{ Exception -> 0x042c }
        L_0x042b:
            return r4
        L_0x042c:
            r0 = move-exception
            r2.setValue(r4)
            r3.setValue(r4)
            java.util.HashMap<java.lang.String, fm.liveswitch.IceGatherer> r2 = r1.__gatherers
            r2.clear()
            java.util.ArrayList<fm.liveswitch.CoreTransport> r2 = r1.__primaryCoreTransports
            r2.clear()
            java.util.ArrayList<fm.liveswitch.CoreTransport> r2 = r1.__secondaryCoreTransports
            r2.clear()
            java.util.HashMap<java.lang.String, fm.liveswitch.CoreTransport> r2 = r1.__coreTransportForIceTransport
            r2.clear()
            java.util.HashMap<java.lang.String, fm.liveswitch.CoreTransport> r2 = r1.__coreTransportForDtlsTransport
            r2.clear()
            java.util.HashMap<java.lang.String, fm.liveswitch.DtlsTransport> r2 = r1.__dtlsTransports
            r2.clear()
            java.util.HashMap<java.lang.String, fm.liveswitch.IceTransport> r2 = r1.__iceTransports
            r2.clear()
            java.lang.String r2 = super.getId()
            java.lang.String r3 = "Could not build core transport for connection {0}."
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r2)
            fm.liveswitch.ILog r3 = __log
            r3.error((java.lang.String) r2, (java.lang.Exception) r0)
            fm.liveswitch.Error r2 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r3 = fm.liveswitch.ErrorCode.ConnectionInternalError
            r2.<init>((fm.liveswitch.ErrorCode) r3, (java.lang.Exception) r0)
            r2.setException(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.doBuildCoreTransport(fm.liveswitch.IceGatherer, fm.liveswitch.IceGatherer, fm.liveswitch.Holder, fm.liveswitch.Holder, boolean, boolean, int):fm.liveswitch.Error");
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x01d9 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x017e A[Catch:{ Exception -> 0x01ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0181 A[Catch:{ Exception -> 0x01ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x018e A[Catch:{ Exception -> 0x01ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01c5 A[Catch:{ Exception -> 0x01ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01da A[Catch:{ Exception -> 0x01ef }, LOOP:2: B:42:0x00f8->B:85:0x01da, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.Error doBuildStreamTransports(fm.liveswitch.BundleGroup[] r20) {
        /*
            r19 = this;
            r9 = r19
            fm.liveswitch.StreamType r0 = fm.liveswitch.StreamType.Audio     // Catch:{ Exception -> 0x01ef }
            java.util.ArrayList r0 = r9.getStreamsByType(r0)     // Catch:{ Exception -> 0x01ef }
            r10 = 0
            fm.liveswitch.AudioStream[] r1 = new fm.liveswitch.AudioStream[r10]     // Catch:{ Exception -> 0x01ef }
            java.lang.Object[] r0 = r0.toArray(r1)     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.AudioStream[] r0 = (fm.liveswitch.AudioStream[]) r0     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.StreamType r1 = fm.liveswitch.StreamType.Video     // Catch:{ Exception -> 0x01ef }
            java.util.ArrayList r1 = r9.getStreamsByType(r1)     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.VideoStream[] r2 = new fm.liveswitch.VideoStream[r10]     // Catch:{ Exception -> 0x01ef }
            java.lang.Object[] r1 = r1.toArray(r2)     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.VideoStream[] r1 = (fm.liveswitch.VideoStream[]) r1     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.StreamType r2 = fm.liveswitch.StreamType.Application     // Catch:{ Exception -> 0x01ef }
            java.util.ArrayList r2 = r9.getStreamsByType(r2)     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.DataStream[] r3 = new fm.liveswitch.DataStream[r10]     // Catch:{ Exception -> 0x01ef }
            java.lang.Object[] r2 = r2.toArray(r3)     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.DataStream[] r2 = (fm.liveswitch.DataStream[]) r2     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.BundlePolicy r3 = super.getBundlePolicy()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.BundlePolicy r4 = fm.liveswitch.BundlePolicy.Balanced     // Catch:{ Exception -> 0x01ef }
            boolean r3 = fm.liveswitch.Global.equals(r3, r4)     // Catch:{ Exception -> 0x01ef }
            if (r3 != 0) goto L_0x01e2
            fm.liveswitch.BundlePolicy r3 = super.getBundlePolicy()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.BundlePolicy r4 = fm.liveswitch.BundlePolicy.MaxBundle     // Catch:{ Exception -> 0x01ef }
            boolean r3 = fm.liveswitch.Global.equals(r3, r4)     // Catch:{ Exception -> 0x01ef }
            r11 = 1
            if (r3 == 0) goto L_0x004e
            fm.liveswitch.MultiplexPolicy r2 = fm.liveswitch.MultiplexPolicy.Required     // Catch:{ Exception -> 0x01ef }
            r9.setMultiplexPolicy(r2)     // Catch:{ Exception -> 0x01ef }
            r2 = 1
            r3 = 1
            goto L_0x007f
        L_0x004e:
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)     // Catch:{ Exception -> 0x01ef }
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x01ef }
            int r3 = r3 + r4
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r2)     // Catch:{ Exception -> 0x01ef }
            int r3 = r3 + r2
            fm.liveswitch.MultiplexPolicy r2 = r19.getMultiplexPolicy()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.MultiplexPolicy r4 = fm.liveswitch.MultiplexPolicy.Required     // Catch:{ Exception -> 0x01ef }
            boolean r2 = fm.liveswitch.Global.equals(r2, r4)     // Catch:{ Exception -> 0x01ef }
            if (r20 == 0) goto L_0x007f
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r20)     // Catch:{ Exception -> 0x01ef }
            if (r4 <= 0) goto L_0x007f
            fm.liveswitch.BundlePolicy r4 = super.getBundlePolicy()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.BundlePolicy r5 = fm.liveswitch.BundlePolicy.Disabled     // Catch:{ Exception -> 0x01ef }
            boolean r4 = fm.liveswitch.Global.equals(r4, r5)     // Catch:{ Exception -> 0x01ef }
            if (r4 != 0) goto L_0x007f
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r20)     // Catch:{ Exception -> 0x01ef }
            r2 = 1
        L_0x007f:
            if (r2 != 0) goto L_0x008b
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ Exception -> 0x01ef }
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)     // Catch:{ Exception -> 0x01ef }
            int r1 = r1 + r0
            goto L_0x008c
        L_0x008b:
            r1 = 0
        L_0x008c:
            fm.liveswitch.CoreTransport[] r0 = new fm.liveswitch.CoreTransport[r3]     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.CoreTransport[] r12 = new fm.liveswitch.CoreTransport[r1]     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.Stream[] r1 = r19.getStreams()     // Catch:{ Exception -> 0x01ef }
            int r2 = r1.length     // Catch:{ Exception -> 0x01ef }
            r3 = 0
            r13 = 0
        L_0x0097:
            if (r3 >= r2) goto L_0x00aa
            r4 = r1[r3]     // Catch:{ Exception -> 0x01ef }
            if (r13 != 0) goto L_0x00a6
            boolean r4 = r4.getUseDtls()     // Catch:{ Exception -> 0x01ef }
            if (r4 == 0) goto L_0x00a4
            goto L_0x00a6
        L_0x00a4:
            r13 = 0
            goto L_0x00a7
        L_0x00a6:
            r13 = 1
        L_0x00a7:
            int r3 = r3 + 1
            goto L_0x0097
        L_0x00aa:
            r14 = 0
            r1 = r14
            r2 = r1
            r3 = r2
            r15 = 0
        L_0x00af:
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)     // Catch:{ Exception -> 0x01ef }
            if (r15 >= r4) goto L_0x00f5
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r12)     // Catch:{ Exception -> 0x01ef }
            int r3 = r3 - r11
            if (r15 <= r3) goto L_0x00bf
            r16 = 1
            goto L_0x00c1
        L_0x00bf:
            r16 = 0
        L_0x00c1:
            fm.liveswitch.Holder r8 = new fm.liveswitch.Holder     // Catch:{ Exception -> 0x01ef }
            r8.<init>(r1)     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.Holder r7 = new fm.liveswitch.Holder     // Catch:{ Exception -> 0x01ef }
            r7.<init>(r2)     // Catch:{ Exception -> 0x01ef }
            r2 = 0
            r3 = 0
            r1 = r19
            r4 = r8
            r5 = r7
            r6 = r16
            r17 = r7
            r7 = r13
            r18 = r8
            r8 = r15
            fm.liveswitch.Error r3 = r1.buildCoreTransport(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x01ef }
            java.lang.Object r1 = r18.getValue()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.CoreTransport r1 = (fm.liveswitch.CoreTransport) r1     // Catch:{ Exception -> 0x01ef }
            java.lang.Object r2 = r17.getValue()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.CoreTransport r2 = (fm.liveswitch.CoreTransport) r2     // Catch:{ Exception -> 0x01ef }
            if (r3 == 0) goto L_0x00ec
            return r3
        L_0x00ec:
            r0[r15] = r1     // Catch:{ Exception -> 0x01ef }
            if (r16 != 0) goto L_0x00f2
            r12[r15] = r2     // Catch:{ Exception -> 0x01ef }
        L_0x00f2:
            int r15 = r15 + 1
            goto L_0x00af
        L_0x00f5:
            r1 = -1
            r2 = 0
            r4 = 0
        L_0x00f8:
            fm.liveswitch.Stream[] r5 = r19.getStreams()     // Catch:{ Exception -> 0x01ef }
            int r5 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r5)     // Catch:{ Exception -> 0x01ef }
            if (r2 >= r5) goto L_0x01e1
            fm.liveswitch.Stream[] r5 = r19.getStreams()     // Catch:{ Exception -> 0x01ef }
            r5 = r5[r2]     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.BundlePolicy r6 = super.getBundlePolicy()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.BundlePolicy r7 = fm.liveswitch.BundlePolicy.Disabled     // Catch:{ Exception -> 0x01ef }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ Exception -> 0x01ef }
            if (r6 != 0) goto L_0x0174
            fm.liveswitch.BundlePolicy r6 = super.getBundlePolicy()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.BundlePolicy r7 = fm.liveswitch.BundlePolicy.Balanced     // Catch:{ Exception -> 0x01ef }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ Exception -> 0x01ef }
            if (r6 == 0) goto L_0x0121
            goto L_0x0174
        L_0x0121:
            fm.liveswitch.BundlePolicy r6 = super.getBundlePolicy()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.BundlePolicy r7 = fm.liveswitch.BundlePolicy.MaxCompatibility     // Catch:{ Exception -> 0x01ef }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ Exception -> 0x01ef }
            if (r6 == 0) goto L_0x0150
            if (r20 == 0) goto L_0x014d
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r20)     // Catch:{ Exception -> 0x01ef }
            if (r1 <= 0) goto L_0x014d
            r1 = r20[r10]     // Catch:{ Exception -> 0x01ef }
            java.lang.String[] r1 = r1.getMediaStreamIdentifiers()     // Catch:{ Exception -> 0x01ef }
            java.lang.String r4 = r5.getMediaStreamIdentification()     // Catch:{ Exception -> 0x01ef }
            r1 = r1[r10]     // Catch:{ Exception -> 0x01ef }
            boolean r1 = fm.liveswitch.Global.equals(r4, r1)     // Catch:{ Exception -> 0x01ef }
            if (r1 != 0) goto L_0x014a
            r5.setBundled(r11)     // Catch:{ Exception -> 0x01ef }
        L_0x014a:
            r1 = 0
            r4 = 0
            goto L_0x0176
        L_0x014d:
            r4 = r2
        L_0x014e:
            r1 = 0
            goto L_0x0176
        L_0x0150:
            fm.liveswitch.BundlePolicy r6 = super.getBundlePolicy()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.BundlePolicy r7 = fm.liveswitch.BundlePolicy.MaxBundle     // Catch:{ Exception -> 0x01ef }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ Exception -> 0x01ef }
            if (r6 == 0) goto L_0x0176
            if (r20 == 0) goto L_0x014e
            r1 = r20[r10]     // Catch:{ Exception -> 0x01ef }
            java.lang.String[] r1 = r1.getMediaStreamIdentifiers()     // Catch:{ Exception -> 0x01ef }
            java.lang.String r6 = r5.getMediaStreamIdentification()     // Catch:{ Exception -> 0x01ef }
            r1 = r1[r10]     // Catch:{ Exception -> 0x01ef }
            boolean r1 = fm.liveswitch.Global.equals(r6, r1)     // Catch:{ Exception -> 0x01ef }
            if (r1 != 0) goto L_0x014e
            r5.setBundled(r11)     // Catch:{ Exception -> 0x01ef }
            goto L_0x014e
        L_0x0174:
            r1 = r2
            r4 = r1
        L_0x0176:
            r6 = r0[r4]     // Catch:{ Exception -> 0x01ef }
            int r7 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r12)     // Catch:{ Exception -> 0x01ef }
            if (r4 >= r7) goto L_0x0181
            r7 = r12[r4]     // Catch:{ Exception -> 0x01ef }
            goto L_0x0182
        L_0x0181:
            r7 = r14
        L_0x0182:
            fm.liveswitch.StreamType r8 = r5.getType()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.StreamType r13 = fm.liveswitch.StreamType.Audio     // Catch:{ Exception -> 0x01ef }
            boolean r8 = fm.liveswitch.Global.equals(r8, r13)     // Catch:{ Exception -> 0x01ef }
            if (r8 != 0) goto L_0x01b3
            fm.liveswitch.StreamType r8 = r5.getType()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.StreamType r13 = fm.liveswitch.StreamType.Video     // Catch:{ Exception -> 0x01ef }
            boolean r8 = fm.liveswitch.Global.equals(r8, r13)     // Catch:{ Exception -> 0x01ef }
            if (r8 == 0) goto L_0x019b
            goto L_0x01b3
        L_0x019b:
            fm.liveswitch.StreamType r7 = r5.getType()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.StreamType r8 = fm.liveswitch.StreamType.Application     // Catch:{ Exception -> 0x01ef }
            boolean r7 = fm.liveswitch.Global.equals(r7, r8)     // Catch:{ Exception -> 0x01ef }
            if (r7 == 0) goto L_0x01b9
            r3 = r5
            fm.liveswitch.DataStream r3 = (fm.liveswitch.DataStream) r3     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.DataStream r3 = (fm.liveswitch.DataStream) r3     // Catch:{ Exception -> 0x01ef }
            r7 = r0[r1]     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.Error r3 = r9.buildDataStream(r3, r6, r7)     // Catch:{ Exception -> 0x01ef }
            goto L_0x01b9
        L_0x01b3:
            r3 = r0[r1]     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.Error r3 = r9.buildMediaStream(r5, r6, r7, r3)     // Catch:{ Exception -> 0x01ef }
        L_0x01b9:
            fm.liveswitch.EncryptionPolicy r6 = r5.getEncryptionPolicy()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.EncryptionPolicy r7 = fm.liveswitch.EncryptionPolicy.Disabled     // Catch:{ Exception -> 0x01ef }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ Exception -> 0x01ef }
            if (r6 != 0) goto L_0x01d7
            fm.liveswitch.DtlsParameters r6 = new fm.liveswitch.DtlsParameters     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.DtlsRole r7 = fm.liveswitch.DtlsRole.Auto     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.DtlsFingerprint[] r8 = r19.getLocalDtlsFingerprints()     // Catch:{ Exception -> 0x01ef }
            fm.liveswitch.DtlsRole r13 = r19.getPreferredDtlsRole()     // Catch:{ Exception -> 0x01ef }
            r6.<init>(r7, r8, r13)     // Catch:{ Exception -> 0x01ef }
            r5.setLocalDtlsParameters(r6)     // Catch:{ Exception -> 0x01ef }
        L_0x01d7:
            if (r3 == 0) goto L_0x01da
            return r3
        L_0x01da:
            r5.assignLocalParametersToCoreTransports()     // Catch:{ Exception -> 0x01ef }
            int r2 = r2 + 1
            goto L_0x00f8
        L_0x01e1:
            return r14
        L_0x01e2:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x01ef }
            java.lang.Exception r1 = new java.lang.Exception     // Catch:{ Exception -> 0x01ef }
            java.lang.String r2 = "Balanced Bundle Policy is not supported."
            r1.<init>(r2)     // Catch:{ Exception -> 0x01ef }
            r0.<init>(r1)     // Catch:{ Exception -> 0x01ef }
            throw r0     // Catch:{ Exception -> 0x01ef }
        L_0x01ef:
            r0 = move-exception
            fm.liveswitch.Error r1 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r2 = fm.liveswitch.ErrorCode.ConnectionInvalidArchitecture
            r1.<init>((fm.liveswitch.ErrorCode) r2, (java.lang.Exception) r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.doBuildStreamTransports(fm.liveswitch.BundleGroup[]):fm.liveswitch.Error");
    }

    private static void doConnectTo(final Promise<Object> promise, Connection connection, final Connection connection2) {
        connection.createOffer().then(new IAction1<SessionDescription>(connection) {
            final /* synthetic */ Connection val$offerConnection;

            {
                this.val$offerConnection = r1;
            }

            public void invoke(SessionDescription sessionDescription) {
                this.val$offerConnection.setLocalDescription(sessionDescription).then(new IAction1<SessionDescription>() {
                    public void invoke(SessionDescription sessionDescription) {
                        connection2.setRemoteDescription(sessionDescription).then(new IAction1<SessionDescription>() {
                            public void invoke(SessionDescription sessionDescription) {
                                connection2.createAnswer().then(new IAction1<SessionDescription>() {
                                    public void invoke(SessionDescription sessionDescription) {
                                        connection2.setLocalDescription(sessionDescription).then(new IAction1<SessionDescription>() {
                                            public void invoke(SessionDescription sessionDescription) {
                                                AnonymousClass35.this.val$offerConnection.setRemoteDescription(sessionDescription).then(new IAction1<SessionDescription>() {
                                                    public void invoke(SessionDescription sessionDescription) {
                                                        promise.resolve(null);
                                                    }
                                                }, (IAction1<Exception>) new IAction1<Exception>() {
                                                    public void invoke(Exception exc) {
                                                        promise.reject(exc);
                                                    }
                                                });
                                            }
                                        }, (IAction1<Exception>) new IAction1<Exception>() {
                                            public void invoke(Exception exc) {
                                                promise.reject(exc);
                                            }
                                        });
                                    }
                                }, (IAction1<Exception>) new IAction1<Exception>() {
                                    public void invoke(Exception exc) {
                                        promise.reject(exc);
                                    }
                                });
                            }
                        }, (IAction1<Exception>) new IAction1<Exception>() {
                            public void invoke(Exception exc) {
                                promise.reject(exc);
                            }
                        });
                    }
                }, (IAction1<Exception>) new IAction1<Exception>() {
                    public void invoke(Exception exc) {
                        promise.reject(exc);
                    }
                });
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                promise.reject(exc);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void doCreateAnswer(Promise<SessionDescription> promise) {
        synchronized (this._connectionLock) {
            if (super.getUseTrickleIce() || Global.equals(getGatheringState(), IceGatheringState.Complete)) {
                createAnswer(new SessionDescriptionRequirements(super.getTieBreaker(), super.getTrickleIcePolicy(), getMultiplexPolicy(), super.getBundlePolicy()), promise);
            } else {
                this.__promiseToBeResolved = promise;
                this.__createAnswer = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x009a, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean doCreateOffer(fm.liveswitch.Promise<fm.liveswitch.SessionDescription> r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7._connectionLock
            monitor-enter(r0)
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x009b }
            fm.liveswitch.ConnectionState r2 = fm.liveswitch.ConnectionState.New     // Catch:{ all -> 0x009b }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x009b }
            r2 = 0
            if (r1 == 0) goto L_0x0016
            fm.liveswitch.ConnectionState r1 = fm.liveswitch.ConnectionState.Initializing     // Catch:{ all -> 0x009b }
            super.setState(r1)     // Catch:{ all -> 0x009b }
            goto L_0x002e
        L_0x0016:
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x009b }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Connected     // Catch:{ all -> 0x009b }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x009b }
            if (r1 != 0) goto L_0x002e
            java.lang.Exception r1 = new java.lang.Exception     // Catch:{ all -> 0x009b }
            java.lang.String r3 = "SDP Renegotiation is only allowed when Connection is in the Connected state."
            r1.<init>(r3)     // Catch:{ all -> 0x009b }
            r8.reject(r1)     // Catch:{ all -> 0x009b }
            monitor-exit(r0)     // Catch:{ all -> 0x009b }
            return r2
        L_0x002e:
            fm.liveswitch.SessionDescriptionManager r1 = r7.__sessionDescriptionManager     // Catch:{ all -> 0x009b }
            fm.liveswitch.AudioStream[] r3 = r7.getAudioStreams()     // Catch:{ all -> 0x009b }
            fm.liveswitch.VideoStream[] r4 = r7.getVideoStreams()     // Catch:{ all -> 0x009b }
            fm.liveswitch.DataStream[] r5 = r7.getDataStreams()     // Catch:{ all -> 0x009b }
            r1.setMediaStreamIdentifications(r3, r4, r5)     // Catch:{ all -> 0x009b }
            java.util.ArrayList<fm.liveswitch.CoreTransport> r1 = r7.__primaryCoreTransports     // Catch:{ all -> 0x009b }
            int r1 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x009b }
            r3 = 0
            r4 = 1
            if (r1 != 0) goto L_0x004e
            boolean r1 = r7.prepareTransports(r3)     // Catch:{ all -> 0x009b }
            goto L_0x004f
        L_0x004e:
            r1 = 1
        L_0x004f:
            if (r1 != 0) goto L_0x0063
            fm.liveswitch.Error r1 = super.getError()     // Catch:{ all -> 0x009b }
            java.lang.Exception r1 = r1.getException()     // Catch:{ all -> 0x009b }
            r8.reject(r1)     // Catch:{ all -> 0x009b }
            r7.__promiseToBeResolved = r3     // Catch:{ all -> 0x009b }
            r7.__createOffer = r2     // Catch:{ all -> 0x009b }
            r7.__createAnswer = r2     // Catch:{ all -> 0x009b }
            goto L_0x0099
        L_0x0063:
            boolean r1 = super.getUseTrickleIce()     // Catch:{ all -> 0x009b }
            if (r1 != 0) goto L_0x007b
            fm.liveswitch.IceGatheringState r1 = r7.getGatheringState()     // Catch:{ all -> 0x009b }
            fm.liveswitch.IceGatheringState r5 = fm.liveswitch.IceGatheringState.Complete     // Catch:{ all -> 0x009b }
            boolean r1 = fm.liveswitch.Global.equals(r1, r5)     // Catch:{ all -> 0x009b }
            if (r1 == 0) goto L_0x0076
            goto L_0x007b
        L_0x0076:
            r7.__promiseToBeResolved = r8     // Catch:{ all -> 0x009b }
            r7.__createOffer = r4     // Catch:{ all -> 0x009b }
            goto L_0x0099
        L_0x007b:
            r7.__promiseToBeResolved = r3     // Catch:{ all -> 0x009b }
            r7.__createOffer = r2     // Catch:{ all -> 0x009b }
            r7.__createAnswer = r2     // Catch:{ all -> 0x009b }
            fm.liveswitch.SessionDescriptionRequirements r1 = new fm.liveswitch.SessionDescriptionRequirements     // Catch:{ all -> 0x009b }
            java.lang.String r2 = super.getTieBreaker()     // Catch:{ all -> 0x009b }
            fm.liveswitch.TrickleIcePolicy r3 = super.getTrickleIcePolicy()     // Catch:{ all -> 0x009b }
            fm.liveswitch.MultiplexPolicy r5 = r7.getMultiplexPolicy()     // Catch:{ all -> 0x009b }
            fm.liveswitch.BundlePolicy r6 = super.getBundlePolicy()     // Catch:{ all -> 0x009b }
            r1.<init>(r2, r3, r5, r6)     // Catch:{ all -> 0x009b }
            r7.createOffer(r1, r8)     // Catch:{ all -> 0x009b }
        L_0x0099:
            monitor-exit(r0)     // Catch:{ all -> 0x009b }
            return r4
        L_0x009b:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009b }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.doCreateOffer(fm.liveswitch.Promise):boolean");
    }

    /* access modifiers changed from: protected */
    public Error doProcessDescription(SessionDescription sessionDescription, boolean z) {
        String remoteDescriptionMediaId;
        BundleGroup[] bundleGroups;
        this.__sessionDescriptionManager.clear();
        if (!Global.equals(super.getBundlePolicy(), BundlePolicy.MaxBundle) || ((bundleGroups = sessionDescription.getSdpMessage().getBundleGroups()) != null && ArrayExtensions.getLength((Object[]) bundleGroups) == 1)) {
            Error doProcessDescription = super.doProcessDescription(sessionDescription, z);
            if (doProcessDescription != null) {
                return doProcessDescription;
            }
            for (Candidate addRemoteCandidate : this.__sessionDescriptionManager.getRemoteCandidates()) {
                super.addRemoteCandidate(addRemoteCandidate);
            }
            for (Pair pair : this.__sessionDescriptionManager.getDisableSecondaryComponents()) {
                disableSecondaryComponent((Stream) pair.getItem1(), ((Integer) pair.getItem2()).intValue());
            }
            if (!sessionDescription.getIsOffer() || z) {
                Pair<Triple<CoreTransport, CoreTransport, IntegerHolder>[], Stream[]> reassignTransportsAndUpdateProperties = BundleDescriptionManager.reassignTransportsAndUpdateProperties(sessionDescription, getStreams(), z);
                for (Triple triple : (Triple[]) reassignTransportsAndUpdateProperties.getItem1()) {
                    disablePrimaryComponent((CoreTransport) triple.getItem1(), (CoreTransport) triple.getItem2(), ((IntegerHolder) triple.getItem3()).getValue());
                }
                for (Stream stream : reassignTransportsAndUpdateProperties.getItem2()) {
                    CoreTransport bundleCoreTransport = stream.getBundleCoreTransport();
                    CoreTransport coreTransportRtp = stream.getCoreTransportRtp();
                    if (bundleCoreTransport != null && !Global.equals(bundleCoreTransport.getId(), coreTransportRtp.getId())) {
                        IceTransport iceTransport = bundleCoreTransport.getIceTransport();
                        DtlsTransport dtlsTransport = bundleCoreTransport.getDtlsTransport();
                        Holder holder = new Holder(null);
                        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__streamsForIceTransport, iceTransport.getId(), holder);
                        ArrayList arrayList = (ArrayList) holder.getValue();
                        if (iceTransport != null && tryGetValue) {
                            arrayList.remove(stream);
                        }
                        Holder holder2 = new Holder(arrayList);
                        boolean tryGetValue2 = HashMapExtensions.tryGetValue(this.__streamsForDtlsTransport, dtlsTransport.getId(), holder2);
                        ArrayList arrayList2 = (ArrayList) holder2.getValue();
                        if (dtlsTransport != null && tryGetValue2) {
                            arrayList2.remove(stream);
                        }
                    }
                    stream.setBundleCoreTransport((CoreTransport) null);
                }
            }
            if (!sessionDescription.getRenegotiation() && !z) {
                HashMap hashMap = new HashMap();
                for (Stream tryCast : getStreams()) {
                    ISynchronizableStream iSynchronizableStream = (ISynchronizableStream) Global.tryCast(tryCast, ISynchronizableStream.class);
                    if (!(iSynchronizableStream == null || iSynchronizableStream.getOutputSynchronizationDisabled() || (remoteDescriptionMediaId = iSynchronizableStream.getRemoteDescriptionMediaId()) == null)) {
                        Holder holder3 = new Holder(null);
                        boolean tryGetValue3 = HashMapExtensions.tryGetValue(hashMap, remoteDescriptionMediaId, holder3);
                        ArrayList arrayList3 = (ArrayList) holder3.getValue();
                        if (!tryGetValue3) {
                            HashMap item = HashMapExtensions.getItem(hashMap);
                            ArrayList arrayList4 = new ArrayList();
                            HashMapExtensions.set(item, remoteDescriptionMediaId, arrayList4);
                            arrayList3 = arrayList4;
                        }
                        arrayList3.add(iSynchronizableStream);
                    }
                }
                for (String str : HashMapExtensions.getKeys(hashMap)) {
                    ArrayList arrayList5 = (ArrayList) HashMapExtensions.getItem(hashMap).get(str);
                    if (ArrayListExtensions.getCount(arrayList5) > 1) {
                        ISynchronizableStream iSynchronizableStream2 = (ISynchronizableStream) ArrayListExtensions.getItem(arrayList5).get(0);
                        if (!hasNullOutputs((Stream) Global.tryCast(iSynchronizableStream2, Stream.class))) {
                            doSynchronize(iSynchronizableStream2, (ISynchronizableStream[]) ArrayListExtensions.getRange(arrayList5, 1, ArrayListExtensions.getCount(arrayList5) - 1).toArray(new ISynchronizableStream[0]));
                        }
                    }
                }
            }
            return null;
        }
        throw new RuntimeException(new Exception(StringExtensions.concat("Local bundle policy is set to MaxBundle, but ", z ? ImagesContract.LOCAL : "remote", " description does not indicate support for bundling.")));
    }

    private void doProcessIceTransportClosed(IceTransport iceTransport) {
        if (super.getIsTerminatingOrTerminated() || iceTransport.getClosingShouldNotTriggerGlobalNonGracefulShutdown()) {
            if (iceTransport.getClosingShouldNotTriggerGlobalNonGracefulShutdown()) {
                HashMapExtensions.remove(this.__iceTransports, iceTransport.getId());
            }
            processIceTransportDown(iceTransport);
            return;
        }
        shutdownOnFailure(new Error(ErrorCode.ConnectionTransportClosed, new Exception("IceTransport shut down unexpectedly.")), TransportType.IceTransport, iceTransport.getId());
    }

    private void doProcessIceTransportConnected(IceTransport iceTransport) {
        TransportType transportType = TransportType.Unset;
        ErrorCode errorCode = ErrorCode.ConnectionInternalError;
        String str = null;
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__coreTransportForIceTransport, iceTransport.getId(), holder);
        CoreTransport coreTransport = (CoreTransport) holder.getValue();
        DtlsTransport dtlsTransport = (!tryGetValue || coreTransport == null) ? null : coreTransport.getDtlsTransport();
        if (dtlsTransport != null) {
            try {
                dtlsTransport.start();
            } catch (Exception e) {
                errorCode = ErrorCode.ConnectionTransportStartError;
                str = e.getMessage();
                transportType = TransportType.DtlsTransport;
            }
        } else {
            Holder holder2 = new Holder(null);
            boolean tryGetValue2 = HashMapExtensions.tryGetValue(this.__streamsForIceTransport, iceTransport.getId(), holder2);
            ArrayList arrayList = (ArrayList) holder2.getValue();
            if (tryGetValue2) {
                __log.debug(StringExtensions.format("ICE transport {0} connected. Starting remaining transports on associated streams.", (Object) iceTransport.getId()));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Stream stream = (Stream) it.next();
                    if (Global.equals(stream.getType(), StreamType.Audio) || Global.equals(stream.getType(), StreamType.Video)) {
                        CoreTransport coreTransportRtcp = stream.getCoreTransportRtcp();
                        CoreTransport coreTransportRtp = stream.getCoreTransportRtp();
                        if (coreTransportRtcp == null || ((Global.equals(coreTransportRtp.getIceTransport().getState(), IceTransportState.Connected) || Global.equals(coreTransportRtp.getIceTransport().getState(), IceTransportState.Disconnected)) && (Global.equals(coreTransportRtcp.getIceTransport().getState(), IceTransportState.Connected) || Global.equals(coreTransportRtcp.getIceTransport().getState(), IceTransportState.Disconnected)))) {
                            DataBuffer localCryptoKey = stream.getLocalCryptoKey();
                            DataBuffer remoteCryptoKey = stream.getRemoteCryptoKey();
                            DataBuffer localCryptoSalt = stream.getLocalCryptoSalt();
                            DataBuffer remoteCryptoSalt = stream.getRemoteCryptoSalt();
                            if (localCryptoKey != null && remoteCryptoKey != null && localCryptoSalt != null && remoteCryptoSalt != null) {
                                EncryptionMode[] encryptionModes = stream.getEncryptionModes();
                                startRtpTransport(stream, SrtpProtectionProfile.encryptionModeToProtectionProfileCode((encryptionModes == null || ArrayExtensions.getLength((Object[]) encryptionModes) <= 0) ? EncryptionMode.Null : encryptionModes[0]), localCryptoKey, localCryptoSalt, remoteCryptoKey, remoteCryptoSalt, coreTransportRtp.getReceiveBufferSize());
                            } else if (!Global.equals(stream.getEncryptionMode(), EncryptionMode.Null)) {
                                shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception(StringExtensions.format("SDES is in use for stream {0} of type {1}. But {2} attributes are missing.", stream.getId(), stream.getType().toString(), ((localCryptoKey == null || localCryptoSalt == null) && (remoteCryptoKey == null || remoteCryptoSalt == null)) ? "local and remote" : (localCryptoKey == null || localCryptoSalt == null) ? ImagesContract.LOCAL : "remote"))), TransportType.SrtpTransport, StringExtensions.empty);
                                return;
                            } else {
                                startRtpTransport(stream, 0, (DataBuffer) null, (DataBuffer) null, (DataBuffer) null, (DataBuffer) null, coreTransportRtp.getReceiveBufferSize());
                            }
                        }
                    } else if (Global.equals(stream.getType(), StreamType.Application)) {
                        shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception("Data streams must use DTLS, but no DTLS transport has been prepared.")), TransportType.DtlsTransport, StringExtensions.empty);
                        return;
                    } else {
                        shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception(StringExtensions.format("Unsupported data stream type {0}.", (Object) stream.getType().toString()))), TransportType.DtlsTransport, StringExtensions.empty);
                        return;
                    }
                }
            }
        }
        if (str != null) {
            shutdownOnFailure(new Error(errorCode, new Exception(str)), transportType, (Global.equals(transportType, TransportType.Unset) || dtlsTransport == null) ? StringExtensions.empty : dtlsTransport.getId());
        }
    }

    private void doProcessIceTransportDisconnected(IceTransport iceTransport) {
        if (!super.getIsTerminatingOrTerminated()) {
            __log.debug(StringExtensions.format("Connectivity currently not available with peer for connection {0}.", (Object) super.getId()));
        }
    }

    private void doProcessIceTransportReconnected(IceTransport iceTransport) {
        RtpTransport rtpTransport;
        RtpReceiver[] receivers;
        RtpReceiver[] receivers2;
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__streamsForIceTransport, iceTransport.getId(), holder);
        ArrayList arrayList = (ArrayList) holder.getValue();
        if (tryGetValue) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Stream stream = (Stream) it.next();
                int i = 0;
                if (Global.equals(stream.getType(), StreamType.Audio)) {
                    RtpTransport rtpTransport2 = ((AudioStream) stream).getRtpTransport();
                    if (!(rtpTransport2 == null || (receivers2 = rtpTransport2.getReceivers()) == null)) {
                        int length = receivers2.length;
                        while (i < length) {
                            receivers2[i].setFlushBuffer(true);
                            i++;
                        }
                    }
                } else if (!(!Global.equals(stream.getType(), StreamType.Video) || (rtpTransport = ((VideoStream) stream).getRtpTransport()) == null || (receivers = rtpTransport.getReceivers()) == null)) {
                    int length2 = receivers.length;
                    while (i < length2) {
                        receivers[i].setFlushBuffer(true);
                        i++;
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        if (fm.liveswitch.Global.equals(r5.getState(), fm.liveswitch.MediaTransportState.Started) == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        processTopTransportConnected();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0043, code lost:
        if (fm.liveswitch.Global.equals(r5.getState(), fm.liveswitch.MediaTransportState.Stopped) == false) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0045, code lost:
        processMediaTransportDown(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0053, code lost:
        if (fm.liveswitch.Global.equals(r5.getState(), fm.liveswitch.MediaTransportState.Failed) == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0055, code lost:
        shutdownOnFailure(new fm.liveswitch.Error(fm.liveswitch.ErrorCode.MediaTransportFailed, new java.lang.Exception("Media Transport failed.")), fm.liveswitch.TransportType.MediaTransport, r5.getId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean doProcessMediaTransportStateChange(fm.liveswitch.IMediaTransport r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4._connectionLock
            monitor-enter(r0)
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x0071 }
            fm.liveswitch.ConnectionState r2 = fm.liveswitch.ConnectionState.Closed     // Catch:{ all -> 0x0071 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0071 }
            if (r1 != 0) goto L_0x006e
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x0071 }
            fm.liveswitch.ConnectionState r2 = fm.liveswitch.ConnectionState.Failing     // Catch:{ all -> 0x0071 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0071 }
            if (r1 != 0) goto L_0x006e
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x0071 }
            fm.liveswitch.ConnectionState r2 = fm.liveswitch.ConnectionState.Failed     // Catch:{ all -> 0x0071 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0071 }
            if (r1 == 0) goto L_0x0028
            goto L_0x006e
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x0071 }
            fm.liveswitch.MediaTransportState r0 = r5.getState()
            fm.liveswitch.MediaTransportState r1 = fm.liveswitch.MediaTransportState.Started
            boolean r0 = fm.liveswitch.Global.equals(r0, r1)
            if (r0 == 0) goto L_0x0039
            r4.processTopTransportConnected()
            goto L_0x006c
        L_0x0039:
            fm.liveswitch.MediaTransportState r0 = r5.getState()
            fm.liveswitch.MediaTransportState r1 = fm.liveswitch.MediaTransportState.Stopped
            boolean r0 = fm.liveswitch.Global.equals(r0, r1)
            if (r0 == 0) goto L_0x0049
            r4.processMediaTransportDown(r5)
            goto L_0x006c
        L_0x0049:
            fm.liveswitch.MediaTransportState r0 = r5.getState()
            fm.liveswitch.MediaTransportState r1 = fm.liveswitch.MediaTransportState.Failed
            boolean r0 = fm.liveswitch.Global.equals(r0, r1)
            if (r0 == 0) goto L_0x006c
            fm.liveswitch.Error r0 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r1 = fm.liveswitch.ErrorCode.MediaTransportFailed
            java.lang.Exception r2 = new java.lang.Exception
            java.lang.String r3 = "Media Transport failed."
            r2.<init>(r3)
            r0.<init>((fm.liveswitch.ErrorCode) r1, (java.lang.Exception) r2)
            fm.liveswitch.TransportType r1 = fm.liveswitch.TransportType.MediaTransport
            java.lang.String r5 = r5.getId()
            r4.shutdownOnFailure(r0, r1, r5)
        L_0x006c:
            r5 = 1
            return r5
        L_0x006e:
            r5 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x0071 }
            return r5
        L_0x0071:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0071 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.doProcessMediaTransportStateChange(fm.liveswitch.IMediaTransport):boolean");
    }

    /* access modifiers changed from: protected */
    public void doSendCachedLocalCandidates() {
        Candidate[] candidateArr = (Candidate[]) this.__cachedLocalCandidates.toArray(new Candidate[0]);
        this.__cachedLocalCandidates.clear();
        for (Candidate raiseLocalCandidate : candidateArr) {
            raiseLocalCandidate(raiseLocalCandidate);
        }
    }

    /* access modifiers changed from: protected */
    public void doSetLocalDescription(Promise<SessionDescription> promise, SessionDescription sessionDescription) {
        try {
            synchronized (this._connectionLock) {
                if (sessionDescription.getIsOffer()) {
                    if (Global.equals(super.getSignallingState(), SignallingState.HaveRemoteOffer)) {
                        throw new RuntimeException(new Exception("Cannot set a local offer when we have a remote offer."));
                    }
                }
                processAndSetDescription(sessionDescription, true, promise);
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    public void doSetRemoteDescription(Promise<SessionDescription> promise, SessionDescription sessionDescription) {
        try {
            synchronized (this._connectionLock) {
                sessionDescription.setRenegotiation(this.__sessionDescriptionManager.getOriginalSignallingExchangeComplete());
                Pair<StreamDescription[], Object[]> updateMediaStreamIdentifications = this.__sessionDescriptionManager.updateMediaStreamIdentifications(sessionDescription, getAudioStreams(), getVideoStreams(), getDataStreams());
                boolean z = true;
                if (sessionDescription.getRenegotiation()) {
                    verifyRenegotiationIsValid(sessionDescription, getRemoteDescription());
                    registerStreams(processStreamUpdate(updateMediaStreamIdentifications, getOnRemoteAddStream(), getOnRemoteRemoveStream()), true);
                    unregisterStreams(updateMediaStreamIdentifications.getItem2());
                }
                BundleGroup[] extractBundleGroups = BundleDescriptionManager.extractBundleGroups(sessionDescription, getStreams());
                if ((extractBundleGroups == null || ArrayExtensions.getLength((Object[]) extractBundleGroups) == 0) && Global.equals(super.getBundlePolicy(), BundlePolicy.MaxBundle)) {
                    throw new RuntimeException(new Exception("Local bundle policy is set to MaxBundle, but remote description does not indicate support for bundling."));
                }
                if (Global.equals(super.getState(), ConnectionState.New)) {
                    super.setState(ConnectionState.Initializing);
                } else if (!Global.equals(super.getState(), ConnectionState.Connected) && (!Global.equals(super.getState(), ConnectionState.Initializing) || this.__sessionDescriptionManager.getRemoteDescription() != null)) {
                    promise.reject(new Exception("SDP Renegotiation is only allowed when Connection is in the Connected state."));
                    return;
                }
                if (!sessionDescription.getRenegotiation()) {
                    if (getRemoteDescription() != null) {
                        throw new RuntimeException(new Exception("Remote description is already set."));
                    }
                }
                if (sessionDescription.getIsOffer()) {
                    setIceRole(IceRole.Controlled);
                    if (ArrayListExtensions.getCount(this.__primaryCoreTransports) == 0) {
                        z = prepareTransports(extractBundleGroups);
                    }
                    if (!z) {
                        promise.reject(super.getError() == null ? null : super.getError().getException());
                    }
                }
                processAndSetDescription(sessionDescription, false, promise);
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    private boolean doStopGatherer(IceGatherer iceGatherer) {
        try {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__coreTransportsForGatherer, iceGatherer.getId(), holder);
            ArrayList arrayList = (ArrayList) holder.getValue();
            if (!tryGetValue || ArrayListExtensions.getCount(arrayList) <= 0) {
                __log.debug(StringExtensions.format("Shutting down gatherer {0}.", (Object) iceGatherer.getId()));
                iceGatherer.stop();
                return true;
            }
            __log.debug(StringExtensions.format("A request to stop gatherer {0} has been made; however, {1} transports still use it.", iceGatherer.getId(), IntegerExtensions.toString(Integer.valueOf(ArrayListExtensions.getCount(arrayList)))));
            return false;
        } catch (Exception e) {
            __log.error(StringExtensions.format("No Gatherer transport found for Core Transport while shutting down. Will attempt to close all remaining inner transports.", new Object[0]), e);
            shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, e), TransportType.Gatherer, iceGatherer.getId());
            return true;
        }
    }

    private void doSynchronize(ISynchronizableStream iSynchronizableStream, final ISynchronizableStream[] iSynchronizableStreamArr) {
        ISynchronizer[] synchronizers = iSynchronizableStream.getSynchronizers();
        if (synchronizers == null) {
            __log.warn(StringExtensions.format("Cannot synchronize master {0} stream {1} rendering. Stream outputs do not contain any synchronizers.", iSynchronizableStream.getType().toString(), iSynchronizableStream.getId()));
        } else {
            doSynchronize(synchronizers, getSlaveSynchronizers(iSynchronizableStreamArr));
        }
        iSynchronizableStream.addOnMasterSynchronizeContextReady(new IAction1<SynchronizeContext>() {
            public void invoke(SynchronizeContext synchronizeContext) {
                for (ISynchronizableStream synchronizeContext2 : iSynchronizableStreamArr) {
                    synchronizeContext2.setSynchronizeContext(synchronizeContext);
                }
            }
        });
        for (ISynchronizableStream synchronize : iSynchronizableStreamArr) {
            synchronize.synchronize(false);
        }
        iSynchronizableStream.synchronize(true);
    }

    private void doSynchronize(ISynchronizer[] iSynchronizerArr, ISynchronizer[] iSynchronizerArr2) {
        for (ISynchronizer activate : iSynchronizerArr) {
            activate.activate(true, iSynchronizerArr2);
        }
        for (ISynchronizer activate2 : iSynchronizerArr2) {
            activate2.activate(false, (ISynchronizer[]) null);
        }
    }

    /* access modifiers changed from: private */
    public void establishConnectionTimeout(ScheduledItem scheduledItem) {
        synchronized (this._connectionLock) {
            if (Global.equals(super.getState(), ConnectionState.Connecting) || Global.equals(super.getState(), ConnectionState.Initializing)) {
                shutdownOnFailure(new Error(ErrorCode.ConnectionNotEstablished, new Exception("Could not establish connectivity with remote peer.")), TransportType.Unset, "");
            }
        }
    }

    /* access modifiers changed from: private */
    public void finalise(ConnectionState connectionState, Error error) {
        super.setState(connectionState, error);
    }

    static Pair<Integer, Boolean> findCoreTransportIndex(BundleGroup[] bundleGroupArr, String str) {
        boolean z = false;
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) bundleGroupArr); i++) {
            BundleGroup bundleGroup = bundleGroupArr[i];
            for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) bundleGroup.getMediaStreamIdentifiers()); i2++) {
                if (Global.equals(bundleGroup.getMediaStreamIdentifiers()[i2], str)) {
                    Integer valueOf = Integer.valueOf(i);
                    if (i2 != 0) {
                        z = true;
                    }
                    return new Pair<>(valueOf, Boolean.valueOf(z));
                }
            }
        }
        return null;
    }

    private CoreTransport findExistingCoreTransportForBundling() {
        for (Stream stream : (Stream[]) this.__streams.getValues()) {
            if (!stream.getBundled()) {
                return stream.getCoreTransportRtp();
            }
        }
        return null;
    }

    private IceParameters generateIceParameters() {
        return new IceParameters(StringExtensions.substring(Utility.generateId(), 0, 8), Utility.generateId());
    }

    /* access modifiers changed from: package-private */
    public boolean getActiveIceKeepAliveEnabled() {
        return this.__activeIceKeepAliveEnabled;
    }

    public AudioStream[] getAudioStreams() {
        ArrayList arrayList = new ArrayList();
        for (Stream stream : getStreams()) {
            if (Global.equals(stream.getType(), StreamType.Audio)) {
                arrayList.add((AudioStream) stream);
            }
        }
        return (AudioStream[]) arrayList.toArray(new AudioStream[0]);
    }

    public IFunction1<DatagramSocketCreateArgs, DatagramSocket> getCreateDatagramSocket() {
        return this._createDatagramSocket;
    }

    public IFunction1<StreamSocketCreateArgs, StreamSocket> getCreateStreamSocket() {
        return this._createStreamSocket;
    }

    public DataStream[] getDataStreams() {
        ArrayList arrayList = new ArrayList();
        for (Stream stream : getStreams()) {
            if (Global.equals(stream.getType(), StreamType.Application)) {
                arrayList.add((DataStream) stream);
            }
        }
        return (DataStream[]) arrayList.toArray(new DataStream[0]);
    }

    public static DtlsCertificate getDefaultLocalDtlsCertificate() {
        DtlsCertificate[] defaultLocalDtlsCertificates = getDefaultLocalDtlsCertificates();
        if (defaultLocalDtlsCertificates == null || ArrayExtensions.getLength((Object[]) defaultLocalDtlsCertificates) <= 0) {
            return null;
        }
        return getDefaultLocalDtlsCertificates()[0];
    }

    public static DtlsCertificate[] getDefaultLocalDtlsCertificates() {
        return _defaultLocalDtlsCertificates;
    }

    public DtlsCipherSuite[] getDtlsCipherSuites() {
        return this.__cipherSuites;
    }

    public DtlsProtocolVersion getDtlsClientVersion() {
        return this.__dtlsClientVersion;
    }

    public DtlsProtocolVersion getDtlsServerMaxVersion() {
        return this.__dtlsServerMaxVersion;
    }

    public DtlsProtocolVersion getDtlsServerMinVersion() {
        return this.__dtlsServerMinVersion;
    }

    public IceGatheringState getGatheringState() {
        return this.__gatheringState;
    }

    /* access modifiers changed from: package-private */
    public IFunction0<Long> getGetInboundRtcpTransportSystemTimestamp() {
        return this._getInboundRtcpTransportSystemTimestamp;
    }

    /* access modifiers changed from: package-private */
    public IFunction0<Long> getGetInboundRtpTransportSystemTimestamp() {
        return this._getInboundRtpTransportSystemTimestamp;
    }

    /* access modifiers changed from: package-private */
    public IFunction0<Long> getGetOutboundRtcpTransportSystemTimestamp() {
        return this._getOutboundRtcpTransportSystemTimestamp;
    }

    /* access modifiers changed from: package-private */
    public boolean getHexDumpEnabled() {
        return this._hexDumpEnabled;
    }

    /* access modifiers changed from: package-private */
    public String getHexDumpPath() {
        return this._hexDumpPath;
    }

    public AddressType[] getIceAddressTypes() {
        return this._iceAddressTypes;
    }

    public IceConnectionState getIceConnectionState() {
        return this.__iceConnectionState;
    }

    public IcePolicy getIcePolicy() {
        return this.__icePolicy;
    }

    public IcePortRange getIcePortRange() {
        return this._icePortRange;
    }

    public IceRole getIceRole() {
        return this._iceRole;
    }

    private IceTransportOptions getIceTransportOptions() {
        return new IceTransportOptions(IceKeepAlivePolicy.All, super.getDeadStreamTimeout(), getKeepAliveInterval());
    }

    public int getKeepAliveInterval() {
        return this.__keepAliveInterval;
    }

    public SessionDescription getLocalDescription() {
        SessionDescriptionManager sessionDescriptionManager = this.__sessionDescriptionManager;
        if (sessionDescriptionManager != null) {
            return sessionDescriptionManager.getLocalDescription();
        }
        return null;
    }

    public DtlsCertificate getLocalDtlsCertificate() {
        DtlsCertificate[] localDtlsCertificates = getLocalDtlsCertificates();
        if (ArrayExtensions.getLength((Object[]) localDtlsCertificates) == 0) {
            return null;
        }
        return localDtlsCertificates[0];
    }

    public DtlsCertificate[] getLocalDtlsCertificates() {
        DtlsCertificate[] dtlsCertificateArr;
        synchronized (this.__dtlsCertificatesLock) {
            if (this.__localDtlsCertificates == null) {
                if (__log.getIsInfoEnabled()) {
                    __log.info(StringExtensions.format("Generating {0} certificate for connection {1}...", StringExtensions.toUpper(DtlsCertificate.getDefaultKeyType().toString()), super.getId()));
                }
                long timestamp = ManagedStopwatch.getTimestamp();
                this.__localDtlsCertificates = new DtlsCertificate[]{DtlsCertificate.generateCertificate()};
                if (__log.getIsInfoEnabled()) {
                    __log.info(StringExtensions.format("{0} certificate generated in {1}ms for connection {2}.", StringExtensions.toUpper(DtlsCertificate.getDefaultKeyType().toString()), IntegerExtensions.toString(Integer.valueOf((int) ((ManagedStopwatch.getTimestamp() - timestamp) / ((long) Constants.getTicksPerMillisecond())))), super.getId()));
                }
            }
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) this.__localDtlsCertificates); i++) {
                if (this.__localDtlsCertificates[i].getIsExpiring() && this.__localDtlsCertificates[i].getAutoRegenerate()) {
                    __log.info(StringExtensions.format("Regenerating certificate for connection {0}.", (Object) super.getId()));
                    this.__localDtlsCertificates[i].regenerate();
                }
            }
            dtlsCertificateArr = this.__localDtlsCertificates;
        }
        return dtlsCertificateArr;
    }

    /* access modifiers changed from: package-private */
    public DtlsFingerprint[] getLocalDtlsFingerprints() {
        DtlsFingerprint[] dtlsFingerprintArr;
        synchronized (this.__localDtlsFingerprintLock) {
            if (this.__localDtlsFingerprints == null) {
                DtlsCertificate[] localDtlsCertificates = getLocalDtlsCertificates();
                String sha256Algorithm = Fingerprint.getSha256Algorithm();
                if (localDtlsCertificates != null && ArrayExtensions.getLength((Object[]) localDtlsCertificates) > 0) {
                    this.__localDtlsFingerprints = new DtlsFingerprint[ArrayExtensions.getLength((Object[]) localDtlsCertificates)];
                    for (int i = 0; i < ArrayExtensions.getLength((Object[]) localDtlsCertificates); i++) {
                        this.__localDtlsFingerprints[i] = (DtlsFingerprint) localDtlsCertificates[i].calculateFingerprint(sha256Algorithm);
                    }
                }
            }
            dtlsFingerprintArr = this.__localDtlsFingerprints;
        }
        return dtlsFingerprintArr;
    }

    public IceParameters getLocalIceParameters() {
        IceParameters iceParameters;
        synchronized (this._connectionLock) {
            if (this.__localIceParameters == null) {
                this.__localIceParameters = generateIceParameters();
            }
            iceParameters = this.__localIceParameters;
        }
        return iceParameters;
    }

    public MultiplexPolicy getMultiplexPolicy() {
        return this._multiplexPolicy;
    }

    public IFunction1<StreamDescription, Stream> getOnRemoteAddStream() {
        return this._onRemoteAddStream;
    }

    public IAction1<Stream> getOnRemoteRemoveStream() {
        return this._onRemoteRemoveStream;
    }

    public DtlsRole getPreferredDtlsRole() {
        return this.__preferredDtlsRole;
    }

    public String getPrivateIPAddress() {
        String[] privateIPAddresses = getPrivateIPAddresses();
        if (privateIPAddresses == null || ArrayExtensions.getLength((Object[]) privateIPAddresses) <= 0) {
            return null;
        }
        return privateIPAddresses[0];
    }

    public String[] getPrivateIPAddresses() {
        return this._privateIPAddresses;
    }

    public String getPublicIPAddress() {
        String[] publicIPAddresses = getPublicIPAddresses();
        if (publicIPAddresses == null || ArrayExtensions.getLength((Object[]) publicIPAddresses) <= 0) {
            return null;
        }
        return publicIPAddresses[0];
    }

    public String[] getPublicIPAddresses() {
        return this._publicIPAddresses;
    }

    public SessionDescription getRemoteDescription() {
        SessionDescriptionManager sessionDescriptionManager = this.__sessionDescriptionManager;
        if (sessionDescriptionManager != null) {
            return sessionDescriptionManager.getRemoteDescription();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public SessionDescriptionManagerBase<Stream, AudioStream, VideoStream, DataStream, DataChannel> getSessionDescriptionManager() {
        if (this.__sessionDescriptionManager == null) {
            this.__sessionDescriptionManager = new SessionDescriptionManager();
        }
        return this.__sessionDescriptionManager;
    }

    private ISynchronizer[] getSlaveSynchronizers(ISynchronizableStream[] iSynchronizableStreamArr) {
        ArrayList arrayList = new ArrayList();
        for (ISynchronizableStream iSynchronizableStream : iSynchronizableStreamArr) {
            if (!hasNullOutputs((Stream) Global.tryCast(iSynchronizableStream, Stream.class))) {
                ISynchronizer[] synchronizers = iSynchronizableStream.getSynchronizers();
                if (synchronizers == null || ArrayExtensions.getLength((Object[]) synchronizers) == 0) {
                    __log.warn(StringExtensions.format("Cannot synchronize slave {0} stream {1} rendering. Stream outputs do not contain any synchronizers.", iSynchronizableStream.getType().toString(), iSynchronizableStream.getId()));
                } else {
                    ArrayListExtensions.addRange(arrayList, (T[]) synchronizers);
                }
            }
        }
        return (ISynchronizer[]) arrayList.toArray(new ISynchronizer[0]);
    }

    public Future<ConnectionStats> getStats() {
        Promise promise = new Promise();
        try {
            ConnectionStats connectionStats = new ConnectionStats();
            connectionStats.setId(super.getId());
            connectionStats.setExternalId(super.getExternalId());
            connectionStats.setState(super.getState());
            connectionStats.setTimestamp(DateExtensions.getUtcNow());
            ArrayList arrayList = new ArrayList();
            for (Stream stream : getStreams()) {
                if (Global.equals(stream.getType(), StreamType.Audio)) {
                    arrayList.add(((AudioStream) stream).getStats());
                } else if (Global.equals(stream.getType(), StreamType.Video)) {
                    arrayList.add(((VideoStream) stream).getStats());
                } else {
                    connectionStats.setDataStream(((DataStream) stream).getStats());
                }
            }
            connectionStats.setMediaStreams((MediaStreamStats[]) arrayList.toArray(new MediaStreamStats[0]));
            promise.resolve(connectionStats);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    public <TStream extends Stream> TStream getStreamByType(StreamType streamType) {
        return this.__streams.getByType(streamType);
    }

    public Stream[] getStreams() {
        return (Stream[]) this.__streams.getValues();
    }

    public <TStream extends Stream> ArrayList<TStream> getStreamsByType(StreamType streamType) {
        return this.__streams.getManyByType(streamType);
    }

    public int getStunBindingRequestLimit() {
        return this._stunBindingRequestLimit;
    }

    public int getStunRequestTimeout() {
        return this._stunRequestTimeout;
    }

    public boolean getSynchronizeMediaStreams() {
        __log.warn("Getting the value of SynchronizeMediaStreams is deprecated. Use MediaStream.OutputSynchronizationDisabled instead.");
        for (Stream tryCast : getStreams()) {
            ISynchronizableStream iSynchronizableStream = (ISynchronizableStream) Global.tryCast(tryCast, ISynchronizableStream.class);
            if (iSynchronizableStream != null && !iSynchronizableStream.getOutputSynchronizationDisabled()) {
                return true;
            }
        }
        return false;
    }

    public int getTcpConnectTimeout() {
        return this._tcpConnectTimeout;
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

    public int getTurnAllocateRequestLimit() {
        return this._turnAllocateRequestLimit;
    }

    public VideoStream[] getVideoStreams() {
        ArrayList arrayList = new ArrayList();
        for (Stream stream : getStreams()) {
            if (Global.equals(stream.getType(), StreamType.Video)) {
                arrayList.add((VideoStream) stream);
            }
        }
        return (VideoStream[]) arrayList.toArray(new VideoStream[0]);
    }

    private boolean hasNullOutputs(Stream stream) {
        if (Global.equals(stream.getType(), StreamType.Audio)) {
            if (((AudioStream) Global.tryCast(stream, AudioStream.class)).getOutputs() == null || ArrayExtensions.getLength((Object[]) ((AudioStream) Global.tryCast(stream, AudioStream.class)).getOutputs()) == 0) {
                return true;
            }
            return false;
        } else if (!Global.equals(stream.getType(), StreamType.Video)) {
            throw new RuntimeException(new Exception("Cannot test whether a stream other than of type Audio and Video has null outputs"));
        } else if (((VideoStream) Global.tryCast(stream, VideoStream.class)).getOutputs() == null || ArrayExtensions.getLength((Object[]) ((VideoStream) Global.tryCast(stream, VideoStream.class)).getOutputs()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void initialiseDtls(IceTransport iceTransport) {
        DtlsParameters dtlsParameters;
        TransportType transportType = TransportType.Unset;
        ErrorCode errorCode = ErrorCode.ConnectionInternalError;
        String str = null;
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__coreTransportForIceTransport, iceTransport.getId(), holder);
        CoreTransport coreTransport = (CoreTransport) holder.getValue();
        DtlsTransport dtlsTransport = (!tryGetValue || coreTransport == null) ? null : coreTransport.getDtlsTransport();
        if (dtlsTransport != null) {
            Holder holder2 = new Holder(null);
            boolean tryGetValue2 = HashMapExtensions.tryGetValue(this.__streamsForIceTransport, iceTransport.getId(), holder2);
            ArrayList arrayList = (ArrayList) holder2.getValue();
            if (!tryGetValue2 || ArrayListExtensions.getCount(arrayList) < 1) {
                str = StringExtensions.format("Core transport {0} cannot start because there are no streams associated with core transport.", (Object) coreTransport.getId());
                dtlsParameters = null;
            } else {
                dtlsParameters = ((Stream) ArrayListExtensions.getItem(arrayList).get(0)).getRemoteDtlsParameters();
                if (dtlsParameters == null) {
                    transportType = TransportType.DtlsTransport;
                    str = "Remote DTLS parameters are not set.";
                }
            }
            if (str == null) {
                try {
                    dtlsTransport.setRemoteParameters(dtlsParameters);
                    if (Global.equals(dtlsTransport.getLocalParameters().getRole(), DtlsRole.Server)) {
                        dtlsTransport.start();
                    }
                } catch (Exception e) {
                    errorCode = ErrorCode.ConnectionTransportStartError;
                    str = e.getMessage();
                    transportType = TransportType.DtlsTransport;
                }
            }
        }
        if (str != null) {
            shutdownOnFailure(new Error(errorCode, new Exception(str)), transportType, (Global.equals(transportType, TransportType.Unset) || dtlsTransport == null) ? StringExtensions.empty : dtlsTransport.getId());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0091, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean initialiseInternalTransports(fm.liveswitch.BundleGroup[] r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5._connectionLock
            monitor-enter(r0)
            fm.liveswitch.Error r6 = r5.buildStreamTransports(r6)     // Catch:{ all -> 0x0099 }
            r1 = 0
            if (r6 == 0) goto L_0x0040
            fm.liveswitch.ConnectionState r2 = super.getState()     // Catch:{ all -> 0x0099 }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Closed     // Catch:{ all -> 0x0099 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x0099 }
            if (r2 != 0) goto L_0x0040
            fm.liveswitch.ConnectionState r2 = super.getState()     // Catch:{ all -> 0x0099 }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Closing     // Catch:{ all -> 0x0099 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x0099 }
            if (r2 != 0) goto L_0x0040
            fm.liveswitch.ConnectionState r2 = fm.liveswitch.ConnectionState.Failing     // Catch:{ all -> 0x0099 }
            boolean r2 = r5.setState(r2, r6)     // Catch:{ all -> 0x0099 }
            if (r2 == 0) goto L_0x003e
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x0099 }
            java.lang.String r3 = "Connection: internal transports could not be initialised: {0}"
            java.lang.String r6 = r6.getDescription()     // Catch:{ all -> 0x0099 }
            java.lang.String r6 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r6)     // Catch:{ all -> 0x0099 }
            r2.error(r6)     // Catch:{ all -> 0x0099 }
            fm.liveswitch.ConnectionState r6 = fm.liveswitch.ConnectionState.Failed     // Catch:{ all -> 0x0099 }
            super.setState(r6)     // Catch:{ all -> 0x0099 }
        L_0x003e:
            monitor-exit(r0)     // Catch:{ all -> 0x0099 }
            return r1
        L_0x0040:
            if (r6 == 0) goto L_0x0044
            monitor-exit(r0)     // Catch:{ all -> 0x0099 }
            return r1
        L_0x0044:
            java.util.ArrayList<fm.liveswitch.CoreTransport> r6 = r5.__primaryCoreTransports     // Catch:{ all -> 0x0099 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x0099 }
        L_0x004a:
            boolean r2 = r6.hasNext()     // Catch:{ all -> 0x0099 }
            if (r2 == 0) goto L_0x0096
            java.lang.Object r2 = r6.next()     // Catch:{ all -> 0x0099 }
            fm.liveswitch.CoreTransport r2 = (fm.liveswitch.CoreTransport) r2     // Catch:{ all -> 0x0099 }
            fm.liveswitch.Error r2 = r5.commenceGathering(r2)     // Catch:{ all -> 0x0099 }
            if (r2 == 0) goto L_0x0092
            fm.liveswitch.ConnectionState r3 = super.getState()     // Catch:{ all -> 0x0099 }
            fm.liveswitch.ConnectionState r4 = fm.liveswitch.ConnectionState.Closed     // Catch:{ all -> 0x0099 }
            boolean r3 = fm.liveswitch.Global.equals(r3, r4)     // Catch:{ all -> 0x0099 }
            if (r3 != 0) goto L_0x0092
            fm.liveswitch.ConnectionState r3 = super.getState()     // Catch:{ all -> 0x0099 }
            fm.liveswitch.ConnectionState r4 = fm.liveswitch.ConnectionState.Closing     // Catch:{ all -> 0x0099 }
            boolean r3 = fm.liveswitch.Global.equals(r3, r4)     // Catch:{ all -> 0x0099 }
            if (r3 != 0) goto L_0x0092
            fm.liveswitch.ConnectionState r6 = fm.liveswitch.ConnectionState.Failing     // Catch:{ all -> 0x0099 }
            boolean r6 = r5.setState(r6, r2)     // Catch:{ all -> 0x0099 }
            if (r6 == 0) goto L_0x0090
            fm.liveswitch.ILog r6 = __log     // Catch:{ all -> 0x0099 }
            java.lang.String r3 = "Cannot start gathering: {0}"
            java.lang.String r2 = r2.getDescription()     // Catch:{ all -> 0x0099 }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r2)     // Catch:{ all -> 0x0099 }
            r6.error(r2)     // Catch:{ all -> 0x0099 }
            fm.liveswitch.ConnectionState r6 = fm.liveswitch.ConnectionState.Failed     // Catch:{ all -> 0x0099 }
            super.setState(r6)     // Catch:{ all -> 0x0099 }
        L_0x0090:
            monitor-exit(r0)     // Catch:{ all -> 0x0099 }
            return r1
        L_0x0092:
            if (r2 == 0) goto L_0x004a
            monitor-exit(r0)     // Catch:{ all -> 0x0099 }
            return r1
        L_0x0096:
            r6 = 1
            monitor-exit(r0)     // Catch:{ all -> 0x0099 }
            return r6
        L_0x0099:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0099 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.initialiseInternalTransports(fm.liveswitch.BundleGroup[]):boolean");
    }

    private void initialize(Stream[] streamArr) {
        this.__logRTT = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
            public String getId() {
                return "fm.liveswitch.Connection.logRTT";
            }

            public void invoke(ScheduledItem scheduledItem) {
                Connection.this.logRTT(scheduledItem);
            }
        }, 0, 60000, ScheduledItem.getUnset(), ScheduledItem.getUnset());
        this.__localDtlsCertificates = getDefaultLocalDtlsCertificates();
        this.__scheduler = new Scheduler(this._connectionLock);
        setMultiplexPolicy(MultiplexPolicy.Required);
        setIcePortRange(new IcePortRange());
        setIceAddressTypes(new AddressType[]{AddressType.IPv4, AddressType.IPv6});
        setTcpConnectTimeout(1000);
        setTurnAllocateRequestLimit(5);
        setStunBindingRequestLimit(5);
        setStunRequestTimeout(2000);
        setIceRole(IceRole.Controlling);
        byte[] bArr = new byte[8];
        LockedRandomizer.nextBytes(bArr);
        this.__iceTieBreaker = Binary.fromBytes64(bArr, 0, false);
        registerStreams(streamArr, false);
    }

    private void logIceTransportRtt(IceTransport iceTransport, StreamType streamType, boolean z) {
        if (iceTransport != null) {
            int smoothedRoundTripTime = iceTransport.getSmoothedRoundTripTime();
            String id = iceTransport.getId();
            String str = z ? "primary" : "secondary";
            String str2 = Global.equals(streamType, StreamType.Audio) ? "Audio" : Global.equals(streamType, StreamType.Application) ? "Data" : Global.equals(streamType, StreamType.Video) ? "Video" : "Undefined";
            if (smoothedRoundTripTime != -1) {
                __log.debug(StringExtensions.format("Round Trip Time on the {3} IceTransport {0} for {2} stream is {1} ms.", new Object[]{id, IntegerExtensions.toString(Integer.valueOf(smoothedRoundTripTime)), str2, str}));
                int smoothedRelayServerRoundTripTime = iceTransport.getSmoothedRelayServerRoundTripTime();
                if (smoothedRelayServerRoundTripTime != -1) {
                    __log.debug(StringExtensions.format("Round Trip Time to the relay server on the {3} IceTransport {0} for {2} stream is {1} ms.", new Object[]{id, IntegerExtensions.toString(Integer.valueOf(smoothedRelayServerRoundTripTime)), str2, str}));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void logRTT(ScheduledItem scheduledItem) {
        for (Stream stream : (Stream[]) this.__streams.getValues()) {
            if (!stream.getBundled()) {
                CoreTransport coreTransportRtp = stream.getCoreTransportRtp();
                if (coreTransportRtp != null) {
                    logIceTransportRtt(coreTransportRtp.getIceTransport(), stream.getType(), true);
                }
                CoreTransport coreTransportRtcp = stream.getCoreTransportRtcp();
                if (coreTransportRtcp != null) {
                    logIceTransportRtt(coreTransportRtcp.getIceTransport(), stream.getType(), false);
                }
            }
        }
    }

    private boolean prepareTransports(BundleGroup[] bundleGroupArr) {
        return initialiseInternalTransports(bundleGroupArr);
    }

    private void processAndSetDescription(SessionDescription sessionDescription, boolean z, Promise<SessionDescription> promise) {
        Error processDescription = super.processDescription(sessionDescription, z);
        if (processDescription != null) {
            shutdownOnFailure(processDescription, TransportType.Unset, (String) null);
            if (processDescription.getException() != null) {
                throw new RuntimeException(processDescription.getException());
            }
            throw new RuntimeException(new Exception(processDescription.toString()));
        }
        verifyDtlsStillNeeded();
        if (z) {
            setDescription(sessionDescription, true);
            if (sessionDescription.getIsOffer()) {
                super.setSignallingState(SignallingState.HaveLocalOffer);
            } else {
                super.setSignallingState(SignallingState.Stable);
            }
        } else {
            setDescription(sessionDescription, false);
            if (sessionDescription.getIsOffer()) {
                super.setSignallingState(SignallingState.HaveRemoteOffer);
            } else {
                super.setSignallingState(SignallingState.Stable);
            }
        }
        promise.resolve(sessionDescription);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processBandwidthAdaptationPolicyChanged(fm.liveswitch.Stream r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7._connectionLock
            monitor-enter(r0)
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x007a }
            fm.liveswitch.ConnectionState r2 = fm.liveswitch.ConnectionState.Closing     // Catch:{ all -> 0x007a }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x007a }
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0020
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x007a }
            fm.liveswitch.ConnectionState r4 = fm.liveswitch.ConnectionState.Failing     // Catch:{ all -> 0x007a }
            boolean r1 = fm.liveswitch.Global.equals(r1, r4)     // Catch:{ all -> 0x007a }
            if (r1 == 0) goto L_0x001e
            goto L_0x0020
        L_0x001e:
            r1 = 0
            goto L_0x0021
        L_0x0020:
            r1 = 1
        L_0x0021:
            fm.liveswitch.ConnectionState r4 = super.getState()     // Catch:{ all -> 0x007a }
            fm.liveswitch.ConnectionState r5 = fm.liveswitch.ConnectionState.Connected     // Catch:{ all -> 0x007a }
            boolean r4 = fm.liveswitch.Global.equals(r4, r5)     // Catch:{ all -> 0x007a }
            if (r4 != 0) goto L_0x002f
            if (r1 == 0) goto L_0x0078
        L_0x002f:
            fm.liveswitch.StreamType r4 = r8.getType()     // Catch:{ all -> 0x007a }
            fm.liveswitch.StreamType r5 = fm.liveswitch.StreamType.Video     // Catch:{ all -> 0x007a }
            boolean r4 = fm.liveswitch.Global.equals(r4, r5)     // Catch:{ all -> 0x007a }
            if (r4 == 0) goto L_0x005f
            fm.liveswitch.VideoStream r8 = (fm.liveswitch.VideoStream) r8     // Catch:{ all -> 0x007a }
            fm.liveswitch.VideoStream r8 = (fm.liveswitch.VideoStream) r8     // Catch:{ all -> 0x007a }
            fm.liveswitch.RtpTransport r4 = r8.getRtpTransport()     // Catch:{ all -> 0x007a }
            fm.liveswitch.BandwidthAdaptationPolicy r5 = r8.getBandwidthAdaptationPolicy()     // Catch:{ all -> 0x007a }
            fm.liveswitch.BandwidthAdaptationPolicy r6 = fm.liveswitch.BandwidthAdaptationPolicy.Enabled     // Catch:{ all -> 0x007a }
            boolean r5 = fm.liveswitch.Global.equals(r5, r6)     // Catch:{ all -> 0x007a }
            if (r5 == 0) goto L_0x0052
            if (r1 != 0) goto L_0x0052
            goto L_0x0053
        L_0x0052:
            r2 = 0
        L_0x0053:
            if (r4 == 0) goto L_0x0078
            if (r2 == 0) goto L_0x005b
            boolean r3 = r8.getRembEnabled()     // Catch:{ all -> 0x007a }
        L_0x005b:
            r4.setRembEnabled(r3)     // Catch:{ all -> 0x007a }
            goto L_0x0078
        L_0x005f:
            fm.liveswitch.StreamType r1 = r8.getType()     // Catch:{ all -> 0x007a }
            fm.liveswitch.StreamType r2 = fm.liveswitch.StreamType.Audio     // Catch:{ all -> 0x007a }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x007a }
            if (r1 == 0) goto L_0x0078
            fm.liveswitch.AudioStream r8 = (fm.liveswitch.AudioStream) r8     // Catch:{ all -> 0x007a }
            fm.liveswitch.AudioStream r8 = (fm.liveswitch.AudioStream) r8     // Catch:{ all -> 0x007a }
            fm.liveswitch.RtpTransport r8 = r8.getRtpTransport()     // Catch:{ all -> 0x007a }
            if (r8 == 0) goto L_0x0078
            r8.setRembEnabled(r3)     // Catch:{ all -> 0x007a }
        L_0x0078:
            monitor-exit(r0)     // Catch:{ all -> 0x007a }
            return
        L_0x007a:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007a }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.processBandwidthAdaptationPolicyChanged(fm.liveswitch.Stream):void");
    }

    private void processDtlsTransportDown(DtlsTransport dtlsTransport) {
        dtlsTransport.removeOnStateChange(new IActionDelegate1<DtlsTransport>() {
            public String getId() {
                return "fm.liveswitch.Connection.processDtlsTransportStateChange";
            }

            public void invoke(DtlsTransport dtlsTransport) {
                Connection.this.processDtlsTransportStateChange(dtlsTransport);
            }
        });
        HashMapExtensions.remove(this.__streamsForDtlsTransport, dtlsTransport.getId());
        try {
            IceTransport iceTransport = null;
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__coreTransportForDtlsTransport, dtlsTransport.getId(), holder);
            CoreTransport coreTransport = (CoreTransport) holder.getValue();
            if (tryGetValue) {
                HashMapExtensions.remove(this.__coreTransportForDtlsTransport, dtlsTransport.getId());
                if (coreTransport != null) {
                    iceTransport = coreTransport.getIceTransport();
                }
            }
            if (iceTransport != null) {
                synchronized (this._connectionLock) {
                    __log.debug(StringExtensions.format("Shutting down Ice Transport {0} for connection {1}.", iceTransport.getId(), super.getId()));
                    iceTransport.stop();
                    if (Global.equals(iceTransport.getState(), IceTransportState.Failed)) {
                        processIceTransportDown(iceTransport);
                    }
                }
            }
        } catch (Exception e) {
            __log.debug(StringExtensions.format("No Ice transport found for a core transport while shutting down. Will attempt to close all remaining inner transports.", new Object[0]), e);
            shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, e), TransportType.DtlsTransport, dtlsTransport.getId());
        }
    }

    /* access modifiers changed from: private */
    public void processDtlsTransportStateChange(DtlsTransport dtlsTransport) {
        Error error;
        DtlsTransport dtlsTransport2;
        DtlsTransport dtlsTransport3;
        synchronized (this._connectionLock) {
            DtlsTransportState state = dtlsTransport.getState();
            if (Global.equals(state, DtlsTransportState.Connected)) {
                if (!super.getIsTerminatingOrTerminated()) {
                    Holder holder = new Holder(null);
                    boolean tryGetValue = HashMapExtensions.tryGetValue(this.__streamsForDtlsTransport, dtlsTransport.getId(), holder);
                    ArrayList arrayList = (ArrayList) holder.getValue();
                    if (tryGetValue) {
                        __log.debug(StringExtensions.format("DTLS transport {0} connected. Starting remaining transports on associated streams.", (Object) dtlsTransport.getId()));
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            Stream stream = (Stream) it.next();
                            if (Global.equals(stream.getType(), StreamType.Application)) {
                                SctpTransport sctpTransport = ((DataStream) stream).getSctpTransport();
                                if (sctpTransport == null) {
                                    shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception("Data stream does not have SCTP transport assigned.")), TransportType.DtlsTransport, dtlsTransport.getId());
                                } else {
                                    sctpTransport.start();
                                }
                            } else {
                                if (!Global.equals(stream.getType(), StreamType.Audio)) {
                                    if (!Global.equals(stream.getType(), StreamType.Video)) {
                                        shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception(StringExtensions.format("Unsupported data stream type {0}.", (Object) stream.getType().toString()))), TransportType.DtlsTransport, dtlsTransport.getId());
                                    }
                                }
                                CoreTransport coreTransportRtcp = stream.getCoreTransportRtcp();
                                CoreTransport coreTransportRtp = stream.getCoreTransportRtp();
                                if (coreTransportRtp == null) {
                                    dtlsTransport2 = null;
                                } else {
                                    dtlsTransport2 = coreTransportRtp.getDtlsTransport();
                                }
                                if (coreTransportRtcp == null) {
                                    dtlsTransport3 = null;
                                } else {
                                    dtlsTransport3 = coreTransportRtcp.getDtlsTransport();
                                }
                                if (coreTransportRtcp == null || (dtlsTransport2 != null && Global.equals(dtlsTransport2.getState(), DtlsTransportState.Connected) && dtlsTransport3 != null && Global.equals(dtlsTransport3.getState(), DtlsTransportState.Connected))) {
                                    int selectedSrtpProtectionProfile = dtlsTransport2.getSelectedSrtpProtectionProfile();
                                    if (selectedSrtpProtectionProfile == 0) {
                                        shutdownOnFailure(new Error(ErrorCode.ConnectionTransportClosed, new Exception(StringExtensions.format("DTLS-SRTP negotiation failed for {0} stream.", (Object) stream.getType().toString()))), TransportType.DtlsTransport, dtlsTransport.getId());
                                    } else {
                                        startRtpTransport(stream, selectedSrtpProtectionProfile, dtlsTransport2.getLocalKey(), dtlsTransport2.getLocalSalt(), dtlsTransport2.getRemoteKey(), dtlsTransport2.getRemoteSalt(), coreTransportRtp.getReceiveBufferSize());
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (Global.equals(state, DtlsTransportState.Failed) || Global.equals(state, DtlsTransportState.Closed)) {
                if (super.getIsTerminatingOrTerminated()) {
                    if (!Global.equals(super.getState(), ConnectionState.Closing)) {
                        if (!Global.equals(super.getState(), ConnectionState.Failing)) {
                            if (Global.equals(super.getState(), ConnectionState.Closed) || Global.equals(super.getState(), ConnectionState.Failed)) {
                                __log.debug(StringExtensions.format("DTLS transport reports being in {1} state, but connection {0} is already closed.", super.getId(), state.toString()));
                                dtlsTransport.removeOnStateChange(new IActionDelegate1<DtlsTransport>() {
                                    public String getId() {
                                        return "fm.liveswitch.Connection.processDtlsTransportStateChange";
                                    }

                                    public void invoke(DtlsTransport dtlsTransport) {
                                        Connection.this.processDtlsTransportStateChange(dtlsTransport);
                                    }
                                });
                            }
                        }
                    }
                    if (state == DtlsTransportState.Closed || state == DtlsTransportState.Failed) {
                        processDtlsTransportDown(dtlsTransport);
                    }
                } else if (dtlsTransport.getConnectionShouldStayAliveWhenClosed()) {
                    Holder holder2 = new Holder(null);
                    boolean tryGetValue2 = HashMapExtensions.tryGetValue(this.__streamsForDtlsTransport, dtlsTransport.getId(), holder2);
                    ArrayList arrayList2 = (ArrayList) holder2.getValue();
                    if (tryGetValue2) {
                        Iterator it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            Stream stream2 = (Stream) it2.next();
                            __log.debug(StringExtensions.format("DTLS transport {0} shut down expectedly. Encryption and Decryption on stream type {1} with id {2} will happen in SRTP transport.", dtlsTransport.getId(), stream2.getType().toString(), stream2.getId()));
                        }
                    }
                } else if (dtlsTransport.getClosingShouldNotTriggerGlobalNonGracefulShutdown()) {
                    __log.debug(StringExtensions.format("DTLS transport {0} shut down expectedly. Proceeding with Ice Transport shutdown.", (Object) dtlsTransport.getId()));
                    HashMapExtensions.remove(this.__dtlsTransports, dtlsTransport.getId());
                    processDtlsTransportDown(dtlsTransport);
                } else {
                    if (Global.equals(state, DtlsTransportState.Failed)) {
                        error = dtlsTransport.getError();
                        __log.error(StringExtensions.format("{0}. Connection {1} will shut down.", error.getDescription(), super.getId()));
                    } else {
                        error = new Error(ErrorCode.ConnectionTransportClosed, new Exception("DTLS transport shut down unexpectedly"));
                    }
                    shutdownOnFailure(error, TransportType.DtlsTransport, dtlsTransport.getId());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void processGathererStateChange(IceGatherer iceGatherer) {
        synchronized (this._connectionLock) {
            IceGatheringState state = iceGatherer != null ? iceGatherer.getState() : IceGatheringState.Failed;
            if (!Global.equals(state, IceGatheringState.Failed) && !Global.equals(state, IceGatheringState.Closing)) {
                if (!Global.equals(state, IceGatheringState.Closed)) {
                    if (Global.equals(state, IceGatheringState.Gathering)) {
                        IceGatherer relatedRtcpGatherer = iceGatherer.getRelatedRtcpGatherer();
                        if (relatedRtcpGatherer != null) {
                            relatedRtcpGatherer.start();
                        }
                        updateConnectionGatheringState();
                    } else {
                        updateConnectionGatheringState();
                    }
                }
            }
            if (Global.equals(super.getState(), ConnectionState.Closing) || Global.equals(super.getState(), ConnectionState.Closed) || Global.equals(super.getState(), ConnectionState.Failing) || Global.equals(super.getState(), ConnectionState.Failed) || iceGatherer.getClosingShouldNotTriggerGlobalNonGracefulShutdown()) {
                if (!iceGatherer.getClosingShouldNotTriggerGlobalNonGracefulShutdown()) {
                    if (!Global.equals(super.getState(), ConnectionState.Closing)) {
                        if (!Global.equals(super.getState(), ConnectionState.Failing)) {
                            if (Global.equals(super.getState(), ConnectionState.Closed) || Global.equals(super.getState(), ConnectionState.Failed)) {
                                __log.debug(StringExtensions.format("Gatherer reports being in {1} state, but Connection {0} is already closed.", super.getId(), state.toString()));
                                iceGatherer.removeOnStateChange(new IActionDelegate1<IceGatherer>() {
                                    public String getId() {
                                        return "fm.liveswitch.Connection.processGathererStateChange";
                                    }

                                    public void invoke(IceGatherer iceGatherer) {
                                        Connection.this.processGathererStateChange(iceGatherer);
                                    }
                                });
                                iceGatherer.removeOnLocalCandidate(new IActionDelegate2<IceGatherer, IceCandidate>() {
                                    public String getId() {
                                        return "fm.liveswitch.Connection.processNewLocalCandidate";
                                    }

                                    public void invoke(IceGatherer iceGatherer, IceCandidate iceCandidate) {
                                        Connection.this.processNewLocalCandidate(iceGatherer, iceCandidate);
                                    }
                                });
                            }
                        }
                    }
                    if (Global.equals(state, IceGatheringState.Closed)) {
                        __log.debug(StringExtensions.format("Gatherer {0} shut down gracefully.", (Object) iceGatherer.getId()));
                        ScheduledItem scheduledItem = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.verifyGatherersDown";
                            }

                            public void invoke(ScheduledItem scheduledItem) {
                                Connection.this.verifyGatherersDown(scheduledItem);
                            }
                        }, 1, ScheduledItem.getUnset(), ScheduledItem.getUnset(), 1);
                        this.__verifyGatherersDownScheduledItem = scheduledItem;
                        this.__scheduler.add(scheduledItem);
                    }
                } else if (state == IceGatheringState.Closed || state == IceGatheringState.Failed) {
                    HashMapExtensions.remove(this.__gatherers, iceGatherer.getId());
                    iceGatherer.removeOnStateChange(new IActionDelegate1<IceGatherer>() {
                        public String getId() {
                            return "fm.liveswitch.Connection.processGathererStateChange";
                        }

                        public void invoke(IceGatherer iceGatherer) {
                            Connection.this.processGathererStateChange(iceGatherer);
                        }
                    });
                    iceGatherer.removeOnLocalCandidate(new IActionDelegate2<IceGatherer, IceCandidate>() {
                        public String getId() {
                            return "fm.liveswitch.Connection.processNewLocalCandidate";
                        }

                        public void invoke(IceGatherer iceGatherer, IceCandidate iceCandidate) {
                            Connection.this.processNewLocalCandidate(iceGatherer, iceCandidate);
                        }
                    });
                    __log.debug(StringExtensions.format("Gatherer {0} shut down gracefully.", (Object) iceGatherer.getId()));
                }
            } else if (Global.equals(state, IceGatheringState.Failed)) {
                __log.error(StringExtensions.format("Connection {0}: Gatherer {1} error {2}", super.getId(), iceGatherer.getId(), iceGatherer.getError() != null ? iceGatherer.getError().getDescription() : "Generic gatherer error."));
            } else {
                shutdownOnFailure(new Error(ErrorCode.ConnectionTransportClosed, new Exception(StringExtensions.format("Connection {0}: Gatherer {1} shut down unexpectedly.", super.getId(), iceGatherer.getId()))), TransportType.Gatherer, iceGatherer.getId());
            }
            updateConnectionGatheringState();
        }
    }

    private void processIceTransportDown(IceTransport iceTransport) {
        __log.debug(StringExtensions.format("IceTransport {0} shut down gracefully.", (Object) iceTransport.getId()));
        iceTransport.removeOnStateChange(new IActionDelegate1<IceTransport>() {
            public String getId() {
                return "fm.liveswitch.Connection.processIceTransportStateChange";
            }

            public void invoke(IceTransport iceTransport) {
                Connection.this.processIceTransportStateChange(iceTransport);
            }
        });
        iceTransport.removeOnActiveCandidatePairChange(new IActionDelegate2<IceTransport, IceCandidatePair>() {
            public String getId() {
                return "fm.liveswitch.Connection.processActiveCandidatePairChange";
            }

            public void invoke(IceTransport iceTransport, IceCandidatePair iceCandidatePair) {
                Connection.this.processActiveCandidatePairChange(iceTransport, iceCandidatePair);
            }
        });
        try {
            HashMapExtensions.remove(this.__streamsForIceTransport, iceTransport.getId());
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__coreTransportForIceTransport, iceTransport.getId(), holder);
            CoreTransport coreTransport = (CoreTransport) holder.getValue();
            if (tryGetValue) {
                HashMapExtensions.remove(this.__coreTransportForIceTransport, iceTransport.getId());
                if (coreTransport != null) {
                    IceGatherer gatherer = coreTransport.getGatherer();
                    removeFromDictionaryList(this.__coreTransportsForGatherer, gatherer.getId(), coreTransport);
                    stopGatherer(gatherer);
                    return;
                }
                return;
            }
            shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception("No Gatherer transport found for Core Transport while shutting down. Will attempt to close all remaining inner transports.")), TransportType.IceTransport, iceTransport.getId());
        } catch (Exception e) {
            __log.debug(StringExtensions.format("No Gatherer transport found for Core Transport while shutting down. Will attempt to close all remaining inner transports.", new Object[0]), e);
            shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, e), TransportType.IceTransport, iceTransport.getId());
        }
    }

    /* access modifiers changed from: private */
    public void processIceTransportStateChange(IceTransport iceTransport) {
        synchronized (this._connectionLock) {
            if (this._verboseDebugMessages) {
                __log.debug(StringExtensions.format("Ice transport {0} state change to {1}", iceTransport.getId(), iceTransport.getState().toString()));
            }
            IceTransportState state = iceTransport.getState();
            updateIceConnectionsState();
            if (state == IceTransportState.Connected) {
                if (iceTransport.getDynamicValue("fm.icelink.IceTransportAlreadyConnected") == null) {
                    iceTransport.setDynamicValue("fm.icelink.IceTransportAlreadyConnected", new Object());
                    doProcessIceTransportConnected(iceTransport);
                } else {
                    __log.debug(StringExtensions.format("Connectivity is now available with peer for connection {0}.", (Object) super.getId()));
                    doProcessIceTransportReconnected(iceTransport);
                }
            } else if (state == IceTransportState.Disconnected) {
                doProcessIceTransportDisconnected(iceTransport);
            } else if (state == IceTransportState.Closed) {
                doProcessIceTransportClosed(iceTransport);
            } else if (state == IceTransportState.Failed) {
                shutdownOnFailure(iceTransport.getError(), TransportType.IceTransport, iceTransport.getId());
            }
        }
    }

    private void processMediaTransportDown(IMediaTransport iMediaTransport) {
        iMediaTransport.removeOnStateChange(new IActionDelegate1<IMediaTransport>() {
            public String getId() {
                return "fm.liveswitch.Connection.processMediaTransportStateChange";
            }

            public void invoke(IMediaTransport iMediaTransport) {
                Connection.this.processMediaTransportStateChange(iMediaTransport);
            }
        });
        try {
            if (iMediaTransport instanceof RtpAudioTransport) {
                if (this.__streamForAudioTransport.containsKey(iMediaTransport.getId())) {
                    HashMapExtensions.remove(this.__streamForAudioTransport, iMediaTransport.getId());
                    final AudioStream audioStream = (AudioStream) HashMapExtensions.getItem(this.__streamForAudioTransport).get(iMediaTransport.getId());
                    RtpTransport rtpTransport = audioStream.getRtpTransport();
                    if (rtpTransport != null) {
                        rtpTransport.removeOnReceiveControlFrames(new IActionDelegate1<MediaControlFrame[]>() {
                            public String getId() {
                                return "fm.liveswitch.MediaStream<fm.liveswitch.IAudioOutput,fm.liveswitch.IAudioOutputCollection,fm.liveswitch.IAudioInput,fm.liveswitch.IAudioInputCollection,fm.liveswitch.IAudioElement,fm.liveswitch.AudioSource,fm.liveswitch.AudioSink,fm.liveswitch.AudioPipe,fm.liveswitch.AudioTrack,fm.liveswitch.AudioBranch,fm.liveswitch.AudioFrame,fm.liveswitch.AudioBuffer,fm.liveswitch.AudioBufferCollection,fm.liveswitch.AudioFormat,fm.liveswitch.AudioFormatCollection>.receiveControlFrames";
                            }

                            public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                                audioStream.receiveControlFrames(mediaControlFrameArr);
                            }
                        });
                        rtpTransport.removeOnReceiveFrame(new IActionDelegate1<AudioFrame>() {
                            public String getId() {
                                return "fm.liveswitch.MediaStream<fm.liveswitch.IAudioOutput,fm.liveswitch.IAudioOutputCollection,fm.liveswitch.IAudioInput,fm.liveswitch.IAudioInputCollection,fm.liveswitch.IAudioElement,fm.liveswitch.AudioSource,fm.liveswitch.AudioSink,fm.liveswitch.AudioPipe,fm.liveswitch.AudioTrack,fm.liveswitch.AudioBranch,fm.liveswitch.AudioFrame,fm.liveswitch.AudioBuffer,fm.liveswitch.AudioBufferCollection,fm.liveswitch.AudioFormat,fm.liveswitch.AudioFormatCollection>.receiveFrame";
                            }

                            public void invoke(AudioFrame audioFrame) {
                                audioStream.receiveFrame(audioFrame);
                            }
                        });
                        if (iMediaTransport instanceof RtpAudioTransport) {
                            audioStream.setRtpTransport((RtpTransport) null);
                        }
                    }
                }
            } else if ((iMediaTransport instanceof RtpVideoTransport) && this.__streamForVideoTransport.containsKey(iMediaTransport.getId())) {
                HashMapExtensions.remove(this.__streamForVideoTransport, iMediaTransport.getId());
                final VideoStream videoStream = (VideoStream) HashMapExtensions.getItem(this.__streamForVideoTransport).get(iMediaTransport.getId());
                RtpTransport rtpTransport2 = videoStream.getRtpTransport();
                if (rtpTransport2 != null) {
                    rtpTransport2.removeOnReceiveControlFrames(new IActionDelegate1<MediaControlFrame[]>() {
                        public String getId() {
                            return "fm.liveswitch.MediaStream<fm.liveswitch.IVideoOutput,fm.liveswitch.IVideoOutputCollection,fm.liveswitch.IVideoInput,fm.liveswitch.IVideoInputCollection,fm.liveswitch.IVideoElement,fm.liveswitch.VideoSource,fm.liveswitch.VideoSink,fm.liveswitch.VideoPipe,fm.liveswitch.VideoTrack,fm.liveswitch.VideoBranch,fm.liveswitch.VideoFrame,fm.liveswitch.VideoBuffer,fm.liveswitch.VideoBufferCollection,fm.liveswitch.VideoFormat,fm.liveswitch.VideoFormatCollection>.receiveControlFrames";
                        }

                        public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                            videoStream.receiveControlFrames(mediaControlFrameArr);
                        }
                    });
                    rtpTransport2.removeOnReceiveFrame(new IActionDelegate1<VideoFrame>() {
                        public String getId() {
                            return "fm.liveswitch.MediaStream<fm.liveswitch.IVideoOutput,fm.liveswitch.IVideoOutputCollection,fm.liveswitch.IVideoInput,fm.liveswitch.IVideoInputCollection,fm.liveswitch.IVideoElement,fm.liveswitch.VideoSource,fm.liveswitch.VideoSink,fm.liveswitch.VideoPipe,fm.liveswitch.VideoTrack,fm.liveswitch.VideoBranch,fm.liveswitch.VideoFrame,fm.liveswitch.VideoBuffer,fm.liveswitch.VideoBufferCollection,fm.liveswitch.VideoFormat,fm.liveswitch.VideoFormatCollection>.receiveFrame";
                        }

                        public void invoke(VideoFrame videoFrame) {
                            videoStream.receiveFrame(videoFrame);
                        }
                    });
                    if (iMediaTransport instanceof RtpVideoTransport) {
                        videoStream.setRtpTransport((RtpTransport) null);
                    }
                }
            }
        } catch (Exception e) {
            __log.error(StringExtensions.format("No Dtls transport found for Reliable Data Transport while shutting down. Will attempt to close all remaining inner transports.", new Object[0]), e);
            shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, e), TransportType.SctpTransport, iMediaTransport.getId());
        }
    }

    /* access modifiers changed from: private */
    public void processMediaTransportStateChange(IMediaTransport iMediaTransport) {
        doProcessMediaTransportStateChange(iMediaTransport);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00fd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processNewLocalCandidate(fm.liveswitch.IceGatherer r12, fm.liveswitch.IceCandidate r13) {
        /*
            r11 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Object r1 = r11._connectionLock
            monitor-enter(r1)
            fm.liveswitch.ConnectionState r2 = super.getState()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Closed     // Catch:{ all -> 0x0113 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x0113 }
            r3 = 1
            r4 = 0
            if (r2 != 0) goto L_0x00fa
            fm.liveswitch.ConnectionState r2 = super.getState()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.ConnectionState r5 = fm.liveswitch.ConnectionState.Closing     // Catch:{ all -> 0x0113 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r5)     // Catch:{ all -> 0x0113 }
            if (r2 != 0) goto L_0x00fa
            fm.liveswitch.ConnectionState r2 = super.getState()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.ConnectionState r5 = fm.liveswitch.ConnectionState.Failing     // Catch:{ all -> 0x0113 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r5)     // Catch:{ all -> 0x0113 }
            if (r2 != 0) goto L_0x00fa
            fm.liveswitch.ConnectionState r2 = super.getState()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.ConnectionState r5 = fm.liveswitch.ConnectionState.Failed     // Catch:{ all -> 0x0113 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r5)     // Catch:{ all -> 0x0113 }
            if (r2 != 0) goto L_0x00fa
            fm.liveswitch.ConnectionState r2 = super.getState()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.ConnectionState r5 = fm.liveswitch.ConnectionState.New     // Catch:{ all -> 0x0113 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r5)     // Catch:{ all -> 0x0113 }
            if (r2 != 0) goto L_0x00fa
            fm.liveswitch.Holder r2 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0113 }
            r5 = 0
            r2.<init>(r5)     // Catch:{ all -> 0x0113 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.CoreTransport>> r6 = r11.__coreTransportsForGatherer     // Catch:{ all -> 0x0113 }
            java.lang.String r12 = r12.getId()     // Catch:{ all -> 0x0113 }
            boolean r12 = fm.liveswitch.HashMapExtensions.tryGetValue(r6, r12, r2)     // Catch:{ all -> 0x0113 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x0113 }
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ all -> 0x0113 }
            if (r12 == 0) goto L_0x00d6
            java.util.Iterator r12 = r2.iterator()     // Catch:{ all -> 0x0113 }
        L_0x0062:
            boolean r2 = r12.hasNext()     // Catch:{ all -> 0x0113 }
            if (r2 == 0) goto L_0x00d6
            java.lang.Object r2 = r12.next()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.CoreTransport r2 = (fm.liveswitch.CoreTransport) r2     // Catch:{ all -> 0x0113 }
            fm.liveswitch.IceTransport r6 = r2.getIceTransport()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.Holder r7 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0113 }
            r7.<init>(r5)     // Catch:{ all -> 0x0113 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.Stream>> r8 = r11.__streamsForIceTransport     // Catch:{ all -> 0x0113 }
            java.lang.String r6 = r6.getId()     // Catch:{ all -> 0x0113 }
            boolean r6 = fm.liveswitch.HashMapExtensions.tryGetValue(r8, r6, r7)     // Catch:{ all -> 0x0113 }
            java.lang.Object r7 = r7.getValue()     // Catch:{ all -> 0x0113 }
            java.util.ArrayList r7 = (java.util.ArrayList) r7     // Catch:{ all -> 0x0113 }
            if (r6 == 0) goto L_0x0062
            fm.liveswitch.IceTransport r6 = r2.getIceTransport()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.IceComponent r6 = r6.getComponent()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.IceComponent r8 = fm.liveswitch.IceComponent.Rtp     // Catch:{ all -> 0x0113 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r8)     // Catch:{ all -> 0x0113 }
            if (r6 == 0) goto L_0x009b
            r6 = 1
            goto L_0x009c
        L_0x009b:
            r6 = 2
        L_0x009c:
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x0113 }
        L_0x00a0:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x0113 }
            if (r8 == 0) goto L_0x0062
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.Stream r8 = (fm.liveswitch.Stream) r8     // Catch:{ all -> 0x0113 }
            boolean r9 = r8.getBundled()     // Catch:{ all -> 0x0113 }
            if (r9 != 0) goto L_0x00a0
            fm.liveswitch.CoreTransport r9 = r8.getCoreTransportRtp()     // Catch:{ all -> 0x0113 }
            java.lang.String r9 = r9.getId()     // Catch:{ all -> 0x0113 }
            java.lang.String r10 = r2.getId()     // Catch:{ all -> 0x0113 }
            boolean r9 = fm.liveswitch.Global.equals(r9, r10)     // Catch:{ all -> 0x0113 }
            if (r9 == 0) goto L_0x00a0
            int r9 = r8.getIndex()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.Candidate r9 = r13.toCandidate(r6, r9)     // Catch:{ all -> 0x0113 }
            r9.setDispatched(r4)     // Catch:{ all -> 0x0113 }
            r0.add(r9)     // Catch:{ all -> 0x0113 }
            r8.addLocalCandidate(r9)     // Catch:{ all -> 0x0113 }
            goto L_0x00a0
        L_0x00d6:
            fm.liveswitch.ConnectionState r12 = super.getState()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.ConnectionState r13 = fm.liveswitch.ConnectionState.Connected     // Catch:{ all -> 0x0113 }
            boolean r12 = fm.liveswitch.Global.equals(r12, r13)     // Catch:{ all -> 0x0113 }
            if (r12 != 0) goto L_0x00ee
            fm.liveswitch.ConnectionState r12 = super.getState()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.ConnectionState r13 = fm.liveswitch.ConnectionState.Connecting     // Catch:{ all -> 0x0113 }
            boolean r12 = fm.liveswitch.Global.equals(r12, r13)     // Catch:{ all -> 0x0113 }
            if (r12 == 0) goto L_0x00f5
        L_0x00ee:
            boolean r12 = super.getUseTrickleIce()     // Catch:{ all -> 0x0113 }
            if (r12 == 0) goto L_0x00f5
            goto L_0x00fb
        L_0x00f5:
            java.util.ArrayList<fm.liveswitch.Candidate> r12 = r11.__cachedLocalCandidates     // Catch:{ all -> 0x0113 }
            fm.liveswitch.ArrayListExtensions.addRange(r12, r0)     // Catch:{ all -> 0x0113 }
        L_0x00fa:
            r3 = 0
        L_0x00fb:
            if (r3 == 0) goto L_0x0111
            java.util.Iterator r12 = r0.iterator()     // Catch:{ all -> 0x0113 }
        L_0x0101:
            boolean r13 = r12.hasNext()     // Catch:{ all -> 0x0113 }
            if (r13 == 0) goto L_0x0111
            java.lang.Object r13 = r12.next()     // Catch:{ all -> 0x0113 }
            fm.liveswitch.Candidate r13 = (fm.liveswitch.Candidate) r13     // Catch:{ all -> 0x0113 }
            r11.raiseLocalCandidate(r13)     // Catch:{ all -> 0x0113 }
            goto L_0x0101
        L_0x0111:
            monitor-exit(r1)     // Catch:{ all -> 0x0113 }
            return
        L_0x0113:
            r12 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0113 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.processNewLocalCandidate(fm.liveswitch.IceGatherer, fm.liveswitch.IceCandidate):void");
    }

    /* access modifiers changed from: private */
    public void processOnNewDataChannel(DataChannel dataChannel) {
        synchronized (this._connectionLock) {
            dataChannel.removeOnStateChange(new IActionDelegate1<DataChannel>() {
                public String getId() {
                    return "fm.liveswitch.Connection.processReliableDataChannelStateChange";
                }

                public void invoke(DataChannel dataChannel) {
                    Connection.this.processReliableDataChannelStateChange(dataChannel);
                }
            });
            dataChannel.addOnStateChange(new IActionDelegate1<DataChannel>() {
                public String getId() {
                    return "fm.liveswitch.Connection.processReliableDataChannelStateChange";
                }

                public void invoke(DataChannel dataChannel) {
                    Connection.this.processReliableDataChannelStateChange(dataChannel);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void processReliableDataChannelStateChange(DataChannel dataChannel) {
        DataChannelState state = dataChannel == null ? DataChannelState.Failed : dataChannel.getState();
        if (state == DataChannelState.Connected) {
            processTopTransportConnected();
        } else if (state == DataChannelState.Closing) {
            dataChannel.removeOnStateChange(new IActionDelegate1<DataChannel>() {
                public String getId() {
                    return "fm.liveswitch.Connection.processReliableDataChannelStateChange";
                }

                public void invoke(DataChannel dataChannel) {
                    Connection.this.processReliableDataChannelStateChange(dataChannel);
                }
            });
        } else if (state == DataChannelState.Closed) {
            dataChannel.removeOnStateChange(new IActionDelegate1<DataChannel>() {
                public String getId() {
                    return "fm.liveswitch.Connection.processReliableDataChannelStateChange";
                }

                public void invoke(DataChannel dataChannel) {
                    Connection.this.processReliableDataChannelStateChange(dataChannel);
                }
            });
        } else if (state == DataChannelState.Failed) {
            dataChannel.removeOnStateChange(new IActionDelegate1<DataChannel>() {
                public String getId() {
                    return "fm.liveswitch.Connection.processReliableDataChannelStateChange";
                }

                public void invoke(DataChannel dataChannel) {
                    Connection.this.processReliableDataChannelStateChange(dataChannel);
                }
            });
        }
    }

    private void processReliableDataTransportDown(ReliableTransport reliableTransport) {
        reliableTransport.removeOnStateChange(new IActionDelegate1<ReliableTransport>() {
            public String getId() {
                return "fm.liveswitch.Connection.processReliableDataTransportStateChange";
            }

            public void invoke(ReliableTransport reliableTransport) {
                Connection.this.processReliableDataTransportStateChange(reliableTransport);
            }
        });
        try {
            HashMapExtensions.remove(this.__streamForReliableDataTransport, reliableTransport.getId());
            SctpTransport sctpTransport = HashMapExtensions.getItem(this.__streamForReliableDataTransport).get(reliableTransport.getId()).getSctpTransport();
            if (sctpTransport != null) {
                sctpTransport.stop();
            }
        } catch (Exception e) {
            __log.debug(StringExtensions.format("No Sctp transport found for Reliable Data Transport while shutting down. Will attempt to close all remaining inner transports.", new Object[0]), e);
            shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, e), TransportType.ReliableDataTransport, reliableTransport.getId());
        }
    }

    /* access modifiers changed from: private */
    public void processReliableDataTransportStateChange(ReliableTransport reliableTransport) {
        Error error;
        ReliableTransportState state = reliableTransport.getState();
        synchronized (this._connectionLock) {
            if (!Global.equals(super.getState(), ConnectionState.Closed) && !Global.equals(super.getState(), ConnectionState.Failing) && !Global.equals(super.getState(), ConnectionState.Failed) && !Global.equals(state, ReliableTransportState.New) && !Global.equals(state, ReliableTransportState.Opening) && !Global.equals(state, ReliableTransportState.Connected) && (Global.equals(state, ReliableTransportState.Failed) || Global.equals(state, ReliableTransportState.Closing) || Global.equals(state, ReliableTransportState.Closed))) {
                if (Global.equals(super.getState(), ConnectionState.Closing) || Global.equals(super.getState(), ConnectionState.Closed)) {
                    if (!Global.equals(super.getState(), ConnectionState.Closing)) {
                        if (!Global.equals(super.getState(), ConnectionState.Failing)) {
                            if (Global.equals(super.getState(), ConnectionState.Closed) || Global.equals(super.getState(), ConnectionState.Failed)) {
                                __log.debug(StringExtensions.format("ReliableData transport reports being in {1} state, but Connection {0} is already closed.", super.getId(), state.toString()));
                                reliableTransport.removeOnStateChange(new IActionDelegate1<ReliableTransport>() {
                                    public String getId() {
                                        return "fm.liveswitch.Connection.processReliableDataTransportStateChange";
                                    }

                                    public void invoke(ReliableTransport reliableTransport) {
                                        Connection.this.processReliableDataTransportStateChange(reliableTransport);
                                    }
                                });
                            }
                        }
                    }
                    if (Global.equals(state, ReliableTransportState.Closed)) {
                        processReliableDataTransportDown(reliableTransport);
                    }
                } else {
                    if (Global.equals(state, ReliableTransportState.Failed)) {
                        error = reliableTransport.getError();
                        if (error != null) {
                            __log.error(StringExtensions.format("{0}. Connection {1} will shut down.", error.getDescription(), super.getId()));
                        } else {
                            __log.error(StringExtensions.format("Connection {0} will shut down.", (Object) super.getId()));
                        }
                    } else {
                        error = new Error(ErrorCode.ConnectionTransportClosed, new Exception("Reliable Transport shut down unexpectedly."));
                    }
                    shutdownOnFailure(error, TransportType.ReliableDataTransport, reliableTransport.getId());
                }
            }
        }
    }

    private void processRtpListenerDown(RtpListener rtpListener) {
        rtpListener.removeOnStateChange(new IActionDelegate1<RtpListener>() {
            public String getId() {
                return "fm.liveswitch.Connection.processRtpListenerStateChange";
            }

            public void invoke(RtpListener rtpListener) {
                Connection.this.processRtpListenerStateChange(rtpListener);
            }
        });
        Stream stream = null;
        try {
            if (this.__streamForVideoTransport.containsKey(rtpListener.getId())) {
                stream = HashMapExtensions.getItem(this.__streamForVideoTransport).get(rtpListener.getId());
                HashMapExtensions.remove(this.__streamForVideoTransport, rtpListener.getId());
            } else if (this.__streamForAudioTransport.containsKey(rtpListener.getId())) {
                stream = HashMapExtensions.getItem(this.__streamForAudioTransport).get(rtpListener.getId());
                HashMapExtensions.remove(this.__streamForAudioTransport, rtpListener.getId());
            }
            if (stream != null) {
                CoreTransport coreTransportRtp = stream.getCoreTransportRtp();
                CoreTransport coreTransportRtcp = stream.getCoreTransportRtcp();
                CoreTransport bundleCoreTransport = stream.getBundleCoreTransport();
                DtlsTransport dtlsTransport = coreTransportRtp.getDtlsTransport();
                if (dtlsTransport != null) {
                    removeFromDictionaryList(this.__streamsForDtlsTransport, dtlsTransport.getId(), stream);
                }
                if (coreTransportRtp != null) {
                    streamReadyToCloseTransport(coreTransportRtp);
                    if (coreTransportRtcp != null && !Global.equals(coreTransportRtcp.getId(), coreTransportRtp.getId())) {
                        streamReadyToCloseTransport(coreTransportRtcp);
                    }
                    if (bundleCoreTransport != null && !Global.equals(bundleCoreTransport.getId(), coreTransportRtp.getId())) {
                        streamReadyToCloseTransport(bundleCoreTransport);
                    }
                }
            }
        } catch (Exception e) {
            __log.error(StringExtensions.format("No Dtls transport found for Reliable Data Transport while shutting down. Will attempt to close all remaining inner transports.", new Object[0]), e);
            shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, e), TransportType.SctpTransport, rtpListener.getId());
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        if (r0 == fm.liveswitch.MediaTransportState.New) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        if (r0 == fm.liveswitch.MediaTransportState.Starting) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003e, code lost:
        if (r0 != fm.liveswitch.MediaTransportState.Stopping) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0043, code lost:
        if (r0 != fm.liveswitch.MediaTransportState.Started) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0045, code lost:
        processTopTransportConnected();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004b, code lost:
        if (r0 != fm.liveswitch.MediaTransportState.Stopped) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004d, code lost:
        processRtpListenerDown(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processRtpListenerStateChange(fm.liveswitch.RtpListener r5) {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0005
            fm.liveswitch.MediaTransportState r0 = fm.liveswitch.MediaTransportState.Failed
            goto L_0x0009
        L_0x0005:
            fm.liveswitch.MediaTransportState r0 = r5.getState()
        L_0x0009:
            java.lang.Object r1 = r4._connectionLock
            monitor-enter(r1)
            fm.liveswitch.ConnectionState r2 = super.getState()     // Catch:{ all -> 0x0053 }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Closed     // Catch:{ all -> 0x0053 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x0053 }
            if (r2 != 0) goto L_0x0051
            fm.liveswitch.ConnectionState r2 = super.getState()     // Catch:{ all -> 0x0053 }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Failing     // Catch:{ all -> 0x0053 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x0053 }
            if (r2 != 0) goto L_0x0051
            fm.liveswitch.ConnectionState r2 = super.getState()     // Catch:{ all -> 0x0053 }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Failed     // Catch:{ all -> 0x0053 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x0053 }
            if (r2 != 0) goto L_0x0051
            if (r5 != 0) goto L_0x0033
            goto L_0x0051
        L_0x0033:
            monitor-exit(r1)     // Catch:{ all -> 0x0053 }
            fm.liveswitch.MediaTransportState r1 = fm.liveswitch.MediaTransportState.New
            if (r0 == r1) goto L_0x0050
            fm.liveswitch.MediaTransportState r1 = fm.liveswitch.MediaTransportState.Starting
            if (r0 == r1) goto L_0x0050
            fm.liveswitch.MediaTransportState r1 = fm.liveswitch.MediaTransportState.Stopping
            if (r0 != r1) goto L_0x0041
            goto L_0x0050
        L_0x0041:
            fm.liveswitch.MediaTransportState r1 = fm.liveswitch.MediaTransportState.Started
            if (r0 != r1) goto L_0x0049
            r4.processTopTransportConnected()
            return
        L_0x0049:
            fm.liveswitch.MediaTransportState r1 = fm.liveswitch.MediaTransportState.Stopped
            if (r0 != r1) goto L_0x0050
            r4.processRtpListenerDown(r5)
        L_0x0050:
            return
        L_0x0051:
            monitor-exit(r1)     // Catch:{ all -> 0x0053 }
            return
        L_0x0053:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0053 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.processRtpListenerStateChange(fm.liveswitch.RtpListener):void");
    }

    private void processSctpTransportDown(SctpTransport sctpTransport) {
        sctpTransport.removeOnStateChange(new IActionDelegate1<SctpTransport>() {
            public String getId() {
                return "fm.liveswitch.Connection.processSctpTransportStateChange";
            }

            public void invoke(SctpTransport sctpTransport) {
                Connection.this.processSctpTransportStateChange(sctpTransport);
            }
        });
        try {
            DataStream dataStream = HashMapExtensions.getItem(this.__streamForSctpTransport).get(sctpTransport.getId());
            HashMapExtensions.remove(this.__streamForSctpTransport, sctpTransport.getId());
            CoreTransport coreTransportRtp = dataStream.getCoreTransportRtp();
            CoreTransport bundleCoreTransport = dataStream.getBundleCoreTransport();
            removeFromDictionaryList(this.__streamsForDtlsTransport, coreTransportRtp.getDtlsTransport().getId(), dataStream);
            streamReadyToCloseTransport(coreTransportRtp);
            if (bundleCoreTransport != null && !Global.equals(coreTransportRtp.getId(), bundleCoreTransport.getId())) {
                streamReadyToCloseTransport(bundleCoreTransport);
            }
        } catch (Exception e) {
            __log.error(StringExtensions.format("No Dtls transport found for Reliable Data Transport while shutting down. Will attempt to close all remaining inner transports.", new Object[0]), e);
            shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, e), TransportType.SctpTransport, sctpTransport.getId());
        }
    }

    /* access modifiers changed from: private */
    public void processSctpTransportStateChange(SctpTransport sctpTransport) {
        Error error;
        SctpTransportState state = sctpTransport.getState();
        synchronized (this._connectionLock) {
            if (!Global.equals(super.getState(), ConnectionState.Closed) && !Global.equals(super.getState(), ConnectionState.Failing) && !Global.equals(super.getState(), ConnectionState.Failed) && !Global.equals(state, SctpTransportState.New) && !Global.equals(state, SctpTransportState.Connecting)) {
                if (Global.equals(state, SctpTransportState.Connected)) {
                    try {
                        DataStream dataStream = HashMapExtensions.getItem(this.__streamForSctpTransport).get(sctpTransport.getId());
                        ReliableTransport reliableDataTransport = dataStream.getReliableDataTransport();
                        if (dataStream.getBundled()) {
                            reliableDataTransport.start(Global.equals(dataStream.getBundleCoreTransport().getDtlsTransport().getLocalParameters().getRole(), DtlsRole.Server), this.__isOfferer);
                        } else {
                            reliableDataTransport.start(Global.equals(dataStream.getCoreTransportRtp().getDtlsTransport().getLocalParameters().getRole(), DtlsRole.Server), this.__isOfferer);
                        }
                    } catch (Exception e) {
                        __log.error(StringExtensions.format("SCTP transport {0} started but corresponding reliable data transport cannot be found for the data stream.", (Object) sctpTransport.getId()), e);
                        shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, e), TransportType.SctpTransport, sctpTransport.getId());
                    }
                } else if (!Global.equals(state, SctpTransportState.Failing) && !Global.equals(state, SctpTransportState.Closing) && (Global.equals(state, SctpTransportState.Failed) || Global.equals(state, SctpTransportState.Closed))) {
                    if (!super.getIsTerminatingOrTerminated()) {
                        if (Global.equals(state, SctpTransportState.Failed)) {
                            error = sctpTransport.getError();
                            __log.error(StringExtensions.format("{0}. Connection {1} will shut down.", error.getDescription(), super.getId()));
                        } else {
                            error = new Error(ErrorCode.ConnectionTransportClosed, new Exception("SCTP transport shut down unexpectedly."));
                        }
                        shutdownOnFailure(error, TransportType.SctpTransport, sctpTransport.getId());
                    } else {
                        if (!Global.equals(super.getState(), ConnectionState.Closing)) {
                            if (!Global.equals(super.getState(), ConnectionState.Failing)) {
                                if (Global.equals(super.getState(), ConnectionState.Closed) || Global.equals(super.getState(), ConnectionState.Failed)) {
                                    __log.debug(StringExtensions.format("SCTP transport reports being in {1} state, but connection {0} is already closed.", super.getId(), state.toString()));
                                    sctpTransport.removeOnStateChange(new IActionDelegate1<SctpTransport>() {
                                        public String getId() {
                                            return "fm.liveswitch.Connection.processSctpTransportStateChange";
                                        }

                                        public void invoke(SctpTransport sctpTransport) {
                                            Connection.this.processSctpTransportStateChange(sctpTransport);
                                        }
                                    });
                                }
                            }
                        }
                        processSctpTransportDown(sctpTransport);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void processSctpTransportStateTransitionsAsTopTransport(SctpTransport sctpTransport) {
        if (Global.equals(sctpTransport.getState(), SctpTransportState.Connected)) {
            processTopTransportConnected();
        }
    }

    /* access modifiers changed from: protected */
    public void processStateChange() {
        super.processStateChange();
        if (Global.equals(super.getState(), ConnectionState.Initializing)) {
            this.__scheduler.start();
            if (super.getLegacyTimeout()) {
                ScheduledItem scheduledItem = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.Connection.establishConnectionTimeout";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        Connection.this.establishConnectionTimeout(scheduledItem);
                    }
                }, super.getTimeout(), ScheduledItem.getUnset(), ScheduledItem.getUnset(), 1);
                this.__establishConnectionTimeoutSI = scheduledItem;
                this.__scheduler.add(scheduledItem);
            }
        } else if (!Global.equals(super.getState(), ConnectionState.Connecting)) {
            int i = 0;
            if (Global.equals(super.getState(), ConnectionState.Connected) || Global.equals(super.getState(), ConnectionState.Closing) || Global.equals(super.getState(), ConnectionState.Failing)) {
                Stream[] streams = getStreams();
                int length = streams.length;
                while (i < length) {
                    processBandwidthAdaptationPolicyChanged(streams[i]);
                    i++;
                }
            } else if (Global.equals(super.getState(), ConnectionState.Closed) || Global.equals(super.getState(), ConnectionState.Failed)) {
                Stream[] streams2 = getStreams();
                int length2 = streams2.length;
                while (i < length2) {
                    Stream stream = streams2[i];
                    if (Global.equals(stream.getType(), StreamType.Audio)) {
                        ((AudioStream) stream).removeOnBandwidthAdaptationPolicyChange(new IActionDelegate1<Stream>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processBandwidthAdaptationPolicyChanged";
                            }

                            public void invoke(Stream stream) {
                                Connection.this.processBandwidthAdaptationPolicyChanged(stream);
                            }
                        });
                    } else if (Global.equals(stream.getType(), StreamType.Video)) {
                        ((VideoStream) stream).removeOnBandwidthAdaptationPolicyChange(new IActionDelegate1<Stream>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processBandwidthAdaptationPolicyChanged";
                            }

                            public void invoke(Stream stream) {
                                Connection.this.processBandwidthAdaptationPolicyChanged(stream);
                            }
                        });
                    }
                    i++;
                }
            }
        } else if (!super.getLegacyTimeout()) {
            ScheduledItem scheduledItem2 = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
                public String getId() {
                    return "fm.liveswitch.Connection.establishConnectionTimeout";
                }

                public void invoke(ScheduledItem scheduledItem) {
                    Connection.this.establishConnectionTimeout(scheduledItem);
                }
            }, super.getTimeout(), ScheduledItem.getUnset(), ScheduledItem.getUnset(), 1);
            this.__establishConnectionTimeoutSI = scheduledItem2;
            this.__scheduler.add(scheduledItem2);
        }
    }

    static Stream[] processStreamUpdate(Pair<StreamDescription[], Object[]> pair, IFunction1<StreamDescription, Stream> iFunction1, IAction1<Stream> iAction1) {
        StreamDescription[] item1 = pair.getItem1();
        ArrayList arrayList = new ArrayList();
        if (iFunction1 != null) {
            for (StreamDescription streamDescription : item1) {
                String mediaDescriptionIdentifier = streamDescription.getMediaDescriptionIdentifier();
                Stream invoke = iFunction1.invoke(streamDescription);
                if (invoke != null) {
                    if (!StringExtensions.isNullOrEmpty(mediaDescriptionIdentifier)) {
                        invoke.setMediaStreamIdentification(mediaDescriptionIdentifier);
                    }
                    arrayList.add(invoke);
                }
            }
        } else if (item1 != null && ArrayExtensions.getLength((Object[]) item1) > 0) {
            throw new RuntimeException(new Exception("Remote peer has added new streams but Connection.OnAddStream property is not assigned."));
        }
        Object[] item2 = pair.getItem2();
        if (iAction1 != null) {
            for (Object tryCast : item2) {
                iAction1.invoke((Stream) Global.tryCast(tryCast, Stream.class));
            }
        } else if (item2 != null && ArrayExtensions.getLength(item2) > 0) {
            throw new RuntimeException(new Exception("Remote peer has added new streams but Connection.OnAddStream property is not assigned."));
        }
        return (Stream[]) arrayList.toArray(new Stream[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0156, code lost:
        return true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0140 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean processTopTransportConnected() {
        /*
            r11 = this;
            java.lang.Object r0 = r11._connectionLock
            monitor-enter(r0)
            fm.liveswitch.Stream[] r1 = r11.getStreams()     // Catch:{ all -> 0x0158 }
            int r2 = r1.length     // Catch:{ all -> 0x0158 }
            r3 = 0
            r4 = 0
        L_0x000a:
            if (r4 >= r2) goto L_0x0144
            r5 = r1[r4]     // Catch:{ all -> 0x0158 }
            fm.liveswitch.StreamType r6 = r5.getType()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.StreamType r7 = fm.liveswitch.StreamType.Application     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 == 0) goto L_0x0081
            boolean r6 = r5.getDisabled()     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x0081
            fm.liveswitch.StreamDirection r6 = r5.getDirection()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.Inactive     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x0081
            fm.liveswitch.DataStream r5 = (fm.liveswitch.DataStream) r5     // Catch:{ all -> 0x0158 }
            fm.liveswitch.DataStream r5 = (fm.liveswitch.DataStream) r5     // Catch:{ all -> 0x0158 }
            fm.liveswitch.DataChannel[] r6 = r5.getChannels()     // Catch:{ all -> 0x0158 }
            if (r6 == 0) goto L_0x0058
            int r6 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r6)     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x003d
            goto L_0x0058
        L_0x003d:
            fm.liveswitch.DataChannel[] r6 = r5.getChannels()     // Catch:{ all -> 0x0158 }
            int r7 = r6.length     // Catch:{ all -> 0x0158 }
            r8 = 0
        L_0x0043:
            if (r8 >= r7) goto L_0x006e
            r9 = r6[r8]     // Catch:{ all -> 0x0158 }
            fm.liveswitch.DataChannelState r9 = r9.getState()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.DataChannelState r10 = fm.liveswitch.DataChannelState.Connected     // Catch:{ all -> 0x0158 }
            boolean r9 = fm.liveswitch.Global.equals(r9, r10)     // Catch:{ all -> 0x0158 }
            if (r9 != 0) goto L_0x0055
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            return r3
        L_0x0055:
            int r8 = r8 + 1
            goto L_0x0043
        L_0x0058:
            fm.liveswitch.SctpTransport r6 = r5.getSctpTransport()     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x0060
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            return r3
        L_0x0060:
            fm.liveswitch.SctpTransportState r6 = r6.getState()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.SctpTransportState r7 = fm.liveswitch.SctpTransportState.Connected     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x006e
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            return r3
        L_0x006e:
            fm.liveswitch.StreamState r6 = r5.getState()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.StreamState r7 = fm.liveswitch.StreamState.Connecting     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 == 0) goto L_0x0140
            fm.liveswitch.StreamState r6 = fm.liveswitch.StreamState.Connected     // Catch:{ all -> 0x0158 }
            r5.setState(r6)     // Catch:{ all -> 0x0158 }
            goto L_0x0140
        L_0x0081:
            fm.liveswitch.StreamType r6 = r5.getType()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.StreamType r7 = fm.liveswitch.StreamType.Audio     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 == 0) goto L_0x00e1
            boolean r6 = r5.getDisabled()     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x00e1
            fm.liveswitch.StreamDirection r6 = r5.getDirection()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.Inactive     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x00e1
            fm.liveswitch.AudioStream r5 = (fm.liveswitch.AudioStream) r5     // Catch:{ all -> 0x0158 }
            fm.liveswitch.AudioStream r5 = (fm.liveswitch.AudioStream) r5     // Catch:{ all -> 0x0158 }
            fm.liveswitch.RtpTransport r6 = r5.getRtpTransport()     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x00ab
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            return r3
        L_0x00ab:
            fm.liveswitch.MediaTransportState r7 = r6.getState()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.MediaTransportState r8 = fm.liveswitch.MediaTransportState.Started     // Catch:{ all -> 0x0158 }
            boolean r7 = fm.liveswitch.Global.equals(r7, r8)     // Catch:{ all -> 0x0158 }
            if (r7 != 0) goto L_0x00b9
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            return r3
        L_0x00b9:
            fm.liveswitch.RtpListener r6 = r6.getListener()     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x00c1
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            return r3
        L_0x00c1:
            fm.liveswitch.MediaTransportState r6 = r6.getState()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.MediaTransportState r7 = fm.liveswitch.MediaTransportState.Started     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x00cf
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            return r3
        L_0x00cf:
            fm.liveswitch.StreamState r6 = r5.getState()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.StreamState r7 = fm.liveswitch.StreamState.Connecting     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 == 0) goto L_0x0140
            fm.liveswitch.StreamState r6 = fm.liveswitch.StreamState.Connected     // Catch:{ all -> 0x0158 }
            r5.setState(r6)     // Catch:{ all -> 0x0158 }
            goto L_0x0140
        L_0x00e1:
            fm.liveswitch.StreamType r6 = r5.getType()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.StreamType r7 = fm.liveswitch.StreamType.Video     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 == 0) goto L_0x0140
            boolean r6 = r5.getDisabled()     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x0140
            fm.liveswitch.StreamDirection r6 = r5.getDirection()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.Inactive     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x0140
            fm.liveswitch.VideoStream r5 = (fm.liveswitch.VideoStream) r5     // Catch:{ all -> 0x0158 }
            fm.liveswitch.VideoStream r5 = (fm.liveswitch.VideoStream) r5     // Catch:{ all -> 0x0158 }
            fm.liveswitch.RtpTransport r6 = r5.getRtpTransport()     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x010b
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            return r3
        L_0x010b:
            fm.liveswitch.MediaTransportState r7 = r6.getState()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.MediaTransportState r8 = fm.liveswitch.MediaTransportState.Started     // Catch:{ all -> 0x0158 }
            boolean r7 = fm.liveswitch.Global.equals(r7, r8)     // Catch:{ all -> 0x0158 }
            if (r7 != 0) goto L_0x0119
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            return r3
        L_0x0119:
            fm.liveswitch.RtpListener r6 = r6.getListener()     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x0121
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            return r3
        L_0x0121:
            fm.liveswitch.MediaTransportState r6 = r6.getState()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.MediaTransportState r7 = fm.liveswitch.MediaTransportState.Started     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 != 0) goto L_0x012f
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            return r3
        L_0x012f:
            fm.liveswitch.StreamState r6 = r5.getState()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.StreamState r7 = fm.liveswitch.StreamState.Connecting     // Catch:{ all -> 0x0158 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)     // Catch:{ all -> 0x0158 }
            if (r6 == 0) goto L_0x0140
            fm.liveswitch.StreamState r6 = fm.liveswitch.StreamState.Connected     // Catch:{ all -> 0x0158 }
            r5.setState(r6)     // Catch:{ all -> 0x0158 }
        L_0x0140:
            int r4 = r4 + 1
            goto L_0x000a
        L_0x0144:
            fm.liveswitch.ConnectionState r1 = super.getState()     // Catch:{ all -> 0x0158 }
            fm.liveswitch.ConnectionState r2 = fm.liveswitch.ConnectionState.Connecting     // Catch:{ all -> 0x0158 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0158 }
            if (r1 == 0) goto L_0x0155
            fm.liveswitch.ConnectionState r1 = fm.liveswitch.ConnectionState.Connected     // Catch:{ all -> 0x0158 }
            super.setState(r1)     // Catch:{ all -> 0x0158 }
        L_0x0155:
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            r0 = 1
            return r0
        L_0x0158:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0158 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Connection.processTopTransportConnected():boolean");
    }

    /* access modifiers changed from: protected */
    public void raiseLocalCandidate(Candidate candidate) {
        int offererStreamIndexFor = getSessionDescriptionManager().getOffererStreamIndexFor(candidate.getSdpMediaIndex());
        if (offererStreamIndexFor > -1) {
            candidate.setSdpMediaIndex(offererStreamIndexFor);
            super.raiseLocalCandidate(candidate);
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot raise local candidate. Mapping for the stream index {0} does not exist.", (Object) IntegerExtensions.toString(Integer.valueOf(candidate.getSdpMediaIndex())))));
    }

    private void registerStreams(Stream[] streamArr, boolean z) {
        if (streamArr != null) {
            int count = this.__streams.getCount();
            int length = streamArr.length;
            int i = 0;
            while (i < length) {
                AudioStream audioStream = streamArr[i];
                if (audioStream == null) {
                    throw new RuntimeException(new Exception("Cannot initialize a connection with a null stream."));
                } else if (audioStream.getConnectionId() == null) {
                    int i2 = count + 1;
                    audioStream.setIndex(count);
                    audioStream.setLocalIceParameters(getLocalIceParameters());
                    audioStream.setConnectionId(super.getId());
                    audioStream.setStateLock(this._connectionLock);
                    if (Global.equals(audioStream.getType(), StreamType.Audio)) {
                        AudioStream audioStream2 = audioStream;
                        audioStream2.removeOnBandwidthAdaptationPolicyChange(new IActionDelegate1<Stream>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processBandwidthAdaptationPolicyChanged";
                            }

                            public void invoke(Stream stream) {
                                Connection.this.processBandwidthAdaptationPolicyChanged(stream);
                            }
                        });
                        audioStream2.addOnBandwidthAdaptationPolicyChange(new IActionDelegate1<Stream>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processBandwidthAdaptationPolicyChanged";
                            }

                            public void invoke(Stream stream) {
                                Connection.this.processBandwidthAdaptationPolicyChanged(stream);
                            }
                        });
                    } else if (Global.equals(audioStream.getType(), StreamType.Video)) {
                        VideoStream videoStream = (VideoStream) audioStream;
                        videoStream.removeOnBandwidthAdaptationPolicyChange(new IActionDelegate1<Stream>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processBandwidthAdaptationPolicyChanged";
                            }

                            public void invoke(Stream stream) {
                                Connection.this.processBandwidthAdaptationPolicyChanged(stream);
                            }
                        });
                        videoStream.addOnBandwidthAdaptationPolicyChange(new IActionDelegate1<Stream>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processBandwidthAdaptationPolicyChanged";
                            }

                            public void invoke(Stream stream) {
                                Connection.this.processBandwidthAdaptationPolicyChanged(stream);
                            }
                        });
                    }
                    audioStream.setBundlePolicy(super.getBundlePolicy());
                    super.registerStreamWithSessionDescriptionManager(audioStream);
                    if (z) {
                        this.__cachedNewStreams.add(audioStream);
                    }
                    i++;
                    count = i2;
                } else {
                    throw new RuntimeException(new Exception(StringExtensions.format("Stream ({0}) is already associated with another connection.", (Object) StringExtensions.toLower(audioStream.getType().toString()))));
                }
            }
            this.__streams.addMany(streamArr);
            String connectionWideCanonicalName = super.getConnectionWideCanonicalName();
            for (DataStream dataStream : streamArr) {
                if (Global.equals(dataStream.getType(), StreamType.Application)) {
                    dataStream.setCanonicalName(connectionWideCanonicalName);
                }
            }
            updateMidPolicies();
        }
    }

    private <T> void removeFromDictionaryList(HashMap<String, ArrayList<T>> hashMap, String str, T t) {
        synchronized (this._connectionLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap, str, holder);
            ArrayList arrayList = (ArrayList) holder.getValue();
            if (tryGetValue) {
                if (arrayList != null) {
                    arrayList.remove(t);
                }
                if (arrayList == null || ArrayListExtensions.getCount(arrayList) == 0) {
                    HashMapExtensions.remove(hashMap, str);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setActiveIceKeepAliveEnabled(boolean z) {
        this.__activeIceKeepAliveEnabled = z;
    }

    public void setCreateDatagramSocket(IFunction1<DatagramSocketCreateArgs, DatagramSocket> iFunction1) {
        this._createDatagramSocket = iFunction1;
    }

    public void setCreateStreamSocket(IFunction1<StreamSocketCreateArgs, StreamSocket> iFunction1) {
        this._createStreamSocket = iFunction1;
    }

    public static void setDefaultLocalDtlsCertificate(DtlsCertificate dtlsCertificate) {
        if (dtlsCertificate == null) {
            setDefaultLocalDtlsCertificates((DtlsCertificate[]) null);
            return;
        }
        setDefaultLocalDtlsCertificates(new DtlsCertificate[]{dtlsCertificate});
    }

    public static void setDefaultLocalDtlsCertificates(DtlsCertificate[] dtlsCertificateArr) {
        _defaultLocalDtlsCertificates = dtlsCertificateArr;
    }

    private void setDescription(SessionDescription sessionDescription, boolean z) {
        SessionDescriptionManager sessionDescriptionManager = this.__sessionDescriptionManager;
        if (sessionDescriptionManager != null && sessionDescriptionManager.setDescription(sessionDescription, z)) {
            for (Stream rtpHeaderExtensionsToTransports : getStreams()) {
                setRtpHeaderExtensionsToTransports(rtpHeaderExtensionsToTransports);
            }
            startRtpTransportForCachedNewStreams();
            beginConnectingToPeer();
        }
    }

    public void setDtlsCipherSuites(DtlsCipherSuite[] dtlsCipherSuiteArr) {
        this.__cipherSuites = dtlsCipherSuiteArr;
    }

    public void setDtlsClientVersion(DtlsProtocolVersion dtlsProtocolVersion) {
        this.__dtlsClientVersion = dtlsProtocolVersion;
    }

    public void setDtlsServerMaxVersion(DtlsProtocolVersion dtlsProtocolVersion) {
        this.__dtlsServerMaxVersion = dtlsProtocolVersion;
    }

    public void setDtlsServerMinVersion(DtlsProtocolVersion dtlsProtocolVersion) {
        this.__dtlsServerMinVersion = dtlsProtocolVersion;
    }

    /* access modifiers changed from: protected */
    public void setGatheringState(IceGatheringState iceGatheringState) {
        IceGatheringState iceGatheringState2 = this.__gatheringState;
        synchronized (this._connectionLock) {
            if (!Global.equals(this.__gatheringState, iceGatheringState)) {
                this.__gatheringState = iceGatheringState;
                super.raiseGatheringStateChange(this);
            }
        }
        if (!Global.equals(iceGatheringState2, iceGatheringState)) {
            if (Global.equals(iceGatheringState, IceGatheringState.Complete)) {
                if (this.__promiseToBeResolved != null) {
                    SessionDescriptionRequirements sessionDescriptionRequirements = new SessionDescriptionRequirements(super.getTieBreaker(), super.getTrickleIcePolicy(), getMultiplexPolicy(), super.getBundlePolicy());
                    if (this.__createOffer) {
                        createOffer(sessionDescriptionRequirements, this.__promiseToBeResolved);
                    } else if (this.__createAnswer) {
                        createAnswer(sessionDescriptionRequirements, this.__promiseToBeResolved);
                    }
                    this.__promiseToBeResolved = null;
                }
            } else if (Global.equals(iceGatheringState, IceGatheringState.Failed)) {
                Promise<SessionDescription> promise = this.__promiseToBeResolved;
                if (promise != null) {
                    promise.reject(new Exception("Gathering failed."));
                }
                this.__promiseToBeResolved = null;
            } else if (Global.equals(iceGatheringState, IceGatheringState.Closing) || Global.equals(iceGatheringState, IceGatheringState.Closed)) {
                Promise<SessionDescription> promise2 = this.__promiseToBeResolved;
                if (promise2 != null) {
                    promise2.reject(new Exception("Gathering could not be completed because gathering on this connection was shutdown."));
                }
                this.__promiseToBeResolved = null;
            }
            this.__createOffer = false;
            this.__createAnswer = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void setGetInboundRtcpTransportSystemTimestamp(IFunction0<Long> iFunction0) {
        this._getInboundRtcpTransportSystemTimestamp = iFunction0;
    }

    /* access modifiers changed from: package-private */
    public void setGetInboundRtpTransportSystemTimestamp(IFunction0<Long> iFunction0) {
        this._getInboundRtpTransportSystemTimestamp = iFunction0;
    }

    /* access modifiers changed from: package-private */
    public void setGetOutboundRtcpTransportSystemTimestamp(IFunction0<Long> iFunction0) {
        this._getOutboundRtcpTransportSystemTimestamp = iFunction0;
    }

    /* access modifiers changed from: package-private */
    public void setHexDumpEnabled(boolean z) {
        this._hexDumpEnabled = z;
    }

    /* access modifiers changed from: package-private */
    public void setHexDumpPath(String str) {
        this._hexDumpPath = str;
    }

    public void setIceAddressTypes(AddressType[] addressTypeArr) {
        this._iceAddressTypes = addressTypeArr;
    }

    /* access modifiers changed from: protected */
    public void setIceConnectionState(IceConnectionState iceConnectionState) {
        if (!Global.equals(iceConnectionState, this.__iceConnectionState)) {
            this.__iceConnectionState = iceConnectionState;
            super.raiseIceConnectionStateChange(this);
        }
    }

    public void setIcePolicy(IcePolicy icePolicy) {
        this.__icePolicy = icePolicy;
    }

    public void setIcePortRange(IcePortRange icePortRange) {
        this._icePortRange = icePortRange;
    }

    private void setIceRole(IceRole iceRole) {
        this._iceRole = iceRole;
    }

    public void setKeepAliveInterval(int i) {
        this.__keepAliveInterval = i;
    }

    public void setLocalDtlsCertificate(DtlsCertificate dtlsCertificate) {
        if (dtlsCertificate == null) {
            setLocalDtlsCertificates((DtlsCertificate[]) null);
            return;
        }
        setLocalDtlsCertificates(new DtlsCertificate[]{dtlsCertificate});
    }

    public void setLocalDtlsCertificates(DtlsCertificate[] dtlsCertificateArr) {
        synchronized (this.__dtlsCertificatesLock) {
            this.__localDtlsCertificates = dtlsCertificateArr;
        }
    }

    public void setMultiplexPolicy(MultiplexPolicy multiplexPolicy) {
        this._multiplexPolicy = multiplexPolicy;
    }

    public void setOnRemoteAddStream(IFunction1<StreamDescription, Stream> iFunction1) {
        this._onRemoteAddStream = iFunction1;
    }

    public void setOnRemoteRemoveStream(IAction1<Stream> iAction1) {
        this._onRemoteRemoveStream = iAction1;
    }

    public void setPreferredDtlsRole(DtlsRole dtlsRole) {
        this.__preferredDtlsRole = dtlsRole;
    }

    public void setPrivateIPAddress(String str) {
        if (str == null) {
            setPrivateIPAddresses((String[]) null);
            return;
        }
        setPrivateIPAddresses(new String[]{str});
    }

    public void setPrivateIPAddresses(String[] strArr) {
        this._privateIPAddresses = strArr;
    }

    public void setPublicIPAddress(String str) {
        if (str == null) {
            setPublicIPAddresses((String[]) null);
            return;
        }
        setPublicIPAddresses(new String[]{str});
    }

    public void setPublicIPAddresses(String[] strArr) {
        this._publicIPAddresses = strArr;
    }

    private void setRtpHeaderExtensionsToTransports(Stream stream) {
        synchronized (this._connectionLock) {
            if (Global.equals(stream.getType(), StreamType.Audio)) {
                AudioStream audioStream = (AudioStream) stream;
                RtpTransport rtpTransport = audioStream.getRtpTransport();
                if (rtpTransport != null) {
                    rtpTransport.getExtensionParameters().setRtpHeaderExtensionRegistry(audioStream.getRtpHeaderExtensionRegistry());
                    rtpTransport.getExtensionParameters().setAbsoluteSenderTimeDirection(audioStream.getAbsoluteSenderTimeDirection());
                    rtpTransport.getExtensionParameters().setSdesMidDirection(audioStream.getSdesMidDirection());
                    rtpTransport.getExtensionParameters().setSdesRtpStreamIdDirection(audioStream.getSdesRtpStreamIdDirection());
                    rtpTransport.getExtensionParameters().setSdesRepairedRtpStreamIdDirection(audioStream.getSdesRepairedRtpStreamIdDirection());
                } else {
                    Log.error("Attempting to set RTP Header Extensions To RTP Transports, but RTP Transport is not set.");
                }
            } else if (Global.equals(stream.getType(), StreamType.Video)) {
                VideoStream videoStream = (VideoStream) stream;
                RtpTransport rtpTransport2 = videoStream.getRtpTransport();
                if (rtpTransport2 != null) {
                    rtpTransport2.getExtensionParameters().setRtpHeaderExtensionRegistry(videoStream.getRtpHeaderExtensionRegistry());
                    rtpTransport2.getExtensionParameters().setAbsoluteSenderTimeDirection(videoStream.getAbsoluteSenderTimeDirection());
                    rtpTransport2.getExtensionParameters().setSdesMidDirection(videoStream.getSdesMidDirection());
                    rtpTransport2.getExtensionParameters().setSdesRtpStreamIdDirection(videoStream.getSdesRtpStreamIdDirection());
                    rtpTransport2.getExtensionParameters().setSdesRepairedRtpStreamIdDirection(videoStream.getSdesRepairedRtpStreamIdDirection());
                } else {
                    Log.error("Attempting to set RTP Header Extensions To RTP Transports, but RTP Transport is not set.");
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setSessionDescriptionManager(SessionDescriptionManagerBase<Stream, AudioStream, VideoStream, DataStream, DataChannel> sessionDescriptionManagerBase) {
        this.__sessionDescriptionManager = (SessionDescriptionManager) sessionDescriptionManagerBase;
    }

    /* access modifiers changed from: package-private */
    public boolean setState(final ConnectionState connectionState, final Error error) {
        Scheduler scheduler = this.__scheduler;
        if ((Global.equals(connectionState, ConnectionState.Closed) || Global.equals(connectionState, ConnectionState.Failed)) && scheduler != null) {
            scheduler.stop().then(new IAction1<Object>() {
                public void invoke(Object obj) {
                    Connection.this.finalise(connectionState, error);
                }
            }).fail((IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    Connection.this.finalise(connectionState, error);
                }
            });
            return true;
        }
        if (Global.equals(connectionState, ConnectionState.Connected)) {
            scheduler.add(this.__logRTT);
        } else if (Global.equals(connectionState, ConnectionState.Failing) || Global.equals(connectionState, ConnectionState.Closing)) {
            scheduler.remove(this.__establishConnectionTimeoutSI);
            scheduler.remove(this.__logRTT);
        }
        if (Global.equals(connectionState, ConnectionState.Initializing)) {
            if (getHexDumpEnabled()) {
                this.__hexDump = new HexDump(getHexDumpPath() != null ? getHexDumpPath() : StringExtensions.concat(super.getId(), ".hexdump"));
            }
        } else if (Global.equals(connectionState, ConnectionState.Connecting)) {
            if (this.__hexDump != null) {
                long timestamp = ManagedStopwatch.getTimestamp();
                if (getLocalDescription().getIsOffer()) {
                    this.__hexDump.writeOffer(true, timestamp, getLocalDescription().getSdpMessage());
                    this.__hexDump.writeAnswer(false, timestamp, getRemoteDescription().getSdpMessage());
                } else {
                    this.__hexDump.writeOffer(false, timestamp, getRemoteDescription().getSdpMessage());
                    this.__hexDump.writeAnswer(true, timestamp, getLocalDescription().getSdpMessage());
                }
            }
        } else if ((Global.equals(connectionState, ConnectionState.Failing) || Global.equals(connectionState, ConnectionState.Closing)) && this.__hexDump != null) {
            long timestamp2 = ManagedStopwatch.getTimestamp();
            if (getLocalDescription().getIsOffer()) {
                this.__hexDump.writeBye(true, timestamp2);
            } else {
                this.__hexDump.writeBye(false, timestamp2);
            }
            this.__hexDump.close();
            this.__hexDump = null;
        }
        return super.setState(connectionState, error);
    }

    public void setStunBindingRequestLimit(int i) {
        this._stunBindingRequestLimit = i;
    }

    public void setStunRequestTimeout(int i) {
        this._stunRequestTimeout = i;
    }

    public void setSynchronizeMediaStreams(boolean z) {
        __log.warn("Setting the value of SynchronizeMediaStreams is deprecated. Use MediaStream.OutputSynchronizationDisabled instead.");
        for (Stream tryCast : getStreams()) {
            ISynchronizableStream iSynchronizableStream = (ISynchronizableStream) Global.tryCast(tryCast, ISynchronizableStream.class);
            if (iSynchronizableStream != null) {
                iSynchronizableStream.setOutputSynchronizationDisabled(!z);
            }
        }
    }

    public void setTcpConnectTimeout(int i) {
        this._tcpConnectTimeout = i;
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

    public void setTurnAllocateRequestLimit(int i) {
        this._turnAllocateRequestLimit = i;
    }

    private void shutdownOnFailure(Error error, TransportType transportType, String str) {
        synchronized (this._connectionLock) {
            if (!super.getIsTerminatingOrTerminated() && setState(ConnectionState.Failing, error)) {
                __log.error(super.getId(), StringExtensions.format("Error encountered: {0} Causing transport: {1}. Connection {2} will shut down.", error.getDescription(), transportType.toString(), super.getId()));
                simpleNonGracefulShutdown();
            }
        }
    }

    private void simpleNonGracefulShutdown() {
        CoreTransport[] coreTransportArr;
        for (Stream stream : getStreams()) {
            if (Global.equals(stream.getType(), StreamType.Application)) {
                DataStream dataStream = (DataStream) stream;
                ReliableTransport reliableDataTransport = dataStream.getReliableDataTransport();
                if (reliableDataTransport != null) {
                    reliableDataTransport.removeOnStateChange(new IActionDelegate1<ReliableTransport>() {
                        public String getId() {
                            return "fm.liveswitch.Connection.processReliableDataTransportStateChange";
                        }

                        public void invoke(ReliableTransport reliableTransport) {
                            Connection.this.processReliableDataTransportStateChange(reliableTransport);
                        }
                    });
                    reliableDataTransport.stop();
                }
                SctpTransport sctpTransport = dataStream.getSctpTransport();
                if (sctpTransport != null) {
                    sctpTransport.removeOnStateChange(new IActionDelegate1<SctpTransport>() {
                        public String getId() {
                            return "fm.liveswitch.Connection.processSctpTransportStateChange";
                        }

                        public void invoke(SctpTransport sctpTransport) {
                            Connection.this.processSctpTransportStateChange(sctpTransport);
                        }
                    });
                    sctpTransport.stop();
                }
            } else if (Global.equals(stream.getType(), StreamType.Audio)) {
                AudioStream audioStream = (AudioStream) stream;
                audioStream.setDisabled(true);
                audioStream.destroy();
                RtpTransport rtpTransport = audioStream.getRtpTransport();
                if (rtpTransport != null) {
                    rtpTransport.removeOnStateChange(new IActionDelegate1<IMediaTransport>() {
                        public String getId() {
                            return "fm.liveswitch.Connection.processMediaTransportStateChange";
                        }

                        public void invoke(IMediaTransport iMediaTransport) {
                            Connection.this.processMediaTransportStateChange(iMediaTransport);
                        }
                    });
                    rtpTransport.stop();
                    RtpListener listener = rtpTransport.getListener();
                    if (listener != null) {
                        listener.removeOnStateChange(new IActionDelegate1<RtpListener>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processRtpListenerStateChange";
                            }

                            public void invoke(RtpListener rtpListener) {
                                Connection.this.processRtpListenerStateChange(rtpListener);
                            }
                        });
                        listener.stop();
                    }
                }
            } else if (Global.equals(stream.getType(), StreamType.Video)) {
                VideoStream videoStream = (VideoStream) stream;
                videoStream.setDisabled(true);
                videoStream.destroy();
                RtpTransport rtpTransport2 = videoStream.getRtpTransport();
                if (rtpTransport2 != null) {
                    rtpTransport2.removeOnStateChange(new IActionDelegate1<IMediaTransport>() {
                        public String getId() {
                            return "fm.liveswitch.Connection.processMediaTransportStateChange";
                        }

                        public void invoke(IMediaTransport iMediaTransport) {
                            Connection.this.processMediaTransportStateChange(iMediaTransport);
                        }
                    });
                    rtpTransport2.stop();
                    RtpListener listener2 = rtpTransport2.getListener();
                    if (listener2 != null) {
                        listener2.removeOnStateChange(new IActionDelegate1<RtpListener>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processRtpListenerStateChange";
                            }

                            public void invoke(RtpListener rtpListener) {
                                Connection.this.processRtpListenerStateChange(rtpListener);
                            }
                        });
                        listener2.stop();
                    }
                }
            }
            CoreTransport coreTransportRtp = stream.getCoreTransportRtp();
            CoreTransport coreTransportRtcp = stream.getCoreTransportRtcp();
            if (coreTransportRtp != null) {
                if (coreTransportRtcp == null) {
                    coreTransportArr = new CoreTransport[]{coreTransportRtp};
                } else {
                    coreTransportArr = new CoreTransport[]{coreTransportRtp, coreTransportRtcp};
                }
                for (CoreTransport coreTransport : coreTransportArr) {
                    BundleTransport bundleTransport = coreTransport.getBundleTransport();
                    if (bundleTransport != null) {
                        bundleTransport.stop();
                        HashMapExtensions.remove(this.__bundleTransports, bundleTransport.getId());
                    }
                    DtlsTransport dtlsTransport = coreTransport.getDtlsTransport();
                    if (dtlsTransport != null) {
                        dtlsTransport.removeOnStateChange(new IActionDelegate1<DtlsTransport>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processDtlsTransportStateChange";
                            }

                            public void invoke(DtlsTransport dtlsTransport) {
                                Connection.this.processDtlsTransportStateChange(dtlsTransport);
                            }
                        });
                        dtlsTransport.stop();
                    }
                    IceTransport iceTransport = coreTransport.getIceTransport();
                    if (iceTransport != null) {
                        iceTransport.removeOnStateChange(new IActionDelegate1<IceTransport>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processIceTransportStateChange";
                            }

                            public void invoke(IceTransport iceTransport) {
                                Connection.this.processIceTransportStateChange(iceTransport);
                            }
                        });
                        iceTransport.removeOnActiveCandidatePairChange(new IActionDelegate2<IceTransport, IceCandidatePair>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processActiveCandidatePairChange";
                            }

                            public void invoke(IceTransport iceTransport, IceCandidatePair iceCandidatePair) {
                                Connection.this.processActiveCandidatePairChange(iceTransport, iceCandidatePair);
                            }
                        });
                        iceTransport.stop();
                    }
                    IceGatherer gatherer = coreTransport.getGatherer();
                    if (gatherer != null) {
                        gatherer.removeOnLocalCandidate(new IActionDelegate2<IceGatherer, IceCandidate>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processNewLocalCandidate";
                            }

                            public void invoke(IceGatherer iceGatherer, IceCandidate iceCandidate) {
                                Connection.this.processNewLocalCandidate(iceGatherer, iceCandidate);
                            }
                        });
                        gatherer.stop();
                    }
                }
            }
            ScheduledItem scheduledItem = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
                public String getId() {
                    return "fm.liveswitch.Connection.verifyGatherersDown";
                }

                public void invoke(ScheduledItem scheduledItem) {
                    Connection.this.verifyGatherersDown(scheduledItem);
                }
            }, 5000, ScheduledItem.getUnset(), ScheduledItem.getUnset(), 1);
            this.__verifyGatherersDownScheduledItem = scheduledItem;
            this.__scheduler.add(scheduledItem);
        }
    }

    private void startRtpTransport(Stream stream, int i, DataBuffer dataBuffer, DataBuffer dataBuffer2, DataBuffer dataBuffer3, DataBuffer dataBuffer4, int i2) {
        SrtpProtectionParameters srtpProtectionParameters = new SrtpProtectionParameters(i, dataBuffer, dataBuffer2);
        SrtpProtectionParameters srtpProtectionParameters2 = new SrtpProtectionParameters(i, dataBuffer3, dataBuffer4);
        if (Global.equals(stream.getType(), StreamType.Video)) {
            final VideoStream videoStream = (VideoStream) stream;
            RtpTransport rtpTransport = videoStream.getRtpTransport();
            if (rtpTransport != null) {
                rtpTransport.setConnectionId(super.getId());
                rtpTransport.setStreamId(stream.getId());
                rtpTransport.setMediaStreamType(stream.getType());
                rtpTransport.removeOnReceiveFrame(new IActionDelegate1<VideoFrame>() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<fm.liveswitch.IVideoOutput,fm.liveswitch.IVideoOutputCollection,fm.liveswitch.IVideoInput,fm.liveswitch.IVideoInputCollection,fm.liveswitch.IVideoElement,fm.liveswitch.VideoSource,fm.liveswitch.VideoSink,fm.liveswitch.VideoPipe,fm.liveswitch.VideoTrack,fm.liveswitch.VideoBranch,fm.liveswitch.VideoFrame,fm.liveswitch.VideoBuffer,fm.liveswitch.VideoBufferCollection,fm.liveswitch.VideoFormat,fm.liveswitch.VideoFormatCollection>.receiveFrame";
                    }

                    public void invoke(VideoFrame videoFrame) {
                        videoStream.receiveFrame(videoFrame);
                    }
                });
                rtpTransport.removeOnReceiveControlFrames(new IActionDelegate1<MediaControlFrame[]>() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<fm.liveswitch.IVideoOutput,fm.liveswitch.IVideoOutputCollection,fm.liveswitch.IVideoInput,fm.liveswitch.IVideoInputCollection,fm.liveswitch.IVideoElement,fm.liveswitch.VideoSource,fm.liveswitch.VideoSink,fm.liveswitch.VideoPipe,fm.liveswitch.VideoTrack,fm.liveswitch.VideoBranch,fm.liveswitch.VideoFrame,fm.liveswitch.VideoBuffer,fm.liveswitch.VideoBufferCollection,fm.liveswitch.VideoFormat,fm.liveswitch.VideoFormatCollection>.receiveControlFrames";
                    }

                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        videoStream.receiveControlFrames(mediaControlFrameArr);
                    }
                });
                rtpTransport.addOnReceiveFrame(new IActionDelegate1<VideoFrame>() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<fm.liveswitch.IVideoOutput,fm.liveswitch.IVideoOutputCollection,fm.liveswitch.IVideoInput,fm.liveswitch.IVideoInputCollection,fm.liveswitch.IVideoElement,fm.liveswitch.VideoSource,fm.liveswitch.VideoSink,fm.liveswitch.VideoPipe,fm.liveswitch.VideoTrack,fm.liveswitch.VideoBranch,fm.liveswitch.VideoFrame,fm.liveswitch.VideoBuffer,fm.liveswitch.VideoBufferCollection,fm.liveswitch.VideoFormat,fm.liveswitch.VideoFormatCollection>.receiveFrame";
                    }

                    public void invoke(VideoFrame videoFrame) {
                        videoStream.receiveFrame(videoFrame);
                    }
                });
                rtpTransport.addOnReceiveControlFrames(new IActionDelegate1<MediaControlFrame[]>() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<fm.liveswitch.IVideoOutput,fm.liveswitch.IVideoOutputCollection,fm.liveswitch.IVideoInput,fm.liveswitch.IVideoInputCollection,fm.liveswitch.IVideoElement,fm.liveswitch.VideoSource,fm.liveswitch.VideoSink,fm.liveswitch.VideoPipe,fm.liveswitch.VideoTrack,fm.liveswitch.VideoBranch,fm.liveswitch.VideoFrame,fm.liveswitch.VideoBuffer,fm.liveswitch.VideoBufferCollection,fm.liveswitch.VideoFormat,fm.liveswitch.VideoFormatCollection>.receiveControlFrames";
                    }

                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        videoStream.receiveControlFrames(mediaControlFrameArr);
                    }
                });
                rtpTransport.setParameters(videoStream.getRtpParameters());
                rtpTransport.setProtectionParameters(srtpProtectionParameters, srtpProtectionParameters2);
                videoStream.setProtectionParameters(srtpProtectionParameters, srtpProtectionParameters2);
                rtpTransport.getListener().start(i2);
                rtpTransport.start();
                return;
            }
            throw new RuntimeException(new Exception(StringExtensions.format("Could not start RTP transport for stream  {0} of type Audio - no RTP Transport assigned.", (Object) stream.getId())));
        } else if (Global.equals(stream.getType(), StreamType.Audio)) {
            final AudioStream audioStream = (AudioStream) stream;
            RtpTransport rtpTransport2 = audioStream.getRtpTransport();
            if (rtpTransport2 != null) {
                rtpTransport2.setConnectionId(super.getId());
                rtpTransport2.setStreamId(stream.getId());
                rtpTransport2.setMediaStreamType(stream.getType());
                rtpTransport2.removeOnReceiveFrame(new IActionDelegate1<AudioFrame>() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<fm.liveswitch.IAudioOutput,fm.liveswitch.IAudioOutputCollection,fm.liveswitch.IAudioInput,fm.liveswitch.IAudioInputCollection,fm.liveswitch.IAudioElement,fm.liveswitch.AudioSource,fm.liveswitch.AudioSink,fm.liveswitch.AudioPipe,fm.liveswitch.AudioTrack,fm.liveswitch.AudioBranch,fm.liveswitch.AudioFrame,fm.liveswitch.AudioBuffer,fm.liveswitch.AudioBufferCollection,fm.liveswitch.AudioFormat,fm.liveswitch.AudioFormatCollection>.receiveFrame";
                    }

                    public void invoke(AudioFrame audioFrame) {
                        audioStream.receiveFrame(audioFrame);
                    }
                });
                rtpTransport2.removeOnReceiveControlFrames(new IActionDelegate1<MediaControlFrame[]>() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<fm.liveswitch.IAudioOutput,fm.liveswitch.IAudioOutputCollection,fm.liveswitch.IAudioInput,fm.liveswitch.IAudioInputCollection,fm.liveswitch.IAudioElement,fm.liveswitch.AudioSource,fm.liveswitch.AudioSink,fm.liveswitch.AudioPipe,fm.liveswitch.AudioTrack,fm.liveswitch.AudioBranch,fm.liveswitch.AudioFrame,fm.liveswitch.AudioBuffer,fm.liveswitch.AudioBufferCollection,fm.liveswitch.AudioFormat,fm.liveswitch.AudioFormatCollection>.receiveControlFrames";
                    }

                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        audioStream.receiveControlFrames(mediaControlFrameArr);
                    }
                });
                rtpTransport2.addOnReceiveFrame(new IActionDelegate1<AudioFrame>() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<fm.liveswitch.IAudioOutput,fm.liveswitch.IAudioOutputCollection,fm.liveswitch.IAudioInput,fm.liveswitch.IAudioInputCollection,fm.liveswitch.IAudioElement,fm.liveswitch.AudioSource,fm.liveswitch.AudioSink,fm.liveswitch.AudioPipe,fm.liveswitch.AudioTrack,fm.liveswitch.AudioBranch,fm.liveswitch.AudioFrame,fm.liveswitch.AudioBuffer,fm.liveswitch.AudioBufferCollection,fm.liveswitch.AudioFormat,fm.liveswitch.AudioFormatCollection>.receiveFrame";
                    }

                    public void invoke(AudioFrame audioFrame) {
                        audioStream.receiveFrame(audioFrame);
                    }
                });
                rtpTransport2.addOnReceiveControlFrames(new IActionDelegate1<MediaControlFrame[]>() {
                    public String getId() {
                        return "fm.liveswitch.MediaStream<fm.liveswitch.IAudioOutput,fm.liveswitch.IAudioOutputCollection,fm.liveswitch.IAudioInput,fm.liveswitch.IAudioInputCollection,fm.liveswitch.IAudioElement,fm.liveswitch.AudioSource,fm.liveswitch.AudioSink,fm.liveswitch.AudioPipe,fm.liveswitch.AudioTrack,fm.liveswitch.AudioBranch,fm.liveswitch.AudioFrame,fm.liveswitch.AudioBuffer,fm.liveswitch.AudioBufferCollection,fm.liveswitch.AudioFormat,fm.liveswitch.AudioFormatCollection>.receiveControlFrames";
                    }

                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        audioStream.receiveControlFrames(mediaControlFrameArr);
                    }
                });
                rtpTransport2.setParameters(audioStream.getRtpParameters());
                rtpTransport2.setProtectionParameters(srtpProtectionParameters, srtpProtectionParameters2);
                audioStream.setProtectionParameters(srtpProtectionParameters, srtpProtectionParameters2);
                rtpTransport2.getListener().start(i2);
                rtpTransport2.start();
                return;
            }
            throw new RuntimeException(new Exception(StringExtensions.format("Could not start RTP transport for stream  {0} of type Video - no RTP Transport assigned.", (Object) stream.getId())));
        }
    }

    private void startRtpTransportForCachedNewStreams() {
        int i = 0;
        Stream[] streamArr = (Stream[]) this.__cachedNewStreams.toArray(new Stream[0]);
        this.__cachedNewStreams.clear();
        int length = streamArr.length;
        while (i < length) {
            Stream stream = streamArr[i];
            if (Global.equals(stream.getType(), StreamType.Application)) {
                throw new RuntimeException(new Exception("Adding new stream of type Application to an existing connection is not supported."));
            } else if (Global.equals(stream.getType(), StreamType.Audio) || Global.equals(stream.getType(), StreamType.Video)) {
                CoreTransport findExistingCoreTransportForBundling = findExistingCoreTransportForBundling();
                if (findExistingCoreTransportForBundling != null) {
                    Error buildMediaStream = buildMediaStream(stream, findExistingCoreTransportForBundling, (CoreTransport) null, findExistingCoreTransportForBundling);
                    if (buildMediaStream != null) {
                        shutdownOnFailure(buildMediaStream, TransportType.Unset, (String) null);
                    } else {
                        stream.setBundled(true);
                        setRtpHeaderExtensionsToTransports(stream);
                        DtlsTransport dtlsTransport = findExistingCoreTransportForBundling.getDtlsTransport();
                        DataBuffer localKey = dtlsTransport.getLocalKey();
                        DataBuffer remoteKey = dtlsTransport.getRemoteKey();
                        DataBuffer localSalt = dtlsTransport.getLocalSalt();
                        DataBuffer remoteSalt = dtlsTransport.getRemoteSalt();
                        startRtpTransport(stream, dtlsTransport.getSelectedSrtpProtectionProfile(), localKey, localSalt, remoteKey, remoteSalt, findExistingCoreTransportForBundling.getReceiveBufferSize());
                    }
                    i++;
                } else {
                    throw new RuntimeException(new Exception("Cannot find an existing CoreTransport to add a new media stream."));
                }
            } else {
                throw new RuntimeException(new Exception(StringExtensions.format("Adding new stream of type {0} to an existing connection is not supported.", (Object) stream.getType().toString())));
            }
        }
    }

    private Error startStreamCore(CoreTransport coreTransport) {
        synchronized (this._connectionLock) {
            IceGatherer gatherer = coreTransport.getGatherer();
            IceTransport iceTransport = coreTransport.getIceTransport();
            DtlsTransport dtlsTransport = coreTransport.getDtlsTransport();
            if (gatherer != null) {
                if (iceTransport != null) {
                    Holder holder = new Holder(null);
                    boolean tryGetValue = HashMapExtensions.tryGetValue(this.__streamsForIceTransport, iceTransport.getId(), holder);
                    ArrayList arrayList = (ArrayList) holder.getValue();
                    if (tryGetValue) {
                        if (ArrayListExtensions.getCount(arrayList) >= 1) {
                            if (dtlsTransport == null) {
                                Iterator it = arrayList.iterator();
                                boolean z = false;
                                while (it.hasNext()) {
                                    Stream stream = (Stream) it.next();
                                    if (!stream.getBundled()) {
                                        setIceRole(stream.getIceRole());
                                    }
                                    if (stream.getUseDtls()) {
                                        z = true;
                                    }
                                }
                                if (z) {
                                    Error error = new Error(ErrorCode.ConnectionInternalError, new Exception("Core transport not prepared prior to starting internal stream transports. Dtls transport is missing."));
                                    return error;
                                }
                            }
                            IceParameters remoteIceParameters = ((Stream) ArrayListExtensions.getItem(arrayList).get(0)).getRemoteIceParameters();
                            if (remoteIceParameters == null) {
                                Error error2 = new Error(ErrorCode.ConnectionInternalError, new Exception(StringExtensions.format("Core transport cannot start because RemoteIceParameters for this connection are not set.", new Object[0])));
                                return error2;
                            }
                            initialiseDtls(coreTransport.getIceTransport());
                            try {
                                coreTransport.getIceTransport().start(gatherer, remoteIceParameters, getIceRole(), this.__iceTieBreaker);
                                return null;
                            } catch (Exception e) {
                                return new Error(ErrorCode.ConnectionTransportStartError, e);
                            }
                        }
                    }
                    Error error3 = new Error(ErrorCode.ConnectionInternalError, new Exception(StringExtensions.format("Core transport {0} cannot start because there are no streams associated with core transport.", (Object) coreTransport.getId())));
                    return error3;
                }
            }
            Error error4 = new Error(ErrorCode.ConnectionInternalError, new Exception("Core transport not prepared prior to starting internal stream transports."));
            return error4;
        }
    }

    private boolean stopGatherer(IceGatherer iceGatherer) {
        boolean doStopGatherer;
        synchronized (this._connectionLock) {
            doStopGatherer = doStopGatherer(iceGatherer);
        }
        return doStopGatherer;
    }

    private void stopStreamCore(CoreTransport coreTransport) {
        try {
            BundleTransport bundleTransport = coreTransport.getBundleTransport();
            IceTransport iceTransport = coreTransport.getIceTransport();
            DtlsTransport dtlsTransport = coreTransport.getDtlsTransport();
            HashMapExtensions.remove(this.__streamsForIceTransport, iceTransport.getId());
            if (bundleTransport != null) {
                bundleTransport.stop();
                HashMapExtensions.remove(this.__bundleTransports, bundleTransport.getId());
            }
            if (dtlsTransport != null) {
                if (!dtlsTransport.stop()) {
                    processDtlsTransportDown(dtlsTransport);
                }
            } else if (!iceTransport.stop()) {
                processIceTransportDown(iceTransport);
            }
        } catch (Exception e) {
            __log.error(StringExtensions.format("Could not shut down Dtls transport. Will attempt to shut down ICE transport.", new Object[0]), e);
            shutdownOnFailure(new Error(ErrorCode.ConnectionInvalidArchitecture, e), TransportType.Unset, (String) null);
        }
    }

    private void streamReadyToCloseTransport(CoreTransport coreTransport) {
        RtpListener rtpListener;
        RtpListener rtpListener2;
        IceTransport iceTransport = coreTransport.getIceTransport();
        boolean z = true;
        if (iceTransport != null) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__streamsForIceTransport, iceTransport.getId(), holder);
            ArrayList arrayList = (ArrayList) holder.getValue();
            if (tryGetValue) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Stream stream = (Stream) it.next();
                    if (Global.equals(stream.getType(), StreamType.Application)) {
                        SctpTransport sctpTransport = ((DataStream) stream).getSctpTransport();
                        if (sctpTransport != null) {
                            if (!Global.equals(sctpTransport.getState(), SctpTransportState.Closed)) {
                                if (Global.equals(sctpTransport.getState(), SctpTransportState.Failed)) {
                                }
                            }
                        }
                    } else if (Global.equals(stream.getType(), StreamType.Video)) {
                        RtpTransport rtpTransport = ((VideoStream) stream).getRtpTransport();
                        if (rtpTransport == null) {
                            rtpListener2 = null;
                        } else {
                            rtpListener2 = rtpTransport.getListener();
                        }
                        if (rtpListener2 != null) {
                            if (!Global.equals(rtpListener2.getState(), MediaTransportState.Stopped)) {
                                if (Global.equals(rtpListener2.getState(), MediaTransportState.Failed)) {
                                }
                            }
                        }
                    } else if (Global.equals(stream.getType(), StreamType.Audio)) {
                        RtpTransport rtpTransport2 = ((AudioStream) stream).getRtpTransport();
                        if (rtpTransport2 == null) {
                            rtpListener = null;
                        } else {
                            rtpListener = rtpTransport2.getListener();
                        }
                        if (rtpListener != null) {
                            if (!Global.equals(rtpListener.getState(), MediaTransportState.Stopped)) {
                                if (Global.equals(rtpListener.getState(), MediaTransportState.Failed)) {
                                }
                            }
                        }
                    } else {
                        throw new RuntimeException(new Exception(StringExtensions.format("Unexpected stream type {0}", (Object) stream.getType().toString())));
                    }
                    z = false;
                }
            }
        }
        if (z) {
            stopStreamCore(coreTransport);
        }
    }

    private void unregisterStreams(Object[] objArr) {
        if (objArr != null) {
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                Stream stream = objArr[i];
                if (stream != null) {
                    if (Global.equals(stream.getType(), StreamType.Audio)) {
                        AudioStream audioStream = (AudioStream) stream;
                        audioStream.removeOnBandwidthAdaptationPolicyChange(new IActionDelegate1<Stream>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processBandwidthAdaptationPolicyChanged";
                            }

                            public void invoke(Stream stream) {
                                Connection.this.processBandwidthAdaptationPolicyChanged(stream);
                            }
                        });
                        HashMapExtensions.remove(this.__streamForAudioTransport, audioStream.getRtpTransport().getId());
                        HashMapExtensions.remove(this.__streamForAudioTransport, audioStream.getRtpTransport().getListener().getId());
                        RtpTransport rtpTransport = audioStream.getRtpTransport();
                        if (rtpTransport != null) {
                            rtpTransport.removeOnStateChange(new IActionDelegate1<IMediaTransport>() {
                                public String getId() {
                                    return "fm.liveswitch.Connection.processMediaTransportStateChange";
                                }

                                public void invoke(IMediaTransport iMediaTransport) {
                                    Connection.this.processMediaTransportStateChange(iMediaTransport);
                                }
                            });
                            RtpListener listener = rtpTransport.getListener();
                            if (listener != null) {
                                listener.removeOnStateChange(new IActionDelegate1<RtpListener>() {
                                    public String getId() {
                                        return "fm.liveswitch.Connection.processRtpListenerStateChange";
                                    }

                                    public void invoke(RtpListener rtpListener) {
                                        Connection.this.processRtpListenerStateChange(rtpListener);
                                    }
                                });
                            }
                        }
                    } else if (Global.equals(stream.getType(), StreamType.Video)) {
                        VideoStream videoStream = (VideoStream) stream;
                        videoStream.removeOnBandwidthAdaptationPolicyChange(new IActionDelegate1<Stream>() {
                            public String getId() {
                                return "fm.liveswitch.Connection.processBandwidthAdaptationPolicyChanged";
                            }

                            public void invoke(Stream stream) {
                                Connection.this.processBandwidthAdaptationPolicyChanged(stream);
                            }
                        });
                        HashMapExtensions.remove(this.__streamForVideoTransport, videoStream.getRtpTransport().getId());
                        HashMapExtensions.remove(this.__streamForVideoTransport, videoStream.getRtpTransport().getListener().getId());
                        RtpTransport rtpTransport2 = videoStream.getRtpTransport();
                        if (rtpTransport2 != null) {
                            rtpTransport2.removeOnStateChange(new IActionDelegate1<IMediaTransport>() {
                                public String getId() {
                                    return "fm.liveswitch.Connection.processMediaTransportStateChange";
                                }

                                public void invoke(IMediaTransport iMediaTransport) {
                                    Connection.this.processMediaTransportStateChange(iMediaTransport);
                                }
                            });
                            RtpListener listener2 = rtpTransport2.getListener();
                            if (listener2 != null) {
                                listener2.removeOnStateChange(new IActionDelegate1<RtpListener>() {
                                    public String getId() {
                                        return "fm.liveswitch.Connection.processRtpListenerStateChange";
                                    }

                                    public void invoke(RtpListener rtpListener) {
                                        Connection.this.processRtpListenerStateChange(rtpListener);
                                    }
                                });
                            }
                        }
                    }
                    super.unregisterStreamWithSessionDescriptionManager(stream);
                    this.__streams.remove(stream);
                    i++;
                } else {
                    throw new RuntimeException(new Exception("Cannot remove a null stream."));
                }
            }
        }
    }

    static int updateBundledStateAndGetCoreTransportIndex(BundleGroup[] bundleGroupArr, int i, Stream stream) {
        Pair<Integer, Boolean> findCoreTransportIndex;
        if (bundleGroupArr == null || (findCoreTransportIndex = findCoreTransportIndex(bundleGroupArr, stream.getMediaStreamIdentification())) == null) {
            return i;
        }
        stream.setBundled(findCoreTransportIndex.getItem2().booleanValue());
        return findCoreTransportIndex.getItem1().intValue();
    }

    /* access modifiers changed from: protected */
    public void updateBundlePolicy(BundlePolicy bundlePolicy) {
        super.updateBundlePolicy(bundlePolicy);
        for (Stream bundlePolicy2 : getStreams()) {
            bundlePolicy2.setBundlePolicy(bundlePolicy);
        }
        updateMidPolicies();
    }

    private void updateConnectionGatheringState() {
        boolean z = true;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = true;
        for (IceGatherer next : HashMapExtensions.getValues(this.__gatherers)) {
            if (!Global.equals(next.getState(), IceGatheringState.New)) {
                if (Global.equals(next.getState(), IceGatheringState.Gathering)) {
                    z = false;
                    z2 = true;
                } else if (Global.equals(next.getState(), IceGatheringState.Complete)) {
                    z = false;
                    z4 = false;
                } else if (Global.equals(next.getState(), IceGatheringState.Closing) || Global.equals(next.getState(), IceGatheringState.Closed) || Global.equals(next.getState(), IceGatheringState.Failed)) {
                    z = false;
                }
            }
            z3 = false;
            z4 = false;
        }
        if (z) {
            setGatheringState(IceGatheringState.New);
        } else if (z2) {
            setGatheringState(IceGatheringState.Gathering);
        } else if (z3 && !z4) {
            setGatheringState(IceGatheringState.Complete);
        } else if (z4) {
            setGatheringState(IceGatheringState.Closed);
        }
    }

    private void updateIceConnectionsState() {
        boolean z = true;
        boolean z2 = true;
        boolean z3 = true;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        for (IceTransport next : HashMapExtensions.getValues(this.__iceTransports)) {
            if (Global.equals(next.getState(), IceTransportState.New)) {
                z2 = false;
            } else if (Global.equals(next.getState(), IceTransportState.Checking)) {
                z = false;
                z2 = false;
                z3 = false;
                z5 = true;
            } else if (Global.equals(next.getState(), IceTransportState.Connected)) {
                z = false;
            } else if (Global.equals(next.getState(), IceTransportState.Disconnected)) {
                z = false;
                z2 = false;
                z3 = false;
                z6 = true;
            } else if (Global.equals(next.getState(), IceTransportState.Failed)) {
                z = false;
                z2 = false;
                z3 = false;
                z4 = true;
            } else if (Global.equals(next.getState(), IceTransportState.Closed)) {
                z = false;
            }
            z3 = false;
        }
        if (z) {
            setIceConnectionState(IceConnectionState.New);
        } else if (z2) {
            setIceConnectionState(IceConnectionState.Connected);
        } else if (z3) {
            setIceConnectionState(IceConnectionState.Closed);
        } else if (z4) {
            setIceConnectionState(IceConnectionState.Failed);
        } else if (z5) {
            setIceConnectionState(IceConnectionState.Checking);
        } else if (z6) {
            setIceConnectionState(IceConnectionState.Disconnected);
        }
    }

    private void updateMidPolicies() {
        synchronized (this._connectionLock) {
            if (!Global.equals(super.getBundlePolicy(), BundlePolicy.Disabled)) {
                for (Stream stream : getStreams()) {
                    if (Global.equals(stream.getType(), StreamType.Audio)) {
                        ((AudioStream) stream).setSdesMidPolicy(MediaHeaderExtensionPolicy.Negotiated);
                    } else if (Global.equals(stream.getType(), StreamType.Video)) {
                        ((VideoStream) stream).setSdesMidPolicy(MediaHeaderExtensionPolicy.Negotiated);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateRemoteCandidateIndex(Candidate candidate) {
        super.updateRemoteCandidateIndex(candidate);
        candidate.setSdpMediaIndex(getSessionDescriptionManager().getOffererStreamIndexFor(candidate.getSdpMediaIndex()));
    }

    /* access modifiers changed from: package-private */
    public void verifyConnectivity() {
        for (IceTransport verifyConnectivity : HashMapExtensions.getValues(this.__iceTransports)) {
            verifyConnectivity.verifyConnectivity();
        }
    }

    private void verifyDtlsStillNeeded() {
        synchronized (this._connectionLock) {
            ArrayList arrayList = new ArrayList();
            for (Stream stream : getStreams()) {
                if (!stream.getUseDtls()) {
                    __log.debug(StringExtensions.format("DTLS encryption is not supported for {0} stream {1}. DTLS transport will be disabled.", Global.equals(stream.getType(), StreamType.Audio) ? "audio" : Global.equals(stream.getType(), StreamType.Video) ? "video" : "data", stream.getId()));
                    CoreTransport coreTransportRtp = stream.getCoreTransportRtp();
                    CoreTransport coreTransportRtcp = stream.getCoreTransportRtcp();
                    if (coreTransportRtp != null) {
                        DtlsTransport dtlsTransport = coreTransportRtp.getDtlsTransport();
                        if (dtlsTransport != null) {
                            arrayList.add(dtlsTransport);
                        }
                        coreTransportRtp.removeDtlsTransport();
                    }
                    if (coreTransportRtcp != null) {
                        DtlsTransport dtlsTransport2 = coreTransportRtcp.getDtlsTransport();
                        if (dtlsTransport2 != null) {
                            arrayList.add(dtlsTransport2);
                        }
                        coreTransportRtcp.removeDtlsTransport();
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                DtlsTransport dtlsTransport3 = (DtlsTransport) it.next();
                HashMapExtensions.remove(this.__streamsForDtlsTransport, dtlsTransport3.getId());
                dtlsTransport3.setClosingShouldNotTriggerGlobalNonGracefulShutdown(true);
                dtlsTransport3.removeOnStateChange(new IActionDelegate1<DtlsTransport>() {
                    public String getId() {
                        return "fm.liveswitch.Connection.processDtlsTransportStateChange";
                    }

                    public void invoke(DtlsTransport dtlsTransport) {
                        Connection.this.processDtlsTransportStateChange(dtlsTransport);
                    }
                });
                dtlsTransport3.disableInnerTransport();
                dtlsTransport3.stop();
            }
        }
    }

    /* access modifiers changed from: private */
    public void verifyGatherersDown(ScheduledItem scheduledItem) {
        synchronized (this._connectionLock) {
            if (!Global.equals(super.getState(), ConnectionState.Failed) && !Global.equals(super.getState(), ConnectionState.Closed)) {
                HashMap<String, IceGatherer> hashMap = this.__gatherers;
                boolean z = true;
                if (hashMap != null) {
                    for (String str : HashMapExtensions.getKeys(hashMap)) {
                        IceGatherer iceGatherer = HashMapExtensions.getItem(this.__gatherers).get(str);
                        if (Global.equals(iceGatherer.getState(), IceGatheringState.Closed) || Global.equals(iceGatherer.getState(), IceGatheringState.Failed) || Global.equals(super.getState(), ConnectionState.Failing)) {
                            iceGatherer.removeOnLocalCandidate(new IActionDelegate2<IceGatherer, IceCandidate>() {
                                public String getId() {
                                    return "fm.liveswitch.Connection.processNewLocalCandidate";
                                }

                                public void invoke(IceGatherer iceGatherer, IceCandidate iceCandidate) {
                                    Connection.this.processNewLocalCandidate(iceGatherer, iceCandidate);
                                }
                            });
                            iceGatherer.removeOnStateChange(new IActionDelegate1<IceGatherer>() {
                                public String getId() {
                                    return "fm.liveswitch.Connection.processGathererStateChange";
                                }

                                public void invoke(IceGatherer iceGatherer) {
                                    Connection.this.processGathererStateChange(iceGatherer);
                                }
                            });
                        } else {
                            z = false;
                        }
                    }
                }
                if (z) {
                    this.__primaryCoreTransports.clear();
                    this.__secondaryCoreTransports.clear();
                    for (Stream close : (Stream[]) this.__streams.getValues()) {
                        close.close();
                    }
                    if (Global.equals(super.getState(), ConnectionState.Closing)) {
                        super.setState(ConnectionState.Closed);
                    } else if (Global.equals(super.getState(), ConnectionState.Failing)) {
                        super.setState(ConnectionState.Failed);
                    }
                    __log.debug("Connection shut down.");
                }
            }
        }
    }

    static boolean verifyRenegotiationIsValid(SessionDescription sessionDescription, SessionDescription sessionDescription2) {
        int length = ArrayExtensions.getLength((Object[]) sessionDescription.getSdpMessage().getMediaDescriptions());
        int length2 = ArrayExtensions.getLength((Object[]) sessionDescription2.getSdpMessage().getMediaDescriptions());
        boolean z = sessionDescription.getSdpMessage().getMediaDescriptions()[0].getMediaStreamIdentifierAttribute() != null;
        boolean z2 = sessionDescription2.getSdpMessage().getMediaDescriptions()[0].getMediaStreamIdentifierAttribute() != null;
        if ((!z || z2) && (z || !z2)) {
            if (length == length2) {
                String[] strArr = new String[length];
                String[] strArr2 = new String[length];
                if (!z && !z2) {
                    return true;
                }
                for (int i = 0; i < length; i++) {
                    MediaStreamIdAttribute mediaStreamIdentifierAttribute = sessionDescription.getSdpMessage().getMediaDescriptions()[i].getMediaStreamIdentifierAttribute();
                    if (mediaStreamIdentifierAttribute != null) {
                        if (!z) {
                            Log.error("Attempted to renegotiate session, where the remote description contains SDP mid attributes for some but not for all the streams.");
                            return false;
                        }
                        strArr2[i] = mediaStreamIdentifierAttribute.getIdentificationTag();
                    } else if (z) {
                        Log.error("Attempted to renegotiate session, where the remote description contains SDP mid attributes for some but not for all the streams.");
                        return false;
                    }
                    MediaStreamIdAttribute mediaStreamIdentifierAttribute2 = sessionDescription2.getSdpMessage().getMediaDescriptions()[i].getMediaStreamIdentifierAttribute();
                    if (mediaStreamIdentifierAttribute2 != null) {
                        strArr[i] = mediaStreamIdentifierAttribute2.getIdentificationTag();
                    }
                }
                boolean z3 = true;
                for (int i2 = 0; i2 < length; i2++) {
                    String str = strArr2[i2];
                    boolean z4 = false;
                    for (int i3 = 0; i3 < length; i3++) {
                        if (Global.equals(strArr[i3], str)) {
                            z4 = true;
                        }
                    }
                    z3 &= z4;
                }
                if (z3) {
                    return true;
                }
            }
            BundleGroup[] bundleGroups = sessionDescription2.getSdpMessage().getBundleGroups();
            BundleGroup[] bundleGroups2 = sessionDescription.getSdpMessage().getBundleGroups();
            if (ArrayExtensions.getLength((Object[]) bundleGroups) == 1 || ArrayExtensions.getLength((Object[]) bundleGroups2) == 1) {
                String identificationTag = bundleGroups[0].getTaggedMSection().getMediaStreamIdentifierAttribute().getIdentificationTag();
                String identificationTag2 = bundleGroups2[0].getTaggedMSection().getMediaStreamIdentifierAttribute().getIdentificationTag();
                if (Global.equals(identificationTag, identificationTag2)) {
                    return true;
                }
                Log.error(StringExtensions.format("Attempted to renegotiate session by changing the stream in tagged m section. Existing {0}, now is {1}.", identificationTag, identificationTag2));
                return false;
            }
            Log.error("Attempted to renegotiate session by changing number of streams, where bundling is either not supported or number of bundle groups is greater than 1.");
            return false;
        } else if (z) {
            Log.error("Attempted to renegotiate session, where the existing remote description did not contain SDP mid attributes, but the new remote description contains SDP mid attributes.");
            return false;
        } else {
            Log.error("Attempted to renegotiate session, where the existing remote description contained SDP mid attributes, but the new remote description did not contain SDP mid attributes.");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void virtualize(VirtualAdapter virtualAdapter) {
        this.__virtualAdapter = virtualAdapter;
        setCreateDatagramSocket(new IFunctionDelegate1<DatagramSocketCreateArgs, DatagramSocket>() {
            public String getId() {
                return "fm.liveswitch.Connection.createVirtualDatagramSocket";
            }

            public DatagramSocket invoke(DatagramSocketCreateArgs datagramSocketCreateArgs) {
                return Connection.this.createVirtualDatagramSocket(datagramSocketCreateArgs);
            }
        });
        setCreateStreamSocket(new IFunctionDelegate1<StreamSocketCreateArgs, StreamSocket>() {
            public String getId() {
                return "fm.liveswitch.Connection.createVirtualStreamSocket";
            }

            public StreamSocket invoke(StreamSocketCreateArgs streamSocketCreateArgs) {
                return Connection.this.createVirtualStreamSocket(streamSocketCreateArgs);
            }
        });
        setPrivateIPAddress(this.__virtualAdapter.getIPAddress());
    }
}
