package com.twilio.video;

public abstract class RemoteTrackStats extends BaseTrackStats {
    public final long bytesReceived;
    public final int packetsReceived;

    protected RemoteTrackStats(String str, int i, String str2, String str3, double d, long j, int i2) {
        super(str, i, str2, str3, d);
        this.bytesReceived = j;
        this.packetsReceived = i2;
    }
}
