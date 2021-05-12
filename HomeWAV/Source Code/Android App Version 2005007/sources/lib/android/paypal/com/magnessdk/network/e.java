package lib.android.paypal.com.magnessdk.network;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

public class e {
    public static HostnameVerifier a() {
        return HttpsURLConnection.getDefaultHostnameVerifier();
    }
}
