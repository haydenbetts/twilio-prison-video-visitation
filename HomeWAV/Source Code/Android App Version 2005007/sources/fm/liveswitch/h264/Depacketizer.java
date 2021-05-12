package fm.liveswitch.h264;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.RtpPacketHeader;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoDepacketizer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;

public class Depacketizer extends VideoDepacketizer<Fragment> {
    private int _packetizationMode;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "H.264 Depacketizer";
    }

    public Depacketizer() {
        super((VideoFormat) new Format(ProfileLevelId.getDefault(), PacketizationMode.getDefault()));
        initialize();
    }

    public Depacketizer(IVideoOutput iVideoOutput) {
        super(iVideoOutput);
        initialize();
    }

    public Depacketizer(int i) {
        super((VideoFormat) new Format(ProfileLevelId.getDefault(), i));
        initialize();
    }

    public Depacketizer(String str, String str2, int i) {
        super((VideoFormat) new Format(str, str2, i));
        initialize();
    }

    public Depacketizer(ProfileLevelId profileLevelId, int i) {
        super((VideoFormat) new Format(profileLevelId, i));
        initialize();
    }

    /* access modifiers changed from: protected */
    public Fragment doCreateFragment(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer) {
        return new Fragment(rtpPacketHeader, dataBuffer);
    }

    public int getPacketizationMode() {
        return this._packetizationMode;
    }

    private void initialize() {
        IntegerHolder integerHolder = new IntegerHolder(0);
        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(((VideoFormat) super.getInputFormat()).getPacketizationMode(), integerHolder);
        int value = integerHolder.getValue();
        if (!tryParseIntegerValue) {
            value = PacketizationMode.getSingleNal();
        }
        setPacketizationMode(value);
    }

    /* access modifiers changed from: protected */
    public boolean isKeyFrame(DataBuffer dataBuffer) {
        return Utility.isKeyFrame(dataBuffer);
    }

    /* access modifiers changed from: protected */
    public void raiseFrame(VideoFrame videoFrame) {
        VideoBuffer videoBuffer = (VideoBuffer) videoFrame.getLastBuffer();
        if (!(videoBuffer == null || videoBuffer.getDataBuffer() == null)) {
            videoBuffer.setDataBuffer(Utility.trimAud(videoBuffer.getDataBuffer()));
        }
        super.raiseFrame(videoFrame);
    }

    private void setPacketizationMode(int i) {
        this._packetizationMode = i;
    }
}
