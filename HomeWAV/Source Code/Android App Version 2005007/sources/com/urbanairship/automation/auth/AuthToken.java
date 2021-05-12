package com.urbanairship.automation.auth;

class AuthToken {
    private final String channelId;
    private final long expiration;
    private final String token;

    AuthToken(String str, String str2, long j) {
        this.token = str2;
        this.expiration = j;
        this.channelId = str;
    }

    public long getExpiration() {
        return this.expiration;
    }

    public String getToken() {
        return this.token;
    }

    public String getChannelId() {
        return this.channelId;
    }
}
