package fm.liveswitch;

import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;

class FecReceiver {
    private IAction2<FecRawPacket, Boolean> __callback;
    private FecContext __fec = new FecContext();
    private boolean __lastPacketReceivedSoloFec;
    private ManagedLock __lock = new ManagedLock();
    private int __numFecPackets;
    private int __numPackets;
    private int __numRecoveredPackets;
    private ArrayList<FecReceivedPacket> __receivedPackets = new ArrayList<>();
    private ArrayList<FecRecoveredPacket> __recoveredPackets = new ArrayList<>();

    public boolean addReceivedRedPacket(int i, int i2, byte[] bArr, int i3, int i4) {
        int i5;
        int i6;
        this.__lastPacketReceivedSoloFec = false;
        int i7 = i3 - i;
        FecReceivedPacket fecReceivedPacket = new FecReceivedPacket();
        fecReceivedPacket.setRaw(new FecRawPacket());
        byte castInteger = BitAssistant.castInteger(bArr[i]) & ByteCompanionObject.MAX_VALUE;
        fecReceivedPacket.setIsFec(castInteger == i4);
        fecReceivedPacket.setSequenceNumber(i2);
        if ((BitAssistant.castInteger(bArr[i]) & 128) > 0) {
            int i8 = i + 2;
            if (BitAssistant.rightShift((byte) (BitAssistant.leftShift(bArr[i + 1], 8) + bArr[i8]), 2) != 0) {
                Log.warn("Corrupt payload found.");
                return false;
            }
            i5 = ((BitAssistant.castInteger(bArr[i8]) & 3) << 8) + bArr[i + 3];
            if ((BitAssistant.castInteger(bArr[i + 4]) & 128) > 0) {
                throw new RuntimeException(new Exception("More than 2 blocks in packet not supported."));
            } else if (i5 <= i7 - 4) {
                i6 = 4;
            } else {
                throw new RuntimeException(new Exception("Block length longer than packet."));
            }
        } else {
            i5 = 0;
            i6 = 1;
        }
        this.__numPackets++;
        FecReceivedPacket fecReceivedPacket2 = null;
        if (i5 > 0) {
            BitAssistant.copy(bArr, 0, fecReceivedPacket.getRaw().getData(), 0, i);
            fecReceivedPacket.getRaw().getData()[1] = (byte) (fecReceivedPacket.getRaw().getData()[1] & ByteCompanionObject.MIN_VALUE);
            fecReceivedPacket.getRaw().getData()[1] = (byte) (fecReceivedPacket.getRaw().getData()[1] + castInteger);
            int i9 = i + 5;
            BitAssistant.copy(bArr, i9, fecReceivedPacket.getRaw().getData(), i, i5);
            fecReceivedPacket.getRaw().setLength(i5);
            fecReceivedPacket2 = new FecReceivedPacket();
            fecReceivedPacket2.setRaw(new FecRawPacket());
            fecReceivedPacket2.setIsFec(true);
            fecReceivedPacket2.setSequenceNumber(i2);
            this.__numFecPackets++;
            int i10 = (i7 - 5) - i5;
            BitAssistant.copy(bArr, i9 + i5, fecReceivedPacket2.getRaw().getData(), 0, i10);
            fecReceivedPacket2.getRaw().setLength(i10);
        } else if (fecReceivedPacket.getIsFec()) {
            this.__numFecPackets++;
            this.__lastPacketReceivedSoloFec = true;
            int i11 = i7 - i6;
            BitAssistant.copy(bArr, i + i6, fecReceivedPacket.getRaw().getData(), 0, i11);
            fecReceivedPacket.getRaw().setLength(i11);
            fecReceivedPacket.setSynchronizationSource(Binary.fromBytes32(bArr, 8, false));
        } else {
            BitAssistant.copy(bArr, 0, fecReceivedPacket.getRaw().getData(), 0, i);
            fecReceivedPacket.getRaw().getData()[1] = (byte) (fecReceivedPacket.getRaw().getData()[1] & ByteCompanionObject.MIN_VALUE);
            fecReceivedPacket.getRaw().getData()[1] = (byte) (fecReceivedPacket.getRaw().getData()[1] + castInteger);
            BitAssistant.copy(bArr, i + i6, fecReceivedPacket.getRaw().getData(), i, i7 - i6);
            fecReceivedPacket.getRaw().setLength((i + i7) - i6);
        }
        if (fecReceivedPacket.getRaw().getLength() != 0) {
            this.__receivedPackets.add(fecReceivedPacket);
            if (fecReceivedPacket2 != null) {
                this.__receivedPackets.add(fecReceivedPacket2);
            }
        }
        return true;
    }

    public FecReceiver(IAction2<FecRawPacket, Boolean> iAction2) {
        this.__callback = iAction2;
    }

    public boolean getLastPacketReceivedSoloFec() {
        return this.__lastPacketReceivedSoloFec;
    }

    public int getNumFecPackets() {
        return this.__numFecPackets;
    }

    public int getNumPackets() {
        return this.__numPackets;
    }

    public int getNumRecoveredPackets() {
        return this.__numRecoveredPackets;
    }

    public boolean processReceivedFec() {
        this.__lock.lock();
        if (ArrayListExtensions.getCount(this.__receivedPackets) != 0) {
            if (!((FecReceivedPacket) ArrayListExtensions.getItem(this.__receivedPackets).get(0)).getIsFec()) {
                FecRawPacket raw = ((FecReceivedPacket) ArrayListExtensions.getItem(this.__receivedPackets).get(0)).getRaw();
                this.__lock.unlock();
                this.__callback.invoke(raw, false);
                this.__lock.lock();
            }
            if (!this.__fec.decode(this.__receivedPackets, this.__recoveredPackets)) {
                this.__lock.unlock();
                return false;
            } else if (ArrayListExtensions.getCount(this.__receivedPackets) > 0) {
                throw new RuntimeException(new Exception("Received packet list must be empty."));
            }
        }
        for (int i = 0; i != ArrayListExtensions.getCount(this.__recoveredPackets); i++) {
            FecRecoveredPacket fecRecoveredPacket = (FecRecoveredPacket) ArrayListExtensions.getItem(this.__recoveredPackets).get(i);
            if (!fecRecoveredPacket.getReturned()) {
                FecRawPacket raw2 = fecRecoveredPacket.getRaw();
                this.__numRecoveredPackets++;
                this.__lock.unlock();
                this.__callback.invoke(raw2, true);
                this.__lock.lock();
                fecRecoveredPacket.setReturned(true);
            }
        }
        this.__lock.unlock();
        return true;
    }
}
