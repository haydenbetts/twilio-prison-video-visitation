package lib.android.paypal.com.magnessdk.network;

import com.microsoft.appcenter.http.DefaultHttpClient;
import lib.android.paypal.com.magnessdk.network.httpclient.MagnesNetworking;
import lib.android.paypal.com.magnessdk.network.httpclient.MagnesNetworkingFactory;

public final class MagnesNetworkingFactoryImpl implements MagnesNetworkingFactory {
    public MagnesNetworking createHttpClient(String str) throws Exception {
        str.hashCode();
        return !str.equals(DefaultHttpClient.METHOD_GET) ? !str.equals(DefaultHttpClient.METHOD_POST) ? new g() : new g() : new f();
    }
}
