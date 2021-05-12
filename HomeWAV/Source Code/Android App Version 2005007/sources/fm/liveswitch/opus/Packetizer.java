package fm.liveswitch.opus;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.BasicAudioPacketizer;
import fm.liveswitch.IAudioOutput;

public class Packetizer extends BasicAudioPacketizer {
    public String getLabel() {
        return "Opus Packetizer";
    }

    public Packetizer() {
        this(Format.getDefaultConfig());
    }

    public Packetizer(AudioConfig audioConfig) {
        super(new Format(audioConfig), new Format(48000, 2));
    }

    public Packetizer(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        super.addInput(iAudioOutput);
    }
}
