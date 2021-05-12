package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class SctpTransport {
    static final int DefaultPort = 5000;
    private static ILog __log = Log.getLogger(SctpTransport.class);
    private boolean __dataRetransmission;
    private SctpErrorChunk __errorToCombineWithCookieEcho;
    private ScheduledItem __initiationControlChunkScheduledItem;
    private BundleTransport __innerTransport;
    private BundleTransport __innerTransportAlternative;
    private long __maxMessageSize;
    private SctpDataChunk __nextDataChunkToBeExaminedForSending;
    private int __numberOfPacketsSentSinceLastProcessorYield;
    /* access modifiers changed from: private */
    public List<IAction1<SctpTransport>> __onStateChange;
    private ScheduledItem __outgoingQueueScheduledItem;
    private SctpReceiveDataQueue __receiveDataQueue;
    private Scheduler __scheduler;
    private SctpSendControlChunkQueue __sendControlChunkQueue;
    private SctpSendDataQueue __sendDataQueue;
    private Object __stateLock;
    private SctpTransmissionControlBlock __tcb;
    private Error _error;
    private String _id;
    private IAction1<SctpMessage> _onMessage;
    private IAction1<SctpTransport> _onStateChange;
    private int _port;

    private int getNewDataPacketCountTrigger() {
        return 4;
    }

    private int getT3TimerExtension() {
        return 1000;
    }

    public static int getUnset() {
        return -1;
    }

    private long translateIndextoTSN(int i, boolean z, long j) {
        if (!z) {
            return j + ((long) i);
        }
        long j2 = j + ((long) i);
        return j2 <= 4294967295L ? j2 : (j2 - 4294967295L) - 1;
    }

    public void addOnStateChange(IAction1<SctpTransport> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<SctpTransport>() {
                    public void invoke(SctpTransport sctpTransport) {
                        Iterator it = new ArrayList(SctpTransport.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(sctpTransport);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    private void assembleMessage(Holder<byte[]> holder, Holder<long[]> holder2, long j) {
        int i;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ByteCollection byteCollection = new ByteCollection();
        synchronized (this.__stateLock) {
            boolean z = false;
            while (!z) {
                arrayList.add(this.__receiveDataQueue.getChunk(j));
                arrayList2.add(new LongHolder(j));
                if (!this.__receiveDataQueue.getChunk(j).getBeginning()) {
                    j = SctpDataChunk.decrementTSN(j);
                    if (!this.__receiveDataQueue.chunkExists(j)) {
                        __log.error("SCTP: While assembling, did not encounter the beginning of the message in the receiving queue.");
                    }
                }
                z = true;
            }
        }
        for (int count = ArrayListExtensions.getCount(arrayList) - 1; count > -1; count--) {
            byteCollection.addRange(((SctpDataChunk) ArrayListExtensions.getItem(arrayList).get(count)).getUserData());
        }
        holder.setValue(byteCollection.toArray());
        holder2.setValue(new long[ArrayListExtensions.getCount(arrayList2)]);
        for (i = 0; i < ArrayExtensions.getLength(holder2.getValue()); i++) {
            holder2.getValue()[i] = ((LongHolder) ArrayListExtensions.getItem(arrayList2).get(i)).getValue();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:145:0x02dc  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01db  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean buildSctpPacket(fm.liveswitch.Holder<fm.liveswitch.SctpPacket> r20, boolean r21) {
        /*
            r19 = this;
            r1 = r19
            r0 = r20
            fm.liveswitch.SctpTransmissionControlBlock r2 = r1.__tcb
            long r2 = r2.getPeerVerificationTag()
            fm.liveswitch.ILog r4 = __log
            boolean r4 = r4.getIsVerboseEnabled()
            if (r4 == 0) goto L_0x0025
            fm.liveswitch.ILog r4 = __log
            java.lang.String r5 = "Building outgoing SCTP packet. Peer verification tag is set to {0}."
            java.lang.Long r6 = java.lang.Long.valueOf(r2)
            java.lang.String r6 = fm.liveswitch.LongExtensions.toString(r6)
            java.lang.String r5 = fm.liveswitch.StringExtensions.format((java.lang.String) r5, (java.lang.Object) r6)
            r4.verbose(r5)
        L_0x0025:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.lang.Object r5 = r1.__stateLock
            monitor-enter(r5)
            fm.liveswitch.SctpTransmissionControlBlock r6 = r1.__tcb     // Catch:{ all -> 0x032a }
            boolean r6 = r6.getRemoteLikelyInConnectedState()     // Catch:{ all -> 0x032a }
            r7 = 0
            r8 = 1
            if (r6 != 0) goto L_0x0055
            fm.liveswitch.SctpSendDataQueue r6 = r1.__sendDataQueue     // Catch:{ all -> 0x032a }
            int r6 = r6.getCount()     // Catch:{ all -> 0x032a }
            if (r6 <= 0) goto L_0x0055
            fm.liveswitch.SctpTransportState r6 = r19.getState()     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpTransportState r9 = fm.liveswitch.SctpTransportState.Connected     // Catch:{ all -> 0x032a }
            boolean r6 = fm.liveswitch.Global.equals(r6, r9)     // Catch:{ all -> 0x032a }
            if (r6 == 0) goto L_0x0055
            fm.liveswitch.SctpTransmissionControlBlock r6 = r1.__tcb     // Catch:{ all -> 0x032a }
            boolean r6 = r6.getCookieAckSent()     // Catch:{ all -> 0x032a }
            if (r6 == 0) goto L_0x0055
            r6 = 1
            goto L_0x0056
        L_0x0055:
            r6 = 0
        L_0x0056:
            fm.liveswitch.SctpSendControlChunkQueue r9 = r1.__sendControlChunkQueue     // Catch:{ all -> 0x032a }
            int r9 = r9.getCount()     // Catch:{ all -> 0x032a }
            r10 = 0
            r11 = 1050(0x41a, float:1.471E-42)
            if (r9 > 0) goto L_0x0069
            if (r6 == 0) goto L_0x0064
            goto L_0x0069
        L_0x0064:
            r12 = r10
            r6 = 1
        L_0x0066:
            r9 = 0
            goto L_0x0139
        L_0x0069:
            fm.liveswitch.SctpSendControlChunkQueue r9 = r1.__sendControlChunkQueue     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpControlChunk r9 = r9.dequeue()     // Catch:{ all -> 0x032a }
            if (r9 != 0) goto L_0x0095
            if (r6 == 0) goto L_0x0095
            fm.liveswitch.SctpTransmissionControlBlock r6 = r1.__tcb     // Catch:{ all -> 0x032a }
            boolean r6 = r6.getRemoteLikelyInConnectedState()     // Catch:{ all -> 0x032a }
            if (r6 != 0) goto L_0x00db
            fm.liveswitch.SctpSendDataQueue r6 = r1.__sendDataQueue     // Catch:{ all -> 0x032a }
            int r6 = r6.getCount()     // Catch:{ all -> 0x032a }
            if (r6 <= 0) goto L_0x00db
            fm.liveswitch.SctpTransportState r6 = r19.getState()     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpTransportState r12 = fm.liveswitch.SctpTransportState.Connected     // Catch:{ all -> 0x032a }
            boolean r6 = fm.liveswitch.Global.equals(r6, r12)     // Catch:{ all -> 0x032a }
            if (r6 == 0) goto L_0x00db
            fm.liveswitch.SctpCookieAckChunk r9 = new fm.liveswitch.SctpCookieAckChunk     // Catch:{ all -> 0x032a }
            r9.<init>()     // Catch:{ all -> 0x032a }
            goto L_0x00db
        L_0x0095:
            int r6 = r9.getType()     // Catch:{ all -> 0x032a }
            byte r12 = fm.liveswitch.SctpChunkType.getInitiationAck()     // Catch:{ all -> 0x032a }
            if (r6 != r12) goto L_0x00cc
            r2 = r9
            fm.liveswitch.SctpInitAckChunk r2 = (fm.liveswitch.SctpInitAckChunk) r2     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpInitAckChunk r2 = (fm.liveswitch.SctpInitAckChunk) r2     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpStateCookieChunkParameter r2 = r2.getStateCookieChunk()     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpStateCookie r2 = r2.getStateCookie()     // Catch:{ all -> 0x032a }
            long r2 = r2.getPeerVerificationTag()     // Catch:{ all -> 0x032a }
            fm.liveswitch.ILog r6 = __log     // Catch:{ all -> 0x032a }
            boolean r6 = r6.getIsVerboseEnabled()     // Catch:{ all -> 0x032a }
            if (r6 == 0) goto L_0x00db
            fm.liveswitch.ILog r6 = __log     // Catch:{ all -> 0x032a }
            java.lang.String r12 = "Updating peer verification tag to {0}."
            java.lang.Long r13 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x032a }
            java.lang.String r13 = fm.liveswitch.LongExtensions.toString(r13)     // Catch:{ all -> 0x032a }
            java.lang.String r12 = fm.liveswitch.StringExtensions.format((java.lang.String) r12, (java.lang.Object) r13)     // Catch:{ all -> 0x032a }
            r6.verbose(r12)     // Catch:{ all -> 0x032a }
            goto L_0x00db
        L_0x00cc:
            int r6 = r9.getType()     // Catch:{ all -> 0x032a }
            byte r12 = fm.liveswitch.SctpChunkType.getCookieAck()     // Catch:{ all -> 0x032a }
            if (r6 != r12) goto L_0x00db
            fm.liveswitch.SctpTransmissionControlBlock r6 = r1.__tcb     // Catch:{ all -> 0x032a }
            r6.setCookieAckSent(r8)     // Catch:{ all -> 0x032a }
        L_0x00db:
            fm.liveswitch.ILog r6 = __log     // Catch:{ all -> 0x032a }
            boolean r6 = r6.getIsVerboseEnabled()     // Catch:{ all -> 0x032a }
            if (r6 == 0) goto L_0x00fa
            fm.liveswitch.ILog r6 = __log     // Catch:{ all -> 0x032a }
            java.lang.String r12 = "Outgoing SCTP packet will contain control chunk of type {0}."
            int r13 = r9.getType()     // Catch:{ all -> 0x032a }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x032a }
            java.lang.String r13 = fm.liveswitch.IntegerExtensions.toString(r13)     // Catch:{ all -> 0x032a }
            java.lang.String r12 = fm.liveswitch.StringExtensions.format((java.lang.String) r12, (java.lang.Object) r13)     // Catch:{ all -> 0x032a }
            r6.verbose(r12)     // Catch:{ all -> 0x032a }
        L_0x00fa:
            boolean r6 = r9.getCanBundleWithDataAndSackChunks()     // Catch:{ all -> 0x032a }
            r6 = r6 & r8
            r4.add(r9)     // Catch:{ all -> 0x032a }
            fm.liveswitch.ScheduledItem r12 = r9.getResendScheduledItem()     // Catch:{ all -> 0x032a }
            int r13 = r9.getType()     // Catch:{ all -> 0x032a }
            byte r14 = fm.liveswitch.SctpChunkType.getCookieEcho()     // Catch:{ all -> 0x032a }
            if (r13 != r14) goto L_0x0130
            fm.liveswitch.SctpErrorChunk r13 = r1.__errorToCombineWithCookieEcho     // Catch:{ all -> 0x032a }
            if (r13 == 0) goto L_0x0130
            byte[] r9 = r9.getBytes()     // Catch:{ all -> 0x032a }
            int r9 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r9)     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpErrorChunk r13 = r1.__errorToCombineWithCookieEcho     // Catch:{ all -> 0x032a }
            byte[] r13 = r13.getBytes()     // Catch:{ all -> 0x032a }
            int r13 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r13)     // Catch:{ all -> 0x032a }
            int r9 = r9 + r13
            if (r9 > r11) goto L_0x0130
            fm.liveswitch.SctpErrorChunk r9 = r1.__errorToCombineWithCookieEcho     // Catch:{ all -> 0x032a }
            r4.add(r9)     // Catch:{ all -> 0x032a }
            r1.__errorToCombineWithCookieEcho = r10     // Catch:{ all -> 0x032a }
        L_0x0130:
            fm.liveswitch.SctpSendControlChunkQueue r9 = r1.__sendControlChunkQueue     // Catch:{ all -> 0x032a }
            int r9 = r9.getCount()     // Catch:{ all -> 0x032a }
            if (r9 <= 0) goto L_0x0066
            r9 = 1
        L_0x0139:
            fm.liveswitch.SctpTransmissionControlBlock r13 = r1.__tcb     // Catch:{ all -> 0x032a }
            long r13 = r13.getEarliestAllowedSackSendTime()     // Catch:{ all -> 0x032a }
            int r15 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x032a }
            r16 = r9
            long r8 = (long) r15     // Catch:{ all -> 0x032a }
            int r15 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1))
            if (r15 == 0) goto L_0x01c8
            fm.liveswitch.SctpTransmissionControlBlock r8 = r1.__tcb     // Catch:{ all -> 0x032a }
            long r8 = r8.getEarliestAllowedSackSendTime()     // Catch:{ all -> 0x032a }
            long r13 = fm.liveswitch.Scheduler.getCurrentTime()     // Catch:{ all -> 0x032a }
            int r15 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r15 >= 0) goto L_0x01c8
            fm.liveswitch.SctpTransmissionControlBlock r8 = r1.__tcb     // Catch:{ all -> 0x032a }
            int r8 = r8.getSackCounter()     // Catch:{ all -> 0x032a }
            if (r8 <= 0) goto L_0x01c8
            if (r6 == 0) goto L_0x01c5
            java.util.Iterator r8 = r4.iterator()     // Catch:{ all -> 0x032a }
            r9 = 0
        L_0x0167:
            boolean r13 = r8.hasNext()     // Catch:{ all -> 0x032a }
            if (r13 == 0) goto L_0x017d
            java.lang.Object r13 = r8.next()     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpChunk r13 = (fm.liveswitch.SctpChunk) r13     // Catch:{ all -> 0x032a }
            byte[] r13 = r13.getBytes()     // Catch:{ all -> 0x032a }
            int r13 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r13)     // Catch:{ all -> 0x032a }
            int r9 = r9 + r13
            goto L_0x0167
        L_0x017d:
            java.lang.Object r8 = r1.__stateLock     // Catch:{ all -> 0x032a }
            monitor-enter(r8)     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpReceiveDataQueue r13 = r1.__receiveDataQueue     // Catch:{ all -> 0x01c2 }
            fm.liveswitch.SctpSackChunk r13 = r13.getSackChunk()     // Catch:{ all -> 0x01c2 }
            monitor-exit(r8)     // Catch:{ all -> 0x01c2 }
            byte[] r8 = r13.getBytes(r11)     // Catch:{ all -> 0x032a }
            int r8 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r8)     // Catch:{ all -> 0x032a }
            int r8 = r8 + r9
            if (r8 >= r11) goto L_0x01bc
            fm.liveswitch.SctpTransmissionControlBlock r8 = r1.__tcb     // Catch:{ all -> 0x032a }
            r8.setSackCounter(r7)     // Catch:{ all -> 0x032a }
            fm.liveswitch.ILog r8 = __log     // Catch:{ all -> 0x032a }
            boolean r8 = r8.getIsVerboseEnabled()     // Catch:{ all -> 0x032a }
            if (r8 == 0) goto L_0x01ae
            fm.liveswitch.ILog r8 = __log     // Catch:{ all -> 0x032a }
            java.lang.String r14 = "SCTP: Adding {0} to outgoing packet."
            java.lang.String r15 = r13.toString()     // Catch:{ all -> 0x032a }
            java.lang.String r14 = fm.liveswitch.StringExtensions.format((java.lang.String) r14, (java.lang.Object) r15)     // Catch:{ all -> 0x032a }
            r8.verbose(r14)     // Catch:{ all -> 0x032a }
        L_0x01ae:
            r4.add(r13)     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpTransmissionControlBlock r8 = r1.__tcb     // Catch:{ all -> 0x032a }
            int r13 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x032a }
            long r13 = (long) r13     // Catch:{ all -> 0x032a }
            r8.setEarliestAllowedSackSendTime(r13)     // Catch:{ all -> 0x032a }
            goto L_0x01be
        L_0x01bc:
            r16 = 1
        L_0x01be:
            r8 = r9
            r9 = r16
            goto L_0x01cb
        L_0x01c2:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x01c2 }
            throw r0     // Catch:{ all -> 0x032a }
        L_0x01c5:
            r8 = 0
            r9 = 1
            goto L_0x01cb
        L_0x01c8:
            r9 = r16
            r8 = 0
        L_0x01cb:
            fm.liveswitch.SctpSendDataQueue r13 = r1.__sendDataQueue     // Catch:{ all -> 0x032a }
            int r13 = r13.getCount()     // Catch:{ all -> 0x032a }
            if (r13 <= 0) goto L_0x02df
            boolean r13 = r19.get_CanSendDataChunksInState()     // Catch:{ all -> 0x032a }
            if (r13 == 0) goto L_0x02df
            if (r6 == 0) goto L_0x02dc
            java.util.Iterator r6 = r4.iterator()     // Catch:{ all -> 0x032a }
        L_0x01df:
            boolean r13 = r6.hasNext()     // Catch:{ all -> 0x032a }
            if (r13 == 0) goto L_0x01f5
            java.lang.Object r13 = r6.next()     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpChunk r13 = (fm.liveswitch.SctpChunk) r13     // Catch:{ all -> 0x032a }
            byte[] r13 = r13.getBytes()     // Catch:{ all -> 0x032a }
            int r13 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r13)     // Catch:{ all -> 0x032a }
            int r8 = r8 + r13
            goto L_0x01df
        L_0x01f5:
            r6 = 0
        L_0x01f6:
            r13 = 0
            if (r6 != 0) goto L_0x02a6
            fm.liveswitch.SctpDataChunk r15 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            if (r15 == 0) goto L_0x02a6
            boolean r15 = r15.getAcked()     // Catch:{ all -> 0x032a }
            if (r15 != 0) goto L_0x0214
            boolean r15 = r1.__dataRetransmission     // Catch:{ all -> 0x032a }
            if (r15 == 0) goto L_0x0214
            fm.liveswitch.SctpDataChunk r15 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            long r16 = r15.getTransmissionTime()     // Catch:{ all -> 0x032a }
            int r15 = (r16 > r13 ? 1 : (r16 == r13 ? 0 : -1))
            if (r15 <= 0) goto L_0x0214
            r15 = 1
            goto L_0x0215
        L_0x0214:
            r15 = 0
        L_0x0215:
            fm.liveswitch.SctpDataChunk r10 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            boolean r10 = r10.getAcked()     // Catch:{ all -> 0x032a }
            if (r10 != 0) goto L_0x0237
            fm.liveswitch.SctpDataChunk r10 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            long r17 = r10.getTransmissionTime()     // Catch:{ all -> 0x032a }
            int r10 = (r17 > r13 ? 1 : (r17 == r13 ? 0 : -1))
            if (r10 >= 0) goto L_0x0237
            fm.liveswitch.SctpSendDataQueue r10 = r1.__sendDataQueue     // Catch:{ all -> 0x032a }
            long r13 = r10.getCwnd()     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpTransmissionControlBlock r10 = r1.__tcb     // Catch:{ all -> 0x032a }
            long r17 = r10.getMaximumStaticCongestionWindow()     // Catch:{ all -> 0x032a }
            int r10 = (r13 > r17 ? 1 : (r13 == r17 ? 0 : -1))
            if (r10 <= 0) goto L_0x0239
        L_0x0237:
            if (r15 == 0) goto L_0x0286
        L_0x0239:
            fm.liveswitch.SctpDataChunk r10 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            byte[] r10 = r10.getBytes()     // Catch:{ all -> 0x032a }
            int r10 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r10)     // Catch:{ all -> 0x032a }
            int r10 = r10 + r8
            fm.liveswitch.SctpDataChunk r13 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            byte[] r13 = r13.getBytes()     // Catch:{ all -> 0x032a }
            int r13 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r13)     // Catch:{ all -> 0x032a }
            int r13 = r13 + r8
            if (r13 >= r11) goto L_0x0284
            fm.liveswitch.SctpDataChunk r8 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            r4.add(r8)     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpSendDataQueue r8 = r1.__sendDataQueue     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpDataChunk r13 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            r8.markChunkTransmitted(r13)     // Catch:{ all -> 0x032a }
            if (r21 == 0) goto L_0x0266
            fm.liveswitch.SctpDataChunk r8 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            r15 = 1
            r8.setSackImmediately(r15)     // Catch:{ all -> 0x032a }
            goto L_0x0274
        L_0x0266:
            r15 = 1
            fm.liveswitch.SctpDataChunk r8 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            boolean r8 = r8.getEnding()     // Catch:{ all -> 0x032a }
            if (r8 != 0) goto L_0x0274
            fm.liveswitch.SctpDataChunk r8 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            r8.setSackImmediately(r7)     // Catch:{ all -> 0x032a }
        L_0x0274:
            fm.liveswitch.SctpSendDataQueue r8 = r1.__sendDataQueue     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpDataChunk r13 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            long r13 = r13.getTsn()     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpDataChunk r8 = r8.getNextChunk(r13)     // Catch:{ all -> 0x032a }
            r1.__nextDataChunkToBeExaminedForSending = r8     // Catch:{ all -> 0x032a }
            r8 = r10
            goto L_0x02a3
        L_0x0284:
            r15 = 1
            goto L_0x02a2
        L_0x0286:
            r15 = 1
            boolean r10 = r1.__dataRetransmission     // Catch:{ all -> 0x032a }
            if (r10 == 0) goto L_0x02a2
            fm.liveswitch.SctpDataChunk r10 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            boolean r10 = r10.getAcked()     // Catch:{ all -> 0x032a }
            if (r10 == 0) goto L_0x02a2
            fm.liveswitch.SctpSendDataQueue r10 = r1.__sendDataQueue     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpDataChunk r13 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            long r13 = r13.getTsn()     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpDataChunk r10 = r10.getNextChunk(r13)     // Catch:{ all -> 0x032a }
            r1.__nextDataChunkToBeExaminedForSending = r10     // Catch:{ all -> 0x032a }
            goto L_0x02a3
        L_0x02a2:
            r6 = 1
        L_0x02a3:
            r10 = 0
            goto L_0x01f6
        L_0x02a6:
            r15 = 1
            fm.liveswitch.SctpDataChunk r6 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            if (r6 == 0) goto L_0x02df
            boolean r6 = r6.getAcked()     // Catch:{ all -> 0x032a }
            if (r6 != 0) goto L_0x02da
            fm.liveswitch.SctpDataChunk r6 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            long r8 = r6.getTransmissionTime()     // Catch:{ all -> 0x032a }
            int r6 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r6 >= 0) goto L_0x02cb
            fm.liveswitch.SctpSendDataQueue r6 = r1.__sendDataQueue     // Catch:{ all -> 0x032a }
            long r8 = r6.getCwnd()     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpTransmissionControlBlock r6 = r1.__tcb     // Catch:{ all -> 0x032a }
            long r10 = r6.getMaximumStaticCongestionWindow()     // Catch:{ all -> 0x032a }
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 <= 0) goto L_0x02dd
        L_0x02cb:
            boolean r6 = r1.__dataRetransmission     // Catch:{ all -> 0x032a }
            if (r6 == 0) goto L_0x02da
            fm.liveswitch.SctpDataChunk r6 = r1.__nextDataChunkToBeExaminedForSending     // Catch:{ all -> 0x032a }
            long r8 = r6.getTransmissionTime()     // Catch:{ all -> 0x032a }
            int r6 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r6 <= 0) goto L_0x02da
            goto L_0x02dd
        L_0x02da:
            r8 = 0
            goto L_0x02e0
        L_0x02dc:
            r15 = 1
        L_0x02dd:
            r8 = 1
            goto L_0x02e0
        L_0x02df:
            r8 = r9
        L_0x02e0:
            monitor-exit(r5)     // Catch:{ all -> 0x032a }
            fm.liveswitch.SctpCommonHeader r5 = new fm.liveswitch.SctpCommonHeader
            int r6 = r19.getPort()
            int r9 = r19.getPort()
            r5.<init>(r6, r9, r2)
            int r2 = fm.liveswitch.ArrayListExtensions.getCount(r4)
            if (r2 <= 0) goto L_0x0325
            fm.liveswitch.SctpPacket r2 = new fm.liveswitch.SctpPacket
            fm.liveswitch.SctpChunk[] r3 = new fm.liveswitch.SctpChunk[r7]
            java.lang.Object[] r3 = r4.toArray(r3)
            fm.liveswitch.SctpChunk[] r3 = (fm.liveswitch.SctpChunk[]) r3
            r2.<init>(r5, r3)
            r0.setValue(r2)
            if (r12 == 0) goto L_0x0324
            java.lang.Object r2 = r12.getState()
            fm.liveswitch.SctpResendArgs r2 = (fm.liveswitch.SctpResendArgs) r2
            fm.liveswitch.SctpResendArgs r2 = (fm.liveswitch.SctpResendArgs) r2
            java.lang.Object r0 = r20.getValue()
            fm.liveswitch.SctpPacket r0 = (fm.liveswitch.SctpPacket) r0
            byte[] r0 = r0.getBytes()
            fm.liveswitch.DataBuffer r0 = fm.liveswitch.DataBuffer.wrap(r0)
            r2.setPacketBytes(r0)
            fm.liveswitch.Scheduler r0 = r1.__scheduler
            r0.add(r12)
        L_0x0324:
            return r8
        L_0x0325:
            r2 = 0
            r0.setValue(r2)
            return r8
        L_0x032a:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x032a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SctpTransport.buildSctpPacket(fm.liveswitch.Holder, boolean):boolean");
    }

    private boolean checkVerificationTag(SctpPacket sctpPacket) {
        if (ArrayExtensions.getLength((Object[]) sctpPacket.getChunks()) > 0) {
            if (sctpPacket.getChunks()[0].getType() == SctpChunkType.getInitiation()) {
                if (sctpPacket.getHeader().getVerificationTag() == 0) {
                    return true;
                }
                return false;
            } else if (sctpPacket.getChunks()[0].getType() == SctpChunkType.getAbort()) {
                if (((SctpAbortChunk) sctpPacket.getChunks()[0]).getVerificationTagReflected()) {
                    if (this.__tcb.getPeerVerificationTag() == sctpPacket.getHeader().getVerificationTag()) {
                        return true;
                    }
                    return false;
                } else if (this.__tcb.getMyVerificationTag() == sctpPacket.getHeader().getVerificationTag()) {
                    return true;
                } else {
                    return false;
                }
            } else if (sctpPacket.getChunks()[0].getType() == SctpChunkType.getShutdownComplete()) {
                if (((SctpShutdownCompleteChunk) sctpPacket.getChunks()[0]).getVerificationTagReflected()) {
                    if (this.__tcb.getPeerVerificationTag() == sctpPacket.getHeader().getVerificationTag()) {
                        return true;
                    }
                    return false;
                } else if (this.__tcb.getMyVerificationTag() == sctpPacket.getHeader().getVerificationTag()) {
                    return true;
                } else {
                    return false;
                }
            } else if (sctpPacket.getChunks()[0].getType() == SctpChunkType.getCookieEcho()) {
                return true;
            } else {
                if (sctpPacket.getChunks()[0].getType() == SctpChunkType.getShutdownAck() && (Global.equals(get_InnerState(), SctpTcbState.CookieEchoed) || Global.equals(get_InnerState(), SctpTcbState.CookieWait))) {
                    return false;
                }
            }
        }
        if (this.__tcb.getMyVerificationTag() == sctpPacket.getHeader().getVerificationTag()) {
            return true;
        }
        return false;
    }

    private void closeOnFailure(Exception exc) {
        synchronized (this.__stateLock) {
            __log.debug("SCTP: Failure occurred. Proceeding to Close the SCTP Association.");
            setError(new Error(ErrorCode.SctpInternalError, exc));
            set_InnerState(SctpTcbState.Failing);
            stopAllDataChunkTransmission();
            stopAllControlChunkTransmission();
            set_InnerState(SctpTcbState.Failed);
            __log.debug("SCTP: Association shut down.");
            this.__innerTransport = null;
        }
    }

    private void dispatch(DataBuffer dataBuffer) {
        BundleTransport innerTransport = getInnerTransport();
        if (innerTransport != null) {
            DtlsTransport dtlsTransport = innerTransport.getDtlsTransport();
            if (dtlsTransport != null && !dtlsTransport.getIsClosed()) {
                try {
                    dtlsTransport.send(dataBuffer);
                } catch (Exception unused) {
                }
            } else if (dtlsTransport == null) {
                Log.debug("Cannot send SCTP message: DTLS transport is not set.");
            }
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:225)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    private boolean doProcessIncomingSctpPacket(fm.liveswitch.DataBuffer r20) {
        /*
            r19 = this;
            r1 = r19
            byte[] r0 = r20.toArray()
            int r2 = r20.getLength()
            fm.liveswitch.ILog r3 = __log
            boolean r3 = r3.getIsVerboseEnabled()
            if (r3 == 0) goto L_0x0029
            fm.liveswitch.ILog r3 = __log
            java.lang.String r4 = "SCTP Manager received an SCTP packet at {0}"
            long r5 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            java.lang.String r5 = fm.liveswitch.LongExtensions.toString(r5)
            java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object) r5)
            r3.verbose(r4)
        L_0x0029:
            r3 = 0
            boolean r4 = fm.liveswitch.SctpPacket.verifyCRC32cChecksum(r0, r3, r2)
            if (r4 != 0) goto L_0x0050
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsVerboseEnabled()
            if (r0 == 0) goto L_0x004f
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = "Incoming packet dropped due to invalid CRC32c checksum at {0}"
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r4)
            r0.verbose(r2)
        L_0x004f:
            return r3
        L_0x0050:
            fm.liveswitch.SctpPacket r2 = fm.liveswitch.SctpPacket.parseBytes(r0, r3, r2)     // Catch:{ Exception -> 0x0905 }
            if (r2 != 0) goto L_0x005e
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = "Could not parse SCTP packets."
            r0.warn(r2)
            return r3
        L_0x005e:
            boolean r0 = r1.checkVerificationTag(r2)
            if (r0 != 0) goto L_0x0084
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsVerboseEnabled()
            if (r0 == 0) goto L_0x0083
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = "SCTP packet contains invalid verification tag. Dropping packet at {0}"
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r4)
            r0.verbose(r2)
        L_0x0083:
            return r3
        L_0x0084:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            if (r0 != 0) goto L_0x008c
            r0 = 0
            goto L_0x008d
        L_0x008c:
            r0 = 1
        L_0x008d:
            fm.liveswitch.SctpTransmissionControlBlock r5 = r1.__tcb
            long r5 = r5.getGreatestCumulativeTsnReceived()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r8 = r5
            r6 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r5 = r0
        L_0x009f:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)
            if (r6 >= r0) goto L_0x0672
            if (r5 == 0) goto L_0x0672
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsVerboseEnabled()
            if (r0 == 0) goto L_0x0147
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r15 = r0.getType()
            byte r14 = fm.liveswitch.SctpChunkType.getData()
            if (r15 != r14) goto L_0x0124
            fm.liveswitch.SctpDataChunk r0 = (fm.liveswitch.SctpDataChunk) r0
            fm.liveswitch.SctpDataChunk r0 = (fm.liveswitch.SctpDataChunk) r0
            fm.liveswitch.ILog r14 = __log
            java.lang.String r15 = "SCTP packet received by manager contains chunk of type {1} with TSN {2}, SSN {3} on the Sctp Stream {4} at {0}."
            r4 = 5
            java.lang.Object[] r4 = new java.lang.Object[r4]
            long r17 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r17 = java.lang.Long.valueOf(r17)
            java.lang.String r17 = fm.liveswitch.LongExtensions.toString(r17)
            r4[r3] = r17
            int r17 = r0.getType()
            java.lang.Integer r17 = java.lang.Integer.valueOf(r17)
            java.lang.String r17 = fm.liveswitch.IntegerExtensions.toString(r17)
            r16 = 1
            r4[r16] = r17
            long r17 = r0.getTsn()
            java.lang.Long r17 = java.lang.Long.valueOf(r17)
            java.lang.String r17 = fm.liveswitch.LongExtensions.toString(r17)
            r18 = 2
            r4[r18] = r17
            r17 = 3
            int r18 = r0.getStreamSequenceNumber()
            java.lang.Integer r18 = java.lang.Integer.valueOf(r18)
            java.lang.String r18 = fm.liveswitch.IntegerExtensions.toString(r18)
            r4[r17] = r18
            r17 = 4
            int r0 = r0.getStreamIdentifier()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r4[r17] = r0
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r15, (java.lang.Object[]) r4)
            r14.verbose(r0)
            goto L_0x0147
        L_0x0124:
            fm.liveswitch.ILog r4 = __log
            java.lang.String r14 = "SCTP packet received by manager contains chunk of type {1}."
            long r17 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r15 = java.lang.Long.valueOf(r17)
            java.lang.String r15 = fm.liveswitch.LongExtensions.toString(r15)
            int r0 = r0.getType()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            java.lang.String r0 = fm.liveswitch.StringExtensions.format(r14, r15, r0)
            r4.verbose(r0)
        L_0x0147:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r0 = r0.getType()
            byte r4 = fm.liveswitch.SctpChunkType.getInitiation()
            if (r0 != r4) goto L_0x019a
            java.lang.Object r4 = r1.__stateLock
            monitor-enter(r4)
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()     // Catch:{ all -> 0x0197 }
            fm.liveswitch.SctpTcbState r5 = fm.liveswitch.SctpTcbState.ClosedNeverOpened     // Catch:{ all -> 0x0197 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)     // Catch:{ all -> 0x0197 }
            if (r0 != 0) goto L_0x017e
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()     // Catch:{ all -> 0x0197 }
            fm.liveswitch.SctpTcbState r5 = fm.liveswitch.SctpTcbState.CookieWait     // Catch:{ all -> 0x0197 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)     // Catch:{ all -> 0x0197 }
            if (r0 != 0) goto L_0x017e
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()     // Catch:{ all -> 0x0197 }
            fm.liveswitch.SctpTcbState r5 = fm.liveswitch.SctpTcbState.CookieEchoed     // Catch:{ all -> 0x0197 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)     // Catch:{ all -> 0x0197 }
            if (r0 == 0) goto L_0x018e
        L_0x017e:
            if (r6 != 0) goto L_0x018e
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()     // Catch:{ all -> 0x0197 }
            r0 = r0[r3]     // Catch:{ all -> 0x0197 }
            fm.liveswitch.SctpInitChunk r0 = (fm.liveswitch.SctpInitChunk) r0     // Catch:{ all -> 0x0197 }
            fm.liveswitch.SctpInitChunk r0 = (fm.liveswitch.SctpInitChunk) r0     // Catch:{ all -> 0x0197 }
            r1.respondWithINIT_ACK(r0)     // Catch:{ all -> 0x0197 }
            goto L_0x0195
        L_0x018e:
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x0197 }
            java.lang.String r5 = "SCTP: Association not in CLOSED, COOKIE WAIT or COOKIE ECHOED state when INIT received or control chunk not the first in sequence. Stale packet?"
            r0.debug(r5)     // Catch:{ all -> 0x0197 }
        L_0x0195:
            monitor-exit(r4)     // Catch:{ all -> 0x0197 }
            goto L_0x01d1
        L_0x0197:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0197 }
            throw r0
        L_0x019a:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r0 = r0.getType()
            byte r4 = fm.liveswitch.SctpChunkType.getInitiationAck()
            if (r0 != r4) goto L_0x01d7
            java.lang.Object r4 = r1.__stateLock
            monitor-enter(r4)
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()     // Catch:{ all -> 0x01d4 }
            fm.liveswitch.SctpTcbState r5 = fm.liveswitch.SctpTcbState.CookieWait     // Catch:{ all -> 0x01d4 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)     // Catch:{ all -> 0x01d4 }
            if (r0 == 0) goto L_0x01c9
            if (r6 != 0) goto L_0x01c9
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()     // Catch:{ all -> 0x01d4 }
            r0 = r0[r3]     // Catch:{ all -> 0x01d4 }
            fm.liveswitch.SctpInitAckChunk r0 = (fm.liveswitch.SctpInitAckChunk) r0     // Catch:{ all -> 0x01d4 }
            fm.liveswitch.SctpInitAckChunk r0 = (fm.liveswitch.SctpInitAckChunk) r0     // Catch:{ all -> 0x01d4 }
            r1.respondWithCOOKIE_ECHO(r0)     // Catch:{ all -> 0x01d4 }
            goto L_0x01d0
        L_0x01c9:
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x01d4 }
            java.lang.String r5 = "SCTP: Association not in COOKIE_WAIT state when INIT_ACK received or control chunk not the first in sequence. Stale packet?"
            r0.debug(r5)     // Catch:{ all -> 0x01d4 }
        L_0x01d0:
            monitor-exit(r4)     // Catch:{ all -> 0x01d4 }
        L_0x01d1:
            r5 = 0
            goto L_0x066d
        L_0x01d4:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x01d4 }
            throw r0
        L_0x01d7:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r0 = r0.getType()
            byte r4 = fm.liveswitch.SctpChunkType.getCookieEcho()
            if (r0 != r4) goto L_0x0239
            java.lang.Object r4 = r1.__stateLock
            monitor-enter(r4)
            fm.liveswitch.SctpTransportState r0 = r19.getState()     // Catch:{ all -> 0x0236 }
            fm.liveswitch.SctpTransportState r14 = fm.liveswitch.SctpTransportState.Connecting     // Catch:{ all -> 0x0236 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r14)     // Catch:{ all -> 0x0236 }
            if (r0 != 0) goto L_0x020e
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()     // Catch:{ all -> 0x0236 }
            fm.liveswitch.SctpTcbState r14 = fm.liveswitch.SctpTcbState.Established     // Catch:{ all -> 0x0236 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r14)     // Catch:{ all -> 0x0236 }
            if (r0 != 0) goto L_0x020e
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()     // Catch:{ all -> 0x0236 }
            fm.liveswitch.SctpTcbState r14 = fm.liveswitch.SctpTcbState.ClosedNeverOpened     // Catch:{ all -> 0x0236 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r14)     // Catch:{ all -> 0x0236 }
            if (r0 == 0) goto L_0x022b
        L_0x020e:
            if (r6 != 0) goto L_0x022b
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()     // Catch:{ all -> 0x0236 }
            r0 = r0[r3]     // Catch:{ all -> 0x0236 }
            fm.liveswitch.SctpCookieEchoChunk r0 = (fm.liveswitch.SctpCookieEchoChunk) r0     // Catch:{ all -> 0x0236 }
            fm.liveswitch.SctpCookieEchoChunk r0 = (fm.liveswitch.SctpCookieEchoChunk) r0     // Catch:{ all -> 0x0236 }
            fm.liveswitch.SctpCommonHeader r8 = r2.getHeader()     // Catch:{ all -> 0x0236 }
            boolean r0 = r1.respondWithCOOKIE_ACK(r0, r8)     // Catch:{ all -> 0x0236 }
            fm.liveswitch.SctpTransmissionControlBlock r8 = r1.__tcb     // Catch:{ all -> 0x0236 }
            long r8 = r8.getGreatestCumulativeTsnReceived()     // Catch:{ all -> 0x0236 }
            if (r0 != 0) goto L_0x0233
            goto L_0x0232
        L_0x022b:
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x0236 }
            java.lang.String r5 = "SCTP: Cookie received in state other than CLOSED or control chunk not the first in sequence. Stale packet?"
            r0.debug(r5)     // Catch:{ all -> 0x0236 }
        L_0x0232:
            r5 = 0
        L_0x0233:
            monitor-exit(r4)     // Catch:{ all -> 0x0236 }
            goto L_0x066d
        L_0x0236:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0236 }
            throw r0
        L_0x0239:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r0 = r0.getType()
            byte r4 = fm.liveswitch.SctpChunkType.getCookieAck()
            if (r0 != r4) goto L_0x0295
            java.lang.Object r4 = r1.__stateLock
            monitor-enter(r4)
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()     // Catch:{ all -> 0x0292 }
            fm.liveswitch.SctpTcbState r14 = fm.liveswitch.SctpTcbState.CookieEchoed     // Catch:{ all -> 0x0292 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r14)     // Catch:{ all -> 0x0292 }
            if (r0 == 0) goto L_0x0273
            if (r6 != 0) goto L_0x0273
            fm.liveswitch.SctpTcbState r0 = fm.liveswitch.SctpTcbState.Established     // Catch:{ all -> 0x0292 }
            r1.set_InnerState(r0)     // Catch:{ all -> 0x0292 }
            fm.liveswitch.SctpErrorChunk r0 = r1.__errorToCombineWithCookieEcho     // Catch:{ all -> 0x0292 }
            if (r0 == 0) goto L_0x028f
            fm.liveswitch.SctpSendControlChunkQueue r14 = r1.__sendControlChunkQueue     // Catch:{ all -> 0x0292 }
            r14.enqueue(r0)     // Catch:{ all -> 0x0292 }
            r0 = 0
            r1.__errorToCombineWithCookieEcho = r0     // Catch:{ all -> 0x0292 }
            fm.liveswitch.Scheduler r0 = r1.__scheduler     // Catch:{ all -> 0x0292 }
            fm.liveswitch.ScheduledItem r14 = r1.__outgoingQueueScheduledItem     // Catch:{ all -> 0x0292 }
            r0.trigger(r14)     // Catch:{ all -> 0x0292 }
            goto L_0x028f
        L_0x0273:
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()     // Catch:{ all -> 0x0292 }
            fm.liveswitch.SctpTcbState r5 = fm.liveswitch.SctpTcbState.CookieEchoed     // Catch:{ all -> 0x0292 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)     // Catch:{ all -> 0x0292 }
            if (r0 != 0) goto L_0x0287
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x0292 }
            java.lang.String r5 = "SCTP: Cookie_ACK received in state other than SCTPStates.COOKIE_ECHOED. Ignoring the Cookie_ACK chunk. If there are other chunks in the packet, they will be examined."
            r0.debug(r5)     // Catch:{ all -> 0x0292 }
            goto L_0x028e
        L_0x0287:
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x0292 }
            java.lang.String r5 = "SCTP: Cookie_ACK control chunk not the first in packet sequence. Droping packet."
            r0.debug(r5)     // Catch:{ all -> 0x0292 }
        L_0x028e:
            r5 = 1
        L_0x028f:
            monitor-exit(r4)     // Catch:{ all -> 0x0292 }
            goto L_0x066d
        L_0x0292:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0292 }
            throw r0
        L_0x0295:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r0 = r0.getType()
            byte r4 = fm.liveswitch.SctpChunkType.getForwardCumulativeTSN()
            if (r0 != r4) goto L_0x033e
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r4 = fm.liveswitch.SctpTcbState.Established
            boolean r0 = fm.liveswitch.Global.equals(r0, r4)
            if (r0 != 0) goto L_0x02ce
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r4 = fm.liveswitch.SctpTcbState.Closing
            boolean r0 = fm.liveswitch.Global.equals(r0, r4)
            if (r0 != 0) goto L_0x02ce
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r4 = fm.liveswitch.SctpTcbState.CookieEchoed
            boolean r0 = fm.liveswitch.Global.equals(r0, r4)
            if (r0 == 0) goto L_0x02ca
            goto L_0x02ce
        L_0x02ca:
            r17 = r5
            goto L_0x066b
        L_0x02ce:
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb
            fm.liveswitch.SctpPartialReliabilitySupportParameters r0 = r0.getPartialReliabilitySupport()
            if (r0 == 0) goto L_0x032c
            boolean r0 = r0.getPartialReliabilityUsedInThisAssociation()
            if (r0 == 0) goto L_0x032c
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb
            r4 = 1
            r0.setRemoteLikelyInConnectedState(r4)
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            fm.liveswitch.SctpForwardTsnChunk r0 = (fm.liveswitch.SctpForwardTsnChunk) r0
            fm.liveswitch.SctpForwardTsnChunk r0 = (fm.liveswitch.SctpForwardTsnChunk) r0
            fm.liveswitch.ILog r4 = __log
            boolean r4 = r4.getIsVerboseEnabled()
            if (r4 == 0) goto L_0x0317
            fm.liveswitch.ILog r4 = __log
            java.lang.String r14 = "SCTP: Received Forward TSN chunk with the New Cumulative Tsn Ack {0} at {1}"
            long r17 = r0.getNewCumulativeTsnAck()
            java.lang.Long r15 = java.lang.Long.valueOf(r17)
            java.lang.String r15 = fm.liveswitch.LongExtensions.toString(r15)
            long r17 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r17 = java.lang.Long.valueOf(r17)
            java.lang.String r3 = fm.liveswitch.LongExtensions.toString(r17)
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r14, r15, r3)
            r4.verbose(r3)
        L_0x0317:
            java.lang.Object r3 = r1.__stateLock
            monitor-enter(r3)
            fm.liveswitch.SctpReceiveDataQueue r4 = r1.__receiveDataQueue     // Catch:{ all -> 0x0329 }
            boolean r0 = r4.processForwardTsnChunk(r0)     // Catch:{ all -> 0x0329 }
            if (r0 != 0) goto L_0x0324
            r0 = 1
            goto L_0x0325
        L_0x0324:
            r0 = 0
        L_0x0325:
            r11 = r11 | r0
            monitor-exit(r3)     // Catch:{ all -> 0x0329 }
            goto L_0x066d
        L_0x0329:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0329 }
            throw r0
        L_0x032c:
            fm.liveswitch.ILog r0 = __log
            java.lang.String r3 = "SCTP: remote sent Forward Cumulative TSN chunk, but this party has not enabled support for partial reliability OR PR was not negotiated. Ignoring this chunk and reporting it as unrecognied to the other party."
            r0.debug(r3)
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            r7.add(r0)
            goto L_0x066d
        L_0x033e:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r0 = r0.getType()
            byte r3 = fm.liveswitch.SctpChunkType.getData()
            if (r0 != r3) goto L_0x03f3
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r3 = fm.liveswitch.SctpTcbState.Established
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 != 0) goto L_0x0372
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r3 = fm.liveswitch.SctpTcbState.Closing
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 != 0) goto L_0x0372
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r3 = fm.liveswitch.SctpTcbState.CookieEchoed
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 == 0) goto L_0x02ca
        L_0x0372:
            int r10 = r10 + 1
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            fm.liveswitch.SctpDataChunk r0 = (fm.liveswitch.SctpDataChunk) r0
            fm.liveswitch.SctpDataChunk r0 = (fm.liveswitch.SctpDataChunk) r0
            boolean r3 = r0.getSackImmediately()
            r11 = r11 | r3
            java.lang.Object r3 = r1.__stateLock
            monitor-enter(r3)
            fm.liveswitch.SctpTransmissionControlBlock r4 = r1.__tcb     // Catch:{ all -> 0x03f0 }
            r14 = 1
            r4.setRemoteLikelyInConnectedState(r14)     // Catch:{ all -> 0x03f0 }
            long r14 = r0.getTsn()     // Catch:{ all -> 0x03f0 }
            fm.liveswitch.SctpTransmissionControlBlock r4 = r1.__tcb     // Catch:{ all -> 0x03f0 }
            r17 = r5
            long r4 = r4.getGreatestReceivedTsn()     // Catch:{ all -> 0x03f0 }
            int r4 = fm.liveswitch.SctpDataChunk.compareTsns(r14, r4)     // Catch:{ all -> 0x03f0 }
            r5 = 1
            if (r4 != r5) goto L_0x03c9
            fm.liveswitch.SctpTransmissionControlBlock r4 = r1.__tcb     // Catch:{ all -> 0x03f0 }
            long r14 = r0.getTsn()     // Catch:{ all -> 0x03f0 }
            r4.setGreatestReceivedTsn(r14)     // Catch:{ all -> 0x03f0 }
            fm.liveswitch.ILog r4 = __log     // Catch:{ all -> 0x03f0 }
            boolean r4 = r4.getIsVerboseEnabled()     // Catch:{ all -> 0x03f0 }
            if (r4 == 0) goto L_0x03c9
            fm.liveswitch.ILog r4 = __log     // Catch:{ all -> 0x03f0 }
            java.lang.String r5 = "Updating greatest received TSN to "
            fm.liveswitch.SctpTransmissionControlBlock r14 = r1.__tcb     // Catch:{ all -> 0x03f0 }
            long r14 = r14.getGreatestReceivedTsn()     // Catch:{ all -> 0x03f0 }
            java.lang.Long r14 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x03f0 }
            java.lang.String r14 = fm.liveswitch.LongExtensions.toString(r14)     // Catch:{ all -> 0x03f0 }
            java.lang.String r5 = fm.liveswitch.StringExtensions.concat(r5, r14)     // Catch:{ all -> 0x03f0 }
            r4.verbose(r5)     // Catch:{ all -> 0x03f0 }
        L_0x03c9:
            long r4 = r0.getTsn()     // Catch:{ all -> 0x03f0 }
            fm.liveswitch.SctpReceiveDataQueue r14 = r1.__receiveDataQueue     // Catch:{ all -> 0x03f0 }
            long r14 = r14.getCumulativeACK()     // Catch:{ all -> 0x03f0 }
            int r4 = fm.liveswitch.SctpDataChunk.compareTsns(r4, r14)     // Catch:{ all -> 0x03f0 }
            r5 = 1
            if (r4 != r5) goto L_0x03ed
            fm.liveswitch.SctpReceiveDataQueue r4 = r1.__receiveDataQueue     // Catch:{ all -> 0x03f0 }
            long r14 = r0.getTsn()     // Catch:{ all -> 0x03f0 }
            boolean r4 = r4.chunkExists(r14)     // Catch:{ all -> 0x03f0 }
            if (r4 != 0) goto L_0x03ed
            int r12 = r12 + 1
            fm.liveswitch.SctpReceiveDataQueue r4 = r1.__receiveDataQueue     // Catch:{ all -> 0x03f0 }
            r4.add(r0)     // Catch:{ all -> 0x03f0 }
        L_0x03ed:
            monitor-exit(r3)     // Catch:{ all -> 0x03f0 }
            goto L_0x066b
        L_0x03f0:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x03f0 }
            throw r0
        L_0x03f3:
            r17 = r5
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r0 = r0.getType()
            byte r3 = fm.liveswitch.SctpChunkType.getSack()
            if (r0 != r3) goto L_0x04f6
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r3 = fm.liveswitch.SctpTcbState.Established
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 != 0) goto L_0x0438
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r3 = fm.liveswitch.SctpTcbState.Closing
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 != 0) goto L_0x0438
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r3 = fm.liveswitch.SctpTcbState.CookieEchoed
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 == 0) goto L_0x042a
            goto L_0x0438
        L_0x042a:
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb
            fm.liveswitch.SctpTcbState r0 = r0.getState()
            fm.liveswitch.SctpTcbState r3 = fm.liveswitch.SctpTcbState.Closed
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            goto L_0x066b
        L_0x0438:
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb
            r3 = 1
            r0.setRemoteLikelyInConnectedState(r3)
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb
            fm.liveswitch.SctpSackChunk r0 = r0.getFreshestReceivedSack()
            if (r0 == 0) goto L_0x0465
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            fm.liveswitch.SctpSackChunk r0 = (fm.liveswitch.SctpSackChunk) r0
            fm.liveswitch.SctpSackChunk r0 = (fm.liveswitch.SctpSackChunk) r0
            long r3 = r0.getCumulativeTsnAck()
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb
            fm.liveswitch.SctpSackChunk r0 = r0.getFreshestReceivedSack()
            long r14 = r0.getCumulativeTsnAck()
            int r0 = fm.liveswitch.SctpDataChunk.compareTsns(r3, r14)
            r3 = 2
            if (r0 >= r3) goto L_0x066b
        L_0x0465:
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsVerboseEnabled()
            if (r0 == 0) goto L_0x04e2
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb
            fm.liveswitch.SctpSackChunk r0 = r0.getFreshestReceivedSack()
            if (r0 == 0) goto L_0x04b5
            fm.liveswitch.ILog r0 = __log
            java.lang.String r3 = "New SACK received with the cumulative TSN ACK of {1}, while association's cumulative ACK is {2} at {0}"
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            fm.liveswitch.SctpChunk[] r5 = r2.getChunks()
            r5 = r5[r6]
            fm.liveswitch.SctpSackChunk r5 = (fm.liveswitch.SctpSackChunk) r5
            fm.liveswitch.SctpSackChunk r5 = (fm.liveswitch.SctpSackChunk) r5
            long r13 = r5.getCumulativeTsnAck()
            java.lang.Long r5 = java.lang.Long.valueOf(r13)
            java.lang.String r5 = fm.liveswitch.LongExtensions.toString(r5)
            fm.liveswitch.SctpTransmissionControlBlock r13 = r1.__tcb
            fm.liveswitch.SctpSackChunk r13 = r13.getFreshestReceivedSack()
            long r13 = r13.getCumulativeTsnAck()
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            java.lang.String r13 = fm.liveswitch.LongExtensions.toString(r13)
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r3, r4, r5, r13)
            r0.verbose(r3)
            goto L_0x04e2
        L_0x04b5:
            fm.liveswitch.ILog r0 = __log
            java.lang.String r3 = "New SACK received with the cumulative TSN ACK of {1} at {0}"
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            fm.liveswitch.SctpChunk[] r5 = r2.getChunks()
            r5 = r5[r6]
            fm.liveswitch.SctpSackChunk r5 = (fm.liveswitch.SctpSackChunk) r5
            fm.liveswitch.SctpSackChunk r5 = (fm.liveswitch.SctpSackChunk) r5
            long r13 = r5.getCumulativeTsnAck()
            java.lang.Long r5 = java.lang.Long.valueOf(r13)
            java.lang.String r5 = fm.liveswitch.LongExtensions.toString(r5)
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r3, r4, r5)
            r0.debug(r3)
        L_0x04e2:
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb
            fm.liveswitch.SctpChunk[] r3 = r2.getChunks()
            r3 = r3[r6]
            fm.liveswitch.SctpSackChunk r3 = (fm.liveswitch.SctpSackChunk) r3
            fm.liveswitch.SctpSackChunk r3 = (fm.liveswitch.SctpSackChunk) r3
            r0.setFreshestReceivedSack(r3)
            r5 = r17
            r13 = 1
            goto L_0x066d
        L_0x04f6:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r0 = r0.getType()
            byte r3 = fm.liveswitch.SctpChunkType.getHeartbeat()
            if (r0 != r3) goto L_0x05ad
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r3 = fm.liveswitch.SctpTcbState.Established
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 != 0) goto L_0x0554
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r3 = fm.liveswitch.SctpTcbState.Closing
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 != 0) goto L_0x0554
            fm.liveswitch.SctpTcbState r0 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r3 = fm.liveswitch.SctpTcbState.CookieEchoed
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 == 0) goto L_0x052b
            goto L_0x0554
        L_0x052b:
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsVerboseEnabled()
            if (r0 == 0) goto L_0x066b
            fm.liveswitch.ILog r0 = __log
            java.lang.String r3 = "SCTP: Received a heartbeat; discarding it because the association is in state {1} at {0}."
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            fm.liveswitch.SctpTcbState r5 = r19.get_InnerState()
            java.lang.String r5 = r5.toString()
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r3, r4, r5)
            r0.verbose(r3)
            goto L_0x066b
        L_0x0554:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()     // Catch:{ Exception -> 0x059b }
            r0 = r0[r6]     // Catch:{ Exception -> 0x059b }
            fm.liveswitch.SctpHeartbeatChunk r0 = (fm.liveswitch.SctpHeartbeatChunk) r0     // Catch:{ Exception -> 0x059b }
            fm.liveswitch.SctpHeartbeatChunk r0 = (fm.liveswitch.SctpHeartbeatChunk) r0     // Catch:{ Exception -> 0x059b }
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x059b }
            boolean r3 = r3.getIsVerboseEnabled()     // Catch:{ Exception -> 0x059b }
            if (r3 == 0) goto L_0x057d
            fm.liveswitch.ILog r3 = __log     // Catch:{ Exception -> 0x059b }
            java.lang.String r4 = "SCTP: Received a heartbeat. Sending HEARTBEAT_ACK at {0}."
            long r14 = fm.liveswitch.Scheduler.getCurrentTime()     // Catch:{ Exception -> 0x059b }
            java.lang.Long r5 = java.lang.Long.valueOf(r14)     // Catch:{ Exception -> 0x059b }
            java.lang.String r5 = fm.liveswitch.LongExtensions.toString(r5)     // Catch:{ Exception -> 0x059b }
            java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ Exception -> 0x059b }
            r3.verbose(r4)     // Catch:{ Exception -> 0x059b }
        L_0x057d:
            fm.liveswitch.SctpHeartbeatAckChunk r3 = new fm.liveswitch.SctpHeartbeatAckChunk     // Catch:{ Exception -> 0x059b }
            fm.liveswitch.SctpTlvParameter r0 = r0.getHeartbeatInfo()     // Catch:{ Exception -> 0x059b }
            r3.<init>(r0)     // Catch:{ Exception -> 0x059b }
            java.lang.Object r4 = r1.__stateLock     // Catch:{ Exception -> 0x059b }
            monitor-enter(r4)     // Catch:{ Exception -> 0x059b }
            fm.liveswitch.SctpSendControlChunkQueue r0 = r1.__sendControlChunkQueue     // Catch:{ all -> 0x0598 }
            r0.enqueue(r3)     // Catch:{ all -> 0x0598 }
            fm.liveswitch.Scheduler r0 = r1.__scheduler     // Catch:{ all -> 0x0598 }
            fm.liveswitch.ScheduledItem r3 = r1.__outgoingQueueScheduledItem     // Catch:{ all -> 0x0598 }
            r0.trigger(r3)     // Catch:{ all -> 0x0598 }
            monitor-exit(r4)     // Catch:{ all -> 0x0598 }
            goto L_0x066b
        L_0x0598:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0598 }
            throw r0     // Catch:{ Exception -> 0x059b }
        L_0x059b:
            r0 = move-exception
            fm.liveswitch.ILog r3 = __log
            java.lang.String r4 = "Failure to process incoming SCTP Heartbeats: {0}"
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object) r0)
            r3.debug(r0)
            goto L_0x066b
        L_0x05ad:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r0 = r0.getType()
            byte r3 = fm.liveswitch.SctpChunkType.getHeartbeatAck()
            if (r0 != r3) goto L_0x05de
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsVerboseEnabled()
            if (r0 == 0) goto L_0x066b
            fm.liveswitch.ILog r0 = __log
            java.lang.String r3 = "SCTP: Received a heartbeat ack at {0}."
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            java.lang.String r3 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r4)
            r0.verbose(r3)
            goto L_0x066b
        L_0x05de:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r0 = r0.getType()
            byte r3 = fm.liveswitch.SctpChunkType.getAbort()
            if (r0 != r3) goto L_0x064e
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsVerboseEnabled()
            if (r0 == 0) goto L_0x0646
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            fm.liveswitch.SctpAbortChunk r0 = (fm.liveswitch.SctpAbortChunk) r0
            fm.liveswitch.SctpAbortChunk r0 = (fm.liveswitch.SctpAbortChunk) r0
            fm.liveswitch.SctpErrorCause[] r3 = r0.getErrorCauses()
            if (r3 == 0) goto L_0x062e
            fm.liveswitch.SctpErrorCause[] r0 = r0.getErrorCauses()
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)
            fm.liveswitch.ILog r3 = __log
            java.lang.String r4 = "SCTP:  Received ABORT from another peer containing {1} error causes at {0}."
            long r14 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r5 = java.lang.Long.valueOf(r14)
            java.lang.String r5 = fm.liveswitch.LongExtensions.toString(r5)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            java.lang.String r0 = fm.liveswitch.StringExtensions.format(r4, r5, r0)
            r3.verbose(r0)
            goto L_0x066b
        L_0x062e:
            fm.liveswitch.ILog r0 = __log
            java.lang.String r3 = "SCTP:  Received ABORT from another peer containing no error causes at {0}."
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            java.lang.String r3 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r4)
            r0.verbose(r3)
            goto L_0x066b
        L_0x0646:
            fm.liveswitch.ILog r0 = __log
            java.lang.String r3 = "SCTP: Received ABORT from another peer."
            r0.debug(r3)
            goto L_0x066b
        L_0x064e:
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            int r0 = r0.getType()
            byte r3 = fm.liveswitch.SctpChunkType.getError()
            if (r0 != r3) goto L_0x066b
            fm.liveswitch.SctpChunk[] r0 = r2.getChunks()
            r0 = r0[r6]
            fm.liveswitch.SctpErrorChunk r0 = (fm.liveswitch.SctpErrorChunk) r0
            fm.liveswitch.SctpErrorChunk r0 = (fm.liveswitch.SctpErrorChunk) r0
            r1.processError(r0)
        L_0x066b:
            r5 = r17
        L_0x066d:
            int r6 = r6 + 1
            r3 = 0
            goto L_0x009f
        L_0x0672:
            fm.liveswitch.SctpChunk[] r0 = r2.getUnrecognizedChunksThatShouldBeReportedToSender()
            if (r0 == 0) goto L_0x067f
            fm.liveswitch.SctpChunk[] r0 = r2.getUnrecognizedChunksThatShouldBeReportedToSender()
            fm.liveswitch.ArrayListExtensions.addRange(r7, (T[]) r0)
        L_0x067f:
            int r0 = fm.liveswitch.ArrayListExtensions.getCount(r7)
            if (r0 <= 0) goto L_0x06e5
            r2 = 0
            fm.liveswitch.SctpChunk[] r0 = new fm.liveswitch.SctpChunk[r2]
            java.lang.Object[] r0 = r7.toArray(r0)
            fm.liveswitch.SctpChunk[] r0 = (fm.liveswitch.SctpChunk[]) r0
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)
            if (r2 <= 0) goto L_0x06e5
            fm.liveswitch.ILog r2 = __log
            boolean r2 = r2.getIsVerboseEnabled()
            if (r2 == 0) goto L_0x06b3
            fm.liveswitch.ILog r2 = __log
            java.lang.String r3 = "SCTP: Reporting unrecognised chunks to the other peer at {0}."
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            java.lang.String r3 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r4)
            r2.verbose(r3)
        L_0x06b3:
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)
            fm.liveswitch.SctpUnrecognizedChunkType[] r2 = new fm.liveswitch.SctpUnrecognizedChunkType[r2]
            r3 = 0
        L_0x06ba:
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)
            if (r3 >= r4) goto L_0x06cc
            fm.liveswitch.SctpUnrecognizedChunkType r4 = new fm.liveswitch.SctpUnrecognizedChunkType
            r5 = r0[r3]
            r4.<init>(r5)
            r2[r3] = r4
            int r3 = r3 + 1
            goto L_0x06ba
        L_0x06cc:
            fm.liveswitch.SctpErrorChunk r0 = new fm.liveswitch.SctpErrorChunk
            r0.<init>(r2)
            java.lang.Object r2 = r1.__stateLock
            monitor-enter(r2)
            fm.liveswitch.SctpSendControlChunkQueue r3 = r1.__sendControlChunkQueue     // Catch:{ all -> 0x06e2 }
            r3.enqueue(r0)     // Catch:{ all -> 0x06e2 }
            fm.liveswitch.Scheduler r0 = r1.__scheduler     // Catch:{ all -> 0x06e2 }
            fm.liveswitch.ScheduledItem r3 = r1.__outgoingQueueScheduledItem     // Catch:{ all -> 0x06e2 }
            r0.trigger(r3)     // Catch:{ all -> 0x06e2 }
            monitor-exit(r2)     // Catch:{ all -> 0x06e2 }
            goto L_0x06e5
        L_0x06e2:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x06e2 }
            throw r0
        L_0x06e5:
            java.lang.Object r2 = r1.__stateLock
            monitor-enter(r2)
            if (r10 <= 0) goto L_0x0812
            fm.liveswitch.SctpReceiveDataQueue r0 = r1.__receiveDataQueue     // Catch:{ all -> 0x0902 }
            int r0 = r0.getCount()     // Catch:{ all -> 0x0902 }
            if (r0 <= 0) goto L_0x0812
            fm.liveswitch.SctpReceiveDataQueue r0 = r1.__receiveDataQueue     // Catch:{ all -> 0x0902 }
            long r3 = r0.getEarliestTSN()     // Catch:{ all -> 0x0902 }
            long r5 = fm.liveswitch.SctpDataChunk.incrementTSN(r8)     // Catch:{ all -> 0x0902 }
            int r0 = fm.liveswitch.SctpDataChunk.compareTsns(r3, r5)     // Catch:{ all -> 0x0902 }
            r5 = 1
            if (r0 != r5) goto L_0x0707
            long r3 = fm.liveswitch.SctpDataChunk.incrementTSN(r8)     // Catch:{ all -> 0x0902 }
        L_0x0707:
            fm.liveswitch.SctpReceiveDataQueue r0 = r1.__receiveDataQueue     // Catch:{ all -> 0x0902 }
            fm.liveswitch.SctpDataChunk r0 = r0.getChunk(r3)     // Catch:{ all -> 0x0902 }
            if (r0 != 0) goto L_0x0718
            fm.liveswitch.SctpReceiveDataQueue r0 = r1.__receiveDataQueue     // Catch:{ all -> 0x0902 }
            fm.liveswitch.SctpDataChunk r0 = r0.getNextChunk(r3)     // Catch:{ all -> 0x0902 }
            r3 = 0
            r4 = 0
            goto L_0x071a
        L_0x0718:
            r3 = 0
            r4 = 1
        L_0x071a:
            r5 = 0
        L_0x071b:
            if (r0 == 0) goto L_0x07dc
            boolean r6 = r0.getRaised()     // Catch:{ all -> 0x0902 }
            if (r6 != 0) goto L_0x07c2
            if (r4 != 0) goto L_0x072d
            if (r4 != 0) goto L_0x07c2
            boolean r6 = r0.getUnordered()     // Catch:{ all -> 0x0902 }
            if (r6 == 0) goto L_0x07c2
        L_0x072d:
            fm.liveswitch.SctpTransmissionControlBlock r6 = r1.__tcb     // Catch:{ all -> 0x0902 }
            fm.liveswitch.SctpStreamCollection r6 = r6.getInboundStreams()     // Catch:{ all -> 0x0902 }
            int r7 = r0.getStreamIdentifier()     // Catch:{ all -> 0x0902 }
            fm.liveswitch.SctpStream r6 = r6.getStream(r7)     // Catch:{ all -> 0x0902 }
            if (r5 == 0) goto L_0x0788
            boolean r7 = r0.getBeginning()     // Catch:{ all -> 0x0902 }
            if (r7 == 0) goto L_0x0754
            if (r3 == 0) goto L_0x0747
            goto L_0x07c2
        L_0x0747:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0902 }
            java.lang.Exception r3 = new java.lang.Exception     // Catch:{ all -> 0x0902 }
            java.lang.String r4 = "SCTP: Encountered an unfinished message"
            r3.<init>(r4)     // Catch:{ all -> 0x0902 }
            r0.<init>(r3)     // Catch:{ all -> 0x0902 }
            throw r0     // Catch:{ all -> 0x0902 }
        L_0x0754:
            boolean r7 = r0.getEnding()     // Catch:{ all -> 0x0902 }
            if (r7 == 0) goto L_0x07c2
            if (r3 != 0) goto L_0x07c2
            boolean r5 = r0.getUnordered()     // Catch:{ all -> 0x0902 }
            if (r5 == 0) goto L_0x076a
            long r5 = r0.getTsn()     // Catch:{ all -> 0x0902 }
            r1.raiseReceivedMessage(r5)     // Catch:{ all -> 0x0902 }
            goto L_0x0786
        L_0x076a:
            int r5 = r0.getStreamSequenceNumber()     // Catch:{ all -> 0x0902 }
            int r7 = r6.getNextSsn()     // Catch:{ all -> 0x0902 }
            if (r5 != r7) goto L_0x0786
            long r7 = r0.getTsn()     // Catch:{ all -> 0x0902 }
            r1.raiseReceivedMessage(r7)     // Catch:{ all -> 0x0902 }
            int r5 = r6.getNextSsn()     // Catch:{ all -> 0x0902 }
            int r5 = fm.liveswitch.SctpDataChunk.incrementSSN(r5)     // Catch:{ all -> 0x0902 }
            r6.setNextSsn(r5)     // Catch:{ all -> 0x0902 }
        L_0x0786:
            r5 = 0
            goto L_0x07c2
        L_0x0788:
            boolean r7 = r0.getBeginning()     // Catch:{ all -> 0x0902 }
            if (r7 == 0) goto L_0x07c2
            boolean r3 = r0.getEnding()     // Catch:{ all -> 0x0902 }
            if (r3 == 0) goto L_0x07c0
            boolean r3 = r0.getUnordered()     // Catch:{ all -> 0x0902 }
            if (r3 == 0) goto L_0x07a2
            long r6 = r0.getTsn()     // Catch:{ all -> 0x0902 }
            r1.raiseReceivedMessage(r6)     // Catch:{ all -> 0x0902 }
            goto L_0x07be
        L_0x07a2:
            int r3 = r0.getStreamSequenceNumber()     // Catch:{ all -> 0x0902 }
            int r7 = r6.getNextSsn()     // Catch:{ all -> 0x0902 }
            if (r3 != r7) goto L_0x07be
            long r7 = r0.getTsn()     // Catch:{ all -> 0x0902 }
            r1.raiseReceivedMessage(r7)     // Catch:{ all -> 0x0902 }
            int r3 = r6.getNextSsn()     // Catch:{ all -> 0x0902 }
            int r3 = fm.liveswitch.SctpDataChunk.incrementSSN(r3)     // Catch:{ all -> 0x0902 }
            r6.setNextSsn(r3)     // Catch:{ all -> 0x0902 }
        L_0x07be:
            r3 = 0
            goto L_0x07c2
        L_0x07c0:
            r3 = 0
            r5 = 1
        L_0x07c2:
            long r6 = r0.getTsn()     // Catch:{ all -> 0x0902 }
            fm.liveswitch.SctpReceiveDataQueue r0 = r1.__receiveDataQueue     // Catch:{ all -> 0x0902 }
            long r8 = fm.liveswitch.SctpDataChunk.incrementTSN(r6)     // Catch:{ all -> 0x0902 }
            fm.liveswitch.SctpDataChunk r0 = r0.getChunk(r8)     // Catch:{ all -> 0x0902 }
            if (r0 != 0) goto L_0x071b
            fm.liveswitch.SctpReceiveDataQueue r0 = r1.__receiveDataQueue     // Catch:{ all -> 0x0902 }
            fm.liveswitch.SctpDataChunk r0 = r0.getNextChunk(r6)     // Catch:{ all -> 0x0902 }
            r3 = 1
            r4 = 0
            goto L_0x071b
        L_0x07dc:
            fm.liveswitch.SctpReceiveDataQueue r0 = r1.__receiveDataQueue     // Catch:{ all -> 0x0902 }
            long r3 = r0.getEarliestTSN()     // Catch:{ all -> 0x0902 }
            fm.liveswitch.SctpDataChunk r0 = r0.getChunk(r3)     // Catch:{ all -> 0x0902 }
            r3 = 1
        L_0x07e7:
            if (r3 == 0) goto L_0x0807
            if (r0 == 0) goto L_0x0807
            boolean r4 = r0.getRaised()     // Catch:{ all -> 0x0902 }
            if (r4 == 0) goto L_0x07fb
            fm.liveswitch.SctpReceiveDataQueue r4 = r1.__receiveDataQueue     // Catch:{ all -> 0x0902 }
            long r5 = r0.getTsn()     // Catch:{ all -> 0x0902 }
            r4.remove(r5)     // Catch:{ all -> 0x0902 }
            goto L_0x07fc
        L_0x07fb:
            r3 = 0
        L_0x07fc:
            fm.liveswitch.SctpReceiveDataQueue r4 = r1.__receiveDataQueue     // Catch:{ all -> 0x0902 }
            long r5 = r0.getTsn()     // Catch:{ all -> 0x0902 }
            fm.liveswitch.SctpDataChunk r0 = r4.getNextChunk(r5)     // Catch:{ all -> 0x0902 }
            goto L_0x07e7
        L_0x0807:
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb     // Catch:{ all -> 0x0902 }
            fm.liveswitch.SctpReceiveDataQueue r3 = r1.__receiveDataQueue     // Catch:{ all -> 0x0902 }
            long r3 = r3.getCumulativeACK()     // Catch:{ all -> 0x0902 }
            r0.setGreatestCumulativeTsnReceived(r3)     // Catch:{ all -> 0x0902 }
        L_0x0812:
            monitor-exit(r2)     // Catch:{ all -> 0x0902 }
            if (r13 != 0) goto L_0x081b
            if (r10 <= 0) goto L_0x0818
            goto L_0x081b
        L_0x0818:
            r2 = 1
            goto L_0x08fe
        L_0x081b:
            java.lang.Object r3 = r1.__stateLock
            monitor-enter(r3)
            int r0 = r19.getNewDataPacketCountTrigger()     // Catch:{ all -> 0x08ff }
            if (r10 <= 0) goto L_0x0855
            fm.liveswitch.SctpTransmissionControlBlock r2 = r1.__tcb     // Catch:{ all -> 0x08ff }
            int r2 = r2.getSackCounter()     // Catch:{ all -> 0x08ff }
            fm.liveswitch.SctpTransmissionControlBlock r4 = r1.__tcb     // Catch:{ all -> 0x08ff }
            r5 = 1
            int r2 = r2 + r5
            r4.setSackCounter(r2)     // Catch:{ all -> 0x08ff }
            int r12 = r12 / r10
            float r2 = (float) r12     // Catch:{ all -> 0x08ff }
            r4 = 1053609165(0x3ecccccd, float:0.4)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x0855
            fm.liveswitch.SctpTransmissionControlBlock r2 = r1.__tcb     // Catch:{ all -> 0x08ff }
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()     // Catch:{ all -> 0x08ff }
            r2.setEarliestAllowedSackSendTime(r4)     // Catch:{ all -> 0x08ff }
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x08ff }
            boolean r2 = r2.getIsVerboseEnabled()     // Catch:{ all -> 0x08ff }
            if (r2 == 0) goto L_0x0852
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x08ff }
            java.lang.String r4 = "Latest SCTP Packet contained too many retransmitted chunks. Sending SACK immediately."
            r2.verbose(r4)     // Catch:{ all -> 0x08ff }
        L_0x0852:
            r18 = 1
            goto L_0x0857
        L_0x0855:
            r18 = 0
        L_0x0857:
            if (r13 == 0) goto L_0x085b
            r18 = 1
        L_0x085b:
            if (r11 != 0) goto L_0x08ce
            fm.liveswitch.SctpTransmissionControlBlock r2 = r1.__tcb     // Catch:{ all -> 0x08ff }
            int r2 = r2.getSackCounter()     // Catch:{ all -> 0x08ff }
            if (r2 < r0) goto L_0x0866
            goto L_0x08ce
        L_0x0866:
            fm.liveswitch.SctpTransmissionControlBlock r2 = r1.__tcb     // Catch:{ all -> 0x08ff }
            int r2 = r2.getSackCounter()     // Catch:{ all -> 0x08ff }
            if (r2 >= r0) goto L_0x08f2
            if (r10 <= 0) goto L_0x08f2
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb     // Catch:{ all -> 0x08ff }
            long r4 = r0.getEarliestAllowedSackSendTime()     // Catch:{ all -> 0x08ff }
            int r0 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x08ff }
            long r6 = (long) r0     // Catch:{ all -> 0x08ff }
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x08bf
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb     // Catch:{ all -> 0x08ff }
            r4 = 500(0x1f4, double:2.47E-321)
            long r6 = fm.liveswitch.Scheduler.getCurrentTime()     // Catch:{ all -> 0x08ff }
            long r6 = r6 + r4
            r0.setEarliestAllowedSackSendTime(r6)     // Catch:{ all -> 0x08ff }
            fm.liveswitch.Scheduler r0 = r1.__scheduler     // Catch:{ all -> 0x08ff }
            fm.liveswitch.ScheduledItem r2 = r1.__outgoingQueueScheduledItem     // Catch:{ all -> 0x08ff }
            boolean r0 = r0.itemIsScheduled(r2)     // Catch:{ all -> 0x08ff }
            if (r0 != 0) goto L_0x08f2
            r0 = 500(0x1f4, float:7.0E-43)
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x08ff }
            boolean r2 = r2.getIsVerboseEnabled()     // Catch:{ all -> 0x08ff }
            if (r2 == 0) goto L_0x08b2
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x08ff }
            java.lang.String r4 = "Starting countdown to sending new data. Scheduling outgoing queue processing in {0}"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x08ff }
            java.lang.String r5 = fm.liveswitch.IntegerExtensions.toString(r5)     // Catch:{ all -> 0x08ff }
            java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x08ff }
            r2.verbose(r4)     // Catch:{ all -> 0x08ff }
        L_0x08b2:
            fm.liveswitch.ScheduledItem r2 = r1.__outgoingQueueScheduledItem     // Catch:{ all -> 0x08ff }
            r2.setDelay(r0)     // Catch:{ all -> 0x08ff }
            fm.liveswitch.Scheduler r0 = r1.__scheduler     // Catch:{ all -> 0x08ff }
            fm.liveswitch.ScheduledItem r2 = r1.__outgoingQueueScheduledItem     // Catch:{ all -> 0x08ff }
            r0.add(r2)     // Catch:{ all -> 0x08ff }
            goto L_0x08f2
        L_0x08bf:
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb     // Catch:{ all -> 0x08ff }
            long r4 = r0.getEarliestAllowedSackSendTime()     // Catch:{ all -> 0x08ff }
            long r6 = fm.liveswitch.Scheduler.getCurrentTime()     // Catch:{ all -> 0x08ff }
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x08f2
            goto L_0x08f0
        L_0x08ce:
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x08ff }
            boolean r0 = r0.getIsVerboseEnabled()     // Catch:{ all -> 0x08ff }
            if (r0 == 0) goto L_0x08e7
            if (r11 == 0) goto L_0x08e0
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x08ff }
            java.lang.String r2 = "One of received DATA chunks contained SACK-Immediately bit set. Will transmit SACK immediately."
            r0.verbose(r2)     // Catch:{ all -> 0x08ff }
            goto L_0x08e7
        L_0x08e0:
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x08ff }
            java.lang.String r2 = "SACK counter exceeded. Will transmit SACK now."
            r0.verbose(r2)     // Catch:{ all -> 0x08ff }
        L_0x08e7:
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb     // Catch:{ all -> 0x08ff }
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()     // Catch:{ all -> 0x08ff }
            r0.setEarliestAllowedSackSendTime(r4)     // Catch:{ all -> 0x08ff }
        L_0x08f0:
            r18 = 1
        L_0x08f2:
            if (r18 == 0) goto L_0x08fb
            fm.liveswitch.Scheduler r0 = r1.__scheduler     // Catch:{ all -> 0x08ff }
            fm.liveswitch.ScheduledItem r2 = r1.__outgoingQueueScheduledItem     // Catch:{ all -> 0x08ff }
            r0.trigger(r2)     // Catch:{ all -> 0x08ff }
        L_0x08fb:
            monitor-exit(r3)     // Catch:{ all -> 0x08ff }
            goto L_0x0818
        L_0x08fe:
            return r2
        L_0x08ff:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x08ff }
            throw r0
        L_0x0902:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0902 }
            throw r0
        L_0x0905:
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = "Failed to parse SCTP packets."
            r0.error(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SctpTransport.doProcessIncomingSctpPacket(fm.liveswitch.DataBuffer):boolean");
    }

    private boolean get_Active() {
        return Global.equals(getState(), SctpTransportState.Closing) || Global.equals(getState(), SctpTransportState.Connected) || Global.equals(getState(), SctpTransportState.Connecting);
    }

    private boolean get_CanSendDataChunksInState() {
        return Global.equals(get_InnerState(), SctpTcbState.Established) || Global.equals(get_InnerState(), SctpTcbState.Closing) || Global.equals(get_InnerState(), SctpTcbState.CookieEchoed);
    }

    private SctpTcbState get_InnerState() {
        synchronized (this.__stateLock) {
            SctpTransmissionControlBlock sctpTransmissionControlBlock = this.__tcb;
            if (sctpTransmissionControlBlock != null) {
                SctpTcbState state = sctpTransmissionControlBlock.getState();
                return state;
            }
            SctpTcbState sctpTcbState = SctpTcbState.ClosedNeverOpened;
            return sctpTcbState;
        }
    }

    public Error getError() {
        return this._error;
    }

    public String getId() {
        return this._id;
    }

    /* access modifiers changed from: package-private */
    public BundleTransport getInnerTransport() {
        return this.__innerTransport;
    }

    public boolean getIsClosed() {
        return Global.equals(getState(), SctpTransportState.Closed) || Global.equals(getState(), SctpTransportState.Closing) || Global.equals(getState(), SctpTransportState.Failed) || Global.equals(getState(), SctpTransportState.New);
    }

    public boolean getLocalSupportsPartialReliabilityExtension() {
        SctpTransmissionControlBlock sctpTransmissionControlBlock = this.__tcb;
        if (sctpTransmissionControlBlock != null) {
            SctpPartialReliabilitySupportParameters partialReliabilitySupport = sctpTransmissionControlBlock.getPartialReliabilitySupport();
            return partialReliabilitySupport != null && partialReliabilitySupport.getPartialReliabilitySupportedByThisEndpoint();
        }
        throw new RuntimeException(new Exception("SCTP: TCB is not set, cannot get support for partial reliability extension."));
    }

    public long getMaxMessageSize() {
        return this.__maxMessageSize;
    }

    public IAction1<SctpMessage> getOnMessage() {
        return this._onMessage;
    }

    public int getPort() {
        return this._port;
    }

    public SctpTransportState getState() {
        synchronized (this.__stateLock) {
            SctpTransmissionControlBlock sctpTransmissionControlBlock = this.__tcb;
            if (sctpTransmissionControlBlock == null) {
                SctpTransportState sctpTransportState = SctpTransportState.New;
                return sctpTransportState;
            } else if (Global.equals(sctpTransmissionControlBlock.getState(), SctpTcbState.ClosedNeverOpened)) {
                SctpTransportState sctpTransportState2 = SctpTransportState.New;
                return sctpTransportState2;
            } else if (Global.equals(this.__tcb.getState(), SctpTcbState.Failed)) {
                SctpTransportState sctpTransportState3 = SctpTransportState.Failed;
                return sctpTransportState3;
            } else if (Global.equals(this.__tcb.getState(), SctpTcbState.Closed)) {
                SctpTransportState sctpTransportState4 = SctpTransportState.Closed;
                return sctpTransportState4;
            } else if (Global.equals(this.__tcb.getState(), SctpTcbState.Closing)) {
                SctpTransportState sctpTransportState5 = SctpTransportState.Closing;
                return sctpTransportState5;
            } else if (Global.equals(this.__tcb.getState(), SctpTcbState.Failing)) {
                SctpTransportState sctpTransportState6 = SctpTransportState.Failing;
                return sctpTransportState6;
            } else if (Global.equals(this.__tcb.getState(), SctpTcbState.CookieEchoed)) {
                SctpTransportState sctpTransportState7 = SctpTransportState.Connecting;
                return sctpTransportState7;
            } else if (Global.equals(this.__tcb.getState(), SctpTcbState.CookieWait)) {
                SctpTransportState sctpTransportState8 = SctpTransportState.Connecting;
                return sctpTransportState8;
            } else if (Global.equals(this.__tcb.getState(), SctpTcbState.Established)) {
                SctpTransportState sctpTransportState9 = SctpTransportState.Connected;
                return sctpTransportState9;
            } else {
                throw new RuntimeException(new Exception(StringExtensions.format("SCTP: unknown TCB state {0}.", (Object) this.__tcb.getState().toString())));
            }
        }
    }

    /* access modifiers changed from: private */
    public void initializationFailure(ScheduledItem scheduledItem) {
        String str;
        SctpResendArgs sctpResendArgs = (SctpResendArgs) scheduledItem.getState();
        SctpTcbState state = sctpResendArgs.getState();
        synchronized (this.__stateLock) {
            if (Global.equals(state, get_InnerState())) {
                if (__log.getIsVerboseEnabled()) {
                    str = StringExtensions.format("SCTP has not received valid response to a control chunk of type {0} within expected time period at {1}.", ByteExtensions.toString(Byte.valueOf(sctpResendArgs.getType())), LongExtensions.toString(Long.valueOf(Scheduler.getCurrentTime())));
                } else {
                    str = StringExtensions.format("SCTP has not received valid response to a control chunk of type {0} within expected time period.", (Object) ByteExtensions.toString(Byte.valueOf(sctpResendArgs.getType())));
                }
                __log.verbose(str);
                closeOnFailure(new Exception(str));
            }
        }
    }

    private void initiate() {
        if (Global.equals(get_InnerState(), SctpTcbState.Closed) || Global.equals(get_InnerState(), SctpTcbState.Failed) || Global.equals(get_InnerState(), SctpTcbState.ClosedNeverOpened)) {
            this.__tcb.setMyVerificationTag(MathAssistant.max(1, (long) (LockedRandomizer.nextDouble() * 4.294967295E9d)));
            SctpTransmissionControlBlock sctpTransmissionControlBlock = this.__tcb;
            sctpTransmissionControlBlock.setNextTsnToSend(sctpTransmissionControlBlock.getMyVerificationTag());
            SctpInitChunk sctpInitChunk = new SctpInitChunk(this.__tcb.getMyVerificationTag(), this.__tcb.getAdvertisedReceiverWindowCredit(), this.__tcb.getOutboundStreams().getCount(), this.__tcb.getInboundStreams().getCount(), this.__tcb.getNextTsnToSend(), this.__tcb.getPartialReliabilitySupport(), this.__tcb.getAuthenticatedChunksSupport(), this.__tcb.getDynamicAddressReconfigurationSupport(), (SctpCookiePreservativeChunkParameter) null, (SctpHostNameAddressChunkParameter) null);
            set_InnerState(SctpTcbState.CookieWait);
            SctpResendArgs sctpResendArgs = new SctpResendArgs(SctpTcbState.CookieWait);
            sctpResendArgs.setType(SctpChunkType.getInitiation());
            ScheduledItem scheduledItem = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
                public String getId() {
                    return "fm.liveswitch.SctpTransport.resendPacket";
                }

                public void invoke(ScheduledItem scheduledItem) {
                    SctpTransport.this.resendPacket(scheduledItem);
                }
            }, 200, 200, 10000, ScheduledItem.getUnset());
            scheduledItem.setState(sctpResendArgs);
            scheduledItem.setTimeoutCallback(new IActionDelegate1<ScheduledItem>() {
                public String getId() {
                    return "fm.liveswitch.SctpTransport.initializationFailure";
                }

                public void invoke(ScheduledItem scheduledItem) {
                    SctpTransport.this.initializationFailure(scheduledItem);
                }
            });
            this.__initiationControlChunkScheduledItem = scheduledItem;
            sctpInitChunk.setResendScheduledItem(scheduledItem);
            this.__sendControlChunkQueue.enqueue(sctpInitChunk);
            if (__log.getIsVerboseEnabled()) {
                __log.verbose(StringExtensions.format("SCTP: sending INIT at {0} and moving into the COOKIE_WAIT state. My verification tag = Next TSN to send: {1}.", LongExtensions.toString(Long.valueOf(Scheduler.getCurrentTime())), LongExtensions.toString(Long.valueOf(this.__tcb.getNextTsnToSend()))));
            } else {
                __log.debug("SCTP: sending INIT and moving into the COOKIE_WAIT state.");
            }
            this.__scheduler.trigger(this.__outgoingQueueScheduledItem);
        }
    }

    private void processError(SctpErrorChunk sctpErrorChunk) {
        for (SctpErrorCause sctpErrorCause : sctpErrorChunk.getErrorCauses()) {
            int causeCode = sctpErrorCause.getCauseCode();
            if (causeCode == 6) {
                if (((SctpUnrecognizedChunkType) sctpErrorCause).getUnrecognizedChunk().getType() == SctpChunkType.getForwardCumulativeTSN()) {
                    SctpPartialReliabilitySupportParameters partialReliabilitySupport = this.__tcb.getPartialReliabilitySupport();
                    if (partialReliabilitySupport != null) {
                        partialReliabilitySupport = new SctpPartialReliabilitySupportParameters(true);
                    }
                    partialReliabilitySupport.setRemoteIndicatedLackOfPRSupport(true);
                    __log.debug("SCTP: Remote party indicates that it does not recognise SCTP Forward Cumulative TSN Chunk. Partial Reliability extension will be disabled for data stream.");
                }
            } else if (causeCode == 8) {
                for (SctpTlvParameter type : ((SctpUnrecognizedParameters) sctpErrorCause).getParameters()) {
                    if (type.getType() == 49152) {
                        SctpPartialReliabilitySupportParameters partialReliabilitySupport2 = this.__tcb.getPartialReliabilitySupport();
                        if (partialReliabilitySupport2 != null) {
                            partialReliabilitySupport2 = new SctpPartialReliabilitySupportParameters(true);
                        }
                        partialReliabilitySupport2.setRemoteIndicatedLackOfPRSupport(true);
                        __log.debug("SCTP: Remote party indicates that it does not recognise SCTP Forward-TSN-Supported-Parameter. Partial Reliability extension will be disabled for data stream.");
                    }
                }
            }
        }
    }

    public void processIncomingSctpPacket(DataBuffer dataBuffer) {
        doProcessIncomingSctpPacket(dataBuffer);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: fm.liveswitch.SctpPacket} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x014d A[Catch:{ Exception -> 0x0218 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0157 A[Catch:{ Exception -> 0x0218 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processOutgoingQueueLoop(fm.liveswitch.ScheduledItem r20) {
        /*
            r19 = this;
            r1 = r19
            fm.liveswitch.Scheduler r0 = r1.__scheduler
            r2 = r20
            r0.remove(r2)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Object r2 = r1.__stateLock     // Catch:{ Exception -> 0x0218 }
            monitor-enter(r2)     // Catch:{ Exception -> 0x0218 }
            long r3 = fm.liveswitch.Scheduler.getCurrentTime()     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpTransmissionControlBlock r5 = r1.__tcb     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpSackChunk r5 = r5.getFreshestReceivedSack()     // Catch:{ all -> 0x0215 }
            r6 = 0
            if (r5 == 0) goto L_0x002b
            fm.liveswitch.SctpSendDataQueue r5 = r1.__sendDataQueue     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpTransmissionControlBlock r7 = r1.__tcb     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpSackChunk r7 = r7.getFreshestReceivedSack()     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpDataChunk[] r5 = r5.processSackChunk(r7)     // Catch:{ all -> 0x0215 }
            goto L_0x002c
        L_0x002b:
            r5 = r6
        L_0x002c:
            fm.liveswitch.SctpTransmissionControlBlock r7 = r1.__tcb     // Catch:{ all -> 0x0215 }
            long r7 = r7.getEarliestAllowedRetransmissionTime()     // Catch:{ all -> 0x0215 }
            r9 = -1
            r11 = 0
            r12 = 1
            int r13 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r13 == 0) goto L_0x0046
            fm.liveswitch.SctpTransmissionControlBlock r7 = r1.__tcb     // Catch:{ all -> 0x0215 }
            long r7 = r7.getEarliestAllowedRetransmissionTime()     // Catch:{ all -> 0x0215 }
            int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r9 >= 0) goto L_0x0046
            r7 = 1
            goto L_0x0047
        L_0x0046:
            r7 = 0
        L_0x0047:
            r1.__dataRetransmission = r7     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpSendDataQueue r7 = r1.__sendDataQueue     // Catch:{ all -> 0x0215 }
            boolean r7 = r7.getNonsentDataAvailable()     // Catch:{ all -> 0x0215 }
            if (r7 == 0) goto L_0x0061
            fm.liveswitch.SctpSendDataQueue r7 = r1.__sendDataQueue     // Catch:{ all -> 0x0215 }
            long r7 = r7.getCwnd()     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpTransmissionControlBlock r9 = r1.__tcb     // Catch:{ all -> 0x0215 }
            long r9 = r9.getMaximumStaticCongestionWindow()     // Catch:{ all -> 0x0215 }
            int r13 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r13 <= 0) goto L_0x0075
        L_0x0061:
            fm.liveswitch.SctpSendDataQueue r7 = r1.__sendDataQueue     // Catch:{ all -> 0x0215 }
            boolean r7 = r7.getAllSentAcked()     // Catch:{ all -> 0x0215 }
            if (r7 != 0) goto L_0x0077
            boolean r7 = r1.__dataRetransmission     // Catch:{ all -> 0x0215 }
            if (r7 == 0) goto L_0x0077
            fm.liveswitch.SctpSendDataQueue r7 = r1.__sendDataQueue     // Catch:{ all -> 0x0215 }
            int r7 = r7.getCount()     // Catch:{ all -> 0x0215 }
            if (r7 <= 0) goto L_0x0077
        L_0x0075:
            r7 = 1
            goto L_0x0078
        L_0x0077:
            r7 = 0
        L_0x0078:
            if (r7 == 0) goto L_0x00ba
            fm.liveswitch.SctpTransmissionControlBlock r8 = r1.__tcb     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpForwardTsnChunk r8 = r8.getMostRecentOutgoingForwardTsnChunk()     // Catch:{ all -> 0x0215 }
            if (r8 == 0) goto L_0x00e5
            fm.liveswitch.SctpTransmissionControlBlock r8 = r1.__tcb     // Catch:{ all -> 0x0215 }
            int r8 = r8.getNumberOfDuplicateForwardTsnRequests()     // Catch:{ all -> 0x0215 }
            if (r8 != 0) goto L_0x0096
            fm.liveswitch.SctpSendControlChunkQueue r8 = r1.__sendControlChunkQueue     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpTransmissionControlBlock r9 = r1.__tcb     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpForwardTsnChunk r9 = r9.getMostRecentOutgoingForwardTsnChunk()     // Catch:{ all -> 0x0215 }
            r8.enqueue(r9)     // Catch:{ all -> 0x0215 }
            goto L_0x00e5
        L_0x0096:
            fm.liveswitch.ScheduledItem r8 = new fm.liveswitch.ScheduledItem     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpTransport$4 r14 = new fm.liveswitch.SctpTransport$4     // Catch:{ all -> 0x0215 }
            r14.<init>()     // Catch:{ all -> 0x0215 }
            r15 = 500(0x1f4, float:7.0E-43)
            int r16 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0215 }
            int r17 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0215 }
            int r18 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0215 }
            r13 = r8
            r13.<init>(r14, r15, r16, r17, r18)     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpTransmissionControlBlock r9 = r1.__tcb     // Catch:{ all -> 0x0215 }
            r9.setSendForwardTsnScheduledItem(r8)     // Catch:{ all -> 0x0215 }
            fm.liveswitch.Scheduler r9 = r1.__scheduler     // Catch:{ all -> 0x0215 }
            r9.add(r8)     // Catch:{ all -> 0x0215 }
            goto L_0x00e5
        L_0x00ba:
            fm.liveswitch.SctpTransmissionControlBlock r8 = r1.__tcb     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpForwardTsnChunk r8 = r8.getMostRecentOutgoingForwardTsnChunk()     // Catch:{ all -> 0x0215 }
            if (r8 == 0) goto L_0x00e5
            fm.liveswitch.ScheduledItem r8 = new fm.liveswitch.ScheduledItem     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpTransport$5 r14 = new fm.liveswitch.SctpTransport$5     // Catch:{ all -> 0x0215 }
            r14.<init>()     // Catch:{ all -> 0x0215 }
            r15 = 500(0x1f4, float:7.0E-43)
            int r16 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0215 }
            int r17 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0215 }
            int r18 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0215 }
            r13 = r8
            r13.<init>(r14, r15, r16, r17, r18)     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpTransmissionControlBlock r9 = r1.__tcb     // Catch:{ all -> 0x0215 }
            r9.setSendForwardTsnScheduledItem(r8)     // Catch:{ all -> 0x0215 }
            fm.liveswitch.Scheduler r9 = r1.__scheduler     // Catch:{ all -> 0x0215 }
            r9.add(r8)     // Catch:{ all -> 0x0215 }
        L_0x00e5:
            fm.liveswitch.SctpSendDataQueue r8 = r1.__sendDataQueue     // Catch:{ all -> 0x0215 }
            long r8 = r8.getCwnd()     // Catch:{ all -> 0x0215 }
            float r8 = (float) r8     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpTransmissionControlBlock r9 = r1.__tcb     // Catch:{ all -> 0x0215 }
            long r9 = r9.getMaximumStaticCongestionWindow()     // Catch:{ all -> 0x0215 }
            float r9 = (float) r9     // Catch:{ all -> 0x0215 }
            r10 = 1063675494(0x3f666666, float:0.9)
            float r9 = r9 * r10
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 < 0) goto L_0x00fe
            r8 = 1
            goto L_0x00ff
        L_0x00fe:
            r8 = 0
        L_0x00ff:
            fm.liveswitch.SctpSendControlChunkQueue r9 = r1.__sendControlChunkQueue     // Catch:{ all -> 0x0215 }
            int r9 = r9.getCount()     // Catch:{ all -> 0x0215 }
            if (r9 > 0) goto L_0x012d
            if (r7 != 0) goto L_0x012d
            fm.liveswitch.SctpTransmissionControlBlock r9 = r1.__tcb     // Catch:{ all -> 0x0215 }
            long r9 = r9.getEarliestAllowedSackSendTime()     // Catch:{ all -> 0x0215 }
            int r13 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0215 }
            long r13 = (long) r13     // Catch:{ all -> 0x0215 }
            int r15 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r15 == 0) goto L_0x012b
            fm.liveswitch.SctpTransmissionControlBlock r9 = r1.__tcb     // Catch:{ all -> 0x0215 }
            long r9 = r9.getEarliestAllowedSackSendTime()     // Catch:{ all -> 0x0215 }
            int r13 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r13 >= 0) goto L_0x012b
            fm.liveswitch.SctpTransmissionControlBlock r9 = r1.__tcb     // Catch:{ all -> 0x0215 }
            int r9 = r9.getSackCounter()     // Catch:{ all -> 0x0215 }
            if (r9 <= 0) goto L_0x012b
            goto L_0x012d
        L_0x012b:
            r9 = 0
            goto L_0x012e
        L_0x012d:
            r9 = 1
        L_0x012e:
            if (r7 == 0) goto L_0x0145
            fm.liveswitch.SctpTransmissionControlBlock r7 = r1.__tcb     // Catch:{ all -> 0x0215 }
            long r13 = r7.getEarliestAllowedRetransmissionTime()     // Catch:{ all -> 0x0215 }
            int r7 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r7 >= 0) goto L_0x0145
            fm.liveswitch.SctpTransmissionControlBlock r7 = r1.__tcb     // Catch:{ all -> 0x0215 }
            int r10 = r19.getT3TimerExtension()     // Catch:{ all -> 0x0215 }
            long r13 = (long) r10     // Catch:{ all -> 0x0215 }
            long r3 = r3 + r13
            r7.setEarliestAllowedRetransmissionTime(r3)     // Catch:{ all -> 0x0215 }
        L_0x0145:
            fm.liveswitch.SctpSendDataQueue r3 = r1.__sendDataQueue     // Catch:{ all -> 0x0215 }
            int r3 = r3.getCount()     // Catch:{ all -> 0x0215 }
            if (r3 <= 0) goto L_0x0155
            fm.liveswitch.SctpSendDataQueue r3 = r1.__sendDataQueue     // Catch:{ all -> 0x0215 }
            fm.liveswitch.SctpDataChunk r3 = r3.getFirstUnAcked()     // Catch:{ all -> 0x0215 }
            r1.__nextDataChunkToBeExaminedForSending = r3     // Catch:{ all -> 0x0215 }
        L_0x0155:
            if (r9 == 0) goto L_0x016d
            fm.liveswitch.Holder r3 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0215 }
            r3.<init>(r6)     // Catch:{ all -> 0x0215 }
            boolean r9 = r1.buildSctpPacket(r3, r8)     // Catch:{ all -> 0x0215 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x0215 }
            r6 = r3
            fm.liveswitch.SctpPacket r6 = (fm.liveswitch.SctpPacket) r6     // Catch:{ all -> 0x0215 }
            if (r6 == 0) goto L_0x0155
            r0.add(r6)     // Catch:{ all -> 0x0215 }
            goto L_0x0155
        L_0x016d:
            monitor-exit(r2)     // Catch:{ all -> 0x0215 }
            java.util.Iterator r2 = r0.iterator()     // Catch:{ Exception -> 0x0218 }
        L_0x0172:
            boolean r3 = r2.hasNext()     // Catch:{ Exception -> 0x0218 }
            if (r3 == 0) goto L_0x0198
            java.lang.Object r3 = r2.next()     // Catch:{ Exception -> 0x0218 }
            fm.liveswitch.SctpPacket r3 = (fm.liveswitch.SctpPacket) r3     // Catch:{ Exception -> 0x0218 }
            int r4 = r1.__numberOfPacketsSentSinceLastProcessorYield     // Catch:{ Exception -> 0x0218 }
            if (r4 < 0) goto L_0x0188
            fm.liveswitch.ManagedThread.sleep(r12)     // Catch:{ Exception -> 0x0218 }
            r1.__numberOfPacketsSentSinceLastProcessorYield = r11     // Catch:{ Exception -> 0x0218 }
            goto L_0x018c
        L_0x0188:
            int r4 = r4 + 1
            r1.__numberOfPacketsSentSinceLastProcessorYield = r4     // Catch:{ Exception -> 0x0218 }
        L_0x018c:
            byte[] r3 = r3.getBytes()     // Catch:{ Exception -> 0x0218 }
            fm.liveswitch.DataBuffer r3 = fm.liveswitch.DataBuffer.wrap(r3)     // Catch:{ Exception -> 0x0218 }
            r1.dispatch(r3)     // Catch:{ Exception -> 0x0218 }
            goto L_0x0172
        L_0x0198:
            if (r5 == 0) goto L_0x01ab
            int r2 = r5.length     // Catch:{ Exception -> 0x0218 }
        L_0x019b:
            if (r11 >= r2) goto L_0x01ab
            r3 = r5[r11]     // Catch:{ Exception -> 0x0218 }
            fm.liveswitch.SctpMessage r3 = r3.getMessage()     // Catch:{ Exception -> 0x0218 }
            if (r3 == 0) goto L_0x01a8
            r3.raiseSuccess()     // Catch:{ Exception -> 0x0218 }
        L_0x01a8:
            int r11 = r11 + 1
            goto L_0x019b
        L_0x01ab:
            r0.clear()     // Catch:{ Exception -> 0x0218 }
            java.lang.Object r2 = r1.__stateLock     // Catch:{ Exception -> 0x0218 }
            monitor-enter(r2)     // Catch:{ Exception -> 0x0218 }
            long r3 = fm.liveswitch.Scheduler.getCurrentTime()     // Catch:{ all -> 0x0212 }
            int r0 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0212 }
            fm.liveswitch.SctpSendDataQueue r5 = r1.__sendDataQueue     // Catch:{ all -> 0x0212 }
            int r5 = r5.getCount()     // Catch:{ all -> 0x0212 }
            r6 = 0
            if (r5 <= 0) goto L_0x01cf
            fm.liveswitch.SctpTransmissionControlBlock r0 = r1.__tcb     // Catch:{ all -> 0x0212 }
            long r8 = r0.getEarliestAllowedRetransmissionTime()     // Catch:{ all -> 0x0212 }
            long r8 = r8 - r3
            long r8 = fm.liveswitch.MathAssistant.max((long) r8, (long) r6)     // Catch:{ all -> 0x0212 }
            int r0 = (int) r8     // Catch:{ all -> 0x0212 }
        L_0x01cf:
            fm.liveswitch.SctpTransmissionControlBlock r5 = r1.__tcb     // Catch:{ all -> 0x0212 }
            int r5 = r5.getSackCounter()     // Catch:{ all -> 0x0212 }
            if (r5 <= 0) goto L_0x01fe
            fm.liveswitch.SctpTransmissionControlBlock r5 = r1.__tcb     // Catch:{ all -> 0x0212 }
            long r8 = r5.getEarliestAllowedSackSendTime()     // Catch:{ all -> 0x0212 }
            int r5 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0212 }
            long r10 = (long) r5     // Catch:{ all -> 0x0212 }
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 == 0) goto L_0x01fe
            fm.liveswitch.SctpTransmissionControlBlock r5 = r1.__tcb     // Catch:{ all -> 0x0212 }
            long r8 = r5.getEarliestAllowedSackSendTime()     // Catch:{ all -> 0x0212 }
            long r8 = r8 - r3
            long r3 = fm.liveswitch.MathAssistant.max((long) r8, (long) r6)     // Catch:{ all -> 0x0212 }
            int r4 = (int) r3     // Catch:{ all -> 0x0212 }
            int r3 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0212 }
            if (r0 <= r3) goto L_0x01fd
            int r0 = fm.liveswitch.MathAssistant.min((int) r0, (int) r4)     // Catch:{ all -> 0x0212 }
            goto L_0x01fe
        L_0x01fd:
            r0 = r4
        L_0x01fe:
            int r3 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0212 }
            if (r0 <= r3) goto L_0x0210
            fm.liveswitch.ScheduledItem r3 = r1.__outgoingQueueScheduledItem     // Catch:{ all -> 0x0212 }
            r3.setDelay(r0)     // Catch:{ all -> 0x0212 }
            fm.liveswitch.Scheduler r0 = r1.__scheduler     // Catch:{ all -> 0x0212 }
            fm.liveswitch.ScheduledItem r3 = r1.__outgoingQueueScheduledItem     // Catch:{ all -> 0x0212 }
            r0.add(r3)     // Catch:{ all -> 0x0212 }
        L_0x0210:
            monitor-exit(r2)     // Catch:{ all -> 0x0212 }
            goto L_0x0232
        L_0x0212:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0212 }
            throw r0     // Catch:{ Exception -> 0x0218 }
        L_0x0215:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0215 }
            throw r0     // Catch:{ Exception -> 0x0218 }
        L_0x0218:
            r0 = move-exception
            boolean r2 = r19.get_Active()
            if (r2 != 0) goto L_0x022b
            fm.liveswitch.SctpTcbState r2 = r19.get_InnerState()
            fm.liveswitch.SctpTcbState r3 = fm.liveswitch.SctpTcbState.ClosedNeverOpened
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)
            if (r2 == 0) goto L_0x0232
        L_0x022b:
            fm.liveswitch.ILog r2 = __log
            java.lang.String r3 = "SCTP Transport: Could not process outgoing queue."
            r2.error((java.lang.String) r3, (java.lang.Exception) r0)
        L_0x0232:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SctpTransport.processOutgoingQueueLoop(fm.liveswitch.ScheduledItem):void");
    }

    /* access modifiers changed from: private */
    public void putForwardTsnOntoControlChunkQueue(ScheduledItem scheduledItem) {
        synchronized (this.__stateLock) {
            this.__tcb.setSendForwardTsnScheduledItem((ScheduledItem) null);
            this.__tcb.setNumberOfDuplicateForwardTsnRequests(0);
            SctpForwardTsnChunk mostRecentOutgoingForwardTsnChunk = this.__tcb.getMostRecentOutgoingForwardTsnChunk();
            if (mostRecentOutgoingForwardTsnChunk != null) {
                this.__sendControlChunkQueue.enqueue(mostRecentOutgoingForwardTsnChunk);
                this.__scheduler.trigger(this.__outgoingQueueScheduledItem);
            }
        }
    }

    private void raiseReceivedMessage(long j) {
        Holder holder = new Holder(new byte[0]);
        Holder holder2 = new Holder(new long[0]);
        assembleMessage(holder, holder2, j);
        byte[] bArr = (byte[]) holder.getValue();
        long[] jArr = (long[]) holder2.getValue();
        IAction1<SctpMessage> onMessage = getOnMessage();
        if (onMessage != null) {
            SctpMessage sctpMessage = new SctpMessage(DataBuffer.wrap(bArr), this.__receiveDataQueue.getChunk(j).getStreamIdentifier());
            sctpMessage.setPayloadType(this.__receiveDataQueue.getChunk(j).getPayloadProtocolIdentifier());
            sctpMessage.setUnordered(this.__receiveDataQueue.getChunk(j).getUnordered());
            onMessage.invoke(sctpMessage);
        }
        for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
            this.__receiveDataQueue.getChunk(jArr[i]).setRaised(true);
        }
    }

    public void removeOnStateChange(IAction1<SctpTransport> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    /* access modifiers changed from: private */
    public void resendPacket(ScheduledItem scheduledItem) {
        SctpResendArgs sctpResendArgs = (SctpResendArgs) scheduledItem.getState();
        SctpTcbState state = sctpResendArgs.getState();
        DataBuffer packetBytes = sctpResendArgs.getPacketBytes();
        synchronized (this.__stateLock) {
            if (Global.equals(state, get_InnerState())) {
                if (__log.getIsVerboseEnabled()) {
                    __log.verbose(StringExtensions.format("SCTP is retransmitting a control chunk {0} at {1}.", ByteExtensions.toString(Byte.valueOf(sctpResendArgs.getType())), LongExtensions.toString(Long.valueOf(Scheduler.getCurrentTime()))));
                }
                dispatch(packetBytes);
            } else {
                this.__scheduler.remove(scheduledItem);
            }
        }
    }

    private boolean respondWithCOOKIE_ACK(SctpCookieEchoChunk sctpCookieEchoChunk, SctpCommonHeader sctpCommonHeader) {
        if (this.__tcb.getSecretKeyForCookie() == null) {
            __log.error("SCTP: missing secret key to extract cookie.");
            return false;
        }
        IntegerHolder integerHolder = new IntegerHolder(0);
        SctpStateCookie parseBytes = SctpStateCookie.parseBytes(sctpCookieEchoChunk.getCookieBytes(), integerHolder, this.__tcb.getSecretKeyForCookie());
        integerHolder.getValue();
        if (parseBytes == null) {
            __log.error("SCTP: Could not extract cookie.");
            return false;
        }
        SctpTransmissionControlBlock sctpTransmissionControlBlock = new SctpTransmissionControlBlock(parseBytes);
        if (Global.equals(get_InnerState(), SctpTcbState.ClosedNeverOpened)) {
            if (sctpCommonHeader.getVerificationTag() == sctpTransmissionControlBlock.getMyVerificationTag()) {
                if (parseBytes.getTimestamp() + 12000 >= Scheduler.getCurrentTime()) {
                    if (__log.getIsVerboseEnabled()) {
                        __log.verbose(StringExtensions.format("SCTP: Received a valid COOKIE_ECHO at {0}.", (Object) LongExtensions.toString(Long.valueOf(Scheduler.getCurrentTime()))));
                    } else {
                        __log.debug("SCTP: Received a valid COOKIE_ECHO.");
                    }
                    this.__tcb.importTcbParameters(sctpTransmissionControlBlock);
                    set_InnerState(SctpTcbState.Established);
                    if (__log.getIsVerboseEnabled()) {
                        __log.verbose(StringExtensions.format("SCTP: Sending COOKIE_ACK at {0}.", (Object) LongExtensions.toString(Long.valueOf(Scheduler.getCurrentTime()))));
                    } else {
                        __log.debug("SCTP: Sending COOKIE_ACK.");
                    }
                    this.__sendControlChunkQueue.enqueue(new SctpCookieAckChunk());
                    this.__scheduler.trigger(this.__outgoingQueueScheduledItem);
                    return true;
                }
                this.__sendControlChunkQueue.enqueue(new SctpErrorChunk(new SctpErrorCause[]{new SctpStaleCookieError(new NullableLong((parseBytes.getTimestamp() + 12000) - Scheduler.getCurrentTime()))}));
                __log.debug("SCTP: Stale cookie at initiation stage.");
                this.__scheduler.trigger(this.__outgoingQueueScheduledItem);
            }
            return false;
        } else if ((!Global.equals(get_InnerState(), SctpTcbState.CookieEchoed) && !Global.equals(get_InnerState(), SctpTcbState.Established)) || parseBytes.getTimestamp() + 12000 < Scheduler.getCurrentTime() || this.__tcb.getMyVerificationTag() != sctpTransmissionControlBlock.getMyVerificationTag()) {
            return false;
        } else {
            if (Global.equals(get_InnerState(), SctpTcbState.CookieEchoed)) {
                if (__log.getIsVerboseEnabled()) {
                    __log.verbose(StringExtensions.format("SCTP: Received a valid COOKIE_ECHO in Cookie Echoed state at {0}.", (Object) LongExtensions.toString(Long.valueOf(Scheduler.getCurrentTime()))));
                } else {
                    __log.debug("SCTP: Received a valid COOKIE_ECHO in Cookie Echoed state.");
                }
                this.__tcb.importTcbParameters(sctpTransmissionControlBlock);
                set_InnerState(SctpTcbState.Established);
            } else {
                if (this.__tcb.getPeerVerificationTag() != sctpTransmissionControlBlock.getPeerVerificationTag()) {
                    this.__tcb.setPeerVerificationTag(sctpTransmissionControlBlock.getPeerVerificationTag());
                    __log.debug("SCTP: Updating peer verification tag from incoming Cookie.");
                }
                if (__log.getIsVerboseEnabled()) {
                    __log.verbose(StringExtensions.format("SCTP: Received a valid duplicate COOKIE_ECHO. Re-sending COOKIE_ACK at {0}.", (Object) LongExtensions.toString(Long.valueOf(Scheduler.getCurrentTime()))));
                } else {
                    __log.debug("SCTP: Received a valid duplicate COOKIE_ECHO. Re-sending COOKIE_ACK.");
                }
            }
            if (__log.getIsVerboseEnabled()) {
                __log.verbose(StringExtensions.format("SCTP: sending COOKIE_ACK at {0}.", (Object) LongExtensions.toString(Long.valueOf(Scheduler.getCurrentTime()))));
            } else {
                __log.debug("SCTP: sending COOKIE_ACK.");
            }
            this.__sendControlChunkQueue.enqueue(new SctpCookieAckChunk());
            this.__scheduler.trigger(this.__outgoingQueueScheduledItem);
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void respondWithCOOKIE_ECHO(fm.liveswitch.SctpInitAckChunk r10) {
        /*
            r9 = this;
            fm.liveswitch.SctpStateCookieChunkParameter r0 = r10.getStateCookieChunk()
            byte[] r0 = r0.getStateCookieBytes()
            if (r0 != 0) goto L_0x001b
            java.lang.String r10 = "SCTP: init ack does not contain properly reflected cookie."
            fm.liveswitch.ILog r0 = __log
            r0.error(r10)
            java.lang.Exception r0 = new java.lang.Exception
            r0.<init>(r10)
            r9.closeOnFailure(r0)
            goto L_0x01d0
        L_0x001b:
            fm.liveswitch.SctpCookieEchoChunk r1 = new fm.liveswitch.SctpCookieEchoChunk
            r1.<init>((byte[]) r0)
            fm.liveswitch.SctpResendArgs r0 = new fm.liveswitch.SctpResendArgs
            fm.liveswitch.SctpTcbState r2 = fm.liveswitch.SctpTcbState.CookieEchoed
            r0.<init>(r2)
            byte r2 = fm.liveswitch.SctpChunkType.getCookieEcho()
            r0.setType(r2)
            fm.liveswitch.ScheduledItem r2 = new fm.liveswitch.ScheduledItem
            fm.liveswitch.SctpTransport$6 r4 = new fm.liveswitch.SctpTransport$6
            r4.<init>()
            r5 = 200(0xc8, float:2.8E-43)
            r6 = 200(0xc8, float:2.8E-43)
            r7 = 10000(0x2710, float:1.4013E-41)
            int r8 = fm.liveswitch.ScheduledItem.getUnset()
            r3 = r2
            r3.<init>(r4, r5, r6, r7, r8)
            r2.setState(r0)
            fm.liveswitch.SctpTransport$7 r0 = new fm.liveswitch.SctpTransport$7
            r0.<init>()
            r2.setTimeoutCallback(r0)
            r9.__initiationControlChunkScheduledItem = r2
            r1.setResendScheduledItem(r2)
            long r2 = r10.getInitiateTag()
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x006e
            java.lang.String r10 = "SCTP: initiate tag is 0. Aborting association establishment."
            fm.liveswitch.ILog r0 = __log
            r0.error(r10)
            java.lang.Exception r0 = new java.lang.Exception
            r0.<init>(r10)
            r9.closeOnFailure(r0)
            goto L_0x01d0
        L_0x006e:
            fm.liveswitch.SctpTransmissionControlBlock r0 = r9.__tcb
            long r2 = r10.getInitiateTag()
            r0.setPeerVerificationTag(r2)
            fm.liveswitch.SctpTransmissionControlBlock r0 = r9.__tcb
            long r2 = r10.getAdvertisedReceiverWindowCredit()
            r0.setPeerReceiverWindowCredit(r2)
            int r0 = r10.getNumberOfInboundStreams()
            fm.liveswitch.SctpTransmissionControlBlock r2 = r9.__tcb
            fm.liveswitch.SctpStreamCollection r2 = r2.getOutboundStreams()
            int r2 = r2.getCount()
            if (r0 >= r2) goto L_0x009a
            fm.liveswitch.SctpTransmissionControlBlock r2 = r9.__tcb
            fm.liveswitch.SctpStreamCollection r3 = new fm.liveswitch.SctpStreamCollection
            r3.<init>(r0)
            r2.setOutboundStreams(r3)
        L_0x009a:
            fm.liveswitch.SctpTransmissionControlBlock r0 = r9.__tcb
            fm.liveswitch.SctpStreamCollection r0 = r0.getOutboundStreams()
            int r0 = r0.getCount()
            if (r0 != 0) goto L_0x00b7
            java.lang.String r10 = "SCTP: The number of outbound streams must be a positive value."
            fm.liveswitch.ILog r0 = __log
            r0.error(r10)
            java.lang.Exception r0 = new java.lang.Exception
            r0.<init>(r10)
            r9.closeOnFailure(r0)
            goto L_0x01d0
        L_0x00b7:
            fm.liveswitch.SctpAuthenticatedChunksParameters r0 = r10.getAuthenticatedChunksParameters()
            if (r0 == 0) goto L_0x00ce
            fm.liveswitch.SctpAuthenticatedChunksParameters r0 = r10.getAuthenticatedChunksParameters()
            boolean r0 = r0.getAuthenticatedChunksSupportedByThisEndpoint()
            if (r0 == 0) goto L_0x00ce
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = "Remote party supports optional SCTP authenticated chunks feature, which is not yet supported by this party. Authenticated chunks feature will be disabled in this association"
            r0.debug(r2)
        L_0x00ce:
            fm.liveswitch.SctpPartialReliabilitySupportParameters r0 = r10.getPartialReliabilityParameters()
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x00f6
            fm.liveswitch.SctpPartialReliabilitySupportParameters r0 = r10.getPartialReliabilityParameters()
            boolean r0 = r0.getPartialReliabilitySupportedByThisEndpoint()
            if (r0 == 0) goto L_0x00f6
            boolean r0 = r9.getLocalSupportsPartialReliabilityExtension()
            if (r0 == 0) goto L_0x00ef
            fm.liveswitch.ILog r0 = __log
            java.lang.String r4 = "SCTP partial reliability has been negotiated for data stream."
            r0.debug(r4)
            r0 = 1
            goto L_0x00f7
        L_0x00ef:
            fm.liveswitch.ILog r0 = __log
            java.lang.String r4 = "Remote party supports optional SCTP partial reliability feature, support for which is currently disabled for the local party. Partial reliability extension support is currently in beta and maybe enabled via DataStream.SupportsSctpPartialReliabilityExtension. Partial reliability feature will be disabled in this association"
            r0.debug(r4)
        L_0x00f6:
            r0 = 0
        L_0x00f7:
            fm.liveswitch.SctpDynamicAddressReconfigurationSupportParameters r4 = r10.getDynamicAddressReconfigurationParameters()
            if (r4 == 0) goto L_0x010e
            fm.liveswitch.SctpDynamicAddressReconfigurationSupportParameters r4 = r10.getDynamicAddressReconfigurationParameters()
            boolean r4 = r4.getDynamicAddressReconfigurationSupportedByThisEndpoint()
            if (r4 == 0) goto L_0x010e
            fm.liveswitch.ILog r4 = __log
            java.lang.String r5 = "Remote party supports optional SCTP address reconfiguration feature, which is not yet supported by this party. Address reconfiguration feature will be disabled in this association"
            r4.debug(r5)
        L_0x010e:
            fm.liveswitch.SctpUnrecognizedParameterChunkParameter r4 = r10.getUnrecognizedParameter()
            if (r4 == 0) goto L_0x0146
            fm.liveswitch.SctpUnrecognizedParameterChunkParameter r4 = r10.getUnrecognizedParameter()
            fm.liveswitch.SctpTlvParameter[] r4 = r4.getUnrecognizedParameters()
            int r5 = r4.length
            r6 = 0
        L_0x011e:
            if (r6 >= r5) goto L_0x0146
            r7 = r4[r6]
            int r7 = r7.getType()
            r8 = 49152(0xc000, float:6.8877E-41)
            if (r7 != r8) goto L_0x0143
            fm.liveswitch.SctpTransmissionControlBlock r0 = r9.__tcb
            fm.liveswitch.SctpPartialReliabilitySupportParameters r0 = r0.getPartialReliabilitySupport()
            if (r0 == 0) goto L_0x0138
            fm.liveswitch.SctpPartialReliabilitySupportParameters r0 = new fm.liveswitch.SctpPartialReliabilitySupportParameters
            r0.<init>(r3)
        L_0x0138:
            r0.setRemoteIndicatedLackOfPRSupport(r3)
            fm.liveswitch.ILog r0 = __log
            java.lang.String r7 = "SCTP: Remote party indicates that it does not recognise SCTP Forward-TSN-Supported-Parameter. Partial Reliability extension will be disabled for data stream."
            r0.debug(r7)
            r0 = 0
        L_0x0143:
            int r6 = r6 + 1
            goto L_0x011e
        L_0x0146:
            fm.liveswitch.SctpTransmissionControlBlock r4 = r9.__tcb
            long r5 = r10.getInitialTsn()
            long r5 = fm.liveswitch.SctpDataChunk.decrementTSN(r5)
            r4.setGreatestReceivedTsn(r5)
            fm.liveswitch.SctpTransmissionControlBlock r4 = r9.__tcb
            long r5 = r4.getGreatestReceivedTsn()
            r4.setGreatestCumulativeTsnReceived(r5)
            fm.liveswitch.SctpReceiveDataQueue r4 = r9.__receiveDataQueue
            long r5 = r10.getInitialTsn()
            r4.setInitialTSN(r5)
            fm.liveswitch.SctpTransmissionControlBlock r4 = r9.__tcb
            fm.liveswitch.SctpTcbState r5 = fm.liveswitch.SctpTcbState.CookieEchoed
            r4.setState(r5)
            if (r0 == 0) goto L_0x0178
            fm.liveswitch.SctpTransmissionControlBlock r0 = r9.__tcb
            fm.liveswitch.SctpPartialReliabilitySupportParameters r0 = r0.getPartialReliabilitySupport()
            r0.setPartialReliabilityUsedInThisAssociation(r3)
            goto L_0x0181
        L_0x0178:
            fm.liveswitch.SctpTransmissionControlBlock r0 = r9.__tcb
            fm.liveswitch.SctpPartialReliabilitySupportParameters r0 = r0.getPartialReliabilitySupport()
            r0.setPartialReliabilityUsedInThisAssociation(r2)
        L_0x0181:
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsVerboseEnabled()
            if (r0 == 0) goto L_0x01a1
            fm.liveswitch.ILog r0 = __log
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            java.lang.String r5 = "SCTP: sending COOKIE_ECHO at {0} and moving into the COOKIE_ECHOED state."
            java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r5, (java.lang.Object) r4)
            r0.verbose(r4)
            goto L_0x01a8
        L_0x01a1:
            fm.liveswitch.ILog r0 = __log
            java.lang.String r4 = "SCTP: sending COOKIE_ECHO and moving into the COOKIE_ECHOED state."
            r0.debug(r4)
        L_0x01a8:
            fm.liveswitch.SctpSendControlChunkQueue r0 = r9.__sendControlChunkQueue
            r0.enqueue(r1)
            fm.liveswitch.SctpGenericChunkParameter[] r10 = r10.getUnrecognizedParametersThatNeedToBeReportedBackToSender()
            if (r10 == 0) goto L_0x01c9
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r10)
            if (r0 <= 0) goto L_0x01c9
            fm.liveswitch.SctpUnrecognizedParameters r0 = new fm.liveswitch.SctpUnrecognizedParameters
            r0.<init>(r10)
            fm.liveswitch.SctpErrorChunk r10 = new fm.liveswitch.SctpErrorChunk
            fm.liveswitch.SctpErrorCause[] r1 = new fm.liveswitch.SctpErrorCause[r3]
            r1[r2] = r0
            r10.<init>(r1)
            r9.__errorToCombineWithCookieEcho = r10
        L_0x01c9:
            fm.liveswitch.Scheduler r10 = r9.__scheduler
            fm.liveswitch.ScheduledItem r0 = r9.__outgoingQueueScheduledItem
            r10.trigger(r0)
        L_0x01d0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SctpTransport.respondWithCOOKIE_ECHO(fm.liveswitch.SctpInitAckChunk):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0131  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void respondWithINIT_ACK(fm.liveswitch.SctpInitChunk r23) {
        /*
            r22 = this;
            r0 = r22
            fm.liveswitch.SctpTcbState r1 = r22.get_InnerState()
            fm.liveswitch.SctpTcbState r2 = fm.liveswitch.SctpTcbState.ClosedNeverOpened
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)
            if (r1 == 0) goto L_0x0021
            r1 = 1
            r3 = 4751297606873776128(0x41efffffffe00000, double:4.294967295E9)
            double r5 = fm.liveswitch.LockedRandomizer.nextDouble()
            double r5 = r5 * r3
            long r3 = (long) r5
            long r1 = fm.liveswitch.MathAssistant.max((long) r1, (long) r3)
            goto L_0x0027
        L_0x0021:
            fm.liveswitch.SctpTransmissionControlBlock r1 = r0.__tcb
            long r1 = r1.getMyVerificationTag()
        L_0x0027:
            long r6 = r23.getInitiateTag()
            fm.liveswitch.ILog r3 = __log
            boolean r3 = r3.getIsVerboseEnabled()
            if (r3 == 0) goto L_0x004e
            fm.liveswitch.ILog r3 = __log
            java.lang.Long r4 = java.lang.Long.valueOf(r1)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            java.lang.Long r5 = java.lang.Long.valueOf(r6)
            java.lang.String r5 = fm.liveswitch.LongExtensions.toString(r5)
            java.lang.String r8 = "Local inititate/verification tag is set to {0}; remote initiate/verification tag is set to {1}."
            java.lang.String r4 = fm.liveswitch.StringExtensions.format(r8, r4, r5)
            r3.verbose(r4)
        L_0x004e:
            long r14 = r23.getAdvertisedReceiverWindowCredit()
            int r3 = r23.getNumberOfInboundStreams()
            fm.liveswitch.SctpTransmissionControlBlock r4 = r0.__tcb
            fm.liveswitch.SctpStreamCollection r4 = r4.getOutboundStreams()
            int r4 = r4.getCount()
            if (r3 >= r4) goto L_0x0065
            r16 = r3
            goto L_0x0067
        L_0x0065:
            r16 = r4
        L_0x0067:
            if (r16 != 0) goto L_0x007a
            fm.liveswitch.ILog r1 = __log
            java.lang.String r2 = "SCTP: The number of outbound channels must be a positive value."
            r1.error(r2)
            java.lang.Exception r1 = new java.lang.Exception
            r1.<init>(r2)
            r0.closeOnFailure(r1)
            goto L_0x01af
        L_0x007a:
            long r3 = r23.getInitialTsn()
            r5 = 0
            fm.liveswitch.SctpAuthenticatedChunksParameters r8 = r23.getAuthenticatedChunksParameters()
            if (r8 == 0) goto L_0x0096
            fm.liveswitch.SctpAuthenticatedChunksParameters r8 = r23.getAuthenticatedChunksParameters()
            boolean r8 = r8.getAuthenticatedChunksSupportedByThisEndpoint()
            if (r8 == 0) goto L_0x0096
            fm.liveswitch.ILog r8 = __log
            java.lang.String r9 = "Remote party supports optional SCTP authenticated chunks feature, which is not yet supported by this party. Authenticated chunks feature will be disabled in this association"
            r8.debug(r9)
        L_0x0096:
            fm.liveswitch.SctpPartialReliabilitySupportParameters r8 = r23.getPartialReliabilityParameters()
            r13 = 1
            if (r8 == 0) goto L_0x00be
            fm.liveswitch.SctpPartialReliabilitySupportParameters r8 = r23.getPartialReliabilityParameters()
            boolean r8 = r8.getPartialReliabilitySupportedByThisEndpoint()
            if (r8 == 0) goto L_0x00be
            boolean r8 = r22.getLocalSupportsPartialReliabilityExtension()
            if (r8 == 0) goto L_0x00b7
            fm.liveswitch.ILog r5 = __log
            java.lang.String r8 = "SCTP partial reliability has been negotiated for data stream."
            r5.debug(r8)
            r17 = 1
            goto L_0x00c0
        L_0x00b7:
            fm.liveswitch.ILog r8 = __log
            java.lang.String r9 = "Remote party supports optional SCTP partial reliability feature, support for which is currently disabled for the local party. Partial reliability extension support is currently in beta and maybe enabled via DataStream.SupportsSctpPartialReliabilityExtension. Partial reliability feature will be disabled in this association"
            r8.debug(r9)
        L_0x00be:
            r17 = 0
        L_0x00c0:
            fm.liveswitch.SctpDynamicAddressReconfigurationSupportParameters r5 = r23.getDynamicAddressReconfigurationParameters()
            if (r5 == 0) goto L_0x00d7
            fm.liveswitch.SctpDynamicAddressReconfigurationSupportParameters r5 = r23.getDynamicAddressReconfigurationParameters()
            boolean r5 = r5.getDynamicAddressReconfigurationSupportedByThisEndpoint()
            if (r5 == 0) goto L_0x00d7
            fm.liveswitch.ILog r5 = __log
            java.lang.String r8 = "Remote party supports optional SCTP address reconfiguration feature, which is not yet supported by this party. Address reconfiguration feature will be disabled in this association"
            r5.debug(r8)
        L_0x00d7:
            r5 = 0
            fm.liveswitch.SctpGenericChunkParameter[] r8 = r23.getUnrecognizedParametersThatNeedToBeReportedBackToSender()
            if (r8 == 0) goto L_0x00f1
            fm.liveswitch.SctpGenericChunkParameter[] r8 = r23.getUnrecognizedParametersThatNeedToBeReportedBackToSender()
            int r8 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r8)
            if (r8 <= 0) goto L_0x00f1
            fm.liveswitch.SctpUnrecognizedParameterChunkParameter r5 = new fm.liveswitch.SctpUnrecognizedParameterChunkParameter
            fm.liveswitch.SctpGenericChunkParameter[] r8 = r23.getUnrecognizedParametersThatNeedToBeReportedBackToSender()
            r5.<init>(r8)
        L_0x00f1:
            r18 = r5
            fm.liveswitch.SctpTransmissionControlBlock r12 = new fm.liveswitch.SctpTransmissionControlBlock
            long r10 = fm.liveswitch.SctpDataChunk.decrementTSN(r3)
            fm.liveswitch.SctpTransmissionControlBlock r3 = r0.__tcb
            fm.liveswitch.DataBuffer r19 = r3.getSecretKeyForCookie()
            r3 = r12
            r4 = r1
            r8 = r14
            r20 = r14
            r14 = r12
            r12 = r16
            r15 = 1
            r13 = r19
            r3.<init>(r4, r6, r8, r10, r12, r13)
            if (r17 == 0) goto L_0x011a
            fm.liveswitch.SctpPartialReliabilitySupportParameters r3 = new fm.liveswitch.SctpPartialReliabilitySupportParameters
            r3.<init>(r15)
            r3.setPartialReliabilityUsedInThisAssociation(r15)
            r14.setPartialReliabilitySupport(r3)
        L_0x011a:
            fm.liveswitch.SctpStateCookie r3 = r14.getNewCookie()
            if (r3 != 0) goto L_0x0131
            java.lang.String r1 = "SCTP: could not prepare cookie. Shutting down."
            fm.liveswitch.ILog r2 = __log
            r2.error(r1)
            java.lang.Exception r2 = new java.lang.Exception
            r2.<init>(r1)
            r0.closeOnFailure(r2)
            goto L_0x01af
        L_0x0131:
            fm.liveswitch.SctpStateCookieChunkParameter r12 = new fm.liveswitch.SctpStateCookieChunkParameter
            r12.<init>((fm.liveswitch.SctpStateCookie) r3)
            fm.liveswitch.SctpInitAckChunk r15 = new fm.liveswitch.SctpInitAckChunk
            fm.liveswitch.SctpTransmissionControlBlock r3 = r0.__tcb
            fm.liveswitch.SctpStreamCollection r3 = r3.getInboundStreams()
            int r9 = r3.getCount()
            r13 = 0
            r3 = r15
            r4 = r1
            r6 = r20
            r8 = r16
            r10 = r1
            r14 = r18
            r3.<init>(r4, r6, r8, r9, r10, r12, r13, r14)
            fm.liveswitch.SctpTransmissionControlBlock r1 = r0.__tcb
            fm.liveswitch.SctpPartialReliabilitySupportParameters r1 = r1.getPartialReliabilitySupport()
            r15.setPartialReliabilityParameters(r1)
            fm.liveswitch.SctpSendControlChunkQueue r1 = r0.__sendControlChunkQueue
            r1.enqueue(r15)
            fm.liveswitch.SctpTcbState r1 = r22.get_InnerState()
            fm.liveswitch.SctpTcbState r2 = fm.liveswitch.SctpTcbState.CookieWait
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)
            if (r1 == 0) goto L_0x016c
            java.lang.String r1 = "COOKIE_WAIT"
            goto L_0x017d
        L_0x016c:
            fm.liveswitch.SctpTcbState r1 = r22.get_InnerState()
            fm.liveswitch.SctpTcbState r2 = fm.liveswitch.SctpTcbState.CookieEchoed
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)
            if (r1 == 0) goto L_0x017b
            java.lang.String r1 = "COOKIE_ECHOED"
            goto L_0x017d
        L_0x017b:
            java.lang.String r1 = "CLOSED"
        L_0x017d:
            fm.liveswitch.ILog r2 = __log
            boolean r2 = r2.getIsVerboseEnabled()
            if (r2 == 0) goto L_0x019d
            fm.liveswitch.ILog r2 = __log
            long r3 = fm.liveswitch.Scheduler.getCurrentTime()
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.String r3 = fm.liveswitch.LongExtensions.toString(r3)
            java.lang.String r4 = "SCTP: Responding with INIT_ACK at {0}. Remaining in {1} state."
            java.lang.String r1 = fm.liveswitch.StringExtensions.format(r4, r3, r1)
            r2.verbose(r1)
            goto L_0x01a8
        L_0x019d:
            fm.liveswitch.ILog r2 = __log
            java.lang.String r3 = "SCTP: Responding with INIT_ACK. Remaining in {0} state."
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r1)
            r2.debug(r1)
        L_0x01a8:
            fm.liveswitch.Scheduler r1 = r0.__scheduler
            fm.liveswitch.ScheduledItem r2 = r0.__outgoingQueueScheduledItem
            r1.trigger(r2)
        L_0x01af:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SctpTransport.respondWithINIT_ACK(fm.liveswitch.SctpInitChunk):void");
    }

    public SctpTransport(Object obj, Scheduler scheduler, BundleTransport bundleTransport, BundleTransport bundleTransport2, int i, int i2, long j) {
        this(obj, scheduler, bundleTransport, bundleTransport2, i, i2, j, 5000);
    }

    public SctpTransport(Object obj, Scheduler scheduler, BundleTransport bundleTransport, BundleTransport bundleTransport2, int i, int i2, long j, int i3) {
        this.__onStateChange = new ArrayList();
        this._onStateChange = null;
        this.__maxMessageSize = (long) getUnset();
        this.__outgoingQueueScheduledItem = null;
        this.__sendControlChunkQueue = new SctpSendControlChunkQueue();
        this.__initiationControlChunkScheduledItem = null;
        this.__stateLock = obj;
        this.__sendDataQueue = new SctpSendDataQueue(this.__stateLock);
        this.__receiveDataQueue = new SctpReceiveDataQueue(this.__stateLock);
        this.__scheduler = scheduler;
        setId(Utility.generateId());
        if (bundleTransport == null) {
            __log.error("Null inner transport argument.");
        }
        this.__innerTransport = bundleTransport;
        this.__innerTransportAlternative = bundleTransport2;
        int t3TimerExtension = getT3TimerExtension();
        this.__outgoingQueueScheduledItem = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
            public String getId() {
                return "fm.liveswitch.SctpTransport.processOutgoingQueueLoop";
            }

            public void invoke(ScheduledItem scheduledItem) {
                SctpTransport.this.processOutgoingQueueLoop(scheduledItem);
            }
        }, 0, 500 > t3TimerExtension ? t3TimerExtension : 500, ScheduledItem.getUnset(), ScheduledItem.getUnset());
        if (i2 < 1) {
            throw new RuntimeException(new Exception("SCTP: Maximum supported number of inbound channels must be at least 1"));
        } else if (i >= 1) {
            SctpTransmissionControlBlock sctpTransmissionControlBlock = new SctpTransmissionControlBlock(i, i2, j);
            sctpTransmissionControlBlock.setMaximumStaticCongestionWindow(500);
            this.__tcb = sctpTransmissionControlBlock;
            setPort(i3);
            this.__sendControlChunkQueue = new SctpSendControlChunkQueue();
            this.__tcb.setPartialReliabilitySupport(new SctpPartialReliabilitySupportParameters(false));
        } else {
            throw new RuntimeException(new Exception("SCTP: Desirable number of outbound channels must be at least 1"));
        }
    }

    public Error sendData(SctpMessage sctpMessage) {
        Object obj;
        SctpMessage sctpMessage2 = sctpMessage;
        if (sctpMessage2 == null || sctpMessage.getPayload() == null || sctpMessage.getPayload().getLength() == 0) {
            return new Error(ErrorCode.SctpNoPayloadData, new Exception("SCTP payload cannot be null or empty."));
        }
        if (((long) sctpMessage.getPayload().getLength()) > getMaxMessageSize() && getMaxMessageSize() != ((long) getUnset())) {
            return new Error(ErrorCode.SctpNoPayloadData, new Exception("Message size transmitted over Data Stream is greater than the peer is willing to receive. Message size: {0} bytes. Maximum message size peer is willing to receive: {1} bytes."));
        }
        byte[] array = sctpMessage.getPayload().toArray();
        boolean unordered = sctpMessage.getUnordered();
        int streamId = sctpMessage.getStreamId();
        long payloadType = sctpMessage.getPayloadType();
        Object obj2 = this.__stateLock;
        synchronized (obj2) {
            try {
                if (streamId > this.__tcb.getOutboundStreams().getCount() - 1) {
                    Error error = new Error(ErrorCode.SctpUnsupportedStream, new Exception(StringExtensions.format("SCTP: Communication on an unsupported SCTP stream {0}.", (Object) IntegerExtensions.toString(Integer.valueOf(streamId)))));
                    return error;
                } else if (!Global.equals(get_InnerState(), SctpTcbState.Established)) {
                    Error error2 = new Error(ErrorCode.SctpInvalidState, new Exception(StringExtensions.format("SCTP: Communication is only allowed in the Established state. Sctp transport is in {0} state.", (Object) getState().toString())));
                    return error2;
                } else {
                    ByteCollection byteCollection = new ByteCollection(array);
                    int min = MathAssistant.min(byteCollection.getCount(), (int) SctpTransmissionControlBlock.MaxDataChunkSize);
                    boolean z = false;
                    boolean z2 = true;
                    int i = 0;
                    while (!z) {
                        boolean z3 = min == byteCollection.getCount() - i ? true : z;
                        if (__log.getIsVerboseEnabled()) {
                            __log.debug(StringExtensions.format("Adding a new DATA chunk with TSN {0} to the outgoing queue at {1}.", LongExtensions.toString(Long.valueOf(this.__tcb.getNextTsnToSend())), LongExtensions.toString(Long.valueOf(Scheduler.getCurrentTime()))));
                        }
                        SctpStream stream = this.__tcb.getOutboundStreams().getStream(streamId);
                        long nextTsnToSend = this.__tcb.getNextTsnToSend();
                        int nextSsn = stream.getNextSsn();
                        boolean z4 = unordered;
                        boolean z5 = unordered;
                        SctpDataChunk sctpDataChunk = r3;
                        long j = nextTsnToSend;
                        int i2 = i;
                        int i3 = min;
                        int i4 = nextSsn;
                        ByteCollection byteCollection2 = byteCollection;
                        obj = obj2;
                        int i5 = streamId;
                        SctpDataChunk sctpDataChunk2 = new SctpDataChunk(z4, z2, z3, j, streamId, i4, payloadType, byteCollection.getRange(i, min), z3);
                        sctpDataChunk.setMessage(sctpMessage2);
                        sctpDataChunk.setAcked(false);
                        this.__sendDataQueue.add(sctpDataChunk);
                        i = i2 + i3;
                        int min2 = MathAssistant.min(byteCollection2.getCount() - i, (int) SctpTransmissionControlBlock.MaxDataChunkSize);
                        SctpTransmissionControlBlock sctpTransmissionControlBlock = this.__tcb;
                        sctpTransmissionControlBlock.setNextTsnToSend(SctpDataChunk.incrementTSN(sctpTransmissionControlBlock.getNextTsnToSend()));
                        min = min2;
                        z = z3;
                        byteCollection = byteCollection2;
                        unordered = z5;
                        obj2 = obj;
                        streamId = i5;
                        z2 = false;
                    }
                    Object obj3 = obj2;
                    int i6 = streamId;
                    if (!unordered) {
                        SctpStream stream2 = this.__tcb.getOutboundStreams().getStream(i6);
                        stream2.setNextSsn(SctpDataChunk.incrementSSN(stream2.getNextSsn()));
                    }
                    this.__scheduler.trigger(this.__outgoingQueueScheduledItem);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        }
    }

    private void set_InnerState(SctpTcbState sctpTcbState) {
        synchronized (this.__stateLock) {
            SctpTransmissionControlBlock sctpTransmissionControlBlock = this.__tcb;
            if (sctpTransmissionControlBlock != null && !Global.equals(sctpTcbState, sctpTransmissionControlBlock.getState())) {
                this.__tcb.setState(sctpTcbState);
                if (__log.getIsVerboseEnabled()) {
                    __log.verbose(StringExtensions.format("SCTP: Moving into the {0} state at {1}.", sctpTcbState.toString(), LongExtensions.toString(Long.valueOf(Scheduler.getCurrentTime()))));
                } else {
                    __log.debug(StringExtensions.format("SCTP: Moving into the {0} state.", (Object) sctpTcbState.toString()));
                }
                IAction1<SctpTransport> iAction1 = this._onStateChange;
                if (iAction1 != null) {
                    iAction1.invoke(this);
                }
            }
        }
    }

    private void setError(Error error) {
        this._error = error;
    }

    private void setId(String str) {
        this._id = str;
    }

    /* access modifiers changed from: package-private */
    public void setInnerTransport(BundleTransport bundleTransport) {
        if (!Global.equals(this.__innerTransport, bundleTransport)) {
            this.__innerTransport = bundleTransport;
        }
    }

    public void setLocalSupportsPartialReliabilityExtension(boolean z) {
        SctpTransmissionControlBlock sctpTransmissionControlBlock = this.__tcb;
        if (sctpTransmissionControlBlock != null) {
            SctpPartialReliabilitySupportParameters partialReliabilitySupport = sctpTransmissionControlBlock.getPartialReliabilitySupport();
            if (partialReliabilitySupport == null) {
                new SctpPartialReliabilitySupportParameters(z);
            } else {
                partialReliabilitySupport.setPartialReliabilitySupportedByThisEndpoint(z);
            }
        } else {
            throw new RuntimeException(new Exception("SCTP: TCB is not set, cannot set support for partial reliability extension."));
        }
    }

    public void setMaxMessageSize(long j) {
        this.__maxMessageSize = j;
    }

    public void setOnMessage(IAction1<SctpMessage> iAction1) {
        this._onMessage = iAction1;
    }

    public void setPort(int i) {
        this._port = i;
    }

    public void start() {
        synchronized (this.__stateLock) {
            if (!get_Active()) {
                this.__scheduler.add(this.__outgoingQueueScheduledItem);
                DtlsTransport dtlsTransport = this.__innerTransport.getDtlsTransport();
                DtlsTransport dtlsTransport2 = this.__innerTransportAlternative.getDtlsTransport();
                if (dtlsTransport != null) {
                    dtlsTransport.removeOnReceive(new IActionDelegate1<DataBuffer>() {
                        public String getId() {
                            return "fm.liveswitch.SctpTransport.processIncomingSctpPacket";
                        }

                        public void invoke(DataBuffer dataBuffer) {
                            SctpTransport.this.processIncomingSctpPacket(dataBuffer);
                        }
                    });
                    dtlsTransport.addOnReceive(new IActionDelegate1<DataBuffer>() {
                        public String getId() {
                            return "fm.liveswitch.SctpTransport.processIncomingSctpPacket";
                        }

                        public void invoke(DataBuffer dataBuffer) {
                            SctpTransport.this.processIncomingSctpPacket(dataBuffer);
                        }
                    });
                }
                if (dtlsTransport2 != null) {
                    dtlsTransport2.removeOnReceive(new IActionDelegate1<DataBuffer>() {
                        public String getId() {
                            return "fm.liveswitch.SctpTransport.processIncomingSctpPacket";
                        }

                        public void invoke(DataBuffer dataBuffer) {
                            SctpTransport.this.processIncomingSctpPacket(dataBuffer);
                        }
                    });
                    dtlsTransport2.addOnReceive(new IActionDelegate1<DataBuffer>() {
                        public String getId() {
                            return "fm.liveswitch.SctpTransport.processIncomingSctpPacket";
                        }

                        public void invoke(DataBuffer dataBuffer) {
                            SctpTransport.this.processIncomingSctpPacket(dataBuffer);
                        }
                    });
                }
                initiate();
            }
        }
    }

    public void stop() {
        synchronized (this.__stateLock) {
            if (!Global.equals(getState(), SctpTransportState.Closed) && !Global.equals(getState(), SctpTransportState.Closing)) {
                __log.debug("SCTP: Association shutdown");
                set_InnerState(SctpTcbState.Closing);
                stopAllDataChunkTransmission();
                stopAllControlChunkTransmission();
                DtlsTransport dtlsTransport = this.__innerTransport.getDtlsTransport();
                BundleTransport bundleTransport = this.__innerTransportAlternative;
                DtlsTransport dtlsTransport2 = bundleTransport != null ? bundleTransport.getDtlsTransport() : null;
                if (dtlsTransport != null) {
                    dtlsTransport.removeOnReceive(new IActionDelegate1<DataBuffer>() {
                        public String getId() {
                            return "fm.liveswitch.SctpTransport.processIncomingSctpPacket";
                        }

                        public void invoke(DataBuffer dataBuffer) {
                            SctpTransport.this.processIncomingSctpPacket(dataBuffer);
                        }
                    });
                }
                if (dtlsTransport2 != null) {
                    dtlsTransport2.removeOnReceive(new IActionDelegate1<DataBuffer>() {
                        public String getId() {
                            return "fm.liveswitch.SctpTransport.processIncomingSctpPacket";
                        }

                        public void invoke(DataBuffer dataBuffer) {
                            SctpTransport.this.processIncomingSctpPacket(dataBuffer);
                        }
                    });
                }
                set_InnerState(SctpTcbState.Closed);
                this.__innerTransport = null;
                this.__innerTransportAlternative = null;
            }
        }
    }

    private void stopAllControlChunkTransmission() {
        this.__sendControlChunkQueue.removeAll();
        Scheduler scheduler = this.__scheduler;
        if (scheduler != null) {
            scheduler.remove(this.__initiationControlChunkScheduledItem);
        }
    }

    private void stopAllDataChunkTransmission() {
        SctpMessage message;
        synchronized (this.__stateLock) {
            for (long chunk : this.__sendDataQueue.getTsns()) {
                SctpDataChunk chunk2 = this.__sendDataQueue.getChunk(chunk);
                if (chunk2.getEnding() && (message = chunk2.getMessage()) != null) {
                    message.raiseFailure(new Exception(StringExtensions.format("SCTP: message delivery not acknowledged before shutdown. Chunk TSN {0}, chunk status: {1}acknowledged.", LongExtensions.toString(Long.valueOf(chunk2.getTsn())), chunk2.getAcked() ? "" : "not ")));
                }
            }
            this.__sendDataQueue.removeAll();
            Scheduler scheduler = this.__scheduler;
            if (scheduler != null) {
                scheduler.remove(this.__outgoingQueueScheduledItem);
            }
        }
        this.__tcb.resetAssociationState();
    }

    /* access modifiers changed from: package-private */
    public void switchToAlternativeInternalTransport() {
        setInnerTransport(this.__innerTransportAlternative);
    }
}
