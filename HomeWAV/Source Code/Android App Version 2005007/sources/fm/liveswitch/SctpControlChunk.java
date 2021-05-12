package fm.liveswitch;

abstract class SctpControlChunk extends SctpChunk {
    private boolean _canBundleWithDataAndSackChunks;

    public boolean getCanBundleWithDataAndSackChunks() {
        return this._canBundleWithDataAndSackChunks;
    }

    protected SctpControlChunk() {
    }

    public void setCanBundleWithDataAndSackChunks(boolean z) {
        this._canBundleWithDataAndSackChunks = z;
    }
}
