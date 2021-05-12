package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.StringExtensions;

public class File {
    private Ebml _ebml;
    private Segment _segment;

    public File() {
    }

    public File(byte[] bArr) {
        this();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, Ebml.getEbmlId())) {
                setEbml(new Ebml(readValue));
            } else if (Element.compare(readId, Segment.getEbmlId())) {
                setSegment(new Segment(readValue));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaFile: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
    }

    public byte[] getBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        if (getEbml() != null) {
            byteOutputStream.writeBuffer(getEbml().getBytes());
        }
        if (getSegment() != null) {
            byteOutputStream.writeBuffer(getSegment().getBytes());
        }
        return byteOutputStream.toArray();
    }

    public Ebml getEbml() {
        return this._ebml;
    }

    public Segment getSegment() {
        return this._segment;
    }

    public void merge(File file) {
        if (file != null) {
            if (getEbml() == null) {
                setEbml(file.getEbml());
            } else {
                getEbml().merge(file.getEbml());
            }
            if (getSegment() == null) {
                setSegment(file.getSegment());
            } else {
                getSegment().merge(file.getSegment());
            }
        }
    }

    public void setEbml(Ebml ebml) {
        this._ebml = ebml;
    }

    public void setSegment(Segment segment) {
        this._segment = segment;
    }
}
