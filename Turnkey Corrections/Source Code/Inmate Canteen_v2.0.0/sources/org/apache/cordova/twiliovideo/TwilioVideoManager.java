package org.apache.cordova.twiliovideo;

import org.json.JSONObject;

public class TwilioVideoManager {
    private static TwilioVideoManager instance;
    private CallActionObserver actionListener;
    private CallEventObserver eventListener;

    public static TwilioVideoManager getInstance() {
        if (instance == null) {
            instance = new TwilioVideoManager();
        }
        return instance;
    }

    public void setEventObserver(CallEventObserver callEventObserver) {
        this.eventListener = callEventObserver;
    }

    public void setActionListenerObserver(CallActionObserver callActionObserver) {
        this.actionListener = callActionObserver;
    }

    public void publishEvent(CallEvent callEvent) {
        publishEvent(callEvent, (JSONObject) null);
    }

    public void publishEvent(CallEvent callEvent, JSONObject jSONObject) {
        if (hasEventListener()) {
            this.eventListener.onEvent(callEvent.name(), jSONObject);
        }
    }

    public boolean publishDisconnection() {
        if (!hasActionListener()) {
            return false;
        }
        this.actionListener.onDisconnect();
        return true;
    }

    private boolean hasEventListener() {
        return this.eventListener != null;
    }

    private boolean hasActionListener() {
        return this.actionListener != null;
    }
}
