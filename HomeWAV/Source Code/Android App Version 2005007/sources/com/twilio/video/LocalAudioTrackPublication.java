package com.twilio.video;

public class LocalAudioTrackPublication implements AudioTrackPublication {
    private final LocalAudioTrack localAudioTrack;
    private final String sid;

    LocalAudioTrackPublication(String str, LocalAudioTrack localAudioTrack2) {
        Preconditions.checkNotNull(str, "SID must not be null");
        Preconditions.checkNotNull(localAudioTrack2, "Local audio track must not be null");
        Preconditions.checkArgument(!str.isEmpty(), "SID must not be empty");
        this.sid = str;
        this.localAudioTrack = localAudioTrack2;
    }

    public String getTrackSid() {
        return this.sid;
    }

    public String getTrackName() {
        return this.localAudioTrack.getName();
    }

    public boolean isTrackEnabled() {
        return this.localAudioTrack.isEnabled();
    }

    public AudioTrack getAudioTrack() {
        return this.localAudioTrack;
    }

    public LocalAudioTrack getLocalAudioTrack() {
        return this.localAudioTrack;
    }
}
