package fm.liveswitch;

interface RtpIExtensionParameters {
    StreamDirection getAbsoluteSenderTimeDirection();

    RtpHeaderExtensionRegistry getRtpHeaderExtensionRegistry();

    StreamDirection getSdesMidDirection();

    StreamDirection getSdesRepairedRtpStreamIdDirection();

    StreamDirection getSdesRtpStreamIdDirection();

    void setAbsoluteSenderTimeDirection(StreamDirection streamDirection);

    void setRtpHeaderExtensionRegistry(RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry);

    void setSdesMidDirection(StreamDirection streamDirection);

    void setSdesRepairedRtpStreamIdDirection(StreamDirection streamDirection);

    void setSdesRtpStreamIdDirection(StreamDirection streamDirection);
}
