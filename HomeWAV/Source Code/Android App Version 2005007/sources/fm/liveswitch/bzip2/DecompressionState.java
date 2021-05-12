package fm.liveswitch.bzip2;

import fm.liveswitch.ArrayExtensions;

class DecompressionState {
    public int[] _cftab = new int[257];
    public int[][] _gBase = initRectangularArrayInt(Constants.getNGroups(), Constants.getMaxAlphaSize());
    public int[][] _gLimit = initRectangularArrayInt(Constants.getNGroups(), Constants.getMaxAlphaSize());
    public int[] _gMinlen = new int[Constants.getNGroups()];
    public int[][] _gPerm = initRectangularArrayInt(Constants.getNGroups(), Constants.getMaxAlphaSize());
    public byte[] _getAndMoveToFrontDecode_yy = new byte[256];
    public boolean[] _inUse = new boolean[256];
    public byte[] _ll8;
    public byte[] _recvDecodingTables_pos = new byte[Constants.getNGroups()];
    public byte[] _selector = new byte[Constants.getMaxSelectors()];
    public byte[] _selectorMtf = new byte[Constants.getMaxSelectors()];
    public byte[] _seqToUnseq = new byte[256];
    public char[][] _temp_charArray2d = initRectangularArrayChar(Constants.getNGroups(), Constants.getMaxAlphaSize());
    public int[] _tt;
    public int[] _unzftab = new int[256];

    public DecompressionState(int i) {
        this._ll8 = new byte[(i * Constants.getBlockSizeMultiple())];
    }

    static char[][] initRectangularArrayChar(int i, int i2) {
        char[][] cArr = new char[i][];
        for (int i3 = 0; i3 < i; i3++) {
            cArr[i3] = new char[i2];
        }
        return cArr;
    }

    static int[][] initRectangularArrayInt(int i, int i2) {
        int[][] iArr = new int[i][];
        for (int i3 = 0; i3 < i; i3++) {
            iArr[i3] = new int[i2];
        }
        return iArr;
    }

    public int[] initTT(int i) {
        int[] iArr = this._tt;
        if (iArr != null && ArrayExtensions.getLength(iArr) >= i) {
            return iArr;
        }
        int[] iArr2 = new int[i];
        this._tt = iArr2;
        return iArr2;
    }
}
