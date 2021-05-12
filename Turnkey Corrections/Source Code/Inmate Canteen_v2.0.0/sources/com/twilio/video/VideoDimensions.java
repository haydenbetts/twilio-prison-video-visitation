package com.twilio.video;

import androidx.annotation.NonNull;

public class VideoDimensions {
    @NonNull
    public static final VideoDimensions CIF_VIDEO_DIMENSIONS = new VideoDimensions(CIF_VIDEO_WIDTH, CIF_VIDEO_HEIGHT);
    public static final int CIF_VIDEO_HEIGHT = 288;
    public static final int CIF_VIDEO_WIDTH = 352;
    @NonNull
    public static final VideoDimensions HD_1080P_VIDEO_DIMENSIONS = new VideoDimensions(HD_1080P_VIDEO_WIDTH, 1080);
    public static final int HD_1080P_VIDEO_HEIGHT = 1080;
    public static final int HD_1080P_VIDEO_WIDTH = 1920;
    @NonNull
    public static final VideoDimensions HD_540P_VIDEO_DIMENSIONS = new VideoDimensions(960, HD_540P_VIDEO_HEIGHT);
    public static final int HD_540P_VIDEO_HEIGHT = 540;
    public static final int HD_540P_VIDEO_WIDTH = 960;
    @NonNull
    public static final VideoDimensions HD_720P_VIDEO_DIMENSIONS = new VideoDimensions(1280, HD_720P_VIDEO_HEIGHT);
    public static final int HD_720P_VIDEO_HEIGHT = 720;
    public static final int HD_720P_VIDEO_WIDTH = 1280;
    @NonNull
    public static final VideoDimensions HD_960P_VIDEO_DIMENSIONS = new VideoDimensions(1280, 960);
    public static final int HD_960P_VIDEO_HEIGHT = 960;
    public static final int HD_960P_VIDEO_WIDTH = 1280;
    @NonNull
    public static final VideoDimensions HD_S1080P_VIDEO_DIMENSIONS = new VideoDimensions(HD_S1080P_VIDEO_WIDTH, 1080);
    public static final int HD_S1080P_VIDEO_HEIGHT = 1080;
    public static final int HD_S1080P_VIDEO_WIDTH = 1440;
    @NonNull
    public static final VideoDimensions VGA_VIDEO_DIMENSIONS = new VideoDimensions(VGA_VIDEO_WIDTH, 480);
    public static final int VGA_VIDEO_HEIGHT = 480;
    public static final int VGA_VIDEO_WIDTH = 640;
    @NonNull
    public static final VideoDimensions WVGA_VIDEO_DIMENSIONS = new VideoDimensions(WVGA_VIDEO_WIDTH, 480);
    public static final int WVGA_VIDEO_HEIGHT = 480;
    public static final int WVGA_VIDEO_WIDTH = 800;
    public final int height;
    public final int width;

    public VideoDimensions(int i, int i2) {
        if (i < 0) {
            throw new IllegalStateException("Width must not be less than 0");
        } else if (i2 >= 0) {
            this.width = i;
            this.height = i2;
        } else {
            throw new IllegalStateException("Height must not be less than 0");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VideoDimensions videoDimensions = (VideoDimensions) obj;
        if (this.width == videoDimensions.width && this.height == videoDimensions.height) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.width * 31) + this.height;
    }

    @NonNull
    public String toString() {
        return String.valueOf(this.width) + "x" + String.valueOf(this.height);
    }
}
