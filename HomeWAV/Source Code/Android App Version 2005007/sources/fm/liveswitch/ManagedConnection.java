package fm.liveswitch;

import com.microsoft.appcenter.utils.PrefStorageConstants;
import fm.liveswitch.dtmf.Tone;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.Message;
import fm.liveswitch.sdp.Origin;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ManagedConnection extends Dynamic {
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(ManagedConnection.class);
    private static int _defaultStatsInterval;
    private IFunction1<Message, Future<Message>> __doSend = null;
    /* access modifiers changed from: private */
    public ConnectionStats __lastStats;
    /* access modifiers changed from: private */
    public Object __lastStatsLock = new Object();
    private Object __lock;
    /* access modifiers changed from: private */
    public DispatchQueue<Message> __messageQueue = new DispatchQueue<>(new IActionDelegate1<Message>() {
        public String getId() {
            return "fm.liveswitch.ManagedConnection.processQueuedMessage";
        }

        public void invoke(Message message) {
            ManagedConnection.this.processQueuedMessage(message);
        }
    });
    /* access modifiers changed from: private */
    public List<IAction1<ManagedConnection>> __onGatheringStateChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<ManagedConnection>> __onIceConnectionStateChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<ManagedConnection, Candidate>> __onLocalCandidate = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<ManagedConnection, SessionDescription>> __onLocalDescription = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<EncodingInfo, EncodingInfo>> __onRemoteAudioEncodingSwitch = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<ManagedConnection, Candidate>> __onRemoteCandidate = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<ManagedConnection, SessionDescription>> __onRemoteDescription = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<ConnectionInfo, ConnectionInfo>> __onRemoteUpdate = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<EncodingInfo, EncodingInfo>> __onRemoteVideoEncodingSwitch = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<ManagedConnection>> __onSignallingStateChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<ManagedConnection>> __onStateChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onStats = new ArrayList();
    private volatile boolean __sendStatsCancelled;
    private TimeoutTimer __sendStatsTimer;
    private String _applicationId;
    private AudioStream _audioStream;
    private String _channelId;
    private String _clientId;
    private DataStream _dataStream;
    private String _deviceId;
    private boolean _disableAutomaticIceServers;
    private Connection _internalConnection;
    private FormatInfo[] _localAudioFormats;
    private FormatInfo[] _localVideoFormats;
    private String _mediaId;
    private IAction1<ManagedConnection> _onGatheringStateChange = null;
    private IAction1<ManagedConnection> _onIceConnectionStateChange = null;
    private IAction2<ManagedConnection, Candidate> _onLocalCandidate = null;
    private IAction2<ManagedConnection, SessionDescription> _onLocalDescription = null;
    private IAction2<EncodingInfo, EncodingInfo> _onRemoteAudioEncodingSwitch = null;
    private IAction2<ManagedConnection, Candidate> _onRemoteCandidate = null;
    private IAction2<ManagedConnection, SessionDescription> _onRemoteDescription = null;
    private IAction2<ConnectionInfo, ConnectionInfo> _onRemoteUpdate = null;
    private IAction2<EncodingInfo, EncodingInfo> _onRemoteVideoEncodingSwitch = null;
    private IAction1<ManagedConnection> _onSignallingStateChange = null;
    private IAction1<ManagedConnection> _onStateChange = null;
    /* access modifiers changed from: private */
    public IAction0 _onStats = null;
    private Promise<Object> _openPromise = null;
    private FormatInfo[] _remoteAudioFormats;
    private boolean _remoteAudioMuted;
    private boolean _remoteClosed;
    private String _remoteConnectionId;
    private boolean _remoteFailed;
    private String _remoteMediaId;
    private boolean _remoteRejected;
    private String _remoteTag;
    private FormatInfo[] _remoteVideoFormats;
    private boolean _remoteVideoMuted;
    private int _statsInterval;
    private String _tag;
    private String _type;
    private String _userId;
    private VideoStream _videoStream;

    /* access modifiers changed from: protected */
    public abstract Message doCreateCandidateMessage(Candidate candidate);

    /* access modifiers changed from: protected */
    public abstract Message doCreateCloseMessage();

    /* access modifiers changed from: protected */
    public abstract void doOpen();

    /* access modifiers changed from: protected */
    public abstract void doProcessMessage(Message message);

    /* access modifiers changed from: protected */
    public abstract boolean isMediaDirectionAllowed(String str);

    /* access modifiers changed from: protected */
    public abstract void processAnswer(Message message);

    public void addOnGatheringStateChange(IAction1<ManagedConnection> iAction1) {
        if (iAction1 != null) {
            if (this._onGatheringStateChange == null) {
                this._onGatheringStateChange = new IAction1<ManagedConnection>() {
                    public void invoke(ManagedConnection managedConnection) {
                        Iterator it = new ArrayList(ManagedConnection.this.__onGatheringStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(managedConnection);
                        }
                    }
                };
            }
            this.__onGatheringStateChange.add(iAction1);
        }
    }

    public void addOnIceConnectionStateChange(IAction1<ManagedConnection> iAction1) {
        if (iAction1 != null) {
            if (this._onIceConnectionStateChange == null) {
                this._onIceConnectionStateChange = new IAction1<ManagedConnection>() {
                    public void invoke(ManagedConnection managedConnection) {
                        Iterator it = new ArrayList(ManagedConnection.this.__onIceConnectionStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(managedConnection);
                        }
                    }
                };
            }
            this.__onIceConnectionStateChange.add(iAction1);
        }
    }

    public void addOnLocalCandidate(IAction2<ManagedConnection, Candidate> iAction2) {
        if (iAction2 != null) {
            if (this._onLocalCandidate == null) {
                this._onLocalCandidate = new IAction2<ManagedConnection, Candidate>() {
                    public void invoke(ManagedConnection managedConnection, Candidate candidate) {
                        Iterator it = new ArrayList(ManagedConnection.this.__onLocalCandidate).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(managedConnection, candidate);
                        }
                    }
                };
            }
            this.__onLocalCandidate.add(iAction2);
        }
    }

    public void addOnLocalDescription(IAction2<ManagedConnection, SessionDescription> iAction2) {
        if (iAction2 != null) {
            if (this._onLocalDescription == null) {
                this._onLocalDescription = new IAction2<ManagedConnection, SessionDescription>() {
                    public void invoke(ManagedConnection managedConnection, SessionDescription sessionDescription) {
                        Iterator it = new ArrayList(ManagedConnection.this.__onLocalDescription).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(managedConnection, sessionDescription);
                        }
                    }
                };
            }
            this.__onLocalDescription.add(iAction2);
        }
    }

    public void addOnRemoteAudioEncodingSwitch(IAction2<EncodingInfo, EncodingInfo> iAction2) {
        if (iAction2 != null) {
            if (this._onRemoteAudioEncodingSwitch == null) {
                this._onRemoteAudioEncodingSwitch = new IAction2<EncodingInfo, EncodingInfo>() {
                    public void invoke(EncodingInfo encodingInfo, EncodingInfo encodingInfo2) {
                        Iterator it = new ArrayList(ManagedConnection.this.__onRemoteAudioEncodingSwitch).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(encodingInfo, encodingInfo2);
                        }
                    }
                };
            }
            this.__onRemoteAudioEncodingSwitch.add(iAction2);
        }
    }

    public void addOnRemoteCandidate(IAction2<ManagedConnection, Candidate> iAction2) {
        if (iAction2 != null) {
            if (this._onRemoteCandidate == null) {
                this._onRemoteCandidate = new IAction2<ManagedConnection, Candidate>() {
                    public void invoke(ManagedConnection managedConnection, Candidate candidate) {
                        Iterator it = new ArrayList(ManagedConnection.this.__onRemoteCandidate).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(managedConnection, candidate);
                        }
                    }
                };
            }
            this.__onRemoteCandidate.add(iAction2);
        }
    }

    public void addOnRemoteDescription(IAction2<ManagedConnection, SessionDescription> iAction2) {
        if (iAction2 != null) {
            if (this._onRemoteDescription == null) {
                this._onRemoteDescription = new IAction2<ManagedConnection, SessionDescription>() {
                    public void invoke(ManagedConnection managedConnection, SessionDescription sessionDescription) {
                        Iterator it = new ArrayList(ManagedConnection.this.__onRemoteDescription).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(managedConnection, sessionDescription);
                        }
                    }
                };
            }
            this.__onRemoteDescription.add(iAction2);
        }
    }

    public void addOnRemoteUpdate(IAction2<ConnectionInfo, ConnectionInfo> iAction2) {
        if (iAction2 != null) {
            if (this._onRemoteUpdate == null) {
                this._onRemoteUpdate = new IAction2<ConnectionInfo, ConnectionInfo>() {
                    public void invoke(ConnectionInfo connectionInfo, ConnectionInfo connectionInfo2) {
                        Iterator it = new ArrayList(ManagedConnection.this.__onRemoteUpdate).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(connectionInfo, connectionInfo2);
                        }
                    }
                };
            }
            this.__onRemoteUpdate.add(iAction2);
        }
    }

    public void addOnRemoteVideoEncodingSwitch(IAction2<EncodingInfo, EncodingInfo> iAction2) {
        if (iAction2 != null) {
            if (this._onRemoteVideoEncodingSwitch == null) {
                this._onRemoteVideoEncodingSwitch = new IAction2<EncodingInfo, EncodingInfo>() {
                    public void invoke(EncodingInfo encodingInfo, EncodingInfo encodingInfo2) {
                        Iterator it = new ArrayList(ManagedConnection.this.__onRemoteVideoEncodingSwitch).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(encodingInfo, encodingInfo2);
                        }
                    }
                };
            }
            this.__onRemoteVideoEncodingSwitch.add(iAction2);
        }
    }

    public void addOnSignallingStateChange(IAction1<ManagedConnection> iAction1) {
        if (iAction1 != null) {
            if (this._onSignallingStateChange == null) {
                this._onSignallingStateChange = new IAction1<ManagedConnection>() {
                    public void invoke(ManagedConnection managedConnection) {
                        Iterator it = new ArrayList(ManagedConnection.this.__onSignallingStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(managedConnection);
                        }
                    }
                };
            }
            this.__onSignallingStateChange.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<ManagedConnection> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<ManagedConnection>() {
                    public void invoke(ManagedConnection managedConnection) {
                        Iterator it = new ArrayList(ManagedConnection.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(managedConnection);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    /* access modifiers changed from: package-private */
    public void addOnStats(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onStats == null) {
                this._onStats = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(ManagedConnection.this.__onStats).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onStats.add(iAction0);
        }
    }

    /* access modifiers changed from: private */
    public void audioStream_OnDiscardOutboundDtmfTones(Tone[] toneArr) {
        send(Message.createDtmfTonesMessage(toneArr));
    }

    private void cancelSendStats() {
        this.__sendStatsCancelled = true;
        TimeoutTimer timeoutTimer = this.__sendStatsTimer;
        if (timeoutTimer != null) {
            timeoutTimer.stop();
        }
    }

    public Future<Object> close() {
        Promise promise = new Promise();
        doClose(promise);
        return promise;
    }

    private Message createCandidateMessage(Candidate candidate) {
        return doCreateCandidateMessage(candidate);
    }

    private Message createCloseMessage() {
        return doCreateCloseMessage();
    }

    private void doClose(final Promise<Object> promise) {
        try {
            if (!Global.equals(getInternalConnection().getState(), ConnectionState.Closing) && !Global.equals(getInternalConnection().getState(), ConnectionState.Closed) && !Global.equals(getInternalConnection().getState(), ConnectionState.Failing)) {
                if (!Global.equals(getInternalConnection().getState(), ConnectionState.Failed)) {
                    __log.debug(StringExtensions.format("Closing connection with ID '{0}'.", (Object) getId()));
                    if (getInternalConnection().close()) {
                        send(createCloseMessage()).then(new IAction1<Message>() {
                            public void invoke(Message message) {
                                ManagedConnection.this.__messageQueue.destroy();
                                promise.resolve(null);
                            }
                        }, (IAction1<Exception>) new IAction1<Exception>() {
                            public void invoke(Exception exc) {
                                ManagedConnection.__log.debug("Could not send close message.", exc);
                                ManagedConnection.this.__messageQueue.destroy();
                                promise.resolve(null);
                            }
                        });
                        return;
                    }
                    this.__messageQueue.destroy();
                    promise.resolve(null);
                    return;
                }
            }
            this.__messageQueue.destroy();
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    public Message doCreateUpdateMessage(ConnectionConfig connectionConfig) {
        return Message.createUpdateMessage(connectionConfig.toJson());
    }

    private void doSelfNegotiate(final SessionDescription sessionDescription, final Promise<Object> promise) {
        getInternalConnection().createOffer().then(new IAction1<SessionDescription>() {
            public void invoke(SessionDescription sessionDescription) {
                ManagedConnection.this.getInternalConnection().setLocalDescription(sessionDescription).then(new IAction1<SessionDescription>() {
                    public void invoke(SessionDescription sessionDescription) {
                        ManagedConnection.this.getInternalConnection().setRemoteDescription(sessionDescription).then(new IAction1<SessionDescription>() {
                            public void invoke(SessionDescription sessionDescription) {
                                promise.resolve(null);
                            }
                        }, (IAction1<Exception>) new IAction1<Exception>() {
                            public void invoke(Exception exc) {
                                promise.reject(exc);
                            }
                        });
                    }
                }, (IAction1<Exception>) new IAction1<Exception>() {
                    public void invoke(Exception exc) {
                        promise.reject(exc);
                    }
                });
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                promise.reject(exc);
            }
        });
    }

    private void doSendConnectionEvent(final Promise<Object> promise, final EventInfo eventInfo) {
        if (Global.equals(eventInfo.getType(), EventType.ConnectionStats)) {
            getInternalConnection().getStats().then(new IAction1<ConnectionStats>() {
                public void invoke(ConnectionStats connectionStats) {
                    synchronized (ManagedConnection.this.__lastStatsLock) {
                        eventInfo.getConnection().populateInfo(connectionStats, ManagedConnection.this.__lastStats, eventInfo.getType());
                        ConnectionStats unused = ManagedConnection.this.__lastStats = connectionStats;
                        IAction0 access$1600 = ManagedConnection.this._onStats;
                        if (access$1600 != null) {
                            access$1600.invoke();
                        }
                    }
                    ManagedConnection.this.sendConnectionEventMessage(promise, eventInfo);
                }
            }, (IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    promise.reject(exc);
                }
            });
            return;
        }
        sendConnectionEventMessage(promise, eventInfo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x011a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doUpdate(final fm.liveswitch.ConnectionConfig r7, final fm.liveswitch.Promise<java.lang.Object> r8) {
        /*
            r6 = this;
            fm.liveswitch.AudioStream r0 = r6.getAudioStream()
            if (r0 == 0) goto L_0x001e
            java.lang.String r0 = r7.getAudioDirection()
            java.lang.String r1 = r6.getAudioDirection()
            boolean r0 = fm.liveswitch.Global.equals(r0, r1)
            if (r0 != 0) goto L_0x001e
            java.lang.String r0 = r7.getAudioDirection()
            boolean r0 = r6.isMediaDirectionAllowed(r0)
            if (r0 == 0) goto L_0x003d
        L_0x001e:
            fm.liveswitch.VideoStream r0 = r6.getVideoStream()
            if (r0 == 0) goto L_0x005e
            java.lang.String r0 = r7.getVideoDirection()
            java.lang.String r1 = r6.getVideoDirection()
            boolean r0 = fm.liveswitch.Global.equals(r0, r1)
            if (r0 != 0) goto L_0x005e
            java.lang.String r0 = r7.getVideoDirection()
            boolean r0 = r6.isMediaDirectionAllowed(r0)
            if (r0 == 0) goto L_0x003d
            goto L_0x005e
        L_0x003d:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.Exception r0 = new java.lang.Exception
            java.lang.String r1 = r6.getType()
            java.lang.String r1 = fm.liveswitch.StringExtensions.toUpper(r1)
            java.lang.String r2 = r6.getId()
            java.lang.String r7 = r7.getAudioDirection()
            java.lang.String r3 = "{0} connection {1} does not allow the '{2}' direction."
            java.lang.String r7 = fm.liveswitch.StringExtensions.format(r3, r1, r2, r7)
            r0.<init>(r7)
            r8.<init>(r0)
            throw r8
        L_0x005e:
            r0 = 0
            java.lang.String r1 = r7.getTag()
            java.lang.String r2 = r6.getTag()
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)
            r2 = 1
            r1 = r1 ^ r2
            fm.liveswitch.AudioStream r3 = r6.getAudioStream()
            r4 = 0
            if (r3 == 0) goto L_0x00b3
            boolean r0 = r7.getLocalAudioMuted()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            fm.liveswitch.AudioStream r3 = r6.getAudioStream()
            boolean r3 = r3.getInputMuted()
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 != 0) goto L_0x008f
            r1 = 1
        L_0x008f:
            java.lang.String r0 = r7.getAudioDirection()
            java.lang.String r3 = r6.getAudioDirection()
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            r0 = r0 ^ r2
            fm.liveswitch.EncodingInfo r3 = r7.getRemoteAudioEncoding()
            fm.liveswitch.AudioStream r5 = r6.getAudioStream()
            fm.liveswitch.EncodingInfo r5 = r5.getRemoteEncoding()
            boolean r3 = fm.liveswitch.Global.equals(r3, r5)
            if (r3 != 0) goto L_0x00b0
            r1 = 1
            goto L_0x00b3
        L_0x00b0:
            r7.setRemoteAudioEncoding(r4)
        L_0x00b3:
            fm.liveswitch.VideoStream r3 = r6.getVideoStream()
            if (r3 == 0) goto L_0x00f9
            boolean r3 = r7.getLocalVideoMuted()
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            fm.liveswitch.VideoStream r5 = r6.getVideoStream()
            boolean r5 = r5.getInputMuted()
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            boolean r3 = fm.liveswitch.Global.equals(r3, r5)
            if (r3 != 0) goto L_0x00d4
            r1 = 1
        L_0x00d4:
            java.lang.String r3 = r7.getVideoDirection()
            java.lang.String r5 = r6.getVideoDirection()
            boolean r3 = fm.liveswitch.Global.equals(r3, r5)
            if (r3 != 0) goto L_0x00e3
            r0 = 1
        L_0x00e3:
            fm.liveswitch.EncodingInfo r3 = r7.getRemoteVideoEncoding()
            fm.liveswitch.VideoStream r5 = r6.getVideoStream()
            fm.liveswitch.EncodingInfo r5 = r5.getRemoteEncoding()
            boolean r3 = fm.liveswitch.Global.equals(r3, r5)
            if (r3 != 0) goto L_0x00f6
            goto L_0x00fa
        L_0x00f6:
            r7.setRemoteVideoEncoding(r4)
        L_0x00f9:
            r2 = r1
        L_0x00fa:
            if (r2 != 0) goto L_0x0103
            if (r0 == 0) goto L_0x00ff
            goto L_0x0103
        L_0x00ff:
            r8.resolve(r4)
            goto L_0x012b
        L_0x0103:
            if (r0 == 0) goto L_0x011a
            java.lang.String r0 = r7.getAudioDirection()
            fm.liveswitch.StreamDirection r0 = fm.liveswitch.StreamDirectionHelper.directionFromString(r0)
            java.lang.String r1 = r7.getVideoDirection()
            fm.liveswitch.StreamDirection r1 = fm.liveswitch.StreamDirectionHelper.directionFromString(r1)
            fm.liveswitch.Future r0 = r6.selfRenegotiate(r0, r1)
            goto L_0x011e
        L_0x011a:
            fm.liveswitch.Future r0 = fm.liveswitch.PromiseBase.resolveNow(r4)
        L_0x011e:
            fm.liveswitch.ManagedConnection$19 r1 = new fm.liveswitch.ManagedConnection$19
            r1.<init>(r7, r8)
            fm.liveswitch.ManagedConnection$20 r7 = new fm.liveswitch.ManagedConnection$20
            r7.<init>(r8)
            r0.then(r1, (fm.liveswitch.IAction1<java.lang.Exception>) r7)
        L_0x012b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.ManagedConnection.doUpdate(fm.liveswitch.ConnectionConfig, fm.liveswitch.Promise):void");
    }

    private void doUpdateConnection(final ConnectionInfo connectionInfo, final ConnectionInfo connectionInfo2, final Promise<Object> promise) {
        Future<Object> future;
        String str;
        String str2 = "muted";
        if (!Global.equals(Boolean.valueOf(getRemoteAudioMuted()), Boolean.valueOf(connectionInfo2.getLocalAudioMuted()))) {
            ILog iLog = __log;
            String id = getId();
            if (connectionInfo2.getLocalAudioMuted()) {
                str = str2;
            } else {
                str = "unmuted";
            }
            iLog.info(StringExtensions.format("Remote connection {0} has {1} their audio stream.", id, str));
            setRemoteAudioMuted(connectionInfo2.getLocalAudioMuted());
        }
        if (!Global.equals(Boolean.valueOf(getRemoteVideoMuted()), Boolean.valueOf(connectionInfo2.getLocalVideoMuted()))) {
            ILog iLog2 = __log;
            String id2 = getId();
            if (!connectionInfo2.getLocalVideoMuted()) {
                str2 = "unmuted";
            }
            iLog2.info(StringExtensions.format("Remote connection {0} has {1} their video stream.", id2, str2));
            setRemoteVideoMuted(connectionInfo2.getLocalVideoMuted());
        }
        StreamDirection directionFromString = StreamDirectionHelper.directionFromString(getAudioDirection());
        StreamDirection directionFromString2 = StreamDirectionHelper.directionFromString(getVideoDirection());
        boolean z = true;
        boolean z2 = false;
        if (Global.equals(getType(), ConnectionType.getPeer())) {
            String str3 = "disabled";
            if (!Global.equals(Boolean.valueOf(StreamDirectionHelper.isReceiveDisabled(getAudioDirection())), Boolean.valueOf(StreamDirectionHelper.isSendDisabled(connectionInfo2.getAudioDirection())))) {
                directionFromString = StreamDirectionHelper.setReceiveDisabled(directionFromString, StreamDirectionHelper.isSendDisabled(connectionInfo2.getAudioDirection()));
                __log.info(StringExtensions.format("Remote connection {0} has {1} their audio stream.", getId(), StreamDirectionHelper.isSendDisabled(connectionInfo2.getAudioDirection()) ? str3 : PrefStorageConstants.KEY_ENABLED));
                z2 = true;
            }
            if (!Global.equals(Boolean.valueOf(StreamDirectionHelper.isReceiveDisabled(getVideoDirection())), Boolean.valueOf(StreamDirectionHelper.isSendDisabled(connectionInfo2.getVideoDirection())))) {
                directionFromString2 = StreamDirectionHelper.setReceiveDisabled(directionFromString2, StreamDirectionHelper.isSendDisabled(connectionInfo2.getVideoDirection()));
                __log.info(StringExtensions.format("Remote connection {0} has {1} their video stream.", getId(), StreamDirectionHelper.isSendDisabled(connectionInfo2.getVideoDirection()) ? str3 : PrefStorageConstants.KEY_ENABLED));
                z2 = true;
            }
            if (!Global.equals(Boolean.valueOf(StreamDirectionHelper.isSendDisabled(getAudioDirection())), Boolean.valueOf(StreamDirectionHelper.isReceiveDisabled(connectionInfo2.getAudioDirection())))) {
                directionFromString = StreamDirectionHelper.setSendDisabled(directionFromString, StreamDirectionHelper.isReceiveDisabled(connectionInfo2.getAudioDirection()));
                __log.info(StringExtensions.format("Remote connection {0} has {1} your audio stream.", getId(), StreamDirectionHelper.isReceiveDisabled(connectionInfo2.getAudioDirection()) ? str3 : PrefStorageConstants.KEY_ENABLED));
                z2 = true;
            }
            if (!Global.equals(Boolean.valueOf(StreamDirectionHelper.isSendDisabled(getVideoDirection())), Boolean.valueOf(StreamDirectionHelper.isReceiveDisabled(connectionInfo2.getVideoDirection())))) {
                directionFromString2 = StreamDirectionHelper.setSendDisabled(directionFromString2, StreamDirectionHelper.isReceiveDisabled(connectionInfo2.getVideoDirection()));
                ILog iLog3 = __log;
                String id3 = getId();
                if (!StreamDirectionHelper.isReceiveDisabled(connectionInfo2.getVideoDirection())) {
                    str3 = PrefStorageConstants.KEY_ENABLED;
                }
                iLog3.info(StringExtensions.format("Remote connection {0} has {1} your video stream.", id3, str3));
            } else {
                z = z2;
            }
        } else {
            z = false;
        }
        if (z) {
            __log.debug(StringExtensions.format("Doing self-renegotiation for connection {0}", (Object) getId()));
            future = selfRenegotiate(directionFromString, directionFromString2);
        } else {
            future = PromiseBase.resolveNow(null);
        }
        future.then((IAction1<Object>) new IAction1<Object>() {
            public void invoke(Object obj) {
                ManagedConnection.this.raiseRemoteUpdate(connectionInfo, connectionInfo2);
                promise.resolve(null);
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

    public String getAudioDirection() {
        if (getAudioStream() != null) {
            return StreamDirectionHelper.directionToString(getAudioStream().getDirection());
        }
        return null;
    }

    public AudioStream getAudioStream() {
        return this._audioStream;
    }

    public BundlePolicy getBundlePolicy() {
        return getInternalConnection().getBundlePolicy();
    }

    public String getCanonicalName() {
        return getInternalConnection().getCanonicalName();
    }

    public String getChannelId() {
        return this._channelId;
    }

    public String getClientId() {
        return this._clientId;
    }

    public ConnectionConfig getConfig() {
        ConnectionConfig connectionConfig = new ConnectionConfig();
        connectionConfig.setTag(getTag());
        connectionConfig.setLocalAudioMuted(getLocalAudioMuted());
        connectionConfig.setLocalVideoMuted(getLocalVideoMuted());
        connectionConfig.setLocalAudioDisabled(getLocalAudioDisabled());
        connectionConfig.setLocalVideoDisabled(getLocalVideoDisabled());
        connectionConfig.setRemoteAudioDisabled(getRemoteAudioDisabled());
        connectionConfig.setRemoteVideoDisabled(getRemoteVideoDisabled());
        connectionConfig.setRemoteAudioEncoding(getRemoteAudioEncoding());
        connectionConfig.setRemoteVideoEncoding(getRemoteVideoEncoding());
        return connectionConfig;
    }

    public IFunction1<DatagramSocketCreateArgs, DatagramSocket> getCreateDatagramSocket() {
        return getInternalConnection().getCreateDatagramSocket();
    }

    public IFunction1<StreamSocketCreateArgs, StreamSocket> getCreateStreamSocket() {
        return getInternalConnection().getCreateStreamSocket();
    }

    public String getDataDirection() {
        if (getDataStream() != null) {
            return StreamDirectionHelper.directionToString(getDataStream().getDirection());
        }
        return null;
    }

    public DataStream getDataStream() {
        return this._dataStream;
    }

    public static DtlsCertificate getDefaultLocalDtlsCertificate() {
        return Connection.getDefaultLocalDtlsCertificate();
    }

    public static DtlsCertificate[] getDefaultLocalDtlsCertificates() {
        return Connection.getDefaultLocalDtlsCertificates();
    }

    public static int getDefaultStatsInterval() {
        return _defaultStatsInterval;
    }

    public String getDeviceId() {
        return this._deviceId;
    }

    public boolean getDisableAutomaticIceServers() {
        return this._disableAutomaticIceServers;
    }

    public DtlsCipherSuite[] getDtlsCipherSuites() {
        return getInternalConnection().getDtlsCipherSuites();
    }

    public DtlsProtocolVersion getDtlsClientVersion() {
        return getInternalConnection().getDtlsClientVersion();
    }

    public DtlsProtocolVersion getDtlsServerMaxVersion() {
        return getInternalConnection().getDtlsServerMaxVersion();
    }

    public DtlsProtocolVersion getDtlsServerMinVersion() {
        return getInternalConnection().getDtlsServerMinVersion();
    }

    public Error getError() {
        return getInternalConnection().getError();
    }

    public String getExternalId() {
        return getInternalConnection().getExternalId();
    }

    public IceGatheringState getGatheringState() {
        return getInternalConnection().getGatheringState();
    }

    public boolean getHasAudio() {
        return getAudioStream() != null;
    }

    public boolean getHasData() {
        return getDataStream() != null;
    }

    public boolean getHasVideo() {
        return getVideoStream() != null;
    }

    public AddressType[] getIceAddressTypes() {
        return getInternalConnection().getIceAddressTypes();
    }

    public IceConnectionState getIceConnectionState() {
        return getInternalConnection().getIceConnectionState();
    }

    public IceGatherPolicy getIceGatherPolicy() {
        return getInternalConnection().getIceGatherPolicy();
    }

    public IcePolicy getIcePolicy() {
        return getInternalConnection().getIcePolicy();
    }

    public IcePortRange getIcePortRange() {
        return getInternalConnection().getIcePortRange();
    }

    public IceRole getIceRole() {
        return getInternalConnection().getIceRole();
    }

    public IceServer getIceServer() {
        return getInternalConnection().getIceServer();
    }

    public IceServer[] getIceServers() {
        return getInternalConnection().getIceServers();
    }

    public String getId() {
        return getInternalConnection().getId();
    }

    public ConnectionInfo getInfo() {
        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setId(getId());
        connectionInfo.setExternalId(getExternalId());
        connectionInfo.setState(StringExtensions.toLower(getState().toString()));
        connectionInfo.setTag(getTag());
        connectionInfo.setType(getType());
        connectionInfo.setError(getError());
        connectionInfo.setMediaId(getMediaId());
        connectionInfo.setRemoteMediaId(getRemoteMediaId());
        connectionInfo.setRemoteConnectionId(getRemoteConnectionId());
        connectionInfo.setApplicationId(getApplicationId());
        connectionInfo.setChannelId(getChannelId());
        connectionInfo.setClientId(getClientId());
        connectionInfo.setClientTag((String) null);
        connectionInfo.setClientRoles((String[]) null);
        connectionInfo.setUserId(getUserId());
        connectionInfo.setUserAlias((String) null);
        connectionInfo.setDeviceId(getDeviceId());
        connectionInfo.setDeviceAlias((String) null);
        connectionInfo.setRecording(false);
        connectionInfo.setTransports(getTransportInfos());
        if (getAudioStream() != null) {
            connectionInfo.setAudioStreams(new MediaStreamInfo[]{getAudioStream().getInfo()});
        }
        if (getVideoStream() != null) {
            connectionInfo.setVideoStreams(new MediaStreamInfo[]{getVideoStream().getInfo()});
        }
        if (getDataStream() != null) {
            connectionInfo.setDataStream(getDataStream().getInfo());
        }
        return connectionInfo;
    }

    /* access modifiers changed from: package-private */
    public Connection getInternalConnection() {
        return this._internalConnection;
    }

    public int getKeepAliveInterval() {
        return getInternalConnection().getKeepAliveInterval();
    }

    /* access modifiers changed from: package-private */
    public ConnectionStats getLastStats() {
        ConnectionStats connectionStats;
        synchronized (this.__lastStatsLock) {
            connectionStats = this.__lastStats;
        }
        return connectionStats;
    }

    public boolean getLocalAudioDisabled() {
        return StreamDirectionHelper.isSendDisabled(getAudioDirection());
    }

    public FormatInfo[] getLocalAudioFormats() {
        return this._localAudioFormats;
    }

    public boolean getLocalAudioMuted() {
        if (getAudioStream() == null) {
            return false;
        }
        return getAudioStream().getInputMuted();
    }

    public boolean getLocalDataDisabled() {
        return StreamDirectionHelper.isSendDisabled(getDataDirection());
    }

    public SessionDescription getLocalDescription() {
        return getInternalConnection().getLocalDescription();
    }

    public DtlsCertificate getLocalDtlsCertificate() {
        return getInternalConnection().getLocalDtlsCertificate();
    }

    public DtlsCertificate[] getLocalDtlsCertificates() {
        return getInternalConnection().getLocalDtlsCertificates();
    }

    public boolean getLocalVideoDisabled() {
        return StreamDirectionHelper.isSendDisabled(getVideoDirection());
    }

    public FormatInfo[] getLocalVideoFormats() {
        return this._localVideoFormats;
    }

    public boolean getLocalVideoMuted() {
        if (getVideoStream() == null) {
            return false;
        }
        return getVideoStream().getInputMuted();
    }

    public String getMediaId() {
        return this._mediaId;
    }

    public MultiplexPolicy getMultiplexPolicy() {
        return getInternalConnection().getMultiplexPolicy();
    }

    public String getPrivateIPAddress() {
        return getInternalConnection().getPrivateIPAddress();
    }

    public String[] getPrivateIPAddresses() {
        return getInternalConnection().getPrivateIPAddresses();
    }

    public boolean getRemoteAudioDisabled() {
        return StreamDirectionHelper.isReceiveDisabled(getAudioDirection());
    }

    public EncodingInfo getRemoteAudioEncoding() {
        if (getAudioStream() != null) {
            return getAudioStream().getRemoteEncoding();
        }
        return null;
    }

    public FormatInfo[] getRemoteAudioFormats() {
        return this._remoteAudioFormats;
    }

    public boolean getRemoteAudioMuted() {
        return this._remoteAudioMuted;
    }

    public boolean getRemoteClosed() {
        return this._remoteClosed;
    }

    public String getRemoteConnectionId() {
        return this._remoteConnectionId;
    }

    public boolean getRemoteDataDisabled() {
        return StreamDirectionHelper.isReceiveDisabled(getDataDirection());
    }

    public SessionDescription getRemoteDescription() {
        return getInternalConnection().getRemoteDescription();
    }

    /* access modifiers changed from: package-private */
    public boolean getRemoteFailed() {
        return this._remoteFailed;
    }

    public String getRemoteMediaId() {
        return this._remoteMediaId;
    }

    public boolean getRemoteRejected() {
        return this._remoteRejected;
    }

    public String getRemoteTag() {
        return this._remoteTag;
    }

    public boolean getRemoteVideoDisabled() {
        return StreamDirectionHelper.isReceiveDisabled(getVideoDirection());
    }

    public EncodingInfo getRemoteVideoEncoding() {
        if (getVideoStream() != null) {
            return getVideoStream().getRemoteEncoding();
        }
        return null;
    }

    public FormatInfo[] getRemoteVideoFormats() {
        return this._remoteVideoFormats;
    }

    public boolean getRemoteVideoMuted() {
        return this._remoteVideoMuted;
    }

    public SignallingState getSignallingState() {
        return getInternalConnection().getSignallingState();
    }

    public ConnectionState getState() {
        ConnectionState state = getInternalConnection().getState();
        if (!getRemoteFailed()) {
            return state;
        }
        if (state == ConnectionState.Closing) {
            return ConnectionState.Failing;
        }
        return state == ConnectionState.Closed ? ConnectionState.Failed : state;
    }

    public Future<ConnectionStats> getStats() {
        return getInternalConnection().getStats();
    }

    public int getStatsInterval() {
        return this._statsInterval;
    }

    public int getStunBindingRequestLimit() {
        return getInternalConnection().getStunBindingRequestLimit();
    }

    public int getStunRequestTimeout() {
        return getInternalConnection().getStunRequestTimeout();
    }

    public String getTag() {
        return this._tag;
    }

    public int getTcpConnectTimeout() {
        return getInternalConnection().getTcpConnectTimeout();
    }

    public IFunction1<DataBuffer, DataBuffer> getTestReceivedRtpBuffer() {
        return getInternalConnection().getTestReceivedRtpBuffer();
    }

    public int getTestRoundTripTime() {
        return getInternalConnection().getTestRoundTripTime();
    }

    public IFunction1<DataBuffer, DataBuffer> getTestSendingRtpBuffer() {
        return getInternalConnection().getTestSendingRtpBuffer();
    }

    private TransportInfo[] getTransportInfos() {
        TransportInfo transportInfo;
        ArrayList arrayList = new ArrayList();
        if (getAudioStream() != null) {
            TransportInfo transportInfo2 = getAudioStream().getTransportInfo();
            if (transportInfo2 != null && !arrayList.contains(transportInfo2)) {
                arrayList.add(transportInfo2);
            }
            TransportInfo controlTransportInfo = getAudioStream().getControlTransportInfo();
            if (controlTransportInfo != null && !arrayList.contains(controlTransportInfo)) {
                arrayList.add(controlTransportInfo);
            }
        }
        if (getVideoStream() != null) {
            TransportInfo transportInfo3 = getVideoStream().getTransportInfo();
            if (transportInfo3 != null && !arrayList.contains(transportInfo3)) {
                arrayList.add(transportInfo3);
            }
            TransportInfo controlTransportInfo2 = getVideoStream().getControlTransportInfo();
            if (controlTransportInfo2 != null && !arrayList.contains(controlTransportInfo2)) {
                arrayList.add(controlTransportInfo2);
            }
        }
        if (!(getDataStream() == null || (transportInfo = getDataStream().getTransportInfo()) == null || arrayList.contains(transportInfo))) {
            arrayList.add(transportInfo);
        }
        return (TransportInfo[]) arrayList.toArray(new TransportInfo[0]);
    }

    public TrickleIcePolicy getTrickleIcePolicy() {
        return getInternalConnection().getTrickleIcePolicy();
    }

    public int getTurnAllocateRequestLimit() {
        return getInternalConnection().getTurnAllocateRequestLimit();
    }

    public String getType() {
        return this._type;
    }

    public String getUserId() {
        return this._userId;
    }

    public String getVideoDirection() {
        if (getVideoStream() != null) {
            return StreamDirectionHelper.directionToString(getVideoStream().getDirection());
        }
        return null;
    }

    public VideoStream getVideoStream() {
        return this._videoStream;
    }

    /* access modifiers changed from: private */
    public void internalConnection_OnGatheringStateChange(Connection connection) {
        synchronized (this.__lock) {
            IAction1<ManagedConnection> iAction1 = this._onGatheringStateChange;
            if (iAction1 != null) {
                iAction1.invoke(this);
            }
        }
    }

    /* access modifiers changed from: private */
    public void internalConnection_OnIceConnectionStateChange(Connection connection) {
        synchronized (this.__lock) {
            IAction1<ManagedConnection> iAction1 = this._onIceConnectionStateChange;
            if (iAction1 != null) {
                iAction1.invoke(this);
            }
        }
    }

    /* access modifiers changed from: private */
    public void internalConnection_OnLocalCandidate(Connection connection, Candidate candidate) {
        IAction2<ManagedConnection, Candidate> iAction2 = this._onLocalCandidate;
        if (!Global.equals(candidate.getProtocol(), ProtocolType.Tcp)) {
            if (iAction2 != null) {
                iAction2.invoke(this, candidate);
            }
            send(createCandidateMessage(candidate)).fail((IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    ManagedConnection.__log.error("Could not send candidate message.", exc);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void internalConnection_OnLocalDescription(Connection connection, SessionDescription sessionDescription) {
        IAction2<ManagedConnection, SessionDescription> iAction2 = this._onLocalDescription;
        if (iAction2 != null) {
            iAction2.invoke(this, sessionDescription);
        }
    }

    /* access modifiers changed from: private */
    public void internalConnection_OnRemoteCandidate(Connection connection, Candidate candidate) {
        IAction2<ManagedConnection, Candidate> iAction2 = this._onRemoteCandidate;
        if (iAction2 != null) {
            iAction2.invoke(this, candidate);
        }
    }

    /* access modifiers changed from: private */
    public void internalConnection_OnRemoteDescription(Connection connection, SessionDescription sessionDescription) {
        IAction2<ManagedConnection, SessionDescription> iAction2 = this._onRemoteDescription;
        if (iAction2 != null) {
            iAction2.invoke(this, sessionDescription);
        }
    }

    /* access modifiers changed from: private */
    public void internalConnection_OnSignallingStateChange(Connection connection) {
        synchronized (this.__lock) {
            IAction1<ManagedConnection> iAction1 = this._onSignallingStateChange;
            if (iAction1 != null) {
                iAction1.invoke(this);
            }
        }
    }

    /* access modifiers changed from: private */
    public void internalConnection_OnStateChange(Connection connection) {
        processStateChangeEvent(connection.getState());
        synchronized (this.__lock) {
            if (this._openPromise != null) {
                if (Global.equals(connection.getState(), ConnectionState.Connected)) {
                    Promise<Object> promise = this._openPromise;
                    this._openPromise = null;
                    promise.resolveAsync(null);
                } else if (Global.equals(connection.getState(), ConnectionState.Failed)) {
                    Promise<Object> promise2 = this._openPromise;
                    this._openPromise = null;
                    if (connection.getError() == null) {
                        promise2.rejectAsync(new Exception("Connection failed."));
                    } else {
                        promise2.rejectAsync(new Exception(StringExtensions.format("Connection failed. ({0})", (Object) connection.getError().getDescription())));
                    }
                } else if (Global.equals(connection.getState(), ConnectionState.Closed)) {
                    Promise<Object> promise3 = this._openPromise;
                    this._openPromise = null;
                    if (connection.getError() == null) {
                        promise3.rejectAsync(new Exception("Connection closed."));
                    } else {
                        promise3.rejectAsync(new Exception(StringExtensions.format("Connection closed. ({0})", (Object) connection.getError().getDescription())));
                    }
                }
            }
            IAction1<ManagedConnection> iAction1 = this._onStateChange;
            if (iAction1 != null) {
                iAction1.invoke(this);
            }
        }
    }

    static {
        setDefaultStatsInterval(10000);
    }

    ManagedConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, String str6, AudioStream audioStream, VideoStream videoStream, DataStream dataStream, String str7) {
        this.__lock = obj;
        setApplicationId(str);
        setChannelId(str2);
        setUserId(str3);
        setDeviceId(str4);
        setClientId(str5);
        setMediaId(str7);
        this.__doSend = iFunction1;
        setType(str6);
        setAudioStream(audioStream);
        setVideoStream(videoStream);
        setDataStream(dataStream);
        if (audioStream != null) {
            audioStream.addOnDiscardOutboundDtmfTones(new IActionDelegate1<Tone[]>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.audioStream_OnDiscardOutboundDtmfTones";
                }

                public void invoke(Tone[] toneArr) {
                    ManagedConnection.this.audioStream_OnDiscardOutboundDtmfTones(toneArr);
                }
            });
            audioStream.addOnDiscardBitrateRequest(new IActionDelegate1<BitrateRequest>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.mediaStream_OnDiscardBitrateRequest";
                }

                public void invoke(BitrateRequest bitrateRequest) {
                    ManagedConnection.this.mediaStream_OnDiscardBitrateRequest(bitrateRequest);
                }
            });
            audioStream.addOnDiscardBitrateNotification(new IActionDelegate1<BitrateNotification>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.mediaStream_OnDiscardBitrateNotification";
                }

                public void invoke(BitrateNotification bitrateNotification) {
                    ManagedConnection.this.mediaStream_OnDiscardBitrateNotification(bitrateNotification);
                }
            });
        }
        if (videoStream != null) {
            videoStream.addOnDiscardKeyFrameRequest(new IActionDelegate1<long[]>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.videoStream_OnDiscardKeyFrameRequest";
                }

                public void invoke(long[] jArr) {
                    ManagedConnection.this.videoStream_OnDiscardKeyFrameRequest(jArr);
                }
            });
            videoStream.addOnDiscardBitrateRequest(new IActionDelegate1<BitrateRequest>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.mediaStream_OnDiscardBitrateRequest";
                }

                public void invoke(BitrateRequest bitrateRequest) {
                    ManagedConnection.this.mediaStream_OnDiscardBitrateRequest(bitrateRequest);
                }
            });
            videoStream.addOnDiscardBitrateNotification(new IActionDelegate1<BitrateNotification>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.mediaStream_OnDiscardBitrateNotification";
                }

                public void invoke(BitrateNotification bitrateNotification) {
                    ManagedConnection.this.mediaStream_OnDiscardBitrateNotification(bitrateNotification);
                }
            });
        }
        setDisableAutomaticIceServers(false);
        setStatsInterval(getDefaultStatsInterval());
        setInternalConnection(ConnectionUtility.createConnection(this.__lock, getType(), getAudioStream(), getVideoStream(), getDataStream()));
        getInternalConnection().setLegacyTimeout(true);
        getInternalConnection().addOnStateChange(new IActionDelegate1<Connection>() {
            public String getId() {
                return "fm.liveswitch.ManagedConnection.internalConnection_OnStateChange";
            }

            public void invoke(Connection connection) {
                ManagedConnection.this.internalConnection_OnStateChange(connection);
            }
        });
        getInternalConnection().addOnSignallingStateChange(new IActionDelegate1<Connection>() {
            public String getId() {
                return "fm.liveswitch.ManagedConnection.internalConnection_OnSignallingStateChange";
            }

            public void invoke(Connection connection) {
                ManagedConnection.this.internalConnection_OnSignallingStateChange(connection);
            }
        });
        getInternalConnection().addOnIceConnectionStateChange(new IActionDelegate1<Connection>() {
            public String getId() {
                return "fm.liveswitch.ManagedConnection.internalConnection_OnIceConnectionStateChange";
            }

            public void invoke(Connection connection) {
                ManagedConnection.this.internalConnection_OnIceConnectionStateChange(connection);
            }
        });
        getInternalConnection().addOnGatheringStateChange(new IActionDelegate1<Connection>() {
            public String getId() {
                return "fm.liveswitch.ManagedConnection.internalConnection_OnGatheringStateChange";
            }

            public void invoke(Connection connection) {
                ManagedConnection.this.internalConnection_OnGatheringStateChange(connection);
            }
        });
        getInternalConnection().addOnLocalDescription(new IActionDelegate2<Connection, SessionDescription>() {
            public String getId() {
                return "fm.liveswitch.ManagedConnection.internalConnection_OnLocalDescription";
            }

            public void invoke(Connection connection, SessionDescription sessionDescription) {
                ManagedConnection.this.internalConnection_OnLocalDescription(connection, sessionDescription);
            }
        });
        getInternalConnection().addOnRemoteDescription(new IActionDelegate2<Connection, SessionDescription>() {
            public String getId() {
                return "fm.liveswitch.ManagedConnection.internalConnection_OnRemoteDescription";
            }

            public void invoke(Connection connection, SessionDescription sessionDescription) {
                ManagedConnection.this.internalConnection_OnRemoteDescription(connection, sessionDescription);
            }
        });
        getInternalConnection().addOnLocalCandidate(new IActionDelegate2<Connection, Candidate>() {
            public String getId() {
                return "fm.liveswitch.ManagedConnection.internalConnection_OnLocalCandidate";
            }

            public void invoke(Connection connection, Candidate candidate) {
                ManagedConnection.this.internalConnection_OnLocalCandidate(connection, candidate);
            }
        });
        getInternalConnection().addOnRemoteCandidate(new IActionDelegate2<Connection, Candidate>() {
            public String getId() {
                return "fm.liveswitch.ManagedConnection.internalConnection_OnRemoteCandidate";
            }

            public void invoke(Connection connection, Candidate candidate) {
                ManagedConnection.this.internalConnection_OnRemoteCandidate(connection, candidate);
            }
        });
    }

    /* access modifiers changed from: private */
    public void mediaStream_OnDiscardBitrateNotification(BitrateNotification bitrateNotification) {
        if (bitrateNotification != null) {
            send(Message.createBitrateNotificationMessage(bitrateNotification));
        }
    }

    /* access modifiers changed from: private */
    public void mediaStream_OnDiscardBitrateRequest(BitrateRequest bitrateRequest) {
        if (bitrateRequest != null) {
            send(Message.createBitrateRequestMessage(bitrateRequest));
        }
    }

    public Future<Object> open() {
        try {
            Promise<Object> promise = new Promise<>();
            synchronized (this.__lock) {
                if (this._openPromise == null) {
                    this._openPromise = promise;
                } else {
                    throw new RuntimeException(new Exception("Open can only be called once."));
                }
            }
            __log.debug(StringExtensions.format("Opening connection with ID '{0}'.", (Object) getId()));
            if (!getDisableAutomaticIceServers()) {
                if (ArrayExtensions.getLength((Object[]) getIceServers()) <= 0) {
                    __log.debug(StringExtensions.format("Getting ICE servers for connection with ID '{0}'.", (Object) getId()));
                    send(Message.createIceServersMessage()).then(new IAction1<Message>() {
                        public void invoke(Message message) {
                            if (Global.equals(message.getType(), MessageType.getIceServers())) {
                                ManagedConnection.this.setIceServers(IceServer.fromJsonArray(message.getPayload()));
                                ManagedConnection.this.doOpen();
                                return;
                            }
                            ManagedConnection.this.processMessage(message);
                        }
                    }, (IAction1<Exception>) new IAction1<Exception>() {
                        public void invoke(Exception exc) {
                            ManagedConnection.__log.warn(StringExtensions.format("Could not get ICE servers for connection with ID '{0}'.", (Object) ManagedConnection.this.getId()), exc);
                            ManagedConnection.this.doOpen();
                        }
                    });
                    return promise;
                }
            }
            doOpen();
            return promise;
        } catch (Exception e) {
            Promise promise2 = new Promise();
            promise2.reject(e);
            return promise2;
        }
    }

    private void processBitrateNotification(Message message) {
        BitrateNotification[] fromJsonArray = BitrateNotification.fromJsonArray(message.getPayload());
        if (fromJsonArray == null) {
            __log.warn("Received a malformed 'bitrateNotification' message.");
        } else if (ArrayExtensions.getLength((Object[]) fromJsonArray) == 0) {
            __log.warn("Received a 'bitrateNotification' message with no bitrate notifications.");
        } else {
            AudioStream audioStream = getAudioStream();
            VideoStream videoStream = getVideoStream();
            for (BitrateNotification bitrateNotification : fromJsonArray) {
                if (audioStream != null && Global.equals(bitrateNotification.getMediaDescriptionId(), audioStream.getMediaDescriptionId())) {
                    raiseAudioBitrateNotification(audioStream, bitrateNotification);
                } else if (videoStream != null && Global.equals(bitrateNotification.getMediaDescriptionId(), videoStream.getMediaDescriptionId())) {
                    raiseVideoBitrateNotification(videoStream, bitrateNotification);
                } else if (audioStream != null && Global.equals(bitrateNotification.getMediaDescriptionId(), "audio")) {
                    raiseAudioBitrateNotification(audioStream, bitrateNotification);
                } else if (videoStream != null && Global.equals(bitrateNotification.getMediaDescriptionId(), "video")) {
                    raiseVideoBitrateNotification(videoStream, bitrateNotification);
                } else if (videoStream == null || bitrateNotification.getMediaDescriptionId() != null) {
                    __log.warn(StringExtensions.format("Received a 'bitrateNotification' message with unexpected media description ID '{0}'.", (Object) bitrateNotification.getMediaDescriptionId()));
                } else {
                    raiseVideoBitrateNotification(videoStream, bitrateNotification);
                }
            }
        }
    }

    private void processBitrateRequest(Message message) {
        BitrateRequest[] fromJsonArray = BitrateRequest.fromJsonArray(message.getPayload());
        if (fromJsonArray == null) {
            __log.warn("Received a malformed 'bitrateRequest' message.");
        } else if (ArrayExtensions.getLength((Object[]) fromJsonArray) == 0) {
            __log.warn("Received a 'bitrateRequest' message with no bitrate requests.");
        } else {
            AudioStream audioStream = getAudioStream();
            VideoStream videoStream = getVideoStream();
            for (BitrateRequest bitrateRequest : fromJsonArray) {
                if (audioStream != null && Global.equals(bitrateRequest.getMediaDescriptionId(), audioStream.getMediaDescriptionId())) {
                    raiseAudioBitrateRequest(audioStream, bitrateRequest);
                } else if (videoStream != null && Global.equals(bitrateRequest.getMediaDescriptionId(), videoStream.getMediaDescriptionId())) {
                    raiseVideoBitrateRequest(videoStream, bitrateRequest);
                } else if (audioStream != null && Global.equals(bitrateRequest.getMediaDescriptionId(), "audio")) {
                    raiseAudioBitrateRequest(audioStream, bitrateRequest);
                } else if (videoStream != null && Global.equals(bitrateRequest.getMediaDescriptionId(), "video")) {
                    raiseVideoBitrateRequest(videoStream, bitrateRequest);
                } else if (videoStream == null || bitrateRequest.getMediaDescriptionId() != null) {
                    __log.warn(StringExtensions.format("Received a 'bitrateRequest' message with unexpected media description ID '{0}'.", (Object) bitrateRequest.getMediaDescriptionId()));
                } else {
                    raiseVideoBitrateRequest(videoStream, bitrateRequest);
                }
            }
        }
    }

    private void processCandidate(Message message) {
        getInternalConnection().addRemoteCandidate(Candidate.fromJson(message.getPayload())).fail((IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                ManagedConnection.__log.error("Could not add remote candidate.", exc);
            }
        });
    }

    private void processClose() {
        setRemoteClosed(true);
        getInternalConnection().close();
    }

    private void processFail(Error error) {
        setRemoteFailed(true);
        getInternalConnection().setError(error);
        getInternalConnection().close();
    }

    private void processKeyFrameRequest(Message message) {
        long[] deserializeLongArray = JsonSerializer.deserializeLongArray(message.getPayload());
        if (deserializeLongArray == null) {
            __log.warn("Received a malformed 'keyFrameRequest' message.");
        } else if (ArrayExtensions.getLength(deserializeLongArray) == 0) {
            __log.warn("Received a 'keyFrameRequest' message with no sychronization sources.");
        } else {
            VideoStream videoStream = getVideoStream();
            if (videoStream != null) {
                videoStream.raiseKeyFrameRequest(deserializeLongArray);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processLocalError(Error error) {
        setRemoteClosed(false);
        getInternalConnection().setError(error);
        getInternalConnection().close();
    }

    /* access modifiers changed from: package-private */
    public void processMessage(Message message) {
        this.__messageQueue.enqueue(message);
    }

    /* access modifiers changed from: private */
    public void processQueuedMessage(Message message) {
        if (Global.equals(message.getType(), MessageType.getOffer())) {
            return;
        }
        if (Global.equals(message.getType(), MessageType.getAnswer())) {
            processAnswer(message);
        } else if (Global.equals(message.getType(), MessageType.getCandidate())) {
            processCandidate(message);
        } else if (Global.equals(message.getType(), MessageType.getKeyFrameRequest())) {
            processKeyFrameRequest(message);
        } else if (Global.equals(message.getType(), MessageType.getBitrateRequest())) {
            processBitrateRequest(message);
        } else if (Global.equals(message.getType(), MessageType.getBitrateNotification())) {
            processBitrateNotification(message);
        } else if (Global.equals(message.getType(), MessageType.getClose())) {
            processClose();
        } else if (Global.equals(message.getType(), MessageType.getFail())) {
            processFail(new Error(ErrorCode.ConnectionRemoteFailure, new Exception("Media server is no longer available.")));
        } else if (Global.equals(message.getType(), MessageType.getReject())) {
            processReject();
        } else if (Global.equals(message.getType(), MessageType.getError())) {
            String deserializeString = JsonSerializer.deserializeString(message.getPayload());
            if (!Global.equals(getType(), ConnectionType.getPeer())) {
                __log.error(StringExtensions.format("Received an error from the media server: {0}.", (Object) deserializeString));
            } else {
                __log.error(StringExtensions.format("Received an error from the remote peer: {0}.", (Object) deserializeString));
            }
            if (Global.equals(deserializeString, ErrorType.getSfuInvalidUpstream()) || Global.equals(deserializeString, ErrorType.getServerNoAudioCodecs()) || Global.equals(deserializeString, ErrorType.getServerNoVideoCodecs()) || Global.equals(deserializeString, ErrorType.getSdpStreamMismatch()) || Global.equals(deserializeString, ErrorType.getSdpSimulcastMismatch()) || Global.equals(deserializeString, ErrorType.getSdpCodecMismatch())) {
                processRemoteError(new Error(ErrorCode.ConnectionRemoteFailure, new Exception(deserializeString)));
            } else {
                processFail(new Error(ErrorCode.ConnectionRemoteFailure, new Exception(deserializeString)));
            }
        } else {
            doProcessMessage(message);
        }
    }

    private void processReject() {
        setRemoteRejected(true);
        getInternalConnection().close();
    }

    private void processRemoteError(Error error) {
        setRemoteClosed(true);
        getInternalConnection().setError(error);
        getInternalConnection().close();
    }

    private void processStateChangeEvent(final ConnectionState connectionState) {
        if (Global.equals(connectionState, ConnectionState.Closed) || Global.equals(connectionState, ConnectionState.Failed)) {
            cancelSendStats();
            unsubscribeFromInternalEvents();
        } else if (Global.equals(connectionState, ConnectionState.Closing) || Global.equals(connectionState, ConnectionState.Failing)) {
            cancelSendStats();
        }
        sendConnectionEvent(EventType.fromConnectionState(connectionState)).then(new IAction1<Object>() {
            public void invoke(Object obj) {
                if (Global.equals(connectionState, ConnectionState.Connected)) {
                    ManagedConnection.this.scheduleSendStats();
                }
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                ManagedConnection.__log.warn(StringExtensions.format("Could not send {0} event.", (Object) EventType.fromConnectionState(connectionState)), exc);
                if (Global.equals(connectionState, ConnectionState.Connected)) {
                    ManagedConnection.this.scheduleSendStats();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void raiseAudioBitrateNotification(AudioStream audioStream, BitrateNotification bitrateNotification) {
        audioStream.raiseBitrateNotification(bitrateNotification);
    }

    /* access modifiers changed from: protected */
    public void raiseAudioBitrateRequest(AudioStream audioStream, BitrateRequest bitrateRequest) {
        audioStream.raiseBitrateRequest(bitrateRequest);
    }

    /* access modifiers changed from: package-private */
    public void raiseRemoteUpdate(ConnectionInfo connectionInfo, ConnectionInfo connectionInfo2) {
        IAction2<ConnectionInfo, ConnectionInfo> iAction2 = this._onRemoteUpdate;
        if (iAction2 != null) {
            iAction2.invoke(connectionInfo, connectionInfo2);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseVideoBitrateNotification(VideoStream videoStream, BitrateNotification bitrateNotification) {
        videoStream.raiseBitrateNotification(bitrateNotification);
    }

    /* access modifiers changed from: protected */
    public void raiseVideoBitrateRequest(VideoStream videoStream, BitrateRequest bitrateRequest) {
        videoStream.raiseBitrateRequest(bitrateRequest);
    }

    public void removeOnGatheringStateChange(IAction1<ManagedConnection> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onGatheringStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onGatheringStateChange.remove(iAction1);
        if (this.__onGatheringStateChange.size() == 0) {
            this._onGatheringStateChange = null;
        }
    }

    public void removeOnIceConnectionStateChange(IAction1<ManagedConnection> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onIceConnectionStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onIceConnectionStateChange.remove(iAction1);
        if (this.__onIceConnectionStateChange.size() == 0) {
            this._onIceConnectionStateChange = null;
        }
    }

    public void removeOnLocalCandidate(IAction2<ManagedConnection, Candidate> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onLocalCandidate, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onLocalCandidate.remove(iAction2);
        if (this.__onLocalCandidate.size() == 0) {
            this._onLocalCandidate = null;
        }
    }

    public void removeOnLocalDescription(IAction2<ManagedConnection, SessionDescription> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onLocalDescription, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onLocalDescription.remove(iAction2);
        if (this.__onLocalDescription.size() == 0) {
            this._onLocalDescription = null;
        }
    }

    public void removeOnRemoteAudioEncodingSwitch(IAction2<EncodingInfo, EncodingInfo> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onRemoteAudioEncodingSwitch, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onRemoteAudioEncodingSwitch.remove(iAction2);
        if (this.__onRemoteAudioEncodingSwitch.size() == 0) {
            this._onRemoteAudioEncodingSwitch = null;
        }
    }

    public void removeOnRemoteCandidate(IAction2<ManagedConnection, Candidate> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onRemoteCandidate, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onRemoteCandidate.remove(iAction2);
        if (this.__onRemoteCandidate.size() == 0) {
            this._onRemoteCandidate = null;
        }
    }

    public void removeOnRemoteDescription(IAction2<ManagedConnection, SessionDescription> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onRemoteDescription, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onRemoteDescription.remove(iAction2);
        if (this.__onRemoteDescription.size() == 0) {
            this._onRemoteDescription = null;
        }
    }

    public void removeOnRemoteUpdate(IAction2<ConnectionInfo, ConnectionInfo> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onRemoteUpdate, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onRemoteUpdate.remove(iAction2);
        if (this.__onRemoteUpdate.size() == 0) {
            this._onRemoteUpdate = null;
        }
    }

    public void removeOnRemoteVideoEncodingSwitch(IAction2<EncodingInfo, EncodingInfo> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onRemoteVideoEncodingSwitch, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onRemoteVideoEncodingSwitch.remove(iAction2);
        if (this.__onRemoteVideoEncodingSwitch.size() == 0) {
            this._onRemoteVideoEncodingSwitch = null;
        }
    }

    public void removeOnSignallingStateChange(IAction1<ManagedConnection> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onSignallingStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onSignallingStateChange.remove(iAction1);
        if (this.__onSignallingStateChange.size() == 0) {
            this._onSignallingStateChange = null;
        }
    }

    public void removeOnStateChange(IAction1<ManagedConnection> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void removeOnStats(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onStats, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onStats.remove(iAction0);
        if (this.__onStats.size() == 0) {
            this._onStats = null;
        }
    }

    /* access modifiers changed from: private */
    public void scheduleSendStats() {
        if (!this.__sendStatsCancelled) {
            TimeoutTimer timeoutTimer = new TimeoutTimer(new IActionDelegate1<Object>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.sendStats";
                }

                public void invoke(Object obj) {
                    ManagedConnection.this.sendStats(obj);
                }
            }, (Object) null);
            this.__sendStatsTimer = timeoutTimer;
            timeoutTimer.start(getStatsInterval());
        }
    }

    private Future<Object> selfRenegotiate(StreamDirection streamDirection, StreamDirection streamDirection2) {
        Promise promise = new Promise();
        try {
            if (getAudioStream() != null && !Global.equals(getAudioStream().getDirection(), streamDirection)) {
                getAudioStream().changeDirection(streamDirection);
            }
            if (getVideoStream() != null && !Global.equals(getVideoStream().getDirection(), streamDirection2)) {
                getVideoStream().changeDirection(streamDirection2);
            }
            SessionDescription sessionDescription = new SessionDescription();
            sessionDescription.setRenegotiation(true);
            sessionDescription.setTieBreaker(Guid.newGuid().toString());
            sessionDescription.setType(SessionDescriptionType.Answer);
            sessionDescription.setSdpMessage(Message.parse(getInternalConnection().getRemoteDescription().getSdpMessage().toString()));
            Origin origin = sessionDescription.getSdpMessage().getOrigin();
            origin.setSessionVersion(origin.getSessionVersion() + 1);
            StreamDirection receiveDisabled = StreamDirectionHelper.setReceiveDisabled(StreamDirectionHelper.setSendDisabled(streamDirection, StreamDirectionHelper.isReceiveDisabled(streamDirection)), StreamDirectionHelper.isSendDisabled(streamDirection));
            StreamDirection receiveDisabled2 = StreamDirectionHelper.setReceiveDisabled(StreamDirectionHelper.setSendDisabled(streamDirection2, StreamDirectionHelper.isReceiveDisabled(streamDirection2)), StreamDirectionHelper.isSendDisabled(streamDirection2));
            for (MediaDescription mediaDescription : sessionDescription.getSdpMessage().getMediaDescriptions()) {
                if (mediaDescription.getIsAudio()) {
                    if (!Global.equals(mediaDescription.getStreamDirection(), receiveDisabled)) {
                        mediaDescription.setStreamDirection(receiveDisabled);
                    }
                } else if (mediaDescription.getIsVideo() && !Global.equals(mediaDescription.getStreamDirection(), receiveDisabled2)) {
                    mediaDescription.setStreamDirection(receiveDisabled2);
                }
            }
            doSelfNegotiate(sessionDescription, promise);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    /* access modifiers changed from: protected */
    public Future<Message> send(Message message) {
        message.setConnectionId(getId());
        message.setConnectionType(getType());
        message.setMediaId(getMediaId());
        return this.__doSend.invoke(message);
    }

    private Future<Object> sendConnectionEvent(String str) {
        Promise promise = new Promise();
        EventInfo eventInfo = new EventInfo(str);
        eventInfo.setConnection(getInfo());
        doSendConnectionEvent(promise, eventInfo);
        return promise;
    }

    /* access modifiers changed from: private */
    public void sendConnectionEventMessage(final Promise<Object> promise, EventInfo eventInfo) {
        if (eventInfo.getConnection() != null) {
            eventInfo.getConnection().setSerializeLegacyProperties(false);
        }
        send(Message.createEventMessage(eventInfo.toJson())).then(new IAction1<Message>() {
            public void invoke(Message message) {
                promise.resolve(null);
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                promise.reject(exc);
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendStats(Object obj) {
        if (!this.__sendStatsCancelled) {
            sendConnectionEvent(EventType.ConnectionStats).then(new IAction1<Object>() {
                public void invoke(Object obj) {
                    ManagedConnection.this.scheduleSendStats();
                }
            }, (IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    ManagedConnection.__log.warn(StringExtensions.format("Could not send {0} event.", (Object) EventType.ConnectionStats), exc);
                    ManagedConnection.this.scheduleSendStats();
                }
            });
        }
    }

    private void setApplicationId(String str) {
        this._applicationId = str;
    }

    private void setAudioStream(AudioStream audioStream) {
        this._audioStream = audioStream;
    }

    public void setBundlePolicy(BundlePolicy bundlePolicy) {
        getInternalConnection().setBundlePolicy(bundlePolicy);
    }

    private void setChannelId(String str) {
        this._channelId = str;
    }

    private void setClientId(String str) {
        this._clientId = str;
    }

    public void setCreateDatagramSocket(IFunction1<DatagramSocketCreateArgs, DatagramSocket> iFunction1) {
        getInternalConnection().setCreateDatagramSocket(iFunction1);
    }

    public void setCreateStreamSocket(IFunction1<StreamSocketCreateArgs, StreamSocket> iFunction1) {
        getInternalConnection().setCreateStreamSocket(iFunction1);
    }

    private void setDataStream(DataStream dataStream) {
        this._dataStream = dataStream;
    }

    public static void setDefaultLocalDtlsCertificate(DtlsCertificate dtlsCertificate) {
        Connection.setDefaultLocalDtlsCertificate(dtlsCertificate);
    }

    public static void setDefaultLocalDtlsCertificates(DtlsCertificate[] dtlsCertificateArr) {
        Connection.setDefaultLocalDtlsCertificates(dtlsCertificateArr);
    }

    public static void setDefaultStatsInterval(int i) {
        _defaultStatsInterval = i;
    }

    private void setDeviceId(String str) {
        this._deviceId = str;
    }

    public void setDisableAutomaticIceServers(boolean z) {
        this._disableAutomaticIceServers = z;
    }

    public void setDtlsCipherSuites(DtlsCipherSuite[] dtlsCipherSuiteArr) {
        getInternalConnection().setDtlsCipherSuites(dtlsCipherSuiteArr);
    }

    public void setDtlsClientVersion(DtlsProtocolVersion dtlsProtocolVersion) {
        getInternalConnection().setDtlsClientVersion(dtlsProtocolVersion);
    }

    public void setDtlsServerMaxVersion(DtlsProtocolVersion dtlsProtocolVersion) {
        getInternalConnection().setDtlsServerMaxVersion(dtlsProtocolVersion);
    }

    public void setDtlsServerMinVersion(DtlsProtocolVersion dtlsProtocolVersion) {
        getInternalConnection().setDtlsServerMinVersion(dtlsProtocolVersion);
    }

    public void setIceAddressTypes(AddressType[] addressTypeArr) {
        getInternalConnection().setIceAddressTypes(addressTypeArr);
    }

    public void setIceGatherPolicy(IceGatherPolicy iceGatherPolicy) {
        getInternalConnection().setIceGatherPolicy(iceGatherPolicy);
    }

    public void setIcePolicy(IcePolicy icePolicy) {
        getInternalConnection().setIcePolicy(icePolicy);
    }

    public void setIcePortRange(IcePortRange icePortRange) {
        getInternalConnection().setIcePortRange(icePortRange);
    }

    public void setIceServer(IceServer iceServer) {
        getInternalConnection().setIceServer(iceServer);
    }

    public void setIceServers(IceServer[] iceServerArr) {
        getInternalConnection().setIceServers(iceServerArr);
    }

    private void setInternalConnection(Connection connection) {
        this._internalConnection = connection;
    }

    public void setKeepAliveInterval(int i) {
        getInternalConnection().setKeepAliveInterval(i);
    }

    /* access modifiers changed from: protected */
    public void setLocalAudioFormats(FormatInfo[] formatInfoArr) {
        this._localAudioFormats = formatInfoArr;
    }

    public void setLocalDtlsCertificate(DtlsCertificate dtlsCertificate) {
        getInternalConnection().setLocalDtlsCertificate(dtlsCertificate);
    }

    public void setLocalDtlsCertificates(DtlsCertificate[] dtlsCertificateArr) {
        getInternalConnection().setLocalDtlsCertificates(dtlsCertificateArr);
    }

    /* access modifiers changed from: protected */
    public void setLocalVideoFormats(FormatInfo[] formatInfoArr) {
        this._localVideoFormats = formatInfoArr;
    }

    /* access modifiers changed from: protected */
    public void setMediaId(String str) {
        this._mediaId = str;
    }

    public void setMultiplexPolicy(MultiplexPolicy multiplexPolicy) {
        getInternalConnection().setMultiplexPolicy(multiplexPolicy);
    }

    public void setPrivateIPAddress(String str) {
        getInternalConnection().setPrivateIPAddress(str);
    }

    public void setPrivateIPAddresses(String[] strArr) {
        getInternalConnection().setPrivateIPAddresses(strArr);
    }

    /* access modifiers changed from: protected */
    public void setRemoteAudioEncoding(EncodingInfo encodingInfo) {
        AudioStream audioStream = getAudioStream();
        if (audioStream != null) {
            EncodingInfo remoteEncoding = audioStream.getRemoteEncoding();
            audioStream.setRemoteEncoding(encodingInfo);
            IAction2<EncodingInfo, EncodingInfo> iAction2 = this._onRemoteAudioEncodingSwitch;
            if (iAction2 != null) {
                iAction2.invoke(remoteEncoding, encodingInfo);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setRemoteAudioFormats(FormatInfo[] formatInfoArr) {
        this._remoteAudioFormats = formatInfoArr;
    }

    /* access modifiers changed from: protected */
    public void setRemoteAudioMuted(boolean z) {
        this._remoteAudioMuted = z;
    }

    private void setRemoteClosed(boolean z) {
        this._remoteClosed = z;
    }

    /* access modifiers changed from: protected */
    public void setRemoteConnectionId(String str) {
        this._remoteConnectionId = str;
    }

    private void setRemoteFailed(boolean z) {
        this._remoteFailed = z;
    }

    /* access modifiers changed from: protected */
    public void setRemoteMediaId(String str) {
        this._remoteMediaId = str;
    }

    private void setRemoteRejected(boolean z) {
        this._remoteRejected = z;
    }

    /* access modifiers changed from: protected */
    public void setRemoteTag(String str) {
        this._remoteTag = str;
    }

    /* access modifiers changed from: protected */
    public void setRemoteVideoEncoding(EncodingInfo encodingInfo) {
        VideoStream videoStream = getVideoStream();
        if (videoStream != null) {
            EncodingInfo remoteEncoding = videoStream.getRemoteEncoding();
            videoStream.setRemoteEncoding(encodingInfo);
            IAction2<EncodingInfo, EncodingInfo> iAction2 = this._onRemoteVideoEncodingSwitch;
            if (iAction2 != null) {
                iAction2.invoke(remoteEncoding, encodingInfo);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setRemoteVideoFormats(FormatInfo[] formatInfoArr) {
        this._remoteVideoFormats = formatInfoArr;
    }

    /* access modifiers changed from: protected */
    public void setRemoteVideoMuted(boolean z) {
        this._remoteVideoMuted = z;
    }

    public void setStatsInterval(int i) {
        this._statsInterval = i;
    }

    public void setStunBindingRequestLimit(int i) {
        getInternalConnection().setStunBindingRequestLimit(i);
    }

    public void setStunRequestTimeout(int i) {
        getInternalConnection().setStunRequestTimeout(i);
    }

    public void setTag(String str) {
        this._tag = str;
    }

    public void setTcpConnectTimeout(int i) {
        getInternalConnection().setTcpConnectTimeout(i);
    }

    public void setTestReceivedRtpBuffer(IFunction1<DataBuffer, DataBuffer> iFunction1) {
        getInternalConnection().setTestReceivedRtpBuffer(iFunction1);
    }

    public void setTestRoundTripTime(int i) {
        getInternalConnection().setTestRoundTripTime(i);
    }

    public void setTestSendingRtpBuffer(IFunction1<DataBuffer, DataBuffer> iFunction1) {
        getInternalConnection().setTestSendingRtpBuffer(iFunction1);
    }

    public void setTrickleIcePolicy(TrickleIcePolicy trickleIcePolicy) {
        getInternalConnection().setTrickleIcePolicy(trickleIcePolicy);
    }

    public void setTurnAllocateRequestLimit(int i) {
        getInternalConnection().setTurnAllocateRequestLimit(i);
    }

    private void setType(String str) {
        this._type = str;
    }

    private void setUserId(String str) {
        this._userId = str;
    }

    private void setVideoStream(VideoStream videoStream) {
        this._videoStream = videoStream;
    }

    private void unsubscribeFromInternalEvents() {
        Connection internalConnection = getInternalConnection();
        if (internalConnection != null) {
            internalConnection.removeOnStateChange(new IActionDelegate1<Connection>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.internalConnection_OnStateChange";
                }

                public void invoke(Connection connection) {
                    ManagedConnection.this.internalConnection_OnStateChange(connection);
                }
            });
            internalConnection.removeOnSignallingStateChange(new IActionDelegate1<Connection>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.internalConnection_OnSignallingStateChange";
                }

                public void invoke(Connection connection) {
                    ManagedConnection.this.internalConnection_OnSignallingStateChange(connection);
                }
            });
            internalConnection.removeOnIceConnectionStateChange(new IActionDelegate1<Connection>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.internalConnection_OnIceConnectionStateChange";
                }

                public void invoke(Connection connection) {
                    ManagedConnection.this.internalConnection_OnIceConnectionStateChange(connection);
                }
            });
            internalConnection.removeOnGatheringStateChange(new IActionDelegate1<Connection>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.internalConnection_OnGatheringStateChange";
                }

                public void invoke(Connection connection) {
                    ManagedConnection.this.internalConnection_OnGatheringStateChange(connection);
                }
            });
            internalConnection.removeOnLocalCandidate(new IActionDelegate2<Connection, Candidate>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConnection.internalConnection_OnLocalCandidate";
                }

                public void invoke(Connection connection, Candidate candidate) {
                    ManagedConnection.this.internalConnection_OnLocalCandidate(connection, candidate);
                }
            });
        }
    }

    public Future<Object> update(ConnectionConfig connectionConfig) {
        Promise promise = new Promise();
        try {
            doUpdate(connectionConfig, promise);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    /* access modifiers changed from: package-private */
    public Future<Object> updateConnection(ConnectionInfo connectionInfo, ConnectionInfo connectionInfo2) {
        Promise promise = new Promise();
        try {
            doUpdateConnection(connectionInfo, connectionInfo2, promise);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    /* access modifiers changed from: private */
    public void videoStream_OnDiscardKeyFrameRequest(long[] jArr) {
        if (jArr != null && ArrayExtensions.getLength(jArr) > 0) {
            send(Message.createKeyFrameRequestMessage(jArr));
        }
    }
}
