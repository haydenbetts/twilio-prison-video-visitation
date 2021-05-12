package com.twilio.video;

public class VideoFormat {
    public final VideoDimensions dimensions;
    public final int framerate;
    public final VideoPixelFormat pixelFormat;

    public VideoFormat(VideoDimensions videoDimensions, int i, VideoPixelFormat videoPixelFormat) {
        this.dimensions = videoDimensions;
        this.framerate = i;
        this.pixelFormat = videoPixelFormat;
    }
}
