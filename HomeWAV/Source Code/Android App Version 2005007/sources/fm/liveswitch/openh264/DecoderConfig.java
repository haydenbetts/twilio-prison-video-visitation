package fm.liveswitch.openh264;

public class DecoderConfig {
    private NativeDecoderConfig __config = new NativeDecoderConfig();
    private ErrorConcealmentMethod __errorConcealmentMethod = ErrorConcealmentMethod.getDisable();
    private VideoBitstreamType __videoBitstreamType = VideoBitstreamType.getAvc();

    public DecoderConfig deepCopy() {
        DecoderConfig decoderConfig = new DecoderConfig();
        decoderConfig.__errorConcealmentMethod = this.__errorConcealmentMethod;
        decoderConfig.__videoBitstreamType = this.__videoBitstreamType;
        decoderConfig.__config = this.__config.deepCopy();
        return decoderConfig;
    }

    public int getCpuLoad() {
        return this.__config.getCpuLoad();
    }

    public ErrorConcealmentMethod getEcActive() {
        return this.__errorConcealmentMethod;
    }

    public String getFileNameRestructed() {
        return this.__config.getFileNameRestructed();
    }

    /* access modifiers changed from: package-private */
    public NativeDecoderConfig getNativeConfig() {
        return this.__config;
    }

    public boolean getParseOnly() {
        return this.__config.getParseOnly() == 1;
    }

    public char getTargetDqLayer() {
        return this.__config.getTargetDqLayer();
    }

    public VideoBitstreamType getVideoBsType() {
        return this.__videoBitstreamType;
    }

    public int getVideoSize() {
        return this.__config.getVideoSize();
    }

    public void setCpuLoad(int i) {
        this.__config.setCpuLoad(i);
    }

    public void setEcActive(ErrorConcealmentMethod errorConcealmentMethod) {
        this.__errorConcealmentMethod = errorConcealmentMethod;
        this.__config.setEcActiveIdc(errorConcealmentMethod.getValue());
    }

    public void setFileNameRestructed(String str) {
        this.__config.setFileNameRestructed(str);
    }

    public void setParseOnly(boolean z) {
        this.__config.setParseOnly(z ? 1 : 0);
    }

    public void setTargetDqLayer(char c) {
        this.__config.setTargetDqLayer(c);
    }

    public void setVideoBsType(VideoBitstreamType videoBitstreamType) {
        this.__videoBitstreamType = videoBitstreamType;
        this.__config.setVideoBsType(videoBitstreamType.getValue());
    }

    public void setVideoSize(int i) {
        this.__config.setVideoSize(i);
    }
}
