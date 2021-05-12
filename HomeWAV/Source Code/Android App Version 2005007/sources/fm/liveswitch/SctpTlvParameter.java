package fm.liveswitch;

import kotlin.jvm.internal.ByteCompanionObject;

abstract class SctpTlvParameter {
    private int _type;

    public static boolean isInManualListOfUnrecognizedChunkParameterThatMustBeReported(int i) {
        return i == 49152;
    }

    public abstract byte[] getBytes();

    public boolean getDoNotProcessFurtherParametersIfItIsNotRecognized() {
        return (Binary.toBytes16(getType(), false)[0] & ByteCompanionObject.MIN_VALUE) != 128;
    }

    public boolean getReportToSenderIfItIsNotRecognized() {
        return (Binary.toBytes16(getType(), false)[0] & 64) == 64;
    }

    public int getType() {
        return this._type;
    }

    public static SctpTlvParameter parseBytes(byte[] bArr, int i, IntegerHolder integerHolder) {
        try {
            byte[] bArr2 = new byte[(ArrayExtensions.getLength(bArr) - i)];
            BitAssistant.copy(bArr, i, bArr2, 0, ArrayExtensions.getLength(bArr2));
            int fromBytes16 = Binary.fromBytes16(bArr2, 0, false);
            if (fromBytes16 == 1) {
                return SctpHeartbeatInfoChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 5) {
                return SctpIPv4ChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 9) {
                return SctpCookiePreservativeChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 8) {
                return SctpUnrecognizedParameterChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 7) {
                return SctpStateCookieChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 11) {
                return SctpHostNameAddressChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 12) {
                return SctpSupportedAddressTypesChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 32773) {
                return SctpPadChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 49152) {
                return SctpForwardTsnSupportedChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 32776) {
                return SctpSupportedExtensionsChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 32770) {
                return SctpRandomChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 32772) {
                return SctpRequestedHmacAlgorithmChunkParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 32771) {
                return SctpChunkListChunkParameter.parseBytes(bArr2, integerHolder);
            }
            return SctpGenericChunkParameter.parseBytes(bArr2, integerHolder);
        } catch (Exception unused) {
            integerHolder.setValue(0);
            Log.warn("Error parsing SCTP TLV Parameter");
            return null;
        }
    }

    protected SctpTlvParameter() {
    }

    /* access modifiers changed from: protected */
    public void setType(int i) {
        this._type = i;
    }
}
