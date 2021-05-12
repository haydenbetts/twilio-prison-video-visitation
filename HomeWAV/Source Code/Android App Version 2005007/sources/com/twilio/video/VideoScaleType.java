package com.twilio.video;

public enum VideoScaleType {
    ASPECT_FIT,
    ASPECT_FILL,
    ASPECT_BALANCED;

    static VideoScaleType fromInt(int i) {
        VideoScaleType videoScaleType = ASPECT_FIT;
        if (i == videoScaleType.ordinal()) {
            return videoScaleType;
        }
        VideoScaleType videoScaleType2 = ASPECT_FILL;
        if (i == videoScaleType2.ordinal()) {
            return videoScaleType2;
        }
        VideoScaleType videoScaleType3 = ASPECT_BALANCED;
        return i == videoScaleType3.ordinal() ? videoScaleType3 : videoScaleType;
    }
}
