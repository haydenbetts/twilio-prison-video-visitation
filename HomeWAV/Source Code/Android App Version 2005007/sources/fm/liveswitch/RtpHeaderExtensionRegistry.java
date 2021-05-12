package fm.liveswitch;

import java.util.HashMap;

class RtpHeaderExtensionRegistry {
    public static final int Unset = -1;
    private static ILog __log = Log.getLogger(RtpHeaderExtensionRegistry.class);
    private static int _maxIdValue = 14;
    private static int _minIdValue = 1;
    private HashMap<Integer, RtpHeaderExtensionRegistryElement> __intRegistry;
    private Object __lock;
    private HashMap<RtpHeaderExtensionType, RtpHeaderExtensionRegistryElement> __typeRegistry;

    public static StreamDirection calculateRtpHeaderDirectionRegistryEntry(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy, StreamDirection streamDirection, StreamDirection streamDirection2) {
        return convertToHeaderExtensionDirection(rtpHeaderExtensionDirectionOK(mediaHeaderExtensionPolicy, streamDirection, streamDirection2, true), rtpHeaderExtensionDirectionOK(mediaHeaderExtensionPolicy, streamDirection, streamDirection2, false));
    }

    public void clear() {
        synchronized (this.__lock) {
            this.__typeRegistry.clear();
            this.__intRegistry.clear();
        }
    }

    private static StreamDirection convertToHeaderExtensionDirection(boolean z, boolean z2) {
        if (z && z2) {
            return StreamDirection.SendReceive;
        }
        if (z) {
            return StreamDirection.SendOnly;
        }
        if (z2) {
            return StreamDirection.ReceiveOnly;
        }
        return StreamDirection.Inactive;
    }

    /* access modifiers changed from: package-private */
    public int getNumberOfNegotiatedExtensions() {
        int count;
        synchronized (this.__lock) {
            count = HashMapExtensions.getCount(this.__intRegistry);
        }
        return count;
    }

    public boolean getRtpHeaderExtensionAbsTimeRecvEnabled() {
        return headerExtensionEnabled(RtpHeaderExtensionType.AbsSendTime, false);
    }

    public boolean getRtpHeaderExtensionAbsTimeSendEnabled() {
        return headerExtensionEnabled(RtpHeaderExtensionType.AbsSendTime, true);
    }

    public boolean getRtpHeaderExtensionRepairedRtpStreamIdRecvEnabled() {
        return headerExtensionEnabled(RtpHeaderExtensionType.SdesRepairedRtpStreamId, false);
    }

    public boolean getRtpHeaderExtensionRepairedRtpStreamIdSendEnabled() {
        return headerExtensionEnabled(RtpHeaderExtensionType.SdesRepairedRtpStreamId, true);
    }

    public boolean getRtpHeaderExtensionRtpStreamIdRecvEnabled() {
        return headerExtensionEnabled(RtpHeaderExtensionType.SdesRtpStreamId, false);
    }

    public boolean getRtpHeaderExtensionRtpStreamIdSendEnabled() {
        return headerExtensionEnabled(RtpHeaderExtensionType.SdesRtpStreamId, true);
    }

    public boolean getRtpHeaderExtensionSdesMidRecvEnabled() {
        return headerExtensionEnabled(RtpHeaderExtensionType.SdesMid, false);
    }

    public boolean getRtpHeaderExtensionSdesMidSendEnabled() {
        return headerExtensionEnabled(RtpHeaderExtensionType.SdesMid, true);
    }

