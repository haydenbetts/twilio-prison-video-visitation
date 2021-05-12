package fm.liveswitch;

import java.util.ArrayList;

class SctpInitChunk extends SctpControlChunk {
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
    private SctpCookiePreservativeChunkParameter _suggestedCookieLifeSpanIncrement;
    private SctpSupportedAddressTypesChunkParameter _supportedAddressTypes;
    private byte[] _supportedExtensionsChunks;
    private SctpGenericChunkParameter[] _unrecognizedParametersThatNeedToBeReportedBackToSender;

    private byte[] generateAdditionalSupportedExtensions() {
        ByteCollection byteCollection = new ByteCollection();
        if (getPartialReliabilityParameters() != null && getPartialReliabilityParameters().getPartialReliabilitySupportedByThisEndpoint()) {
            byteCollection.add(SctpChunkType.getForwardCumulativeTSN());
        }
        if (getAuthenticatedChunksParameters() != null && getAuthenticatedChunksParameters().getAuthenticatedChunksSupportedByThisEndpoint()) {
            byteCollection.add(SctpChunkType.getAuth());
        }
        if (getDynamicAddressReconfigurationParameters() != null && getDynamicAddressReconfigurationParameters().getDynamicAddressReconfigurationSupportedByThisEndpoint()) {
            if (getAuthenticatedChunksParameters() == null || getAuthenticatedChunksParameters().getAuthenticatedChunksSupportedByThisEndpoint()) {
                byteCollection.add(SctpChunkType.getAsConf());
                byteCollection.add(SctpChunkType.getAsConfAck());
                byteCollection.add(SctpChunkType.getReConfig());
            } else {
                Log.warn("For SCTP dynamic address reconfiguration to work, SCTP authenticated chunks must be supported");
                return new byte[0];
            }
        }
        return byteCollection.toArray();
    }

    public long getAdvertisedReceiverWindowCredit() {
        return this._advertisedReceiverWindowCredit;
    }

    public SctpAuthenticatedChunksParameters getAuthenticatedChunksParameters() {
        return this._authenticatedChunksParameters;
    }

