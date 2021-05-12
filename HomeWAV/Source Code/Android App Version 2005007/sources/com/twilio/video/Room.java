package com.twilio.video;

import android.content.Context;
import android.os.Handler;
import android.util.Pair;
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
        public void onConnected(Room room) {
            Room.this.handler.post(new Runnable(room) {
                public final /* synthetic */ Room f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    Room.AnonymousClass1.this.lambda$onConnected$0$Room$1(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onConnected$0$Room$1(Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onConnected()");
            Room.this.listener.onConnected(room);
        }

        public void onConnectFailure(Room room, TwilioException twilioException) {
            Room.this.releaseRoom();
            Room.this.handler.post(new Runnable(room, twilioException) {
                public final /* synthetic */ Room f$1;
                public final /* synthetic */ TwilioException f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.this.lambda$onConnectFailure$1$Room$1(this.f$1, this.f$2);
                }
            });
        }

        public /* synthetic */ void lambda$onConnectFailure$1$Room$1(Room room, TwilioException twilioException) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onConnectFailure()");
            State unused = Room.this.state = State.DISCONNECTED;
            Room.this.release();
            Room.this.listener.onConnectFailure(room, twilioException);
        }

        public void onReconnecting(Room room, TwilioException twilioException) {
            Room.this.handler.post(new Runnable(room, twilioException) {
                public final /* synthetic */ Room f$1;
                public final /* synthetic */ TwilioException f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.this.lambda$onReconnecting$2$Room$1(this.f$1, this.f$2);
                }
            });
        }

        public /* synthetic */ void lambda$onReconnecting$2$Room$1(Room room, TwilioException twilioException) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onReconnecting()");
            State unused = Room.this.state = State.RECONNECTING;
            Room.this.listener.onReconnecting(room, twilioException);
        }

        public void onReconnected(Room room) {
            Room.this.handler.post(new Runnable(room) {
                public final /* synthetic */ Room f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    Room.AnonymousClass1.this.lambda$onReconnected$3$Room$1(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onReconnected$3$Room$1(Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onReconnected()");
            State unused = Room.this.state = State.CONNECTED;
            Room.this.listener.onReconnected(room);
        }

        public void onDisconnected(Room room, TwilioException twilioException) {
            Room.this.releaseRoom();
            if (Room.this.localParticipant != null) {
                Room.this.localParticipant.release();
            }
            Room.this.handler.post(new Runnable(room, twilioException) {
                public final /* synthetic */ Room f$1;
                public final /* synthetic */ TwilioException f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.this.lambda$onDisconnected$4$Room$1(this.f$1, this.f$2);
                }
            });
        }

        public /* synthetic */ void lambda$onDisconnected$4$Room$1(Room room, TwilioException twilioException) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onDisconnected()");
            State unused = Room.this.state = State.DISCONNECTED;
            Room.this.release();
            Room.this.listener.onDisconnected(room, twilioException);
        }

        public void onParticipantConnected(Room room, RemoteParticipant remoteParticipant) {
            Room.this.handler.post(new Runnable(remoteParticipant, room) {
                public final /* synthetic */ RemoteParticipant f$1;
                public final /* synthetic */ Room f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.this.lambda$onParticipantConnected$5$Room$1(this.f$1, this.f$2);
                }
            });
        }

        public /* synthetic */ void lambda$onParticipantConnected$5$Room$1(RemoteParticipant remoteParticipant, Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onParticipantConnected()");
            Room.this.participantMap.put(remoteParticipant.getSid(), remoteParticipant);
            Room.this.listener.onParticipantConnected(room, remoteParticipant);
        }

        public void onParticipantDisconnected(Room room, RemoteParticipant remoteParticipant) {
            remoteParticipant.release();
            Room.this.handler.post(new Runnable(remoteParticipant, room) {
                public final /* synthetic */ RemoteParticipant f$1;
                public final /* synthetic */ Room f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.this.lambda$onParticipantDisconnected$6$Room$1(this.f$1, this.f$2);
                }
            });
        }

        public /* synthetic */ void lambda$onParticipantDisconnected$6$Room$1(RemoteParticipant remoteParticipant, Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onParticipantDisconnected()");
            Room.this.participantMap.remove(remoteParticipant.getSid());
            Room.this.listener.onParticipantDisconnected(room, remoteParticipant);
        }

        public void onDominantSpeakerChanged(Room room, RemoteParticipant remoteParticipant) {
            Room.this.handler.post(new Runnable(remoteParticipant, room) {
                public final /* synthetic */ RemoteParticipant f$1;
                public final /* synthetic */ Room f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Room.AnonymousClass1.this.lambda$onDominantSpeakerChanged$7$Room$1(this.f$1, this.f$2);
                }
            });
        }

        public /* synthetic */ void lambda$onDominantSpeakerChanged$7$Room$1(RemoteParticipant remoteParticipant, Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onDominantSpeakerChanged()");
            RemoteParticipant unused = Room.this.dominantSpeaker = remoteParticipant;
            Room.this.listener.onDominantSpeakerChanged(room, remoteParticipant);
        }

        public void onRecordingStarted(Room room) {
            Room.this.handler.post(new Runnable(room) {
                public final /* synthetic */ Room f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    Room.AnonymousClass1.this.lambda$onRecordingStarted$8$Room$1(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onRecordingStarted$8$Room$1(Room room) {
            ThreadChecker.checkIsValidThread(Room.this.handler);
            Room.logger.d("onRecordingStarted()");
            Room.this.listener.onRecordingStarted(room);
        }

        public void onRecordingStopped(Room room) {
            Room.this.handler.post(new Runnable(room) {
                public final /* synthetic */ Room f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    Room.AnonymousClass1.this.lambda$onRecordingStopped$9$Room$1(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onRecordingStopped$9$Room$1(Room room) {
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
            Room.this.lambda$new$1$Room(list);
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
            Field declaredField = WebRtcAudioManager.class.getDeclaredField("blacklistDeviceForOpenSLESUsage");
            blacklistDeviceForOpenSLESUsageField = declaredField;
            Field declaredField2 = WebRtcAudioManager.class.getDeclaredField("blacklistDeviceForOpenSLESUsageIsOverridden");
            blacklistDeviceForOpenSLESUsageIsOverriddenField = declaredField2;
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public /* synthetic */ void lambda$new$1$Room(List list) {
        Pair poll = this.statsListenersQueue.poll();
        if (poll != null) {
            ((Handler) poll.first).post(new Runnable(poll, list) {
                public final /* synthetic */ Pair f$0;
                public final /* synthetic */ List f$1;

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

    Room(Context context2, String str, Handler handler2, Listener listener2) {
        this.context = context2;
        this.name = str;
        this.sid = "";
        this.state = State.DISCONNECTED;
        this.listener = listener2;
        this.handler = handler2;
        this.statsListenersQueue = new ConcurrentLinkedQueue();
        configureOpenSLES();
    }

    public String getName() {
        return this.name;
    }

    public String getSid() {
        return this.sid;
    }

    public String getMediaRegion() {
        return this.mediaRegion;
    }

    public synchronized State getState() {
        return this.state;
    }

    public RemoteParticipant getDominantSpeaker() {
        return this.dominantSpeaker;
    }

    public synchronized boolean isRecording() {
        return this.state == State.CONNECTED && nativeIsRecording(this.nativeRoomDelegate);
    }

    public synchronized List<RemoteParticipant> getRemoteParticipants() {
        return new ArrayList(this.participantMap.values());
    }

    public synchronized LocalParticipant getLocalParticipant() {
        return this.localParticipant;
    }

    public synchronized void getStats(StatsListener statsListener) {
        Preconditions.checkNotNull(statsListener, "StatsListener must not be null");
        if (this.state != State.DISCONNECTED) {
            this.statsListenersQueue.offer(new Pair(Util.createCallbackHandler(), statsListener));
            nativeGetStats(this.nativeRoomDelegate);
        }
    }

    public synchronized void disconnect() {
        if (!(this.state == State.DISCONNECTED || this.nativeRoomDelegate == 0)) {
            LocalParticipant localParticipant2 = this.localParticipant;
            if (localParticipant2 != null) {
                localParticipant2.release();
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
    public void connect(ConnectOptions connectOptions) {
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
            this.nativeRoomDelegate = nativeConnect(connectOptions, this.roomListenerProxy, this.statsListenerProxy, mediaFactory2.getNativeMediaFactoryHandle(), this.handler);
            this.state = State.CONNECTING;
        }
    }

    static void configureOpenSLES() {
        if (!openSLESEnabled()) {
            WebRtcAudioManager.setBlacklistDeviceForOpenSLESUsage(true);
        }
    }

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
        String str3 = this.name;
        if (str3 == null || str3.isEmpty()) {
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
        long j = this.nativeRoomDelegate;
        if (j != 0) {
            nativeRelease(j);
            this.nativeRoomDelegate = 0;
            this.mediaFactory.release(this);
        }
    }

    private void cleanupStatsListenerQueue() {
        for (Pair pair : this.statsListenersQueue) {
            ((Handler) pair.first).post(new Runnable(pair) {
                public final /* synthetic */ Pair f$0;

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
        void onConnectFailure(Room room, TwilioException twilioException);

        void onConnected(Room room);

        void onDisconnected(Room room, TwilioException twilioException);

        void onDominantSpeakerChanged(Room room, RemoteParticipant remoteParticipant);

        void onParticipantConnected(Room room, RemoteParticipant remoteParticipant);

        void onParticipantDisconnected(Room room, RemoteParticipant remoteParticipant);

        void onReconnected(Room room);

        void onReconnecting(Room room, TwilioException twilioException);

        void onRecordingStarted(Room room);

        void onRecordingStopped(Room room);

        /* renamed from: com.twilio.video.Room$Listener$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onDominantSpeakerChanged(Listener _this, Room room, RemoteParticipant remoteParticipant) {
                Room.logger.d("onDominantSpeakerChanged");
            }
        }
    }
}
