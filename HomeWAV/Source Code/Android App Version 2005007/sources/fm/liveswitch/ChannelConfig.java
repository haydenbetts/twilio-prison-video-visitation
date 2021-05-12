package fm.liveswitch;

import java.util.HashMap;

public class ChannelConfig {
    private NullableBoolean _recording = new NullableBoolean();

    public static ChannelConfig fromJson(String str) {
        return (ChannelConfig) JsonSerializer.deserializeObject(str, new IFunction0<ChannelConfig>() {
            public ChannelConfig invoke() {
                return new ChannelConfig();
            }
        }, new IAction3<ChannelConfig, String, String>() {
            public void invoke(ChannelConfig channelConfig, String str, String str2) {
                if (str != null && Global.equals(str, "recording")) {
                    channelConfig.setRecording(JsonSerializer.deserializeBoolean(str2));
                }
            }
        });
    }

    public NullableBoolean getRecording() {
        return this._recording;
    }

    public void setRecording(NullableBoolean nullableBoolean) {
        this._recording = nullableBoolean;
    }

    public static String toJson(ChannelConfig channelConfig) {
        return JsonSerializer.serializeObject(channelConfig, new IAction2<ChannelConfig, HashMap<String, String>>() {
            public void invoke(ChannelConfig channelConfig, HashMap<String, String> hashMap) {
                if (channelConfig.getRecording().getHasValue()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "recording", JsonSerializer.serializeBoolean(channelConfig.getRecording()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
