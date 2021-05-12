package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.NullableLong;
import fm.liveswitch.StringExtensions;

public class Video extends Element {
    private static byte[] _alphaModeId = {83, -64};
    private static byte[] _displayHeightId = {84, -70};
    private static byte[] _displayUnitId = {84, -78};
    private static byte[] _displayWidthId = {84, -80};
    private static byte[] _flagInterlacedId = {-102};
    private static byte[] _pixelCropBottomId = {84, -86};
    private static byte[] _pixelCropLeftId = {84, -52};
    private static byte[] _pixelCropRightId = {84, -35};
    private static byte[] _pixelCropTopId = {84, -69};
    private static byte[] _pixelHeightId = {-70};
    private static byte[] _pixelWidthId = {-80};
    private long _alphaMode;
    private NullableLong _displayHeight;
    private long _displayUnit;
    private NullableLong _displayWidth;
    private long _flagInterlaced;
    private long _pixelCropBottom;
    private long _pixelCropLeft;
    private long _pixelCropRight;
    private long _pixelCropTop;
    private long _pixelHeight;
    private long _pixelWidth;

    public static long getDefaultAlphaMode() {
        return 0;
    }

    public static long getDefaultDisplayUnit() {
        return 0;
    }

    public static long getDefaultFlagInterlaced() {
        return 0;
    }

    public static long getDefaultPixelCropBottom() {
        return 0;
    }

    public static long getDefaultPixelCropLeft() {
        return 0;
    }

    public static long getDefaultPixelCropRight() {
        return 0;
    }

    public static long getDefaultPixelCropTop() {
        return 0;
    }

    public static byte[] getEbmlId() {
        return new byte[]{-32};
    }

    public long getAlphaMode() {
        return this._alphaMode;
    }

    public NullableLong getDisplayHeight() {
        return this._displayHeight;
    }

    public long getDisplayUnit() {
        return this._displayUnit;
    }

    public NullableLong getDisplayWidth() {
        return this._displayWidth;
    }

    public long getFlagInterlaced() {
        return this._flagInterlaced;
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        if (super.getWriteDefaultValues() || getFlagInterlaced() != getDefaultFlagInterlaced()) {
            super.writeUnsignedInteger(getFlagInterlaced(), _flagInterlacedId, byteOutputStream);
        }
        super.writeUnsignedInteger(getPixelWidth(), _pixelWidthId, byteOutputStream);
        super.writeUnsignedInteger(getPixelHeight(), _pixelHeightId, byteOutputStream);
        if (super.getWriteDefaultValues() || getPixelCropBottom() != getDefaultPixelCropBottom()) {
            super.writeUnsignedInteger(getPixelCropBottom(), _pixelCropBottomId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getPixelCropTop() != getDefaultPixelCropTop()) {
            super.writeUnsignedInteger(getPixelCropTop(), _pixelCropTopId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getPixelCropLeft() != getDefaultPixelCropLeft()) {
            super.writeUnsignedInteger(getPixelCropLeft(), _pixelCropLeftId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getPixelCropRight() != getDefaultPixelCropRight()) {
            super.writeUnsignedInteger(getPixelCropRight(), _pixelCropRightId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getDisplayWidth().getHasValue()) {
            if (super.getWriteDefaultValues() && !getDisplayWidth().getHasValue()) {
                setDisplayWidth(new NullableLong(getPixelWidth()));
            }
            super.writeUnsignedInteger(getDisplayWidth().getValue(), _displayWidthId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getDisplayHeight().getHasValue()) {
            if (super.getWriteDefaultValues() && !getDisplayHeight().getHasValue()) {
                setDisplayHeight(new NullableLong(getPixelHeight()));
            }
            super.writeUnsignedInteger(getDisplayHeight().getValue(), _displayHeightId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getDisplayUnit() != getDefaultDisplayUnit()) {
            super.writeUnsignedInteger(getDisplayUnit(), _displayUnitId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getAlphaMode() != getDefaultAlphaMode()) {
            super.writeUnsignedInteger(getAlphaMode(), _alphaModeId, byteOutputStream);
        }
        return byteOutputStream.toArray();
    }

    public long getPixelCropBottom() {
        return this._pixelCropBottom;
    }

    public long getPixelCropLeft() {
        return this._pixelCropLeft;
    }

    public long getPixelCropRight() {
        return this._pixelCropRight;
    }

    public long getPixelCropTop() {
        return this._pixelCropTop;
    }

    public long getPixelHeight() {
        return this._pixelHeight;
    }

    public long getPixelWidth() {
        return this._pixelWidth;
    }

    public void setAlphaMode(long j) {
        this._alphaMode = j;
    }

    public void setDisplayHeight(NullableLong nullableLong) {
        this._displayHeight = nullableLong;
    }

    public void setDisplayUnit(long j) {
        this._displayUnit = j;
    }

    public void setDisplayWidth(NullableLong nullableLong) {
        this._displayWidth = nullableLong;
    }

    public void setFlagInterlaced(long j) {
        this._flagInterlaced = j;
    }

    public void setPixelCropBottom(long j) {
        this._pixelCropBottom = j;
    }

    public void setPixelCropLeft(long j) {
        this._pixelCropLeft = j;
    }

    public void setPixelCropRight(long j) {
        this._pixelCropRight = j;
    }

    public void setPixelCropTop(long j) {
        this._pixelCropTop = j;
    }

    public void setPixelHeight(long j) {
        this._pixelHeight = j;
    }

    public void setPixelWidth(long j) {
        this._pixelWidth = j;
    }

    public Video() {
        this._displayHeight = new NullableLong();
        this._displayWidth = new NullableLong();
        setPixelCropBottom(getDefaultPixelCropBottom());
        setPixelCropTop(getDefaultPixelCropTop());
        setPixelCropLeft(getDefaultPixelCropLeft());
        setPixelCropRight(getDefaultPixelCropRight());
        setDisplayUnit(getDefaultDisplayUnit());
        setAlphaMode(getDefaultAlphaMode());
    }

    public Video(byte[] bArr) {
        this();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, _flagInterlacedId)) {
                setFlagInterlaced(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _pixelWidthId)) {
                setPixelWidth(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _pixelHeightId)) {
                setPixelHeight(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _pixelCropBottomId)) {
                setPixelCropBottom(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _pixelCropTopId)) {
                setPixelCropTop(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _pixelCropLeftId)) {
                setPixelCropLeft(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _pixelCropRightId)) {
                setPixelCropRight(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _displayWidthId)) {
                setDisplayWidth(new NullableLong(Element.readUnsignedInteger(readValue)));
            } else if (Element.compare(readId, _displayHeightId)) {
                setDisplayHeight(new NullableLong(Element.readUnsignedInteger(readValue)));
            } else if (Element.compare(readId, _displayUnitId)) {
                setDisplayUnit(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _alphaModeId)) {
                setAlphaMode(Element.readUnsignedInteger(readValue));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaVideo: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
    }
}
