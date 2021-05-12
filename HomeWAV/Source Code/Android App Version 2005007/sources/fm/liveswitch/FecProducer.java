package fm.liveswitch;

import java.util.ArrayList;

class FecProducer {
    private FecContext __fec;
    private ArrayList<FecRawPacket> __fecPackets = new ArrayList<>();
    private boolean __incompleteFrame = false;
    private ArrayList<FecRawPacket> __mediaPackets = new ArrayList<>();
    private int __minimumMediaPackets = 1;
    private FecProtectionParameters __newParameters = new FecProtectionParameters();
    private int __numFirstPartition = 0;
    private int __numFrames = 0;
    private FecProtectionParameters __parameters = new FecProtectionParameters();

    public boolean addRtpPacketAndGenerateFec(byte[] bArr, int i, int i2) {
        if (ArrayListExtensions.getCount(this.__fecPackets) <= 0) {
            if (ArrayListExtensions.getCount(this.__mediaPackets) == 0) {
                this.__parameters = this.__newParameters;
            }
            this.__incompleteFrame = true;
            boolean z = (BitAssistant.castInteger(bArr[1]) & 128) > 0;
            if (ArrayListExtensions.getCount(this.__mediaPackets) < FecContext.getMaxMediaPackets()) {
                FecRawPacket fecRawPacket = new FecRawPacket();
                fecRawPacket.setLength(i + i2);
                BitAssistant.copy(bArr, 0, fecRawPacket.getData(), 0, fecRawPacket.getLength());
                this.__mediaPackets.add(fecRawPacket);
            }
            if (z) {
                this.__numFrames++;
                this.__incompleteFrame = false;
            }
            if (this.__incompleteFrame || (this.__numFrames != this.__parameters.getMaxFrames() && (!getExcessOverheadBelowMax() || !getMinimumMediaPacketsReached()))) {
                return true;
            }
            if (this.__numFirstPartition <= FecContext.getMaxMediaPackets()) {
                boolean generate = this.__fec.generate(this.__mediaPackets, this.__parameters.getRate(), this.__numFirstPartition, this.__parameters.getUseUepProtection(), this.__parameters.getMaskType(), this.__fecPackets);
                if (ArrayListExtensions.getCount(this.__fecPackets) == 0) {
                    this.__numFrames = 0;
                    deletePackets();
                }
                return generate;
            }
            throw new RuntimeException(new Exception("Number of packets in the first partition exceeds the maximum allowed."));
        }
        throw new RuntimeException(new Exception("FEC packets not empty."));
    }

    public FecRedPacket buildRedPacket(byte[] bArr, int i, int i2, int i3) {
        FecRedPacket fecRedPacket = new FecRedPacket(FecRedPacket.getRedForFecHeaderLength() + i + i2);
        fecRedPacket.createHeader(bArr, i2, i3, BitAssistant.castInteger(bArr[1]) & 127);
        fecRedPacket.assignPayload(bArr, i2, i);
        return fecRedPacket;
    }

    private void deletePackets() {
        this.__mediaPackets.clear();
    }

    public FecProducer(FecContext fecContext) {
        this.__fec = fecContext;
    }

    public boolean getExcessOverheadBelowMax() {
        return getOverhead() - this.__parameters.getRate() < FecRedPacket.getMaxExcessOverhead();
    }

    public boolean getFecAvailable() {
        return ArrayListExtensions.getCount(this.__fecPackets) > 0;
    }

    public FecRedPacket getFecPacket(int i, int i2, int i3, int i4) {
        if (ArrayListExtensions.getCount(this.__fecPackets) == 0) {
            return null;
        }
        FecRawPacket fecRawPacket = (FecRawPacket) ArrayListExtensions.getItem(this.__fecPackets).get(0);
        FecRedPacket fecRedPacket = new FecRedPacket(fecRawPacket.getLength() + FecRedPacket.getRedForFecHeaderLength() + i4);
        fecRedPacket.createHeader(((FecRawPacket) ArrayListExtensions.getItem(this.__mediaPackets).get(ArrayListExtensions.getCount(this.__mediaPackets) - 1)).getData(), i4, i, i2);
        fecRedPacket.setSequenceNumber(i3);
        fecRedPacket.clearMarkerBit();
        fecRedPacket.assignPayload(fecRawPacket.getData(), 0, fecRawPacket.getLength());
        ArrayListExtensions.removeAt(this.__fecPackets, 0);
        if (ArrayListExtensions.getCount(this.__fecPackets) == 0) {
            deletePackets();
            this.__numFrames = 0;
        }
        return fecRedPacket;
    }

    public boolean getMinimumMediaPacketsReached() {
        if (((float) ArrayListExtensions.getCount(this.__mediaPackets)) / ((float) this.__numFrames) < 2.0f) {
            if (ArrayListExtensions.getCount(this.__mediaPackets) >= this.__minimumMediaPackets) {
                return true;
            }
            return false;
        } else if (ArrayListExtensions.getCount(this.__mediaPackets) >= this.__minimumMediaPackets + 1) {
            return true;
        } else {
            return false;
        }
    }

    private int getOverhead() {
        if (ArrayListExtensions.getCount(this.__mediaPackets) == 0) {
            return -1;
        }
        return (this.__fec.getNumberOfFecPackets(ArrayListExtensions.getCount(this.__mediaPackets), this.__parameters.getRate()) << 8) / ArrayListExtensions.getCount(this.__mediaPackets);
    }

    public void setFecParameters(FecProtectionParameters fecProtectionParameters, int i) {
        if (i > FecContext.getMaxMediaPackets()) {
            i = FecContext.getMaxMediaPackets();
        }
        this.__newParameters = fecProtectionParameters;
        this.__numFirstPartition = i;
        if (fecProtectionParameters.getRate() > FecRedPacket.getHighProtectionThreshold()) {
            this.__minimumMediaPackets = FecRedPacket.getMinimumMediaPackets();
        } else {
            this.__minimumMediaPackets = 1;
        }
    }
}
