package fm.liveswitch;

import java.util.ArrayList;

class SctpSendDataQueue extends SctpDataQueue {
    private static ILog __log = Log.getLogger(SctpSendDataQueue.class);
    private long __cwnd = -1;
    private volatile boolean __dirty = true;
    private long __greatestTsnAdded = -1;
    private SctpSackChunk __oldSackChunk;

    public void add(SctpDataChunk sctpDataChunk) {
        synchronized (this.__lock) {
            this.__dirty = true;
            if (__log.getIsVerboseEnabled()) {
                __log.verbose(StringExtensions.format("SCTP SendDataQueue: adding data chunk with TSN {0}.", (Object) LongExtensions.toString(Long.valueOf(sctpDataChunk.getTsn()))));
            }
            this.__greatestTsnAdded = SctpDataChunk.maxTsns(this.__greatestTsnAdded, sctpDataChunk.getTsn());
            super.add(sctpDataChunk);
        }
    }

    private void calculateCWND() {
        long j;
        synchronized (this.__lock) {
            SctpSackChunk sctpSackChunk = this.__oldSackChunk;
            if (sctpSackChunk == null) {
                LinkedListNode<SctpDataChunk> first = this.__tsnDataLinkedList.getFirst();
                j = 0;
                while (first != null && first.getValue().getTransmissionTime() > 0) {
                    j++;
                    first = first.getNext();
                }
            } else {
                long cumulativeTsnAck = sctpSackChunk.getCumulativeTsnAck();
                long j2 = 0;
                for (SctpGapAckBlock sctpGapAckBlock : this.__oldSackChunk.getGapAckBlocks()) {
                    long absoluteGapAckBlockStart = sctpGapAckBlock.getAbsoluteGapAckBlockStart();
                    while (true) {
                        cumulativeTsnAck = SctpDataChunk.incrementTSN(cumulativeTsnAck);
                        if (SctpDataChunk.compareTsns(cumulativeTsnAck, absoluteGapAckBlockStart) != 2) {
                            break;
                        }
                        SctpDataChunk chunk = super.getChunk(cumulativeTsnAck);
                        if (chunk != null && chunk.getTransmissionTime() > 0) {
                            j2++;
                        }
                    }
                    cumulativeTsnAck = sctpGapAckBlock.getAbsoluteGapAckBlockEnd();
                }
                SctpDataChunk chunk2 = super.getChunk(SctpDataChunk.incrementTSN(cumulativeTsnAck));
                long j3 = j2;
                while (chunk2 != null && chunk2.getTransmissionTime() > 0) {
                    j3 = j + 1;
                    chunk2 = super.getNextChunk(chunk2.getTsn());
                }
            }
            this.__cwnd = j;
            this.__dirty = false;
        }
    }

    public long getAllAckedUpTo() {
        SctpSackChunk sctpSackChunk = this.__oldSackChunk;
        if (sctpSackChunk != null) {
            return sctpSackChunk.getCumulativeTsnAck();
        }
        return -1;
    }

    public boolean getAllSentAcked() {
        return getCwnd() == 0;
    }

    public long getCwnd() {
        long j;
        synchronized (this.__lock) {
            if (this.__dirty) {
                calculateCWND();
            }
            j = this.__cwnd;
        }
        return j;
    }

