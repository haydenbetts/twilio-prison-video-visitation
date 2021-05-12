package fm.liveswitch;

public class PacketizedAudioBuffer extends AudioBuffer {
    /* access modifiers changed from: package-private */
    public boolean getIsPacketized() {
        return true;
    }

    /* access modifiers changed from: protected */
    public AudioBuffer createInstance() {
        return new PacketizedAudioBuffer();
    }

    private PacketizedAudioBuffer() {
    }

    public PacketizedAudioBuffer(DataBuffer dataBuffer, AudioFormat audioFormat, RtpPacketHeader rtpPacketHeader) {
        super(dataBuffer, audioFormat);
        super.setRtpHeader(rtpPacketHeader);
    }

    public PacketizedAudioBuffer(DataBuffer[] dataBufferArr, AudioFormat audioFormat, RtpPacketHeader[] rtpPacketHeaderArr) {
        super(dataBufferArr, audioFormat);
        super.setRtpHeaders(rtpPacketHeaderArr);
    }
}
