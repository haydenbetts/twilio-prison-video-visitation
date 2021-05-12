package lib.android.paypal.com.magnessdk.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import lib.android.paypal.com.magnessdk.b.a;

final class o extends SSLSocketFactory {
    private static final String a = "o";
    private static TrustManager[] b;
    private SSLContext c;
    private SSLSocketFactory d;
    private TrustManager[] e;

    private o() {
        try {
            this.c = SSLContext.getInstance("TLS");
            TrustManager[] b2 = b();
            this.e = b2;
            this.c.init((KeyManager[]) null, b2, (SecureRandom) null);
            this.d = this.c.getSocketFactory();
        } catch (Exception e2) {
            a.a(getClass(), 3, (Throwable) e2);
        }
    }

    private Socket a(Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            ArrayList arrayList = new ArrayList(Arrays.asList(sSLSocket.getSupportedProtocols()));
            arrayList.retainAll(Arrays.asList(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1"}));
            sSLSocket.setEnabledProtocols((String[]) arrayList.toArray(new String[arrayList.size()]));
        }
        return socket;
    }

    static o a() {
        return new o();
    }

    private TrustManager[] b() {
        if (b == null) {
            try {
                KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
                instance.load((InputStream) null, (char[]) null);
                for (Certificate certificate : CertificateFactory.getInstance("X.509").generateCertificates(PayPalCertificate.getCertInputStream())) {
                    if (certificate instanceof X509Certificate) {
                        instance.setCertificateEntry(((X509Certificate) certificate).getSubjectDN().getName(), certificate);
                    }
                }
                TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance2.init(instance);
                this.e = instance2.getTrustManagers();
            } catch (Exception e2) {
                a.a(getClass(), 3, (Throwable) e2);
            }
            b = this.e;
        }
        return b;
    }

    public Socket createSocket(String str, int i) throws IOException {
        return a(this.d.createSocket(str, i));
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return a(this.d.createSocket(str, i, inetAddress, i2));
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return a(this.d.createSocket(inetAddress, i));
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return a(this.d.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return a(this.d.createSocket(socket, str, i, z));
    }

    public String[] getDefaultCipherSuites() {
        return this.d.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.d.getSupportedCipherSuites();
    }
}
