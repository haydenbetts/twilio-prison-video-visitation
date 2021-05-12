package com.urbanairship.automation.auth;

import android.util.Base64;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.http.DefaultHttpClient;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.Request;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.http.Response;
import com.urbanairship.http.ResponseParser;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Clock;
import com.urbanairship.util.UAHttpStatusUtil;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

class AuthApiClient {
    private static final String AUTH_PATH = "api/auth/device";
    private static final String EXPIRES_KEY = "expires_in";
    private static final String TOKEN_KEY = "token";
    private final Clock clock;
    private final RequestFactory requestFactory;
    private final AirshipRuntimeConfig runtimeConfig;

    public AuthApiClient(AirshipRuntimeConfig airshipRuntimeConfig) {
        this(airshipRuntimeConfig, Clock.DEFAULT_CLOCK, RequestFactory.DEFAULT_REQUEST_FACTORY);
    }

    AuthApiClient(AirshipRuntimeConfig airshipRuntimeConfig, Clock clock2, RequestFactory requestFactory2) {
        this.runtimeConfig = airshipRuntimeConfig;
        this.clock = clock2;
        this.requestFactory = requestFactory2;
    }

    public Response<AuthToken> getToken(final String str) throws RequestException {
        URL build = this.runtimeConfig.getUrlConfig().deviceUrl().appendEncodedPath(AUTH_PATH).build();
        try {
            String createBearerToken = createBearerToken(str);
            final long currentTimeMillis = this.clock.currentTimeMillis();
            Request header = this.requestFactory.createRequest().setOperation(DefaultHttpClient.METHOD_GET, build).setAirshipJsonAcceptsHeader().setHeader("X-UA-App-Key", this.runtimeConfig.getConfigOptions().appKey).setHeader("X-UA-Channel", str);
            return header.setHeader(Constants.AUTHORIZATION_HEADER, "Bearer " + createBearerToken).execute(new ResponseParser<AuthToken>() {
                public AuthToken parseResponse(int i, Map<String, List<String>> map, String str) throws Exception {
                    if (UAHttpStatusUtil.inSuccessRange(i)) {
                        return AuthApiClient.parseResponse(str, str, currentTimeMillis);
                    }
                    return null;
                }
            });
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RequestException("Unable to create bearer token.", e);
        }
    }

    private String createBearerToken(String str) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Mac instance = Mac.getInstance("HmacSHA256");
        instance.init(new SecretKeySpec(this.runtimeConfig.getConfigOptions().appSecret.getBytes("UTF-8"), "HmacSHA256"));
        return Base64.encodeToString(instance.doFinal((this.runtimeConfig.getConfigOptions().appKey + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + str).getBytes("UTF-8")), 0);
    }

    /* access modifiers changed from: private */
    public static AuthToken parseResponse(String str, String str2, long j) throws JsonException {
        JsonMap optMap = JsonValue.parseString(str).optMap();
        String string = optMap.opt(TOKEN_KEY).getString();
        long j2 = optMap.opt(EXPIRES_KEY).getLong(0);
        if (string != null && j2 > 0) {
            return new AuthToken(str2, string, j + j2);
        }
        throw new JsonException("Invalid response: " + str);
    }
}
