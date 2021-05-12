package com.twilio.video;

import androidx.annotation.NonNull;

public class Vp8Codec extends VideoCodec {
    @NonNull
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
