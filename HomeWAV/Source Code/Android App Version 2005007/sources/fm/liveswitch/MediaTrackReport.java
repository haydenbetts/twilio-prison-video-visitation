package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import wseemann.media.FFmpegMediaMetadataRetriever;

public class MediaTrackReport extends Report {
    private NullableInteger _bitrate = new NullableInteger();
    private NullableInteger _frameHeight = new NullableInteger();
    private NullableDouble _frameRate = new NullableDouble();
    private NullableInteger _frameWidth = new NullableInteger();
    private NullableLong _framesCorrupted = new NullableLong();
    private NullableLong _framesDecoded = new NullableLong();
    private NullableLong _framesDropped = new NullableLong();
    private NullableLong _framesEncoded = new NullableLong();
    private NullableLong _framesReceived = new NullableLong();
    private NullableLong _framesSent = new NullableLong();
    private NullableInteger _maxBitrate = new NullableInteger();
    private NullableInteger _minBitrate = new NullableInteger();

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("minBitrate")) {
            setMinBitrate(JsonSerializer.deserializeInteger(str2));
        } else if (str.equals("maxBitrate")) {
            setMaxBitrate(JsonSerializer.deserializeInteger(str2));
        } else if (str.equals(FFmpegMediaMetadataRetriever.METADATA_KEY_VARIANT_BITRATE)) {
            setBitrate(JsonSerializer.deserializeInteger(str2));
        } else if (str.equals("frameWidth")) {
            setFrameWidth(JsonSerializer.deserializeInteger(str2));
        } else if (str.equals("frameHeight")) {
            setFrameHeight(JsonSerializer.deserializeInteger(str2));
        } else if (str.equals("frameRate")) {
            setFrameRate(JsonSerializer.deserializeDouble(str2));
        } else if (str.equals("framesSent")) {
            setFramesSent(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("framesReceived")) {
            setFramesReceived(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("framesDecoded")) {
            setFramesDecoded(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("framesDropped")) {
            setFramesDropped(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("framesCorrupted")) {
            setFramesCorrupted(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("framesEncoded")) {
            setFramesEncoded(JsonSerializer.deserializeLong(str2));
        }
    }

    public static MediaTrackReport fromJson(String str) {
        return (MediaTrackReport) JsonSerializer.deserializeObject(str, new IFunction0<MediaTrackReport>() {
            public MediaTrackReport invoke() {
                return new MediaTrackReport();
            }
        }, new IAction3<MediaTrackReport, String, String>() {
            public void invoke(MediaTrackReport mediaTrackReport, String str, String str2) {
                mediaTrackReport.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaTrackReport[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaTrackReport>() {
            public String getId() {
                return "fm.liveswitch.MediaTrackReport.fromJson";
            }

            public MediaTrackReport invoke(String str) {
                return MediaTrackReport.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaTrackReport[]) deserializeObjectArray.toArray(new MediaTrackReport[0]);
    }

    public NullableInteger getBitrate() {
        return this._bitrate;
    }

    public NullableInteger getFrameHeight() {
        return this._frameHeight;
    }

    public NullableDouble getFrameRate() {
        return this._frameRate;
    }

    public NullableLong getFramesCorrupted() {
        return this._framesCorrupted;
    }

    public NullableLong getFramesDecoded() {
        return this._framesDecoded;
    }

    public NullableLong getFramesDropped() {
        return this._framesDropped;
    }

    public NullableLong getFramesEncoded() {
        return this._framesEncoded;
    }

    public NullableLong getFramesReceived() {
        return this._framesReceived;
    }

    public NullableLong getFramesSent() {
        return this._framesSent;
    }

    public NullableInteger getFrameWidth() {
        return this._frameWidth;
    }

    public NullableInteger getMaxBitrate() {
        return this._maxBitrate;
    }

    public NullableInteger getMinBitrate() {
        return this._minBitrate;
    }

    public MediaTrackReport() {
    }

    MediaTrackReport(MediaTrackStats mediaTrackStats, MediaTrackStats mediaTrackStats2) {
        int i = 0;
        boolean z = mediaTrackStats2 == null;
        setMinBitrate(Report.processInteger(mediaTrackStats.getMinBitrate(), z ? 0 : mediaTrackStats2.getMinBitrate()));
        setMaxBitrate(Report.processInteger(mediaTrackStats.getMaxBitrate(), z ? 0 : mediaTrackStats2.getMaxBitrate()));
        setBitrate(Report.processInteger(mediaTrackStats.getBitrate(), z ? 0 : mediaTrackStats2.getBitrate()));
        long j = 0;
        setFramesCorrupted(Report.processLong(mediaTrackStats.getFramesCorrupted(), z ? 0 : mediaTrackStats2.getFramesCorrupted()));
        setFramesDecoded(Report.processLong(mediaTrackStats.getFramesDecoded(), z ? 0 : mediaTrackStats2.getFramesDecoded()));
        setFramesDropped(Report.processLong(mediaTrackStats.getFramesDropped(), z ? 0 : mediaTrackStats2.getFramesDropped()));
        setFramesEncoded(Report.processLong(mediaTrackStats.getFramesEncoded(), z ? 0 : mediaTrackStats2.getFramesEncoded()));
        setFrameHeight(Report.processInteger(mediaTrackStats.getFrameHeight(), z ? 0 : mediaTrackStats2.getFrameHeight()));
        setFrameRate(Report.processDouble(mediaTrackStats.getFrameRate(), z ? 0.0d : mediaTrackStats2.getFrameRate()));
        setFramesReceived(Report.processLong(mediaTrackStats.getFramesReceived(), z ? 0 : mediaTrackStats2.getFramesReceived()));
        setFramesSent(Report.processLong(mediaTrackStats.getFramesSent(), !z ? mediaTrackStats2.getFramesSent() : j));
        setFrameWidth(Report.processInteger(mediaTrackStats.getFrameWidth(), !z ? mediaTrackStats2.getFrameWidth() : i));
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getMinBitrate().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "minBitrate", JsonSerializer.serializeInteger(getMinBitrate()));
        }
        if (getMaxBitrate().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "maxBitrate", JsonSerializer.serializeInteger(getMaxBitrate()));
        }
        if (getBitrate().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), FFmpegMediaMetadataRetriever.METADATA_KEY_VARIANT_BITRATE, JsonSerializer.serializeInteger(getBitrate()));
        }
        if (getFrameWidth().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "frameWidth", JsonSerializer.serializeInteger(getFrameWidth()));
        }
        if (getFrameHeight().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "frameHeight", JsonSerializer.serializeInteger(getFrameHeight()));
        }
        if (getFrameRate().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "frameRate", JsonSerializer.serializeDouble(getFrameRate()));
        }
        if (getFramesSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesSent", JsonSerializer.serializeLong(getFramesSent()));
        }
        if (getFramesReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesReceived", JsonSerializer.serializeLong(getFramesReceived()));
        }
        if (getFramesDecoded().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesDecoded", JsonSerializer.serializeLong(getFramesDecoded()));
        }
        if (getFramesDropped().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesDropped", JsonSerializer.serializeLong(getFramesDropped()));
        }
        if (getFramesCorrupted().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesCorrupted", JsonSerializer.serializeLong(getFramesCorrupted()));
        }
        if (getFramesEncoded().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesEncoded", JsonSerializer.serializeLong(getFramesEncoded()));
        }
    }

    public void setBitrate(NullableInteger nullableInteger) {
        this._bitrate = nullableInteger;
    }

    public void setFrameHeight(NullableInteger nullableInteger) {
        this._frameHeight = nullableInteger;
    }

    public void setFrameRate(NullableDouble nullableDouble) {
        this._frameRate = nullableDouble;
    }

    public void setFramesCorrupted(NullableLong nullableLong) {
        this._framesCorrupted = nullableLong;
    }

    public void setFramesDecoded(NullableLong nullableLong) {
        this._framesDecoded = nullableLong;
    }

    public void setFramesDropped(NullableLong nullableLong) {
        this._framesDropped = nullableLong;
    }

    public void setFramesEncoded(NullableLong nullableLong) {
        this._framesEncoded = nullableLong;
    }

    public void setFramesReceived(NullableLong nullableLong) {
        this._framesReceived = nullableLong;
    }

    public void setFramesSent(NullableLong nullableLong) {
        this._framesSent = nullableLong;
    }

    public void setFrameWidth(NullableInteger nullableInteger) {
        this._frameWidth = nullableInteger;
    }

    public void setMaxBitrate(NullableInteger nullableInteger) {
        this._maxBitrate = nullableInteger;
    }

    public void setMinBitrate(NullableInteger nullableInteger) {
        this._minBitrate = nullableInteger;
    }

    public static String toJson(MediaTrackReport mediaTrackReport) {
        return JsonSerializer.serializeObject(mediaTrackReport, new IAction2<MediaTrackReport, HashMap<String, String>>() {
            public void invoke(MediaTrackReport mediaTrackReport, HashMap<String, String> hashMap) {
                mediaTrackReport.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaTrackReport[] mediaTrackReportArr) {
        return JsonSerializer.serializeObjectArray(mediaTrackReportArr, new IFunctionDelegate1<MediaTrackReport, String>() {
            public String getId() {
                return "fm.liveswitch.MediaTrackReport.toJson";
            }

            public String invoke(MediaTrackReport mediaTrackReport) {
                return MediaTrackReport.toJson(mediaTrackReport);
            }
        });
    }
}
