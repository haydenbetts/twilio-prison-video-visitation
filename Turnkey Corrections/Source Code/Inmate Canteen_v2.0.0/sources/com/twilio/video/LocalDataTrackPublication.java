package com.twilio.video;

import androidx.annotation.NonNull;

public class LocalDataTrackPublication implements DataTrackPublication {
    private final LocalDataTrack localDataTrack;
    private final String sid;

    LocalDataTrackPublication(@NonNull String str, @NonNull LocalDataTrack localDataTrack2) {
        Preconditions.checkNotNull(str, "SID must not be null");
        Preconditions.checkArgument(!str.isEmpty(), "SID must not be empty");
        Preconditions.checkNotNull(localDataTrack2, "Local data track must not be null");
        this.sid = str;
        this.localDataTrack = localDataTrack2;
    }

    @NonNull
    public String getTrackSid() {
        return this.sid;
    }

    @NonNull
    public String getTrackName() {
        return this.localDataTrack.getName();
    }

    public boolean isTrackEnabled() {
        return this.localDataTrack.isEnabled();
    }

    public DataTrack getDataTrack() {
        return this.localDataTrack;
    }

    @NonNull
    public LocalDataTrack getLocalDataTrack() {
        return this.localDataTrack;
    }
}
