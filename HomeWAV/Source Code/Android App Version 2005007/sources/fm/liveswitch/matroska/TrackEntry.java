package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.Global;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.LockedRandomizer;
import fm.liveswitch.Log;
import fm.liveswitch.NullableFloat;
import fm.liveswitch.NullableLong;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;

public class TrackEntry extends Element {
    private static byte[] _attachmentLinkId = {116, 70};
    private static byte[] _codecDelayId = {86, -86};
    private static byte[] _codecIdId = {-122};
    private static byte[] _codecNameId = {37, -122, -120};
    private static byte[] _codecPrivateId = {99, -94};
    private static byte[] _defaultDurationId = {35, -29, -125};
    private static byte[] _flagDefaultId = {-120};
    private static byte[] _flagEnabledId = {-71};
    private static byte[] _flagForcedId = {85, -86};
    private static byte[] _flagLacingId = {-100};
    private static byte[] _languageId = {34, -75, -100};
    private static byte[] _maxCacheId = {109, -8};
    private static byte[] _minCacheId = {109, -25};
    private static byte[] _nameId = {83, 110};
    private static byte[] _seekPreRollId = {86, -69};
    private static byte[] _trackNumberId = {-41};
    private static byte[] _trackTimecodeScaleId = {35, 49, 79};
    private static byte[] _trackTypeId = {-125};
    private static byte[] _trackUidId = {115, -59};
    private long[] _attachmentLinks;
    private Audio _audio;
    private NullableLong _codecDelay;
    private String _codecId;
    private String _codecName;
    private byte[] _codecPrivate;
    private ContentEncodings _contentEncodings;
    private NullableLong _defaultDuration;
    private boolean _flagDefault;
    private boolean _flagEnabled;
    private boolean _flagForced;
    private boolean _flagLacing;
    private String _language;
    private NullableLong _maxCache;
    private long _minCache;
    private String _name;
    private NullableLong _seekPreRoll;
    private long _trackNumber;
    private NullableFloat _trackTimecodeScale;
    private long _trackType;
    private long _trackUid;
    private Video _video;

    public static boolean getDefaultFlagDefault() {
        return true;
    }

    public static boolean getDefaultFlagEnabled() {
        return true;
    }

    public static boolean getDefaultFlagForced() {
        return false;
    }

    public static boolean getDefaultFlagLacing() {
        return true;
    }

    public static String getDefaultLanguage() {
        return "eng";
    }

    public static long getDefaultMinCache() {
        return 0;
    }

    public static byte[] getEbmlId() {
        return new byte[]{-82};
    }

    public static int getG722FormatTag() {
        return 655;
    }

    public static String getH264CodecId() {
        return "V_MPEG4/ISO/AVC";
    }

    public static String getOpusCodecId() {
        return "A_OPUS";
    }

    public static String getPcmCodecId() {
        return "A_MS/ACM";
    }

    public static int getPcmaFormatTag() {
        return 6;
    }

    public static int getPcmuFormatTag() {
        return 7;
    }

    public static String getVp8CodecId() {
        return "V_VP8";
    }

    public static String getVp9CodecId() {
        return "V_VP9";
    }

    public long[] getAttachmentLinks() {
        return this._attachmentLinks;
    }

    public Audio getAudio() {
        return this._audio;
    }

    public NullableLong getCodecDelay() {
        return this._codecDelay;
    }

    public String getCodecId() {
        return this._codecId;
    }

    public String getCodecName() {
        return this._codecName;
    }

    public byte[] getCodecPrivate() {
        return this._codecPrivate;
    }

    public ContentEncodings getContentEncodings() {
        return this._contentEncodings;
    }

    public NullableLong getDefaultDuration() {
        return this._defaultDuration;
    }

    public boolean getFlagDefault() {
        return this._flagDefault;
    }

    public boolean getFlagEnabled() {
        return this._flagEnabled;
    }

    public boolean getFlagForced() {
        return this._flagForced;
    }

