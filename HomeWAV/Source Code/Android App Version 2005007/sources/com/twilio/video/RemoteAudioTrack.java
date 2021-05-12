package com.twilio.video;

public class RemoteAudioTrack extends AudioTrack {
    private static final Logger logger = Logger.getLogger(RemoteAudioTrack.class);
    private long nativeRemoteAudioTrackHandle;
    private boolean playbackEnabled = true;
    private final String sid;

    private native void nativeEnablePlayback(long j, boolean z);

    private native void nativeRelease(long j);

    RemoteAudioTrack(long j, String str, String str2, boolean z) {
        super(j, z, str2);
        this.nativeRemoteAudioTrackHandle = j;
        this.sid = str;
    }

    public String getSid() {
        return this.sid;
    }

    public synchronized void enablePlayback(boolean z) {
        this.playbackEnabled = z;
        if (!isReleased()) {
            nativeEnablePlayback(this.nativeRemoteAudioTrackHandle, z);
        } else {
            logger.w("Cannot enable playback of remote audio track that is no longer subscribed to");
        }
    }

    public synchronized boolean isPlaybackEnabled() {
        return this.playbackEnabled;
    }

    /* access modifiers changed from: package-private */
    public synchronized void release() {
        if (!isReleased()) {
            nativeRelease(this.nativeRemoteAudioTrackHandle);
            this.nativeRemoteAudioTrackHandle = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isReleased() {
        return this.nativeRemoteAudioTrackHandle == 0;
    }
}
