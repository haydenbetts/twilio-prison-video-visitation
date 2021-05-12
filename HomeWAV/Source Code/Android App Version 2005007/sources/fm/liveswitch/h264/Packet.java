package fm.liveswitch.h264;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;

public class Packet {
    private DataBuffer __buffer;
    private boolean _fragmentEnd;
    private boolean _fragmentStart;
    private boolean _last;
    private int _naluType;
    private Nalu[] _nalus;
    private long _sequenceNumber;

    public static int getFuEBitMask() {
        return 64;
    }

    public static int getFuRBitMask() {
        return 32;
    }

    public static int getFuSBitMask() {
        return 128;
    }

    public static int getMaxPacketSize() {
        return 1150;
    }

    public static DataBuffer depacketize(Packet[] packetArr) {
        return depacketize(packetArr, 0);
    }

    public static DataBuffer depacketize(Packet[] packetArr, int i) {
        DataBuffer empty = DataBuffer.getEmpty();
        for (Packet bytes : packetArr) {
            empty.append(bytes.getBytes());
        }
        return empty;
    }

    private static Packet flushNaluAccumulator(Nalu[] naluArr) {
        if (ArrayExtensions.getLength((Object[]) naluArr) == 1) {
            return new Packet(naluArr[0]);
        }
        Packet packet = new Packet();
        packet.setNaluType(24);
        packet.setNalus(naluArr);
        return packet;
    }

