package fm.liveswitch.openh264;

public class UsageType {
    private static UsageType __cameraVideoNonRealTime = new UsageType(2);
    private static UsageType __cameraVideoRealTime = new UsageType(0);
    private static UsageType __screenContentRealTime = new UsageType(1);
    private int _value;

    public static UsageType getCameraVideoNonRealTime() {
        return __cameraVideoNonRealTime;
    }

    public static UsageType getCameraVideoRealTime() {
        return __cameraVideoRealTime;
    }

    public static UsageType getScreenContentRealTime() {
        return __screenContentRealTime;
    }

    public int getValue() {
        return this._value;
    }

    private void setValue(int i) {
        this._value = i;
    }

    private UsageType() {
    }

    private UsageType(int i) {
        setValue(i);
    }
}
