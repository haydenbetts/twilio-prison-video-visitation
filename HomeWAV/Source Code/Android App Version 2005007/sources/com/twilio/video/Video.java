package com.twilio.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.getkeepsafe.relinker.ReLinker;
import com.twilio.video.Room;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Video {
    private static Context applicationContext = null;
    private static final BroadcastReceiver connectivityChangeReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
                return;
            }
            if (isInitialStickyBroadcast()) {
                Video.logger.d("Ignoring network event, sticky broadcast");
                return;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            NetworkChangeEvent networkChangeEvent = NetworkChangeEvent.CONNECTION_CHANGED;
            if (activeNetworkInfo != null && (Video.currentNetworkInfo == null || Video.currentNetworkInfo.getDetailedState() != activeNetworkInfo.getDetailedState() || Video.currentNetworkInfo.getType() != activeNetworkInfo.getType() || Video.currentNetworkInfo.getSubtype() != activeNetworkInfo.getSubtype())) {
                if (!activeNetworkInfo.isConnectedOrConnecting()) {
                    networkChangeEvent = NetworkChangeEvent.CONNECTION_LOST;
                }
                Logger access$000 = Video.logger;
                access$000.d("Network event detected: " + networkChangeEvent.name());
                Video.onNetworkChange(networkChangeEvent);
            } else if (activeNetworkInfo == null) {
                NetworkChangeEvent networkChangeEvent2 = NetworkChangeEvent.CONNECTION_LOST;
                Video.logger.d("Network connection lost");
                Video.onNetworkChange(networkChangeEvent2);
            }
            NetworkInfo unused = Video.currentNetworkInfo = activeNetworkInfo;
        }
    };
    /* access modifiers changed from: private */
    public static NetworkInfo currentNetworkInfo = null;
    private static LogLevel level = LogLevel.OFF;
    private static volatile boolean libraryIsLoaded = false;
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(Video.class);
    private static Map<LogModule, LogLevel> moduleLogLevel = new EnumMap(LogModule.class);
    private static final Set<Room> rooms = new HashSet();

    enum NetworkChangeEvent {
        CONNECTION_LOST,
        CONNECTION_CHANGED
    }

    public static String getVersion() {
        return BuildConfig.VERSION_NAME;
    }

    private static native int nativeGetCoreLogLevel();

    private static native void nativeSetCoreLogLevel(int i);

    private static native void nativeSetModuleLevel(int i, int i2);

    public static synchronized Room connect(Context context, ConnectOptions connectOptions, Room.Listener listener) {
        Room room;
        synchronized (Video.class) {
            Preconditions.checkNotNull(context, "context must not be null");
            Preconditions.checkNotNull(connectOptions, "connectOptions must not be null");
            Preconditions.checkNotNull(listener, "roomListener must not be null");
            if (applicationContext == null) {
                applicationContext = context.getApplicationContext();
            }
            if (!libraryIsLoaded) {
                ReLinker.loadLibrary(applicationContext, BuildConfig.TWILIO_VIDEO_ANDROID_LIBRARY);
                libraryIsLoaded = true;
                trySetCoreLogLevel(level.ordinal());
                for (LogModule next : moduleLogLevel.keySet()) {
                    trySetCoreModuleLogLevel(next.ordinal(), moduleLogLevel.get(next).ordinal());
                }
            }
            Set<Room> set = rooms;
            if (set.isEmpty()) {
                currentNetworkInfo = ((ConnectivityManager) applicationContext.getSystemService("connectivity")).getActiveNetworkInfo();
                registerConnectivityBroadcastReceiver();
            }
            room = new Room(applicationContext, connectOptions.getRoomName(), Util.createCallbackHandler(), roomListenerProxy(listener));
            set.add(room);
            room.connect(connectOptions);
        }
        return room;
    }

    static synchronized void release(Room room) {
        synchronized (Video.class) {
            Set<Room> set = rooms;
            set.remove(room);
            if (set.isEmpty()) {
                unregisterConnectivityBroadcastReceiver();
                PlatformInfo.release();
            }
        }
    }

    /* access modifiers changed from: private */
    public static synchronized void onNetworkChange(NetworkChangeEvent networkChangeEvent) {
        synchronized (Video.class) {
            for (Room onNetworkChanged : rooms) {
                onNetworkChanged.onNetworkChanged(networkChangeEvent);
            }
        }
    }

    private static Room.Listener roomListenerProxy(final Room.Listener listener) {
        return new Room.Listener() {
            public void onConnected(Room room) {
                listener.onConnected(room);
            }

            public void onConnectFailure(Room room, TwilioException twilioException) {
                listener.onConnectFailure(room, twilioException);
                Video.release(room);
            }

            public void onReconnecting(Room room, TwilioException twilioException) {
                listener.onReconnecting(room, twilioException);
            }

            public void onReconnected(Room room) {
                listener.onReconnected(room);
            }

            public void onDisconnected(Room room, TwilioException twilioException) {
                listener.onDisconnected(room, twilioException);
                Video.release(room);
            }

            public void onParticipantConnected(Room room, RemoteParticipant remoteParticipant) {
                listener.onParticipantConnected(room, remoteParticipant);
            }

            public void onParticipantDisconnected(Room room, RemoteParticipant remoteParticipant) {
                listener.onParticipantDisconnected(room, remoteParticipant);
            }

            public void onDominantSpeakerChanged(Room room, RemoteParticipant remoteParticipant) {
                listener.onDominantSpeakerChanged(room, remoteParticipant);
            }

            public void onRecordingStarted(Room room) {
                listener.onRecordingStarted(room);
            }

            public void onRecordingStopped(Room room) {
                listener.onRecordingStopped(room);
            }
        };
    }

    public static LogLevel getLogLevel() {
        return LogLevel.values()[tryGetCoreLogLevel()];
    }

    public static void setLogLevel(LogLevel logLevel) {
        Preconditions.checkNotNull(logLevel, "LogLevel should not be null");
        setSDKLogLevel(logLevel);
        trySetCoreLogLevel(logLevel.ordinal());
        level = logLevel;
    }

    public static void setModuleLogLevel(LogModule logModule, LogLevel logLevel) {
        Preconditions.checkNotNull(logModule, "LogModule should not be null");
        Preconditions.checkNotNull(logLevel, "LogLevel should not be null");
        if (logModule == LogModule.PLATFORM) {
            setSDKLogLevel(logLevel);
        }
        trySetCoreModuleLogLevel(logModule.ordinal(), logLevel.ordinal());
        moduleLogLevel.put(logModule, logLevel);
    }

    /* renamed from: com.twilio.video.Video$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$twilio$video$LogLevel;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.twilio.video.LogLevel[] r0 = com.twilio.video.LogLevel.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$twilio$video$LogLevel = r0
                com.twilio.video.LogLevel r1 = com.twilio.video.LogLevel.OFF     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$twilio$video$LogLevel     // Catch:{ NoSuchFieldError -> 0x001d }
                com.twilio.video.LogLevel r1 = com.twilio.video.LogLevel.FATAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$twilio$video$LogLevel     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.twilio.video.LogLevel r1 = com.twilio.video.LogLevel.ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$twilio$video$LogLevel     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.twilio.video.LogLevel r1 = com.twilio.video.LogLevel.WARNING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$twilio$video$LogLevel     // Catch:{ NoSuchFieldError -> 0x003e }
                com.twilio.video.LogLevel r1 = com.twilio.video.LogLevel.INFO     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$twilio$video$LogLevel     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.twilio.video.LogLevel r1 = com.twilio.video.LogLevel.DEBUG     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$twilio$video$LogLevel     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.twilio.video.LogLevel r1 = com.twilio.video.LogLevel.TRACE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$twilio$video$LogLevel     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.twilio.video.LogLevel r1 = com.twilio.video.LogLevel.ALL     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.twilio.video.Video.AnonymousClass3.<clinit>():void");
        }
    }

    private static void setSDKLogLevel(LogLevel logLevel) {
        switch (AnonymousClass3.$SwitchMap$com$twilio$video$LogLevel[logLevel.ordinal()]) {
            case 1:
                Logger.setLogLevel(7);
                return;
            case 2:
                Logger.setLogLevel(6);
                return;
            case 3:
                Logger.setLogLevel(6);
                return;
            case 4:
                Logger.setLogLevel(5);
                return;
            case 5:
                Logger.setLogLevel(4);
                return;
            case 6:
                Logger.setLogLevel(3);
                return;
            case 7:
                Logger.setLogLevel(2);
                return;
            case 8:
                Logger.setLogLevel(2);
                return;
            default:
                Logger.setLogLevel(7);
                return;
        }
    }

    private static void trySetCoreLogLevel(int i) {
        if (libraryIsLoaded) {
            nativeSetCoreLogLevel(i);
        }
    }

    private static int tryGetCoreLogLevel() {
        return libraryIsLoaded ? nativeGetCoreLogLevel() : level.ordinal();
    }

    private static void trySetCoreModuleLogLevel(int i, int i2) {
        if (libraryIsLoaded) {
            nativeSetModuleLevel(i, i2);
        }
    }

    private static void registerConnectivityBroadcastReceiver() {
        Context context = applicationContext;
        if (context != null) {
            context.registerReceiver(connectivityChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    private static void unregisterConnectivityBroadcastReceiver() {
        Context context = applicationContext;
        if (context != null) {
            context.unregisterReceiver(connectivityChangeReceiver);
        }
    }
}
