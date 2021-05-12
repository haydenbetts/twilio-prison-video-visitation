package com.twilio.video;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.twilio.video.VideoConstraints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import tvi.webrtc.VideoTrack;

public class LocalVideoTrack extends VideoTrack {
    private static final double ASPECT_RATIO_TOLERANCE = 0.05d;
    private static final String CAPTURER_MUST_HAVE_ONE_SUPPORTED_FORMAT = "A VideoCapturer must provide at least one supported VideoFormat";
    static final VideoConstraints DEFAULT_VIDEO_CONSTRAINTS = new VideoConstraints.Builder().maxFps(30).maxVideoDimensions(VideoDimensions.VGA_VIDEO_DIMENSIONS).build();
    private static final Logger logger = Logger.getLogger(LocalVideoTrack.class);
    private final MediaFactory mediaFactory;
    private long nativeLocalVideoTrackHandle;
    private final String nativeTrackHash;
    private final VideoCapturer videoCapturer;
    private final VideoConstraints videoConstraints;

    @VisibleForTesting(otherwise = 5)
    private native long nativeAddRendererWithWants(long j, boolean z);

    private native void nativeEnable(long j, boolean z);

    private native boolean nativeIsEnabled(long j);

    private native void nativeRelease(long j);

    @VisibleForTesting(otherwise = 5)
    private native void nativeRemoveRendererWithWants(long j, long j2);

    @Nullable
    public static LocalVideoTrack create(@NonNull Context context, boolean z, @NonNull VideoCapturer videoCapturer2) {
        return create(context, z, videoCapturer2, (VideoConstraints) null, (String) null);
    }

    @Nullable
    public static LocalVideoTrack create(@NonNull Context context, boolean z, @NonNull VideoCapturer videoCapturer2, @Nullable VideoConstraints videoConstraints2) {
        return create(context, z, videoCapturer2, videoConstraints2, (String) null);
    }

    @Nullable
    public static LocalVideoTrack create(@NonNull Context context, boolean z, @NonNull VideoCapturer videoCapturer2, @Nullable String str) {
        return create(context, z, videoCapturer2, (VideoConstraints) null, str);
    }

    @Nullable
    public static LocalVideoTrack create(@NonNull Context context, boolean z, @NonNull VideoCapturer videoCapturer2, @Nullable VideoConstraints videoConstraints2, @Nullable String str) {
        Preconditions.checkNotNull(context, "Context must not be null");
        Preconditions.checkNotNull(videoCapturer2, "VideoCapturer must not be null");
        Preconditions.checkState(videoCapturer2.getSupportedFormats() != null && !videoCapturer2.getSupportedFormats().isEmpty(), CAPTURER_MUST_HAVE_ONE_SUPPORTED_FORMAT);
        Object obj = new Object();
        MediaFactory instance = MediaFactory.instance(obj, context);
        LocalVideoTrack createVideoTrack = instance.createVideoTrack(context, z, videoCapturer2, resolveConstraints(videoCapturer2, videoConstraints2), str);
        if (createVideoTrack == null) {
            logger.e("Failed to create local video track");
        }
        instance.release(obj);
        return createVideoTrack;
    }

    @NonNull
    public VideoCapturer getVideoCapturer() {
        return this.videoCapturer;
    }

    @NonNull
    public VideoConstraints getVideoConstraints() {
        return this.videoConstraints;
    }

    public synchronized void addRenderer(@NonNull VideoRenderer videoRenderer) {
        Preconditions.checkState(!isReleased(), "Cannot add renderer to video track that has been released");
        super.addRenderer(videoRenderer);
    }

    public synchronized void removeRenderer(@NonNull VideoRenderer videoRenderer) {
        Preconditions.checkState(!isReleased(), "Cannot remove renderer from video track that has been released");
        super.removeRenderer(videoRenderer);
    }

    public synchronized boolean isEnabled() {
        if (!isReleased()) {
            return nativeIsEnabled(this.nativeLocalVideoTrackHandle);
        }
        logger.e("Local video track is not enabled because it has been released");
        return false;
    }

    @NonNull
    public String getName() {
        return super.getName();
    }

    public synchronized void enable(boolean z) {
        if (!isReleased()) {
            nativeEnable(this.nativeLocalVideoTrackHandle, z);
        } else {
            logger.e("Cannot enable a local video track that has been removed");
        }
    }

    public synchronized void release() {
        if (!isReleased()) {
            super.release();
            nativeRelease(this.nativeLocalVideoTrackHandle);
            this.nativeLocalVideoTrackHandle = 0;
            this.mediaFactory.release(this);
        }
    }

