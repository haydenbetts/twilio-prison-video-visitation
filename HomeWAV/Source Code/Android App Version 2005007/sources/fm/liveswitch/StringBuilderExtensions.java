package fm.liveswitch;

public class StringBuilderExtensions {
    public static StringBuilder append(StringBuilder sb, String str) {
        sb.append(str);
        return sb;
    }

    public static StringBuilder append(StringBuilder sb, char c) {
        sb.append(c);
        return sb;
    }

    public static StringBuilder append(StringBuilder sb, String str, int i, int i2) {
        sb.append(str, i, i2 + i);
        return sb;
    }

    public static int getLength(StringBuilder sb) {
        return sb.length();
    }

    public static StringBuilder remove(StringBuilder sb, int i, int i2) {
        sb.delete(i, i2 + i);
        return sb;
    }

    public static String substring(StringBuilder sb, int i, int i2) {
        return sb.substring(i, i2 + i);
    }
}
