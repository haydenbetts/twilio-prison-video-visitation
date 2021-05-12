package fm.liveswitch.g722;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;

public class Format extends AudioFormat {
    public static int getDefaultChannelCount() {
        return 1;
    }

    public static int getDefaultClockRate() {
        return 16000;
    }

    private int getFixedBitrate() {
        return 64;
    }

    /* access modifiers changed from: protected */
    public AudioFormat createInstance() {
        return new Format();
    }

    public Format() {
        this(getDefaultConfig());
    }

    public Format(int i, int i2) {
        super(AudioFormat.getG722Name(), i, i2);
        super.setIsFixedBitrate(true);
        super.setStaticPayloadType(9);
    }

    public Format(AudioConfig audioConfig) {
        this(audioConfig.getClockRate(), audioConfig.getChannelCount());
    }

    public static AudioConfig getDefaultConfig() {
        return new AudioConfig(getDefaultClockRate(), getDefaultChannelCount());
    }

    public int getMaxBitrate() {
        return getFixedBitrate();
    }

    public int getMinBitrate() {
        return getFixedBitrate();
    }
}
