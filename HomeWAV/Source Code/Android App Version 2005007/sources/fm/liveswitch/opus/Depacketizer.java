package fm.liveswitch.opus;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.BasicAudioDepacketizer;
import fm.liveswitch.IAudioOutput;

public class Depacketizer extends BasicAudioDepacketizer {
    public String getLabel() {
        return "Opus Depacketizer";
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
