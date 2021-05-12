package tvi.webrtc.audio;

import tvi.webrtc.voiceengine.WebRtcAudioRecord;
import tvi.webrtc.voiceengine.WebRtcAudioTrack;

@Deprecated
public class LegacyAudioDeviceModule implements AudioDeviceModule {
    public long getNativeAudioDeviceModulePointer() {
        return 0;
    }

    public void release() {
    }

    public static AudioDeviceModule Create() {
        return new LegacyAudioDeviceModule();
    }

    public void setSpeakerMute(boolean z) {
        WebRtcAudioTrack.setSpeakerMute(z);
    }

    public void setMicrophoneMute(boolean z) {
        WebRtcAudioRecord.setMicrophoneMute(z);
    }
}
