package com.twilio.video;

import androidx.annotation.NonNull;

public class H264Codec extends VideoCodec {
    @NonNull
    public static final String NAME = "H264";

    public H264Codec() {
        super(NAME);
    }
}
