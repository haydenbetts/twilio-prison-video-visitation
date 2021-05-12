package fm.liveswitch;

import fm.liveswitch.AudioTrack;
import fm.liveswitch.LocalMediaBase;
import fm.liveswitch.VideoTrack;

public abstract class LocalMediaBase<TLocalMedia extends LocalMediaBase<TLocalMedia, TAudioTrack, TVideoTrack>, TAudioTrack extends AudioTrack, TVideoTrack extends VideoTrack> extends Media<TAudioTrack, TVideoTrack> {
    private static double __defaultVideoBitsPerPixel = 0.05d;
    private boolean __audioEncodingsSet = false;
    private Object __audioEncodingsSetLock = new Object();
    private AudioSimulcastConfig __audioSimulcast;
    /* access modifiers changed from: private */
    public Object __stateLock = new Object();
    private boolean __videoEncodingsSet = false;
    private Object __videoEncodingsSetLock = new Object();
    private VideoSimulcastConfig __videoSimulcast;
    private LocalMediaState _state;

    /* access modifiers changed from: protected */
    public abstract AudioEncodingConfig[] doGetAudioEncodings();

    /* access modifiers changed from: protected */
    public abstract VideoEncodingConfig[] doGetVideoEncodings();

    /* access modifiers changed from: protected */
    public abstract void doSetAudioEncodings(AudioEncodingConfig[] audioEncodingConfigArr);

    /* access modifiers changed from: protected */
    public abstract void doSetVideoEncodings(VideoEncodingConfig[] videoEncodingConfigArr);

    /* access modifiers changed from: protected */
    public abstract Future<TLocalMedia> doStart();

    /* access modifiers changed from: protected */
    public abstract Future<TLocalMedia> doStop();

    /* access modifiers changed from: protected */
    public void abortStart(final Promise<TLocalMedia> promise, final Exception exc) {
        Log.debug(StringExtensions.format("Local media failed to start and is in a partial state. Stopping...", new Object[0]));
        doStop().then(new IAction1<TLocalMedia>() {
            public void invoke(TLocalMedia tlocalmedia) {
                Log.debug(StringExtensions.format("Local media has successfully stopped.", new Object[0]));
                synchronized (LocalMediaBase.this.__stateLock) {
                    LocalMediaBase.this.setState(LocalMediaState.Stopped);
                    promise.reject(exc);
                }
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                Log.debug(StringExtensions.format("Local media did not stop successfully.", new Object[0]));
                synchronized (LocalMediaBase.this.__stateLock) {
                    LocalMediaBase.this.setState(LocalMediaState.Stopped);
                    promise.reject(exc);
                }
            }
        });
    }

    public AudioEncodingConfig getAudioEncoding() {
        return (AudioEncodingConfig) Utility.firstOrDefault((T[]) getAudioEncodings());
    }

    public AudioEncodingConfig[] getAudioEncodings() {
        return doGetAudioEncodings();
    }

    public boolean getAudioSimulcastDisabled() {
        return this.__audioSimulcast.getDisabled();
    }

    public int getAudioSimulcastEncodingCount() {
        return this.__audioSimulcast.getEncodingCount();
    }

    public int getAudioSimulcastPreferredBitrate() {
        return this.__audioSimulcast.getPreferredBitrate();
    }

    public static double getDefaultVideoBitsPerPixel() {
        return __defaultVideoBitsPerPixel;
    }

    public LocalMediaState getState() {
        return this._state;
    }

    public VideoEncodingConfig getVideoEncoding() {
        return (VideoEncodingConfig) Utility.firstOrDefault((T[]) getVideoEncodings());
    }

    public VideoEncodingConfig[] getVideoEncodings() {
        return doGetVideoEncodings();
    }

    public double getVideoSimulcastBitsPerPixel() {
        return this.__videoSimulcast.getBitsPerPixel();
    }

    public VideoDegradationPreference getVideoSimulcastDegradationPreference() {
        return this.__videoSimulcast.getDegradationPreference();
    }

    public boolean getVideoSimulcastDisabled() {
        return this.__videoSimulcast.getDisabled();
    }

    public int getVideoSimulcastEncodingCount() {
        return this.__videoSimulcast.getEncodingCount();
    }

    public int getVideoSimulcastPreferredBitrate() {
        return this.__videoSimulcast.getPreferredBitrate();
    }

