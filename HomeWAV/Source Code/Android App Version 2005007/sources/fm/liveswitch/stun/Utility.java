package fm.liveswitch.stun;

import fm.liveswitch.HashContextBase;
import fm.liveswitch.HashType;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.Utf8;

public abstract class Utility {
    public static byte[] createLongTermKey(String str, String str2, String str3) {
        return HashContextBase.compute(HashType.Md5, StringExtensions.format("{0}:{1}:{2}", str, str2, str3)).toArray();
    }

    public static byte[] createShortTermKey(String str) {
        return Utf8.encode(str);
    }
}
