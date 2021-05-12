package com.twilio.video;

import androidx.annotation.NonNull;

public interface Track {
    @NonNull
    String getName();

    boolean isEnabled();
}
