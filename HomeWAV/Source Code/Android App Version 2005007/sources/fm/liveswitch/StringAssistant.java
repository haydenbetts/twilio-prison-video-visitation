package fm.liveswitch;

public class StringAssistant {
    public static boolean isNullOrWhiteSpace(String str) {
        return str == null || StringExtensions.isNullOrEmpty(str.trim());
    }

    public static String[] subArray(String[] strArr, int i) {
        return subArray(strArr, i, ArrayExtensions.getLength((Object[]) strArr) - i);
    }

    public static String[] subArray(String[] strArr, int i, int i2) {
        String[] strArr2 = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            strArr2[i3] = strArr[i + i3];
        }
        return strArr2;
    }
}
