package com.urbanairship.remotedata;

import android.os.Build;
import com.microsoft.appcenter.http.DefaultHttpClient;
import com.urbanairship.Logger;
import com.urbanairship.PushProviders;
import com.urbanairship.UAirship;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.config.UrlBuilder;
import com.urbanairship.http.Request;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.http.Response;
import com.urbanairship.push.PushProvider;
import com.urbanairship.util.UAStringUtil;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class RemoteDataApiClient {
    private static final String AMAZON = "amazon";
    private static final String ANDROID = "android";
    private static final String COUNTRY_QUERY_PARAM = "country";
    private static final String LANGUAGE_QUERY_PARAM = "language";
    private static final List<String> MANUFACTURERS_ALLOWED = Collections.singletonList("huawei");
    private static final String MANUFACTURER_QUERY_PARAM = "manufacturer";
    private static final String PUSH_PROVIDER_QUERY_PARAM = "push_providers";
    private static final String REMOTE_DATA_PATH = "api/remote-data/app/";
    private static final String SDK_VERSION_QUERY_PARAM = "sdk_version";
    private final PushProviders pushProviders;
    private final RequestFactory requestFactory;
    private final AirshipRuntimeConfig runtimeConfig;

    RemoteDataApiClient(AirshipRuntimeConfig airshipRuntimeConfig, PushProviders pushProviders2) {
        this(airshipRuntimeConfig, pushProviders2, RequestFactory.DEFAULT_REQUEST_FACTORY);
    }

    RemoteDataApiClient(AirshipRuntimeConfig airshipRuntimeConfig, PushProviders pushProviders2, RequestFactory requestFactory2) {
        this.runtimeConfig = airshipRuntimeConfig;
        this.pushProviders = pushProviders2;
        this.requestFactory = requestFactory2;
    }

    /* access modifiers changed from: package-private */
    public Response fetchRemoteData(String str, Locale locale) {
        URL remoteDataURL = getRemoteDataURL(locale);
        if (remoteDataURL == null) {
            Logger.debug("Remote Data URL null. Unable to update tagGroups.", new Object[0]);
            return null;
        }
        Request credentials = this.requestFactory.createRequest(DefaultHttpClient.METHOD_GET, remoteDataURL).setCredentials(this.runtimeConfig.getConfigOptions().appKey, this.runtimeConfig.getConfigOptions().appSecret);
        if (str != null) {
            credentials.setHeader("If-Modified-Since", str);
        }
        return credentials.safeExecute();
    }

    private URL getRemoteDataURL(Locale locale) {
        UrlBuilder appendQueryParameter = this.runtimeConfig.getUrlConfig().remoteDataUrl().appendEncodedPath(REMOTE_DATA_PATH).appendPath(this.runtimeConfig.getConfigOptions().appKey).appendPath(this.runtimeConfig.getPlatform() == 1 ? "amazon" : "android").appendQueryParameter("sdk_version", UAirship.getVersion());
        String manufacturer = getManufacturer();
        if (shouldIncludeManufacturer(manufacturer)) {
            appendQueryParameter.appendQueryParameter(MANUFACTURER_QUERY_PARAM, manufacturer);
        }
        String pushProviderCsv = getPushProviderCsv();
        if (pushProviderCsv != null) {
            appendQueryParameter.appendQueryParameter(PUSH_PROVIDER_QUERY_PARAM, pushProviderCsv);
        }
        if (!UAStringUtil.isEmpty(locale.getLanguage())) {
            appendQueryParameter.appendQueryParameter("language", locale.getLanguage());
        }
        if (!UAStringUtil.isEmpty(locale.getCountry())) {
            appendQueryParameter.appendQueryParameter("country", locale.getCountry());
        }
        return appendQueryParameter.build();
    }

    private boolean shouldIncludeManufacturer(String str) {
        return MANUFACTURERS_ALLOWED.contains(str.toLowerCase());
    }

    private static String getManufacturer() {
        String str = Build.MANUFACTURER;
        if (str == null) {
            return "";
        }
        return str.toLowerCase(Locale.US);
    }

    private String getPushProviderCsv() {
        HashSet hashSet = new HashSet();
        for (PushProvider deliveryType : this.pushProviders.getAvailableProviders()) {
            hashSet.add(deliveryType.getDeliveryType());
        }
        if (hashSet.isEmpty()) {
            return null;
        }
        return UAStringUtil.join(hashSet, ",");
    }
}
