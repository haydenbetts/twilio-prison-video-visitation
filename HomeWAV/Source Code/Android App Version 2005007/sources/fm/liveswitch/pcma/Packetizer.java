package fm.liveswitch.pcma;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.BasicAudioPacketizer;
import fm.liveswitch.IAudioOutput;
import fm.liveswitch.g711.Format;

public class Packetizer extends BasicAudioPacketizer {
    public String getLabel() {
        return "PCMA (G.711a) Packetizer";
    }

    public Packetizer() {
        this(Format.getDefaultConfig());
    }

    public Packetizer(AudioConfig audioConfig) {
        super((AudioFormat) new Format(audioConfig));
    }

    public Packetizer(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        super.addInput(iAudioOutput);
    }
}
