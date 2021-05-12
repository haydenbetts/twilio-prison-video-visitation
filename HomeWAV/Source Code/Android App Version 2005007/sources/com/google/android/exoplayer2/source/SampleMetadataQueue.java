package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

final class SampleMetadataQueue {
    private static final int SAMPLE_CAPACITY_INCREMENT = 1000;
    private int absoluteFirstIndex;
    private int capacity = 1000;
    private TrackOutput.CryptoData[] cryptoDatas = new TrackOutput.CryptoData[1000];
    private int[] flags = new int[1000];
    private Format[] formats = new Format[1000];
    private long largestDiscardedTimestampUs = Long.MIN_VALUE;
    private long largestQueuedTimestampUs = Long.MIN_VALUE;
    private int length;
    private long[] offsets = new long[1000];
    private int readPosition;
    private int relativeFirstIndex;
    private int[] sizes = new int[1000];
    private int[] sourceIds = new int[1000];
    private long[] timesUs = new long[1000];
    private Format upstreamFormat;
    private boolean upstreamFormatRequired = true;
    private boolean upstreamKeyframeRequired = true;
    private int upstreamSourceId;

    public static final class SampleExtrasHolder {
        public TrackOutput.CryptoData cryptoData;
        public long offset;
        public int size;
    }

    public void reset(boolean z) {
        this.length = 0;
        this.absoluteFirstIndex = 0;
        this.relativeFirstIndex = 0;
        this.readPosition = 0;
        this.upstreamKeyframeRequired = true;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        if (z) {
            this.upstreamFormat = null;
            this.upstreamFormatRequired = true;
        }
    }

    public int getWriteIndex() {
        return this.absoluteFirstIndex + this.length;
    }

