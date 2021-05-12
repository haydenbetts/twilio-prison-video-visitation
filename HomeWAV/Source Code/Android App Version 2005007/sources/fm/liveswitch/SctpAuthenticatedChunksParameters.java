package fm.liveswitch;

class SctpAuthenticatedChunksParameters {
    private boolean _authenticatedChunksSupportedByThisEndpoint;
    private byte[] _chunksToBeAuthenticated;
    private int[] _hmacIdentifiers;
    private byte[] _random;
    private boolean _sctpAuthenticatedChunksUsedInThisAssociation;

    public boolean getAuthenticatedChunksSupportedByThisEndpoint() {
        return this._authenticatedChunksSupportedByThisEndpoint;
    }

    public byte[] getChunksToBeAuthenticated() {
        return this._chunksToBeAuthenticated;
    }

    public int[] getHmacIdentifiers() {
        return this._hmacIdentifiers;
    }

    public byte[] getRandom() {
        return this._random;
    }

    public boolean getSCTPAuthenticatedChunksUsedInThisAssociation() {
        return this._sctpAuthenticatedChunksUsedInThisAssociation;
    }

    public SctpAuthenticatedChunksParameters(boolean z, byte[] bArr, int[] iArr, byte[] bArr2) {
        setAuthenticatedChunksSupportedByThisEndpoint(z);
        setRandom(bArr);
        setHmacIdentifiers(iArr);
        setChunksToBeAuthenticated(bArr2);
    }

    /* access modifiers changed from: package-private */
    public void setAuthenticatedChunksSupportedByThisEndpoint(boolean z) {
        this._authenticatedChunksSupportedByThisEndpoint = z;
    }

    /* access modifiers changed from: package-private */
    public void setChunksToBeAuthenticated(byte[] bArr) {
        this._chunksToBeAuthenticated = bArr;
    }

    /* access modifiers changed from: package-private */
    public void setHmacIdentifiers(int[] iArr) {
        this._hmacIdentifiers = iArr;
    }

    /* access modifiers changed from: package-private */
    public void setRandom(byte[] bArr) {
        this._random = bArr;
    }

    public void setSCTPAuthenticatedChunksUsedInThisAssociation(boolean z) {
        this._sctpAuthenticatedChunksUsedInThisAssociation = z;
    }
}
