package fm.liveswitch.matroska;

import com.google.android.exoplayer2.C;
import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.NullableDate;
import fm.liveswitch.NullableFloat;
import fm.liveswitch.NullableGuid;
import fm.liveswitch.StringExtensions;
import kotlin.jvm.internal.ByteCompanionObject;

public class SegmentInfo extends Element {
    private static byte[] _dateUtcId = {68, 97};
    private static byte[] _durationId = {68, -119};
    private static byte[] _muxingAppId = {77, ByteCompanionObject.MIN_VALUE};
    private static byte[] _nextFilenameId = {62, -125, -69};
    private static byte[] _nextUidId = {62, -71, 35};
    private static byte[] _prevFilenameId = {60, -125, -85};
    private static byte[] _prevUidId = {60, -71, 35};
    private static byte[] _segmentFilenameId = {115, -124};
    private static byte[] _segmentUidId = {115, -92};
    private static byte[] _timecodeScaleId = {42, -41, -79};
    private static byte[] _titleId = {123, -87};
    private static byte[] _writingAppId = {87, 65};
    private NullableDate _dateUtc;
    private NullableFloat _duration;
    private String _muxingApp;
    private String _nextFilename;
    private NullableGuid _nextUid;
    private String _prevFilename;
    private NullableGuid _prevUid;
    private String _segmentFilename;
    private NullableGuid _segmentUid;
    private long _timecodeScale;
    private String _title;
    private String _writingApp;

    public static long getDefaultTimecodeScale() {
        return C.MICROS_PER_SECOND;
    }

    public NullableDate getDateUtc() {
        return this._dateUtc;
    }

    public NullableFloat getDuration() {
        return this._duration;
    }

    public static byte[] getEbmlId() {
        return new byte[]{21, 73, -87, 102};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        if (getSegmentUid().getHasValue()) {
            super.writeGuid(getSegmentUid().getValue(), _segmentUidId, byteOutputStream);
        }
        if (getSegmentFilename() != null) {
            super.writeUtf8(getSegmentFilename(), _segmentFilenameId, byteOutputStream);
        }
        if (getPrevUid().getHasValue()) {
            super.writeGuid(getPrevUid().getValue(), _prevUidId, byteOutputStream);
        }
        if (getPrevFilename() != null) {
            super.writeUtf8(getPrevFilename(), _prevFilenameId, byteOutputStream);
        }
        if (getNextUid().getHasValue()) {
            super.writeGuid(getNextUid().getValue(), _nextUidId, byteOutputStream);
        }
        if (getNextFilename() != null) {
            super.writeUtf8(getNextFilename(), _nextFilenameId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getTimecodeScale() != getDefaultTimecodeScale()) {
            super.writeUnsignedInteger(getTimecodeScale(), _timecodeScaleId, byteOutputStream);
        }
        if (getDuration().getHasValue()) {
            super.writeFloat(getDuration().getValue(), _durationId, byteOutputStream);
        }
        if (getTitle() != null) {
            super.writeUtf8(getTitle(), _titleId, byteOutputStream);
        }
        if (getMuxingApp() != null) {
            super.writeString(getMuxingApp(), _muxingAppId, byteOutputStream);
        }
        if (getWritingApp() != null) {
            super.writeUtf8(getWritingApp(), _writingAppId, byteOutputStream);
        }
        if (getDateUtc().getHasValue()) {
            super.writeDate(getDateUtc().getValue(), _dateUtcId, byteOutputStream);
        }
        return byteOutputStream.toArray();
    }

    public String getMuxingApp() {
        return this._muxingApp;
    }

    public String getNextFilename() {
        return this._nextFilename;
    }

    public NullableGuid getNextUid() {
        return this._nextUid;
    }

    public String getPrevFilename() {
        return this._prevFilename;
    }

    public NullableGuid getPrevUid() {
        return this._prevUid;
    }

    public String getSegmentFilename() {
        return this._segmentFilename;
    }

    public NullableGuid getSegmentUid() {
        return this._segmentUid;
    }

    public long getTimecodeScale() {
        return this._timecodeScale;
    }

    public String getTitle() {
        return this._title;
    }

    public String getWritingApp() {
        return this._writingApp;
    }

    public void merge(SegmentInfo segmentInfo) {
        if (segmentInfo != null && segmentInfo.getTimecodeScale() != getTimecodeScale()) {
            throw new RuntimeException(new Exception("Could not merge segment infos. Timecode scales do not match."));
        }
    }

    public SegmentInfo() {
        this._dateUtc = new NullableDate();
        this._duration = new NullableFloat();
        this._nextUid = new NullableGuid();
        this._prevUid = new NullableGuid();
        this._segmentUid = new NullableGuid();
        setTimecodeScale(getDefaultTimecodeScale());
    }

    public SegmentInfo(byte[] bArr) {
        this();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, _segmentUidId)) {
                setSegmentUid(new NullableGuid(Element.readGuid(readValue)));
            } else if (Element.compare(readId, _segmentFilenameId)) {
                setSegmentFilename(Element.readUtf8(readValue));
            } else if (Element.compare(readId, _prevUidId)) {
                setPrevUid(new NullableGuid(Element.readGuid(readValue)));
            } else if (Element.compare(readId, _prevFilenameId)) {
                setPrevFilename(Element.readUtf8(readValue));
            } else if (Element.compare(readId, _nextUidId)) {
                setNextUid(new NullableGuid(Element.readGuid(readValue)));
            } else if (Element.compare(readId, _nextFilenameId)) {
                setNextFilename(Element.readUtf8(readValue));
            } else if (Element.compare(readId, _timecodeScaleId)) {
                setTimecodeScale(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _durationId)) {
                setDuration(new NullableFloat(Element.readFloat(readValue)));
            } else if (Element.compare(readId, _titleId)) {
                setTitle(Element.readUtf8(readValue));
            } else if (Element.compare(readId, _muxingAppId)) {
                setMuxingApp(Element.readString(readValue));
            } else if (Element.compare(readId, _writingAppId)) {
                setWritingApp(Element.readUtf8(readValue));
            } else if (Element.compare(readId, _dateUtcId)) {
                setDateUtc(new NullableDate(Element.readDate(readValue)));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaSegmentInfo: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
    }

    public void setDateUtc(NullableDate nullableDate) {
        this._dateUtc = nullableDate;
    }

    public void setDuration(NullableFloat nullableFloat) {
        this._duration = nullableFloat;
    }

    public void setMuxingApp(String str) {
        this._muxingApp = str;
    }

    public void setNextFilename(String str) {
        this._nextFilename = str;
    }

    public void setNextUid(NullableGuid nullableGuid) {
        this._nextUid = nullableGuid;
    }

    public void setPrevFilename(String str) {
        this._prevFilename = str;
    }

    public void setPrevUid(NullableGuid nullableGuid) {
        this._prevUid = nullableGuid;
    }

    public void setSegmentFilename(String str) {
        this._segmentFilename = str;
    }

    public void setSegmentUid(NullableGuid nullableGuid) {
        this._segmentUid = nullableGuid;
    }

    public void setTimecodeScale(long j) {
        this._timecodeScale = j;
    }

    public void setTitle(String str) {
        this._title = str;
    }

    public void setWritingApp(String str) {
        this._writingApp = str;
    }
}
