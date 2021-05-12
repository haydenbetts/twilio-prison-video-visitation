package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class SourceInput {
    private String _id;
    private String _name;

    public static SourceInput fromJson(String str) {
        return (SourceInput) JsonSerializer.deserializeObject(str, new IFunction0<SourceInput>() {
            public SourceInput invoke() {
                return new SourceInput();
            }
        }, new IAction3<SourceInput, String, String>() {
            public void invoke(SourceInput sourceInput, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "id")) {
                    sourceInput.setId(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "name")) {
                    sourceInput.setName(JsonSerializer.deserializeString(str2));
                }
            }
        });
    }

    public static SourceInput[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, SourceInput>() {
            public String getId() {
                return "fm.liveswitch.SourceInput.fromJson";
            }

            public SourceInput invoke(String str) {
                return SourceInput.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (SourceInput[]) deserializeObjectArray.toArray(new SourceInput[0]);
    }

    public String getId() {
        return this._id;
    }

    public String getName() {
        return this._name;
    }

    public void setId(String str) {
        this._id = str;
    }

    public void setName(String str) {
        this._name = str;
    }

    private SourceInput() {
    }

    public SourceInput(String str, String str2) {
        setId(str);
        setName(str2);
    }

    public static String toJson(SourceInput sourceInput) {
        return JsonSerializer.serializeObject(sourceInput, new IAction2<SourceInput, HashMap<String, String>>() {
            public void invoke(SourceInput sourceInput, HashMap<String, String> hashMap) {
                if (sourceInput.getId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "id", JsonSerializer.serializeString(sourceInput.getId()));
                }
                if (sourceInput.getName() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "name", JsonSerializer.serializeString(sourceInput.getName()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(SourceInput[] sourceInputArr) {
        return JsonSerializer.serializeObjectArray(sourceInputArr, new IFunctionDelegate1<SourceInput, String>() {
            public String getId() {
                return "fm.liveswitch.SourceInput.toJson";
            }

            public String invoke(SourceInput sourceInput) {
                return SourceInput.toJson(sourceInput);
            }
        });
    }

    public String toString() {
        return getName();
    }
}
