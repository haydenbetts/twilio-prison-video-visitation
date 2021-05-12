package fm.liveswitch;

class RtpHeaderExtensionRegistryArgs {
    private MediaHeaderExtensionPolicy __absoluteSenderTimePolicy = MediaHeaderExtensionPolicy.Disabled;
    private MediaHeaderExtensionPolicy __sdesMidPolicy = MediaHeaderExtensionPolicy.Disabled;
    private MediaHeaderExtensionPolicy __sdesRepairedRtpStreamIdPolicy = MediaHeaderExtensionPolicy.Disabled;
    private MediaHeaderExtensionPolicy __sdesRtpStreamIdPolicy = MediaHeaderExtensionPolicy.Disabled;
    private StreamDirection __streamDirection = StreamDirection.Unset;
    private StreamDirection _absoluteSenderTimeDirection;
    private StreamDirection _sdesMidDirection;
    private StreamDirection _sdesRepairedRtpStreamIdDirection;
    private StreamDirection _sdesRtpStreamIdDirection;

    public StreamDirection getAbsoluteSenderTimeDirection() {
        return this._absoluteSenderTimeDirection;
    }

    public MediaHeaderExtensionPolicy getAbsoluteSenderTimePolicy() {
        return this.__absoluteSenderTimePolicy;
    }

    public StreamDirection getSdesMidDirection() {
        return this._sdesMidDirection;
    }

    public MediaHeaderExtensionPolicy getSdesMidPolicy() {
        return this.__sdesMidPolicy;
    }

    public StreamDirection getSdesRepairedRtpStreamIdDirection() {
        return this._sdesRepairedRtpStreamIdDirection;
    }

    public MediaHeaderExtensionPolicy getSdesRepairedRtpStreamIdPolicy() {
        return this.__sdesRepairedRtpStreamIdPolicy;
    }

    public StreamDirection getSdesRtpStreamIdDirection() {
        return this._sdesRtpStreamIdDirection;
    }

    public MediaHeaderExtensionPolicy getSdesRtpStreamIdPolicy() {
        return this.__sdesRtpStreamIdPolicy;
    }

    public StreamDirection getStreamDirection() {
        return this.__streamDirection;
    }

    public void setAbsoluteSenderTimeDirection(StreamDirection streamDirection) {
        this._absoluteSenderTimeDirection = streamDirection;
    }

    public void setAbsoluteSenderTimePolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this.__absoluteSenderTimePolicy = mediaHeaderExtensionPolicy;
    }

    public void setSdesMidDirection(StreamDirection streamDirection) {
        this._sdesMidDirection = streamDirection;
    }

    public void setSdesMidPolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this.__sdesMidPolicy = mediaHeaderExtensionPolicy;
    }

    public void setSdesRepairedRtpStreamIdDirection(StreamDirection streamDirection) {
        this._sdesRepairedRtpStreamIdDirection = streamDirection;
    }

    public void setSdesRepairedRtpStreamIdPolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this.__sdesRepairedRtpStreamIdPolicy = mediaHeaderExtensionPolicy;
    }

    public void setSdesRtpStreamIdDirection(StreamDirection streamDirection) {
        this._sdesRtpStreamIdDirection = streamDirection;
    }

    public void setSdesRtpStreamIdPolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this.__sdesRtpStreamIdPolicy = mediaHeaderExtensionPolicy;
    }

    public void setStreamDirection(StreamDirection streamDirection) {
        this.__streamDirection = streamDirection;
    }
}
