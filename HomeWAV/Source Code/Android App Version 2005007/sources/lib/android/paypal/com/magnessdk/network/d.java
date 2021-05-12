package lib.android.paypal.com.magnessdk.network;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class d {
    static final String A = "https://c.sandbox.paypal.com/r/v1/device/client-metadata";
    static final String B = "POST";
    static final String C = "GET";
    static final int D = -1;
    static final int E = 200;
    static final String F = "correlation-id";
    protected static final String G = "Network Setup Exception, Check PPNetworkEnvironment for details";
    protected static final String H = "production";
    protected static final String I = "stage";
    static final int J = 60000;
    static final int K = 60000;
    static final int L = 1024;
    public static final String M = "PayPal-Client-Metadata-Id exceeds the maximum length allowed. This is your own unique identifier for the payload. If you do not pass in this value, a new PayPal-Client-Metadata-Id is generated per method call. ***Maximum length: 32 characters***";
    public static final String N = "Application’s Globally Unique Identifier (AppGUID) exceeds maximum length allowed, This is a string that identifies the merchant application that sets up Magnes on the mobile device. If the merchant app does not pass an AppGuid, Magnes creates one to identify the app. An AppGuid is an application identifier per-installation; that is, if a new instance of the app is installed on the mobile device, or the app is reinstalled, it will have a new AppGuid. ***Maximum length: 36 characters***";
    static final int a = 50;
    static final int b = 51;
    static final int c = 52;
    static final int d = 53;
    static final int e = 54;
    static final int f = 55;
    static final int g = 0;
    static final int h = 1;
    static final int i = 2;
    static final int j = 10;
    static final int k = 11;
    static final int l = 12;
    static final int m = 20;
    static final int n = 21;
    static final int o = 22;
    static final int p = 40;
    static final int q = 41;
    static final int r = 42;
    static final int s = 10;
    static final int t = 10;
    static final int u = 60000;
    static final int v = 256;
    public static final String w = "https://www.paypalobjects.com/digitalassets/c/rda-magnes/magnes_config_android_v4.json";
    public static final String x = "https://www.paypalobjects.com/digitalassets/c/rda-magnes/magnes_android_rc_v1.json";
    static final String y = "https://c.paypal.com/r/v1/device/client-metadata";
    static final String z = "https://b.stats.paypal.com/counter.cgi";

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    private d() {
    }
}
