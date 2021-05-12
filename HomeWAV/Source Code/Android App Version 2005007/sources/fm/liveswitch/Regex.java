package fm.liveswitch;

import java.util.regex.Pattern;

public class Regex {
    private String _pattern;

    public Regex(String str) {
        this._pattern = str;
    }

    public static boolean isMatch(String str, String str2) {
        return Pattern.compile(str2).matcher(str).matches();
    }

    public boolean isMatch(String str) {
        return Pattern.compile(this._pattern).matcher(str).matches();
    }
}
