package fm.liveswitch.g711;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;

public abstract class Format extends AudioFormat {
    public static int getDefaultChannelCount() {
        return 1;
    }

    public static int getDefaultClockRate() {
        return 8000;
    }

    public Format(String str, int i, int i2) {
        super(str, i, i2);
        super.setIsFixedBitrate(true);
    }

    public static AudioConfig getDefaultConfig() {
        return new AudioConfig(getDefaultClockRate(), getDefaultChannelCount());
    }

    private int getFixedBitrate() {
        return ((((super.getConfig().getClockRate() * super.getConfig().getChannelCount()) * 2) * 8) / 2) / 1000;
    }

    public int getMaxBitrate() {
        return getFixedBitrate();
    }

    public int getMinBitrate() {
        return getFixedBitrate();
    }
}
