package com.twilio.video;

public interface TrackPublication {
    String getTrackName();

    String getTrackSid();

    boolean isTrackEnabled();
}