    public boolean getFlagLacing() {
        return this._flagLacing;
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        if (getCodecId() == null) {
            return null;
        }
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        super.writeUnsignedInteger(getTrackNumber(), _trackNumberId, byteOutputStream);
        super.writeUnsignedInteger(getTrackUid(), _trackUidId, byteOutputStream);
        super.writeUnsignedInteger(getTrackType(), _trackTypeId, byteOutputStream);
        if (super.getWriteDefaultValues() || !Global.equals(Boolean.valueOf(getFlagEnabled()), Boolean.valueOf(getDefaultFlagEnabled()))) {
            super.writeBool(getFlagEnabled(), _flagEnabledId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || !Global.equals(Boolean.valueOf(getFlagDefault()), Boolean.valueOf(getDefaultFlagDefault()))) {
            super.writeBool(getFlagDefault(), _flagDefaultId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || !Global.equals(Boolean.valueOf(getFlagForced()), Boolean.valueOf(getDefaultFlagForced()))) {
            super.writeBool(getFlagForced(), _flagForcedId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || !Global.equals(Boolean.valueOf(getFlagLacing()), Boolean.valueOf(getDefaultFlagLacing()))) {
            super.writeBool(getFlagLacing(), _flagLacingId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getMinCache() != getDefaultMinCache()) {
            super.writeUnsignedInteger(getMinCache(), _minCacheId, byteOutputStream);
        }
        if (getMaxCache().getHasValue()) {
            super.writeUnsignedInteger(getMaxCache().getValue(), _maxCacheId, byteOutputStream);
        }
        if (getDefaultDuration().getHasValue()) {
            super.writeUnsignedInteger(getDefaultDuration().getValue(), _defaultDurationId, byteOutputStream);
        }
        if (getTrackTimecodeScale().getHasValue()) {
            super.writeFloat(getTrackTimecodeScale().getValue(), _trackTimecodeScaleId, byteOutputStream);
        }
        if (getName() != null) {
            super.writeUtf8(getName(), _nameId, byteOutputStream);
        }
        if ((super.getWriteDefaultValues() || !Global.equals(getLanguage(), getDefaultLanguage())) && getLanguage() != null) {
            super.writeString(getLanguage(), _languageId, byteOutputStream);
        }
        super.writeString(getCodecId(), _codecIdId, byteOutputStream);
        if (getCodecPrivate() != null) {
            super.write(getCodecPrivate(), _codecPrivateId, byteOutputStream);
        }
        if (getCodecName() != null) {
            super.writeUtf8(getCodecName(), _codecNameId, byteOutputStream);
        }
        if (getCodecDelay().getHasValue()) {
            super.writeUnsignedInteger(getCodecDelay().getValue(), _codecDelayId, byteOutputStream);
        }
        if (getSeekPreRoll().getHasValue()) {
            super.writeUnsignedInteger(getSeekPreRoll().getValue(), _seekPreRollId, byteOutputStream);
        }
        if (getAttachmentLinks() != null) {
            for (long writeUnsignedInteger : getAttachmentLinks()) {
                super.writeUnsignedInteger(writeUnsignedInteger, _attachmentLinkId, byteOutputStream);
            }
        }
        if (getVideo() != null) {
            byteOutputStream.writeBuffer(getVideo().getBytes());
        }
        if (getAudio() != null) {
            byteOutputStream.writeBuffer(getAudio().getBytes());
        }
        if (getContentEncodings() != null) {
            byteOutputStream.writeBuffer(getContentEncodings().getBytes());
        }
        return byteOutputStream.toArray();
    }

    public String getLanguage() {
        return this._language;
    }

    public NullableLong getMaxCache() {
        return this._maxCache;
    }

    public long getMinCache() {
        return this._minCache;
    }

    public String getName() {
        return this._name;
    }

    public NullableLong getSeekPreRoll() {
        return this._seekPreRoll;
    }

    public long getTrackNumber() {
        return this._trackNumber;
    }

    public NullableFloat getTrackTimecodeScale() {
        return this._trackTimecodeScale;
    }

    public long getTrackType() {
        return this._trackType;
    }

    public long getTrackUid() {
        return this._trackUid;
    }

    public Video getVideo() {
        return this._video;
    }

    public void setAttachmentLinks(long[] jArr) {
        this._attachmentLinks = jArr;
    }

    public void setAudio(Audio audio) {
        this._audio = audio;
    }

    public void setCodecDelay(NullableLong nullableLong) {
        this._codecDelay = nullableLong;
    }

    public void setCodecId(String str) {
        this._codecId = str;
    }

    public void setCodecName(String str) {
        this._codecName = str;
    }

    public void setCodecPrivate(byte[] bArr) {
        this._codecPrivate = bArr;
    }

    public void setContentEncodings(ContentEncodings contentEncodings) {
        this._contentEncodings = contentEncodings;
    }

    public void setDefaultDuration(NullableLong nullableLong) {
        this._defaultDuration = nullableLong;
    }

    public void setFlagDefault(boolean z) {
        this._flagDefault = z;
    }

    public void setFlagEnabled(boolean z) {
        this._flagEnabled = z;
    }

    public void setFlagForced(boolean z) {
        this._flagForced = z;
    }

    public void setFlagLacing(boolean z) {
        this._flagLacing = z;
    }

    public void setLanguage(String str) {
        this._language = str;
    }

    public void setMaxCache(NullableLong nullableLong) {
        this._maxCache = nullableLong;
    }

    public void setMinCache(long j) {
        this._minCache = j;
    }

    public void setName(String str) {
        this._name = str;
    }

    public void setSeekPreRoll(NullableLong nullableLong) {
        this._seekPreRoll = nullableLong;
    }

    public void setTrackNumber(long j) {
        this._trackNumber = j;
    }

    public void setTrackTimecodeScale(NullableFloat nullableFloat) {
        this._trackTimecodeScale = nullableFloat;
    }

    public void setTrackType(long j) {
        this._trackType = j;
    }

    public void setTrackUid(long j) {
        this._trackUid = j;
    }

    public void setVideo(Video video) {
        this._video = video;
    }

    public TrackEntry() {
        this._codecDelay = new NullableLong();
        this._defaultDuration = new NullableLong();
        this._maxCache = new NullableLong();
        this._seekPreRoll = new NullableLong();
        this._trackTimecodeScale = new NullableFloat();
        setTrackUid(LockedRandomizer.nextLong());
        setFlagEnabled(getDefaultFlagEnabled());
        setFlagDefault(getDefaultFlagDefault());
        setFlagForced(getDefaultFlagForced());
        setFlagLacing(getDefaultFlagLacing());
        setMinCache(getDefaultMinCache());
        setLanguage(getDefaultLanguage());
    }

    public TrackEntry(byte[] bArr) {
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
            if (Element.compare(readId, _trackNumberId)) {
                setTrackNumber(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _trackUidId)) {
                setTrackUid(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _trackTypeId)) {
                setTrackType(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _flagEnabledId)) {
                setFlagEnabled(Element.readBool(readValue));
            } else if (Element.compare(readId, _flagDefaultId)) {
                setFlagDefault(Element.readBool(readValue));
            } else if (Element.compare(readId, _flagForcedId)) {
                setFlagForced(Element.readBool(readValue));
            } else if (Element.compare(readId, _flagLacingId)) {
                setFlagLacing(Element.readBool(readValue));
            } else if (Element.compare(readId, _minCacheId)) {
                setMinCache(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _maxCacheId)) {
                setMaxCache(new NullableLong(Element.readUnsignedInteger(readValue)));
            } else if (Element.compare(readId, _defaultDurationId)) {
                setDefaultDuration(new NullableLong(Element.readUnsignedInteger(readValue)));
            } else if (Element.compare(readId, _trackTimecodeScaleId)) {
                setTrackTimecodeScale(new NullableFloat(Element.readFloat(readValue)));
            } else if (Element.compare(readId, _nameId)) {
                setName(Element.readUtf8(readValue));
            } else if (Element.compare(readId, _languageId)) {
                setLanguage(Element.readString(readValue));
            } else if (Element.compare(readId, _codecIdId)) {
                setCodecId(Element.readString(readValue));
            } else if (Element.compare(readId, _codecPrivateId)) {
                setCodecPrivate(readValue);
            } else if (Element.compare(readId, _codecNameId)) {
                setCodecName(Element.readUtf8(readValue));
            } else if (Element.compare(readId, _codecDelayId)) {
                setCodecDelay(new NullableLong(Element.readUnsignedInteger(readValue)));
            } else if (Element.compare(readId, _seekPreRollId)) {
                setSeekPreRoll(new NullableLong(Element.readUnsignedInteger(readValue)));
            } else if (Element.compare(readId, _attachmentLinkId)) {
                arrayList.add(Long.valueOf(Element.readUnsignedInteger(readValue)));
            } else if (Element.compare(readId, Video.getEbmlId())) {
                setVideo(new Video(readValue));
            } else if (Element.compare(readId, Audio.getEbmlId())) {
                setAudio(new Audio(readValue));
            } else if (Element.compare(readId, ContentEncodings.getEbmlId())) {
                setContentEncodings(new ContentEncodings(readValue));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaTrackEntry: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
        if (ArrayListExtensions.getCount(arrayList) > 0) {
            long[] jArr = new long[ArrayListExtensions.getCount(arrayList)];
            for (int i2 = 0; i2 < ArrayListExtensions.getCount(arrayList); i2++) {
                jArr[i2] = ((Long) ArrayListExtensions.getItem(arrayList).get(i2)).longValue();
            }
            setAttachmentLinks(jArr);
        }
    }
}
