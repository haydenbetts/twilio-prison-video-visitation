package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class SinkOutput {
    private String _id;
    private String _name;

    public static SinkOutput fromJson(String str) {
        return (SinkOutput) JsonSerializer.deserializeObject(str, new IFunction0<SinkOutput>() {
            public SinkOutput invoke() {
                return new SinkOutput();
            }
        }, new IAction3<SinkOutput, String, String>() {
            public void invoke(SinkOutput sinkOutput, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "id")) {
                    sinkOutput.setId(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "name")) {
                    sinkOutput.setName(JsonSerializer.deserializeString(str2));
                }
            }
        });
    }

    public static SinkOutput[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, SinkOutput>() {
            public String getId() {
                return "fm.liveswitch.SinkOutput.fromJson";
            }

            public SinkOutput invoke(String str) {
                return SinkOutput.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (SinkOutput[]) deserializeObjectArray.toArray(new SinkOutput[0]);
    }

    public String getId() {
        return this._id;
    }

    public String getName() {
        return this._name;
    }

    /* access modifiers changed from: private */
    public void setId(String str) {
        this._id = str;
    }

    /* access modifiers changed from: private */
    public void setName(String str) {
        this._name = str;
    }

    private SinkOutput() {
    }

    public SinkOutput(String str, String str2) {
        setId(str);
        setName(str2);
    }

    public static String toJson(SinkOutput sinkOutput) {
        return JsonSerializer.serializeObject(sinkOutput, new IAction2<SinkOutput, HashMap<String, String>>() {
            public void invoke(SinkOutput sinkOutput, HashMap<String, String> hashMap) {
                if (sinkOutput.getId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "id", JsonSerializer.serializeString(sinkOutput.getId()));
                }
                if (sinkOutput.getName() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "name", JsonSerializer.serializeString(sinkOutput.getName()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(SinkOutput[] sinkOutputArr) {
        return JsonSerializer.serializeObjectArray(sinkOutputArr, new IFunctionDelegate1<SinkOutput, String>() {
            public String getId() {
                return "fm.liveswitch.SinkOutput.toJson";
            }

            public String invoke(SinkOutput sinkOutput) {
                return SinkOutput.toJson(sinkOutput);
            }
        });
    }

    public String toString() {
        return getName();
    }
}
