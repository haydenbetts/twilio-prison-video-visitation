package fm.liveswitch.sdp.rtcp;

import com.google.firebase.iid.MessengerIpcClient;

public class FeedbackAttributeType {
    public static String getAck() {
        return MessengerIpcClient.KEY_ACK;
    }

    public static String getApp() {
        return "app";
    }

    public static String getCcm() {
        return "ccm";
    }

    public static String getNack() {
        return "nack";
    }

    public static String getRemb() {
        return "goog-remb";
    }
}
