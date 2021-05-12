package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;

public class ContentEncodings extends Element {
    private ContentEncoding[] _contentEncodingElements;

    public ContentEncodings() {
    }

    public ContentEncodings(byte[] bArr) {
        this();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, ContentEncoding.getEbmlId())) {
                arrayList.add(new ContentEncoding(readValue));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaContentEncodings: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
        if (ArrayListExtensions.getCount(arrayList) > 0) {
            setContentEncodingElements((ContentEncoding[]) arrayList.toArray(new ContentEncoding[0]));
        }
    }

    public ContentEncoding[] getContentEncodingElements() {
        return this._contentEncodingElements;
    }

    public static byte[] getEbmlId() {
        return new byte[]{109, ByteCompanionObject.MIN_VALUE};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        if (getContentEncodingElements() != null) {
            for (ContentEncoding bytes : getContentEncodingElements()) {
                byteOutputStream.writeBuffer(bytes.getBytes());
            }
        }
        return byteOutputStream.toArray();
    }

    public void setContentEncodingElements(ContentEncoding[] contentEncodingArr) {
        this._contentEncodingElements = contentEncodingArr;
    }
}
