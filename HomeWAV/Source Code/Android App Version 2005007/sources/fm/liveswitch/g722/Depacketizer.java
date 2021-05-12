package fm.liveswitch.g722;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.BasicAudioDepacketizer;
import fm.liveswitch.IAudioOutput;

public class Depacketizer extends BasicAudioDepacketizer {
    public String getLabel() {
        return "G.722 Depacketizer";
    }

    public Depacketizer() {
        this(Format.getDefaultConfig());
    }

    public Depacketizer(AudioConfig audioConfig) {
        super(new Format(8000, 1), new Format(audioConfig));
    }

    public Depacketizer(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        super.addInput(iAudioOutput);
    }
}
