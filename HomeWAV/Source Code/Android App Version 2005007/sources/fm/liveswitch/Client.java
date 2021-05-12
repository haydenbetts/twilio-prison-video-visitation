package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client extends Dynamic {
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(Client.class);
    private ManagedConcurrentDictionary<String, Channel> __channels;
    /* access modifiers changed from: private */
    public String __deviceAlias;
    /* access modifiers changed from: private */
    public ManagedConcurrentDictionary<String, ArrayList<Message>> __earlyChannelMessages;
    private SignallingClient __gatewayClient;
    /* access modifiers changed from: private */
    public ArrayList<String> __joining;
    /* access modifiers changed from: private */
    public ArrayList<String> __leaving;
    /* access modifiers changed from: private */
    public Object __lock;
    /* access modifiers changed from: private */
    public List<IAction1<HttpRequestCreatedArgs>> __onHttpRequestCreated;
    /* access modifiers changed from: private */
    public List<IAction1<HttpResponseReceivedArgs>> __onHttpResponseReceived;
    /* access modifiers changed from: private */
    public List<IAction2<ClientInfo, ClientInfo>> __onRemoteUpdate;
    /* access modifiers changed from: private */
    public List<IAction1<Client>> __onStateChange;
    /* access modifiers changed from: private */
    public String __region;
    private boolean __registered;
    private boolean __registering;
    /* access modifiers changed from: private */
    public String[] __roles;
    private SendQueue __sendQueue;
    /* access modifiers changed from: private */
    public String __tag;
    private boolean __unregistering;
    /* access modifiers changed from: private */
    public String __userAlias;
    private String _applicationId;
    private String _deviceId;
    private String _externalId;
    private String _gatewayUrl;
    private String _id;
    private IAction1<HttpRequestCreatedArgs> _onHttpRequestCreated;
    private IAction1<HttpResponseReceivedArgs> _onHttpResponseReceived;
    private IAction2<ClientInfo, ClientInfo> _onRemoteUpdate;
    private IAction1<Client> _onStateChange;
    private String _userId;

    /* access modifiers changed from: private */
    public void _gatewayClient_OnHttpRequestCreated(HttpRequestCreatedArgs httpRequestCreatedArgs) {
        IAction1<HttpRequestCreatedArgs> iAction1 = this._onHttpRequestCreated;
        if (iAction1 != null) {
            iAction1.invoke(httpRequestCreatedArgs);
        }
    }

    /* access modifiers changed from: private */
    public void _gatewayClient_OnHttpResponseReceived(HttpResponseReceivedArgs httpResponseReceivedArgs) {
        IAction1<HttpResponseReceivedArgs> iAction1 = this._onHttpResponseReceived;
        if (iAction1 != null) {
            iAction1.invoke(httpResponseReceivedArgs);
        }
    }

    /* access modifiers changed from: private */
    public void _gatewayClient_OnServerSubscribe(SignallingServerSubscribeArgs signallingServerSubscribeArgs) {
        signallingServerSubscribeArgs.setOnReceive(new IAction1<SignallingSubscribeReceiveArgs>() {
            public void invoke(SignallingSubscribeReceiveArgs signallingSubscribeReceiveArgs) {
                Client.this.receive(Message.fromJson(signallingSubscribeReceiveArgs.getDataJson()));
            }
        });
    }

    /* access modifiers changed from: private */
    public void _gatewayClient_OnStateChange(SignallingClient signallingClient) {
        if (__log.getIsInfoEnabled()) {
            __log.info(StringExtensions.format("Setting client {0} state to {1}.", getId(), StringExtensions.toLower(getState().toString())), getUnregisterException());
        }
        synchronized (this.__lock) {
            if (Global.equals(signallingClient.getState(), SignallingClientState.Connecting)) {
                this.__registering = true;
                this.__registered = false;
                this.__unregistering = false;
            } else if (Global.equals(signallingClient.getState(), SignallingClientState.Connected)) {
                this.__registering = false;
                this.__registered = true;
                this.__unregistering = false;
                this.__sendQueue.trySendPending();
            } else if (Global.equals(signallingClient.getState(), SignallingClientState.Disconnecting)) {
                this.__unregistering = true;
                this.__registering = false;
                dispatchCloseAll(getChannels());
            } else if (Global.equals(signallingClient.getState(), SignallingClientState.Disconnected)) {
                this.__unregistering = false;
                this.__registered = false;
                this.__registering = false;
                dispatchCloseAll(getChannels());
                this.__joining.clear();
                this.__leaving.clear();
                this.__sendQueue.clear();
                for (Channel id : getChannels()) {
                    removeChannel(id.getId());
                }
            }
            IAction1<Client> iAction1 = this._onStateChange;
            if (iAction1 != null) {
                iAction1.invoke(this);
            }
        }
    }

    /* access modifiers changed from: private */
    public Channel addChannel(String str) {
        return this.__channels.getOrAdd(str, new IFunctionDelegate1<String, Channel>() {
            public String getId() {
                return "fm.liveswitch.Client.createChannel";
            }

            public Channel invoke(String str) {
                return Client.this.createChannel(str);
            }
        });
    }

    public void addOnHttpRequestCreated(IAction1<HttpRequestCreatedArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onHttpRequestCreated == null) {
                this._onHttpRequestCreated = new IAction1<HttpRequestCreatedArgs>() {
                    public void invoke(HttpRequestCreatedArgs httpRequestCreatedArgs) {
                        Iterator it = new ArrayList(Client.this.__onHttpRequestCreated).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(httpRequestCreatedArgs);
                        }
                    }
                };
            }
            this.__onHttpRequestCreated.add(iAction1);
        }
    }

    public void addOnHttpResponseReceived(IAction1<HttpResponseReceivedArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onHttpResponseReceived == null) {
                this._onHttpResponseReceived = new IAction1<HttpResponseReceivedArgs>() {
                    public void invoke(HttpResponseReceivedArgs httpResponseReceivedArgs) {
                        Iterator it = new ArrayList(Client.this.__onHttpResponseReceived).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(httpResponseReceivedArgs);
                        }
                    }
                };
            }
            this.__onHttpResponseReceived.add(iAction1);
        }
    }

    public void addOnRemoteUpdate(IAction2<ClientInfo, ClientInfo> iAction2) {
        if (iAction2 != null) {
            if (this._onRemoteUpdate == null) {
                this._onRemoteUpdate = new IAction2<ClientInfo, ClientInfo>() {
                    public void invoke(ClientInfo clientInfo, ClientInfo clientInfo2) {
                        Iterator it = new ArrayList(Client.this.__onRemoteUpdate).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(clientInfo, clientInfo2);
                        }
                    }
                };
            }
            this.__onRemoteUpdate.add(iAction2);
        }
    }

    public void addOnStateChange(IAction1<Client> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<Client>() {
                    public void invoke(Client client) {
                        Iterator it = new ArrayList(Client.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(client);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    private void attachChannelEvents(final Channel channel, final String str) {
        channel.addOnKick(new IAction0() {
            public void invoke() {
                channel.closeAll();
                Channel unused = Client.this.removeChannel(str);
            }
        });
    }

    public Client(String str, String str2) {
        this(str, str2, (String) null);
    }

    public Client(String str, String str2, String str3) {
        this(str, str2, str3, (String) null);
    }

    public Client(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, (String) null, (String[]) null);
    }

    public Client(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3, str4, str5, (String[]) null);
    }

    public Client(String str, String str2, String str3, String str4, String str5, String[] strArr) {
        this(str, str2, str3, str4, str5, strArr, (String) null);
    }

    public Client(String str, String str2, String str3, String str4, String str5, String[] strArr, String str6) {
        this.__onHttpRequestCreated = new ArrayList();
        this.__onHttpResponseReceived = new ArrayList();
        this.__onRemoteUpdate = new ArrayList();
        this.__onStateChange = new ArrayList();
        this._onHttpRequestCreated = null;
        this._onHttpResponseReceived = null;
        this._onRemoteUpdate = null;
        this._onStateChange = null;
        this.__lock = new Object();
        this.__registered = false;
        this.__registering = false;
        this.__unregistering = false;
        this.__joining = new ArrayList<>();
        this.__leaving = new ArrayList<>();
        this.__earlyChannelMessages = new ManagedConcurrentDictionary<>();
        if (StringExtensions.isNullOrEmpty(str)) {
            throw new RuntimeException(new Exception("Gateway URL cannot be null or empty."));
        } else if (!StringExtensions.isNullOrEmpty(str2)) {
            setGatewayUrl(str);
            setApplicationId(str2);
            if (str5 != null) {
                setExternalId(str5);
                __log.warn(StringExtensions.format("Client IDs are automatically generated. The provided client ID '{0}' will be moved to the ExternalId property and replaced with a generated ID.", (Object) getExternalId()));
                str5 = null;
            }
            setUserId(str3 == null ? Guid.newGuid().toString().replace("-", "") : str3);
            setDeviceId(str4 == null ? Guid.newGuid().toString().replace("-", "") : str4);
            setId(str5 == null ? Guid.newGuid().toString().replace("-", "") : str5);
            this.__roles = strArr;
            this.__region = str6;
            this.__channels = new ManagedConcurrentDictionary<>();
            SignallingClient signallingClient = new SignallingClient(getGatewayUrl(), this.__lock);
            signallingClient.setDisableBinary(true);
            signallingClient.setRequestMaxRetries(3);
            this.__gatewayClient = signallingClient;
            this.__sendQueue = new SendQueue(new SignallingSendQueueTransport(this.__gatewayClient));
            this.__gatewayClient.addOnServerSubscribe(new IActionDelegate1<SignallingServerSubscribeArgs>() {
                public String getId() {
                    return "fm.liveswitch.Client._gatewayClient_OnServerSubscribe";
                }

                public void invoke(SignallingServerSubscribeArgs signallingServerSubscribeArgs) {
                    Client.this._gatewayClient_OnServerSubscribe(signallingServerSubscribeArgs);
                }
            });
            this.__gatewayClient.addOnStateChange(new IActionDelegate1<SignallingClient>() {
                public String getId() {
                    return "fm.liveswitch.Client._gatewayClient_OnStateChange";
                }

                public void invoke(SignallingClient signallingClient) {
                    Client.this._gatewayClient_OnStateChange(signallingClient);
                }
            });
            this.__gatewayClient.addOnHttpRequestCreated(new IActionDelegate1<HttpRequestCreatedArgs>() {
                public String getId() {
                    return "fm.liveswitch.Client._gatewayClient_OnHttpRequestCreated";
                }

                public void invoke(HttpRequestCreatedArgs httpRequestCreatedArgs) {
                    Client.this._gatewayClient_OnHttpRequestCreated(httpRequestCreatedArgs);
                }
            });
            this.__gatewayClient.addOnHttpResponseReceived(new IActionDelegate1<HttpResponseReceivedArgs>() {
                public String getId() {
                    return "fm.liveswitch.Client._gatewayClient_OnHttpResponseReceived";
                }

                public void invoke(HttpResponseReceivedArgs httpResponseReceivedArgs) {
                    Client.this._gatewayClient_OnHttpResponseReceived(httpResponseReceivedArgs);
                }
            });
        } else {
            throw new RuntimeException(new Exception("Application ID cannot be null or empty."));
        }
    }

    public Future<Object> closeAll() {
        return doCloseAll(getChannels());
    }

    /* access modifiers changed from: private */
    public Channel createChannel(String str) {
        Channel channel = new Channel(this.__lock, new ConnectionFactory(), getApplicationId(), getUserId(), getUserAlias(), getDeviceId(), getDeviceAlias(), getId(), getTag(), getRoles(), str, new IFunctionDelegate1<Message, Future<Message>>() {
            public String getId() {
                return "fm.liveswitch.Client.send";
            }

            public Future<Message> invoke(Message message) {
                return Client.this.send(message);
            }
        });
        attachChannelEvents(channel, str);
        return channel;
    }

    private void dispatchCloseAll(final Channel[] channelArr) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                Future unused = Client.this.doCloseAll(channelArr);
            }
        });
    }

    /* access modifiers changed from: private */
    public Future<Object> doCloseAll(Channel[] channelArr) {
        __log.debug(getId(), "Closing all connections.");
        ArrayList arrayList = new ArrayList();
        for (Channel closeAll : channelArr) {
            arrayList.add(closeAll.closeAll());
        }
        return PromiseBase.all((Future[]) arrayList.toArray(new Future[0]));
    }

    /* access modifiers changed from: private */
    public void doDisconnect(final Promise<Object> promise, final long j, ChannelReport[] channelReportArr) {
        __log.debug(getId(), "Sending unregister request.");
        SignallingDisconnectArgs signallingDisconnectArgs = new SignallingDisconnectArgs();
        signallingDisconnectArgs.setMetaJson(Message.createUnregisterMessage(channelReportArr).toJson());
        signallingDisconnectArgs.setOnComplete(new IAction1<SignallingDisconnectCompleteArgs>() {
            public void invoke(SignallingDisconnectCompleteArgs signallingDisconnectCompleteArgs) {
                Client.__log.info(Client.this.getId(), StringExtensions.format("Client {0} took {1}ms to unregister.", Client.this.getId(), LongExtensions.toString(Long.valueOf((ManagedStopwatch.getTimestamp() - j) / ((long) Constants.getTicksPerMillisecond())))));
                if (signallingDisconnectCompleteArgs.getException() != null) {
                    Client.__log.error(Client.this.getId(), "Could not send unregister message.", signallingDisconnectCompleteArgs.getException());
                }
                promise.resolve(null);
            }
        });
        this.__gatewayClient.disconnect(signallingDisconnectArgs);
    }

    private Future<Channel> doJoin(final Promise<Channel> promise, final String str, String str2, long j) {
        __log.info(getId(), StringExtensions.format("Joining channel {0}.", (Object) str));
        synchronized (this.__lock) {
            if (!this.__registered) {
                promise.reject(new Exception("Client is not registered."));
                return promise;
            } else if (this.__unregistering) {
                promise.reject(new Exception("Client is unregistering."));
                return promise;
            } else {
                if (this.__channels.containsKey(str)) {
                    Holder holder = new Holder(null);
                    boolean tryGetValue = this.__channels.tryGetValue(str, holder);
                    Channel channel = (Channel) holder.getValue();
                    if (tryGetValue) {
                        promise.resolve(channel);
                        return promise;
                    }
                }
                if (this.__joining.contains(str)) {
                    promise.reject(new Exception("Client is already joining."));
                    return promise;
                } else if (this.__leaving.contains(str)) {
                    promise.reject(new Exception("Client is currently leaving."));
                    return promise;
                } else {
                    this.__joining.add(str);
                    __log.debug(getId(), "Sending join request.");
                    final long j2 = j;
                    final String str3 = str;
                    final Promise<Channel> promise2 = promise;
                    send(Message.createJoinMessage(str, str2)).then(new IAction1<Message>() {
                        public void invoke(Message message) {
                            ArrayList arrayList;
                            Client.__log.info(Client.this.getId(), StringExtensions.format("Client {0} took {1}ms to join {2}.", Client.this.getId(), LongExtensions.toString(Long.valueOf((ManagedStopwatch.getTimestamp() - j2) / ((long) Constants.getTicksPerMillisecond()))), str3));
                            String str = null;
                            if (message == null || !Global.equals(message.getType(), MessageType.getError())) {
                                Channel access$1600 = Client.this.addChannel(str3);
                                synchronized (Client.this.__lock) {
                                    Client.this.__joining.remove(str3);
                                }
                                if (message != null) {
                                    if (Global.equals(message.getType(), MessageType.getNotifyJoin())) {
                                        access$1600.processMessageSync(message);
                                    } else {
                                        Client.__log.warn(Client.this.getId(), StringExtensions.concat("Unexpected message in join response: ", message.toJson()));
                                    }
                                }
                                synchronized (Client.this.__lock) {
                                    Holder holder = new Holder(null);
                                    Client.this.__earlyChannelMessages.tryRemove(str3, holder);
                                    arrayList = (ArrayList) holder.getValue();
                                }
                                if (arrayList != null) {
                                    Iterator it = arrayList.iterator();
                                    while (it.hasNext()) {
                                        access$1600.processMessageAsync((Message) it.next());
                                    }
                                }
                                promise2.resolve(access$1600);
                                return;
                            }
                            synchronized (Client.this.__lock) {
                                Client.this.__joining.remove(str3);
                            }
                            if (message.getPayload() != null) {
                                str = JsonSerializer.deserializeString(message.getPayload());
                            }
                            if (str == null) {
                                promise2.reject(new Exception("Gateway declined join request."));
                            } else {
                                promise2.reject(new Exception(StringExtensions.concat("Gateway declined join request. ", str)));
                            }
                        }
                    }, (IAction1<Exception>) new IAction1<Exception>() {
                        public void invoke(Exception exc) {
                            Client.__log.error(Client.this.getId(), "Could not send join message.", exc);
                            synchronized (Client.this.__lock) {
                                Client.this.__joining.remove(str);
                            }
                            promise.reject(exc);
                        }
                    });
                    return promise;
                }
            }
        }
    }

    private Future<Channel> doLeave(Promise<Channel> promise, String str, long j) {
        __log.info(getId(), StringExtensions.format("Leaving channel {0}.", (Object) str));
        synchronized (this.__lock) {
            if (!this.__registered) {
                promise.reject(new Exception("Client is not registered."));
                return promise;
            } else if (this.__unregistering) {
                promise.reject(new Exception("Client is unregistering."));
                return promise;
            } else {
                Holder holder = new Holder(null);
                boolean tryGetValue = this.__channels.tryGetValue(str, holder);
                Channel channel = (Channel) holder.getValue();
                if (!tryGetValue) {
                    promise.resolve(null);
                    return promise;
                } else if (this.__leaving.contains(str)) {
                    promise.reject(new Exception("Client is already leaving."));
                    return promise;
                } else if (this.__joining.contains(str)) {
                    promise.reject(new Exception("Client is currently joining."));
                    return promise;
                } else {
                    this.__leaving.add(str);
                    doLeaveChannel(promise, str, channel, j);
                    return promise;
                }
            }
        }
    }

    private void doLeaveChannel(final Promise<Channel> promise, final String str, Channel channel, long j) {
        __log.debug(getId(), "Sending leave request.");
        final long j2 = j;
        final String str2 = str;
        final Promise<Channel> promise2 = promise;
        final Channel channel2 = channel;
        send(Message.createLeaveMessage(str, channel.getReport())).then(new IAction1<Message>() {
            public void invoke(Message message) {
                Client.__log.info(Client.this.getId(), StringExtensions.format("Client {0} took {1}ms to leave {2}.", Client.this.getId(), LongExtensions.toString(Long.valueOf((ManagedStopwatch.getTimestamp() - j2) / ((long) Constants.getTicksPerMillisecond()))), str2));
                String str = null;
                if (message == null || !Global.equals(message.getType(), MessageType.getError())) {
                    Channel unused = Client.this.removeChannel(str2);
                    synchronized (Client.this.__lock) {
                        Client.this.__leaving.remove(str2);
                    }
                    Channel channel = channel2;
                    if (channel != null) {
                        channel.closeAll();
                    }
                    if (message != null) {
                        Client.__log.warn(Client.this.getId(), StringExtensions.concat("Unexpected message in leave response: ", message.toJson()));
                    }
                    promise2.resolve(null);
                    return;
                }
                synchronized (Client.this.__lock) {
                    Client.this.__leaving.remove(str2);
                }
                if (message.getPayload() != null) {
                    str = JsonSerializer.deserializeString(message.getPayload());
                }
                if (str == null) {
                    promise2.reject(new Exception("Gateway declined leave request."));
                } else {
                    promise2.reject(new Exception(StringExtensions.concat("Gateway declined leave request. ", str)));
                }
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                Client.__log.error(Client.this.getId(), "Could not send leave message.", exc);
                synchronized (Client.this.__lock) {
                    Client.this.__leaving.remove(str);
                }
                promise.reject(exc);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0041, code lost:
        r0 = new fm.liveswitch.SignallingConnectArgs();
        r0.setMetaJson(fm.liveswitch.Message.createRegisterMessage(getApplicationId(), getUserId(), getUserAlias(), getDeviceId(), getDeviceAlias(), getId(), getTag(), getRoles(), getRegion(), r13).toJson());
        r4 = r14;
        r6 = r13;
        r7 = r12;
        r0.setOnSuccess(new fm.liveswitch.Client.AnonymousClass19(r11));
        r0.setOnFailure(new fm.liveswitch.Client.AnonymousClass20(r11));
        r13 = getInfo();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0093, code lost:
        if (r13.getArchitecture() == null) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0095, code lost:
        r0.setExtensionValueJson("architecture", fm.liveswitch.JsonSerializer.serializeString(r13.getArchitecture()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a6, code lost:
        if (r13.getCoreCount() == 0) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a8, code lost:
        r0.setExtensionValueJson("coreCount", fm.liveswitch.JsonSerializer.serializeInteger(new fm.liveswitch.NullableInteger(r13.getCoreCount())));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00be, code lost:
        if (r13.getMachineName() == null) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c0, code lost:
        r0.setExtensionValueJson("machineName", fm.liveswitch.JsonSerializer.serializeString(r13.getMachineName()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d1, code lost:
        if (r13.getOperatingSystem() == null) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d3, code lost:
        r0.setExtensionValueJson("operatingSystem", fm.liveswitch.JsonSerializer.serializeString(r13.getOperatingSystem()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e4, code lost:
        if (r13.getOperatingSystemVersion() == null) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e6, code lost:
        r0.setExtensionValueJson("operatingSystemVersion", fm.liveswitch.JsonSerializer.serializeString(r13.getOperatingSystemVersion()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00fb, code lost:
        if (r13.getPhysicalMemory() == 0) goto L_0x010f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fd, code lost:
        r0.setExtensionValueJson("physicalMemory", fm.liveswitch.JsonSerializer.serializeLong(new fm.liveswitch.NullableLong(r13.getPhysicalMemory())));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0113, code lost:
        if (r13.getSourceLanguage() == null) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0115, code lost:
        r0.setExtensionValueJson("sourceLanguage", fm.liveswitch.JsonSerializer.serializeString(r13.getSourceLanguage()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0122, code lost:
        __log.debug(getId(), "Sending register request.");
        r11.__gatewayClient.connect(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0132, code lost:
        return r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.Future<fm.liveswitch.Channel[]> doRegister(final fm.liveswitch.Promise<fm.liveswitch.Channel[]> r12, java.lang.String r13, long r14) {
        /*
            r11 = this;
            fm.liveswitch.ILog r0 = __log
            java.lang.String r1 = r11.getId()
            java.lang.String r2 = "Registering to application {0}."
            java.lang.String r3 = r11.getApplicationId()
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r3)
            r0.info((java.lang.String) r1, (java.lang.String) r2)
            java.lang.Object r0 = r11.__lock
            monitor-enter(r0)
            boolean r1 = r11.__registered     // Catch:{ all -> 0x0133 }
            if (r1 == 0) goto L_0x0020
            r13 = 0
            r12.resolve(r13)     // Catch:{ all -> 0x0133 }
            monitor-exit(r0)     // Catch:{ all -> 0x0133 }
            return r12
        L_0x0020:
            boolean r1 = r11.__registering     // Catch:{ all -> 0x0133 }
            if (r1 == 0) goto L_0x0030
            java.lang.Exception r13 = new java.lang.Exception     // Catch:{ all -> 0x0133 }
            java.lang.String r14 = "Client is already registering."
            r13.<init>(r14)     // Catch:{ all -> 0x0133 }
            r12.reject(r13)     // Catch:{ all -> 0x0133 }
            monitor-exit(r0)     // Catch:{ all -> 0x0133 }
            return r12
        L_0x0030:
            boolean r1 = r11.__unregistering     // Catch:{ all -> 0x0133 }
            if (r1 == 0) goto L_0x0040
            java.lang.Exception r13 = new java.lang.Exception     // Catch:{ all -> 0x0133 }
            java.lang.String r14 = "Client is currently unregistering."
            r13.<init>(r14)     // Catch:{ all -> 0x0133 }
            r12.reject(r13)     // Catch:{ all -> 0x0133 }
            monitor-exit(r0)     // Catch:{ all -> 0x0133 }
            return r12
        L_0x0040:
            monitor-exit(r0)     // Catch:{ all -> 0x0133 }
            fm.liveswitch.SignallingConnectArgs r0 = new fm.liveswitch.SignallingConnectArgs
            r0.<init>()
            java.lang.String r1 = r11.getApplicationId()
            java.lang.String r2 = r11.getUserId()
            java.lang.String r3 = r11.getUserAlias()
            java.lang.String r4 = r11.getDeviceId()
            java.lang.String r5 = r11.getDeviceAlias()
            java.lang.String r6 = r11.getId()
            java.lang.String r7 = r11.getTag()
            java.lang.String[] r8 = r11.getRoles()
            java.lang.String r9 = r11.getRegion()
            r10 = r13
            fm.liveswitch.Message r1 = fm.liveswitch.Message.createRegisterMessage(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            java.lang.String r1 = r1.toJson()
            r0.setMetaJson(r1)
            fm.liveswitch.Client$19 r1 = new fm.liveswitch.Client$19
            r2 = r1
            r3 = r11
            r4 = r14
            r6 = r13
            r7 = r12
            r2.<init>(r4, r6, r7)
            r0.setOnSuccess(r1)
            fm.liveswitch.Client$20 r13 = new fm.liveswitch.Client$20
            r13.<init>(r12)
            r0.setOnFailure(r13)
            fm.liveswitch.ClientInfo r13 = r11.getInfo()
            java.lang.String r14 = r13.getArchitecture()
            if (r14 == 0) goto L_0x00a2
            java.lang.String r14 = "architecture"
            java.lang.String r15 = r13.getArchitecture()
            java.lang.String r15 = fm.liveswitch.JsonSerializer.serializeString(r15)
            r0.setExtensionValueJson(r14, r15)
        L_0x00a2:
            int r14 = r13.getCoreCount()
            if (r14 == 0) goto L_0x00ba
            java.lang.String r14 = "coreCount"
            fm.liveswitch.NullableInteger r15 = new fm.liveswitch.NullableInteger
            int r1 = r13.getCoreCount()
            r15.<init>((int) r1)
            java.lang.String r15 = fm.liveswitch.JsonSerializer.serializeInteger(r15)
            r0.setExtensionValueJson(r14, r15)
        L_0x00ba:
            java.lang.String r14 = r13.getMachineName()
            if (r14 == 0) goto L_0x00cd
            java.lang.String r14 = "machineName"
            java.lang.String r15 = r13.getMachineName()
            java.lang.String r15 = fm.liveswitch.JsonSerializer.serializeString(r15)
            r0.setExtensionValueJson(r14, r15)
        L_0x00cd:
            java.lang.String r14 = r13.getOperatingSystem()
            if (r14 == 0) goto L_0x00e0
            java.lang.String r14 = "operatingSystem"
            java.lang.String r15 = r13.getOperatingSystem()
            java.lang.String r15 = fm.liveswitch.JsonSerializer.serializeString(r15)
            r0.setExtensionValueJson(r14, r15)
        L_0x00e0:
            java.lang.String r14 = r13.getOperatingSystemVersion()
            if (r14 == 0) goto L_0x00f3
            java.lang.String r14 = "operatingSystemVersion"
            java.lang.String r15 = r13.getOperatingSystemVersion()
            java.lang.String r15 = fm.liveswitch.JsonSerializer.serializeString(r15)
            r0.setExtensionValueJson(r14, r15)
        L_0x00f3:
            long r14 = r13.getPhysicalMemory()
            r1 = 0
            int r3 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
            if (r3 == 0) goto L_0x010f
            java.lang.String r14 = "physicalMemory"
            fm.liveswitch.NullableLong r15 = new fm.liveswitch.NullableLong
            long r1 = r13.getPhysicalMemory()
            r15.<init>((long) r1)
            java.lang.String r15 = fm.liveswitch.JsonSerializer.serializeLong(r15)
            r0.setExtensionValueJson(r14, r15)
        L_0x010f:
            java.lang.String r14 = r13.getSourceLanguage()
            if (r14 == 0) goto L_0x0122
            java.lang.String r14 = "sourceLanguage"
            java.lang.String r13 = r13.getSourceLanguage()
            java.lang.String r13 = fm.liveswitch.JsonSerializer.serializeString(r13)
            r0.setExtensionValueJson(r14, r13)
        L_0x0122:
            fm.liveswitch.ILog r13 = __log
            java.lang.String r14 = r11.getId()
            java.lang.String r15 = "Sending register request."
            r13.debug((java.lang.String) r14, (java.lang.String) r15)
            fm.liveswitch.SignallingClient r13 = r11.__gatewayClient
            r13.connect(r0)
            return r12
        L_0x0133:
            r12 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0133 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Client.doRegister(fm.liveswitch.Promise, java.lang.String, long):fm.liveswitch.Future");
    }

    private Future<Object> doUnregister(Promise<Object> promise, long j) {
        __log.info(getId(), StringExtensions.format("Unregistering from application {0}.", (Object) getApplicationId()));
        synchronized (this.__lock) {
            if (!this.__registered) {
                promise.resolve(null);
                return promise;
            } else if (this.__unregistering) {
                promise.reject(new Exception("Client is already unregistering."));
                return promise;
            } else if (this.__registering) {
                promise.reject(new Exception("Client is currently registering."));
                return promise;
            } else {
                ArrayList arrayList = new ArrayList();
                for (Channel report : getChannels()) {
                    arrayList.add(report.getReport());
                }
                doUnregister(promise, j, (ChannelReport[]) arrayList.toArray(new ChannelReport[0]));
                return promise;
            }
        }
    }

    private void doUnregister(Promise<Object> promise, long j, ChannelReport[] channelReportArr) {
        final Promise<Object> promise2 = promise;
        final long j2 = j;
        final ChannelReport[] channelReportArr2 = channelReportArr;
        closeAll().then(new IAction1<Object>() {
            public void invoke(Object obj) {
                Client.this.doDisconnect(promise2, j2, channelReportArr2);
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                Client.__log.warn("An exception was encountered while closing connections before unregistering.", exc);
                Client.this.doDisconnect(promise2, j2, channelReportArr2);
            }
        });
    }

    public String getApplicationId() {
        return this._applicationId;
    }

    public Channel[] getChannels() {
        ArrayList arrayList = new ArrayList();
        for (Channel add : this.__channels.getValues()) {
            arrayList.add(add);
        }
        return (Channel[]) arrayList.toArray(new Channel[0]);
    }

    public ClientConfig getConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setUserAlias(getUserAlias());
        clientConfig.setDeviceAlias(getDeviceAlias());
        clientConfig.setTag(getTag());
        clientConfig.setRoles(getRoles());
        return clientConfig;
    }

    public String getDeviceAlias() {
        return this.__deviceAlias;
    }

    public String getDeviceId() {
        return this._deviceId;
    }

    public boolean getDisableWebSockets() {
        return this.__gatewayClient.getDisableWebSockets();
    }

    public String getExternalId() {
        return this._externalId;
    }

    public String getGatewayUrl() {
        return this._gatewayUrl;
    }

    public String getId() {
        return this._id;
    }

    public ClientInfo getInfo() {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setApplicationId(getApplicationId());
        clientInfo.setArchitecture(StringExtensions.toLower(Platform.getInstance().getArchitecture().toString()));
        clientInfo.setCoreCount(Platform.getInstance().getCoreCount());
        clientInfo.setDeviceAlias(getDeviceAlias());
        clientInfo.setDeviceId(getDeviceId());
        clientInfo.setExternalId(getExternalId());
        clientInfo.setId(getId());
        clientInfo.setMachineName(Platform.getInstance().getMachineName());
        clientInfo.setOperatingSystem(StringExtensions.toLower(Platform.getInstance().getOperatingSystem().toString()));
        clientInfo.setOperatingSystemVersion(Platform.getInstance().getOperatingSystemVersion());
        clientInfo.setPhysicalMemory(Platform.getInstance().getPhysicalMemory());
        clientInfo.setRegion(getRegion());
        clientInfo.setRoles(getRoles());
        clientInfo.setSourceLanguage(StringExtensions.toLower(Platform.getInstance().getSourceLanguage().toString()));
        clientInfo.setTag(getTag());
        clientInfo.setUserAlias(getUserAlias());
        clientInfo.setUserId(getUserId());
        clientInfo.setVersion(Build.getVersion());
        return clientInfo;
    }

    public String getRegion() {
        return this.__region;
    }

    public int getRequestMaxRetries() {
        return this.__gatewayClient.getRequestMaxRetries();
    }

    public int getRequestTimeout() {
        return this.__gatewayClient.getRequestTimeout();
    }

    public String[] getRoles() {
        return this.__roles;
    }

    public ClientState getState() {
        SignallingClientState state = this.__gatewayClient.getState();
        if (state == SignallingClientState.Connecting) {
            return ClientState.Registering;
        }
        if (state == SignallingClientState.Connected) {
            return ClientState.Registered;
        }
        if (state == SignallingClientState.Disconnecting) {
            return ClientState.Unregistering;
        }
        if (state == SignallingClientState.Disconnected) {
            return ClientState.Unregistered;
        }
        return ClientState.New;
    }

    public String getTag() {
        return this.__tag;
    }

    public Exception getUnregisterException() {
        return this.__gatewayClient.getDisconnectException();
    }

    public String getUserAlias() {
        return this.__userAlias;
    }

    public String getUserId() {
        return this._userId;
    }

    public Future<Channel> join(String str, String str2) {
        Promise promise = new Promise();
        Holder holder = new Holder(null);
        Holder holder2 = new Holder(null);
        boolean validateJoinToken = validateJoinToken(str2, holder, holder2);
        ChannelClaim channelClaim = (ChannelClaim) holder.getValue();
        String str3 = (String) holder2.getValue();
        if (!validateJoinToken) {
            promise.reject(new Exception(str3));
            return promise;
        } else if (!Global.equals(str, channelClaim.getId())) {
            promise.reject(new Exception("Token channel claim ID does not match channel ID."));
            return promise;
        } else {
            doJoin(promise, channelClaim.getId(), str2, ManagedStopwatch.getTimestamp());
            return promise;
        }
    }

    public Future<Channel> join(String str) {
        Promise promise = new Promise();
        Holder holder = new Holder(null);
        Holder holder2 = new Holder(null);
        boolean validateJoinToken = validateJoinToken(str, holder, holder2);
        ChannelClaim channelClaim = (ChannelClaim) holder.getValue();
        String str2 = (String) holder2.getValue();
        if (!validateJoinToken) {
            promise.reject(new Exception(str2));
            return promise;
        }
        doJoin(promise, channelClaim.getId(), str, ManagedStopwatch.getTimestamp());
        return promise;
    }

    public Future<Channel> leave(String str) {
        Promise promise = new Promise();
        doLeave(promise, str, ManagedStopwatch.getTimestamp());
        return promise;
    }

    private void processChannelMessage(Message message) {
        Channel channel;
        String channelId = message.getChannelId();
        synchronized (this.__lock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = this.__channels.tryGetValue(channelId, holder);
            channel = (Channel) holder.getValue();
            if (!tryGetValue) {
                this.__earlyChannelMessages.getOrAdd(channelId, new IFunction1<String, ArrayList<Message>>() {
                    public ArrayList<Message> invoke(String str) {
                        return new ArrayList<>();
                    }
                }).add(message);
            }
        }
        if (channel != null) {
            channel.processMessageAsync(message);
        }
    }

    private void processMessage(Message message) {
        if (message == null) {
            throw new RuntimeException(new Exception("Message is null."));
        } else if (message.getChannelId() != null) {
            processChannelMessage(message);
        } else if (Global.equals(message.getType(), MessageType.getUpdate())) {
            ClientConfig fromJson = ClientConfig.fromJson(message.getPayload());
            ClientInfo clientInfo = new ClientInfo(getUserId(), getUserAlias(), getDeviceId(), getDeviceAlias(), getId(), getTag(), getRoles());
            ClientInfo clientInfo2 = new ClientInfo(getUserId(), fromJson.getUserAlias() != null ? fromJson.getUserAlias() : getUserAlias(), getDeviceId(), fromJson.getDeviceAlias() != null ? fromJson.getDeviceAlias() : getDeviceAlias(), getId(), fromJson.getTag() != null ? fromJson.getTag() : getTag(), fromJson.getRoles() != null ? fromJson.getRoles() : getRoles());
            this.__userAlias = clientInfo2.getUserAlias();
            this.__deviceAlias = clientInfo2.getDeviceAlias();
            this.__tag = clientInfo2.getTag();
            this.__roles = clientInfo2.getRoles();
            this.__region = clientInfo2.getRegion();
            for (Channel updateClientConfig : getChannels()) {
                updateClientConfig.updateClientConfig(fromJson);
            }
            IAction2<ClientInfo, ClientInfo> iAction2 = this._onRemoteUpdate;
            if (iAction2 != null) {
                iAction2.invoke(clientInfo, clientInfo2);
            }
        } else {
            __log.error(getId(), StringExtensions.concat("Unexpected message: ", message.toJson()));
        }
    }

    /* access modifiers changed from: private */
    public void receive(Message message) {
        String str;
        try {
            if (__log.getIsDebugEnabled()) {
                if (message.getPayload() == null) {
                    str = StringExtensions.format("Receiving {0} message for channel {1}.", message.getType(), message.getChannelId());
                } else {
                    str = StringExtensions.format("Receiving {0} message for channel {1}: {2}", message.getType(), message.getChannelId(), message.getPayload());
                }
                __log.debug(getId(), str);
            }
            processMessage(message);
        } catch (Exception e) {
            __log.error(getId(), "Could not process message.", e);
        }
    }

    public Future<Channel[]> register(String str) {
        Promise promise = new Promise();
        Token parse = Token.parse(str);
        if (parse == null) {
            promise.reject(new Exception("Could not parse token."));
            return promise;
        }
        ChannelClaim[] channelClaims = parse.getChannelClaims();
        if (channelClaims != null) {
            for (ChannelClaim id : channelClaims) {
                if (StringExtensions.isNullOrEmpty(id.getId())) {
                    promise.reject(new Exception("Token channel claim is missing ID."));
                    return promise;
                }
            }
        }
        doRegister(promise, str, ManagedStopwatch.getTimestamp());
        return promise;
    }

    /* access modifiers changed from: private */
    public Channel removeChannel(String str) {
        Holder holder = new Holder(null);
        boolean tryRemove = this.__channels.tryRemove(str, holder);
        Channel channel = (Channel) holder.getValue();
        if (!tryRemove) {
            return null;
        }
        channel.leave();
        return channel;
    }

    public void removeOnHttpRequestCreated(IAction1<HttpRequestCreatedArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onHttpRequestCreated, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onHttpRequestCreated.remove(iAction1);
        if (this.__onHttpRequestCreated.size() == 0) {
            this._onHttpRequestCreated = null;
        }
    }

    public void removeOnHttpResponseReceived(IAction1<HttpResponseReceivedArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onHttpResponseReceived, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onHttpResponseReceived.remove(iAction1);
        if (this.__onHttpResponseReceived.size() == 0) {
            this._onHttpResponseReceived = null;
        }
    }

    public void removeOnRemoteUpdate(IAction2<ClientInfo, ClientInfo> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onRemoteUpdate, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onRemoteUpdate.remove(iAction2);
        if (this.__onRemoteUpdate.size() == 0) {
            this._onRemoteUpdate = null;
        }
    }

    public void removeOnStateChange(IAction1<Client> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    private boolean rolesAreEquivalent(String[] strArr, String[] strArr2) {
        if (strArr == null && strArr2 == null) {
            return true;
        }
        if (strArr == null || strArr2 == null || ArrayExtensions.getLength((Object[]) strArr) != ArrayExtensions.getLength((Object[]) strArr2)) {
            return false;
        }
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) strArr); i++) {
            if (!Global.equals(strArr[i], strArr2[i])) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public Future<Message> send(Message message) {
        String str;
        try {
            message.setApplicationId(getApplicationId());
            message.setUserId(getUserId());
            message.setDeviceId(getDeviceId());
            message.setClientId(getId());
            if (__log.getIsDebugEnabled()) {
                if (message.getPayload() == null) {
                    str = StringExtensions.format("Sending {0} message.", (Object) message.getType());
                } else {
                    str = StringExtensions.format("Sending {0} message: {1}", message.getType(), message.getPayload());
                }
                if (Global.equals(message.getType(), MessageType.getEvent())) {
                    __log.verbose(getId(), str);
                } else {
                    __log.debug(getId(), str);
                }
            }
            return this.__sendQueue.send(message);
        } catch (Exception e) {
            Promise promise = new Promise();
            promise.reject(e);
            return promise;
        }
    }

    private void setApplicationId(String str) {
        this._applicationId = str;
    }

    public void setDeviceAlias(String str) {
        if (!this.__registered) {
            this.__deviceAlias = str;
            for (Channel updateClientConfig : getChannels()) {
                ClientConfig clientConfig = new ClientConfig();
                clientConfig.setDeviceAlias(str);
                updateClientConfig.updateClientConfig(clientConfig);
            }
            return;
        }
        throw new RuntimeException(new Exception("Please use the 'Update' method to update this property after registration."));
    }

    private void setDeviceId(String str) {
        this._deviceId = str;
    }

    public void setDisableWebSockets(boolean z) {
        this.__gatewayClient.setDisableWebSockets(z);
    }

    public void setExternalId(String str) {
        this._externalId = str;
    }

    private void setGatewayUrl(String str) {
        this._gatewayUrl = str;
    }

    private void setId(String str) {
        this._id = str;
    }

    public void setRequestMaxRetries(int i) {
        this.__gatewayClient.setRequestMaxRetries(i);
    }

    public void setRequestTimeout(int i) {
        this.__gatewayClient.setRequestTimeout(i);
    }

    public void setTag(String str) {
        if (!this.__registered) {
            this.__tag = str;
            for (Channel updateClientConfig : getChannels()) {
                ClientConfig clientConfig = new ClientConfig();
                clientConfig.setTag(str);
                updateClientConfig.updateClientConfig(clientConfig);
            }
            return;
        }
        throw new RuntimeException(new Exception("Please use the 'Update' method to update this property after registration."));
    }

    public void setUserAlias(String str) {
        if (!this.__registered) {
            this.__userAlias = str;
            for (Channel updateClientConfig : getChannels()) {
                ClientConfig clientConfig = new ClientConfig();
                clientConfig.setUserAlias(str);
                updateClientConfig.updateClientConfig(clientConfig);
            }
            return;
        }
        throw new RuntimeException(new Exception("Please use the 'Update' method to update this property after registration."));
    }

    private void setUserId(String str) {
        this._userId = str;
    }

    public Future<Object> unregister() {
        Promise promise = new Promise();
        doUnregister(promise, ManagedStopwatch.getTimestamp());
        return promise;
    }

    public Future<Object> update(final ClientConfig clientConfig) {
        if (!rolesAreEquivalent(clientConfig.getRoles(), getRoles())) {
            return PromiseBase.rejectNow(new Exception("For security reasons, roles cannot be updated from the SDK. Roles can only be updated with the REST API."));
        }
        boolean z = true;
        boolean z2 = !Global.equals(clientConfig.getUserAlias(), getUserAlias());
        if (!Global.equals(clientConfig.getDeviceAlias(), getDeviceAlias())) {
            z2 = true;
        }
        if (Global.equals(clientConfig.getTag(), getTag())) {
            z = z2;
        }
        if (!z) {
            return PromiseBase.resolveNow(null);
        }
        return send(Message.createUpdateMessage(clientConfig.toJson())).then(new IFunction1<Message, Future<Object>>() {
            public Future<Object> invoke(Message message) {
                String unused = Client.this.__userAlias = clientConfig.getUserAlias();
                String unused2 = Client.this.__deviceAlias = clientConfig.getDeviceAlias();
                String unused3 = Client.this.__tag = clientConfig.getTag();
                for (Channel updateClientConfig : Client.this.getChannels()) {
                    updateClientConfig.updateClientConfig(clientConfig);
                }
                return PromiseBase.resolveNow(null);
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                Client.__log.error(Client.this.getId(), "Could not send update message.", exc);
            }
        });
    }

    private boolean validateJoinToken(String str, Holder<ChannelClaim> holder, Holder<String> holder2) {
        Token parse = Token.parse(str);
        if (parse == null) {
            holder.setValue(null);
            holder2.setValue("Could not parse token.");
            return false;
        }
        holder.setValue(parse.getChannelClaim());
        if (holder.getValue() == null) {
            holder2.setValue("Token is missing channel claim.");
            return false;
        } else if (StringExtensions.isNullOrEmpty(holder.getValue().getId())) {
            holder2.setValue("Token channel claim is missing ID.");
            return false;
        } else {
            holder2.setValue(null);
            return true;
        }
    }
}
