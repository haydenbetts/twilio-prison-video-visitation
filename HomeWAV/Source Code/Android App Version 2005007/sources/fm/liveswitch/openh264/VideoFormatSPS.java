package fm.liveswitch.openh264;

public class VideoFormatSPS {
    private static VideoFormatSPS __component = new VideoFormatSPS(0);
    private static VideoFormatSPS __mac = new VideoFormatSPS(4);
    private static VideoFormatSPS __ntsc = new VideoFormatSPS(2);
    private static VideoFormatSPS __numEnum = new VideoFormatSPS(6);
    private static VideoFormatSPS __pal = new VideoFormatSPS(1);
    private static VideoFormatSPS __secam = new VideoFormatSPS(3);
    private static VideoFormatSPS __undefined = new VideoFormatSPS(5);
    private char _value;

    public static VideoFormatSPS getComponent() {
        return __component;
    }

    public static VideoFormatSPS getMac() {
        return __mac;
    }

    public static VideoFormatSPS getNtsc() {
        return __ntsc;
    }

    public static VideoFormatSPS getNumEnum() {
        return __numEnum;
    }

    public static VideoFormatSPS getPal() {
        return __pal;
    }

    public static VideoFormatSPS getSecam() {
        return __secam;
    }

    public static VideoFormatSPS getUndefined() {
        return __undefined;
    }

    public char getValue() {
        return this._value;
    }

    private void setValue(char c) {
        this._value = c;
    }

    private VideoFormatSPS() {
    }

    private VideoFormatSPS(int i) {
        setValue((char) i);
    }
}
