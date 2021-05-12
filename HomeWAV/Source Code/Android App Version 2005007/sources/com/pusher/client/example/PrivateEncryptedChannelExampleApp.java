package com.pusher.client.example;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.PrivateEncryptedChannel;
import com.pusher.client.channel.PrivateEncryptedChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.util.HttpAuthorizer;

public class PrivateEncryptedChannelExampleApp {
    private String authorizationEndpoint = "http://localhost:3030/pusher/auth";
    private PrivateEncryptedChannel channel;
    private String channelName = "private-encrypted-channel";
    private String channelsKey = "FILL_ME_IN";
    private String cluster = "eu";
    private String eventName = "my-event";

    public static void main(String[] strArr) {
        new PrivateEncryptedChannelExampleApp(strArr);
    }

    private PrivateEncryptedChannelExampleApp(String[] strArr) {
        int length = strArr.length;
        if (length != 1) {
            if (length != 2) {
                if (length != 3) {
                    if (length == 4) {
                        this.cluster = strArr[3];
                    }
                    Pusher pusher = new Pusher(this.channelsKey, new PusherOptions().setCluster(this.cluster).setAuthorizer(new HttpAuthorizer("http://localhost:3030/pusher/auth")).setUseTLS(true));
                    pusher.connect(new ConnectionEventListener() {
                        public void onConnectionStateChange(ConnectionStateChange connectionStateChange) {
                            System.out.println(String.format("Connection state changed from [%s] to [%s]", new Object[]{connectionStateChange.getPreviousState(), connectionStateChange.getCurrentState()}));
                        }

                        public void onError(String str, String str2, Exception exc) {
                            System.out.println(String.format("An error was received with message [%s], code [%s], exception [%s]", new Object[]{str, str2, exc}));
                        }
                    }, new ConnectionState[0]);
                    AnonymousClass2 r8 = new PrivateEncryptedChannelEventListener() {
                        public void onSubscriptionSucceeded(String str) {
                            System.out.println(String.format("Subscription to channel [%s] succeeded", new Object[]{str}));
                        }

                        public void onEvent(PusherEvent pusherEvent) {
                            System.out.println(String.format("Received event [%s]", new Object[]{pusherEvent.toString()}));
                        }

                        public void onAuthenticationFailure(String str, Exception exc) {
                            System.out.println(String.format("Authentication failure due to [%s], exception was [%s]", new Object[]{str, exc}));
                        }

                        public void onDecryptionFailure(String str, String str2) {
                            System.out.println(String.format("An error was received decrypting message for event:[%s] - reason: [%s]", new Object[]{str, str2}));
                        }
                    };
                    this.channel = pusher.subscribePrivateEncrypted(this.channelName, r8, this.eventName);
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
        Pusher pusher2 = new Pusher(this.channelsKey, new PusherOptions().setCluster(this.cluster).setAuthorizer(new HttpAuthorizer("http://localhost:3030/pusher/auth")).setUseTLS(true));
        pusher2.connect(new ConnectionEventListener() {
            public void onConnectionStateChange(ConnectionStateChange connectionStateChange) {
                System.out.println(String.format("Connection state changed from [%s] to [%s]", new Object[]{connectionStateChange.getPreviousState(), connectionStateChange.getCurrentState()}));
            }

            public void onError(String str, String str2, Exception exc) {
                System.out.println(String.format("An error was received with message [%s], code [%s], exception [%s]", new Object[]{str, str2, exc}));
            }
        }, new ConnectionState[0]);
        AnonymousClass2 r82 = new PrivateEncryptedChannelEventListener() {
            public void onSubscriptionSucceeded(String str) {
                System.out.println(String.format("Subscription to channel [%s] succeeded", new Object[]{str}));
            }

            public void onEvent(PusherEvent pusherEvent) {
                System.out.println(String.format("Received event [%s]", new Object[]{pusherEvent.toString()}));
            }

            public void onAuthenticationFailure(String str, Exception exc) {
                System.out.println(String.format("Authentication failure due to [%s], exception was [%s]", new Object[]{str, exc}));
            }

            public void onDecryptionFailure(String str, String str2) {
                System.out.println(String.format("An error was received decrypting message for event:[%s] - reason: [%s]", new Object[]{str, str2}));
            }
        };
        this.channel = pusher2.subscribePrivateEncrypted(this.channelName, r82, this.eventName);
        while (true) {
            Thread.sleep(1000);
        }
    }
}
