package fm.liveswitch;

class RtpExtensionParameters implements RtpIExtensionParameters {
    private StreamDirection __absoluteSenderTimeDirection = StreamDirection.Unset;
    private StreamDirection __sdesMidDirection = StreamDirection.Unset;
    private StreamDirection __sdesRepairedRtpStreamIdDirection = StreamDirection.Unset;
    private StreamDirection __sdesRtpStreamIdDirection = StreamDirection.Unset;
    private RtpHeaderExtensionRegistry _rtpHeaderExtensionRegistry;

    public StreamDirection getAbsoluteSenderTimeDirection() {
        return this.__absoluteSenderTimeDirection;
    }

    public RtpHeaderExtensionRegistry getRtpHeaderExtensionRegistry() {
        return this._rtpHeaderExtensionRegistry;
    }

    public StreamDirection getSdesMidDirection() {
        return this.__sdesMidDirection;
    }

    public StreamDirection getSdesRepairedRtpStreamIdDirection() {
        return this.__sdesRepairedRtpStreamIdDirection;
    }

    public StreamDirection getSdesRtpStreamIdDirection() {
        return this.__sdesRtpStreamIdDirection;
    }

    public RtpExtensionParameters() {
        setRtpHeaderExtensionRegistry(new RtpHeaderExtensionRegistry());
    }

    public void setAbsoluteSenderTimeDirection(StreamDirection streamDirection) {
        this.__absoluteSenderTimeDirection = streamDirection;
    }

    public void setRtpHeaderExtensionRegistry(RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry) {
        this._rtpHeaderExtensionRegistry = rtpHeaderExtensionRegistry;
    }

    public void setSdesMidDirection(StreamDirection streamDirection) {
        this.__sdesMidDirection = streamDirection;
    }

    public void setSdesRepairedRtpStreamIdDirection(StreamDirection streamDirection) {
        this.__sdesRepairedRtpStreamIdDirection = streamDirection;
    }

    public void setSdesRtpStreamIdDirection(StreamDirection streamDirection) {
        this.__sdesRtpStreamIdDirection = streamDirection;
    }
}
