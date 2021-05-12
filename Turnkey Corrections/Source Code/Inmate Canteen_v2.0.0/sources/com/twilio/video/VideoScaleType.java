package com.twilio.video;

import androidx.annotation.NonNull;

public enum VideoScaleType {
    ASPECT_FIT,
    ASPECT_FILL,
    ASPECT_BALANCED;

    @NonNull
    static VideoScaleType fromInt(int i) {
        if (i == ASPECT_FIT.ordinal()) {
            return ASPECT_FIT;
        }
        if (i == ASPECT_FILL.ordinal()) {
            return ASPECT_FILL;
        }
        if (i == ASPECT_BALANCED.ordinal()) {
            return ASPECT_BALANCED;
        }
        return ASPECT_FIT;
    }
}
