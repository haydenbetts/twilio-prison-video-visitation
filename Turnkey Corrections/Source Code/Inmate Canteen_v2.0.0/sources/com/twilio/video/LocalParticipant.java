package com.twilio.video;

import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.twilio.video.LocalParticipant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class LocalParticipant implements Participant {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(LocalParticipant.class);
    /* access modifiers changed from: private */
    public final List<AudioTrackPublication> audioTrackPublications;
    /* access modifiers changed from: private */
    public final List<DataTrackPublication> dataTrackPublications;
    /* access modifiers changed from: private */
    public final Handler handler;
    private final String identity;
    /* access modifiers changed from: private */
    public final AtomicReference<Listener> listenerReference = new AtomicReference<>((Object) null);
    /* access modifiers changed from: private */
    public final List<LocalAudioTrackPublication> localAudioTrackPublications;
    /* access modifiers changed from: private */
    public final List<LocalDataTrackPublication> localDataTrackPublications;
    final Listener localParticipantListenerProxy = new Listener() {
        public void onAudioTrackPublished(@NonNull LocalParticipant localParticipant, @NonNull LocalAudioTrackPublication localAudioTrackPublication) {
            checkPublishedCallback(localParticipant, localAudioTrackPublication, "onAudioTrackPublished");
            LocalParticipant.this.handler.post(new Runnable(localAudioTrackPublication, localParticipant) {
                private final /* synthetic */ LocalAudioTrackPublication f$1;
                private final /* synthetic */ LocalParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    LocalParticipant.AnonymousClass1.lambda$onAudioTrackPublished$0(LocalParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onAudioTrackPublished$0(AnonymousClass1 r2, LocalAudioTrackPublication localAudioTrackPublication, LocalParticipant localParticipant) {
            LocalParticipant.logger.d("onAudioTrackPublished");
            LocalParticipant.this.audioTrackPublications.add(localAudioTrackPublication);
            LocalParticipant.this.localAudioTrackPublications.add(localAudioTrackPublication);
            Listener listener = (Listener) LocalParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onAudioTrackPublished(localParticipant, localAudioTrackPublication);
            }
        }

        public void onAudioTrackPublicationFailed(@NonNull LocalParticipant localParticipant, @NonNull LocalAudioTrack localAudioTrack, @NonNull TwilioException twilioException) {
            checkPublicationFailedCallback(localParticipant, localAudioTrack, twilioException, "onAudioTrackPublicationFailed");
            LocalParticipant.this.handler.post(new Runnable(localParticipant, localAudioTrack, twilioException) {
                private final /* synthetic */ LocalParticipant f$1;
                private final /* synthetic */ LocalAudioTrack f$2;
                private final /* synthetic */ TwilioException f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    LocalParticipant.AnonymousClass1.lambda$onAudioTrackPublicationFailed$1(LocalParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onAudioTrackPublicationFailed$1(AnonymousClass1 r2, LocalParticipant localParticipant, LocalAudioTrack localAudioTrack, TwilioException twilioException) {
            LocalParticipant.logger.d("onAudioTrackPublicationFailed");
            Listener listener = (Listener) LocalParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onAudioTrackPublicationFailed(localParticipant, localAudioTrack, twilioException);
            }
        }

        public void onVideoTrackPublished(@NonNull LocalParticipant localParticipant, @NonNull LocalVideoTrackPublication localVideoTrackPublication) {
            checkPublishedCallback(localParticipant, localVideoTrackPublication, "onVideoTrackPublished");
            LocalParticipant.this.handler.post(new Runnable(localVideoTrackPublication, localParticipant) {
                private final /* synthetic */ LocalVideoTrackPublication f$1;
                private final /* synthetic */ LocalParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    LocalParticipant.AnonymousClass1.lambda$onVideoTrackPublished$2(LocalParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onVideoTrackPublished$2(AnonymousClass1 r2, LocalVideoTrackPublication localVideoTrackPublication, LocalParticipant localParticipant) {
            LocalParticipant.logger.d("onAudioTrackPublished");
            LocalParticipant.this.videoTrackPublications.add(localVideoTrackPublication);
            LocalParticipant.this.localVideoTrackPublications.add(localVideoTrackPublication);
            Listener listener = (Listener) LocalParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onVideoTrackPublished(localParticipant, localVideoTrackPublication);
            }
        }

        public void onVideoTrackPublicationFailed(@NonNull LocalParticipant localParticipant, @NonNull LocalVideoTrack localVideoTrack, @NonNull TwilioException twilioException) {
            checkPublicationFailedCallback(localParticipant, localVideoTrack, twilioException, "onVideoTrackPublicationFailed");
            LocalParticipant.this.handler.post(new Runnable(localParticipant, localVideoTrack, twilioException) {
                private final /* synthetic */ LocalParticipant f$1;
                private final /* synthetic */ LocalVideoTrack f$2;
                private final /* synthetic */ TwilioException f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    LocalParticipant.AnonymousClass1.lambda$onVideoTrackPublicationFailed$3(LocalParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onVideoTrackPublicationFailed$3(AnonymousClass1 r2, LocalParticipant localParticipant, LocalVideoTrack localVideoTrack, TwilioException twilioException) {
            LocalParticipant.logger.d("onVideoTrackPublicationFailed");
            Listener listener = (Listener) LocalParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onVideoTrackPublicationFailed(localParticipant, localVideoTrack, twilioException);
            }
        }

        public void onDataTrackPublished(@NonNull LocalParticipant localParticipant, @NonNull LocalDataTrackPublication localDataTrackPublication) {
            checkPublishedCallback(localParticipant, localDataTrackPublication, "onDataTrackPublished");
            LocalParticipant.this.handler.post(new Runnable(localDataTrackPublication, localParticipant) {
                private final /* synthetic */ LocalDataTrackPublication f$1;
                private final /* synthetic */ LocalParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    LocalParticipant.AnonymousClass1.lambda$onDataTrackPublished$4(LocalParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onDataTrackPublished$4(AnonymousClass1 r2, LocalDataTrackPublication localDataTrackPublication, LocalParticipant localParticipant) {
            LocalParticipant.logger.d("onDataTrackPublished");
            LocalParticipant.this.dataTrackPublications.add(localDataTrackPublication);
            LocalParticipant.this.localDataTrackPublications.add(localDataTrackPublication);
            Listener listener = (Listener) LocalParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onDataTrackPublished(localParticipant, localDataTrackPublication);
            }
        }

        public void onDataTrackPublicationFailed(@NonNull LocalParticipant localParticipant, @NonNull LocalDataTrack localDataTrack, @NonNull TwilioException twilioException) {
            checkPublicationFailedCallback(localParticipant, localDataTrack, twilioException, "onDataTrackPublicationFailed");
            LocalParticipant.this.handler.post(new Runnable(localParticipant, localDataTrack, twilioException) {
                private final /* synthetic */ LocalParticipant f$1;
                private final /* synthetic */ LocalDataTrack f$2;
                private final /* synthetic */ TwilioException f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    LocalParticipant.AnonymousClass1.lambda$onDataTrackPublicationFailed$5(LocalParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onDataTrackPublicationFailed$5(AnonymousClass1 r2, LocalParticipant localParticipant, LocalDataTrack localDataTrack, TwilioException twilioException) {
            LocalParticipant.logger.d("onDataTrackPublicationFailed");
            Listener listener = (Listener) LocalParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onDataTrackPublicationFailed(localParticipant, localDataTrack, twilioException);
            }
        }

        public void onNetworkQualityLevelChanged(@NonNull LocalParticipant localParticipant, @NonNull NetworkQualityLevel networkQualityLevel) {
            LocalParticipant.this.handler.post(new Runnable(localParticipant, networkQualityLevel) {
                private final /* synthetic */ LocalParticipant f$1;
                private final /* synthetic */ NetworkQualityLevel f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    LocalParticipant.AnonymousClass1.lambda$onNetworkQualityLevelChanged$6(LocalParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onNetworkQualityLevelChanged$6(AnonymousClass1 r2, LocalParticipant localParticipant, NetworkQualityLevel networkQualityLevel) {
            LocalParticipant.logger.d("onNetworkQualityLevelChanged");
            Listener listener = (Listener) LocalParticipant.this.listenerReference.get();
            NetworkQualityLevel unused = localParticipant.networkQualityLevel = networkQualityLevel;
            if (listener != null) {
                listener.onNetworkQualityLevelChanged(localParticipant, networkQualityLevel);
            }
        }

        private void checkPublishedCallback(LocalParticipant localParticipant, TrackPublication trackPublication, String str) {
            boolean z = true;
            Preconditions.checkState(localParticipant != null, "Received null local participant in %s", (Object) str);
            if (trackPublication == null) {
                z = false;
            }
            Preconditions.checkState(z, "Received null track publication in %s", (Object) str);
        }

        private void checkPublicationFailedCallback(LocalParticipant localParticipant, Track track, TwilioException twilioException, String str) {
            boolean z = true;
            Preconditions.checkState(localParticipant != null, "Received null local participant in %s", (Object) str);
            Preconditions.checkState(track != null, "Received null track in %s", (Object) str);
            if (twilioException == null) {
                z = false;
            }
            Preconditions.checkState(z, "Received null exception in %s", (Object) str);
        }
    };
    /* access modifiers changed from: private */
    public final List<LocalVideoTrackPublication> localVideoTrackPublications;
    private long nativeLocalParticipantHandle;
    /* access modifiers changed from: private */
    public NetworkQualityLevel networkQualityLevel = NetworkQualityLevel.NETWORK_QUALITY_LEVEL_UNKNOWN;
    private final String sid;
    private final String signalingRegion;
    /* access modifiers changed from: private */
    public final List<VideoTrackPublication> videoTrackPublications;

    public interface Listener {

        /* renamed from: com.twilio.video.LocalParticipant$Listener$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onNetworkQualityLevelChanged(@NonNull Listener listener, @NonNull LocalParticipant localParticipant, NetworkQualityLevel networkQualityLevel) {
            }
        }

        void onAudioTrackPublicationFailed(@NonNull LocalParticipant localParticipant, @NonNull LocalAudioTrack localAudioTrack, @NonNull TwilioException twilioException);

        void onAudioTrackPublished(@NonNull LocalParticipant localParticipant, @NonNull LocalAudioTrackPublication localAudioTrackPublication);

        void onDataTrackPublicationFailed(@NonNull LocalParticipant localParticipant, @NonNull LocalDataTrack localDataTrack, @NonNull TwilioException twilioException);

        void onDataTrackPublished(@NonNull LocalParticipant localParticipant, @NonNull LocalDataTrackPublication localDataTrackPublication);

        void onNetworkQualityLevelChanged(@NonNull LocalParticipant localParticipant, @NonNull NetworkQualityLevel networkQualityLevel);

        void onVideoTrackPublicationFailed(@NonNull LocalParticipant localParticipant, @NonNull LocalVideoTrack localVideoTrack, @NonNull TwilioException twilioException);

        void onVideoTrackPublished(@NonNull LocalParticipant localParticipant, @NonNull LocalVideoTrackPublication localVideoTrackPublication);
    }

    private native boolean nativePublishAudioTrack(long j, LocalAudioTrack localAudioTrack, long j2);

    private native boolean nativePublishDataTrack(long j, LocalDataTrack localDataTrack, long j2);

    private native boolean nativePublishVideoTrack(long j, LocalVideoTrack localVideoTrack, long j2);

    private native void nativeRelease(long j);

    private native void nativeSetEncodingParameters(long j, EncodingParameters encodingParameters);

    private native boolean nativeUnpublishAudioTrack(long j, long j2);

    private native boolean nativeUnpublishDataTrack(long j, long j2);

    private native boolean nativeUnpublishVideoTrack(long j, long j2);

    @NonNull
    public String getSid() {
        return this.sid;
    }

    @NonNull
    public String getIdentity() {
        return this.identity;
    }

    @NonNull
    public NetworkQualityLevel getNetworkQualityLevel() {
        return this.networkQualityLevel;
    }

    @NonNull
    public String getSignalingRegion() {
        return this.signalingRegion;
    }

    @NonNull
    public synchronized List<AudioTrackPublication> getAudioTracks() {
        return Collections.unmodifiableList(this.audioTrackPublications);
    }

    @NonNull
    public synchronized List<VideoTrackPublication> getVideoTracks() {
        return Collections.unmodifiableList(this.videoTrackPublications);
    }

    @NonNull
    public synchronized List<DataTrackPublication> getDataTracks() {
        return Collections.unmodifiableList(this.dataTrackPublications);
    }

    @Nullable
    public synchronized List<LocalAudioTrackPublication> getLocalAudioTracks() {
        return Collections.unmodifiableList(this.localAudioTrackPublications);
    }

    @Nullable
    public synchronized List<LocalVideoTrackPublication> getLocalVideoTracks() {
        return Collections.unmodifiableList(this.localVideoTrackPublications);
    }

    @Nullable
    public synchronized List<LocalDataTrackPublication> getLocalDataTracks() {
        return Collections.unmodifiableList(this.localDataTrackPublications);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        if (nativePublishAudioTrack(r9.nativeLocalParticipantHandle, r10, r10.getNativeHandle()) != false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean publishTrack(@androidx.annotation.NonNull com.twilio.video.LocalAudioTrack r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.String r0 = "LocalAudioTrack must not be null"
            com.twilio.video.Preconditions.checkNotNull(r10, r0)     // Catch:{ all -> 0x002e }
            boolean r0 = r10.isReleased()     // Catch:{ all -> 0x002e }
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            java.lang.String r3 = "LocalAudioTrack must not be released"
            com.twilio.video.Preconditions.checkArgument(r0, r3)     // Catch:{ all -> 0x002e }
            boolean r0 = r9.isReleased()     // Catch:{ all -> 0x002e }
            if (r0 != 0) goto L_0x002b
            long r4 = r9.nativeLocalParticipantHandle     // Catch:{ all -> 0x002e }
            long r7 = r10.getNativeHandle()     // Catch:{ all -> 0x002e }
            r3 = r9
            r6 = r10
            boolean r10 = r3.nativePublishAudioTrack(r4, r6, r7)     // Catch:{ all -> 0x002e }
            if (r10 == 0) goto L_0x002b
            goto L_0x002c
        L_0x002b:
            r1 = 0
        L_0x002c:
            monitor-exit(r9)
            return r1
        L_0x002e:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.twilio.video.LocalParticipant.publishTrack(com.twilio.video.LocalAudioTrack):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        if (nativePublishVideoTrack(r9.nativeLocalParticipantHandle, r10, r10.getNativeHandle()) != false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean publishTrack(@androidx.annotation.NonNull com.twilio.video.LocalVideoTrack r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.String r0 = "LocalVideoTrack must not be null"
            com.twilio.video.Preconditions.checkNotNull(r10, r0)     // Catch:{ all -> 0x002e }
            boolean r0 = r10.isReleased()     // Catch:{ all -> 0x002e }
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            java.lang.String r3 = "LocalVideoTrack must not be released"
            com.twilio.video.Preconditions.checkArgument(r0, r3)     // Catch:{ all -> 0x002e }
            boolean r0 = r9.isReleased()     // Catch:{ all -> 0x002e }
            if (r0 != 0) goto L_0x002b
            long r4 = r9.nativeLocalParticipantHandle     // Catch:{ all -> 0x002e }
            long r7 = r10.getNativeHandle()     // Catch:{ all -> 0x002e }
            r3 = r9
            r6 = r10
            boolean r10 = r3.nativePublishVideoTrack(r4, r6, r7)     // Catch:{ all -> 0x002e }
            if (r10 == 0) goto L_0x002b
            goto L_0x002c
        L_0x002b:
            r1 = 0
        L_0x002c:
            monitor-exit(r9)
            return r1
        L_0x002e:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.twilio.video.LocalParticipant.publishTrack(com.twilio.video.LocalVideoTrack):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        if (nativePublishDataTrack(r9.nativeLocalParticipantHandle, r10, r10.getNativeHandle()) != false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean publishTrack(@androidx.annotation.NonNull com.twilio.video.LocalDataTrack r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.String r0 = "LocalDataTrack must not be null"
            com.twilio.video.Preconditions.checkNotNull(r10, r0)     // Catch:{ all -> 0x002e }
            boolean r0 = r10.isReleased()     // Catch:{ all -> 0x002e }
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            java.lang.String r3 = "LocalDataTrack must not be released"
            com.twilio.video.Preconditions.checkArgument(r0, r3)     // Catch:{ all -> 0x002e }
            boolean r0 = r9.isReleased()     // Catch:{ all -> 0x002e }
            if (r0 != 0) goto L_0x002b
            long r4 = r9.nativeLocalParticipantHandle     // Catch:{ all -> 0x002e }
            long r7 = r10.getNativeHandle()     // Catch:{ all -> 0x002e }
            r3 = r9
            r6 = r10
            boolean r10 = r3.nativePublishDataTrack(r4, r6, r7)     // Catch:{ all -> 0x002e }
            if (r10 == 0) goto L_0x002b
            goto L_0x002c
        L_0x002b:
            r1 = 0
        L_0x002c:
            monitor-exit(r9)
            return r1
        L_0x002e:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.twilio.video.LocalParticipant.publishTrack(com.twilio.video.LocalDataTrack):boolean");
    }

    public synchronized boolean unpublishTrack(@NonNull LocalAudioTrack localAudioTrack) {
        Preconditions.checkNotNull(localAudioTrack, "LocalAudioTrack must not be null");
        Preconditions.checkArgument(!localAudioTrack.isReleased(), "LocalAudioTrack must not be released");
        if (isReleased()) {
            return false;
        }
        removePublishedAudioTrack(localAudioTrack);
        return nativeUnpublishAudioTrack(this.nativeLocalParticipantHandle, localAudioTrack.getNativeHandle());
    }

    public synchronized boolean unpublishTrack(@NonNull LocalVideoTrack localVideoTrack) {
        Preconditions.checkNotNull(localVideoTrack, "LocalVideoTrack must not be null");
        Preconditions.checkArgument(!localVideoTrack.isReleased(), "LocalVideoTrack must not be released");
        if (isReleased()) {
            return false;
        }
        removePublishedVideoTrack(localVideoTrack);
        return nativeUnpublishVideoTrack(this.nativeLocalParticipantHandle, localVideoTrack.getNativeHandle());
    }

    public synchronized boolean unpublishTrack(@NonNull LocalDataTrack localDataTrack) {
        Preconditions.checkNotNull(localDataTrack, "LocalDataTrack must not be null");
        Preconditions.checkArgument(!localDataTrack.isReleased(), "LocalDataTrack must not be released");
        if (isReleased()) {
            return false;
        }
        removePublishedDataTrack(localDataTrack);
        return nativeUnpublishDataTrack(this.nativeLocalParticipantHandle, localDataTrack.getNativeHandle());
    }

    public void setListener(@NonNull Listener listener) {
        Preconditions.checkNotNull(listener, "Listener must not be null");
        this.listenerReference.set(listener);
    }

    public synchronized void setEncodingParameters(@Nullable EncodingParameters encodingParameters) {
        if (!isReleased()) {
            nativeSetEncodingParameters(this.nativeLocalParticipantHandle, encodingParameters);
        } else {
            logger.w("Cannot set encoding parameters after disconnected from a room");
        }
    }

    LocalParticipant(long j, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<LocalAudioTrackPublication> list, @NonNull List<LocalVideoTrackPublication> list2, @NonNull List<LocalDataTrackPublication> list3, @NonNull Handler handler2) {
        Preconditions.checkNotNull(str, "SID must not be null");
        Preconditions.checkArgument(!str.isEmpty(), "SID must not be empty");
        Preconditions.checkNotNull(str2, "Identity must not be null");
        Preconditions.checkNotNull(str3, "Signaling region must not be null");
        this.nativeLocalParticipantHandle = j;
        this.sid = str;
        this.identity = str2;
        this.signalingRegion = str3;
        this.localAudioTrackPublications = list;
        this.audioTrackPublications = new ArrayList(list.size());
        addAudioTracks(list);
        this.localVideoTrackPublications = list2;
        this.videoTrackPublications = new ArrayList(list2.size());
        addVideoTracks(list2);
        this.localDataTrackPublications = list3;
        this.dataTrackPublications = new ArrayList(list3.size());
        addDataTracks(list3);
        this.handler = handler2;
    }

    /* access modifiers changed from: package-private */
    public synchronized void release() {
        if (!isReleased()) {
            nativeRelease(this.nativeLocalParticipantHandle);
            this.nativeLocalParticipantHandle = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isReleased() {
        return this.nativeLocalParticipantHandle == 0;
    }

    private void addAudioTracks(List<LocalAudioTrackPublication> list) {
        this.audioTrackPublications.addAll(list);
    }

    private void addVideoTracks(List<LocalVideoTrackPublication> list) {
        this.videoTrackPublications.addAll(list);
    }

    private void addDataTracks(List<LocalDataTrackPublication> list) {
        this.dataTrackPublications.addAll(list);
    }

    private void removePublishedAudioTrack(LocalAudioTrack localAudioTrack) {
        for (LocalAudioTrackPublication next : this.localAudioTrackPublications) {
            if (localAudioTrack.equals(next.getLocalAudioTrack())) {
                this.audioTrackPublications.remove(next);
                this.localAudioTrackPublications.remove(next);
                return;
            }
        }
    }

    private void removePublishedVideoTrack(LocalVideoTrack localVideoTrack) {
        for (LocalVideoTrackPublication next : this.localVideoTrackPublications) {
            if (localVideoTrack.equals(next.getLocalVideoTrack())) {
                this.videoTrackPublications.remove(next);
                this.localVideoTrackPublications.remove(next);
                return;
            }
        }
    }

    private void removePublishedDataTrack(LocalDataTrack localDataTrack) {
        for (LocalDataTrackPublication next : this.localDataTrackPublications) {
            if (localDataTrack.equals(next.getLocalDataTrack())) {
                this.dataTrackPublications.remove(next);
                this.localDataTrackPublications.remove(next);
                return;
            }
        }
    }
}
