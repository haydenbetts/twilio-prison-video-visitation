package com.stripe.android;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

class StripeSSLSocketFactory extends SSLSocketFactory {
    private static final String TLS_V11_PROTO = "TLSv1.1";
    private static final String TLS_V12_PROTO = "TLSv1.2";
    private final boolean tlsv11Supported;
    private final boolean tlsv12Supported;
    private final SSLSocketFactory under = HttpsURLConnection.getDefaultSSLSocketFactory();

    StripeSSLSocketFactory() {
        String[] strArr;
        try {
            strArr = SSLContext.getDefault().getSupportedSSLParameters().getProtocols();
        } catch (NoSuchAlgorithmException unused) {
            strArr = new String[0];
        }
        boolean z = false;
        boolean z2 = false;
        for (String str : strArr) {
            if (str.equals(TLS_V11_PROTO)) {
                z = true;
            } else if (str.equals(TLS_V12_PROTO)) {
                z2 = true;
            }
        }
        this.tlsv11Supported = z;
        this.tlsv12Supported = z2;
    }

    public String[] getDefaultCipherSuites() {
        return this.under.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.under.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return fixupSocket(this.under.createSocket(socket, str, i, z));
    }

    public Socket createSocket(String str, int i) throws IOException {
        return fixupSocket(this.under.createSocket(str, i));
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return fixupSocket(this.under.createSocket(str, i, inetAddress, i2));
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return fixupSocket(this.under.createSocket(inetAddress, i));
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return fixupSocket(this.under.createSocket(inetAddress, i, inetAddress2, i2));
    }

    private Socket fixupSocket(Socket socket) {
        if (!(socket instanceof SSLSocket)) {
            return socket;
        }
        SSLSocket sSLSocket = (SSLSocket) socket;
        HashSet hashSet = new HashSet(Arrays.asList(sSLSocket.getEnabledProtocols()));
        if (this.tlsv11Supported) {
            hashSet.add(TLS_V11_PROTO);
        }
        if (this.tlsv12Supported) {
            hashSet.add(TLS_V12_PROTO);
        }
        sSLSocket.setEnabledProtocols((String[]) hashSet.toArray(new String[0]));
        return sSLSocket;
    }
}
