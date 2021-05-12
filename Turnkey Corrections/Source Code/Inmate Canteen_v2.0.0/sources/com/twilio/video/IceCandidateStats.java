package com.twilio.video;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IceCandidateStats {
    @NonNull
    public final String candidateType;
    public final boolean deleted;
    @NonNull
    public final String ip;
    public final boolean isRemote;
    public final int port;
    public final int priority;
    @NonNull
    public final String protocol;
    @NonNull
    public final String transportId;
    @Nullable
    public final String url;

    public IceCandidateStats(@NonNull String str, boolean z, @NonNull String str2, int i, @NonNull String str3, @NonNull String str4, int i2, @Nullable String str5, boolean z2) {
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
