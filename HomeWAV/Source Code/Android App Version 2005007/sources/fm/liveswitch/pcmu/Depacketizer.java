package fm.liveswitch.pcmu;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.BasicAudioDepacketizer;
import fm.liveswitch.IAudioOutput;
import fm.liveswitch.g711.Format;

public class Depacketizer extends BasicAudioDepacketizer {
    public String getLabel() {
        return "PCMU (G.711u) Depacketizer";
    }

    public Depacketizer() {
        this(Format.getDefaultConfig());
    }

    public Depacketizer(AudioConfig audioConfig) {
        super((AudioFormat) new Format(audioConfig));
    }

    public Depacketizer(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        super.addInput(iAudioOutput);
    }
}
