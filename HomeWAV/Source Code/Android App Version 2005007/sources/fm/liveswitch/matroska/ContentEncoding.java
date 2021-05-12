package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.StringExtensions;

public class ContentEncoding extends Element {
    private static byte[] _contentEncodingOrderId = {80, 49};
    private static byte[] _contentEncodingScopeId = {80, 50};
    private static byte[] _contentEncodingTypeId = {80, 51};
    private ContentCompression _contentCompression;
    private long _contentEncodingOrder;
    private long _contentEncodingScope;
    private long _contentEncodingType;

    public static long getDefaultContentEncodingOrder() {
        return 0;
    }

    public static long getDefaultContentEncodingScope() {
        return 1;
    }

    public static long getDefaultContentEncodingType() {
        return 0;
    }

    public ContentEncoding() {
    }

    public ContentEncoding(byte[] bArr) {
        this();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, _contentEncodingOrderId)) {
                setContentEncodingOrder(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _contentEncodingScopeId)) {
                setContentEncodingScope(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _contentEncodingTypeId)) {
                setContentEncodingType(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, ContentCompression.getEbmlId())) {
                setContentCompression(new ContentCompression(readValue));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaContentEncoding: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
    }

    public ContentCompression getContentCompression() {
        return this._contentCompression;
    }

    public long getContentEncodingOrder() {
        return this._contentEncodingOrder;
    }

    public long getContentEncodingScope() {
        return this._contentEncodingScope;
    }

    public long getContentEncodingType() {
        return this._contentEncodingType;
    }

    public static byte[] getEbmlId() {
        return new byte[]{98, 64};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        if (super.getWriteDefaultValues() || getContentEncodingOrder() != getDefaultContentEncodingOrder()) {
            super.writeUnsignedInteger(getContentEncodingOrder(), _contentEncodingOrderId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getContentEncodingScope() != getDefaultContentEncodingScope()) {
            super.writeUnsignedInteger(getContentEncodingScope(), _contentEncodingScopeId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getContentEncodingType() != getDefaultContentEncodingType()) {
            super.writeUnsignedInteger(getContentEncodingType(), _contentEncodingTypeId, byteOutputStream);
        }
        if (getContentCompression() != null) {
            byteOutputStream.writeBuffer(getContentCompression().getBytes());
        }
        return byteOutputStream.toArray();
    }

    public void setContentCompression(ContentCompression contentCompression) {
        this._contentCompression = contentCompression;
    }

    public void setContentEncodingOrder(long j) {
        this._contentEncodingOrder = j;
    }

    public void setContentEncodingScope(long j) {
        this._contentEncodingScope = j;
    }

    public void setContentEncodingType(long j) {
        this._contentEncodingType = j;
    }
}
