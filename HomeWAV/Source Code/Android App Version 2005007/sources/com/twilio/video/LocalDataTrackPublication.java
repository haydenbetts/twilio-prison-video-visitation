package com.twilio.video;

public class LocalDataTrackPublication implements DataTrackPublication {
    private final LocalDataTrack localDataTrack;
    private final String sid;

    LocalDataTrackPublication(String str, LocalDataTrack localDataTrack2) {
        Preconditions.checkNotNull(str, "SID must not be null");
        Preconditions.checkArgument(!str.isEmpty(), "SID must not be empty");
        Preconditions.checkNotNull(localDataTrack2, "Local data track must not be null");
        this.sid = str;
        this.localDataTrack = localDataTrack2;
    }

    public String getTrackSid() {
        return this.sid;
    }

    public String getTrackName() {
        return this.localDataTrack.getName();
    }

    public boolean isTrackEnabled() {
        return this.localDataTrack.isEnabled();
    }

    public DataTrack getDataTrack() {
        return this.localDataTrack;
    }

    public LocalDataTrack getLocalDataTrack() {
        return this.localDataTrack;
    }
}
