package fm.liveswitch.matroska;

public class Tags extends Element {
    public static byte[] getEbmlId() {
        return new byte[]{18, 84, -61, 103};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        return new byte[super.getLength()];
    }
}
