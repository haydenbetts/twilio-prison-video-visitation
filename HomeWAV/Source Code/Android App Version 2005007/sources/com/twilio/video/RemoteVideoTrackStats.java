package com.twilio.video;

public class RemoteVideoTrackStats extends RemoteTrackStats {
    public final VideoDimensions dimensions;
    public final int frameRate;

    RemoteVideoTrackStats(String str, int i, String str2, String str3, double d, long j, int i2, VideoDimensions videoDimensions, int i3) {
        super(str, i, str2, str3, d, j, i2);
        this.dimensions = videoDimensions;
        this.frameRate = i3;
    }
}
