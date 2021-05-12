package com.twilio.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @NonNull
    public static String getVersion() {
        return BuildConfig.VERSION_NAME;
    }

    private static native int nativeGetCoreLogLevel();

    private static native void nativeSetCoreLogLevel(int i);

    private static native void nativeSetModuleLevel(int i, int i2);

    @NonNull
    public static synchronized Room connect(@NonNull Context context, @NonNull ConnectOptions connectOptions, @NonNull Room.Listener listener) {
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
            if (rooms.isEmpty()) {
                currentNetworkInfo = ((ConnectivityManager) applicationContext.getSystemService("connectivity")).getActiveNetworkInfo();
                registerConnectivityBroadcastReceiver();
            }
            room = new Room(applicationContext, connectOptions.getRoomName(), Util.createCallbackHandler(), roomListenerProxy(listener));
            rooms.add(room);
            room.connect(connectOptions);
        }
        return room;
    }

    static synchronized void release(Room room) {
        synchronized (Video.class) {
            rooms.remove(room);
            if (rooms.isEmpty()) {
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
            public void onConnected(@NonNull Room room) {
                listener.onConnected(room);
            }

            public void onConnectFailure(@NonNull Room room, @NonNull TwilioException twilioException) {
                listener.onConnectFailure(room, twilioException);
                Video.release(room);
            }

            public void onReconnecting(@NonNull Room room, @NonNull TwilioException twilioException) {
                listener.onReconnecting(room, twilioException);
            }

            public void onReconnected(@NonNull Room room) {
                listener.onReconnected(room);
            }

            public void onDisconnected(@NonNull Room room, @Nullable TwilioException twilioException) {
                listener.onDisconnected(room, twilioException);
                Video.release(room);
            }

            public void onParticipantConnected(@NonNull Room room, @NonNull RemoteParticipant remoteParticipant) {
                listener.onParticipantConnected(room, remoteParticipant);
            }

            public void onParticipantDisconnected(@NonNull Room room, @NonNull RemoteParticipant remoteParticipant) {
                listener.onParticipantDisconnected(room, remoteParticipant);
            }

            public void onDominantSpeakerChanged(@NonNull Room room, @Nullable RemoteParticipant remoteParticipant) {
                listener.onDominantSpeakerChanged(room, remoteParticipant);
            }

            public void onRecordingStarted(@NonNull Room room) {
                listener.onRecordingStarted(room);
            }

            public void onRecordingStopped(@NonNull Room room) {
                listener.onRecordingStopped(room);
            }
        };
    }

    @NonNull
    public static LogLevel getLogLevel() {
        return LogLevel.values()[tryGetCoreLogLevel()];
    }

    public static void setLogLevel(@NonNull LogLevel logLevel) {
        Preconditions.checkNotNull(logLevel, "LogLevel should not be null");
        setSDKLogLevel(logLevel);
        trySetCoreLogLevel(logLevel.ordinal());
        level = logLevel;
    }

    public static void setModuleLogLevel(@NonNull LogModule logModule, @NonNull LogLevel logLevel) {
        Preconditions.checkNotNull(logModule, "LogModule should not be null");
        Preconditions.checkNotNull(logLevel, "LogLevel should not be null");
        if (logModule == LogModule.PLATFORM) {
            setSDKLogLevel(logLevel);
        }
        trySetCoreModuleLogLevel(logModule.ordinal(), logLevel.ordinal());
        moduleLogLevel.put(logModule, logLevel);
    }

    private static void setSDKLogLevel(LogLevel logLevel) {
        switch (logLevel) {
            case OFF:
                Logger.setLogLevel(7);
                return;
            case FATAL:
                Logger.setLogLevel(6);
                return;
            case ERROR:
                Logger.setLogLevel(6);
                return;
            case WARNING:
                Logger.setLogLevel(5);
                return;
            case INFO:
                Logger.setLogLevel(4);
                return;
            case DEBUG:
                Logger.setLogLevel(3);
                return;
            case TRACE:
                Logger.setLogLevel(2);
                return;
            case ALL:
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