    public long discardUpstreamSamples(int i) {
        int writeIndex = getWriteIndex() - i;
        Assertions.checkArgument(writeIndex >= 0 && writeIndex <= this.length - this.readPosition);
        int i2 = this.length - writeIndex;
        this.length = i2;
        this.largestQueuedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i2));
        int i3 = this.length;
        if (i3 == 0) {
            return 0;
        }
        int relativeIndex = getRelativeIndex(i3 - 1);
        return this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]);
    }

    public void sourceId(int i) {
        this.upstreamSourceId = i;
    }

    public int getFirstIndex() {
        return this.absoluteFirstIndex;
    }

    public int getReadIndex() {
        return this.absoluteFirstIndex + this.readPosition;
    }

    public int peekSourceId() {
        return hasNextSample() ? this.sourceIds[getRelativeIndex(this.readPosition)] : this.upstreamSourceId;
    }

    public synchronized boolean hasNextSample() {
        return this.readPosition != this.length;
    }

    public synchronized Format getUpstreamFormat() {
        return this.upstreamFormatRequired ? null : this.upstreamFormat;
    }

    public synchronized long getLargestQueuedTimestampUs() {
        return this.largestQueuedTimestampUs;
    }

    public synchronized long getFirstTimestampUs() {
        return this.length == 0 ? Long.MIN_VALUE : this.timesUs[this.relativeFirstIndex];
    }

    public synchronized void rewind() {
        this.readPosition = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(com.google.android.exoplayer2.FormatHolder r5, com.google.android.exoplayer2.decoder.DecoderInputBuffer r6, boolean r7, boolean r8, com.google.android.exoplayer2.Format r9, com.google.android.exoplayer2.source.SampleMetadataQueue.SampleExtrasHolder r10) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.hasNextSample()     // Catch:{ all -> 0x0066 }
            r1 = -5
            r2 = -3
            r3 = -4
            if (r0 != 0) goto L_0x0020
            if (r8 == 0) goto L_0x0012
            r5 = 4
            r6.setFlags(r5)     // Catch:{ all -> 0x0066 }
            monitor-exit(r4)
            return r3
        L_0x0012:
            com.google.android.exoplayer2.Format r6 = r4.upstreamFormat     // Catch:{ all -> 0x0066 }
            if (r6 == 0) goto L_0x001e
            if (r7 != 0) goto L_0x001a
            if (r6 == r9) goto L_0x001e
        L_0x001a:
            r5.format = r6     // Catch:{ all -> 0x0066 }
            monitor-exit(r4)
            return r1
        L_0x001e:
            monitor-exit(r4)
            return r2
        L_0x0020:
            int r8 = r4.readPosition     // Catch:{ all -> 0x0066 }
            int r8 = r4.getRelativeIndex(r8)     // Catch:{ all -> 0x0066 }
            if (r7 != 0) goto L_0x005e
            com.google.android.exoplayer2.Format[] r7 = r4.formats     // Catch:{ all -> 0x0066 }
            r7 = r7[r8]     // Catch:{ all -> 0x0066 }
            if (r7 == r9) goto L_0x002f
            goto L_0x005e
        L_0x002f:
            boolean r5 = r6.isFlagsOnly()     // Catch:{ all -> 0x0066 }
            if (r5 == 0) goto L_0x0037
            monitor-exit(r4)
            return r2
        L_0x0037:
            long[] r5 = r4.timesUs     // Catch:{ all -> 0x0066 }
            r0 = r5[r8]     // Catch:{ all -> 0x0066 }
            r6.timeUs = r0     // Catch:{ all -> 0x0066 }
            int[] r5 = r4.flags     // Catch:{ all -> 0x0066 }
            r5 = r5[r8]     // Catch:{ all -> 0x0066 }
            r6.setFlags(r5)     // Catch:{ all -> 0x0066 }
            int[] r5 = r4.sizes     // Catch:{ all -> 0x0066 }
            r5 = r5[r8]     // Catch:{ all -> 0x0066 }
            r10.size = r5     // Catch:{ all -> 0x0066 }
            long[] r5 = r4.offsets     // Catch:{ all -> 0x0066 }
            r6 = r5[r8]     // Catch:{ all -> 0x0066 }
            r10.offset = r6     // Catch:{ all -> 0x0066 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r5 = r4.cryptoDatas     // Catch:{ all -> 0x0066 }
            r5 = r5[r8]     // Catch:{ all -> 0x0066 }
            r10.cryptoData = r5     // Catch:{ all -> 0x0066 }
            int r5 = r4.readPosition     // Catch:{ all -> 0x0066 }
            int r5 = r5 + 1
            r4.readPosition = r5     // Catch:{ all -> 0x0066 }
            monitor-exit(r4)
            return r3
        L_0x005e:
            com.google.android.exoplayer2.Format[] r6 = r4.formats     // Catch:{ all -> 0x0066 }
            r6 = r6[r8]     // Catch:{ all -> 0x0066 }
            r5.format = r6     // Catch:{ all -> 0x0066 }
            monitor-exit(r4)
            return r1
        L_0x0066:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.read(com.google.android.exoplayer2.FormatHolder, com.google.android.exoplayer2.decoder.DecoderInputBuffer, boolean, boolean, com.google.android.exoplayer2.Format, com.google.android.exoplayer2.source.SampleMetadataQueue$SampleExtrasHolder):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int advanceTo(long r9, boolean r11, boolean r12) {
        /*
            r8 = this;
            monitor-enter(r8)
            int r0 = r8.readPosition     // Catch:{ all -> 0x0039 }
            int r2 = r8.getRelativeIndex(r0)     // Catch:{ all -> 0x0039 }
            boolean r0 = r8.hasNextSample()     // Catch:{ all -> 0x0039 }
            r7 = -1
            if (r0 == 0) goto L_0x0037
            long[] r0 = r8.timesUs     // Catch:{ all -> 0x0039 }
            r3 = r0[r2]     // Catch:{ all -> 0x0039 }
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 < 0) goto L_0x0037
            long r0 = r8.largestQueuedTimestampUs     // Catch:{ all -> 0x0039 }
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x001f
            if (r12 != 0) goto L_0x001f
            goto L_0x0037
        L_0x001f:
            int r12 = r8.length     // Catch:{ all -> 0x0039 }
            int r0 = r8.readPosition     // Catch:{ all -> 0x0039 }
            int r3 = r12 - r0
            r1 = r8
            r4 = r9
            r6 = r11
            int r9 = r1.findSampleBefore(r2, r3, r4, r6)     // Catch:{ all -> 0x0039 }
            if (r9 != r7) goto L_0x0030
            monitor-exit(r8)
            return r7
        L_0x0030:
            int r10 = r8.readPosition     // Catch:{ all -> 0x0039 }
            int r10 = r10 + r9
            r8.readPosition = r10     // Catch:{ all -> 0x0039 }
            monitor-exit(r8)
            return r9
        L_0x0037:
            monitor-exit(r8)
            return r7
        L_0x0039:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.advanceTo(long, boolean, boolean):int");
    }

    public synchronized int advanceToEnd() {
        int i;
        int i2 = this.length;
        i = i2 - this.readPosition;
        this.readPosition = i2;
        return i;
    }

    public synchronized boolean setReadPosition(int i) {
        int i2 = this.absoluteFirstIndex;
        if (i2 > i || i > this.length + i2) {
            return false;
        }
        this.readPosition = i - i2;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002e, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long discardTo(long r11, boolean r13, boolean r14) {
        /*
            r10 = this;
            monitor-enter(r10)
            int r0 = r10.length     // Catch:{ all -> 0x002f }
            r1 = -1
            if (r0 == 0) goto L_0x002d
            long[] r3 = r10.timesUs     // Catch:{ all -> 0x002f }
            int r5 = r10.relativeFirstIndex     // Catch:{ all -> 0x002f }
            r6 = r3[r5]     // Catch:{ all -> 0x002f }
            int r3 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r3 >= 0) goto L_0x0012
            goto L_0x002d
        L_0x0012:
            if (r14 == 0) goto L_0x001a
            int r14 = r10.readPosition     // Catch:{ all -> 0x002f }
            if (r14 == r0) goto L_0x001a
            int r0 = r14 + 1
        L_0x001a:
            r6 = r0
            r4 = r10
            r7 = r11
            r9 = r13
            int r11 = r4.findSampleBefore(r5, r6, r7, r9)     // Catch:{ all -> 0x002f }
            r12 = -1
            if (r11 != r12) goto L_0x0027
            monitor-exit(r10)
            return r1
        L_0x0027:
            long r11 = r10.discardSamples(r11)     // Catch:{ all -> 0x002f }
            monitor-exit(r10)
            return r11
        L_0x002d:
            monitor-exit(r10)
            return r1
        L_0x002f:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.discardTo(long, boolean, boolean):long");
    }

    public synchronized long discardToRead() {
        int i = this.readPosition;
        if (i == 0) {
            return -1;
        }
        return discardSamples(i);
    }

    public synchronized long discardToEnd() {
        int i = this.length;
        if (i == 0) {
            return -1;
        }
        return discardSamples(i);
    }

    public synchronized boolean format(Format format) {
        if (format == null) {
            this.upstreamFormatRequired = true;
            return false;
        }
        this.upstreamFormatRequired = false;
        if (Util.areEqual(format, this.upstreamFormat)) {
            return false;
        }
        this.upstreamFormat = format;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00c7, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void commitSample(long r6, int r8, long r9, int r11, com.google.android.exoplayer2.extractor.TrackOutput.CryptoData r12) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.upstreamKeyframeRequired     // Catch:{ all -> 0x00c8 }
            r1 = 0
            if (r0 == 0) goto L_0x000e
            r0 = r8 & 1
            if (r0 != 0) goto L_0x000c
            monitor-exit(r5)
            return
        L_0x000c:
            r5.upstreamKeyframeRequired = r1     // Catch:{ all -> 0x00c8 }
        L_0x000e:
            boolean r0 = r5.upstreamFormatRequired     // Catch:{ all -> 0x00c8 }
            r2 = 1
            if (r0 != 0) goto L_0x0015
            r0 = 1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            com.google.android.exoplayer2.util.Assertions.checkState(r0)     // Catch:{ all -> 0x00c8 }
            r5.commitSampleTimestamp(r6)     // Catch:{ all -> 0x00c8 }
            int r0 = r5.length     // Catch:{ all -> 0x00c8 }
            int r0 = r5.getRelativeIndex(r0)     // Catch:{ all -> 0x00c8 }
            long[] r3 = r5.timesUs     // Catch:{ all -> 0x00c8 }
            r3[r0] = r6     // Catch:{ all -> 0x00c8 }
            long[] r6 = r5.offsets     // Catch:{ all -> 0x00c8 }
            r6[r0] = r9     // Catch:{ all -> 0x00c8 }
            int[] r7 = r5.sizes     // Catch:{ all -> 0x00c8 }
            r7[r0] = r11     // Catch:{ all -> 0x00c8 }
            int[] r7 = r5.flags     // Catch:{ all -> 0x00c8 }
            r7[r0] = r8     // Catch:{ all -> 0x00c8 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r7 = r5.cryptoDatas     // Catch:{ all -> 0x00c8 }
            r7[r0] = r12     // Catch:{ all -> 0x00c8 }
            com.google.android.exoplayer2.Format[] r7 = r5.formats     // Catch:{ all -> 0x00c8 }
            com.google.android.exoplayer2.Format r8 = r5.upstreamFormat     // Catch:{ all -> 0x00c8 }
            r7[r0] = r8     // Catch:{ all -> 0x00c8 }
            int[] r7 = r5.sourceIds     // Catch:{ all -> 0x00c8 }
            int r8 = r5.upstreamSourceId     // Catch:{ all -> 0x00c8 }
            r7[r0] = r8     // Catch:{ all -> 0x00c8 }
            int r7 = r5.length     // Catch:{ all -> 0x00c8 }
            int r7 = r7 + r2
            r5.length = r7     // Catch:{ all -> 0x00c8 }
            int r8 = r5.capacity     // Catch:{ all -> 0x00c8 }
            if (r7 != r8) goto L_0x00c6
            int r7 = r8 + 1000
            int[] r9 = new int[r7]     // Catch:{ all -> 0x00c8 }
            long[] r10 = new long[r7]     // Catch:{ all -> 0x00c8 }
            long[] r11 = new long[r7]     // Catch:{ all -> 0x00c8 }
            int[] r12 = new int[r7]     // Catch:{ all -> 0x00c8 }
            int[] r0 = new int[r7]     // Catch:{ all -> 0x00c8 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r2 = new com.google.android.exoplayer2.extractor.TrackOutput.CryptoData[r7]     // Catch:{ all -> 0x00c8 }
            com.google.android.exoplayer2.Format[] r3 = new com.google.android.exoplayer2.Format[r7]     // Catch:{ all -> 0x00c8 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00c8 }
            int r8 = r8 - r4
            java.lang.System.arraycopy(r6, r4, r10, r1, r8)     // Catch:{ all -> 0x00c8 }
            long[] r6 = r5.timesUs     // Catch:{ all -> 0x00c8 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r6, r4, r11, r1, r8)     // Catch:{ all -> 0x00c8 }
            int[] r6 = r5.flags     // Catch:{ all -> 0x00c8 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r6, r4, r12, r1, r8)     // Catch:{ all -> 0x00c8 }
            int[] r6 = r5.sizes     // Catch:{ all -> 0x00c8 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r6, r4, r0, r1, r8)     // Catch:{ all -> 0x00c8 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r6 = r5.cryptoDatas     // Catch:{ all -> 0x00c8 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r6, r4, r2, r1, r8)     // Catch:{ all -> 0x00c8 }
            com.google.android.exoplayer2.Format[] r6 = r5.formats     // Catch:{ all -> 0x00c8 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r6, r4, r3, r1, r8)     // Catch:{ all -> 0x00c8 }
            int[] r6 = r5.sourceIds     // Catch:{ all -> 0x00c8 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r6, r4, r9, r1, r8)     // Catch:{ all -> 0x00c8 }
            int r6 = r5.relativeFirstIndex     // Catch:{ all -> 0x00c8 }
            long[] r4 = r5.offsets     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r4, r1, r10, r8, r6)     // Catch:{ all -> 0x00c8 }
            long[] r4 = r5.timesUs     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r4, r1, r11, r8, r6)     // Catch:{ all -> 0x00c8 }
            int[] r4 = r5.flags     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r4, r1, r12, r8, r6)     // Catch:{ all -> 0x00c8 }
            int[] r4 = r5.sizes     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r4, r1, r0, r8, r6)     // Catch:{ all -> 0x00c8 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r4 = r5.cryptoDatas     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r4, r1, r2, r8, r6)     // Catch:{ all -> 0x00c8 }
            com.google.android.exoplayer2.Format[] r4 = r5.formats     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r4, r1, r3, r8, r6)     // Catch:{ all -> 0x00c8 }
            int[] r4 = r5.sourceIds     // Catch:{ all -> 0x00c8 }
            java.lang.System.arraycopy(r4, r1, r9, r8, r6)     // Catch:{ all -> 0x00c8 }
            r5.offsets = r10     // Catch:{ all -> 0x00c8 }
            r5.timesUs = r11     // Catch:{ all -> 0x00c8 }
            r5.flags = r12     // Catch:{ all -> 0x00c8 }
            r5.sizes = r0     // Catch:{ all -> 0x00c8 }
            r5.cryptoDatas = r2     // Catch:{ all -> 0x00c8 }
            r5.formats = r3     // Catch:{ all -> 0x00c8 }
            r5.sourceIds = r9     // Catch:{ all -> 0x00c8 }
            r5.relativeFirstIndex = r1     // Catch:{ all -> 0x00c8 }
            int r6 = r5.capacity     // Catch:{ all -> 0x00c8 }
            r5.length = r6     // Catch:{ all -> 0x00c8 }
            r5.capacity = r7     // Catch:{ all -> 0x00c8 }
        L_0x00c6:
            monitor-exit(r5)
            return
        L_0x00c8:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.commitSample(long, int, long, int, com.google.android.exoplayer2.extractor.TrackOutput$CryptoData):void");
    }

    public synchronized void commitSampleTimestamp(long j) {
        this.largestQueuedTimestampUs = Math.max(this.largestQueuedTimestampUs, j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean attemptSplice(long r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            int r0 = r7.length     // Catch:{ all -> 0x0049 }
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0010
            long r3 = r7.largestDiscardedTimestampUs     // Catch:{ all -> 0x0049 }
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x000e
            r1 = 1
        L_0x000e:
            monitor-exit(r7)
            return r1
        L_0x0010:
            long r3 = r7.largestDiscardedTimestampUs     // Catch:{ all -> 0x0049 }
            int r0 = r7.readPosition     // Catch:{ all -> 0x0049 }
            long r5 = r7.getLargestTimestamp(r0)     // Catch:{ all -> 0x0049 }
            long r3 = java.lang.Math.max(r3, r5)     // Catch:{ all -> 0x0049 }
            int r0 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r0 < 0) goto L_0x0022
            monitor-exit(r7)
            return r1
        L_0x0022:
            int r0 = r7.length     // Catch:{ all -> 0x0049 }
            int r1 = r0 + -1
            int r1 = r7.getRelativeIndex(r1)     // Catch:{ all -> 0x0049 }
        L_0x002a:
            int r3 = r7.readPosition     // Catch:{ all -> 0x0049 }
            if (r0 <= r3) goto L_0x0041
            long[] r3 = r7.timesUs     // Catch:{ all -> 0x0049 }
            r4 = r3[r1]     // Catch:{ all -> 0x0049 }
            int r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r3 < 0) goto L_0x0041
            int r0 = r0 + -1
            int r1 = r1 + -1
            r3 = -1
            if (r1 != r3) goto L_0x002a
            int r1 = r7.capacity     // Catch:{ all -> 0x0049 }
            int r1 = r1 - r2
            goto L_0x002a
        L_0x0041:
            int r8 = r7.absoluteFirstIndex     // Catch:{ all -> 0x0049 }
            int r8 = r8 + r0
            r7.discardUpstreamSamples(r8)     // Catch:{ all -> 0x0049 }
            monitor-exit(r7)
            return r2
        L_0x0049:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.attemptSplice(long):boolean");
    }

    private int findSampleBefore(int i, int i2, long j, boolean z) {
        int i3 = -1;
        for (int i4 = 0; i4 < i2 && this.timesUs[i] <= j; i4++) {
            if (!z || (this.flags[i] & 1) != 0) {
                i3 = i4;
            }
            i++;
            if (i == this.capacity) {
                i = 0;
            }
        }
        return i3;
    }

    private long discardSamples(int i) {
        this.largestDiscardedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i));
        int i2 = this.length - i;
        this.length = i2;
        this.absoluteFirstIndex += i;
        int i3 = this.relativeFirstIndex + i;
        this.relativeFirstIndex = i3;
        int i4 = this.capacity;
        if (i3 >= i4) {
            this.relativeFirstIndex = i3 - i4;
        }
        int i5 = this.readPosition - i;
        this.readPosition = i5;
        if (i5 < 0) {
            this.readPosition = 0;
        }
        if (i2 != 0) {
            return this.offsets[this.relativeFirstIndex];
        }
        int i6 = this.relativeFirstIndex;
        if (i6 != 0) {
            i4 = i6;
        }
        int i7 = i4 - 1;
        return this.offsets[i7] + ((long) this.sizes[i7]);
    }

    private long getLargestTimestamp(int i) {
        long j = Long.MIN_VALUE;
        if (i == 0) {
            return Long.MIN_VALUE;
        }
        int relativeIndex = getRelativeIndex(i - 1);
        for (int i2 = 0; i2 < i; i2++) {
            j = Math.max(j, this.timesUs[relativeIndex]);
            if ((this.flags[relativeIndex] & 1) != 0) {
                break;
            }
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return j;
    }

    private int getRelativeIndex(int i) {
        int i2 = this.relativeFirstIndex + i;
        int i3 = this.capacity;
        return i2 < i3 ? i2 : i2 - i3;
    }
}
