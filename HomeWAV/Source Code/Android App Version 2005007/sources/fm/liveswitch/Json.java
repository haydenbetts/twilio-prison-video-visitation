package fm.liveswitch;

public class Json {
    private static JsonProvider __provider;

    public static <T> T deserialize(String str) {
        return getProvider().deserialize(str);
    }

    private static JsonProvider getDefault() {
        return new NullJsonProvider();
    }

    public static JsonProvider getProvider() {
        if (__provider == null) {
            __provider = getDefault();
        }
        return __provider;
    }

    public static <T> String serialize(T t) {
        return getProvider().serialize(t);
    }

    public static void setProvider(JsonProvider jsonProvider) {
        if (jsonProvider == null) {
            jsonProvider = getDefault();
        }
        __provider = jsonProvider;
    }
}
