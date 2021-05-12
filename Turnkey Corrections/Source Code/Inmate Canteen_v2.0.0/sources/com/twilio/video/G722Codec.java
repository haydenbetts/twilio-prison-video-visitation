package com.twilio.video;

import androidx.annotation.NonNull;

public class G722Codec extends AudioCodec {
    @NonNull
    public static final String NAME = "G722";

    public G722Codec() {
        super(NAME);
    }
}
