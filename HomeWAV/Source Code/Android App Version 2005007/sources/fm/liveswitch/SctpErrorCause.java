package fm.liveswitch;

abstract class SctpErrorCause {
    private int _causeCode;

    public abstract byte[] getBytes();

    public int getCauseCode() {
        return this._causeCode;
    }

    public static SctpErrorCause parseBytes(byte[] bArr, int i, IntegerHolder integerHolder) {
        try {
            byte[] bArr2 = new byte[(ArrayExtensions.getLength(bArr) - i)];
            BitAssistant.copy(bArr, i, bArr2, 0, ArrayExtensions.getLength(bArr2));
            int fromBytes16 = Binary.fromBytes16(bArr2, 0, false);
            if (fromBytes16 == 1) {
                return SctpInvalidStreamIdentifier.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 2) {
                return SctpMissingMandatoryParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 3) {
                return SctpStaleCookieError.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 4) {
                return SctpOutOfResource.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 5) {
                return SctpUnresolvableAddress.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 6) {
                return SctpUnrecognizedChunkType.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 7) {
                return SctpInvalidMandatoryParameter.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 8) {
                return SctpUnrecognizedParameters.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 9) {
                return SctpNoUserData.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 10) {
                return SctpCookieReceivedWhileShuttingDown.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 11) {
                return SctpRestartOfAnAssociationWithNewAddresses.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 12) {
                return SctpUserInitiatedAbort.parseBytes(bArr2, integerHolder);
            }
            if (fromBytes16 == 13) {
                return SctpProtocolViolation.parseBytes(bArr2, integerHolder);
            }
            integerHolder.setValue(0);
            return null;
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    protected SctpErrorCause() {
    }

    /* access modifiers changed from: protected */
    public void setCauseCode(int i) {
        this._causeCode = i;
    }
}
