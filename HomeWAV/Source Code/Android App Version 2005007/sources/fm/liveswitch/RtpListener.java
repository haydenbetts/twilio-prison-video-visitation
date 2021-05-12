package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class RtpListener {
    private static ILog __log = Log.getLogger(RtpListener.class);
    private String __id = Utility.generateId();
    private Object __innerLocker = new Object();
    private Object __lock;
    private int __maxRtcpDispatchQueueBuffered;
    private int __maxRtpDispatchQueueBuffered;
    /* access modifiers changed from: private */
    public List<IAction1<RtpInboundRtcp>> __onReceiveRtcp = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<RtpInboundRtp>> __onReceiveRtp = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<RtpListener>> __onStateChange = new ArrayList();
    private IDispatchQueue<RtpInboundRtcp> __receiveRtcpDispatchQueue;
    private IDispatchQueue<RtpInboundRtp> __receiveRtpDispatchQueue;
    private AtomicInteger __rtcpDispatchQueueBuffered;
    private AtomicInteger __rtpDispatchQueueBuffered;
    private MediaTransportState __state = MediaTransportState.New;
    private IMediaTransport _mediaTransport;
    private IAction1<RtpInboundRtcp> _onReceiveRtcp = null;
    private IAction1<RtpInboundRtp> _onReceiveRtp = null;
    private IAction1<RtpListener> _onStateChange = null;
    private StreamType _streamType;
    private boolean _useDispatchQueues = true;

    public void addOnReceiveRtcp(IAction1<RtpInboundRtcp> iAction1) {
        if (iAction1 != null) {
            if (this._onReceiveRtcp == null) {
                this._onReceiveRtcp = new IAction1<RtpInboundRtcp>() {
                    public void invoke(RtpInboundRtcp rtpInboundRtcp) {
                        Iterator it = new ArrayList(RtpListener.this.__onReceiveRtcp).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(rtpInboundRtcp);
                        }
                    }
                };
            }
            this.__onReceiveRtcp.add(iAction1);
        }
    }

    public void addOnReceiveRtp(IAction1<RtpInboundRtp> iAction1) {
        if (iAction1 != null) {
            if (this._onReceiveRtp == null) {
                this._onReceiveRtp = new IAction1<RtpInboundRtp>() {
                    public void invoke(RtpInboundRtp rtpInboundRtp) {
                        Iterator it = new ArrayList(RtpListener.this.__onReceiveRtp).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(rtpInboundRtp);
                        }
                    }
                };
            }
            this.__onReceiveRtp.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<RtpListener> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<RtpListener>() {
                    public void invoke(RtpListener rtpListener) {
                        Iterator it = new ArrayList(RtpListener.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(rtpListener);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void dispatchReceiveRtcp(fm.liveswitch.RtpInboundRtcp r5) {
        /*
            r4 = this;
            r0 = 0
            fm.liveswitch.AtomicInteger r1 = r4.__rtcpDispatchQueueBuffered     // Catch:{ all -> 0x0025 }
            int r2 = r5.getFootprint()     // Catch:{ all -> 0x0025 }
            r1.subtract(r2)     // Catch:{ all -> 0x0025 }
            fm.liveswitch.IAction1<fm.liveswitch.RtpInboundRtcp> r1 = r4._onReceiveRtcp     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x0011
            r1.invoke(r5)     // Catch:{ all -> 0x0025 }
        L_0x0011:
            fm.liveswitch.MediaControlFrame[] r5 = r5.getFrames()
            int r1 = r5.length
        L_0x0016:
            if (r0 >= r1) goto L_0x0024
            r2 = r5[r0]
            fm.liveswitch.DataBuffer r2 = r2.getDataBuffer()
            r2.free()
            int r0 = r0 + 1
            goto L_0x0016
        L_0x0024:
            return
        L_0x0025:
            r1 = move-exception
            fm.liveswitch.MediaControlFrame[] r5 = r5.getFrames()
            int r2 = r5.length
        L_0x002b:
            if (r0 >= r2) goto L_0x0039
            r3 = r5[r0]
            fm.liveswitch.DataBuffer r3 = r3.getDataBuffer()
            r3.free()
            int r0 = r0 + 1
            goto L_0x002b
        L_0x0039:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtpListener.dispatchReceiveRtcp(fm.liveswitch.RtpInboundRtcp):void");
    }

    /* access modifiers changed from: private */
    public void dispatchReceiveRtp(RtpInboundRtp rtpInboundRtp) {
        try {
            this.__rtpDispatchQueueBuffered.subtract(rtpInboundRtp.getFootprint());
            IAction1<RtpInboundRtp> iAction1 = this._onReceiveRtp;
            if (iAction1 != null) {
                iAction1.invoke(rtpInboundRtp);
            }
        } finally {
            rtpInboundRtp.getBuffer().free();
        }
    }

    public String getId() {
        return this.__id;
    }

    public IMediaTransport getMediaTransport() {
        return this._mediaTransport;
    }

    public MediaTransportState getState() {
        return this.__state;
    }

    public StreamType getStreamType() {
        return this._streamType;
    }

    private void initialize(Object obj, IMediaTransport iMediaTransport) {
        this.__lock = obj;
        setMediaTransport(iMediaTransport);
        this.__maxRtpDispatchQueueBuffered = -1;
        this.__maxRtcpDispatchQueueBuffered = -1;
        this.__rtpDispatchQueueBuffered = new AtomicInteger();
        this.__rtcpDispatchQueueBuffered = new AtomicInteger();
        this.__receiveRtpDispatchQueue = new DispatchQueue(new IActionDelegate1<RtpInboundRtp>() {
            public String getId() {
                return "fm.liveswitch.RtpListener.dispatchReceiveRtp";
            }

            public void invoke(RtpInboundRtp rtpInboundRtp) {
                RtpListener.this.dispatchReceiveRtp(rtpInboundRtp);
            }
        });
        this.__receiveRtcpDispatchQueue = new DispatchQueue(new IActionDelegate1<RtpInboundRtcp>() {
            public String getId() {
                return "fm.liveswitch.RtpListener.dispatchReceiveRtcp";
            }

            public void invoke(RtpInboundRtcp rtpInboundRtcp) {
                RtpListener.this.dispatchReceiveRtcp(rtpInboundRtcp);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void processRtcp(RtpInboundRtcp rtpInboundRtcp) {
        for (MediaControlFrame dataBuffer : rtpInboundRtcp.getFrames()) {
            dataBuffer.getDataBuffer().keep();
        }
        if (this._useDispatchQueues) {
            int i = this.__maxRtcpDispatchQueueBuffered;
            if (i == -1 || this.__rtcpDispatchQueueBuffered.getValue() + rtpInboundRtcp.getFootprint() < i) {
                this.__rtcpDispatchQueueBuffered.add(rtpInboundRtcp.getFootprint());
                this.__receiveRtcpDispatchQueue.enqueue(rtpInboundRtcp);
            } else if (__log.getIsVerboseEnabled()) {
                for (MediaControlFrame payloadType : rtpInboundRtcp.getFrames()) {
                    __log.verbose(StringExtensions.format("Discarding inbound RTCP packet with payload type {0} in {1} stream due to slow inbound media processing.", IntegerExtensions.toString(Integer.valueOf(payloadType.getPayloadType())), getStreamType().toString()));
                }
            }
        } else {
            dispatchReceiveRtcp(rtpInboundRtcp);
        }
    }

    /* access modifiers changed from: package-private */
    public void processRtp(RtpInboundRtp rtpInboundRtp) {
        rtpInboundRtp.getBuffer().keep();
        if (this._useDispatchQueues) {
            int i = this.__maxRtpDispatchQueueBuffered;
            if (i == -1 || this.__rtpDispatchQueueBuffered.getValue() + rtpInboundRtp.getFootprint() < i) {
                this.__rtpDispatchQueueBuffered.add(rtpInboundRtp.getFootprint());
                this.__receiveRtpDispatchQueue.enqueue(rtpInboundRtp);
            } else if (__log.getIsVerboseEnabled()) {
                __log.verbose(StringExtensions.format("Discarding inbound RTP packet with payload type {0} in {1} stream due to slow inbound media processing.", IntegerExtensions.toString(Integer.valueOf(rtpInboundRtp.getHeader().getPayloadType())), getStreamType().toString()));
            }
        } else {
            dispatchReceiveRtp(rtpInboundRtp);
        }
    }

    public void removeOnReceiveRtcp(IAction1<RtpInboundRtcp> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceiveRtcp, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceiveRtcp.remove(iAction1);
        if (this.__onReceiveRtcp.size() == 0) {
            this._onReceiveRtcp = null;
        }
    }

    public void removeOnReceiveRtp(IAction1<RtpInboundRtp> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceiveRtp, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceiveRtp.remove(iAction1);
        if (this.__onReceiveRtp.size() == 0) {
            this._onReceiveRtp = null;
        }
    }

    public void removeOnStateChange(IAction1<RtpListener> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    public RtpListener(Object obj, RtpAudioTransport rtpAudioTransport) {
        setStreamType(StreamType.Audio);
        rtpAudioTransport.setListener(this);
        initialize(obj, rtpAudioTransport);
    }

    public RtpListener(Object obj, RtpVideoTransport rtpVideoTransport) {
        setStreamType(StreamType.Video);
        rtpVideoTransport.setListener(this);
        initialize(obj, rtpVideoTransport);
    }

    private void setMediaTransport(IMediaTransport iMediaTransport) {
        this._mediaTransport = iMediaTransport;
    }

    private void setStreamType(StreamType streamType) {
        this._streamType = streamType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean start(int r4) {
        /*
            r3 = this;
            r0 = -1
            if (r4 != r0) goto L_0x0005
            r4 = 65536(0x10000, float:9.18355E-41)
        L_0x0005:
            r0 = 2048(0x800, float:2.87E-42)
            int r4 = fm.liveswitch.MathAssistant.max((int) r0, (int) r4)
            java.lang.Object r0 = r3.__lock
            monitor-enter(r0)
            fm.liveswitch.MediaTransportState r1 = r3.__state     // Catch:{ all -> 0x0038 }
            fm.liveswitch.MediaTransportState r2 = fm.liveswitch.MediaTransportState.Started     // Catch:{ all -> 0x0038 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0038 }
            if (r1 != 0) goto L_0x0035
            fm.liveswitch.MediaTransportState r1 = r3.__state     // Catch:{ all -> 0x0038 }
            fm.liveswitch.MediaTransportState r2 = fm.liveswitch.MediaTransportState.Stopped     // Catch:{ all -> 0x0038 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0038 }
            if (r1 == 0) goto L_0x0023
            goto L_0x0035
        L_0x0023:
            r3.__maxRtpDispatchQueueBuffered = r4     // Catch:{ all -> 0x0038 }
            r3.__maxRtcpDispatchQueueBuffered = r4     // Catch:{ all -> 0x0038 }
            fm.liveswitch.MediaTransportState r4 = fm.liveswitch.MediaTransportState.Started     // Catch:{ all -> 0x0038 }
            r3.__state = r4     // Catch:{ all -> 0x0038 }
            fm.liveswitch.IAction1<fm.liveswitch.RtpListener> r4 = r3._onStateChange     // Catch:{ all -> 0x0038 }
            if (r4 == 0) goto L_0x0032
            r4.invoke(r3)     // Catch:{ all -> 0x0038 }
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0038 }
            r4 = 1
            return r4
        L_0x0035:
            r4 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x0038 }
            return r4
        L_0x0038:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0038 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtpListener.start(int):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        r4.__receiveRtpDispatchQueue.destroy();
        r4.__receiveRtcpDispatchQueue.destroy();
        __log.debug(getId(), fm.liveswitch.StringExtensions.format("Listener shut down.", new java.lang.Object[0]));
        setMediaTransport((fm.liveswitch.IMediaTransport) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003c, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean stop() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.__lock
            monitor-enter(r0)
            fm.liveswitch.MediaTransportState r1 = r4.__state     // Catch:{ all -> 0x003d }
            fm.liveswitch.MediaTransportState r2 = fm.liveswitch.MediaTransportState.Stopped     // Catch:{ all -> 0x003d }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x003d }
            r2 = 0
            if (r1 == 0) goto L_0x0010
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            return r2
        L_0x0010:
            fm.liveswitch.MediaTransportState r1 = fm.liveswitch.MediaTransportState.Stopped     // Catch:{ all -> 0x003d }
            r4.__state = r1     // Catch:{ all -> 0x003d }
            fm.liveswitch.IAction1<fm.liveswitch.RtpListener> r1 = r4._onStateChange     // Catch:{ all -> 0x003d }
            if (r1 == 0) goto L_0x001b
            r1.invoke(r4)     // Catch:{ all -> 0x003d }
        L_0x001b:
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            fm.liveswitch.IDispatchQueue<fm.liveswitch.RtpInboundRtp> r0 = r4.__receiveRtpDispatchQueue
            r0.destroy()
            fm.liveswitch.IDispatchQueue<fm.liveswitch.RtpInboundRtcp> r0 = r4.__receiveRtcpDispatchQueue
            r0.destroy()
            fm.liveswitch.ILog r0 = __log
            java.lang.String r1 = r4.getId()
            java.lang.String r3 = "Listener shut down."
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object[]) r2)
            r0.debug((java.lang.String) r1, (java.lang.String) r2)
            r0 = 0
            r4.setMediaTransport(r0)
            r0 = 1
            return r0
        L_0x003d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtpListener.stop():boolean");
    }
}
