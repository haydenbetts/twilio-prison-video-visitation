package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.StringExtensions;

public class ContentCompression extends Element {
    private static byte[] _contentCompAlgoId = {66, 84};
    private static byte[] _contentCompSettingsId = {66, 85};
    private long _contentCompAlgo;
    private byte[] _contentCompSettings;

    public static long getDefaultContentCompAlgo() {
        return 0;
    }

    public ContentCompression() {
    }

    public ContentCompression(byte[] bArr) {
        this();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, _contentCompAlgoId)) {
                setContentCompAlgo(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _contentCompSettingsId)) {
                setContentCompSettings(readValue);
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaContentCompression: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
    }

    public long getContentCompAlgo() {
        return this._contentCompAlgo;
    }

    public byte[] getContentCompSettings() {
        return this._contentCompSettings;
    }

    public static byte[] getEbmlId() {
        return new byte[]{80, 52};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        if (super.getWriteDefaultValues() || getContentCompAlgo() != getDefaultContentCompAlgo()) {
            super.writeUnsignedInteger(getContentCompAlgo(), _contentCompAlgoId, byteOutputStream);
        }
        if (getContentCompSettings() != null) {
            super.write(getContentCompSettings(), _contentCompSettingsId, byteOutputStream);
        }
        return byteOutputStream.toArray();
    }

    public void setContentCompAlgo(long j) {
        this._contentCompAlgo = j;
    }

    public void setContentCompSettings(byte[] bArr) {
        this._contentCompSettings = bArr;
    }
}
