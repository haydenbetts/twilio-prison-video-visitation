package fm.liveswitch;

class SrtpContext {
    private static IDataBufferPool __dataBufferPool;
    private static ILog __log;
    private static byte __rtcpAuthLabel = 4;
    private static byte __rtcpKeyLabel = 3;
    private static byte __rtcpSaltLabel = 5;
    private static byte __rtpAuthLabel = 1;
    private static byte __rtpKeyLabel = 0;
    private static byte __rtpSaltLabel = 2;
    private volatile boolean __clearing = false;
    private volatile boolean __decryptingRtcp = false;
    private volatile boolean __decryptingRtp = false;
    private volatile boolean __encryptingRtcp = false;
    private volatile boolean __encryptingRtp = false;
    private String __id = Utility.generateId();
    private DataBuffer __localKey;
    private DataBuffer __localSalt;
    private long __localSynchronizationSource = -1;
    private String __protectionProfile;
    private DataBuffer __remoteKey;
    private DataBuffer __remoteSalt;
    private long __remoteSynchronizationSource = -1;
    private AesCounter __rtcpDecryption;
    private MacContext __rtcpDecryptionMacContext;
    private AesCounter __rtcpEncryption;
    private MacContext __rtcpEncryptionMacContext;
    private int __rtcpIntegritySize;
    private AesCounter __rtpDecryptionCounter;
    private int __rtpDecryptionHighestSequenceNumber = -1;
    private MacContext __rtpDecryptionMacContext;
    private long __rtpDecryptionRoc = 0;
    private AesCounter __rtpEncryptionCounter;
    private int __rtpEncryptionHighestSequenceNumber = -1;
    private MacContext __rtpEncryptionMacContext;
    private long __rtpEncryptionRoc = 0;
    private int __rtpIntegritySize;
    private int __srtcpIndex = 0;
    private boolean _disabled;

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.RtcpPacket[] decryptRtcp(fm.liveswitch.DataBuffer r15) {
        /*
            r14 = this;
            boolean r0 = r14.getDisabled()
            if (r0 == 0) goto L_0x000b
            fm.liveswitch.RtcpPacket[] r15 = fm.liveswitch.RtcpPacket.parse(r15)
            return r15
        L_0x000b:
            boolean r0 = r14.__clearing
            r1 = 0
            if (r0 != 0) goto L_0x00cf
            r0 = 1
            r14.__decryptingRtcp = r0
            r0 = 0
            boolean r2 = r14.__clearing     // Catch:{ Exception -> 0x00b4, all -> 0x00b2 }
            if (r2 != 0) goto L_0x00af
            int r2 = r15.getLength()     // Catch:{ Exception -> 0x00b4, all -> 0x00b2 }
            int r3 = r14.__rtcpIntegritySize     // Catch:{ Exception -> 0x00b4, all -> 0x00b2 }
            int r4 = r3 + 12
            if (r2 >= r4) goto L_0x0025
            r14.__decryptingRtcp = r0
            return r1
        L_0x0025:
            int r2 = r15.getLength()     // Catch:{ Exception -> 0x00b4, all -> 0x00b2 }
            r4 = 4
            int r2 = r2 - r4
            int r2 = r2 - r3
            int r5 = r2 + 4
            fm.liveswitch.DataBuffer r6 = r15.subset(r5)     // Catch:{ Exception -> 0x00b4, all -> 0x00b2 }
            fm.liveswitch.DataBuffer r5 = r15.subset(r0, r5)     // Catch:{ Exception -> 0x00b4, all -> 0x00b2 }
            fm.liveswitch.MacContext r7 = r14.__rtcpDecryptionMacContext     // Catch:{ Exception -> 0x00b4, all -> 0x00b2 }
            fm.liveswitch.DataBuffer r5 = r7.compute(r5)     // Catch:{ Exception -> 0x00b4, all -> 0x00b2 }
            fm.liveswitch.DataBuffer r7 = r5.subset(r0, r3)     // Catch:{ Exception -> 0x00ad }
            byte[] r8 = r6.getData()     // Catch:{ Exception -> 0x00ad }
            int r6 = r6.getIndex()     // Catch:{ Exception -> 0x00ad }
            byte[] r9 = r7.getData()     // Catch:{ Exception -> 0x00ad }
            int r7 = r7.getIndex()     // Catch:{ Exception -> 0x00ad }
            boolean r3 = fm.liveswitch.BitAssistant.sequencesAreEqual(r8, r6, r9, r7, r3)     // Catch:{ Exception -> 0x00ad }
            r6 = 8
            if (r3 != 0) goto L_0x0077
            fm.liveswitch.ILog r2 = __log     // Catch:{ Exception -> 0x00ad }
            java.lang.String r3 = "Could not decrypt RTCP. Auth check failed. Packet Header: {0}; Packet: {1}"
            fm.liveswitch.DataBuffer r4 = r15.subset(r0, r6)     // Catch:{ Exception -> 0x00ad }
            java.lang.String r4 = r4.toHexString()     // Catch:{ Exception -> 0x00ad }
            java.lang.String r15 = r15.toHexString()     // Catch:{ Exception -> 0x00ad }
            java.lang.String r15 = fm.liveswitch.StringExtensions.format(r3, r4, r15)     // Catch:{ Exception -> 0x00ad }
            r2.error(r15)     // Catch:{ Exception -> 0x00ad }
            r14.__decryptingRtcp = r0
            if (r5 == 0) goto L_0x0076
            r5.free()
        L_0x0076:
            return r1
        L_0x0077:
            long r9 = r15.read32(r4)     // Catch:{ Exception -> 0x00ad }
            r15.write1(r0, r2, r0)     // Catch:{ Exception -> 0x00ad }
            long r11 = r15.read32(r2)     // Catch:{ Exception -> 0x00ad }
            int r3 = r2 + -8
            fm.liveswitch.DataBuffer r8 = r15.subset(r6, r3)     // Catch:{ Exception -> 0x00ad }
            fm.liveswitch.AesCounter r7 = r14.__rtcpDecryption     // Catch:{ Exception -> 0x00ad }
            if (r7 == 0) goto L_0x009d
            r13 = 1
            fm.liveswitch.DataBuffer r3 = r7.decrypt(r8, r9, r11, r13)     // Catch:{ Exception -> 0x00ad }
            r15.write(r3, r6)     // Catch:{ all -> 0x0098 }
            r3.free()     // Catch:{ Exception -> 0x00ad }
            goto L_0x009d
        L_0x0098:
            r15 = move-exception
            r3.free()     // Catch:{ Exception -> 0x00ad }
            throw r15     // Catch:{ Exception -> 0x00ad }
        L_0x009d:
            fm.liveswitch.DataBuffer r15 = r15.subset(r0, r2)     // Catch:{ Exception -> 0x00ad }
            fm.liveswitch.RtcpPacket[] r15 = fm.liveswitch.RtcpPacket.parse(r15)     // Catch:{ Exception -> 0x00ad }
            r14.__decryptingRtcp = r0
            if (r5 == 0) goto L_0x00ac
            r5.free()
        L_0x00ac:
            return r15
        L_0x00ad:
            r15 = move-exception
            goto L_0x00b6
        L_0x00af:
            r14.__decryptingRtcp = r0
            goto L_0x00cf
        L_0x00b2:
            r15 = move-exception
            goto L_0x00c7
        L_0x00b4:
            r15 = move-exception
            r5 = r1
        L_0x00b6:
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = "Could not decrypt RTCP packet."
            r2.error((java.lang.String) r3, (java.lang.Exception) r15)     // Catch:{ all -> 0x00c5 }
            r14.__decryptingRtcp = r0
            if (r5 == 0) goto L_0x00cf
            r5.free()
            goto L_0x00cf
        L_0x00c5:
            r15 = move-exception
            r1 = r5
        L_0x00c7:
            r14.__decryptingRtcp = r0
            if (r1 == 0) goto L_0x00ce
            r1.free()
        L_0x00ce:
            throw r15
        L_0x00cf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SrtpContext.decryptRtcp(fm.liveswitch.DataBuffer):fm.liveswitch.RtcpPacket[]");
    }

    public RtpPacketPair decryptRtp(DataBuffer dataBuffer) {
        return decryptRtp(dataBuffer, (RtpPacketHeader) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:70:0x01b8 A[Catch:{ all -> 0x02a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0255  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.RtpPacketPair decryptRtp(fm.liveswitch.DataBuffer r35, fm.liveswitch.RtpPacketHeader r36) {
        /*
            r34 = this;
            r8 = r34
            r0 = r35
            if (r36 != 0) goto L_0x000c
            fm.liveswitch.RtpPacketHeader r1 = fm.liveswitch.RtpPacketHeader.readFrom(r35)
            r9 = r1
            goto L_0x000e
        L_0x000c:
            r9 = r36
        L_0x000e:
            r10 = 0
            if (r9 == 0) goto L_0x02c4
            int r1 = r9.calculateHeaderLength()
            int r2 = r35.getLength()
            int r2 = r2 - r1
            boolean r3 = r34.getDisabled()
            if (r3 == 0) goto L_0x002e
            fm.liveswitch.RtpPacketPair r3 = new fm.liveswitch.RtpPacketPair
            fm.liveswitch.DataBuffer r0 = r0.subset(r1, r2)
            fm.liveswitch.DataBuffer r0 = r0.keep()
            r3.<init>(r9, r0)
            return r3
        L_0x002e:
            boolean r3 = r8.__clearing
            if (r3 != 0) goto L_0x02c4
            r11 = 1
            r8.__decryptingRtp = r11
            r12 = 0
            boolean r3 = r8.__clearing     // Catch:{ Exception -> 0x02b8 }
            if (r3 != 0) goto L_0x02b2
            int r3 = r35.getLength()     // Catch:{ Exception -> 0x02b8 }
            int r4 = fm.liveswitch.RtpPacketHeader.getFixedHeaderLength()     // Catch:{ Exception -> 0x02b8 }
            int r13 = r8.__rtpIntegritySize     // Catch:{ Exception -> 0x02b8 }
            int r4 = r4 + r13
            if (r3 >= r4) goto L_0x004a
            r8.__decryptingRtp = r12
            return r10
        L_0x004a:
            int r3 = r35.getLength()     // Catch:{ Exception -> 0x02b8 }
            int r14 = r3 - r13
            int r2 = r2 - r13
            if (r2 <= 0) goto L_0x005a
            fm.liveswitch.DataBuffer r1 = r0.subset(r1, r2)     // Catch:{ Exception -> 0x02b8 }
            r16 = r1
            goto L_0x005c
        L_0x005a:
            r16 = r10
        L_0x005c:
            fm.liveswitch.IDataBufferPool r1 = __dataBufferPool     // Catch:{ Exception -> 0x02b8 }
            fm.liveswitch.DataBuffer r15 = r1.take(r13)     // Catch:{ Exception -> 0x02b8 }
            fm.liveswitch.DataBuffer r1 = r0.subset(r14)     // Catch:{ all -> 0x02ac }
            r15.write(r1, r12)     // Catch:{ all -> 0x02ac }
            fm.liveswitch.LongHolder r1 = new fm.liveswitch.LongHolder     // Catch:{ all -> 0x02ac }
            r6 = 0
            r1.<init>(r6)     // Catch:{ all -> 0x02ac }
            int r2 = r9.getSequenceNumber()     // Catch:{ all -> 0x02ac }
            long r19 = r8.getRtpDecryptionPacketIndex(r2, r1)     // Catch:{ all -> 0x02ac }
            long r4 = r1.getValue()     // Catch:{ all -> 0x02ac }
            int r1 = r14 + 4
            fm.liveswitch.DataBuffer r17 = r0.subset(r12, r1)     // Catch:{ all -> 0x02ac }
            r1 = r34
            r2 = r15
            r3 = r17
            r21 = r4
            r23 = r6
            r6 = r13
            r7 = r14
            boolean r1 = r1.rtpDecryptionTagCheck(r2, r3, r4, r6, r7)     // Catch:{ all -> 0x02ac }
            if (r1 == 0) goto L_0x00ac
            fm.liveswitch.AesCounter r0 = r8.__rtpDecryptionCounter     // Catch:{ all -> 0x02ac }
            long r17 = r9.getSynchronizationSource()     // Catch:{ all -> 0x02ac }
            r21 = 1
            r7 = r15
            r15 = r0
            fm.liveswitch.DataBuffer r0 = r15.decrypt(r16, r17, r19, r21)     // Catch:{ all -> 0x02a9 }
            fm.liveswitch.RtpPacketPair r1 = new fm.liveswitch.RtpPacketPair     // Catch:{ all -> 0x02a9 }
            r1.<init>(r9, r0)     // Catch:{ all -> 0x02a9 }
            r7.free()     // Catch:{ Exception -> 0x02b8 }
            r8.__decryptingRtp = r12
            return r1
        L_0x00ac:
            r7 = r15
            int r1 = r9.getSequenceNumber()     // Catch:{ all -> 0x02a9 }
            r15 = 5
            java.lang.String r6 = "Could not decrypt RTP. Auth check failed for sequence number {0}. Packet Header: {1}; Payload Type: {2}; Tag: {3}; Packet: {4}"
            r4 = 32768(0x8000, float:4.5918E-41)
            r25 = 1
            r27 = 3
            r28 = 2
            r5 = 12
            r3 = 4
            if (r1 >= r4) goto L_0x0194
            r1 = r21
            int r18 = (r1 > r23 ? 1 : (r1 == r23 ? 0 : -1))
            if (r18 <= 0) goto L_0x0196
            long r21 = r1 - r25
            r1 = r34
            r2 = r7
            r4 = 4
            r3 = r17
            r10 = 12
            r11 = 4
            r4 = r21
            r29 = r6
            r6 = r13
            r13 = r7
            r7 = r14
            boolean r1 = r1.rtpDecryptionTagCheck(r2, r3, r4, r6, r7)     // Catch:{ all -> 0x0190 }
            if (r1 == 0) goto L_0x0141
            fm.liveswitch.AesCounter r15 = r8.__rtpDecryptionCounter     // Catch:{ all -> 0x0190 }
            long r17 = r9.getSynchronizationSource()     // Catch:{ all -> 0x0190 }
            r21 = 1
            fm.liveswitch.DataBuffer r1 = r15.decrypt(r16, r17, r19, r21)     // Catch:{ all -> 0x0190 }
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x0190 }
            boolean r2 = r2.getIsDebugEnabled()     // Catch:{ all -> 0x0190 }
            if (r2 == 0) goto L_0x0136
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x0190 }
            java.lang.String r3 = "Recovered RTP packet after failed decryption. Original auth check failed for sequence number {0}. Packet Header: {1}; Payload Type: {2}; Tag: {3}"
            java.lang.Object[] r4 = new java.lang.Object[r11]     // Catch:{ all -> 0x0190 }
            int r5 = r9.getSequenceNumber()     // Catch:{ all -> 0x0190 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0190 }
            java.lang.String r5 = fm.liveswitch.IntegerExtensions.toString(r5)     // Catch:{ all -> 0x0190 }
            r4[r12] = r5     // Catch:{ all -> 0x0190 }
            fm.liveswitch.DataBuffer r0 = r0.subset(r12, r10)     // Catch:{ all -> 0x0190 }
            java.lang.String r0 = r0.toHexString()     // Catch:{ all -> 0x0190 }
            r5 = 1
            r4[r5] = r0     // Catch:{ all -> 0x0190 }
            int r0 = r9.getPayloadType()     // Catch:{ all -> 0x0190 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0190 }
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)     // Catch:{ all -> 0x0190 }
            r4[r28] = r0     // Catch:{ all -> 0x0190 }
            long r5 = r13.read32(r12)     // Catch:{ all -> 0x0190 }
            java.lang.Long r0 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0190 }
            java.lang.String r0 = fm.liveswitch.LongExtensions.toString(r0)     // Catch:{ all -> 0x0190 }
            r4[r27] = r0     // Catch:{ all -> 0x0190 }
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ all -> 0x0190 }
            r2.debug(r0)     // Catch:{ all -> 0x0190 }
        L_0x0136:
            fm.liveswitch.RtpPacketPair r0 = new fm.liveswitch.RtpPacketPair     // Catch:{ all -> 0x0190 }
            r0.<init>(r9, r1)     // Catch:{ all -> 0x0190 }
            r13.free()     // Catch:{ Exception -> 0x02b8 }
            r8.__decryptingRtp = r12
            return r0
        L_0x0141:
            fm.liveswitch.ILog r1 = __log     // Catch:{ all -> 0x0190 }
            java.lang.Object[] r2 = new java.lang.Object[r15]     // Catch:{ all -> 0x0190 }
            int r3 = r9.getSequenceNumber()     // Catch:{ all -> 0x0190 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0190 }
            java.lang.String r3 = fm.liveswitch.IntegerExtensions.toString(r3)     // Catch:{ all -> 0x0190 }
            r2[r12] = r3     // Catch:{ all -> 0x0190 }
            fm.liveswitch.DataBuffer r3 = r0.subset(r12, r10)     // Catch:{ all -> 0x0190 }
            java.lang.String r3 = r3.toHexString()     // Catch:{ all -> 0x0190 }
            r4 = 1
            r2[r4] = r3     // Catch:{ all -> 0x0190 }
            int r3 = r9.getPayloadType()     // Catch:{ all -> 0x0190 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0190 }
            java.lang.String r3 = fm.liveswitch.IntegerExtensions.toString(r3)     // Catch:{ all -> 0x0190 }
            r2[r28] = r3     // Catch:{ all -> 0x0190 }
            long r3 = r13.read32(r12)     // Catch:{ all -> 0x0190 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0190 }
            java.lang.String r3 = fm.liveswitch.LongExtensions.toString(r3)     // Catch:{ all -> 0x0190 }
            r2[r27] = r3     // Catch:{ all -> 0x0190 }
            java.lang.String r0 = r35.toHexString()     // Catch:{ all -> 0x0190 }
            r2[r11] = r0     // Catch:{ all -> 0x0190 }
            r7 = r29
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r7, (java.lang.Object[]) r2)     // Catch:{ all -> 0x0190 }
            r1.error(r0)     // Catch:{ all -> 0x0190 }
            r13.free()     // Catch:{ Exception -> 0x02b8 }
        L_0x018c:
            r8.__decryptingRtp = r12
            r1 = 0
            return r1
        L_0x0190:
            r0 = move-exception
            r5 = r13
            goto L_0x02ae
        L_0x0194:
            r1 = r21
        L_0x0196:
            r10 = 12
            r11 = 4
            r33 = r7
            r7 = r6
            r6 = r33
            long r21 = r1 + r25
            r2 = r1
            r1 = r34
            r30 = r2
            r2 = r6
            r3 = r17
            r15 = 32768(0x8000, float:4.5918E-41)
            r4 = r21
            r32 = r6
            r6 = r13
            r13 = r7
            r7 = r14
            boolean r1 = r1.rtpDecryptionTagCheck(r2, r3, r4, r6, r7)     // Catch:{ all -> 0x02a5 }
            if (r1 == 0) goto L_0x0255
            int r1 = r9.getSequenceNumber()     // Catch:{ all -> 0x02a5 }
            if (r1 < r15) goto L_0x0224
            fm.liveswitch.AesCounter r15 = r8.__rtpDecryptionCounter     // Catch:{ all -> 0x02a5 }
            long r17 = r9.getSynchronizationSource()     // Catch:{ all -> 0x02a5 }
            r21 = 1
            fm.liveswitch.DataBuffer r1 = r15.decrypt(r16, r17, r19, r21)     // Catch:{ all -> 0x02a5 }
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x02a5 }
            boolean r2 = r2.getIsDebugEnabled()     // Catch:{ all -> 0x02a5 }
            if (r2 == 0) goto L_0x0217
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x02a5 }
            java.lang.String r3 = "Recovered RTP packet after failed decryption due to chrome bug. Original auth check failed for sequence number {0}. Packet Header: {1}; Payload Type: {2}; Tag: {3}"
            java.lang.Object[] r4 = new java.lang.Object[r11]     // Catch:{ all -> 0x02a5 }
            int r5 = r9.getSequenceNumber()     // Catch:{ all -> 0x02a5 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x02a5 }
            java.lang.String r5 = fm.liveswitch.IntegerExtensions.toString(r5)     // Catch:{ all -> 0x02a5 }
            r4[r12] = r5     // Catch:{ all -> 0x02a5 }
            fm.liveswitch.DataBuffer r0 = r0.subset(r12, r10)     // Catch:{ all -> 0x02a5 }
            java.lang.String r0 = r0.toHexString()     // Catch:{ all -> 0x02a5 }
            r5 = 1
            r4[r5] = r0     // Catch:{ all -> 0x02a5 }
            int r0 = r9.getPayloadType()     // Catch:{ all -> 0x02a5 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x02a5 }
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)     // Catch:{ all -> 0x02a5 }
            r4[r28] = r0     // Catch:{ all -> 0x02a5 }
            r5 = r32
            long r6 = r5.read32(r12)     // Catch:{ all -> 0x02a3 }
            java.lang.Long r0 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x02a3 }
            java.lang.String r0 = fm.liveswitch.LongExtensions.toString(r0)     // Catch:{ all -> 0x02a3 }
            r4[r27] = r0     // Catch:{ all -> 0x02a3 }
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ all -> 0x02a3 }
            r2.debug(r0)     // Catch:{ all -> 0x02a3 }
            goto L_0x0219
        L_0x0217:
            r5 = r32
        L_0x0219:
            fm.liveswitch.RtpPacketPair r0 = new fm.liveswitch.RtpPacketPair     // Catch:{ all -> 0x02a3 }
            r0.<init>(r9, r1)     // Catch:{ all -> 0x02a3 }
            r5.free()     // Catch:{ Exception -> 0x02b8 }
            r8.__decryptingRtp = r12
            return r0
        L_0x0224:
            r5 = r32
            long r0 = r8.__rtpDecryptionRoc     // Catch:{ all -> 0x02a3 }
            long r0 = r0 + r25
            r8.__rtpDecryptionRoc = r0     // Catch:{ all -> 0x02a3 }
            fm.liveswitch.LongHolder r0 = new fm.liveswitch.LongHolder     // Catch:{ all -> 0x02a3 }
            r1 = r30
            r0.<init>(r1)     // Catch:{ all -> 0x02a3 }
            int r1 = r9.getSequenceNumber()     // Catch:{ all -> 0x02a3 }
            long r19 = r8.getRtpDecryptionPacketIndex(r1, r0)     // Catch:{ all -> 0x02a3 }
            r0.getValue()     // Catch:{ all -> 0x02a3 }
            fm.liveswitch.RtpPacketPair r0 = new fm.liveswitch.RtpPacketPair     // Catch:{ all -> 0x02a3 }
            fm.liveswitch.AesCounter r15 = r8.__rtpDecryptionCounter     // Catch:{ all -> 0x02a3 }
            long r17 = r9.getSynchronizationSource()     // Catch:{ all -> 0x02a3 }
            r21 = 1
            fm.liveswitch.DataBuffer r1 = r15.decrypt(r16, r17, r19, r21)     // Catch:{ all -> 0x02a3 }
            r0.<init>(r9, r1)     // Catch:{ all -> 0x02a3 }
            r5.free()     // Catch:{ Exception -> 0x02b8 }
            r8.__decryptingRtp = r12
            return r0
        L_0x0255:
            r5 = r32
            fm.liveswitch.ILog r1 = __log     // Catch:{ all -> 0x02a3 }
            r2 = 5
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x02a3 }
            int r3 = r9.getSequenceNumber()     // Catch:{ all -> 0x02a3 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x02a3 }
            java.lang.String r3 = fm.liveswitch.IntegerExtensions.toString(r3)     // Catch:{ all -> 0x02a3 }
            r2[r12] = r3     // Catch:{ all -> 0x02a3 }
            fm.liveswitch.DataBuffer r3 = r0.subset(r12, r10)     // Catch:{ all -> 0x02a3 }
            java.lang.String r3 = r3.toHexString()     // Catch:{ all -> 0x02a3 }
            r4 = 1
            r2[r4] = r3     // Catch:{ all -> 0x02a3 }
            int r3 = r9.getPayloadType()     // Catch:{ all -> 0x02a3 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x02a3 }
            java.lang.String r3 = fm.liveswitch.IntegerExtensions.toString(r3)     // Catch:{ all -> 0x02a3 }
            r2[r28] = r3     // Catch:{ all -> 0x02a3 }
            long r3 = r5.read32(r12)     // Catch:{ all -> 0x02a3 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x02a3 }
            java.lang.String r3 = fm.liveswitch.LongExtensions.toString(r3)     // Catch:{ all -> 0x02a3 }
            r2[r27] = r3     // Catch:{ all -> 0x02a3 }
            java.lang.String r0 = r35.toHexString()     // Catch:{ all -> 0x02a3 }
            r2[r11] = r0     // Catch:{ all -> 0x02a3 }
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r13, (java.lang.Object[]) r2)     // Catch:{ all -> 0x02a3 }
            r1.error(r0)     // Catch:{ all -> 0x02a3 }
            r5.free()     // Catch:{ Exception -> 0x02b8 }
            goto L_0x018c
        L_0x02a3:
            r0 = move-exception
            goto L_0x02ae
        L_0x02a5:
            r0 = move-exception
            r5 = r32
            goto L_0x02ae
        L_0x02a9:
            r0 = move-exception
            r5 = r7
            goto L_0x02ae
        L_0x02ac:
            r0 = move-exception
            r5 = r15
        L_0x02ae:
            r5.free()     // Catch:{ Exception -> 0x02b8 }
            throw r0     // Catch:{ Exception -> 0x02b8 }
        L_0x02b2:
            r8.__decryptingRtp = r12
            r1 = 0
            goto L_0x02c5
        L_0x02b6:
            r0 = move-exception
            goto L_0x02c1
        L_0x02b8:
            r0 = move-exception
            fm.liveswitch.ILog r1 = __log     // Catch:{ all -> 0x02b6 }
            java.lang.String r2 = "Could not decrypt RTP packet."
            r1.error((java.lang.String) r2, (java.lang.Exception) r0)     // Catch:{ all -> 0x02b6 }
            goto L_0x02b2
        L_0x02c1:
            r8.__decryptingRtp = r12
            throw r0
        L_0x02c4:
            r1 = r10
        L_0x02c5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SrtpContext.decryptRtp(fm.liveswitch.DataBuffer, fm.liveswitch.RtpPacketHeader):fm.liveswitch.RtpPacketPair");
    }

    public void destroy() {
        if (!getDisabled()) {
            this.__clearing = true;
            while (true) {
                if (!this.__encryptingRtp && !this.__encryptingRtcp && !this.__decryptingRtp && !this.__decryptingRtcp) {
                    break;
                }
                ManagedThread.sleep(10);
            }
            AesCounter aesCounter = this.__rtpEncryptionCounter;
            if (aesCounter != null) {
                aesCounter.clear();
                this.__rtpEncryptionCounter = null;
            }
            AesCounter aesCounter2 = this.__rtcpEncryption;
            if (aesCounter2 != null) {
                aesCounter2.clear();
                this.__rtcpEncryption = null;
            }
            AesCounter aesCounter3 = this.__rtpDecryptionCounter;
            if (aesCounter3 != null) {
                aesCounter3.clear();
                this.__rtpDecryptionCounter = null;
            }
            AesCounter aesCounter4 = this.__rtcpDecryption;
            if (aesCounter4 != null) {
                aesCounter4.clear();
                this.__rtcpDecryption = null;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.DataBuffer encryptRtcp(fm.liveswitch.RtcpPacket[] r18) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            boolean r2 = r17.getDisabled()
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x004a
            int r2 = r0.length
            r5 = 0
            r6 = 0
        L_0x000f:
            if (r5 >= r2) goto L_0x001f
            r7 = r0[r5]
            fm.liveswitch.DataBuffer r7 = r7.getBuffer()
            int r7 = r7.getLength()
            int r6 = r6 + r7
            int r5 = r5 + 1
            goto L_0x000f
        L_0x001f:
            r2 = r0[r4]
            fm.liveswitch.DataBuffer r2 = r2.getBuffer()
            int r4 = r2.getLength()
            r2.resize(r6)
        L_0x002c:
            int r5 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r18)
            if (r3 >= r5) goto L_0x0049
            r5 = r0[r3]
            fm.liveswitch.DataBuffer r5 = r5.getBuffer()
            r2.write(r5, r4)
            r5 = r0[r3]
            fm.liveswitch.DataBuffer r5 = r5.getBuffer()
            int r5 = r5.getLength()
            int r4 = r4 + r5
            int r3 = r3 + 1
            goto L_0x002c
        L_0x0049:
            return r2
        L_0x004a:
            boolean r2 = r1.__clearing
            r5 = 0
            if (r2 != 0) goto L_0x0118
            r1.__encryptingRtcp = r3
            boolean r2 = r1.__clearing     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            if (r2 != 0) goto L_0x00f8
            if (r0 != 0) goto L_0x005a
            r1.__encryptingRtcp = r4
            return r5
        L_0x005a:
            int r2 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r18)     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            if (r2 != 0) goto L_0x0067
            fm.liveswitch.DataBuffer r0 = fm.liveswitch.DataBuffer.getEmpty()     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            r1.__encryptingRtcp = r4
            return r0
        L_0x0067:
            int r2 = r1.__rtcpIntegritySize     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            int r6 = r0.length     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            r7 = 0
            r8 = 0
        L_0x006c:
            if (r7 >= r6) goto L_0x007c
            r9 = r0[r7]     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            fm.liveswitch.DataBuffer r9 = r9.getBuffer()     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            int r9 = r9.getLength()     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            int r8 = r8 + r9
            int r7 = r7 + 1
            goto L_0x006c
        L_0x007c:
            int r6 = r8 + 4
            r7 = r0[r4]     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            fm.liveswitch.DataBuffer r7 = r7.getBuffer()     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            int r9 = r7.getLength()     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            int r10 = r6 + r2
            r7.resize(r10)     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            r10 = 1
        L_0x008e:
            int r11 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r18)     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            if (r10 >= r11) goto L_0x00ab
            r11 = r0[r10]     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            fm.liveswitch.DataBuffer r11 = r11.getBuffer()     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            r7.write(r11, r9)     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            r11 = r0[r10]     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            fm.liveswitch.DataBuffer r11 = r11.getBuffer()     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            int r11 = r11.getLength()     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            int r9 = r9 + r11
            int r10 = r10 + 1
            goto L_0x008e
        L_0x00ab:
            int r0 = r17.getRtcpEncryptionPacketIndex()     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            r9 = 4
            long r12 = r7.read32(r9)     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            int r9 = r8 + -8
            r14 = 8
            fm.liveswitch.DataBuffer r11 = r7.subset(r14, r9)     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            fm.liveswitch.AesCounter r10 = r1.__rtcpEncryption     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            if (r10 == 0) goto L_0x00d6
            long r14 = (long) r0     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            r16 = 1
            r9 = 8
            fm.liveswitch.DataBuffer r10 = r10.encrypt(r11, r12, r14, r16)     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            r7.write(r10, r9)     // Catch:{ all -> 0x00d0 }
            r10.free()     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            goto L_0x00d6
        L_0x00d0:
            r0 = move-exception
            r2 = r0
            r10.free()     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            throw r2     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
        L_0x00d6:
            long r9 = (long) r0     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            r7.write32(r9, r8)     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            r7.write1(r3, r8, r4)     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            fm.liveswitch.DataBuffer r0 = r7.subset(r4, r6)     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            fm.liveswitch.MacContext r3 = r1.__rtcpEncryptionMacContext     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            fm.liveswitch.DataBuffer r3 = r3.compute(r0)     // Catch:{ Exception -> 0x00fd, all -> 0x00fb }
            fm.liveswitch.DataBuffer r0 = r3.subset(r4, r2)     // Catch:{ Exception -> 0x00f6 }
            r7.write(r0, r6)     // Catch:{ Exception -> 0x00f6 }
            r1.__encryptingRtcp = r4
            if (r3 == 0) goto L_0x00f5
            r3.free()
        L_0x00f5:
            return r7
        L_0x00f6:
            r0 = move-exception
            goto L_0x00ff
        L_0x00f8:
            r1.__encryptingRtcp = r4
            goto L_0x0118
        L_0x00fb:
            r0 = move-exception
            goto L_0x0110
        L_0x00fd:
            r0 = move-exception
            r3 = r5
        L_0x00ff:
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x010e }
            java.lang.String r6 = "Could not encrypt RTCP packet."
            r2.error((java.lang.String) r6, (java.lang.Exception) r0)     // Catch:{ all -> 0x010e }
            r1.__encryptingRtcp = r4
            if (r3 == 0) goto L_0x0118
            r3.free()
            goto L_0x0118
        L_0x010e:
            r0 = move-exception
            r5 = r3
        L_0x0110:
            r1.__encryptingRtcp = r4
            if (r5 == 0) goto L_0x0117
            r5.free()
        L_0x0117:
            throw r0
        L_0x0118:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SrtpContext.encryptRtcp(fm.liveswitch.RtcpPacket[]):fm.liveswitch.DataBuffer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.DataBuffer encryptRtp(fm.liveswitch.RtpPacketHeader r18, fm.liveswitch.DataBuffer r19) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r3 = r19
            r9 = 0
            if (r0 == 0) goto L_0x00b3
            if (r3 == 0) goto L_0x00b3
            int r10 = r18.calculateHeaderLength()
            int r2 = r19.getLength()
            int r11 = r10 + r2
            boolean r2 = r17.getDisabled()
            r12 = 0
            if (r2 == 0) goto L_0x0029
            fm.liveswitch.IDataBufferPool r2 = __dataBufferPool
            fm.liveswitch.DataBuffer r2 = r2.take(r11)
            r0.writeTo(r2, r12)
            r2.write(r3, r10)
            return r2
        L_0x0029:
            boolean r2 = r1.__clearing
            if (r2 != 0) goto L_0x00b3
            r2 = 1
            r1.__encryptingRtp = r2
            boolean r2 = r1.__clearing     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            if (r2 != 0) goto L_0x0093
            int r13 = r1.__rtpIntegritySize     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            fm.liveswitch.IDataBufferPool r2 = __dataBufferPool     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            int r4 = r11 + r13
            fm.liveswitch.DataBuffer r14 = r2.take(r4)     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            r0.writeTo(r14, r12)     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            r4 = 0
            fm.liveswitch.AesCounter r2 = r1.__rtpEncryptionCounter     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            if (r2 == 0) goto L_0x0073
            fm.liveswitch.LongHolder r2 = new fm.liveswitch.LongHolder     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            int r4 = r18.getSequenceNumber()     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            long r6 = r1.getRtpEncryptionPacketIndex(r4, r2)     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            long r15 = r2.getValue()     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            fm.liveswitch.AesCounter r2 = r1.__rtpEncryptionCounter     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            long r4 = r18.getSynchronizationSource()     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            r8 = 1
            r3 = r19
            fm.liveswitch.DataBuffer r2 = r2.encrypt(r3, r4, r6, r8)     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            r14.write(r2, r10)     // Catch:{ all -> 0x006d }
            r2.free()     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            r4 = r15
            goto L_0x0073
        L_0x006d:
            r0 = move-exception
            r3 = r0
            r2.free()     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            throw r3     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
        L_0x0073:
            int r0 = r11 + 4
            fm.liveswitch.DataBuffer r0 = r14.subset(r12, r0)     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            r0.write32(r4, r11)     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            fm.liveswitch.MacContext r2 = r1.__rtpEncryptionMacContext     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            fm.liveswitch.DataBuffer r2 = r2.compute(r0)     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            fm.liveswitch.DataBuffer r0 = r2.subset(r12, r13)     // Catch:{ Exception -> 0x0091 }
            r14.write(r0, r11)     // Catch:{ Exception -> 0x0091 }
            r1.__encryptingRtp = r12
            if (r2 == 0) goto L_0x0090
            r2.free()
        L_0x0090:
            return r14
        L_0x0091:
            r0 = move-exception
            goto L_0x009a
        L_0x0093:
            r1.__encryptingRtp = r12
            goto L_0x00b3
        L_0x0096:
            r0 = move-exception
            goto L_0x00ab
        L_0x0098:
            r0 = move-exception
            r2 = r9
        L_0x009a:
            fm.liveswitch.ILog r3 = __log     // Catch:{ all -> 0x00a9 }
            java.lang.String r4 = "Could not encrypt RTP packet."
            r3.error((java.lang.String) r4, (java.lang.Exception) r0)     // Catch:{ all -> 0x00a9 }
            r1.__encryptingRtp = r12
            if (r2 == 0) goto L_0x00b3
            r2.free()
            goto L_0x00b3
        L_0x00a9:
            r0 = move-exception
            r9 = r2
        L_0x00ab:
            r1.__encryptingRtp = r12
            if (r9 == 0) goto L_0x00b2
            r9.free()
        L_0x00b2:
            throw r0
        L_0x00b3:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SrtpContext.encryptRtp(fm.liveswitch.RtpPacketHeader, fm.liveswitch.DataBuffer):fm.liveswitch.DataBuffer");
    }

    public boolean getDisabled() {
        return this._disabled;
    }

    public String getId() {
        return this.__id;
    }

    public DataBuffer getLocalKey() {
        return this.__localKey;
    }

    public DataBuffer getLocalSalt() {
        return this.__localSalt;
    }

    public long getLocalSynchronizationSource() {
        return this.__localSynchronizationSource;
    }

    public String getProtectionProfile() {
        return this.__protectionProfile;
    }

    public DataBuffer getRemoteKey() {
        return this.__remoteKey;
    }

    public DataBuffer getRemoteSalt() {
        return this.__remoteSalt;
    }

    public long getRemoteSynchronizationSource() {
        return this.__remoteSynchronizationSource;
    }

    private int getRtcpEncryptionPacketIndex() {
        int i = this.__srtcpIndex;
        this.__srtcpIndex = i + 1;
        return i;
    }

    public long getRtpDecryptionPacketIndex(int i) {
        LongHolder longHolder = new LongHolder(0);
        long rtpDecryptionPacketIndex = getRtpDecryptionPacketIndex(i, longHolder);
        longHolder.getValue();
        return rtpDecryptionPacketIndex;
    }

    public long getRtpDecryptionPacketIndex(int i, LongHolder longHolder) {
        long j;
        int i2 = this.__rtpDecryptionHighestSequenceNumber;
        if (i2 == -1) {
            this.__rtpDecryptionHighestSequenceNumber = i;
            longHolder.setValue(this.__rtpDecryptionRoc);
            return (long) i;
        }
        if (i2 < 32768) {
            if (i - i2 > 32768) {
                j = (this.__rtpDecryptionRoc - 1) % 4294967296L;
            } else {
                j = this.__rtpDecryptionRoc;
                this.__rtpDecryptionHighestSequenceNumber = MathAssistant.max(i2, i);
            }
        } else if (i2 - 32768 > i) {
            j = (this.__rtpDecryptionRoc + 1) % 4294967296L;
            this.__rtpDecryptionHighestSequenceNumber = i;
            this.__rtpDecryptionRoc = j;
        } else {
            j = this.__rtpDecryptionRoc;
            this.__rtpDecryptionHighestSequenceNumber = MathAssistant.max(i2, i);
        }
        longHolder.setValue(j);
        return (j * 65536) + ((long) i);
    }

    public long getRtpEncryptionPacketIndex(int i, LongHolder longHolder) {
        long j;
        int i2 = this.__rtpEncryptionHighestSequenceNumber;
        if (i2 == -1) {
            this.__rtpEncryptionHighestSequenceNumber = i;
            longHolder.setValue(this.__rtpEncryptionRoc);
            return (long) i;
        }
        if (i2 < 32768) {
            if (i - i2 > 32768) {
                j = (this.__rtpEncryptionRoc - 1) % 4294967296L;
            } else {
                j = this.__rtpEncryptionRoc;
                this.__rtpEncryptionHighestSequenceNumber = MathAssistant.max(i2, i);
            }
        } else if (i2 - 32768 > i) {
            j = (this.__rtpEncryptionRoc + 1) % 4294967296L;
            this.__rtpEncryptionHighestSequenceNumber = i;
            this.__rtpEncryptionRoc = j;
        } else {
            j = this.__rtpEncryptionRoc;
            this.__rtpEncryptionHighestSequenceNumber = MathAssistant.max(i2, i);
        }
        longHolder.setValue(j);
        return (j * 65536) + ((long) i);
    }

    private boolean rtpDecryptionTagCheck(DataBuffer dataBuffer, DataBuffer dataBuffer2, long j, int i, int i2) {
        dataBuffer2.write32(j, i2);
        DataBuffer compute = this.__rtpDecryptionMacContext.compute(dataBuffer2);
        if (compute == null) {
            return false;
        }
        try {
            DataBuffer subset = compute.subset(0, i);
            return BitAssistant.sequencesAreEqual(dataBuffer.getData(), dataBuffer.getIndex(), subset.getData(), subset.getIndex(), i);
        } finally {
            compute.free();
        }
    }

    private void setDisabled(boolean z) {
        this._disabled = z;
    }

    public void setLocalSynchronizationSource(long j) {
        this.__localSynchronizationSource = j;
    }

    public void setRemoteSynchronizationSource(long j) {
        this.__remoteSynchronizationSource = j;
    }

    static {
        Class<SrtpContext> cls = SrtpContext.class;
        __dataBufferPool = DataBufferPool.getTracer((Class) cls);
        __log = Log.getLogger((Class) cls);
    }

    public SrtpContext(String str, DataBuffer dataBuffer, DataBuffer dataBuffer2, DataBuffer dataBuffer3, DataBuffer dataBuffer4) {
        if (str == null) {
            setDisabled(true);
            return;
        }
        this.__protectionProfile = str;
        this.__localKey = dataBuffer;
        this.__localSalt = dataBuffer2;
        this.__remoteKey = dataBuffer3;
        this.__remoteSalt = dataBuffer4;
        AesCounter aesCounter = new AesCounter(dataBuffer, dataBuffer2, 0);
        AesCounter aesCounter2 = new AesCounter(dataBuffer3, dataBuffer4, 0);
        if (Global.equals(str, SrtpProtectionProfile.getAes128CmHmacSha132ProtectionProfileString()) || Global.equals(str, SrtpProtectionProfile.getAes128CmHmacSha180ProtectionProfileString())) {
            this.__rtpEncryptionCounter = new AesCounter(aesCounter.generate(__rtpKeyLabel, 16), aesCounter.generate(__rtpSaltLabel, 14), 2048);
            this.__rtcpEncryption = new AesCounter(aesCounter.generate(__rtcpKeyLabel, 16), aesCounter.generate(__rtcpSaltLabel, 14), 2048);
            this.__rtpDecryptionCounter = new AesCounter(aesCounter2.generate(__rtpKeyLabel, 16), aesCounter2.generate(__rtpSaltLabel, 14), 2048);
            this.__rtcpDecryption = new AesCounter(aesCounter2.generate(__rtcpKeyLabel, 16), aesCounter2.generate(__rtcpSaltLabel, 14), 2048);
        }
        this.__rtpEncryptionMacContext = new MacContext(MacType.HmacSha1, aesCounter.generate(__rtpAuthLabel, 20));
        this.__rtcpEncryptionMacContext = new MacContext(MacType.HmacSha1, aesCounter.generate(__rtcpAuthLabel, 20));
        this.__rtpDecryptionMacContext = new MacContext(MacType.HmacSha1, aesCounter2.generate(__rtpAuthLabel, 20));
        this.__rtcpDecryptionMacContext = new MacContext(MacType.HmacSha1, aesCounter2.generate(__rtcpAuthLabel, 20));
        if (Global.equals(str, SrtpProtectionProfile.getAes128CmHmacSha132ProtectionProfileString()) || Global.equals(str, SrtpProtectionProfile.getNullHmacSha132ProtectionProfileString())) {
            this.__rtpIntegritySize = 4;
        } else {
            this.__rtpIntegritySize = 10;
        }
        this.__rtcpIntegritySize = 10;
        aesCounter2.clear();
        aesCounter.clear();
    }
}
