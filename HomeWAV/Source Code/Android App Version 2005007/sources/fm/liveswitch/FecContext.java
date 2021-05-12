package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.ByteCompanionObject;

class FecContext {
    private static ILog __log = Log.getLogger(FecContext.class);
    private ArrayList<FecRawPacket> _generatedPackets = new ArrayList<>(48);
    private ArrayList<FecPacket> _packetList = new ArrayList<>();
    private boolean _packetReceived;

    private static int constrain16(int i) {
        while (i < 0) {
            i += 65536;
        }
        while (i > 65535) {
            i -= 65536;
        }
        return i;
    }

    public static int getIPPacketSize() {
        return 1500;
    }

    public static int getMaxMediaPackets() {
        return 48;
    }

    public static int getPacketOverhead() {
        return 18;
    }

    private int setProtectionAllocation(int i, int i2, int i3) {
        int i4 = (int) (((double) i2) * 0.5d);
        if (i3 < i4) {
            i4 = i3;
        }
        if (i2 != 1 || i <= i3 * 2) {
            return i4;
        }
        return 0;
    }

    private static void assignRecoveredPackets(FecPacket fecPacket, ArrayList<FecRecoveredPacket> arrayList) {
        ArrayList<FecProtectedPacket> protectedPacketList = fecPacket.getProtectedPacketList();
        ArrayList arrayList2 = new ArrayList();
        setIntersection(arrayList, 0, ArrayListExtensions.getCount(arrayList), protectedPacketList, 0, ArrayListExtensions.getCount(protectedPacketList), arrayList2, new IFunctionDelegate2<FecSortablePacket, FecSortablePacket, CompareResult>() {
            public String getId() {
                return "fm.liveswitch.FecContext.sortablePacketLessThan";
            }

            public CompareResult invoke(FecSortablePacket fecSortablePacket, FecSortablePacket fecSortablePacket2) {
                return FecContext.sortablePacketLessThan(fecSortablePacket, fecSortablePacket2);
            }
        });
        int i = 0;
        for (int i2 = 0; i2 != ArrayListExtensions.getCount(arrayList2); i2++) {
            while (true) {
                if (((FecProtectedPacket) ArrayListExtensions.getItem(protectedPacketList).get(i)).getSequenceNumber() != ((FecRecoveredPacket) ArrayListExtensions.getItem(arrayList2).get(i2)).getSequenceNumber()) {
                    i++;
                    if (i >= ArrayListExtensions.getCount(protectedPacketList)) {
                        Log.error("Could not find unrecovered packet in FEC context.");
                        break;
                    }
                } else {
                    break;
                }
            }
            if (i < ArrayListExtensions.getCount(protectedPacketList)) {
                ((FecProtectedPacket) ArrayListExtensions.getItem(protectedPacketList).get(i)).setRaw(((FecRecoveredPacket) ArrayListExtensions.getItem(arrayList2).get(i2)).getRaw());
            }
        }
    }

    private void attemptRecover(ArrayList<FecRecoveredPacket> arrayList) {
        while (true) {
            int i = 0;
            while (i != ArrayListExtensions.getCount(this._packetList)) {
                int numCoveredPacketsMissing = numCoveredPacketsMissing((FecPacket) ArrayListExtensions.getItem(this._packetList).get(i));
                if (numCoveredPacketsMissing == 1) {
                    FecRecoveredPacket fecRecoveredPacket = new FecRecoveredPacket();
                    fecRecoveredPacket.setRaw((FecRawPacket) null);
                    recoverPacket((FecPacket) ArrayListExtensions.getItem(this._packetList).get(i), fecRecoveredPacket);
                    arrayList.add(fecRecoveredPacket);
                    Sort.quickSort(arrayList, new IFunctionDelegate2<FecRecoveredPacket, FecRecoveredPacket, CompareResult>() {
                        public String getId() {
                            return "fm.liveswitch.FecContext.recoveredPacketLessThan";
                        }

                        public CompareResult invoke(FecRecoveredPacket fecRecoveredPacket, FecRecoveredPacket fecRecoveredPacket2) {
                            return FecContext.recoveredPacketLessThan(fecRecoveredPacket, fecRecoveredPacket2);
                        }
                    });
                    updateCoveringFECPackets(fecRecoveredPacket);
                    discardOldPackets(arrayList);
                    discardFECPacket((FecPacket) ArrayListExtensions.getItem(this._packetList).get(i));
                    ArrayListExtensions.removeAt(this._packetList, i);
                } else if (numCoveredPacketsMissing == 0) {
                    discardFECPacket((FecPacket) ArrayListExtensions.getItem(this._packetList).get(i));
                    ArrayListExtensions.removeAt(this._packetList, i);
                } else {
                    i++;
                }
            }
            return;
        }
    }

