package fm.liveswitch.matroska;

public class Attachments extends Element {
    public static byte[] getEbmlId() {
        return new byte[]{25, 65, -92, 105};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        return new byte[super.getLength()];
    }
}
