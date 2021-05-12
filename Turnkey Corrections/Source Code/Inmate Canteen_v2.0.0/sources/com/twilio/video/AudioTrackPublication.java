package com.twilio.video;

import androidx.annotation.Nullable;

public interface AudioTrackPublication extends TrackPublication {
    @Nullable
    AudioTrack getAudioTrack();
}
