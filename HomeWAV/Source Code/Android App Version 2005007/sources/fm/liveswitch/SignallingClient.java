package fm.liveswitch;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Marker;

class SignallingClient extends SignallingClientBase {
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(SignallingClient.class);
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientBindEndArgs>> __onBindEnd = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientBindRequestArgs>> __onBindRequest = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientBindResponseArgs>> __onBindResponse = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientConnectEndArgs>> __onConnectEnd = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientConnectRequestArgs>> __onConnectRequest = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientConnectResponseArgs>> __onConnectResponse = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientDisconnectEndArgs>> __onDisconnectEnd = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientDisconnectRequestArgs>> __onDisconnectRequest = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientDisconnectResponseArgs>> __onDisconnectResponse = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientPublishEndArgs>> __onPublishEnd = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientPublishRequestArgs>> __onPublishRequest = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientPublishResponseArgs>> __onPublishResponse = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientServiceEndArgs>> __onServiceEnd = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientServiceRequestArgs>> __onServiceRequest = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientServiceResponseArgs>> __onServiceResponse = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientSubscribeEndArgs>> __onSubscribeEnd = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientSubscribeRequestArgs>> __onSubscribeRequest = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientSubscribeResponseArgs>> __onSubscribeResponse = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientUnbindEndArgs>> __onUnbindEnd = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientUnbindRequestArgs>> __onUnbindRequest = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientUnbindResponseArgs>> __onUnbindResponse = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientUnsubscribeEndArgs>> __onUnsubscribeEnd = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientUnsubscribeRequestArgs>> __onUnsubscribeRequest = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingClient, SignallingClientUnsubscribeResponseArgs>> __onUnsubscribeResponse = new ArrayList();
    private static String _bayeuxMinimumVersion = "1.0";
    private static String _bayeuxVersion = "1.0";
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientBindEndArgs> _onBindEnd = null;
    private static IAction2<SignallingClient, SignallingClientBindRequestArgs> _onBindRequest = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientBindResponseArgs> _onBindResponse = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientConnectEndArgs> _onConnectEnd = null;
    private static IAction2<SignallingClient, SignallingClientConnectRequestArgs> _onConnectRequest = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientConnectResponseArgs> _onConnectResponse = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientDisconnectEndArgs> _onDisconnectEnd = null;
    private static IAction2<SignallingClient, SignallingClientDisconnectRequestArgs> _onDisconnectRequest = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientDisconnectResponseArgs> _onDisconnectResponse = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientPublishEndArgs> _onPublishEnd = null;
    private static IAction2<SignallingClient, SignallingClientPublishRequestArgs> _onPublishRequest = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientPublishResponseArgs> _onPublishResponse = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientServiceEndArgs> _onServiceEnd = null;
    private static IAction2<SignallingClient, SignallingClientServiceRequestArgs> _onServiceRequest = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientServiceResponseArgs> _onServiceResponse = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientSubscribeEndArgs> _onSubscribeEnd = null;
    private static IAction2<SignallingClient, SignallingClientSubscribeRequestArgs> _onSubscribeRequest = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientSubscribeResponseArgs> _onSubscribeResponse = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientUnbindEndArgs> _onUnbindEnd = null;
    private static IAction2<SignallingClient, SignallingClientUnbindRequestArgs> _onUnbindRequest = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientUnbindResponseArgs> _onUnbindResponse = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientUnsubscribeEndArgs> _onUnsubscribeEnd = null;
    private static IAction2<SignallingClient, SignallingClientUnsubscribeRequestArgs> _onUnsubscribeRequest = null;
    /* access modifiers changed from: private */
    public static IAction2<SignallingClient, SignallingClientUnsubscribeResponseArgs> _onUnsubscribeResponse = null;
    private static HashMap<String, String> _requestUrlCache = new HashMap<>();
    private static Object _requestUrlCacheLock = new Object();
    /* access modifiers changed from: private */
    public List<IAction1<SignallingBindFailureArgs>> __onBindFailure;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingBindSuccessArgs>> __onBindSuccess;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingSubscribeReceiveArgs>> __onClientChannelReceive;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingConnectFailureArgs>> __onConnectFailure;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingConnectSuccessArgs>> __onConnectSuccess;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingSubscribeReceiveArgs>> __onDeviceChannelReceive;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingDisconnectCompleteArgs>> __onDisconnectComplete;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingPublishFailureArgs>> __onPublishFailure;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingPublishSuccessArgs>> __onPublishSuccess;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingServerBindArgs>> __onServerBind;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingServerSubscribeArgs>> __onServerSubscribe;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingServerUnbindArgs>> __onServerUnbind;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingServerUnsubscribeArgs>> __onServerUnsubscribe;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingServiceFailureArgs>> __onServiceFailure;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingServiceSuccessArgs>> __onServiceSuccess;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingClient>> __onStateChange;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingSubscribeFailureArgs>> __onSubscribeFailure;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingSubscribeSuccessArgs>> __onSubscribeSuccess;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingUnbindFailureArgs>> __onUnbindFailure;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingUnbindSuccessArgs>> __onUnbindSuccess;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingUnsubscribeFailureArgs>> __onUnsubscribeFailure;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingUnsubscribeSuccessArgs>> __onUnsubscribeSuccess;
    /* access modifiers changed from: private */
    public List<IAction1<SignallingSubscribeReceiveArgs>> __onUserChannelReceive;
    private SignallingClientState __state;
    /* access modifiers changed from: private */
    public Object __stateLock;
    private String __streamRequestUrl;
    private String _authToken;
    private int _batchCounter;
    private Object _batchCounterLock;
    private SignallingRecords _boundRecords;
    private Object _boundRecordsLock;
    private String _clientId;
    /* access modifiers changed from: private */
    public SignallingConnectionType _connectionType;
    private int _counter;
    private Object _counterLock;
    private HashMap<String, HashMap<String, IAction1<SignallingSubscribeReceiveArgs>>> _customOnReceives;
    private Object _customOnReceivesLock;
    private TimeoutTimer _deferrer;
    private String _deviceId;
    private boolean _disableWebSockets;
    private Exception _disconnectException;
    /* access modifiers changed from: private */
    public int _lastInterval;
    private SignallingReconnect _lastReconnect;
    private IAction1<SignallingBindFailureArgs> _onBindFailure;
    private IAction1<SignallingBindSuccessArgs> _onBindSuccess;
    private IAction1<SignallingSubscribeReceiveArgs> _onClientChannelReceive;
    private IAction1<SignallingConnectFailureArgs> _onConnectFailure;
    private IAction1<SignallingConnectSuccessArgs> _onConnectSuccess;
    private IAction1<SignallingSubscribeReceiveArgs> _onDeviceChannelReceive;
    private IAction1<SignallingDisconnectCompleteArgs> _onDisconnectComplete;
    private IAction1<SignallingPublishFailureArgs> _onPublishFailure;
    private IAction1<SignallingPublishSuccessArgs> _onPublishSuccess;
    private IAction1<SignallingServerBindArgs> _onServerBind;
    private IAction1<SignallingServerSubscribeArgs> _onServerSubscribe;
    private IAction1<SignallingServerUnbindArgs> _onServerUnbind;
    private IAction1<SignallingServerUnsubscribeArgs> _onServerUnsubscribe;
    private IAction1<SignallingServiceFailureArgs> _onServiceFailure;
    private IAction1<SignallingServiceSuccessArgs> _onServiceSuccess;
    private IAction1<SignallingClient> _onStateChange;
    private IAction1<SignallingSubscribeFailureArgs> _onSubscribeFailure;
    private IAction1<SignallingSubscribeSuccessArgs> _onSubscribeSuccess;
    private IAction1<SignallingUnbindFailureArgs> _onUnbindFailure;
    private IAction1<SignallingUnbindSuccessArgs> _onUnbindSuccess;
    private IAction1<SignallingUnsubscribeFailureArgs> _onUnsubscribeFailure;
    private IAction1<SignallingUnsubscribeSuccessArgs> _onUnsubscribeSuccess;
    private IAction1<SignallingSubscribeReceiveArgs> _onUserChannelReceive;
    private HashMap<String, ArrayList<SignallingMessage>> _pendingReceives;
    private Object _presenceCacheLock;
    private HashMap<String, HashMap<String, HashMap<String, SignallingRecords>>> _presenceDeviceCache;
    private HashMap<String, HashMap<String, HashMap<String, SignallingRecords>>> _presenceUserCache;
    private boolean _queueActivated;
    private boolean _receivedMessages;
    private HashMap<String, ArrayList<SignallingClientRequest>> _requestQueue;
    /* access modifiers changed from: private */
    public SignallingMessageTransfer _requestTransfer;
    private int _serverTimeout;
    private String _streamId;
    private SignallingMessageTransfer _streamRequestTransfer;
    private HashMap<String, HashMap<String, Object>> _subscribedChannels;
    private Object _subscribedChannelsLock;
    private HashMap<String, HashMap<String, IAction1<SignallingSubscribePresenceArgs>>> _subscribedOnPresences;
    /* access modifiers changed from: private */
    public HashMap<String, HashMap<String, Object>> _subscribedOnPresencesDynamicProperties;
    private HashMap<String, HashMap<String, IAction1<SignallingSubscribeReceiveArgs>>> _subscribedOnReceives;
    /* access modifiers changed from: private */
    public HashMap<String, HashMap<String, Object>> _subscribedOnReceivesDynamicProperties;
    /* access modifiers changed from: private */
    public Object _subscribedOnReceivesLock;
    /* access modifiers changed from: private */
    public SignallingConnectionType[] _supportedConnectionTypes;
    private NullableBoolean _synchronous;
    private String _token;
    private String _userId;
    /* access modifiers changed from: private */
    public boolean _webSocketOpened;

    private void activateStream() {
        processQueue(true);
        this._receivedMessages = false;
        stream();
    }

    /* access modifiers changed from: private */
    public void addBoundRecords(SignallingRecord[] signallingRecordArr) {
        synchronized (this._boundRecordsLock) {
            for (SignallingRecord add : signallingRecordArr) {
                this._boundRecords.add(add);
            }
        }
    }

