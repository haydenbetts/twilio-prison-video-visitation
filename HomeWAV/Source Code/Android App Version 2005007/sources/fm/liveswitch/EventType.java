package fm.liveswitch;

public abstract class EventType {
    public static final String ChannelActivated = "channel.activated";
    public static final String ChannelClientJoined = "channel.client.joined";
    public static final String ChannelClientLeft = "channel.client.left";
    public static final String ChannelDeactivated = "channel.deactivated";
    public static final String ClientRegistered = "client.registered";
    public static final String ClientStats = "client.stats";
    public static final String ClientUnregistered = "client.unregistered";
    public static final String ClientUpdated = "client.updated";
    public static final String ConnectionClosed = "connection.closed";
    public static final String ConnectionClosing = "connection.closing";
    public static final String ConnectionConnected = "connection.connected";
    public static final String ConnectionConnecting = "connection.connecting";
    public static final String ConnectionFailed = "connection.failed";
    public static final String ConnectionFailing = "connection.failing";
    public static final String ConnectionInitializing = "connection.initializing";
    public static final String ConnectionStats = "connection.stats";
    public static final String ConnectionUpdated = "connection.updated";
    public static final String GatewayStarted = "gateway.started";
    public static final String GatewayStats = "gateway.stats";
    public static final String GatewayStopped = "gateway.stopped";
    public static final String MediaServerRegistered = "mediaserver.registered";
    public static final String MediaServerStarted = "mediaserver.started";
    public static final String MediaServerStats = "mediaserver.stats";
    public static final String MediaServerStopped = "mediaserver.stopped";
    public static final String MediaServerUnregistered = "mediaserver.unregistered";
    public static final String SipConnectorRegistered = "sipconnector.registered";
    public static final String SipConnectorStarted = "sipconnector.started";
    public static final String SipConnectorStats = "sipconnector.stats";
    public static final String SipConnectorStopped = "sipconnector.stopped";
    public static final String SipConnectorUnregistered = "sipconnector.unregistered";

    public static String fromConnectionState(ConnectionState connectionState) {
        if (connectionState == ConnectionState.Initializing) {
            return ConnectionInitializing;
        }
        if (connectionState == ConnectionState.Connecting) {
            return ConnectionConnecting;
        }
        if (connectionState == ConnectionState.Connected) {
            return ConnectionConnected;
        }
        if (connectionState == ConnectionState.Failing) {
            return ConnectionFailing;
        }
        if (connectionState == ConnectionState.Failed) {
            return ConnectionFailed;
        }
        if (connectionState == ConnectionState.Closing) {
            return ConnectionClosing;
        }
        if (connectionState == ConnectionState.Closed) {
            return ConnectionClosed;
        }
        return null;
    }
}
