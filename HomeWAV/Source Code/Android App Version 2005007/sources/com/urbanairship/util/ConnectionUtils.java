package com.urbanairship.util;

import android.content.Context;
import android.os.Build;
import com.urbanairship.Logger;
import com.urbanairship.google.NetworkProviderInstaller;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class ConnectionUtils {
    private static boolean isInstalled = false;
    private static boolean skipInstall = false;

    public static URLConnection openSecureConnection(Context context, URL url) throws IOException {
        boolean installProvider = installProvider(context);
        URLConnection openConnection = url.openConnection();
        if (!installProvider && Build.VERSION.SDK_INT <= 19 && (openConnection instanceof HttpsURLConnection)) {
            try {
                ((HttpsURLConnection) openConnection).setSSLSocketFactory(TlsSocketFactory.newFactory());
                Logger.debug("TlsSocketFactory set for HttpsURLConnection", new Object[0]);
            } catch (Exception e) {
                Logger.error(e, "Failed to create TLS SSLSocketFactory.", new Object[0]);
            }
        }
        return openConnection;
    }

    private static synchronized boolean installProvider(Context context) {
        synchronized (ConnectionUtils.class) {
            if (skipInstall) {
                boolean z = isInstalled;
                return z;
            } else if (!ManifestUtils.shouldInstallNetworkSecurityProvider()) {
                skipInstall = true;
                boolean z2 = isInstalled;
                return z2;
            } else {
                int installSecurityProvider = NetworkProviderInstaller.installSecurityProvider(context);
                if (installSecurityProvider == 0) {
                    Logger.info("Network Security Provider installed.", new Object[0]);
                    skipInstall = true;
                    isInstalled = true;
                } else if (installSecurityProvider == 1) {
                    Logger.info("Network Security Provider failed to install with a recoverable error.", new Object[0]);
                } else if (installSecurityProvider == 2) {
                    Logger.info("Network Security Provider failed to install.", new Object[0]);
                    skipInstall = true;
                }
                boolean z3 = isInstalled;
                return z3;
            }
        }
    }

    private static class TlsSocketFactory extends SSLSocketFactory {
        private static final String[] PROTOCOLS = {"TLSv1.2"};
        private SSLSocketFactory baseFactory;

        private TlsSocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.baseFactory = sSLSocketFactory;
        }

        static TlsSocketFactory newFactory() throws KeyManagementException, NoSuchAlgorithmException {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
            return new TlsSocketFactory(instance.getSocketFactory());
        }

        public String[] getDefaultCipherSuites() {
            return this.baseFactory.getDefaultCipherSuites();
        }

        public String[] getSupportedCipherSuites() {
            return this.baseFactory.getSupportedCipherSuites();
        }

        public Socket createSocket() throws IOException {
            return onSocketCreated(this.baseFactory.createSocket());
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return onSocketCreated(this.baseFactory.createSocket(socket, str, i, z));
        }

        public Socket createSocket(String str, int i) throws IOException {
            return onSocketCreated(this.baseFactory.createSocket(str, i));
        }

        public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
            return onSocketCreated(this.baseFactory.createSocket(str, i, inetAddress, i2));
        }

        public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return onSocketCreated(this.baseFactory.createSocket(inetAddress, i));
        }

        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            return onSocketCreated(this.baseFactory.createSocket(inetAddress, i, inetAddress2, i2));
        }

        private Socket onSocketCreated(Socket socket) {
            if (socket instanceof SSLSocket) {
                ((SSLSocket) socket).setEnabledProtocols(PROTOCOLS);
            }
            return socket;
        }
    }
}
