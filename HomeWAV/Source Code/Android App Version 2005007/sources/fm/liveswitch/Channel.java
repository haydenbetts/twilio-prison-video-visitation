package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Channel extends Dynamic {
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(Channel.class);
    private DispatchQueue<Message> __channelQueue;
    private ConnectionFactory __connectionFactory;
    private VideoLayout __deferredLayout;
    private IFunction1<Message, Future<Message>> __doSend;
    private ManagedConcurrentDictionary<String, Invitation> __invitations;
    private boolean __left = false;
    private Object __lock;
    private AtomicLong __messageBytesReceived = new AtomicLong();
    private AtomicLong __messageBytesSent = new AtomicLong();
    private AtomicLong __messagesReceived = new AtomicLong();
    private AtomicLong __messagesSent = new AtomicLong();
    /* access modifiers changed from: private */
    public List<IAction2<ChannelClaim, ChannelClaim>> __onClaimUpdate = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<ClientInfo, String>> __onClientMessage = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<ClientInfo, String>> __onDeviceMessage = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onKick = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<VideoLayout>> __onMcuVideoLayout = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<ClientInfo, String>> __onMessage = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<PeerConnectionOffer>> __onPeerConnectionOffer = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<ClientInfo>> __onRemoteClientJoin = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<ClientInfo>> __onRemoteClientLeave = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<ClientInfo, ClientInfo>> __onRemoteClientUpdate = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<ConnectionInfo>> __onRemoteUpstreamConnectionClose = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<ConnectionInfo>> __onRemoteUpstreamConnectionOpen = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<ConnectionInfo, ConnectionInfo>> __onRemoteUpstreamConnectionUpdate = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<ClientInfo, String>> __onUserMessage = new ArrayList();
    private ManagedConcurrentDictionary<String, PeerConnectionOffer> __peerConnectionOffers;
    private ArrayList<ClientInfo> __remoteClientInfos;
    private ArrayList<ConnectionInfo> __remoteUpstreamConnectionInfos;
    private String _applicationId;
    private ChannelClaim _claim;
    private String _clientId;
    private String[] _clientRoles;
    private String _clientTag;
    private ManagedConnectionCollection _connections;
    private String _deviceAlias;
    private String _deviceId;
    private String _id;
    private IAction2<ChannelClaim, ChannelClaim> _onClaimUpdate = null;
    private IAction2<ClientInfo, String> _onClientMessage = null;
    private IAction2<ClientInfo, String> _onDeviceMessage = null;
    private IAction0 _onKick = null;
    private IAction1<VideoLayout> _onMcuVideoLayout = null;
    private IAction2<ClientInfo, String> _onMessage = null;
    private IAction1<PeerConnectionOffer> _onPeerConnectionOffer = null;
    private IAction1<ClientInfo> _onRemoteClientJoin = null;
    private IAction1<ClientInfo> _onRemoteClientLeave = null;
    private IAction2<ClientInfo, ClientInfo> _onRemoteClientUpdate = null;
    private IAction1<ConnectionInfo> _onRemoteUpstreamConnectionClose = null;
    private IAction1<ConnectionInfo> _onRemoteUpstreamConnectionOpen = null;
    private IAction2<ConnectionInfo, ConnectionInfo> _onRemoteUpstreamConnectionUpdate = null;
    private IAction2<ClientInfo, String> _onUserMessage = null;
    private String _userAlias;
    private String _userId;

    public void addOnClaimUpdate(IAction2<ChannelClaim, ChannelClaim> iAction2) {
        if (iAction2 != null) {
            if (this._onClaimUpdate == null) {
                this._onClaimUpdate = new IAction2<ChannelClaim, ChannelClaim>() {
                    public void invoke(ChannelClaim channelClaim, ChannelClaim channelClaim2) {
                        Iterator it = new ArrayList(Channel.this.__onClaimUpdate).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(channelClaim, channelClaim2);
                        }
                    }
                };
            }
            this.__onClaimUpdate.add(iAction2);
        }
    }

    public void addOnClientMessage(IAction2<ClientInfo, String> iAction2) {
        if (iAction2 != null) {
            if (this._onClientMessage == null) {
                this._onClientMessage = new IAction2<ClientInfo, String>() {
                    public void invoke(ClientInfo clientInfo, String str) {
                        Iterator it = new ArrayList(Channel.this.__onClientMessage).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(clientInfo, str);
                        }
                    }
                };
            }
            this.__onClientMessage.add(iAction2);
        }
    }

    public void addOnDeviceMessage(IAction2<ClientInfo, String> iAction2) {
        if (iAction2 != null) {
            if (this._onDeviceMessage == null) {
                this._onDeviceMessage = new IAction2<ClientInfo, String>() {
                    public void invoke(ClientInfo clientInfo, String str) {
                        Iterator it = new ArrayList(Channel.this.__onDeviceMessage).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(clientInfo, str);
                        }
                    }
                };
            }
            this.__onDeviceMessage.add(iAction2);
        }
    }

    public void addOnKick(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onKick == null) {
                this._onKick = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(Channel.this.__onKick).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onKick.add(iAction0);
        }
    }

    public void addOnMcuVideoLayout(IAction1<VideoLayout> iAction1) {
        if (iAction1 != null) {
            if (this._onMcuVideoLayout == null) {
                this._onMcuVideoLayout = new IAction1<VideoLayout>() {
                    public void invoke(VideoLayout videoLayout) {
                        Iterator it = new ArrayList(Channel.this.__onMcuVideoLayout).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(videoLayout);
                        }
                    }
                };
            }
            this.__onMcuVideoLayout.add(iAction1);
        }
    }

    public void addOnMessage(IAction2<ClientInfo, String> iAction2) {
        if (iAction2 != null) {
            if (this._onMessage == null) {
                this._onMessage = new IAction2<ClientInfo, String>() {
                    public void invoke(ClientInfo clientInfo, String str) {
                        Iterator it = new ArrayList(Channel.this.__onMessage).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(clientInfo, str);
                        }
                    }
                };
            }
            this.__onMessage.add(iAction2);
        }
    }

    public void addOnPeerConnectionOffer(IAction1<PeerConnectionOffer> iAction1) {
        if (iAction1 != null) {
            if (this._onPeerConnectionOffer == null) {
                this._onPeerConnectionOffer = new IAction1<PeerConnectionOffer>() {
                    public void invoke(PeerConnectionOffer peerConnectionOffer) {
                        Iterator it = new ArrayList(Channel.this.__onPeerConnectionOffer).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(peerConnectionOffer);
                        }
                    }
                };
            }
            this.__onPeerConnectionOffer.add(iAction1);
        }
    }

    public void addOnRemoteClientJoin(IAction1<ClientInfo> iAction1) {
        if (iAction1 != null) {
            if (this._onRemoteClientJoin == null) {
                this._onRemoteClientJoin = new IAction1<ClientInfo>() {
                    public void invoke(ClientInfo clientInfo) {
                        Iterator it = new ArrayList(Channel.this.__onRemoteClientJoin).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(clientInfo);
                        }
                    }
                };
            }
            this.__onRemoteClientJoin.add(iAction1);
        }
    }

    public void addOnRemoteClientLeave(IAction1<ClientInfo> iAction1) {
        if (iAction1 != null) {
            if (this._onRemoteClientLeave == null) {
                this._onRemoteClientLeave = new IAction1<ClientInfo>() {
                    public void invoke(ClientInfo clientInfo) {
                        Iterator it = new ArrayList(Channel.this.__onRemoteClientLeave).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(clientInfo);
                        }
                    }
                };
            }
            this.__onRemoteClientLeave.add(iAction1);
        }
    }

    public void addOnRemoteClientUpdate(IAction2<ClientInfo, ClientInfo> iAction2) {
        if (iAction2 != null) {
            if (this._onRemoteClientUpdate == null) {
                this._onRemoteClientUpdate = new IAction2<ClientInfo, ClientInfo>() {
                    public void invoke(ClientInfo clientInfo, ClientInfo clientInfo2) {
                        Iterator it = new ArrayList(Channel.this.__onRemoteClientUpdate).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(clientInfo, clientInfo2);
                        }
                    }
                };
            }
            this.__onRemoteClientUpdate.add(iAction2);
        }
    }

    public void addOnRemoteUpstreamConnectionClose(IAction1<ConnectionInfo> iAction1) {
        if (iAction1 != null) {
            if (this._onRemoteUpstreamConnectionClose == null) {
                this._onRemoteUpstreamConnectionClose = new IAction1<ConnectionInfo>() {
                    public void invoke(ConnectionInfo connectionInfo) {
                        Iterator it = new ArrayList(Channel.this.__onRemoteUpstreamConnectionClose).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(connectionInfo);
                        }
                    }
                };
            }
            this.__onRemoteUpstreamConnectionClose.add(iAction1);
        }
    }

    public void addOnRemoteUpstreamConnectionOpen(IAction1<ConnectionInfo> iAction1) {
        if (iAction1 != null) {
            if (this._onRemoteUpstreamConnectionOpen == null) {
                this._onRemoteUpstreamConnectionOpen = new IAction1<ConnectionInfo>() {
                    public void invoke(ConnectionInfo connectionInfo) {
                        Iterator it = new ArrayList(Channel.this.__onRemoteUpstreamConnectionOpen).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(connectionInfo);
                        }
                    }
                };
            }
            this.__onRemoteUpstreamConnectionOpen.add(iAction1);
        }
    }

    public void addOnRemoteUpstreamConnectionUpdate(IAction2<ConnectionInfo, ConnectionInfo> iAction2) {
        if (iAction2 != null) {
            if (this._onRemoteUpstreamConnectionUpdate == null) {
                this._onRemoteUpstreamConnectionUpdate = new IAction2<ConnectionInfo, ConnectionInfo>() {
                    public void invoke(ConnectionInfo connectionInfo, ConnectionInfo connectionInfo2) {
                        Iterator it = new ArrayList(Channel.this.__onRemoteUpstreamConnectionUpdate).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(connectionInfo, connectionInfo2);
                        }
                    }
                };
            }
            this.__onRemoteUpstreamConnectionUpdate.add(iAction2);
        }
    }

    public void addOnUserMessage(IAction2<ClientInfo, String> iAction2) {
        if (iAction2 != null) {
            if (this._onUserMessage == null) {
                this._onUserMessage = new IAction2<ClientInfo, String>() {
                    public void invoke(ClientInfo clientInfo, String str) {
                        Iterator it = new ArrayList(Channel.this.__onUserMessage).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(clientInfo, str);
                        }
                    }
                };
            }
            this.__onUserMessage.add(iAction2);
        }
    }

    Channel(Object obj, ConnectionFactory connectionFactory, String str, String str2, String str3, String str4, String str5, String str6, String str7, String[] strArr, String str8, IFunction1<Message, Future<Message>> iFunction1) {
        this.__lock = obj;
        this.__channelQueue = new DispatchQueue<>(new IActionDelegate1<Message>() {
            public String getId() {
                return "fm.liveswitch.Channel.processMessageSync";
            }

            public void invoke(Message message) {
                Channel.this.processMessageSync(message);
            }
        });
        setApplicationId(str);
        setUserId(str2);
        setUserAlias(str3);
        setDeviceId(str4);
        setDeviceAlias(str5);
        setClientId(str6);
        setClientTag(str7);
        setClientRoles(strArr);
        setId(str8);
        this.__doSend = iFunction1;
        this.__connectionFactory = connectionFactory;
        this.__remoteClientInfos = new ArrayList<>();
        this.__remoteUpstreamConnectionInfos = new ArrayList<>();
        this.__peerConnectionOffers = new ManagedConcurrentDictionary<>();
        this.__invitations = new ManagedConcurrentDictionary<>();
        setConnections(new ManagedConnectionCollection());
    }

    public Future<Object> closeAll() {
        __log.debug(StringExtensions.format("Closing all channel {0} connections.", (Object) getId()));
        ArrayList arrayList = new ArrayList();
        for (ManagedConnection close : (ManagedConnection[]) getConnections().getValues()) {
            arrayList.add(close.close());
        }
        return PromiseBase.all((Future[]) arrayList.toArray(new Future[0]));
    }

    public McuConnection createMcuConnection(AudioStream audioStream) {
        return createMcuConnection(audioStream, (VideoStream) null, (DataStream) null, (String) null);
    }

    public McuConnection createMcuConnection(AudioStream audioStream, DataStream dataStream) {
        return createMcuConnection(audioStream, (VideoStream) null, dataStream, (String) null);
    }

    public McuConnection createMcuConnection(AudioStream audioStream, DataStream dataStream, String str) {
        return createMcuConnection(audioStream, (VideoStream) null, dataStream, str);
    }

    public McuConnection createMcuConnection(AudioStream audioStream, String str) {
        return createMcuConnection(audioStream, (VideoStream) null, (DataStream) null, str);
    }

    public McuConnection createMcuConnection(AudioStream audioStream, VideoStream videoStream) {
        return createMcuConnection(audioStream, videoStream, (DataStream) null, (String) null);
    }

    public McuConnection createMcuConnection(AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        return createMcuConnection(audioStream, videoStream, dataStream, (String) null);
    }

    public McuConnection createMcuConnection(AudioStream audioStream, VideoStream videoStream, DataStream dataStream, String str) {
        McuConnection createMcuConnection;
        DataStream dataStream2 = dataStream;
        synchronized (this.__lock) {
            if (!this.__left) {
                if (audioStream == null && videoStream == null) {
                    if (dataStream2 == null) {
                        throw new RuntimeException(new Exception("Cannot open a connection with no streams."));
                    }
                }
                if (dataStream2 != null) {
                    dataStream2.setGetRemoteConnectionInfo(new IFunctionDelegate1<String, Object>() {
                        public String getId() {
                            return "fm.liveswitch.Channel.getRemoteConnectionInfo";
                        }

                        public ConnectionInfo invoke(String str) {
                            return Channel.this.getRemoteConnectionInfo(str);
                        }
                    });
                }
                createMcuConnection = this.__connectionFactory.createMcuConnection(this.__lock, getApplicationId(), getId(), getUserId(), getDeviceId(), getClientId(), new IFunctionDelegate1<Message, Future<Message>>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.send";
                    }

                    public Future<Message> invoke(Message message) {
                        return Channel.this.send(message);
                    }
                }, audioStream, videoStream, dataStream, str);
                getConnections().add(createMcuConnection);
                createMcuConnection.addOnStateChange(new IActionDelegate1<ManagedConnection>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.processConnectionStateChange";
                    }

                    public void invoke(ManagedConnection managedConnection) {
                        Channel.this.processConnectionStateChange(managedConnection);
                    }
                });
            } else {
                throw new RuntimeException(new Exception("You have left this channel."));
            }
        }
        return createMcuConnection;
    }

    public McuConnection createMcuConnection(AudioStream audioStream, VideoStream videoStream, String str) {
        return createMcuConnection(audioStream, videoStream, (DataStream) null, str);
    }

    public McuConnection createMcuConnection(DataStream dataStream) {
        return createMcuConnection((AudioStream) null, (VideoStream) null, dataStream, (String) null);
    }

    public McuConnection createMcuConnection(DataStream dataStream, String str) {
        return createMcuConnection((AudioStream) null, (VideoStream) null, dataStream, str);
    }

    public McuConnection createMcuConnection(VideoStream videoStream) {
        return createMcuConnection((AudioStream) null, videoStream, (DataStream) null, (String) null);
    }

    public McuConnection createMcuConnection(VideoStream videoStream, DataStream dataStream) {
        return createMcuConnection((AudioStream) null, videoStream, dataStream, (String) null);
    }

    public McuConnection createMcuConnection(VideoStream videoStream, DataStream dataStream, String str) {
        return createMcuConnection((AudioStream) null, videoStream, dataStream, str);
    }

    public McuConnection createMcuConnection(VideoStream videoStream, String str) {
        return createMcuConnection((AudioStream) null, videoStream, (DataStream) null, str);
    }

    public PeerConnection createPeerConnection(PeerConnectionOffer peerConnectionOffer, AudioStream audioStream) {
        return createPeerConnection(peerConnectionOffer, audioStream, (VideoStream) null, (DataStream) null);
    }

    public PeerConnection createPeerConnection(PeerConnectionOffer peerConnectionOffer, AudioStream audioStream, DataStream dataStream) {
        return createPeerConnection(peerConnectionOffer, audioStream, (VideoStream) null, dataStream);
    }

    public PeerConnection createPeerConnection(PeerConnectionOffer peerConnectionOffer, AudioStream audioStream, VideoStream videoStream) {
        return createPeerConnection(peerConnectionOffer, audioStream, videoStream, (DataStream) null);
    }

    public PeerConnection createPeerConnection(PeerConnectionOffer peerConnectionOffer, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        synchronized (this.__lock) {
            if (this.__left) {
                throw new RuntimeException(new Exception("You have left this channel."));
            } else if (peerConnectionOffer != null) {
                if (audioStream == null && videoStream == null) {
                    if (dataStream == null) {
                        throw new RuntimeException(new Exception("Cannot open a connection with no streams."));
                    }
                }
                if (audioStream != null) {
                    if (!peerConnectionOffer.getHasAudio()) {
                        throw new RuntimeException(new Exception("Cannot open a connection with an audio stream if the offer does not contain an audio description."));
                    }
                }
                if (videoStream != null) {
                    if (!peerConnectionOffer.getHasVideo()) {
                        throw new RuntimeException(new Exception("Cannot open a connection with a video stream if the offer does not contain a video description."));
                    }
                }
                if (dataStream != null) {
                    if (!peerConnectionOffer.getHasData()) {
                        throw new RuntimeException(new Exception("Cannot open a connection with a data stream if the offer does not contain a data description."));
                    }
                }
                if (audioStream == null) {
                    if (peerConnectionOffer.getHasAudio()) {
                        throw new RuntimeException(new Exception("An audio stream is required by the offer."));
                    }
                }
                if (videoStream == null) {
                    if (peerConnectionOffer.getHasVideo()) {
                        throw new RuntimeException(new Exception("A video stream is required by the offer."));
                    }
                }
                if (dataStream == null) {
                    if (peerConnectionOffer.getHasData()) {
                        throw new RuntimeException(new Exception("A data stream is required by the offer."));
                    }
                }
                if (!this.__peerConnectionOffers.tryRemove(peerConnectionOffer.getRemoteConnectionId())) {
                    return null;
                }
                PeerConnection createPeerConnection = this.__connectionFactory.createPeerConnection(this.__lock, getApplicationId(), getId(), getUserId(), getDeviceId(), getClientId(), (IFunction1<Message, Future<Message>>) new IFunctionDelegate1<Message, Future<Message>>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.send";
                    }

                    public Future<Message> invoke(Message message) {
                        return Channel.this.send(message);
                    }
                }, peerConnectionOffer, audioStream, videoStream, dataStream);
                getConnections().add(createPeerConnection);
                createPeerConnection.addOnStateChange(new IActionDelegate1<ManagedConnection>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.processConnectionStateChange";
                    }

                    public void invoke(ManagedConnection managedConnection) {
                        Channel.this.processConnectionStateChange(managedConnection);
                    }
                });
                for (Message processMessage : peerConnectionOffer.getEarlyCandidateMessages()) {
                    createPeerConnection.processMessage(processMessage);
                }
                return createPeerConnection;
            } else {
                throw new RuntimeException(new Exception("Peer connection offer cannot be null."));
            }
        }
    }

    public PeerConnection createPeerConnection(PeerConnectionOffer peerConnectionOffer, DataStream dataStream) {
        return createPeerConnection(peerConnectionOffer, (AudioStream) null, (VideoStream) null, dataStream);
    }

    public PeerConnection createPeerConnection(PeerConnectionOffer peerConnectionOffer, VideoStream videoStream) {
        return createPeerConnection(peerConnectionOffer, (AudioStream) null, videoStream, (DataStream) null);
    }

    public PeerConnection createPeerConnection(PeerConnectionOffer peerConnectionOffer, VideoStream videoStream, DataStream dataStream) {
        return createPeerConnection(peerConnectionOffer, (AudioStream) null, videoStream, dataStream);
    }

    public PeerConnection createPeerConnection(ClientInfo clientInfo, AudioStream audioStream) {
        return createPeerConnection(clientInfo, audioStream, (VideoStream) null, (DataStream) null);
    }

    public PeerConnection createPeerConnection(ClientInfo clientInfo, AudioStream audioStream, DataStream dataStream) {
        return createPeerConnection(clientInfo, audioStream, (VideoStream) null, dataStream);
    }

    public PeerConnection createPeerConnection(ClientInfo clientInfo, AudioStream audioStream, VideoStream videoStream) {
        return createPeerConnection(clientInfo, audioStream, videoStream, (DataStream) null);
    }

    public PeerConnection createPeerConnection(ClientInfo clientInfo, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        PeerConnection createPeerConnection;
        synchronized (this.__lock) {
            if (this.__left) {
                throw new RuntimeException(new Exception("You have left this channel."));
            } else if (clientInfo != null) {
                if (audioStream == null && videoStream == null) {
                    if (dataStream == null) {
                        throw new RuntimeException(new Exception("Cannot open a connection with no streams."));
                    }
                }
                createPeerConnection = this.__connectionFactory.createPeerConnection(this.__lock, getApplicationId(), getId(), getUserId(), getDeviceId(), getClientId(), (IFunction1<Message, Future<Message>>) new IFunctionDelegate1<Message, Future<Message>>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.send";
                    }

                    public Future<Message> invoke(Message message) {
                        return Channel.this.send(message);
                    }
                }, clientInfo, audioStream, videoStream, dataStream);
                getConnections().add(createPeerConnection);
                createPeerConnection.addOnStateChange(new IActionDelegate1<ManagedConnection>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.processConnectionStateChange";
                    }

                    public void invoke(ManagedConnection managedConnection) {
                        Channel.this.processConnectionStateChange(managedConnection);
                    }
                });
            } else {
                throw new RuntimeException(new Exception("Remote client information cannot be null."));
            }
        }
        return createPeerConnection;
    }

    public PeerConnection createPeerConnection(ClientInfo clientInfo, DataStream dataStream) {
        return createPeerConnection(clientInfo, (AudioStream) null, (VideoStream) null, dataStream);
    }

    public PeerConnection createPeerConnection(ClientInfo clientInfo, VideoStream videoStream) {
        return createPeerConnection(clientInfo, (AudioStream) null, videoStream, (DataStream) null);
    }

    public PeerConnection createPeerConnection(ClientInfo clientInfo, VideoStream videoStream, DataStream dataStream) {
        return createPeerConnection(clientInfo, (AudioStream) null, videoStream, dataStream);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(ConnectionInfo connectionInfo, AudioStream audioStream) {
        return createSfuDownstreamConnection(connectionInfo, audioStream, (VideoStream) null, (DataStream) null);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(ConnectionInfo connectionInfo, AudioStream audioStream, DataStream dataStream) {
        return createSfuDownstreamConnection(connectionInfo, audioStream, (VideoStream) null, dataStream);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(ConnectionInfo connectionInfo, AudioStream audioStream, VideoStream videoStream) {
        return createSfuDownstreamConnection(connectionInfo, audioStream, videoStream, (DataStream) null);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(ConnectionInfo connectionInfo, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        SfuDownstreamConnection createSfuDownstreamConnection;
        DataStream dataStream2 = dataStream;
        synchronized (this.__lock) {
            if (this.__left) {
                throw new RuntimeException(new Exception("You have left this channel."));
            } else if (connectionInfo != null) {
                if (audioStream == null && videoStream == null) {
                    if (dataStream2 == null) {
                        throw new RuntimeException(new Exception("Cannot open a connection with no streams."));
                    }
                }
                if (dataStream2 != null) {
                    dataStream2.setGetRemoteConnectionInfo(new IFunctionDelegate1<String, Object>() {
                        public String getId() {
                            return "fm.liveswitch.Channel.getRemoteConnectionInfo";
                        }

                        public ConnectionInfo invoke(String str) {
                            return Channel.this.getRemoteConnectionInfo(str);
                        }
                    });
                }
                createSfuDownstreamConnection = this.__connectionFactory.createSfuDownstreamConnection(this.__lock, getApplicationId(), getId(), getUserId(), getDeviceId(), getClientId(), (IFunction1<Message, Future<Message>>) new IFunctionDelegate1<Message, Future<Message>>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.send";
                    }

                    public Future<Message> invoke(Message message) {
                        return Channel.this.send(message);
                    }
                }, connectionInfo, audioStream, videoStream, dataStream);
                getConnections().add(createSfuDownstreamConnection);
                createSfuDownstreamConnection.addOnStateChange(new IActionDelegate1<ManagedConnection>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.processConnectionStateChange";
                    }

                    public void invoke(ManagedConnection managedConnection) {
                        Channel.this.processConnectionStateChange(managedConnection);
                    }
                });
            } else {
                throw new RuntimeException(new Exception("Remote connection information cannot be null."));
            }
        }
        return createSfuDownstreamConnection;
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(ConnectionInfo connectionInfo, DataStream dataStream) {
        return createSfuDownstreamConnection(connectionInfo, (AudioStream) null, (VideoStream) null, dataStream);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(ConnectionInfo connectionInfo, VideoStream videoStream) {
        return createSfuDownstreamConnection(connectionInfo, (AudioStream) null, videoStream, (DataStream) null);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(ConnectionInfo connectionInfo, VideoStream videoStream, DataStream dataStream) {
        return createSfuDownstreamConnection(connectionInfo, (AudioStream) null, videoStream, dataStream);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(String str, AudioStream audioStream) {
        return createSfuDownstreamConnection(str, audioStream, (VideoStream) null, (DataStream) null);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(String str, AudioStream audioStream, DataStream dataStream) {
        return createSfuDownstreamConnection(str, audioStream, (VideoStream) null, dataStream);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(String str, AudioStream audioStream, VideoStream videoStream) {
        return createSfuDownstreamConnection(str, audioStream, videoStream, (DataStream) null);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(String str, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        SfuDownstreamConnection createSfuDownstreamConnection;
        DataStream dataStream2 = dataStream;
        synchronized (this.__lock) {
            if (this.__left) {
                throw new RuntimeException(new Exception("You have left this channel."));
            } else if (str != null) {
                if (audioStream == null && videoStream == null) {
                    if (dataStream2 == null) {
                        throw new RuntimeException(new Exception("Cannot open a connection with no streams."));
                    }
                }
                if (dataStream2 != null) {
                    dataStream2.setGetRemoteConnectionInfo(new IFunctionDelegate1<String, Object>() {
                        public String getId() {
                            return "fm.liveswitch.Channel.getRemoteConnectionInfo";
                        }

                        public ConnectionInfo invoke(String str) {
                            return Channel.this.getRemoteConnectionInfo(str);
                        }
                    });
                }
                createSfuDownstreamConnection = this.__connectionFactory.createSfuDownstreamConnection(this.__lock, getApplicationId(), getId(), getUserId(), getDeviceId(), getClientId(), (IFunction1<Message, Future<Message>>) new IFunctionDelegate1<Message, Future<Message>>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.send";
                    }

                    public Future<Message> invoke(Message message) {
                        return Channel.this.send(message);
                    }
                }, str, audioStream, videoStream, dataStream);
                getConnections().add(createSfuDownstreamConnection);
                createSfuDownstreamConnection.addOnStateChange(new IActionDelegate1<ManagedConnection>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.processConnectionStateChange";
                    }

                    public void invoke(ManagedConnection managedConnection) {
                        Channel.this.processConnectionStateChange(managedConnection);
                    }
                });
            } else {
                throw new RuntimeException(new Exception("Remote media identifier cannot be null."));
            }
        }
        return createSfuDownstreamConnection;
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(String str, DataStream dataStream) {
        return createSfuDownstreamConnection(str, (AudioStream) null, (VideoStream) null, dataStream);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(String str, VideoStream videoStream) {
        return createSfuDownstreamConnection(str, (AudioStream) null, videoStream, (DataStream) null);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(String str, VideoStream videoStream, DataStream dataStream) {
        return createSfuDownstreamConnection(str, (AudioStream) null, videoStream, dataStream);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(AudioStream audioStream) {
        return createSfuUpstreamConnection(audioStream, (VideoStream) null, (DataStream) null, (String) null);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(AudioStream audioStream, DataStream dataStream) {
        return createSfuUpstreamConnection(audioStream, (VideoStream) null, dataStream);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(AudioStream audioStream, DataStream dataStream, String str) {
        return createSfuUpstreamConnection(audioStream, (VideoStream) null, dataStream, str);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(AudioStream audioStream, String str) {
        return createSfuUpstreamConnection(audioStream, (VideoStream) null, (DataStream) null, str);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(AudioStream audioStream, VideoStream videoStream) {
        return createSfuUpstreamConnection(audioStream, videoStream, (DataStream) null, (String) null);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        return createSfuUpstreamConnection(audioStream, videoStream, dataStream, (String) null);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(AudioStream audioStream, VideoStream videoStream, DataStream dataStream, String str) {
        SfuUpstreamConnection createSfuUpstreamConnection;
        DataStream dataStream2 = dataStream;
        synchronized (this.__lock) {
            if (!this.__left) {
                if (audioStream == null && videoStream == null) {
                    if (dataStream2 == null) {
                        throw new RuntimeException(new Exception("Cannot open a connection with no streams."));
                    }
                }
                if (dataStream2 != null) {
                    dataStream2.setGetRemoteConnectionInfo(new IFunctionDelegate1<String, Object>() {
                        public String getId() {
                            return "fm.liveswitch.Channel.getRemoteConnectionInfo";
                        }

                        public ConnectionInfo invoke(String str) {
                            return Channel.this.getRemoteConnectionInfo(str);
                        }
                    });
                }
                createSfuUpstreamConnection = this.__connectionFactory.createSfuUpstreamConnection(this.__lock, getApplicationId(), getId(), getUserId(), getDeviceId(), getClientId(), new IFunctionDelegate1<Message, Future<Message>>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.send";
                    }

                    public Future<Message> invoke(Message message) {
                        return Channel.this.send(message);
                    }
                }, audioStream, videoStream, dataStream, str);
                getConnections().add(createSfuUpstreamConnection);
                createSfuUpstreamConnection.addOnStateChange(new IActionDelegate1<ManagedConnection>() {
                    public String getId() {
                        return "fm.liveswitch.Channel.processConnectionStateChange";
                    }

                    public void invoke(ManagedConnection managedConnection) {
                        Channel.this.processConnectionStateChange(managedConnection);
                    }
                });
            } else {
                throw new RuntimeException(new Exception("You have left this channel."));
            }
        }
        return createSfuUpstreamConnection;
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(AudioStream audioStream, VideoStream videoStream, String str) {
        return createSfuUpstreamConnection(audioStream, videoStream, (DataStream) null, str);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(DataStream dataStream) {
        return createSfuUpstreamConnection((AudioStream) null, (VideoStream) null, dataStream);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(DataStream dataStream, String str) {
        return createSfuUpstreamConnection((AudioStream) null, (VideoStream) null, dataStream, str);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(VideoStream videoStream) {
        return createSfuUpstreamConnection((AudioStream) null, videoStream, (DataStream) null, (String) null);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(VideoStream videoStream, DataStream dataStream) {
        return createSfuUpstreamConnection((AudioStream) null, videoStream, dataStream);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(VideoStream videoStream, DataStream dataStream, String str) {
        return createSfuUpstreamConnection((AudioStream) null, videoStream, dataStream, str);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(VideoStream videoStream, String str) {
        return createSfuUpstreamConnection((AudioStream) null, videoStream, (DataStream) null, str);
    }

    private void doInvite(final Promise<Invitation> promise, final Invitation invitation, Message message) {
        send(message).then(new IAction1<Message>() {
            public void invoke(Message message) {
                boolean unused = Channel.this.processInviteFeedback(message);
                promise.resolve(invitation);
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                promise.reject(exc);
            }
        });
    }

    private void doKickClient(final Promise<Object> promise, ClientInfo clientInfo) {
        send(Message.createKickMessage(getId(), clientInfo.getId())).then(new IAction1<Message>() {
            public void invoke(Message message) {
                if (message == null || !Global.equals(message.getType(), MessageType.getError())) {
                    promise.resolve(null);
                } else {
                    promise.reject(new Exception(message.getPayload()));
                }
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                promise.reject(exc);
            }
        });
    }

    private void doSimpleSend(final Promise<Object> promise, Message message) {
        try {
            synchronized (this.__lock) {
                if (!this.__left) {
                    send(message).then(new IAction1<Message>() {
                        public void invoke(Message message) {
                            promise.resolve(null);
                        }
                    }, (IAction1<Exception>) new IAction1<Exception>() {
                        public void invoke(Exception exc) {
                            promise.reject(exc);
                        }
                    });
                } else {
                    throw new RuntimeException(new Exception("You have left this channel."));
                }
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    private void doUpdate(final Promise<Object> promise, ChannelConfig channelConfig) {
        send(Message.createUpdateMessage(channelConfig.toJson())).then(new IAction1<Message>() {
            public void invoke(Message message) {
                if (message == null || !Global.equals(message.getType(), MessageType.getError())) {
                    promise.resolve(null);
                } else {
                    promise.reject(new Exception(message.getPayload()));
                }
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                promise.reject(exc);
            }
        });
    }

    public String getApplicationId() {
        return this._applicationId;
    }

    public ChannelClaim getClaim() {
        return this._claim;
    }

    public String getClientId() {
        return this._clientId;
    }

    public String[] getClientRoles() {
        return this._clientRoles;
    }

    public String getClientTag() {
        return this._clientTag;
    }

    public ManagedConnectionCollection getConnections() {
        return this._connections;
    }

    public String getDeviceAlias() {
        return this._deviceAlias;
    }

    public String getDeviceId() {
        return this._deviceId;
    }

    private ClientInfo getEquivalentRemoteClientInfo(ClientInfo clientInfo) {
        for (ClientInfo clientInfo2 : getRemoteClientInfos()) {
            if (clientInfo2.isEquivalent(clientInfo)) {
                return clientInfo2;
            }
        }
        return null;
    }

    private ConnectionInfo getEquivalentRemoteUpstreamConnectionInfo(ConnectionInfo connectionInfo) {
        for (ConnectionInfo connectionInfo2 : getRemoteUpstreamConnectionInfos()) {
            if (connectionInfo2.isEquivalent(connectionInfo)) {
                return connectionInfo2;
            }
        }
        return null;
    }

    public String getId() {
        return this._id;
    }

    public long getMessageBytesReceived() {
        return this.__messageBytesReceived.getValue();
    }

    public long getMessageBytesSent() {
        return this.__messageBytesSent.getValue();
    }

    public long getMessagesReceived() {
        return this.__messagesReceived.getValue();
    }

    public long getMessagesSent() {
        return this.__messagesSent.getValue();
    }

    public ClientInfo getRemoteClientInfo(String str) {
        for (ClientInfo clientInfo : getRemoteClientInfos()) {
            if (Global.equals(clientInfo.getId(), str)) {
                return clientInfo;
            }
        }
        return null;
    }

    public ClientInfo[] getRemoteClientInfos() {
        ClientInfo[] clientInfoArr;
        synchronized (this.__lock) {
            clientInfoArr = (ClientInfo[]) this.__remoteClientInfos.toArray(new ClientInfo[0]);
        }
        return clientInfoArr;
    }

    public ConnectionInfo getRemoteConnectionInfo(String str) {
        for (ConnectionInfo connectionInfo : getRemoteUpstreamConnectionInfos()) {
            if (Global.equals(connectionInfo.getId(), str)) {
                return connectionInfo;
            }
        }
        return null;
    }

    public ConnectionInfo[] getRemoteUpstreamConnectionInfos() {
        ConnectionInfo[] connectionInfoArr;
        synchronized (this.__lock) {
            connectionInfoArr = (ConnectionInfo[]) this.__remoteUpstreamConnectionInfos.toArray(new ConnectionInfo[0]);
        }
        return connectionInfoArr;
    }

    /* access modifiers changed from: package-private */
    public ChannelReport getReport() {
        ChannelReport channelReport = new ChannelReport();
        channelReport.setId(getId());
        channelReport.setMessagesSent(new NullableLong(getMessagesSent()));
        channelReport.setMessagesReceived(new NullableLong(getMessagesReceived()));
        channelReport.setMessageBytesSent(new NullableLong(getMessageBytesSent()));
        channelReport.setMessageBytesReceived(new NullableLong(getMessageBytesReceived()));
        return channelReport;
    }

    public String getUserAlias() {
        return this._userAlias;
    }

    public String getUserId() {
        return this._userId;
    }

    public Future<Invitation> invite(String str, String str2) {
        Invitation invitation = new Invitation(this, str, str2);
        if (!this.__invitations.tryAdd(invitation.getInviteKey(), invitation)) {
            return PromiseBase.rejectNow(new Exception("Already existing invitation for this user and protocol."));
        }
        Promise promise = new Promise();
        doInvite(promise, invitation, Message.createInviteMessage(str, str2));
        return promise;
    }

    public Future<Object> kickClient(ClientInfo clientInfo) {
        Promise promise = new Promise();
        doKickClient(promise, clientInfo);
        return promise;
    }

    /* access modifiers changed from: package-private */
    public void leave() {
        this.__channelQueue.destroy();
        synchronized (this.__lock) {
            this.__left = true;
        }
    }

    private void processAnswer(Message message) {
        if (message.getConnectionId() != null) {
            processMessageForLocalConnection(message);
        } else if (message.getRemoteConnectionId() != null) {
            processMessageForRemoteConnection(message);
        } else {
            __log.debug(StringExtensions.format("Discarding unrecognized '{0}' message. ({1})", message.getType(), message.toJson()));
        }
    }

    private void processBitrateNotification(Message message) {
        ManagedConnection byId = message.getConnectionId() != null ? getConnections().getById(message.getConnectionId()) : getConnections().getByRemoteId(message.getRemoteConnectionId());
        if (byId != null) {
            byId.processMessage(message);
            return;
        }
        Holder holder = new Holder(null);
        boolean tryGetValue = this.__peerConnectionOffers.tryGetValue(message.getRemoteConnectionId(), holder);
        PeerConnectionOffer peerConnectionOffer = (PeerConnectionOffer) holder.getValue();
        if (message.getRemoteConnectionId() == null || !tryGetValue) {
            __log.debug(StringExtensions.concat("Discarding bitrateRequest message for unrecognized connection: ", message.toJson()));
        } else {
            peerConnectionOffer.processMessage(message);
        }
    }

    private void processBitrateRequest(Message message) {
        ManagedConnection byId = message.getConnectionId() != null ? getConnections().getById(message.getConnectionId()) : getConnections().getByRemoteId(message.getRemoteConnectionId());
        if (byId != null) {
            byId.processMessage(message);
            return;
        }
        Holder holder = new Holder(null);
        boolean tryGetValue = this.__peerConnectionOffers.tryGetValue(message.getRemoteConnectionId(), holder);
        PeerConnectionOffer peerConnectionOffer = (PeerConnectionOffer) holder.getValue();
        if (message.getRemoteConnectionId() == null || !tryGetValue) {
            __log.debug(StringExtensions.concat("Discarding bitrateRequest message for unrecognized connection: ", message.toJson()));
        } else {
            peerConnectionOffer.processMessage(message);
        }
    }

    private void processCandidate(Message message) {
        if (message.getConnectionId() != null) {
            processMessageForLocalConnection(message);
        } else if (message.getRemoteConnectionId() != null) {
            processMessageForRemoteConnection(message);
        } else {
            __log.debug(StringExtensions.format("Discarding unrecognized '{0}' message. ({1})", message.getType(), message.toJson()));
        }
    }

    private void processClientUpdate(Message message) {
        ClientConfig fromJson = ClientConfig.fromJson(message.getPayload());
        ClientInfo equivalentRemoteClientInfo = getEquivalentRemoteClientInfo(new ClientInfo(message.getRemoteUserId(), (String) null, message.getRemoteDeviceId(), (String) null, message.getRemoteClientId(), (String) null, (String[]) null));
        if (equivalentRemoteClientInfo != null) {
            ClientInfo clientInfo = new ClientInfo(equivalentRemoteClientInfo.getUserId(), equivalentRemoteClientInfo.getUserAlias(), equivalentRemoteClientInfo.getDeviceId(), equivalentRemoteClientInfo.getDeviceAlias(), equivalentRemoteClientInfo.getId(), equivalentRemoteClientInfo.getTag(), equivalentRemoteClientInfo.getRoles());
            equivalentRemoteClientInfo.inflate(fromJson.getUserAlias(), fromJson.getDeviceAlias(), fromJson.getTag(), fromJson.getRoles());
            IAction2<ClientInfo, ClientInfo> iAction2 = this._onRemoteClientUpdate;
            if (iAction2 != null) {
                iAction2.invoke(clientInfo, equivalentRemoteClientInfo);
            }
        }
    }

    private void processClose(Message message) {
        if (message.getConnectionId() != null) {
            processMessageForLocalConnection(message);
        } else if (message.getRemoteConnectionId() != null) {
            processMessageForRemoteConnection(message);
        } else {
            for (ManagedConnection managedConnection : (ManagedConnection[]) getConnections().getValues()) {
                Message clone = message.clone();
                clone.setConnectionId(managedConnection.getId());
                managedConnection.processMessage(clone);
            }
            this.__peerConnectionOffers.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean processConnectionMessages(Message message) {
        if (Global.equals(message.getType(), MessageType.getOffer())) {
            processOffer(message);
            return true;
        } else if (Global.equals(message.getType(), MessageType.getAnswer())) {
            processAnswer(message);
            return true;
        } else if (Global.equals(message.getType(), MessageType.getCandidate())) {
            processCandidate(message);
            return true;
        } else if (Global.equals(message.getType(), MessageType.getKeyFrameRequest())) {
            processKeyFrameRequest(message);
            return true;
        } else if (Global.equals(message.getType(), MessageType.getBitrateRequest())) {
            processBitrateRequest(message);
            return true;
        } else if (Global.equals(message.getType(), MessageType.getBitrateNotification())) {
            processBitrateNotification(message);
            return true;
        } else if (Global.equals(message.getType(), MessageType.getClose())) {
            processClose(message);
            return true;
        } else if (Global.equals(message.getType(), MessageType.getFail())) {
            processFail(message);
            return true;
        } else if (!Global.equals(message.getType(), MessageType.getUpdate()) || message.getConnectionId() == null) {
            return false;
        } else {
            ManagedConnection byId = getConnections().getById(message.getConnectionId());
            if (byId != null) {
                byId.processMessage(message);
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void processConnectionStateChange(ManagedConnection managedConnection) {
        if (Global.equals(managedConnection.getState(), ConnectionState.Failed) || Global.equals(managedConnection.getState(), ConnectionState.Closed)) {
            getConnections().remove(managedConnection);
            DataStream dataStream = managedConnection.getDataStream();
            if (dataStream != null) {
                dataStream.setGetRemoteConnectionInfo((IFunction1<String, Object>) null);
            }
        }
    }

    private void processError(Message message) {
        if (message.getConnectionId() != null) {
            processMessageForLocalConnection(message);
        } else if (message.getRemoteConnectionId() != null) {
            processMessageForRemoteConnection(message);
        } else {
            __log.debug(StringExtensions.format("Discarding unrecognized '{0}' message. ({1})", message.getType(), message.toJson()));
        }
    }

    private void processFail(Message message) {
        if (message.getConnectionId() != null) {
            processMessageForLocalConnection(message);
        } else if (message.getRemoteConnectionId() != null) {
            processMessageForRemoteConnection(message);
        } else {
            for (ManagedConnection managedConnection : (ManagedConnection[]) getConnections().getValues()) {
                Message clone = message.clone();
                clone.setConnectionId(managedConnection.getId());
                managedConnection.processMessage(clone);
            }
            this.__peerConnectionOffers.clear();
        }
    }

    /* access modifiers changed from: private */
    public boolean processInviteFeedback(Message message) {
        InviteFeedback fromJson = InviteFeedback.fromJson(message.getPayload());
        String inviteKey = Invitation.getInviteKey(getId(), fromJson.getUserId(), fromJson.getProtocol());
        Holder holder = new Holder(null);
        boolean tryGetValue = this.__invitations.tryGetValue(inviteKey, holder);
        Invitation invitation = (Invitation) holder.getValue();
        if (!tryGetValue) {
            __log.error("Received InviteFeedback for unknown invite.");
            return false;
        } else if (invitation.processFeedback(fromJson)) {
            return true;
        } else {
            Holder holder2 = new Holder(invitation);
            this.__invitations.tryRemove(inviteKey, holder2);
            Invitation invitation2 = (Invitation) holder2.getValue();
            return false;
        }
    }

    private void processKeyFrameRequest(Message message) {
        if (message.getConnectionId() != null) {
            processMessageForLocalConnection(message);
        } else if (message.getRemoteConnectionId() != null) {
            processMessageForRemoteConnection(message);
        } else {
            __log.debug(StringExtensions.format("Discarding unrecognized '{0}' message. ({1})", message.getType(), message.toJson()));
        }
    }

    /* access modifiers changed from: package-private */
    public void processMessageAsync(Message message) {
        if (!processConnectionMessages(message)) {
            this.__channelQueue.enqueue(message);
        }
    }

    private boolean processMessageForLocalConnection(Message message) {
        String connectionId = message.getConnectionId();
        ManagedConnection byId = getConnections().getById(connectionId);
        if (byId != null) {
            byId.processMessage(message);
            return true;
        }
        __log.debug(StringExtensions.format("Discarding '{0}' message for unrecognized local connection '{1}'. ({2})", message.getType(), connectionId, message.toJson()));
        return false;
    }

    private boolean processMessageForRemoteConnection(Message message) {
        String remoteConnectionId = message.getRemoteConnectionId();
        ManagedConnection byRemoteId = getConnections().getByRemoteId(remoteConnectionId);
        if (byRemoteId != null) {
            byRemoteId.processMessage(message);
            return true;
        }
        Holder holder = new Holder(null);
        boolean tryGetValue = this.__peerConnectionOffers.tryGetValue(remoteConnectionId, holder);
        PeerConnectionOffer peerConnectionOffer = (PeerConnectionOffer) holder.getValue();
        if (tryGetValue) {
            peerConnectionOffer.processMessage(message);
            return true;
        }
        __log.debug(StringExtensions.format("Discarding '{0}' message for unrecognized remote connection '{1}'. ({2})", message.getType(), remoteConnectionId, message.toJson()));
        return false;
    }

    /* access modifiers changed from: package-private */
    public void processMessageSync(Message message) {
        if (Global.equals(message.getType(), MessageType.getNotifyJoin())) {
            processNotifyJoin(ClientInfo.fromJsonArray(message.getPayload()));
        } else if (Global.equals(message.getType(), MessageType.getNotifyLeave())) {
            processNotifyLeave(ClientInfo.fromJsonArray(message.getPayload()), new ClientInfo(message.getRemoteUserId(), message.getRemoteDeviceId(), message.getRemoteClientId()));
        } else if (Global.equals(message.getType(), MessageType.getNotifyUpstreamOpen())) {
            processNotifyUpstreamOpen(ConnectionInfo.fromJsonArray(message.getPayload()));
        } else if (Global.equals(message.getType(), MessageType.getNotifyUpstreamClose())) {
            processNotifyUpstreamClose(ConnectionInfo.fromJsonArray(message.getPayload()));
        } else if (Global.equals(message.getType(), MessageType.getError())) {
            processError(message);
        } else if (Global.equals(message.getType(), MessageType.getNotifyUpstreamUpdate())) {
            processNotifyUpstreamUpdate(ConnectionInfo.fromJson(message.getPayload()));
        } else if (Global.equals(message.getType(), MessageType.getReject())) {
            processReject(message);
        } else if (Global.equals(message.getType(), MessageType.getMcuVideoLayout())) {
            processVideoLayout(VideoLayout.fromJson(message.getPayload()));
        } else if (Global.equals(message.getType(), MessageType.getMessage())) {
            processMessageType(message);
        } else if (Global.equals(message.getType(), MessageType.getUpdate()) && message.getRemoteClientId() == null) {
            ChannelClaim fromJson = ChannelClaim.fromJson(message.getPayload());
            if (getClaim() == null) {
                setClaim(fromJson);
                return;
            }
            ChannelClaim claim = getClaim();
            setClaim(fromJson);
            IAction2<ChannelClaim, ChannelClaim> iAction2 = this._onClaimUpdate;
            if (iAction2 != null) {
                iAction2.invoke(claim, fromJson);
            }
        } else if (Global.equals(message.getType(), MessageType.getUpdate()) && message.getConnectionId() == null) {
            processClientUpdate(message);
        } else if (Global.equals(message.getType(), MessageType.getInviteFeedback())) {
            processInviteFeedback(message);
        } else {
            __log.error(StringExtensions.concat("Unexpected message: ", message.toJson()));
        }
    }

    private void processMessageType(Message message) {
        ClientInfo clientInfo = new ClientInfo(message.getRemoteUserId(), message.getUserAlias(), message.getRemoteDeviceId(), message.getDeviceAlias(), message.getRemoteClientId(), message.getClientTag(), message.getClientRoles());
        String deserializeString = JsonSerializer.deserializeString(message.getPayload());
        this.__messagesReceived.increment();
        this.__messageBytesReceived.add((long) ArrayExtensions.getLength(Utf8.encode(deserializeString)));
        if (message.getClientId() != null) {
            IAction2<ClientInfo, String> iAction2 = this._onClientMessage;
            if (iAction2 != null) {
                iAction2.invoke(clientInfo, deserializeString);
            }
        } else if (message.getDeviceId() != null) {
            IAction2<ClientInfo, String> iAction22 = this._onDeviceMessage;
            if (iAction22 != null) {
                iAction22.invoke(clientInfo, deserializeString);
            }
        } else if (message.getUserId() != null) {
            IAction2<ClientInfo, String> iAction23 = this._onUserMessage;
            if (iAction23 != null) {
                iAction23.invoke(clientInfo, deserializeString);
            }
        } else {
            IAction2<ClientInfo, String> iAction24 = this._onMessage;
            if (iAction24 != null) {
                iAction24.invoke(clientInfo, deserializeString);
            }
        }
    }

    private void processNotifyJoin(ClientInfo[] clientInfoArr) {
        boolean z;
        IAction1<ClientInfo> iAction1;
        for (ClientInfo clientInfo : clientInfoArr) {
            if (!clientInfo.isEquivalent(getUserId(), getDeviceId(), getClientId())) {
                synchronized (this.__lock) {
                    Iterator<ClientInfo> it = this.__remoteClientInfos.iterator();
                    z = false;
                    while (it.hasNext()) {
                        if (it.next().isEquivalent(clientInfo)) {
                            z = true;
                        }
                    }
                    if (!z) {
                        this.__remoteClientInfos.add(clientInfo);
                    }
                }
                if (!z && (iAction1 = this._onRemoteClientJoin) != null) {
                    iAction1.invoke(clientInfo);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0082, code lost:
        r8 = (fm.liveswitch.SfuDownstreamConnection) r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processNotifyLeave(fm.liveswitch.ClientInfo[] r11, fm.liveswitch.ClientInfo r12) {
        /*
            r10 = this;
            int r0 = r11.length
            r1 = 0
            r2 = 0
        L_0x0003:
            if (r2 >= r0) goto L_0x00b0
            r3 = r11[r2]
            java.lang.String r4 = r10.getUserId()
            java.lang.String r5 = r10.getDeviceId()
            java.lang.String r6 = r10.getClientId()
            boolean r4 = r3.isEquivalent(r4, r5, r6)
            if (r4 == 0) goto L_0x0046
            java.lang.String r4 = r10.getUserId()
            java.lang.String r5 = r10.getDeviceId()
            java.lang.String r6 = r10.getClientId()
            boolean r4 = r12.isEquivalent(r4, r5, r6)
            if (r4 == 0) goto L_0x0046
            fm.liveswitch.ILog r3 = __log
            java.lang.String r4 = "This client {0} has been kicked from channel {1}."
            java.lang.String r5 = r10.getClientId()
            java.lang.String r6 = r10.getId()
            java.lang.String r4 = fm.liveswitch.StringExtensions.format(r4, r5, r6)
            r3.debug(r4)
            fm.liveswitch.IAction0 r3 = r10._onKick
            if (r3 == 0) goto L_0x00ac
            r3.invoke()
            goto L_0x00ac
        L_0x0046:
            fm.liveswitch.ClientInfo r3 = r10.getEquivalentRemoteClientInfo(r3)
            if (r3 == 0) goto L_0x00ac
            java.lang.Object r4 = r10.__lock
            monitor-enter(r4)
            java.util.ArrayList<fm.liveswitch.ClientInfo> r5 = r10.__remoteClientInfos     // Catch:{ all -> 0x00a9 }
            r5.remove(r3)     // Catch:{ all -> 0x00a9 }
            fm.liveswitch.ManagedConnectionCollection r5 = r10.getConnections()     // Catch:{ all -> 0x00a9 }
            java.lang.Object[] r5 = r5.getValues()     // Catch:{ all -> 0x00a9 }
            fm.liveswitch.ManagedConnection[] r5 = (fm.liveswitch.ManagedConnection[]) r5     // Catch:{ all -> 0x00a9 }
            int r6 = r5.length     // Catch:{ all -> 0x00a9 }
            r7 = 0
        L_0x0060:
            if (r7 >= r6) goto L_0x00a0
            r8 = r5[r7]     // Catch:{ all -> 0x00a9 }
            boolean r9 = r8 instanceof fm.liveswitch.PeerConnection     // Catch:{ all -> 0x00a9 }
            if (r9 == 0) goto L_0x007e
            fm.liveswitch.PeerConnection r8 = (fm.liveswitch.PeerConnection) r8     // Catch:{ all -> 0x00a9 }
            fm.liveswitch.PeerConnection r8 = (fm.liveswitch.PeerConnection) r8     // Catch:{ all -> 0x00a9 }
            fm.liveswitch.ClientInfo r9 = r8.getRemoteClientInfo()     // Catch:{ all -> 0x00a9 }
            boolean r9 = r3.isEquivalent((fm.liveswitch.ClientInfo) r9)     // Catch:{ all -> 0x00a9 }
            if (r9 == 0) goto L_0x009d
            fm.liveswitch.Message r9 = fm.liveswitch.Message.createCloseMessage()     // Catch:{ all -> 0x00a9 }
            r8.processMessage(r9)     // Catch:{ all -> 0x00a9 }
            goto L_0x009d
        L_0x007e:
            boolean r9 = r8 instanceof fm.liveswitch.SfuDownstreamConnection     // Catch:{ all -> 0x00a9 }
            if (r9 == 0) goto L_0x009d
            fm.liveswitch.SfuDownstreamConnection r8 = (fm.liveswitch.SfuDownstreamConnection) r8     // Catch:{ all -> 0x00a9 }
            fm.liveswitch.SfuDownstreamConnection r8 = (fm.liveswitch.SfuDownstreamConnection) r8     // Catch:{ all -> 0x00a9 }
            fm.liveswitch.ConnectionInfo r9 = r8.getRemoteConnectionInfo()     // Catch:{ all -> 0x00a9 }
            if (r9 == 0) goto L_0x009d
            fm.liveswitch.ClientInfo r9 = r9.createClientInfo()     // Catch:{ all -> 0x00a9 }
            boolean r9 = r3.isEquivalent((fm.liveswitch.ClientInfo) r9)     // Catch:{ all -> 0x00a9 }
            if (r9 == 0) goto L_0x009d
            fm.liveswitch.Message r9 = fm.liveswitch.Message.createCloseMessage()     // Catch:{ all -> 0x00a9 }
            r8.processMessage(r9)     // Catch:{ all -> 0x00a9 }
        L_0x009d:
            int r7 = r7 + 1
            goto L_0x0060
        L_0x00a0:
            monitor-exit(r4)     // Catch:{ all -> 0x00a9 }
            fm.liveswitch.IAction1<fm.liveswitch.ClientInfo> r4 = r10._onRemoteClientLeave
            if (r4 == 0) goto L_0x00ac
            r4.invoke(r3)
            goto L_0x00ac
        L_0x00a9:
            r11 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00a9 }
            throw r11
        L_0x00ac:
            int r2 = r2 + 1
            goto L_0x0003
        L_0x00b0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Channel.processNotifyLeave(fm.liveswitch.ClientInfo[], fm.liveswitch.ClientInfo):void");
    }

    private void processNotifyUpstreamClose(ConnectionInfo[] connectionInfoArr) {
        for (ConnectionInfo equivalentRemoteUpstreamConnectionInfo : connectionInfoArr) {
            ConnectionInfo equivalentRemoteUpstreamConnectionInfo2 = getEquivalentRemoteUpstreamConnectionInfo(equivalentRemoteUpstreamConnectionInfo);
            if (equivalentRemoteUpstreamConnectionInfo2 != null) {
                synchronized (this.__lock) {
                    this.__remoteUpstreamConnectionInfos.remove(equivalentRemoteUpstreamConnectionInfo2);
                    ManagedConnection byId = getConnections().getById(equivalentRemoteUpstreamConnectionInfo2.getId());
                    if (byId != null) {
                        byId.processMessage(Message.createCloseMessage());
                    }
                }
                IAction1<ConnectionInfo> iAction1 = this._onRemoteUpstreamConnectionClose;
                if (iAction1 != null) {
                    iAction1.invoke(equivalentRemoteUpstreamConnectionInfo2);
                }
            }
        }
    }

    private void processNotifyUpstreamOpen(ConnectionInfo[] connectionInfoArr) {
        boolean z;
        IAction1<ConnectionInfo> iAction1;
        for (ConnectionInfo connectionInfo : connectionInfoArr) {
            if (!connectionInfo.createClientInfo().isEquivalent(getUserId(), getDeviceId(), getClientId())) {
                synchronized (this.__lock) {
                    if (getEquivalentRemoteUpstreamConnectionInfo(connectionInfo) == null) {
                        this.__remoteUpstreamConnectionInfos.add(connectionInfo);
                        z = true;
                    } else {
                        z = false;
                    }
                }
                if (z && (iAction1 = this._onRemoteUpstreamConnectionOpen) != null) {
                    iAction1.invoke(connectionInfo);
                }
            }
        }
        synchronized (this.__lock) {
            VideoLayout videoLayout = this.__deferredLayout;
            if (videoLayout != null && processVideoLayout(videoLayout)) {
                this.__deferredLayout = null;
            }
        }
    }

    private void processNotifyUpstreamUpdate(ConnectionInfo connectionInfo) {
        SfuDownstreamConnection sfuDownstreamConnection;
        ConnectionInfo equivalentRemoteUpstreamConnectionInfo = getEquivalentRemoteUpstreamConnectionInfo(connectionInfo);
        if (equivalentRemoteUpstreamConnectionInfo != null) {
            connectionInfo.inflate(equivalentRemoteUpstreamConnectionInfo.getUserAlias(), equivalentRemoteUpstreamConnectionInfo.getDeviceAlias(), equivalentRemoteUpstreamConnectionInfo.getClientTag(), equivalentRemoteUpstreamConnectionInfo.getClientRoles());
            ManagedConnection[] managedConnectionArr = (ManagedConnection[]) getConnections().getValues();
            int length = managedConnectionArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                ManagedConnection managedConnection = managedConnectionArr[i];
                if (Global.equals(managedConnection.getType(), ConnectionType.getSfu()) && (sfuDownstreamConnection = (SfuDownstreamConnection) Global.tryCast(managedConnection, SfuDownstreamConnection.class)) != null) {
                    String remoteMediaId = sfuDownstreamConnection.getRemoteMediaId();
                    ConnectionInfo remoteConnectionInfo = sfuDownstreamConnection.getRemoteConnectionInfo();
                    if ((remoteConnectionInfo != null && remoteConnectionInfo.isEquivalent(equivalentRemoteUpstreamConnectionInfo)) || (remoteMediaId != null && Global.equals(remoteMediaId, equivalentRemoteUpstreamConnectionInfo.getMediaId()))) {
                        sfuDownstreamConnection.updateConnection(equivalentRemoteUpstreamConnectionInfo, connectionInfo).fail((IAction1<Exception>) new IAction1<Exception>() {
                            public void invoke(Exception exc) {
                                Channel.__log.error(StringExtensions.format("Could not update SFU downstream connection {0}.", (Object) Channel.this.getId()), exc);
                            }
                        });
                    }
                }
                i++;
            }
            sfuDownstreamConnection.updateConnection(equivalentRemoteUpstreamConnectionInfo, connectionInfo).fail((IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    Channel.__log.error(StringExtensions.format("Could not update SFU downstream connection {0}.", (Object) Channel.this.getId()), exc);
                }
            });
            IAction2<ConnectionInfo, ConnectionInfo> iAction2 = this._onRemoteUpstreamConnectionUpdate;
            if (iAction2 != null) {
                iAction2.invoke(equivalentRemoteUpstreamConnectionInfo, connectionInfo);
            }
        }
    }

    private void processOffer(Message message) {
        ClientInfo equivalentRemoteClientInfo;
        if (message.getConnectionId() != null) {
            ManagedConnection byId = getConnections().getById(message.getConnectionId());
            if (byId != null) {
                byId.processMessage(message);
                return;
            }
            return;
        }
        Message message2 = message;
        if (message.getRemoteConnectionId() != null && (equivalentRemoteClientInfo = getEquivalentRemoteClientInfo(new ClientInfo(message.getRemoteUserId(), (String) null, message.getRemoteDeviceId(), (String) null, message.getRemoteClientId(), (String) null, (String[]) null))) != null) {
            PeerConnectionOffer peerConnectionOffer = new PeerConnectionOffer(this, equivalentRemoteClientInfo, message.getRemoteConnectionId(), message.getConnectionTag(), SessionDescription.fromJson(message.getPayload()));
            this.__peerConnectionOffers.addOrUpdate(message.getRemoteConnectionId(), peerConnectionOffer);
            IAction1<PeerConnectionOffer> iAction1 = this._onPeerConnectionOffer;
            if (iAction1 != null) {
                iAction1.invoke(peerConnectionOffer);
            }
        }
    }

    private void processReject(Message message) {
        if (message.getConnectionId() != null) {
            processMessageForLocalConnection(message);
        } else if (message.getRemoteConnectionId() != null) {
            processMessageForRemoteConnection(message);
        } else {
            __log.debug(StringExtensions.format("Discarding unrecognized '{0}' message. ({1})", message.getType(), message.toJson()));
        }
    }

    private boolean processVideoLayout(VideoLayout videoLayout) {
        boolean z;
        IAction1<VideoLayout> iAction1;
        synchronized (this.__lock) {
            if (videoLayout != null) {
                z = videoLayout.inflate(this, new ClientInfo(getUserId(), getUserAlias(), getDeviceId(), getDeviceAlias(), getClientId(), getClientTag(), getClientRoles()), getRemoteUpstreamConnectionInfos());
                if (!z) {
                    __log.debug("Unable to inflate video layout due to delayed upstream notifications. Deferring until arrived.");
                    this.__deferredLayout = videoLayout;
                }
            } else {
                z = false;
            }
        }
        if (z && (iAction1 = this._onMcuVideoLayout) != null) {
            iAction1.invoke(videoLayout);
        }
        return z;
    }

    public Future<Object> rejectPeerConnection(PeerConnectionOffer peerConnectionOffer) {
        return simpleSend(Message.createPeerRejectMessage(peerConnectionOffer.getRemoteClientInfo().getUserId(), peerConnectionOffer.getRemoteClientInfo().getDeviceId(), peerConnectionOffer.getRemoteClientInfo().getId(), peerConnectionOffer.getRemoteConnectionId()));
    }

    public void removeOnClaimUpdate(IAction2<ChannelClaim, ChannelClaim> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onClaimUpdate, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onClaimUpdate.remove(iAction2);
        if (this.__onClaimUpdate.size() == 0) {
            this._onClaimUpdate = null;
        }
    }

    public void removeOnClientMessage(IAction2<ClientInfo, String> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onClientMessage, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onClientMessage.remove(iAction2);
        if (this.__onClientMessage.size() == 0) {
            this._onClientMessage = null;
        }
    }

    public void removeOnDeviceMessage(IAction2<ClientInfo, String> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onDeviceMessage, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onDeviceMessage.remove(iAction2);
        if (this.__onDeviceMessage.size() == 0) {
            this._onDeviceMessage = null;
        }
    }

    public void removeOnKick(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onKick, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onKick.remove(iAction0);
        if (this.__onKick.size() == 0) {
            this._onKick = null;
        }
    }

    public void removeOnMcuVideoLayout(IAction1<VideoLayout> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onMcuVideoLayout, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onMcuVideoLayout.remove(iAction1);
        if (this.__onMcuVideoLayout.size() == 0) {
            this._onMcuVideoLayout = null;
        }
    }

    public void removeOnMessage(IAction2<ClientInfo, String> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onMessage, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onMessage.remove(iAction2);
        if (this.__onMessage.size() == 0) {
            this._onMessage = null;
        }
    }

    public void removeOnPeerConnectionOffer(IAction1<PeerConnectionOffer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onPeerConnectionOffer, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onPeerConnectionOffer.remove(iAction1);
        if (this.__onPeerConnectionOffer.size() == 0) {
            this._onPeerConnectionOffer = null;
        }
    }

    public void removeOnRemoteClientJoin(IAction1<ClientInfo> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRemoteClientJoin, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRemoteClientJoin.remove(iAction1);
        if (this.__onRemoteClientJoin.size() == 0) {
            this._onRemoteClientJoin = null;
        }
    }

    public void removeOnRemoteClientLeave(IAction1<ClientInfo> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRemoteClientLeave, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRemoteClientLeave.remove(iAction1);
        if (this.__onRemoteClientLeave.size() == 0) {
            this._onRemoteClientLeave = null;
        }
    }

    public void removeOnRemoteClientUpdate(IAction2<ClientInfo, ClientInfo> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onRemoteClientUpdate, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onRemoteClientUpdate.remove(iAction2);
        if (this.__onRemoteClientUpdate.size() == 0) {
            this._onRemoteClientUpdate = null;
        }
    }

    public void removeOnRemoteUpstreamConnectionClose(IAction1<ConnectionInfo> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRemoteUpstreamConnectionClose, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRemoteUpstreamConnectionClose.remove(iAction1);
        if (this.__onRemoteUpstreamConnectionClose.size() == 0) {
            this._onRemoteUpstreamConnectionClose = null;
        }
    }

    public void removeOnRemoteUpstreamConnectionOpen(IAction1<ConnectionInfo> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRemoteUpstreamConnectionOpen, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRemoteUpstreamConnectionOpen.remove(iAction1);
        if (this.__onRemoteUpstreamConnectionOpen.size() == 0) {
            this._onRemoteUpstreamConnectionOpen = null;
        }
    }

    public void removeOnRemoteUpstreamConnectionUpdate(IAction2<ConnectionInfo, ConnectionInfo> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onRemoteUpstreamConnectionUpdate, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onRemoteUpstreamConnectionUpdate.remove(iAction2);
        if (this.__onRemoteUpstreamConnectionUpdate.size() == 0) {
            this._onRemoteUpstreamConnectionUpdate = null;
        }
    }

    public void removeOnUserMessage(IAction2<ClientInfo, String> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onUserMessage, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onUserMessage.remove(iAction2);
        if (this.__onUserMessage.size() == 0) {
            this._onUserMessage = null;
        }
    }

    /* access modifiers changed from: protected */
    public Future<Message> send(Message message) {
        message.setChannelId(getId());
        if (Global.equals(message.getType(), MessageType.getMessage())) {
            String deserializeString = JsonSerializer.deserializeString(message.getPayload());
            this.__messagesSent.increment();
            this.__messageBytesSent.add((long) ArrayExtensions.getLength(Utf8.encode(deserializeString)));
        }
        return this.__doSend.invoke(message);
    }

    public Future<Object> sendClientMessage(String str, String str2, String str3, String str4) {
        return simpleSend(Message.createMessageMessage(str4, str, str2, str3));
    }

    public Future<Object> sendDeviceMessage(String str, String str2, String str3) {
        return simpleSend(Message.createMessageMessage(str3, str, str2));
    }

    public Future<Object> sendMessage(String str) {
        return simpleSend(Message.createMessageMessage(str));
    }

    public Future<Object> sendUserMessage(String str, String str2) {
        return simpleSend(Message.createMessageMessage(str2, str));
    }

    private void setApplicationId(String str) {
        this._applicationId = str;
    }

    private void setClaim(ChannelClaim channelClaim) {
        this._claim = channelClaim;
    }

    private void setClientId(String str) {
        this._clientId = str;
    }

    private void setClientRoles(String[] strArr) {
        this._clientRoles = strArr;
    }

    private void setClientTag(String str) {
        this._clientTag = str;
    }

    private void setConnections(ManagedConnectionCollection managedConnectionCollection) {
        this._connections = managedConnectionCollection;
    }

    private void setDeviceAlias(String str) {
        this._deviceAlias = str;
    }

    private void setDeviceId(String str) {
        this._deviceId = str;
    }

    private void setId(String str) {
        this._id = str;
    }

    private void setUserAlias(String str) {
        this._userAlias = str;
    }

    private void setUserId(String str) {
        this._userId = str;
    }

    private Future<Object> simpleSend(Message message) {
        Promise promise = new Promise();
        doSimpleSend(promise, message);
        return promise;
    }

    public Future<Object> update(ChannelConfig channelConfig) {
        Promise promise = new Promise();
        doUpdate(promise, channelConfig);
        return promise;
    }

    /* access modifiers changed from: package-private */
    public void updateClientConfig(ClientConfig clientConfig) {
        setUserAlias(clientConfig.getUserAlias());
        setDeviceAlias(clientConfig.getDeviceAlias());
        setClientTag(clientConfig.getTag());
        setClientRoles(clientConfig.getRoles());
    }
}
