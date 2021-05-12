package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public final class ConcatenatingMediaSource extends CompositeMediaSource<Integer> {
    private final boolean isAtomic;
    private MediaSource.Listener listener;
    private final Object[] manifests;
    private final MediaSource[] mediaSources;
    private final ShuffleOrder shuffleOrder;
    private final Map<MediaPeriod, Integer> sourceIndexByMediaPeriod;
    private ConcatenatedTimeline timeline;
    private final Timeline[] timelines;

    public ConcatenatingMediaSource(MediaSource... mediaSourceArr) {
        this(false, mediaSourceArr);
    }

    public ConcatenatingMediaSource(boolean z, MediaSource... mediaSourceArr) {
        this(z, new ShuffleOrder.DefaultShuffleOrder(mediaSourceArr.length), mediaSourceArr);
    }

    public ConcatenatingMediaSource(boolean z, ShuffleOrder shuffleOrder2, MediaSource... mediaSourceArr) {
        boolean z2 = false;
        for (MediaSource checkNotNull : mediaSourceArr) {
            Assertions.checkNotNull(checkNotNull);
        }
        Assertions.checkArgument(shuffleOrder2.getLength() == mediaSourceArr.length ? true : z2);
        this.mediaSources = mediaSourceArr;
        this.isAtomic = z;
        this.shuffleOrder = shuffleOrder2;
        this.timelines = new Timeline[mediaSourceArr.length];
        this.manifests = new Object[mediaSourceArr.length];
        this.sourceIndexByMediaPeriod = new HashMap();
    }

    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener2) {
        super.prepareSource(exoPlayer, z, listener2);
        this.listener = listener2;
        boolean[] buildDuplicateFlags = buildDuplicateFlags(this.mediaSources);
        if (this.mediaSources.length == 0) {
            listener2.onSourceInfoRefreshed(this, Timeline.EMPTY, (Object) null);
            return;
        }
        for (int i = 0; i < this.mediaSources.length; i++) {
            if (!buildDuplicateFlags[i]) {
                prepareChildSource(Integer.valueOf(i), this.mediaSources[i]);
            }
        }
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        int childIndexByPeriodIndex = this.timeline.getChildIndexByPeriodIndex(mediaPeriodId.periodIndex);
        MediaPeriod createPeriod = this.mediaSources[childIndexByPeriodIndex].createPeriod(mediaPeriodId.copyWithPeriodIndex(mediaPeriodId.periodIndex - this.timeline.getFirstPeriodIndexByChildIndex(childIndexByPeriodIndex)), allocator);
        this.sourceIndexByMediaPeriod.put(createPeriod, Integer.valueOf(childIndexByPeriodIndex));
        return createPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        int intValue = this.sourceIndexByMediaPeriod.get(mediaPeriod).intValue();
        this.sourceIndexByMediaPeriod.remove(mediaPeriod);
        this.mediaSources[intValue].releasePeriod(mediaPeriod);
    }

    public void releaseSource() {
        super.releaseSource();
        this.listener = null;
        this.timeline = null;
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Integer num, MediaSource mediaSource, Timeline timeline2, Object obj) {
        this.timelines[num.intValue()] = timeline2;
        this.manifests[num.intValue()] = obj;
        int intValue = num.intValue();
        while (true) {
            intValue++;
            MediaSource[] mediaSourceArr = this.mediaSources;
            if (intValue >= mediaSourceArr.length) {
                break;
            } else if (mediaSourceArr[intValue] == mediaSource) {
                this.timelines[intValue] = timeline2;
                this.manifests[intValue] = obj;
            }
        }
        Timeline[] timelineArr = this.timelines;
        int length = timelineArr.length;
        int i = 0;
        while (i < length) {
            if (timelineArr[i] != null) {
                i++;
            } else {
                return;
            }
        }
        ConcatenatedTimeline concatenatedTimeline = new ConcatenatedTimeline((Timeline[]) this.timelines.clone(), this.isAtomic, this.shuffleOrder);
        this.timeline = concatenatedTimeline;
        this.listener.onSourceInfoRefreshed(this, concatenatedTimeline, this.manifests.clone());
    }

    private static boolean[] buildDuplicateFlags(MediaSource[] mediaSourceArr) {
        boolean[] zArr = new boolean[mediaSourceArr.length];
        IdentityHashMap identityHashMap = new IdentityHashMap(mediaSourceArr.length);
        for (int i = 0; i < mediaSourceArr.length; i++) {
            MediaSource mediaSource = mediaSourceArr[i];
            if (!identityHashMap.containsKey(mediaSource)) {
                identityHashMap.put(mediaSource, (Object) null);
            } else {
                zArr[i] = true;
            }
        }
        return zArr;
    }

    private static final class ConcatenatedTimeline extends AbstractConcatenatedTimeline {
        private final int[] sourcePeriodOffsets;
        private final int[] sourceWindowOffsets;
        private final Timeline[] timelines;

        public ConcatenatedTimeline(Timeline[] timelineArr, boolean z, ShuffleOrder shuffleOrder) {
            super(z, shuffleOrder);
            int[] iArr = new int[timelineArr.length];
            int[] iArr2 = new int[timelineArr.length];
            long j = 0;
            int i = 0;
            for (int i2 = 0; i2 < timelineArr.length; i2++) {
                Timeline timeline = timelineArr[i2];
                j += (long) timeline.getPeriodCount();
                Assertions.checkState(j <= 2147483647L, "ConcatenatingMediaSource children contain too many periods");
                iArr[i2] = (int) j;
                i += timeline.getWindowCount();
                iArr2[i2] = i;
            }
            this.timelines = timelineArr;
            this.sourcePeriodOffsets = iArr;
            this.sourceWindowOffsets = iArr2;
        }

        public int getWindowCount() {
            int[] iArr = this.sourceWindowOffsets;
            return iArr[iArr.length - 1];
        }

        public int getPeriodCount() {
            int[] iArr = this.sourcePeriodOffsets;
            return iArr[iArr.length - 1];
        }

        /* access modifiers changed from: protected */
        public int getChildIndexByPeriodIndex(int i) {
            return Util.binarySearchFloor(this.sourcePeriodOffsets, i + 1, false, false) + 1;
        }

        /* access modifiers changed from: protected */
        public int getChildIndexByWindowIndex(int i) {
            return Util.binarySearchFloor(this.sourceWindowOffsets, i + 1, false, false) + 1;
        }

        /* access modifiers changed from: protected */
        public int getChildIndexByChildUid(Object obj) {
            if (!(obj instanceof Integer)) {
                return -1;
            }
            return ((Integer) obj).intValue();
        }

        /* access modifiers changed from: protected */
        public Timeline getTimelineByChildIndex(int i) {
            return this.timelines[i];
        }

        /* access modifiers changed from: protected */
        public int getFirstPeriodIndexByChildIndex(int i) {
            if (i == 0) {
                return 0;
            }
            return this.sourcePeriodOffsets[i - 1];
        }

        /* access modifiers changed from: protected */
        public int getFirstWindowIndexByChildIndex(int i) {
            if (i == 0) {
                return 0;
            }
            return this.sourceWindowOffsets[i - 1];
        }

        /* access modifiers changed from: protected */
        public Object getChildUidByChildIndex(int i) {
            return Integer.valueOf(i);
        }
    }
}
