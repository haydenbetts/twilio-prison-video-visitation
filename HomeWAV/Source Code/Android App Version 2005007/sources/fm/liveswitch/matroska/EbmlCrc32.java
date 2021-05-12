package fm.liveswitch.matroska;

public class EbmlCrc32 extends Element {
    private byte[] _data;

    public static byte[] getEbmlId() {
        return new byte[]{-65};
    }

    public byte[] getData() {
        return this._data;
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        return getData();
    }

    public void setData(byte[] bArr) {
        this._data = bArr;
    }
}
