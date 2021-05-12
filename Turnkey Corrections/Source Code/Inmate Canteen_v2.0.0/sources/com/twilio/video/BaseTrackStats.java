package com.twilio.video;

import androidx.annotation.NonNull;

public abstract class BaseTrackStats {
    @NonNull
    public final String codec;
    public final int packetsLost;
    @NonNull
    public final String ssrc;
    public final double timestamp;
    @NonNull
    public final String trackSid;

    BaseTrackStats(@NonNull String str, int i, @NonNull String str2, @NonNull String str3, double d) {
        this.trackSid = str;
        this.packetsLost = i;
        this.codec = str2;
        this.ssrc = str3;
        this.timestamp = d;
    }
}
