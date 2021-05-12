package com.twilio.video;

public abstract class AudioCodec {
    private final String name;

    protected AudioCodec(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