    public static byte[] getBytes(SctpInitChunk sctpInitChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpInitChunk.getType());
        byteCollection.add((byte) 0);
        byteCollection.addRange(Binary.toBytes32(sctpInitChunk.getInitiateTag(), false));
        byteCollection.addRange(Binary.toBytes32(sctpInitChunk.getAdvertisedReceiverWindowCredit(), false));
        byteCollection.addRange(Binary.toBytes16(sctpInitChunk.getNumberOfOutboundStreams(), false));
        byteCollection.addRange(Binary.toBytes16(sctpInitChunk.getNumberOfInboundStreams(), false));
        byteCollection.addRange(Binary.toBytes32(sctpInitChunk.getInitialTsn(), false));
        if (sctpInitChunk.getIPv4Addresses() != null) {
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) sctpInitChunk.getIPv4Addresses()); i++) {
                byteCollection.addRange(sctpInitChunk.getIPv4Addresses()[i].getBytes());
            }
        }
        if (sctpInitChunk.getSuggestedCookieLifeSpanIncrement() != null) {
            byteCollection.addRange(sctpInitChunk.getSuggestedCookieLifeSpanIncrement().getBytes());
        }
        if (sctpInitChunk.getHostNameAddress() != null) {
            byteCollection.addRange(sctpInitChunk.getHostNameAddress().getBytes());
        }
        if (sctpInitChunk.getSupportedAddressTypes() != null) {
            byteCollection.addRange(sctpInitChunk.getSupportedAddressTypes().getBytes());
        }
        if (sctpInitChunk.getPartialReliabilityParameters() != null && sctpInitChunk.getPartialReliabilityParameters().getPartialReliabilitySupportedByThisEndpoint()) {
            byteCollection.addRange(new SctpForwardTsnSupportedChunkParameter().getBytes());
        }
        byte[] generateAdditionalSupportedExtensions = sctpInitChunk.generateAdditionalSupportedExtensions();
        if (ArrayExtensions.getLength(generateAdditionalSupportedExtensions) > 0) {
            byteCollection.addRange(new SctpSupportedExtensionsChunkParameter(generateAdditionalSupportedExtensions).getBytes());
        }
        if (sctpInitChunk.getAuthenticatedChunksParameters() != null && sctpInitChunk.getAuthenticatedChunksParameters().getAuthenticatedChunksSupportedByThisEndpoint() && sctpInitChunk.getAuthenticatedChunksParameters().getHmacIdentifiers() != null && ArrayExtensions.getLength(sctpInitChunk.getAuthenticatedChunksParameters().getHmacIdentifiers()) > 0 && sctpInitChunk.getAuthenticatedChunksParameters().getChunksToBeAuthenticated() != null && ArrayExtensions.getLength(sctpInitChunk.getAuthenticatedChunksParameters().getChunksToBeAuthenticated()) > 0) {
            byteCollection.addRange(new SctpRandomChunkParameter(sctpInitChunk.getAuthenticatedChunksParameters().getRandom()).getBytes());
            byteCollection.addRange(new SctpRequestedHmacAlgorithmChunkParameter(sctpInitChunk.getAuthenticatedChunksParameters().getHmacIdentifiers()).getBytes());
            byteCollection.addRange(new SctpChunkListChunkParameter(sctpInitChunk.getAuthenticatedChunksParameters().getChunksToBeAuthenticated()).getBytes());
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

    public SctpCookiePreservativeChunkParameter getSuggestedCookieLifeSpanIncrement() {
        return this._suggestedCookieLifeSpanIncrement;
    }

    public SctpSupportedAddressTypesChunkParameter getSupportedAddressTypes() {
        return this._supportedAddressTypes;
    }

    public byte[] getSupportedExtensionsChunks() {
        return this._supportedExtensionsChunks;
    }

    public SctpGenericChunkParameter[] getUnrecognizedParametersThatNeedToBeReportedBackToSender() {
        return this._unrecognizedParametersThatNeedToBeReportedBackToSender;
    }

    public static SctpInitChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        SctpInitChunk sctpInitChunk;
        SctpInitChunk sctpInitChunk2;
        SctpIPv4ChunkParameter[] sctpIPv4ChunkParameterArr;
        byte[] bArr2 = bArr;
        IntegerHolder integerHolder2 = integerHolder;
        try {
            Log.debug("SCTP manager received INIT chunk from the other party");
            int fromBytes16 = Binary.fromBytes16(bArr2, 2, false);
            long fromBytes32 = Binary.fromBytes32(bArr2, 4, false);
            long fromBytes322 = Binary.fromBytes32(bArr2, 8, false);
            int fromBytes162 = Binary.fromBytes16(bArr2, 12, false);
            int fromBytes163 = Binary.fromBytes16(bArr2, 14, false);
            long fromBytes323 = Binary.fromBytes32(bArr2, 16, false);
            ArrayList arrayList = new ArrayList();
            new SctpInitChunk(fromBytes32, fromBytes322, fromBytes162, fromBytes163, fromBytes323);
            int i = 20;
            boolean z = true;
            while (i < fromBytes16) {
                SctpTlvParameter parseBytes = SctpTlvParameter.parseBytes(bArr2, i, integerHolder2);
                if (parseBytes == null) {
                    integerHolder2.setValue(0);
                    return null;
                }
                if (parseBytes.getType() == 32773) {
                    sctpInitChunk2 = sctpInitChunk;
                } else if (parseBytes.getType() != 5 || !z) {
                    sctpInitChunk2 = sctpInitChunk;
                    if (parseBytes.getType() == 9 && z) {
                        sctpInitChunk2.setSuggestedCookieLifeSpanIncrement((SctpCookiePreservativeChunkParameter) parseBytes);
                    } else if (parseBytes.getType() == 11 && z) {
                        sctpInitChunk2.setHostNameAddress((SctpHostNameAddressChunkParameter) parseBytes);
                    } else if (parseBytes.getType() == 12 && z) {
                        sctpInitChunk2.setSupportedAddressTypes((SctpSupportedAddressTypesChunkParameter) parseBytes);
                    } else if (parseBytes.getType() != 49152 || !z) {
                        if (parseBytes.getType() == 32776 && z) {
                            sctpInitChunk2.setSupportedExtensionsChunks(((SctpSupportedExtensionsChunkParameter) parseBytes).getSupportedChunkExtensions());
                            sctpInitChunk2.updateSupportedFunctionalityFromTheListOfSupportedExtensions();
                        } else if (parseBytes.getType() != 32770 || !z) {
                            if (parseBytes.getType() != 32772 || !z) {
                                if (parseBytes.getType() != 32771 || !z) {
                                    if (parseBytes.getDoNotProcessFurtherParametersIfItIsNotRecognized()) {
                                        z = false;
                                    }
                                    if (parseBytes.getReportToSenderIfItIsNotRecognized()) {
                                        arrayList.add(SctpGenericChunkParameter.toGenericParameter(parseBytes));
                                    }
                                } else if (ArrayExtensions.getLength(((SctpChunkListChunkParameter) parseBytes).getChunkList()) > 0) {
                                    if (sctpInitChunk2.getAuthenticatedChunksParameters() == null) {
                                        sctpInitChunk2.setAuthenticatedChunksParameters(new SctpAuthenticatedChunksParameters(true, (byte[]) null, (int[]) null, ((SctpChunkListChunkParameter) parseBytes).getChunkList()));
                                    } else {
                                        sctpInitChunk2.getAuthenticatedChunksParameters().setAuthenticatedChunksSupportedByThisEndpoint(true);
                                        sctpInitChunk2.getAuthenticatedChunksParameters().setChunksToBeAuthenticated(((SctpChunkListChunkParameter) parseBytes).getChunkList());
                                    }
                                }
                            } else if (sctpInitChunk2.getAuthenticatedChunksParameters() == null) {
                                sctpInitChunk2.setAuthenticatedChunksParameters(new SctpAuthenticatedChunksParameters(true, (byte[]) null, ((SctpRequestedHmacAlgorithmChunkParameter) parseBytes).getHmacIdentifiers(), (byte[]) null));
                            } else {
                                sctpInitChunk2.getAuthenticatedChunksParameters().setAuthenticatedChunksSupportedByThisEndpoint(true);
                                sctpInitChunk2.getAuthenticatedChunksParameters().setHmacIdentifiers(((SctpRequestedHmacAlgorithmChunkParameter) parseBytes).getHmacIdentifiers());
                            }
                        } else if (sctpInitChunk2.getAuthenticatedChunksParameters() == null) {
                            sctpInitChunk2.setAuthenticatedChunksParameters(new SctpAuthenticatedChunksParameters(true, ((SctpRandomChunkParameter) parseBytes).getRandomNumber(), (int[]) null, (byte[]) null));
                        } else {
                            sctpInitChunk2.getAuthenticatedChunksParameters().setAuthenticatedChunksSupportedByThisEndpoint(true);
                            sctpInitChunk2.getAuthenticatedChunksParameters().setRandom(((SctpRandomChunkParameter) parseBytes).getRandomNumber());
                        }
                    } else if (sctpInitChunk2.getPartialReliabilityParameters() == null) {
                        sctpInitChunk2.setPartialReliabilityParameters(new SctpPartialReliabilitySupportParameters(true));
                    } else {
                        sctpInitChunk2.getPartialReliabilityParameters().setPartialReliabilitySupportedByThisEndpoint(true);
                    }
                } else {
                    if (sctpInitChunk.getIPv4Addresses() != null) {
                        sctpIPv4ChunkParameterArr = new SctpIPv4ChunkParameter[(ArrayExtensions.getLength((Object[]) sctpInitChunk.getIPv4Addresses()) + 1)];
                        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) sctpInitChunk.getIPv4Addresses()); i2++) {
                            sctpIPv4ChunkParameterArr[i2] = sctpInitChunk.getIPv4Addresses()[i2];
                        }
                    } else {
                        sctpIPv4ChunkParameterArr = new SctpIPv4ChunkParameter[1];
                    }
                    sctpIPv4ChunkParameterArr[ArrayExtensions.getLength((Object[]) sctpIPv4ChunkParameterArr) - 1] = (SctpIPv4ChunkParameter) parseBytes;
                    sctpInitChunk2 = sctpInitChunk;
                    sctpInitChunk2.setIPv4Addresses(sctpIPv4ChunkParameterArr);
                }
                if (SctpTlvParameter.isInManualListOfUnrecognizedChunkParameterThatMustBeReported(parseBytes.getType())) {
                    arrayList.add(SctpGenericChunkParameter.toGenericParameter(parseBytes));
                }
                i += integerHolder.getValue();
                sctpInitChunk = sctpInitChunk2;
            }
            SctpInitChunk sctpInitChunk3 = sctpInitChunk;
            if (ArrayListExtensions.getCount(arrayList) > 0) {
                sctpInitChunk3.setUnrecognizedParametersThatNeedToBeReportedBackToSender((SctpGenericChunkParameter[]) arrayList.toArray(new SctpGenericChunkParameter[0]));
            }
            integerHolder2.setValue(i);
            return sctpInitChunk3;
        } catch (Exception unused) {
            Log.error("Failed to process an incoming SCTP INIT chunk");
            integerHolder2.setValue(0);
            return null;
        }
    }

    public SctpInitChunk(long j, long j2, int i, int i2, long j3) {
        super.setCanBundleWithDataAndSackChunks(false);
        super.setType(SctpChunkType.getInitiation());
        setInitiateTag(j);
        setAdvertisedReceiverWindowCredit(j2);
        setNumberOfOutboundStreams(i);
        setNumberOfInboundStreams(i2);
        setInitialTsn(j3);
        super.setUnrecognized(false);
    }

    public SctpInitChunk(long j, long j2, int i, int i2, long j3, SctpPartialReliabilitySupportParameters sctpPartialReliabilitySupportParameters, SctpAuthenticatedChunksParameters sctpAuthenticatedChunksParameters, SctpDynamicAddressReconfigurationSupportParameters sctpDynamicAddressReconfigurationSupportParameters, SctpCookiePreservativeChunkParameter sctpCookiePreservativeChunkParameter, SctpHostNameAddressChunkParameter sctpHostNameAddressChunkParameter) {
        this(j, j2, i, i2, j3);
        setSuggestedCookieLifeSpanIncrement(sctpCookiePreservativeChunkParameter);
        setHostNameAddress(sctpHostNameAddressChunkParameter);
        setPartialReliabilityParameters(sctpPartialReliabilitySupportParameters);
        setAuthenticatedChunksParameters(sctpAuthenticatedChunksParameters);
        setDynamicAddressReconfigurationParameters(sctpDynamicAddressReconfigurationSupportParameters);
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

    public void setSuggestedCookieLifeSpanIncrement(SctpCookiePreservativeChunkParameter sctpCookiePreservativeChunkParameter) {
        this._suggestedCookieLifeSpanIncrement = sctpCookiePreservativeChunkParameter;
    }

    public void setSupportedAddressTypes(SctpSupportedAddressTypesChunkParameter sctpSupportedAddressTypesChunkParameter) {
        this._supportedAddressTypes = sctpSupportedAddressTypesChunkParameter;
    }

    private void setSupportedExtensionsChunks(byte[] bArr) {
        this._supportedExtensionsChunks = bArr;
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
