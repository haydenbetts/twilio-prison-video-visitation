package com.twilio.video;

public class IceCandidateStats {
    public final String candidateType;
    public final boolean deleted;
    public final String ip;
    public final boolean isRemote;
    public final int port;
    public final int priority;
    public final String protocol;
    public final String transportId;
    public final String url;

    public IceCandidateStats(String str, boolean z, String str2, int i, String str3, String str4, int i2, String str5, boolean z2) {
        this.transportId = str;
        this.isRemote = z;
        this.ip = str2;
        this.port = i;
        this.protocol = str3;
        this.candidateType = str4;
        this.priority = i2;
        this.url = str5;
        this.deleted = z2;
    }
}
