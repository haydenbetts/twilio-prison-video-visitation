package com.twilio.video;

public class VideoDimensions {
    public static final VideoDimensions CIF_VIDEO_DIMENSIONS = new VideoDimensions(CIF_VIDEO_WIDTH, CIF_VIDEO_HEIGHT);
    public static final int CIF_VIDEO_HEIGHT = 288;
    public static final int CIF_VIDEO_WIDTH = 352;
    public static final VideoDimensions HD_1080P_VIDEO_DIMENSIONS = new VideoDimensions(HD_1080P_VIDEO_WIDTH, 1080);
    public static final int HD_1080P_VIDEO_HEIGHT = 1080;
    public static final int HD_1080P_VIDEO_WIDTH = 1920;
    public static final VideoDimensions HD_540P_VIDEO_DIMENSIONS = new VideoDimensions(960, 540);
    public static final int HD_540P_VIDEO_HEIGHT = 540;
    public static final int HD_540P_VIDEO_WIDTH = 960;
    public static final VideoDimensions HD_720P_VIDEO_DIMENSIONS = new VideoDimensions(1280, HD_720P_VIDEO_HEIGHT);
    public static final int HD_720P_VIDEO_HEIGHT = 720;
    public static final int HD_720P_VIDEO_WIDTH = 1280;
    public static final VideoDimensions HD_960P_VIDEO_DIMENSIONS = new VideoDimensions(1280, 960);
    public static final int HD_960P_VIDEO_HEIGHT = 960;
    public static final int HD_960P_VIDEO_WIDTH = 1280;
    public static final VideoDimensions HD_S1080P_VIDEO_DIMENSIONS = new VideoDimensions(1440, 1080);
    public static final int HD_S1080P_VIDEO_HEIGHT = 1080;
    public static final int HD_S1080P_VIDEO_WIDTH = 1440;
    public static final VideoDimensions VGA_VIDEO_DIMENSIONS = new VideoDimensions(640, 480);
    public static final int VGA_VIDEO_HEIGHT = 480;
    public static final int VGA_VIDEO_WIDTH = 640;
    public static final VideoDimensions WVGA_VIDEO_DIMENSIONS = new VideoDimensions(800, 480);
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

    public String toString() {
        return String.valueOf(this.width) + "x" + String.valueOf(this.height);
    }
}
