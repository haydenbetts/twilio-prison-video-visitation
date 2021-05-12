package tvi.webrtc;

public interface StatsObserver {
    @CalledByNative
    void onComplete(StatsReport[] statsReportArr);
}
