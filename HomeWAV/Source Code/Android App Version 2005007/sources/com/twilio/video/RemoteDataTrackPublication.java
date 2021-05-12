package com.twilio.video;

public class RemoteDataTrackPublication implements DataTrackPublication {
    private boolean enabled;
    private final String name;
    private RemoteDataTrack remoteDataTrack;
    private final String sid;
    private boolean subscribed;

    RemoteDataTrackPublication(boolean z, boolean z2, String str, String str2) {
        this.enabled = z2;
        this.subscribed = z;
        this.sid = str;
        this.name = str2;
    }

    public String getTrackSid() {
        return this.sid;
    }

    public synchronized DataTrack getDataTrack() {
        return this.remoteDataTrack;
    }

    public String getTrackName() {
        return this.name;
    }

    public boolean isTrackEnabled() {
        return this.enabled;
    }

    public synchronized boolean isTrackSubscribed() {
        return this.subscribed;
    }

    public synchronized RemoteDataTrack getRemoteDataTrack() {
        return this.remoteDataTrack;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setSubscribed(boolean z) {
        this.subscribed = z;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setRemoteDataTrack(RemoteDataTrack remoteDataTrack2) {
        this.remoteDataTrack = remoteDataTrack2;
    }
}
