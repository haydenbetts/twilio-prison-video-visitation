package com.twilio.video;

import androidx.annotation.NonNull;

public class LocalVideoTrackStats extends LocalTrackStats {
    @NonNull
    public final VideoDimensions captureDimensions;
    public final int capturedFrameRate;
    @NonNull
    public final VideoDimensions dimensions;
    public final int frameRate;

    LocalVideoTrackStats(String str, int i, String str2, String str3, double d, long j, int i2, long j2, @NonNull VideoDimensions videoDimensions, @NonNull VideoDimensions videoDimensions2, int i3, int i4) {
        super(str, i, str2, str3, d, j, i2, j2);
        this.captureDimensions = videoDimensions;
        this.dimensions = videoDimensions2;
        this.frameRate = i4;
        this.capturedFrameRate = i3;
    }
}