    private static void blockCopy(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        BitAssistant.copy(bArr2, i2, bArr, i, i3);
    }

    private static void blockCopy(byte[] bArr, byte[] bArr2, int i) {
        blockCopy(bArr, 0, bArr2, 0, i);
    }

    private static void copyColumn(byte[] bArr, int i, byte[] bArr2, int i2, int i3, int i4, int i5) {
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = (i6 * i) + (i4 / 8);
            int i8 = (i6 * i2) + (i5 / 8);
            bArr[i7] = (byte) (bArr[i7] | ((byte) ((BitAssistant.castInteger(bArr2[i8]) & 128) >> 7)));
            if (i4 % 8 != 7) {
                bArr[i7] = BitAssistant.leftShift(bArr[i7], 1);
            }
            bArr2[i8] = BitAssistant.leftShift(bArr2[i8], 1);
        }
    }

    public boolean decode(ArrayList<FecReceivedPacket> arrayList, ArrayList<FecRecoveredPacket> arrayList2) {
        if (ArrayListExtensions.getCount(arrayList2) == 48 && MathAssistant.abs(((FecReceivedPacket) ArrayListExtensions.getItem(arrayList).get(0)).getSequenceNumber() - ((FecRecoveredPacket) ArrayListExtensions.getItem(arrayList2).get(ArrayListExtensions.getCount(arrayList2) - 1)).getSequenceNumber()) > 48) {
            resetState(arrayList2);
        }
        insertPackets(arrayList, arrayList2);
        attemptRecover(arrayList2);
        return true;
    }

    private static void discardFECPacket(FecPacket fecPacket) {
        boolean z;
        while (true) {
            z = false;
            if (ArrayListExtensions.getCount(fecPacket.getProtectedPacketList()) == 0) {
                break;
            }
            ArrayListExtensions.removeAt(fecPacket.getProtectedPacketList(), 0);
        }
        if (ArrayListExtensions.getCount(fecPacket.getProtectedPacketList()) == 0) {
            z = true;
        }
        verify(z);
    }

    private static void discardOldPackets(ArrayList<FecRecoveredPacket> arrayList) {
        boolean z;
        while (true) {
            z = false;
            if (ArrayListExtensions.getCount(arrayList) <= 48) {
                break;
            }
            ArrayListExtensions.removeAt(arrayList, 0);
        }
        if (ArrayListExtensions.getCount(arrayList) <= 48) {
            z = true;
        }
        verify(z);
    }

    public FecContext() {
        for (int i = 0; i < 48; i++) {
            this._generatedPackets.add(new FecRawPacket());
        }
        setPacketReceived(false);
    }

    private static void finishRecovery(FecRecoveredPacket fecRecoveredPacket) {
        fecRecoveredPacket.getRaw().getData()[0] = (byte) (fecRecoveredPacket.getRaw().getData()[0] | ByteCompanionObject.MIN_VALUE);
        fecRecoveredPacket.getRaw().getData()[0] = (byte) (fecRecoveredPacket.getRaw().getData()[0] & 191);
        Binary.toBytes16(fecRecoveredPacket.getSequenceNumber(), false, fecRecoveredPacket.getRaw().getData(), 2);
        fecRecoveredPacket.getRaw().setLength(Binary.fromBytes16(fecRecoveredPacket.getLengthRecovery(), 0, false) + 12);
    }

    private void fitSubMask(int i, int i2, int i3, byte[] bArr, byte[] bArr2) {
        fitSubMask(i, i2, i3, bArr, bArr2, 0);
    }

    private void fitSubMask(int i, int i2, int i3, byte[] bArr, byte[] bArr2, int i4) {
        if (i == i2) {
            blockCopy(bArr2, i4, bArr, 0, i3 * i2);
            return;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = i5 * i;
            int i7 = i5 * i2;
            for (int i8 = 0; i8 < i2; i8++) {
                bArr2[i4 + i6] = bArr[i7];
                i6++;
                i7++;
            }
        }
    }

    public boolean generate(ArrayList<FecRawPacket> arrayList, int i, int i2, boolean z, FecMaskType fecMaskType, ArrayList<FecRawPacket> arrayList2) {
        ArrayList<FecRawPacket> arrayList3 = arrayList;
        int i3 = i2;
        int count = ArrayListExtensions.getCount(arrayList);
        int max = MathAssistant.max(0, MathAssistant.min(255, i));
        verify(count > 0);
        verify(i3 >= 0 && i3 <= count);
        verify(ArrayListExtensions.getCount(arrayList2) == 0);
        if (count > 48) {
            __log.warn(StringExtensions.format("Can't protect {0} media packets per frame. Max is {1}.", IntegerExtensions.toString(Integer.valueOf(count)), IntegerExtensions.toString(48)));
            return false;
        }
        int i4 = count > 16 ? 6 : 2;
        Iterator<FecRawPacket> it = arrayList.iterator();
        while (it.hasNext()) {
            FecRawPacket next = it.next();
            verify(next != null);
            if (next.getLength() < 12) {
                __log.warn(StringExtensions.format("Media packet {0} bytes is smaller than RTP header.", (Object) IntegerExtensions.toString(Integer.valueOf(next.getLength()))));
                return false;
            } else if (next.getLength() + getPacketOverhead() + 28 > getIPPacketSize()) {
                __log.warn(StringExtensions.format("Media packet {0} bytes with overhead is larger than {1}.", IntegerExtensions.toString(Integer.valueOf(next.getLength())), IntegerExtensions.toString(Integer.valueOf(getIPPacketSize()))));
            }
        }
        int numberOfFecPackets = getNumberOfFecPackets(count, max);
        if (numberOfFecPackets != 0) {
            for (int i5 = 0; i5 < numberOfFecPackets; i5++) {
                BitAssistant.set(((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i5)).getData(), 0, getIPPacketSize(), 0);
                ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i5)).setLength(0);
                arrayList2.add(ArrayListExtensions.getItem(this._generatedPackets).get(i5));
            }
            byte[] bArr = new byte[(numberOfFecPackets * 6)];
            generatePacketMasks(count, numberOfFecPackets, i2, z, new FecPacketMaskTable(fecMaskType, count), bArr);
            int insertZerosInBitMasks = insertZerosInBitMasks(arrayList3, bArr, i4, numberOfFecPackets);
            boolean z2 = insertZerosInBitMasks > 16;
            if (insertZerosInBitMasks < 0) {
                return false;
            }
            generateFecBitStrings(arrayList3, bArr, numberOfFecPackets, z2);
            generateFecUlpHeaders(arrayList3, bArr, z2, numberOfFecPackets);
        }
        return true;
    }

    private void generateFecBitStrings(ArrayList<FecRawPacket> arrayList, byte[] bArr, int i, boolean z) {
        boolean z2;
        int i2;
        if (ArrayListExtensions.getCount(arrayList) != 0) {
            int i3 = 2;
            byte[] bArr2 = new byte[2];
            int i4 = z ? 6 : 2;
            int i5 = (z ? 8 : 4) + 10;
            int i6 = i5 - 12;
            boolean z3 = false;
            int i7 = i;
            int i8 = 0;
            while (i8 < i7) {
                int i9 = i8 * i4;
                int parseSequenceNumber = parseSequenceNumber(((FecRawPacket) ArrayListExtensions.getItem(arrayList).get(z3 ? 1 : 0)).getData());
                int i10 = 0;
                while (true) {
                    int i11 = 0;
                    while (true) {
                        z2 = true;
                        if (i10 == ArrayListExtensions.getCount(arrayList)) {
                            break;
                        }
                        if ((BitAssistant.castInteger(bArr[i9]) & (1 << (7 - i11))) > 0) {
                            FecRawPacket fecRawPacket = (FecRawPacket) ArrayListExtensions.getItem(arrayList).get(i10);
                            Binary.toBytes16(fecRawPacket.getLength() - 12, z3, bArr2, z3 ? 1 : 0);
                            int length = fecRawPacket.getLength() + i6;
                            if (((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getLength() == 0) {
                                blockCopy(((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData(), fecRawPacket.getData(), i3);
                                blockCopy(((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData(), 4, fecRawPacket.getData(), 4, 4);
                                i2 = i4;
                                blockCopy(((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData(), 8, bArr2, 0, 2);
                                blockCopy(((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData(), i5, fecRawPacket.getData(), 12, fecRawPacket.getLength() - 12);
                            } else {
                                i2 = i4;
                                ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[0] = (byte) (((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[0] ^ fecRawPacket.getData()[0]);
                                ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[1] = (byte) (((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[1] ^ fecRawPacket.getData()[1]);
                                for (int i12 = 4; i12 < 8; i12++) {
                                    ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[i12] = (byte) (((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[i12] ^ fecRawPacket.getData()[i12]);
                                }
                                ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[8] = (byte) (((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[8] ^ bArr2[0]);
                                ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[9] = (byte) (((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[9] ^ bArr2[1]);
                                for (int i13 = i5; i13 < length; i13++) {
                                    ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[i13] = (byte) (((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getData()[i13] ^ fecRawPacket.getData()[i13 - i6]);
                                }
                            }
                            if (length > ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getLength()) {
                                ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).setLength(length);
                            }
                        } else {
                            i2 = i4;
                        }
                        i10++;
                        if (i10 != ArrayListExtensions.getCount(arrayList)) {
                            int parseSequenceNumber2 = parseSequenceNumber(((FecRawPacket) ArrayListExtensions.getItem(arrayList).get(i10)).getData());
                            i11 += constrain16(parseSequenceNumber2 - parseSequenceNumber);
                            parseSequenceNumber = parseSequenceNumber2;
                        }
                        if (i11 == 8) {
                            break;
                        }
                        i4 = i2;
                        i3 = 2;
                        z3 = false;
                    }
                    i9++;
                    i4 = i2;
                    i3 = 2;
                    z3 = false;
                }
                int i14 = i4;
                if (((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i8)).getLength() == 0) {
                    z2 = false;
                }
                verify(z2);
                i8++;
                i4 = i14;
                i3 = 2;
                z3 = false;
            }
        }
    }

    private void generateFecUlpHeaders(ArrayList<FecRawPacket> arrayList, byte[] bArr, boolean z, int i) {
        FecRawPacket fecRawPacket = (FecRawPacket) ArrayListExtensions.getItem(arrayList).get(0);
        verify(fecRawPacket != null);
        int i2 = z ? 6 : 2;
        int i3 = z ? 8 : 4;
        for (int i4 = 0; i4 < i; i4++) {
            ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i4)).getData()[0] = (byte) (((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i4)).getData()[0] & ByteCompanionObject.MAX_VALUE);
            if (!z) {
                ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i4)).getData()[0] = (byte) (((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i4)).getData()[0] & 191);
            } else {
                ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i4)).getData()[0] = (byte) (((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i4)).getData()[0] | 64);
            }
            blockCopy(((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i4)).getData(), 2, fecRawPacket.getData(), 2, 2);
            Binary.toBytes16((((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i4)).getLength() - 10) - i3, false, ((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i4)).getData(), 10);
            blockCopy(((FecRawPacket) ArrayListExtensions.getItem(this._generatedPackets).get(i4)).getData(), 12, bArr, i4 * i2, i2);
        }
    }

    private void generatePacketMasks(int i, int i2, int i3, boolean z, FecPacketMaskTable fecPacketMaskTable, byte[] bArr) {
        boolean z2 = false;
        verify(i > 0);
        verify(i2 <= i && i2 > 0);
        verify(i3 <= i && i3 >= 0);
        if (i > 16) {
            z2 = true;
        }
        int i4 = z2 ? 6 : 2;
        if (!z || i3 == 0) {
            blockCopy(bArr, fecPacketMaskTable.getTable()[i - 1][i2 - 1], i2 * i4);
        } else {
            unequalProtectionMask(i, i2, i3, i4, bArr, fecPacketMaskTable);
        }
    }

    public int getNumberOfFecPackets(int i, int i2) {
        int i3 = ((i * i2) + 128) >> 8;
        boolean z = true;
        if (i2 > 0 && i3 == 0) {
            i3 = 1;
        }
        if (i3 > i) {
            z = false;
        }
        verify(z);
        return i3;
    }

    public boolean getPacketReceived() {
        return this._packetReceived;
    }

    private void importantPacketProtection(int i, int i2, int i3, byte[] bArr, FecPacketMaskTable fecPacketMaskTable) {
        fitSubMask(i3, i2 > 16 ? 6 : 2, i, fecPacketMaskTable.getTable()[i2 - 1][i - 1], bArr);
    }

    private static void initRecovery(FecPacket fecPacket, FecRecoveredPacket fecRecoveredPacket) {
        int i = (BitAssistant.castInteger(fecPacket.getRaw().getData()[0]) & 64) != 0 ? 8 : 4;
        fecRecoveredPacket.setRaw(new FecRawPacket());
        BitAssistant.set(fecRecoveredPacket.getRaw().getData(), 0, getIPPacketSize(), 0);
        fecRecoveredPacket.setReturned(false);
        fecRecoveredPacket.setWasRecovered(true);
        byte[] bArr = new byte[2];
        blockCopy(bArr, 0, fecPacket.getRaw().getData(), 10, 2);
        blockCopy(fecRecoveredPacket.getRaw().getData(), 12, fecPacket.getRaw().getData(), i + 10, Binary.fromBytes16(bArr, 0, false));
        blockCopy(fecRecoveredPacket.getLengthRecovery(), 0, fecPacket.getRaw().getData(), 8, 2);
        blockCopy(fecRecoveredPacket.getRaw().getData(), fecPacket.getRaw().getData(), 2);
        blockCopy(fecRecoveredPacket.getRaw().getData(), 4, fecPacket.getRaw().getData(), 4, 4);
        Binary.toBytes32(fecPacket.getSynchronizationSource(), false, fecRecoveredPacket.getRaw().getData(), 8);
    }

    private void insertFECPacket(FecReceivedPacket fecReceivedPacket, ArrayList<FecRecoveredPacket> arrayList) {
        boolean z = true;
        setPacketReceived(true);
        for (int i = 0; i != ArrayListExtensions.getCount(this._packetList); i++) {
            if (fecReceivedPacket.getSequenceNumber() == ((FecPacket) ArrayListExtensions.getItem(this._packetList).get(i)).getSequenceNumber()) {
                fecReceivedPacket.setRaw((FecRawPacket) null);
                return;
            }
        }
        FecPacket fecPacket = new FecPacket();
        fecPacket.setRaw(fecReceivedPacket.getRaw());
        fecPacket.setSequenceNumber(fecReceivedPacket.getSequenceNumber());
        fecPacket.setSynchronizationSource(fecReceivedPacket.getSynchronizationSource());
        int i2 = 2;
        int fromBytes16 = Binary.fromBytes16(fecPacket.getRaw().getData(), 2, false);
        if ((BitAssistant.castInteger(fecPacket.getRaw().getData()[0]) & 64) != 0) {
            i2 = 6;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = fecPacket.getRaw().getData()[i3 + 12];
            for (int i4 = 0; i4 < 8; i4++) {
                if ((BitAssistant.castInteger(b) & (1 << (7 - i4))) > 0) {
                    FecProtectedPacket fecProtectedPacket = new FecProtectedPacket();
                    fecPacket.getProtectedPacketList().add(fecProtectedPacket);
                    fecProtectedPacket.setSequenceNumber(constrain16((i3 << 3) + fromBytes16 + i4));
                    fecProtectedPacket.setRaw((FecRawPacket) null);
                }
            }
        }
        if (ArrayListExtensions.getCount(fecPacket.getProtectedPacketList()) == 0) {
            Log.warn("FEC packet has an all-zero packet mask.");
            return;
        }
        assignRecoveredPackets(fecPacket, arrayList);
        this._packetList.add(fecPacket);
        Sort.quickSort(this._packetList, new IFunctionDelegate2<FecPacket, FecPacket, CompareResult>() {
            public String getId() {
                return "fm.liveswitch.FecContext.packetLessThan";
            }

            public CompareResult invoke(FecPacket fecPacket, FecPacket fecPacket2) {
                return FecContext.packetLessThan(fecPacket, fecPacket2);
            }
        });
        if (ArrayListExtensions.getCount(this._packetList) > 48) {
            discardFECPacket((FecPacket) ArrayListExtensions.getItem(this._packetList).get(0));
            ArrayListExtensions.removeAt(this._packetList, 0);
        }
        if (ArrayListExtensions.getCount(this._packetList) > 48) {
            z = false;
        }
        verify(z);
    }

    private void insertMediaPacket(FecReceivedPacket fecReceivedPacket, ArrayList<FecRecoveredPacket> arrayList) {
        for (int i = 0; i != ArrayListExtensions.getCount(arrayList); i++) {
            if (fecReceivedPacket.getSequenceNumber() == ((FecRecoveredPacket) ArrayListExtensions.getItem(arrayList).get(i)).getSequenceNumber()) {
                fecReceivedPacket.setRaw((FecRawPacket) null);
                return;
            }
        }
        FecRecoveredPacket fecRecoveredPacket = new FecRecoveredPacket();
        fecRecoveredPacket.setWasRecovered(false);
        fecRecoveredPacket.setReturned(true);
        fecRecoveredPacket.setSequenceNumber(fecReceivedPacket.getSequenceNumber());
        fecRecoveredPacket.setRaw(fecReceivedPacket.getRaw());
        fecRecoveredPacket.getRaw().setLength(fecReceivedPacket.getRaw().getLength());
        arrayList.add(fecRecoveredPacket);
        Sort.quickSort(arrayList, new IFunctionDelegate2<FecRecoveredPacket, FecRecoveredPacket, CompareResult>() {
            public String getId() {
                return "fm.liveswitch.FecContext.recoveredPacketLessThan";
            }

            public CompareResult invoke(FecRecoveredPacket fecRecoveredPacket, FecRecoveredPacket fecRecoveredPacket2) {
                return FecContext.recoveredPacketLessThan(fecRecoveredPacket, fecRecoveredPacket2);
            }
        });
        updateCoveringFECPackets(fecRecoveredPacket);
    }

    private void insertPackets(ArrayList<FecReceivedPacket> arrayList, ArrayList<FecRecoveredPacket> arrayList2) {
        boolean z;
        while (true) {
            z = false;
            if (ArrayListExtensions.getCount(arrayList) == 0) {
                break;
            }
            FecReceivedPacket fecReceivedPacket = (FecReceivedPacket) ArrayListExtensions.getItem(arrayList).get(0);
            if (ArrayListExtensions.getCount(this._packetList) != 0 && MathAssistant.abs(fecReceivedPacket.getSequenceNumber() - ((FecPacket) ArrayListExtensions.getItem(this._packetList).get(0)).getSequenceNumber()) > 16383) {
                discardFECPacket((FecPacket) ArrayListExtensions.getItem(this._packetList).get(0));
                ArrayListExtensions.removeAt(this._packetList, 0);
            }
            if (fecReceivedPacket.getIsFec()) {
                insertFECPacket(fecReceivedPacket, arrayList2);
            } else {
                insertMediaPacket(fecReceivedPacket, arrayList2);
            }
            ArrayListExtensions.removeAt(arrayList, 0);
        }
        if (ArrayListExtensions.getCount(arrayList) == 0) {
            z = true;
        }
        verify(z);
        discardOldPackets(arrayList2);
    }

    private static void insertZeroColumns(int i, byte[] bArr, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = (i5 * i2) + (i4 / 8);
            bArr[i6] = BitAssistant.leftShift(bArr[i6], MathAssistant.min(i, 7 - (i4 % 8)));
        }
    }

    private int insertZerosInBitMasks(ArrayList<FecRawPacket> arrayList, byte[] bArr, int i, int i2) {
        int i3 = i2;
        if (ArrayListExtensions.getCount(arrayList) <= 1) {
            return ArrayListExtensions.getCount(arrayList);
        }
        int parseSequenceNumber = parseSequenceNumber(((FecRawPacket) ArrayListExtensions.getItem(arrayList).get(ArrayListExtensions.getCount(arrayList) - 1)).getData());
        int parseSequenceNumber2 = parseSequenceNumber(((FecRawPacket) ArrayListExtensions.getItem(arrayList).get(0)).getData());
        int constrain16 = (constrain16(parseSequenceNumber - parseSequenceNumber2) - ArrayListExtensions.getCount(arrayList)) + 1;
        if (constrain16 == 0) {
            return ArrayListExtensions.getCount(arrayList);
        }
        int i4 = ArrayListExtensions.getCount(arrayList) + constrain16 > 16 ? 6 : 2;
        int i5 = i3 * 6;
        byte[] bArr2 = new byte[i5];
        copyColumn(bArr2, i4, bArr, i, i2, 0, 0);
        int i6 = 1;
        int i7 = 1;
        int i8 = 1;
        while (i7 != ArrayListExtensions.getCount(arrayList) && i6 != 48) {
            int parseSequenceNumber3 = parseSequenceNumber(((FecRawPacket) ArrayListExtensions.getItem(arrayList).get(i7)).getData());
            int constrain162 = constrain16((parseSequenceNumber3 - parseSequenceNumber2) - 1);
            if (constrain162 > 0) {
                insertZeroColumns(constrain162, bArr2, i4, i3, i6);
            }
            int i9 = i6 + constrain162;
            copyColumn(bArr2, i4, bArr, i, i2, i9, i8);
            i6 = i9 + 1;
            i8++;
            i7++;
            parseSequenceNumber2 = parseSequenceNumber3;
        }
        int i10 = i6 % 8;
        if (i10 != 0) {
            for (int i11 = 0; i11 < i3; i11++) {
                int i12 = (i11 * i4) + (i6 / 8);
                bArr2[i12] = BitAssistant.leftShift(bArr2[i12], 7 - i10);
            }
        }
        blockCopy(bArr, bArr2, i5);
        return i6;
    }

    private static boolean isNewerSequenceNumber(int i, int i2) {
        return i != i2 && constrain16(i - i2) < 32768;
    }

    private static int lowerBound(ArrayList<FecProtectedPacket> arrayList, int i, int i2, FecRecoveredPacket fecRecoveredPacket, IFunction2<FecSortablePacket, FecSortablePacket, CompareResult> iFunction2) {
        int abs = MathAssistant.abs(i2 - i);
        while (abs > 0) {
            int i3 = abs / 2;
            int i4 = i + i3;
            if (Global.equals(iFunction2.invoke(ArrayListExtensions.getItem(arrayList).get(i4), fecRecoveredPacket), CompareResult.Negative)) {
                i = i4 + 1;
                abs -= i3 + 1;
            } else {
                abs = i3;
            }
        }
        return i;
    }

    private static int numCoveredPacketsMissing(FecPacket fecPacket) {
        Iterator<FecProtectedPacket> it = fecPacket.getProtectedPacketList().iterator();
        int i = 0;
        while (it.hasNext() && (it.next().getRaw() != null || (i = i + 1) <= 1)) {
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static CompareResult packetLessThan(FecPacket fecPacket, FecPacket fecPacket2) {
        return sortablePacketLessThan(fecPacket, fecPacket2);
    }

    private static int parseSequenceNumber(byte[] bArr) {
        return Binary.fromBytes16(bArr, 2, false);
    }

    /* access modifiers changed from: private */
    public static CompareResult recoveredPacketLessThan(FecRecoveredPacket fecRecoveredPacket, FecRecoveredPacket fecRecoveredPacket2) {
        return sortablePacketLessThan(fecRecoveredPacket, fecRecoveredPacket2);
    }

    private static void recoverPacket(FecPacket fecPacket, FecRecoveredPacket fecRecoveredPacket) {
        initRecovery(fecPacket, fecRecoveredPacket);
        Iterator<FecProtectedPacket> it = fecPacket.getProtectedPacketList().iterator();
        while (it.hasNext()) {
            FecProtectedPacket next = it.next();
            if (next.getRaw() == null) {
                fecRecoveredPacket.setSequenceNumber(next.getSequenceNumber());
            } else {
                xorPackets(next.getRaw(), fecRecoveredPacket);
            }
        }
        finishRecovery(fecRecoveredPacket);
    }

    private void remainingPacketProtection(int i, int i2, int i3, int i4, FecProtectionMode fecProtectionMode, byte[] bArr, FecPacketMaskTable fecPacketMaskTable) {
        int i5 = i2;
        FecProtectionMode fecProtectionMode2 = fecProtectionMode;
        int i6 = 0;
        if (Global.equals(fecProtectionMode, FecProtectionMode.NoOverlap)) {
            int i7 = i - i3;
            if (i7 > 16) {
                i6 = 1;
            }
            int i8 = i6 == 1 ? 6 : 2;
            shiftFitSubMask(i4, i8, i3, i3 + i5, fecPacketMaskTable.getTable()[i7 - 1][i5 - 1], bArr);
        } else if (Global.equals(fecProtectionMode, FecProtectionMode.Overlap) || Global.equals(fecProtectionMode, FecProtectionMode.BiasFirstPacket)) {
            fitSubMask(i4, i4, i2, fecPacketMaskTable.getTable()[i - 1][i5 - 1], bArr, i3 * i4);
            if (Global.equals(fecProtectionMode, FecProtectionMode.BiasFirstPacket)) {
                while (i6 < i5) {
                    int i9 = i6 * i4;
                    bArr[i9] = (byte) (BitAssistant.castInteger(bArr[i9]) | 128);
                    i6++;
                }
            }
        } else {
            verify(false);
        }
    }

    public void resetState(ArrayList<FecRecoveredPacket> arrayList) {
        boolean z = false;
        setPacketReceived(false);
        while (ArrayListExtensions.getCount(arrayList) != 0) {
            ArrayListExtensions.removeAt(arrayList, 0);
        }
        verify(ArrayListExtensions.getCount(arrayList) == 0);
        while (ArrayListExtensions.getCount(this._packetList) != 0) {
            FecPacket fecPacket = (FecPacket) ArrayListExtensions.getItem(this._packetList).get(0);
            fecPacket.getProtectedPacketList().clear();
            verify(ArrayListExtensions.getCount(fecPacket.getProtectedPacketList()) == 0);
            ArrayListExtensions.removeAt(this._packetList, 0);
        }
        if (ArrayListExtensions.getCount(this._packetList) == 0) {
            z = true;
        }
        verify(z);
    }

    private static ArrayList<FecRecoveredPacket> setIntersection(ArrayList<FecRecoveredPacket> arrayList, int i, int i2, ArrayList<FecProtectedPacket> arrayList2, int i3, int i4, ArrayList<FecRecoveredPacket> arrayList3, IFunction2<FecSortablePacket, FecSortablePacket, CompareResult> iFunction2) {
        while (i != i2 && i3 != i4) {
            if (Global.equals(iFunction2.invoke(ArrayListExtensions.getItem(arrayList).get(i), ArrayListExtensions.getItem(arrayList2).get(i3)), CompareResult.Negative)) {
                i++;
            } else {
                if (!Global.equals(iFunction2.invoke(ArrayListExtensions.getItem(arrayList2).get(i3), ArrayListExtensions.getItem(arrayList).get(i)), CompareResult.Negative)) {
                    arrayList3.add(ArrayListExtensions.getItem(arrayList).get(i));
                    i++;
                }
                i3++;
            }
        }
        return arrayList3;
    }

    private void setPacketReceived(boolean z) {
        this._packetReceived = z;
    }

    private void shiftFitSubMask(int i, int i2, int i3, int i4, byte[] bArr, byte[] bArr2) {
        int i5 = i3 % 8;
        int i6 = i3 >> 3;
        for (int i7 = i3; i7 < i4; i7++) {
            int i8 = (((i7 * i) + i2) - 1) + i6;
            int i9 = (((i7 - i3) * i2) + i2) - 1;
            if (i > i2) {
                bArr2[i8 + 1] = (byte) BitAssistant.leftShift(bArr[i9], 8 - i5);
            }
            for (int i10 = i2 - 1; i10 > 0; i10--) {
                bArr2[i8] = (byte) (BitAssistant.rightShift(bArr[i9], i5) | BitAssistant.leftShift(bArr[i9 - 1], 8 - i5));
                i8--;
                i9--;
            }
            bArr2[i8] = (byte) BitAssistant.rightShift(bArr[i9], i5);
        }
    }

    /* access modifiers changed from: private */
    public static CompareResult sortablePacketLessThan(FecSortablePacket fecSortablePacket, FecSortablePacket fecSortablePacket2) {
        if (isNewerSequenceNumber(fecSortablePacket2.getSequenceNumber(), fecSortablePacket.getSequenceNumber())) {
            return CompareResult.Negative;
        }
        return CompareResult.Positive;
    }

    private void unequalProtectionMask(int i, int i2, int i3, int i4, byte[] bArr, FecPacketMaskTable fecPacketMaskTable) {
        FecProtectionMode fecProtectionMode = FecProtectionMode.Overlap;
        int protectionAllocation = !Global.equals(fecProtectionMode, FecProtectionMode.BiasFirstPacket) ? setProtectionAllocation(i, i2, i3) : 0;
        int i5 = i2 - protectionAllocation;
        if (protectionAllocation > 0) {
            importantPacketProtection(protectionAllocation, i3, i4, bArr, fecPacketMaskTable);
        }
        if (i5 > 0) {
            remainingPacketProtection(i, i5, protectionAllocation, i4, fecProtectionMode, bArr, fecPacketMaskTable);
        }
    }

    private void updateCoveringFECPackets(FecRecoveredPacket fecRecoveredPacket) {
        for (int i = 0; i != ArrayListExtensions.getCount(this._packetList); i++) {
            ArrayList<FecProtectedPacket> protectedPacketList = ((FecPacket) ArrayListExtensions.getItem(this._packetList).get(i)).getProtectedPacketList();
            int lowerBound = lowerBound(protectedPacketList, 0, ArrayListExtensions.getCount(protectedPacketList), fecRecoveredPacket, new IFunctionDelegate2<FecSortablePacket, FecSortablePacket, CompareResult>() {
                public String getId() {
                    return "fm.liveswitch.FecContext.sortablePacketLessThan";
                }

                public CompareResult invoke(FecSortablePacket fecSortablePacket, FecSortablePacket fecSortablePacket2) {
                    return FecContext.sortablePacketLessThan(fecSortablePacket, fecSortablePacket2);
                }
            });
            if (lowerBound < ArrayListExtensions.getCount(protectedPacketList) && ((FecProtectedPacket) ArrayListExtensions.getItem(protectedPacketList).get(lowerBound)).getSequenceNumber() == fecRecoveredPacket.getSequenceNumber()) {
                ((FecProtectedPacket) ArrayListExtensions.getItem(protectedPacketList).get(lowerBound)).setRaw(fecRecoveredPacket.getRaw());
            }
        }
    }

    private static void verify(boolean z) {
        if (!z) {
            throw new RuntimeException(new Exception("Assertion failed."));
        }
    }

    private static void xorPackets(FecRawPacket fecRawPacket, FecRecoveredPacket fecRecoveredPacket) {
        for (int i = 0; i < 2; i++) {
            fecRecoveredPacket.getRaw().getData()[i] = (byte) (fecRecoveredPacket.getRaw().getData()[i] ^ fecRawPacket.getData()[i]);
        }
        for (int i2 = 4; i2 < 8; i2++) {
            fecRecoveredPacket.getRaw().getData()[i2] = (byte) (fecRecoveredPacket.getRaw().getData()[i2] ^ fecRawPacket.getData()[i2]);
        }
        byte[] bArr = new byte[2];
        Binary.toBytes16(fecRawPacket.getLength() - 12, false, bArr, 0);
        fecRecoveredPacket.getLengthRecovery()[0] = (byte) (fecRecoveredPacket.getLengthRecovery()[0] ^ bArr[0]);
        fecRecoveredPacket.getLengthRecovery()[1] = (byte) (bArr[1] ^ fecRecoveredPacket.getLengthRecovery()[1]);
        for (int i3 = 12; i3 < fecRawPacket.getLength(); i3++) {
            fecRecoveredPacket.getRaw().getData()[i3] = (byte) (fecRecoveredPacket.getRaw().getData()[i3] ^ fecRawPacket.getData()[i3]);
        }
    }
}