    public static void addOnBindEnd(IAction2<SignallingClient, SignallingClientBindEndArgs> iAction2) {
        if (iAction2 != null) {
            if (_onBindEnd == null) {
                _onBindEnd = new IAction2<SignallingClient, SignallingClientBindEndArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientBindEndArgs signallingClientBindEndArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onBindEnd).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientBindEndArgs);
                        }
                    }
                };
            }
            __onBindEnd.add(iAction2);
        }
    }

    public void addOnBindFailure(IAction1<SignallingBindFailureArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onBindFailure == null) {
                this._onBindFailure = new IAction1<SignallingBindFailureArgs>() {
                    public void invoke(SignallingBindFailureArgs signallingBindFailureArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onBindFailure).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingBindFailureArgs);
                        }
                    }
                };
            }
            this.__onBindFailure.add(iAction1);
        }
    }

    public static void addOnBindRequest(IAction2<SignallingClient, SignallingClientBindRequestArgs> iAction2) {
        if (iAction2 != null) {
            if (_onBindRequest == null) {
                _onBindRequest = new IAction2<SignallingClient, SignallingClientBindRequestArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientBindRequestArgs signallingClientBindRequestArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onBindRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientBindRequestArgs);
                        }
                    }
                };
            }
            __onBindRequest.add(iAction2);
        }
    }

    public static void addOnBindResponse(IAction2<SignallingClient, SignallingClientBindResponseArgs> iAction2) {
        if (iAction2 != null) {
            if (_onBindResponse == null) {
                _onBindResponse = new IAction2<SignallingClient, SignallingClientBindResponseArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientBindResponseArgs signallingClientBindResponseArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onBindResponse).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientBindResponseArgs);
                        }
                    }
                };
            }
            __onBindResponse.add(iAction2);
        }
    }

    public void addOnBindSuccess(IAction1<SignallingBindSuccessArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onBindSuccess == null) {
                this._onBindSuccess = new IAction1<SignallingBindSuccessArgs>() {
                    public void invoke(SignallingBindSuccessArgs signallingBindSuccessArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onBindSuccess).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingBindSuccessArgs);
                        }
                    }
                };
            }
            this.__onBindSuccess.add(iAction1);
        }
    }

    public void addOnClientChannelReceive(IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onClientChannelReceive == null) {
                this._onClientChannelReceive = new IAction1<SignallingSubscribeReceiveArgs>() {
                    public void invoke(SignallingSubscribeReceiveArgs signallingSubscribeReceiveArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onClientChannelReceive).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingSubscribeReceiveArgs);
                        }
                    }
                };
            }
            this.__onClientChannelReceive.add(iAction1);
        }
    }

    public static void addOnConnectEnd(IAction2<SignallingClient, SignallingClientConnectEndArgs> iAction2) {
        if (iAction2 != null) {
            if (_onConnectEnd == null) {
                _onConnectEnd = new IAction2<SignallingClient, SignallingClientConnectEndArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientConnectEndArgs signallingClientConnectEndArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onConnectEnd).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientConnectEndArgs);
                        }
                    }
                };
            }
            __onConnectEnd.add(iAction2);
        }
    }

    public void addOnConnectFailure(IAction1<SignallingConnectFailureArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onConnectFailure == null) {
                this._onConnectFailure = new IAction1<SignallingConnectFailureArgs>() {
                    public void invoke(SignallingConnectFailureArgs signallingConnectFailureArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onConnectFailure).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingConnectFailureArgs);
                        }
                    }
                };
            }
            this.__onConnectFailure.add(iAction1);
        }
    }

    public static void addOnConnectRequest(IAction2<SignallingClient, SignallingClientConnectRequestArgs> iAction2) {
        if (iAction2 != null) {
            if (_onConnectRequest == null) {
                _onConnectRequest = new IAction2<SignallingClient, SignallingClientConnectRequestArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientConnectRequestArgs signallingClientConnectRequestArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onConnectRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientConnectRequestArgs);
                        }
                    }
                };
            }
            __onConnectRequest.add(iAction2);
        }
    }

    public static void addOnConnectResponse(IAction2<SignallingClient, SignallingClientConnectResponseArgs> iAction2) {
        if (iAction2 != null) {
            if (_onConnectResponse == null) {
                _onConnectResponse = new IAction2<SignallingClient, SignallingClientConnectResponseArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientConnectResponseArgs signallingClientConnectResponseArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onConnectResponse).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientConnectResponseArgs);
                        }
                    }
                };
            }
            __onConnectResponse.add(iAction2);
        }
    }

    public void addOnConnectSuccess(IAction1<SignallingConnectSuccessArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onConnectSuccess == null) {
                this._onConnectSuccess = new IAction1<SignallingConnectSuccessArgs>() {
                    public void invoke(SignallingConnectSuccessArgs signallingConnectSuccessArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onConnectSuccess).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingConnectSuccessArgs);
                        }
                    }
                };
            }
            this.__onConnectSuccess.add(iAction1);
        }
    }

    public void addOnDeviceChannelReceive(IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onDeviceChannelReceive == null) {
                this._onDeviceChannelReceive = new IAction1<SignallingSubscribeReceiveArgs>() {
                    public void invoke(SignallingSubscribeReceiveArgs signallingSubscribeReceiveArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onDeviceChannelReceive).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingSubscribeReceiveArgs);
                        }
                    }
                };
            }
            this.__onDeviceChannelReceive.add(iAction1);
        }
    }

    public void addOnDisconnectComplete(IAction1<SignallingDisconnectCompleteArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onDisconnectComplete == null) {
                this._onDisconnectComplete = new IAction1<SignallingDisconnectCompleteArgs>() {
                    public void invoke(SignallingDisconnectCompleteArgs signallingDisconnectCompleteArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onDisconnectComplete).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingDisconnectCompleteArgs);
                        }
                    }
                };
            }
            this.__onDisconnectComplete.add(iAction1);
        }
    }

    public static void addOnDisconnectEnd(IAction2<SignallingClient, SignallingClientDisconnectEndArgs> iAction2) {
        if (iAction2 != null) {
            if (_onDisconnectEnd == null) {
                _onDisconnectEnd = new IAction2<SignallingClient, SignallingClientDisconnectEndArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientDisconnectEndArgs signallingClientDisconnectEndArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onDisconnectEnd).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientDisconnectEndArgs);
                        }
                    }
                };
            }
            __onDisconnectEnd.add(iAction2);
        }
    }

    public static void addOnDisconnectRequest(IAction2<SignallingClient, SignallingClientDisconnectRequestArgs> iAction2) {
        if (iAction2 != null) {
            if (_onDisconnectRequest == null) {
                _onDisconnectRequest = new IAction2<SignallingClient, SignallingClientDisconnectRequestArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientDisconnectRequestArgs signallingClientDisconnectRequestArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onDisconnectRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientDisconnectRequestArgs);
                        }
                    }
                };
            }
            __onDisconnectRequest.add(iAction2);
        }
    }

    public static void addOnDisconnectResponse(IAction2<SignallingClient, SignallingClientDisconnectResponseArgs> iAction2) {
        if (iAction2 != null) {
            if (_onDisconnectResponse == null) {
                _onDisconnectResponse = new IAction2<SignallingClient, SignallingClientDisconnectResponseArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientDisconnectResponseArgs signallingClientDisconnectResponseArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onDisconnectResponse).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientDisconnectResponseArgs);
                        }
                    }
                };
            }
            __onDisconnectResponse.add(iAction2);
        }
    }

    public static void addOnPublishEnd(IAction2<SignallingClient, SignallingClientPublishEndArgs> iAction2) {
        if (iAction2 != null) {
            if (_onPublishEnd == null) {
                _onPublishEnd = new IAction2<SignallingClient, SignallingClientPublishEndArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientPublishEndArgs signallingClientPublishEndArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onPublishEnd).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientPublishEndArgs);
                        }
                    }
                };
            }
            __onPublishEnd.add(iAction2);
        }
    }

    public void addOnPublishFailure(IAction1<SignallingPublishFailureArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onPublishFailure == null) {
                this._onPublishFailure = new IAction1<SignallingPublishFailureArgs>() {
                    public void invoke(SignallingPublishFailureArgs signallingPublishFailureArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onPublishFailure).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingPublishFailureArgs);
                        }
                    }
                };
            }
            this.__onPublishFailure.add(iAction1);
        }
    }

    public static void addOnPublishRequest(IAction2<SignallingClient, SignallingClientPublishRequestArgs> iAction2) {
        if (iAction2 != null) {
            if (_onPublishRequest == null) {
                _onPublishRequest = new IAction2<SignallingClient, SignallingClientPublishRequestArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientPublishRequestArgs signallingClientPublishRequestArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onPublishRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientPublishRequestArgs);
                        }
                    }
                };
            }
            __onPublishRequest.add(iAction2);
        }
    }

    public static void addOnPublishResponse(IAction2<SignallingClient, SignallingClientPublishResponseArgs> iAction2) {
        if (iAction2 != null) {
            if (_onPublishResponse == null) {
                _onPublishResponse = new IAction2<SignallingClient, SignallingClientPublishResponseArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientPublishResponseArgs signallingClientPublishResponseArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onPublishResponse).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientPublishResponseArgs);
                        }
                    }
                };
            }
            __onPublishResponse.add(iAction2);
        }
    }

    public void addOnPublishSuccess(IAction1<SignallingPublishSuccessArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onPublishSuccess == null) {
                this._onPublishSuccess = new IAction1<SignallingPublishSuccessArgs>() {
                    public void invoke(SignallingPublishSuccessArgs signallingPublishSuccessArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onPublishSuccess).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingPublishSuccessArgs);
                        }
                    }
                };
            }
            this.__onPublishSuccess.add(iAction1);
        }
    }

    public void addOnServerBind(IAction1<SignallingServerBindArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onServerBind == null) {
                this._onServerBind = new IAction1<SignallingServerBindArgs>() {
                    public void invoke(SignallingServerBindArgs signallingServerBindArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onServerBind).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingServerBindArgs);
                        }
                    }
                };
            }
            this.__onServerBind.add(iAction1);
        }
    }

    public void addOnServerSubscribe(IAction1<SignallingServerSubscribeArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onServerSubscribe == null) {
                this._onServerSubscribe = new IAction1<SignallingServerSubscribeArgs>() {
                    public void invoke(SignallingServerSubscribeArgs signallingServerSubscribeArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onServerSubscribe).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingServerSubscribeArgs);
                        }
                    }
                };
            }
            this.__onServerSubscribe.add(iAction1);
        }
    }

    public void addOnServerUnbind(IAction1<SignallingServerUnbindArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onServerUnbind == null) {
                this._onServerUnbind = new IAction1<SignallingServerUnbindArgs>() {
                    public void invoke(SignallingServerUnbindArgs signallingServerUnbindArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onServerUnbind).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingServerUnbindArgs);
                        }
                    }
                };
            }
            this.__onServerUnbind.add(iAction1);
        }
    }

    public void addOnServerUnsubscribe(IAction1<SignallingServerUnsubscribeArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onServerUnsubscribe == null) {
                this._onServerUnsubscribe = new IAction1<SignallingServerUnsubscribeArgs>() {
                    public void invoke(SignallingServerUnsubscribeArgs signallingServerUnsubscribeArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onServerUnsubscribe).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingServerUnsubscribeArgs);
                        }
                    }
                };
            }
            this.__onServerUnsubscribe.add(iAction1);
        }
    }

    public static void addOnServiceEnd(IAction2<SignallingClient, SignallingClientServiceEndArgs> iAction2) {
        if (iAction2 != null) {
            if (_onServiceEnd == null) {
                _onServiceEnd = new IAction2<SignallingClient, SignallingClientServiceEndArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientServiceEndArgs signallingClientServiceEndArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onServiceEnd).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientServiceEndArgs);
                        }
                    }
                };
            }
            __onServiceEnd.add(iAction2);
        }
    }

    public void addOnServiceFailure(IAction1<SignallingServiceFailureArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onServiceFailure == null) {
                this._onServiceFailure = new IAction1<SignallingServiceFailureArgs>() {
                    public void invoke(SignallingServiceFailureArgs signallingServiceFailureArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onServiceFailure).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingServiceFailureArgs);
                        }
                    }
                };
            }
            this.__onServiceFailure.add(iAction1);
        }
    }

    public static void addOnServiceRequest(IAction2<SignallingClient, SignallingClientServiceRequestArgs> iAction2) {
        if (iAction2 != null) {
            if (_onServiceRequest == null) {
                _onServiceRequest = new IAction2<SignallingClient, SignallingClientServiceRequestArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientServiceRequestArgs signallingClientServiceRequestArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onServiceRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientServiceRequestArgs);
                        }
                    }
                };
            }
            __onServiceRequest.add(iAction2);
        }
    }

    public static void addOnServiceResponse(IAction2<SignallingClient, SignallingClientServiceResponseArgs> iAction2) {
        if (iAction2 != null) {
            if (_onServiceResponse == null) {
                _onServiceResponse = new IAction2<SignallingClient, SignallingClientServiceResponseArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientServiceResponseArgs signallingClientServiceResponseArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onServiceResponse).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientServiceResponseArgs);
                        }
                    }
                };
            }
            __onServiceResponse.add(iAction2);
        }
    }

    public void addOnServiceSuccess(IAction1<SignallingServiceSuccessArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onServiceSuccess == null) {
                this._onServiceSuccess = new IAction1<SignallingServiceSuccessArgs>() {
                    public void invoke(SignallingServiceSuccessArgs signallingServiceSuccessArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onServiceSuccess).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingServiceSuccessArgs);
                        }
                    }
                };
            }
            this.__onServiceSuccess.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<SignallingClient> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<SignallingClient>() {
                    public void invoke(SignallingClient signallingClient) {
                        Iterator it = new ArrayList(SignallingClient.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingClient);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    public static void addOnSubscribeEnd(IAction2<SignallingClient, SignallingClientSubscribeEndArgs> iAction2) {
        if (iAction2 != null) {
            if (_onSubscribeEnd == null) {
                _onSubscribeEnd = new IAction2<SignallingClient, SignallingClientSubscribeEndArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientSubscribeEndArgs signallingClientSubscribeEndArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onSubscribeEnd).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientSubscribeEndArgs);
                        }
                    }
                };
            }
            __onSubscribeEnd.add(iAction2);
        }
    }

    public void addOnSubscribeFailure(IAction1<SignallingSubscribeFailureArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onSubscribeFailure == null) {
                this._onSubscribeFailure = new IAction1<SignallingSubscribeFailureArgs>() {
                    public void invoke(SignallingSubscribeFailureArgs signallingSubscribeFailureArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onSubscribeFailure).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingSubscribeFailureArgs);
                        }
                    }
                };
            }
            this.__onSubscribeFailure.add(iAction1);
        }
    }

    public static void addOnSubscribeRequest(IAction2<SignallingClient, SignallingClientSubscribeRequestArgs> iAction2) {
        if (iAction2 != null) {
            if (_onSubscribeRequest == null) {
                _onSubscribeRequest = new IAction2<SignallingClient, SignallingClientSubscribeRequestArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientSubscribeRequestArgs signallingClientSubscribeRequestArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onSubscribeRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientSubscribeRequestArgs);
                        }
                    }
                };
            }
            __onSubscribeRequest.add(iAction2);
        }
    }

    public static void addOnSubscribeResponse(IAction2<SignallingClient, SignallingClientSubscribeResponseArgs> iAction2) {
        if (iAction2 != null) {
            if (_onSubscribeResponse == null) {
                _onSubscribeResponse = new IAction2<SignallingClient, SignallingClientSubscribeResponseArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientSubscribeResponseArgs signallingClientSubscribeResponseArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onSubscribeResponse).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientSubscribeResponseArgs);
                        }
                    }
                };
            }
            __onSubscribeResponse.add(iAction2);
        }
    }

    public void addOnSubscribeSuccess(IAction1<SignallingSubscribeSuccessArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onSubscribeSuccess == null) {
                this._onSubscribeSuccess = new IAction1<SignallingSubscribeSuccessArgs>() {
                    public void invoke(SignallingSubscribeSuccessArgs signallingSubscribeSuccessArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onSubscribeSuccess).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingSubscribeSuccessArgs);
                        }
                    }
                };
            }
            this.__onSubscribeSuccess.add(iAction1);
        }
    }

    public static void addOnUnbindEnd(IAction2<SignallingClient, SignallingClientUnbindEndArgs> iAction2) {
        if (iAction2 != null) {
            if (_onUnbindEnd == null) {
                _onUnbindEnd = new IAction2<SignallingClient, SignallingClientUnbindEndArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientUnbindEndArgs signallingClientUnbindEndArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onUnbindEnd).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientUnbindEndArgs);
                        }
                    }
                };
            }
            __onUnbindEnd.add(iAction2);
        }
    }

    public void addOnUnbindFailure(IAction1<SignallingUnbindFailureArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onUnbindFailure == null) {
                this._onUnbindFailure = new IAction1<SignallingUnbindFailureArgs>() {
                    public void invoke(SignallingUnbindFailureArgs signallingUnbindFailureArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onUnbindFailure).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingUnbindFailureArgs);
                        }
                    }
                };
            }
            this.__onUnbindFailure.add(iAction1);
        }
    }

    public static void addOnUnbindRequest(IAction2<SignallingClient, SignallingClientUnbindRequestArgs> iAction2) {
        if (iAction2 != null) {
            if (_onUnbindRequest == null) {
                _onUnbindRequest = new IAction2<SignallingClient, SignallingClientUnbindRequestArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientUnbindRequestArgs signallingClientUnbindRequestArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onUnbindRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientUnbindRequestArgs);
                        }
                    }
                };
            }
            __onUnbindRequest.add(iAction2);
        }
    }

    public static void addOnUnbindResponse(IAction2<SignallingClient, SignallingClientUnbindResponseArgs> iAction2) {
        if (iAction2 != null) {
            if (_onUnbindResponse == null) {
                _onUnbindResponse = new IAction2<SignallingClient, SignallingClientUnbindResponseArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientUnbindResponseArgs signallingClientUnbindResponseArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onUnbindResponse).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientUnbindResponseArgs);
                        }
                    }
                };
            }
            __onUnbindResponse.add(iAction2);
        }
    }

    public void addOnUnbindSuccess(IAction1<SignallingUnbindSuccessArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onUnbindSuccess == null) {
                this._onUnbindSuccess = new IAction1<SignallingUnbindSuccessArgs>() {
                    public void invoke(SignallingUnbindSuccessArgs signallingUnbindSuccessArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onUnbindSuccess).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingUnbindSuccessArgs);
                        }
                    }
                };
            }
            this.__onUnbindSuccess.add(iAction1);
        }
    }

    public static void addOnUnsubscribeEnd(IAction2<SignallingClient, SignallingClientUnsubscribeEndArgs> iAction2) {
        if (iAction2 != null) {
            if (_onUnsubscribeEnd == null) {
                _onUnsubscribeEnd = new IAction2<SignallingClient, SignallingClientUnsubscribeEndArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientUnsubscribeEndArgs signallingClientUnsubscribeEndArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onUnsubscribeEnd).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientUnsubscribeEndArgs);
                        }
                    }
                };
            }
            __onUnsubscribeEnd.add(iAction2);
        }
    }

    public void addOnUnsubscribeFailure(IAction1<SignallingUnsubscribeFailureArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onUnsubscribeFailure == null) {
                this._onUnsubscribeFailure = new IAction1<SignallingUnsubscribeFailureArgs>() {
                    public void invoke(SignallingUnsubscribeFailureArgs signallingUnsubscribeFailureArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onUnsubscribeFailure).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingUnsubscribeFailureArgs);
                        }
                    }
                };
            }
            this.__onUnsubscribeFailure.add(iAction1);
        }
    }

    public static void addOnUnsubscribeRequest(IAction2<SignallingClient, SignallingClientUnsubscribeRequestArgs> iAction2) {
        if (iAction2 != null) {
            if (_onUnsubscribeRequest == null) {
                _onUnsubscribeRequest = new IAction2<SignallingClient, SignallingClientUnsubscribeRequestArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientUnsubscribeRequestArgs signallingClientUnsubscribeRequestArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onUnsubscribeRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientUnsubscribeRequestArgs);
                        }
                    }
                };
            }
            __onUnsubscribeRequest.add(iAction2);
        }
    }

    public static void addOnUnsubscribeResponse(IAction2<SignallingClient, SignallingClientUnsubscribeResponseArgs> iAction2) {
        if (iAction2 != null) {
            if (_onUnsubscribeResponse == null) {
                _onUnsubscribeResponse = new IAction2<SignallingClient, SignallingClientUnsubscribeResponseArgs>() {
                    public void invoke(SignallingClient signallingClient, SignallingClientUnsubscribeResponseArgs signallingClientUnsubscribeResponseArgs) {
                        Iterator it = new ArrayList(SignallingClient.__onUnsubscribeResponse).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingClient, signallingClientUnsubscribeResponseArgs);
                        }
                    }
                };
            }
            __onUnsubscribeResponse.add(iAction2);
        }
    }

    public void addOnUnsubscribeSuccess(IAction1<SignallingUnsubscribeSuccessArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onUnsubscribeSuccess == null) {
                this._onUnsubscribeSuccess = new IAction1<SignallingUnsubscribeSuccessArgs>() {
                    public void invoke(SignallingUnsubscribeSuccessArgs signallingUnsubscribeSuccessArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onUnsubscribeSuccess).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingUnsubscribeSuccessArgs);
                        }
                    }
                };
            }
            this.__onUnsubscribeSuccess.add(iAction1);
        }
    }

    public void addOnUserChannelReceive(IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        if (iAction1 != null) {
            if (this._onUserChannelReceive == null) {
                this._onUserChannelReceive = new IAction1<SignallingSubscribeReceiveArgs>() {
                    public void invoke(SignallingSubscribeReceiveArgs signallingSubscribeReceiveArgs) {
                        Iterator it = new ArrayList(SignallingClient.this.__onUserChannelReceive).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingSubscribeReceiveArgs);
                        }
                    }
                };
            }
            this.__onUserChannelReceive.add(iAction1);
        }
    }

    /* access modifiers changed from: private */
    public void addSubscribedChannels(String str, String[] strArr) {
        synchronized (this._subscribedChannelsLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this._subscribedChannels, str, holder);
            HashMap hashMap = (HashMap) holder.getValue();
            if (!tryGetValue) {
                hashMap = new HashMap();
                HashMapExtensions.set(HashMapExtensions.getItem(this._subscribedChannels), str, hashMap);
            }
            for (String str2 : strArr) {
                if (!hashMap.containsKey(str2)) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), str2, new Object());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public int addSubscribedOnPresence(String str, String[] strArr, IAction1<SignallingSubscribePresenceArgs> iAction1, HashMap<String, Object> hashMap, int i) {
        if (iAction1 != null) {
            synchronized (this._subscribedOnReceivesLock) {
                Holder holder = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(this._subscribedOnPresences, str, holder);
                HashMap hashMap2 = (HashMap) holder.getValue();
                if (!tryGetValue) {
                    hashMap2 = new HashMap();
                    HashMapExtensions.set(HashMapExtensions.getItem(this._subscribedOnPresences), str, hashMap2);
                }
                for (String str2 : strArr) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap2), str2, iAction1);
                    HashMapExtensions.set(HashMapExtensions.getItem(this._subscribedOnPresencesDynamicProperties), getSubscriptionKey(str2, str), hashMap);
                }
                i = processPendingReceives(strArr, i);
            }
        }
        return i;
    }

    /* access modifiers changed from: private */
    public int addSubscribedOnReceive(String str, String[] strArr, IAction1<SignallingSubscribeReceiveArgs> iAction1, HashMap<String, Object> hashMap, int i) {
        int processPendingReceives;
        synchronized (this._subscribedOnReceivesLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this._subscribedOnReceives, str, holder);
            HashMap hashMap2 = (HashMap) holder.getValue();
            if (!tryGetValue) {
                hashMap2 = new HashMap();
                HashMapExtensions.set(HashMapExtensions.getItem(this._subscribedOnReceives), str, hashMap2);
            }
            for (String str2 : strArr) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap2), str2, iAction1);
                HashMapExtensions.set(HashMapExtensions.getItem(this._subscribedOnReceivesDynamicProperties), getSubscriptionKey(str2, str), hashMap);
            }
            processPendingReceives = processPendingReceives(strArr, i);
        }
        return processPendingReceives;
    }

    private void addToQueue(SignallingClientRequest signallingClientRequest, String str, boolean z, int i, int i2) {
        if (str == null) {
            str = super.getRequestUrl();
        }
        Object[] objArr = new Object[4];
        objArr[0] = IntegerExtensions.toString(Integer.valueOf(i));
        objArr[1] = IntegerExtensions.toString(Integer.valueOf(i2));
        objArr[2] = z ? "1" : "0";
        objArr[3] = str;
        String format = StringExtensions.format("{0}+{1}|{2}{3}", objArr);
        synchronized (this.__stateLock) {
            if (!this._requestQueue.containsKey(format)) {
                HashMapExtensions.set(HashMapExtensions.getItem(this._requestQueue), format, new ArrayList());
            }
            HashMapExtensions.getItem(this._requestQueue).get(format).add(signallingClientRequest);
        }
    }

    public SignallingClient bind(final SignallingBindArgs signallingBindArgs) {
        synchronized (this.__stateLock) {
            if (signallingBindArgs.getRecords() != null) {
                if (ArrayExtensions.getLength((Object[]) signallingBindArgs.getRecords()) != 0) {
                    SignallingRecord[] records = signallingBindArgs.getRecords();
                    int length = records.length;
                    int i = 0;
                    while (i < length) {
                        SignallingRecord signallingRecord = records[i];
                        if (signallingRecord.getKey() == null) {
                            raiseBindFailure(signallingBindArgs, createFailureResponse(signallingBindArgs, "Records cannot have null keys."));
                            return this;
                        } else if (signallingRecord.getValueJson() == null) {
                            raiseBindFailure(signallingBindArgs, createFailureResponse(signallingBindArgs, "Records cannot have null values."));
                            return this;
                        } else {
                            i++;
                        }
                    }
                    if (!raiseRequestEvent(_onBindRequest, new SignallingClientBindRequestArgs(signallingBindArgs), "OnBindRequest")) {
                        SignallingClientResponse createFailureResponse = createFailureResponse(signallingBindArgs, "Application cancelled request.");
                        raiseResponseEvent(_onBindResponse, new SignallingClientBindResponseArgs(signallingBindArgs), "OnBindResponse", createFailureResponse);
                        raiseBindFailure(signallingBindArgs, createFailureResponse);
                        raiseEndEvent(_onBindEnd, new SignallingClientBindEndArgs(signallingBindArgs), "OnBindEnd", createFailureResponse);
                        return this;
                    }
                    SignallingClientRequest signallingClientRequest = new SignallingClientRequest();
                    SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.getBind());
                    signallingMessage.setValidate(false);
                    signallingMessage.setRecords(signallingBindArgs.getRecords());
                    signallingMessage.setIsPrivate(new NullableBoolean(signallingBindArgs.getIsPrivate()));
                    signallingMessage.setExtensions(signallingBindArgs.getExtensions());
                    signallingClientRequest.setMessage(signallingMessage);
                    signallingClientRequest.setCallback(new IAction1<SignallingClientResponse>() {
                        public void invoke(SignallingClientResponse signallingClientResponse) {
                            synchronized (SignallingClient.this.__stateLock) {
                                SignallingClient.this.raiseResponseEvent(SignallingClient._onBindResponse, new SignallingClientBindResponseArgs(signallingBindArgs), "OnBindResponse", signallingClientResponse);
                                if (signallingClientResponse.getException() != null) {
                                    SignallingClient.this.raiseBindFailure(signallingBindArgs, signallingClientResponse);
                                } else {
                                    SignallingClient.this.addBoundRecords(signallingClientResponse.getMessage().getRecords());
                                    SignallingClient.this.raiseBindSuccess(signallingBindArgs, signallingClientResponse);
                                }
                                SignallingClient.this.raiseEndEvent(SignallingClient._onBindEnd, new SignallingClientBindEndArgs(signallingBindArgs), "OnBindEnd", signallingClientResponse);
                            }
                        }
                    });
                    addToQueue(signallingClientRequest, signallingBindArgs.getRequestUrl(), checkSynchronous(signallingBindArgs.getSynchronous()), signallingBindArgs.getRequestTimeout().getHasValue() ? signallingBindArgs.getRequestTimeout().getValue() : 0, signallingBindArgs.getRequestMaxRetries().getHasValue() ? signallingBindArgs.getRequestMaxRetries().getValue() : 0);
                    processQueue(false);
                    return this;
                }
            }
            raiseBindFailure(signallingBindArgs, createFailureResponse(signallingBindArgs, "Records cannot be null."));
            return this;
        }
    }

    public Future<SignallingBindSuccessArgs> bindAsync(SignallingBindArgs signallingBindArgs) {
        return doBindAsync(signallingBindArgs, signallingBindArgs.getOnSuccess(), signallingBindArgs.getOnFailure(), new Promise());
    }

    public Future<SignallingBindSuccessArgs> bindAsync(SignallingRecord signallingRecord) {
        return bindAsync(new SignallingBindArgs(signallingRecord));
    }

    public Future<SignallingBindSuccessArgs> bindAsync(SignallingRecord[] signallingRecordArr) {
        return bindAsync(new SignallingBindArgs(signallingRecordArr));
    }

    /* access modifiers changed from: package-private */
    public boolean checkSynchronous(NullableBoolean nullableBoolean) {
        if (nullableBoolean.getHasValue()) {
            return nullableBoolean.getValue();
        }
        if (getSynchronous().getHasValue()) {
            return getSynchronous().getValue();
        }
        return false;
    }

    private void clearBoundRecords() {
        synchronized (this._boundRecordsLock) {
            this._boundRecords.clear();
        }
    }

    private void clearSubscribedChannels() {
        synchronized (this._subscribedChannelsLock) {
            this._subscribedChannels.clear();
        }
        synchronized (this._customOnReceivesLock) {
            this._customOnReceives.clear();
        }
    }

    public SignallingClient connect() {
        return connect(new SignallingConnectArgs());
    }

    public SignallingClient connect(final SignallingConnectArgs signallingConnectArgs) {
        synchronized (this.__stateLock) {
            if (!raiseRequestEvent(_onConnectRequest, new SignallingClientConnectRequestArgs(signallingConnectArgs), "OnConnectRequest")) {
                SignallingClientResponse createFailureResponse = createFailureResponse(signallingConnectArgs, "Application cancelled request.");
                raiseResponseEvent(_onConnectResponse, new SignallingClientConnectResponseArgs(signallingConnectArgs), "OnConnectResponse", createFailureResponse);
                raiseConnectFailure(signallingConnectArgs, createFailureResponse);
                raiseEndEvent(_onConnectEnd, new SignallingClientConnectEndArgs(signallingConnectArgs), "OnConnectEnd", createFailureResponse);
                return this;
            }
            if (!Global.equals(getState(), SignallingClientState.Connected)) {
                if (!Global.equals(getState(), SignallingClientState.Connecting)) {
                    setState(SignallingClientState.Connecting);
                    ArrayList arrayList = new ArrayList();
                    if (!getDisableWebSockets()) {
                        arrayList.add(SignallingConnectionType.WebSocket);
                    }
                    arrayList.add(SignallingConnectionType.LongPolling);
                    this._supportedConnectionTypes = (SignallingConnectionType[]) arrayList.toArray(new SignallingConnectionType[0]);
                    setDisconnectException((Exception) null);
                    SignallingClientRequest signallingClientRequest = new SignallingClientRequest();
                    SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.getHandshake());
                    signallingMessage.setClientId(getClientId());
                    signallingMessage.setStreamId(getStreamId());
                    signallingMessage.setAuthToken(getAuthToken());
                    signallingMessage.setUserId(getUserId());
                    signallingMessage.setDeviceId(getDeviceId());
                    signallingMessage.setVersion(_bayeuxVersion);
                    signallingMessage.setMinimumVersion(_bayeuxMinimumVersion);
                    signallingMessage.setSupportedConnectionTypes(this._supportedConnectionTypes);
                    signallingMessage.setExtensions(signallingConnectArgs.getExtensions());
                    signallingClientRequest.setMessage(signallingMessage);
                    signallingClientRequest.setCallback(new IAction1<SignallingClientResponse>() {
                        public void invoke(SignallingClientResponse signallingClientResponse) {
                            synchronized (SignallingClient.this.__stateLock) {
                                SignallingClient.this.raiseResponseEvent(SignallingClient._onConnectResponse, new SignallingClientConnectResponseArgs(signallingConnectArgs), "OnConnectResponse", signallingClientResponse);
                                if (signallingClientResponse.getException() == null) {
                                    SignallingClient.this.setClientId(signallingClientResponse.getMessage().getClientId());
                                    SignallingClient.this.setStreamId(signallingClientResponse.getMessage().getStreamId());
                                    SignallingClient.this.setAuthToken(signallingClientResponse.getMessage().getAuthToken());
                                    NullableInteger serverTimeout = signallingClientResponse.getMessage().getServerTimeout();
                                    if (serverTimeout.getHasValue()) {
                                        SignallingClient.this.setServerTimeout(serverTimeout.getValue());
                                    } else {
                                        SignallingClient.this.setServerTimeout(25000);
                                    }
                                    int i = Integer.MAX_VALUE;
                                    for (SignallingConnectionType signallingConnectionType : signallingClientResponse.getMessage().getSupportedConnectionTypes()) {
                                        int i2 = 0;
                                        while (true) {
                                            if (i2 >= ArrayExtensions.getLength((Object[]) SignallingClient.this._supportedConnectionTypes)) {
                                                break;
                                            } else if (!Global.equals(SignallingClient.this._supportedConnectionTypes[i2], signallingConnectionType)) {
                                                i2++;
                                            } else if (i2 < i) {
                                                i = i2;
                                            }
                                        }
                                    }
                                    if (i >= 0) {
                                        if (i <= ArrayExtensions.getLength((Object[]) SignallingClient.this._supportedConnectionTypes)) {
                                            SignallingClient signallingClient = SignallingClient.this;
                                            SignallingConnectionType unused = signallingClient._connectionType = signallingClient._supportedConnectionTypes[i];
                                        }
                                    }
                                    signallingClientResponse.setFailureSource(FailureSource.Message);
                                    signallingClientResponse.setException(new Exception("Could not negotiate a connection type with the server."));
                                }
                                if (signallingClientResponse.getException() != null) {
                                    SignallingClient.this.setDisconnectException(signallingClientResponse.getException());
                                    SignallingClient.this.setState(SignallingClientState.Disconnected);
                                    SignallingClient.this.raiseConnectFailure(signallingConnectArgs, signallingClientResponse);
                                    SignallingClient.this.raiseEndEvent(SignallingClient._onConnectEnd, new SignallingClientConnectEndArgs(signallingConnectArgs), "OnConnectEnd", signallingClientResponse);
                                } else {
                                    if (!Global.equals(SignallingClient.this._connectionType, SignallingConnectionType.WebSocket) || SignallingClient.this.getDisableWebSockets()) {
                                        SignallingClient.this.doLongPolling();
                                    } else if (!SignallingClient.this.tryWebSocket()) {
                                        SignallingClient.this.doLongPolling();
                                    }
                                    SignallingClient.this.setState(SignallingClientState.Connected);
                                    SignallingClient.this.raiseConnectSuccess(signallingConnectArgs, signallingClientResponse);
                                    SignallingClient.this.raiseEndEvent(SignallingClient._onConnectEnd, new SignallingClientConnectEndArgs(signallingConnectArgs), "OnConnectEnd", signallingClientResponse);
                                }
                            }
                        }
                    });
                    send(signallingClientRequest, signallingConnectArgs.getRequestUrl(), checkSynchronous(signallingConnectArgs.getSynchronous()), signallingConnectArgs.getRequestTimeout().getHasValue() ? signallingConnectArgs.getRequestTimeout().getValue() : 0, signallingConnectArgs.getRequestMaxRetries().getHasValue() ? signallingConnectArgs.getRequestMaxRetries().getValue() : 0);
                    return this;
                }
            }
            SignallingClientResponse createFailureResponse2 = createFailureResponse(signallingConnectArgs, Global.equals(getState(), SignallingClientState.Connecting) ? "Client is already connecting." : "Client is already connected.");
            raiseResponseEvent(_onConnectResponse, new SignallingClientConnectResponseArgs(signallingConnectArgs), "OnConnectResponse", createFailureResponse2);
            raiseConnectFailure(signallingConnectArgs, createFailureResponse2);
            raiseEndEvent(_onConnectEnd, new SignallingClientConnectEndArgs(signallingConnectArgs), "OnConnectEnd", createFailureResponse2);
            return this;
        }
    }

    public Future<SignallingConnectSuccessArgs> connectAsync() {
        return connectAsync(new SignallingConnectArgs());
    }

    public Future<SignallingConnectSuccessArgs> connectAsync(SignallingConnectArgs signallingConnectArgs) {
        return doConnectAsync(signallingConnectArgs, signallingConnectArgs.getOnSuccess(), signallingConnectArgs.getOnFailure(), new Promise());
    }

    private SignallingClientResponse createFailureResponse(SignallingInputArgs signallingInputArgs, String str) {
        SignallingClientResponse signallingClientResponse = new SignallingClientResponse();
        signallingClientResponse.setDynamicProperties(signallingInputArgs.getDynamicProperties());
        SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.getHandshake());
        signallingMessage.setExtensions(signallingInputArgs.getExtensions());
        signallingMessage.setTimestamp(new NullableDate(DateExtensions.getUtcNow()));
        signallingMessage.setSuccessful(false);
        signallingMessage.setError(str);
        signallingClientResponse.setMessage(signallingMessage);
        signallingClientResponse.setFailureSource(FailureSource.Message);
        signallingClientResponse.setException(new Exception(str));
        return signallingClientResponse;
    }

    public SignallingClient disconnect() {
        return disconnect(new SignallingDisconnectArgs());
    }

    public SignallingClient disconnect(final SignallingDisconnectArgs signallingDisconnectArgs) {
        synchronized (this.__stateLock) {
            if (!raiseRequestEvent(_onDisconnectRequest, new SignallingClientDisconnectRequestArgs(signallingDisconnectArgs), "OnDisconnectRequest")) {
                SignallingClientResponse createFailureResponse = createFailureResponse(signallingDisconnectArgs, "Application cancelled request.");
                raiseResponseEvent(_onDisconnectResponse, new SignallingClientDisconnectResponseArgs(signallingDisconnectArgs), "OnDisconnectResponse", createFailureResponse);
                raiseDisconnectComplete(signallingDisconnectArgs, createFailureResponse);
                raiseEndEvent(_onDisconnectEnd, new SignallingClientDisconnectEndArgs(signallingDisconnectArgs), "OnDisconnectEnd", createFailureResponse);
                return this;
            }
            if (!Global.equals(getState(), SignallingClientState.Disconnecting)) {
                if (!Global.equals(getState(), SignallingClientState.Disconnected)) {
                    setState(SignallingClientState.Disconnecting);
                    SignallingClientRequest signallingClientRequest = new SignallingClientRequest();
                    SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.getDisconnect());
                    signallingMessage.setExtensions(signallingDisconnectArgs.getExtensions());
                    signallingClientRequest.setMessage(signallingMessage);
                    signallingClientRequest.setCallback(new IAction1<SignallingClientResponse>() {
                        public void invoke(SignallingClientResponse signallingClientResponse) {
                            synchronized (SignallingClient.this.__stateLock) {
                                SignallingClient.this.raiseResponseEvent(SignallingClient._onDisconnectResponse, new SignallingClientDisconnectResponseArgs(signallingDisconnectArgs), "OnDisconnectResponse", signallingClientResponse);
                                boolean unused = SignallingClient.this._webSocketOpened = false;
                                SignallingClient.this.setDisconnectException((Exception) null);
                                SignallingClient.this.setState(SignallingClientState.Disconnected);
                                SignallingClient.this.raiseDisconnectComplete(signallingDisconnectArgs, signallingClientResponse);
                                SignallingClient.this.raiseEndEvent(SignallingClient._onDisconnectEnd, new SignallingClientDisconnectEndArgs(signallingDisconnectArgs), "OnDisconnectEnd", signallingClientResponse);
                            }
                        }
                    });
                    addToQueue(signallingClientRequest, signallingDisconnectArgs.getRequestUrl(), checkSynchronous(signallingDisconnectArgs.getSynchronous()), signallingDisconnectArgs.getRequestTimeout().getHasValue() ? signallingDisconnectArgs.getRequestTimeout().getValue() : 0, signallingDisconnectArgs.getRequestMaxRetries().getHasValue() ? signallingDisconnectArgs.getRequestMaxRetries().getValue() : 0);
                    processQueue(false);
                    return this;
                }
            }
            String str = Global.equals(getState(), SignallingClientState.Disconnecting) ? "Client is already disconnecting." : "Client is already disconnected.";
            SignallingClientResponse signallingClientResponse = new SignallingClientResponse();
            signallingClientResponse.setDynamicProperties(signallingDisconnectArgs.getDynamicProperties());
            SignallingMessage signallingMessage2 = new SignallingMessage(SignallingMetaChannels.getHandshake());
            signallingMessage2.setExtensions(signallingDisconnectArgs.getExtensions());
            signallingMessage2.setTimestamp(new NullableDate(DateExtensions.getUtcNow()));
            signallingMessage2.setSuccessful(false);
            signallingMessage2.setError(str);
            signallingClientResponse.setMessage(signallingMessage2);
            signallingClientResponse.setFailureSource(FailureSource.Message);
            signallingClientResponse.setException(new Exception(str));
            raiseResponseEvent(_onDisconnectResponse, new SignallingClientDisconnectResponseArgs(signallingDisconnectArgs), "OnDisconnectResponse", signallingClientResponse);
            raiseDisconnectComplete(signallingDisconnectArgs, signallingClientResponse);
            raiseEndEvent(_onDisconnectEnd, new SignallingClientDisconnectEndArgs(signallingDisconnectArgs), "OnDisconnectEnd", signallingClientResponse);
            return this;
        }
    }

    public Future<SignallingDisconnectCompleteArgs> disconnectAsync() {
        return disconnectAsync(new SignallingDisconnectArgs());
    }

    public Future<SignallingDisconnectCompleteArgs> disconnectAsync(SignallingDisconnectArgs signallingDisconnectArgs) {
        return doDisconnectAsync(signallingDisconnectArgs, signallingDisconnectArgs.getOnComplete(), new Promise());
    }

    private Future<SignallingBindSuccessArgs> doBindAsync(SignallingBindArgs signallingBindArgs, final IAction1<SignallingBindSuccessArgs> iAction1, final IAction1<SignallingBindFailureArgs> iAction12, final Promise<SignallingBindSuccessArgs> promise) {
        signallingBindArgs.setOnSuccess(new IAction1<SignallingBindSuccessArgs>() {
            public void invoke(SignallingBindSuccessArgs signallingBindSuccessArgs) {
                IAction1 iAction1 = iAction1;
                if (iAction1 != null) {
                    iAction1.invoke(signallingBindSuccessArgs);
                }
                promise.resolve(signallingBindSuccessArgs);
            }
        });
        signallingBindArgs.setOnFailure(new IAction1<SignallingBindFailureArgs>() {
            public void invoke(SignallingBindFailureArgs signallingBindFailureArgs) {
                IAction1 iAction1 = iAction12;
                if (iAction1 != null) {
                    iAction1.invoke(signallingBindFailureArgs);
                }
                promise.reject(signallingBindFailureArgs.getException());
            }
        });
        bind(signallingBindArgs);
        return promise;
    }

    private Future<SignallingConnectSuccessArgs> doConnectAsync(SignallingConnectArgs signallingConnectArgs, final IAction1<SignallingConnectSuccessArgs> iAction1, final IAction1<SignallingConnectFailureArgs> iAction12, final Promise<SignallingConnectSuccessArgs> promise) {
        signallingConnectArgs.setOnSuccess(new IAction1<SignallingConnectSuccessArgs>() {
            public void invoke(SignallingConnectSuccessArgs signallingConnectSuccessArgs) {
                IAction1 iAction1 = iAction1;
                if (iAction1 != null) {
                    iAction1.invoke(signallingConnectSuccessArgs);
                }
                promise.resolve(signallingConnectSuccessArgs);
            }
        });
        signallingConnectArgs.setOnFailure(new IAction1<SignallingConnectFailureArgs>() {
            public void invoke(SignallingConnectFailureArgs signallingConnectFailureArgs) {
                IAction1 iAction1 = iAction12;
                if (iAction1 != null) {
                    iAction1.invoke(signallingConnectFailureArgs);
                }
                promise.reject(signallingConnectFailureArgs.getException());
            }
        });
        connect(signallingConnectArgs);
        return promise;
    }

    private Future<SignallingDisconnectCompleteArgs> doDisconnectAsync(SignallingDisconnectArgs signallingDisconnectArgs, final IAction1<SignallingDisconnectCompleteArgs> iAction1, final Promise<SignallingDisconnectCompleteArgs> promise) {
        signallingDisconnectArgs.setOnComplete(new IAction1<SignallingDisconnectCompleteArgs>() {
            public void invoke(SignallingDisconnectCompleteArgs signallingDisconnectCompleteArgs) {
                IAction1 iAction1 = iAction1;
                if (iAction1 != null) {
                    iAction1.invoke(signallingDisconnectCompleteArgs);
                }
                if (signallingDisconnectCompleteArgs.getException() == null) {
                    promise.resolve(signallingDisconnectCompleteArgs);
                } else {
                    promise.reject(signallingDisconnectCompleteArgs.getException());
                }
            }
        });
        disconnect(signallingDisconnectArgs);
        return promise;
    }

    /* access modifiers changed from: private */
    public void doLongPolling() {
        this._connectionType = SignallingConnectionType.LongPolling;
        this._streamRequestTransfer = SignallingMessageTransferFactory.getHttpMessageTransfer();
        activateStream();
    }

    private Future<SignallingPublishSuccessArgs> doPublishAsync(SignallingPublishArgs signallingPublishArgs, final IAction1<SignallingPublishSuccessArgs> iAction1, final IAction1<SignallingPublishFailureArgs> iAction12, final Promise<SignallingPublishSuccessArgs> promise) {
        signallingPublishArgs.setOnSuccess(new IAction1<SignallingPublishSuccessArgs>() {
            public void invoke(SignallingPublishSuccessArgs signallingPublishSuccessArgs) {
                IAction1 iAction1 = iAction1;
                if (iAction1 != null) {
                    iAction1.invoke(signallingPublishSuccessArgs);
                }
                promise.resolve(signallingPublishSuccessArgs);
            }
        });
        signallingPublishArgs.setOnFailure(new IAction1<SignallingPublishFailureArgs>() {
            public void invoke(SignallingPublishFailureArgs signallingPublishFailureArgs) {
                IAction1 iAction1 = iAction12;
                if (iAction1 != null) {
                    iAction1.invoke(signallingPublishFailureArgs);
                }
                promise.reject(signallingPublishFailureArgs.getException());
            }
        });
        publish(signallingPublishArgs);
        return promise;
    }

    private void doSendMany(boolean z, SignallingMessageTransfer signallingMessageTransfer, final SignallingMessageRequestArgs signallingMessageRequestArgs, final SignallingClientSendState signallingClientSendState) {
        if (z) {
            if (__log.getIsVerboseEnabled()) {
                __log.verbose(StringExtensions.format("Starting synchronous send for {0} messages...", (Object) IntegerExtensions.toString(Integer.valueOf(ArrayExtensions.getLength((Object[]) signallingMessageRequestArgs.getMessages())))));
            }
            SignallingMessageResponseArgs send = signallingMessageTransfer.send(signallingMessageRequestArgs);
            if (__log.getIsVerboseEnabled()) {
                __log.verbose(StringExtensions.format("Finished synchronous send for {0} messages.", (Object) IntegerExtensions.toString(Integer.valueOf(ArrayExtensions.getLength((Object[]) signallingMessageRequestArgs.getMessages())))));
            }
            sendCallback(signallingClientSendState, send);
            return;
        }
        if (__log.getIsVerboseEnabled()) {
            __log.verbose(StringExtensions.format("Starting asynchronous send on {0} for {1} messages...", this._requestTransfer instanceof SignallingWebSocketMessageTransfer ? "websocket" : "http", IntegerExtensions.toString(Integer.valueOf(ArrayExtensions.getLength((Object[]) signallingMessageRequestArgs.getMessages())))));
        }
        signallingMessageTransfer.sendAsync(signallingMessageRequestArgs, new IAction1<SignallingMessageResponseArgs>() {
            public void invoke(SignallingMessageResponseArgs signallingMessageResponseArgs) {
                if (SignallingClient.__log.getIsVerboseEnabled()) {
                    SignallingClient.__log.verbose(StringExtensions.format("Finished asynchronous send on {0} for {1} messages. Received {2} messages.", SignallingClient.this._requestTransfer instanceof SignallingWebSocketMessageTransfer ? "websocket" : "http", IntegerExtensions.toString(Integer.valueOf(ArrayExtensions.getLength((Object[]) signallingMessageRequestArgs.getMessages()))), signallingMessageResponseArgs.getMessages() != null ? IntegerExtensions.toString(Integer.valueOf(ArrayExtensions.getLength((Object[]) signallingMessageResponseArgs.getMessages()))) : "0"));
                }
                SignallingClient.this.sendCallback(signallingClientSendState, signallingMessageResponseArgs);
            }
        });
    }

    private Future<SignallingServiceSuccessArgs> doServiceAsync(SignallingServiceArgs signallingServiceArgs, final IAction1<SignallingServiceSuccessArgs> iAction1, final IAction1<SignallingServiceFailureArgs> iAction12, final Promise<SignallingServiceSuccessArgs> promise) {
        signallingServiceArgs.setOnSuccess(new IAction1<SignallingServiceSuccessArgs>() {
            public void invoke(SignallingServiceSuccessArgs signallingServiceSuccessArgs) {
                IAction1 iAction1 = iAction1;
                if (iAction1 != null) {
                    iAction1.invoke(signallingServiceSuccessArgs);
                }
                promise.resolve(signallingServiceSuccessArgs);
            }
        });
        signallingServiceArgs.setOnFailure(new IAction1<SignallingServiceFailureArgs>() {
            public void invoke(SignallingServiceFailureArgs signallingServiceFailureArgs) {
                IAction1 iAction1 = iAction12;
                if (iAction1 != null) {
                    iAction1.invoke(signallingServiceFailureArgs);
                }
                promise.reject(signallingServiceFailureArgs.getException());
            }
        });
        service(signallingServiceArgs);
        return promise;
    }

    private Future<SignallingSubscribeSuccessArgs> doSubscribeAsync(SignallingSubscribeArgs signallingSubscribeArgs, final IAction1<SignallingSubscribeSuccessArgs> iAction1, final IAction1<SignallingSubscribeFailureArgs> iAction12, final Promise<SignallingSubscribeSuccessArgs> promise) {
        signallingSubscribeArgs.setOnSuccess(new IAction1<SignallingSubscribeSuccessArgs>() {
            public void invoke(SignallingSubscribeSuccessArgs signallingSubscribeSuccessArgs) {
                IAction1 iAction1 = iAction1;
                if (iAction1 != null) {
                    iAction1.invoke(signallingSubscribeSuccessArgs);
                }
                promise.resolve(signallingSubscribeSuccessArgs);
            }
        });
        signallingSubscribeArgs.setOnFailure(new IAction1<SignallingSubscribeFailureArgs>() {
            public void invoke(SignallingSubscribeFailureArgs signallingSubscribeFailureArgs) {
                IAction1 iAction1 = iAction12;
                if (iAction1 != null) {
                    iAction1.invoke(signallingSubscribeFailureArgs);
                }
                promise.reject(signallingSubscribeFailureArgs.getException());
            }
        });
        subscribe(signallingSubscribeArgs);
        return promise;
    }

    private Future<SignallingUnbindSuccessArgs> doUnbindAsync(SignallingUnbindArgs signallingUnbindArgs, final IAction1<SignallingUnbindSuccessArgs> iAction1, final IAction1<SignallingUnbindFailureArgs> iAction12, final Promise<SignallingUnbindSuccessArgs> promise) {
        signallingUnbindArgs.setOnSuccess(new IAction1<SignallingUnbindSuccessArgs>() {
            public void invoke(SignallingUnbindSuccessArgs signallingUnbindSuccessArgs) {
                IAction1 iAction1 = iAction1;
                if (iAction1 != null) {
                    iAction1.invoke(signallingUnbindSuccessArgs);
                }
                promise.resolve(signallingUnbindSuccessArgs);
            }
        });
        signallingUnbindArgs.setOnFailure(new IAction1<SignallingUnbindFailureArgs>() {
            public void invoke(SignallingUnbindFailureArgs signallingUnbindFailureArgs) {
                IAction1 iAction1 = iAction12;
                if (iAction1 != null) {
                    iAction1.invoke(signallingUnbindFailureArgs);
                }
                promise.reject(signallingUnbindFailureArgs.getException());
            }
        });
        unbind(signallingUnbindArgs);
        return promise;
    }

    private Future<SignallingUnsubscribeSuccessArgs> doUnsubscribeAsync(SignallingUnsubscribeArgs signallingUnsubscribeArgs, final IAction1<SignallingUnsubscribeSuccessArgs> iAction1, final IAction1<SignallingUnsubscribeFailureArgs> iAction12, final Promise<SignallingUnsubscribeSuccessArgs> promise) {
        signallingUnsubscribeArgs.setOnSuccess(new IAction1<SignallingUnsubscribeSuccessArgs>() {
            public void invoke(SignallingUnsubscribeSuccessArgs signallingUnsubscribeSuccessArgs) {
                IAction1 iAction1 = iAction1;
                if (iAction1 != null) {
                    iAction1.invoke(signallingUnsubscribeSuccessArgs);
                }
                promise.resolve(signallingUnsubscribeSuccessArgs);
            }
        });
        signallingUnsubscribeArgs.setOnFailure(new IAction1<SignallingUnsubscribeFailureArgs>() {
            public void invoke(SignallingUnsubscribeFailureArgs signallingUnsubscribeFailureArgs) {
                IAction1 iAction1 = iAction12;
                if (iAction1 != null) {
                    iAction1.invoke(signallingUnsubscribeFailureArgs);
                }
                promise.reject(signallingUnsubscribeFailureArgs.getException());
            }
        });
        unsubscribe(signallingUnsubscribeArgs);
        return promise;
    }

    public SignallingClient endBatch() {
        boolean z;
        synchronized (this._batchCounterLock) {
            z = true;
            int i = this._batchCounter - 1;
            this._batchCounter = i;
            if (i <= 0) {
                this._batchCounter = 0;
            } else {
                z = false;
            }
        }
        if (z) {
            processQueue(false);
        }
        return this;
    }

    public static String generateToken() {
        String longExtensions = LongExtensions.toString(Long.valueOf(DateExtensions.getTicks(DateExtensions.getUtcNow())));
        return StringExtensions.substring(longExtensions, StringExtensions.getLength(longExtensions) - 12, 8);
    }

    public String getAuthToken() {
        return this._authToken;
    }

    private static boolean getBindIsPrivateForSubscribe(SignallingMessage signallingMessage, SignallingSubscribeArgs signallingSubscribeArgs) {
        if (signallingMessage == null) {
            return signallingSubscribeArgs.getBindIsPrivate();
        }
        if (!signallingMessage.getIsPrivate().getHasValue()) {
            return signallingSubscribeArgs.getBindIsPrivate();
        }
        return signallingMessage.getIsPrivate().getValue();
    }

    private static SignallingRecord[] getBindRecordsForSubscribe(SignallingMessage signallingMessage, SignallingSubscribeArgs signallingSubscribeArgs) {
        if (signallingMessage == null) {
            return signallingSubscribeArgs.__bindRecords;
        }
        if (signallingMessage.getRecords() == null) {
            return signallingSubscribeArgs.__bindRecords;
        }
        return signallingMessage.getRecords();
    }

    public SignallingRecords getBoundRecords() {
        SignallingRecords signallingRecords = new SignallingRecords();
        synchronized (this._boundRecordsLock) {
            for (SignallingRecord duplicate : this._boundRecords.getRecords()) {
                signallingRecords.add(duplicate.duplicate());
            }
        }
        return signallingRecords;
    }

    private static String getChannelForPublish(SignallingMessage signallingMessage, SignallingPublishArgs signallingPublishArgs) {
        if (signallingMessage == null) {
            return signallingPublishArgs.__channel;
        }
        if (signallingMessage.getChannel() == null) {
            return signallingPublishArgs.__channel;
        }
        return signallingMessage.getChannel();
    }

    private static String getChannelForService(SignallingMessage signallingMessage, SignallingServiceArgs signallingServiceArgs) {
        if (signallingMessage == null) {
            return signallingServiceArgs.__channel;
        }
        if (signallingMessage.getChannel() == null) {
            return signallingServiceArgs.__channel;
        }
        return signallingMessage.getChannel();
    }

    private static String[] getChannelsForSubscribe(SignallingMessage signallingMessage, SignallingSubscribeArgs signallingSubscribeArgs) {
        if (signallingMessage == null) {
            return signallingSubscribeArgs.__channels;
        }
        if (signallingMessage.getChannels() == null) {
            return signallingSubscribeArgs.__channels;
        }
        return signallingMessage.getChannels();
    }

    private static String[] getChannelsForUnsubscribe(SignallingMessage signallingMessage, SignallingUnsubscribeArgs signallingUnsubscribeArgs) {
        if (signallingMessage == null) {
            return signallingUnsubscribeArgs.__channels;
        }
        if (signallingMessage.getChannels() == null) {
            return signallingUnsubscribeArgs.__channels;
        }
        return signallingMessage.getChannels();
    }

    public String getClientId() {
        return this._clientId;
    }

    public IAction1<SignallingSubscribeReceiveArgs> getCustomOnReceive(String str) {
        return getCustomOnReceiveWithTag(str, StringExtensions.empty);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.IAction1<fm.liveswitch.SignallingSubscribeReceiveArgs> getCustomOnReceiveWithTag(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0037
            if (r6 != 0) goto L_0x0006
            java.lang.String r6 = fm.liveswitch.StringExtensions.empty
        L_0x0006:
            java.lang.Object r0 = r4._customOnReceivesLock
            monitor-enter(r0)
            fm.liveswitch.Holder r1 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0034 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0034 }
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, fm.liveswitch.IAction1<fm.liveswitch.SignallingSubscribeReceiveArgs>>> r3 = r4._customOnReceives     // Catch:{ all -> 0x0034 }
            boolean r6 = fm.liveswitch.HashMapExtensions.tryGetValue(r3, r6, r1)     // Catch:{ all -> 0x0034 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0034 }
            java.util.HashMap r1 = (java.util.HashMap) r1     // Catch:{ all -> 0x0034 }
            if (r6 != 0) goto L_0x001f
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return r2
        L_0x001f:
            fm.liveswitch.Holder r6 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0034 }
            r6.<init>(r2)     // Catch:{ all -> 0x0034 }
            boolean r5 = fm.liveswitch.HashMapExtensions.tryGetValue(r1, r5, r6)     // Catch:{ all -> 0x0034 }
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x0034 }
            fm.liveswitch.IAction1 r6 = (fm.liveswitch.IAction1) r6     // Catch:{ all -> 0x0034 }
            if (r5 != 0) goto L_0x0031
            goto L_0x0032
        L_0x0031:
            r2 = r6
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return r2
        L_0x0034:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            throw r5
        L_0x0037:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.Exception r6 = new java.lang.Exception
            java.lang.String r0 = "channel cannot be null."
            r6.<init>(r0)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SignallingClient.getCustomOnReceiveWithTag(java.lang.String, java.lang.String):fm.liveswitch.IAction1");
    }

    private static byte[] getDataBytesForPublish(SignallingMessage signallingMessage, SignallingPublishArgs signallingPublishArgs) {
        if (signallingMessage == null || signallingMessage.getDataBytes() == null) {
            return null;
        }
        return signallingMessage.getDataBytes();
    }

    private static byte[] getDataBytesForService(SignallingMessage signallingMessage, SignallingServiceArgs signallingServiceArgs) {
        if (signallingMessage == null || signallingMessage.getDataBytes() == null) {
            return null;
        }
        return signallingMessage.getDataBytes();
    }

    private static String getDataJsonForPublish(SignallingMessage signallingMessage, SignallingPublishArgs signallingPublishArgs) {
        if (signallingMessage == null || signallingMessage.getDataJson() == null) {
            return null;
        }
        return signallingMessage.getDataJson();
    }

    private static String getDataJsonForService(SignallingMessage signallingMessage, SignallingServiceArgs signallingServiceArgs) {
        if (signallingMessage == null || signallingMessage.getDataJson() == null) {
            return null;
        }
        return signallingMessage.getDataJson();
    }

    public String getDeviceId() {
        return this._deviceId;
    }

    public boolean getDisableWebSockets() {
        return this._disableWebSockets;
    }

    public Exception getDisconnectException() {
        return this._disconnectException;
    }

    private static SignallingExtensions getExtensions(SignallingMessage signallingMessage, SignallingExtensible signallingExtensible) {
        if (signallingMessage != null) {
            return signallingMessage.getExtensions();
        }
        return signallingExtensible.getExtensions();
    }

    private static boolean getIsPrivateForBind(SignallingMessage signallingMessage, SignallingBindArgs signallingBindArgs) {
        if (signallingMessage == null) {
            return signallingBindArgs.getIsPrivate();
        }
        if (!signallingMessage.getIsPrivate().getHasValue()) {
            return signallingBindArgs.getIsPrivate();
        }
        return signallingMessage.getIsPrivate().getValue();
    }

    private static String[] getKeysForUnbind(SignallingMessage signallingMessage, SignallingUnbindArgs signallingUnbindArgs) {
        if (signallingMessage == null) {
            return signallingUnbindArgs.__keys;
        }
        if (signallingMessage.getKeys() == null) {
            return signallingUnbindArgs.__keys;
        }
        return signallingMessage.getKeys();
    }

    private static SignallingRecord[] getRecordsForBind(SignallingMessage signallingMessage, SignallingBindArgs signallingBindArgs) {
        if (signallingMessage == null) {
            return signallingBindArgs.__records;
        }
        if (signallingMessage.getRecords() == null) {
            return signallingBindArgs.__records;
        }
        return signallingMessage.getRecords();
    }

    public int getServerTimeout() {
        return this._serverTimeout;
    }

    public SignallingClientState getState() {
        return this.__state;
    }

    public String getStreamId() {
        return this._streamId;
    }

    public int getStreamRequestTimeout() {
        return super.getRequestTimeout() + getServerTimeout();
    }

    public String getStreamRequestUrl() {
        return this.__streamRequestUrl;
    }

    public String[] getSubscribedChannels() {
        ArrayList arrayList = new ArrayList();
        synchronized (this._subscribedChannelsLock) {
            for (String str : HashMapExtensions.getKeys(this._subscribedChannels)) {
                for (String add : HashMapExtensions.getKeys(HashMapExtensions.getItem(this._subscribedChannels).get(str))) {
                    arrayList.add(add);
                }
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public String[] getSubscribedChannels(String str) {
        HashMap hashMap;
        ArrayList arrayList = new ArrayList();
        synchronized (this._subscribedChannelsLock) {
            hashMap = null;
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this._subscribedChannels, str, holder);
            HashMap hashMap2 = (HashMap) holder.getValue();
            if (tryGetValue) {
                hashMap = hashMap2;
            }
        }
        if (hashMap != null) {
            for (String add : HashMapExtensions.getKeys(hashMap)) {
                arrayList.add(add);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private static HashMap<String, SignallingRemoteClient[]> getSubscribedClientsForSubscribe(SignallingMessage signallingMessage, SignallingSubscribeArgs signallingSubscribeArgs) {
        if (signallingMessage == null || signallingMessage.getSubscribedClients() == null) {
            return null;
        }
        return signallingMessage.getSubscribedClients();
    }

    /* access modifiers changed from: private */
    public static String getSubscriptionKey(String str, String str2) {
        if (str2 == null) {
            return StringExtensions.format("-1|{0}", (Object) str);
        }
        return StringExtensions.format("{0}|{1}{2}", IntegerExtensions.toString(Integer.valueOf(StringExtensions.getLength(str2))), str2, str);
    }

    public NullableBoolean getSynchronous() {
        return this._synchronous;
    }

    private static NullableDate getTimestamp(SignallingMessage signallingMessage) {
        if (signallingMessage != null) {
            return signallingMessage.getTimestamp();
        }
        return new NullableDate((Date) null);
    }

    public String getToken() {
        return this._token;
    }

    private static String[] getUnbindKeysForUnsubscribe(SignallingMessage signallingMessage, SignallingUnsubscribeArgs signallingUnsubscribeArgs) {
        if (signallingMessage == null) {
            return signallingUnsubscribeArgs.__unbindKeys;
        }
        if (signallingMessage.getKeys() == null) {
            return signallingUnsubscribeArgs.__unbindKeys;
        }
        return signallingMessage.getKeys();
    }

    public String getUserId() {
        return this._userId;
    }

    public boolean inBatch() {
        boolean z;
        synchronized (this._batchCounterLock) {
            z = this._batchCounter > 0;
        }
        return z;
    }

    private void processAdvice(SignallingBaseAdvice signallingBaseAdvice) {
        if (signallingBaseAdvice.getInterval().getHasValue()) {
            this._lastInterval = signallingBaseAdvice.getInterval().getValue();
        }
        if (!Global.equals(signallingBaseAdvice.getReconnect(), SignallingReconnect.NotSet)) {
            this._lastReconnect = signallingBaseAdvice.getReconnect();
        }
    }

    private int processPendingReceives(String[] strArr, int i) {
        synchronized (this._subscribedOnReceivesLock) {
            for (String str : strArr) {
                Holder holder = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(this._pendingReceives, str, holder);
                ArrayList arrayList = (ArrayList) holder.getValue();
                if (tryGetValue) {
                    HashMapExtensions.remove(this._pendingReceives, str);
                }
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        i = receiveMessage((SignallingMessage) it.next(), i);
                    }
                }
            }
        }
        return i;
    }

    private void processQueue(boolean z) {
        synchronized (this.__stateLock) {
            if (z) {
                this._queueActivated = true;
            }
            if (this._queueActivated && !inBatch()) {
                HashMap<String, ArrayList<SignallingClientRequest>> hashMap = this._requestQueue;
                this._requestQueue = new HashMap<>();
                for (String next : HashMapExtensions.getKeys(hashMap)) {
                    int indexOf = StringExtensions.indexOf(next, "|", StringComparison.InvariantCulture);
                    String substring = StringExtensions.substring(next, 0, indexOf);
                    String substring2 = next.substring(indexOf + 1);
                    int indexOf2 = StringExtensions.indexOf(substring, Marker.ANY_NON_NULL_MARKER, StringComparison.InvariantCulture);
                    String substring3 = StringExtensions.substring(substring, 0, indexOf2);
                    String substring4 = substring.substring(indexOf2 + 1);
                    int parseIntegerValue = ParseAssistant.parseIntegerValue(substring3);
                    int parseIntegerValue2 = ParseAssistant.parseIntegerValue(substring4);
                    boolean equals = Global.equals(StringExtensions.substring(substring2, 0, 1), "1");
                    sendMany((SignallingClientRequest[]) HashMapExtensions.getItem(hashMap).get(next).toArray(new SignallingClientRequest[0]), substring2.substring(1), equals, parseIntegerValue, parseIntegerValue2);
                }
            }
        }
    }

    private String processRequestUrl(String str) {
        boolean tryGetValue;
        String str2;
        if (StringExtensions.isNullOrEmpty(str)) {
            str = super.getRequestUrl();
        }
        boolean z = false;
        String str3 = null;
        if (Global.equals(super.getConcurrencyMode(), SignallingConcurrencyMode.High)) {
            synchronized (_requestUrlCacheLock) {
                Holder holder = new Holder(null);
                tryGetValue = HashMapExtensions.tryGetValue(_requestUrlCache, str, holder);
                str2 = (String) holder.getValue();
            }
            boolean z2 = tryGetValue;
            str3 = str2;
            z = z2;
        }
        if (!z) {
            str3 = HttpTransfer.addQueryToUrl(HttpTransfer.addQueryToUrl(HttpTransfer.addQueryToUrl(str, "token", getToken()), "src", HttpWebRequestTransfer.getPlatformCode()), "AspxAutoDetectCookieSupport", "1");
            if (!Global.equals(super.getConcurrencyMode(), SignallingConcurrencyMode.High)) {
                return str3;
            }
            synchronized (_requestUrlCache) {
                HashMapExtensions.set(HashMapExtensions.getItem(_requestUrlCache), str, str3);
            }
        }
        return str3;
    }

    private int processServerAction(SignallingMessage signallingMessage, int i) {
        if (Global.equals(signallingMessage.getBayeuxChannel(), SignallingMetaChannels.getBind())) {
            addBoundRecords(signallingMessage.getRecords());
            raiseServerBind(signallingMessage);
            return i;
        } else if (Global.equals(signallingMessage.getBayeuxChannel(), SignallingMetaChannels.getUnbind())) {
            removeBoundRecords(signallingMessage.getKeys());
            raiseServerUnbind(signallingMessage);
            return i;
        } else if (Global.equals(signallingMessage.getBayeuxChannel(), SignallingMetaChannels.getSubscribe())) {
            addSubscribedChannels(signallingMessage.getTag(), signallingMessage.getChannels());
            Holder holder = new Holder(null);
            Holder holder2 = new Holder(null);
            raiseServerSubscribe(signallingMessage, holder, holder2);
            IAction1 iAction1 = (IAction1) holder.getValue();
            IAction1 iAction12 = (IAction1) holder2.getValue();
            if (iAction1 != null) {
                return addSubscribedOnPresence(signallingMessage.getTag(), signallingMessage.getChannels(), iAction12, signallingMessage.getDynamicProperties(), addSubscribedOnReceive(signallingMessage.getTag(), signallingMessage.getChannels(), iAction1, signallingMessage.getDynamicProperties(), i));
            }
            throw new RuntimeException(new Exception(StringExtensions.format("The server subscribed the client to [{0}], but the client did not set ServerSubscribeArgs.OnReceive to handle incoming messages or ServerSubscribeArgs.OnPresence to handle incoming presence notifications.", (Object) StringExtensions.join(", ", signallingMessage.getChannels()))));
        } else {
            if (Global.equals(signallingMessage.getBayeuxChannel(), SignallingMetaChannels.getUnsubscribe())) {
                removeSubscribedChannels(signallingMessage.getTag(), signallingMessage.getChannels());
                raiseServerUnsubscribe(signallingMessage);
                removeSubscribedOnReceive(signallingMessage.getTag(), signallingMessage.getChannels());
                removeSubscribedOnPresence(signallingMessage.getTag(), signallingMessage.getChannels());
            }
            return i;
        }
    }

    public SignallingClient publish(final SignallingPublishArgs signallingPublishArgs) {
        synchronized (this.__stateLock) {
            if (StringExtensions.isNullOrEmpty(signallingPublishArgs.getChannel())) {
                raisePublishFailure(signallingPublishArgs, createFailureResponse(signallingPublishArgs, "Channel cannot be null."));
                return this;
            } else if (StringExtensions.isNullOrEmpty(signallingPublishArgs.getDataJson())) {
                raisePublishFailure(signallingPublishArgs, createFailureResponse(signallingPublishArgs, "Data cannot be null."));
                return this;
            } else if (!raiseRequestEvent(_onPublishRequest, new SignallingClientPublishRequestArgs(signallingPublishArgs), "OnPublishRequest")) {
                SignallingClientResponse createFailureResponse = createFailureResponse(signallingPublishArgs, "Application cancelled request.");
                raiseResponseEvent(_onPublishResponse, new SignallingClientPublishResponseArgs(signallingPublishArgs), "OnPublishResponse", createFailureResponse);
                raisePublishFailure(signallingPublishArgs, createFailureResponse);
                raiseEndEvent(_onPublishEnd, new SignallingClientPublishEndArgs(signallingPublishArgs), "OnPublishEnd", createFailureResponse);
                return this;
            } else {
                SignallingClientRequest signallingClientRequest = new SignallingClientRequest();
                SignallingMessage signallingMessage = new SignallingMessage(signallingPublishArgs.getChannel());
                signallingMessage.setValidate(false);
                signallingMessage.setDataJson(signallingPublishArgs.getDataJson());
                signallingMessage.setExtensions(signallingPublishArgs.getExtensions());
                signallingClientRequest.setMessage(signallingMessage);
                signallingClientRequest.setCallback(new IAction1<SignallingClientResponse>() {
                    public void invoke(SignallingClientResponse signallingClientResponse) {
                        synchronized (SignallingClient.this.__stateLock) {
                            SignallingClient.this.raiseResponseEvent(SignallingClient._onPublishResponse, new SignallingClientPublishResponseArgs(signallingPublishArgs), "OnPublishResponse", signallingClientResponse);
                            if (signallingClientResponse.getException() != null) {
                                SignallingClient.this.raisePublishFailure(signallingPublishArgs, signallingClientResponse);
                            } else {
                                SignallingClient.this.raisePublishSuccess(signallingPublishArgs, signallingClientResponse);
                            }
                            SignallingClient.this.raiseEndEvent(SignallingClient._onPublishEnd, new SignallingClientPublishEndArgs(signallingPublishArgs), "OnPublishEnd", signallingClientResponse);
                        }
                    }
                });
                addToQueue(signallingClientRequest, signallingPublishArgs.getRequestUrl(), checkSynchronous(signallingPublishArgs.getSynchronous()), signallingPublishArgs.getRequestTimeout().getHasValue() ? signallingPublishArgs.getRequestTimeout().getValue() : 0, signallingPublishArgs.getRequestMaxRetries().getHasValue() ? signallingPublishArgs.getRequestMaxRetries().getValue() : 0);
                processQueue(false);
                return this;
            }
        }
    }

    public Future<SignallingPublishSuccessArgs> publishAsync(String str, byte[] bArr) {
        return publishAsync(new SignallingPublishArgs(str, bArr));
    }

    public Future<SignallingPublishSuccessArgs> publishAsync(String str, byte[] bArr, String str2) {
        return publishAsync(new SignallingPublishArgs(str, bArr, str2));
    }

    public Future<SignallingPublishSuccessArgs> publishAsync(String str, String str2) {
        return publishAsync(new SignallingPublishArgs(str, str2));
    }

    public Future<SignallingPublishSuccessArgs> publishAsync(String str, String str2, String str3) {
        return publishAsync(new SignallingPublishArgs(str, str2, str3));
    }

    public Future<SignallingPublishSuccessArgs> publishAsync(SignallingPublishArgs signallingPublishArgs) {
        return doPublishAsync(signallingPublishArgs, signallingPublishArgs.getOnSuccess(), signallingPublishArgs.getOnFailure(), new Promise());
    }

    /* access modifiers changed from: protected */
    public <T> void raiseAction(IAction1<T> iAction1, T t, String str) {
        try {
            iAction1.invoke(t);
        } catch (Exception e) {
            if (!super.raiseUnhandledException(e)) {
                Unhandled.logException(e, str);
            }
        }
    }

    /* access modifiers changed from: private */
    public void raiseBindFailure(SignallingBindArgs signallingBindArgs, SignallingClientResponse signallingClientResponse) {
        SignallingBindFailureArgs signallingBindFailureArgs = new SignallingBindFailureArgs(getRecordsForBind(signallingClientResponse.getMessage(), signallingBindArgs), getIsPrivateForBind(signallingClientResponse.getMessage(), signallingBindArgs));
        signallingBindFailureArgs.setException(signallingClientResponse.getException());
        signallingBindFailureArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingBindArgs));
        signallingBindFailureArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingBindFailureArgs.setClient(this);
        signallingBindFailureArgs.setDynamicProperties(signallingBindArgs.getDynamicProperties());
        IAction1<SignallingBindFailureArgs> iAction1 = this._onBindFailure;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingBindFailureArgs, "Client -> OnBindFailure");
        }
        if (signallingBindArgs.getOnFailure() != null) {
            raiseAction(signallingBindArgs.getOnFailure(), signallingBindFailureArgs, "Client -> Bind -> OnFailure");
        }
    }

    /* access modifiers changed from: private */
    public void raiseBindSuccess(SignallingBindArgs signallingBindArgs, SignallingClientResponse signallingClientResponse) {
        SignallingBindSuccessArgs signallingBindSuccessArgs = new SignallingBindSuccessArgs(getRecordsForBind(signallingClientResponse.getMessage(), signallingBindArgs), getIsPrivateForBind(signallingClientResponse.getMessage(), signallingBindArgs));
        signallingBindSuccessArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingBindArgs));
        signallingBindSuccessArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingBindSuccessArgs.setClient(this);
        signallingBindSuccessArgs.setDynamicProperties(signallingBindArgs.getDynamicProperties());
        IAction1<SignallingBindSuccessArgs> iAction1 = this._onBindSuccess;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingBindSuccessArgs, "Client -> OnBindSuccess");
        }
        if (signallingBindArgs.getOnSuccess() != null) {
            raiseAction(signallingBindArgs.getOnSuccess(), signallingBindSuccessArgs, "Client -> Bind -> OnSuccess");
        }
    }

    private int raiseClientChannelDelivery(IAction1<SignallingSubscribeReceiveArgs> iAction1, SignallingMessage signallingMessage, int i) {
        SignallingSubscribeReceiveArgs signallingSubscribeReceiveArgs = new SignallingSubscribeReceiveArgs(signallingMessage.getBayeuxChannel(), signallingMessage.__dataJson, signallingMessage.__dataBytes, this._connectionType, i);
        signallingSubscribeReceiveArgs.setExtensions(signallingMessage.getExtensions());
        signallingSubscribeReceiveArgs.setTimestamp(getTimestamp(signallingMessage));
        signallingSubscribeReceiveArgs.setClient(this);
        if (iAction1 != null) {
            raiseAction(iAction1, signallingSubscribeReceiveArgs, "Client -> OnClientChannelReceive");
        }
        return signallingSubscribeReceiveArgs.getReconnectAfter();
    }

    /* access modifiers changed from: private */
    public void raiseConnectFailure(SignallingConnectArgs signallingConnectArgs, SignallingClientResponse signallingClientResponse) {
        SignallingConnectFailureArgs signallingConnectFailureArgs = new SignallingConnectFailureArgs();
        signallingConnectFailureArgs.setException(signallingClientResponse.getException());
        signallingConnectFailureArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingConnectArgs));
        signallingConnectFailureArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingConnectFailureArgs.setClient(this);
        signallingConnectFailureArgs.setDynamicProperties(signallingConnectArgs.getDynamicProperties());
        IAction1<SignallingConnectFailureArgs> iAction1 = this._onConnectFailure;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingConnectFailureArgs, "Client -> OnConnectFailure");
        }
        if (signallingConnectArgs.getOnFailure() != null) {
            raiseAction(signallingConnectArgs.getOnFailure(), signallingConnectFailureArgs, "Client -> Connect -> OnFailure");
        }
    }

    /* access modifiers changed from: private */
    public void raiseConnectSuccess(SignallingConnectArgs signallingConnectArgs, SignallingClientResponse signallingClientResponse) {
        SignallingConnectSuccessArgs signallingConnectSuccessArgs = new SignallingConnectSuccessArgs();
        signallingConnectSuccessArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingConnectArgs));
        signallingConnectSuccessArgs.setConnectionType(this._connectionType);
        signallingConnectSuccessArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingConnectSuccessArgs.setClient(this);
        signallingConnectSuccessArgs.setDynamicProperties(signallingConnectArgs.getDynamicProperties());
        IAction1<SignallingConnectSuccessArgs> iAction1 = this._onConnectSuccess;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingConnectSuccessArgs, "Client -> OnConnectSuccess");
        }
        if (signallingConnectArgs.getOnSuccess() != null) {
            raiseAction(signallingConnectArgs.getOnSuccess(), signallingConnectSuccessArgs, "Client -> Connect -> OnSuccess");
        }
    }

    private int raiseDeviceChannelDelivery(IAction1<SignallingSubscribeReceiveArgs> iAction1, SignallingMessage signallingMessage, int i) {
        SignallingSubscribeReceiveArgs signallingSubscribeReceiveArgs = new SignallingSubscribeReceiveArgs(signallingMessage.getBayeuxChannel(), signallingMessage.__dataJson, signallingMessage.__dataBytes, this._connectionType, i);
        signallingSubscribeReceiveArgs.setExtensions(signallingMessage.getExtensions());
        signallingSubscribeReceiveArgs.setTimestamp(getTimestamp(signallingMessage));
        signallingSubscribeReceiveArgs.setClient(this);
        if (iAction1 != null) {
            raiseAction(iAction1, signallingSubscribeReceiveArgs, "Client -> OnDeviceChannelReceive");
        }
        return signallingSubscribeReceiveArgs.getReconnectAfter();
    }

    /* access modifiers changed from: private */
    public void raiseDisconnectComplete(SignallingDisconnectArgs signallingDisconnectArgs, SignallingClientResponse signallingClientResponse) {
        SignallingDisconnectCompleteArgs signallingDisconnectCompleteArgs = new SignallingDisconnectCompleteArgs();
        signallingDisconnectCompleteArgs.setException(signallingClientResponse.getException());
        signallingDisconnectCompleteArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingDisconnectArgs));
        signallingDisconnectCompleteArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingDisconnectCompleteArgs.setClient(this);
        signallingDisconnectCompleteArgs.setDynamicProperties(signallingDisconnectArgs.getDynamicProperties());
        IAction1<SignallingDisconnectCompleteArgs> iAction1 = this._onDisconnectComplete;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingDisconnectCompleteArgs, "Client -> OnDisconnectComplete");
        }
        if (signallingDisconnectArgs.getOnComplete() != null) {
            raiseAction(signallingDisconnectArgs.getOnComplete(), signallingDisconnectCompleteArgs, "Client -> Disconnect -> OnComplete");
        }
    }

    /* access modifiers changed from: private */
    public <T extends SignallingClientEndArgs> void raiseEndEvent(IAction2<SignallingClient, T> iAction2, T t, String str, SignallingClientResponse signallingClientResponse) {
        t.setException(signallingClientResponse.getException());
        t.setMessage(signallingClientResponse.getMessage());
        raiseEvent(iAction2, t, str);
    }

    /* access modifiers changed from: protected */
    public <T extends SignallingClientArgs> void raiseEvent(IAction2<SignallingClient, T> iAction2, T t, String str) {
        t.setClient(this);
        if (iAction2 != null) {
            try {
                iAction2.invoke(this, t);
            } catch (Exception e) {
                if (!super.raiseUnhandledException(e)) {
                    Unhandled.logException(e, StringExtensions.format("Client -> {0}", (Object) str));
                }
            }
        }
    }

    private void raiseForcedUnbinds() {
        ArrayList arrayList = new ArrayList();
        synchronized (this._boundRecordsLock) {
            String[] keys = this._boundRecords.getKeys();
            int length = keys.length;
            for (int i = 0; i < length; i++) {
                arrayList.add(new SignallingUnbindArgs(new String[]{keys[i]}));
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            SignallingUnbindArgs signallingUnbindArgs = (SignallingUnbindArgs) it.next();
            raiseUnbindSuccess(signallingUnbindArgs, createFailureResponse(signallingUnbindArgs, "Disconnect forced unbind."), true);
        }
    }

    private void raiseForcedUnsubscribes() {
        ArrayList arrayList = new ArrayList();
        synchronized (this._subscribedChannelsLock) {
            for (String next : HashMapExtensions.getKeys(this._subscribedChannels)) {
                for (String str : HashMapExtensions.getKeys(HashMapExtensions.getItem(this._subscribedChannels).get(next))) {
                    arrayList.add(new SignallingUnsubscribeArgs(new String[]{str}, next));
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            SignallingUnsubscribeArgs signallingUnsubscribeArgs = (SignallingUnsubscribeArgs) it.next();
            raiseUnsubscribeSuccess(signallingUnsubscribeArgs, createFailureResponse(signallingUnsubscribeArgs, "Disconnect forced unsubscribe."), true);
        }
    }

    /* access modifiers changed from: protected */
    public <T, R> R raiseFunction(IFunction1<T, R> iFunction1, T t, String str) {
        try {
            return iFunction1.invoke(t);
        } catch (Exception e) {
            if (super.raiseUnhandledException(e)) {
                return null;
            }
            Unhandled.logException(e, str);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void raisePublishFailure(SignallingPublishArgs signallingPublishArgs, SignallingClientResponse signallingClientResponse) {
        SignallingPublishFailureArgs signallingPublishFailureArgs = new SignallingPublishFailureArgs(getChannelForPublish(signallingClientResponse.getMessage(), signallingPublishArgs), getDataJsonForPublish(signallingClientResponse.getMessage(), signallingPublishArgs), getDataBytesForPublish(signallingClientResponse.getMessage(), signallingPublishArgs));
        signallingPublishFailureArgs.setException(signallingClientResponse.getException());
        signallingPublishFailureArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingPublishArgs));
        signallingPublishFailureArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingPublishFailureArgs.setClient(this);
        signallingPublishFailureArgs.setDynamicProperties(signallingPublishArgs.getDynamicProperties());
        IAction1<SignallingPublishFailureArgs> iAction1 = this._onPublishFailure;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingPublishFailureArgs, "Client -> OnPublishFailure");
        }
        if (signallingPublishArgs.getOnFailure() != null) {
            raiseAction(signallingPublishArgs.getOnFailure(), signallingPublishFailureArgs, "Client -> Publish -> OnFailure");
        }
    }

    /* access modifiers changed from: private */
    public void raisePublishSuccess(SignallingPublishArgs signallingPublishArgs, SignallingClientResponse signallingClientResponse) {
        SignallingPublishSuccessArgs signallingPublishSuccessArgs = new SignallingPublishSuccessArgs(getChannelForPublish(signallingClientResponse.getMessage(), signallingPublishArgs), getDataJsonForPublish(signallingClientResponse.getMessage(), signallingPublishArgs), getDataBytesForPublish(signallingClientResponse.getMessage(), signallingPublishArgs));
        signallingPublishSuccessArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingPublishArgs));
        signallingPublishSuccessArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingPublishSuccessArgs.setClient(this);
        signallingPublishSuccessArgs.setDynamicProperties(signallingPublishArgs.getDynamicProperties());
        IAction1<SignallingPublishSuccessArgs> iAction1 = this._onPublishSuccess;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingPublishSuccessArgs, "Client -> OnPublishSuccess");
        }
        if (signallingPublishArgs.getOnSuccess() != null) {
            raiseAction(signallingPublishArgs.getOnSuccess(), signallingPublishSuccessArgs, "Client -> Publish -> OnSuccess");
        }
    }

    /* access modifiers changed from: protected */
    public <T extends SignallingClientRequestArgs> boolean raiseRequestEvent(IAction2<SignallingClient, T> iAction2, T t, String str) {
        raiseEvent(iAction2, t, str);
        return !t.getCancel();
    }

    /* access modifiers changed from: private */
    public <T extends SignallingClientResponseArgs> void raiseResponseEvent(IAction2<SignallingClient, T> iAction2, T t, String str, SignallingClientResponse signallingClientResponse) {
        t.setException(signallingClientResponse.getException());
        t.setMessage(signallingClientResponse.getMessage());
        raiseEvent(iAction2, t, str);
    }

    private void raiseSendException(SignallingClientSendState signallingClientSendState, Exception exc, FailureSource failureSource) {
        for (SignallingClientRequest signallingClientRequest : signallingClientSendState.getRequests()) {
            SignallingClientResponse signallingClientResponse = new SignallingClientResponse();
            signallingClientResponse.setDynamicProperties(signallingClientRequest.getDynamicProperties());
            signallingClientResponse.setException(exc);
            signallingClientResponse.setFailureSource(failureSource);
            signallingClientRequest.getCallback().invoke(signallingClientResponse);
        }
    }

    private void raiseServerBind(SignallingMessage signallingMessage) {
        IAction1<SignallingServerBindArgs> iAction1 = this._onServerBind;
        if (iAction1 != null) {
            SignallingServerBindArgs signallingServerBindArgs = new SignallingServerBindArgs();
            signallingServerBindArgs.__records = signallingMessage.getRecords();
            signallingServerBindArgs.setExtensions(signallingMessage.getExtensions());
            signallingServerBindArgs.setTimestamp(signallingMessage.getTimestamp());
            signallingServerBindArgs.setClient(this);
            raiseAction(iAction1, signallingServerBindArgs, "Client -> OnServerBind");
        }
    }

    private void raiseServerSubscribe(SignallingMessage signallingMessage, Holder<IAction1<SignallingSubscribeReceiveArgs>> holder, Holder<IAction1<SignallingSubscribePresenceArgs>> holder2) {
        IAction1<SignallingServerSubscribeArgs> iAction1 = this._onServerSubscribe;
        if (iAction1 != null) {
            SignallingServerSubscribeArgs signallingServerSubscribeArgs = new SignallingServerSubscribeArgs();
            signallingServerSubscribeArgs.__channels = signallingMessage.getChannels();
            signallingServerSubscribeArgs.setExtensions(signallingMessage.getExtensions());
            signallingServerSubscribeArgs.setTimestamp(signallingMessage.getTimestamp());
            signallingServerSubscribeArgs.setClient(this);
            raiseAction(iAction1, signallingServerSubscribeArgs, "Client -> OnServerSubscribe");
            holder.setValue(signallingServerSubscribeArgs.getOnReceive());
            holder2.setValue(signallingServerSubscribeArgs.getOnPresence());
            return;
        }
        holder.setValue(null);
        holder2.setValue(null);
    }

    private void raiseServerUnbind(SignallingMessage signallingMessage) {
        IAction1<SignallingServerUnbindArgs> iAction1 = this._onServerUnbind;
        if (iAction1 != null) {
            SignallingServerUnbindArgs signallingServerUnbindArgs = new SignallingServerUnbindArgs();
            signallingServerUnbindArgs.__keys = signallingMessage.getKeys();
            signallingServerUnbindArgs.setExtensions(signallingMessage.getExtensions());
            signallingServerUnbindArgs.setTimestamp(signallingMessage.getTimestamp());
            signallingServerUnbindArgs.setClient(this);
            raiseAction(iAction1, signallingServerUnbindArgs, "Client -> OnServerUnbind");
        }
    }

    private void raiseServerUnsubscribe(SignallingMessage signallingMessage) {
        IAction1<SignallingServerUnsubscribeArgs> iAction1 = this._onServerUnsubscribe;
        if (iAction1 != null) {
            SignallingServerUnsubscribeArgs signallingServerUnsubscribeArgs = new SignallingServerUnsubscribeArgs();
            signallingServerUnsubscribeArgs.__channels = signallingMessage.getChannels();
            signallingServerUnsubscribeArgs.setExtensions(signallingMessage.getExtensions());
            signallingServerUnsubscribeArgs.setTimestamp(signallingMessage.getTimestamp());
            signallingServerUnsubscribeArgs.setClient(this);
            raiseAction(iAction1, signallingServerUnsubscribeArgs, "Client -> OnServerUnsubscribe");
        }
    }

    /* access modifiers changed from: private */
    public void raiseServiceFailure(SignallingServiceArgs signallingServiceArgs, SignallingClientResponse signallingClientResponse) {
        SignallingServiceFailureArgs signallingServiceFailureArgs = new SignallingServiceFailureArgs(getChannelForService(signallingClientResponse.getMessage(), signallingServiceArgs), getDataJsonForService(signallingClientResponse.getMessage(), signallingServiceArgs), getDataBytesForService(signallingClientResponse.getMessage(), signallingServiceArgs));
        signallingServiceFailureArgs.setException(signallingClientResponse.getException());
        signallingServiceFailureArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingServiceArgs));
        signallingServiceFailureArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingServiceFailureArgs.setClient(this);
        signallingServiceFailureArgs.setDynamicProperties(signallingServiceArgs.getDynamicProperties());
        signallingServiceFailureArgs.setSource(signallingClientResponse.getFailureSource());
        IAction1<SignallingServiceFailureArgs> iAction1 = this._onServiceFailure;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingServiceFailureArgs, "Client -> OnServiceFailure");
        }
        if (signallingServiceArgs.getOnFailure() != null) {
            raiseAction(signallingServiceArgs.getOnFailure(), signallingServiceFailureArgs, "Client -> Service -> OnFailure");
        }
    }

    /* access modifiers changed from: private */
    public void raiseServiceSuccess(SignallingServiceArgs signallingServiceArgs, SignallingClientResponse signallingClientResponse) {
        SignallingServiceSuccessArgs signallingServiceSuccessArgs = new SignallingServiceSuccessArgs(getChannelForService(signallingClientResponse.getMessage(), signallingServiceArgs), getDataJsonForService(signallingClientResponse.getMessage(), signallingServiceArgs), getDataBytesForService(signallingClientResponse.getMessage(), signallingServiceArgs));
        signallingServiceSuccessArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingServiceArgs));
        signallingServiceSuccessArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingServiceSuccessArgs.setClient(this);
        signallingServiceSuccessArgs.setDynamicProperties(signallingServiceArgs.getDynamicProperties());
        IAction1<SignallingServiceSuccessArgs> iAction1 = this._onServiceSuccess;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingServiceSuccessArgs, "Client -> OnServiceSuccess");
        }
        if (signallingServiceArgs.getOnSuccess() != null) {
            raiseAction(signallingServiceArgs.getOnSuccess(), signallingServiceSuccessArgs, "Client -> Service -> OnSuccess");
        }
    }

    /* access modifiers changed from: private */
    public void raiseSubscribeFailure(SignallingSubscribeArgs signallingSubscribeArgs, SignallingClientResponse signallingClientResponse) {
        SignallingSubscribeFailureArgs signallingSubscribeFailureArgs = new SignallingSubscribeFailureArgs(getChannelsForSubscribe(signallingClientResponse.getMessage(), signallingSubscribeArgs), getBindRecordsForSubscribe(signallingClientResponse.getMessage(), signallingSubscribeArgs), getBindIsPrivateForSubscribe(signallingClientResponse.getMessage(), signallingSubscribeArgs));
        signallingSubscribeFailureArgs.setException(signallingClientResponse.getException());
        signallingSubscribeFailureArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingSubscribeArgs));
        signallingSubscribeFailureArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingSubscribeFailureArgs.setClient(this);
        signallingSubscribeFailureArgs.setDynamicProperties(signallingSubscribeArgs.getDynamicProperties());
        IAction1<SignallingSubscribeFailureArgs> iAction1 = this._onSubscribeFailure;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingSubscribeFailureArgs, "Client -> OnSubscribeFailure");
        }
        if (signallingSubscribeArgs.getOnFailure() != null) {
            raiseAction(signallingSubscribeArgs.getOnFailure(), signallingSubscribeFailureArgs, "Client -> Subscribe -> OnFailure");
        }
    }

    private int raiseSubscribePresence(IAction1<SignallingSubscribePresenceArgs> iAction1, HashMap<String, Object> hashMap, SignallingMessage signallingMessage, int i) {
        SignallingRemoteClient remoteClient;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        String bayeuxChannel = signallingMessage.getBayeuxChannel();
        SignallingPresenceNotification fromJson = SignallingPresenceNotification.fromJson(signallingMessage.getDataJson());
        if (fromJson == null || Global.equals(fromJson.getRemoteClient().getClientId(), getClientId()) || (remoteClient = fromJson.getRemoteClient()) == null) {
            return i;
        }
        String userId = remoteClient.getUserId() != null ? remoteClient.getUserId() : StringExtensions.empty;
        String deviceId = remoteClient.getDeviceId() != null ? remoteClient.getDeviceId() : StringExtensions.empty;
        synchronized (this._presenceCacheLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this._presenceUserCache, bayeuxChannel, holder);
            HashMap hashMap2 = (HashMap) holder.getValue();
            if (!tryGetValue) {
                HashMap<String, HashMap<String, HashMap<String, SignallingRecords>>> item = HashMapExtensions.getItem(this._presenceUserCache);
                HashMap hashMap3 = new HashMap();
                HashMapExtensions.set(item, bayeuxChannel, hashMap3);
                hashMap2 = hashMap3;
            }
            Holder holder2 = new Holder(null);
            boolean tryGetValue2 = HashMapExtensions.tryGetValue(this._presenceDeviceCache, bayeuxChannel, holder2);
            HashMap hashMap4 = (HashMap) holder2.getValue();
            if (!tryGetValue2) {
                HashMap<String, HashMap<String, HashMap<String, SignallingRecords>>> item2 = HashMapExtensions.getItem(this._presenceDeviceCache);
                HashMap hashMap5 = new HashMap();
                HashMapExtensions.set(item2, bayeuxChannel, hashMap5);
                hashMap4 = hashMap5;
            }
            Holder holder3 = new Holder(null);
            boolean tryGetValue3 = HashMapExtensions.tryGetValue(hashMap2, userId, holder3);
            HashMap hashMap6 = (HashMap) holder3.getValue();
            if (!tryGetValue3) {
                HashMap item3 = HashMapExtensions.getItem(hashMap2);
                hashMap6 = new HashMap();
                HashMapExtensions.set(item3, userId, hashMap6);
            }
            Holder holder4 = new Holder(null);
            boolean tryGetValue4 = HashMapExtensions.tryGetValue(hashMap4, deviceId, holder4);
            HashMap hashMap7 = (HashMap) holder4.getValue();
            if (!tryGetValue4) {
                HashMap item4 = HashMapExtensions.getItem(hashMap4);
                HashMap hashMap8 = new HashMap();
                HashMapExtensions.set(item4, deviceId, hashMap8);
                hashMap7 = hashMap8;
            }
            z = true;
            z2 = false;
            if (Global.equals(fromJson.getType(), SignallingPresenceType.Subscribe)) {
                z4 = HashMapExtensions.getCount(hashMap6) == 0;
                if (HashMapExtensions.getCount(hashMap7) != 0) {
                    z = false;
                }
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap6), remoteClient.getClientId(), remoteClient.getBoundRecords());
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap7), remoteClient.getClientId(), remoteClient.getBoundRecords());
            } else {
                boolean z5 = HashMapExtensions.remove(hashMap6, remoteClient.getClientId()) && HashMapExtensions.getCount(hashMap6) == 0;
                if (HashMapExtensions.remove(hashMap7, remoteClient.getClientId())) {
                    if (HashMapExtensions.getCount(hashMap7) != 0) {
                        z = false;
                    }
                    z2 = z5;
                    z3 = z;
                    z4 = false;
                    z = false;
                } else {
                    z2 = z5;
                    z4 = false;
                    z = false;
                }
            }
            z3 = false;
        }
        SignallingSubscribePresenceArgs signallingSubscribePresenceArgs = new SignallingSubscribePresenceArgs(bayeuxChannel, fromJson.getRemoteClient(), fromJson.getType(), z4, z2, z, z3, this._connectionType, i);
        signallingSubscribePresenceArgs.setExtensions(signallingMessage.getExtensions());
        signallingSubscribePresenceArgs.setTimestamp(getTimestamp(signallingMessage));
        signallingSubscribePresenceArgs.setClient(this);
        signallingSubscribePresenceArgs.setDynamicProperties(hashMap);
        if (iAction1 != null) {
            raiseAction(iAction1, signallingSubscribePresenceArgs, "Client -> Subscribe -> OnPresence");
        }
        return signallingSubscribePresenceArgs.getReconnectAfter();
    }

    private int raiseSubscribeReceive(IAction1<SignallingSubscribeReceiveArgs> iAction1, HashMap<String, Object> hashMap, SignallingMessage signallingMessage, int i) {
        SignallingSubscribeReceiveArgs signallingSubscribeReceiveArgs = new SignallingSubscribeReceiveArgs(signallingMessage.getBayeuxChannel(), signallingMessage.__dataJson, signallingMessage.__dataBytes, this._connectionType, i);
        signallingSubscribeReceiveArgs.setExtensions(signallingMessage.getExtensions());
        signallingSubscribeReceiveArgs.setTimestamp(getTimestamp(signallingMessage));
        signallingSubscribeReceiveArgs.setClient(this);
        signallingSubscribeReceiveArgs.setDynamicProperties(hashMap);
        if (iAction1 != null) {
            raiseAction(iAction1, signallingSubscribeReceiveArgs, "Client -> Subscribe -> OnReceive");
        }
        return signallingSubscribeReceiveArgs.getReconnectAfter();
    }

    /* access modifiers changed from: private */
    public void raiseSubscribeSuccess(SignallingSubscribeArgs signallingSubscribeArgs, SignallingClientResponse signallingClientResponse) {
        SignallingSubscribeSuccessArgs signallingSubscribeSuccessArgs = new SignallingSubscribeSuccessArgs(getChannelsForSubscribe(signallingClientResponse.getMessage(), signallingSubscribeArgs), getBindRecordsForSubscribe(signallingClientResponse.getMessage(), signallingSubscribeArgs), getBindIsPrivateForSubscribe(signallingClientResponse.getMessage(), signallingSubscribeArgs), getSubscribedClientsForSubscribe(signallingClientResponse.getMessage(), signallingSubscribeArgs));
        signallingSubscribeSuccessArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingSubscribeArgs));
        signallingSubscribeSuccessArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingSubscribeSuccessArgs.setClient(this);
        signallingSubscribeSuccessArgs.setDynamicProperties(signallingSubscribeArgs.getDynamicProperties());
        IAction1<SignallingSubscribeSuccessArgs> iAction1 = this._onSubscribeSuccess;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingSubscribeSuccessArgs, "Client -> OnSubscribeSuccess");
        }
        if (signallingSubscribeArgs.getOnSuccess() != null) {
            raiseAction(signallingSubscribeArgs.getOnSuccess(), signallingSubscribeSuccessArgs, "Client -> Subscribe -> OnSuccess");
        }
    }

    /* access modifiers changed from: private */
    public void raiseUnbindFailure(SignallingUnbindArgs signallingUnbindArgs, SignallingClientResponse signallingClientResponse) {
        SignallingUnbindFailureArgs signallingUnbindFailureArgs = new SignallingUnbindFailureArgs(getKeysForUnbind(signallingClientResponse.getMessage(), signallingUnbindArgs));
        signallingUnbindFailureArgs.setException(signallingClientResponse.getException());
        signallingUnbindFailureArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingUnbindArgs));
        signallingUnbindFailureArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingUnbindFailureArgs.setClient(this);
        signallingUnbindFailureArgs.setDynamicProperties(signallingUnbindArgs.getDynamicProperties());
        IAction1<SignallingUnbindFailureArgs> iAction1 = this._onUnbindFailure;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingUnbindFailureArgs, "Client -> OnUnbindFailure");
        }
        if (signallingUnbindArgs.getOnFailure() != null) {
            raiseAction(signallingUnbindArgs.getOnFailure(), signallingUnbindFailureArgs, "Client -> Unbind -> OnFailure");
        }
    }

    /* access modifiers changed from: private */
    public void raiseUnbindSuccess(SignallingUnbindArgs signallingUnbindArgs, SignallingClientResponse signallingClientResponse, boolean z) {
        SignallingUnbindSuccessArgs signallingUnbindSuccessArgs = new SignallingUnbindSuccessArgs(getKeysForUnbind(signallingClientResponse.getMessage(), signallingUnbindArgs), z);
        signallingUnbindSuccessArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingUnbindArgs));
        signallingUnbindSuccessArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingUnbindSuccessArgs.setClient(this);
        signallingUnbindSuccessArgs.setDynamicProperties(signallingUnbindArgs.getDynamicProperties());
        IAction1<SignallingUnbindSuccessArgs> iAction1 = this._onUnbindSuccess;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingUnbindSuccessArgs, "Client -> OnUnbindSuccess");
        }
        if (signallingUnbindArgs.getOnSuccess() != null) {
            raiseAction(signallingUnbindArgs.getOnSuccess(), signallingUnbindSuccessArgs, "Client -> Unbind -> OnSuccess");
        }
    }

    /* access modifiers changed from: private */
    public void raiseUnsubscribeFailure(SignallingUnsubscribeArgs signallingUnsubscribeArgs, SignallingClientResponse signallingClientResponse) {
        SignallingUnsubscribeFailureArgs signallingUnsubscribeFailureArgs = new SignallingUnsubscribeFailureArgs(getChannelsForUnsubscribe(signallingClientResponse.getMessage(), signallingUnsubscribeArgs), getUnbindKeysForUnsubscribe(signallingClientResponse.getMessage(), signallingUnsubscribeArgs));
        signallingUnsubscribeFailureArgs.setException(signallingClientResponse.getException());
        signallingUnsubscribeFailureArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingUnsubscribeArgs));
        signallingUnsubscribeFailureArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingUnsubscribeFailureArgs.setClient(this);
        signallingUnsubscribeFailureArgs.setDynamicProperties(signallingUnsubscribeArgs.getDynamicProperties());
        IAction1<SignallingUnsubscribeFailureArgs> iAction1 = this._onUnsubscribeFailure;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingUnsubscribeFailureArgs, "Client -> OnUnsubscribeFailure");
        }
        if (signallingUnsubscribeArgs.getOnFailure() != null) {
            raiseAction(signallingUnsubscribeArgs.getOnFailure(), signallingUnsubscribeFailureArgs, "Client -> Unsubscribe -> OnFailure");
        }
    }

    /* access modifiers changed from: private */
    public void raiseUnsubscribeSuccess(SignallingUnsubscribeArgs signallingUnsubscribeArgs, SignallingClientResponse signallingClientResponse, boolean z) {
        SignallingUnsubscribeSuccessArgs signallingUnsubscribeSuccessArgs = new SignallingUnsubscribeSuccessArgs(getChannelsForUnsubscribe(signallingClientResponse.getMessage(), signallingUnsubscribeArgs), getUnbindKeysForUnsubscribe(signallingClientResponse.getMessage(), signallingUnsubscribeArgs), z);
        signallingUnsubscribeSuccessArgs.setExtensions(getExtensions(signallingClientResponse.getMessage(), signallingUnsubscribeArgs));
        signallingUnsubscribeSuccessArgs.setTimestamp(getTimestamp(signallingClientResponse.getMessage()));
        signallingUnsubscribeSuccessArgs.setClient(this);
        signallingUnsubscribeSuccessArgs.setDynamicProperties(signallingUnsubscribeArgs.getDynamicProperties());
        IAction1<SignallingUnsubscribeSuccessArgs> iAction1 = this._onUnsubscribeSuccess;
        if (iAction1 != null) {
            raiseAction(iAction1, signallingUnsubscribeSuccessArgs, "Client -> OnUnsubscribeSuccess");
        }
        if (signallingUnsubscribeArgs.getOnSuccess() != null) {
            raiseAction(signallingUnsubscribeArgs.getOnSuccess(), signallingUnsubscribeSuccessArgs, "Client -> Unsubscribe -> OnSuccess");
        }
    }

    private int raiseUserChannelDelivery(IAction1<SignallingSubscribeReceiveArgs> iAction1, SignallingMessage signallingMessage, int i) {
        SignallingSubscribeReceiveArgs signallingSubscribeReceiveArgs = new SignallingSubscribeReceiveArgs(signallingMessage.getBayeuxChannel(), signallingMessage.__dataJson, signallingMessage.__dataBytes, this._connectionType, i);
        signallingSubscribeReceiveArgs.setExtensions(signallingMessage.getExtensions());
        signallingSubscribeReceiveArgs.setTimestamp(getTimestamp(signallingMessage));
        signallingSubscribeReceiveArgs.setClient(this);
        if (iAction1 != null) {
            raiseAction(iAction1, signallingSubscribeReceiveArgs, "Client -> OnUserChannelReceive");
        }
        return signallingSubscribeReceiveArgs.getReconnectAfter();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0231, code lost:
        if (r4 >= fm.liveswitch.ArrayListExtensions.getCount(r7)) goto L_0x023e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0233, code lost:
        r5 = (java.util.HashMap) fm.liveswitch.ArrayListExtensions.getItem(r7).get(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x023e, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x023f, code lost:
        r2 = raiseSubscribePresence(r3, r5, r0, r2);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0246, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0247, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x024c, code lost:
        if (r4 >= fm.liveswitch.ArrayListExtensions.getCount(r5)) goto L_0x0271;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x024e, code lost:
        r3 = (fm.liveswitch.IAction1) fm.liveswitch.ArrayListExtensions.getItem(r5).get(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x025c, code lost:
        if (r4 >= fm.liveswitch.ArrayListExtensions.getCount(r7)) goto L_0x0269;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x025e, code lost:
        r6 = (java.util.HashMap) fm.liveswitch.ArrayListExtensions.getItem(r7).get(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0269, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x026a, code lost:
        r2 = raiseSubscribeReceive(r3, r6, r0, r2);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0271, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x021a, code lost:
        if (r3 == false) goto L_0x0247;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x021c, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0221, code lost:
        if (r4 >= fm.liveswitch.ArrayListExtensions.getCount(r6)) goto L_0x0246;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0223, code lost:
        r3 = (fm.liveswitch.IAction1) fm.liveswitch.ArrayListExtensions.getItem(r6).get(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int receiveMessage(fm.liveswitch.SignallingMessage r17, int r18) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            r2 = r18
            if (r0 == 0) goto L_0x0282
            java.lang.String r3 = r17.getBayeuxChannel()
            if (r3 == 0) goto L_0x0275
            java.lang.String r4 = fm.liveswitch.SignallingReserved.getClientChannelPrefix()
            boolean r4 = r3.startsWith(r4)
            if (r4 == 0) goto L_0x004d
            java.lang.String r3 = r17.getTag()
            java.lang.String r4 = fm.liveswitch.SignallingExtensible.getServerActionsExtensionName()
            boolean r3 = fm.liveswitch.Global.equals(r3, r4)
            if (r3 == 0) goto L_0x0042
            java.lang.String r0 = r17.getDataJson()
            if (r0 == 0) goto L_0x0035
            fm.liveswitch.SignallingMessage r0 = fm.liveswitch.SignallingMessage.fromJson(r0)
            int r0 = r1.processServerAction(r0, r2)
            return r0
        L_0x0035:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r2 = new java.lang.Exception
            java.lang.String r3 = "Signalling client could not process received message. Payload was null."
            r2.<init>(r3)
            r0.<init>(r2)
            throw r0
        L_0x0042:
            fm.liveswitch.IAction1<fm.liveswitch.SignallingSubscribeReceiveArgs> r3 = r1._onClientChannelReceive
            if (r3 == 0) goto L_0x004b
            int r0 = r1.raiseClientChannelDelivery(r3, r0, r2)
            goto L_0x004c
        L_0x004b:
            r0 = r2
        L_0x004c:
            return r0
        L_0x004d:
            java.lang.String r4 = fm.liveswitch.SignallingReserved.getDeviceChannelPrefix()
            boolean r4 = r3.startsWith(r4)
            if (r4 == 0) goto L_0x0062
            fm.liveswitch.IAction1<fm.liveswitch.SignallingSubscribeReceiveArgs> r3 = r1._onDeviceChannelReceive
            if (r3 == 0) goto L_0x0060
            int r0 = r1.raiseDeviceChannelDelivery(r3, r0, r2)
            goto L_0x0061
        L_0x0060:
            r0 = r2
        L_0x0061:
            return r0
        L_0x0062:
            java.lang.String r4 = fm.liveswitch.SignallingReserved.getUserChannelPrefix()
            boolean r3 = r3.startsWith(r4)
            if (r3 == 0) goto L_0x0077
            fm.liveswitch.IAction1<fm.liveswitch.SignallingSubscribeReceiveArgs> r3 = r1._onUserChannelReceive
            if (r3 == 0) goto L_0x0075
            int r0 = r1.raiseUserChannelDelivery(r3, r0, r2)
            goto L_0x0076
        L_0x0075:
            r0 = r2
        L_0x0076:
            return r0
        L_0x0077:
            fm.liveswitch.NullableBoolean r3 = r17.getPresence()
            boolean r3 = r3.getHasValue()
            if (r3 == 0) goto L_0x0095
            fm.liveswitch.NullableBoolean r3 = r17.getPresence()
            boolean r3 = r3.getHasValue()
            if (r3 != 0) goto L_0x008c
            goto L_0x0095
        L_0x008c:
            fm.liveswitch.NullableBoolean r3 = r17.getPresence()
            boolean r3 = r3.getValue()
            goto L_0x0096
        L_0x0095:
            r3 = 0
        L_0x0096:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            if (r3 != 0) goto L_0x0103
            java.lang.Object r8 = r1._customOnReceivesLock
            monitor-enter(r8)
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, fm.liveswitch.IAction1<fm.liveswitch.SignallingSubscribeReceiveArgs>>> r9 = r1._customOnReceives     // Catch:{ all -> 0x0100 }
            java.util.Set r9 = fm.liveswitch.HashMapExtensions.getKeys(r9)     // Catch:{ all -> 0x0100 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x0100 }
        L_0x00b4:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x0100 }
            if (r10 == 0) goto L_0x00fe
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x0100 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x0100 }
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, fm.liveswitch.IAction1<fm.liveswitch.SignallingSubscribeReceiveArgs>>> r11 = r1._customOnReceives     // Catch:{ all -> 0x0100 }
            java.util.HashMap r11 = fm.liveswitch.HashMapExtensions.getItem(r11)     // Catch:{ all -> 0x0100 }
            java.lang.Object r10 = r11.get(r10)     // Catch:{ all -> 0x0100 }
            java.util.HashMap r10 = (java.util.HashMap) r10     // Catch:{ all -> 0x0100 }
            java.util.Set r11 = fm.liveswitch.HashMapExtensions.getKeys(r10)     // Catch:{ all -> 0x0100 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x0100 }
        L_0x00d4:
            boolean r12 = r11.hasNext()     // Catch:{ all -> 0x0100 }
            if (r12 == 0) goto L_0x00b4
            java.lang.Object r12 = r11.next()     // Catch:{ all -> 0x0100 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0100 }
            java.lang.String r13 = r17.getChannel()     // Catch:{ all -> 0x0100 }
            boolean r13 = fm.liveswitch.Global.equals(r12, r13)     // Catch:{ all -> 0x0100 }
            if (r13 == 0) goto L_0x00d4
            java.util.HashMap r13 = fm.liveswitch.HashMapExtensions.getItem(r10)     // Catch:{ all -> 0x0100 }
            java.lang.Object r12 = r13.get(r12)     // Catch:{ all -> 0x0100 }
            r5.add(r12)     // Catch:{ all -> 0x0100 }
            java.util.HashMap r12 = new java.util.HashMap     // Catch:{ all -> 0x0100 }
            r12.<init>()     // Catch:{ all -> 0x0100 }
            r7.add(r12)     // Catch:{ all -> 0x0100 }
            goto L_0x00d4
        L_0x00fe:
            monitor-exit(r8)     // Catch:{ all -> 0x0100 }
            goto L_0x0103
        L_0x0100:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0100 }
            throw r0
        L_0x0103:
            java.lang.Object r8 = r1._subscribedOnReceivesLock
            monitor-enter(r8)
            r9 = 0
            if (r3 == 0) goto L_0x0174
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, fm.liveswitch.IAction1<fm.liveswitch.SignallingSubscribePresenceArgs>>> r10 = r1._subscribedOnPresences     // Catch:{ all -> 0x0272 }
            java.util.Set r10 = fm.liveswitch.HashMapExtensions.getKeys(r10)     // Catch:{ all -> 0x0272 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x0272 }
        L_0x0113:
            boolean r11 = r10.hasNext()     // Catch:{ all -> 0x0272 }
            if (r11 == 0) goto L_0x01df
            java.lang.Object r11 = r10.next()     // Catch:{ all -> 0x0272 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0272 }
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, fm.liveswitch.IAction1<fm.liveswitch.SignallingSubscribePresenceArgs>>> r12 = r1._subscribedOnPresences     // Catch:{ all -> 0x0272 }
            java.util.HashMap r12 = fm.liveswitch.HashMapExtensions.getItem(r12)     // Catch:{ all -> 0x0272 }
            java.lang.Object r12 = r12.get(r11)     // Catch:{ all -> 0x0272 }
            java.util.HashMap r12 = (java.util.HashMap) r12     // Catch:{ all -> 0x0272 }
            java.util.Set r13 = fm.liveswitch.HashMapExtensions.getKeys(r12)     // Catch:{ all -> 0x0272 }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ all -> 0x0272 }
        L_0x0133:
            boolean r14 = r13.hasNext()     // Catch:{ all -> 0x0272 }
            if (r14 == 0) goto L_0x0113
            java.lang.Object r14 = r13.next()     // Catch:{ all -> 0x0272 }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ all -> 0x0272 }
            java.lang.String r15 = r17.getChannel()     // Catch:{ all -> 0x0272 }
            boolean r15 = fm.liveswitch.Global.equals(r14, r15)     // Catch:{ all -> 0x0272 }
            if (r15 == 0) goto L_0x0133
            java.util.HashMap r15 = fm.liveswitch.HashMapExtensions.getItem(r12)     // Catch:{ all -> 0x0272 }
            java.lang.Object r15 = r15.get(r14)     // Catch:{ all -> 0x0272 }
            r6.add(r15)     // Catch:{ all -> 0x0272 }
            fm.liveswitch.Holder r15 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0272 }
            r15.<init>(r9)     // Catch:{ all -> 0x0272 }
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, java.lang.Object>> r4 = r1._subscribedOnPresencesDynamicProperties     // Catch:{ all -> 0x0272 }
            java.lang.String r14 = getSubscriptionKey(r14, r11)     // Catch:{ all -> 0x0272 }
            boolean r4 = fm.liveswitch.HashMapExtensions.tryGetValue(r4, r14, r15)     // Catch:{ all -> 0x0272 }
            java.lang.Object r14 = r15.getValue()     // Catch:{ all -> 0x0272 }
            java.util.HashMap r14 = (java.util.HashMap) r14     // Catch:{ all -> 0x0272 }
            if (r4 != 0) goto L_0x0170
            java.util.HashMap r14 = new java.util.HashMap     // Catch:{ all -> 0x0272 }
            r14.<init>()     // Catch:{ all -> 0x0272 }
        L_0x0170:
            r7.add(r14)     // Catch:{ all -> 0x0272 }
            goto L_0x0133
        L_0x0174:
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, fm.liveswitch.IAction1<fm.liveswitch.SignallingSubscribeReceiveArgs>>> r4 = r1._subscribedOnReceives     // Catch:{ all -> 0x0272 }
            java.util.Set r4 = fm.liveswitch.HashMapExtensions.getKeys(r4)     // Catch:{ all -> 0x0272 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0272 }
        L_0x017e:
            boolean r10 = r4.hasNext()     // Catch:{ all -> 0x0272 }
            if (r10 == 0) goto L_0x01df
            java.lang.Object r10 = r4.next()     // Catch:{ all -> 0x0272 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x0272 }
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, fm.liveswitch.IAction1<fm.liveswitch.SignallingSubscribeReceiveArgs>>> r11 = r1._subscribedOnReceives     // Catch:{ all -> 0x0272 }
            java.util.HashMap r11 = fm.liveswitch.HashMapExtensions.getItem(r11)     // Catch:{ all -> 0x0272 }
            java.lang.Object r11 = r11.get(r10)     // Catch:{ all -> 0x0272 }
            java.util.HashMap r11 = (java.util.HashMap) r11     // Catch:{ all -> 0x0272 }
            java.util.Set r12 = fm.liveswitch.HashMapExtensions.getKeys(r11)     // Catch:{ all -> 0x0272 }
            java.util.Iterator r12 = r12.iterator()     // Catch:{ all -> 0x0272 }
        L_0x019e:
            boolean r13 = r12.hasNext()     // Catch:{ all -> 0x0272 }
            if (r13 == 0) goto L_0x017e
            java.lang.Object r13 = r12.next()     // Catch:{ all -> 0x0272 }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ all -> 0x0272 }
            java.lang.String r14 = r17.getChannel()     // Catch:{ all -> 0x0272 }
            boolean r14 = fm.liveswitch.Global.equals(r13, r14)     // Catch:{ all -> 0x0272 }
            if (r14 == 0) goto L_0x019e
            java.util.HashMap r14 = fm.liveswitch.HashMapExtensions.getItem(r11)     // Catch:{ all -> 0x0272 }
            java.lang.Object r14 = r14.get(r13)     // Catch:{ all -> 0x0272 }
            r5.add(r14)     // Catch:{ all -> 0x0272 }
            fm.liveswitch.Holder r14 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0272 }
            r14.<init>(r9)     // Catch:{ all -> 0x0272 }
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, java.lang.Object>> r15 = r1._subscribedOnReceivesDynamicProperties     // Catch:{ all -> 0x0272 }
            java.lang.String r13 = getSubscriptionKey(r13, r10)     // Catch:{ all -> 0x0272 }
            boolean r13 = fm.liveswitch.HashMapExtensions.tryGetValue(r15, r13, r14)     // Catch:{ all -> 0x0272 }
            java.lang.Object r14 = r14.getValue()     // Catch:{ all -> 0x0272 }
            java.util.HashMap r14 = (java.util.HashMap) r14     // Catch:{ all -> 0x0272 }
            if (r13 != 0) goto L_0x01db
            java.util.HashMap r14 = new java.util.HashMap     // Catch:{ all -> 0x0272 }
            r14.<init>()     // Catch:{ all -> 0x0272 }
        L_0x01db:
            r7.add(r14)     // Catch:{ all -> 0x0272 }
            goto L_0x019e
        L_0x01df:
            int r4 = fm.liveswitch.ArrayListExtensions.getCount(r5)     // Catch:{ all -> 0x0272 }
            if (r4 != 0) goto L_0x0219
            int r4 = fm.liveswitch.ArrayListExtensions.getCount(r6)     // Catch:{ all -> 0x0272 }
            if (r4 != 0) goto L_0x0219
            fm.liveswitch.Holder r3 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0272 }
            r3.<init>(r9)     // Catch:{ all -> 0x0272 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.SignallingMessage>> r4 = r1._pendingReceives     // Catch:{ all -> 0x0272 }
            java.lang.String r5 = r17.getChannel()     // Catch:{ all -> 0x0272 }
            boolean r4 = fm.liveswitch.HashMapExtensions.tryGetValue(r4, r5, r3)     // Catch:{ all -> 0x0272 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x0272 }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x0272 }
            if (r4 != 0) goto L_0x0214
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0272 }
            r3.<init>()     // Catch:{ all -> 0x0272 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.SignallingMessage>> r4 = r1._pendingReceives     // Catch:{ all -> 0x0272 }
            java.util.HashMap r4 = fm.liveswitch.HashMapExtensions.getItem(r4)     // Catch:{ all -> 0x0272 }
            java.lang.String r5 = r17.getChannel()     // Catch:{ all -> 0x0272 }
            fm.liveswitch.HashMapExtensions.set(r4, r5, r3)     // Catch:{ all -> 0x0272 }
        L_0x0214:
            r3.add(r0)     // Catch:{ all -> 0x0272 }
            monitor-exit(r8)     // Catch:{ all -> 0x0272 }
            return r2
        L_0x0219:
            monitor-exit(r8)     // Catch:{ all -> 0x0272 }
            if (r3 == 0) goto L_0x0247
            r4 = 0
        L_0x021d:
            int r3 = fm.liveswitch.ArrayListExtensions.getCount(r6)
            if (r4 >= r3) goto L_0x0246
            java.util.ArrayList r3 = fm.liveswitch.ArrayListExtensions.getItem(r6)
            java.lang.Object r3 = r3.get(r4)
            fm.liveswitch.IAction1 r3 = (fm.liveswitch.IAction1) r3
            int r5 = fm.liveswitch.ArrayListExtensions.getCount(r7)
            if (r4 >= r5) goto L_0x023e
            java.util.ArrayList r5 = fm.liveswitch.ArrayListExtensions.getItem(r7)
            java.lang.Object r5 = r5.get(r4)
            java.util.HashMap r5 = (java.util.HashMap) r5
            goto L_0x023f
        L_0x023e:
            r5 = r9
        L_0x023f:
            int r2 = r1.raiseSubscribePresence(r3, r5, r0, r2)
            int r4 = r4 + 1
            goto L_0x021d
        L_0x0246:
            return r2
        L_0x0247:
            r4 = 0
        L_0x0248:
            int r3 = fm.liveswitch.ArrayListExtensions.getCount(r5)
            if (r4 >= r3) goto L_0x0271
            java.util.ArrayList r3 = fm.liveswitch.ArrayListExtensions.getItem(r5)
            java.lang.Object r3 = r3.get(r4)
            fm.liveswitch.IAction1 r3 = (fm.liveswitch.IAction1) r3
            int r6 = fm.liveswitch.ArrayListExtensions.getCount(r7)
            if (r4 >= r6) goto L_0x0269
            java.util.ArrayList r6 = fm.liveswitch.ArrayListExtensions.getItem(r7)
            java.lang.Object r6 = r6.get(r4)
            java.util.HashMap r6 = (java.util.HashMap) r6
            goto L_0x026a
        L_0x0269:
            r6 = r9
        L_0x026a:
            int r2 = r1.raiseSubscribeReceive(r3, r6, r0, r2)
            int r4 = r4 + 1
            goto L_0x0248
        L_0x0271:
            return r2
        L_0x0272:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0272 }
            throw r0
        L_0x0275:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r2 = new java.lang.Exception
            java.lang.String r3 = "Signalling client could not process received message. BayeuxChannel was null."
            r2.<init>(r3)
            r0.<init>(r2)
            throw r0
        L_0x0282:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r2 = new java.lang.Exception
            java.lang.String r3 = "Signalling client could not process received message. Message was null."
            r2.<init>(r3)
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SignallingClient.receiveMessage(fm.liveswitch.SignallingMessage, int):int");
    }

    /* access modifiers changed from: private */
    public void receiveSink(SignallingSubscribeReceiveArgs signallingSubscribeReceiveArgs) {
        __log.warn("Received message on channel with no receive handler.");
    }

    /* access modifiers changed from: private */
    public void removeBoundRecords(String[] strArr) {
        synchronized (this._boundRecordsLock) {
            for (String remove : strArr) {
                this._boundRecords.remove(remove);
            }
        }
    }

    public static void removeOnBindEnd(IAction2<SignallingClient, SignallingClientBindEndArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onBindEnd, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onBindEnd.remove(iAction2);
        if (__onBindEnd.size() == 0) {
            _onBindEnd = null;
        }
    }

    public void removeOnBindFailure(IAction1<SignallingBindFailureArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onBindFailure, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onBindFailure.remove(iAction1);
        if (this.__onBindFailure.size() == 0) {
            this._onBindFailure = null;
        }
    }

    public static void removeOnBindRequest(IAction2<SignallingClient, SignallingClientBindRequestArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onBindRequest, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onBindRequest.remove(iAction2);
        if (__onBindRequest.size() == 0) {
            _onBindRequest = null;
        }
    }

    public static void removeOnBindResponse(IAction2<SignallingClient, SignallingClientBindResponseArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onBindResponse, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onBindResponse.remove(iAction2);
        if (__onBindResponse.size() == 0) {
            _onBindResponse = null;
        }
    }

    public void removeOnBindSuccess(IAction1<SignallingBindSuccessArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onBindSuccess, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onBindSuccess.remove(iAction1);
        if (this.__onBindSuccess.size() == 0) {
            this._onBindSuccess = null;
        }
    }

    public void removeOnClientChannelReceive(IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onClientChannelReceive, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onClientChannelReceive.remove(iAction1);
        if (this.__onClientChannelReceive.size() == 0) {
            this._onClientChannelReceive = null;
        }
    }

    public static void removeOnConnectEnd(IAction2<SignallingClient, SignallingClientConnectEndArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onConnectEnd, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onConnectEnd.remove(iAction2);
        if (__onConnectEnd.size() == 0) {
            _onConnectEnd = null;
        }
    }

    public void removeOnConnectFailure(IAction1<SignallingConnectFailureArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onConnectFailure, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onConnectFailure.remove(iAction1);
        if (this.__onConnectFailure.size() == 0) {
            this._onConnectFailure = null;
        }
    }

    public static void removeOnConnectRequest(IAction2<SignallingClient, SignallingClientConnectRequestArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onConnectRequest, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onConnectRequest.remove(iAction2);
        if (__onConnectRequest.size() == 0) {
            _onConnectRequest = null;
        }
    }

    public static void removeOnConnectResponse(IAction2<SignallingClient, SignallingClientConnectResponseArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onConnectResponse, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onConnectResponse.remove(iAction2);
        if (__onConnectResponse.size() == 0) {
            _onConnectResponse = null;
        }
    }

    public void removeOnConnectSuccess(IAction1<SignallingConnectSuccessArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onConnectSuccess, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onConnectSuccess.remove(iAction1);
        if (this.__onConnectSuccess.size() == 0) {
            this._onConnectSuccess = null;
        }
    }

    public void removeOnDeviceChannelReceive(IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onDeviceChannelReceive, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onDeviceChannelReceive.remove(iAction1);
        if (this.__onDeviceChannelReceive.size() == 0) {
            this._onDeviceChannelReceive = null;
        }
    }

    public void removeOnDisconnectComplete(IAction1<SignallingDisconnectCompleteArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onDisconnectComplete, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onDisconnectComplete.remove(iAction1);
        if (this.__onDisconnectComplete.size() == 0) {
            this._onDisconnectComplete = null;
        }
    }

    public static void removeOnDisconnectEnd(IAction2<SignallingClient, SignallingClientDisconnectEndArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onDisconnectEnd, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onDisconnectEnd.remove(iAction2);
        if (__onDisconnectEnd.size() == 0) {
            _onDisconnectEnd = null;
        }
    }

    public static void removeOnDisconnectRequest(IAction2<SignallingClient, SignallingClientDisconnectRequestArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onDisconnectRequest, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onDisconnectRequest.remove(iAction2);
        if (__onDisconnectRequest.size() == 0) {
            _onDisconnectRequest = null;
        }
    }

    public static void removeOnDisconnectResponse(IAction2<SignallingClient, SignallingClientDisconnectResponseArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onDisconnectResponse, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onDisconnectResponse.remove(iAction2);
        if (__onDisconnectResponse.size() == 0) {
            _onDisconnectResponse = null;
        }
    }

    public static void removeOnPublishEnd(IAction2<SignallingClient, SignallingClientPublishEndArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onPublishEnd, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onPublishEnd.remove(iAction2);
        if (__onPublishEnd.size() == 0) {
            _onPublishEnd = null;
        }
    }

    public void removeOnPublishFailure(IAction1<SignallingPublishFailureArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onPublishFailure, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onPublishFailure.remove(iAction1);
        if (this.__onPublishFailure.size() == 0) {
            this._onPublishFailure = null;
        }
    }

    public static void removeOnPublishRequest(IAction2<SignallingClient, SignallingClientPublishRequestArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onPublishRequest, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onPublishRequest.remove(iAction2);
        if (__onPublishRequest.size() == 0) {
            _onPublishRequest = null;
        }
    }

    public static void removeOnPublishResponse(IAction2<SignallingClient, SignallingClientPublishResponseArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onPublishResponse, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onPublishResponse.remove(iAction2);
        if (__onPublishResponse.size() == 0) {
            _onPublishResponse = null;
        }
    }

    public void removeOnPublishSuccess(IAction1<SignallingPublishSuccessArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onPublishSuccess, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onPublishSuccess.remove(iAction1);
        if (this.__onPublishSuccess.size() == 0) {
            this._onPublishSuccess = null;
        }
    }

    public void removeOnServerBind(IAction1<SignallingServerBindArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onServerBind, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onServerBind.remove(iAction1);
        if (this.__onServerBind.size() == 0) {
            this._onServerBind = null;
        }
    }

    public void removeOnServerSubscribe(IAction1<SignallingServerSubscribeArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onServerSubscribe, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onServerSubscribe.remove(iAction1);
        if (this.__onServerSubscribe.size() == 0) {
            this._onServerSubscribe = null;
        }
    }

    public void removeOnServerUnbind(IAction1<SignallingServerUnbindArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onServerUnbind, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onServerUnbind.remove(iAction1);
        if (this.__onServerUnbind.size() == 0) {
            this._onServerUnbind = null;
        }
    }

    public void removeOnServerUnsubscribe(IAction1<SignallingServerUnsubscribeArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onServerUnsubscribe, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onServerUnsubscribe.remove(iAction1);
        if (this.__onServerUnsubscribe.size() == 0) {
            this._onServerUnsubscribe = null;
        }
    }

    public static void removeOnServiceEnd(IAction2<SignallingClient, SignallingClientServiceEndArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onServiceEnd, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onServiceEnd.remove(iAction2);
        if (__onServiceEnd.size() == 0) {
            _onServiceEnd = null;
        }
    }

    public void removeOnServiceFailure(IAction1<SignallingServiceFailureArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onServiceFailure, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onServiceFailure.remove(iAction1);
        if (this.__onServiceFailure.size() == 0) {
            this._onServiceFailure = null;
        }
    }

    public static void removeOnServiceRequest(IAction2<SignallingClient, SignallingClientServiceRequestArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onServiceRequest, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onServiceRequest.remove(iAction2);
        if (__onServiceRequest.size() == 0) {
            _onServiceRequest = null;
        }
    }

    public static void removeOnServiceResponse(IAction2<SignallingClient, SignallingClientServiceResponseArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onServiceResponse, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onServiceResponse.remove(iAction2);
        if (__onServiceResponse.size() == 0) {
            _onServiceResponse = null;
        }
    }

    public void removeOnServiceSuccess(IAction1<SignallingServiceSuccessArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onServiceSuccess, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onServiceSuccess.remove(iAction1);
        if (this.__onServiceSuccess.size() == 0) {
            this._onServiceSuccess = null;
        }
    }

    public void removeOnStateChange(IAction1<SignallingClient> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    public static void removeOnSubscribeEnd(IAction2<SignallingClient, SignallingClientSubscribeEndArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onSubscribeEnd, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onSubscribeEnd.remove(iAction2);
        if (__onSubscribeEnd.size() == 0) {
            _onSubscribeEnd = null;
        }
    }

    public void removeOnSubscribeFailure(IAction1<SignallingSubscribeFailureArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onSubscribeFailure, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onSubscribeFailure.remove(iAction1);
        if (this.__onSubscribeFailure.size() == 0) {
            this._onSubscribeFailure = null;
        }
    }

    public static void removeOnSubscribeRequest(IAction2<SignallingClient, SignallingClientSubscribeRequestArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onSubscribeRequest, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onSubscribeRequest.remove(iAction2);
        if (__onSubscribeRequest.size() == 0) {
            _onSubscribeRequest = null;
        }
    }

    public static void removeOnSubscribeResponse(IAction2<SignallingClient, SignallingClientSubscribeResponseArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onSubscribeResponse, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onSubscribeResponse.remove(iAction2);
        if (__onSubscribeResponse.size() == 0) {
            _onSubscribeResponse = null;
        }
    }

    public void removeOnSubscribeSuccess(IAction1<SignallingSubscribeSuccessArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onSubscribeSuccess, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onSubscribeSuccess.remove(iAction1);
        if (this.__onSubscribeSuccess.size() == 0) {
            this._onSubscribeSuccess = null;
        }
    }

    public static void removeOnUnbindEnd(IAction2<SignallingClient, SignallingClientUnbindEndArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onUnbindEnd, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onUnbindEnd.remove(iAction2);
        if (__onUnbindEnd.size() == 0) {
            _onUnbindEnd = null;
        }
    }

    public void removeOnUnbindFailure(IAction1<SignallingUnbindFailureArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onUnbindFailure, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onUnbindFailure.remove(iAction1);
        if (this.__onUnbindFailure.size() == 0) {
            this._onUnbindFailure = null;
        }
    }

    public static void removeOnUnbindRequest(IAction2<SignallingClient, SignallingClientUnbindRequestArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onUnbindRequest, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onUnbindRequest.remove(iAction2);
        if (__onUnbindRequest.size() == 0) {
            _onUnbindRequest = null;
        }
    }

    public static void removeOnUnbindResponse(IAction2<SignallingClient, SignallingClientUnbindResponseArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onUnbindResponse, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onUnbindResponse.remove(iAction2);
        if (__onUnbindResponse.size() == 0) {
            _onUnbindResponse = null;
        }
    }

    public void removeOnUnbindSuccess(IAction1<SignallingUnbindSuccessArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onUnbindSuccess, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onUnbindSuccess.remove(iAction1);
        if (this.__onUnbindSuccess.size() == 0) {
            this._onUnbindSuccess = null;
        }
    }

    public static void removeOnUnsubscribeEnd(IAction2<SignallingClient, SignallingClientUnsubscribeEndArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onUnsubscribeEnd, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onUnsubscribeEnd.remove(iAction2);
        if (__onUnsubscribeEnd.size() == 0) {
            _onUnsubscribeEnd = null;
        }
    }

    public void removeOnUnsubscribeFailure(IAction1<SignallingUnsubscribeFailureArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onUnsubscribeFailure, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onUnsubscribeFailure.remove(iAction1);
        if (this.__onUnsubscribeFailure.size() == 0) {
            this._onUnsubscribeFailure = null;
        }
    }

    public static void removeOnUnsubscribeRequest(IAction2<SignallingClient, SignallingClientUnsubscribeRequestArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onUnsubscribeRequest, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onUnsubscribeRequest.remove(iAction2);
        if (__onUnsubscribeRequest.size() == 0) {
            _onUnsubscribeRequest = null;
        }
    }

    public static void removeOnUnsubscribeResponse(IAction2<SignallingClient, SignallingClientUnsubscribeResponseArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onUnsubscribeResponse, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onUnsubscribeResponse.remove(iAction2);
        if (__onUnsubscribeResponse.size() == 0) {
            _onUnsubscribeResponse = null;
        }
    }

    public void removeOnUnsubscribeSuccess(IAction1<SignallingUnsubscribeSuccessArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onUnsubscribeSuccess, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onUnsubscribeSuccess.remove(iAction1);
        if (this.__onUnsubscribeSuccess.size() == 0) {
            this._onUnsubscribeSuccess = null;
        }
    }

    public void removeOnUserChannelReceive(IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onUserChannelReceive, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onUserChannelReceive.remove(iAction1);
        if (this.__onUserChannelReceive.size() == 0) {
            this._onUserChannelReceive = null;
        }
    }

    /* access modifiers changed from: private */
    public void removeSubscribedChannels(String str, String[] strArr) {
        synchronized (this._subscribedChannelsLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this._subscribedChannels, str, holder);
            HashMap hashMap = (HashMap) holder.getValue();
            if (tryGetValue) {
                for (String remove : strArr) {
                    HashMapExtensions.remove(hashMap, remove);
                }
                if (HashMapExtensions.getCount(hashMap) == 0) {
                    HashMapExtensions.remove(this._subscribedChannels, str);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeSubscribedOnPresence(String str, String[] strArr) {
        synchronized (this._subscribedOnReceivesLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this._subscribedOnPresences, str, holder);
            HashMap hashMap = (HashMap) holder.getValue();
            if (tryGetValue) {
                for (String str2 : strArr) {
                    HashMapExtensions.remove(hashMap, str2);
                    HashMapExtensions.remove(this._subscribedOnPresencesDynamicProperties, getSubscriptionKey(str2, str));
                }
                if (HashMapExtensions.getCount(hashMap) == 0) {
                    HashMapExtensions.remove(this._subscribedOnPresences, str);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeSubscribedOnReceive(String str, String[] strArr) {
        synchronized (this._subscribedOnReceivesLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this._subscribedOnReceives, str, holder);
            HashMap hashMap = (HashMap) holder.getValue();
            if (tryGetValue) {
                for (String str2 : strArr) {
                    HashMapExtensions.remove(hashMap, str2);
                    HashMapExtensions.remove(this._subscribedOnReceivesDynamicProperties, getSubscriptionKey(str2, str));
                }
                if (HashMapExtensions.getCount(hashMap) == 0) {
                    HashMapExtensions.remove(this._subscribedOnReceives, str);
                }
            }
        }
    }

    private void restream(boolean z, int i) {
        int i2 = this._lastInterval;
        if (i < i2) {
            i = i2;
        }
        this._receivedMessages = z;
        if (!Global.equals(super.getConcurrencyMode(), SignallingConcurrencyMode.Low) || i <= 0) {
            stream();
            return;
        }
        TimeoutTimer timeoutTimer = new TimeoutTimer(new IAction1<Object>() {
            public void invoke(Object obj) {
                SignallingClient.this.stream();
            }
        }, (Object) null);
        this._deferrer = timeoutTimer;
        timeoutTimer.start(i);
    }

    private void send(SignallingClientRequest signallingClientRequest, String str, boolean z, int i, int i2) {
        sendMany(new SignallingClientRequest[]{signallingClientRequest}, str, z, i, i2);
    }

    /* access modifiers changed from: private */
    public void sendCallback(SignallingClientSendState signallingClientSendState, SignallingMessageResponseArgs signallingMessageResponseArgs) {
        if (signallingMessageResponseArgs.getException() != null) {
            raiseSendException(signallingClientSendState, signallingMessageResponseArgs.getException(), FailureSource.Network);
        } else if (signallingMessageResponseArgs.getMessages() == null) {
            raiseSendException(signallingClientSendState, new Exception(super.getInvalidResponseMessage(signallingMessageResponseArgs)), FailureSource.Message);
        } else if (ArrayExtensions.getLength((Object[]) signallingMessageResponseArgs.getMessages()) == 0) {
            raiseSendException(signallingClientSendState, new Exception(super.getEmptyResponseMessage(signallingMessageResponseArgs)), FailureSource.Message);
        } else {
            ArrayList arrayList = new ArrayList();
            for (SignallingMessage signallingMessage : signallingMessageResponseArgs.getMessages()) {
                SignallingAdvice advice = signallingMessage.getAdvice();
                if (advice != null) {
                    processAdvice(advice);
                    SignallingConnectionType signallingConnectionType = this._connectionType;
                    if (signallingConnectionType == SignallingConnectionType.WebSocket) {
                        if (advice.getWebSocket() != null) {
                            processAdvice(advice.getWebSocket());
                        }
                    } else if (signallingConnectionType == SignallingConnectionType.LongPolling) {
                        if (advice.getLongPolling() != null) {
                            processAdvice(advice.getLongPolling());
                        }
                    } else if (signallingConnectionType == SignallingConnectionType.CallbackPolling && advice.getCallbackPolling() != null) {
                        processAdvice(advice.getCallbackPolling());
                    }
                }
                if (signallingClientSendState.getIsStream() && !Global.equals(signallingMessage.getBayeuxChannel(), SignallingMetaChannels.getConnect())) {
                    arrayList.add(signallingMessage);
                } else if (StringExtensions.isNullOrEmpty(signallingMessage.getId())) {
                    raiseSendException(signallingClientSendState, new Exception(signallingMessage.getError()), FailureSource.Message);
                    return;
                } else {
                    Exception exc = null;
                    Holder holder = new Holder(null);
                    boolean tryGetValue = HashMapExtensions.tryGetValue(signallingClientSendState.getRequestMapping(), signallingMessage.getId(), holder);
                    SignallingClientRequest signallingClientRequest = (SignallingClientRequest) holder.getValue();
                    if (!tryGetValue) {
                        __log.error("Unknown response message id.");
                        raiseSendException(signallingClientSendState, new Exception(signallingMessage.getError()), FailureSource.Message);
                        return;
                    }
                    SignallingClientResponse signallingClientResponse = new SignallingClientResponse();
                    signallingClientResponse.setDynamicProperties(signallingClientRequest.getDynamicProperties());
                    signallingClientResponse.setMessage(signallingMessage);
                    if (!signallingMessage.getSuccessful()) {
                        exc = new Exception(signallingMessage.getError());
                    }
                    signallingClientResponse.setException(exc);
                    signallingClientResponse.setFailureSource(signallingMessage.getSuccessful() ? FailureSource.None : FailureSource.Message);
                    signallingClientRequest.getCallback().invoke(signallingClientResponse);
                }
            }
            if (ArrayListExtensions.getCount(arrayList) > 0) {
                SignallingClientResponse signallingClientResponse2 = new SignallingClientResponse();
                signallingClientResponse2.setDynamicProperties(signallingClientSendState.getRequests()[0].getDynamicProperties());
                signallingClientResponse2.setMessages((SignallingMessage[]) arrayList.toArray(new SignallingMessage[0]));
                signallingClientSendState.getRequests()[0].getCallback().invoke(signallingClientResponse2);
            }
            for (SignallingMessage serverActions : signallingMessageResponseArgs.getMessages()) {
                SignallingMessage[] serverActions2 = serverActions.getServerActions();
                if (serverActions2 != null) {
                    for (SignallingMessage processServerAction : serverActions2) {
                        processServerAction(processServerAction, this._lastInterval);
                    }
                }
            }
        }
    }

    private void sendMany(SignallingClientRequest[] signallingClientRequestArr, String str, boolean z, int i, int i2) {
        int i3;
        int i4;
        SignallingClientRequest[] signallingClientRequestArr2 = signallingClientRequestArr;
        String str2 = str;
        boolean equals = ArrayExtensions.getLength((Object[]) signallingClientRequestArr) == 1 ? Global.equals(signallingClientRequestArr2[0].getMessage().getBayeuxChannel(), SignallingMetaChannels.getConnect()) : false;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (SignallingClientRequest signallingClientRequest : signallingClientRequestArr2) {
            if (!signallingClientRequest.getMessage().isConnect()) {
                signallingClientRequest.getMessage().setClientId(getClientId());
                signallingClientRequest.getMessage().setStreamId(getStreamId());
                signallingClientRequest.getMessage().setAuthToken(getAuthToken());
            }
            if (super.getDisableBinary()) {
                signallingClientRequest.getMessage().setDisableBinary(new NullableBoolean(super.getDisableBinary()));
            }
            synchronized (this._counterLock) {
                this._counter++;
                signallingClientRequest.getMessage().setId(IntegerExtensions.toString(Integer.valueOf(this._counter)));
            }
            arrayList.add(signallingClientRequest.getMessage());
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), signallingClientRequest.getMessage().getId(), signallingClientRequest);
        }
        String processRequestUrl = processRequestUrl(str2);
        String processRequestUrl2 = processRequestUrl(str2);
        if (equals) {
            i3 = getStreamRequestTimeout();
        } else {
            i3 = i == 0 ? super.getRequestTimeout() : i;
        }
        if (equals) {
            i4 = 0;
        } else {
            i4 = i2 == 0 ? super.getRequestMaxRetries() : i2;
        }
        SignallingMessageTransfer signallingMessageTransfer = equals ? this._streamRequestTransfer : this._requestTransfer;
        SignallingMessageRequestArgs signallingMessageRequestArgs = new SignallingMessageRequestArgs(super.createHeaders());
        signallingMessageRequestArgs.setMessages((SignallingMessage[]) arrayList.toArray(new SignallingMessage[0]));
        signallingMessageRequestArgs.setOnRequestCreated(new IActionDelegate1<SignallingMessageRequestCreatedArgs>() {
            public String getId() {
                return "fm.liveswitch.SignallingClientBase.internalOnRequestCreated";
            }

            public void invoke(SignallingMessageRequestCreatedArgs signallingMessageRequestCreatedArgs) {
                SignallingClient.this.internalOnRequestCreated(signallingMessageRequestCreatedArgs);
            }
        });
        signallingMessageRequestArgs.setOnResponseReceived(new IActionDelegate1<SignallingMessageResponseReceivedArgs>() {
            public String getId() {
                return "fm.liveswitch.SignallingClientBase.internalOnResponseReceived";
            }

            public void invoke(SignallingMessageResponseReceivedArgs signallingMessageResponseReceivedArgs) {
                SignallingClient.this.internalOnResponseReceived(signallingMessageResponseReceivedArgs);
            }
        });
        signallingMessageRequestArgs.setOnHttpRequestCreated(new IActionDelegate1<HttpRequestCreatedArgs>() {
            public String getId() {
                return "fm.liveswitch.SignallingClientBase.internalOnHttpRequestCreated";
            }

            public void invoke(HttpRequestCreatedArgs httpRequestCreatedArgs) {
                SignallingClient.this.internalOnHttpRequestCreated(httpRequestCreatedArgs);
            }
        });
        signallingMessageRequestArgs.setOnHttpResponseReceived(new IActionDelegate1<HttpResponseReceivedArgs>() {
            public String getId() {
                return "fm.liveswitch.SignallingClientBase.internalOnHttpResponseReceived";
            }

            public void invoke(HttpResponseReceivedArgs httpResponseReceivedArgs) {
                SignallingClient.this.internalOnHttpResponseReceived(httpResponseReceivedArgs);
            }
        });
        signallingMessageRequestArgs.setSender(this);
        signallingMessageRequestArgs.setTimeout(i3);
        signallingMessageRequestArgs.setMaxRetries(i4);
        signallingMessageRequestArgs.setUrl(processRequestUrl2);
        signallingMessageRequestArgs.setDynamicValue("frameUrl", processRequestUrl);
        SignallingClientSendState signallingClientSendState = new SignallingClientSendState();
        signallingClientSendState.setRequests(signallingClientRequestArr);
        signallingClientSendState.setRequestMapping(hashMap);
        signallingClientSendState.setIsStream(equals);
        doSendMany(z, signallingMessageTransfer, signallingMessageRequestArgs, signallingClientSendState);
    }

    public SignallingClient service(final SignallingServiceArgs signallingServiceArgs) {
        synchronized (this.__stateLock) {
            if (StringExtensions.isNullOrEmpty(signallingServiceArgs.getChannel())) {
                raiseServiceFailure(signallingServiceArgs, createFailureResponse(signallingServiceArgs, "Channel cannot be null."));
                return this;
            } else if (!raiseRequestEvent(_onServiceRequest, new SignallingClientServiceRequestArgs(signallingServiceArgs), "OnServiceRequest")) {
                SignallingClientResponse createFailureResponse = createFailureResponse(signallingServiceArgs, "Application cancelled request.");
                raiseResponseEvent(_onServiceResponse, new SignallingClientServiceResponseArgs(signallingServiceArgs), "OnServiceResponse", createFailureResponse);
                raiseServiceFailure(signallingServiceArgs, createFailureResponse);
                raiseEndEvent(_onServiceEnd, new SignallingClientServiceEndArgs(signallingServiceArgs), "OnServiceEnd", createFailureResponse);
                return this;
            } else {
                SignallingClientRequest signallingClientRequest = new SignallingClientRequest();
                SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.convertChannelToServiced(signallingServiceArgs.getChannel()));
                signallingMessage.setValidate(false);
                signallingMessage.setDataJson(signallingServiceArgs.getDataJson());
                signallingMessage.setExtensions(signallingServiceArgs.getExtensions());
                signallingClientRequest.setMessage(signallingMessage);
                signallingClientRequest.setCallback(new IAction1<SignallingClientResponse>() {
                    public void invoke(SignallingClientResponse signallingClientResponse) {
                        synchronized (SignallingClient.this.__stateLock) {
                            SignallingClient.this.raiseResponseEvent(SignallingClient._onServiceResponse, new SignallingClientServiceResponseArgs(signallingServiceArgs), "OnServiceResponse", signallingClientResponse);
                            if (signallingClientResponse.getException() != null) {
                                SignallingClient.this.raiseServiceFailure(signallingServiceArgs, signallingClientResponse);
                            } else {
                                SignallingClient.this.raiseServiceSuccess(signallingServiceArgs, signallingClientResponse);
                            }
                            SignallingClient.this.raiseEndEvent(SignallingClient._onServiceEnd, new SignallingClientServiceEndArgs(signallingServiceArgs), "OnServiceEnd", signallingClientResponse);
                        }
                    }
                });
                addToQueue(signallingClientRequest, signallingServiceArgs.getRequestUrl(), checkSynchronous(signallingServiceArgs.getSynchronous()), signallingServiceArgs.getRequestTimeout().getHasValue() ? signallingServiceArgs.getRequestTimeout().getValue() : 0, signallingServiceArgs.getRequestMaxRetries().getHasValue() ? signallingServiceArgs.getRequestMaxRetries().getValue() : 0);
                processQueue(false);
                return this;
            }
        }
    }

    public Future<SignallingServiceSuccessArgs> serviceAsync(String str, byte[] bArr) {
        return serviceAsync(new SignallingServiceArgs(str, bArr));
    }

    public Future<SignallingServiceSuccessArgs> serviceAsync(String str, byte[] bArr, String str2) {
        return serviceAsync(new SignallingServiceArgs(str, bArr, str2));
    }

    public Future<SignallingServiceSuccessArgs> serviceAsync(String str, String str2) {
        return serviceAsync(new SignallingServiceArgs(str, str2));
    }

    public Future<SignallingServiceSuccessArgs> serviceAsync(String str, String str2, String str3) {
        return serviceAsync(new SignallingServiceArgs(str, str2, str3));
    }

    public Future<SignallingServiceSuccessArgs> serviceAsync(SignallingServiceArgs signallingServiceArgs) {
        return doServiceAsync(signallingServiceArgs, signallingServiceArgs.getOnSuccess(), signallingServiceArgs.getOnFailure(), new Promise());
    }

    /* access modifiers changed from: private */
    public void setAuthToken(String str) {
        this._authToken = str;
    }

    /* access modifiers changed from: private */
    public void setClientId(String str) {
        this._clientId = str;
    }

    public void setCustomOnReceive(String str, IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        setCustomOnReceiveWithTag(str, StringExtensions.empty, iAction1);
    }

    public void setCustomOnReceiveWithTag(String str, String str2, IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        if (str == null) {
            throw new RuntimeException(new Exception("channel cannot be null."));
        } else if (iAction1 != null) {
            if (str2 == null) {
                str2 = StringExtensions.empty;
            }
            synchronized (this._customOnReceivesLock) {
                Holder holder = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(this._customOnReceives, str2, holder);
                HashMap hashMap = (HashMap) holder.getValue();
                if (!tryGetValue) {
                    hashMap = new HashMap();
                    HashMapExtensions.set(HashMapExtensions.getItem(this._customOnReceives), str2, hashMap);
                }
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), str, iAction1);
            }
            processPendingReceives(new String[]{str}, this._lastInterval);
        } else {
            throw new RuntimeException(new Exception("onReceive cannot be null."));
        }
    }

    public void setDeviceId(String str) {
        this._deviceId = str;
    }

    public void setDisableWebSockets(boolean z) {
        this._disableWebSockets = z;
    }

    /* access modifiers changed from: private */
    public void setDisconnectException(Exception exc) {
        this._disconnectException = exc;
    }

    /* access modifiers changed from: private */
    public void setServerTimeout(int i) {
        this._serverTimeout = i;
    }

    /* access modifiers changed from: private */
    public void setState(SignallingClientState signallingClientState) {
        synchronized (this.__stateLock) {
            if (!Global.equals(signallingClientState, this.__state)) {
                if (Global.equals(signallingClientState, SignallingClientState.Connecting)) {
                    this._requestTransfer = SignallingMessageTransferFactory.getHttpMessageTransfer();
                } else if (Global.equals(signallingClientState, SignallingClientState.Disconnected)) {
                    raiseForcedUnsubscribes();
                    raiseForcedUnbinds();
                    clearBoundRecords();
                    clearSubscribedChannels();
                    try {
                        SignallingMessageTransfer signallingMessageTransfer = this._requestTransfer;
                        if (signallingMessageTransfer != null) {
                            signallingMessageTransfer.shutdown();
                            this._requestTransfer = null;
                        }
                        SignallingMessageTransfer signallingMessageTransfer2 = this._streamRequestTransfer;
                        if (signallingMessageTransfer2 != null) {
                            signallingMessageTransfer2.shutdown();
                            this._streamRequestTransfer = null;
                        }
                    } catch (Exception unused) {
                    }
                    this._queueActivated = false;
                }
                this.__state = signallingClientState;
                IAction1<SignallingClient> iAction1 = this._onStateChange;
                if (iAction1 != null) {
                    iAction1.invoke(this);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void setStreamId(String str) {
        this._streamId = str;
    }

    public void setStreamRequestUrl(String str) {
        if (str != null) {
            this.__streamRequestUrl = HttpTransfer.replaceWildcards(str);
            return;
        }
        throw new RuntimeException(new Exception("Stream request URL cannot be null."));
    }

    public void setSynchronous(NullableBoolean nullableBoolean) {
        this._synchronous = nullableBoolean;
    }

    public void setToken(String str) {
        this._token = str;
    }

    public void setUserId(String str) {
        this._userId = str;
    }

    public SignallingClient(String str) {
        this(str, (Object) null);
    }

    public SignallingClient(String str, Object obj) {
        this(str, str, obj);
    }

    public SignallingClient(String str, String str2, Object obj) {
        this(obj);
        super.setRequestUrl(str);
        setStreamRequestUrl(str2);
    }

    private SignallingClient(Object obj) {
        this.__onBindFailure = new ArrayList();
        this.__onBindSuccess = new ArrayList();
        this.__onClientChannelReceive = new ArrayList();
        this.__onConnectFailure = new ArrayList();
        this.__onConnectSuccess = new ArrayList();
        this.__onDeviceChannelReceive = new ArrayList();
        this.__onDisconnectComplete = new ArrayList();
        this.__onPublishFailure = new ArrayList();
        this.__onPublishSuccess = new ArrayList();
        this.__onServerBind = new ArrayList();
        this.__onServerSubscribe = new ArrayList();
        this.__onServerUnbind = new ArrayList();
        this.__onServerUnsubscribe = new ArrayList();
        this.__onServiceFailure = new ArrayList();
        this.__onServiceSuccess = new ArrayList();
        this.__onStateChange = new ArrayList();
        this.__onSubscribeFailure = new ArrayList();
        this.__onSubscribeSuccess = new ArrayList();
        this.__onUnbindFailure = new ArrayList();
        this.__onUnbindSuccess = new ArrayList();
        this.__onUnsubscribeFailure = new ArrayList();
        this.__onUnsubscribeSuccess = new ArrayList();
        this.__onUserChannelReceive = new ArrayList();
        this._onBindFailure = null;
        this._onBindSuccess = null;
        this._onClientChannelReceive = null;
        this._onConnectFailure = null;
        this._onConnectSuccess = null;
        this._onDeviceChannelReceive = null;
        this._onDisconnectComplete = null;
        this._onPublishFailure = null;
        this._onPublishSuccess = null;
        this._onServerBind = null;
        this._onServerSubscribe = null;
        this._onServerUnbind = null;
        this._onServerUnsubscribe = null;
        this._onServiceFailure = null;
        this._onServiceSuccess = null;
        this._onStateChange = null;
        this._onSubscribeFailure = null;
        this._onSubscribeSuccess = null;
        this._onUnbindFailure = null;
        this._onUnbindSuccess = null;
        this._onUnsubscribeFailure = null;
        this._onUnsubscribeSuccess = null;
        this._onUserChannelReceive = null;
        this._synchronous = new NullableBoolean();
        this.__state = SignallingClientState.New;
        this._counter = 0;
        this._lastInterval = 0;
        this._lastReconnect = SignallingReconnect.NotSet;
        this._boundRecordsLock = new Object();
        this._boundRecords = new SignallingRecords();
        this._subscribedChannelsLock = new Object();
        this._subscribedChannels = new HashMap<>();
        this._subscribedOnReceivesLock = new Object();
        this._subscribedOnReceives = new HashMap<>();
        this._subscribedOnPresences = new HashMap<>();
        this._subscribedOnReceivesDynamicProperties = new HashMap<>();
        this._subscribedOnPresencesDynamicProperties = new HashMap<>();
        this._pendingReceives = new HashMap<>();
        this._customOnReceivesLock = new Object();
        this._customOnReceives = new HashMap<>();
        this._presenceCacheLock = new Object();
        this._presenceUserCache = new HashMap<>();
        this._presenceDeviceCache = new HashMap<>();
        this._queueActivated = false;
        this._requestQueue = new HashMap<>();
        this._supportedConnectionTypes = new SignallingConnectionType[0];
        this._connectionType = SignallingConnectionType.LongPolling;
        this._batchCounter = 0;
        this._batchCounterLock = new Object();
        this._deferrer = null;
        this._receivedMessages = false;
        this._webSocketOpened = false;
        this._counterLock = new Object();
        this.__stateLock = obj == null ? new Object() : obj;
        setSynchronous(new NullableBoolean(false));
        setToken(generateToken());
        setUserId(Guid.newGuid().toString().replace("-", ""));
        setDeviceId(Guid.newGuid().toString().replace("-", ""));
        if (!Global.equals(Platform.getInstance().getSourceLanguage(), SourceLanguage.TypeScript)) {
            setDisableWebSockets(true);
        }
    }

    public SignallingClient startBatch() {
        synchronized (this._batchCounterLock) {
            this._batchCounter++;
        }
        return this;
    }

    /* access modifiers changed from: private */
    public void stream() {
        SignallingClientRequest signallingClientRequest = new SignallingClientRequest();
        SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.getConnect());
        signallingMessage.setConnectionType(this._connectionType);
        signallingMessage.setAcknowledgement(new NullableBoolean(this._receivedMessages));
        signallingClientRequest.setMessage(signallingMessage);
        signallingClientRequest.setCallback(new IActionDelegate1<SignallingClientResponse>() {
            public String getId() {
                return "fm.liveswitch.SignallingClient.streamCallback";
            }

            public void invoke(SignallingClientResponse signallingClientResponse) {
                SignallingClient.this.streamCallback(signallingClientResponse);
            }
        });
        send(signallingClientRequest, getStreamRequestUrl(), false, 0, 0);
    }

    /* access modifiers changed from: private */
    public void streamCallback(SignallingClientResponse signallingClientResponse) {
        if (Global.equals(getState(), SignallingClientState.Connected)) {
            if (signallingClientResponse.getException() != null) {
                if (signallingClientResponse.getMessages() == null) {
                    setDisconnectException(signallingClientResponse.getException());
                    setState(SignallingClientState.Disconnected);
                } else if (Global.equals(this._lastReconnect, SignallingReconnect.Retry)) {
                    restream(false, this._lastInterval);
                } else {
                    setDisconnectException(signallingClientResponse.getException());
                    setState(SignallingClientState.Disconnected);
                }
            } else if (ArrayExtensions.getLength((Object[]) signallingClientResponse.getMessages()) != 1 || !Global.equals(signallingClientResponse.getMessages()[0].getBayeuxChannel(), SignallingMetaChannels.getConnect())) {
                int i = this._lastInterval;
                for (SignallingMessage receiveMessage : signallingClientResponse.getMessages()) {
                    i = receiveMessage(receiveMessage, i);
                }
                restream(true, i);
            } else {
                restream(false, this._lastInterval);
            }
        }
    }

    public SignallingClient subscribe(final SignallingSubscribeArgs signallingSubscribeArgs) {
        synchronized (this.__stateLock) {
            if (signallingSubscribeArgs.getChannels() != null) {
                if (ArrayExtensions.getLength((Object[]) signallingSubscribeArgs.getChannels()) != 0) {
                    if (signallingSubscribeArgs.getOnReceive() == null) {
                        signallingSubscribeArgs.setOnReceive(new IActionDelegate1<SignallingSubscribeReceiveArgs>() {
                            public String getId() {
                                return "fm.liveswitch.SignallingClient.receiveSink";
                            }

                            public void invoke(SignallingSubscribeReceiveArgs signallingSubscribeReceiveArgs) {
                                SignallingClient.this.receiveSink(signallingSubscribeReceiveArgs);
                            }
                        });
                    }
                    if (signallingSubscribeArgs.getTag() == null) {
                        signallingSubscribeArgs.setTag(StringExtensions.empty);
                    }
                    if (!raiseRequestEvent(_onSubscribeRequest, new SignallingClientSubscribeRequestArgs(signallingSubscribeArgs), "OnSubscribeRequest")) {
                        SignallingClientResponse createFailureResponse = createFailureResponse(signallingSubscribeArgs, "Application cancelled request.");
                        raiseResponseEvent(_onSubscribeResponse, new SignallingClientSubscribeResponseArgs(signallingSubscribeArgs), "OnSubscribeResponse", createFailureResponse);
                        raiseSubscribeFailure(signallingSubscribeArgs, createFailureResponse);
                        raiseEndEvent(_onSubscribeEnd, new SignallingClientSubscribeEndArgs(signallingSubscribeArgs), "OnSubscribeEnd", createFailureResponse);
                        return this;
                    }
                    SignallingClientRequest signallingClientRequest = new SignallingClientRequest();
                    SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.getSubscribe());
                    signallingMessage.setValidate(false);
                    signallingMessage.setChannels(signallingSubscribeArgs.getChannels());
                    signallingMessage.setRecords(signallingSubscribeArgs.getBindRecords());
                    signallingMessage.setIsPrivate(new NullableBoolean(signallingSubscribeArgs.getBindIsPrivate()));
                    signallingMessage.setExtensions(signallingSubscribeArgs.getExtensions());
                    signallingClientRequest.setMessage(signallingMessage);
                    signallingClientRequest.setCallback(new IAction1<SignallingClientResponse>() {
                        public void invoke(SignallingClientResponse signallingClientResponse) {
                            SignallingClientResponse signallingClientResponse2 = signallingClientResponse;
                            synchronized (SignallingClient.this.__stateLock) {
                                SignallingClient.this.raiseResponseEvent(SignallingClient._onSubscribeResponse, new SignallingClientSubscribeResponseArgs(signallingSubscribeArgs), "OnSubscribeResponse", signallingClientResponse2);
                                if (signallingClientResponse.getException() != null) {
                                    SignallingClient.this.raiseSubscribeFailure(signallingSubscribeArgs, signallingClientResponse2);
                                } else {
                                    synchronized (SignallingClient.this._subscribedOnReceivesLock) {
                                        for (String access$9000 : signallingClientResponse.getMessage().getChannels()) {
                                            String access$90002 = SignallingClient.getSubscriptionKey(access$9000, signallingSubscribeArgs.getTag());
                                            HashMapExtensions.set(HashMapExtensions.getItem(SignallingClient.this._subscribedOnReceivesDynamicProperties), access$90002, signallingSubscribeArgs.getDynamicProperties());
                                            HashMapExtensions.set(HashMapExtensions.getItem(SignallingClient.this._subscribedOnPresencesDynamicProperties), access$90002, signallingSubscribeArgs.getDynamicProperties());
                                        }
                                    }
                                    SignallingClient.this.addSubscribedChannels(signallingClientResponse.getMessage().getTag(), signallingClientResponse.getMessage().getChannels());
                                    SignallingClient.this.raiseSubscribeSuccess(signallingSubscribeArgs, signallingClientResponse2);
                                    int unused = SignallingClient.this.addSubscribedOnReceive(signallingClientResponse.getMessage().getTag(), signallingClientResponse.getMessage().getChannels(), signallingSubscribeArgs.getOnReceive(), signallingSubscribeArgs.getDynamicProperties(), SignallingClient.this._lastInterval);
                                    int unused2 = SignallingClient.this.addSubscribedOnPresence(signallingClientResponse.getMessage().getTag(), signallingClientResponse.getMessage().getChannels(), signallingSubscribeArgs.getOnPresence(), signallingSubscribeArgs.getDynamicProperties(), SignallingClient.this._lastInterval);
                                }
                                SignallingClient.this.raiseEndEvent(SignallingClient._onSubscribeEnd, new SignallingClientSubscribeEndArgs(signallingSubscribeArgs), "OnSubscribeEnd", signallingClientResponse2);
                            }
                        }
                    });
                    addToQueue(signallingClientRequest, signallingSubscribeArgs.getRequestUrl(), checkSynchronous(signallingSubscribeArgs.getSynchronous()), signallingSubscribeArgs.getRequestTimeout().getHasValue() ? signallingSubscribeArgs.getRequestTimeout().getValue() : 0, signallingSubscribeArgs.getRequestMaxRetries().getHasValue() ? signallingSubscribeArgs.getRequestMaxRetries().getValue() : 0);
                    processQueue(false);
                    return this;
                }
            }
            raiseSubscribeFailure(signallingSubscribeArgs, createFailureResponse(signallingSubscribeArgs, "Channels cannot be null."));
            return this;
        }
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String str) {
        return subscribeAsync(new SignallingSubscribeArgs(str));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String str, IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        return subscribeAsync(new SignallingSubscribeArgs(str, iAction1));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String str, IAction1<SignallingSubscribeReceiveArgs> iAction1, IAction1<SignallingSubscribePresenceArgs> iAction12) {
        return subscribeAsync(new SignallingSubscribeArgs(str, iAction1, iAction12));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String str, String str2) {
        return subscribeAsync(new SignallingSubscribeArgs(str, str2));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String str, String str2, IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        return subscribeAsync(new SignallingSubscribeArgs(str, str2, iAction1));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String str, String str2, IAction1<SignallingSubscribeReceiveArgs> iAction1, IAction1<SignallingSubscribePresenceArgs> iAction12) {
        return subscribeAsync(new SignallingSubscribeArgs(str, str2, iAction1, iAction12));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String[] strArr) {
        return subscribeAsync(new SignallingSubscribeArgs(strArr));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String[] strArr, IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        return subscribeAsync(new SignallingSubscribeArgs(strArr, iAction1));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String[] strArr, IAction1<SignallingSubscribeReceiveArgs> iAction1, IAction1<SignallingSubscribePresenceArgs> iAction12) {
        return subscribeAsync(new SignallingSubscribeArgs(strArr, iAction1, iAction12));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String[] strArr, String str) {
        return subscribeAsync(new SignallingSubscribeArgs(strArr, str));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String[] strArr, String str, IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        return subscribeAsync(new SignallingSubscribeArgs(strArr, str, iAction1));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(String[] strArr, String str, IAction1<SignallingSubscribeReceiveArgs> iAction1, IAction1<SignallingSubscribePresenceArgs> iAction12) {
        return subscribeAsync(new SignallingSubscribeArgs(strArr, str, iAction1, iAction12));
    }

    public Future<SignallingSubscribeSuccessArgs> subscribeAsync(SignallingSubscribeArgs signallingSubscribeArgs) {
        return doSubscribeAsync(signallingSubscribeArgs, signallingSubscribeArgs.getOnSuccess(), signallingSubscribeArgs.getOnFailure(), new Promise());
    }

    /* access modifiers changed from: private */
    public boolean tryWebSocket() {
        try {
            SignallingWebSocketMessageTransfer webSocketMessageTransfer = SignallingMessageTransferFactory.getWebSocketMessageTransfer(processRequestUrl(getStreamRequestUrl()));
            webSocketMessageTransfer.setHandshakeTimeout(super.getRequestTimeout());
            webSocketMessageTransfer.setStreamTimeout(getStreamRequestTimeout());
            webSocketMessageTransfer.setOnRequestCreated(new IActionDelegate1<HttpRequestCreatedArgs>() {
                public String getId() {
                    return "fm.liveswitch.SignallingClientBase.internalOnHttpRequestCreated";
                }

                public void invoke(HttpRequestCreatedArgs httpRequestCreatedArgs) {
                    SignallingClient.this.internalOnHttpRequestCreated(httpRequestCreatedArgs);
                }
            });
            webSocketMessageTransfer.setOnResponseReceived(new IActionDelegate1<HttpResponseReceivedArgs>() {
                public String getId() {
                    return "fm.liveswitch.SignallingClientBase.internalOnHttpResponseReceived";
                }

                public void invoke(HttpResponseReceivedArgs httpResponseReceivedArgs) {
                    SignallingClient.this.internalOnHttpResponseReceived(httpResponseReceivedArgs);
                }
            });
            webSocketMessageTransfer.setOnOpenSuccess(new IActionDelegate1<WebSocketOpenSuccessArgs>() {
                public String getId() {
                    return "fm.liveswitch.SignallingClient.webSocketOpenSuccess";
                }

                public void invoke(WebSocketOpenSuccessArgs webSocketOpenSuccessArgs) {
                    SignallingClient.this.webSocketOpenSuccess(webSocketOpenSuccessArgs);
                }
            });
            webSocketMessageTransfer.setOnOpenFailure(new IActionDelegate1<WebSocketOpenFailureArgs>() {
                public String getId() {
                    return "fm.liveswitch.SignallingClient.webSocketOpenFailure";
                }

                public void invoke(WebSocketOpenFailureArgs webSocketOpenFailureArgs) {
                    SignallingClient.this.webSocketOpenFailure(webSocketOpenFailureArgs);
                }
            });
            webSocketMessageTransfer.setOnStreamFailure(new IActionDelegate1<WebSocketStreamFailureArgs>() {
                public String getId() {
                    return "fm.liveswitch.SignallingClient.webSocketStreamFailure";
                }

                public void invoke(WebSocketStreamFailureArgs webSocketStreamFailureArgs) {
                    SignallingClient.this.webSocketStreamFailure(webSocketStreamFailureArgs);
                }
            });
            webSocketMessageTransfer.setSender(this);
            this._connectionType = SignallingConnectionType.WebSocket;
            this._streamRequestTransfer = webSocketMessageTransfer;
            webSocketMessageTransfer.open(super.createHeaders());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public SignallingClient unbind(final SignallingUnbindArgs signallingUnbindArgs) {
        synchronized (this.__stateLock) {
            if (signallingUnbindArgs.getKeys() != null) {
                if (ArrayExtensions.getLength((Object[]) signallingUnbindArgs.getKeys()) != 0) {
                    if (!raiseRequestEvent(_onUnbindRequest, new SignallingClientUnbindRequestArgs(signallingUnbindArgs), "OnUnbindRequest")) {
                        SignallingClientResponse createFailureResponse = createFailureResponse(signallingUnbindArgs, "Application cancelled request.");
                        raiseResponseEvent(_onUnbindResponse, new SignallingClientUnbindResponseArgs(signallingUnbindArgs), "OnUnbindResponse", createFailureResponse);
                        raiseUnbindFailure(signallingUnbindArgs, createFailureResponse);
                        raiseEndEvent(_onUnbindEnd, new SignallingClientUnbindEndArgs(signallingUnbindArgs), "OnUnbindEnd", createFailureResponse);
                        return this;
                    }
                    SignallingClientRequest signallingClientRequest = new SignallingClientRequest();
                    SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.getUnbind());
                    signallingMessage.setValidate(false);
                    signallingMessage.setKeys(signallingUnbindArgs.getKeys());
                    signallingMessage.setExtensions(signallingUnbindArgs.getExtensions());
                    signallingClientRequest.setMessage(signallingMessage);
                    signallingClientRequest.setCallback(new IAction1<SignallingClientResponse>() {
                        public void invoke(SignallingClientResponse signallingClientResponse) {
                            synchronized (SignallingClient.this.__stateLock) {
                                SignallingClient.this.raiseResponseEvent(SignallingClient._onUnbindResponse, new SignallingClientUnbindResponseArgs(signallingUnbindArgs), "OnUnbindResponse", signallingClientResponse);
                                if (signallingClientResponse.getException() != null) {
                                    SignallingClient.this.raiseUnbindFailure(signallingUnbindArgs, signallingClientResponse);
                                } else {
                                    SignallingClient.this.removeBoundRecords(signallingClientResponse.getMessage().getKeys());
                                    SignallingClient.this.raiseUnbindSuccess(signallingUnbindArgs, signallingClientResponse, false);
                                }
                                SignallingClient.this.raiseEndEvent(SignallingClient._onUnbindEnd, new SignallingClientUnbindEndArgs(signallingUnbindArgs), "OnUnbindEnd", signallingClientResponse);
                            }
                        }
                    });
                    addToQueue(signallingClientRequest, signallingUnbindArgs.getRequestUrl(), checkSynchronous(signallingUnbindArgs.getSynchronous()), signallingUnbindArgs.getRequestTimeout().getHasValue() ? signallingUnbindArgs.getRequestTimeout().getValue() : 0, signallingUnbindArgs.getRequestMaxRetries().getHasValue() ? signallingUnbindArgs.getRequestMaxRetries().getValue() : 0);
                    processQueue(false);
                    return this;
                }
            }
            raiseUnbindFailure(signallingUnbindArgs, createFailureResponse(signallingUnbindArgs, "Keys cannot be null."));
            return this;
        }
    }

    public Future<SignallingUnbindSuccessArgs> unbindAsync(String str) {
        return unbindAsync(new SignallingUnbindArgs(str));
    }

    public Future<SignallingUnbindSuccessArgs> unbindAsync(String[] strArr) {
        return unbindAsync(new SignallingUnbindArgs(strArr));
    }

    public Future<SignallingUnbindSuccessArgs> unbindAsync(SignallingUnbindArgs signallingUnbindArgs) {
        return doUnbindAsync(signallingUnbindArgs, signallingUnbindArgs.getOnSuccess(), signallingUnbindArgs.getOnFailure(), new Promise());
    }

    public boolean unsetCustomOnReceive(String str) {
        return unsetCustomOnReceiveWithTag(str, StringExtensions.empty);
    }

    public boolean unsetCustomOnReceiveWithTag(String str, String str2) {
        if (str != null) {
            if (str2 == null) {
                str2 = StringExtensions.empty;
            }
            synchronized (this._customOnReceivesLock) {
                Holder holder = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(this._customOnReceives, str2, holder);
                HashMap hashMap = (HashMap) holder.getValue();
                if (!tryGetValue) {
                    return false;
                }
                if (!HashMapExtensions.remove(hashMap, str)) {
                    return false;
                }
                if (HashMapExtensions.getCount(hashMap) == 0) {
                    HashMapExtensions.remove(this._customOnReceives, str2);
                }
                return true;
            }
        }
        throw new RuntimeException(new Exception("channel cannot be null."));
    }

    public SignallingClient unsubscribe(final SignallingUnsubscribeArgs signallingUnsubscribeArgs) {
        synchronized (this.__stateLock) {
            if (signallingUnsubscribeArgs.getChannels() != null) {
                if (ArrayExtensions.getLength((Object[]) signallingUnsubscribeArgs.getChannels()) != 0) {
                    if (signallingUnsubscribeArgs.getTag() == null) {
                        signallingUnsubscribeArgs.setTag(StringExtensions.empty);
                    }
                    if (!raiseRequestEvent(_onUnsubscribeRequest, new SignallingClientUnsubscribeRequestArgs(signallingUnsubscribeArgs), "OnUnsubscribeRequest")) {
                        SignallingClientResponse createFailureResponse = createFailureResponse(signallingUnsubscribeArgs, "Application cancelled request.");
                        raiseResponseEvent(_onUnsubscribeResponse, new SignallingClientUnsubscribeResponseArgs(signallingUnsubscribeArgs), "OnUnsubscribeResponse", createFailureResponse);
                        raiseUnsubscribeFailure(signallingUnsubscribeArgs, createFailureResponse);
                        raiseEndEvent(_onUnsubscribeEnd, new SignallingClientUnsubscribeEndArgs(signallingUnsubscribeArgs), "OnUnsubscribeEnd", createFailureResponse);
                        return this;
                    }
                    SignallingClientRequest signallingClientRequest = new SignallingClientRequest();
                    SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.getUnsubscribe());
                    signallingMessage.setValidate(false);
                    signallingMessage.setChannels(signallingUnsubscribeArgs.getChannels());
                    signallingMessage.setKeys(signallingUnsubscribeArgs.getUnbindKeys());
                    signallingMessage.setExtensions(signallingUnsubscribeArgs.getExtensions());
                    signallingClientRequest.setMessage(signallingMessage);
                    signallingClientRequest.setCallback(new IAction1<SignallingClientResponse>() {
                        public void invoke(SignallingClientResponse signallingClientResponse) {
                            synchronized (SignallingClient.this.__stateLock) {
                                SignallingClient.this.raiseResponseEvent(SignallingClient._onUnsubscribeResponse, new SignallingClientUnsubscribeResponseArgs(signallingUnsubscribeArgs), "OnUnsubscribeResponse", signallingClientResponse);
                                if (signallingClientResponse.getException() != null) {
                                    SignallingClient.this.raiseUnsubscribeFailure(signallingUnsubscribeArgs, signallingClientResponse);
                                } else {
                                    SignallingClient.this.removeSubscribedChannels(signallingClientResponse.getMessage().getTag(), signallingClientResponse.getMessage().getChannels());
                                    SignallingClient.this.removeSubscribedOnReceive(signallingClientResponse.getMessage().getTag(), signallingClientResponse.getMessage().getChannels());
                                    SignallingClient.this.removeSubscribedOnPresence(signallingClientResponse.getMessage().getTag(), signallingClientResponse.getMessage().getChannels());
                                    SignallingClient.this.raiseUnsubscribeSuccess(signallingUnsubscribeArgs, signallingClientResponse, false);
                                }
                                SignallingClient.this.raiseEndEvent(SignallingClient._onUnsubscribeEnd, new SignallingClientUnsubscribeEndArgs(signallingUnsubscribeArgs), "OnUnsubscribeEnd", signallingClientResponse);
                            }
                        }
                    });
                    addToQueue(signallingClientRequest, signallingUnsubscribeArgs.getRequestUrl(), checkSynchronous(signallingUnsubscribeArgs.getSynchronous()), signallingUnsubscribeArgs.getRequestTimeout().getHasValue() ? signallingUnsubscribeArgs.getRequestTimeout().getValue() : 0, signallingUnsubscribeArgs.getRequestMaxRetries().getHasValue() ? signallingUnsubscribeArgs.getRequestMaxRetries().getValue() : 0);
                    processQueue(false);
                    return this;
                }
            }
            raiseUnsubscribeFailure(signallingUnsubscribeArgs, createFailureResponse(signallingUnsubscribeArgs, "Channels cannot be null."));
            return this;
        }
    }

    public Future<SignallingUnsubscribeSuccessArgs> unsubscribeAsync(String str) {
        return unsubscribeAsync(new SignallingUnsubscribeArgs(str));
    }

    public Future<SignallingUnsubscribeSuccessArgs> unsubscribeAsync(String str, String str2) {
        return unsubscribeAsync(new SignallingUnsubscribeArgs(str, str2));
    }

    public Future<SignallingUnsubscribeSuccessArgs> unsubscribeAsync(String[] strArr) {
        return unsubscribeAsync(new SignallingUnsubscribeArgs(strArr));
    }

    public Future<SignallingUnsubscribeSuccessArgs> unsubscribeAsync(String[] strArr, String str) {
        return unsubscribeAsync(new SignallingUnsubscribeArgs(strArr, str));
    }

    public Future<SignallingUnsubscribeSuccessArgs> unsubscribeAsync(SignallingUnsubscribeArgs signallingUnsubscribeArgs) {
        return doUnsubscribeAsync(signallingUnsubscribeArgs, signallingUnsubscribeArgs.getOnSuccess(), signallingUnsubscribeArgs.getOnFailure(), new Promise());
    }

    /* access modifiers changed from: private */
    public void webSocketOpenFailure(WebSocketOpenFailureArgs webSocketOpenFailureArgs) {
        if (!this._webSocketOpened) {
            __log.warn("Unable to connect via Web Socket, falling back to Long Polling.");
            doLongPolling();
        } else if (!tryWebSocket()) {
            doLongPolling();
        }
    }

    /* access modifiers changed from: private */
    public void webSocketOpenSuccess(WebSocketOpenSuccessArgs webSocketOpenSuccessArgs) {
        this._webSocketOpened = true;
        activateStream();
    }

    /* access modifiers changed from: private */
    public void webSocketStreamFailure(WebSocketStreamFailureArgs webSocketStreamFailureArgs) {
        SignallingClientResponse signallingClientResponse = new SignallingClientResponse();
        signallingClientResponse.setException(new Exception(StringExtensions.format("WebSocket stream error. {0}", (Object) webSocketStreamFailureArgs.getException().getMessage())));
        streamCallback(signallingClientResponse);
    }
}
