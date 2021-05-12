package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocalMedia extends LocalMediaBase<LocalMedia, AudioTrack, VideoTrack> implements ILocalMedia<LocalMedia, AudioTrack, VideoTrack>, IMedia<AudioTrack, VideoTrack> {
    /* access modifiers changed from: private */
    public List<IAction0> __onAudioStarted = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onAudioStopped = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onVideoStarted = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onVideoStopped = new ArrayList();
    private IAction0 _onAudioStarted = null;
    private IAction0 _onAudioStopped = null;
    private IAction0 _onVideoStarted = null;
    private IAction0 _onVideoStopped = null;

    /* access modifiers changed from: protected */
    public void addAudioTrack(AudioTrack audioTrack) {
        if (audioTrack != null && ArrayExtensions.getLength((Object[]) getAudioTracks()) == 0) {
            audioTrack.addOnStarted(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.LocalMedia.audioTrack_OnStarted";
                }

                public void invoke() {
                    LocalMedia.this.audioTrack_OnStarted();
                }
            });
            audioTrack.addOnStopped(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.LocalMedia.audioTrack_OnStopped";
                }

                public void invoke() {
                    LocalMedia.this.audioTrack_OnStopped();
                }
            });
        }
        super.addAudioTrack(audioTrack);
    }

    public void addOnAudioStarted(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onAudioStarted == null) {
                this._onAudioStarted = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(LocalMedia.this.__onAudioStarted).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onAudioStarted.add(iAction0);
        }
    }

    public void addOnAudioStopped(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onAudioStopped == null) {
                this._onAudioStopped = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(LocalMedia.this.__onAudioStopped).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onAudioStopped.add(iAction0);
        }
    }

    public void addOnVideoStarted(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onVideoStarted == null) {
                this._onVideoStarted = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(LocalMedia.this.__onVideoStarted).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onVideoStarted.add(iAction0);
        }
    }

    public void addOnVideoStopped(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onVideoStopped == null) {
                this._onVideoStopped = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(LocalMedia.this.__onVideoStopped).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onVideoStopped.add(iAction0);
        }
    }

    /* access modifiers changed from: protected */
    public void addVideoTrack(VideoTrack videoTrack) {
        if (videoTrack != null && ArrayExtensions.getLength((Object[]) getVideoTracks()) == 0) {
            videoTrack.addOnStarted(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.LocalMedia.videoTrack_OnStarted";
                }

                public void invoke() {
                    LocalMedia.this.videoTrack_OnStarted();
                }
            });
            videoTrack.addOnStopped(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.LocalMedia.videoTrack_OnStopped";
                }

                public void invoke() {
                    LocalMedia.this.videoTrack_OnStopped();
                }
            });
        }
        super.addVideoTrack(videoTrack);
    }

    /* access modifiers changed from: protected */
    public AudioTrack[] arrayFromAudioTracks(ArrayList<AudioTrack> arrayList) {
        return (AudioTrack[]) arrayList.toArray(new AudioTrack[0]);
    }

    /* access modifiers changed from: protected */
    public VideoTrack[] arrayFromVideoTracks(ArrayList<VideoTrack> arrayList) {
        return (VideoTrack[]) arrayList.toArray(new VideoTrack[0]);
    }

    /* access modifiers changed from: private */
    public void audioTrack_OnStarted() {
        IAction0 iAction0 = this._onAudioStarted;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }

    /* access modifiers changed from: private */
    public void audioTrack_OnStopped() {
        IAction0 iAction0 = this._onAudioStopped;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }

    public Future<Object> changeAudioSourceInput(SourceInput sourceInput) {
        AudioTrack audioTrack = (AudioTrack) super.getAudioTrack();
        if (audioTrack != null) {
            return audioTrack.changeSourceInput(sourceInput);
        }
        Promise promise = new Promise();
        promise.reject(new Exception("No audio track."));
        return promise;
    }

    public Future<Object> changeVideoSourceInput(SourceInput sourceInput) {
        VideoTrack videoTrack = (VideoTrack) super.getVideoTrack();
        if (videoTrack != null) {
            return videoTrack.changeSourceInput(sourceInput);
        }
        Promise promise = new Promise();
        promise.reject(new Exception("No video track."));
        return promise;
    }

    /* access modifiers changed from: protected */
    public ArrayList<AudioTrack> createAudioTrackCollection() {
        return new ArrayList<>();
    }

    /* access modifiers changed from: protected */
    public ArrayList<VideoTrack> createVideoTrackCollection() {
        return new ArrayList<>();
    }

    /* access modifiers changed from: protected */
    public AudioEncodingConfig[] doGetAudioEncodings() {
        throw new RuntimeException(new Exception("Implement DoGetAudioEncodings in your LocalMedia subclass."));
    }

    /* access modifiers changed from: protected */
    public VideoEncodingConfig[] doGetVideoEncodings() {
        throw new RuntimeException(new Exception("Implement DoGetVideoEncodings in your LocalMedia subclass."));
    }

    /* access modifiers changed from: protected */
    public void doSetAudioEncodings(AudioEncodingConfig[] audioEncodingConfigArr) {
        throw new RuntimeException(new Exception("Implement DoSetAudioEncodings in your LocalMedia subclass."));
    }

    /* access modifiers changed from: protected */
    public void doSetVideoEncodings(VideoEncodingConfig[] videoEncodingConfigArr) {
        throw new RuntimeException(new Exception("Implement DoSetVideoEncodings in your LocalMedia subclass."));
    }

    /* access modifiers changed from: protected */
    public Future<LocalMedia> doStart() {
        Promise promise = new Promise();
        MediaSourceBase[] mediaSources = getMediaSources();
        if (ArrayExtensions.getLength((Object[]) mediaSources) > 0) {
            doStartSource(promise, mediaSources, 0);
            return promise;
        }
        promise.resolve(this);
        return promise;
    }

    /* access modifiers changed from: private */
    public void doStartSource(final Promise<LocalMedia> promise, final MediaSourceBase[] mediaSourceBaseArr, final int i) {
        if (i < ArrayExtensions.getLength((Object[]) mediaSourceBaseArr)) {
            mediaSourceBaseArr[i].start().then(new IAction1<Object>() {
                public void invoke(Object obj) {
                    LocalMedia.this.doStartSource(promise, mediaSourceBaseArr, i + 1);
                }
            }, (IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    LocalMedia.this.abortStart(promise, exc);
                }
            });
            return;
        }
        promise.resolve(this);
    }

    /* access modifiers changed from: protected */
    public Future<LocalMedia> doStop() {
        Promise promise = new Promise();
        MediaSourceBase[] mediaSources = getMediaSources();
        if (ArrayExtensions.getLength((Object[]) mediaSources) > 0) {
            doStopSource(promise, mediaSources, 0);
            return promise;
        }
        promise.resolve(this);
        return promise;
    }

    /* access modifiers changed from: private */
    public void doStopSource(final Promise<LocalMedia> promise, final MediaSourceBase[] mediaSourceBaseArr, final int i) {
        if (i < ArrayExtensions.getLength((Object[]) mediaSourceBaseArr)) {
            mediaSourceBaseArr[i].stop().then(new IAction1<Object>() {
                public void invoke(Object obj) {
                    LocalMedia.this.doStopSource(promise, mediaSourceBaseArr, i + 1);
                }
            }, (IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    promise.reject(exc);
                }
            });
            return;
        }
        promise.resolve(this);
    }

    public AudioSource getAudioSource() {
        AudioSource[] audioSources = getAudioSources();
        if (audioSources == null || ArrayExtensions.getLength((Object[]) audioSources) == 0) {
            return null;
        }
        return audioSources[0];
    }

    public SourceInput getAudioSourceInput() {
        AudioTrack audioTrack = (AudioTrack) super.getAudioTrack();
        if (audioTrack != null) {
            return audioTrack.getSourceInput();
        }
        return null;
    }

    public Future<SourceInput[]> getAudioSourceInputs() {
        AudioTrack audioTrack = (AudioTrack) super.getAudioTrack();
        if (audioTrack != null) {
            return audioTrack.getSourceInputs();
        }
        Promise promise = new Promise();
        promise.resolve(new SourceInput[0]);
        return promise;
    }

    public AudioSource[] getAudioSources() {
        ArrayList arrayList = new ArrayList();
        for (AudioTrack audioTrack : getAudioTracks()) {
            if (audioTrack.getSource() != null) {
                arrayList.add(audioTrack.getSource());
            }
        }
        return (AudioSource[]) arrayList.toArray(new AudioSource[0]);
    }

    public MediaSourceBase[] getMediaSources() {
        ArrayList arrayList = new ArrayList();
        ArrayListExtensions.addRange(arrayList, (T[]) getAudioSources());
        ArrayListExtensions.addRange(arrayList, (T[]) getVideoSources());
        return (MediaSourceBase[]) arrayList.toArray(new MediaSourceBase[0]);
    }

    public VideoSource getVideoSource() {
        VideoSource[] videoSources = getVideoSources();
        if (videoSources == null || ArrayExtensions.getLength((Object[]) videoSources) == 0) {
            return null;
        }
        return videoSources[0];
    }

    public SourceInput getVideoSourceInput() {
        VideoTrack videoTrack = (VideoTrack) super.getVideoTrack();
        if (videoTrack != null) {
            return videoTrack.getSourceInput();
        }
        return null;
    }

    public Future<SourceInput[]> getVideoSourceInputs() {
        VideoTrack videoTrack = (VideoTrack) super.getVideoTrack();
        if (videoTrack != null) {
            return videoTrack.getSourceInputs();
        }
        Promise promise = new Promise();
        promise.resolve(new SourceInput[0]);
        return promise;
    }

    public VideoSource[] getVideoSources() {
        ArrayList arrayList = new ArrayList();
        for (VideoTrack videoTrack : getVideoTracks()) {
            if (videoTrack.getSource() != null) {
                arrayList.add(videoTrack.getSource());
            }
        }
        return (VideoSource[]) arrayList.toArray(new VideoSource[0]);
    }

    /* access modifiers changed from: protected */
    public boolean removeAudioTrack(AudioTrack audioTrack) {
        if (audioTrack != null) {
            audioTrack.removeOnStarted(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.LocalMedia.audioTrack_OnStarted";
                }

                public void invoke() {
                    LocalMedia.this.audioTrack_OnStarted();
                }
            });
            audioTrack.removeOnStopped(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.LocalMedia.audioTrack_OnStopped";
                }

                public void invoke() {
                    LocalMedia.this.audioTrack_OnStopped();
                }
            });
        }
        return super.removeAudioTrack(audioTrack);
    }

    public void removeOnAudioStarted(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onAudioStarted, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onAudioStarted.remove(iAction0);
        if (this.__onAudioStarted.size() == 0) {
            this._onAudioStarted = null;
        }
    }

    public void removeOnAudioStopped(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onAudioStopped, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onAudioStopped.remove(iAction0);
        if (this.__onAudioStopped.size() == 0) {
            this._onAudioStopped = null;
        }
    }

    public void removeOnVideoStarted(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onVideoStarted, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onVideoStarted.remove(iAction0);
        if (this.__onVideoStarted.size() == 0) {
            this._onVideoStarted = null;
        }
    }

    public void removeOnVideoStopped(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onVideoStopped, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onVideoStopped.remove(iAction0);
        if (this.__onVideoStopped.size() == 0) {
            this._onVideoStopped = null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean removeVideoTrack(VideoTrack videoTrack) {
        if (videoTrack != null) {
            videoTrack.removeOnStarted(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.LocalMedia.videoTrack_OnStarted";
                }

                public void invoke() {
                    LocalMedia.this.videoTrack_OnStarted();
                }
            });
            videoTrack.removeOnStopped(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.LocalMedia.videoTrack_OnStopped";
                }

                public void invoke() {
                    LocalMedia.this.videoTrack_OnStopped();
                }
            });
        }
        return super.removeVideoTrack(videoTrack);
    }

    public void setAudioSourceInput(SourceInput sourceInput) {
        AudioTrack audioTrack = (AudioTrack) super.getAudioTrack();
        if (audioTrack != null) {
            audioTrack.setSourceInput(sourceInput);
        }
    }

    public void setVideoSourceInput(SourceInput sourceInput) {
        VideoTrack videoTrack = (VideoTrack) super.getVideoTrack();
        if (videoTrack != null) {
            videoTrack.setSourceInput(sourceInput);
        }
    }

    /* access modifiers changed from: private */
    public void videoTrack_OnStarted() {
        IAction0 iAction0 = this._onVideoStarted;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }

    /* access modifiers changed from: private */
    public void videoTrack_OnStopped() {
        IAction0 iAction0 = this._onVideoStopped;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }
}
