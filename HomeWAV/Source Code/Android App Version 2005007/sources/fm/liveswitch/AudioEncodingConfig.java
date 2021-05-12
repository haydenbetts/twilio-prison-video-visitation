package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class AudioEncodingConfig extends EncodingConfig {
    public AudioEncodingConfig() {
    }

    public AudioEncodingConfig(EncodingInfo encodingInfo) {
        super(encodingInfo);
    }

    public static AudioEncodingConfig fromJson(String str) {
        return (AudioEncodingConfig) JsonSerializer.deserializeObject(str, new IFunction0<AudioEncodingConfig>() {
            public AudioEncodingConfig invoke() {
                return new AudioEncodingConfig();
            }
        }, new IAction3<AudioEncodingConfig, String, String>() {
            public void invoke(AudioEncodingConfig audioEncodingConfig, String str, String str2) {
                audioEncodingConfig.deserializeProperties(str, str2);
            }
        });
    }

    public static AudioEncodingConfig[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, AudioEncodingConfig>() {
            public String getId() {
                return "fm.liveswitch.AudioEncodingConfig.fromJson";
            }

            public AudioEncodingConfig invoke(String str) {
                return AudioEncodingConfig.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (AudioEncodingConfig[]) deserializeObjectArray.toArray(new AudioEncodingConfig[0]);
    }

    public static String toJson(AudioEncodingConfig audioEncodingConfig) {
        return JsonSerializer.serializeObject(audioEncodingConfig, new IAction2<AudioEncodingConfig, HashMap<String, String>>() {
            public void invoke(AudioEncodingConfig audioEncodingConfig, HashMap<String, String> hashMap) {
                audioEncodingConfig.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(AudioEncodingConfig[] audioEncodingConfigArr) {
        return JsonSerializer.serializeObjectArray(audioEncodingConfigArr, new IFunctionDelegate1<AudioEncodingConfig, String>() {
            public String getId() {
                return "fm.liveswitch.AudioEncodingConfig.toJson";
            }

            public String invoke(AudioEncodingConfig audioEncodingConfig) {
                return AudioEncodingConfig.toJson(audioEncodingConfig);
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
        if (bitrate != -1) {
            z = true;
            arrayList.add(StringExtensions.format("Bitrate: {0}", (Object) IntegerExtensions.toString(Integer.valueOf(bitrate))));
        } else {
            z = false;
        }
        String join = StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0]));
        return !z ? StringExtensions.format("{0} [Unrestricted]", (Object) join).trim() : join;
    }
}
