package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.Global;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.StringExtensions;

public class Ebml extends Element {
    private static byte[] _docTypeId = {66, -126};
    private static byte[] _docTypeReadVersionId = {66, -123};
    private static byte[] _docTypeVersionId = {66, -121};
    private static byte[] _ebmlMaxIdLengthId = {66, -14};
    private static byte[] _ebmlMaxSizeLengthId = {66, -13};
    private static byte[] _ebmlReadVersionId = {66, -9};
    private static byte[] _ebmlVersionId = {66, -122};
    private String _docType;
    private long _docTypeReadVersion;
    private long _docTypeVersion;
    private long _ebmlMaxIdLength;
    private long _ebmlMaxSizeLength;
    private long _ebmlReadVersion;
    private long _ebmlVersion;

    public static String getDefaultDocType() {
        return "matroska";
    }

    public static long getDefaultDocTypeReadVersion() {
        return 1;
    }

    public static long getDefaultDocTypeVersion() {
        return 1;
    }

    public static long getDefaultEbmlMaxIdLength() {
        return 4;
    }

    public static long getDefaultEbmlMaxSizeLength() {
        return 8;
    }

    public static long getDefaultEbmlReadVersion() {
        return 1;
    }

    public static long getDefaultEbmlVersion() {
        return 1;
    }

    public void merge(Ebml ebml) {
    }

    public Ebml() {
        setEbmlVersion(getDefaultEbmlVersion());
        setEbmlReadVersion(getDefaultEbmlReadVersion());
        setEbmlMaxIdLength(getDefaultEbmlMaxIdLength());
        setEbmlMaxSizeLength(getDefaultEbmlMaxSizeLength());
        setDocType(getDefaultDocType());
        setDocTypeVersion(getDefaultDocTypeVersion());
        setDocTypeReadVersion(getDefaultDocTypeReadVersion());
    }

    public Ebml(byte[] bArr) {
        this();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, _ebmlVersionId)) {
                setEbmlVersion(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _ebmlReadVersionId)) {
                setEbmlReadVersion(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _ebmlMaxIdLengthId)) {
                setEbmlMaxIdLength(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _ebmlMaxSizeLengthId)) {
                setEbmlMaxSizeLength(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _docTypeId)) {
                setDocType(Element.readString(readValue));
            } else if (Element.compare(readId, _docTypeVersionId)) {
                setDocTypeVersion(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _docTypeReadVersionId)) {
                setDocTypeReadVersion(Element.readUnsignedInteger(readValue));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaEbml: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
    }

    public String getDocType() {
        return this._docType;
    }

    public long getDocTypeReadVersion() {
        return this._docTypeReadVersion;
    }

    public long getDocTypeVersion() {
        return this._docTypeVersion;
    }

    public static byte[] getEbmlId() {
        return new byte[]{26, 69, -33, -93};
    }

    public long getEbmlMaxIdLength() {
        return this._ebmlMaxIdLength;
    }

    public long getEbmlMaxSizeLength() {
        return this._ebmlMaxSizeLength;
    }

    public long getEbmlReadVersion() {
        return this._ebmlReadVersion;
    }

    public long getEbmlVersion() {
        return this._ebmlVersion;
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        if (super.getWriteDefaultValues() || getEbmlVersion() != getDefaultEbmlVersion()) {
            super.writeUnsignedInteger(getEbmlVersion(), _ebmlVersionId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getEbmlReadVersion() != getDefaultEbmlReadVersion()) {
            super.writeUnsignedInteger(getEbmlReadVersion(), _ebmlReadVersionId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getEbmlMaxIdLength() != getDefaultEbmlMaxIdLength()) {
            super.writeUnsignedInteger(getEbmlMaxIdLength(), _ebmlMaxIdLengthId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getEbmlMaxSizeLength() != getDefaultEbmlMaxSizeLength()) {
            super.writeUnsignedInteger(getEbmlMaxSizeLength(), _ebmlMaxSizeLengthId, byteOutputStream);
        }
        if ((super.getWriteDefaultValues() || !Global.equals(getDocType(), getDefaultDocType())) && getDocType() != null) {
            super.writeString(getDocType(), _docTypeId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getDocTypeVersion() != getDefaultDocTypeVersion()) {
            super.writeUnsignedInteger(getDocTypeVersion(), _docTypeVersionId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getDocTypeReadVersion() != getDefaultDocTypeReadVersion()) {
            super.writeUnsignedInteger(getDocTypeReadVersion(), _docTypeReadVersionId, byteOutputStream);
        }
        return byteOutputStream.toArray();
    }

    public void setDocType(String str) {
        this._docType = str;
    }

    public void setDocTypeReadVersion(long j) {
        this._docTypeReadVersion = j;
    }

    public void setDocTypeVersion(long j) {
        this._docTypeVersion = j;
    }

    public void setEbmlMaxIdLength(long j) {
        this._ebmlMaxIdLength = j;
    }

    public void setEbmlMaxSizeLength(long j) {
        this._ebmlMaxSizeLength = j;
    }

    public void setEbmlReadVersion(long j) {
        this._ebmlReadVersion = j;
    }

    public void setEbmlVersion(long j) {
        this._ebmlVersion = j;
    }
}
