package fm.liveswitch;

import java.util.ArrayList;

public class MediaControlFrame extends Dynamic {
    private DataBuffer __dataBuffer = null;
    private RtcpPacket _packet;
    private String _streamId;

    public static int getFixedHeaderLength() {
        return 4;
    }

    public static MediaControlFrame[] addControlFrame(MediaControlFrame[] mediaControlFrameArr, MediaControlFrame mediaControlFrame, int i) {
        return (MediaControlFrame[]) Utility.splice((T[]) mediaControlFrameArr, i, (T[]) new MediaControlFrame[]{mediaControlFrame}, (IFunction1<Integer, T[]>) new IFunctionDelegate1<Integer, MediaControlFrame[]>() {
            public String getId() {
                return "fm.liveswitch.MediaControlFrame.createControlFrameArray";
            }

            public MediaControlFrame[] invoke(Integer num) {
                return MediaControlFrame.createControlFrameArray(num.intValue());
            }
        });
    }

    public static MediaControlFrame[] addControlFrames(MediaControlFrame[] mediaControlFrameArr, MediaControlFrame[] mediaControlFrameArr2, int i) {
        return (MediaControlFrame[]) Utility.splice((T[]) mediaControlFrameArr, i, (T[]) mediaControlFrameArr2, (IFunction1<Integer, T[]>) new IFunctionDelegate1<Integer, MediaControlFrame[]>() {
            public String getId() {
                return "fm.liveswitch.MediaControlFrame.createControlFrameArray";
            }

            public MediaControlFrame[] invoke(Integer num) {
                return MediaControlFrame.createControlFrameArray(num.intValue());
            }
        });
    }

    /* access modifiers changed from: private */
    public static MediaControlFrame[] createControlFrameArray(int i) {
        return new MediaControlFrame[i];
    }

    public int getByte1Last5Bits() {
        return this._packet.getByte1Last5Bits();
    }

    public DataBuffer getDataBuffer() {
        return this.__dataBuffer;
    }

    public DataBuffer getPayload() {
        return this._packet.getPayload();
    }

    public int getPayloadLengthWithPadding() {
        return this._packet.getPayloadLengthWithPadding();
    }

    public int getPayloadType() {
        return this._packet.getPayloadType();
    }

    public String getStreamId() {
        return this._streamId;
    }

    public int getVersion() {
        return this._packet.getVersion();
    }

