package com.twilio.video;

import androidx.annotation.NonNull;

public class IsacCodec extends AudioCodec {
    @NonNull
    public static final String NAME = "isac";

    public IsacCodec() {
        super(NAME);
    }
}
