package com.twilio.video;

public class VideoFrame {
    public final VideoDimensions dimensions;
    public final byte[] imageBuffer;
    public final RotationAngle orientation;
    public final long timestamp;
    final tvi.webrtc.VideoFrame webRtcVideoFrame;

    public enum RotationAngle {
        ROTATION_0(0),
        ROTATION_90(90),
        ROTATION_180(180),
        ROTATION_270(270);
        
        private final int rotation;

        private RotationAngle(int i) {
            this.rotation = i;
        }

        public int getValue() {
            return this.rotation;
        }

        public static RotationAngle fromInt(int i) {
            if (i == 0) {
                return ROTATION_0;
            }
            if (i == 90) {
                return ROTATION_90;
            }
            if (i == 180) {
                return ROTATION_180;
            }
            if (i == 270) {
                return ROTATION_270;
            }
            throw new IllegalArgumentException("Orientation value must be 0, 90, 180 or 270: " + i);
        }
    }

    public VideoFrame(byte[] bArr, VideoDimensions videoDimensions, RotationAngle rotationAngle, long j) {
        this(bArr, (tvi.webrtc.VideoFrame) null, videoDimensions, rotationAngle, j);
    }

    VideoFrame(tvi.webrtc.VideoFrame videoFrame, VideoDimensions videoDimensions, RotationAngle rotationAngle) {
        this((byte[]) null, videoFrame, videoDimensions, rotationAngle, videoFrame.getTimestampNs());
    }

    private VideoFrame(byte[] bArr, tvi.webrtc.VideoFrame videoFrame, VideoDimensions videoDimensions, RotationAngle rotationAngle, long j) {
        this.imageBuffer = bArr;
        this.webRtcVideoFrame = videoFrame;
        this.dimensions = videoDimensions;
        this.orientation = rotationAngle;
        this.timestamp = j;
    }
}
