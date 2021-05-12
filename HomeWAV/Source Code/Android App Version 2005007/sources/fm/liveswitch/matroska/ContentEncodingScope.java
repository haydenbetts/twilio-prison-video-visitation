package fm.liveswitch.matroska;

public class ContentEncodingScope {
    public static long getAll() {
        return 1;
    }

    public static long getCodecPrivate() {
        return 2;
    }

    public static long getContentCompressionInNextContentEncoding() {
        return 4;
    }
}
