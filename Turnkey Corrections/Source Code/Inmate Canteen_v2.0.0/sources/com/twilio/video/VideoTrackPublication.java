package com.twilio.video;

import androidx.annotation.Nullable;

public interface VideoTrackPublication extends TrackPublication {
    @Nullable
    VideoTrack getVideoTrack();
}
