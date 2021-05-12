package fm.liveswitch;

import java.util.ArrayList;

class SctpReceiveDataQueue extends SctpDataQueue {
    private static ILog __log = Log.getLogger(SctpReceiveDataQueue.class);
    private long __cumulativeACKPoint = -1;
    private ArrayList<SctpGapAckBlock> __gapAckBlocks;
    private long __initialTSN = -1;

    public void add(SctpDataChunk sctpDataChunk) {
        boolean z;
        boolean z2;
        long j;
        boolean z3;
        boolean z4;
        synchronized (this.__lock) {
            long tsn = sctpDataChunk.getTsn();
            boolean tryUpdateCumulativeACKPoint = tryUpdateCumulativeACKPoint(tsn);
            if (__log.getIsVerboseEnabled()) {
                __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Inserting into queue: newTSN: {0}. Cumulative: {1}.", LongExtensions.toString(Long.valueOf(tsn)), LongExtensions.toString(Long.valueOf(this.__cumulativeACKPoint))));
                __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Moved cumulative: {0}", (Object) BooleanExtensions.toString(Boolean.valueOf(tryUpdateCumulativeACKPoint))));
            }
            if (tryUpdateCumulativeACKPoint && ArrayListExtensions.getCount(this.__gapAckBlocks) > 0) {
                if (((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(0)).getAbsoluteGapAckBlockStart() == this.__cumulativeACKPoint + 1) {
                    if (__log.getIsVerboseEnabled()) {
                        __log.verbose("SCTP ReceiveDataQueue Add: Merged the first block in.");
                    }
                    this.__cumulativeACKPoint = ((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(0)).getAbsoluteGapAckBlockEnd();
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (z4) {
                    ArrayListExtensions.removeAt(this.__gapAckBlocks, 0);
                }
            } else if (!tryUpdateCumulativeACKPoint) {
                ArrayList<SctpGapAckBlock> arrayList = new ArrayList<>();
                if (ArrayListExtensions.getCount(this.__gapAckBlocks) == 0) {
                    arrayList.add(new SctpGapAckBlock(tsn, tsn));
                    if (__log.getIsVerboseEnabled()) {
                        __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Inserted a new block. Case 1", new Object[0]));
                    }
                    z = true;
                } else {
                    z = false;
                }
                int i = 0;
                while (!z) {
                    long absoluteGapAckBlockStart = ((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i)).getAbsoluteGapAckBlockStart();
                    long absoluteGapAckBlockEnd = ((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i)).getAbsoluteGapAckBlockEnd();
                    int i2 = i + 1;
                    long absoluteGapAckBlockStart2 = i2 < ArrayListExtensions.getCount(this.__gapAckBlocks) ? ((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i2)).getAbsoluteGapAckBlockStart() : -1;
                    if (__log.getIsVerboseEnabled()) {
                        z2 = z;
                        __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: start {0} end {1} nextStart {2}", LongExtensions.toString(Long.valueOf(absoluteGapAckBlockStart)), LongExtensions.toString(Long.valueOf(absoluteGapAckBlockEnd)), LongExtensions.toString(Long.valueOf(absoluteGapAckBlockStart2))));
                    } else {
                        z2 = z;
                    }
                    if (SctpDataChunk.compareTsns(SctpDataChunk.incrementTSN(tsn), absoluteGapAckBlockStart) == 2) {
                        if (__log.getIsVerboseEnabled()) {
                            __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Inserted a new block. Case 2", new Object[0]));
                        }
                        arrayList = this.__gapAckBlocks;
                        ArrayListExtensions.insert(arrayList, i, new SctpGapAckBlock(tsn, tsn));
                        z3 = true;
                        j = 1;
                    } else {
                        if (SctpDataChunk.compareTsns(SctpDataChunk.incrementTSN(tsn), absoluteGapAckBlockStart) == 0) {
                            if (__log.getIsVerboseEnabled()) {
                                __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Expanded the beginning of this block. Case 3", new Object[0]));
                            }
                            long decrementTSN = SctpDataChunk.decrementTSN(((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i)).getAbsoluteGapAckBlockStart());
                            if (i > 0) {
                                ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(this.__gapAckBlocks, 0, i));
                                int i3 = i - 1;
                                j = 1;
                                if (SctpDataChunk.compareTsns(SctpDataChunk.incrementTSN(((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i3)).getAbsoluteGapAckBlockEnd() + 1), decrementTSN) != 2) {
                                    if (__log.getIsVerboseEnabled()) {
                                        __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Merged with the previous. Subcase 3", new Object[0]));
                                    }
                                    arrayList.add(new SctpGapAckBlock(((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i3)).getAbsoluteGapAckBlockStart(), ((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i)).getAbsoluteGapAckBlockEnd()));
                                } else {
                                    arrayList.add(ArrayListExtensions.getItem(this.__gapAckBlocks).get(i3));
                                    arrayList.add(new SctpGapAckBlock(decrementTSN, ((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i)).getAbsoluteGapAckBlockEnd()));
                                }
                            } else {
                                j = 1;
                                arrayList.add(new SctpGapAckBlock(decrementTSN, ((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i)).getAbsoluteGapAckBlockEnd()));
                            }
                            if (ArrayListExtensions.getCount(this.__gapAckBlocks) > i2) {
                                ArrayList<SctpGapAckBlock> arrayList2 = this.__gapAckBlocks;
                                ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(arrayList2, i2, (ArrayListExtensions.getCount(arrayList2) - i) - 1));
                            }
                        } else {
                            j = 1;
                            if (SctpDataChunk.compareTsns(tsn, absoluteGapAckBlockStart) >= 2 || SctpDataChunk.compareTsns(tsn, absoluteGapAckBlockEnd) == 1) {
                                if (SctpDataChunk.compareTsns(SctpDataChunk.incrementTSN(absoluteGapAckBlockEnd), tsn) == 0) {
                                    if (__log.getIsVerboseEnabled()) {
                                        __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Expanded the end of this block. Case 5", new Object[0]));
                                    }
                                    ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(this.__gapAckBlocks, 0, i));
                                    if (absoluteGapAckBlockStart2 == -1) {
                                        arrayList.add(new SctpGapAckBlock(((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i)).getAbsoluteGapAckBlockStart(), tsn));
                                    } else if (SctpDataChunk.compareTsns(((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i2)).getAbsoluteGapAckBlockStart(), SctpDataChunk.incrementTSN(tsn)) != 1) {
                                        if (__log.getIsVerboseEnabled()) {
                                            __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Merged with the next. Subcase 5", new Object[0]));
                                        }
                                        arrayList.add(new SctpGapAckBlock(((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i)).getAbsoluteGapAckBlockStart(), ((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i2)).getAbsoluteGapAckBlockEnd()));
                                        int i4 = i + 2;
                                        if (ArrayListExtensions.getCount(this.__gapAckBlocks) > i4) {
                                            ArrayList<SctpGapAckBlock> arrayList3 = this.__gapAckBlocks;
                                            ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(arrayList3, i4, (ArrayListExtensions.getCount(arrayList3) - i) - 2));
                                        }
                                    } else {
                                        arrayList.add(new SctpGapAckBlock(((SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i)).getAbsoluteGapAckBlockStart(), tsn));
                                        if (ArrayListExtensions.getCount(this.__gapAckBlocks) > i2) {
                                            ArrayList<SctpGapAckBlock> arrayList4 = this.__gapAckBlocks;
                                            ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(arrayList4, i2, (ArrayListExtensions.getCount(arrayList4) - i) - 1));
                                        }
                                    }
                                } else {
                                    long j2 = absoluteGapAckBlockStart2;
                                    if (j2 == -1) {
                                        if (__log.getIsVerboseEnabled()) {
                                            __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Inserted new block. Case 6.", new Object[0]));
                                        }
                                        ArrayListExtensions.addRange(arrayList, this.__gapAckBlocks);
                                        arrayList.add(new SctpGapAckBlock(tsn, tsn));
                                    } else {
                                        if (SctpDataChunk.compareTsns(j2, SctpDataChunk.incrementTSN(tsn)) == 1) {
                                            if (__log.getIsVerboseEnabled()) {
                                                __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Inserted new block. Case 7", new Object[0]));
                                            }
                                            ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(this.__gapAckBlocks, 0, i2));
                                            arrayList.add(new SctpGapAckBlock(tsn, tsn));
                                            if (ArrayListExtensions.getCount(this.__gapAckBlocks) > i2) {
                                                ArrayList<SctpGapAckBlock> arrayList5 = this.__gapAckBlocks;
                                                ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(arrayList5, i2, (ArrayListExtensions.getCount(arrayList5) - i) - 1));
                                            }
                                        } else if (SctpDataChunk.compareTsns(j2, SctpDataChunk.incrementTSN(tsn)) == 0) {
                                            if (__log.getIsVerboseEnabled()) {
                                                __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Expanded beginning of the next block. Case 8", new Object[0]));
                                            }
                                            arrayList = this.__gapAckBlocks;
                                            ((SctpGapAckBlock) ArrayListExtensions.getItem(arrayList).get(i2)).setAbsoluteGapAckBlockStart(SctpDataChunk.decrementTSN(((SctpGapAckBlock) ArrayListExtensions.getItem(arrayList).get(i2)).getAbsoluteGapAckBlockStart()));
                                        } else {
                                            z3 = z2;
                                        }
                                        z3 = true;
                                    }
                                }
                            } else if (__log.getIsVerboseEnabled()) {
                                __log.verbose(StringExtensions.format("SCTP ReceiveDataQueue Add: Already have this chunk. Case 4", new Object[0]));
                            }
                        }
                        z3 = true;
                    }
                    i = i2;
                    z = z3;
                    long j3 = j;
                }
                this.__gapAckBlocks = arrayList;
            }
            super.add(sctpDataChunk);
        }
    }

    public long getCumulativeACK() {
        long j;
        synchronized (this.__lock) {
            j = this.__cumulativeACKPoint;
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public long getInitialTSN() {
        return this.__initialTSN;
    }

    public SctpSackChunk getSackChunk() {
        SctpSackChunk sctpSackChunk;
        synchronized (this.__lock) {
            if (getCumulativeACK() != -1) {
                sctpSackChunk = new SctpSackChunk(getCumulativeACK(), 4294967295L, (SctpGapAckBlock[]) this.__gapAckBlocks.toArray(new SctpGapAckBlock[0]), (long[]) null);
            } else {
                sctpSackChunk = null;
            }
        }
        return sctpSackChunk;
    }

    public boolean processForwardTsnChunk(SctpForwardTsnChunk sctpForwardTsnChunk) {
        long newCumulativeTsnAck = sctpForwardTsnChunk.getNewCumulativeTsnAck();
        synchronized (this.__lock) {
            if (SctpDataChunk.compareTsns(this.__cumulativeACKPoint, newCumulativeTsnAck) != 2) {
                return false;
            }
            purge(newCumulativeTsnAck);
            return true;
        }
    }

    public void purge(long j) {
        synchronized (this.__lock) {
            super.purge(j);
            if (SctpDataChunk.compareTsns(j, this.__cumulativeACKPoint) == 1) {
                this.__cumulativeACKPoint = j;
                ArrayList<SctpGapAckBlock> arrayList = new ArrayList<>();
                boolean z = false;
                for (int i = 0; i < ArrayListExtensions.getCount(this.__gapAckBlocks) && !z; i++) {
                    SctpGapAckBlock sctpGapAckBlock = (SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i);
                    if (SctpDataChunk.compareTsns(sctpGapAckBlock.getAbsoluteGapAckBlockEnd(), this.__cumulativeACKPoint) != 2 && SctpDataChunk.compareTsns(sctpGapAckBlock.getAbsoluteGapAckBlockStart(), SctpDataChunk.incrementTSN(this.__cumulativeACKPoint)) != 1) {
                        this.__cumulativeACKPoint = sctpGapAckBlock.getAbsoluteGapAckBlockEnd();
                    } else if (SctpDataChunk.compareTsns(sctpGapAckBlock.getAbsoluteGapAckBlockEnd(), this.__cumulativeACKPoint) == 1) {
                        ArrayList<SctpGapAckBlock> arrayList2 = this.__gapAckBlocks;
                        ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(arrayList2, i, ArrayListExtensions.getCount(arrayList2) - i));
                        z = true;
                    }
                }
                this.__gapAckBlocks = arrayList;
            }
        }
    }

    public boolean remove(long j) {
        boolean remove;
        synchronized (this.__lock) {
            remove = super.remove(j);
            if (remove) {
                ArrayList<SctpGapAckBlock> arrayList = null;
                boolean z = false;
                int i = 0;
                while (!z) {
                    if (i < ArrayListExtensions.getCount(this.__gapAckBlocks)) {
                        SctpGapAckBlock sctpGapAckBlock = (SctpGapAckBlock) ArrayListExtensions.getItem(this.__gapAckBlocks).get(i);
                        if (sctpGapAckBlock.getAbsoluteGapAckBlockStart() == j) {
                            arrayList = new ArrayList<>();
                            ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(this.__gapAckBlocks, 0, i));
                            if (sctpGapAckBlock.getAbsoluteGapAckBlockStart() != sctpGapAckBlock.getAbsoluteGapAckBlockEnd()) {
                                sctpGapAckBlock.setAbsoluteGapAckBlockStart(SctpDataChunk.incrementTSN(sctpGapAckBlock.getAbsoluteGapAckBlockStart()));
                                arrayList.add(sctpGapAckBlock);
                            }
                            ArrayList<SctpGapAckBlock> arrayList2 = this.__gapAckBlocks;
                            ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(arrayList2, i + 1, (ArrayListExtensions.getCount(arrayList2) - i) - 1));
                        } else if (sctpGapAckBlock.getAbsoluteGapAckBlockEnd() == j) {
                            arrayList = new ArrayList<>();
                            ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(this.__gapAckBlocks, 0, i));
                            sctpGapAckBlock.setAbsoluteGapAckBlockEnd(SctpDataChunk.decrementTSN(sctpGapAckBlock.getAbsoluteGapAckBlockEnd()));
                            arrayList.add(sctpGapAckBlock);
                            ArrayList<SctpGapAckBlock> arrayList3 = this.__gapAckBlocks;
                            ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(arrayList3, i + 1, (ArrayListExtensions.getCount(arrayList3) - i) - 1));
                        } else {
                            if (SctpDataChunk.compareTsns(sctpGapAckBlock.getAbsoluteGapAckBlockStart(), j) == 2 && SctpDataChunk.compareTsns(sctpGapAckBlock.getAbsoluteGapAckBlockEnd(), j) == 1) {
                                arrayList = new ArrayList<>();
                                ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(this.__gapAckBlocks, 0, i));
                                long absoluteGapAckBlockEnd = sctpGapAckBlock.getAbsoluteGapAckBlockEnd();
                                sctpGapAckBlock.setAbsoluteGapAckBlockEnd(SctpDataChunk.decrementTSN(j));
                                arrayList.add(sctpGapAckBlock);
                                arrayList.add(new SctpGapAckBlock(SctpDataChunk.incrementTSN(j), absoluteGapAckBlockEnd));
                                ArrayList<SctpGapAckBlock> arrayList4 = this.__gapAckBlocks;
                                ArrayListExtensions.addRange(arrayList, ArrayListExtensions.getRange(arrayList4, i + 1, (ArrayListExtensions.getCount(arrayList4) - i) - 1));
                            }
                            i++;
                        }
                    }
                    z = true;
                    i++;
                }
                if (arrayList != null) {
                    this.__gapAckBlocks = arrayList;
                }
            }
        }
        return remove;
    }

    public void removeAll() {
        synchronized (this.__lock) {
            super.removeAll();
        }
    }

    public SctpReceiveDataQueue(Object obj) {
        this.__lock = obj;
        this.__gapAckBlocks = new ArrayList<>();
    }

    /* access modifiers changed from: package-private */
    public void setInitialTSN(long j) {
        this.__initialTSN = j;
        this.__cumulativeACKPoint = SctpDataChunk.decrementTSN(j);
    }

    private boolean tryUpdateCumulativeACKPoint(long j) {
        long j2 = this.__cumulativeACKPoint;
        if (j2 == -1) {
            throw new RuntimeException(new Exception("Initial TSN was not set for SCTP Receive DATA Queue."));
        } else if (SctpDataChunk.compareTsns(SctpDataChunk.incrementTSN(j2), j) != 0) {
            return false;
        } else {
            this.__cumulativeACKPoint = j;
            return true;
        }
    }
}
