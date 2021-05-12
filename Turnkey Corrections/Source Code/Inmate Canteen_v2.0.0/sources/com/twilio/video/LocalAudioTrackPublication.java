package com.twilio.video;

import androidx.annotation.NonNull;

public class LocalAudioTrackPublication implements AudioTrackPublication {
    private final LocalAudioTrack localAudioTrack;
    private final String sid;

    LocalAudioTrackPublication(@NonNull String str, @NonNull LocalAudioTrack localAudioTrack2) {
        Preconditions.checkNotNull(str, "SID must not be null");
        Preconditions.checkNotNull(localAudioTrack2, "Local audio track must not be null");
        Preconditions.checkArgument(!str.isEmpty(), "SID must not be empty");
        this.sid = str;
        this.localAudioTrack = localAudioTrack2;
    }

    @NonNull
    public String getTrackSid() {
        return this.sid;
    }

    @NonNull
    public String getTrackName() {
        return this.localAudioTrack.getName();
    }

    public boolean isTrackEnabled() {
        return this.localAudioTrack.isEnabled();
    }

    @NonNull
    public AudioTrack getAudioTrack() {
        return this.localAudioTrack;
    }

    @NonNull
    public LocalAudioTrack getLocalAudioTrack() {
        return this.localAudioTrack;
    }
}
