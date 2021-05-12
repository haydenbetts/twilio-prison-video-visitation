package fm.liveswitch.sdp;

import com.google.android.exoplayer2.util.MimeTypes;
import fm.liveswitch.Global;
import fm.liveswitch.StreamType;

public abstract class MediaType {
    public static String getApplication() {
        return MimeTypes.BASE_TYPE_APPLICATION;
    }

    public static String getAudio() {
        return "audio";
    }

    public static String getMessage() {
        return "message";
    }

    public static String getText() {
        return "text";
    }

    public static String getVideo() {
        return "video";
    }

    public static String fromStreamType(StreamType streamType) {
        if (Global.equals(streamType, StreamType.Audio)) {
            return getAudio();
        }
        if (Global.equals(streamType, StreamType.Video)) {
            return getVideo();
        }
        if (!Global.equals(streamType, StreamType.Application)) {
            if (Global.equals(streamType, StreamType.Message)) {
                return getMessage();
            }
            if (Global.equals(streamType, StreamType.Text)) {
                return getText();
            }
        }
        return getApplication();
    }

    public static StreamType toStreamType(String str) {
        if (Global.equals(str, getAudio())) {
            return StreamType.Audio;
        }
        if (Global.equals(str, getVideo())) {
            return StreamType.Video;
        }
        if (!Global.equals(str, getApplication())) {
            if (Global.equals(str, getMessage())) {
                return StreamType.Message;
            }
            if (Global.equals(str, getText())) {
                return StreamType.Text;
            }
        }
        return StreamType.Application;
    }
}
