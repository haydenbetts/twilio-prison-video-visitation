package fm.liveswitch;

class SctpInitAckChunk extends SctpControlChunk {
    private long _advertisedReceiverWindowCredit;
    private SctpAuthenticatedChunksParameters _authenticatedChunksParameters;
    private SctpDynamicAddressReconfigurationSupportParameters _dynamicAddressReconfigurationParameters;
    private SctpHostNameAddressChunkParameter _hostNameAddress;
    private SctpIPv4ChunkParameter[] _iPv4Addresses;
    private long _initialTsn;
    private long _initiateTag;
    private int _numberOfInboundStreams;
    private int _numberOfOutboundStreams;
    private SctpPartialReliabilitySupportParameters _partialReliabilityParameters;
    private SctpStateCookieChunkParameter _stateCookieChunk;
    private byte[] _supportedExtensionsChunks;
    private SctpUnrecognizedParameterChunkParameter _unrecognizedParameter;
    private SctpGenericChunkParameter[] _unrecognizedParametersThatNeedToBeReportedBackToSender;

    public long getAdvertisedReceiverWindowCredit() {
        return this._advertisedReceiverWindowCredit;
    }

    public SctpAuthenticatedChunksParameters getAuthenticatedChunksParameters() {
        return this._authenticatedChunksParameters;
    }