    public SctpDataChunk getFirstUnAcked() {
        return super.getNextChunk(getAllAckedUpTo());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0059, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getNonsentDataAvailable() {
        /*
            r9 = this;
            java.lang.Object r0 = r9.__lock
            monitor-enter(r0)
            fm.liveswitch.SctpSackChunk r1 = r9.__oldSackChunk     // Catch:{ all -> 0x005a }
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0014
            fm.liveswitch.LinkedList<fm.liveswitch.SctpDataChunk> r1 = r9.__tsnDataLinkedList     // Catch:{ all -> 0x005a }
            int r1 = r1.getCount()     // Catch:{ all -> 0x005a }
            if (r1 == 0) goto L_0x0012
            r2 = 1
        L_0x0012:
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            return r2
        L_0x0014:
            long r4 = r1.getCumulativeTsnAck()     // Catch:{ all -> 0x005a }
            fm.liveswitch.SctpSackChunk r1 = r9.__oldSackChunk     // Catch:{ all -> 0x005a }
            fm.liveswitch.SctpGapAckBlock[] r1 = r1.getGapAckBlocks()     // Catch:{ all -> 0x005a }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ all -> 0x005a }
            if (r1 <= 0) goto L_0x003b
            fm.liveswitch.SctpSackChunk r1 = r9.__oldSackChunk     // Catch:{ all -> 0x005a }
            fm.liveswitch.SctpGapAckBlock[] r1 = r1.getGapAckBlocks()     // Catch:{ all -> 0x005a }
            fm.liveswitch.SctpSackChunk r4 = r9.__oldSackChunk     // Catch:{ all -> 0x005a }
            fm.liveswitch.SctpGapAckBlock[] r4 = r4.getGapAckBlocks()     // Catch:{ all -> 0x005a }
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r4)     // Catch:{ all -> 0x005a }
            int r4 = r4 - r3
            r1 = r1[r4]     // Catch:{ all -> 0x005a }
            long r4 = r1.getAbsoluteGapAckBlockEnd()     // Catch:{ all -> 0x005a }
        L_0x003b:
            fm.liveswitch.SctpDataChunk r1 = super.getNextChunk(r4)     // Catch:{ all -> 0x005a }
        L_0x003f:
            if (r1 == 0) goto L_0x0058
            if (r2 != 0) goto L_0x0058
            long r4 = r1.getTransmissionTime()     // Catch:{ all -> 0x005a }
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 > 0) goto L_0x004f
            r2 = 1
            goto L_0x003f
        L_0x004f:
            long r4 = r1.getTsn()     // Catch:{ all -> 0x005a }
            fm.liveswitch.SctpDataChunk r1 = super.getNextChunk(r4)     // Catch:{ all -> 0x005a }
            goto L_0x003f
        L_0x0058:
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            return r2
        L_0x005a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SctpSendDataQueue.getNonsentDataAvailable():boolean");
    }

    public long getNotAckedPast() {
        SctpSackChunk sctpSackChunk = this.__oldSackChunk;
        if (sctpSackChunk == null) {
            return -1;
        }
        int numberOfGapAckBlocks = sctpSackChunk.getNumberOfGapAckBlocks();
        if (numberOfGapAckBlocks == 0) {
            return this.__oldSackChunk.getCumulativeTsnAck();
        }
        return this.__oldSackChunk.getGapAckBlocks()[numberOfGapAckBlocks - 1].getAbsoluteGapAckBlockEnd();
    }

    private void markAs(boolean z, long j, long j2) {
        if (SctpDataChunk.compareTsns(j, j2) != 1) {
            while (SctpDataChunk.compareTsns(j, j2) != 1) {
                SctpDataChunk chunk = super.getChunk(j);
                if (chunk != null) {
                    chunk.setAcked(z);
                }
                j = SctpDataChunk.incrementTSN(j);
            }
        }
    }

    public void markChunkTransmitted(SctpDataChunk sctpDataChunk) {
        synchronized (this.__lock) {
            this.__dirty = true;
            sctpDataChunk.setTransmissionTime(Scheduler.getCurrentTime());
        }
    }

    /* access modifiers changed from: package-private */
    public SctpDataChunk[] processFullyAckedMessages(long j, long j2, boolean z) {
        long j3;
        SctpDataChunk sctpDataChunk;
        boolean z2;
        SctpDataChunk sctpDataChunk2;
        boolean z3;
        long j4;
        long j5;
        long j6 = j2;
        ArrayList arrayList = new ArrayList();
        SctpDataChunk chunk = super.getChunk(j);
        boolean z4 = true;
        if (chunk == null) {
            j3 = SctpDataChunk.incrementTSN(j);
            while (chunk == null && SctpDataChunk.compareTsns(j3, j6) != 1 && ArrayExtensions.getLength(super.getTsns()) > 0) {
                chunk = super.getChunk(j3);
                j3 = SctpDataChunk.incrementTSN(j3);
            }
            if (chunk != null) {
                j3 = chunk.getTsn();
            }
        } else {
            j3 = j;
        }
        if (sctpDataChunk == null) {
            return new SctpDataChunk[0];
        }
        while (sctpDataChunk != null && SctpDataChunk.compareTsns(sctpDataChunk.getTsn(), j6) != z2) {
            sctpDataChunk.setAcked(z2);
            if (sctpDataChunk.getUnordered() || !z) {
                boolean beginning = sctpDataChunk.getBeginning();
                if (beginning) {
                    sctpDataChunk2 = sctpDataChunk;
                } else {
                    sctpDataChunk2 = null;
                    boolean z5 = false;
                    while (!beginning && !z5) {
                        SctpDataChunk previousChunk = super.getPreviousChunk(j3);
                        if (previousChunk == null || !previousChunk.getAcked()) {
                            z5 = true;
                        } else {
                            if (previousChunk.getBeginning()) {
                                sctpDataChunk2 = previousChunk;
                                beginning = true;
                            }
                            j3 = previousChunk.getTsn();
                        }
                    }
                }
                if (!beginning) {
                    boolean z6 = true;
                    while (z6) {
                        sctpDataChunk = super.getNextChunk(sctpDataChunk.getTsn());
                        if (sctpDataChunk != null) {
                            j3 = sctpDataChunk.getTsn();
                            if (SctpDataChunk.compareTsns(j3, j6) != z2) {
                                sctpDataChunk.setAcked(z2);
                                if (sctpDataChunk.getBeginning()) {
                                    sctpDataChunk2 = sctpDataChunk;
                                    beginning = true;
                                }
                            }
                        }
                        z6 = false;
                    }
                }
                if (beginning) {
                    if (sctpDataChunk2.getEnding()) {
                        long tsn = sctpDataChunk2.getTsn();
                        if (sctpDataChunk2.getUnordered() || !z) {
                            arrayList.add(sctpDataChunk2);
                            remove(tsn);
                        }
                        sctpDataChunk = super.getNextChunk(sctpDataChunk2.getTsn());
                        if (sctpDataChunk != null) {
                            tsn = sctpDataChunk.getTsn();
                        }
                        z3 = true;
                    } else {
                        sctpDataChunk = sctpDataChunk2;
                        z3 = false;
                    }
                    boolean z7 = false;
                    while (!z3 && !z7) {
                        SctpDataChunk nextChunk = super.getNextChunk(sctpDataChunk.getTsn());
                        if (nextChunk != null) {
                            j3 = nextChunk.getTsn();
                        }
                        if (nextChunk == null || SctpDataChunk.compareTsns(j3, j6) == z2) {
                            j4 = j3;
                            nextChunk = null;
                            z7 = true;
                        } else {
                            nextChunk.setAcked(z2);
                            if (nextChunk.getEnding()) {
                                if (nextChunk.getUnordered() || !z) {
                                    arrayList.add(nextChunk);
                                    j5 = j3;
                                    removeFromQueue(sctpDataChunk2.getTsn(), nextChunk.getTsn());
                                } else {
                                    j5 = j3;
                                }
                                nextChunk = super.getNextChunk(nextChunk.getTsn());
                                long j7 = nextChunk != null ? nextChunk.getTsn() : j5;
                                z2 = true;
                                z3 = true;
                            } else {
                                j4 = nextChunk != null ? nextChunk.getTsn() : j3;
                            }
                        }
                        z2 = true;
                    }
                }
            } else {
                sctpDataChunk = super.getNextChunk(sctpDataChunk.getTsn());
                if (sctpDataChunk != null) {
                    j3 = sctpDataChunk.getTsn();
                }
            }
            z4 = true;
        }
        return (SctpDataChunk[]) arrayList.toArray(new SctpDataChunk[0]);
    }

    public SctpDataChunk[] processSackChunk(SctpSackChunk sctpSackChunk) {
        SctpSendDataQueue sctpSendDataQueue;
        SctpSackChunk sctpSackChunk2;
        int i;
        long j;
        int i2;
        int i3;
        long j2;
        int i4;
        long cumulativeTsnAck = sctpSackChunk.getCumulativeTsnAck();
        SctpGapAckBlock[] gapAckBlocks = sctpSackChunk.getGapAckBlocks();
        int numberOfGapAckBlocks = sctpSackChunk.getNumberOfGapAckBlocks();
        ArrayList arrayList = new ArrayList();
        if (__log.getIsVerboseEnabled()) {
            SctpSackChunk sctpSackChunk3 = this.__oldSackChunk;
            if (sctpSackChunk3 != null) {
                __log.verbose(StringExtensions.format("SCTP SendDataQueue: current SACK state {0}", (Object) sctpSackChunk3.toString()));
            } else {
                __log.verbose("SCTP SendDataQueue: current SACK state: nothing has been acknowledged yet.");
            }
            __log.verbose(StringExtensions.format("SCTP SendDataQueue: received {0}.", (Object) sctpSackChunk.toString()));
        }
        synchronized (this.__lock) {
            try {
                if (this.__oldSackChunk == null) {
                    this.__dirty = true;
                    if (SctpDataChunk.compareTsns(super.getEarliestTSN(), cumulativeTsnAck) != 1) {
                        ArrayListExtensions.addRange(arrayList, (T[]) processFullyAckedMessages(super.getEarliestTSN(), cumulativeTsnAck, false));
                    }
                    SctpDataChunk nextChunk = super.getNextChunk(cumulativeTsnAck);
                    int i5 = 0;
                    while (nextChunk != null && i5 < numberOfGapAckBlocks) {
                        if (SctpDataChunk.compareTsns(gapAckBlocks[i5].getAbsoluteGapAckBlockStart(), nextChunk.getTsn()) == 1) {
                            nextChunk = super.getNextChunk(nextChunk.getTsn());
                        } else if (SctpDataChunk.compareTsns(gapAckBlocks[i5].getAbsoluteGapAckBlockStart(), nextChunk.getTsn()) == 1 || SctpDataChunk.compareTsns(gapAckBlocks[i5].getAbsoluteGapAckBlockEnd(), nextChunk.getTsn()) == 2) {
                            ArrayListExtensions.addRange(arrayList, (T[]) processFullyAckedMessages(gapAckBlocks[i5].getAbsoluteGapAckBlockStart(), gapAckBlocks[i5].getAbsoluteGapAckBlockEnd(), true));
                            i5++;
                        } else {
                            nextChunk.setAcked(true);
                            nextChunk = super.getNextChunk(nextChunk.getTsn());
                        }
                    }
                    sctpSackChunk2 = sctpSackChunk;
                    sctpSendDataQueue = this;
                } else {
                    this.__dirty = true;
                    long cumulativeTsnAck2 = this.__oldSackChunk.getCumulativeTsnAck();
                    SctpGapAckBlock[] gapAckBlocks2 = this.__oldSackChunk.getGapAckBlocks();
                    int numberOfGapAckBlocks2 = this.__oldSackChunk.getNumberOfGapAckBlocks();
                    if (SctpDataChunk.compareTsns(cumulativeTsnAck2, cumulativeTsnAck) == 2) {
                        j = cumulativeTsnAck2;
                        i = numberOfGapAckBlocks2;
                        ArrayListExtensions.addRange(arrayList, (T[]) processFullyAckedMessages(SctpDataChunk.incrementTSN(cumulativeTsnAck2), cumulativeTsnAck, false));
                        boolean z = false;
                        i2 = 0;
                        for (int i6 = 0; i6 < i && !z; i6++) {
                            if (SctpDataChunk.compareTsns(gapAckBlocks2[i6].getAbsoluteGapAckBlockEnd(), cumulativeTsnAck) == 1) {
                                i2 = i6;
                                z = true;
                            } else {
                                int i7 = i6 + 1;
                                if (i7 >= i) {
                                    i2 = i7;
                                }
                            }
                        }
                    } else {
                        j = cumulativeTsnAck2;
                        i = numberOfGapAckBlocks2;
                        if (SctpDataChunk.compareTsns(j, cumulativeTsnAck) == 1) {
                            long minTsns = ArrayExtensions.getLength((Object[]) gapAckBlocks) > 0 ? SctpDataChunk.minTsns(SctpDataChunk.decrementTSN(gapAckBlocks[0].getAbsoluteGapAckBlockStart()), j) : j;
                            long minTsns2 = SctpDataChunk.minTsns(super.getEarliestTSN(), SctpDataChunk.incrementTSN(cumulativeTsnAck));
                            if (SctpDataChunk.compareTsns(minTsns2, minTsns) != 1) {
                                cumulativeTsnAck = minTsns;
                                markAs(false, minTsns2, minTsns);
                            } else {
                                cumulativeTsnAck = minTsns;
                            }
                        }
                        i2 = 0;
                    }
                    long j3 = cumulativeTsnAck;
                    int i8 = 0;
                    int i9 = i2;
                    while (i8 < numberOfGapAckBlocks) {
                        try {
                            long absoluteGapAckBlockStart = gapAckBlocks[i8].getAbsoluteGapAckBlockStart();
                            long absoluteGapAckBlockEnd = gapAckBlocks[i8].getAbsoluteGapAckBlockEnd();
                            long incrementTSN = SctpDataChunk.incrementTSN(j3);
                            int i10 = i9;
                            int i11 = numberOfGapAckBlocks;
                            long decrementTSN = SctpDataChunk.decrementTSN(absoluteGapAckBlockStart);
                            if (SctpDataChunk.compareTsns(incrementTSN, j) != 2) {
                                i3 = i8;
                                j2 = absoluteGapAckBlockEnd;
                            } else if (SctpDataChunk.compareTsns(decrementTSN, j) != 1) {
                                i3 = i8;
                                j2 = absoluteGapAckBlockEnd;
                                markAs(false, incrementTSN, decrementTSN);
                                j3 = decrementTSN;
                            } else {
                                i3 = i8;
                                j2 = absoluteGapAckBlockEnd;
                                markAs(false, incrementTSN, j);
                                j3 = j;
                            }
                            int i12 = i10;
                            boolean z2 = false;
                            while (i12 < i && !z2) {
                                long j4 = j;
                                long maxTsns = SctpDataChunk.maxTsns(gapAckBlocks2[i12].getAbsoluteGapAckBlockStart(), SctpDataChunk.incrementTSN(j3));
                                if (SctpDataChunk.compareTsns(decrementTSN, maxTsns) != 2) {
                                    long minTsns3 = SctpDataChunk.minTsns(decrementTSN, gapAckBlocks2[i12].getAbsoluteGapAckBlockEnd());
                                    long j5 = maxTsns;
                                    i4 = i12;
                                    markAs(false, j5, minTsns3);
                                    j3 = minTsns3;
                                } else {
                                    i4 = i12;
                                }
                                if (SctpDataChunk.compareTsns(gapAckBlocks2[i4].getAbsoluteGapAckBlockEnd(), decrementTSN) != 2) {
                                    i10 = i4;
                                    z2 = true;
                                } else {
                                    int i13 = i4 + 1;
                                    if (i13 >= i) {
                                        i10 = i13;
                                    }
                                }
                                i12 = i4 + 1;
                                j = j4;
                            }
                            long j6 = j;
                            long j7 = j3;
                            long j8 = absoluteGapAckBlockStart;
                            int i14 = i10;
                            boolean z3 = false;
                            while (i14 < i && !z3) {
                                if (SctpDataChunk.compareTsns(j8, gapAckBlocks2[i14].getAbsoluteGapAckBlockStart()) == 2 && SctpDataChunk.compareTsns(j2, gapAckBlocks2[i14].getAbsoluteGapAckBlockStart()) != 2) {
                                    long decrementTSN2 = SctpDataChunk.decrementTSN(gapAckBlocks2[i14].getAbsoluteGapAckBlockStart());
                                    if (SctpDataChunk.compareTsns(decrementTSN2, j7) == 1) {
                                        markAs(true, j8, decrementTSN2);
                                        j7 = decrementTSN2;
                                    }
                                }
                                if (SctpDataChunk.compareTsns(j2, gapAckBlocks2[i14].getAbsoluteGapAckBlockEnd()) == 1) {
                                    long incrementTSN2 = SctpDataChunk.incrementTSN(gapAckBlocks2[i14].getAbsoluteGapAckBlockEnd());
                                    j7 = gapAckBlocks2[i14].getAbsoluteGapAckBlockEnd();
                                    int i15 = i14 + 1;
                                    if (i15 >= i || SctpDataChunk.compareTsns(j2, gapAckBlocks2[i15].getAbsoluteGapAckBlockEnd()) != 1) {
                                        j8 = incrementTSN2;
                                    } else {
                                        long decrementTSN3 = SctpDataChunk.decrementTSN(gapAckBlocks2[i15].getAbsoluteGapAckBlockStart());
                                        markAs(true, incrementTSN2, decrementTSN3);
                                        j8 = incrementTSN2;
                                        j7 = decrementTSN3;
                                    }
                                } else {
                                    j8 = SctpDataChunk.incrementTSN(j2);
                                }
                                int i16 = i14 + 1;
                                boolean z4 = i16 < i;
                                if (SctpDataChunk.compareTsns(gapAckBlocks2[i14].getAbsoluteGapAckBlockEnd(), j2) == 2) {
                                    if (z4) {
                                        if (SctpDataChunk.compareTsns(gapAckBlocks2[i16].getAbsoluteGapAckBlockStart(), j2) == 1) {
                                        }
                                    }
                                    if (i16 >= i) {
                                        i10 = i16;
                                    }
                                    i14 = i16;
                                }
                                i10 = i16;
                                z3 = true;
                                i14 = i16;
                            }
                            markAs(true, j8, j2);
                            ArrayListExtensions.addRange(arrayList, (T[]) processFullyAckedMessages(absoluteGapAckBlockStart, j2, true));
                            j3 = j2;
                            numberOfGapAckBlocks = i11;
                            i9 = i10;
                            j = j6;
                            i8 = i3 + 1;
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    }
                    int i17 = i9;
                    long j9 = j3;
                    while (i9 < i) {
                        markAs(false, SctpDataChunk.maxTsns(SctpDataChunk.incrementTSN(j9), gapAckBlocks2[i9].getAbsoluteGapAckBlockStart()), gapAckBlocks2[i9].getAbsoluteGapAckBlockEnd());
                        j9 = SctpDataChunk.maxTsns(gapAckBlocks2[i9].getAbsoluteGapAckBlockEnd(), SctpDataChunk.maxTsns(j9, gapAckBlocks2[i9].getAbsoluteGapAckBlockStart()));
                        i9++;
                    }
                    sctpSendDataQueue = this;
                    sctpSackChunk2 = sctpSackChunk;
                }
                try {
                    sctpSendDataQueue.__oldSackChunk = sctpSackChunk2;
                    return (SctpDataChunk[]) arrayList.toArray(new SctpDataChunk[0]);
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    public void purge(long j) {
        synchronized (this.__lock) {
            if (__log.getIsVerboseEnabled()) {
                __log.verbose(StringExtensions.format("SCTP SendDataQueue: purging data chunks with TSN prior to and including {0}", (Object) LongExtensions.toString(Long.valueOf(j))));
            }
            super.purge(j);
        }
    }

    public boolean remove(long j) {
        boolean remove;
        synchronized (this.__lock) {
            this.__dirty = true;
            if (__log.getIsVerboseEnabled()) {
                __log.verbose(StringExtensions.format("SCTP SendDataQueue: removing data chunk with TSN {0}.", (Object) LongExtensions.toString(Long.valueOf(j))));
            }
            remove = super.remove(j);
        }
        return remove;
    }

    public void removeAll() {
        synchronized (this.__lock) {
            super.removeAll();
        }
    }

    private void removeFromQueue(long j, long j2) {
        while (SctpDataChunk.compareTsns(j, j2) != 1) {
            remove(j);
            j = SctpDataChunk.incrementTSN(j);
        }
    }

    public SctpSendDataQueue(Object obj) {
        this.__lock = obj;
    }
}
