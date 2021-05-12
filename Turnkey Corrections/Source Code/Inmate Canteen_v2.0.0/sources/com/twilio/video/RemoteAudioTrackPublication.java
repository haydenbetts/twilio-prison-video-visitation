package com.twilio.video;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RemoteAudioTrackPublication implements AudioTrackPublication {
    private boolean enabled;
    private final String name;
    private RemoteAudioTrack remoteAudioTrack;
    private final String sid;
    private boolean subscribed;

    RemoteAudioTrackPublication(boolean z, boolean z2, @NonNull String str, @NonNull String str2) {
        this.subscribed = z;
        this.sid = str;
        this.name = str2;
        this.enabled = z2;
    }

    @NonNull
    public String getTrackSid() {
        return this.sid;
    }

    @Nullable
    public synchronized AudioTrack getAudioTrack() {
        return this.remoteAudioTrack;
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
    public synchronized RemoteAudioTrack getRemoteAudioTrack() {
        return this.remoteAudioTrack;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setSubscribed(boolean z) {
        this.subscribed = z;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setEnabled(boolean z) {
        this.enabled = z;
        if (this.remoteAudioTrack != null) {
            this.remoteAudioTrack.setEnabled(z);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void setRemoteAudioTrack(RemoteAudioTrack remoteAudioTrack2) {
        this.remoteAudioTrack = remoteAudioTrack2;
    }
}
