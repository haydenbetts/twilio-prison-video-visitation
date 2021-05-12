package fm.liveswitch.openh264;

public class VideoBitstreamType {
    private static VideoBitstreamType __avc = new VideoBitstreamType(0);
    private static VideoBitstreamType __svc = new VideoBitstreamType(1);
    private int _value;

    public static VideoBitstreamType getAvc() {
        return __avc;
    }

    public static VideoBitstreamType getDefault() {
        return getSvc();
    }

    public static VideoBitstreamType getSvc() {
        return __svc;
    }

    public int getValue() {
        return this._value;
    }

    private void setValue(int i) {
        this._value = i;
    }

    private VideoBitstreamType() {
    }

    private VideoBitstreamType(int i) {
        setValue(i);
    }
}
