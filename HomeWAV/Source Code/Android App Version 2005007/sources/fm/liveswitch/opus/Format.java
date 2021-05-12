package fm.liveswitch.opus;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;

public class Format extends AudioFormat {
    public static int getDefaultChannelCount() {
        return 2;
    }

    public static int getDefaultClockRate() {
        return 48000;
    }

    public int getMaxBitrate() {
        return 510;
    }

    public int getMinBitrate() {
        return 6;
    }

    /* access modifiers changed from: protected */
    public AudioFormat createInstance() {
        return new Format();
    }

    public Format() {
        this(getDefaultConfig());
    }

    public Format(int i, int i2) {
        super(AudioFormat.getOpusName(), i, i2);
    }

    public Format(AudioConfig audioConfig) {
        this(audioConfig.getClockRate(), audioConfig.getChannelCount());
    }

    public static AudioConfig getDefaultConfig() {
        return new AudioConfig(getDefaultClockRate(), getDefaultChannelCount());
    }
}
