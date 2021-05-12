package fm.liveswitch.pcm;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;

public class Format extends AudioFormat {
    public Format(int i, int i2) {
        super(AudioFormat.getPcmName(), i, i2);
        super.setIsFixedBitrate(true);
        super.setLittleEndian(true);
    }

    public Format(AudioConfig audioConfig) {
        this(audioConfig.getClockRate(), audioConfig.getChannelCount());
    }
}
