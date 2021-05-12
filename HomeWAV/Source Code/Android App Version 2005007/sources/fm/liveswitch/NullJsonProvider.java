package fm.liveswitch;

public class NullJsonProvider extends JsonProvider {
    public <T> T deserialize(String str) {
        return null;
    }

    public <T> String serialize(T t) {
        return null;
    }
}
