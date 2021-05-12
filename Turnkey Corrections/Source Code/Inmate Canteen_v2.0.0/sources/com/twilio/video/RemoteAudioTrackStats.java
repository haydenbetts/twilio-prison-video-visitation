package com.twilio.video;

import androidx.annotation.NonNull;

public class RemoteAudioTrackStats extends RemoteTrackStats {
    public final int audioLevel;
    public final int jitter;

    RemoteAudioTrackStats(@NonNull String str, int i, @NonNull String str2, @NonNull String str3, double d, long j, int i2, int i3, int i4) {
        super(str, i, str2, str3, d, j, i2);
        this.audioLevel = i3;
        this.jitter = i4;
    }
}
