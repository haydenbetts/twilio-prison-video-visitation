package com.google.android.exoplayer2.source.dash;

import android.net.Uri;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer2.source.chunk.ChunkHolder;
import com.google.android.exoplayer2.source.chunk.ContainerMediaChunk;
import com.google.android.exoplayer2.source.chunk.InitializationChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.SingleSampleMediaChunk;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultDashChunkSource implements DashChunkSource {
    private final int[] adaptationSetIndices;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetMs;
    private IOException fatalError;
    private long liveEdgeTimeUs = C.TIME_UNSET;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int maxSegmentsPerLoad;
    private boolean missingLastSegment;
    private int periodIndex;
    private final PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler;
    protected final RepresentationHolder[] representationHolders;
    private final TrackSelection trackSelection;
    private final int trackType;

    public static final class Factory implements DashChunkSource.Factory {
        private final DataSource.Factory dataSourceFactory;
        private final int maxSegmentsPerLoad;

        public Factory(DataSource.Factory factory) {
            this(factory, 1);
        }

        public Factory(DataSource.Factory factory, int i) {
            this.dataSourceFactory = factory;
            this.maxSegmentsPerLoad = i;
        }

        public DashChunkSource createDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, TrackSelection trackSelection, int i2, long j, boolean z, boolean z2, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler) {
            return new DefaultDashChunkSource(loaderErrorThrower, dashManifest, i, iArr, trackSelection, i2, this.dataSourceFactory.createDataSource(), j, this.maxSegmentsPerLoad, z, z2, playerTrackEmsgHandler);
        }
    }

    public DefaultDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, TrackSelection trackSelection2, int i2, DataSource dataSource2, long j, int i3, boolean z, boolean z2, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2) {
        TrackSelection trackSelection3 = trackSelection2;
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.manifest = dashManifest;
        this.adaptationSetIndices = iArr;
        this.trackSelection = trackSelection3;
        this.trackType = i2;
        this.dataSource = dataSource2;
        this.periodIndex = i;
        this.elapsedRealtimeOffsetMs = j;
        this.maxSegmentsPerLoad = i3;
        this.playerTrackEmsgHandler = playerTrackEmsgHandler2;
        long periodDurationUs = dashManifest.getPeriodDurationUs(i);
        ArrayList<Representation> representations = getRepresentations();
        this.representationHolders = new RepresentationHolder[trackSelection2.length()];
        for (int i4 = 0; i4 < this.representationHolders.length; i4++) {
            this.representationHolders[i4] = new RepresentationHolder(periodDurationUs, i2, representations.get(trackSelection3.getIndexInTrackGroup(i4)), z, z2, playerTrackEmsgHandler2);
        }
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        for (RepresentationHolder representationHolder : this.representationHolders) {
            if (representationHolder.segmentIndex != null) {
                long segmentNum = representationHolder.getSegmentNum(j);
                long segmentStartTimeUs = representationHolder.getSegmentStartTimeUs(segmentNum);
                return Util.resolveSeekPositionUs(j, seekParameters, segmentStartTimeUs, (segmentStartTimeUs >= j || segmentNum >= ((long) (representationHolder.getSegmentCount() + -1))) ? segmentStartTimeUs : representationHolder.getSegmentStartTimeUs(segmentNum + 1));
            }
        }
        return j;
    }

    public void updateManifest(DashManifest dashManifest, int i) {
        try {
            this.manifest = dashManifest;
            this.periodIndex = i;
            long periodDurationUs = dashManifest.getPeriodDurationUs(i);
            ArrayList<Representation> representations = getRepresentations();
            for (int i2 = 0; i2 < this.representationHolders.length; i2++) {
                this.representationHolders[i2].updateRepresentation(periodDurationUs, representations.get(this.trackSelection.getIndexInTrackGroup(i2)));
            }
        } catch (BehindLiveWindowException e) {
            this.fatalError = e;
        }
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            this.manifestLoaderErrorThrower.maybeThrowError();
            return;
        }
        throw iOException;
    }

    public int getPreferredQueueSize(long j, List<? extends MediaChunk> list) {
        if (this.fatalError != null || this.trackSelection.length() < 2) {
            return list.size();
        }
        return this.trackSelection.evaluateQueueSize(j, list);
    }

    public void getNextChunk(MediaChunk mediaChunk, long j, long j2, ChunkHolder chunkHolder) {
        long j3;
        long j4;
        long j5 = j;
        long j6 = j2;
        ChunkHolder chunkHolder2 = chunkHolder;
        if (this.fatalError == null) {
            long j7 = j6 - j5;
            long resolveTimeToLiveEdgeUs = resolveTimeToLiveEdgeUs(j5);
            long msToUs = C.msToUs(this.manifest.availabilityStartTimeMs) + C.msToUs(this.manifest.getPeriod(this.periodIndex).startMs) + j6;
            PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2 = this.playerTrackEmsgHandler;
            if (playerTrackEmsgHandler2 == null || !playerTrackEmsgHandler2.maybeRefreshManifestBeforeLoadingNextChunk(msToUs)) {
                this.trackSelection.updateSelectedTrack(j, j7, resolveTimeToLiveEdgeUs);
                RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.getSelectedIndex()];
                if (representationHolder.extractorWrapper != null) {
                    Representation representation = representationHolder.representation;
                    RangedUri initializationUri = representationHolder.extractorWrapper.getSampleFormats() == null ? representation.getInitializationUri() : null;
                    RangedUri indexUri = representationHolder.segmentIndex == null ? representation.getIndexUri() : null;
                    if (!(initializationUri == null && indexUri == null)) {
                        chunkHolder2.chunk = newInitializationChunk(representationHolder, this.dataSource, this.trackSelection.getSelectedFormat(), this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), initializationUri, indexUri);
                        return;
                    }
                }
                int segmentCount = representationHolder.getSegmentCount();
                boolean z = false;
                if (segmentCount == 0) {
                    if (!this.manifest.dynamic || this.periodIndex < this.manifest.getPeriodCount() - 1) {
                        z = true;
                    }
                    chunkHolder2.endOfStream = z;
                    return;
                }
                long firstSegmentNum = representationHolder.getFirstSegmentNum();
                if (segmentCount == -1) {
                    long nowUnixTimeUs = (getNowUnixTimeUs() - C.msToUs(this.manifest.availabilityStartTimeMs)) - C.msToUs(this.manifest.getPeriod(this.periodIndex).startMs);
                    if (this.manifest.timeShiftBufferDepthMs != C.TIME_UNSET) {
                        firstSegmentNum = Math.max(firstSegmentNum, representationHolder.getSegmentNum(nowUnixTimeUs - C.msToUs(this.manifest.timeShiftBufferDepthMs)));
                    }
                    j3 = representationHolder.getSegmentNum(nowUnixTimeUs);
                } else {
                    j3 = ((long) segmentCount) + firstSegmentNum;
                }
                long j8 = j3 - 1;
                long j9 = firstSegmentNum;
                updateLiveEdgeTimeUs(representationHolder, j8);
                if (mediaChunk == null) {
                    j4 = Util.constrainValue(representationHolder.getSegmentNum(j6), j9, j8);
                } else {
                    j4 = mediaChunk.getNextChunkIndex();
                    if (j4 < j9) {
                        this.fatalError = new BehindLiveWindowException();
                        return;
                    }
                }
                long j10 = j4;
                int i = (j10 > j8 ? 1 : (j10 == j8 ? 0 : -1));
                if (i > 0 || (this.missingLastSegment && i >= 0)) {
                    if (!this.manifest.dynamic || this.periodIndex < this.manifest.getPeriodCount() - 1) {
                        z = true;
                    }
                    chunkHolder2.endOfStream = z;
                    return;
                }
                chunkHolder2.chunk = newMediaChunk(representationHolder, this.dataSource, this.trackType, this.trackSelection.getSelectedFormat(), this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), j10, (int) Math.min((long) this.maxSegmentsPerLoad, (j8 - j10) + 1));
            }
        }
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        SeekMap seekMap;
        if (chunk instanceof InitializationChunk) {
            RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.indexOf(((InitializationChunk) chunk).trackFormat)];
            if (representationHolder.segmentIndex == null && (seekMap = representationHolder.extractorWrapper.getSeekMap()) != null) {
                representationHolder.segmentIndex = new DashWrappingSegmentIndex((ChunkIndex) seekMap);
            }
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2 = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler2 != null) {
            playerTrackEmsgHandler2.onChunkLoadCompleted(chunk);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        r8 = r6.representationHolders[r6.trackSelection.indexOf(r7.trackFormat)];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onChunkLoadError(com.google.android.exoplayer2.source.chunk.Chunk r7, boolean r8, java.lang.Exception r9) {
        /*
            r6 = this;
            if (r8 != 0) goto L_0x0004
            r7 = 0
            return r7
        L_0x0004:
            com.google.android.exoplayer2.source.dash.PlayerEmsgHandler$PlayerTrackEmsgHandler r8 = r6.playerTrackEmsgHandler
            r0 = 1
            if (r8 == 0) goto L_0x0010
            boolean r8 = r8.maybeRefreshManifestOnLoadingError(r7)
            if (r8 == 0) goto L_0x0010
            return r0
        L_0x0010:
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r8 = r6.manifest
            boolean r8 = r8.dynamic
            if (r8 != 0) goto L_0x0053
            boolean r8 = r7 instanceof com.google.android.exoplayer2.source.chunk.MediaChunk
            if (r8 == 0) goto L_0x0053
            boolean r8 = r9 instanceof com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException
            if (r8 == 0) goto L_0x0053
            r8 = r9
            com.google.android.exoplayer2.upstream.HttpDataSource$InvalidResponseCodeException r8 = (com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException) r8
            int r8 = r8.responseCode
            r1 = 404(0x194, float:5.66E-43)
            if (r8 != r1) goto L_0x0053
            com.google.android.exoplayer2.source.dash.DefaultDashChunkSource$RepresentationHolder[] r8 = r6.representationHolders
            com.google.android.exoplayer2.trackselection.TrackSelection r1 = r6.trackSelection
            com.google.android.exoplayer2.Format r2 = r7.trackFormat
            int r1 = r1.indexOf((com.google.android.exoplayer2.Format) r2)
            r8 = r8[r1]
            int r1 = r8.getSegmentCount()
            r2 = -1
            if (r1 == r2) goto L_0x0053
            if (r1 == 0) goto L_0x0053
            long r2 = r8.getFirstSegmentNum()
            long r4 = (long) r1
            long r2 = r2 + r4
            r4 = 1
            long r2 = r2 - r4
            r8 = r7
            com.google.android.exoplayer2.source.chunk.MediaChunk r8 = (com.google.android.exoplayer2.source.chunk.MediaChunk) r8
            long r4 = r8.getNextChunkIndex()
            int r8 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r8 <= 0) goto L_0x0053
            r6.missingLastSegment = r0
            return r0
        L_0x0053:
            com.google.android.exoplayer2.trackselection.TrackSelection r8 = r6.trackSelection
            com.google.android.exoplayer2.Format r7 = r7.trackFormat
            int r7 = r8.indexOf((com.google.android.exoplayer2.Format) r7)
            boolean r7 = com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil.maybeBlacklistTrack(r8, r7, r9)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.DefaultDashChunkSource.onChunkLoadError(com.google.android.exoplayer2.source.chunk.Chunk, boolean, java.lang.Exception):boolean");
    }

    private ArrayList<Representation> getRepresentations() {
        List<AdaptationSet> list = this.manifest.getPeriod(this.periodIndex).adaptationSets;
        ArrayList<Representation> arrayList = new ArrayList<>();
        for (int i : this.adaptationSetIndices) {
            arrayList.addAll(list.get(i).representations);
        }
        return arrayList;
    }

    private void updateLiveEdgeTimeUs(RepresentationHolder representationHolder, long j) {
        this.liveEdgeTimeUs = this.manifest.dynamic ? representationHolder.getSegmentEndTimeUs(j) : C.TIME_UNSET;
    }

    private long getNowUnixTimeUs() {
        long currentTimeMillis;
        if (this.elapsedRealtimeOffsetMs != 0) {
            currentTimeMillis = SystemClock.elapsedRealtime() + this.elapsedRealtimeOffsetMs;
        } else {
            currentTimeMillis = System.currentTimeMillis();
        }
        return currentTimeMillis * 1000;
    }

    private long resolveTimeToLiveEdgeUs(long j) {
        if (this.manifest.dynamic && this.liveEdgeTimeUs != C.TIME_UNSET) {
            return this.liveEdgeTimeUs - j;
        }
        return C.TIME_UNSET;
    }

    protected static Chunk newInitializationChunk(RepresentationHolder representationHolder, DataSource dataSource2, Format format, int i, Object obj, RangedUri rangedUri, RangedUri rangedUri2) {
        String str = representationHolder.representation.baseUrl;
        if (rangedUri == null || (rangedUri2 = rangedUri.attemptMerge(rangedUri2, str)) != null) {
            rangedUri = rangedUri2;
        }
        return new InitializationChunk(dataSource2, new DataSpec(rangedUri.resolveUri(str), rangedUri.start, rangedUri.length, representationHolder.representation.getCacheKey()), format, i, obj, representationHolder.extractorWrapper);
    }

    protected static Chunk newMediaChunk(RepresentationHolder representationHolder, DataSource dataSource2, int i, Format format, int i2, Object obj, long j, int i3) {
        RepresentationHolder representationHolder2 = representationHolder;
        long j2 = j;
        Representation representation = representationHolder2.representation;
        long segmentStartTimeUs = representationHolder2.getSegmentStartTimeUs(j2);
        RangedUri segmentUrl = representationHolder2.getSegmentUrl(j2);
        String str = representation.baseUrl;
        if (representationHolder2.extractorWrapper == null) {
            return new SingleSampleMediaChunk(dataSource2, new DataSpec(segmentUrl.resolveUri(str), segmentUrl.start, segmentUrl.length, representation.getCacheKey()), format, i2, obj, segmentStartTimeUs, representationHolder2.getSegmentEndTimeUs(j2), j, i, format);
        }
        int i4 = 1;
        int i5 = i3;
        int i6 = 1;
        while (i4 < i5) {
            RangedUri attemptMerge = segmentUrl.attemptMerge(representationHolder2.getSegmentUrl(((long) i4) + j2), str);
            if (attemptMerge == null) {
                break;
            }
            i6++;
            i4++;
            segmentUrl = attemptMerge;
        }
        long segmentEndTimeUs = representationHolder2.getSegmentEndTimeUs((((long) i6) + j2) - 1);
        Uri resolveUri = segmentUrl.resolveUri(str);
        long j3 = segmentUrl.start;
        DataSpec dataSpec = new DataSpec(resolveUri, j3, segmentUrl.length, representation.getCacheKey());
        return new ContainerMediaChunk(dataSource2, dataSpec, format, i2, obj, segmentStartTimeUs, segmentEndTimeUs, j, i6, -representation.presentationTimeOffsetUs, representationHolder2.extractorWrapper);
    }

    protected static final class RepresentationHolder {
        final ChunkExtractorWrapper extractorWrapper;
        private long periodDurationUs;
        public Representation representation;
        public DashSegmentIndex segmentIndex;
        private long segmentNumShift;

        /* JADX WARNING: type inference failed for: r9v12, types: [com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor] */
        /* JADX WARNING: type inference failed for: r9v13, types: [com.google.android.exoplayer2.extractor.rawcc.RawCcExtractor] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        RepresentationHolder(long r9, int r11, com.google.android.exoplayer2.source.dash.manifest.Representation r12, boolean r13, boolean r14, com.google.android.exoplayer2.extractor.TrackOutput r15) {
            /*
                r8 = this;
                r8.<init>()
                r8.periodDurationUs = r9
                r8.representation = r12
                com.google.android.exoplayer2.Format r9 = r12.format
                java.lang.String r9 = r9.containerMimeType
                boolean r10 = mimeTypeIsRawText(r9)
                r0 = 0
                if (r10 == 0) goto L_0x0015
                r8.extractorWrapper = r0
                goto L_0x005e
            L_0x0015:
                java.lang.String r10 = "application/x-rawcc"
                boolean r10 = r10.equals(r9)
                if (r10 == 0) goto L_0x0025
                com.google.android.exoplayer2.extractor.rawcc.RawCcExtractor r9 = new com.google.android.exoplayer2.extractor.rawcc.RawCcExtractor
                com.google.android.exoplayer2.Format r10 = r12.format
                r9.<init>(r10)
                goto L_0x0055
            L_0x0025:
                boolean r9 = mimeTypeIsWebm(r9)
                if (r9 == 0) goto L_0x0032
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor r9 = new com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor
                r10 = 1
                r9.<init>(r10)
                goto L_0x0055
            L_0x0032:
                r9 = 0
                if (r13 == 0) goto L_0x0038
                r10 = 4
                r2 = 4
                goto L_0x0039
            L_0x0038:
                r2 = 0
            L_0x0039:
                if (r14 == 0) goto L_0x0046
                java.lang.String r10 = "application/cea-608"
                com.google.android.exoplayer2.Format r9 = com.google.android.exoplayer2.Format.createTextSampleFormat(r0, r10, r9, r0)
                java.util.List r9 = java.util.Collections.singletonList(r9)
                goto L_0x004a
            L_0x0046:
                java.util.List r9 = java.util.Collections.emptyList()
            L_0x004a:
                r6 = r9
                com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor r9 = new com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor
                r3 = 0
                r4 = 0
                r5 = 0
                r1 = r9
                r7 = r15
                r1.<init>(r2, r3, r4, r5, r6, r7)
            L_0x0055:
                com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper r10 = new com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper
                com.google.android.exoplayer2.Format r13 = r12.format
                r10.<init>(r9, r11, r13)
                r8.extractorWrapper = r10
            L_0x005e:
                com.google.android.exoplayer2.source.dash.DashSegmentIndex r9 = r12.getIndex()
                r8.segmentIndex = r9
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.DefaultDashChunkSource.RepresentationHolder.<init>(long, int, com.google.android.exoplayer2.source.dash.manifest.Representation, boolean, boolean, com.google.android.exoplayer2.extractor.TrackOutput):void");
        }

        /* access modifiers changed from: package-private */
        public void updateRepresentation(long j, Representation representation2) throws BehindLiveWindowException {
            int segmentCount;
            DashSegmentIndex index = this.representation.getIndex();
            DashSegmentIndex index2 = representation2.getIndex();
            this.periodDurationUs = j;
            this.representation = representation2;
            if (index != null) {
                this.segmentIndex = index2;
                if (index.isExplicit() && (segmentCount = index.getSegmentCount(this.periodDurationUs)) != 0) {
                    long firstSegmentNum = (index.getFirstSegmentNum() + ((long) segmentCount)) - 1;
                    long timeUs = index.getTimeUs(firstSegmentNum) + index.getDurationUs(firstSegmentNum, this.periodDurationUs);
                    long firstSegmentNum2 = index2.getFirstSegmentNum();
                    long timeUs2 = index2.getTimeUs(firstSegmentNum2);
                    int i = (timeUs > timeUs2 ? 1 : (timeUs == timeUs2 ? 0 : -1));
                    if (i == 0) {
                        this.segmentNumShift += (firstSegmentNum + 1) - firstSegmentNum2;
                    } else if (i >= 0) {
                        this.segmentNumShift += index.getSegmentNum(timeUs2, this.periodDurationUs) - firstSegmentNum2;
                    } else {
                        throw new BehindLiveWindowException();
                    }
                }
            }
        }

        public long getFirstSegmentNum() {
            return this.segmentIndex.getFirstSegmentNum() + this.segmentNumShift;
        }

        public int getSegmentCount() {
            return this.segmentIndex.getSegmentCount(this.periodDurationUs);
        }

        public long getSegmentStartTimeUs(long j) {
            return this.segmentIndex.getTimeUs(j - this.segmentNumShift);
        }

        public long getSegmentEndTimeUs(long j) {
            return getSegmentStartTimeUs(j) + this.segmentIndex.getDurationUs(j - this.segmentNumShift, this.periodDurationUs);
        }

        public long getSegmentNum(long j) {
            return this.segmentIndex.getSegmentNum(j, this.periodDurationUs) + this.segmentNumShift;
        }

        public RangedUri getSegmentUrl(long j) {
            return this.segmentIndex.getSegmentUrl(j - this.segmentNumShift);
        }

        private static boolean mimeTypeIsWebm(String str) {
            return str.startsWith(MimeTypes.VIDEO_WEBM) || str.startsWith(MimeTypes.AUDIO_WEBM) || str.startsWith(MimeTypes.APPLICATION_WEBM);
        }

        private static boolean mimeTypeIsRawText(String str) {
            return MimeTypes.isText(str) || MimeTypes.APPLICATION_TTML.equals(str);
        }
    }
}
