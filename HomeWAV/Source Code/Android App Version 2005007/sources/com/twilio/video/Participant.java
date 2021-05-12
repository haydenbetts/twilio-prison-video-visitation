package com.twilio.video;

import java.util.List;

public interface Participant {
    List<AudioTrackPublication> getAudioTracks();

    List<DataTrackPublication> getDataTracks();

    String getIdentity();

    NetworkQualityLevel getNetworkQualityLevel();

    String getSid();

    List<VideoTrackPublication> getVideoTracks();
}
