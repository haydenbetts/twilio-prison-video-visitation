package com.twilio.video;

import androidx.annotation.NonNull;

public class VideoConstraints {
    public static final int FPS_10 = 10;
    public static final int FPS_15 = 15;
    public static final int FPS_20 = 20;
    public static final int FPS_24 = 24;
    public static final int FPS_30 = 30;
    private final AspectRatio aspectRatio;
    private final int maxFps;
    private final VideoDimensions maxVideoDimensions;
    private final int minFps;
    private final VideoDimensions minVideoDimensions;

    private VideoConstraints(@NonNull Builder builder) {
        this.minVideoDimensions = builder.minVideoDimensions;
        this.maxVideoDimensions = builder.maxVideoDimensions;
        this.minFps = builder.minFps;
        this.maxFps = builder.maxFps;
        this.aspectRatio = builder.aspectRatio;
    }

    @NonNull
    public VideoDimensions getMinVideoDimensions() {
        return this.minVideoDimensions;
    }

    @NonNull
    public VideoDimensions getMaxVideoDimensions() {
        return this.maxVideoDimensions;
    }

    public int getMinFps() {
        return this.minFps;
    }

    public int getMaxFps() {
        return this.maxFps;
    }

    @NonNull
    public AspectRatio getAspectRatio() {
        return this.aspectRatio;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VideoConstraints videoConstraints = (VideoConstraints) obj;
        if (this.minFps != videoConstraints.minFps || this.maxFps != videoConstraints.maxFps || !this.minVideoDimensions.equals(videoConstraints.minVideoDimensions) || !this.maxVideoDimensions.equals(videoConstraints.maxVideoDimensions) || !this.aspectRatio.equals(videoConstraints.aspectRatio)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((this.minVideoDimensions.hashCode() * 31) + this.maxVideoDimensions.hashCode()) * 31) + this.minFps) * 31) + this.maxFps) * 31) + this.aspectRatio.hashCode();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public AspectRatio aspectRatio = new AspectRatio(0, 0);
        /* access modifiers changed from: private */
        public int maxFps = 0;
        /* access modifiers changed from: private */
        public VideoDimensions maxVideoDimensions = new VideoDimensions(0, 0);
        /* access modifiers changed from: private */
        public int minFps = 0;
        /* access modifiers changed from: private */
        public VideoDimensions minVideoDimensions = new VideoDimensions(0, 0);

        @NonNull
        public Builder minVideoDimensions(@NonNull VideoDimensions videoDimensions) {
            this.minVideoDimensions = videoDimensions;
            return this;
        }

        @NonNull
        public Builder maxVideoDimensions(@NonNull VideoDimensions videoDimensions) {
            this.maxVideoDimensions = videoDimensions;
            return this;
        }

        @NonNull
        public Builder minFps(int i) {
            this.minFps = i;
            return this;
        }

        @NonNull
        public Builder maxFps(int i) {
            this.maxFps = i;
            return this;
        }

        @NonNull
        public Builder aspectRatio(@NonNull AspectRatio aspectRatio2) {
            this.aspectRatio = aspectRatio2;
            return this;
        }

        @NonNull
        public VideoConstraints build() {
            VideoDimensions videoDimensions = this.minVideoDimensions;
            if (videoDimensions == null) {
                throw new NullPointerException("MinVideoDimensions must not be null");
            } else if (this.maxVideoDimensions != null) {
                int i = this.minFps;
                int i2 = this.maxFps;
                if (i > i2) {
                    throw new IllegalStateException("MinFps " + this.minFps + " is greater than maxFps " + this.maxFps);
                } else if (i < 0) {
                    throw new IllegalStateException("MinFps is less than 0");
                } else if (i2 < 0) {
                    throw new IllegalStateException("MaxFps is less than 0");
                } else if (videoDimensions.width > this.maxVideoDimensions.width) {
                    throw new IllegalStateException("Min video dimensions width " + this.minVideoDimensions.width + " is greater than max video dimensions width " + this.maxVideoDimensions.width);
                } else if (this.minVideoDimensions.height > this.maxVideoDimensions.height) {
                    throw new IllegalStateException("Min video dimensions height " + this.minVideoDimensions.height + " is greater than max video dimensions height " + this.maxVideoDimensions.height);
                } else if (this.aspectRatio.numerator < 0) {
                    throw new IllegalStateException("aspectRatio numerator is less than 0");
                } else if (this.aspectRatio.denominator >= 0) {
                    return new VideoConstraints(this);
                } else {
                    throw new IllegalStateException("aspectRatio denominator is less than 0");
                }
            } else {
                throw new NullPointerException("MaxVideoDimensions must not be null");
            }
        }
    }
}
