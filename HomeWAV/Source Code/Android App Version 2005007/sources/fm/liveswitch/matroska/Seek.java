package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.StringExtensions;

public class Seek extends Element {
    private static byte[] _seekIdId = {83, -85};
    private static byte[] _seekPositionId = {83, -84};
    private long _seekId;
    private long _seekPosition;

    public static byte[] getEbmlId() {
        return new byte[]{77, -69};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        super.writeUnsignedInteger(getSeekId(), _seekIdId, byteOutputStream);
        super.writeUnsignedInteger(getSeekPosition(), _seekPositionId, byteOutputStream);
        return byteOutputStream.toArray();
    }

    public long getSeekId() {
        return this._seekId;
    }

    public long getSeekPosition() {
        return this._seekPosition;
    }

    public Seek() {
    }

    public Seek(byte[] bArr) {
        this();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, _seekIdId)) {
                setSeekId(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _seekPositionId)) {
                setSeekPosition(Element.readUnsignedInteger(readValue));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaSeek: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
    }

    public void setSeekId(long j) {
        this._seekId = j;
    }

    public void setSeekPosition(long j) {
        this._seekPosition = j;
    }
}
