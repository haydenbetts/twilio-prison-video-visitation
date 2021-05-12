package com.twilio.video;

class MediaOptions {
    private final String audioFilePath;
    private final boolean enableH264;

    private MediaOptions(Builder builder) {
        this.enableH264 = builder.enableH264;
        this.audioFilePath = builder.audioFilePath;
    }

    static class Builder {
        /* access modifiers changed from: private */
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
        public Builder audioFilePath(String str) {
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
