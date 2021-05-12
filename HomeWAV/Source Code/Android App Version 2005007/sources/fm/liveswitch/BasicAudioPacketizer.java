package fm.liveswitch;

public class BasicAudioPacketizer extends AudioPacketizer {
    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "Basic Audio Packetizer";
    }

    public BasicAudioPacketizer(AudioFormat audioFormat) {
        super(audioFormat);
    }

    public BasicAudioPacketizer(IAudioOutput iAudioOutput) {
        super(iAudioOutput);
    }

    public BasicAudioPacketizer(AudioFormat audioFormat, AudioFormat audioFormat2) {
        super(audioFormat, audioFormat2);
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        DataBuffer[] dataBuffers = audioBuffer.getDataBuffers();
        PacketizedAudioBuffer packetizedAudioBuffer = new PacketizedAudioBuffer(dataBuffers, (AudioFormat) audioBuffer.getFormat(), new RtpPacketHeader[ArrayExtensions.getLength((Object[]) dataBuffers)]);
        packetizedAudioBuffer.setFormat(super.getOutputFormat());
        audioFrame.addBuffer(packetizedAudioBuffer);
        raiseFrame(audioFrame);
    }
}
