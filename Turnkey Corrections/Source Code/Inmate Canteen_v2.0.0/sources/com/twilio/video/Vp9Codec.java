package com.twilio.video;

import androidx.annotation.NonNull;

public class Vp9Codec extends VideoCodec {
    @NonNull
    public static final String NAME = "VP9";

    public Vp9Codec() {
        super(NAME);
    }
}