    private void initializeSimulcastConfigs() {
        AudioSimulcastConfig audioSimulcastConfig = new AudioSimulcastConfig(1, 32);
        audioSimulcastConfig.setDisabled(true);
        this.__audioSimulcast = audioSimulcastConfig;
        VideoSimulcastConfig videoSimulcastConfig = new VideoSimulcastConfig(2, 1024, getDefaultVideoBitsPerPixel());
        videoSimulcastConfig.setDisabled(false);
        this.__videoSimulcast = videoSimulcastConfig;
    }

    public LocalMediaBase() {
        setState(LocalMediaState.New);
        initializeSimulcastConfigs();
    }

    /* access modifiers changed from: protected */
    public void lockAudioEncodings() {
        if (getAudioEncodings() == null || ArrayExtensions.getLength((Object[]) getAudioEncodings()) == 0) {
            setAudioEncodings(this.__audioSimulcast.getEncodingConfigs());
        }
    }

    /* access modifiers changed from: protected */
    public void lockVideoEncodings() {
        lockVideoEncodings(VideoType.Unknown);
    }

    /* access modifiers changed from: protected */
    public void lockVideoEncodings(VideoType videoType) {
        lockVideoEncodings(videoType, -1, -1, -1.0d);
    }

    /* access modifiers changed from: protected */
    public void lockVideoEncodings(VideoType videoType, int i, int i2, double d) {
        if (getVideoEncodings() == null || ArrayExtensions.getLength((Object[]) getVideoEncodings()) == 0) {
            setVideoEncodings(this.__videoSimulcast.getEncodingConfigs(videoType, i, i2, d));
        }
    }

    public void setAudioEncodings(AudioEncodingConfig[] audioEncodingConfigArr) {
        if (audioEncodingConfigArr == null) {
            audioEncodingConfigArr = new AudioEncodingConfig[]{new AudioEncodingConfig()};
        }
        if (ArrayExtensions.getLength((Object[]) audioEncodingConfigArr) != 0) {
            AudioEncodingConfig[] audioEncodings = getAudioEncodings();
            synchronized (this.__audioEncodingsSetLock) {
                if (this.__audioEncodingsSet && audioEncodings != null) {
                    if (ArrayExtensions.getLength((Object[]) audioEncodings) != ArrayExtensions.getLength((Object[]) audioEncodingConfigArr)) {
                        throw new RuntimeException(new Exception("The number of audio encodings cannot be changed once set."));
                    }
                }
                this.__audioEncodingsSet = true;
            }
            doSetAudioEncodings(audioEncodingConfigArr);
            return;
        }
        throw new RuntimeException(new Exception("Audio encodings cannot be empty."));
    }

    public void setAudioSimulcastDisabled(boolean z) {
        this.__audioSimulcast.setDisabled(z);
    }

    public void setAudioSimulcastEncodingCount(int i) {
        this.__audioSimulcast.setEncodingCount(i);
    }

    public void setAudioSimulcastPreferredBitrate(int i) {
        this.__audioSimulcast.setPreferredBitrate(i);
    }

    public static void setDefaultVideoBitsPerPixel(double d) {
        if (d >= 0.0d) {
            __defaultVideoBitsPerPixel = d;
            return;
        }
        throw new RuntimeException(new Exception("Default bits-per-pixel must be greater than or equal to zero."));
    }

    /* access modifiers changed from: private */
    public void setState(LocalMediaState localMediaState) {
        this._state = localMediaState;
    }

    public void setVideoEncodings(VideoEncodingConfig[] videoEncodingConfigArr) {
        if (videoEncodingConfigArr == null) {
            throw new RuntimeException(new Exception("Video encodings cannot be null."));
        } else if (ArrayExtensions.getLength((Object[]) videoEncodingConfigArr) != 0) {
            VideoEncodingConfig[] videoEncodings = getVideoEncodings();
            synchronized (this.__videoEncodingsSetLock) {
                if (this.__videoEncodingsSet && videoEncodings != null) {
                    if (ArrayExtensions.getLength((Object[]) videoEncodings) != ArrayExtensions.getLength((Object[]) videoEncodingConfigArr)) {
                        throw new RuntimeException(new Exception("The number of video encodings cannot be changed once set."));
                    }
                }
                this.__videoEncodingsSet = true;
            }
            doSetVideoEncodings(videoEncodingConfigArr);
        } else {
            throw new RuntimeException(new Exception("Video encodings cannot be empty."));
        }
    }

    public void setVideoSimulcastBitsPerPixel(double d) {
        this.__videoSimulcast.setBitsPerPixel(d);
    }

