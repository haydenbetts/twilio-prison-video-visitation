package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFormatCollection;
import fm.liveswitch.MediaFrame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class MediaTransport<TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>, TFormatCollection extends MediaFormatCollection<TFormat, TFormatCollection>> implements IMediaTransport {
    private RtpExtensionParameters __extensionParameters = new RtpExtensionParameters();
    private String __id = Utility.generateId();
    Object __lock;
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onReceiveControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onReceiveControlFrames = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onReceiveFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<IMediaTransport>> __onStateChange = new ArrayList();
    private RtpParameters<TFormat, TFormatCollection> __parameters = null;
    private volatile boolean __resendingPacket = false;
    private volatile boolean __sendingControlFrame = false;
    private volatile boolean __sendingControlFrameResponse = false;
    private volatile boolean __sendingFrame = false;
    MediaTransportState __state = MediaTransportState.New;
    private String _connectionId;
    private StreamType _mediaStreamType;
    private IAction1<MediaControlFrame[]> _onReceiveControlFrameResponses = null;
    private IAction1<MediaControlFrame[]> _onReceiveControlFrames = null;
    private IAction1<TFrame> _onReceiveFrame = null;
    private IAction1<IMediaTransport> _onStateChange = null;
    private String _streamId;

    public static int getSendFrameErrorEncryptionFailed() {
        return -5;
    }

    public static int getSendFrameErrorFecFailure() {
        return -10;
    }

    public static int getSendFrameErrorMalformedRtpHeader() {
        return -9;
    }

    public static int getSendFrameErrorMissingRtpHeaders() {
        return -7;
    }

    public static int getSendFrameErrorNoBuffers() {
        return -3;
    }

    public static int getSendFrameErrorNoEncryptionContext() {
        return -2;
    }

    public static int getSendFrameErrorNoPacketizedBuffers() {
        return -4;
    }

    public static int getSendFrameErrorNoSynchronizationSource() {
        return -6;
    }

    public static int getSendFrameErrorNoTimestamp() {
        return -11;
    }

    public static int getSendFrameErrorNotStarted() {
        return -1;
    }

    public static int getSendFrameErrorRateLimited() {
        return -13;
    }

    public static int getSendFrameErrorUnknownPayloadType() {
        return -8;
    }

    public static int getSendFrameErrorUnknownSynchronizationSource() {
        return -12;
    }

    public static int getSendFrameSuccess() {
        return 0;
    }

    public abstract int doSendControlFrameResponses(MediaControlFrame[] mediaControlFrameArr);

    public abstract int doSendControlFrames(MediaControlFrame[] mediaControlFrameArr);

    public abstract int doSendFrame(TFrame tframe);

    public abstract boolean doStart();

    public abstract boolean doStop();

    public void addOnReceiveControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onReceiveControlFrameResponses == null) {
                this._onReceiveControlFrameResponses = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaTransport.this.__onReceiveControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onReceiveControlFrameResponses.add(iAction1);
        }
    }

    public void addOnReceiveControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onReceiveControlFrames == null) {
                this._onReceiveControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaTransport.this.__onReceiveControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onReceiveControlFrames.add(iAction1);
        }
    }

    public void addOnReceiveFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onReceiveFrame == null) {
                this._onReceiveFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaTransport.this.__onReceiveFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onReceiveFrame.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<IMediaTransport> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<IMediaTransport>() {
                    public void invoke(IMediaTransport iMediaTransport) {
                        Iterator it = new ArrayList(MediaTransport.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(iMediaTransport);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    public String getConnectionId() {
        return this._connectionId;
    }

    public RtpIExtensionParameters getExtensionParameters() {
        return this.__extensionParameters;
    }

    public RtpIFormatParameters<TFormat> getFormatParameters() {
        return this.__parameters;
    }

    public String getId() {
        return this.__id;
    }

    public StreamType getMediaStreamType() {
        return this._mediaStreamType;
    }

    public RtpIParameters getParameters() {
        return this.__parameters;
    }

    public MediaTransportState getState() {
        return this.__state;
    }

    public String getStreamId() {
        return this._streamId;
    }

    public boolean hasRemoteSynchronizationSource(long j) {
        return getParameters() != null && getParameters().hasRemoteSynchronizationSource(j);
    }

    public MediaTransport(Object obj) {
        this.__lock = obj;
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        IAction1<MediaControlFrame[]> iAction1 = this._onReceiveControlFrameResponses;
        if (iAction1 != null) {
            iAction1.invoke(mediaControlFrameArr);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseStateChange(MediaTransportState mediaTransportState) {
        this.__state = mediaTransportState;
        IAction1<IMediaTransport> iAction1 = this._onStateChange;
        if (iAction1 != null) {
            iAction1.invoke(this);
        }
    }

    /* access modifiers changed from: protected */
    public void receiveControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        IAction1<MediaControlFrame[]> iAction1 = this._onReceiveControlFrames;
        if (iAction1 != null) {
            iAction1.invoke(mediaControlFrameArr);
        }
    }

    /* access modifiers changed from: protected */
    public void receiveFrame(TFrame tframe) {
        IAction1<TFrame> iAction1 = this._onReceiveFrame;
        if (iAction1 != null) {
            iAction1.invoke(tframe);
        }
    }

    public void removeOnReceiveControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceiveControlFrameResponses, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceiveControlFrameResponses.remove(iAction1);
        if (this.__onReceiveControlFrameResponses.size() == 0) {
            this._onReceiveControlFrameResponses = null;
        }
    }

    public void removeOnReceiveControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceiveControlFrames, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceiveControlFrames.remove(iAction1);
        if (this.__onReceiveControlFrames.size() == 0) {
            this._onReceiveControlFrames = null;
        }
    }

    public void removeOnReceiveFrame(IAction1<TFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceiveFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceiveFrame.remove(iAction1);
        if (this.__onReceiveFrame.size() == 0) {
            this._onReceiveFrame = null;
        }
    }

    public void removeOnStateChange(IAction1<IMediaTransport> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    public int sendControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        if (Global.equals(this.__state, MediaTransportState.Started)) {
            this.__sendingControlFrameResponse = true;
            try {
                if (Global.equals(this.__state, MediaTransportState.Started)) {
                    return doSendControlFrameResponses(mediaControlFrameArr);
                }
                this.__sendingControlFrameResponse = false;
            } finally {
                this.__sendingControlFrameResponse = false;
            }
        }
        return getSendFrameErrorNotStarted();
    }

    public int sendControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        if (Global.equals(this.__state, MediaTransportState.Started)) {
            this.__sendingControlFrame = true;
            try {
                if (Global.equals(this.__state, MediaTransportState.Started)) {
                    return doSendControlFrames(mediaControlFrameArr);
                }
                this.__sendingControlFrame = false;
            } finally {
                this.__sendingControlFrame = false;
            }
        }
        return getSendFrameErrorNotStarted();
    }

    public int sendFrame(TFrame tframe) {
        if (Global.equals(this.__state, MediaTransportState.Started)) {
            this.__sendingFrame = true;
            try {
                if (Global.equals(this.__state, MediaTransportState.Started)) {
                    return doSendFrame(tframe);
                }
                this.__sendingFrame = false;
            } finally {
                this.__sendingFrame = false;
            }
        }
        return getSendFrameErrorNotStarted();
    }

    public void setConnectionId(String str) {
        this._connectionId = str;
    }

    public void setMediaStreamType(StreamType streamType) {
        this._mediaStreamType = streamType;
    }

    public void setParameters(RtpParameters<TFormat, TFormatCollection> rtpParameters) {
        this.__parameters = rtpParameters;
    }

    public void setStreamId(String str) {
        this._streamId = str;
    }

    public boolean start() {
        synchronized (this.__lock) {
            if (!Global.equals(this.__state, MediaTransportState.Started) && !Global.equals(this.__state, MediaTransportState.Starting)) {
                raiseStateChange(MediaTransportState.Starting);
                if (doStart()) {
                    raiseStateChange(MediaTransportState.Started);
                    return true;
                }
                raiseStateChange(MediaTransportState.Failed);
            }
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r4.__sendingFrame != false) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        if (r4.__sendingControlFrame != false) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        if (r4.__sendingControlFrameResponse != false) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0024, code lost:
        if (r4.__resendingPacket == false) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0027, code lost:
        r0 = r4.__lock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002e, code lost:
        if (doStop() == false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        fm.liveswitch.Log.debug(fm.liveswitch.StringExtensions.format("MediaTransport {0} shut down gracefully.", (java.lang.Object) getId()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        raiseStateChange(fm.liveswitch.MediaTransportState.Stopped);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0043, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0044, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        fm.liveswitch.Log.debug(fm.liveswitch.StringExtensions.format("MediaTransport {0} requires force shut down.", (java.lang.Object) getId()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        raiseStateChange(fm.liveswitch.MediaTransportState.Stopped);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0057, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0058, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0059, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x005a, code lost:
        raiseStateChange(fm.liveswitch.MediaTransportState.Stopped);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005f, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0063, code lost:
        fm.liveswitch.ManagedThread.sleep(10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean stop() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.__lock
            monitor-enter(r0)
            fm.liveswitch.MediaTransportState r1 = r4.__state     // Catch:{ all -> 0x0069 }
            fm.liveswitch.MediaTransportState r2 = fm.liveswitch.MediaTransportState.Started     // Catch:{ all -> 0x0069 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0069 }
            r2 = 0
            if (r1 != 0) goto L_0x0010
            monitor-exit(r0)     // Catch:{ all -> 0x0069 }
            return r2
        L_0x0010:
            fm.liveswitch.MediaTransportState r1 = fm.liveswitch.MediaTransportState.Stopping     // Catch:{ all -> 0x0069 }
            r4.raiseStateChange(r1)     // Catch:{ all -> 0x0069 }
            monitor-exit(r0)     // Catch:{ all -> 0x0069 }
        L_0x0016:
            boolean r0 = r4.__sendingFrame
            if (r0 != 0) goto L_0x0063
            boolean r0 = r4.__sendingControlFrame
            if (r0 != 0) goto L_0x0063
            boolean r0 = r4.__sendingControlFrameResponse
            if (r0 != 0) goto L_0x0063
            boolean r0 = r4.__resendingPacket
            if (r0 == 0) goto L_0x0027
            goto L_0x0063
        L_0x0027:
            java.lang.Object r0 = r4.__lock
            monitor-enter(r0)
            boolean r1 = r4.doStop()     // Catch:{ all -> 0x0059 }
            if (r1 == 0) goto L_0x0045
            java.lang.String r1 = "MediaTransport {0} shut down gracefully."
            java.lang.String r2 = r4.getId()     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x0059 }
            fm.liveswitch.Log.debug(r1)     // Catch:{ all -> 0x0059 }
            r1 = 1
            fm.liveswitch.MediaTransportState r2 = fm.liveswitch.MediaTransportState.Stopped     // Catch:{ all -> 0x0060 }
            r4.raiseStateChange(r2)     // Catch:{ all -> 0x0060 }
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return r1
        L_0x0045:
            java.lang.String r1 = "MediaTransport {0} requires force shut down."
            java.lang.String r3 = r4.getId()     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r3)     // Catch:{ all -> 0x0059 }
            fm.liveswitch.Log.debug(r1)     // Catch:{ all -> 0x0059 }
            fm.liveswitch.MediaTransportState r1 = fm.liveswitch.MediaTransportState.Stopped     // Catch:{ all -> 0x0060 }
            r4.raiseStateChange(r1)     // Catch:{ all -> 0x0060 }
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return r2
        L_0x0059:
            r1 = move-exception
            fm.liveswitch.MediaTransportState r2 = fm.liveswitch.MediaTransportState.Stopped     // Catch:{ all -> 0x0060 }
            r4.raiseStateChange(r2)     // Catch:{ all -> 0x0060 }
            throw r1     // Catch:{ all -> 0x0060 }
        L_0x0060:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            throw r1
        L_0x0063:
            r0 = 10
            fm.liveswitch.ManagedThread.sleep(r0)
            goto L_0x0016
        L_0x0069:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0069 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaTransport.stop():boolean");
    }
}
