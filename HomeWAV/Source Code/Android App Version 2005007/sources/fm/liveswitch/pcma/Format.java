package fm.liveswitch.pcma;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;

public class Format extends fm.liveswitch.g711.Format {
    /* access modifiers changed from: protected */
    public AudioFormat createInstance() {
        return new Format();
    }

    public Format() {
        this(fm.liveswitch.g711.Format.getDefaultConfig());
    }

    public Format(int i, int i2) {
        super(AudioFormat.getPcmaName(), i, i2);
        super.setStaticPayloadType(8);
    }

    public Format(AudioConfig audioConfig) {
        this(audioConfig.getClockRate(), audioConfig.getChannelCount());
    }
}
