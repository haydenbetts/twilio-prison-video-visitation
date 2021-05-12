package fm.liveswitch.ogg;

public class PageHeaderType {
    public static byte getAll() {
        return 7;
    }

    public static byte getFirstPage() {
        return 2;
    }

    public static byte getIsContinued() {
        return 1;
    }

    public static byte getLastPage() {
        return 4;
    }

    public static byte getNone() {
        return 0;
    }
}
