package com.twilio.video;

import androidx.annotation.NonNull;
import tvi.webrtc.VideoTrack;

public class RemoteVideoTrack extends VideoTrack {
    private final String sid;

    RemoteVideoTrack(@NonNull VideoTrack videoTrack, @NonNull String str, @NonNull String str2, boolean z) {
        super(videoTrack, z, str2);
        this.sid = str;
    }

    @NonNull
    public String getSid() {
        return this.sid;
    }
}
