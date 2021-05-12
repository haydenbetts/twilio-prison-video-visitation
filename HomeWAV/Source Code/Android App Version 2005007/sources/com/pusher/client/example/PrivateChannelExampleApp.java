package com.pusher.client.example;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.util.HttpAuthorizer;

public class PrivateChannelExampleApp {
    private String authorizationEndpoint = "http://localhost:3030/pusher/auth";
    private final PrivateChannel channel;
    private String channelName = "my-channel";
    private String channelsKey = "FILL_ME_IN";
    private String cluster = "eu";
    private String eventName = "my-event";

    public static void main(String[] strArr) {
        new PrivateChannelExampleApp(strArr);
    }

    PrivateChannelExampleApp(String[] strArr) {
        int length = strArr.length;
        if (length != 1) {
            if (length != 2) {
                if (length != 3) {
                    if (length == 4) {
                        this.cluster = strArr[3];
                    }
                    Pusher pusher = new Pusher(this.channelsKey, new PusherOptions().setUseTLS(true).setCluster(this.cluster).setAuthorizer(new HttpAuthorizer("http://localhost:3030/pusher/auth")));
                    pusher.connect(new ConnectionEventListener() {
                        public void onConnectionStateChange(ConnectionStateChange connectionStateChange) {
                            System.out.println(String.format("Connection state changed from [%s] to [%s]", new Object[]{connectionStateChange.getPreviousState(), connectionStateChange.getCurrentState()}));
                        }

                        public void onError(String str, String str2, Exception exc) {
                            System.out.println(String.format("An error was received with message [%s], code [%s], exception [%s]", new Object[]{str, str2, exc}));
                        }
                    }, new ConnectionState[0]);
                    AnonymousClass2 r8 = new PrivateChannelEventListener() {
                        public void onSubscriptionSucceeded(String str) {
                            System.out.println(String.format("Subscription to channel [%s] succeeded", new Object[]{str}));
                        }

                        public void onEvent(PusherEvent pusherEvent) {
                            System.out.println(String.format("Received event [%s]", new Object[]{pusherEvent.toString()}));
                        }

                        public void onAuthenticationFailure(String str, Exception exc) {
                            System.out.println(String.format("Authentication failure due to [%s], exception was [%s]", new Object[]{str, exc}));
                        }
                    };
                    this.channel = pusher.subscribePrivate(this.channelName, r8, this.eventName);
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.eventName = strArr[2];
            }
            this.channelName = strArr[1];
        }
        this.channelsKey = strArr[0];
        Pusher pusher2 = new Pusher(this.channelsKey, new PusherOptions().setUseTLS(true).setCluster(this.cluster).setAuthorizer(new HttpAuthorizer("http://localhost:3030/pusher/auth")));
        pusher2.connect(new ConnectionEventListener() {
            public void onConnectionStateChange(ConnectionStateChange connectionStateChange) {
                System.out.println(String.format("Connection state changed from [%s] to [%s]", new Object[]{connectionStateChange.getPreviousState(), connectionStateChange.getCurrentState()}));
            }

            public void onError(String str, String str2, Exception exc) {
                System.out.println(String.format("An error was received with message [%s], code [%s], exception [%s]", new Object[]{str, str2, exc}));
            }
        }, new ConnectionState[0]);
        AnonymousClass2 r82 = new PrivateChannelEventListener() {
            public void onSubscriptionSucceeded(String str) {
                System.out.println(String.format("Subscription to channel [%s] succeeded", new Object[]{str}));
            }

            public void onEvent(PusherEvent pusherEvent) {
                System.out.println(String.format("Received event [%s]", new Object[]{pusherEvent.toString()}));
            }

            public void onAuthenticationFailure(String str, Exception exc) {
                System.out.println(String.format("Authentication failure due to [%s], exception was [%s]", new Object[]{str, exc}));
            }
        };
        this.channel = pusher2.subscribePrivate(this.channelName, r82, this.eventName);
        while (true) {
            Thread.sleep(1000);
        }
    }
}
