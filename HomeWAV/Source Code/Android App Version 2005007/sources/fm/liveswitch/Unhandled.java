package fm.liveswitch;

public class Unhandled {
    public static void logException(Exception exc, String str) {
        Log.error(StringExtensions.concat("An exception in user code (", str, ") was unhandled."), exc);
    }
}
