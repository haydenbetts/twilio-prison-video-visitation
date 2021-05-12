package com.twilio.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.twilio.video.Room;
import com.twilio.video.Video;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import tvi.webrtc.voiceengine.WebRtcAudioManager;

public class Room {
    private static final Field blacklistDeviceForOpenSLESUsageField;
    private static final Field blacklistDeviceForOpenSLESUsageIsOverriddenField;
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(Room.class);
    private Context context;
    /* access modifiers changed from: private */
    public RemoteParticipant dominantSpeaker;
    /* access modifiers changed from: private */
    public final Handler handler;
    /* access modifiers changed from: private */
    public final Listener listener;
    /* access modifiers changed from: private */
    public LocalParticipant localParticipant;
    private MediaFactory mediaFactory;
    private String mediaRegion = null;
    private String name;
    private long nativeRoomDelegate;
    /* access modifiers changed from: private */
    public Map<String, RemoteParticipant> participantMap = new HashMap();
    private final Listener roomListenerProxy = new Listener() {
        public void onConnected(@NonNull Room room) {
            Room.this.handler.post(new Runnable(room) {
                private final /* synthetic */ Room f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    Room.AnonymousClass1.lambda$onConnected$0(Room.AnonymousClass1.this, this.f$1);
                }
            });
        }

        public static /* synthetic */ void lambda$onConnected$0(AnonymousClass1 r2, Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onConnected()");
            Room.this.listener.onConnected(room);
        }