    public static byte[] getBytes(SctpInitAckChunk sctpInitAckChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpInitAckChunk.getType());
        byteCollection.add((byte) 0);
        byteCollection.addRange(Binary.toBytes32(sctpInitAckChunk.getInitiateTag(), false));
        byteCollection.addRange(Binary.toBytes32(sctpInitAckChunk.getAdvertisedReceiverWindowCredit(), false));
        byteCollection.addRange(Binary.toBytes16(sctpInitAckChunk.getNumberOfOutboundStreams(), false));
        byteCollection.addRange(Binary.toBytes16(sctpInitAckChunk.getNumberOfInboundStreams(), false));
        byteCollection.addRange(Binary.toBytes32(sctpInitAckChunk.getInitialTsn(), false));
        byteCollection.addRange(sctpInitAckChunk.getStateCookieChunk().getBytes());
        if (sctpInitAckChunk.getIPv4Addresses() != null) {
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) sctpInitAckChunk.getIPv4Addresses()); i++) {
                byteCollection.addRange(sctpInitAckChunk.getIPv4Addresses()[i].getBytes());
            }
        }
        if (sctpInitAckChunk.getHostNameAddress() != null) {
            byteCollection.addRange(sctpInitAckChunk.getHostNameAddress().getBytes());
        }
        if (sctpInitAckChunk.getUnrecognizedParameter() != null) {
            byteCollection.addRange(sctpInitAckChunk.getUnrecognizedParameter().getBytes());
        }
        byteCollection.insertRange(2, Binary.toBytes16(byteCollection.getCount() + 2, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public SctpDynamicAddressReconfigurationSupportParameters getDynamicAddressReconfigurationParameters() {
        return this._dynamicAddressReconfigurationParameters;
    }

    public SctpHostNameAddressChunkParameter getHostNameAddress() {
        return this._hostNameAddress;
    }

    public long getInitialTsn() {
        return this._initialTsn;
    }

    public long getInitiateTag() {
        return this._initiateTag;
    }

    public SctpIPv4ChunkParameter[] getIPv4Addresses() {
        return this._iPv4Addresses;
    }

    public int getNumberOfInboundStreams() {
        return this._numberOfInboundStreams;
    }

    public int getNumberOfOutboundStreams() {
        return this._numberOfOutboundStreams;
    }

    public SctpPartialReliabilitySupportParameters getPartialReliabilityParameters() {
        return this._partialReliabilityParameters;
    }

    public SctpStateCookieChunkParameter getStateCookieChunk() {
        return this._stateCookieChunk;
    }

    public byte[] getSupportedExtensionsChunks() {
        return this._supportedExtensionsChunks;
    }

    public SctpUnrecognizedParameterChunkParameter getUnrecognizedParameter() {
        return this._unrecognizedParameter;
    }

    public SctpGenericChunkParameter[] getUnrecognizedParametersThatNeedToBeReportedBackToSender() {
        return this._unrecognizedParametersThatNeedToBeReportedBackToSender;
    }

    /* JADX WARNING: Removed duplicated region for block: B:73:0x01fe A[Catch:{ Exception -> 0x0229 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0205 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static fm.liveswitch.SctpInitAckChunk parseBytes(byte[] r21, fm.liveswitch.IntegerHolder r22) {
        /*
            r0 = r21
            r1 = r22
            r2 = 0
            r3 = 0
            java.lang.String r4 = "SCTP manager received INIT_ACK chunk from the other party"
            fm.liveswitch.Log.debug(r4)     // Catch:{ Exception -> 0x0229 }
            r4 = 2
            int r4 = fm.liveswitch.Binary.fromBytes16(r0, r4, r3)     // Catch:{ Exception -> 0x0229 }
            r5 = 4
            long r7 = fm.liveswitch.Binary.fromBytes32(r0, r5, r3)     // Catch:{ Exception -> 0x0229 }
            r5 = 8
            long r9 = fm.liveswitch.Binary.fromBytes32(r0, r5, r3)     // Catch:{ Exception -> 0x0229 }
            r6 = 12
            int r11 = fm.liveswitch.Binary.fromBytes16(r0, r6, r3)     // Catch:{ Exception -> 0x0229 }
            r6 = 14
            int r12 = fm.liveswitch.Binary.fromBytes16(r0, r6, r3)     // Catch:{ Exception -> 0x0229 }
            r6 = 16
            long r13 = fm.liveswitch.Binary.fromBytes32(r0, r6, r3)     // Catch:{ Exception -> 0x0229 }
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ Exception -> 0x0229 }
            r15.<init>()     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpInitAckChunk r6 = new fm.liveswitch.SctpInitAckChunk     // Catch:{ Exception -> 0x0229 }
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = r6
            r20 = r15
            r15 = r16
            r16 = r17
            r17 = r18
            r6.<init>(r7, r9, r11, r12, r13, r15, r16, r17)     // Catch:{ Exception -> 0x0229 }
            r6 = 20
            r7 = 1
            r8 = 1
        L_0x004b:
            if (r6 >= r4) goto L_0x0210
            fm.liveswitch.SctpTlvParameter r9 = fm.liveswitch.SctpTlvParameter.parseBytes(r0, r6, r1)     // Catch:{ Exception -> 0x0229 }
            if (r9 != 0) goto L_0x0057
            r1.setValue(r3)     // Catch:{ Exception -> 0x0229 }
            return r2
        L_0x0057:
            int r10 = r9.getType()     // Catch:{ Exception -> 0x0229 }
            r11 = 7
            if (r10 != r11) goto L_0x006c
            if (r8 == 0) goto L_0x006c
            r10 = r9
            fm.liveswitch.SctpStateCookieChunkParameter r10 = (fm.liveswitch.SctpStateCookieChunkParameter) r10     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpStateCookieChunkParameter r10 = (fm.liveswitch.SctpStateCookieChunkParameter) r10     // Catch:{ Exception -> 0x0229 }
            r11 = r19
            r11.setStateCookieChunk(r10)     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x006c:
            r11 = r19
            int r10 = r9.getType()     // Catch:{ Exception -> 0x0229 }
            r12 = 5
            if (r10 != r12) goto L_0x00b4
            if (r8 == 0) goto L_0x00b4
            fm.liveswitch.SctpIPv4ChunkParameter[] r10 = new fm.liveswitch.SctpIPv4ChunkParameter[r7]     // Catch:{ Exception -> 0x0229 }
            r1.setValue(r3)     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpIPv4ChunkParameter[] r12 = r11.getIPv4Addresses()     // Catch:{ Exception -> 0x0229 }
            if (r12 == 0) goto L_0x00a3
            fm.liveswitch.SctpIPv4ChunkParameter[] r10 = r11.getIPv4Addresses()     // Catch:{ Exception -> 0x0229 }
            int r10 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r10)     // Catch:{ Exception -> 0x0229 }
            int r10 = r10 + r7
            fm.liveswitch.SctpIPv4ChunkParameter[] r10 = new fm.liveswitch.SctpIPv4ChunkParameter[r10]     // Catch:{ Exception -> 0x0229 }
            r12 = 0
        L_0x008e:
            fm.liveswitch.SctpIPv4ChunkParameter[] r13 = r11.getIPv4Addresses()     // Catch:{ Exception -> 0x0229 }
            int r13 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r13)     // Catch:{ Exception -> 0x0229 }
            if (r12 >= r13) goto L_0x00a3
            fm.liveswitch.SctpIPv4ChunkParameter[] r13 = r11.getIPv4Addresses()     // Catch:{ Exception -> 0x0229 }
            r13 = r13[r12]     // Catch:{ Exception -> 0x0229 }
            r10[r12] = r13     // Catch:{ Exception -> 0x0229 }
            int r12 = r12 + 1
            goto L_0x008e
        L_0x00a3:
            int r12 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r10)     // Catch:{ Exception -> 0x0229 }
            int r12 = r12 - r7
            r13 = r9
            fm.liveswitch.SctpIPv4ChunkParameter r13 = (fm.liveswitch.SctpIPv4ChunkParameter) r13     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpIPv4ChunkParameter r13 = (fm.liveswitch.SctpIPv4ChunkParameter) r13     // Catch:{ Exception -> 0x0229 }
            r10[r12] = r13     // Catch:{ Exception -> 0x0229 }
            r11.setIPv4Addresses(r10)     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x00b4:
            int r10 = r9.getType()     // Catch:{ Exception -> 0x0229 }
            if (r10 != r5) goto L_0x00c6
            if (r8 == 0) goto L_0x00c6
            r10 = r9
            fm.liveswitch.SctpUnrecognizedParameterChunkParameter r10 = (fm.liveswitch.SctpUnrecognizedParameterChunkParameter) r10     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpUnrecognizedParameterChunkParameter r10 = (fm.liveswitch.SctpUnrecognizedParameterChunkParameter) r10     // Catch:{ Exception -> 0x0229 }
            r11.setUnrecognizedParameter(r10)     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x00c6:
            int r10 = r9.getType()     // Catch:{ Exception -> 0x0229 }
            r12 = 11
            if (r10 != r12) goto L_0x00da
            if (r8 == 0) goto L_0x00da
            r10 = r9
            fm.liveswitch.SctpHostNameAddressChunkParameter r10 = (fm.liveswitch.SctpHostNameAddressChunkParameter) r10     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpHostNameAddressChunkParameter r10 = (fm.liveswitch.SctpHostNameAddressChunkParameter) r10     // Catch:{ Exception -> 0x0229 }
            r11.setHostNameAddress(r10)     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x00da:
            int r10 = r9.getType()     // Catch:{ Exception -> 0x0229 }
            r12 = 49152(0xc000, float:6.8877E-41)
            if (r10 != r12) goto L_0x00fe
            if (r8 == 0) goto L_0x00fe
            fm.liveswitch.SctpPartialReliabilitySupportParameters r10 = r11.getPartialReliabilityParameters()     // Catch:{ Exception -> 0x0229 }
            if (r10 != 0) goto L_0x00f5
            fm.liveswitch.SctpPartialReliabilitySupportParameters r10 = new fm.liveswitch.SctpPartialReliabilitySupportParameters     // Catch:{ Exception -> 0x0229 }
            r10.<init>(r7)     // Catch:{ Exception -> 0x0229 }
            r11.setPartialReliabilityParameters(r10)     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x00f5:
            fm.liveswitch.SctpPartialReliabilitySupportParameters r10 = r11.getPartialReliabilityParameters()     // Catch:{ Exception -> 0x0229 }
            r10.setPartialReliabilitySupportedByThisEndpoint(r7)     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x00fe:
            int r10 = r9.getType()     // Catch:{ Exception -> 0x0229 }
            r12 = 32776(0x8008, float:4.5929E-41)
            if (r10 != r12) goto L_0x011a
            if (r8 == 0) goto L_0x011a
            r10 = r9
            fm.liveswitch.SctpSupportedExtensionsChunkParameter r10 = (fm.liveswitch.SctpSupportedExtensionsChunkParameter) r10     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpSupportedExtensionsChunkParameter r10 = (fm.liveswitch.SctpSupportedExtensionsChunkParameter) r10     // Catch:{ Exception -> 0x0229 }
            byte[] r10 = r10.getSupportedChunkExtensions()     // Catch:{ Exception -> 0x0229 }
            r11.setSupportedExtensionsChunks(r10)     // Catch:{ Exception -> 0x0229 }
            r11.updateSupportedFunctionalityFromTheListOfSupportedExtensions()     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x011a:
            int r10 = r9.getType()     // Catch:{ Exception -> 0x0229 }
            r12 = 32770(0x8002, float:4.592E-41)
            if (r10 != r12) goto L_0x0157
            if (r8 == 0) goto L_0x0157
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = r11.getAuthenticatedChunksParameters()     // Catch:{ Exception -> 0x0229 }
            if (r10 != 0) goto L_0x013e
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = new fm.liveswitch.SctpAuthenticatedChunksParameters     // Catch:{ Exception -> 0x0229 }
            r12 = r9
            fm.liveswitch.SctpRandomChunkParameter r12 = (fm.liveswitch.SctpRandomChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpRandomChunkParameter r12 = (fm.liveswitch.SctpRandomChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            byte[] r12 = r12.getRandomNumber()     // Catch:{ Exception -> 0x0229 }
            r10.<init>(r7, r12, r2, r2)     // Catch:{ Exception -> 0x0229 }
            r11.setAuthenticatedChunksParameters(r10)     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x013e:
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = r11.getAuthenticatedChunksParameters()     // Catch:{ Exception -> 0x0229 }
            r10.setAuthenticatedChunksSupportedByThisEndpoint(r7)     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = r11.getAuthenticatedChunksParameters()     // Catch:{ Exception -> 0x0229 }
            r12 = r9
            fm.liveswitch.SctpRandomChunkParameter r12 = (fm.liveswitch.SctpRandomChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpRandomChunkParameter r12 = (fm.liveswitch.SctpRandomChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            byte[] r12 = r12.getRandomNumber()     // Catch:{ Exception -> 0x0229 }
            r10.setRandom(r12)     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x0157:
            int r10 = r9.getType()     // Catch:{ Exception -> 0x0229 }
            r12 = 32772(0x8004, float:4.5923E-41)
            if (r10 != r12) goto L_0x0192
            if (r8 == 0) goto L_0x0192
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = r11.getAuthenticatedChunksParameters()     // Catch:{ Exception -> 0x0229 }
            if (r10 != 0) goto L_0x017a
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = new fm.liveswitch.SctpAuthenticatedChunksParameters     // Catch:{ Exception -> 0x0229 }
            r12 = r9
            fm.liveswitch.SctpRequestedHmacAlgorithmChunkParameter r12 = (fm.liveswitch.SctpRequestedHmacAlgorithmChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpRequestedHmacAlgorithmChunkParameter r12 = (fm.liveswitch.SctpRequestedHmacAlgorithmChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            int[] r12 = r12.getHmacIdentifiers()     // Catch:{ Exception -> 0x0229 }
            r10.<init>(r7, r2, r12, r2)     // Catch:{ Exception -> 0x0229 }
            r11.setAuthenticatedChunksParameters(r10)     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x017a:
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = r11.getAuthenticatedChunksParameters()     // Catch:{ Exception -> 0x0229 }
            r10.setAuthenticatedChunksSupportedByThisEndpoint(r7)     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = r11.getAuthenticatedChunksParameters()     // Catch:{ Exception -> 0x0229 }
            r12 = r9
            fm.liveswitch.SctpRequestedHmacAlgorithmChunkParameter r12 = (fm.liveswitch.SctpRequestedHmacAlgorithmChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpRequestedHmacAlgorithmChunkParameter r12 = (fm.liveswitch.SctpRequestedHmacAlgorithmChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            int[] r12 = r12.getHmacIdentifiers()     // Catch:{ Exception -> 0x0229 }
            r10.setHmacIdentifiers(r12)     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x0192:
            int r10 = r9.getType()     // Catch:{ Exception -> 0x0229 }
            r12 = 32771(0x8003, float:4.5922E-41)
            if (r10 != r12) goto L_0x01de
            if (r8 == 0) goto L_0x01de
            r10 = r9
            fm.liveswitch.SctpChunkListChunkParameter r10 = (fm.liveswitch.SctpChunkListChunkParameter) r10     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpChunkListChunkParameter r10 = (fm.liveswitch.SctpChunkListChunkParameter) r10     // Catch:{ Exception -> 0x0229 }
            byte[] r10 = r10.getChunkList()     // Catch:{ Exception -> 0x0229 }
            int r10 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r10)     // Catch:{ Exception -> 0x0229 }
            if (r10 <= 0) goto L_0x01db
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = r11.getAuthenticatedChunksParameters()     // Catch:{ Exception -> 0x0229 }
            if (r10 != 0) goto L_0x01c4
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = new fm.liveswitch.SctpAuthenticatedChunksParameters     // Catch:{ Exception -> 0x0229 }
            r12 = r9
            fm.liveswitch.SctpChunkListChunkParameter r12 = (fm.liveswitch.SctpChunkListChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpChunkListChunkParameter r12 = (fm.liveswitch.SctpChunkListChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            byte[] r12 = r12.getChunkList()     // Catch:{ Exception -> 0x0229 }
            r10.<init>(r7, r2, r2, r12)     // Catch:{ Exception -> 0x0229 }
            r11.setAuthenticatedChunksParameters(r10)     // Catch:{ Exception -> 0x0229 }
            goto L_0x01db
        L_0x01c4:
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = r11.getAuthenticatedChunksParameters()     // Catch:{ Exception -> 0x0229 }
            r10.setAuthenticatedChunksSupportedByThisEndpoint(r7)     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpAuthenticatedChunksParameters r10 = r11.getAuthenticatedChunksParameters()     // Catch:{ Exception -> 0x0229 }
            r12 = r9
            fm.liveswitch.SctpChunkListChunkParameter r12 = (fm.liveswitch.SctpChunkListChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpChunkListChunkParameter r12 = (fm.liveswitch.SctpChunkListChunkParameter) r12     // Catch:{ Exception -> 0x0229 }
            byte[] r12 = r12.getChunkList()     // Catch:{ Exception -> 0x0229 }
            r10.setChunksToBeAuthenticated(r12)     // Catch:{ Exception -> 0x0229 }
        L_0x01db:
            r12 = r20
            goto L_0x01f4
        L_0x01de:
            boolean r10 = r9.getDoNotProcessFurtherParametersIfItIsNotRecognized()     // Catch:{ Exception -> 0x0229 }
            if (r10 == 0) goto L_0x01e5
            r8 = 0
        L_0x01e5:
            boolean r10 = r9.getReportToSenderIfItIsNotRecognized()     // Catch:{ Exception -> 0x0229 }
            if (r10 == 0) goto L_0x01db
            fm.liveswitch.SctpGenericChunkParameter r10 = fm.liveswitch.SctpGenericChunkParameter.toGenericParameter(r9)     // Catch:{ Exception -> 0x0229 }
            r12 = r20
            r12.add(r10)     // Catch:{ Exception -> 0x0229 }
        L_0x01f4:
            int r10 = r9.getType()     // Catch:{ Exception -> 0x0229 }
            boolean r10 = fm.liveswitch.SctpTlvParameter.isInManualListOfUnrecognizedChunkParameterThatMustBeReported(r10)     // Catch:{ Exception -> 0x0229 }
            if (r10 == 0) goto L_0x0205
            fm.liveswitch.SctpGenericChunkParameter r9 = fm.liveswitch.SctpGenericChunkParameter.toGenericParameter(r9)     // Catch:{ Exception -> 0x0229 }
            r12.add(r9)     // Catch:{ Exception -> 0x0229 }
        L_0x0205:
            int r9 = r22.getValue()     // Catch:{ Exception -> 0x0229 }
            int r6 = r6 + r9
            r19 = r11
            r20 = r12
            goto L_0x004b
        L_0x0210:
            r11 = r19
            r12 = r20
            int r0 = fm.liveswitch.ArrayListExtensions.getCount(r12)     // Catch:{ Exception -> 0x0229 }
            if (r0 <= 0) goto L_0x0225
            fm.liveswitch.SctpGenericChunkParameter[] r0 = new fm.liveswitch.SctpGenericChunkParameter[r3]     // Catch:{ Exception -> 0x0229 }
            java.lang.Object[] r0 = r12.toArray(r0)     // Catch:{ Exception -> 0x0229 }
            fm.liveswitch.SctpGenericChunkParameter[] r0 = (fm.liveswitch.SctpGenericChunkParameter[]) r0     // Catch:{ Exception -> 0x0229 }
            r11.setUnrecognizedParametersThatNeedToBeReportedBackToSender(r0)     // Catch:{ Exception -> 0x0229 }
        L_0x0225:
            r1.setValue(r6)     // Catch:{ Exception -> 0x0229 }
            return r11
        L_0x0229:
            r1.setValue(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SctpInitAckChunk.parseBytes(byte[], fm.liveswitch.IntegerHolder):fm.liveswitch.SctpInitAckChunk");
    }

    public SctpInitAckChunk(long j, long j2, int i, int i2, long j3, SctpStateCookieChunkParameter sctpStateCookieChunkParameter, SctpHostNameAddressChunkParameter sctpHostNameAddressChunkParameter, SctpUnrecognizedParameterChunkParameter sctpUnrecognizedParameterChunkParameter) {
        super.setCanBundleWithDataAndSackChunks(false);
        super.setType(SctpChunkType.getInitiationAck());
        setInitiateTag(j);
        setAdvertisedReceiverWindowCredit(j2);
        setNumberOfOutboundStreams(i);
        setNumberOfInboundStreams(i2);
        setInitialTsn(j3);
        setStateCookieChunk(sctpStateCookieChunkParameter);
        setHostNameAddress(sctpHostNameAddressChunkParameter);
        setUnrecognizedParameter(sctpUnrecognizedParameterChunkParameter);
        super.setUnrecognized(false);
    }

    public void setAdvertisedReceiverWindowCredit(long j) {
        this._advertisedReceiverWindowCredit = j;
    }

    public void setAuthenticatedChunksParameters(SctpAuthenticatedChunksParameters sctpAuthenticatedChunksParameters) {
        this._authenticatedChunksParameters = sctpAuthenticatedChunksParameters;
    }

    public void setDynamicAddressReconfigurationParameters(SctpDynamicAddressReconfigurationSupportParameters sctpDynamicAddressReconfigurationSupportParameters) {
        this._dynamicAddressReconfigurationParameters = sctpDynamicAddressReconfigurationSupportParameters;
    }

    public void setHostNameAddress(SctpHostNameAddressChunkParameter sctpHostNameAddressChunkParameter) {
        this._hostNameAddress = sctpHostNameAddressChunkParameter;
    }

    public void setInitialTsn(long j) {
        this._initialTsn = j;
    }

    public void setInitiateTag(long j) {
        this._initiateTag = j;
    }

    public void setIPv4Addresses(SctpIPv4ChunkParameter[] sctpIPv4ChunkParameterArr) {
        this._iPv4Addresses = sctpIPv4ChunkParameterArr;
    }

    public void setNumberOfInboundStreams(int i) {
        this._numberOfInboundStreams = i;
    }

    public void setNumberOfOutboundStreams(int i) {
        this._numberOfOutboundStreams = i;
    }

    public void setPartialReliabilityParameters(SctpPartialReliabilitySupportParameters sctpPartialReliabilitySupportParameters) {
        this._partialReliabilityParameters = sctpPartialReliabilitySupportParameters;
    }

    public void setStateCookieChunk(SctpStateCookieChunkParameter sctpStateCookieChunkParameter) {
        this._stateCookieChunk = sctpStateCookieChunkParameter;
    }

    private void setSupportedExtensionsChunks(byte[] bArr) {
        this._supportedExtensionsChunks = bArr;
    }

    public void setUnrecognizedParameter(SctpUnrecognizedParameterChunkParameter sctpUnrecognizedParameterChunkParameter) {
        this._unrecognizedParameter = sctpUnrecognizedParameterChunkParameter;
    }

    public void setUnrecognizedParametersThatNeedToBeReportedBackToSender(SctpGenericChunkParameter[] sctpGenericChunkParameterArr) {
        this._unrecognizedParametersThatNeedToBeReportedBackToSender = sctpGenericChunkParameterArr;
    }

    private void updateSupportedFunctionalityFromTheListOfSupportedExtensions() {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i = 0; i < ArrayExtensions.getLength(getSupportedExtensionsChunks()); i++) {
            if (getSupportedExtensionsChunks()[i] == SctpChunkType.getForwardCumulativeTSN()) {
                if (getPartialReliabilityParameters() == null) {
                    setPartialReliabilityParameters(new SctpPartialReliabilitySupportParameters(true));
                } else {
                    getPartialReliabilityParameters().setPartialReliabilitySupportedByThisEndpoint(true);
                }
            } else if (getSupportedExtensionsChunks()[i] == SctpChunkType.getReConfig()) {
                if (getDynamicAddressReconfigurationParameters() == null) {
                    setDynamicAddressReconfigurationParameters(new SctpDynamicAddressReconfigurationSupportParameters(true));
                } else {
                    getDynamicAddressReconfigurationParameters().setDynamicAddressReconfigurationSupportedByThisEndpoint(true);
                }
            } else if (getSupportedExtensionsChunks()[i] == SctpChunkType.getAsConf()) {
                z = true;
            } else if (getSupportedExtensionsChunks()[i] == SctpChunkType.getAsConfAck()) {
                z2 = true;
            } else if (getSupportedExtensionsChunks()[i] == SctpChunkType.getAuth()) {
                z3 = true;
            } else {
                Log.debug(StringExtensions.format("Remote party declared ability to send an unknow optional SCTP chunk: {0}", (Object) ByteExtensions.toString(Byte.valueOf(getSupportedExtensionsChunks()[i]))));
            }
        }
        if (z && z2 && z3) {
            if (getAuthenticatedChunksParameters() == null) {
                setAuthenticatedChunksParameters(new SctpAuthenticatedChunksParameters(true, (byte[]) null, (int[]) null, (byte[]) null));
            } else {
                getAuthenticatedChunksParameters().setAuthenticatedChunksSupportedByThisEndpoint(true);
            }
        }
    }
}
