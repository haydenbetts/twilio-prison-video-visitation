package com.twilio.video;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RemoteVideoTrackPublication implements VideoTrackPublication {
    private boolean enabled;
    private final String name;
    private RemoteVideoTrack remoteVideoTrack;
    private final String sid;
    private boolean subscribed;

    RemoteVideoTrackPublication(boolean z, boolean z2, @NonNull String str, @NonNull String str2) {
        this.enabled = z2;
        this.subscribed = z;
        this.sid = str;
        this.name = str2;
    }

    @NonNull
    public String getTrackSid() {
        return this.sid;
    }

    @Nullable
    public synchronized VideoTrack getVideoTrack() {
        return this.remoteVideoTrack;
    }

    @NonNull
    public String getTrackName() {
        return this.name;
    }

    public synchronized boolean isTrackEnabled() {
        return this.enabled;
    }

    public synchronized boolean isTrackSubscribed() {
        return this.subscribed;
    }

    @Nullable
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
        if (this.remoteVideoTrack != null) {
            this.remoteVideoTrack.setEnabled(z);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void setRemoteVideoTrack(RemoteVideoTrack remoteVideoTrack2) {
        this.remoteVideoTrack = remoteVideoTrack2;
    }
}
