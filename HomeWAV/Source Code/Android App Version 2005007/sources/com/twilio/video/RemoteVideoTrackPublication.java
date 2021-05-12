package com.twilio.video;

public class RemoteVideoTrackPublication implements VideoTrackPublication {
    private boolean enabled;
    private final String name;
    private RemoteVideoTrack remoteVideoTrack;
    private final String sid;
    private boolean subscribed;

    RemoteVideoTrackPublication(boolean z, boolean z2, String str, String str2) {
        this.enabled = z2;
        this.subscribed = z;
        this.sid = str;
        this.name = str2;
    }

    public String getTrackSid() {
        return this.sid;
    }

    public synchronized VideoTrack getVideoTrack() {
        return this.remoteVideoTrack;
    }

    public String getTrackName() {
        return this.name;
    }

    public synchronized boolean isTrackEnabled() {
        return this.enabled;
    }

    public synchronized boolean isTrackSubscribed() {
        return this.subscribed;
    }

    public synchronized RemoteVideoTrack getRemoteVideoTrack() {
        return this.remoteVideoTrack;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setSubscribed(boolean z) {
        this.subscribed = z;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setEnabled(boolean z) {
        this.enabled = z;
        RemoteVideoTrack remoteVideoTrack2 = this.remoteVideoTrack;
        if (remoteVideoTrack2 != null) {
            remoteVideoTrack2.setEnabled(z);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void setRemoteVideoTrack(RemoteVideoTrack remoteVideoTrack2) {
        this.remoteVideoTrack = remoteVideoTrack2;
    }
}
