package fm.liveswitch.dtmf;

import fm.liveswitch.AudioFormat;

public class Format extends AudioFormat {
    public static int getDefaultClockRate() {
        return 8000;
    }

    public Format() {
        this(getDefaultClockRate());
    }

    public Format(int i) {
        super(AudioFormat.getDtmfName(), i, 1);
        super.setIsFixedBitrate(true);
        super.setIsInjected(true);
    }
}
