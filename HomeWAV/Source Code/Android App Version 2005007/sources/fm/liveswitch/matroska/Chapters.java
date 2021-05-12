package fm.liveswitch.matroska;

public class Chapters extends Element {
    public static byte[] getEbmlId() {
        return new byte[]{Tnaf.POW_2_WIDTH, 67, -89, 112};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        return new byte[super.getLength()];
    }
}
