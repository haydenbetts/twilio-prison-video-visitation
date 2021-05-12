package com.twilio.video;

import androidx.annotation.NonNull;

public class OpusCodec extends AudioCodec {
    @NonNull
    public static final String NAME = "opus";

    public OpusCodec() {
        super(NAME);
    }
}
