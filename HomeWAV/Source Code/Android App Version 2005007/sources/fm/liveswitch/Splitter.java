package fm.liveswitch;

import java.util.ArrayList;

public class Splitter {
    public static String[] split(String str, String str2) {
        if (str == null) {
            throw new RuntimeException(new Exception("str cannot be null."));
        } else if (str2 == null) {
            throw new RuntimeException(new Exception("delimiter cannot be null."));
        } else if (StringExtensions.getLength(str2) == 0) {
            return new String[]{str};
        } else {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < StringExtensions.getLength(str); i3++) {
                if (str.charAt(i3) == str2.charAt(i2)) {
                    if (i2 == StringExtensions.getLength(str2) - 1) {
                        arrayList.add(StringExtensions.substring(str, i, (i3 - i2) - i));
                        i = i3 + 1;
                    } else {
                        i2++;
                    }
                }
                i2 = 0;
            }
            arrayList.add(str.substring(i));
            return (String[]) arrayList.toArray(new String[0]);
        }
    }
}
