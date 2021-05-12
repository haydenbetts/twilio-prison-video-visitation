package com.twilio.video;

import androidx.annotation.NonNull;

public interface TrackPublication {
    @NonNull
    String getTrackName();

    @NonNull
    String getTrackSid();

    boolean isTrackEnabled();
}
