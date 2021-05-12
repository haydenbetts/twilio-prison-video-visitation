package com.forasoft.homewavvisitor.model;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/model/DeepLink;", "", "path", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getPath", "()Ljava/lang/String;", "EMPTY", "SIGNUP", "ADD_FUNDS", "ADD_INMATES", "FAQ", "MESSAGE_CENTER", "NOTIFICATION_CENTER", "SOCIAL", "SUPPORT", "TUTORIALS", "SCHEDULE_VISIT", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DeepLink.kt */
public enum DeepLink {
    EMPTY((String) null),
    SIGNUP("signup"),
    ADD_FUNDS("add-funds"),
    ADD_INMATES("add-inmates"),
    FAQ("faq"),
    MESSAGE_CENTER("messages"),
    NOTIFICATION_CENTER("notifications"),
    SOCIAL("social-media"),
    SUPPORT("contact"),
    TUTORIALS("video-tutorials"),
    SCHEDULE_VISIT("schedule-visit");
    
    private final String path;

    private DeepLink(String str) {
        this.path = str;
    }

    public final String getPath() {
        return this.path;
    }
}
