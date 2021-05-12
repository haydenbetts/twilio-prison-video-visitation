package com.twilio.video;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting(otherwise = 5)
class MediaOptions {
    @Nullable
    private final String audioFilePath;
    private final boolean enableH264;

    private MediaOptions(Builder builder) {
        this.enableH264 = builder.enableH264;
        this.audioFilePath = builder.audioFilePath;
    }

    static class Builder {
        /* access modifiers changed from: private */
        @Nullable
        public String audioFilePath;
        /* access modifiers changed from: private */
        public boolean enableH264;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public Builder enableH264(boolean z) {
            this.enableH264 = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder audioFilePath(@NonNull String str) {
            Preconditions.checkNotNull("audioFilePath should not be null", str);
            this.audioFilePath = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public MediaOptions build() {
            return new MediaOptions(this);
        }
    }
}
