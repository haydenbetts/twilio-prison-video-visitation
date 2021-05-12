package com.twilio.video;

import androidx.annotation.NonNull;
import java.util.List;

public interface Participant {
    @NonNull
    List<AudioTrackPublication> getAudioTracks();

    @NonNull
    List<DataTrackPublication> getDataTracks();

    @NonNull
    String getIdentity();

    @NonNull
    NetworkQualityLevel getNetworkQualityLevel();

    @NonNull
    String getSid();

    @NonNull
    List<VideoTrackPublication> getVideoTracks();
}
