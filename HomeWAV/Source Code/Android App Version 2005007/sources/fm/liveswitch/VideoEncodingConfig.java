package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class VideoEncodingConfig extends EncodingConfig {
    private double _frameRate;
    private double _scale;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "frameRate")) {
            setFrameRate(JsonSerializer.deserializeDouble(str2).getValue());
        } else if (Global.equals(str, "scale")) {
            setScale(JsonSerializer.deserializeDouble(str2).getValue());
        }
    }

    public static VideoEncodingConfig fromJson(String str) {
        return (VideoEncodingConfig) JsonSerializer.deserializeObject(str, new IFunction0<VideoEncodingConfig>() {
            public VideoEncodingConfig invoke() {
                return new VideoEncodingConfig();
            }
        }, new IAction3<VideoEncodingConfig, String, String>() {
            public void invoke(VideoEncodingConfig videoEncodingConfig, String str, String str2) {
                videoEncodingConfig.deserializeProperties(str, str2);
            }
        });
    }

    public static VideoEncodingConfig[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, VideoEncodingConfig>() {
            public String getId() {
                return "fm.liveswitch.VideoEncodingConfig.fromJson";
            }

            public VideoEncodingConfig invoke(String str) {
                return VideoEncodingConfig.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (VideoEncodingConfig[]) deserializeObjectArray.toArray(new VideoEncodingConfig[0]);
    }

    public double getFrameRate() {
        return this._frameRate;
    }

    public double getScale() {
        return this._scale;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getFrameRate() != -1.0d) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "frameRate", JsonSerializer.serializeDouble(new NullableDouble(getFrameRate())));
        }
        if (getScale() != -1.0d) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "scale", JsonSerializer.serializeDouble(new NullableDouble(getScale())));
        }
    }

    public void setFrameRate(double d) {
        this._frameRate = d;
    }

    public void setScale(double d) {
        this._scale = d;
    }

    public static String toJson(VideoEncodingConfig videoEncodingConfig) {
        return JsonSerializer.serializeObject(videoEncodingConfig, new IAction2<VideoEncodingConfig, HashMap<String, String>>() {
            public void invoke(VideoEncodingConfig videoEncodingConfig, HashMap<String, String> hashMap) {
                videoEncodingConfig.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(VideoEncodingConfig[] videoEncodingConfigArr) {
        return JsonSerializer.serializeObjectArray(videoEncodingConfigArr, new IFunctionDelegate1<VideoEncodingConfig, String>() {
            public String getId() {
                return "fm.liveswitch.VideoEncodingConfig.toJson";
            }

            public String invoke(VideoEncodingConfig videoEncodingConfig) {
                return VideoEncodingConfig.toJson(videoEncodingConfig);
            }
        });
    }

    public String toString() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        String rtpStreamId = super.getRtpStreamId();
        if (rtpStreamId != null) {
            arrayList.add(StringExtensions.format("RTP Stream ID: {0}", (Object) rtpStreamId));
        }
        long synchronizationSource = super.getSynchronizationSource();
        if (synchronizationSource != -1) {
            arrayList.add(StringExtensions.format("Synchronization Source: {0}", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource))));
        }
        boolean deactivated = super.getDeactivated();
        if (deactivated) {
            arrayList.add(StringExtensions.format("Deactivated: {0}", (Object) BooleanExtensions.toString(Boolean.valueOf(deactivated))));
        }
        int bitrate = super.getBitrate();
        boolean z2 = true;
        if (bitrate != -1) {
            arrayList.add(StringExtensions.format("Bitrate: {0}", (Object) IntegerExtensions.toString(Integer.valueOf(bitrate))));
            z = true;
        } else {
            z = false;
        }
        double frameRate = getFrameRate();
        if (frameRate != -1.0d) {
            arrayList.add(StringExtensions.format("Frame Rate: {0}", (Object) DoubleExtensions.toString(Double.valueOf(frameRate))));
            z = true;
        }
        double scale = getScale();
        if (scale != -1.0d) {
            arrayList.add(StringExtensions.format("Scale: {0}", (Object) DoubleExtensions.toString(Double.valueOf(scale))));
        } else {
            z2 = z;
        }
        String join = StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0]));
        return !z2 ? StringExtensions.format("{0} [Unrestricted]", (Object) join).trim() : join;
    }

    public VideoEncodingConfig() {
        setFrameRate(-1.0d);
        setScale(-1.0d);
    }

    public VideoEncodingConfig(EncodingInfo encodingInfo) {
        super(encodingInfo);
        setFrameRate(encodingInfo.getFrameRate());
        setScale(encodingInfo.getScale());
    }
}
