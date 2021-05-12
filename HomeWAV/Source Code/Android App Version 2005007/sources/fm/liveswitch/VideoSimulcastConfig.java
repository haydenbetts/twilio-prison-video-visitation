package fm.liveswitch;

import fm.liveswitch.vp8.Format;
import java.util.ArrayList;

class VideoSimulcastConfig extends SimulcastConfig {
    private double __bitsPerPixel;
    private VideoDegradationPreference _degradationPreference;

    public double getBitsPerPixel() {
        return this.__bitsPerPixel;
    }

    public VideoDegradationPreference getDegradationPreference() {
        return this._degradationPreference;
    }

    /* access modifiers changed from: package-private */
    public VideoEncodingConfig[] getEncodingConfigs(VideoType videoType, int i, int i2, double d) {
        ArrayList arrayList = new ArrayList();
        if (super.getDisabled()) {
            arrayList.add(new VideoEncodingConfig());
        } else {
            Format format = new Format();
            int min = MathAssistant.min(MathAssistant.max(format.getMinBitrate(), VideoUtility.getBitrate(super.getPreferredBitrate(), i, i2, d, getBitsPerPixel())), format.getMaxBitrate());
            VideoType videoType2 = videoType;
            VideoDegradationPreference processDegradationPreference = VideoUtility.processDegradationPreference(getDegradationPreference(), videoType);
            for (int i3 = 0; i3 < super.getEncodingCount(); i3++) {
                double pow = MathAssistant.pow(1.0d / MathAssistant.pow(2.0d, (double) i3), VideoUtility.getBitratePowerScale());
                VideoEncodingConfig videoEncodingConfig = new VideoEncodingConfig();
                videoEncodingConfig.setBitrate((int) MathAssistant.ceil(((double) min) * pow));
                VideoUtility.updateEncodingConfig(videoEncodingConfig, processDegradationPreference, pow, d);
                if (videoEncodingConfig.getBitrate() >= format.getMinBitrate()) {
                    arrayList.add(videoEncodingConfig);
                }
            }
        }
        return (VideoEncodingConfig[]) arrayList.toArray(new VideoEncodingConfig[0]);
    }

    public void setBitsPerPixel(double d) {
        if (d >= 0.0d) {
            this.__bitsPerPixel = d;
            return;
        }
        throw new RuntimeException(new Exception("Bits-per-pixel must be greater than or equal to zero."));
    }

    public void setDegradationPreference(VideoDegradationPreference videoDegradationPreference) {
        this._degradationPreference = videoDegradationPreference;
    }

    public VideoSimulcastConfig(int i, int i2) {
        this(i, i2, 0.0d);
    }

    public VideoSimulcastConfig(int i, int i2, double d) {
        super(i, i2);
        setBitsPerPixel(d);
        setDegradationPreference(VideoDegradationPreference.Automatic);
    }
}
