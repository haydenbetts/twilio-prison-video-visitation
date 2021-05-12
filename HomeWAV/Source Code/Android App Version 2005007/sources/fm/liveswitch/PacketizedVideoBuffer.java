package fm.liveswitch;

public class PacketizedVideoBuffer extends VideoBuffer {
    /* access modifiers changed from: package-private */
    public boolean getIsPacketized() {
        return true;
    }

    /* access modifiers changed from: protected */
    public VideoBuffer createInstance() {
        return new PacketizedVideoBuffer();
    }

    private PacketizedVideoBuffer() {
    }

    public PacketizedVideoBuffer(int i, int i2, DataBuffer dataBuffer, VideoFormat videoFormat, RtpPacketHeader rtpPacketHeader) {
        super(i, i2, dataBuffer, videoFormat);
        super.setRtpHeader(rtpPacketHeader);
    }

    public PacketizedVideoBuffer(int i, int i2, DataBuffer[] dataBufferArr, VideoFormat videoFormat, RtpPacketHeader[] rtpPacketHeaderArr) {
        super(i, i2, dataBufferArr, videoFormat);
        super.setRtpHeaders(rtpPacketHeaderArr);
    }
}
