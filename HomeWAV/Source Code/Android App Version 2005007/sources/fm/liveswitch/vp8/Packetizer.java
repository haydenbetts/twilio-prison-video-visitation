package fm.liveswitch.vp8;

import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.MathAssistant;
import fm.liveswitch.PacketizedVideoBuffer;
import fm.liveswitch.RtpPacketHeader;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import fm.liveswitch.VideoPacketizer;
import java.util.ArrayList;

public class Packetizer extends VideoPacketizer {
    public static int getHeaderPadding() {
        return 20;
    }

    public static int getMaxPacketSize() {
        return SctpTransmissionControlBlock.MaxDataPacketPayloadSize;
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "VP8 Packetizer";
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        VideoBuffer packetize = packetize(videoBuffer.getDataBuffer(), videoBuffer.getWidth(), videoBuffer.getHeight());
        if (packetize != null) {
            videoFrame.addBuffer(packetize);
            raiseFrame(videoFrame);
        }
    }

    private VideoBuffer packetize(DataBuffer dataBuffer, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        int ceil = (int) MathAssistant.ceil(((double) dataBuffer.getLength()) / ((double) (getMaxPacketSize() - 1)));
        if (ceil == 0) {
            ceil = 1;
        }
        if (ceil == 1) {
            arrayList.add(new Packet(dataBuffer, true));
        } else {
            int length = dataBuffer.getLength() / ceil;
            int length2 = dataBuffer.getLength() - (ceil * length);
            int i3 = 0;
            int i4 = 0;
            while (i3 < ceil) {
                int i5 = i3 < length2 ? length + 1 : length;
                arrayList.add(new Packet(dataBuffer.subset(i4, i5), i3 == 0));
                i4 += i5;
                i3++;
            }
        }
        DataBuffer[] dataBufferArr = new DataBuffer[ArrayListExtensions.getCount(arrayList)];
        RtpPacketHeader[] rtpPacketHeaderArr = new RtpPacketHeader[ArrayListExtensions.getCount(arrayList)];
        int i6 = 0;
        while (i6 < ArrayListExtensions.getCount(arrayList)) {
            dataBufferArr[i6] = ((Packet) ArrayListExtensions.getItem(arrayList).get(i6)).getBuffer();
            RtpPacketHeader rtpPacketHeader = new RtpPacketHeader();
            rtpPacketHeader.setMarker(i6 == ArrayListExtensions.getCount(arrayList) - 1);
            rtpPacketHeaderArr[i6] = rtpPacketHeader;
            i6++;
        }
        return new PacketizedVideoBuffer(i, i2, dataBufferArr, (VideoFormat) super.getOutputFormat(), rtpPacketHeaderArr);
    }

    public Packetizer() {
        super((VideoFormat) new Format());
    }

    public Packetizer(IVideoOutput iVideoOutput) {
        this();
        super.addInput(iVideoOutput);
    }
}
