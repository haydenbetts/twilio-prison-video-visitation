package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class SignallingPublisher extends SignallingClientBase {
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingPublisher, SignallingPublisherPublishRequestArgs>> __onPublishRequest = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingPublisher, SignallingPublisherPublishResponseArgs>> __onPublishResponse = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingPublisher, SignallingPublisherServiceRequestArgs>> __onServiceRequest = new ArrayList();
    /* access modifiers changed from: private */
    public static List<IAction2<SignallingPublisher, SignallingPublisherServiceResponseArgs>> __onServiceResponse = new ArrayList();
    private static IAction2<SignallingPublisher, SignallingPublisherPublishRequestArgs> _onPublishRequest = null;
    private static IAction2<SignallingPublisher, SignallingPublisherPublishResponseArgs> _onPublishResponse = null;
    private static IAction2<SignallingPublisher, SignallingPublisherServiceRequestArgs> _onServiceRequest = null;
    private static IAction2<SignallingPublisher, SignallingPublisherServiceResponseArgs> _onServiceResponse = null;
    private static HashMap<String, String> _requestUrlCache = new HashMap<>();
    private static Object _requestUrlCacheLock = new Object();

    public static void addOnPublishRequest(IAction2<SignallingPublisher, SignallingPublisherPublishRequestArgs> iAction2) {
        if (iAction2 != null) {
            if (_onPublishRequest == null) {
                _onPublishRequest = new IAction2<SignallingPublisher, SignallingPublisherPublishRequestArgs>() {
                    public void invoke(SignallingPublisher signallingPublisher, SignallingPublisherPublishRequestArgs signallingPublisherPublishRequestArgs) {
                        Iterator it = new ArrayList(SignallingPublisher.__onPublishRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingPublisher, signallingPublisherPublishRequestArgs);
                        }
                    }
                };
            }
            __onPublishRequest.add(iAction2);
        }
    }

    public static void addOnPublishResponse(IAction2<SignallingPublisher, SignallingPublisherPublishResponseArgs> iAction2) {
        if (iAction2 != null) {
            if (_onPublishResponse == null) {
                _onPublishResponse = new IAction2<SignallingPublisher, SignallingPublisherPublishResponseArgs>() {
                    public void invoke(SignallingPublisher signallingPublisher, SignallingPublisherPublishResponseArgs signallingPublisherPublishResponseArgs) {
                        Iterator it = new ArrayList(SignallingPublisher.__onPublishResponse).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingPublisher, signallingPublisherPublishResponseArgs);
                        }
                    }
                };
            }
            __onPublishResponse.add(iAction2);
        }
    }

    public static void addOnServiceRequest(IAction2<SignallingPublisher, SignallingPublisherServiceRequestArgs> iAction2) {
        if (iAction2 != null) {
            if (_onServiceRequest == null) {
                _onServiceRequest = new IAction2<SignallingPublisher, SignallingPublisherServiceRequestArgs>() {
                    public void invoke(SignallingPublisher signallingPublisher, SignallingPublisherServiceRequestArgs signallingPublisherServiceRequestArgs) {
                        Iterator it = new ArrayList(SignallingPublisher.__onServiceRequest).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingPublisher, signallingPublisherServiceRequestArgs);
                        }
                    }
                };
            }
            __onServiceRequest.add(iAction2);
        }
    }

    public static void addOnServiceResponse(IAction2<SignallingPublisher, SignallingPublisherServiceResponseArgs> iAction2) {
        if (iAction2 != null) {
            if (_onServiceResponse == null) {
                _onServiceResponse = new IAction2<SignallingPublisher, SignallingPublisherServiceResponseArgs>() {
                    public void invoke(SignallingPublisher signallingPublisher, SignallingPublisherServiceResponseArgs signallingPublisherServiceResponseArgs) {
                        Iterator it = new ArrayList(SignallingPublisher.__onServiceResponse).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(signallingPublisher, signallingPublisherServiceResponseArgs);
                        }
                    }
                };
            }
            __onServiceResponse.add(iAction2);
        }
    }

    private SignallingPublication[] performPublish(SignallingPublication[] signallingPublicationArr) {
        if (!raiseRequestEvent(_onPublishRequest, new SignallingPublisherPublishRequestArgs(signallingPublicationArr))) {
            return null;
        }
        SignallingPublisherResponse send = send(SignallingPublication.toMessages(signallingPublicationArr), super.getRequestUrl());
        SignallingPublication[] fromMessages = SignallingPublication.fromMessages(send.getMessages());
        raiseResponseEvent(_onPublishResponse, new SignallingPublisherPublishResponseArgs(signallingPublicationArr, fromMessages), send);
        if (send.getException() == null) {
            return fromMessages;
        }
        throw new RuntimeException(send.getException());
    }

    private SignallingMessage[] performService(SignallingMessage[] signallingMessageArr) {
        for (SignallingMessage signallingMessage : signallingMessageArr) {
            if (!SignallingMetaChannels.isServiceChannel(signallingMessage.getBayeuxChannel())) {
                signallingMessage.setBayeuxChannel(SignallingMetaChannels.convertChannelToServiced(signallingMessage.getBayeuxChannel()));
            }
        }
        if (!raiseRequestEvent(_onServiceRequest, new SignallingPublisherServiceRequestArgs(signallingMessageArr))) {
            return null;
        }
        SignallingPublisherResponse send = send(signallingMessageArr, super.getRequestUrl());
        SignallingMessage[] messages = send.getMessages();
        raiseResponseEvent(_onServiceResponse, new SignallingPublisherServiceResponseArgs(signallingMessageArr, messages), send);
        if (send.getException() == null) {
            return messages;
        }
        throw new RuntimeException(send.getException());
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
            str3 = HttpTransfer.addQueryToUrl(HttpTransfer.addQueryToUrl(str, "src", HttpWebRequestTransfer.getPlatformCode()), "AspxAutoDetectCookieSupport", "1");
            if (!Global.equals(super.getConcurrencyMode(), SignallingConcurrencyMode.High)) {
                return str3;
            }
            synchronized (_requestUrlCache) {
                HashMapExtensions.set(HashMapExtensions.getItem(_requestUrlCache), str, str3);
            }
        }
        return str3;
    }

    public SignallingPublication publish(String str, byte[] bArr) {
        return publish(new SignallingPublication(str, bArr));
    }

    public SignallingPublication publish(String str, byte[] bArr, String str2) {
        return publish(new SignallingPublication(str, bArr, str2));
    }

    public SignallingPublication publish(String str, String str2) {
        return publish(new SignallingPublication(str, str2));
    }

    public SignallingPublication publish(String str, String str2, String str3) {
        return publish(new SignallingPublication(str, str2, str3));
    }

    public SignallingPublication publish(SignallingPublication signallingPublication) {
        SignallingPublication[] publishMany = publishMany(new SignallingPublication[]{signallingPublication});
        if (publishMany == null || ArrayExtensions.getLength((Object[]) publishMany) == 0) {
            return null;
        }
        return publishMany[0];
    }

    public SignallingPublication[] publishMany(SignallingPublication[] signallingPublicationArr) {
        if (signallingPublicationArr != null && ArrayExtensions.getLength((Object[]) signallingPublicationArr) != 0) {
            return performPublish(signallingPublicationArr);
        }
        throw new RuntimeException(new Exception("publications cannot be null/empty."));
    }

    private <T extends SignallingPublisherArgs> void raiseEvent(IAction2<SignallingPublisher, T> iAction2, T t) {
        t.setPublisher(this);
        if (iAction2 != null) {
            iAction2.invoke(this, t);
        }
    }

    private <T extends SignallingPublisherRequestArgs> boolean raiseRequestEvent(IAction2<SignallingPublisher, T> iAction2, T t) {
        raiseEvent(iAction2, t);
        return !t.getCancel();
    }

    private <T extends SignallingPublisherResponseArgs> void raiseResponseEvent(IAction2<SignallingPublisher, T> iAction2, T t, SignallingPublisherResponse signallingPublisherResponse) {
        t.setException(signallingPublisherResponse.getException());
        raiseEvent(iAction2, t);
    }

    public static void removeOnPublishRequest(IAction2<SignallingPublisher, SignallingPublisherPublishRequestArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onPublishRequest, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onPublishRequest.remove(iAction2);
        if (__onPublishRequest.size() == 0) {
            _onPublishRequest = null;
        }
    }

    public static void removeOnPublishResponse(IAction2<SignallingPublisher, SignallingPublisherPublishResponseArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onPublishResponse, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onPublishResponse.remove(iAction2);
        if (__onPublishResponse.size() == 0) {
            _onPublishResponse = null;
        }
    }

    public static void removeOnServiceRequest(IAction2<SignallingPublisher, SignallingPublisherServiceRequestArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onServiceRequest, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onServiceRequest.remove(iAction2);
        if (__onServiceRequest.size() == 0) {
            _onServiceRequest = null;
        }
    }

    public static void removeOnServiceResponse(IAction2<SignallingPublisher, SignallingPublisherServiceResponseArgs> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(__onServiceResponse, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        __onServiceResponse.remove(iAction2);
        if (__onServiceResponse.size() == 0) {
            _onServiceResponse = null;
        }
    }

    private SignallingPublisherResponse send(SignallingMessage[] signallingMessageArr, String str) {
        String processRequestUrl = processRequestUrl(str);
        String processRequestUrl2 = processRequestUrl(str);
        for (SignallingMessage signallingMessage : signallingMessageArr) {
            if (super.getDisableBinary()) {
                signallingMessage.setDisableBinary(new NullableBoolean(super.getDisableBinary()));
            }
        }
        SignallingMessageRequestArgs signallingMessageRequestArgs = new SignallingMessageRequestArgs(super.createHeaders());
        signallingMessageRequestArgs.setMessages(signallingMessageArr);
        signallingMessageRequestArgs.setOnRequestCreated(new IActionDelegate1<SignallingMessageRequestCreatedArgs>() {
            public String getId() {
                return "fm.liveswitch.SignallingClientBase.internalOnRequestCreated";
            }

            public void invoke(SignallingMessageRequestCreatedArgs signallingMessageRequestCreatedArgs) {
                SignallingPublisher.this.internalOnRequestCreated(signallingMessageRequestCreatedArgs);
            }
        });
        signallingMessageRequestArgs.setOnResponseReceived(new IActionDelegate1<SignallingMessageResponseReceivedArgs>() {
            public String getId() {
                return "fm.liveswitch.SignallingClientBase.internalOnResponseReceived";
            }

            public void invoke(SignallingMessageResponseReceivedArgs signallingMessageResponseReceivedArgs) {
                SignallingPublisher.this.internalOnResponseReceived(signallingMessageResponseReceivedArgs);
            }
        });
        signallingMessageRequestArgs.setOnHttpRequestCreated(new IActionDelegate1<HttpRequestCreatedArgs>() {
            public String getId() {
                return "fm.liveswitch.SignallingClientBase.internalOnHttpRequestCreated";
            }

            public void invoke(HttpRequestCreatedArgs httpRequestCreatedArgs) {
                SignallingPublisher.this.internalOnHttpRequestCreated(httpRequestCreatedArgs);
            }
        });
        signallingMessageRequestArgs.setOnHttpResponseReceived(new IActionDelegate1<HttpResponseReceivedArgs>() {
            public String getId() {
                return "fm.liveswitch.SignallingClientBase.internalOnHttpResponseReceived";
            }

            public void invoke(HttpResponseReceivedArgs httpResponseReceivedArgs) {
                SignallingPublisher.this.internalOnHttpResponseReceived(httpResponseReceivedArgs);
            }
        });
        signallingMessageRequestArgs.setSender(this);
        signallingMessageRequestArgs.setTimeout(super.getRequestTimeout());
        signallingMessageRequestArgs.setUrl(processRequestUrl2);
        signallingMessageRequestArgs.setDynamicValue("frameUrl", processRequestUrl);
        SignallingMessageTransfer httpMessageTransfer = SignallingMessageTransferFactory.getHttpMessageTransfer();
        SignallingMessageResponseArgs send = httpMessageTransfer.send(signallingMessageRequestArgs);
        try {
            httpMessageTransfer.shutdown();
        } catch (Exception unused) {
        }
        if (send.getException() != null) {
            SignallingPublisherResponse signallingPublisherResponse = new SignallingPublisherResponse();
            signallingPublisherResponse.setException(send.getException());
            return signallingPublisherResponse;
        } else if (send.getMessages() == null) {
            SignallingPublisherResponse signallingPublisherResponse2 = new SignallingPublisherResponse();
            signallingPublisherResponse2.setException(new Exception(super.getInvalidResponseMessage(send)));
            return signallingPublisherResponse2;
        } else if (ArrayExtensions.getLength((Object[]) send.getMessages()) == 0) {
            SignallingPublisherResponse signallingPublisherResponse3 = new SignallingPublisherResponse();
            signallingPublisherResponse3.setException(new Exception(super.getEmptyResponseMessage(send)));
            return signallingPublisherResponse3;
        } else {
            SignallingPublisherResponse signallingPublisherResponse4 = new SignallingPublisherResponse();
            signallingPublisherResponse4.setMessages(send.getMessages());
            return signallingPublisherResponse4;
        }
    }

    public SignallingMessage service(String str, byte[] bArr) {
        SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.convertChannelToServiced(str));
        signallingMessage.setDataBytes(bArr);
        return service(signallingMessage);
    }

    public SignallingMessage service(String str, byte[] bArr, String str2) {
        SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.convertChannelToServiced(str));
        signallingMessage.setDataBytes(bArr);
        signallingMessage.setTag(str2);
        return service(signallingMessage);
    }

    public SignallingMessage service(String str, String str2) {
        SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.convertChannelToServiced(str));
        signallingMessage.setDataJson(str2);
        return service(signallingMessage);
    }

    public SignallingMessage service(String str, String str2, String str3) {
        SignallingMessage signallingMessage = new SignallingMessage(SignallingMetaChannels.convertChannelToServiced(str));
        signallingMessage.setDataJson(str2);
        signallingMessage.setTag(str3);
        return service(signallingMessage);
    }

    public SignallingMessage service(SignallingMessage signallingMessage) {
        SignallingMessage[] serviceMany = serviceMany(new SignallingMessage[]{signallingMessage});
        if (serviceMany == null || ArrayExtensions.getLength((Object[]) serviceMany) == 0) {
            return null;
        }
        return serviceMany[0];
    }

    public SignallingMessage[] serviceMany(SignallingMessage[] signallingMessageArr) {
        if (signallingMessageArr != null && ArrayExtensions.getLength((Object[]) signallingMessageArr) != 0) {
            return performService(signallingMessageArr);
        }
        throw new RuntimeException(new Exception("messages cannot be null/empty."));
    }

    public SignallingPublisher(String str) {
        super.setRequestUrl(str);
    }
}