    private void initialize(DataBuffer dataBuffer) {
        if (dataBuffer == null) {
            dataBuffer = DataBuffer.allocate(getFixedHeaderLength());
        }
        if (dataBuffer.getLength() >= getFixedHeaderLength()) {
            setDataBuffer(dataBuffer);
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.concat("DataBuffer must be at least ", IntegerExtensions.toString(Integer.valueOf(getFixedHeaderLength())), " bytes long.")));
    }

    public MediaControlFrame() {
        initialize((DataBuffer) null);
    }

    public MediaControlFrame(DataBuffer dataBuffer) {
        initialize(dataBuffer);
    }

    public static MediaControlFrame[] parse(DataBuffer dataBuffer) {
        if (dataBuffer == null) {
            return null;
        }
        return parse(RtcpPacket.parse(dataBuffer));
    }

    static MediaControlFrame parse(RtcpPacket rtcpPacket) {
        if (rtcpPacket == null) {
            return null;
        }
        int payloadType = rtcpPacket.getPayloadType();
        if (payloadType == SRControlFrame.getRegisteredPayloadType()) {
            return new SRControlFrame(rtcpPacket.getBuffer());
        }
        if (payloadType == RRControlFrame.getRegisteredPayloadType()) {
            return new RRControlFrame(rtcpPacket.getBuffer());
        }
        if (payloadType == SdesControlFrame.getRegisteredPayloadType()) {
            return new SdesControlFrame(rtcpPacket.getBuffer());
        }
        if (payloadType == ByeControlFrame.getRegisteredPayloadType()) {
            return new ByeControlFrame(rtcpPacket.getBuffer());
        }
        if (payloadType == AppControlFrame.getRegisteredPayloadType()) {
            return new AppControlFrame(rtcpPacket.getBuffer());
        }
        if (payloadType == RtpControlFrame.getRegisteredPayloadType()) {
            int byte1Last5Bits = rtcpPacket.getByte1Last5Bits();
            if (byte1Last5Bits == GenericNackControlFrame.getRegisteredFeedbackMessageType()) {
                return new GenericNackControlFrame(rtcpPacket.getBuffer());
            }
            if (byte1Last5Bits == TmmbrControlFrame.getRegisteredFeedbackMessageType()) {
                return new TmmbrControlFrame(rtcpPacket.getBuffer());
            }
            if (byte1Last5Bits == TmmbnControlFrame.getRegisteredFeedbackMessageType()) {
                return new TmmbnControlFrame(rtcpPacket.getBuffer());
            }
            return new MediaControlFrame(rtcpPacket.getBuffer());
        } else if (payloadType != PayloadSpecificControlFrame.getRegisteredPayloadType()) {
            return new MediaControlFrame(rtcpPacket.getBuffer());
        } else {
            int byte1Last5Bits2 = rtcpPacket.getByte1Last5Bits();
            if (byte1Last5Bits2 == PliControlFrame.getRegisteredFeedbackMessageType()) {
                return new PliControlFrame(rtcpPacket.getBuffer());
            }
            if (byte1Last5Bits2 == FirControlFrame.getRegisteredFeedbackMessageType()) {
                return new FirControlFrame(rtcpPacket.getBuffer());
            }
            if (byte1Last5Bits2 == LrrControlFrame.getRegisteredFeedbackMessageType()) {
                return new LrrControlFrame(rtcpPacket.getBuffer());
            }
            if (byte1Last5Bits2 == SliControlFrame.getRegisteredFedbackMessageType()) {
                return new SliControlFrame(rtcpPacket.getBuffer());
            }
            if (byte1Last5Bits2 == RembControlFrame.getRegisteredFeedbackMessageType()) {
                return new RembControlFrame(rtcpPacket.getBuffer());
            }
            return new MediaControlFrame(rtcpPacket.getBuffer());
        }
    }

    static MediaControlFrame[] parse(RtcpPacket[] rtcpPacketArr) {
        if (rtcpPacketArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (RtcpPacket parse : rtcpPacketArr) {
            arrayList.add(parse(parse));
        }
        return (MediaControlFrame[]) arrayList.toArray(new MediaControlFrame[0]);
    }

    public static MediaControlFrame[] removeControlFrame(MediaControlFrame[] mediaControlFrameArr, int i) {
        return (MediaControlFrame[]) Utility.splice((T[]) mediaControlFrameArr, i, 1, (IFunction1<Integer, T[]>) new IFunctionDelegate1<Integer, MediaControlFrame[]>() {
            public String getId() {
                return "fm.liveswitch.MediaControlFrame.createControlFrameArray";
            }

            public MediaControlFrame[] invoke(Integer num) {
                return MediaControlFrame.createControlFrameArray(num.intValue());
            }
        });
    }

    public void setByte1Last5Bits(int i) {
        this._packet.setByte1Last5Bits(i);
    }

    /* access modifiers changed from: protected */
    public void setDataBuffer(DataBuffer dataBuffer) {
        this.__dataBuffer = dataBuffer;
        this._packet = new RtcpPacket(dataBuffer);
    }

    private void setPayload(DataBuffer dataBuffer) {
        this._packet.setPayload(dataBuffer);
    }

    /* access modifiers changed from: protected */
    public void setPayloadLengthWithPadding(int i) {
        this._packet.setPayloadLengthWithPadding(i);
    }

    public void setPayloadType(int i) {
        this._packet.setPayloadType(i);
    }

    /* access modifiers changed from: package-private */
    public void setStreamId(String str) {
        this._streamId = str;
    }

    public void setVersion(int i) {
        this._packet.setVersion(i);
    }
}
