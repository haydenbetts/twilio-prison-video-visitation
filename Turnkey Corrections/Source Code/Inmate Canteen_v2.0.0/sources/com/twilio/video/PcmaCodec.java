package com.twilio.video;

import androidx.annotation.NonNull;

public class PcmaCodec extends AudioCodec {
    @NonNull
    public static final String NAME = "PCMA";

    public PcmaCodec() {
        super(NAME);
    }
}
