package fm.liveswitch.h264;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.PacketizedVideoBuffer;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.RtpPacketHeader;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import fm.liveswitch.VideoPacketizer;

public class Packetizer extends VideoPacketizer {
    private int _packetizationMode;

    public static int getMaxPacketSize() {
        return SctpTransmissionControlBlock.MaxDataPacketPayloadSize;
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "H.264 Packetizer";
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        VideoBuffer packetize = packetize(Utility.trimAud(videoBuffer.getDataBuffer()), videoBuffer.getWidth(), videoBuffer.getHeight());
        if (packetize != null) {
            videoFrame.addBuffer(packetize);
            raiseFrame(videoFrame);
        }
    }

    public int getPacketizationMode() {
        return this._packetizationMode;
    }

    private void initialize() {
        IntegerHolder integerHolder = new IntegerHolder(0);
        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(((VideoFormat) super.getOutputFormat()).getPacketizationMode(), integerHolder);
        int value = integerHolder.getValue();
        if (!tryParseIntegerValue) {
            value = PacketizationMode.getSingleNal();
        }
        setPacketizationMode(value);
    }

    private VideoBuffer packetize(DataBuffer dataBuffer, int i, int i2) {
        Packet[] packetize = Packet.packetize(dataBuffer, getPacketizationMode());
        if (packetize == null) {
            return null;
        }
        DataBuffer[] dataBufferArr = new DataBuffer[ArrayExtensions.getLength((Object[]) packetize)];
        RtpPacketHeader[] rtpPacketHeaderArr = new RtpPacketHeader[ArrayExtensions.getLength((Object[]) packetize)];
        for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) packetize); i3++) {
            dataBufferArr[i3] = packetize[i3].getBuffer();
            RtpPacketHeader rtpPacketHeader = new RtpPacketHeader();
            boolean z = true;
            if (i3 != ArrayExtensions.getLength((Object[]) packetize) - 1) {
                z = false;
            }
            rtpPacketHeader.setMarker(z);
            rtpPacketHeaderArr[i3] = rtpPacketHeader;
        }
        return new PacketizedVideoBuffer(i, i2, dataBufferArr, (VideoFormat) super.getOutputFormat(), rtpPacketHeaderArr);
    }

    public Packetizer() {
        super((VideoFormat) new Format(ProfileLevelId.getDefault(), PacketizationMode.getDefault()));
        initialize();
    }

    public Packetizer(IVideoOutput iVideoOutput) {
        super(iVideoOutput);
        initialize();
    }

    public Packetizer(int i) {
        super((VideoFormat) new Format(ProfileLevelId.getDefault(), i));
        initialize();
    }

    public Packetizer(String str, String str2, int i) {
        super((VideoFormat) new Format(str, str2, i));
        initialize();
    }

    public Packetizer(ProfileLevelId profileLevelId, int i) {
        super((VideoFormat) new Format(profileLevelId, i));
        initialize();
    }

    private void setPacketizationMode(int i) {
        this._packetizationMode = i;
    }
}
