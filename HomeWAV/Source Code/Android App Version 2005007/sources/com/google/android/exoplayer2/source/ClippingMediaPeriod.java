package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;

public final class ClippingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    long endUs;
    public final MediaPeriod mediaPeriod;
    private long pendingInitialDiscontinuityPositionUs;
    private ClippingSampleStream[] sampleStreams = new ClippingSampleStream[0];
    long startUs;

    public ClippingMediaPeriod(MediaPeriod mediaPeriod2, boolean z) {
        this.mediaPeriod = mediaPeriod2;
        this.pendingInitialDiscontinuityPositionUs = z ? 0 : -9223372036854775807L;
        this.startUs = C.TIME_UNSET;
        this.endUs = C.TIME_UNSET;
    }

    public void setClipping(long j, long j2) {
        this.startUs = j;
        this.endUs = j2;
    }

    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        this.mediaPeriod.prepare(this, this.startUs + j);
    }

    public void maybeThrowPrepareError() throws IOException {
        this.mediaPeriod.maybeThrowPrepareError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006a, code lost:
        if ((r0.startUs + r3) > r5) goto L_0x006d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long selectTracks(com.google.android.exoplayer2.trackselection.TrackSelection[] r13, boolean[] r14, com.google.android.exoplayer2.source.SampleStream[] r15, boolean[] r16, long r17) {
        /*
            r12 = this;
            r0 = r12
            r1 = r15
            int r2 = r1.length
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r2 = new com.google.android.exoplayer2.source.ClippingMediaPeriod.ClippingSampleStream[r2]
            r0.sampleStreams = r2
            int r2 = r1.length
            com.google.android.exoplayer2.source.SampleStream[] r2 = new com.google.android.exoplayer2.source.SampleStream[r2]
            r10 = 0
            r3 = 0
        L_0x000c:
            int r4 = r1.length
            r11 = 0
            if (r3 >= r4) goto L_0x0025
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r4 = r0.sampleStreams
            r5 = r1[r3]
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream r5 = (com.google.android.exoplayer2.source.ClippingMediaPeriod.ClippingSampleStream) r5
            r4[r3] = r5
            r5 = r4[r3]
            if (r5 == 0) goto L_0x0020
            r4 = r4[r3]
            com.google.android.exoplayer2.source.SampleStream r11 = r4.childStream
        L_0x0020:
            r2[r3] = r11
            int r3 = r3 + 1
            goto L_0x000c
        L_0x0025:
            com.google.android.exoplayer2.source.MediaPeriod r3 = r0.mediaPeriod
            long r4 = r0.startUs
            long r8 = r17 + r4
            r4 = r13
            r5 = r14
            r6 = r2
            r7 = r16
            long r3 = r3.selectTracks(r4, r5, r6, r7, r8)
            long r5 = r0.startUs
            long r3 = r3 - r5
            boolean r5 = r12.isPendingInitialDiscontinuity()
            r6 = 0
            if (r5 == 0) goto L_0x004e
            int r5 = (r17 > r6 ? 1 : (r17 == r6 ? 0 : -1))
            if (r5 != 0) goto L_0x004e
            long r8 = r0.startUs
            r5 = r13
            boolean r5 = shouldKeepInitialDiscontinuity(r8, r13)
            if (r5 == 0) goto L_0x004e
            r8 = r3
            goto L_0x0053
        L_0x004e:
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0053:
            r0.pendingInitialDiscontinuityPositionUs = r8
            int r5 = (r3 > r17 ? 1 : (r3 == r17 ? 0 : -1))
            if (r5 == 0) goto L_0x006f
            int r5 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r5 < 0) goto L_0x006d
            long r5 = r0.endUs
            r7 = -9223372036854775808
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x006f
            long r7 = r0.startUs
            long r7 = r7 + r3
            int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r9 > 0) goto L_0x006d
            goto L_0x006f
        L_0x006d:
            r5 = 0
            goto L_0x0070
        L_0x006f:
            r5 = 1
        L_0x0070:
            com.google.android.exoplayer2.util.Assertions.checkState(r5)
        L_0x0073:
            int r5 = r1.length
            if (r10 >= r5) goto L_0x00a1
            r5 = r2[r10]
            if (r5 != 0) goto L_0x007f
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r5 = r0.sampleStreams
            r5[r10] = r11
            goto L_0x0098
        L_0x007f:
            r5 = r1[r10]
            if (r5 == 0) goto L_0x008d
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r5 = r0.sampleStreams
            r5 = r5[r10]
            com.google.android.exoplayer2.source.SampleStream r5 = r5.childStream
            r6 = r2[r10]
            if (r5 == r6) goto L_0x0098
        L_0x008d:
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r5 = r0.sampleStreams
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream r6 = new com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream
            r7 = r2[r10]
            r6.<init>(r7)
            r5[r10] = r6
        L_0x0098:
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r5 = r0.sampleStreams
            r5 = r5[r10]
            r1[r10] = r5
            int r10 = r10 + 1
            goto L_0x0073
        L_0x00a1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ClippingMediaPeriod.selectTracks(com.google.android.exoplayer2.trackselection.TrackSelection[], boolean[], com.google.android.exoplayer2.source.SampleStream[], boolean[], long):long");
    }

    public void discardBuffer(long j, boolean z) {
        this.mediaPeriod.discardBuffer(j + this.startUs, z);
    }

    public void reevaluateBuffer(long j) {
        this.mediaPeriod.reevaluateBuffer(j + this.startUs);
    }

    public long readDiscontinuity() {
        if (isPendingInitialDiscontinuity()) {
            long j = this.pendingInitialDiscontinuityPositionUs;
            this.pendingInitialDiscontinuityPositionUs = C.TIME_UNSET;
            long readDiscontinuity = readDiscontinuity();
            return readDiscontinuity != C.TIME_UNSET ? readDiscontinuity : j;
        }
        long readDiscontinuity2 = this.mediaPeriod.readDiscontinuity();
        if (readDiscontinuity2 == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        boolean z = true;
        Assertions.checkState(readDiscontinuity2 >= this.startUs);
        long j2 = this.endUs;
        if (j2 != Long.MIN_VALUE && readDiscontinuity2 > j2) {
            z = false;
        }
        Assertions.checkState(z);
        return readDiscontinuity2 - this.startUs;
    }

    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs != Long.MIN_VALUE) {
            long j = this.endUs;
            if (j == Long.MIN_VALUE || bufferedPositionUs < j) {
                return Math.max(0, bufferedPositionUs - this.startUs);
            }
        }
        return Long.MIN_VALUE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        if (r0 > r7) goto L_0x0038;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long seekToUs(long r7) {
        /*
            r6 = this;
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6.pendingInitialDiscontinuityPositionUs = r0
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r0 = r6.sampleStreams
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L_0x000c:
            if (r3 >= r1) goto L_0x0018
            r4 = r0[r3]
            if (r4 == 0) goto L_0x0015
            r4.clearSentEos()
        L_0x0015:
            int r3 = r3 + 1
            goto L_0x000c
        L_0x0018:
            long r0 = r6.startUs
            long r7 = r7 + r0
            com.google.android.exoplayer2.source.MediaPeriod r0 = r6.mediaPeriod
            long r0 = r0.seekToUs(r7)
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0037
            long r7 = r6.startUs
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 < 0) goto L_0x0038
            long r7 = r6.endUs
            r3 = -9223372036854775808
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0037
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 > 0) goto L_0x0038
        L_0x0037:
            r2 = 1
        L_0x0038:
            com.google.android.exoplayer2.util.Assertions.checkState(r2)
            long r7 = r6.startUs
            long r0 = r0 - r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ClippingMediaPeriod.seekToUs(long):long");
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        long j2 = this.startUs;
        if (j == j2) {
            return 0;
        }
        long j3 = j + j2;
        return this.mediaPeriod.getAdjustedSeekPositionUs(j3, clipSeekParameters(j3, seekParameters)) - this.startUs;
    }

    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs != Long.MIN_VALUE) {
            long j = this.endUs;
            if (j == Long.MIN_VALUE || nextLoadPositionUs < j) {
                return nextLoadPositionUs - this.startUs;
            }
        }
        return Long.MIN_VALUE;
    }

    public boolean continueLoading(long j) {
        return this.mediaPeriod.continueLoading(j + this.startUs);
    }

    public void onPrepared(MediaPeriod mediaPeriod2) {
        Assertions.checkState((this.startUs == C.TIME_UNSET || this.endUs == C.TIME_UNSET) ? false : true);
        this.callback.onPrepared(this);
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod2) {
        this.callback.onContinueLoadingRequested(this);
    }

    /* access modifiers changed from: package-private */
    public boolean isPendingInitialDiscontinuity() {
        return this.pendingInitialDiscontinuityPositionUs != C.TIME_UNSET;
    }

    private SeekParameters clipSeekParameters(long j, SeekParameters seekParameters) {
        long j2;
        long min = Math.min(j - this.startUs, seekParameters.toleranceBeforeUs);
        long j3 = this.endUs;
        if (j3 == Long.MIN_VALUE) {
            j2 = seekParameters.toleranceAfterUs;
        } else {
            j2 = Math.min(j3 - j, seekParameters.toleranceAfterUs);
        }
        if (min == seekParameters.toleranceBeforeUs && j2 == seekParameters.toleranceAfterUs) {
            return seekParameters;
        }
        return new SeekParameters(min, j2);
    }

    private static boolean shouldKeepInitialDiscontinuity(long j, TrackSelection[] trackSelectionArr) {
        if (j != 0) {
            for (TrackSelection trackSelection : trackSelectionArr) {
                if (trackSelection != null && !MimeTypes.isAudio(trackSelection.getSelectedFormat().sampleMimeType)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final class ClippingSampleStream implements SampleStream {
        public final SampleStream childStream;
        private boolean sentEos;

        public ClippingSampleStream(SampleStream sampleStream) {
            this.childStream = sampleStream;
        }

        public void clearSentEos() {
            this.sentEos = false;
        }

        public boolean isReady() {
            return !ClippingMediaPeriod.this.isPendingInitialDiscontinuity() && this.childStream.isReady();
        }

        public void maybeThrowError() throws IOException {
            this.childStream.maybeThrowError();
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            if (this.sentEos) {
                decoderInputBuffer.setFlags(4);
                return -4;
            }
            int readData = this.childStream.readData(formatHolder, decoderInputBuffer, z);
            if (readData == -5) {
                Format format = formatHolder.format;
                if (!(format.encoderDelay == -1 && format.encoderPadding == -1)) {
                    int i = 0;
                    int i2 = ClippingMediaPeriod.this.startUs != 0 ? 0 : format.encoderDelay;
                    if (ClippingMediaPeriod.this.endUs == Long.MIN_VALUE) {
                        i = format.encoderPadding;
                    }
                    formatHolder.format = format.copyWithGaplessInfo(i2, i);
                }
                return -5;
            } else if (ClippingMediaPeriod.this.endUs == Long.MIN_VALUE || ((readData != -4 || decoderInputBuffer.timeUs < ClippingMediaPeriod.this.endUs) && !(readData == -3 && ClippingMediaPeriod.this.getBufferedPositionUs() == Long.MIN_VALUE))) {
                if (readData == -4 && !decoderInputBuffer.isEndOfStream()) {
                    decoderInputBuffer.timeUs -= ClippingMediaPeriod.this.startUs;
                }
                return readData;
            } else {
                decoderInputBuffer.clear();
                decoderInputBuffer.setFlags(4);
                this.sentEos = true;
                return -4;
            }
        }

        public int skipData(long j) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            return this.childStream.skipData(ClippingMediaPeriod.this.startUs + j);
        }
    }
}