    public DataBuffer getBuffer() {
        if (this.__buffer == null) {
            this.__buffer = getBytes();
        }
        return this.__buffer;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, byte], vars: [r3v0 ?, r3v14 ?, r3v19 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:51)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    private fm.liveswitch.DataBuffer getBytes() {
        /*
            r7 = this;
            int r0 = r7.getNaluType()
            r1 = 24
            r2 = 28
            r3 = 1
            r4 = 0
            if (r0 != r1) goto L_0x0032
            byte[] r0 = new byte[r3]
            fm.liveswitch.h264.Nalu[] r5 = r7.getNalus()
            r5 = r5[r4]
            boolean r5 = r5.getFBit()
            if (r5 == 0) goto L_0x001b
            goto L_0x002b
        L_0x001b:
            fm.liveswitch.h264.Nalu[] r3 = r7.getNalus()
            r3 = r3[r4]
            int r3 = r3.getNalRefIdc()
            int r5 = r7.getNaluType()
            r3 = r3 | r5
            byte r3 = (byte) r3
        L_0x002b:
            r0[r4] = r3
            fm.liveswitch.DataBuffer r0 = fm.liveswitch.DataBuffer.wrap(r0)
            goto L_0x0087
        L_0x0032:
            int r0 = r7.getNaluType()
            if (r0 != r2) goto L_0x0083
            r0 = 2
            byte[] r0 = new byte[r0]
            fm.liveswitch.h264.Nalu[] r5 = r7.getNalus()
            r5 = r5[r4]
            boolean r5 = r5.getFBit()
            if (r5 == 0) goto L_0x0049
            r5 = 1
            goto L_0x0055
        L_0x0049:
            fm.liveswitch.h264.Nalu[] r5 = r7.getNalus()
            r5 = r5[r4]
            int r5 = r5.getNalRefIdc()
            r5 = r5 | r2
            byte r5 = (byte) r5
        L_0x0055:
            r0[r4] = r5
            boolean r5 = r7.getFragmentStart()
            if (r5 == 0) goto L_0x0062
            int r5 = getFuSBitMask()
            goto L_0x0063
        L_0x0062:
            r5 = 0
        L_0x0063:
            boolean r6 = r7.getFragmentEnd()
            if (r6 == 0) goto L_0x006e
            int r6 = getFuEBitMask()
            goto L_0x006f
        L_0x006e:
            r6 = 0
        L_0x006f:
            r5 = r5 | r6
            fm.liveswitch.h264.Nalu[] r6 = r7.getNalus()
            r6 = r6[r4]
            int r6 = r6.getType()
            r5 = r5 | r6
            byte r5 = (byte) r5
            r0[r3] = r5
            fm.liveswitch.DataBuffer r0 = fm.liveswitch.DataBuffer.wrap(r0)
            goto L_0x0087
        L_0x0083:
            fm.liveswitch.DataBuffer r0 = fm.liveswitch.DataBuffer.getEmpty()
        L_0x0087:
            fm.liveswitch.h264.Nalu[] r3 = r7.getNalus()
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r3)
            if (r4 >= r3) goto L_0x00c8
            int r3 = r7.getNaluType()
            if (r3 != r1) goto L_0x00a4
            fm.liveswitch.h264.Nalu[] r3 = r7.getNalus()
            r3 = r3[r4]
            fm.liveswitch.DataBuffer r3 = r3.getShortLength()
            r0.append((fm.liveswitch.DataBuffer) r3)
        L_0x00a4:
            int r3 = r7.getNaluType()
            if (r3 != r2) goto L_0x00b8
            fm.liveswitch.h264.Nalu[] r3 = r7.getNalus()
            r3 = r3[r4]
            fm.liveswitch.DataBuffer r3 = r3.getPayload()
            r0.append((fm.liveswitch.DataBuffer) r3)
            goto L_0x00c5
        L_0x00b8:
            fm.liveswitch.h264.Nalu[] r3 = r7.getNalus()
            r3 = r3[r4]
            fm.liveswitch.DataBuffer r3 = r3.getBuffer()
            r0.append((fm.liveswitch.DataBuffer) r3)
        L_0x00c5:
            int r4 = r4 + 1
            goto L_0x0087
        L_0x00c8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.h264.Packet.getBytes():fm.liveswitch.DataBuffer");
    }

    public boolean getFragmentEnd() {
        return this._fragmentEnd;
    }

    public boolean getFragmentStart() {
        return this._fragmentStart;
    }

    public boolean getLast() {
        return this._last;
    }

    public Nalu[] getNalus() {
        return this._nalus;
    }

    public int getNaluType() {
        return this._naluType;
    }

    public long getSequenceNumber() {
        return this._sequenceNumber;
    }

    public Packet() {
        setFragmentEnd(false);
        setFragmentStart(false);
    }

    public Packet(Nalu nalu) {
        setNalus(new Nalu[]{nalu});
        setNaluType(nalu.getType());
    }

    public static Packet[] packetize(DataBuffer dataBuffer) {
        return packetize(dataBuffer, 0);
    }

    public static Packet[] packetize(DataBuffer dataBuffer, int i) {
        DataBuffer dataBuffer2 = dataBuffer;
        int i2 = 1;
        int maxPacketSize = getMaxPacketSize() - 1;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int findNextNalu = Nalu.findNextNalu(dataBuffer2, 0);
        int i3 = 0;
        int i4 = 0;
        while (true) {
            IntegerHolder integerHolder = new IntegerHolder(i3);
            Nalu nalu = Nalu.getNalu(dataBuffer2, findNextNalu, integerHolder);
            int value = integerHolder.getValue();
            if (nalu == null) {
                break;
            }
            if (nalu.getPayload().getLength() > maxPacketSize) {
                if (ArrayListExtensions.getCount(arrayList) > 0) {
                    arrayList2.add(flushNaluAccumulator((Nalu[]) arrayList.toArray(new Nalu[0])));
                    arrayList.clear();
                    i4 = 0;
                }
                if (i == 0) {
                    Log.error(StringExtensions.concat("NAL Unit too large for H.264 Single NAL packetization mode. NaluSize=", IntegerExtensions.toString(Integer.valueOf(nalu.getPayload().getLength())), ", MaxPayloadSize=", IntegerExtensions.toString(Integer.valueOf(maxPacketSize))));
                    return null;
                }
                int i5 = maxPacketSize - 1;
                boolean z = false;
                int i6 = 0;
                while (!z) {
                    if (i5 + i6 >= nalu.getPayload().getLength()) {
                        i5 = nalu.getPayload().getLength() - i6;
                        z = true;
                    }
                    DataBuffer allocate = DataBuffer.allocate(i5 + 1);
                    allocate.write8(nalu.getNalRefIdc() | nalu.getType(), 0);
                    allocate.write(nalu.getPayload().subset(i6, i5), i2);
                    Packet packet = new Packet(new Nalu(allocate));
                    packet.setNaluType(28);
                    packet.setFragmentStart(i6 == 0);
                    packet.setFragmentEnd(z);
                    arrayList2.add(packet);
                    i6 += i5;
                    i2 = 1;
                }
            } else if (nalu.getPayload().getLength() > 0) {
                if (i == 0) {
                    arrayList2.add(new Packet(nalu));
                } else {
                    if (nalu.getPayload().getLength() + i4 > maxPacketSize && ArrayListExtensions.getCount(arrayList) > 0) {
                        arrayList2.add(flushNaluAccumulator((Nalu[]) arrayList.toArray(new Nalu[0])));
                        arrayList.clear();
                        i4 = 0;
                    }
                    arrayList.add(nalu);
                    i4 += nalu.getPayload().getLength();
                }
            }
            findNextNalu += nalu.getBuffer().getLength() + value;
            if (findNextNalu >= dataBuffer.getLength()) {
                break;
            }
            i3 = value;
            i2 = 1;
        }
        if (ArrayListExtensions.getCount(arrayList) > 0) {
            arrayList2.add(flushNaluAccumulator((Nalu[]) arrayList.toArray(new Nalu[0])));
        } else if (ArrayListExtensions.getCount(arrayList2) == 0) {
            Log.error("Cannot packetize data.");
            return null;
        }
        return (Packet[]) arrayList2.toArray(new Packet[0]);
    }

    private void setFragmentEnd(boolean z) {
        this._fragmentEnd = z;
    }

    private void setFragmentStart(boolean z) {
        this._fragmentStart = z;
    }

    public void setLast(boolean z) {
        this._last = z;
    }

    public void setNalus(Nalu[] naluArr) {
        this._nalus = naluArr;
    }

    private void setNaluType(int i) {
        this._naluType = i;
    }

    public void setSequenceNumber(long j) {
        this._sequenceNumber = j;
    }

    public static Packet wrap(DataBuffer dataBuffer) {
        Packet packet = new Packet();
        ArrayList arrayList = new ArrayList();
        packet.setNaluType(NaluType.getNaluType(dataBuffer.read8(0)));
        packet.setFragmentStart(false);
        packet.setFragmentEnd(false);
        if (packet.getNaluType() == 24) {
            int i = 3;
            while (i < dataBuffer.getLength()) {
                int read16 = dataBuffer.clone(false).read16(i - 2);
                if (i + read16 > dataBuffer.getLength()) {
                    Log.debug(StringExtensions.format("H264Packet ParseBytes packetBytes STAP-A size error: nalSize={0} packetBytesLength={1} nalStart={2} sizeBytes=[{3}]", new Object[]{IntegerExtensions.toString(Integer.valueOf(read16)), IntegerExtensions.toString(Integer.valueOf(dataBuffer.getLength())), IntegerExtensions.toString(Integer.valueOf(i)), IntegerExtensions.toString(Integer.valueOf(read16))}));
                    read16 = dataBuffer.getLength() - i;
                }
                arrayList.add(new Nalu(dataBuffer.subset(i, read16)));
                i += read16 + 2;
            }
        } else if (packet.getNaluType() == 28) {
            if ((dataBuffer.read8(1) & getFuSBitMask()) != 0) {
                packet.setFragmentStart(true);
            } else if ((dataBuffer.read8(1) & getFuEBitMask()) != 0) {
                packet.setFragmentEnd(true);
            }
            DataBuffer subset = dataBuffer.subset(1);
            subset.write8((dataBuffer.read8(1) & Nalu.getTypeMask()) | (dataBuffer.read8(0) & Nalu.getFBitMask()) | (dataBuffer.read8(0) & Nalu.getNriMask()), 0);
            arrayList.add(new Nalu(subset));
        } else if (NaluType.isSingleNalu(packet.getNaluType())) {
            arrayList.add(new Nalu(dataBuffer));
        } else {
            Log.error("Unsupported H.264 NALU type.");
            return null;
        }
        packet.setNalus((Nalu[]) arrayList.toArray(new Nalu[0]));
        return packet;
    }
}
