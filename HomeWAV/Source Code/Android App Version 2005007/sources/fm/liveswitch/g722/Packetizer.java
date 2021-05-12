package fm.liveswitch.g722;

import fm.liveswitch.AudioConfig;
import fm.liveswitch.BasicAudioPacketizer;
import fm.liveswitch.IAudioOutput;

public class Packetizer extends BasicAudioPacketizer {
    public String getLabel() {
        return "G.722 Packetizer";
    }

    public Packetizer() {
        this(new Format().getConfig());
    }

    public Packetizer(AudioConfig audioConfig) {
        super(new Format(audioConfig), new Format(8000, 1));
    }

    public Packetizer(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        super.addInput(iAudioOutput);
    }
}
