package fm.liveswitch.sdp.rtp;

import fm.liveswitch.StringExtensions;

public class RidRestriction {
    private String _key;
    private String _value;

    public static String getDependKey() {
        return "depend";
    }

    public static String getMaxBitrateKey() {
        return "max-br";
    }

    public static String getMaxBitsPerPixelKey() {
        return "max-bpp";
    }

    public static String getMaxFrameSizeKey() {
        return "max-fs";
    }

    public static String getMaxFramesPerSecondKey() {
        return "max-fps";
    }

    public static String getMaxHeightKey() {
        return "max-height";
    }

    public static String getMaxPixelsPerSecondKey() {
        return "max-pps";
    }

    public static String getMaxWidthKey() {
        return "max-width";
    }

    public String getKey() {
        return this._key;
    }

    public String getValue() {
        return this._value;
    }

    public RidRestriction(String str) {
        this(str, (String) null);
    }

    public RidRestriction(String str, String str2) {
        if (!StringExtensions.isNullOrEmpty(str)) {
            setKey(str);
            setValue(str2);
            return;
        }
        throw new RuntimeException(new Exception("RID restriction 'key' cannot be null."));
    }

    private void setKey(String str) {
        this._key = str;
    }

    public void setValue(String str) {
        this._value = str;
    }
}
