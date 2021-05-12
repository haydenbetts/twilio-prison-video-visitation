package com.pusher.client.channel.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.ChannelState;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.PusherEventDeserializer;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.util.Factory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ChannelImpl implements InternalChannel {
    private static final String INTERNAL_EVENT_PREFIX = "pusher_internal:";
    protected static final String SUBSCRIPTION_SUCCESS_EVENT = "pusher_internal:subscription_succeeded";
    protected final Gson GSON;
    /* access modifiers changed from: private */
    public ChannelEventListener eventListener;
    private final Map<String, Set<SubscriptionEventListener>> eventNameToListenerMap = new HashMap();
    private final Factory factory;
    private final Object lock = new Object();
    protected final String name;
    protected volatile ChannelState state = ChannelState.INITIAL;

    public ChannelImpl(String str, Factory factory2) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PusherEvent.class, new PusherEventDeserializer());
        this.GSON = gsonBuilder.create();
        if (str != null) {
            String[] disallowedNameExpressions = getDisallowedNameExpressions();
            int length = disallowedNameExpressions.length;
            int i = 0;
            while (i < length) {
                if (!str.matches(disallowedNameExpressions[i])) {
                    i++;
                } else {
                    throw new IllegalArgumentException("Channel name " + str + " is invalid. Private channel names must start with \"private-\" and presence channel names must start with \"presence-\"");
                }
            }
            this.name = str;
            this.factory = factory2;
            return;
        }
        throw new IllegalArgumentException("Cannot subscribe to a channel with a null name");
    }

    public String getName() {
        return this.name;
    }

    public void bind(String str, SubscriptionEventListener subscriptionEventListener) {
        validateArguments(str, subscriptionEventListener);
        synchronized (this.lock) {
            Set set = this.eventNameToListenerMap.get(str);
            if (set == null) {
                set = new HashSet();
                this.eventNameToListenerMap.put(str, set);
            }
            set.add(subscriptionEventListener);
        }
    }

    public void unbind(String str, SubscriptionEventListener subscriptionEventListener) {
        validateArguments(str, subscriptionEventListener);
        synchronized (this.lock) {
            Set set = this.eventNameToListenerMap.get(str);
            if (set != null) {
                set.remove(subscriptionEventListener);
                if (set.isEmpty()) {
                    this.eventNameToListenerMap.remove(str);
                }
            }
        }
    }

    public boolean isSubscribed() {
        return this.state == ChannelState.SUBSCRIBED;
    }

    public PusherEvent prepareEvent(String str, String str2) {
        return (PusherEvent) this.GSON.fromJson(str2, PusherEvent.class);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        r4 = prepareEvent(r4, r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMessage(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "pusher_internal:subscription_succeeded"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x000e
            com.pusher.client.channel.ChannelState r4 = com.pusher.client.channel.ChannelState.SUBSCRIBED
            r3.updateState(r4)
            goto L_0x0035
        L_0x000e:
            java.util.Set r0 = r3.getInterestedListeners(r4)
            if (r0 == 0) goto L_0x0035
            com.pusher.client.channel.PusherEvent r4 = r3.prepareEvent(r4, r5)
            if (r4 == 0) goto L_0x0035
            java.util.Iterator r5 = r0.iterator()
        L_0x001e:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0035
            java.lang.Object r0 = r5.next()
            com.pusher.client.channel.SubscriptionEventListener r0 = (com.pusher.client.channel.SubscriptionEventListener) r0
            com.pusher.client.util.Factory r1 = r3.factory
            com.pusher.client.channel.impl.ChannelImpl$1 r2 = new com.pusher.client.channel.impl.ChannelImpl$1
            r2.<init>(r0, r4)
            r1.queueOnEventThread(r2)
            goto L_0x001e
        L_0x0035:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pusher.client.channel.impl.ChannelImpl.onMessage(java.lang.String, java.lang.String):void");
    }

    public String toSubscribeMessage() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("event", "pusher:subscribe");
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(Modules.CHANNEL_MODULE, this.name);
        linkedHashMap.put("data", linkedHashMap2);
        return this.GSON.toJson((Object) linkedHashMap);
    }

    public String toUnsubscribeMessage() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("event", "pusher:unsubscribe");
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(Modules.CHANNEL_MODULE, this.name);
        linkedHashMap.put("data", linkedHashMap2);
        return this.GSON.toJson((Object) linkedHashMap);
    }

    public void updateState(ChannelState channelState) {
        this.state = channelState;
        if (channelState == ChannelState.SUBSCRIBED && this.eventListener != null) {
            this.factory.queueOnEventThread(new Runnable() {
                public void run() {
                    ChannelImpl.this.eventListener.onSubscriptionSucceeded(ChannelImpl.this.getName());
                }
            });
        }
    }

    public void setEventListener(ChannelEventListener channelEventListener) {
        this.eventListener = channelEventListener;
    }

    public ChannelEventListener getEventListener() {
        return this.eventListener;
    }

    public int compareTo(InternalChannel internalChannel) {
        return getName().compareTo(internalChannel.getName());
    }

    public String toString() {
        return String.format("[Public Channel: name=%s]", new Object[]{this.name});
    }

    /* access modifiers changed from: protected */
    public String[] getDisallowedNameExpressions() {
        return new String[]{"^private-.*", "^presence-.*"};
    }

    private void validateArguments(String str, SubscriptionEventListener subscriptionEventListener) {
        if (str == null) {
            throw new IllegalArgumentException("Cannot bind or unbind to channel " + this.name + " with a null event name");
        } else if (subscriptionEventListener == null) {
            throw new IllegalArgumentException("Cannot bind or unbind to channel " + this.name + " with a null listener");
        } else if (str.startsWith(INTERNAL_EVENT_PREFIX)) {
            throw new IllegalArgumentException("Cannot bind or unbind channel " + this.name + " with an internal event name such as " + str);
        } else if (this.state == ChannelState.UNSUBSCRIBED) {
            throw new IllegalStateException("Cannot bind or unbind to events on a channel that has been unsubscribed. Call Pusher.subscribe() to resubscribe to this channel");
        }
    }

    /* access modifiers changed from: protected */
    public Set<SubscriptionEventListener> getInterestedListeners(String str) {
        synchronized (this.lock) {
            Set set = this.eventNameToListenerMap.get(str);
            if (set == null) {
                return null;
            }
            HashSet hashSet = new HashSet(set);
            return hashSet;
        }
    }
}
