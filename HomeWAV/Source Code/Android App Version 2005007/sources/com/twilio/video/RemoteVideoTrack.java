package com.twilio.video;

import tvi.webrtc.VideoTrack;

public class RemoteVideoTrack extends VideoTrack {
    private final String sid;

    RemoteVideoTrack(VideoTrack videoTrack, String str, String str2, boolean z) {
        super(videoTrack, z, str2);
        this.sid = str;
    }

    public String getSid() {
        return this.sid;
    }
}
