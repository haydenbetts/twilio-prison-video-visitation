package com.twilio.video;

public class Vp8Codec extends VideoCodec {
    public static final String NAME = "VP8";
    public final boolean simulcast;

    public Vp8Codec() {
        this(false);
    }

    public Vp8Codec(boolean z) {
        super(NAME);
        this.simulcast = z;
    }
}
