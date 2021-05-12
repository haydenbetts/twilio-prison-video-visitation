package com.twilio.video;

import androidx.annotation.NonNull;

public class RemoteVideoTrackStats extends RemoteTrackStats {
    @NonNull
    public final VideoDimensions dimensions;
    public final int frameRate;

    RemoteVideoTrackStats(@NonNull String str, int i, @NonNull String str2, @NonNull String str3, double d, long j, int i2, @NonNull VideoDimensions videoDimensions, int i3) {
        super(str, i, str2, str3, d, j, i2);
        this.dimensions = videoDimensions;
        this.frameRate = i3;
    }
}
