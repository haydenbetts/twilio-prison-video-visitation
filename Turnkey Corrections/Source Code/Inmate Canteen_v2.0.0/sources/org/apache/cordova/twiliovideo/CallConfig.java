package org.apache.cordova.twiliovideo;

import java.io.Serializable;
import org.json.JSONObject;

public class CallConfig implements Serializable {
    private static final String HANDLE_ERROR_IN_APP = "handleErrorInApp";
    private static final String HANG_UP_IN_APP = "hangUpInApp";
    private static final String PRIMARY_COLOR_PROP = "primaryColor";
    private static final String SECONDARY_COLOR_PROP = "secondaryColor";
    private static final String i18n_ACCEPT_DEF_TEXT = "Accept";
    private static final String i18n_ACCEPT_PROP = "i18nAccept";
    private static final String i18n_CONNECTION_ERROR_DEF_TEXT = "It was not possible to join the room";
    private static final String i18n_CONNECTION_ERROR_PROP = "i18nConnectionError";
    private static final String i18n_DISCONNECTED_WITH_ERROR_DEF_TEXT = "Disconnected";
    private static final String i18n_DISCONNECTED_WITH_ERROR_PROP = "i18nDisconnectedWithError";
    private boolean handleErrorInApp;
    private boolean hangUpInApp;
    private String i18nAccept;
    private String i18nConnectionError;
    private String i18nDisconnectedWithError;
    private String primaryColorHex;
    private String secondaryColorHex;

    public void parse(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.primaryColorHex = jSONObject.optString(PRIMARY_COLOR_PROP, (String) null);
            this.secondaryColorHex = jSONObject.optString(SECONDARY_COLOR_PROP, (String) null);
            this.i18nConnectionError = jSONObject.optString(i18n_CONNECTION_ERROR_PROP, (String) null);
            if (this.i18nConnectionError == null) {
                this.i18nConnectionError = i18n_CONNECTION_ERROR_DEF_TEXT;
            }
            this.i18nDisconnectedWithError = jSONObject.optString(i18n_DISCONNECTED_WITH_ERROR_PROP, (String) null);
            if (this.i18nDisconnectedWithError == null) {
                this.i18nDisconnectedWithError = i18n_DISCONNECTED_WITH_ERROR_DEF_TEXT;
            }
            this.i18nAccept = jSONObject.optString(i18n_ACCEPT_PROP, (String) null);
            if (this.i18nAccept == null) {
                this.i18nAccept = "Accept";
            }
            this.handleErrorInApp = jSONObject.optBoolean(HANDLE_ERROR_IN_APP, false);
            this.hangUpInApp = jSONObject.optBoolean(HANG_UP_IN_APP, false);
        }
    }

    public String getPrimaryColorHex() {
        return this.primaryColorHex;
    }

    public String getSecondaryColorHex() {
        return this.secondaryColorHex;
    }

    public String getI18nConnectionError() {
        return this.i18nConnectionError;
    }

    public String getI18nDisconnectedWithError() {
        return this.i18nDisconnectedWithError;
    }

    public String getI18nAccept() {
        return this.i18nAccept;
    }

    public boolean isHandleErrorInApp() {
        return this.handleErrorInApp;
    }

    public boolean isHangUpInApp() {
        return this.hangUpInApp;
    }
}