    public void setVideoSimulcastDegradationPreference(VideoDegradationPreference videoDegradationPreference) {
        this.__videoSimulcast.setDegradationPreference(videoDegradationPreference);
    }

    public void setVideoSimulcastDisabled(boolean z) {
        this.__videoSimulcast.setDisabled(z);
    }

    public void setVideoSimulcastEncodingCount(int i) {
        this.__videoSimulcast.setEncodingCount(i);
    }

    public void setVideoSimulcastPreferredBitrate(int i) {
        this.__videoSimulcast.setPreferredBitrate(i);
    }

    public Future<TLocalMedia> start() {
        return startInternal(new Promise());
    }

    private Future<TLocalMedia> startInternal(final Promise<TLocalMedia> promise) {
        synchronized (this.__stateLock) {
            LocalMediaState state = getState();
            if (state == LocalMediaState.Starting) {
                promise.reject(new Exception("Local media cannot be started while it is being started on a different thread."));
                return promise;
            } else if (state == LocalMediaState.Started) {
                promise.resolve(this);
                return promise;
            } else if (state == LocalMediaState.Stopping) {
                promise.reject(new Exception("Local media cannot be started while it is being stopped."));
                return promise;
            } else if (state == LocalMediaState.Destroying) {
                promise.reject(new Exception("Local media cannot be started while it is being destroyed."));
                return promise;
            } else if (state == LocalMediaState.Destroyed) {
                promise.reject(new Exception("Local media cannot be started while it is destroyed."));
                return promise;
            } else {
                setState(LocalMediaState.Starting);
                Log.debug(StringExtensions.format("Local media is being started.", new Object[0]));
                doStart().then(new IAction1<TLocalMedia>() {
                    public void invoke(TLocalMedia tlocalmedia) {
                        Log.debug(StringExtensions.format("Local media has successfully started.", new Object[0]));
                        synchronized (LocalMediaBase.this.__stateLock) {
                            LocalMediaBase.this.setState(LocalMediaState.Started);
                            promise.resolve(tlocalmedia);
                        }
                    }
                }, (IAction1<Exception>) new IAction1<Exception>() {
                    public void invoke(Exception exc) {
                        Log.debug(StringExtensions.format("Local media did not start successfully.", new Object[0]));
                        synchronized (LocalMediaBase.this.__stateLock) {
                            LocalMediaBase.this.setState(LocalMediaState.Stopped);
                            promise.reject(exc);
                        }
                    }
                });
                return promise;
            }
        }
    }

    public Future<TLocalMedia> stop() {
        return stopInternal(new Promise());
    }

    private Future<TLocalMedia> stopInternal(final Promise<TLocalMedia> promise) {
        synchronized (this.__stateLock) {
            LocalMediaState state = getState();
            if (state == LocalMediaState.New) {
                promise.resolve(this);
                return promise;
            } else if (state == LocalMediaState.Starting) {
                promise.reject(new Exception("Local media cannot be stopped while it is being started."));
                return promise;
            } else if (state == LocalMediaState.Stopping) {
                promise.reject(new Exception("Local media cannot be stopped while it is being stopped on a different thread."));
                return promise;
            } else if (state == LocalMediaState.Stopped) {
                promise.resolve(this);
                return promise;
            } else if (state == LocalMediaState.Destroying) {
                promise.reject(new Exception("Local media cannot be stopped while it is being destroyed."));
                return promise;
            } else if (state == LocalMediaState.Destroyed) {
                promise.reject(new Exception("Local media cannot be stopped while it is destroyed."));
                return promise;
            } else {
                setState(LocalMediaState.Stopping);
                Log.debug(StringExtensions.format("Local media is being stopped.", new Object[0]));
                doStop().then(new IAction1<TLocalMedia>() {
                    public void invoke(TLocalMedia tlocalmedia) {
                        Log.debug(StringExtensions.format("Local media has successfully stopped.", new Object[0]));
                        synchronized (LocalMediaBase.this.__stateLock) {
                            LocalMediaBase.this.setState(LocalMediaState.Stopped);
                            promise.resolve(tlocalmedia);
                        }
                    }
                }, (IAction1<Exception>) new IAction1<Exception>() {
                    public void invoke(Exception exc) {
                        Log.debug(StringExtensions.format("Local media did not stop successfully.", new Object[0]));
                        synchronized (LocalMediaBase.this.__stateLock) {
                            LocalMediaBase.this.setState(LocalMediaState.Started);
                            promise.reject(exc);
                        }
                    }
                });
                return promise;
            }
        }
    }
}
