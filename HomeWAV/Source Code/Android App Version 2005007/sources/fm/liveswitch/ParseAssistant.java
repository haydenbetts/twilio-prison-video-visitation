package fm.liveswitch;

import java.math.BigDecimal;

public class ParseAssistant {
    public static byte parseByteValue(String str) {
        return Byte.parseByte(str);
    }

    public static short parseShortValue(String str) {
        return Short.parseShort(str);
    }

    public static int parseIntegerValue(String str) {
        return Integer.parseInt(str);
    }

    public static long parseLongValue(String str) {
        return Long.parseLong(str);
    }

    public static float parseFloatValue(String str) {
        return Float.parseFloat(str);
    }

    public static double parseDoubleValue(String str) {
        return Double.parseDouble(str);
    }

    public static BigDecimal parseDecimalValue(String str) {
        return new BigDecimal(str);
    }

    public static boolean parseBooleanValue(String str) {
        return Boolean.parseBoolean(str);
    }

    public static Guid parseGuidValue(String str) {
        return new Guid(str);
    }

    public static boolean tryParseByteValue(String str, ByteHolder byteHolder) {
        try {
            byteHolder.setValue(parseByteValue(str));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryParseShortValue(String str, ShortHolder shortHolder) {
        try {
            shortHolder.setValue(parseShortValue(str));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryParseIntegerValue(String str, IntegerHolder integerHolder) {
        try {
            integerHolder.setValue(parseIntegerValue(str));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryParseLongValue(String str, LongHolder longHolder) {
        try {
            longHolder.setValue(parseLongValue(str));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryParseFloatValue(String str, FloatHolder floatHolder) {
        try {
            floatHolder.setValue(parseFloatValue(str));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryParseDoubleValue(String str, DoubleHolder doubleHolder) {
        try {
            doubleHolder.setValue(parseDoubleValue(str));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryParseDecimalValue(String str, Holder<BigDecimal> holder) {
        try {
            holder.setValue(parseDecimalValue(str));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryParseBooleanValue(String str, BooleanHolder booleanHolder) {
        try {
            booleanHolder.setValue(parseBooleanValue(str));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryParseGuidValue(String str, Holder<Guid> holder) {
        try {
            holder.setValue(parseGuidValue(str));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
