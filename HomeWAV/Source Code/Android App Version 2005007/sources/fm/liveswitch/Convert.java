package fm.liveswitch;

public class Convert {
    public static int toInt32(char c) {
        return c;
    }

    public static int toInt32(String str, int i) {
        return Integer.valueOf(str, i).intValue();
    }
}
