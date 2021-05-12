package fm.liveswitch;

import com.google.android.exoplayer2.DefaultLoadControl;
import fm.liveswitch.ConnectionBase;
import fm.liveswitch.IAudioStream;
import fm.liveswitch.IDataChannel;
import fm.liveswitch.IDataStream;
import fm.liveswitch.IVideoStream;
import fm.liveswitch.StreamBase;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.Message;
import fm.liveswitch.sdp.rtp.SimulcastAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ConnectionBase<TConnection extends ConnectionBase<TConnection, TStream, TAudioStream, TVideoStream, TDataStream, TDataChannel>, TStream extends StreamBase, TAudioStream extends IAudioStream, TVideoStream extends IVideoStream, TDataStream extends IDataStream<TDataChannel>, TDataChannel extends IDataChannel<TDataChannel>> extends Dynamic implements IConnection<TConnection, TStream, TAudioStream, TVideoStream, TDataStream> {
    private BundlePolicy __bundlePolicy = BundlePolicy.MaxCompatibility;
    private Promise<Object> __closed = new Promise<>();
    private Promise<Object> __connected = new Promise<>();
    private String __externalId;
    private Promise<Object> __failed = new Promise<>();
    private IceServerCollection __iceServers = new IceServerCollection();
    private String __id = Utility.generateId();
    /* access modifiers changed from: private */
    public List<IAction2<String, String>> __onExternalIdChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TConnection>> __onGatheringStateChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TConnection>> __onIceConnectionStateChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<TConnection, Candidate>> __onLocalCandidate = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<TConnection, SessionDescription>> __onLocalDescription = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<TConnection, Candidate>> __onRemoteCandidate = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<TConnection, SessionDescription>> __onRemoteDescription = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TConnection>> __onSignallingStateChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TConnection>> __onStateChange = new ArrayList();
    private SignallingState __signallingState = SignallingState.New;
    private ConnectionStateMachine __stateMachine;
    private TrickleIcePolicy __trickleIcePolicy = TrickleIcePolicy.FullTrickle;
    private boolean __useTrickleIce = true;
    protected Object _connectionLock;
    private int _deadStreamTimeout;
    private ArrayList<RemoteCandidatePromise> _earlyRemoteCandidatePromises = new ArrayList<>();
    private Error _error;
    private IceGatherPolicy _iceGatherPolicy;
    private boolean _legacyTimeout;
    private IAction2<String, String> _onExternalIdChange = null;
    private IAction1<TConnection> _onGatheringStateChange = null;
    private IAction1<TConnection> _onIceConnectionStateChange = null;
    private IAction2<TConnection, Candidate> _onLocalCandidate = null;
    private IAction2<TConnection, SessionDescription> _onLocalDescription = null;
    private IAction2<TConnection, Candidate> _onRemoteCandidate = null;
    private IAction2<TConnection, SessionDescription> _onRemoteDescription = null;
    private IAction1<TConnection> _onSignallingStateChange = null;
    private IAction1<TConnection> _onStateChange = null;
    private String _tieBreaker;
    private int _timeout;

    public abstract boolean close();

    /* access modifiers changed from: protected */
    public abstract void doAddRemoteCandidate(Promise<Candidate> promise, Candidate candidate);

    /* access modifiers changed from: protected */
    public abstract void doCreateAnswer(Promise<SessionDescription> promise);

    /* access modifiers changed from: protected */
    public abstract boolean doCreateOffer(Promise<SessionDescription> promise);

    /* access modifiers changed from: protected */
    public abstract void doSendCachedLocalCandidates();

    /* access modifiers changed from: protected */
    public abstract void doSetLocalDescription(Promise<SessionDescription> promise, SessionDescription sessionDescription);

    /* access modifiers changed from: protected */
    public abstract void doSetRemoteDescription(Promise<SessionDescription> promise, SessionDescription sessionDescription);

    public abstract TAudioStream[] getAudioStreams();

    public abstract TDataStream[] getDataStreams();

    public abstract IceGatheringState getGatheringState();

    public abstract IceConnectionState getIceConnectionState();

    /* access modifiers changed from: protected */
    public abstract TConnection getInstance();

    public abstract SessionDescription getLocalDescription();

    public abstract SessionDescription getRemoteDescription();

    /* access modifiers changed from: package-private */
    public abstract SessionDescriptionManagerBase<TStream, TAudioStream, TVideoStream, TDataStream, TDataChannel> getSessionDescriptionManager();

    public abstract Future<ConnectionStats> getStats();

    public abstract TStream[] getStreams();

    public abstract TVideoStream[] getVideoStreams();

    /* access modifiers changed from: protected */
    public abstract void setGatheringState(IceGatheringState iceGatheringState);

    /* access modifiers changed from: protected */
    public abstract void setIceConnectionState(IceConnectionState iceConnectionState);

    /* access modifiers changed from: package-private */
    public abstract void setSessionDescriptionManager(SessionDescriptionManagerBase<TStream, TAudioStream, TVideoStream, TDataStream, TDataChannel> sessionDescriptionManagerBase);

    /* access modifiers changed from: protected */
    public void updateBundlePolicy(BundlePolicy bundlePolicy) {
    }

    /* access modifiers changed from: package-private */
    public void updateRemoteCandidateIndex(Candidate candidate) {
    }

    public void addIceServer(IceServer iceServer) {
        this.__iceServers.add(iceServer);
    }

    public void addIceServers(IceServer[] iceServerArr) {
        this.__iceServers.addMany(iceServerArr);
    }

    public void addOnExternalIdChange(IAction2<String, String> iAction2) {
        if (iAction2 != null) {
            if (this._onExternalIdChange == null) {
                this._onExternalIdChange = new IAction2<String, String>() {
                    public void invoke(String str, String str2) {
                        Iterator it = new ArrayList(ConnectionBase.this.__onExternalIdChange).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(str, str2);
                        }
                    }
                };
            }
            this.__onExternalIdChange.add(iAction2);
        }
    }

    public void addOnGatheringStateChange(IAction1<TConnection> iAction1) {
        if (iAction1 != null) {
            if (this._onGatheringStateChange == null) {
                this._onGatheringStateChange = new IAction1<TConnection>() {
                    public void invoke(TConnection tconnection) {
                        Iterator it = new ArrayList(ConnectionBase.this.__onGatheringStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tconnection);
                        }
                    }
                };
            }
            this.__onGatheringStateChange.add(iAction1);
        }
    }

    public void addOnIceConnectionStateChange(IAction1<TConnection> iAction1) {
        if (iAction1 != null) {
            if (this._onIceConnectionStateChange == null) {
                this._onIceConnectionStateChange = new IAction1<TConnection>() {
                    public void invoke(TConnection tconnection) {
                        Iterator it = new ArrayList(ConnectionBase.this.__onIceConnectionStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tconnection);
                        }
                    }
                };
            }
            this.__onIceConnectionStateChange.add(iAction1);
        }
    }

    public void addOnLocalCandidate(IAction2<TConnection, Candidate> iAction2) {
        if (iAction2 != null) {
            if (this._onLocalCandidate == null) {
                this._onLocalCandidate = new IAction2<TConnection, Candidate>() {
                    public void invoke(TConnection tconnection, Candidate candidate) {
                        Iterator it = new ArrayList(ConnectionBase.this.__onLocalCandidate).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(tconnection, candidate);
                        }
                    }
                };
            }
            this.__onLocalCandidate.add(iAction2);
        }
    }

    public void addOnLocalDescription(IAction2<TConnection, SessionDescription> iAction2) {
        if (iAction2 != null) {
            if (this._onLocalDescription == null) {
                this._onLocalDescription = new IAction2<TConnection, SessionDescription>() {
                    public void invoke(TConnection tconnection, SessionDescription sessionDescription) {
                        Iterator it = new ArrayList(ConnectionBase.this.__onLocalDescription).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(tconnection, sessionDescription);
                        }
                    }
                };
            }
            this.__onLocalDescription.add(iAction2);
        }
    }

    public void addOnRemoteCandidate(IAction2<TConnection, Candidate> iAction2) {
        if (iAction2 != null) {
            if (this._onRemoteCandidate == null) {
                this._onRemoteCandidate = new IAction2<TConnection, Candidate>() {
                    public void invoke(TConnection tconnection, Candidate candidate) {
                        Iterator it = new ArrayList(ConnectionBase.this.__onRemoteCandidate).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(tconnection, candidate);
                        }
                    }
                };
            }
            this.__onRemoteCandidate.add(iAction2);
        }
    }

    public void addOnRemoteDescription(IAction2<TConnection, SessionDescription> iAction2) {
        if (iAction2 != null) {
            if (this._onRemoteDescription == null) {
                this._onRemoteDescription = new IAction2<TConnection, SessionDescription>() {
                    public void invoke(TConnection tconnection, SessionDescription sessionDescription) {
                        Iterator it = new ArrayList(ConnectionBase.this.__onRemoteDescription).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(tconnection, sessionDescription);
                        }
                    }
                };
            }
            this.__onRemoteDescription.add(iAction2);
        }
    }

    public void addOnSignallingStateChange(IAction1<TConnection> iAction1) {
        if (iAction1 != null) {
            if (this._onSignallingStateChange == null) {
                this._onSignallingStateChange = new IAction1<TConnection>() {
                    public void invoke(TConnection tconnection) {
                        Iterator it = new ArrayList(ConnectionBase.this.__onSignallingStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tconnection);
                        }
                    }
                };
            }
            this.__onSignallingStateChange.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<TConnection> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<TConnection>() {
                    public void invoke(TConnection tconnection) {
                        Iterator it = new ArrayList(ConnectionBase.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tconnection);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    public Future<Candidate> addRemoteCandidate(Candidate candidate) {
        RemoteCandidatePromise remoteCandidatePromise = new RemoteCandidatePromise(candidate);
        if (Global.equals(getState(), ConnectionState.New) || Global.equals(getState(), ConnectionState.Initializing)) {
            this._earlyRemoteCandidatePromises.add(remoteCandidatePromise);
            if (!Global.equals(getState(), ConnectionState.New) && !Global.equals(getState(), ConnectionState.Initializing)) {
                this._earlyRemoteCandidatePromises.clear();
                addRemoteCandidatePromise(remoteCandidatePromise);
            }
            return remoteCandidatePromise;
        }
        if (!getIsTerminatingOrTerminated()) {
            addRemoteCandidatePromise(remoteCandidatePromise);
        }
        return remoteCandidatePromise;
    }

    private void addRemoteCandidatePromise(RemoteCandidatePromise remoteCandidatePromise) {
        try {
            updateRemoteCandidateIndex(remoteCandidatePromise.getRemoteCandidate());
            raiseRemoteCandidate(remoteCandidatePromise.getRemoteCandidate());
            doAddRemoteCandidate(remoteCandidatePromise, remoteCandidatePromise.getRemoteCandidate());
        } catch (Exception e) {
            remoteCandidatePromise.reject(e);
        }
    }

    public ConnectionBase(Object obj) {
        this._connectionLock = obj;
        setLegacyTimeout(true);
        setTieBreaker(Utility.generateId());
        setTimeout(DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
        setDeadStreamTimeout(15000);
        setIceGatherPolicy(IceGatherPolicy.All);
        setTrickleIcePolicy(TrickleIcePolicy.FullTrickle);
        this.__stateMachine = new ConnectionStateMachine();
    }

    public Future<SessionDescription> createAnswer() {
        Promise promise = new Promise();
        createAnswerWrapper(promise);
        return promise;
    }

    private void createAnswerWrapper(final Promise<SessionDescription> promise) {
        try {
            Promise promise2 = new Promise();
            doCreateAnswer(promise2);
            promise2.then(new IAction1<SessionDescription>() {
                public void invoke(SessionDescription sessionDescription) {
                    promise.resolveAsync(sessionDescription);
                }
            }, (IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    promise.rejectAsync(exc);
                }
            });
        } catch (Exception e) {
            promise.rejectAsync(e);
        }
    }

    public Future<SessionDescription> createOffer() {
        Promise promise = new Promise();
        createOfferWrapper(promise);
        return promise;
    }

    private void createOfferWrapper(final Promise<SessionDescription> promise) {
        try {
            Promise promise2 = new Promise();
            doCreateOffer(promise2);
            promise2.then(new IAction1<SessionDescription>() {
                public void invoke(SessionDescription sessionDescription) {
                    promise.resolveAsync(sessionDescription);
                }
            }, (IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    promise.rejectAsync(exc);
                }
            });
        } catch (Exception e) {
            promise.rejectAsync(e);
        }
    }

    /* access modifiers changed from: protected */
    public Error doProcessDescription(SessionDescription sessionDescription, boolean z) {
        Error processDescription = getSessionDescriptionManager().processDescription(sessionDescription, z);
        if (processDescription != null) {
            return processDescription;
        }
        if (z || !Global.equals(getTrickleIcePolicy(), TrickleIcePolicy.HalfTrickle)) {
            return null;
        }
        if (sessionDescription.getSdpMessage().getSupportsTrickleIce()) {
            Log.debug(StringExtensions.format("Peer supports Trickle-Ice. Trickle Ice will be enabled in all subsequent communications with the peer for the lifetime of connection {0}.", (Object) getId()));
            setUseTrickleIce(true);
            return null;
        }
        setUseTrickleIce(false);
        return null;
    }

    private Future<SessionDescription> doSetLocalDescriptionInternal(SessionDescription sessionDescription) {
        Promise promise = new Promise();
        doSetLocalDescription(promise, sessionDescription);
        return promise;
    }

    private Future<SessionDescription> doSetRemoteDescriptionInternal(SessionDescription sessionDescription) {
        Promise promise = new Promise();
        doSetRemoteDescription(promise, sessionDescription);
        return promise;
    }

    public TAudioStream getAudioStream() {
        TAudioStream[] audioStreams = getAudioStreams();
        if (audioStreams == null || ArrayExtensions.getLength((Object[]) audioStreams) == 0) {
            return null;
        }
        return audioStreams[0];
    }

    public BundlePolicy getBundlePolicy() {
        return this.__bundlePolicy;
    }

    public String getCanonicalName() {
        Log.warn("Getting the value of Connection.CanonicalName is deprecated. Get the value of MediaStream.LocalCanonicalName instead.");
        return getConnectionWideCanonicalName();
    }

    public Future<Object> getClosed() {
        return this.__closed;
    }

    public Future<Object> getConnected() {
        return this.__connected;
    }

    /* access modifiers changed from: protected */
    public String getConnectionWideCanonicalName() {
        for (StreamBase streamBase : getStreams()) {
            if (Global.equals(streamBase.getType(), StreamType.Audio)) {
                return ((MediaStreamBase) Global.tryCast(streamBase, MediaStreamBase.class)).getLocalCanonicalName();
            }
            if (Global.equals(streamBase.getType(), StreamType.Video)) {
                return ((MediaStreamBase) Global.tryCast(streamBase, MediaStreamBase.class)).getLocalCanonicalName();
            }
        }
        return getId();
    }

    public TDataStream getDataStream() {
        TDataStream[] dataStreams = getDataStreams();
        if (dataStreams == null || ArrayExtensions.getLength((Object[]) dataStreams) == 0) {
            return null;
        }
        return dataStreams[0];
    }

    public int getDeadStreamTimeout() {
        return this._deadStreamTimeout;
    }

    public Error getError() {
        return this._error;
    }

    public String getExternalId() {
        return this.__externalId;
    }

    public Future<Object> getFailed() {
        return this.__failed;
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

    public IceGatherPolicy getIceGatherPolicy() {
        return this._iceGatherPolicy;
    }

    public IceServer getIceServer() {
        return (IceServer) this.__iceServers.getValue();
    }

    public IceServer[] getIceServers() {
        return (IceServer[]) this.__iceServers.getValues();
    }

    public String getId() {
        return this.__id;
    }

    public boolean getIsTerminated() {
        return Global.equals(getState(), ConnectionState.Closed) || Global.equals(getState(), ConnectionState.Failed);
    }

    public boolean getIsTerminating() {
        return Global.equals(getState(), ConnectionState.Closing) || Global.equals(getState(), ConnectionState.Failing);
    }

    public boolean getIsTerminatingOrTerminated() {
        return getIsTerminating() || getIsTerminated();
    }

    public boolean getLegacyTimeout() {
        return this._legacyTimeout;
    }

    public SignallingState getSignallingState() {
        return this.__signallingState;
    }

    public ConnectionState getState() {
        return (ConnectionState) this.__stateMachine.getState();
    }

    public TStream getStream() {
        TStream[] streams = getStreams();
        if (streams == null || ArrayExtensions.getLength((Object[]) streams) == 0) {
            return null;
        }
        return streams[0];
    }

    public String getTieBreaker() {
        return this._tieBreaker;
    }

    public int getTimeout() {
        return this._timeout;
    }

    public TrickleIcePolicy getTrickleIcePolicy() {
        return this.__trickleIcePolicy;
    }

    /* access modifiers changed from: package-private */
    public boolean getUseTrickleIce() {
        return this.__useTrickleIce;
    }

    public TVideoStream getVideoStream() {
        TVideoStream[] videoStreams = getVideoStreams();
        if (videoStreams == null || ArrayExtensions.getLength((Object[]) videoStreams) == 0) {
            return null;
        }
        return videoStreams[0];
    }

    private void logInvalidStateTransition(ConnectionState connectionState) {
        if (Log.getIsDebugEnabled()) {
            Log.debug(StringExtensions.format("Connection {0} state is currently {1}. Cannot transition to {2}.", getId(), StringExtensions.toLower(getState().toString()), StringExtensions.toLower(connectionState.toString())));
        }
    }

    /* access modifiers changed from: protected */
    public Error processDescription(SessionDescription sessionDescription, boolean z) {
        Error doProcessDescription;
        synchronized (this._connectionLock) {
            doProcessDescription = doProcessDescription(sessionDescription, z);
        }
        return doProcessDescription;
    }

    /* access modifiers changed from: protected */
    public void processStateChange() {
        StreamBase[] streams = getStreams();
        if (streams != null) {
            for (StreamBase streamBase : streams) {
                if (Global.equals(getState(), ConnectionState.Initializing)) {
                    streamBase.setState(StreamState.Initializing);
                } else if (Global.equals(getState(), ConnectionState.Connecting)) {
                    streamBase.setState(StreamState.Connecting);
                } else if (Global.equals(getState(), ConnectionState.Connected)) {
                    streamBase.setState(StreamState.Connected);
                } else if (Global.equals(getState(), ConnectionState.Closing)) {
                    streamBase.setState(StreamState.Closing);
                } else if (Global.equals(getState(), ConnectionState.Closed)) {
                    streamBase.setState(StreamState.Closed);
                } else if (Global.equals(getState(), ConnectionState.Failing)) {
                    streamBase.setState(StreamState.Failing);
                } else if (Global.equals(getState(), ConnectionState.Failed)) {
                    streamBase.setState(StreamState.Failed);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void raiseCachedCandidates() {
        Iterator<RemoteCandidatePromise> it = this._earlyRemoteCandidatePromises.iterator();
        while (it.hasNext()) {
            addRemoteCandidatePromise(it.next());
        }
        this._earlyRemoteCandidatePromises.clear();
        doSendCachedLocalCandidates();
    }

    /* access modifiers changed from: private */
    public void raiseConnected() {
        if (Global.equals(this.__connected.getState(), FutureState.Pending)) {
            this.__connected.resolve(null);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseGatheringStateChange(TConnection tconnection) {
        IAction1<TConnection> iAction1 = this._onGatheringStateChange;
        if (iAction1 != null) {
            try {
                iAction1.invoke(tconnection);
            } catch (Exception e) {
                Log.error(StringExtensions.format("Exception occurred while raising gathering state change to the application code for connection {0}.", (Object) getId()), e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void raiseIceConnectionStateChange(TConnection tconnection) {
        IAction1<TConnection> iAction1 = this._onIceConnectionStateChange;
        if (iAction1 != null) {
            try {
                iAction1.invoke(tconnection);
            } catch (Exception e) {
                Log.error(StringExtensions.format("Exception occurred while raising ICE connection state change to the application code for connection {0}.", (Object) getId()), e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void raiseLocalCandidate(Candidate candidate) {
        IAction2<TConnection, Candidate> iAction2 = this._onLocalCandidate;
        if (iAction2 != null && !candidate.getDispatched()) {
            try {
                iAction2.invoke(getInstance(), candidate);
            } catch (Exception e) {
                Log.error(StringExtensions.format("Exception occurred while raising local candidate to the application code for connection {0}.", (Object) getId()), e);
            }
        }
        if (Global.equals(candidate.getRelayProtocol(), ProtocolType.Tcp)) {
            Log.info(StringExtensions.format("Local candidate for stream {0} (over TCP) for connection {2}:\n{1}", IntegerExtensions.toString(Integer.valueOf(candidate.getSdpMediaIndex())), candidate.getSdpCandidateAttribute().toString(), getId()));
        } else if (Global.equals(candidate.getRelayProtocol(), ProtocolType.Tls)) {
            Log.info(StringExtensions.format("Local candidate for stream {0} (over TLS) for connection {2}:\n{1}", IntegerExtensions.toString(Integer.valueOf(candidate.getSdpMediaIndex())), candidate.getSdpCandidateAttribute().toString(), getId()));
        } else {
            Log.info(StringExtensions.format("Local candidate for stream {0} for connection {2}:\n{1}", IntegerExtensions.toString(Integer.valueOf(candidate.getSdpMediaIndex())), candidate.getSdpCandidateAttribute().toString(), getId()));
        }
    }

    private void raiseLocalDescription(SessionDescription sessionDescription) {
        IAction2<TConnection, SessionDescription> iAction2 = this._onLocalDescription;
        if (iAction2 != null) {
            try {
                iAction2.invoke(getInstance(), sessionDescription);
            } catch (Exception e) {
                Log.error(StringExtensions.format("Exception occurred while raising local description to the application code for connection {0}.", (Object) getId()), e);
            }
        }
    }

    private void raiseRemoteCandidate(Candidate candidate) {
        IAction2<TConnection, Candidate> iAction2 = this._onRemoteCandidate;
        if (iAction2 != null) {
            try {
                iAction2.invoke(getInstance(), candidate);
            } catch (Exception e) {
                Log.error(StringExtensions.format("Exception occurred while raising remote candidate to the application code for connection {0}.", (Object) getId()), e);
            }
        }
        Log.info(StringExtensions.format("Remote candidate for stream {0} for connection {2}:\n{1}", IntegerExtensions.toString(Integer.valueOf(candidate.getSdpMediaIndex())), candidate.getSdpCandidateAttribute().toString(), getId()));
    }

    private void raiseRemoteDescription(SessionDescription sessionDescription) {
        IAction2<TConnection, SessionDescription> iAction2 = this._onRemoteDescription;
        if (iAction2 != null) {
            try {
                iAction2.invoke(getInstance(), sessionDescription);
            } catch (Exception e) {
                Log.error(StringExtensions.format("Exception occurred while raising remote description to the application code for connection {0}.", (Object) getId()), e);
            }
        }
    }

    private void raiseStateChange() {
        ConnectionState state = getState();
        if (state == ConnectionState.Connected) {
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    ConnectionBase.this.raiseConnected();
                }
            });
        } else if (state == ConnectionState.Failed) {
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    ConnectionBase.this.raiseTerminated(true);
                }
            });
        } else if (state == ConnectionState.Closed) {
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    ConnectionBase.this.raiseTerminated(false);
                }
            });
        }
        IAction1<TConnection> iAction1 = this._onStateChange;
        if (iAction1 != null) {
            try {
                iAction1.invoke(getInstance());
            } catch (Exception e) {
                Log.error(StringExtensions.format("Exception occurred while raising state change to the application code for connection {0}.", (Object) getId()), e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void raiseTerminated(boolean z) {
        if (z) {
            if (Global.equals(this.__connected.getState(), FutureState.Pending)) {
                this.__connected.reject(getError().getException());
            }
            if (Global.equals(this.__closed.getState(), FutureState.Pending)) {
                this.__closed.reject(getError().getException());
            }
            if (Global.equals(this.__failed.getState(), FutureState.Pending)) {
                this.__failed.resolve(null);
                return;
            }
            return;
        }
        if (Global.equals(this.__connected.getState(), FutureState.Pending)) {
            this.__connected.reject(new Exception("Connection closed."));
        }
        if (Global.equals(this.__failed.getState(), FutureState.Pending)) {
            this.__failed.reject(new Exception("Connection closed."));
        }
        if (Global.equals(this.__closed.getState(), FutureState.Pending)) {
            this.__closed.resolve(null);
        }
    }

    /* access modifiers changed from: protected */
    public void registerStreamWithSessionDescriptionManager(TStream tstream) {
        getSessionDescriptionManager().addStream(tstream);
    }

    public void removeIceServer(IceServer iceServer) {
        this.__iceServers.remove(iceServer);
    }

    public void removeIceServers(IceServer[] iceServerArr) {
        this.__iceServers.removeMany(iceServerArr);
    }

    public void removeOnExternalIdChange(IAction2<String, String> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onExternalIdChange, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onExternalIdChange.remove(iAction2);
        if (this.__onExternalIdChange.size() == 0) {
            this._onExternalIdChange = null;
        }
    }

    public void removeOnGatheringStateChange(IAction1<TConnection> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onGatheringStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onGatheringStateChange.remove(iAction1);
        if (this.__onGatheringStateChange.size() == 0) {
            this._onGatheringStateChange = null;
        }
    }

    public void removeOnIceConnectionStateChange(IAction1<TConnection> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onIceConnectionStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onIceConnectionStateChange.remove(iAction1);
        if (this.__onIceConnectionStateChange.size() == 0) {
            this._onIceConnectionStateChange = null;
        }
    }

    public void removeOnLocalCandidate(IAction2<TConnection, Candidate> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onLocalCandidate, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onLocalCandidate.remove(iAction2);
        if (this.__onLocalCandidate.size() == 0) {
            this._onLocalCandidate = null;
        }
    }

    public void removeOnLocalDescription(IAction2<TConnection, SessionDescription> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onLocalDescription, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onLocalDescription.remove(iAction2);
        if (this.__onLocalDescription.size() == 0) {
            this._onLocalDescription = null;
        }
    }

    public void removeOnRemoteCandidate(IAction2<TConnection, Candidate> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onRemoteCandidate, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onRemoteCandidate.remove(iAction2);
        if (this.__onRemoteCandidate.size() == 0) {
            this._onRemoteCandidate = null;
        }
    }

    public void removeOnRemoteDescription(IAction2<TConnection, SessionDescription> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onRemoteDescription, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onRemoteDescription.remove(iAction2);
        if (this.__onRemoteDescription.size() == 0) {
            this._onRemoteDescription = null;
        }
    }

    public void removeOnSignallingStateChange(IAction1<TConnection> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onSignallingStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onSignallingStateChange.remove(iAction1);
        if (this.__onSignallingStateChange.size() == 0) {
            this._onSignallingStateChange = null;
        }
    }

    public void removeOnStateChange(IAction1<TConnection> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    public void setBundlePolicy(BundlePolicy bundlePolicy) {
        synchronized (this._connectionLock) {
            if (!Global.equals(bundlePolicy, BundlePolicy.Balanced)) {
                this.__bundlePolicy = bundlePolicy;
                updateBundlePolicy(bundlePolicy);
            } else {
                throw new RuntimeException(new Exception("Balanced stream bundling is currently not supported."));
            }
        }
    }

    public void setDeadStreamTimeout(int i) {
        this._deadStreamTimeout = i;
    }

    public void setError(Error error) {
        this._error = error;
    }

    public void setExternalId(String str) {
        String str2 = this.__externalId;
        if (!Global.equals(str2, str)) {
            this.__externalId = str;
            IAction2<String, String> iAction2 = this._onExternalIdChange;
            if (iAction2 != null) {
                try {
                    iAction2.invoke(getId(), str2);
                } catch (Exception e) {
                    Log.error(StringExtensions.format("Exception occurred while raising external ID change to the application code for connection {0}.", (Object) getId()), e);
                }
            }
        }
    }

    public void setIceGatherPolicy(IceGatherPolicy iceGatherPolicy) {
        this._iceGatherPolicy = iceGatherPolicy;
    }

    public void setIceServer(IceServer iceServer) {
        this.__iceServers.setValue(iceServer);
    }

    public void setIceServers(IceServer[] iceServerArr) {
        this.__iceServers.replace(iceServerArr);
    }

    public void setLegacyTimeout(boolean z) {
        this._legacyTimeout = z;
    }

    public Future<SessionDescription> setLocalDescription(SessionDescription sessionDescription) {
        Promise promise = new Promise();
        try {
            setLocalDescriptionInternal(promise, sessionDescription);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    private void setLocalDescriptionInternal(final Promise<SessionDescription> promise, SessionDescription sessionDescription) {
        Future<SessionDescription> doSetLocalDescriptionInternal;
        getSessionDescriptionManager().updateLocalDescription(sessionDescription);
        raiseLocalDescription(sessionDescription);
        synchronized (this._connectionLock) {
            if (!getIsTerminatingOrTerminated()) {
                doSetLocalDescriptionInternal = doSetLocalDescriptionInternal(sessionDescription);
            } else {
                throw new RuntimeException(new Exception(StringExtensions.format("Cannot set local description: connection is {0}.", (Object) getState().toString())));
            }
        }
        doSetLocalDescriptionInternal.then((IAction1<SessionDescription>) new IAction1<SessionDescription>() {
            public void invoke(SessionDescription sessionDescription) {
                try {
                    Log.info(StringExtensions.format("Local session description ({0}) for connection {2}:\n{1}", StringExtensions.toLower(sessionDescription.getType().toString()), sessionDescription.getSdpMessage().toString().replace("\r\n", "\n"), ConnectionBase.this.getId()));
                    Message sdpMessage = sessionDescription.getSdpMessage();
                    if (sdpMessage != null) {
                        for (MediaDescription simulcastAttribute : sdpMessage.getMediaDescriptions()) {
                            SimulcastAttribute simulcastAttribute2 = simulcastAttribute.getSimulcastAttribute();
                            if (simulcastAttribute2 != null) {
                                if (simulcastAttribute2.getReceiveDescription() != null) {
                                    Exception exc = new Exception("Receiving simulcast streams is not supported.");
                                    ConnectionBase.this.setError(new Error(ErrorCode.ConnectionSimulcastNotSupported, exc));
                                    throw new RuntimeException(exc);
                                }
                            }
                        }
                    }
                    promise.resolve(sessionDescription);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                promise.reject(exc);
            }
        });
    }

    public Future<SessionDescription> setRemoteDescription(SessionDescription sessionDescription) {
        Promise promise = new Promise();
        try {
            setRemoteDescriptionInternal(promise, sessionDescription);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    private void setRemoteDescriptionInternal(final Promise<SessionDescription> promise, SessionDescription sessionDescription) {
        Future<SessionDescription> doSetRemoteDescriptionInternal;
        Message sdpMessage = sessionDescription.getSdpMessage();
        if (sdpMessage != null) {
            MediaDescription[] mediaDescriptions = sdpMessage.getMediaDescriptions();
            int length = mediaDescriptions.length;
            int i = 0;
            while (i < length) {
                MediaDescription mediaDescription = mediaDescriptions[i];
                SimulcastAttribute simulcastAttribute = mediaDescription.getSimulcastAttribute();
                if (simulcastAttribute != null && simulcastAttribute.getSendDescription() != null) {
                    Exception exc = new Exception("Receiving simulcast streams is not supported.");
                    setError(new Error(ErrorCode.ConnectionSimulcastNotSupported, exc));
                    throw new RuntimeException(exc);
                } else if (mediaDescription.getSsrcGroupSsrcs("SIM") == null) {
                    i++;
                } else {
                    Exception exc2 = new Exception("Receiving simulcast streams is not supported.");
                    setError(new Error(ErrorCode.ConnectionSimulcastNotSupported, exc2));
                    throw new RuntimeException(exc2);
                }
            }
        }
        raiseRemoteDescription(sessionDescription);
        synchronized (this._connectionLock) {
            verifySessionIdAndVersion(sessionDescription);
            if (!getIsTerminatingOrTerminated()) {
                doSetRemoteDescriptionInternal = doSetRemoteDescriptionInternal(sessionDescription);
            } else {
                throw new RuntimeException(new Exception(StringExtensions.format("Cannot set remote description: connection is {0}.", (Object) getState().toString())));
            }
        }
        doSetRemoteDescriptionInternal.then((IAction1<SessionDescription>) new IAction1<SessionDescription>() {
            public void invoke(SessionDescription sessionDescription) {
                Log.info(StringExtensions.format("Remote session description ({0}) for connection {2}:\n{1}", StringExtensions.toLower(sessionDescription.getType().toString()), sessionDescription.getSdpMessage().toString().replace("\r\n", "\n"), ConnectionBase.this.getId()));
                promise.resolve(sessionDescription);
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                promise.reject(exc);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void setSignallingState(SignallingState signallingState) {
        synchronized (this._connectionLock) {
            if (!Global.equals(this.__signallingState, signallingState)) {
                this.__signallingState = signallingState;
                IAction1<TConnection> iAction1 = this._onSignallingStateChange;
                if (iAction1 != null) {
                    try {
                        iAction1.invoke(getInstance());
                    } catch (Exception e) {
                        Log.error(StringExtensions.format("Exception occurred while raising signalling state change to the application code for connection {0}.", (Object) getId()), e);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean setState(ConnectionState connectionState) {
        return setState(connectionState, (Error) null);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011a, code lost:
        if (r1 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x011c, code lost:
        raiseCachedCandidates();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setState(fm.liveswitch.ConnectionState r7, fm.liveswitch.Error r8) {
        /*
            r6 = this;
            java.lang.Object r0 = r6._connectionLock
            monitor-enter(r0)
            fm.liveswitch.ConnectionState r1 = fm.liveswitch.ConnectionState.Failing     // Catch:{ all -> 0x0121 }
            boolean r1 = fm.liveswitch.Global.equals(r7, r1)     // Catch:{ all -> 0x0121 }
            if (r1 == 0) goto L_0x001b
            if (r8 == 0) goto L_0x000e
            goto L_0x001b
        L_0x000e:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ all -> 0x0121 }
            java.lang.Exception r8 = new java.lang.Exception     // Catch:{ all -> 0x0121 }
            java.lang.String r1 = "Transitioning to the failing state requires an error."
            r8.<init>(r1)     // Catch:{ all -> 0x0121 }
            r7.<init>(r8)     // Catch:{ all -> 0x0121 }
            throw r7     // Catch:{ all -> 0x0121 }
        L_0x001b:
            fm.liveswitch.ConnectionState r1 = fm.liveswitch.ConnectionState.Failing     // Catch:{ all -> 0x0121 }
            boolean r1 = fm.liveswitch.Global.equals(r7, r1)     // Catch:{ all -> 0x0121 }
            if (r1 != 0) goto L_0x0033
            if (r8 != 0) goto L_0x0026
            goto L_0x0033
        L_0x0026:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ all -> 0x0121 }
            java.lang.Exception r8 = new java.lang.Exception     // Catch:{ all -> 0x0121 }
            java.lang.String r1 = "An error can only be specified when transitioning to the failing state."
            r8.<init>(r1)     // Catch:{ all -> 0x0121 }
            r7.<init>(r8)     // Catch:{ all -> 0x0121 }
            throw r7     // Catch:{ all -> 0x0121 }
        L_0x0033:
            fm.liveswitch.ConnectionStateMachine r1 = r6.__stateMachine     // Catch:{ all -> 0x0121 }
            boolean r1 = r1.transition(r7)     // Catch:{ all -> 0x0121 }
            if (r1 != 0) goto L_0x006b
            fm.liveswitch.ConnectionState r8 = r6.getState()     // Catch:{ all -> 0x0121 }
            boolean r8 = fm.liveswitch.Global.equals(r7, r8)     // Catch:{ all -> 0x0121 }
            if (r8 != 0) goto L_0x0068
            fm.liveswitch.ConnectionState r8 = fm.liveswitch.ConnectionState.Closing     // Catch:{ all -> 0x0121 }
            boolean r8 = fm.liveswitch.Global.equals(r7, r8)     // Catch:{ all -> 0x0121 }
            if (r8 == 0) goto L_0x0065
            fm.liveswitch.ConnectionState r8 = r6.getState()     // Catch:{ all -> 0x0121 }
            fm.liveswitch.ConnectionState r1 = fm.liveswitch.ConnectionState.Closed     // Catch:{ all -> 0x0121 }
            boolean r8 = fm.liveswitch.Global.equals(r8, r1)     // Catch:{ all -> 0x0121 }
            if (r8 != 0) goto L_0x0068
            fm.liveswitch.ConnectionState r8 = r6.getState()     // Catch:{ all -> 0x0121 }
            fm.liveswitch.ConnectionState r1 = fm.liveswitch.ConnectionState.Failed     // Catch:{ all -> 0x0121 }
            boolean r8 = fm.liveswitch.Global.equals(r8, r1)     // Catch:{ all -> 0x0121 }
            if (r8 != 0) goto L_0x0068
        L_0x0065:
            r6.logInvalidStateTransition(r7)     // Catch:{ all -> 0x0121 }
        L_0x0068:
            r7 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x0121 }
            return r7
        L_0x006b:
            if (r8 == 0) goto L_0x0070
            r6.setError(r8)     // Catch:{ all -> 0x0121 }
        L_0x0070:
            java.lang.String r7 = r6.getId()     // Catch:{ all -> 0x0121 }
            java.lang.String r1 = r6.getExternalId()     // Catch:{ all -> 0x0121 }
            if (r1 == 0) goto L_0x0080
            java.lang.String r2 = "{0} (external ID {1})"
            java.lang.String r7 = fm.liveswitch.StringExtensions.format(r2, r7, r1)     // Catch:{ all -> 0x0121 }
        L_0x0080:
            fm.liveswitch.ConnectionState r1 = r6.getState()     // Catch:{ all -> 0x0121 }
            fm.liveswitch.ConnectionState r2 = fm.liveswitch.ConnectionState.Connecting     // Catch:{ all -> 0x0121 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0121 }
            boolean r2 = fm.liveswitch.Log.getIsInfoEnabled()     // Catch:{ all -> 0x0121 }
            if (r2 == 0) goto L_0x0113
            fm.liveswitch.ConnectionState r2 = r6.getState()     // Catch:{ all -> 0x0121 }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Connecting     // Catch:{ all -> 0x0121 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x0121 }
            if (r2 == 0) goto L_0x00b8
            java.lang.String r2 = "Connection {0} took {1}ms to initialize (offer/answer signalling)."
            java.lang.String r3 = r6.getId()     // Catch:{ all -> 0x0121 }
            fm.liveswitch.ConnectionStateMachine r4 = r6.__stateMachine     // Catch:{ all -> 0x0121 }
            long r4 = r4.getLastStateMillis()     // Catch:{ all -> 0x0121 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0121 }
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)     // Catch:{ all -> 0x0121 }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r2, r3, r4)     // Catch:{ all -> 0x0121 }
            fm.liveswitch.Log.info(r2)     // Catch:{ all -> 0x0121 }
            goto L_0x00df
        L_0x00b8:
            fm.liveswitch.ConnectionState r2 = r6.getState()     // Catch:{ all -> 0x0121 }
            fm.liveswitch.ConnectionState r3 = fm.liveswitch.ConnectionState.Connected     // Catch:{ all -> 0x0121 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x0121 }
            if (r2 == 0) goto L_0x00df
            java.lang.String r2 = "Connection {0} took {1}ms to connect (connectivity checks and secure key exchange)."
            java.lang.String r3 = r6.getId()     // Catch:{ all -> 0x0121 }
            fm.liveswitch.ConnectionStateMachine r4 = r6.__stateMachine     // Catch:{ all -> 0x0121 }
            long r4 = r4.getLastStateMillis()     // Catch:{ all -> 0x0121 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0121 }
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)     // Catch:{ all -> 0x0121 }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r2, r3, r4)     // Catch:{ all -> 0x0121 }
            fm.liveswitch.Log.info(r2)     // Catch:{ all -> 0x0121 }
        L_0x00df:
            r2 = 0
            if (r8 == 0) goto L_0x00e6
            java.lang.String r2 = r8.getDescription()     // Catch:{ all -> 0x0121 }
        L_0x00e6:
            if (r2 != 0) goto L_0x00fe
            java.lang.String r8 = "Setting connection {0} state to {1}."
            fm.liveswitch.ConnectionState r2 = r6.getState()     // Catch:{ all -> 0x0121 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0121 }
            java.lang.String r2 = fm.liveswitch.StringExtensions.toLower(r2)     // Catch:{ all -> 0x0121 }
            java.lang.String r7 = fm.liveswitch.StringExtensions.format(r8, r7, r2)     // Catch:{ all -> 0x0121 }
            fm.liveswitch.Log.info(r7)     // Catch:{ all -> 0x0121 }
            goto L_0x0113
        L_0x00fe:
            java.lang.String r8 = "Setting connection {0} state to {1}. Error: {2}"
            fm.liveswitch.ConnectionState r3 = r6.getState()     // Catch:{ all -> 0x0121 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0121 }
            java.lang.String r3 = fm.liveswitch.StringExtensions.toLower(r3)     // Catch:{ all -> 0x0121 }
            java.lang.String r7 = fm.liveswitch.StringExtensions.format(r8, r7, r3, r2)     // Catch:{ all -> 0x0121 }
            fm.liveswitch.Log.info(r7)     // Catch:{ all -> 0x0121 }
        L_0x0113:
            r6.raiseStateChange()     // Catch:{ all -> 0x0121 }
            r6.processStateChange()     // Catch:{ all -> 0x0121 }
            monitor-exit(r0)     // Catch:{ all -> 0x0121 }
            if (r1 == 0) goto L_0x011f
            r6.raiseCachedCandidates()
        L_0x011f:
            r7 = 1
            return r7
        L_0x0121:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0121 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.ConnectionBase.setState(fm.liveswitch.ConnectionState, fm.liveswitch.Error):boolean");
    }

    public void setTieBreaker(String str) {
        this._tieBreaker = str;
    }

    public void setTimeout(int i) {
        this._timeout = i;
    }

    public void setTrickleIcePolicy(TrickleIcePolicy trickleIcePolicy) {
        this.__trickleIcePolicy = trickleIcePolicy;
        if (Global.equals(trickleIcePolicy, TrickleIcePolicy.FullTrickle)) {
            setUseTrickleIce(true);
        } else if (Global.equals(trickleIcePolicy, TrickleIcePolicy.NotSupported)) {
            setUseTrickleIce(false);
        } else if (Global.equals(trickleIcePolicy, TrickleIcePolicy.HalfTrickle)) {
            setUseTrickleIce(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void setUseTrickleIce(boolean z) {
        this.__useTrickleIce = z;
    }

    /* access modifiers changed from: protected */
    public void unregisterStreamWithSessionDescriptionManager(TStream tstream) {
        getSessionDescriptionManager().removeStream(tstream);
    }

    private void verifySessionIdAndVersion(SessionDescription sessionDescription) {
        if (getRemoteDescription() != null) {
            long sessionId = getRemoteDescription().getSessionId();
            long sessionId2 = sessionDescription.getSessionId();
            long sessionVersion = getRemoteDescription().getSessionVersion();
            long sessionVersion2 = sessionDescription.getSessionVersion();
            if (sessionId != sessionId2) {
                throw new RuntimeException(new Exception(StringExtensions.format("Received new remote description with session id {0} and session version {1}, but current remote description has id {2} and version {3}. Cannot set remote description.", new Object[]{LongExtensions.toString(Long.valueOf(sessionId2)), LongExtensions.toString(Long.valueOf(sessionVersion2)), LongExtensions.toString(Long.valueOf(sessionId)), LongExtensions.toString(Long.valueOf(sessionVersion))})));
            } else if (sessionVersion >= sessionVersion2) {
                throw new RuntimeException(new Exception(StringExtensions.format("Received new remote description with session id {0} and session version {1}, but current remote description has id {2} and version {3}. Cannot set remote description.", new Object[]{LongExtensions.toString(Long.valueOf(sessionId2)), LongExtensions.toString(Long.valueOf(sessionVersion2)), LongExtensions.toString(Long.valueOf(sessionId)), LongExtensions.toString(Long.valueOf(sessionVersion))})));
            }
        }
    }
}
