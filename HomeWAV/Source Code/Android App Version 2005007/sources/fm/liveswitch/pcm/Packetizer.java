package fm.liveswitch.pcm;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.BasicAudioPacketizer;
import fm.liveswitch.IAudioOutput;

public class Packetizer extends BasicAudioPacketizer {
    public Packetizer(AudioConfig audioConfig) {
        super((AudioFormat) new Format(audioConfig));
    }

    public Packetizer(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        super.addInput(iAudioOutput);
    }
}
