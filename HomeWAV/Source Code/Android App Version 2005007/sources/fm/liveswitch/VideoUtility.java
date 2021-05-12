package fm.liveswitch;

class VideoUtility {
    public static double getBitratePowerScale() {
        return 0.75d;
    }

    public static int getBitrate(int i, double d, double d2) {
        double d3 = (double) 307200;
        return (int) MathAssistant.ceil(MathAssistant.pow(((double) i) / d3, getBitratePowerScale()) * (((d * d3) * d2) / 1000.0d));
    }

    public static int getBitrate(int i, int i2, int i3, double d, double d2) {
        return (i2 <= 0 || i3 <= 0 || d <= 0.0d || d2 <= 0.0d) ? i : getBitrate(i2 * i3, d, d2);
    }

    public static VideoEncodingConfig getEncodingConfig(VideoDegradationPreference videoDegradationPreference, double d, double d2) {
        VideoEncodingConfig videoEncodingConfig = new VideoEncodingConfig();
        updateEncodingConfig(videoEncodingConfig, videoDegradationPreference, d, d2);
        return videoEncodingConfig;
    }

    public static int getPixelCount(int i, double d, double d2) {
        double d3 = (double) 307200;
        return (int) MathAssistant.round(MathAssistant.pow(((double) i) / (((d * d3) * d2) / 1000.0d), 1.0d / getBitratePowerScale()) * d3);
    }

    public static VideoDegradationPreference processDegradationPreference(VideoDegradationPreference videoDegradationPreference, VideoType videoType) {
        if (!Global.equals(videoDegradationPreference, VideoDegradationPreference.Automatic)) {
            return videoDegradationPreference;
        }
        if (Global.equals(videoType, VideoType.Camera)) {
            return VideoDegradationPreference.Resolution;
        }
        if (Global.equals(videoType, VideoType.Screen)) {
            return VideoDegradationPreference.FrameRate;
        }
        return VideoDegradationPreference.Balanced;
    }

    public static void updateEncodingConfig(VideoEncodingConfig videoEncodingConfig, VideoDegradationPreference videoDegradationPreference, double d, double d2) {
        if (Global.equals(videoDegradationPreference, VideoDegradationPreference.Balanced)) {
            d = MathAssistant.sqrt(d);
        }
        if (d2 > 0.0d) {
            if (Global.equals(videoDegradationPreference, VideoDegradationPreference.FrameRate) || Global.equals(videoDegradationPreference, VideoDegradationPreference.Balanced)) {
                videoEncodingConfig.setFrameRate(d2 * d);
            } else {
                videoEncodingConfig.setFrameRate(d2);
            }
        }
        if (Global.equals(videoDegradationPreference, VideoDegradationPreference.Resolution) || Global.equals(videoDegradationPreference, VideoDegradationPreference.Balanced)) {
            videoEncodingConfig.setScale(MathAssistant.sqrt(d));
        } else {
            videoEncodingConfig.setScale(1.0d);
        }
    }
}
