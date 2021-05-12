package fm.liveswitch;

import fm.liveswitch.sdp.rtp.ExtMapAttribute;

class RtpHeaderExtensionRegistryElement {
    private StreamDirection _direction;
    private int _id;
    private RtpHeaderExtensionType _type;
    private String _uri;

    public StreamDirection getDirection() {
        return this._direction;
    }

    static RtpHeaderExtensionRegistryElement getElementFromAttribute(ExtMapAttribute extMapAttribute) {
        String uri = extMapAttribute.getUri();
        int id = extMapAttribute.getId();
        StreamDirection direction = extMapAttribute.getDirection();
        if (uri.equals("http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time")) {
            return new RtpHeaderExtensionRegistryElement(id, RtpHeaderExtensionType.AbsSendTime, direction, uri);
        }
        if (uri.equals("urn:ietf:params:rtp-hdrext:sdes:mid")) {
            return new RtpHeaderExtensionRegistryElement(id, RtpHeaderExtensionType.SdesMid, direction, uri);
        }
        if (uri.equals("urn:ietf:params:rtp-hdrext:sdes:rtp-stream-id")) {
            return new RtpHeaderExtensionRegistryElement(id, RtpHeaderExtensionType.SdesRtpStreamId, direction, uri);
        }
        if (uri.equals("urn:ietf:params:rtp-hdrext:sdes:repaired-rtp-stream-id")) {
            return new RtpHeaderExtensionRegistryElement(id, RtpHeaderExtensionType.SdesRepairedRtpStreamId, direction, uri);
        }
        return null;
    }

    public int getId() {
        return this._id;
    }

    public RtpHeaderExtensionType getType() {
        return this._type;
    }

    public String getUri() {
        return this._uri;
    }

    public RtpHeaderExtensionRegistryElement(int i, RtpHeaderExtensionType rtpHeaderExtensionType, StreamDirection streamDirection, String str) {
        setId(i);
        setType(rtpHeaderExtensionType);
        setDirection(streamDirection);
        setUri(str);
    }

    /* access modifiers changed from: package-private */
    public void setDirection(StreamDirection streamDirection) {
        this._direction = streamDirection;
    }

    private void setId(int i) {
        this._id = i;
    }

    private void setType(RtpHeaderExtensionType rtpHeaderExtensionType) {
        this._type = rtpHeaderExtensionType;
    }

    private void setUri(String str) {
        this._uri = str;
    }
}
