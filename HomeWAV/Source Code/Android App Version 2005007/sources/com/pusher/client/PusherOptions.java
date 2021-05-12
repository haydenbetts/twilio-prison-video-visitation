package com.pusher.client;

import com.urbanairship.json.matchers.VersionMatcher;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.util.Properties;

public class PusherOptions {
    private static final long DEFAULT_ACTIVITY_TIMEOUT = 120000;
    private static final long DEFAULT_PONG_TIMEOUT = 30000;
    private static final String LIB_DEV_VERSION = "0.0.0-dev";
    public static final String LIB_VERSION;
    private static final int MAX_RECONNECTION_ATTEMPTS = 6;
    private static final int MAX_RECONNECT_GAP_IN_SECONDS = 30;
    private static final String PUSHER_DOMAIN = "pusher.com";
    private static final String SRC_LIB_DEV_VERSION = "@version@";
    private static final String URI_SUFFIX;
    private static final int WSS_PORT = 443;
    private static final String WSS_SCHEME = "wss";
    private static final int WS_PORT = 80;
    private static final String WS_SCHEME = "ws";
    private long activityTimeout = DEFAULT_ACTIVITY_TIMEOUT;
    private Authorizer authorizer;
    private String host = "ws.pusherapp.com";
    private int maxReconnectGapInSeconds = 30;
    private int maxReconnectionAttempts = 6;
    private long pongTimeout = 30000;
    private Proxy proxy = Proxy.NO_PROXY;
    private boolean useTLS = true;
    private int wsPort = 80;
    private int wssPort = 443;

    static {
        String readVersionFromProperties = readVersionFromProperties();
        LIB_VERSION = readVersionFromProperties;
        URI_SUFFIX = "?client=java-client&protocol=5&version=" + readVersionFromProperties;
    }

    @Deprecated
    public boolean isEncrypted() {
        return this.useTLS;
    }

    @Deprecated
    public PusherOptions setEncrypted(boolean z) {
        this.useTLS = z;
        return this;
    }

    public boolean isUseTLS() {
        return this.useTLS;
    }

    public PusherOptions setUseTLS(boolean z) {
        this.useTLS = z;
        return this;
    }

    public Authorizer getAuthorizer() {
        return this.authorizer;
    }

    public PusherOptions setAuthorizer(Authorizer authorizer2) {
        this.authorizer = authorizer2;
        return this;
    }

    public PusherOptions setHost(String str) {
        this.host = str;
        return this;
    }

    public PusherOptions setWsPort(int i) {
        this.wsPort = i;
        return this;
    }

    public PusherOptions setWssPort(int i) {
        this.wssPort = i;
        return this;
    }

    public PusherOptions setCluster(String str) {
        this.host = "ws-" + str + "." + PUSHER_DOMAIN;
        this.wsPort = 80;
        this.wssPort = 443;
        return this;
    }

    public PusherOptions setActivityTimeout(long j) {
        if (j >= 1000) {
            this.activityTimeout = j;
            return this;
        }
        throw new IllegalArgumentException("Activity timeout must be at least 1,000ms (and is recommended to be much higher)");
    }

    public long getActivityTimeout() {
        return this.activityTimeout;
    }

    public PusherOptions setPongTimeout(long j) {
        if (j >= 1000) {
            this.pongTimeout = j;
            return this;
        }
        throw new IllegalArgumentException("Pong timeout must be at least 1,000ms (and is recommended to be much higher)");
    }

    public PusherOptions setMaxReconnectionAttempts(int i) {
        this.maxReconnectionAttempts = i;
        return this;
    }

    public PusherOptions setMaxReconnectGapInSeconds(int i) {
        this.maxReconnectGapInSeconds = i;
        return this;
    }

    public long getPongTimeout() {
        return this.pongTimeout;
    }

    public String buildUrl(String str) {
        Object[] objArr = new Object[5];
        boolean z = this.useTLS;
        objArr[0] = z ? WSS_SCHEME : WS_SCHEME;
        objArr[1] = this.host;
        objArr[2] = Integer.valueOf(z ? this.wssPort : this.wsPort);
        objArr[3] = str;
        objArr[4] = URI_SUFFIX;
        return String.format("%s://%s:%s/app/%s%s", objArr);
    }

    public PusherOptions setProxy(Proxy proxy2) {
        if (proxy2 != null) {
            this.proxy = proxy2;
            return this;
        }
        throw new IllegalArgumentException("proxy must not be null (instead use Proxy.NO_PROXY)");
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public int getMaxReconnectionAttempts() {
        return this.maxReconnectionAttempts;
    }

    public int getMaxReconnectGapInSeconds() {
        return this.maxReconnectGapInSeconds;
    }

    private static String readVersionFromProperties() {
        InputStream inputStream = null;
        try {
            Properties properties = new Properties();
            inputStream = PusherOptions.class.getResourceAsStream("/pusher.properties");
            properties.load(inputStream);
            String str = (String) properties.get(VersionMatcher.ALTERNATE_VERSION_KEY);
            if (str.equals(SRC_LIB_DEV_VERSION)) {
                str = LIB_DEV_VERSION;
            }
            if (str == null || str.length() <= 0) {
                if (inputStream == null) {
                    return "0.0.0";
                }
                try {
                    inputStream.close();
                    return "0.0.0";
                } catch (IOException unused) {
                    return "0.0.0";
                }
            } else {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                return str;
            }
        } catch (Exception unused3) {
            if (inputStream == null) {
                return "0.0.0";
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }
}