    LocalVideoTrack(long j, boolean z, VideoCapturer videoCapturer2, VideoConstraints videoConstraints2, VideoTrack videoTrack, String str, String str2, Context context) {
        super(videoTrack, z, str2);
        this.nativeTrackHash = str;
        this.nativeLocalVideoTrackHandle = j;
        this.videoCapturer = videoCapturer2;
        this.videoConstraints = videoConstraints2;
        this.mediaFactory = MediaFactory.instance(this, context);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting(otherwise = 5)
    public long addRendererWithWants(@NonNull VideoRenderer videoRenderer, boolean z) {
        long nativeAddRendererWithWants = nativeAddRendererWithWants(this.nativeLocalVideoTrackHandle, z);
        super.addRenderer(videoRenderer);
        return nativeAddRendererWithWants;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting(otherwise = 5)
    public void removeRendererWithWants(long j) {
        nativeRemoveRendererWithWants(this.nativeLocalVideoTrackHandle, j);
    }

    private static VideoConstraints resolveConstraints(VideoCapturer videoCapturer2, VideoConstraints videoConstraints2) {
        if (videoConstraints2 != null && constraintsCompatible(videoCapturer2, videoConstraints2)) {
            return videoConstraints2;
        }
        logger.e("Applying VideoConstraints closest to 640x480@30 FPS.");
        return getClosestCompatibleVideoConstraints(videoCapturer2, DEFAULT_VIDEO_CONSTRAINTS);
    }

    @VisibleForTesting(otherwise = 2)
    static boolean constraintsCompatible(VideoCapturer videoCapturer2, VideoConstraints videoConstraints2) {
        boolean z;
        Iterator<VideoFormat> it = videoCapturer2.getSupportedFormats().iterator();
        do {
            boolean z2 = false;
            if (it.hasNext()) {
                VideoFormat next = it.next();
                VideoDimensions minVideoDimensions = videoConstraints2.getMinVideoDimensions();
                VideoDimensions maxVideoDimensions = videoConstraints2.getMaxVideoDimensions();
                AspectRatio aspectRatio = videoConstraints2.getAspectRatio();
                int minFps = videoConstraints2.getMinFps();
                int maxFps = videoConstraints2.getMaxFps();
                z = minVideoDimensions.width <= next.dimensions.width && minVideoDimensions.height <= next.dimensions.height && minFps <= next.framerate;
                if (maxVideoDimensions.width > 0) {
                    z &= maxVideoDimensions.width >= next.dimensions.width;
                }
                if (maxVideoDimensions.height > 0) {
                    z &= maxVideoDimensions.height >= next.dimensions.height;
                }
                if (maxFps > 0) {
                    z &= maxFps <= next.framerate;
                }
                if (aspectRatio.numerator > 0 && aspectRatio.denominator > 0) {
                    double d = (double) aspectRatio.numerator;
                    double d2 = (double) aspectRatio.denominator;
                    Double.isNaN(d);
                    Double.isNaN(d2);
                    double d3 = d / d2;
                    double d4 = (double) next.dimensions.width;
                    double d5 = (double) next.dimensions.height;
                    Double.isNaN(d4);
                    Double.isNaN(d5);
                    if (Math.abs((d4 / d5) - d3) < ASPECT_RATIO_TOLERANCE) {
                        z2 = true;
                    }
                    z &= z2;
                    continue;
                }
            } else {
                logger.e("VideoConstraints are not compatible with VideoCapturer");
                return false;
            }
        } while (!z);
        logger.i("VideoConstraints are compatible with VideoCapturer");
        return true;
    }

    private static VideoConstraints getClosestCompatibleVideoConstraints(VideoCapturer videoCapturer2, final VideoConstraints videoConstraints2) {
        List<VideoFormat> supportedFormats = videoCapturer2.getSupportedFormats();
        VideoDimensions videoDimensions = ((VideoFormat) Collections.min(supportedFormats, new ClosestComparator<VideoFormat>() {
            /* access modifiers changed from: package-private */
            public int diff(VideoFormat videoFormat) {
                return Math.abs(videoConstraints2.getMaxVideoDimensions().width - videoFormat.dimensions.width) + Math.abs(videoConstraints2.getMaxVideoDimensions().height - videoFormat.dimensions.height);
            }
        })).dimensions;
        ArrayList arrayList = new ArrayList();
        for (VideoFormat next : supportedFormats) {
            if (next.dimensions.equals(videoDimensions)) {
                arrayList.add(Integer.valueOf(next.framerate));
            }
        }
        return new VideoConstraints.Builder().maxFps(((Integer) Collections.min(arrayList, new ClosestComparator<Integer>() {
            /* access modifiers changed from: package-private */
            public int diff(Integer num) {
                return Math.abs(videoConstraints2.getMaxFps() - num.intValue());
            }
        })).intValue()).maxVideoDimensions(videoDimensions).build();
    }

    /* access modifiers changed from: package-private */
    public boolean isReleased() {
        return this.nativeLocalVideoTrackHandle == 0;
    }

    /* access modifiers changed from: package-private */
    public String getNativeTrackHash() {
        return this.nativeTrackHash;
    }

    /* access modifiers changed from: package-private */
    public synchronized long getNativeHandle() {
        return this.nativeLocalVideoTrackHandle;
    }

    private static abstract class ClosestComparator<T> implements Comparator<T> {
        /* access modifiers changed from: package-private */
        public abstract int diff(T t);

        private ClosestComparator() {
        }

        public int compare(T t, T t2) {
            return diff(t) - diff(t2);
        }
    }
}
