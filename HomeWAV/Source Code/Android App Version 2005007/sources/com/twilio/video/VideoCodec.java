package com.twilio.video;

public abstract class VideoCodec {
    private final String name;

    protected VideoCodec(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
