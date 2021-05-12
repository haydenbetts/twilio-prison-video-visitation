package fm.liveswitch;

import fm.liveswitch.AudioTrack;
import fm.liveswitch.VideoTrack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Media<TAudioTrack extends AudioTrack, TVideoTrack extends VideoTrack> extends MediaBase<TAudioTrack, TVideoTrack> {
    private ArrayList<TAudioTrack> __audioTracks = createAudioTrackCollection();
    /* access modifiers changed from: private */
    public List<IAction0> __onAudioDestroyed = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<Double>> __onAudioLevel = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onVideoDestroyed = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<Size>> __onVideoSize = new ArrayList();
    private ArrayList<TVideoTrack> __videoTracks = createVideoTrackCollection();
    private boolean _isRecordingAudio;
    private boolean _isRecordingVideo;
    private IAction0 _onAudioDestroyed = null;
    private IAction1<Double> _onAudioLevel = null;
    private IAction0 _onVideoDestroyed = null;
    private IAction1<Size> _onVideoSize = null;

    /* access modifiers changed from: protected */
    public abstract TAudioTrack[] arrayFromAudioTracks(ArrayList<TAudioTrack> arrayList);

    /* access modifiers changed from: protected */
    public abstract TVideoTrack[] arrayFromVideoTracks(ArrayList<TVideoTrack> arrayList);

    /* access modifiers changed from: protected */
    public abstract ArrayList<TAudioTrack> createAudioTrackCollection();

    /* access modifiers changed from: protected */
    public abstract ArrayList<TVideoTrack> createVideoTrackCollection();

    /* access modifiers changed from: protected */
    public void addAudioTrack(TAudioTrack taudiotrack) {
        if (taudiotrack != null && ArrayListExtensions.getCount(this.__audioTracks) == 0) {
            taudiotrack.addOnDestroyed(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.Media<TAudioTrack,TVideoTrack>.audioTrack_OnDestroyed";
                }

                public void invoke() {
                    Media.this.audioTrack_OnDestroyed();
                }
            });
            taudiotrack.addOnPcmBuffer(new IActionDelegate1<AudioBuffer>() {
                public String getId() {
                    return "fm.liveswitch.Media<TAudioTrack,TVideoTrack>.audioTrack_OnPcmBuffer";
                }

                public void invoke(AudioBuffer audioBuffer) {
                    Media.this.audioTrack_OnPcmBuffer(audioBuffer);
                }
            });
        }
        this.__audioTracks.add(taudiotrack);
    }

    public void addOnAudioDestroyed(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onAudioDestroyed == null) {
                this._onAudioDestroyed = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(Media.this.__onAudioDestroyed).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onAudioDestroyed.add(iAction0);
        }
    }

    public void addOnAudioLevel(IAction1<Double> iAction1) {
        if (iAction1 != null) {
            if (this._onAudioLevel == null) {
                this._onAudioLevel = new IAction1<Double>() {
                    public void invoke(Double d) {
                        Iterator it = new ArrayList(Media.this.__onAudioLevel).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(d);
                        }
                    }
                };
            }
            this.__onAudioLevel.add(iAction1);
        }
    }

    public void addOnVideoDestroyed(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onVideoDestroyed == null) {
                this._onVideoDestroyed = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(Media.this.__onVideoDestroyed).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onVideoDestroyed.add(iAction0);
        }
    }

    public void addOnVideoSize(IAction1<Size> iAction1) {
        if (iAction1 != null) {
            if (this._onVideoSize == null) {
                this._onVideoSize = new IAction1<Size>() {
                    public void invoke(Size size) {
                        Iterator it = new ArrayList(Media.this.__onVideoSize).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(size);
                        }
                    }
                };
            }
            this.__onVideoSize.add(iAction1);
        }
    }

    /* access modifiers changed from: protected */
    public void addVideoTrack(TVideoTrack tvideotrack) {
        if (tvideotrack != null && ArrayListExtensions.getCount(this.__videoTracks) == 0) {
            tvideotrack.addOnDestroyed(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.Media<TAudioTrack,TVideoTrack>.videoTrack_OnDestroyed";
                }

                public void invoke() {
                    Media.this.videoTrack_OnDestroyed();
                }
            });
            tvideotrack.addOnSize(new IActionDelegate1<Size>() {
                public String getId() {
                    return "fm.liveswitch.Media<TAudioTrack,TVideoTrack>.videoTrack_OnSize";
                }

                public void invoke(Size size) {
                    Media.this.videoTrack_OnSize(size);
                }
            });
        }
        this.__videoTracks.add(tvideotrack);
    }

    /* access modifiers changed from: private */
    public void audioTrack_OnDestroyed() {
        IAction0 iAction0 = this._onAudioDestroyed;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }

    /* access modifiers changed from: private */
    public void audioTrack_OnPcmBuffer(AudioBuffer audioBuffer) {
        IAction1<Double> iAction1 = this._onAudioLevel;
        if (iAction1 != null) {
            iAction1.invoke(Double.valueOf(audioBuffer.calculateLevel()));
        }
    }

    public void destroy() {
        for (AudioTrack audioTrack : getAudioTracks()) {
            if (!audioTrack.getPersistent()) {
                audioTrack.destroy();
            }
        }
        for (VideoTrack videoTrack : getVideoTracks()) {
            if (!videoTrack.getPersistent()) {
                videoTrack.destroy();
            }
        }
    }

    public TAudioTrack[] getAudioTracks() {
        return arrayFromAudioTracks(this.__audioTracks);
    }

    public boolean getIsRecordingAudio() {
        return this._isRecordingAudio;
    }

    public boolean getIsRecordingVideo() {
        return this._isRecordingVideo;
    }

    public Size getVideoSize() {
        VideoTrack videoTrack = (VideoTrack) super.getVideoTrack();
        if (videoTrack != null) {
            return videoTrack.getSize();
        }
        return new Size(0, 0);
    }

    public TVideoTrack[] getVideoTracks() {
        return arrayFromVideoTracks(this.__videoTracks);
    }

    public Future<VideoBuffer> grabVideoFrame() {
        VideoTrack videoTrack = (VideoTrack) super.getVideoTrack();
        if (videoTrack != null) {
            return videoTrack.grabFrame();
        }
        Promise promise = new Promise();
        promise.reject(new Exception("No video track."));
        return promise;
    }

    /* access modifiers changed from: protected */
    public boolean removeAudioTrack(TAudioTrack taudiotrack) {
        if (taudiotrack != null) {
            taudiotrack.removeOnDestroyed(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.Media<TAudioTrack,TVideoTrack>.audioTrack_OnDestroyed";
                }

                public void invoke() {
                    Media.this.audioTrack_OnDestroyed();
                }
            });
            taudiotrack.removeOnPcmBuffer(new IActionDelegate1<AudioBuffer>() {
                public String getId() {
                    return "fm.liveswitch.Media<TAudioTrack,TVideoTrack>.audioTrack_OnPcmBuffer";
                }

                public void invoke(AudioBuffer audioBuffer) {
                    Media.this.audioTrack_OnPcmBuffer(audioBuffer);
                }
            });
        }
        return this.__audioTracks.remove(taudiotrack);
    }

    public void removeOnAudioDestroyed(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onAudioDestroyed, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onAudioDestroyed.remove(iAction0);
        if (this.__onAudioDestroyed.size() == 0) {
            this._onAudioDestroyed = null;
        }
    }

    public void removeOnAudioLevel(IAction1<Double> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onAudioLevel, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onAudioLevel.remove(iAction1);
        if (this.__onAudioLevel.size() == 0) {
            this._onAudioLevel = null;
        }
    }

    public void removeOnVideoDestroyed(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onVideoDestroyed, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onVideoDestroyed.remove(iAction0);
        if (this.__onVideoDestroyed.size() == 0) {
            this._onVideoDestroyed = null;
        }
    }

    public void removeOnVideoSize(IAction1<Size> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onVideoSize, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onVideoSize.remove(iAction1);
        if (this.__onVideoSize.size() == 0) {
            this._onVideoSize = null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean removeVideoTrack(TVideoTrack tvideotrack) {
        if (tvideotrack != null) {
            tvideotrack.removeOnDestroyed(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.Media<TAudioTrack,TVideoTrack>.videoTrack_OnDestroyed";
                }

                public void invoke() {
                    Media.this.videoTrack_OnDestroyed();
                }
            });
            tvideotrack.removeOnSize(new IActionDelegate1<Size>() {
                public String getId() {
                    return "fm.liveswitch.Media<TAudioTrack,TVideoTrack>.videoTrack_OnSize";
                }

                public void invoke(Size size) {
                    Media.this.videoTrack_OnSize(size);
                }
            });
        }
        return this.__videoTracks.remove(tvideotrack);
    }

    /* access modifiers changed from: protected */
    public void setIsRecordingAudio(boolean z) {
        this._isRecordingAudio = z;
    }

    /* access modifiers changed from: protected */
    public void setIsRecordingVideo(boolean z) {
        this._isRecordingVideo = z;
    }

    /* access modifiers changed from: private */
    public void videoTrack_OnDestroyed() {
        IAction0 iAction0 = this._onVideoDestroyed;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }

    /* access modifiers changed from: private */
    public void videoTrack_OnSize(Size size) {
        IAction1<Size> iAction1 = this._onVideoSize;
        if (iAction1 != null) {
            iAction1.invoke(size);
        }
    }
}
