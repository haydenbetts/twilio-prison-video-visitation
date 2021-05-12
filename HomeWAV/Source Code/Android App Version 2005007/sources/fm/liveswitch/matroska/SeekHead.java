package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;

public class SeekHead extends Element {
    private Seek[] _seeks;

    public static byte[] getEbmlId() {
        return new byte[]{17, 77, -101, 116};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        if (getSeeks() != null) {
            for (Seek bytes : getSeeks()) {
                byteOutputStream.writeBuffer(bytes.getBytes());
            }
        }
        return byteOutputStream.toArray();
    }

    public Seek[] getSeeks() {
        return this._seeks;
    }

    public SeekHead() {
    }

    public SeekHead(byte[] bArr) {
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
            if (Element.compare(readId, Seek.getEbmlId())) {
                arrayList.add(new Seek(readValue));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaSeekHead: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
        if (ArrayListExtensions.getCount(arrayList) > 0) {
            setSeeks((Seek[]) arrayList.toArray(new Seek[0]));
        }
    }

    public void setSeeks(Seek[] seekArr) {
        this._seeks = seekArr;
    }
}
