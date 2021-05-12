package com.twilio.video;

import androidx.annotation.NonNull;

public abstract class RemoteTrackStats extends BaseTrackStats {
    public final long bytesReceived;
    public final int packetsReceived;

    protected RemoteTrackStats(@NonNull String str, int i, @NonNull String str2, @NonNull String str3, double d, long j, int i2) {
        super(str, i, str2, str3, d);
        this.bytesReceived = j;
        this.packetsReceived = i2;
    }
}
