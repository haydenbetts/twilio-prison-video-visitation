package com.urbanairship.automation.auth;

import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.Response;
import com.urbanairship.util.Clock;
import com.urbanairship.util.UAStringUtil;

public class AuthManager {
    private final AuthApiClient apiClient;
    private AuthToken cachedAuth;
    private final Object cachedAuthLock;
    private final AirshipChannel channel;
    private final Clock clock;

    public AuthManager(AirshipRuntimeConfig airshipRuntimeConfig, AirshipChannel airshipChannel) {
        this(new AuthApiClient(airshipRuntimeConfig), airshipChannel, Clock.DEFAULT_CLOCK);
    }

    AuthManager(AuthApiClient authApiClient, AirshipChannel airshipChannel, Clock clock2) {
        this.cachedAuthLock = new Object();
        this.apiClient = authApiClient;
        this.channel = airshipChannel;
        this.clock = clock2;
    }

    public String getToken() throws AuthException {
        String id = this.channel.getId();
        if (id != null) {
            String cachedToken = getCachedToken(id);
            if (cachedToken != null) {
                return cachedToken;
            }
            try {
                Response<AuthToken> token = this.apiClient.getToken(id);
                if (token.getResult() == null || !token.isSuccessful()) {
                    throw new AuthException("Failed to generate token. Response: " + token);
                }
                cache(token.getResult());
                return token.getResult().getToken();
            } catch (RequestException e) {
                throw new AuthException("Failed to generate token.", e);
            }
        } else {
            throw new AuthException("Unable to create token, channel not created");
        }
    }

    public void tokenExpired(String str) {
        synchronized (this.cachedAuthLock) {
            if (str.equals(this.cachedAuth.getToken())) {
                this.cachedAuth = null;
            }
        }
    }

    private void cache(AuthToken authToken) {
        synchronized (this.cachedAuthLock) {
            this.cachedAuth = authToken;
        }
    }

    private String getCachedToken(String str) {
        synchronized (this.cachedAuthLock) {
            if (this.cachedAuth == null) {
                return null;
            }
            if (this.clock.currentTimeMillis() >= this.cachedAuth.getExpiration()) {
                return null;
            }
            if (!UAStringUtil.equals(str, this.cachedAuth.getChannelId())) {
                return null;
            }
            String token = this.cachedAuth.getToken();
            return token;
        }
    }
}
