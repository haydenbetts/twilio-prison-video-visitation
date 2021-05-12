package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class BundleTransport {
    public static final String RtcpPacketLengthTag = "FM.IceLink.BundleTransport.RtcpPacketLengthKey";
    private static ILog __log = Log.getLogger(BundleTransport.class);
    private ArrayList<DataBuffer> __cachedRtcpBuffers = new ArrayList<>();
    private DtlsTransport __dtlsTransport;
    private RtpHeaderExtensionRegistry __headerExtensionRegistry;
    private IceTransport __iceTransport;
    private RtpListener __lastListener = null;
    private HashMap<String, String> __listenerIdByMid = new HashMap<>();
    private HashMap<Long, String> __listenerIdByRemoteSsrcViaPt = new HashMap<>();
    private HashMap<Long, String> __listenerIdBySsrc = new HashMap<>();
    private HashMap<Integer, ArrayList<String>> __listenerIdsByPt = new HashMap<>();
    private HashMap<String, RtpListener> __listeners = new HashMap<>();
    private SrtpProtectionParameters __localProtectionParameters;
    private Object __lock = new Object();
    private volatile boolean __processingRtpRtcp;
    private SrtpProtectionParameters __remoteProtectionParameters;
    private HashMap<Integer, LongHolder> __remoteSsrcByPt = new HashMap<>();
    private SrtpContext[] __srtpContexts = new SrtpContext[0];
    private Object __srtpContextsLock = new Object();
    private volatile boolean __stopped;
    private HexDump _hexDump;
    private String _id;
    IAction1<DataBuffer> _onProcessRtcpBuffer;

    private static void addEntryToEntries(HashMap<String, ArrayList<ControlFrameEntry>> hashMap, String str, ControlFrameEntry controlFrameEntry) {
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap, str, holder);
        ArrayList arrayList = (ArrayList) holder.getValue();
        if (tryGetValue) {
            arrayList.add(controlFrameEntry);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(controlFrameEntry);
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), str, arrayList2);
    }

    public void addRtpListener(RtpListener rtpListener, long[] jArr, long[] jArr2, int[] iArr) {
        if (!this.__stopped) {
            HashMapExtensions.set(HashMapExtensions.getItem(this.__listeners), rtpListener.getId(), rtpListener);
            this.__lastListener = rtpListener;
            if (jArr != null) {
                for (long valueOf : jArr) {
                    HashMapExtensions.set(HashMapExtensions.getItem(this.__listenerIdBySsrc), Long.valueOf(valueOf), rtpListener.getId());
                }
            }
            if (jArr2 != null) {
                for (long valueOf2 : jArr2) {
                    HashMapExtensions.set(HashMapExtensions.getItem(this.__listenerIdBySsrc), Long.valueOf(valueOf2), rtpListener.getId());
                }
            }
            if (iArr != null) {
                for (int i : iArr) {
                    synchronized (this.__lock) {
                        boolean z = false;
                        for (Integer intValue : HashMapExtensions.getKeys(this.__listenerIdsByPt)) {
                            if (intValue.intValue() == i) {
                                ArrayList arrayList = HashMapExtensions.getItem(this.__listenerIdsByPt).get(Integer.valueOf(i));
                                if (ArrayListExtensions.getCount(arrayList) > 0 && !Global.equals(ArrayListExtensions.getItem(arrayList).get(0), rtpListener.getId())) {
                                    arrayList.add(rtpListener.getId());
                                    z = true;
                                }
                            }
                        }
                        if (z) {
                            __log.debug(StringExtensions.format("Payload type {0} is already registered for an RTP Listener. Payload type matching will not be available for this payload type.", (Object) IntegerExtensions.toString(Integer.valueOf(i))));
                            Holder holder = new Holder(null);
                            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__remoteSsrcByPt, Integer.valueOf(i), holder);
                            LongHolder longHolder = (LongHolder) holder.getValue();
                            if (tryGetValue) {
                                HashMapExtensions.remove(this.__remoteSsrcByPt, Integer.valueOf(i));
                                HashMapExtensions.remove(this.__listenerIdByRemoteSsrcViaPt, Long.valueOf(longHolder.getValue()));
                            }
                        } else {
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(rtpListener.getId());
                            HashMapExtensions.set(HashMapExtensions.getItem(this.__listenerIdsByPt), Integer.valueOf(i), arrayList2);
                        }
                    }
                }
            }
            if (this.__stopped) {
                stop();
            }
        }
    }

    public void addRtpListener(RtpListener rtpListener, String str) {
        if (!this.__stopped) {
            this.__lastListener = rtpListener;
            HashMapExtensions.set(HashMapExtensions.getItem(this.__listeners), rtpListener.getId(), rtpListener);
            if (str != null) {
                HashMapExtensions.set(HashMapExtensions.getItem(this.__listenerIdByMid), str, rtpListener.getId());
            }
            if (this.__stopped) {
                stop();
            }
        }
    }

    private void addToDistributionList(HashMap<String, ArrayList<MediaControlFrame>> hashMap, String str, MediaControlFrame mediaControlFrame) {
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap, str, holder);
        ArrayList arrayList = (ArrayList) holder.getValue();
        if (!tryGetValue) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(mediaControlFrame);
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), str, arrayList2);
        } else if (!arrayList.contains(mediaControlFrame)) {
            arrayList.add(mediaControlFrame);
        }
    }

    private void addToDistributionList(HashMap<String, ArrayList<MediaControlFrame>> hashMap, long j, MediaControlFrame mediaControlFrame) {
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__listenerIdBySsrc, Long.valueOf(j), holder);
        Holder holder2 = new Holder((String) holder.getValue());
        boolean tryGetValue2 = HashMapExtensions.tryGetValue(this.__listenerIdByRemoteSsrcViaPt, Long.valueOf(j), holder2);
        String str = (String) holder2.getValue();
        if (tryGetValue || tryGetValue2) {
            addToDistributionList(hashMap, str, mediaControlFrame);
        }
    }

    public BundleTransport(IceTransport iceTransport, DtlsTransport dtlsTransport, HexDump hexDump) {
        setId(Utility.generateId());
        setIceTransport(iceTransport);
        setDtlsTransport(dtlsTransport);
        setHexDump(hexDump);
    }

    private boolean cacheRtcp(DataBuffer dataBuffer) {
        dataBuffer.keep();
        this.__cachedRtcpBuffers.add(dataBuffer);
        return true;
    }

    private void clearCachedRtcpBuffers() {
        Iterator<DataBuffer> it = this.__cachedRtcpBuffers.iterator();
        while (it.hasNext()) {
            it.next().free();
        }
        this.__cachedRtcpBuffers.clear();
    }

    private SrtpContext createSrtpContext(long j) {
        SrtpProtectionParameters srtpProtectionParameters;
        SrtpProtectionParameters srtpProtectionParameters2 = this.__localProtectionParameters;
        if (srtpProtectionParameters2 == null || (srtpProtectionParameters = this.__remoteProtectionParameters) == null) {
            return null;
        }
        SrtpContext srtpContext = new SrtpContext(srtpProtectionParameters2.getProtectionProfileString(), srtpProtectionParameters2.getKey(), srtpProtectionParameters2.getSalt(), srtpProtectionParameters.getKey(), srtpProtectionParameters.getSalt());
        srtpContext.setRemoteSynchronizationSource(j);
        return srtpContext;
    }

    /* access modifiers changed from: package-private */
    public boolean demultiplexIncomingRtpPayload(DataBuffer dataBuffer) {
        if (this.__stopped) {
            return false;
        }
        this.__processingRtpRtcp = true;
        try {
            if (this.__stopped) {
                return false;
            }
            if (!processRtp(dataBuffer)) {
                processRtcp(dataBuffer);
            }
            this.__processingRtpRtcp = false;
            return true;
        } finally {
            this.__processingRtpRtcp = false;
        }
    }

    private void destroySrtpContext(SrtpContext srtpContext) {
        srtpContext.destroy();
    }

    private boolean doProcessRtcp(DataBuffer dataBuffer) {
        IAction1<DataBuffer> iAction1 = this._onProcessRtcpBuffer;
        if (iAction1 != null) {
            iAction1.invoke(dataBuffer);
        }
        RtcpReportPacketHeader readFrom = RtcpReportPacketHeader.readFrom(dataBuffer);
        if (readFrom == null || readFrom.getVersion() != 2 || (readFrom.getLength() + 1) * 4 > dataBuffer.getLength()) {
            return false;
        }
        long timestamp = ManagedStopwatch.getTimestamp();
        long synchronizationSource = readFrom.getSynchronizationSource();
        SrtpContext srtpContext = getSrtpContext(synchronizationSource, true);
        if (srtpContext == null) {
            __log.warn(LongExtensions.toString(Long.valueOf(synchronizationSource)), "Discarding inbound control frame(s). Missing decryption context.");
            return false;
        }
        RtcpPacket[] decryptRtcp = srtpContext.decryptRtcp(dataBuffer);
        if (decryptRtcp == null) {
            __log.warn(LongExtensions.toString(Long.valueOf(synchronizationSource)), "Discarding inbound control frame(s). Decryption failed.");
            return false;
        }
        HexDump hexDump = getHexDump();
        if (hexDump != null) {
            hexDump.writeRtcp(false, ManagedStopwatch.getTimestamp(), decryptRtcp);
        }
        HashMap<String, ArrayList<MediaControlFrame>> parseMediaControlFrames = parseMediaControlFrames(MediaControlFrame.parse(decryptRtcp), synchronizationSource, dataBuffer.getLength());
        for (String next : HashMapExtensions.getKeys(parseMediaControlFrames)) {
            ArrayList arrayList = HashMapExtensions.getItem(parseMediaControlFrames).get(next);
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__listeners, next, holder);
            RtpListener rtpListener = (RtpListener) holder.getValue();
            if (tryGetValue) {
                RtpInboundRtcp rtpInboundRtcp = new RtpInboundRtcp();
                rtpInboundRtcp.setFrames((MediaControlFrame[]) arrayList.toArray(new MediaControlFrame[0]));
                rtpInboundRtcp.setNetworkSystemTimestamp(timestamp);
                rtpListener.processRtcp(rtpInboundRtcp);
            }
        }
        return true;
    }

    private RtpListener findListener(long j) {
        RtpListener rtpListener;
        if (HashMapExtensions.getCount(this.__listeners) != 1 || (rtpListener = this.__lastListener) == null) {
            rtpListener = null;
        }
        if (rtpListener == null) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__listenerIdBySsrc, Long.valueOf(j), holder);
            String str = (String) holder.getValue();
            if (tryGetValue) {
                Holder holder2 = new Holder(rtpListener);
                HashMapExtensions.tryGetValue(this.__listeners, str, holder2);
                rtpListener = (RtpListener) holder2.getValue();
            }
        }
        if (rtpListener != null) {
            return rtpListener;
        }
        Holder holder3 = new Holder(null);
        boolean tryGetValue2 = HashMapExtensions.tryGetValue(this.__listenerIdByRemoteSsrcViaPt, Long.valueOf(j), holder3);
        String str2 = (String) holder3.getValue();
        if (!tryGetValue2) {
            return rtpListener;
        }
        Holder holder4 = new Holder(rtpListener);
        HashMapExtensions.tryGetValue(this.__listeners, str2, holder4);
        return (RtpListener) holder4.getValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e6, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.RtpListener findListener(long r9, int r11, java.lang.String r12) {
        /*
            r8 = this;
            fm.liveswitch.RtpListener r0 = r8.findListener(r9)
            r1 = 0
            if (r0 != 0) goto L_0x0054
            if (r12 == 0) goto L_0x0054
            fm.liveswitch.Holder r2 = new fm.liveswitch.Holder
            r2.<init>(r1)
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r8.__listenerIdByMid
            boolean r3 = fm.liveswitch.HashMapExtensions.tryGetValue(r3, r12, r2)
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2
            fm.liveswitch.Holder r4 = new fm.liveswitch.Holder
            r4.<init>(r0)
            java.util.HashMap<java.lang.String, fm.liveswitch.RtpListener> r0 = r8.__listeners
            boolean r0 = fm.liveswitch.HashMapExtensions.tryGetValue(r0, r2, r4)
            java.lang.Object r4 = r4.getValue()
            fm.liveswitch.RtpListener r4 = (fm.liveswitch.RtpListener) r4
            if (r3 == 0) goto L_0x0053
            if (r0 == 0) goto L_0x0053
            fm.liveswitch.ILog r0 = __log
            java.lang.String r3 = r8.getId()
            java.lang.String r5 = "Associated SSRC {0} with stream having mid {1}."
            java.lang.Long r6 = java.lang.Long.valueOf(r9)
            java.lang.String r6 = fm.liveswitch.LongExtensions.toString(r6)
            java.lang.String r12 = fm.liveswitch.StringExtensions.format(r5, r6, r12)
            r0.debug((java.lang.String) r3, (java.lang.String) r12)
            java.util.HashMap<java.lang.Long, java.lang.String> r12 = r8.__listenerIdBySsrc
            java.util.HashMap r12 = fm.liveswitch.HashMapExtensions.getItem(r12)
            java.lang.Long r0 = java.lang.Long.valueOf(r9)
            fm.liveswitch.HashMapExtensions.set(r12, r0, r2)
        L_0x0053:
            r0 = r4
        L_0x0054:
            if (r0 != 0) goto L_0x00ea
            java.lang.Object r12 = r8.__lock
            monitor-enter(r12)
            fm.liveswitch.Holder r2 = new fm.liveswitch.Holder     // Catch:{ all -> 0x00e7 }
            r2.<init>(r1)     // Catch:{ all -> 0x00e7 }
            java.util.HashMap<java.lang.Integer, java.util.ArrayList<java.lang.String>> r1 = r8.__listenerIdsByPt     // Catch:{ all -> 0x00e7 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00e7 }
            boolean r1 = fm.liveswitch.HashMapExtensions.tryGetValue(r1, r3, r2)     // Catch:{ all -> 0x00e7 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x00e7 }
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ all -> 0x00e7 }
            fm.liveswitch.Holder r3 = new fm.liveswitch.Holder     // Catch:{ all -> 0x00e7 }
            r3.<init>(r0)     // Catch:{ all -> 0x00e7 }
            java.util.HashMap<java.lang.String, fm.liveswitch.RtpListener> r0 = r8.__listeners     // Catch:{ all -> 0x00e7 }
            java.util.ArrayList r4 = fm.liveswitch.ArrayListExtensions.getItem(r2)     // Catch:{ all -> 0x00e7 }
            r5 = 0
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x00e7 }
            boolean r0 = fm.liveswitch.HashMapExtensions.tryGetValue(r0, r4, r3)     // Catch:{ all -> 0x00e7 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x00e7 }
            fm.liveswitch.RtpListener r3 = (fm.liveswitch.RtpListener) r3     // Catch:{ all -> 0x00e7 }
            if (r1 == 0) goto L_0x00e5
            if (r2 == 0) goto L_0x00e5
            int r1 = fm.liveswitch.ArrayListExtensions.getCount(r2)     // Catch:{ all -> 0x00e7 }
            if (r1 <= 0) goto L_0x00e5
            if (r0 != 0) goto L_0x0095
            goto L_0x00e5
        L_0x0095:
            int r0 = fm.liveswitch.ArrayListExtensions.getCount(r2)     // Catch:{ all -> 0x00e7 }
            r1 = 2
            if (r0 >= r1) goto L_0x00e2
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x00e7 }
            java.lang.String r1 = r8.getId()     // Catch:{ all -> 0x00e7 }
            java.lang.String r4 = "Associated SSRC {0} with stream having payload type {1}."
            java.lang.Long r6 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x00e7 }
            java.lang.String r6 = fm.liveswitch.LongExtensions.toString(r6)     // Catch:{ all -> 0x00e7 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00e7 }
            java.lang.String r7 = fm.liveswitch.IntegerExtensions.toString(r7)     // Catch:{ all -> 0x00e7 }
            java.lang.String r4 = fm.liveswitch.StringExtensions.format(r4, r6, r7)     // Catch:{ all -> 0x00e7 }
            r0.debug((java.lang.String) r1, (java.lang.String) r4)     // Catch:{ all -> 0x00e7 }
            java.util.HashMap<java.lang.Integer, fm.liveswitch.LongHolder> r0 = r8.__remoteSsrcByPt     // Catch:{ all -> 0x00e7 }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ all -> 0x00e7 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00e7 }
            fm.liveswitch.LongHolder r1 = new fm.liveswitch.LongHolder     // Catch:{ all -> 0x00e7 }
            r1.<init>(r9)     // Catch:{ all -> 0x00e7 }
            fm.liveswitch.HashMapExtensions.set(r0, r11, r1)     // Catch:{ all -> 0x00e7 }
            java.util.HashMap<java.lang.Long, java.lang.String> r11 = r8.__listenerIdByRemoteSsrcViaPt     // Catch:{ all -> 0x00e7 }
            java.util.HashMap r11 = fm.liveswitch.HashMapExtensions.getItem(r11)     // Catch:{ all -> 0x00e7 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x00e7 }
            java.util.ArrayList r10 = fm.liveswitch.ArrayListExtensions.getItem(r2)     // Catch:{ all -> 0x00e7 }
            java.lang.Object r10 = r10.get(r5)     // Catch:{ all -> 0x00e7 }
            fm.liveswitch.HashMapExtensions.set(r11, r9, r10)     // Catch:{ all -> 0x00e7 }
        L_0x00e2:
            monitor-exit(r12)     // Catch:{ all -> 0x00e7 }
            r0 = r3
            goto L_0x00ea
        L_0x00e5:
            monitor-exit(r12)     // Catch:{ all -> 0x00e7 }
            return r3
        L_0x00e7:
            r9 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x00e7 }
            throw r9
        L_0x00ea:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.BundleTransport.findListener(long, int, java.lang.String):fm.liveswitch.RtpListener");
    }

    /* access modifiers changed from: package-private */
    public int getCachedRtcpBufferCount() {
        return ArrayListExtensions.getCount(this.__cachedRtcpBuffers);
    }

    /* access modifiers changed from: package-private */
    public DtlsTransport getDtlsTransport() {
        return this.__dtlsTransport;
    }

    public RtpHeaderExtensionRegistry getHeaderExtensionRegistry() {
        return this.__headerExtensionRegistry;
    }

    public HexDump getHexDump() {
        return this._hexDump;
    }

    /* access modifiers changed from: package-private */
    public IceTransport getIceTransport() {
        return this.__iceTransport;
    }

    public String getId() {
        return this._id;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.SrtpContext getOrAddSrtpContextWithLock(long r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.__srtpContextsLock
            monitor-enter(r0)
            fm.liveswitch.SrtpContext r1 = r6.getSrtpContext(r7)     // Catch:{ all -> 0x0035 }
            if (r1 != 0) goto L_0x0033
            fm.liveswitch.SrtpContext r1 = r6.createSrtpContext(r7)     // Catch:{ all -> 0x0035 }
            if (r1 != 0) goto L_0x0012
            r7 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x0035 }
            return r7
        L_0x0012:
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x0035 }
            r7.<init>()     // Catch:{ all -> 0x0035 }
            fm.liveswitch.SrtpContext[] r8 = r6.__srtpContexts     // Catch:{ all -> 0x0035 }
            int r2 = r8.length     // Catch:{ all -> 0x0035 }
            r3 = 0
            r4 = 0
        L_0x001c:
            if (r4 >= r2) goto L_0x0026
            r5 = r8[r4]     // Catch:{ all -> 0x0035 }
            r7.add(r5)     // Catch:{ all -> 0x0035 }
            int r4 = r4 + 1
            goto L_0x001c
        L_0x0026:
            r7.add(r1)     // Catch:{ all -> 0x0035 }
            fm.liveswitch.SrtpContext[] r8 = new fm.liveswitch.SrtpContext[r3]     // Catch:{ all -> 0x0035 }
            java.lang.Object[] r7 = r7.toArray(r8)     // Catch:{ all -> 0x0035 }
            fm.liveswitch.SrtpContext[] r7 = (fm.liveswitch.SrtpContext[]) r7     // Catch:{ all -> 0x0035 }
            r6.__srtpContexts = r7     // Catch:{ all -> 0x0035 }
        L_0x0033:
            monitor-exit(r0)     // Catch:{ all -> 0x0035 }
            return r1
        L_0x0035:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0035 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.BundleTransport.getOrAddSrtpContextWithLock(long):fm.liveswitch.SrtpContext");
    }

    private SrtpContext getSrtpContext(long j) {
        return getSrtpContext(j, false);
    }

    private SrtpContext getSrtpContext(long j, boolean z) {
        for (SrtpContext srtpContext : this.__srtpContexts) {
            if (srtpContext.getRemoteSynchronizationSource() == j) {
                return srtpContext;
            }
        }
        if (z) {
            return getOrAddSrtpContextWithLock(j);
        }
        return null;
    }

    private HashMap<String, ArrayList<MediaControlFrame>> parseMediaControlFrames(MediaControlFrame[] mediaControlFrameArr, long j, int i) {
        long j2;
        MediaControlFrame[] mediaControlFrameArr2 = mediaControlFrameArr;
        long j3 = j;
        HashMap<String, ArrayList<MediaControlFrame>> hashMap = new HashMap<>();
        int length = mediaControlFrameArr2.length;
        int i2 = 0;
        while (i2 < length) {
            MediaControlFrame mediaControlFrame = mediaControlFrameArr2[i2];
            int payloadType = mediaControlFrame.getPayloadType();
            if (payloadType == SRControlFrame.getRegisteredPayloadType()) {
                SRControlFrame sRControlFrame = (SRControlFrame) mediaControlFrame;
                sRControlFrame.setDynamicValue(RtcpPacketLengthTag, new IntegerHolder(i));
                long synchronizationSource = sRControlFrame.getSynchronizationSource();
                RtpListener findListener = findListener(synchronizationSource);
                if (findListener != null) {
                    j2 = synchronizationSource;
                    addToDistributionList(hashMap, findListener.getId(), (MediaControlFrame) new SRControlFrame(synchronizationSource, sRControlFrame.getNtpTimestamp(), sRControlFrame.getRtpTimestamp(), sRControlFrame.getPacketCount(), sRControlFrame.getOctetCount()));
                } else {
                    j2 = synchronizationSource;
                }
                HashMap<String, ArrayList<ControlFrameEntry>> processReportControlFrame = processReportControlFrame(sRControlFrame);
                for (String next : HashMapExtensions.getKeys(processReportControlFrame)) {
                    ArrayList arrayList = HashMapExtensions.getItem(processReportControlFrame).get(next);
                    ReportBlock[] reportBlockArr = new ReportBlock[ArrayListExtensions.getCount(arrayList)];
                    for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) reportBlockArr); i3++) {
                        reportBlockArr[i3] = (ReportBlock) ArrayListExtensions.getItem(arrayList).get(i3);
                    }
                    RRControlFrame rRControlFrame = new RRControlFrame(reportBlockArr);
                    rRControlFrame.setSynchronizationSource(j2);
                    addToDistributionList(hashMap, next, (MediaControlFrame) rRControlFrame);
                }
            } else {
                int i4 = i;
                if (payloadType == RRControlFrame.getRegisteredPayloadType()) {
                    HashMap<String, ArrayList<ControlFrameEntry>> processReportControlFrame2 = processReportControlFrame((RRControlFrame) mediaControlFrame);
                    for (String next2 : HashMapExtensions.getKeys(processReportControlFrame2)) {
                        ArrayList arrayList2 = HashMapExtensions.getItem(processReportControlFrame2).get(next2);
                        ReportBlock[] reportBlockArr2 = new ReportBlock[ArrayListExtensions.getCount(arrayList2)];
                        for (int i5 = 0; i5 < ArrayExtensions.getLength((Object[]) reportBlockArr2); i5++) {
                            reportBlockArr2[i5] = (ReportBlock) ArrayListExtensions.getItem(arrayList2).get(i5);
                        }
                        addToDistributionList(hashMap, next2, (MediaControlFrame) new RRControlFrame(reportBlockArr2));
                    }
                } else if (payloadType == SdesControlFrame.getRegisteredPayloadType()) {
                    addToDistributionList(hashMap, j3, mediaControlFrame);
                } else if (payloadType == ByeControlFrame.getRegisteredPayloadType()) {
                    for (long addToDistributionList : ((ByeControlFrame) mediaControlFrame).getSources()) {
                        addToDistributionList(hashMap, addToDistributionList, mediaControlFrame);
                    }
                } else if (payloadType == AppControlFrame.getRegisteredPayloadType()) {
                    addToDistributionList(hashMap, ((AppControlFrame) mediaControlFrame).getSynchronizationSource(), mediaControlFrame);
                } else if (payloadType == PayloadSpecificControlFrame.getRegisteredPayloadType()) {
                    if (mediaControlFrame.getByte1Last5Bits() == RembControlFrame.getRegisteredFeedbackMessageType()) {
                        for (long addToDistributionList2 : ((RembControlFrame) mediaControlFrame).getSsrcEntries()) {
                            addToDistributionList(hashMap, addToDistributionList2, mediaControlFrame);
                        }
                    } else if (mediaControlFrame.getByte1Last5Bits() == FirControlFrame.getRegisteredFeedbackMessageType()) {
                        HashMap<String, ArrayList<ControlFrameEntry>> processFirControlFrame = processFirControlFrame((FirControlFrame) mediaControlFrame);
                        for (String next3 : HashMapExtensions.getKeys(processFirControlFrame)) {
                            ArrayList arrayList3 = HashMapExtensions.getItem(processFirControlFrame).get(next3);
                            FirEntry[] firEntryArr = new FirEntry[ArrayListExtensions.getCount(arrayList3)];
                            long j4 = 0;
                            for (int i6 = 0; i6 < ArrayExtensions.getLength((Object[]) firEntryArr); i6++) {
                                firEntryArr[i6] = (FirEntry) ArrayListExtensions.getItem(arrayList3).get(i6);
                                j4 = firEntryArr[i6].getSynchronizationSource();
                            }
                            FirControlFrame firControlFrame = new FirControlFrame(firEntryArr);
                            firControlFrame.setMediaSourceSynchronizationSource(j4);
                            firControlFrame.setPacketSenderSynchronizationSource(j3);
                            addToDistributionList(hashMap, next3, (MediaControlFrame) firControlFrame);
                            MediaControlFrame[] mediaControlFrameArr3 = mediaControlFrameArr;
                        }
                    } else if (mediaControlFrame.getByte1Last5Bits() == LrrControlFrame.getRegisteredFeedbackMessageType()) {
                        HashMap<String, ArrayList<ControlFrameEntry>> processLrrControlFrame = processLrrControlFrame((LrrControlFrame) mediaControlFrame);
                        for (String next4 : HashMapExtensions.getKeys(processLrrControlFrame)) {
                            ArrayList arrayList4 = HashMapExtensions.getItem(processLrrControlFrame).get(next4);
                            LrrEntry[] lrrEntryArr = new LrrEntry[ArrayListExtensions.getCount(arrayList4)];
                            long j5 = 0;
                            for (int i7 = 0; i7 < ArrayExtensions.getLength((Object[]) lrrEntryArr); i7++) {
                                lrrEntryArr[i7] = (LrrEntry) ArrayListExtensions.getItem(arrayList4).get(i7);
                                j5 = lrrEntryArr[i7].getSynchronizationSource();
                            }
                            LrrControlFrame lrrControlFrame = new LrrControlFrame(lrrEntryArr);
                            lrrControlFrame.setMediaSourceSynchronizationSource(j5);
                            lrrControlFrame.setPacketSenderSynchronizationSource(j3);
                            addToDistributionList(hashMap, next4, (MediaControlFrame) lrrControlFrame);
                        }
                    } else {
                        addToDistributionList(hashMap, ((PayloadSpecificControlFrame) mediaControlFrame).getMediaSourceSynchronizationSource(), mediaControlFrame);
                    }
                } else if (payloadType != RtpControlFrame.getRegisteredPayloadType()) {
                    addToDistributionList(hashMap, j3, mediaControlFrame);
                } else if (mediaControlFrame.getByte1Last5Bits() == TmmbrControlFrame.getRegisteredFeedbackMessageType()) {
                    HashMap<String, ArrayList<ControlFrameEntry>> processTmmbrControlFrame = processTmmbrControlFrame((TmmbrControlFrame) mediaControlFrame);
                    for (String next5 : HashMapExtensions.getKeys(processTmmbrControlFrame)) {
                        ArrayList arrayList5 = HashMapExtensions.getItem(processTmmbrControlFrame).get(next5);
                        TmmbrEntry[] tmmbrEntryArr = new TmmbrEntry[ArrayListExtensions.getCount(arrayList5)];
                        long j6 = 0;
                        for (int i8 = 0; i8 < ArrayExtensions.getLength((Object[]) tmmbrEntryArr); i8++) {
                            tmmbrEntryArr[i8] = (TmmbrEntry) ArrayListExtensions.getItem(arrayList5).get(i8);
                            j6 = tmmbrEntryArr[i8].getSynchronizationSource();
                        }
                        TmmbrControlFrame tmmbrControlFrame = new TmmbrControlFrame(tmmbrEntryArr);
                        tmmbrControlFrame.setMediaSourceSynchronizationSource(j6);
                        tmmbrControlFrame.setPacketSenderSynchronizationSource(j3);
                        addToDistributionList(hashMap, next5, (MediaControlFrame) tmmbrControlFrame);
                    }
                } else if (mediaControlFrame.getByte1Last5Bits() == TmmbnControlFrame.getRegisteredFeedbackMessageType()) {
                    HashMap<String, ArrayList<ControlFrameEntry>> processTmmbnControlFrame = processTmmbnControlFrame((TmmbnControlFrame) mediaControlFrame);
                    for (String next6 : HashMapExtensions.getKeys(processTmmbnControlFrame)) {
                        ArrayList arrayList6 = HashMapExtensions.getItem(processTmmbnControlFrame).get(next6);
                        TmmbnEntry[] tmmbnEntryArr = new TmmbnEntry[ArrayListExtensions.getCount(arrayList6)];
                        long j7 = 0;
                        for (int i9 = 0; i9 < ArrayExtensions.getLength((Object[]) tmmbnEntryArr); i9++) {
                            tmmbnEntryArr[i9] = (TmmbnEntry) ArrayListExtensions.getItem(arrayList6).get(i9);
                            j7 = tmmbnEntryArr[i9].getSynchronizationSource();
                        }
                        TmmbnControlFrame tmmbnControlFrame = new TmmbnControlFrame(tmmbnEntryArr);
                        tmmbnControlFrame.setMediaSourceSynchronizationSource(j7);
                        tmmbnControlFrame.setPacketSenderSynchronizationSource(j3);
                        addToDistributionList(hashMap, next6, (MediaControlFrame) tmmbnControlFrame);
                    }
                } else {
                    addToDistributionList(hashMap, ((RtpControlFrame) mediaControlFrame).getMediaSourceSynchronizationSource(), mediaControlFrame);
                }
            }
            i2++;
            mediaControlFrameArr2 = mediaControlFrameArr;
        }
        return hashMap;
    }

    private HashMap<String, ArrayList<ControlFrameEntry>> processFirControlFrame(FirControlFrame firControlFrame) {
        long packetSenderSynchronizationSource = firControlFrame.getPacketSenderSynchronizationSource();
        FirEntry[] entries = firControlFrame.getEntries();
        HashMap<String, ArrayList<ControlFrameEntry>> hashMap = new HashMap<>();
        for (FirEntry firEntry : entries) {
            long synchronizationSource = firEntry.getSynchronizationSource();
            long synchronizationSource2 = firEntry.getSynchronizationSource();
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__listenerIdBySsrc, Long.valueOf(synchronizationSource2), holder);
            String str = (String) holder.getValue();
            if (tryGetValue) {
                addEntryToEntries(hashMap, str, firEntry);
            } else {
                __log.warn(LongExtensions.toString(Long.valueOf(packetSenderSynchronizationSource)), StringExtensions.format("Discarding FIR entry for unrecognized SSRC {0}.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource))));
            }
        }
        return hashMap;
    }

    public void processIncomingData(DataBuffer dataBuffer) {
        if (dataBuffer != null && !this.__stopped) {
            int read8 = dataBuffer.read8(0);
            if (read8 <= 3) {
                __log.debug(getId(), StringExtensions.format("Discarding packet with unexpected first byte {0} (indicates STUN).", (Object) IntegerExtensions.toString(Integer.valueOf(read8))));
            } else if (read8 >= 16 && read8 <= 19) {
                __log.debug(getId(), StringExtensions.format("Discarding packet with unexpected first byte {0} (indicates ZRTP).", (Object) IntegerExtensions.toString(Integer.valueOf(read8))));
            } else if (read8 >= 20 && read8 <= 63) {
                this.__dtlsTransport.processReceive(dataBuffer);
            } else if (read8 >= 64 && read8 <= 79) {
                __log.debug(getId(), StringExtensions.format("Discarding packet with unexpected first byte {0} (indicates TURN).", (Object) IntegerExtensions.toString(Integer.valueOf(read8))));
            } else if (read8 < 128 || read8 > 191) {
                __log.debug(getId(), StringExtensions.format("Discarding packet with unexpected first byte {0}.", (Object) IntegerExtensions.toString(Integer.valueOf(read8))));
            } else {
                demultiplexIncomingRtpPayload(dataBuffer);
            }
            if (this.__stopped) {
                stop();
            }
        }
    }

    private HashMap<String, ArrayList<ControlFrameEntry>> processLrrControlFrame(LrrControlFrame lrrControlFrame) {
        long packetSenderSynchronizationSource = lrrControlFrame.getPacketSenderSynchronizationSource();
        LrrEntry[] entries = lrrControlFrame.getEntries();
        HashMap<String, ArrayList<ControlFrameEntry>> hashMap = new HashMap<>();
        for (LrrEntry lrrEntry : entries) {
            long synchronizationSource = lrrEntry.getSynchronizationSource();
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__listenerIdBySsrc, Long.valueOf(synchronizationSource), holder);
            String str = (String) holder.getValue();
            if (tryGetValue) {
                addEntryToEntries(hashMap, str, lrrEntry);
            } else {
                __log.warn(LongExtensions.toString(Long.valueOf(packetSenderSynchronizationSource)), StringExtensions.format("Discarding LRR entry for unrecognized SSRC {0}.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource))));
            }
        }
        return hashMap;
    }

    private HashMap<String, ArrayList<ControlFrameEntry>> processReportControlFrame(ReportControlFrame reportControlFrame) {
        long synchronizationSource = reportControlFrame.getSynchronizationSource();
        ReportBlock[] reportBlocks = reportControlFrame.getReportBlocks();
        HashMap<String, ArrayList<ControlFrameEntry>> hashMap = new HashMap<>();
        for (ReportBlock reportBlock : reportBlocks) {
            long synchronizationSource2 = reportBlock.getSynchronizationSource();
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__listenerIdBySsrc, Long.valueOf(synchronizationSource2), holder);
            String str = (String) holder.getValue();
            if (tryGetValue) {
                addEntryToEntries(hashMap, str, reportBlock);
            } else {
                __log.warn(LongExtensions.toString(Long.valueOf(synchronizationSource)), StringExtensions.format("Discarding report block for unrecognized SSRC {0}.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource2))));
            }
        }
        return hashMap;
    }

    private boolean processRtcp(DataBuffer dataBuffer) {
        if (this.__localProtectionParameters == null || this.__remoteProtectionParameters == null) {
            cacheRtcp(dataBuffer);
            return true;
        }
        if (ArrayListExtensions.getCount(this.__cachedRtcpBuffers) > 0) {
            uncacheRtcpPackets();
        }
        doProcessRtcp(dataBuffer);
        return true;
    }

    private boolean processRtp(DataBuffer dataBuffer) {
        RtpListener rtpListener;
        RtpHeaderExtension parseRawHeaderExtension;
        RtpHeaderSdesMid sdesMid;
        RtpPacketHeader readFrom = RtpPacketHeader.readFrom(dataBuffer);
        if (readFrom == null || readFrom.getVersion() != 2) {
            return false;
        }
        if (readFrom.getPayloadType() >= 66 && readFrom.getPayloadType() <= 95) {
            return false;
        }
        long timestamp = ManagedStopwatch.getTimestamp();
        long synchronizationSource = readFrom.getSynchronizationSource();
        String str = null;
        RtpRawHeaderExtension rtpRawHeaderExtension = (RtpRawHeaderExtension) Global.tryCast(readFrom.getHeaderExtension(), RtpRawHeaderExtension.class);
        if (!(rtpRawHeaderExtension == null || getHeaderExtensionRegistry() == null || getHeaderExtensionRegistry().getNumberOfNegotiatedExtensions() <= 0 || (parseRawHeaderExtension = RtpHeaderExtension.parseRawHeaderExtension(rtpRawHeaderExtension, getHeaderExtensionRegistry())) == null || (sdesMid = parseRawHeaderExtension.getSdesMid()) == null)) {
            str = sdesMid.getText();
        }
        int payloadType = readFrom.getPayloadType();
        RtpListener findListener = findListener(synchronizationSource, payloadType, str);
        if (findListener == null && HashMapExtensions.getCount(this.__listeners) == 1 && (rtpListener = this.__lastListener) != null) {
            findListener = rtpListener;
        }
        if (findListener != null) {
            RtpInboundRtp rtpInboundRtp = new RtpInboundRtp();
            rtpInboundRtp.setHeader(readFrom);
            rtpInboundRtp.setBuffer(dataBuffer);
            rtpInboundRtp.setNetworkSystemTimestamp(timestamp);
            findListener.processRtp(rtpInboundRtp);
        } else if (str == null) {
            __log.debug(getId(), StringExtensions.format("Discarding RTP packet with unrecognized SSRC {0} and payload type {1}.", LongExtensions.toString(Long.valueOf(synchronizationSource)), IntegerExtensions.toString(Integer.valueOf(payloadType))));
        } else {
            __log.debug(getId(), StringExtensions.format("Discarding RTP packet with unrecognized SSRC {0}, payload type {1} and mid {2}.", LongExtensions.toString(Long.valueOf(synchronizationSource)), IntegerExtensions.toString(Integer.valueOf(payloadType)), str));
        }
        return true;
    }

    private HashMap<String, ArrayList<ControlFrameEntry>> processTmmbnControlFrame(TmmbnControlFrame tmmbnControlFrame) {
        long packetSenderSynchronizationSource = tmmbnControlFrame.getPacketSenderSynchronizationSource();
        TmmbnEntry[] entries = tmmbnControlFrame.getEntries();
        HashMap<String, ArrayList<ControlFrameEntry>> hashMap = new HashMap<>();
        for (TmmbnEntry tmmbnEntry : entries) {
            long synchronizationSource = tmmbnEntry.getSynchronizationSource();
            long synchronizationSource2 = tmmbnEntry.getSynchronizationSource();
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__listenerIdBySsrc, Long.valueOf(synchronizationSource2), holder);
            String str = (String) holder.getValue();
            if (tryGetValue) {
                addEntryToEntries(hashMap, str, tmmbnEntry);
            } else {
                __log.warn(LongExtensions.toString(Long.valueOf(packetSenderSynchronizationSource)), StringExtensions.format("Discarding TMMBN entry for unrecognized SSRC {0}.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource))));
            }
        }
        return hashMap;
    }

    private HashMap<String, ArrayList<ControlFrameEntry>> processTmmbrControlFrame(TmmbrControlFrame tmmbrControlFrame) {
        long packetSenderSynchronizationSource = tmmbrControlFrame.getPacketSenderSynchronizationSource();
        TmmbrEntry[] entries = tmmbrControlFrame.getEntries();
        HashMap<String, ArrayList<ControlFrameEntry>> hashMap = new HashMap<>();
        for (TmmbrEntry tmmbrEntry : entries) {
            long synchronizationSource = tmmbrEntry.getSynchronizationSource();
            long synchronizationSource2 = tmmbrEntry.getSynchronizationSource();
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__listenerIdBySsrc, Long.valueOf(synchronizationSource2), holder);
            String str = (String) holder.getValue();
            if (tryGetValue) {
                addEntryToEntries(hashMap, str, tmmbrEntry);
            } else {
                __log.warn(LongExtensions.toString(Long.valueOf(packetSenderSynchronizationSource)), StringExtensions.format("Discarding TMMBR entry for unrecognized SSRC {0}.", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource))));
            }
        }
        return hashMap;
    }

    public void removeRtpListener(String str) {
        if (!this.__stopped) {
            if (str != null) {
                Holder holder = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(this.__listenerIdByMid, str, holder);
                String str2 = (String) holder.getValue();
                if (tryGetValue) {
                    HashMapExtensions.remove(this.__listenerIdByMid, str2);
                    HashMapExtensions.remove(this.__listenerIdByMid, str);
                }
            }
            if (HashMapExtensions.getCount(this.__listeners) == 0) {
                this.__lastListener = null;
            }
            if (this.__stopped) {
                stop();
            }
        }
    }

    private boolean removeSrtpContext(long j) {
        synchronized (this.__srtpContextsLock) {
            SrtpContext srtpContext = getSrtpContext(j);
            if (srtpContext == null) {
                return false;
            }
            destroySrtpContext(srtpContext);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void setDtlsTransport(DtlsTransport dtlsTransport) {
        this.__dtlsTransport = dtlsTransport;
    }

    public void setHeaderExtensionRegistry(RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry) {
        this.__headerExtensionRegistry = rtpHeaderExtensionRegistry;
    }

    private void setHexDump(HexDump hexDump) {
        this._hexDump = hexDump;
    }

    /* access modifiers changed from: package-private */
    public void setIceTransport(IceTransport iceTransport) {
        if (!Global.equals(iceTransport, this.__iceTransport)) {
            IceTransport iceTransport2 = this.__iceTransport;
            if (iceTransport2 != null) {
                iceTransport2.removeOnReceive(new IActionDelegate1<DataBuffer>() {
                    public String getId() {
                        return "fm.liveswitch.BundleTransport.processIncomingData";
                    }

                    public void invoke(DataBuffer dataBuffer) {
                        BundleTransport.this.processIncomingData(dataBuffer);
                    }
                });
            }
            if (iceTransport != null) {
                iceTransport.removeOnReceive(new IActionDelegate1<DataBuffer>() {
                    public String getId() {
                        return "fm.liveswitch.BundleTransport.processIncomingData";
                    }

                    public void invoke(DataBuffer dataBuffer) {
                        BundleTransport.this.processIncomingData(dataBuffer);
                    }
                });
                iceTransport.addOnReceive(new IActionDelegate1<DataBuffer>() {
                    public String getId() {
                        return "fm.liveswitch.BundleTransport.processIncomingData";
                    }

                    public void invoke(DataBuffer dataBuffer) {
                        BundleTransport.this.processIncomingData(dataBuffer);
                    }
                });
            }
        }
        this.__iceTransport = iceTransport;
    }

    private void setId(String str) {
        this._id = str;
    }

    public void setProtectionParameters(SrtpProtectionParameters srtpProtectionParameters, SrtpProtectionParameters srtpProtectionParameters2) {
        this.__localProtectionParameters = srtpProtectionParameters;
        this.__remoteProtectionParameters = srtpProtectionParameters2;
    }

    public boolean stop() {
        if (this.__stopped) {
            return false;
        }
        this.__stopped = true;
        while (this.__processingRtpRtcp) {
            ManagedThread.sleep(10);
        }
        this.__lastListener = null;
        this.__listeners.clear();
        this.__listenerIdByMid.clear();
        this.__listenerIdBySsrc.clear();
        this.__listenerIdsByPt.clear();
        this.__listenerIdByRemoteSsrcViaPt.clear();
        this.__remoteSsrcByPt.clear();
        clearCachedRtcpBuffers();
        for (SrtpContext destroySrtpContext : this.__srtpContexts) {
            destroySrtpContext(destroySrtpContext);
        }
        setDtlsTransport((DtlsTransport) null);
        setIceTransport((IceTransport) null);
        return true;
    }

    private void uncacheRtcpPackets() {
        Iterator<DataBuffer> it = this.__cachedRtcpBuffers.iterator();
        while (it.hasNext()) {
            doProcessRtcp(it.next());
        }
        clearCachedRtcpBuffers();
    }
}
