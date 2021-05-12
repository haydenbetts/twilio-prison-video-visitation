package com.forasoft.homewavvisitor.model.pusher;

import com.forasoft.homewavvisitor.model.data.Call;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.notifications.MessageListenerService;
import com.forasoft.homewavvisitor.model.pusher.PusherEvent;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import com.pusher.client.channel.PresenceChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.User;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0005H\u0016J\u001e\u0010\r\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0010H\u0016J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0010H\u0016¨\u0006\u0014"}, d2 = {"com/forasoft/homewavvisitor/model/pusher/PusherHolder$eventListener$1", "Lcom/pusher/client/channel/PresenceChannelEventListener;", "onAuthenticationFailure", "", "message", "", "e", "Ljava/lang/Exception;", "onEvent", "event", "Lcom/pusher/client/channel/PusherEvent;", "onSubscriptionSucceeded", "channelName", "onUsersInformationReceived", "users", "", "Lcom/pusher/client/channel/User;", "userSubscribed", "user", "userUnsubscribed", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PusherHolder.kt */
public final class PusherHolder$eventListener$1 implements PresenceChannelEventListener {
    final /* synthetic */ PusherHolder this$0;

    public void onAuthenticationFailure(String str, Exception exc) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(exc, "e");
    }

    public void onSubscriptionSucceeded(String str) {
        Intrinsics.checkParameterIsNotNull(str, "channelName");
    }

    public void onUsersInformationReceived(String str, Set<User> set) {
        Intrinsics.checkParameterIsNotNull(str, "channelName");
        Intrinsics.checkParameterIsNotNull(set, "users");
    }

    public void userSubscribed(String str, User user) {
        Intrinsics.checkParameterIsNotNull(str, "channelName");
        Intrinsics.checkParameterIsNotNull(user, "user");
    }

    public void userUnsubscribed(String str, User user) {
        Intrinsics.checkParameterIsNotNull(str, "channelName");
        Intrinsics.checkParameterIsNotNull(user, "user");
    }

    PusherHolder$eventListener$1(PusherHolder pusherHolder) {
        this.this$0 = pusherHolder;
    }

    public void onEvent(PusherEvent pusherEvent) {
        Intrinsics.checkParameterIsNotNull(pusherEvent, "event");
        JSONObject jSONObject = new JSONObject(pusherEvent.getData());
        String string = jSONObject.getString("message");
        if (string != null) {
            int hashCode = string.hashCode();
            if (hashCode != 210284648) {
                if (hashCode == 1376816541 && string.equals("new_call")) {
                    this.this$0.onEvent(new PusherEvent(PusherEvent.Type.valueOf(string), (Call) this.this$0.gson.fromJson(jSONObject.getString(CommonProperties.VALUE), Call.class)));
                    return;
                }
            } else if (string.equals(MessageListenerService.TYPE_NEW_MESSAGE)) {
                this.this$0.onEvent(new PusherEvent(PusherEvent.Type.valueOf(string), (Message) this.this$0.gson.fromJson(jSONObject.getString(CommonProperties.VALUE), Message.class)));
                return;
            }
        }
        Enum enumR = null;
        String string2 = !jSONObject.isNull(CommonProperties.VALUE) ? jSONObject.getString(CommonProperties.VALUE) : null;
        Intrinsics.checkExpressionValueIsNotNull(string, "type");
        try {
            enumR = Enum.valueOf(PusherEvent.Type.class, string);
        } catch (IllegalArgumentException unused) {
        }
        PusherEvent.Type type = (PusherEvent.Type) enumR;
        if (type != null) {
            this.this$0.onEvent(new PusherEvent(type, string2));
        }
    }
}
