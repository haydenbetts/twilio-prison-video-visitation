package fm.liveswitch.matroska;

public class Cues extends Element {
    public static byte[] getEbmlId() {
        return new byte[]{28, 83, -69, 107};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        return new byte[super.getLength()];
    }
}
