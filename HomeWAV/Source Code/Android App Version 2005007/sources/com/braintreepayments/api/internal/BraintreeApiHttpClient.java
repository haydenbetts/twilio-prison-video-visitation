package com.braintreepayments.api.internal;

import android.text.TextUtils;
import com.braintreepayments.api.exceptions.BraintreeApiErrorResponse;
import com.braintreepayments.api.exceptions.UnprocessableEntityException;
import com.microsoft.appcenter.Constants;
import java.io.IOException;
import java.net.HttpURLConnection;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocketFactory;

public class BraintreeApiHttpClient extends HttpClient {
    public static final String API_VERSION_2016_10_07 = "2016-10-07";
    private final String mApiVersion;
    private final String mBearer;

    public BraintreeApiHttpClient(String str, String str2) {
        this(str, str2, API_VERSION_2016_10_07);
    }

    public BraintreeApiHttpClient(String str, String str2, String str3) {
        this.mBaseUrl = str;
        this.mBearer = str2;
        this.mApiVersion = str3;
        setUserAgent("braintree/android/3.14.2");
        try {
            setSSLSocketFactory(new TLSSocketFactory(BraintreeApiCertificate.getCertInputStream()));
        } catch (SSLException unused) {
            setSSLSocketFactory((SSLSocketFactory) null);
        }
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection init(String str) throws IOException {
        HttpURLConnection init = super.init(str);
        if (!TextUtils.isEmpty(this.mBearer)) {
            init.setRequestProperty(Constants.AUTHORIZATION_HEADER, "Bearer " + this.mBearer);
        }
        init.setRequestProperty("Braintree-Version", this.mApiVersion);
        return init;
    }

    /* access modifiers changed from: protected */
    public String parseResponse(HttpURLConnection httpURLConnection) throws Exception {
        try {
            return super.parseResponse(httpURLConnection);
        } catch (UnprocessableEntityException e) {
            throw new BraintreeApiErrorResponse(e.getMessage());
        }
    }
}
