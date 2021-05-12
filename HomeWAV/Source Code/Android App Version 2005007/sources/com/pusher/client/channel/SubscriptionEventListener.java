package com.pusher.client.channel;

public interface SubscriptionEventListener {
    void onEvent(PusherEvent pusherEvent);
}
