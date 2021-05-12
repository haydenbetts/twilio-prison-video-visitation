package com.twilio.video;

import androidx.annotation.NonNull;

public abstract class VideoCodec {
    private final String name;

    protected VideoCodec(@NonNull String str) {
        this.name = str;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @NonNull
    public String toString() {
        return this.name;
    }
}