    private boolean headerExtensionEnabled(RtpHeaderExtensionType rtpHeaderExtensionType, boolean z) {
        RtpHeaderExtensionRegistryElement rtpHeaderExtensionRegistryElement;
        synchronized (this.__lock) {
            Holder holder = new Holder(null);
            HashMapExtensions.tryGetValue(this.__typeRegistry, rtpHeaderExtensionType, holder);
            rtpHeaderExtensionRegistryElement = (RtpHeaderExtensionRegistryElement) holder.getValue();
        }
        if (rtpHeaderExtensionRegistryElement == null) {
            return false;
        }
        if (!z) {
            return Global.equals(rtpHeaderExtensionRegistryElement.getDirection(), StreamDirection.ReceiveOnly) || Global.equals(rtpHeaderExtensionRegistryElement.getDirection(), StreamDirection.SendReceive) || Global.equals(rtpHeaderExtensionRegistryElement.getDirection(), StreamDirection.Unset);
        }
        if (Global.equals(rtpHeaderExtensionRegistryElement.getDirection(), StreamDirection.SendOnly) || Global.equals(rtpHeaderExtensionRegistryElement.getDirection(), StreamDirection.SendReceive) || Global.equals(rtpHeaderExtensionRegistryElement.getDirection(), StreamDirection.Unset)) {
            return true;
        }
        return false;
    }

    public RtpHeaderExtensionRegistryElement[] obtainRegisteredEntries() {
        RtpHeaderExtensionRegistryElement[] rtpHeaderExtensionRegistryElementArr;
        synchronized (this.__lock) {
            rtpHeaderExtensionRegistryElementArr = new RtpHeaderExtensionRegistryElement[HashMapExtensions.getCount(this.__intRegistry)];
            int i = 0;
            for (RtpHeaderExtensionRegistryElement rtpHeaderExtensionRegistryElement : HashMapExtensions.getValues(this.__intRegistry)) {
                rtpHeaderExtensionRegistryElementArr[i] = rtpHeaderExtensionRegistryElement;
                i++;
            }
        }
        return rtpHeaderExtensionRegistryElementArr;
    }

    /* access modifiers changed from: package-private */
    public void populate(RtpHeaderExtensionRegistryElement[] rtpHeaderExtensionRegistryElementArr) {
        synchronized (this.__lock) {
            clear();
            for (RtpHeaderExtensionRegistryElement rtpHeaderExtensionRegistryElement : rtpHeaderExtensionRegistryElementArr) {
                register(rtpHeaderExtensionRegistryElement.getType(), rtpHeaderExtensionRegistryElement.getDirection(), rtpHeaderExtensionRegistryElement.getUri(), rtpHeaderExtensionRegistryElement.getId());
            }
        }
    }

    public void register(RtpHeaderExtensionType rtpHeaderExtensionType, StreamDirection streamDirection, String str) {
        register(rtpHeaderExtensionType, streamDirection, str, _minIdValue);
    }

    public void register(RtpHeaderExtensionType rtpHeaderExtensionType, StreamDirection streamDirection, String str, int i) {
        synchronized (this.__lock) {
            if (this.__intRegistry.containsKey(Integer.valueOf(i)) || i > _maxIdValue) {
                i = _minIdValue;
            }
            boolean z = false;
            if (!this.__typeRegistry.containsKey(rtpHeaderExtensionType)) {
                while (!z) {
                    if (this.__intRegistry.containsKey(Integer.valueOf(i))) {
                        i++;
                        if (i > _maxIdValue) {
                            throw new RuntimeException(new Exception("Ability to register more than 14 RTP Header Extensions is not yet implemented."));
                        }
                    } else {
                        z = true;
                    }
                }
                RtpHeaderExtensionRegistryElement rtpHeaderExtensionRegistryElement = new RtpHeaderExtensionRegistryElement(i, rtpHeaderExtensionType, streamDirection, str);
                HashMapExtensions.add(this.__intRegistry, Integer.valueOf(i), rtpHeaderExtensionRegistryElement);
                HashMapExtensions.add(this.__typeRegistry, rtpHeaderExtensionType, rtpHeaderExtensionRegistryElement);
            } else {
                throw new RuntimeException(new Exception(StringExtensions.format("Attempted to add RTP Header Extension Type {0} but it already exists in the local registry.", (Object) rtpHeaderExtensionType.toString())));
            }
        }
    }