        public void onConnectFailure(@NonNull Room room, @NonNull TwilioException twilioException) {
            Room.this.releaseRoom();
            Room.this.handler.post(new Runnable(room, twilioException) {
                private final /* synthetic */ Room f$1;
                private final /* synthetic */ TwilioException f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.lambda$onConnectFailure$1(Room.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onConnectFailure$1(AnonymousClass1 r2, Room room, TwilioException twilioException) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onConnectFailure()");
            State unused = Room.this.state = State.DISCONNECTED;
            Room.this.release();
            Room.this.listener.onConnectFailure(room, twilioException);
        }

        public void onReconnecting(@NonNull Room room, @NonNull TwilioException twilioException) {
            Room.this.handler.post(new Runnable(room, twilioException) {
                private final /* synthetic */ Room f$1;
                private final /* synthetic */ TwilioException f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.lambda$onReconnecting$2(Room.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onReconnecting$2(AnonymousClass1 r2, Room room, TwilioException twilioException) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onReconnecting()");
            State unused = Room.this.state = State.RECONNECTING;
            Room.this.listener.onReconnecting(room, twilioException);
        }

        public void onReconnected(@NonNull Room room) {
            Room.this.handler.post(new Runnable(room) {
                private final /* synthetic */ Room f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    Room.AnonymousClass1.lambda$onReconnected$3(Room.AnonymousClass1.this, this.f$1);
                }
            });
        }

        public static /* synthetic */ void lambda$onReconnected$3(AnonymousClass1 r2, Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onReconnected()");
            State unused = Room.this.state = State.CONNECTED;
            Room.this.listener.onReconnected(room);
        }

        public void onDisconnected(@NonNull Room room, @Nullable TwilioException twilioException) {
            Room.this.releaseRoom();
            if (Room.this.localParticipant != null) {
                Room.this.localParticipant.release();
            }
            Room.this.handler.post(new Runnable(room, twilioException) {
                private final /* synthetic */ Room f$1;
                private final /* synthetic */ TwilioException f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.lambda$onDisconnected$4(Room.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onDisconnected$4(AnonymousClass1 r2, Room room, TwilioException twilioException) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onDisconnected()");
            State unused = Room.this.state = State.DISCONNECTED;
            Room.this.release();
            Room.this.listener.onDisconnected(room, twilioException);
        }

        public void onParticipantConnected(@NonNull Room room, @NonNull RemoteParticipant remoteParticipant) {
            Room.this.handler.post(new Runnable(remoteParticipant, room) {
                private final /* synthetic */ RemoteParticipant f$1;
                private final /* synthetic */ Room f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.lambda$onParticipantConnected$5(Room.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onParticipantConnected$5(AnonymousClass1 r2, RemoteParticipant remoteParticipant, Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onParticipantConnected()");
            Room.this.participantMap.put(remoteParticipant.getSid(), remoteParticipant);
            Room.this.listener.onParticipantConnected(room, remoteParticipant);
        }

        public void onParticipantDisconnected(@NonNull Room room, @NonNull RemoteParticipant remoteParticipant) {
            remoteParticipant.release();
            Room.this.handler.post(new Runnable(remoteParticipant, room) {
                private final /* synthetic */ RemoteParticipant f$1;
                private final /* synthetic */ Room f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.lambda$onParticipantDisconnected$6(Room.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onParticipantDisconnected$6(AnonymousClass1 r2, RemoteParticipant remoteParticipant, Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onParticipantDisconnected()");
            Room.this.participantMap.remove(remoteParticipant.getSid());
            Room.this.listener.onParticipantDisconnected(room, remoteParticipant);
        }

        public void onDominantSpeakerChanged(@NonNull Room room, @Nullable RemoteParticipant remoteParticipant) {
            Room.this.handler.post(new Runnable(remoteParticipant, room) {
                private final /* synthetic */ RemoteParticipant f$1;
                private final /* synthetic */ Room f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.lambda$onDominantSpeakerChanged$7(Room.AnonymousClass1.this, this.f$1, this.f$2);
                }
            });
        }

        public static /* synthetic */ void lambda$onDominantSpeakerChanged$7(AnonymousClass1 r2, RemoteParticipant remoteParticipant, Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onDominantSpeakerChanged()");
            RemoteParticipant unused = Room.this.dominantSpeaker = remoteParticipant;
            Room.this.listener.onDominantSpeakerChanged(room, remoteParticipant);
        }

        public void onRecordingStarted(@NonNull Room room) {
            Room.this.handler.post(new Runnable(room) {
                private final /* synthetic */ Room f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    Room.AnonymousClass1.lambda$onRecordingStarted$8(Room.AnonymousClass1.this, this.f$1);
                }
            });
        }

        public static /* synthetic */ void lambda$onRecordingStarted$8(AnonymousClass1 r2, Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onRecordingStarted()");
            Room.this.listener.onRecordingStarted(room);
        }

        public void onRecordingStopped(@NonNull Room room) {
            Room.this.handler.post(new Runnable(room) {
                private final /* synthetic */ Room f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    Room.AnonymousClass1.lambda$onRecordingStopped$9(Room.AnonymousClass1.this, this.f$1);
                }
            });
        }

        public static /* synthetic */ void lambda$onRecordingStopped$9(AnonymousClass1 r2, Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onRecordingStopped()");
            Room.this.listener.onRecordingStopped(room);
        }
    };
    private String sid;
    /* access modifiers changed from: private */
    public State state;
    private final StatsListener statsListenerProxy = new StatsListener() {
        public final void onStats(List list) {
            Room.lambda$new$1(Room.this, list);
        }
    };
    private Queue<Pair<Handler, StatsListener>> statsListenersQueue;

    public enum State {
        CONNECTING,
        CONNECTED,
        RECONNECTING,
        DISCONNECTED
    }

    private native long nativeConnect(ConnectOptions connectOptions, Listener listener2, StatsListener statsListener, long j, Handler handler2);

    private native void nativeDisconnect(long j);

    private native void nativeGetStats(long j);

    private native boolean nativeIsRecording(long j);

    private native void nativeOnNetworkChange(long j, Video.NetworkChangeEvent networkChangeEvent);

    private native void nativeRelease(long j);

    private native void nativeReleaseRoom(long j);

    static {
        try {
            blacklistDeviceForOpenSLESUsageField = WebRtcAudioManager.class.getDeclaredField("blacklistDeviceForOpenSLESUsage");
            blacklistDeviceForOpenSLESUsageIsOverriddenField = WebRtcAudioManager.class.getDeclaredField("blacklistDeviceForOpenSLESUsageIsOverridden");
            blacklistDeviceForOpenSLESUsageField.setAccessible(true);
            blacklistDeviceForOpenSLESUsageIsOverriddenField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$new$1(Room room, List list) {
        Pair poll = room.statsListenersQueue.poll();
        if (poll != null) {
            ((Handler) poll.first).post(new Runnable(poll, list) {
                private final /* synthetic */ Pair f$0;
                private final /* synthetic */ List f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void run() {
                    ((StatsListener) this.f$0.second).onStats(this.f$1);
                }
            });
        }
    }

    Room(@NonNull Context context2, @NonNull String str, @NonNull Handler handler2, @NonNull Listener listener2) {
        this.context = context2;
        this.name = str;
        this.sid = "";
        this.state = State.DISCONNECTED;
        this.listener = listener2;
        this.handler = handler2;
        this.statsListenersQueue = new ConcurrentLinkedQueue();
        configureOpenSLES();
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @NonNull
    public String getSid() {
        return this.sid;
    }

    @Nullable
    public String getMediaRegion() {
        return this.mediaRegion;
    }

    @NonNull
    public synchronized State getState() {
        return this.state;
    }

    @Nullable
    public RemoteParticipant getDominantSpeaker() {
        return this.dominantSpeaker;
    }

    public synchronized boolean isRecording() {
        return this.state == State.CONNECTED && nativeIsRecording(this.nativeRoomDelegate);
    }

    @NonNull
    public synchronized List<RemoteParticipant> getRemoteParticipants() {
        return new ArrayList(this.participantMap.values());
    }

    @Nullable
    public synchronized LocalParticipant getLocalParticipant() {
        return this.localParticipant;
    }

    public synchronized void getStats(@NonNull StatsListener statsListener) {
        Preconditions.checkNotNull(statsListener, "StatsListener must not be null");
        if (this.state != State.DISCONNECTED) {
            this.statsListenersQueue.offer(new Pair(Util.createCallbackHandler(), statsListener));
            nativeGetStats(this.nativeRoomDelegate);
        }
    }

    public synchronized void disconnect() {
        if (!(this.state == State.DISCONNECTED || this.nativeRoomDelegate == 0)) {
            if (this.localParticipant != null) {
                this.localParticipant.release();
            }
            nativeDisconnect(this.nativeRoomDelegate);
        }
    }

    /* access modifiers changed from: package-private */
    public void onNetworkChanged(Video.NetworkChangeEvent networkChangeEvent) {
        long j = this.nativeRoomDelegate;
        if (j != 0) {
            nativeOnNetworkChange(j, networkChangeEvent);
        }
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"RestrictedApi"})
    public void connect(@NonNull ConnectOptions connectOptions) {
        MediaFactory mediaFactory2;
        ConnectOptions.checkAudioTracksReleased(connectOptions.getAudioTracks());
        ConnectOptions.checkVideoTracksReleased(connectOptions.getVideoTracks());
        synchronized (this.roomListenerProxy) {
            if (connectOptions.getMediaFactory() == null) {
                mediaFactory2 = MediaFactory.instance(this, this.context);
            } else {
                mediaFactory2 = connectOptions.getMediaFactory();
            }
            this.mediaFactory = mediaFactory2;
            this.nativeRoomDelegate = nativeConnect(connectOptions, this.roomListenerProxy, this.statsListenerProxy, this.mediaFactory.getNativeMediaFactoryHandle(), this.handler);
            this.state = State.CONNECTING;
        }
    }

    @VisibleForTesting
    static void configureOpenSLES() {
        if (!openSLESEnabled()) {
            WebRtcAudioManager.setBlacklistDeviceForOpenSLESUsage(true);
        }
    }

    @VisibleForTesting
    static boolean openSLESEnabled() {
        try {
            return !((Boolean) blacklistDeviceForOpenSLESUsageField.get((Object) null)).booleanValue() && ((Boolean) blacklistDeviceForOpenSLESUsageIsOverriddenField.get((Object) null)).booleanValue();
        } catch (Exception unused) {
            throw new RuntimeException("Failed to determine if OpenSLES is enabled.");
        }
    }

    private synchronized void setConnected(String str, String str2, LocalParticipant localParticipant2, List<RemoteParticipant> list) {
        logger.d("setConnected()");
        this.sid = str;
        if (str2.isEmpty()) {
            str2 = null;
        }
        this.mediaRegion = str2;
        if (this.name == null || this.name.isEmpty()) {
            this.name = str;
        }
        this.localParticipant = localParticipant2;
        for (RemoteParticipant next : list) {
            this.participantMap.put(next.getSid(), next);
        }
        this.state = State.CONNECTED;
    }

    /* access modifiers changed from: private */
    public synchronized void releaseRoom() {
        if (this.nativeRoomDelegate != 0) {
            for (RemoteParticipant release : this.participantMap.values()) {
                release.release();
            }
            nativeReleaseRoom(this.nativeRoomDelegate);
            cleanupStatsListenerQueue();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void release() {
        ThreadChecker.checkIsValidThread(this.handler);
        if (this.nativeRoomDelegate != 0) {
            nativeRelease(this.nativeRoomDelegate);
            this.nativeRoomDelegate = 0;
            this.mediaFactory.release(this);
        }
    }

    private void cleanupStatsListenerQueue() {
        for (Pair pair : this.statsListenersQueue) {
            ((Handler) pair.first).post(new Runnable(pair) {
                private final /* synthetic */ Pair f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    ((StatsListener) this.f$0.second).onStats(new ArrayList());
                }
            });
        }
        this.statsListenersQueue.clear();
    }

    public interface Listener {
        void onConnectFailure(@NonNull Room room, @NonNull TwilioException twilioException);

        void onConnected(@NonNull Room room);

        void onDisconnected(@NonNull Room room, @Nullable TwilioException twilioException);

        void onDominantSpeakerChanged(@NonNull Room room, @Nullable RemoteParticipant remoteParticipant);

        void onParticipantConnected(@NonNull Room room, @NonNull RemoteParticipant remoteParticipant);

        void onParticipantDisconnected(@NonNull Room room, @NonNull RemoteParticipant remoteParticipant);

        void onReconnected(@NonNull Room room);

        void onReconnecting(@NonNull Room room, @NonNull TwilioException twilioException);

        void onRecordingStarted(@NonNull Room room);

        void onRecordingStopped(@NonNull Room room);

        /* renamed from: com.twilio.video.Room$Listener$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onDominantSpeakerChanged(@NonNull Listener listener, @Nullable Room room, RemoteParticipant remoteParticipant) {
                Room.logger.d("onDominantSpeakerChanged");
            }
        }
    }
}
