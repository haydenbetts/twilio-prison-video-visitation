package fm.liveswitch.matroska;

public class EbmlVoid extends Element {
    public static byte[] getEbmlId() {
        return new byte[]{-20};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        return new byte[super.getLength()];
    }
}
