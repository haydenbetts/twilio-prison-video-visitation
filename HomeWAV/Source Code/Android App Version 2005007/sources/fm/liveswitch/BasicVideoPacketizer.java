package fm.liveswitch;

public class BasicVideoPacketizer extends VideoPacketizer {
    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "Basic Video Packetizer";
    }

    public BasicVideoPacketizer(VideoFormat videoFormat) {
        super(videoFormat);
    }

    public BasicVideoPacketizer(IVideoOutput iVideoOutput) {
        super(iVideoOutput);
    }

    public BasicVideoPacketizer(VideoFormat videoFormat, VideoFormat videoFormat2) {
        super(videoFormat, videoFormat2);
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        DataBuffer[] dataBuffers = videoBuffer.getDataBuffers();
        PacketizedVideoBuffer packetizedVideoBuffer = new PacketizedVideoBuffer(videoBuffer.getWidth(), videoBuffer.getHeight(), dataBuffers, (VideoFormat) videoBuffer.getFormat(), new RtpPacketHeader[ArrayExtensions.getLength((Object[]) dataBuffers)]);
        packetizedVideoBuffer.setFormat(super.getOutputFormat());
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) packetizedVideoBuffer.getRtpHeaders()); i++) {
            RtpPacketHeader rtpPacketHeader = new RtpPacketHeader();
            rtpPacketHeader.setMarker(true);
            packetizedVideoBuffer.getRtpHeaders()[i] = rtpPacketHeader;
        }
        videoFrame.addBuffer(packetizedVideoBuffer);
        raiseFrame(videoFrame);
    }
}
