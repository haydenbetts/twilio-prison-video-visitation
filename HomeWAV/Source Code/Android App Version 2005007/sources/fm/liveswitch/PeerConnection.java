package fm.liveswitch;

public class PeerConnection extends ManagedConnection {
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(PeerConnection.class);
    private SessionDescription __pendingRemoteDescription;
    private ClientInfo _remoteClientInfo;
    private PeerRole _role;

    /* access modifiers changed from: protected */
    public boolean isMediaDirectionAllowed(String str) {
        return true;
    }

    /* access modifiers changed from: protected */
    public Message doCreateCandidateMessage(Candidate candidate) {
        return Message.createPeerCandidateMessage(candidate.toJson(), getRemoteClientInfo().getUserId(), getRemoteClientInfo().getDeviceId(), getRemoteClientInfo().getId());
    }

    /* access modifiers changed from: protected */
    public Message doCreateCloseMessage() {
        return Message.createPeerCloseMessage(getRemoteClientInfo().getUserId(), getRemoteClientInfo().getDeviceId(), getRemoteClientInfo().getId());
    }

    /* access modifiers changed from: protected */
    public Message doCreateUpdateMessage(ConnectionConfig connectionConfig) {
        return Message.createUpdateMessage(connectionConfig.toJson(), getRemoteClientInfo().getUserId(), getRemoteClientInfo().getDeviceId(), getRemoteClientInfo().getId());
    }

