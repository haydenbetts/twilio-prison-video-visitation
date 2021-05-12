package fm.liveswitch.openh264;

import fm.liveswitch.StringExtensions;

class NativeDecoderConfig {
    private int __cpuLoad = 0;
    private int __ecActiveIdc = 0;
    private String __fileNameRestructed = StringExtensions.empty;
    private int __parseOnly = 0;
    private char __targetDqLayer = 0;
    private int __videoBsType = 0;
    private int __videoSize = 0;

    public NativeDecoderConfig deepCopy() {
        NativeDecoderConfig nativeDecoderConfig = new NativeDecoderConfig();
        nativeDecoderConfig.__fileNameRestructed = this.__fileNameRestructed;
        nativeDecoderConfig.__cpuLoad = this.__cpuLoad;
        nativeDecoderConfig.__targetDqLayer = this.__targetDqLayer;
        nativeDecoderConfig.__ecActiveIdc = this.__ecActiveIdc;
        nativeDecoderConfig.__parseOnly = this.__parseOnly;
        nativeDecoderConfig.__videoSize = this.__videoSize;
        nativeDecoderConfig.__videoBsType = this.__videoBsType;
        return nativeDecoderConfig;
    }

    public int getCpuLoad() {
        return this.__cpuLoad;
    }

    public int getEcActiveIdc() {
        return this.__ecActiveIdc;
    }

    public String getFileNameRestructed() {
        return this.__fileNameRestructed;
    }

    public int getParseOnly() {
        return this.__parseOnly;
    }

    public char getTargetDqLayer() {
        return this.__targetDqLayer;
    }

    public int getVideoBsType() {
        return this.__videoBsType;
    }

    public int getVideoSize() {
        return this.__videoSize;
    }

    public void setCpuLoad(int i) {
        this.__cpuLoad = i;
    }

    public void setEcActiveIdc(int i) {
        this.__ecActiveIdc = i;
    }

    public void setFileNameRestructed(String str) {
        this.__fileNameRestructed = str;
    }

    public void setParseOnly(int i) {
        this.__parseOnly = i;
    }

    public void setTargetDqLayer(char c) {
        this.__targetDqLayer = c;
    }

    public void setVideoBsType(int i) {
        this.__videoBsType = i;
    }

    public void setVideoSize(int i) {
        this.__videoSize = i;
    }
}
