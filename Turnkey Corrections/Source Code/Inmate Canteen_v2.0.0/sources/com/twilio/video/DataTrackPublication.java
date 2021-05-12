package com.twilio.video;

import androidx.annotation.Nullable;

public interface DataTrackPublication extends TrackPublication {
    @Nullable
    DataTrack getDataTrack();
}
