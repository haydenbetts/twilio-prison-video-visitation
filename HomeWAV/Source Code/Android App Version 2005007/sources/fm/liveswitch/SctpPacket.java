package fm.liveswitch;

import java.util.ArrayList;

class SctpPacket {
    private static Crc32 __crc32c = new Crc32(Crc32.getCrc32cPolynomial());
    private SctpChunk[] _chunks;
    private SctpCommonHeader _header;
    private SctpChunk[] _unrecognizedChunksThatShouldBeReportedToSender;

    public static long computeCRC32cForSCTP(byte[] bArr, int i, int i2) {
        byte[] bytes32 = Binary.toBytes32(__crc32c.compute(bArr, i, i2), false);
        BitAssistant.reverse(bytes32);
        return Binary.fromBytes32(bytes32, 0, false);
    }

    private static byte[] getBytes(SctpPacket sctpPacket) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(sctpPacket.getHeader().getBytes());
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) sctpPacket.getChunks()); i++) {
            try {
                byteCollection.addRange(sctpPacket.getChunks()[i].getBytes());
            } catch (Exception unused) {
                Log.warn("SCTP could not process chunk.");
            }
        }
        byte[] array = byteCollection.toArray();
        array[8] = 0;
        array[9] = 0;
        array[10] = 0;
        array[11] = 0;
        byte[] bytes32 = Binary.toBytes32(computeCRC32cForSCTP(array, 0, ArrayExtensions.getLength(array)), false);
        array[8] = bytes32[0];
        array[9] = bytes32[1];
        array[10] = bytes32[2];
        array[11] = bytes32[3];
        return array;
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public SctpChunk[] getChunks() {
        return this._chunks;
    }

    public SctpCommonHeader getHeader() {
        return this._header;
    }

    public SctpChunk[] getUnrecognizedChunksThatShouldBeReportedToSender() {
        return this._unrecognizedChunksThatShouldBeReportedToSender;
    }

    public static SctpPacket parseBytes(byte[] bArr) {
        return parseBytes(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public static SctpPacket parseBytes(byte[] bArr, int i, int i2) {
        try {
            byte[] bArr2 = new byte[(i2 - i)];
            BitAssistant.copy(bArr, i, bArr2, 0, i2);
            IntegerHolder integerHolder = new IntegerHolder(0);
            SctpCommonHeader parseBytes = SctpCommonHeader.parseBytes(bArr2, integerHolder);
            int value = integerHolder.getValue();
            if (parseBytes != null) {
                int i3 = value + 0;
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                boolean z = true;
                while (i3 < i2 && z) {
                    IntegerHolder integerHolder2 = new IntegerHolder(value);
                    SctpChunk parseBytes2 = SctpChunk.parseBytes(bArr2, i3, integerHolder2);
                    int value2 = integerHolder2.getValue();
                    if (parseBytes2 == null) {
                        Log.error("SCTP: could not parse chunk.");
                        z = false;
                    } else {
                        i3 += value2;
                        if (parseBytes2.getUnrecognized()) {
                            if (parseBytes2.getReportToSenderIfItIsNotRecognized() && z) {
                                arrayList2.add(parseBytes2);
                            }
                            if (parseBytes2.getDoNotProcessFurtherChunksIfItIsNotRecognized()) {
                                z = false;
                            }
                        }
                        arrayList.add(parseBytes2);
                    }
                    value = value2;
                }
                if (ArrayListExtensions.getCount(arrayList) > 0) {
                    SctpPacket sctpPacket = new SctpPacket(parseBytes, (SctpChunk[]) arrayList.toArray(new SctpChunk[0]));
                    sctpPacket.setUnrecognizedChunksThatShouldBeReportedToSender((SctpChunk[]) arrayList2.toArray(new SctpChunk[0]));
                    return sctpPacket;
                }
            }
            return null;
        } catch (Exception unused) {
            Log.debug("SCTP Error: could not read SCTP packet.");
            return null;
        }
    }

    public SctpPacket(SctpCommonHeader sctpCommonHeader, SctpChunk[] sctpChunkArr) {
        setHeader(sctpCommonHeader);
        setChunks(sctpChunkArr);
    }

    public void setChunks(SctpChunk[] sctpChunkArr) {
        this._chunks = sctpChunkArr;
    }

    public void setHeader(SctpCommonHeader sctpCommonHeader) {
        this._header = sctpCommonHeader;
    }

    private void setUnrecognizedChunksThatShouldBeReportedToSender(SctpChunk[] sctpChunkArr) {
        this._unrecognizedChunksThatShouldBeReportedToSender = sctpChunkArr;
    }

    public static boolean verifyCRC32cChecksum(byte[] bArr, int i, int i2) {
        int i3 = i + 8;
        try {
            long fromBytes32 = Binary.fromBytes32(bArr, i3, false);
            bArr[i3] = 0;
            bArr[i + 9] = 0;
            bArr[i + 10] = 0;
            bArr[i + 11] = 0;
            if (computeCRC32cForSCTP(bArr, i, i2) == fromBytes32) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
