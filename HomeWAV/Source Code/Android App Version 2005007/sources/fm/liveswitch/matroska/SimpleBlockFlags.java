package fm.liveswitch.matroska;

import fm.liveswitch.BitAssistant;

public class SimpleBlockFlags extends BlockFlags {
    public static byte getDiscardable() {
        return 1;
    }

    public static byte getKeyframe() {
        return BitAssistant.castByte(128);
    }
}