    public int registeredIdForRtpHeaderExtensionType(RtpHeaderExtensionType rtpHeaderExtensionType) {
        RtpHeaderExtensionRegistryElement rtpHeaderExtensionRegistryElement;
        synchronized (this.__lock) {
            Holder holder = new Holder(null);
            HashMapExtensions.tryGetValue(this.__typeRegistry, rtpHeaderExtensionType, holder);
            rtpHeaderExtensionRegistryElement = (RtpHeaderExtensionRegistryElement) holder.getValue();
        }
        if (rtpHeaderExtensionRegistryElement != null) {
            return rtpHeaderExtensionRegistryElement.getId();
        }
        return -1;
    }

    public RtpHeaderExtensionType registeredTypeForRtpHeaderExtensionId(int i) {
        RtpHeaderExtensionRegistryElement rtpHeaderExtensionRegistryElement;
        synchronized (this.__lock) {
            Holder holder = new Holder(null);
            HashMapExtensions.tryGetValue(this.__intRegistry, Integer.valueOf(i), holder);
            rtpHeaderExtensionRegistryElement = (RtpHeaderExtensionRegistryElement) holder.getValue();
        }
        if (rtpHeaderExtensionRegistryElement != null) {
            return rtpHeaderExtensionRegistryElement.getType();
        }
        return RtpHeaderExtensionType.Unknown;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        if (fm.liveswitch.Global.equals(r6, fm.liveswitch.StreamDirection.SendOnly) == false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0031, code lost:
        if (fm.liveswitch.Global.equals(r6, fm.liveswitch.StreamDirection.ReceiveOnly) == false) goto L_0x0025;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean rtpHeaderExtensionDirectionOK(fm.liveswitch.MediaHeaderExtensionPolicy r4, fm.liveswitch.StreamDirection r5, fm.liveswitch.StreamDirection r6, boolean r7) {
        /*
            fm.liveswitch.StreamDirection r0 = fm.liveswitch.StreamDirection.SendReceive
            boolean r0 = fm.liveswitch.Global.equals(r6, r0)
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0015
            fm.liveswitch.StreamDirection r0 = fm.liveswitch.StreamDirection.Unset
            boolean r0 = fm.liveswitch.Global.equals(r6, r0)
            if (r0 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r0 = 0
            goto L_0x0016
        L_0x0015:
            r0 = 1
        L_0x0016:
            fm.liveswitch.StreamDirection r3 = fm.liveswitch.StreamDirection.ReceiveOnly
            if (r7 == 0) goto L_0x0027
            if (r0 != 0) goto L_0x0033
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.SendOnly
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            if (r6 == 0) goto L_0x0025
            goto L_0x0033
        L_0x0025:
            r1 = 0
            goto L_0x0033
        L_0x0027:
            fm.liveswitch.StreamDirection r3 = fm.liveswitch.StreamDirection.SendOnly
            if (r0 != 0) goto L_0x0033
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.ReceiveOnly
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            if (r6 == 0) goto L_0x0025
        L_0x0033:
            fm.liveswitch.MediaHeaderExtensionPolicy r6 = fm.liveswitch.MediaHeaderExtensionPolicy.Disabled
            boolean r4 = fm.liveswitch.Global.equals(r4, r6)
            if (r4 != 0) goto L_0x0043
            boolean r4 = fm.liveswitch.Global.equals(r3, r5)
            if (r4 == 0) goto L_0x0042
            goto L_0x0043
        L_0x0042:
            return r1
        L_0x0043:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtpHeaderExtensionRegistry.rtpHeaderExtensionDirectionOK(fm.liveswitch.MediaHeaderExtensionPolicy, fm.liveswitch.StreamDirection, fm.liveswitch.StreamDirection, boolean):boolean");
    }

    public RtpHeaderExtensionRegistry() {
        this.__typeRegistry = new HashMap<>();
        this.__intRegistry = new HashMap<>();
        this.__lock = new Object();
        this.__typeRegistry = new HashMap<>();
        this.__intRegistry = new HashMap<>();
    }

    public RtpHeaderExtensionRegistry(RtpHeaderExtensionRegistryArgs rtpHeaderExtensionRegistryArgs) {
        this();
        StreamDirection streamDirection = rtpHeaderExtensionRegistryArgs.getStreamDirection();
        register(RtpHeaderExtensionType.AbsSendTime, calculateRtpHeaderDirectionRegistryEntry(rtpHeaderExtensionRegistryArgs.getAbsoluteSenderTimePolicy(), streamDirection, rtpHeaderExtensionRegistryArgs.getAbsoluteSenderTimeDirection()), "http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time");
        register(RtpHeaderExtensionType.SdesMid, calculateRtpHeaderDirectionRegistryEntry(rtpHeaderExtensionRegistryArgs.getSdesMidPolicy(), streamDirection, rtpHeaderExtensionRegistryArgs.getSdesMidDirection()), "urn:ietf:params:rtp-hdrext:sdes:mid");
        register(RtpHeaderExtensionType.SdesRtpStreamId, calculateRtpHeaderDirectionRegistryEntry(rtpHeaderExtensionRegistryArgs.getSdesRtpStreamIdPolicy(), streamDirection, rtpHeaderExtensionRegistryArgs.getSdesRtpStreamIdDirection()), "urn:ietf:params:rtp-hdrext:sdes:rtp-stream-id");
        register(RtpHeaderExtensionType.SdesRepairedRtpStreamId, calculateRtpHeaderDirectionRegistryEntry(rtpHeaderExtensionRegistryArgs.getSdesRepairedRtpStreamIdPolicy(), streamDirection, rtpHeaderExtensionRegistryArgs.getSdesRepairedRtpStreamIdDirection()), "urn:ietf:params:rtp-hdrext:sdes:repaired-rtp-stream-id");
    }

    /* access modifiers changed from: package-private */
    public void unify(RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry) {
        synchronized (this.__lock) {
            for (RtpHeaderExtensionRegistryElement rtpHeaderExtensionRegistryElement : rtpHeaderExtensionRegistry.obtainRegisteredEntries()) {
                int id = rtpHeaderExtensionRegistryElement.getId();
                Holder holder = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(this.__intRegistry, Integer.valueOf(id), holder);
                RtpHeaderExtensionRegistryElement rtpHeaderExtensionRegistryElement2 = (RtpHeaderExtensionRegistryElement) holder.getValue();
                if (!tryGetValue) {
                    register(rtpHeaderExtensionRegistryElement.getType(), rtpHeaderExtensionRegistryElement.getDirection(), rtpHeaderExtensionRegistryElement.getUri(), rtpHeaderExtensionRegistryElement.getId());
                } else if (!Global.equals(rtpHeaderExtensionRegistryElement.getType(), rtpHeaderExtensionRegistryElement2.getType())) {
                    __log.debug("Encountered a conflict trying to unify two RTP Header Extension registries. Will keep the entry from the tagged m-section of the SDP.");
                } else if (Global.equals(rtpHeaderExtensionRegistryElement.getDirection(), StreamDirection.SendReceive)) {
                    rtpHeaderExtensionRegistryElement2.setDirection(StreamDirection.SendReceive);
                } else if (Global.equals(rtpHeaderExtensionRegistryElement.getDirection(), StreamDirection.SendOnly)) {
                    if (Global.equals(rtpHeaderExtensionRegistryElement2.getDirection(), StreamDirection.ReceiveOnly)) {
                        rtpHeaderExtensionRegistryElement2.setDirection(StreamDirection.SendReceive);
                    } else if (Global.equals(rtpHeaderExtensionRegistryElement2.getDirection(), StreamDirection.Inactive) || Global.equals(rtpHeaderExtensionRegistryElement2.getDirection(), StreamDirection.Unset)) {
                        rtpHeaderExtensionRegistryElement2.setDirection(StreamDirection.SendOnly);
                    }
                } else if (Global.equals(rtpHeaderExtensionRegistryElement.getDirection(), StreamDirection.ReceiveOnly)) {
                    if (Global.equals(rtpHeaderExtensionRegistryElement2.getDirection(), StreamDirection.SendOnly)) {
                        rtpHeaderExtensionRegistryElement2.setDirection(StreamDirection.SendReceive);
                    } else if (Global.equals(rtpHeaderExtensionRegistryElement2.getDirection(), StreamDirection.Inactive) || Global.equals(rtpHeaderExtensionRegistryElement2.getDirection(), StreamDirection.Unset)) {
                        rtpHeaderExtensionRegistryElement2.setDirection(StreamDirection.ReceiveOnly);
                    }
                }
            }
        }
    }

    public void update(RtpHeaderExtensionRegistryArgs rtpHeaderExtensionRegistryArgs) {
        StreamDirection streamDirection = rtpHeaderExtensionRegistryArgs.getStreamDirection();
        StreamDirection calculateRtpHeaderDirectionRegistryEntry = calculateRtpHeaderDirectionRegistryEntry(rtpHeaderExtensionRegistryArgs.getAbsoluteSenderTimePolicy(), streamDirection, rtpHeaderExtensionRegistryArgs.getAbsoluteSenderTimeDirection());
        if (!Global.equals(calculateRtpHeaderDirectionRegistryEntry, StreamDirection.Unset)) {
            update(RtpHeaderExtensionType.AbsSendTime, calculateRtpHeaderDirectionRegistryEntry, "http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time");
        }
        StreamDirection calculateRtpHeaderDirectionRegistryEntry2 = calculateRtpHeaderDirectionRegistryEntry(rtpHeaderExtensionRegistryArgs.getSdesMidPolicy(), streamDirection, rtpHeaderExtensionRegistryArgs.getSdesMidDirection());
        if (!Global.equals(calculateRtpHeaderDirectionRegistryEntry2, StreamDirection.Unset)) {
            update(RtpHeaderExtensionType.SdesMid, calculateRtpHeaderDirectionRegistryEntry2, "urn:ietf:params:rtp-hdrext:sdes:mid");
        }
        StreamDirection calculateRtpHeaderDirectionRegistryEntry3 = calculateRtpHeaderDirectionRegistryEntry(rtpHeaderExtensionRegistryArgs.getSdesRtpStreamIdPolicy(), streamDirection, rtpHeaderExtensionRegistryArgs.getSdesRtpStreamIdDirection());
        if (!Global.equals(calculateRtpHeaderDirectionRegistryEntry3, StreamDirection.Unset)) {
            update(RtpHeaderExtensionType.SdesRtpStreamId, calculateRtpHeaderDirectionRegistryEntry3, "urn:ietf:params:rtp-hdrext:sdes:rtp-stream-id");
        }
        StreamDirection calculateRtpHeaderDirectionRegistryEntry4 = calculateRtpHeaderDirectionRegistryEntry(rtpHeaderExtensionRegistryArgs.getSdesRepairedRtpStreamIdPolicy(), streamDirection, rtpHeaderExtensionRegistryArgs.getSdesRepairedRtpStreamIdDirection());
        if (!Global.equals(calculateRtpHeaderDirectionRegistryEntry4, StreamDirection.Unset)) {
            update(RtpHeaderExtensionType.SdesRepairedRtpStreamId, calculateRtpHeaderDirectionRegistryEntry4, "urn:ietf:params:rtp-hdrext:sdes:repaired-rtp-stream-id");
        }
    }

    public void update(RtpHeaderExtensionType rtpHeaderExtensionType, StreamDirection streamDirection, String str) {
        synchronized (this.__lock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__typeRegistry, rtpHeaderExtensionType, holder);
            RtpHeaderExtensionRegistryElement rtpHeaderExtensionRegistryElement = (RtpHeaderExtensionRegistryElement) holder.getValue();
            if (!tryGetValue && !Global.equals(streamDirection, StreamDirection.Unset)) {
                register(rtpHeaderExtensionType, streamDirection, str);
            } else if (!Global.equals(streamDirection, StreamDirection.Unset)) {
                rtpHeaderExtensionRegistryElement.setDirection(streamDirection);
            } else {
                rtpHeaderExtensionRegistryElement.setDirection(StreamDirection.Inactive);
            }
        }
    }
}
