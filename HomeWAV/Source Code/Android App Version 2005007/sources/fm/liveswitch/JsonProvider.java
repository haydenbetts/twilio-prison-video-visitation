package fm.liveswitch;

public abstract class JsonProvider {
    public abstract <T> T deserialize(String str);

    public abstract <T> String serialize(T t);

    protected JsonProvider() {
    }
}
