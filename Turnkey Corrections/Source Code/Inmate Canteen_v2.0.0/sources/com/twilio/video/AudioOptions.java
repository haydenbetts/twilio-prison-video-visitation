package com.twilio.video;

import androidx.annotation.NonNull;

public class AudioOptions {
    public final boolean audioJitterBufferFastAccelerate;
    public final boolean autoGainControl;
    public final boolean echoCancellation;
    public final boolean highpassFilter;
    public final boolean noiseSuppression;
    public final boolean stereoSwapping;
    public final boolean typingDetection;

    private AudioOptions(@NonNull Builder builder) {
        this.echoCancellation = builder.echoCancellation;
        this.autoGainControl = builder.autoGainControl;
        this.noiseSuppression = builder.noiseSuppression;
        this.highpassFilter = builder.highpassFilter;
        this.stereoSwapping = builder.stereoSwapping;
        this.audioJitterBufferFastAccelerate = builder.audioJitterBufferFastAccelerate;
        this.typingDetection = builder.typingDetection;
    }

    @NonNull
    public String toString() {
        return "AudioOptions{echoCancellation=" + this.echoCancellation + ", autoGainControl=" + this.autoGainControl + ", noiseSuppression=" + this.noiseSuppression + ", highpassFilter=" + this.highpassFilter + ", stereoSwapping=" + this.stereoSwapping + ", audioJitterBufferFastAccelerate=" + this.audioJitterBufferFastAccelerate + ", typingDetection=" + this.typingDetection + '}';
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean audioJitterBufferFastAccelerate;
        /* access modifiers changed from: private */
        public boolean autoGainControl;
        /* access modifiers changed from: private */
        public boolean echoCancellation;
        /* access modifiers changed from: private */
        public boolean highpassFilter;
        /* access modifiers changed from: private */
        public boolean noiseSuppression;
        /* access modifiers changed from: private */
        public boolean stereoSwapping;
        /* access modifiers changed from: private */
        public boolean typingDetection;

        @NonNull
        public Builder echoCancellation(boolean z) {
            this.echoCancellation = z;
            return this;
        }

        @NonNull
        public Builder autoGainControl(boolean z) {
            this.autoGainControl = z;
            return this;
        }

        @NonNull
        public Builder noiseSuppression(boolean z) {
            this.noiseSuppression = z;
            return this;
        }

        @NonNull
        public Builder highpassFilter(boolean z) {
            this.highpassFilter = z;
            return this;
        }

        @NonNull
        public Builder stereoSwapping(boolean z) {
            this.stereoSwapping = z;
            return this;
        }

        @NonNull
        public Builder audioJitterBufferFastAccelerate(boolean z) {
            this.audioJitterBufferFastAccelerate = z;
            return this;
        }

        @NonNull
        public Builder typingDetection(boolean z) {
            this.typingDetection = z;
            return this;
        }

        @NonNull
        public AudioOptions build() {
            return new AudioOptions(this);
        }
    }
}
