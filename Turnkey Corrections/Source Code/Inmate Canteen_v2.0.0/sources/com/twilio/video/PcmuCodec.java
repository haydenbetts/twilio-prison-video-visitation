package com.twilio.video;

import androidx.annotation.NonNull;

public class PcmuCodec extends AudioCodec {
    @NonNull
    public static final String NAME = "PCMU";

    public PcmuCodec() {
        super(NAME);
    }
}
