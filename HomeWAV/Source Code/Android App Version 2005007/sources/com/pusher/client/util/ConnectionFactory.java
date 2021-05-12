package com.pusher.client.util;

public abstract class ConnectionFactory {
    private String channelName;
    private String socketId;

    public abstract String getBody();

    public abstract String getCharset();

    public abstract String getContentType();

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String str) {
        this.channelName = str;
    }

    public String getSocketId() {
        return this.socketId;
    }

    public void setSocketId(String str) {
        this.socketId = str;
    }
}
