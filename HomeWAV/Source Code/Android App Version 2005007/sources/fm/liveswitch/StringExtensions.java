package fm.liveswitch;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class StringExtensions {
    public static String empty = "";

    public static String concat(String str) {
        return str == null ? "" : str;
    }

    public static String concat(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            sb.append(obj.toString());
        }
        return sb.toString();
    }

    public static String concat(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        return str + str2;
    }

    public static String concat(String str, String str2, String str3) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        return str + str2 + str3;
    }

    public static String concat(String str, String str2, String str3, String str4) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        if (str4 == null) {
            str4 = "";
        }
        return str + str2 + str3 + str4;
    }

    public static String join(String str, String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                sb.append(str);
            }
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    public static String[] split(String str, char[] cArr) {
        int i;
        ArrayList arrayList = new ArrayList();
        if (cArr.length > 0) {
            arrayList.add(str);
            for (char valueOf : cArr) {
                Character valueOf2 = Character.valueOf(valueOf);
                for (int i2 = 0; i2 < arrayList.size(); i2 = i2 + (i - 1) + 1) {
                    String[] split = ((String) arrayList.get(i2)).split(Pattern.quote(valueOf2.toString()));
                    arrayList.remove(i2);
                    i = 0;
                    while (i < split.length) {
                        arrayList.add(i2 + i, split[i]);
                        i++;
                    }
                }
            }
        } else {
            arrayList.add(str);
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }

    public static boolean isEqual(String str, String str2, StringComparison stringComparison) {
        if (stringComparison == StringComparison.CurrentCultureIgnoreCase || stringComparison == StringComparison.InvariantCultureIgnoreCase || stringComparison == StringComparison.OrdinalIgnoreCase) {
            return str.equalsIgnoreCase(str2);
        }
        return str.equals(str2);
    }

    public static int indexOf(String str, String str2) {
        return str.indexOf(str2);
    }

    public static int indexOf(String str, char c) {
        return str.indexOf(c);
    }

    public static int indexOf(String str, String str2, StringComparison stringComparison) {
        if (stringComparison == StringComparison.CurrentCultureIgnoreCase || stringComparison == StringComparison.InvariantCultureIgnoreCase || stringComparison == StringComparison.OrdinalIgnoreCase) {
            return str.toLowerCase().indexOf(str2.toLowerCase());
        }
        return str.indexOf(str2);
    }

    public static int lastIndexOf(String str, String str2) {
        return str.lastIndexOf(str2);
    }

    public static int lastIndexOf(String str, char c) {
        return str.lastIndexOf(c);
    }

    public static int lastIndexOf(String str, String str2, StringComparison stringComparison) {
        if (stringComparison == StringComparison.CurrentCultureIgnoreCase || stringComparison == StringComparison.InvariantCultureIgnoreCase || stringComparison == StringComparison.OrdinalIgnoreCase) {
            return str.toLowerCase().lastIndexOf(str2.toLowerCase());
        }
        return str.lastIndexOf(str2);
    }

    public static boolean startsWith(String str, String str2) {
        return startsWith(str, str2, StringComparison.CurrentCulture);
    }

    public static boolean startsWith(String str, String str2, StringComparison stringComparison) {
        if (stringComparison == StringComparison.CurrentCultureIgnoreCase || stringComparison == StringComparison.InvariantCultureIgnoreCase || stringComparison == StringComparison.OrdinalIgnoreCase) {
            return str.toLowerCase().startsWith(str2.toLowerCase());
        }
        return str.startsWith(str2);
    }

    public static boolean endsWith(String str, String str2) {
        return endsWith(str, str2, StringComparison.CurrentCulture);
    }

    public static boolean endsWith(String str, String str2, StringComparison stringComparison) {
        if (stringComparison == StringComparison.CurrentCultureIgnoreCase || stringComparison == StringComparison.InvariantCultureIgnoreCase || stringComparison == StringComparison.OrdinalIgnoreCase) {
            return str.toLowerCase().endsWith(str2.toLowerCase());
        }
        return str.endsWith(str2);
    }

    public static String format(String str, Object[] objArr) {
        String str2 = str;
        Object[] objArr2 = objArr;
        switch (objArr2.length) {
            case 0:
                return str2;
            case 1:
                return format(str, objArr2[0]);
            case 2:
                return format(str, objArr2[0], objArr2[1]);
            case 3:
                return format(str, objArr2[0], objArr2[1], objArr2[2]);
            case 4:
                return format(str, objArr2[0], objArr2[1], objArr2[2], objArr2[3]);
            case 5:
                return format(str, objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4]);
            case 6:
                return format(str, objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 7:
                return format(str, objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6]);
            case 8:
                return format(str, objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6], objArr2[7]);
            case 9:
                return format(str, objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6], objArr2[7], objArr2[8]);
            default:
                return format(str, objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6], objArr2[7], objArr2[8], objArr2[9]);
        }
    }

    public static String format(String str, Object obj) {
        return String.format(reformatNetFormat(str, 1), new Object[]{obj});
    }

    public static String format(String str, Object obj, Object obj2) {
        return String.format(reformatNetFormat(str, 2), new Object[]{obj, obj2});
    }

    public static String format(String str, Object obj, Object obj2, Object obj3) {
        return String.format(reformatNetFormat(str, 3), new Object[]{obj, obj2, obj3});
    }

    public static String format(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return String.format(reformatNetFormat(str, 4), new Object[]{obj, obj2, obj3, obj4});
    }

    public static String format(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return String.format(reformatNetFormat(str, 5), new Object[]{obj, obj2, obj3, obj4, obj5});
    }

    public static String format(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return String.format(reformatNetFormat(str, 6), new Object[]{obj, obj2, obj3, obj4, obj5, obj6});
    }

    public static String format(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return String.format(reformatNetFormat(str, 7), new Object[]{obj, obj2, obj3, obj4, obj5, obj6, obj7});
    }

    public static String format(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return String.format(reformatNetFormat(str, 8), new Object[]{obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8});
    }

    public static String format(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return String.format(reformatNetFormat(str, 9), new Object[]{obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9});
    }

    public static String format(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return String.format(reformatNetFormat(str, 10), new Object[]{obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10});
    }

    public static String reformatNetFormat(String str, int i) {
        String replace = str.replace("%", "%%");
        int i2 = 0;
        while (i2 < i) {
            i2++;
            replace = replace.replace(String.format(Locale.US, "{%d}", new Object[]{Integer.valueOf(i2)}), String.format(Locale.US, "%%%d$s", new Object[]{Integer.valueOf(i2)}));
        }
        return replace;
    }

    public static String toLower(String str) {
        return str.toLowerCase();
    }

    public static String toUpper(String str) {
        return str.toUpperCase();
    }

    public static int getLength(String str) {
        return str.length();
    }

    public static char[] getChars(String str) {
        char[] cArr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            cArr[i] = str.charAt(i);
        }
        return cArr;
    }

    public static String substring(String str, int i, int i2) {
        return str.substring(i, i2 + i);
    }

    public static String trimEnd(String str, char[] cArr) {
        if (cArr.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (char append : cArr) {
            sb.append(append);
        }
        return str.replaceAll("\\[" + sb.toString() + "\\]+$", "");
    }
}
