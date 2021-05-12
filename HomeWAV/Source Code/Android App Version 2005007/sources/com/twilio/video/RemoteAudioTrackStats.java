package com.twilio.video;

public class RemoteAudioTrackStats extends RemoteTrackStats {
    public final int audioLevel;
    public final int jitter;

    RemoteAudioTrackStats(String str, int i, String str2, String str3, double d, long j, int i2, int i3, int i4) {
        super(str, i, str2, str3, d, j, i2);
        this.audioLevel = i3;
        this.jitter = i4;
    }
}
