package com.pusher.client.channel.impl;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.Authorizer;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.PresenceChannel;
import com.pusher.client.channel.PresenceChannelEventListener;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.channel.User;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.util.Factory;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PresenceChannelImpl extends PrivateChannelImpl implements PresenceChannel {
    private static final Gson GSON = new Gson();
    private static final String MEMBER_ADDED_EVENT = "pusher_internal:member_added";
    private static final String MEMBER_REMOVED_EVENT = "pusher_internal:member_removed";
    private final Map<String, User> idToUserMap = Collections.synchronizedMap(new LinkedHashMap());
    private String myUserID;

    public PresenceChannelImpl(InternalConnection internalConnection, String str, Authorizer authorizer, Factory factory) {
        super(internalConnection, str, authorizer, factory);
    }

    public Set<User> getUsers() {
        return new LinkedHashSet(this.idToUserMap.values());
    }

    public User getMe() {
        return this.idToUserMap.get(this.myUserID);
    }

    public void onMessage(String str, String str2) {
        super.onMessage(str, str2);
        if (str.equals("pusher_internal:subscription_succeeded")) {
            handleSubscriptionSuccessfulMessage(str2);
        } else if (str.equals(MEMBER_ADDED_EVENT)) {
            handleMemberAddedEvent(str2);
        } else if (str.equals(MEMBER_REMOVED_EVENT)) {
            handleMemberRemovedEvent(str2);
        }
    }

    public String toSubscribeMessage() {
        String subscribeMessage = super.toSubscribeMessage();
        this.myUserID = extractUserIdFromChannelData(this.channelData);
        return subscribeMessage;
    }

    public void bind(String str, SubscriptionEventListener subscriptionEventListener) {
        if (subscriptionEventListener instanceof PresenceChannelEventListener) {
            super.bind(str, subscriptionEventListener);
            return;
        }
        throw new IllegalArgumentException("Only instances of PresenceChannelEventListener can be bound to a presence channel");
    }

    /* access modifiers changed from: protected */
    public String[] getDisallowedNameExpressions() {
        return new String[]{"^(?!presence-).*"};
    }

    public String toString() {
        return String.format("[Presence Channel: name=%s]", new Object[]{this.name});
    }

    private void handleSubscriptionSuccessfulMessage(String str) {
        PresenceData extractPresenceDataFrom = extractPresenceDataFrom(str);
        List<String> list = extractPresenceDataFrom.ids;
        Map<String, Object> map = extractPresenceDataFrom.hash;
        if (list != null && !list.isEmpty()) {
            for (String next : list) {
                this.idToUserMap.put(next, new User(next, map.get(next) != null ? GSON.toJson(map.get(next)) : null));
            }
        }
        ChannelEventListener eventListener = getEventListener();
        if (eventListener != null) {
            ((PresenceChannelEventListener) eventListener).onUsersInformationReceived(getName(), getUsers());
        }
    }

    private void handleMemberAddedEvent(String str) {
        String extractDataStringFrom = extractDataStringFrom(str);
        Gson gson = GSON;
        MemberData memberData = (MemberData) gson.fromJson(extractDataStringFrom, MemberData.class);
        String str2 = memberData.userId;
        User user = new User(str2, memberData.userInfo != null ? gson.toJson(memberData.userInfo) : null);
        this.idToUserMap.put(str2, user);
        ChannelEventListener eventListener = getEventListener();
        if (eventListener != null) {
            ((PresenceChannelEventListener) eventListener).userSubscribed(getName(), user);
        }
    }

    private void handleMemberRemovedEvent(String str) {
        User remove = this.idToUserMap.remove(((MemberData) GSON.fromJson(extractDataStringFrom(str), MemberData.class)).userId);
        ChannelEventListener eventListener = getEventListener();
        if (eventListener != null) {
            ((PresenceChannelEventListener) eventListener).userUnsubscribed(getName(), remove);
        }
    }

    private static String extractDataStringFrom(String str) {
        return (String) ((Map) GSON.fromJson(str, Map.class)).get("data");
    }

    private static PresenceData extractPresenceDataFrom(String str) {
        return ((Presence) GSON.fromJson(extractDataStringFrom(str), Presence.class)).presence;
    }

    private String extractUserIdFromChannelData(String str) {
        try {
            try {
                Object obj = ((Map) GSON.fromJson(str, Map.class)).get("user_id");
                if (obj != null) {
                    return String.valueOf(obj);
                }
                throw new AuthorizationFailureException("Invalid response from Authorizer: no user_id key in channel_data object: " + str);
            } catch (NullPointerException unused) {
                throw new AuthorizationFailureException("Invalid response from Authorizer: no user_id key in channel_data object: " + str);
            }
        } catch (JsonSyntaxException e) {
            throw new AuthorizationFailureException("Invalid response from Authorizer: unable to parse channel_data object: " + str, e);
        }
    }

    private class MemberData {
        @SerializedName("user_id")
        public String userId;
        @SerializedName("user_info")
        public Object userInfo;

        private MemberData() {
        }
    }

    private class PresenceData {
        @SerializedName("count")
        public Integer count;
        @SerializedName("hash")
        public Map<String, Object> hash;
        @SerializedName("ids")
        public List<String> ids;

        private PresenceData() {
        }
    }

    private class Presence {
        @SerializedName("presence")
        public PresenceData presence;

        private Presence() {
        }
    }
}
