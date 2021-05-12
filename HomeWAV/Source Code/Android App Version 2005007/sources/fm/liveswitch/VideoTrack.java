package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VideoTrack extends MediaTrack<IVideoOutput, IVideoOutputCollection, IVideoInput, IVideoInputCollection, IVideoElement, VideoSource, VideoSink, VideoPipe, VideoTrack, VideoBranch, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat> implements IVideoTrack, IMediaTrack {
    private ArrayList<IVideoElement> __elements;
    private Object __grabFrameLock;
    private Promise<VideoBuffer> __grabFramePromise;
    /* access modifiers changed from: private */
    public List<IAction1<VideoBuffer>> __onRawBuffer;
    /* access modifiers changed from: private */
    public List<IAction1<Size>> __onSize;
    private IVideoInput __rawSizeInput;
    private IVideoOutput __rawSizeOutput;
    private Size __size;
    private IAction1<VideoBuffer> _onRawBuffer;
    private IAction1<Size> _onSize;

    public String getLabel() {
        return "Video Track";
    }

    /* access modifiers changed from: protected */
    public void addElement(IVideoElement iVideoElement) {
        if (iVideoElement instanceof VideoBranch) {
            for (VideoTrack addOnRawBuffer : (VideoTrack[]) ((VideoBranch) iVideoElement).getTracks()) {
                addOnRawBuffer.addOnRawBuffer(new IActionDelegate1<VideoBuffer>() {
                    public String getId() {
                        return "fm.liveswitch.VideoTrack.track_OnRawBuffer";
                    }

                    public void invoke(VideoBuffer videoBuffer) {
                        VideoTrack.this.track_OnRawBuffer(videoBuffer);
                    }
                });
            }
        } else {
            if (iVideoElement instanceof IVideoInput) {
                IVideoInput iVideoInput = (IVideoInput) iVideoElement;
                VideoFormat videoFormat = (VideoFormat) iVideoInput.getInputFormat();
                if (videoFormat != null && videoFormat.getIsRaw() && this.__rawSizeInput == null && this.__rawSizeOutput == null) {
                    this.__rawSizeInput = iVideoInput;
                    iVideoInput.addOnProcessFrame(new IActionDelegate1<VideoFrame>() {
                        public String getId() {
                            return "fm.liveswitch.VideoTrack.rawSize_OnProcessFrame";
                        }

                        public void invoke(VideoFrame videoFrame) {
                            VideoTrack.this.rawSize_OnProcessFrame(videoFrame);
                        }
                    });
                }
            }
            if (iVideoElement instanceof IVideoOutput) {
                IVideoOutput iVideoOutput = (IVideoOutput) iVideoElement;
                VideoFormat videoFormat2 = (VideoFormat) iVideoOutput.getOutputFormat();
                if (videoFormat2 != null && videoFormat2.getIsRaw() && this.__rawSizeInput == null && this.__rawSizeOutput == null) {
                    this.__rawSizeOutput = iVideoOutput;
                    iVideoOutput.addOnRaiseFrame(new IActionDelegate1<VideoFrame>() {
                        public String getId() {
                            return "fm.liveswitch.VideoTrack.rawSize_OnProcessFrame";
                        }

                        public void invoke(VideoFrame videoFrame) {
                            VideoTrack.this.rawSize_OnProcessFrame(videoFrame);
                        }
                    });
                }
            }
        }
        if (ArrayListExtensions.getCount(this.__elements) == 0 && (iVideoElement instanceof VideoSource)) {
            ((VideoSource) iVideoElement).addOnStateChange(new IActionDelegate1<VideoSource>() {
                public String getId() {
                    return "fm.liveswitch.VideoTrack.source_OnStateChange";
                }

                public void invoke(VideoSource videoSource) {
                    VideoTrack.this.source_OnStateChange(videoSource);
                }
            });
        }
        this.__elements.add(iVideoElement);
    }

    /* access modifiers changed from: package-private */
    public void addOnRawBuffer(IAction1<VideoBuffer> iAction1) {
        if (iAction1 != null) {
            if (this._onRawBuffer == null) {
                this._onRawBuffer = new IAction1<VideoBuffer>() {
                    public void invoke(VideoBuffer videoBuffer) {
                        Iterator it = new ArrayList(VideoTrack.this.__onRawBuffer).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(videoBuffer);
                        }
                    }
                };
            }
            this.__onRawBuffer.add(iAction1);
        }
    }

    public void addOnSize(IAction1<Size> iAction1) {
        if (iAction1 != null) {
            if (this._onSize == null) {
                this._onSize = new IAction1<Size>() {
                    public void invoke(Size size) {
                        Iterator it = new ArrayList(VideoTrack.this.__onSize).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(size);
                        }
                    }
                };
            }
            this.__onSize.add(iAction1);
        }
    }

    /* access modifiers changed from: protected */
    public IVideoInput[] arrayFromInputs(ArrayList<IVideoInput> arrayList) {
        return (IVideoInput[]) arrayList.toArray(new IVideoInput[0]);
    }

    /* access modifiers changed from: protected */
    public IVideoOutput[] arrayFromOutputs(ArrayList<IVideoOutput> arrayList) {
        return (IVideoOutput[]) arrayList.toArray(new IVideoOutput[0]);
    }

    /* access modifiers changed from: protected */
    public VideoSink[] arrayFromSinks(ArrayList<VideoSink> arrayList) {
        return (VideoSink[]) arrayList.toArray(new VideoSink[0]);
    }

    /* access modifiers changed from: protected */
    public VideoBranch branchFromTracks(VideoTrack[] videoTrackArr) {
        return new VideoBranch(videoTrackArr);
    }

    public IVideoElement[] getElements() {
        return (IVideoElement[]) this.__elements.toArray(new IVideoElement[0]);
    }

    public Size getSize() {
        return this.__size;
    }

    public Future<VideoBuffer> grabFrame() {
        Promise<VideoBuffer> promise = new Promise<>();
        synchronized (this.__grabFrameLock) {
            if (this.__grabFramePromise == null) {
                this.__grabFramePromise = promise;
                return promise;
            }
            promise.reject(new Exception("Please wait for the previous frame to be grabbed before attempting another."));
            return promise;
        }
    }

    private void initialize(IVideoElement iVideoElement) {
        if (iVideoElement != null) {
            addElement(iVideoElement);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isBranch(IVideoElement iVideoElement) {
        return iVideoElement instanceof VideoBranch;
    }

    /* access modifiers changed from: protected */
    public boolean isInput(IVideoElement iVideoElement) {
        return iVideoElement instanceof IVideoInput;
    }

    /* access modifiers changed from: protected */
    public boolean isOutput(IVideoElement iVideoElement) {
        return iVideoElement instanceof IVideoOutput;
    }

    /* access modifiers changed from: protected */
    public boolean isPipe(IVideoElement iVideoElement) {
        return iVideoElement instanceof VideoPipe;
    }

    /* access modifiers changed from: protected */
    public boolean isSink(IVideoElement iVideoElement) {
        return iVideoElement instanceof VideoSink;
    }

    /* access modifiers changed from: protected */
    public boolean isSource(IVideoElement iVideoElement) {
        return iVideoElement instanceof VideoSource;
    }

    /* access modifiers changed from: protected */
    public boolean isStream(IVideoElement iVideoElement) {
        return iVideoElement instanceof VideoStream;
    }

    private void raiseRawBuffer(VideoBuffer videoBuffer) {
        Promise<VideoBuffer> promise;
        if (videoBuffer != null) {
            if (this.__grabFramePromise != null) {
                synchronized (this.__grabFrameLock) {
                    promise = this.__grabFramePromise;
                    if (promise != null) {
                        this.__grabFramePromise = null;
                    } else {
                        promise = null;
                    }
                }
                if (promise != null) {
                    promise.resolve(videoBuffer);
                }
            }
            IAction1<VideoBuffer> iAction1 = this._onRawBuffer;
            if (iAction1 != null) {
                iAction1.invoke(videoBuffer);
            }
        }
    }

    private void raiseSize(VideoBuffer videoBuffer) {
        if (videoBuffer != null) {
            Size size = new Size(videoBuffer.getWidth(), videoBuffer.getHeight());
            this.__size = size;
            IAction1<Size> iAction1 = this._onSize;
            if (iAction1 != null) {
                iAction1.invoke(size);
            }
        }
    }

    /* access modifiers changed from: private */
    public void rawSize_OnProcessFrame(VideoFrame videoFrame) {
        VideoBuffer videoBuffer = (VideoBuffer) videoFrame.getLastBuffer();
        raiseRawBuffer(videoBuffer);
        raiseSize(videoBuffer);
    }

    /* access modifiers changed from: package-private */
    public void removeOnRawBuffer(IAction1<VideoBuffer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRawBuffer, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRawBuffer.remove(iAction1);
        if (this.__onRawBuffer.size() == 0) {
            this._onRawBuffer = null;
        }
    }

    public void removeOnSize(IAction1<Size> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onSize, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onSize.remove(iAction1);
        if (this.__onSize.size() == 0) {
            this._onSize = null;
        }
    }

    /* access modifiers changed from: private */
    public void source_OnStateChange(VideoSource videoSource) {
        MediaSourceState state = videoSource.getState();
        if (state == MediaSourceState.Started) {
            super.raiseOnStarted();
        } else if (state == MediaSourceState.Stopped) {
            super.raiseOnStopped();
        }
    }

    /* access modifiers changed from: private */
    public void track_OnRawBuffer(VideoBuffer videoBuffer) {
        if (this.__rawSizeInput == null && this.__rawSizeOutput == null) {
            raiseRawBuffer(videoBuffer);
            raiseSize(videoBuffer);
        }
    }

    public VideoTrack() {
        this.__onRawBuffer = new ArrayList();
        this.__onSize = new ArrayList();
        this._onRawBuffer = null;
        this._onSize = null;
        this.__size = new Size(0, 0);
        this.__elements = new ArrayList<>();
        this.__grabFrameLock = new Object();
        initialize((IVideoElement) null);
    }

    public VideoTrack(IVideoElement iVideoElement) {
        this.__onRawBuffer = new ArrayList();
        this.__onSize = new ArrayList();
        this._onRawBuffer = null;
        this._onSize = null;
        this.__size = new Size(0, 0);
        this.__elements = new ArrayList<>();
        this.__grabFrameLock = new Object();
        initialize(iVideoElement);
    }

    public VideoTrack(VideoTrack[] videoTrackArr) {
        this.__onRawBuffer = new ArrayList();
        this.__onSize = new ArrayList();
        VideoBranch videoBranch = null;
        this._onRawBuffer = null;
        this._onSize = null;
        this.__size = new Size(0, 0);
        this.__elements = new ArrayList<>();
        this.__grabFrameLock = new Object();
        initialize(videoTrackArr != null ? new VideoBranch(videoTrackArr) : videoBranch);
    }
}
