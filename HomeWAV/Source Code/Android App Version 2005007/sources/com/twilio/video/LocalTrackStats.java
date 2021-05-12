package com.twilio.video;

public abstract class LocalTrackStats extends BaseTrackStats {
    public final long bytesSent;
    public final int packetsSent;
    public final long roundTripTime;

    protected LocalTrackStats(String str, int i, String str2, String str3, double d, long j, int i2, long j2) {
        super(str, i, str2, str3, d);
        this.bytesSent = j;
        this.packetsSent = i2;
        this.roundTripTime = j2;
    }
}
