package tvi.webrtc;

import com.microsoft.appcenter.Constants;

public class IceCandidate {
    public final String sdp;
    public final int sdpMLineIndex;
    public final String sdpMid;
    public final String serverUrl;

    public IceCandidate(String str, int i, String str2) {
        this.sdpMid = str;
        this.sdpMLineIndex = i;
        this.sdp = str2;
        this.serverUrl = "";
    }

    IceCandidate(String str, int i, String str2, String str3) {
        this.sdpMid = str;
        this.sdpMLineIndex = i;
        this.sdp = str2;
        this.serverUrl = str3;
    }

    public String toString() {
        return this.sdpMid + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + this.sdpMLineIndex + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + this.sdp + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + this.serverUrl;
    }

    /* access modifiers changed from: package-private */
    public String getSdpMid() {
        return this.sdpMid;
    }

    /* access modifiers changed from: package-private */
    public String getSdp() {
        return this.sdp;
    }
}
