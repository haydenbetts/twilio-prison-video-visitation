package fm.liveswitch;

import java.util.ArrayList;

class RtcpPacket {
    private DataBuffer _buffer;

    public DataBuffer getBuffer() {
        return this._buffer;
    }

    public int getByte1Last5Bits() {
        return getBuffer().read5(0, 3);
    }

    public int getLength() {
        return getPayloadLengthWithPadding() + 4;
    }

    public boolean getPadding() {
        return getBuffer().read1(0, 2);
    }

    public int getPaddingLength() {
        if (getPadding()) {
            return getBuffer().read8(getBuffer().getLength() - 1);
        }
        return 0;
    }

    public DataBuffer getPayload() {
        return getBuffer().subset(4, getPayloadLength());
    }

    public int getPayloadLength() {
        return getPayloadLengthWithPadding() - getPaddingLength();
    }

    public int getPayloadLengthWithPadding() {
        return getBuffer().read16(2) * 4;
    }

    public int getPayloadType() {
        return getBuffer().read8(1);
    }

    public int getVersion() {
        return getBuffer().read2(0, 0);
    }

    public static int nextLength(DataBuffer dataBuffer, int i) {
        if (i + 4 > dataBuffer.getLength()) {
            return -1;
        }
        return (dataBuffer.read16(i + 2) * 4) + 4;
    }

    public static RtcpPacket[] parse(DataBuffer dataBuffer) {
        if (dataBuffer == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < dataBuffer.getLength()) {
            int nextLength = nextLength(dataBuffer, i);
            if (nextLength > dataBuffer.getLength() - i) {
                Log.error(StringExtensions.concat("Malformed RTCP packet detected. Length indicates ", IntegerExtensions.toString(Integer.valueOf(nextLength)), " but the buffer is only ", IntegerExtensions.toString(Integer.valueOf(dataBuffer.getLength() - i))));
                return null;
            }
            arrayList.add(new RtcpPacket(dataBuffer.subset(i, nextLength)));
            i += nextLength;
        }
        return (RtcpPacket[]) arrayList.toArray(new RtcpPacket[0]);
    }

    public RtcpPacket() {
        this(DataBuffer.allocate(4));
    }

    public RtcpPacket(DataBuffer dataBuffer) {
        setBuffer(dataBuffer);
        if (dataBuffer.getLength() >= 4) {
            setVersion(2);
            if (getPayloadLengthWithPadding() == 0 && dataBuffer.getLength() > 4) {
                setPayloadLengthWithPadding(dataBuffer.getLength() - 4);
                setPaddingLength(0);
                return;
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.concat("Invalid RTCP databuffer. Length must be at least ", IntegerExtensions.toString(4), " bytes.")));
    }

    private void setBuffer(DataBuffer dataBuffer) {
        this._buffer = dataBuffer;
    }

    public void setByte1Last5Bits(int i) {
        getBuffer().write5(i, 0, 3);
    }

    public void setPadding(boolean z) {
        getBuffer().write1(z, 0, 2);
    }

    private void setPaddingLength(int i) {
        setPadding(i > 0);
        int i2 = 0;
        while (i2 < i) {
            getBuffer().write8(BitAssistant.castByte(i2 < i + -1 ? 0 : i), (getBuffer().getLength() - i) + i2);
            i2++;
        }
    }

    public void setPayload(DataBuffer dataBuffer) {
        if (dataBuffer.getLength() <= getPayloadLength()) {
            getBuffer().write(dataBuffer, 4);
            return;
        }
        throw new RuntimeException(new Exception("Cannot set RTCP Payload as the value length is longer than the payload length"));
    }

    public void setPayloadLengthWithPadding(int i) {
        getBuffer().write16(i / 4, 2);
    }

    public void setPayloadType(int i) {
        getBuffer().write8(i, 1);
    }

    public void setVersion(int i) {
        getBuffer().write2(i, 0, 0);
    }

    public static void test() {
        if (new RtcpPacket().getLength() == 4) {
            DataBuffer allocate = DataBuffer.allocate(12);
            allocate.write2(1, 0, 0);
            allocate.write5(2, 0, 3);
            allocate.write8(202, 1);
            allocate.write16(3, 2);
            allocate.write32(4294967295L, 4);
            RtcpPacket rtcpPacket = new RtcpPacket(allocate);
            if (rtcpPacket.getVersion() != 2) {
                throw new RuntimeException(new Exception("RTCP Version test failed."));
            } else if (rtcpPacket.getPadding()) {
                throw new RuntimeException(new Exception("RTCP Padding test failed."));
            } else if (rtcpPacket.getByte1Last5Bits() != 2) {
                throw new RuntimeException(new Exception("RTCP Byte1Last5Bits test failed."));
            } else if (rtcpPacket.getPayloadType() != 202) {
                throw new RuntimeException(new Exception("RTCP PayloadType test failed."));
            } else if (rtcpPacket.getPayloadLengthWithPadding() == 8) {
                rtcpPacket.setPaddingLength(5);
                if (rtcpPacket.getPaddingLength() != 5 || !rtcpPacket.getPadding() || rtcpPacket.getBuffer().read32(4) != 4294967040L) {
                    throw new RuntimeException(new Exception("RTCP PaddingLength test failed."));
                }
            } else {
                throw new RuntimeException(new Exception("RTCP PayloadLengthWithPadding test failed."));
            }
        } else {
            throw new RuntimeException(new Exception("Empty constructor test failed."));
        }
    }
}
