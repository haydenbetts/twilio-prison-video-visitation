package fm.liveswitch;

public class Feedback {
    private String __parameter;
    private String __type;

    public Feedback(String str, String str2) {
        this.__type = str;
        this.__parameter = str2;
    }

    public String getParameter() {
        return this.__parameter;
    }

    public String getType() {
        return this.__type;
    }
}