    /* access modifiers changed from: protected */
    public void doOpen() {
        if (Global.equals(getRole(), PeerRole.Offerer)) {
            super.getInternalConnection().createOffer().then(new IAction1<SessionDescription>() {
                public void invoke(SessionDescription sessionDescription) {
                    PeerConnection.this.getInternalConnection().setLocalDescription(sessionDescription).then(new IAction1<SessionDescription>() {
                        public void invoke(SessionDescription sessionDescription) {
                            PeerConnection.this.setLocalAudioFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getAudioDescription()));
                            PeerConnection.this.setLocalVideoFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getVideoDescription()));
                            PeerConnection.this.send(Message.createPeerOfferMessage(PeerConnection.this.getTag(), sessionDescription.toJson(), PeerConnection.this.getRemoteClientInfo().getUserId(), PeerConnection.this.getRemoteClientInfo().getDeviceId(), PeerConnection.this.getRemoteClientInfo().getId())).fail((IAction1<Exception>) new IAction1<Exception>() {
                                public void invoke(Exception exc) {
                                    PeerConnection.__log.error("Could not send offer message.", exc);
                                }
                            });
                        }
                    }, (IAction1<Exception>) new IAction1<Exception>() {
                        public void invoke(Exception exc) {
                            PeerConnection.__log.error("Could not set local description.", exc);
                            PeerConnection.this.processLocalError(new Error(ErrorCode.LocalDescriptionError, exc));
                        }
                    });
                }
            }, (IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    PeerConnection.__log.error("Could not create offer.", exc);
                    PeerConnection.this.processLocalError(new Error(ErrorCode.LocalDescriptionError, exc));
                }
            });
            return;
        }
        super.getInternalConnection().setExternalId(super.getRemoteConnectionId());
        super.getInternalConnection().setRemoteDescription(this.__pendingRemoteDescription).then(new IAction1<SessionDescription>() {
            public void invoke(SessionDescription sessionDescription) {
                PeerConnection.this.setRemoteAudioFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getAudioDescription()));
                PeerConnection.this.setRemoteVideoFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getVideoDescription()));
                PeerConnection.this.getInternalConnection().createAnswer().then(new IAction1<SessionDescription>() {
                    public void invoke(SessionDescription sessionDescription) {
                        PeerConnection.this.getInternalConnection().setLocalDescription(sessionDescription).then(new IAction1<SessionDescription>() {
                            public void invoke(SessionDescription sessionDescription) {
                                PeerConnection.this.setLocalAudioFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getAudioDescription()));
                                PeerConnection.this.setLocalVideoFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getVideoDescription()));
                                PeerConnection.this.send(Message.createPeerAnswerMessage(PeerConnection.this.getTag(), sessionDescription.toJson(), PeerConnection.this.getRemoteClientInfo().getUserId(), PeerConnection.this.getRemoteClientInfo().getDeviceId(), PeerConnection.this.getRemoteClientInfo().getId())).fail((IAction1<Exception>) new IAction1<Exception>() {
                                    public void invoke(Exception exc) {
                                        PeerConnection.__log.error("Could not send answer message.", exc);
                                    }
                                });
                            }
                        }, (IAction1<Exception>) new IAction1<Exception>() {
                            public void invoke(Exception exc) {
                                PeerConnection.__log.error("Could not set local description.", exc);
                                PeerConnection.this.processLocalError(new Error(ErrorCode.LocalDescriptionError, exc));
                                PeerConnection.this.sendErrorToPeer(exc);
                            }
                        });
                    }
                }, (IAction1<Exception>) new IAction1<Exception>() {
                    public void invoke(Exception exc) {
                        PeerConnection.__log.error("Could not create answer.", exc);
                        PeerConnection.this.processLocalError(new Error(ErrorCode.LocalDescriptionError, exc));
                        PeerConnection.this.sendErrorToPeer(exc);
                    }
                });
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                PeerConnection.__log.error("Could not set remote description.", exc);
                PeerConnection.this.processLocalError(new Error(ErrorCode.RemoteDescriptionError, exc));
                PeerConnection.this.sendErrorToPeer(exc);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void doProcessMessage(Message message) {
        if (Global.equals(message.getType(), MessageType.getUpdate())) {
            ConnectionConfig fromJson = ConnectionConfig.fromJson(message.getPayload());
            ConnectionInfo connectionInfo = r5;
            ConnectionInfo connectionInfo2 = new ConnectionInfo(getRemoteClientInfo().getUserId(), getRemoteClientInfo().getUserAlias(), getRemoteClientInfo().getDeviceId(), getRemoteClientInfo().getDeviceAlias(), getRemoteClientInfo().getId(), getRemoteClientInfo().getTag(), getRemoteClientInfo().getRoles(), super.getRemoteConnectionId(), super.getRemoteTag(), super.getType(), super.getRemoteMediaId(), super.getRemoteAudioMuted(), super.getRemoteVideoMuted(), StreamDirectionHelper.directionToString(StreamDirectionHelper.setReceiveDisabled(StreamDirectionHelper.setSendDisabled(StreamDirection.SendReceive, super.getRemoteAudioDisabled()), super.getLocalAudioDisabled())), StreamDirectionHelper.directionToString(StreamDirectionHelper.setReceiveDisabled(StreamDirectionHelper.setSendDisabled(StreamDirection.SendReceive, super.getRemoteAudioDisabled()), super.getLocalAudioDisabled())), StreamDirectionHelper.directionToString(StreamDirectionHelper.setReceiveDisabled(StreamDirectionHelper.setSendDisabled(StreamDirection.SendReceive, super.getRemoteDataDisabled()), super.getLocalDataDisabled())), super.getRemoteAudioFormats(), super.getRemoteVideoFormats());
            super.setRemoteTag(fromJson.getTag());
            ConnectionInfo connectionInfo3 = new ConnectionInfo(getRemoteClientInfo().getUserId(), getRemoteClientInfo().getUserAlias(), getRemoteClientInfo().getDeviceId(), getRemoteClientInfo().getDeviceAlias(), getRemoteClientInfo().getId(), getRemoteClientInfo().getTag(), getRemoteClientInfo().getRoles(), super.getRemoteConnectionId(), super.getRemoteTag(), super.getType(), super.getRemoteMediaId(), fromJson.getLocalAudioMuted(), fromJson.getLocalVideoMuted(), fromJson.getAudioDirection(), fromJson.getVideoDirection(), fromJson.getDataDirection(), super.getRemoteAudioFormats(), super.getRemoteVideoFormats());
            super.updateConnection(connectionInfo, connectionInfo3).fail((IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    PeerConnection.__log.error(StringExtensions.format("Could not update peer connection {0}.", (Object) PeerConnection.this.getId()), exc);
                }
            });
        }
    }

    public ClientInfo getRemoteClientInfo() {
        return this._remoteClientInfo;
    }

    public PeerRole getRole() {
        return this._role;
    }

    PeerConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, PeerConnectionOffer peerConnectionOffer, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        super(obj, str, str2, str3, str4, str5, iFunction1, ConnectionType.getPeer(), audioStream, videoStream, dataStream, (String) null);
        setRemoteClientInfo(peerConnectionOffer.getRemoteClientInfo());
        super.setRemoteConnectionId(peerConnectionOffer.getRemoteConnectionId());
        super.setRemoteTag(peerConnectionOffer.getConnectionTag());
        setRole(PeerRole.Answerer);
        this.__pendingRemoteDescription = peerConnectionOffer.getOffer();
    }

    PeerConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, ClientInfo clientInfo, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        super(obj, str, str2, str3, str4, str5, iFunction1, ConnectionType.getPeer(), audioStream, videoStream, dataStream, (String) null);
        setRemoteClientInfo(clientInfo);
        super.setRemoteConnectionId((String) null);
        super.setRemoteTag((String) null);
        setRole(PeerRole.Offerer);
    }

    /* access modifiers changed from: protected */
    public void processAnswer(Message message) {
        super.setRemoteConnectionId(message.getRemoteConnectionId());
        super.setRemoteTag(message.getConnectionTag());
        super.getInternalConnection().setExternalId(message.getRemoteConnectionId());
        super.getInternalConnection().setRemoteDescription(SessionDescription.fromJson(message.getPayload())).then(new IAction1<SessionDescription>() {
            public void invoke(SessionDescription sessionDescription) {
                PeerConnection.this.setRemoteAudioFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getAudioDescription()));
                PeerConnection.this.setRemoteVideoFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getVideoDescription()));
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                PeerConnection.__log.error("Could not set remote description.", exc);
                PeerConnection.this.processLocalError(new Error(ErrorCode.RemoteDescriptionError, exc));
                PeerConnection.this.sendErrorToPeer(exc);
            }
        });
    }

    /* access modifiers changed from: protected */
    public Future<Message> send(Message message) {
        message.setRemoteConnectionId(super.getRemoteConnectionId());
        return super.send(message);
    }

    /* access modifiers changed from: private */
    public void sendErrorToPeer(Exception exc) {
        Error error = super.getInternalConnection().getError();
        if (error != null && Global.equals(error.getCode(), ErrorCode.ConnectionInvalidArchitecture)) {
            send(Message.createErrorMessage(ErrorType.getSdpStreamMismatch(), getRemoteClientInfo().getUserId(), getRemoteClientInfo().getDeviceId(), getRemoteClientInfo().getId())).fail((IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    PeerConnection.__log.error("Could not send error message.", exc);
                }
            });
        } else if (error == null || !Global.equals(error.getCode(), ErrorCode.ConnectionSimulcastNotSupported)) {
            send(Message.createErrorMessage(ErrorType.getSdpCodecMismatch(), getRemoteClientInfo().getUserId(), getRemoteClientInfo().getDeviceId(), getRemoteClientInfo().getId())).fail((IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    PeerConnection.__log.error("Could not send error message.", exc);
                }
            });
        } else {
            send(Message.createErrorMessage(ErrorType.getSdpSimulcastMismatch(), getRemoteClientInfo().getUserId(), getRemoteClientInfo().getDeviceId(), getRemoteClientInfo().getId())).fail((IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    PeerConnection.__log.error("Could not send error message.", exc);
                }
            });
        }
    }

    private void setRemoteClientInfo(ClientInfo clientInfo) {
        this._remoteClientInfo = clientInfo;
    }

    private void setRole(PeerRole peerRole) {
        this._role = peerRole;
    }
}
