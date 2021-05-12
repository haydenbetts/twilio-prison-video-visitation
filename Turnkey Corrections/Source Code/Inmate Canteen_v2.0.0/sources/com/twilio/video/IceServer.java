package com.twilio.video;

import androidx.annotation.NonNull;

public class IceServer {
    @NonNull
    public final String password;
    @NonNull
    public final String serverUrl;
    @NonNull
    public final String username;

    public IceServer(@NonNull String str) {
        this(str, "", "");
    }

    public IceServer(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.username = str2;
        this.password = str3;
        this.serverUrl = str;
    }
}
