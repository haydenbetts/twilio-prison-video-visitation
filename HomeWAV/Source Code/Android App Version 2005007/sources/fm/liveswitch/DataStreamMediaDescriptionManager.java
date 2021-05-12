package fm.liveswitch;

import fm.liveswitch.sdp.DirectionAttribute;
import fm.liveswitch.sdp.Media;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.MediaType;
import fm.liveswitch.sdp.Message;
import fm.liveswitch.sdp.sctp.MapAttribute;
import fm.liveswitch.sdp.sctp.MaxMessageSizeAttribute;
import fm.liveswitch.sdp.sctp.PortAttribute;

class DataStreamMediaDescriptionManager extends MediaDescriptionManager {
    private boolean _legacySignallingFormat;
    private long _maxMessageSize;
    private int _sctpPort;

    public MediaDescription createSdpMediaDescription(MediaDescriptionRequirements mediaDescriptionRequirements, Message message, boolean z, boolean z2) {
        MediaDescription createSdpMediaDescription = super.createSdpMediaDescription(mediaDescriptionRequirements, message, z, z2);
        if (mediaDescriptionRequirements.getDtlsParameters() != null) {
            DataStreamMediaDescriptionRequirements dataStreamMediaDescriptionRequirements = (DataStreamMediaDescriptionRequirements) mediaDescriptionRequirements;
            int sctpPort = dataStreamMediaDescriptionRequirements.getSctpPort();
            Media media = createSdpMediaDescription.getMedia();
            media.setTransportProtocol(getLegacySignallingFormat() ? fm.liveswitch.sdp.sctp.Media.getDtlsSctpTransportProtocol() : fm.liveswitch.sdp.sctp.Media.getUdpDtlsSctpTransportProtocol());
            media.setFormatDescription(getLegacySignallingFormat() ? IntegerExtensions.toString(Integer.valueOf(sctpPort)) : fm.liveswitch.sdp.sctp.Media.getWebRtcDatachannelAssociationUsage());
            media.setMediaType(MediaType.getApplication());
            if (getLegacySignallingFormat()) {
                createSdpMediaDescription.addMediaAttribute(new MapAttribute(sctpPort, fm.liveswitch.sdp.sctp.Media.getWebRtcDatachannelAssociationUsage(), 256));
            } else {
                createSdpMediaDescription.addMediaAttribute(new PortAttribute(sctpPort));
            }
            createSdpMediaDescription.addMediaAttribute(DirectionAttribute.generateDirectionAttribute(StreamDirection.SendReceive));
            createSdpMediaDescription.addMediaAttribute(new MaxMessageSizeAttribute(dataStreamMediaDescriptionRequirements.getMaxMessageSize()));
            return createSdpMediaDescription;
        }
        throw new RuntimeException(new Exception("Dtls is mandatory for Data Streams, but local Dtls parameters were not set prior to signalling exchange."));
    }

    public boolean getLegacySignallingFormat() {
        return this._legacySignallingFormat;
    }

    public long getMaxMessageSize() {
        return this._maxMessageSize;
    }

    public int getSctpPort() {
        return this._sctpPort;
    }

    public Error processSdpMediaDescription(MediaDescriptionRequirementsBase mediaDescriptionRequirementsBase, Message message, int i, boolean z, boolean z2, boolean z3) {
        Error processSdpMediaDescription = super.processSdpMediaDescription(mediaDescriptionRequirementsBase, message, i, z, z2, z3);
        if (processSdpMediaDescription == null) {
            MediaDescription mediaDescription = message.getMediaDescriptions()[i];
            StreamDirection streamDirection = mediaDescription.getStreamDirection();
            if (Global.equals(streamDirection, StreamDirection.Unset)) {
                streamDirection = message.getSessionLevelDirection();
            }
            if (Global.equals(streamDirection, StreamDirection.Unset)) {
                streamDirection = StreamDirection.SendReceive;
            }
            if (!Global.equals(streamDirection, StreamDirection.SendReceive)) {
                return new Error(ErrorCode.StreamDirectionMismatch, new Exception(StringExtensions.format("Data streams only support SendReceive direction; however, remote peer supplied {0} direction.", (Object) streamDirection.toString())));
            }
            if (!super.getUseDtls()) {
                return new Error(ErrorCode.StreamEncryptionMismatch, new Exception("Data streams must use Dtls encryption; however, the remote peer does not support it."));
            }
            int sctpPort = getSctpPort();
            PortAttribute sctpPortAttribute = mediaDescription.getSctpPortAttribute();
            MapAttribute sctpMapAttribute = mediaDescription.getSctpMapAttribute();
            if (sctpPortAttribute != null) {
                sctpPort = sctpPortAttribute.getPort();
            } else if (sctpMapAttribute != null) {
                sctpPort = sctpMapAttribute.getPort();
                if (!z && z3) {
                    setLegacySignallingFormat(true);
                }
            }
            setSctpPort(sctpPort);
            MaxMessageSizeAttribute sctpMaxMessageSizeAttribute = mediaDescription.getSctpMaxMessageSizeAttribute();
            long maxMessageSize = sctpMaxMessageSizeAttribute != null ? sctpMaxMessageSizeAttribute.getMaxMessageSize() : -1;
            if (Global.equals(mediaDescription.getMedia().getTransportProtocol(), fm.liveswitch.sdp.sctp.Media.getUdpDtlsSctpTransportProtocol()) || Global.equals(mediaDescription.getMedia().getTransportProtocol(), fm.liveswitch.sdp.sctp.Media.getTcpDtlsSctpTransportProtocol())) {
                if (maxMessageSize == -1) {
                    maxMessageSize = 64000;
                } else if (maxMessageSize == 0) {
                    maxMessageSize = (long) SctpTransport.getUnset();
                }
            }
            setMaxMessageSize(maxMessageSize);
        }
        return processSdpMediaDescription;
    }

    private void setLegacySignallingFormat(boolean z) {
        this._legacySignallingFormat = z;
    }

    private void setMaxMessageSize(long j) {
        this._maxMessageSize = j;
    }

    public void setSctpPort(int i) {
        this._sctpPort = i;
    }
}
