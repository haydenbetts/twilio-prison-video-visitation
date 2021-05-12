package com.twilio.video;

import androidx.annotation.NonNull;

public class LocalVideoTrackPublication implements VideoTrackPublication {
    private final LocalVideoTrack localVideoTrack;
    private final String sid;

    LocalVideoTrackPublication(@NonNull String str, @NonNull LocalVideoTrack localVideoTrack2) {
        Preconditions.checkNotNull(str, "SID must not be null");
        Preconditions.checkArgument(!str.isEmpty(), "SID must not be empty");
        Preconditions.checkNotNull(localVideoTrack2, "Local video track must not be null");
        this.sid = str;
        this.localVideoTrack = localVideoTrack2;
    }

    @NonNull
    public String getTrackSid() {
        return this.sid;
    }

    @NonNull
    public String getTrackName() {
        return this.localVideoTrack.getName();
    }

    public boolean isTrackEnabled() {
        return this.localVideoTrack.isEnabled();
    }

    @NonNull
    public VideoTrack getVideoTrack() {
        return this.localVideoTrack;
    }

    @NonNull
    public LocalVideoTrack getLocalVideoTrack() {
        return this.localVideoTrack;
    }
}
