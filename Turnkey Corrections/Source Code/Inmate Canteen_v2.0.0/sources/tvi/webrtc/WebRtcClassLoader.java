package tvi.webrtc;

class WebRtcClassLoader {
    WebRtcClassLoader() {
    }

    @CalledByNative
    static Object getClassLoader() {
        return WebRtcClassLoader.class.getClassLoader();
    }
}
