package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.source.chunk.DataChunk;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.trackselection.BaseTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

class HlsChunkSource {
    private final DataSource encryptionDataSource;
    private byte[] encryptionIv;
    private String encryptionIvString;
    private byte[] encryptionKey;
    private Uri encryptionKeyUri;
    private HlsMasterPlaylist.HlsUrl expectedPlaylistUrl;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isTimestampMaster;
    private long liveEdgeTimeUs = C.TIME_UNSET;
    private final DataSource mediaDataSource;
    private final List<Format> muxedCaptionFormats;
    private final HlsPlaylistTracker playlistTracker;
    private byte[] scratchSpace;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private TrackSelection trackSelection;
    private final HlsMasterPlaylist.HlsUrl[] variants;

    public static final class HlsChunkHolder {
        public Chunk chunk;
        public boolean endOfStream;
        public HlsMasterPlaylist.HlsUrl playlist;

        public HlsChunkHolder() {
            clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlist = null;
        }
    }

    public HlsChunkSource(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsMasterPlaylist.HlsUrl[] hlsUrlArr, HlsDataSourceFactory hlsDataSourceFactory, TimestampAdjusterProvider timestampAdjusterProvider2, List<Format> list) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.variants = hlsUrlArr;
        this.timestampAdjusterProvider = timestampAdjusterProvider2;
        this.muxedCaptionFormats = list;
        Format[] formatArr = new Format[hlsUrlArr.length];
        int[] iArr = new int[hlsUrlArr.length];
        for (int i = 0; i < hlsUrlArr.length; i++) {
            formatArr[i] = hlsUrlArr[i].format;
            iArr[i] = i;
        }
        this.mediaDataSource = hlsDataSourceFactory.createDataSource(1);
        this.encryptionDataSource = hlsDataSourceFactory.createDataSource(3);
        TrackGroup trackGroup2 = new TrackGroup(formatArr);
        this.trackGroup = trackGroup2;
        this.trackSelection = new InitializationTrackSelection(trackGroup2, iArr);
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            HlsMasterPlaylist.HlsUrl hlsUrl = this.expectedPlaylistUrl;
            if (hlsUrl != null) {
                this.playlistTracker.maybeThrowPlaylistRefreshError(hlsUrl);
                return;
            }
            return;
        }
        throw iOException;
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public void selectTracks(TrackSelection trackSelection2) {
        this.trackSelection = trackSelection2;
    }

    public TrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public void reset() {
        this.fatalError = null;
    }

    public void setIsTimestampMaster(boolean z) {
        this.isTimestampMaster = z;
    }

    public void getNextChunk(HlsMediaChunk hlsMediaChunk, long j, long j2, HlsChunkHolder hlsChunkHolder) {
        int i;
        long j3;
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        long j4 = j;
        HlsChunkHolder hlsChunkHolder2 = hlsChunkHolder;
        if (hlsMediaChunk2 == null) {
            i = -1;
        } else {
            i = this.trackGroup.indexOf(hlsMediaChunk2.trackFormat);
        }
        DataSpec dataSpec = null;
        this.expectedPlaylistUrl = null;
        long j5 = j2 - j4;
        long resolveTimeToLiveEdgeUs = resolveTimeToLiveEdgeUs(j4);
        if (hlsMediaChunk2 != null && !this.independentSegments) {
            long durationUs = hlsMediaChunk.getDurationUs();
            j5 = Math.max(0, j5 - durationUs);
            if (resolveTimeToLiveEdgeUs != C.TIME_UNSET) {
                resolveTimeToLiveEdgeUs = Math.max(0, resolveTimeToLiveEdgeUs - durationUs);
            }
        }
        this.trackSelection.updateSelectedTrack(j, j5, resolveTimeToLiveEdgeUs);
        int selectedIndexInTrackGroup = this.trackSelection.getSelectedIndexInTrackGroup();
        boolean z = false;
        boolean z2 = i != selectedIndexInTrackGroup;
        HlsMasterPlaylist.HlsUrl hlsUrl = this.variants[selectedIndexInTrackGroup];
        if (!this.playlistTracker.isSnapshotValid(hlsUrl)) {
            hlsChunkHolder2.playlist = hlsUrl;
            this.expectedPlaylistUrl = hlsUrl;
            return;
        }
        HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(hlsUrl);
        this.independentSegments = playlistSnapshot.hasIndependentSegmentsTag;
        updateLiveEdgeTimeUs(playlistSnapshot);
        if (hlsMediaChunk2 == null || z2) {
            long j6 = (hlsMediaChunk2 == null || this.independentSegments) ? j2 : hlsMediaChunk2.startTimeUs;
            if (playlistSnapshot.hasEndTag || j6 < playlistSnapshot.getEndTimeUs()) {
                List<HlsMediaPlaylist.Segment> list = playlistSnapshot.segments;
                Long valueOf = Long.valueOf(j6);
                if (!this.playlistTracker.isLive() || hlsMediaChunk2 == null) {
                    z = true;
                }
                long binarySearchFloor = ((long) Util.binarySearchFloor(list, valueOf, true, z)) + playlistSnapshot.mediaSequence;
                if (binarySearchFloor >= playlistSnapshot.mediaSequence || hlsMediaChunk2 == null) {
                    j3 = binarySearchFloor;
                } else {
                    hlsUrl = this.variants[i];
                    playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(hlsUrl);
                    selectedIndexInTrackGroup = i;
                    j3 = hlsMediaChunk.getNextChunkIndex();
                }
            } else {
                j3 = playlistSnapshot.mediaSequence + ((long) playlistSnapshot.segments.size());
            }
        } else {
            j3 = hlsMediaChunk.getNextChunkIndex();
        }
        long j7 = j3;
        HlsMasterPlaylist.HlsUrl hlsUrl2 = hlsUrl;
        int i2 = selectedIndexInTrackGroup;
        HlsMediaPlaylist hlsMediaPlaylist = playlistSnapshot;
        if (j7 < hlsMediaPlaylist.mediaSequence) {
            this.fatalError = new BehindLiveWindowException();
            return;
        }
        int i3 = (int) (j7 - hlsMediaPlaylist.mediaSequence);
        if (i3 < hlsMediaPlaylist.segments.size()) {
            HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(i3);
            if (segment.fullSegmentEncryptionKeyUri != null) {
                Uri resolveToUri = UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.fullSegmentEncryptionKeyUri);
                if (!resolveToUri.equals(this.encryptionKeyUri)) {
                    hlsChunkHolder2.chunk = newEncryptionKeyChunk(resolveToUri, segment.encryptionIV, i2, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData());
                    return;
                } else if (!Util.areEqual(segment.encryptionIV, this.encryptionIvString)) {
                    setEncryptionData(resolveToUri, segment.encryptionIV, this.encryptionKey);
                }
            } else {
                clearEncryptionData();
            }
            HlsMediaPlaylist.Segment segment2 = hlsMediaPlaylist.initializationSegment;
            if (segment2 != null) {
                dataSpec = new DataSpec(UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment2.url), segment2.byterangeOffset, segment2.byterangeLength, (String) null);
            }
            long initialStartTimeUs = (hlsMediaPlaylist.startTimeUs - this.playlistTracker.getInitialStartTimeUs()) + segment.relativeStartTimeUs;
            int i4 = hlsMediaPlaylist.discontinuitySequence + segment.relativeDiscontinuitySequence;
            TimestampAdjuster adjuster = this.timestampAdjusterProvider.getAdjuster(i4);
            DataSpec dataSpec2 = r26;
            DataSpec dataSpec3 = new DataSpec(UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.url), segment.byterangeOffset, segment.byterangeLength, (String) null);
            hlsChunkHolder2.chunk = new HlsMediaChunk(this.extractorFactory, this.mediaDataSource, dataSpec2, dataSpec, hlsUrl2, this.muxedCaptionFormats, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), initialStartTimeUs, initialStartTimeUs + segment.durationUs, j7, i4, segment.hasGapTag, this.isTimestampMaster, adjuster, hlsMediaChunk, hlsMediaPlaylist.drmInitData, this.encryptionKey, this.encryptionIv);
        } else if (hlsMediaPlaylist.hasEndTag) {
            hlsChunkHolder2.endOfStream = true;
        } else {
            hlsChunkHolder2.playlist = hlsUrl2;
            this.expectedPlaylistUrl = hlsUrl2;
        }
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            setEncryptionData(encryptionKeyChunk.dataSpec.uri, encryptionKeyChunk.iv, encryptionKeyChunk.getResult());
        }
    }

    public boolean onChunkLoadError(Chunk chunk, boolean z, IOException iOException) {
        if (z) {
            TrackSelection trackSelection2 = this.trackSelection;
            if (ChunkedTrackBlacklistUtil.maybeBlacklistTrack(trackSelection2, trackSelection2.indexOf(this.trackGroup.indexOf(chunk.trackFormat)), iOException)) {
                return true;
            }
        }
        return false;
    }

    public void onPlaylistBlacklisted(HlsMasterPlaylist.HlsUrl hlsUrl, long j) {
        int indexOf;
        int indexOf2 = this.trackGroup.indexOf(hlsUrl.format);
        if (indexOf2 != -1 && (indexOf = this.trackSelection.indexOf(indexOf2)) != -1) {
            this.trackSelection.blacklist(indexOf, j);
        }
    }

    private long resolveTimeToLiveEdgeUs(long j) {
        long j2 = this.liveEdgeTimeUs;
        return (j2 > C.TIME_UNSET ? 1 : (j2 == C.TIME_UNSET ? 0 : -1)) != 0 ? j2 - j : C.TIME_UNSET;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist hlsMediaPlaylist) {
        this.liveEdgeTimeUs = hlsMediaPlaylist.hasEndTag ? C.TIME_UNSET : hlsMediaPlaylist.getEndTimeUs();
    }

    private EncryptionKeyChunk newEncryptionKeyChunk(Uri uri, String str, int i, int i2, Object obj) {
        return new EncryptionKeyChunk(this.encryptionDataSource, new DataSpec(uri, 0, -1, (String) null, 1), this.variants[i].format, i2, obj, this.scratchSpace, str);
    }

    private void setEncryptionData(Uri uri, String str, byte[] bArr) {
        byte[] byteArray = new BigInteger(Util.toLowerInvariant(str).startsWith("0x") ? str.substring(2) : str, 16).toByteArray();
        byte[] bArr2 = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr2, (16 - byteArray.length) + length, byteArray.length - length);
        this.encryptionKeyUri = uri;
        this.encryptionKey = bArr;
        this.encryptionIvString = str;
        this.encryptionIv = bArr2;
    }

    private void clearEncryptionData() {
        this.encryptionKeyUri = null;
        this.encryptionKey = null;
        this.encryptionIvString = null;
        this.encryptionIv = null;
    }

    private static final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        public Object getSelectionData() {
            return null;
        }

        public int getSelectionReason() {
            return 0;
        }

        public InitializationTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
            this.selectedIndex = indexOf(trackGroup.getFormat(0));
        }

        public void updateSelectedTrack(long j, long j2, long j3) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (isBlacklisted(this.selectedIndex, elapsedRealtime)) {
                for (int i = this.length - 1; i >= 0; i--) {
                    if (!isBlacklisted(i, elapsedRealtime)) {
                        this.selectedIndex = i;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }

        public int getSelectedIndex() {
            return this.selectedIndex;
        }
    }

    private static final class EncryptionKeyChunk extends DataChunk {
        public final String iv;
        private byte[] result;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, byte[] bArr, String str) {
            super(dataSource, dataSpec, 3, format, i, obj, bArr);
            this.iv = str;
        }

        /* access modifiers changed from: protected */
        public void consume(byte[] bArr, int i) throws IOException {
            this.result = Arrays.copyOf(bArr, i);
        }

        public byte[] getResult() {
            return this.result;
        }
    }
}
