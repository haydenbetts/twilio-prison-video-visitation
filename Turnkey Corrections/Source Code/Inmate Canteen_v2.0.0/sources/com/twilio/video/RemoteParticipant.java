package com.twilio.video;

import android.os.Handler;
import androidx.annotation.NonNull;
import com.twilio.video.RemoteParticipant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RemoteParticipant implements Participant {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(RemoteParticipant.class);
    /* access modifiers changed from: private */
    public final List<AudioTrackPublication> audioTrackPublications;
    /* access modifiers changed from: private */
    public final List<DataTrackPublication> dataTrackPublications;
    /* access modifiers changed from: private */
    public final Handler handler;
    private final String identity;
    /* access modifiers changed from: private */
    public final AtomicReference<Listener> listenerReference = new AtomicReference<>((Object) null);
    private long nativeParticipantContext;
    /* access modifiers changed from: private */
    public NetworkQualityLevel networkQualityLevel = NetworkQualityLevel.NETWORK_QUALITY_LEVEL_UNKNOWN;
    /* access modifiers changed from: private */
    public final List<RemoteAudioTrackPublication> remoteAudioTrackPublications;
    /* access modifiers changed from: private */
    public final List<RemoteDataTrackPublication> remoteDataTrackPublications;
    final Listener remoteParticipantListenerProxy = new Listener() {
        public void onAudioTrackPublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication) {
            checkCallback(remoteParticipant, remoteAudioTrackPublication, "onAudioTrackPublished");
            RemoteParticipant.this.handler.post(new Runnable(remoteAudioTrackPublication, remoteParticipant) {
                private final /* synthetic */ RemoteAudioTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onAudioTrackPublished$0(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onAudioTrackPublished$0(AnonymousClass1 r2, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onAudioTrackPublished");
            RemoteParticipant.this.audioTrackPublications.add(remoteAudioTrackPublication);
            RemoteParticipant.this.remoteAudioTrackPublications.add(remoteAudioTrackPublication);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onAudioTrackPublished(remoteParticipant, remoteAudioTrackPublication);
            }
        }

        public void onAudioTrackUnpublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication) {
            checkCallback(remoteParticipant, remoteAudioTrackPublication, "onAudioTrackUnpublished");
            RemoteParticipant.this.handler.post(new Runnable(remoteAudioTrackPublication, remoteParticipant) {
                private final /* synthetic */ RemoteAudioTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onAudioTrackUnpublished$1(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onAudioTrackUnpublished$1(AnonymousClass1 r2, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onAudioTrackUnpublished");
            RemoteParticipant.this.audioTrackPublications.remove(remoteAudioTrackPublication);
            RemoteParticipant.this.remoteAudioTrackPublications.remove(remoteAudioTrackPublication);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onAudioTrackUnpublished(remoteParticipant, remoteAudioTrackPublication);
            }
        }

        public void onAudioTrackSubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication, @NonNull RemoteAudioTrack remoteAudioTrack) {
            checkCallback(remoteParticipant, remoteAudioTrackPublication, "onAudioTrackSubscribed");
            RemoteParticipant.this.handler.post(new Runnable(remoteAudioTrackPublication, remoteAudioTrack, remoteParticipant) {
                private final /* synthetic */ RemoteAudioTrackPublication f$1;
                private final /* synthetic */ RemoteAudioTrack f$2;
                private final /* synthetic */ RemoteParticipant f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onAudioTrackSubscribed$2(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onAudioTrackSubscribed$2(AnonymousClass1 r2, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteAudioTrack remoteAudioTrack, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onAudioTrackSubscribed");
            remoteAudioTrackPublication.setSubscribed(true);
            remoteAudioTrackPublication.setRemoteAudioTrack(remoteAudioTrack);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onAudioTrackSubscribed(remoteParticipant, remoteAudioTrackPublication, remoteAudioTrack);
            }
        }

        public void onAudioTrackSubscriptionFailed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication, @NonNull TwilioException twilioException) {
            checkCallback(remoteParticipant, remoteAudioTrackPublication, "onAudioTrackSubscriptionFailed");
            RemoteParticipant.this.handler.post(new Runnable(remoteAudioTrackPublication, remoteParticipant, twilioException) {
                private final /* synthetic */ RemoteAudioTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;
                private final /* synthetic */ TwilioException f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onAudioTrackSubscriptionFailed$3(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onAudioTrackSubscriptionFailed$3(AnonymousClass1 r2, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteParticipant remoteParticipant, TwilioException twilioException) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onAudioTrackSubscriptionFailed");
            remoteAudioTrackPublication.setSubscribed(false);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onAudioTrackSubscriptionFailed(remoteParticipant, remoteAudioTrackPublication, twilioException);
            }
        }

        public void onAudioTrackUnsubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication, @NonNull RemoteAudioTrack remoteAudioTrack) {
            checkCallback(remoteParticipant, remoteAudioTrackPublication, "onAudioTrackUnsubscribed");
            remoteAudioTrack.release();
            RemoteParticipant.this.handler.post(new Runnable(remoteAudioTrackPublication, remoteParticipant, remoteAudioTrack) {
                private final /* synthetic */ RemoteAudioTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;
                private final /* synthetic */ RemoteAudioTrack f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onAudioTrackUnsubscribed$4(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onAudioTrackUnsubscribed$4(AnonymousClass1 r2, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteParticipant remoteParticipant, RemoteAudioTrack remoteAudioTrack) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onAudioTrackUnsubscribed");
            remoteAudioTrackPublication.setRemoteAudioTrack((RemoteAudioTrack) null);
            remoteAudioTrackPublication.setSubscribed(false);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onAudioTrackUnsubscribed(remoteParticipant, remoteAudioTrackPublication, remoteAudioTrack);
            }
        }

        public void onVideoTrackPublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication) {
            checkCallback(remoteParticipant, remoteVideoTrackPublication, "onVideoTrackPublished");
            RemoteParticipant.this.handler.post(new Runnable(remoteVideoTrackPublication, remoteParticipant) {
                private final /* synthetic */ RemoteVideoTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onVideoTrackPublished$5(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onVideoTrackPublished$5(AnonymousClass1 r2, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onVideoTrackPublished");
            RemoteParticipant.this.videoTrackPublications.add(remoteVideoTrackPublication);
            RemoteParticipant.this.remoteVideoTrackPublications.add(remoteVideoTrackPublication);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onVideoTrackPublished(remoteParticipant, remoteVideoTrackPublication);
            }
        }

        public void onVideoTrackUnpublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication) {
            checkCallback(remoteParticipant, remoteVideoTrackPublication, "onVideoTrackUnpublished");
            RemoteParticipant.this.handler.post(new Runnable(remoteVideoTrackPublication, remoteParticipant) {
                private final /* synthetic */ RemoteVideoTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onVideoTrackUnpublished$6(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onVideoTrackUnpublished$6(AnonymousClass1 r2, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onVideoTrackUnpublished");
            RemoteParticipant.this.videoTrackPublications.remove(remoteVideoTrackPublication);
            RemoteParticipant.this.remoteVideoTrackPublications.remove(remoteVideoTrackPublication);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onVideoTrackUnpublished(remoteParticipant, remoteVideoTrackPublication);
            }
        }

        public void onVideoTrackSubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication, @NonNull RemoteVideoTrack remoteVideoTrack) {
            checkCallback(remoteParticipant, remoteVideoTrackPublication, "onVideoTrackSubscribed");
            RemoteParticipant.this.handler.post(new Runnable(remoteVideoTrackPublication, remoteVideoTrack, remoteParticipant) {
                private final /* synthetic */ RemoteVideoTrackPublication f$1;
                private final /* synthetic */ RemoteVideoTrack f$2;
                private final /* synthetic */ RemoteParticipant f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onVideoTrackSubscribed$7(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onVideoTrackSubscribed$7(AnonymousClass1 r2, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteVideoTrack remoteVideoTrack, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onVideoTrackSubscribed");
            remoteVideoTrackPublication.setSubscribed(true);
            remoteVideoTrackPublication.setRemoteVideoTrack(remoteVideoTrack);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onVideoTrackSubscribed(remoteParticipant, remoteVideoTrackPublication, remoteVideoTrack);
            }
        }

        public void onVideoTrackSubscriptionFailed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication, @NonNull TwilioException twilioException) {
            checkCallback(remoteParticipant, remoteVideoTrackPublication, "onVideoTrackSubscriptionFailed");
            RemoteParticipant.this.handler.post(new Runnable(remoteVideoTrackPublication, remoteParticipant, twilioException) {
                private final /* synthetic */ RemoteVideoTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;
                private final /* synthetic */ TwilioException f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onVideoTrackSubscriptionFailed$8(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onVideoTrackSubscriptionFailed$8(AnonymousClass1 r2, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteParticipant remoteParticipant, TwilioException twilioException) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onVideoTrackSubscriptionFailed");
            remoteVideoTrackPublication.setSubscribed(false);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onVideoTrackSubscriptionFailed(remoteParticipant, remoteVideoTrackPublication, twilioException);
            }
        }

        public void onVideoTrackUnsubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication, @NonNull RemoteVideoTrack remoteVideoTrack) {
            checkCallback(remoteParticipant, remoteVideoTrackPublication, "onVideoTrackUnsubscribed");
            remoteVideoTrack.release();
            RemoteParticipant.this.handler.post(new Runnable(remoteVideoTrackPublication, remoteParticipant, remoteVideoTrack) {
                private final /* synthetic */ RemoteVideoTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;
                private final /* synthetic */ RemoteVideoTrack f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onVideoTrackUnsubscribed$9(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onVideoTrackUnsubscribed$9(AnonymousClass1 r2, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteParticipant remoteParticipant, RemoteVideoTrack remoteVideoTrack) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onVideoTrackUnsubscribed");
            remoteVideoTrackPublication.setRemoteVideoTrack((RemoteVideoTrack) null);
            remoteVideoTrackPublication.setSubscribed(false);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onVideoTrackUnsubscribed(remoteParticipant, remoteVideoTrackPublication, remoteVideoTrack);
            }
        }

        public void onDataTrackPublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication) {
            checkCallback(remoteParticipant, remoteDataTrackPublication, "onDataTrackPublished");
            RemoteParticipant.this.handler.post(new Runnable(remoteDataTrackPublication, remoteParticipant) {
                private final /* synthetic */ RemoteDataTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onDataTrackPublished$10(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onDataTrackPublished$10(AnonymousClass1 r2, RemoteDataTrackPublication remoteDataTrackPublication, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onDataTrackPublished");
            RemoteParticipant.this.dataTrackPublications.add(remoteDataTrackPublication);
            RemoteParticipant.this.remoteDataTrackPublications.add(remoteDataTrackPublication);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onDataTrackPublished(remoteParticipant, remoteDataTrackPublication);
            }
        }

        public void onDataTrackUnpublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication) {
            checkCallback(remoteParticipant, remoteDataTrackPublication, "onDataTrackUnpublished");
            RemoteParticipant.this.handler.post(new Runnable(remoteDataTrackPublication, remoteParticipant) {
                private final /* synthetic */ RemoteDataTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onDataTrackUnpublished$11(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onDataTrackUnpublished$11(AnonymousClass1 r2, RemoteDataTrackPublication remoteDataTrackPublication, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onDataTrackUnpublished");
            RemoteParticipant.this.dataTrackPublications.remove(remoteDataTrackPublication);
            RemoteParticipant.this.remoteDataTrackPublications.remove(remoteDataTrackPublication);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onDataTrackUnpublished(remoteParticipant, remoteDataTrackPublication);
            }
        }

        public void onDataTrackSubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication, @NonNull RemoteDataTrack remoteDataTrack) {
            checkCallback(remoteParticipant, remoteDataTrackPublication, "onDataTrackSubscribed");
            RemoteParticipant.this.handler.post(new Runnable(remoteDataTrackPublication, remoteDataTrack, remoteParticipant) {
                private final /* synthetic */ RemoteDataTrackPublication f$1;
                private final /* synthetic */ RemoteDataTrack f$2;
                private final /* synthetic */ RemoteParticipant f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onDataTrackSubscribed$12(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onDataTrackSubscribed$12(AnonymousClass1 r2, RemoteDataTrackPublication remoteDataTrackPublication, RemoteDataTrack remoteDataTrack, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onDataTrackSubscribed");
            remoteDataTrackPublication.setSubscribed(true);
            remoteDataTrackPublication.setRemoteDataTrack(remoteDataTrack);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onDataTrackSubscribed(remoteParticipant, remoteDataTrackPublication, remoteDataTrack);
            }
        }

        public void onDataTrackSubscriptionFailed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication, @NonNull TwilioException twilioException) {
            checkCallback(remoteParticipant, remoteDataTrackPublication, "onDataTrackSubscriptionFailed");
            RemoteParticipant.this.handler.post(new Runnable(remoteDataTrackPublication, remoteParticipant, twilioException) {
                private final /* synthetic */ RemoteDataTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;
                private final /* synthetic */ TwilioException f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onDataTrackSubscriptionFailed$13(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onDataTrackSubscriptionFailed$13(AnonymousClass1 r2, RemoteDataTrackPublication remoteDataTrackPublication, RemoteParticipant remoteParticipant, TwilioException twilioException) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onDataTrackSubscriptionFailed");
            remoteDataTrackPublication.setSubscribed(false);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onDataTrackSubscriptionFailed(remoteParticipant, remoteDataTrackPublication, twilioException);
            }
        }

        public void onDataTrackUnsubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication, @NonNull RemoteDataTrack remoteDataTrack) {
            checkCallback(remoteParticipant, remoteDataTrackPublication, "onDataTrackUnsubscribed");
            remoteDataTrack.release();
            RemoteParticipant.this.handler.post(new Runnable(remoteDataTrackPublication, remoteParticipant, remoteDataTrack) {
                private final /* synthetic */ RemoteDataTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;
                private final /* synthetic */ RemoteDataTrack f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onDataTrackUnsubscribed$14(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public static /* synthetic */ void lambda$onDataTrackUnsubscribed$14(AnonymousClass1 r2, RemoteDataTrackPublication remoteDataTrackPublication, RemoteParticipant remoteParticipant, RemoteDataTrack remoteDataTrack) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onDataTrackUnsubscribed");
            remoteDataTrackPublication.setRemoteDataTrack((RemoteDataTrack) null);
            remoteDataTrackPublication.setSubscribed(false);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onDataTrackUnsubscribed(remoteParticipant, remoteDataTrackPublication, remoteDataTrack);
            }
        }

        public void onAudioTrackEnabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication) {
            checkCallback(remoteParticipant, remoteAudioTrackPublication, "onAudioTrackEnabled");
            RemoteParticipant.this.handler.post(new Runnable(remoteAudioTrackPublication, remoteParticipant) {
                private final /* synthetic */ RemoteAudioTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onAudioTrackEnabled$15(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onAudioTrackEnabled$15(AnonymousClass1 r2, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onAudioTrackEnabled");
            remoteAudioTrackPublication.setEnabled(true);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onAudioTrackEnabled(remoteParticipant, remoteAudioTrackPublication);
            }
        }

        public void onAudioTrackDisabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication) {
            checkCallback(remoteParticipant, remoteAudioTrackPublication, "onAudioTrackDisabled");
            RemoteParticipant.this.handler.post(new Runnable(remoteAudioTrackPublication, remoteParticipant) {
                private final /* synthetic */ RemoteAudioTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onAudioTrackDisabled$16(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onAudioTrackDisabled$16(AnonymousClass1 r2, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onAudioTrackDisabled");
            remoteAudioTrackPublication.setEnabled(false);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onAudioTrackDisabled(remoteParticipant, remoteAudioTrackPublication);
            }
        }

        public void onVideoTrackEnabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication) {
            checkCallback(remoteParticipant, remoteVideoTrackPublication, "onVideoTrackEnabled");
            RemoteParticipant.this.handler.post(new Runnable(remoteVideoTrackPublication, remoteParticipant) {
                private final /* synthetic */ RemoteVideoTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onVideoTrackEnabled$17(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onVideoTrackEnabled$17(AnonymousClass1 r2, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onVideoTrackEnabled");
            remoteVideoTrackPublication.setEnabled(true);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onVideoTrackEnabled(remoteParticipant, remoteVideoTrackPublication);
            }
        }

        public void onVideoTrackDisabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication) {
            checkCallback(remoteParticipant, remoteVideoTrackPublication, "onVideoTrackDisabled");
            RemoteParticipant.this.handler.post(new Runnable(remoteVideoTrackPublication, remoteParticipant) {
                private final /* synthetic */ RemoteVideoTrackPublication f$1;
                private final /* synthetic */ RemoteParticipant f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onVideoTrackDisabled$18(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onVideoTrackDisabled$18(AnonymousClass1 r2, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteParticipant remoteParticipant) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onVideoTrackDisabled");
            remoteVideoTrackPublication.setEnabled(false);
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onVideoTrackDisabled(remoteParticipant, remoteVideoTrackPublication);
            }
        }

        public void onNetworkQualityLevelChanged(@NonNull RemoteParticipant remoteParticipant, @NonNull NetworkQualityLevel networkQualityLevel) {
            RemoteParticipant.this.handler.post(new Runnable(remoteParticipant, networkQualityLevel) {
                private final /* synthetic */ RemoteParticipant f$1;
                private final /* synthetic */ NetworkQualityLevel f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    RemoteParticipant.AnonymousClass1.lambda$onNetworkQualityLevelChanged$19(RemoteParticipant.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onNetworkQualityLevelChanged$19(AnonymousClass1 r2, RemoteParticipant remoteParticipant, NetworkQualityLevel networkQualityLevel) {
            ThreadChecker.checkIsValidThread(RemoteParticipant.this.handler);
            RemoteParticipant.logger.d("onNetworkQualityLevelChanged");
            NetworkQualityLevel unused = remoteParticipant.networkQualityLevel = networkQualityLevel;
            Listener listener = (Listener) RemoteParticipant.this.listenerReference.get();
            if (listener != null) {
                listener.onNetworkQualityLevelChanged(remoteParticipant, networkQualityLevel);
            }
        }

        private void checkCallback(RemoteParticipant remoteParticipant, TrackPublication trackPublication, String str) {
            boolean z = true;
            Preconditions.checkState(remoteParticipant != null, "Received null remote participant in %s", (Object) str);
            if (trackPublication == null) {
                z = false;
            }
            Preconditions.checkState(z, "Received null track publication in %s", (Object) str);
        }
    };
    /* access modifiers changed from: private */
    public final List<RemoteVideoTrackPublication> remoteVideoTrackPublications;
    private final String sid;
    /* access modifiers changed from: private */
    public final List<VideoTrackPublication> videoTrackPublications;

    public interface Listener {

        /* renamed from: com.twilio.video.RemoteParticipant$Listener$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onNetworkQualityLevelChanged(@NonNull Listener listener, @NonNull RemoteParticipant remoteParticipant, NetworkQualityLevel networkQualityLevel) {
            }
        }

        void onAudioTrackDisabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication);

        void onAudioTrackEnabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication);

        void onAudioTrackPublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication);

        void onAudioTrackSubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication, @NonNull RemoteAudioTrack remoteAudioTrack);

        void onAudioTrackSubscriptionFailed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication, @NonNull TwilioException twilioException);

        void onAudioTrackUnpublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication);

        void onAudioTrackUnsubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication, @NonNull RemoteAudioTrack remoteAudioTrack);

        void onDataTrackPublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication);

        void onDataTrackSubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication, @NonNull RemoteDataTrack remoteDataTrack);

        void onDataTrackSubscriptionFailed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication, @NonNull TwilioException twilioException);

        void onDataTrackUnpublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication);

        void onDataTrackUnsubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication, @NonNull RemoteDataTrack remoteDataTrack);

        void onNetworkQualityLevelChanged(@NonNull RemoteParticipant remoteParticipant, @NonNull NetworkQualityLevel networkQualityLevel);

        void onVideoTrackDisabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication);

        void onVideoTrackEnabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication);

        void onVideoTrackPublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication);

        void onVideoTrackSubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication, @NonNull RemoteVideoTrack remoteVideoTrack);

        void onVideoTrackSubscriptionFailed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication, @NonNull TwilioException twilioException);

        void onVideoTrackUnpublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication);

        void onVideoTrackUnsubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication, @NonNull RemoteVideoTrack remoteVideoTrack);
    }

    private native boolean nativeIsConnected(long j);

    private native void nativeRelease(long j);

    @NonNull
    public String getSid() {
        return this.sid;
    }

    RemoteParticipant(String str, String str2, List<RemoteAudioTrackPublication> list, List<RemoteVideoTrackPublication> list2, List<RemoteDataTrackPublication> list3, Handler handler2, long j) {
        this.identity = str;
        this.sid = str2;
        this.remoteAudioTrackPublications = list;
        this.audioTrackPublications = new ArrayList(list.size());
        addAudioTracks(list);
        this.remoteVideoTrackPublications = list2;
        this.videoTrackPublications = new ArrayList(list2.size());
        addVideoTracks(list2);
        this.remoteDataTrackPublications = list3;
        this.dataTrackPublications = new ArrayList(list3.size());
        addDataTracks(list3);
        this.handler = handler2;
        this.nativeParticipantContext = j;
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
    public List<AudioTrackPublication> getAudioTracks() {
        return Collections.unmodifiableList(this.audioTrackPublications);
    }

    @NonNull
    public List<VideoTrackPublication> getVideoTracks() {
        return Collections.unmodifiableList(this.videoTrackPublications);
    }

    @NonNull
    public List<DataTrackPublication> getDataTracks() {
        return Collections.unmodifiableList(this.dataTrackPublications);
    }

    public List<RemoteAudioTrackPublication> getRemoteAudioTracks() {
        return Collections.unmodifiableList(this.remoteAudioTrackPublications);
    }

    public List<RemoteVideoTrackPublication> getRemoteVideoTracks() {
        return Collections.unmodifiableList(this.remoteVideoTrackPublications);
    }

    public List<RemoteDataTrackPublication> getRemoteDataTracks() {
        return Collections.unmodifiableList(this.remoteDataTrackPublications);
    }

    public void setListener(Listener listener) {
        Preconditions.checkNotNull(listener, "Listener must not be null");
        this.listenerReference.set(listener);
    }

    public synchronized boolean isConnected() {
        if (isReleased()) {
            return false;
        }
        return nativeIsConnected(this.nativeParticipantContext);
    }

    /* access modifiers changed from: package-private */
    public synchronized void release() {
        if (!isReleased()) {
            for (RemoteAudioTrackPublication remoteAudioTrack : this.remoteAudioTrackPublications) {
                RemoteAudioTrack remoteAudioTrack2 = remoteAudioTrack.getRemoteAudioTrack();
                if (remoteAudioTrack2 != null) {
                    remoteAudioTrack2.release();
                }
            }
            for (RemoteVideoTrackPublication remoteVideoTrack : this.remoteVideoTrackPublications) {
                RemoteVideoTrack remoteVideoTrack2 = remoteVideoTrack.getRemoteVideoTrack();
                if (remoteVideoTrack2 != null) {
                    remoteVideoTrack2.release();
                }
            }
            nativeRelease(this.nativeParticipantContext);
            this.nativeParticipantContext = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isReleased() {
        return this.nativeParticipantContext == 0;
    }

    private void addAudioTracks(List<RemoteAudioTrackPublication> list) {
        this.audioTrackPublications.addAll(list);
    }

    private void addVideoTracks(List<RemoteVideoTrackPublication> list) {
        this.videoTrackPublications.addAll(list);
    }

    private void addDataTracks(List<RemoteDataTrackPublication> list) {
        this.dataTrackPublications.addAll(list);
    }
}
