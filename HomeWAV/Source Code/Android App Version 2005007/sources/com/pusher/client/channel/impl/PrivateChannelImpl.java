package com.pusher.client.channel.impl;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.Authorizer;
import com.pusher.client.channel.ChannelState;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.util.Factory;
import java.util.LinkedHashMap;
import java.util.Map;

public class PrivateChannelImpl extends ChannelImpl implements PrivateChannel {
    private static final String CLIENT_EVENT_PREFIX = "client-";
    private static final Gson GSON = new Gson();
    private final Authorizer authorizer;
    protected String channelData;
    private final InternalConnection connection;

    public PrivateChannelImpl(InternalConnection internalConnection, String str, Authorizer authorizer2, Factory factory) {
        super(str, factory);
        this.connection = internalConnection;
        this.authorizer = authorizer2;
    }

    public void trigger(String str, String str2) {
        if (str == null || !str.startsWith(CLIENT_EVENT_PREFIX)) {
            throw new IllegalArgumentException("Cannot trigger event " + str + ": client events must start with \"client-\"");
        } else if (this.state != ChannelState.SUBSCRIBED) {
            throw new IllegalStateException("Cannot trigger event " + str + " because channel " + this.name + " is in " + this.state.toString() + " state");
        } else if (this.connection.getState() == ConnectionState.CONNECTED) {
            try {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("event", str);
                linkedHashMap.put(Modules.CHANNEL_MODULE, this.name);
                linkedHashMap.put("data", str2);
                this.connection.sendMessage(GSON.toJson((Object) linkedHashMap));
            } catch (JsonSyntaxException unused) {
                throw new IllegalArgumentException("Cannot trigger event " + str + " because \"" + str2 + "\" could not be parsed as valid JSON");
            }
        } else {
            throw new IllegalStateException("Cannot trigger event " + str + " because connection is in " + this.connection.getState().toString() + " state");
        }
    }

    public void bind(String str, SubscriptionEventListener subscriptionEventListener) {
        if (subscriptionEventListener instanceof PrivateChannelEventListener) {
            super.bind(str, subscriptionEventListener);
            return;
        }
        throw new IllegalArgumentException("Only instances of PrivateChannelEventListener can be bound to a private channel");
    }

    public String toSubscribeMessage() {
        String authResponse = getAuthResponse();
        try {
            Gson gson = GSON;
            Map map = (Map) gson.fromJson(authResponse, Map.class);
            this.channelData = (String) map.get("channel_data");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("event", "pusher:subscribe");
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put(Modules.CHANNEL_MODULE, this.name);
            linkedHashMap2.put("auth", (String) map.get("auth"));
            String str = this.channelData;
            if (str != null) {
                linkedHashMap2.put("channel_data", str);
            }
            linkedHashMap.put("data", linkedHashMap2);
            return gson.toJson((Object) linkedHashMap);
        } catch (Exception e) {
            throw new AuthorizationFailureException("Unable to parse response from Authorizer: " + authResponse, e);
        }
    }

    /* access modifiers changed from: protected */
    public String[] getDisallowedNameExpressions() {
        return new String[]{"^(?!private-).*", "^private-encrypted-.*"};
    }

    /* access modifiers changed from: protected */
    public String getAuthResponse() {
        return this.authorizer.authorize(getName(), this.connection.getSocketId());
    }

    public String toString() {
        return String.format("[Private Channel: name=%s]", new Object[]{this.name});
    }
}
