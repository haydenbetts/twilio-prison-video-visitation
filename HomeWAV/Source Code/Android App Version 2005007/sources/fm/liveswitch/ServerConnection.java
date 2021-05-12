package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ServerConnection extends ManagedConnection {
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(ServerConnection.class);
    private int __inboundAudioBitrate = -1;
    private int __inboundVideoBitrate = -1;
    /* access modifiers changed from: private */
    public List<IAction2<Integer, Integer>> __onInboundAudioBitrateChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<Integer, Integer>> __onInboundVideoBitrateChange = new ArrayList();
    private IAction2<Integer, Integer> _onInboundAudioBitrateChange = null;
    private IAction2<Integer, Integer> _onInboundVideoBitrateChange = null;

    /* access modifiers changed from: protected */
    public abstract Message doCreateOfferMessage(SessionDescription sessionDescription);

    public void addOnInboundAudioBitrateChange(IAction2<Integer, Integer> iAction2) {
        if (iAction2 != null) {
            if (this._onInboundAudioBitrateChange == null) {
                this._onInboundAudioBitrateChange = new IAction2<Integer, Integer>() {
                    public void invoke(Integer num, Integer num2) {
                        Iterator it = new ArrayList(ServerConnection.this.__onInboundAudioBitrateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(num, num2);
                        }
                    }
                };
            }
            this.__onInboundAudioBitrateChange.add(iAction2);
        }
    }

    public void addOnInboundVideoBitrateChange(IAction2<Integer, Integer> iAction2) {
        if (iAction2 != null) {
            if (this._onInboundVideoBitrateChange == null) {
                this._onInboundVideoBitrateChange = new IAction2<Integer, Integer>() {
                    public void invoke(Integer num, Integer num2) {
                        Iterator it = new ArrayList(ServerConnection.this.__onInboundVideoBitrateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(num, num2);
                        }
                    }
                };
            }
            this.__onInboundVideoBitrateChange.add(iAction2);
        }
    }

    /* access modifiers changed from: protected */
    public Message doCreateCandidateMessage(Candidate candidate) {
        return Message.createCandidateMessage(candidate.toJson());
    }

    /* access modifiers changed from: protected */
    public Message doCreateCloseMessage() {
        return Message.createCloseMessage();
    }

    /* access modifiers changed from: protected */
    public void doOpen() {
        super.getInternalConnection().createOffer().then(new IAction1<SessionDescription>() {
            public void invoke(SessionDescription sessionDescription) {
                ServerConnection.this.getInternalConnection().setLocalDescription(sessionDescription).then(new IAction1<SessionDescription>() {
                    public void invoke(SessionDescription sessionDescription) {
                        ServerConnection.this.setLocalAudioFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getAudioDescription()));
                        ServerConnection.this.setLocalVideoFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getVideoDescription()));
                        ServerConnection.this.send(ServerConnection.this.doCreateOfferMessage(sessionDescription)).then(new IAction1<Message>() {
                            public void invoke(Message message) {
                                if (message != null) {
                                    ServerConnection.this.processMessage(message);
                                }
                            }
                        }, (IAction1<Exception>) new IAction1<Exception>() {
                            public void invoke(Exception exc) {
                                ServerConnection.__log.error("Could not send offer message.", exc);
                            }
                        });
                    }
                }, (IAction1<Exception>) new IAction1<Exception>() {
                    public void invoke(Exception exc) {
                        ServerConnection.__log.error("Could not set local description.", exc);
                        ServerConnection.this.processLocalError(new Error(ErrorCode.LocalDescriptionError, exc));
                    }
                });
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                ServerConnection.__log.error("Could not create offer.", exc);
                ServerConnection.this.processLocalError(new Error(ErrorCode.LocalDescriptionError, exc));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void doProcessMessage(Message message) {
        if (Global.equals(message.getType(), MessageType.getUpdate())) {
            ConnectionConfig fromJson = ConnectionConfig.fromJson(message.getPayload());
            EncodingInfo remoteAudioEncoding = super.getRemoteAudioEncoding();
            EncodingInfo remoteAudioEncoding2 = fromJson.getRemoteAudioEncoding();
            if (remoteAudioEncoding2 != null && !Global.equals(remoteAudioEncoding, remoteAudioEncoding2) && (remoteAudioEncoding == null || !remoteAudioEncoding.isEquivalent(remoteAudioEncoding2))) {
                super.setRemoteAudioEncoding(remoteAudioEncoding2);
            }
            EncodingInfo remoteVideoEncoding = super.getRemoteVideoEncoding();
            EncodingInfo remoteVideoEncoding2 = fromJson.getRemoteVideoEncoding();
            if (remoteVideoEncoding2 != null && !Global.equals(remoteVideoEncoding, remoteVideoEncoding2)) {
                if (remoteVideoEncoding == null || !remoteVideoEncoding.isEquivalent(remoteVideoEncoding2)) {
                    super.setRemoteVideoEncoding(remoteVideoEncoding2);
                }
            }
        }
    }

    public int getInboundAudioBitrate() {
        return this.__inboundAudioBitrate;
    }

    public int getInboundVideoBitrate() {
        return this.__inboundVideoBitrate;
    }

    /* access modifiers changed from: protected */
    public void processAnswer(Message message) {
        super.getInternalConnection().setRemoteDescription(SessionDescription.fromJson(message.getPayload())).then(new IAction1<SessionDescription>() {
            public void invoke(SessionDescription sessionDescription) {
                ServerConnection.this.setRemoteAudioFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getAudioDescription()));
                ServerConnection.this.setRemoteVideoFormats(FormatInfo.fromSdpMediaDescription(sessionDescription.getSdpMessage().getVideoDescription()));
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                ServerConnection.__log.error("Could not set remote description.", exc);
                ServerConnection.this.processLocalError(new Error(ErrorCode.RemoteDescriptionError, exc));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void raiseAudioBitrateNotification(AudioStream audioStream, BitrateNotification bitrateNotification) {
        super.raiseAudioBitrateNotification(audioStream, bitrateNotification);
        int inboundAudioBitrate = getInboundAudioBitrate();
        int bitrate = bitrateNotification.getBitrate();
        if (inboundAudioBitrate != bitrate) {
            setInboundAudioBitrate(bitrate);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseVideoBitrateNotification(VideoStream videoStream, BitrateNotification bitrateNotification) {
        super.raiseVideoBitrateNotification(videoStream, bitrateNotification);
        int inboundVideoBitrate = getInboundVideoBitrate();
        int bitrate = bitrateNotification.getBitrate();
        if (inboundVideoBitrate != bitrate) {
            setInboundVideoBitrate(bitrate);
        }
    }

    public void removeOnInboundAudioBitrateChange(IAction2<Integer, Integer> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onInboundAudioBitrateChange, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onInboundAudioBitrateChange.remove(iAction2);
        if (this.__onInboundAudioBitrateChange.size() == 0) {
            this._onInboundAudioBitrateChange = null;
        }
    }

    public void removeOnInboundVideoBitrateChange(IAction2<Integer, Integer> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onInboundVideoBitrateChange, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onInboundVideoBitrateChange.remove(iAction2);
        if (this.__onInboundVideoBitrateChange.size() == 0) {
            this._onInboundVideoBitrateChange = null;
        }
    }

    ServerConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, String str6, AudioStream audioStream, VideoStream videoStream, DataStream dataStream, String str7) {
        super(obj, str, str2, str3, str4, str5, iFunction1, str6, audioStream, videoStream, dataStream, str7);
        super.setBundlePolicy(BundlePolicy.MaxBundle);
    }

    private void setInboundAudioBitrate(int i) {
        int i2 = this.__inboundAudioBitrate;
        this.__inboundAudioBitrate = i;
        IAction2<Integer, Integer> iAction2 = this._onInboundAudioBitrateChange;
        if (iAction2 != null) {
            iAction2.invoke(Integer.valueOf(i2), Integer.valueOf(i));
        }
    }

    private void setInboundVideoBitrate(int i) {
        int i2 = this.__inboundVideoBitrate;
        this.__inboundVideoBitrate = i;
        IAction2<Integer, Integer> iAction2 = this._onInboundVideoBitrateChange;
        if (iAction2 != null) {
            iAction2.invoke(Integer.valueOf(i2), Integer.valueOf(i));
        }
    }
}
