package fm.liveswitch;

import fm.liveswitch.opus.Format;
import java.util.ArrayList;

class AudioSimulcastConfig extends SimulcastConfig {
    public AudioSimulcastConfig(int i, int i2) {
        super(i, i2);
    }

    /* access modifiers changed from: package-private */
    public AudioEncodingConfig[] getEncodingConfigs() {
        ArrayList arrayList = new ArrayList();
        if (super.getDisabled()) {
            arrayList.add(new AudioEncodingConfig());
        } else {
            Format format = new Format();
            int min = MathAssistant.min(MathAssistant.max(format.getMinBitrate(), super.getPreferredBitrate()), format.getMaxBitrate());
            for (int i = 0; i < super.getEncodingCount(); i++) {
                double pow = 1.0d / MathAssistant.pow(2.0d, (double) i);
                AudioEncodingConfig audioEncodingConfig = new AudioEncodingConfig();
                audioEncodingConfig.setBitrate((int) MathAssistant.ceil(((double) min) * pow));
                if (audioEncodingConfig.getBitrate() >= format.getMinBitrate()) {
                    arrayList.add(audioEncodingConfig);
                }
            }
        }
        return (AudioEncodingConfig[]) arrayList.toArray(new AudioEncodingConfig[0]);
    }
}
