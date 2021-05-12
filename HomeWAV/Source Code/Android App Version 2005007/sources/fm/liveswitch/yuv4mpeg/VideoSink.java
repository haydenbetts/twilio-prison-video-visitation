package fm.liveswitch.yuv4mpeg;

import fm.liveswitch.Global;
import fm.liveswitch.IAction0;
import fm.liveswitch.IActionDelegate0;
import fm.liveswitch.ILog;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.Log;
import fm.liveswitch.PliControlFrame;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VideoSink extends fm.liveswitch.VideoSink {
    private static ILog __log = Log.getLogger(VideoSink.class);
    private Object __locker;
    /* access modifiers changed from: private */
    public List<IAction0> __onFileClose;
    /* access modifiers changed from: private */
    public List<IAction0> __onFileOpen;
    private VideoRecorder __recorder;
    private long _baseTimestamp;
    private String _lastFilePath;
    private long _lastTimestamp;
    private IAction0 _onFileClose;
    private IAction0 _onFileOpen;
    private String _path;

    public String getLabel() {
        return "YUV4MPEG Video Sink";
    }

    public void addOnFileClose(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onFileClose == null) {
                this._onFileClose = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(VideoSink.this.__onFileClose).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onFileClose.add(iAction0);
        }
    }

    public void addOnFileOpen(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onFileOpen == null) {
                this._onFileOpen = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(VideoSink.this.__onFileOpen).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onFileOpen.add(iAction0);
        }
    }

    private void closeRecorder() {
        this.__recorder.close();
        this.__recorder = null;
        IAction0 iAction0 = this._onFileClose;
        if (iAction0 != null) {
            iAction0.invoke();
        }
        setBaseTimestamp(-1);
        setLastTimestamp(-1);
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        synchronized (this.__locker) {
            if (this.__recorder != null) {
                closeRecorder();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        synchronized (this.__locker) {
            if (!getDisabled()) {
                if (this.__recorder == null) {
                    openRecorder(videoFrame);
                }
                if (!this.__recorder.getOpened()) {
                    __log.warn("Cannot record. Recorder is not ready.");
                } else if (this.__recorder.write(videoBuffer, videoFrame.getTimestamp())) {
                    setLastTimestamp(videoFrame.getTimestamp());
                } else {
                    super.raiseControlFrame(new PliControlFrame());
                }
            }
        }
    }

    public long getBaseTimestamp() {
        return this._baseTimestamp;
    }

    public String getLastFilePath() {
        return this._lastFilePath;
    }

    public long getLastTimestamp() {
        return this._lastTimestamp;
    }

    public String getOpenFilePath() {
        VideoRecorder videoRecorder = this.__recorder;
        if (videoRecorder == null) {
            return null;
        }
        return videoRecorder.getOpenPath();
    }

    public String getPath() {
        return this._path;
    }

    private void initialize(String str) {
        setPath(str);
        super.addOnDisabledChange(new IActionDelegate0() {
            public String getId() {
                return "fm.liveswitch.yuv4mpeg.VideoSink.videoSink_OnDisabledChange";
            }

            public void invoke() {
                VideoSink.this.videoSink_OnDisabledChange();
            }
        });
    }

    private void openRecorder(VideoFrame videoFrame) {
        VideoRecorder videoRecorder = new VideoRecorder(getPath());
        this.__recorder = videoRecorder;
        videoRecorder.open();
        setLastFilePath(this.__recorder.getOpenPath());
        setBaseTimestamp(videoFrame.getTimestamp());
        setLastTimestamp(videoFrame.getTimestamp());
        IAction0 iAction0 = this._onFileOpen;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }

    public void removeOnFileClose(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onFileClose, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onFileClose.remove(iAction0);
        if (this.__onFileClose.size() == 0) {
            this._onFileClose = null;
        }
    }

    public void removeOnFileOpen(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onFileOpen, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onFileOpen.remove(iAction0);
        if (this.__onFileOpen.size() == 0) {
            this._onFileOpen = null;
        }
    }

    private void setBaseTimestamp(long j) {
        this._baseTimestamp = j;
    }

    private void setLastFilePath(String str) {
        this._lastFilePath = str;
    }

    private void setLastTimestamp(long j) {
        this._lastTimestamp = j;
    }

    private void setPath(String str) {
        this._path = str;
    }

    public VideoSink(String str) {
        super(VideoFormat.getI420());
        this.__onFileClose = new ArrayList();
        this.__onFileOpen = new ArrayList();
        this._onFileClose = null;
        this._onFileOpen = null;
        this.__locker = new Object();
        initialize(str);
    }

    public VideoSink(String str, IVideoOutput iVideoOutput) {
        this(str);
        super.addInput(iVideoOutput);
    }

    /* access modifiers changed from: private */
    public void videoSink_OnDisabledChange() {
        if (getDisabled()) {
            synchronized (this.__locker) {
                if (this.__recorder != null) {
                    closeRecorder();
                }
            }
        }
    }
}
