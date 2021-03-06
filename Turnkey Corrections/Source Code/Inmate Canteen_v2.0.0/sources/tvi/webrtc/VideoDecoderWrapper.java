package tvi.webrtc;

import tvi.webrtc.VideoDecoder;

class VideoDecoderWrapper {
    /* access modifiers changed from: private */
    @NativeClassQualifiedName("webrtc::jni::VideoDecoderWrapper")
    public static native void nativeOnDecodedFrame(long j, VideoFrame videoFrame, Integer num, Integer num2);

    VideoDecoderWrapper() {
    }

    @CalledByNative
    static VideoDecoder.Callback createDecoderCallback(long j) {
        return new VideoDecoder.Callback(j) {
            private final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            public final void onDecodedFrame(VideoFrame videoFrame, Integer num, Integer num2) {
                VideoDecoderWrapper.nativeOnDecodedFrame(this.f$0, videoFrame, num, num2);
            }
        };
    }
}
