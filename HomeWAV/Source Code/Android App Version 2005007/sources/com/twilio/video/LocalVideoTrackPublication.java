package com.twilio.video;

public class LocalVideoTrackPublication implements VideoTrackPublication {
    private final LocalVideoTrack localVideoTrack;
    private final String sid;

    LocalVideoTrackPublication(String str, LocalVideoTrack localVideoTrack2) {
        Preconditions.checkNotNull(str, "SID must not be null");
        Preconditions.checkArgument(!str.isEmpty(), "SID must not be empty");
        Preconditions.checkNotNull(localVideoTrack2, "Local video track must not be null");
        this.sid = str;
        this.localVideoTrack = localVideoTrack2;
    }

    public String getTrackSid() {
        return this.sid;
    }

    public String getTrackName() {
        return this.localVideoTrack.getName();
    }

    public boolean isTrackEnabled() {
        return this.localVideoTrack.isEnabled();
    }

    public VideoTrack getVideoTrack() {
        return this.localVideoTrack;
    }

    public LocalVideoTrack getLocalVideoTrack() {
        return this.localVideoTrack;
    }
}
