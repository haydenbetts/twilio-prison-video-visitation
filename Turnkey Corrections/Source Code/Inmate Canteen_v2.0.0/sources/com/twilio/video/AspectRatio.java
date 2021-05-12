package com.twilio.video;

import androidx.annotation.NonNull;

public class AspectRatio {
    @NonNull
    public static final AspectRatio ASPECT_RATIO_11_9 = new AspectRatio(11, 9);
    @NonNull
    public static final AspectRatio ASPECT_RATIO_16_9 = new AspectRatio(16, 9);
    @NonNull
    public static final AspectRatio ASPECT_RATIO_4_3 = new AspectRatio(4, 3);
    public final int denominator;
    public final int numerator;

    public AspectRatio(int i, int i2) {
        this.numerator = i;
        this.denominator = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AspectRatio aspectRatio = (AspectRatio) obj;
        if (this.numerator == aspectRatio.numerator && this.denominator == aspectRatio.denominator) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.numerator * 31) + this.denominator;
    }
}
