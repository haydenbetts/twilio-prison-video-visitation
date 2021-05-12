package com.twilio.video;

import androidx.annotation.NonNull;

public class VideoFormat {
    @NonNull
    public final VideoDimensions dimensions;
    public final int framerate;
    @NonNull
    public final VideoPixelFormat pixelFormat;

    public VideoFormat(@NonNull VideoDimensions videoDimensions, int i, @NonNull VideoPixelFormat videoPixelFormat) {
        this.dimensions = videoDimensions;
        this.framerate = i;
        this.pixelFormat = videoPixelFormat;
    }
}
