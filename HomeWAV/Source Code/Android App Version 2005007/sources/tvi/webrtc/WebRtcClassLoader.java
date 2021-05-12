package tvi.webrtc;

class WebRtcClassLoader {
    WebRtcClassLoader() {
    }

    static Object getClassLoader() {
        return WebRtcClassLoader.class.getClassLoader();
    }
}
