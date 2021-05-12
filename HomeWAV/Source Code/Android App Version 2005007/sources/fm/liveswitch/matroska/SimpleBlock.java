package fm.liveswitch.matroska;

public class SimpleBlock extends Block {
    public static byte[] getEbmlId() {
        return new byte[]{-93};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    public SimpleBlock() {
    }

    public SimpleBlock(byte[] bArr) {
        super(bArr);
    }
}
