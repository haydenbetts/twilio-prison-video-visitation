package com.forasoft.homewavvisitor.toothpick.provider;

import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.NotificationBody;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.notifications.MessageListenerService;
import com.forasoft.homewavvisitor.model.server.interceptor.ErrorCauseDeserializer;
import com.forasoft.homewavvisitor.model.server.interceptor.LocalDateTimeDeserializer;
import com.forasoft.homewavvisitor.model.server.interceptor.MessageDeserializer;
import com.forasoft.homewavvisitor.model.server.interceptor.UserDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import javax.inject.Inject;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/toothpick/provider/GsonProvider;", "Ljavax/inject/Provider;", "Lcom/google/gson/Gson;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "(Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;)V", "get", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: GsonProvider.kt */
public final class GsonProvider implements Provider<Gson> {
    private final AuthHolder authHolder;

    @Inject
    public GsonProvider(AuthHolder authHolder2) {
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        this.authHolder = authHolder2;
    }

    public Gson get() {
        Gson create = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).registerTypeAdapter(ErrorCause.class, new ErrorCauseDeserializer()).registerTypeAdapter(User.class, new UserDeserializer(this.authHolder)).registerTypeAdapter(Message.class, new MessageDeserializer()).registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(NotificationBody.class, "type").registerSubtype(NotificationBody.BalanceBelowTwo.class, "below_two").registerSubtype(NotificationBody.BalanceBelowZero.class, "below_zero").registerSubtype(NotificationBody.VisitConfirm.class, "confirm").registerSubtype(NotificationBody.VisitCancel.class, "cancel").registerSubtype(NotificationBody.MessageText.class, "text").registerSubtype(NotificationBody.MessageVideo.class, "video").registerSubtype(NotificationBody.MessageS3Video.class, "s3_video").registerSubtype(NotificationBody.MessageImage.class, "image").registerSubtype(NotificationBody.MessageGif.class, "gif").registerSubtype(NotificationBody.FundsAdded.class, MessageListenerService.TYPE_ADDED_FUNDS).registerSubtype(NotificationBody.RequestFunds.class, MessageListenerService.TYPE_REQUEST_FUNDS).registerSubtype(NotificationBody.IncomingCall.class, "incoming_call").registerSubtype(NotificationBody.InmateOnline.class, MessageListenerService.TYPE_INMATE_ONLINE).registerSubtype(NotificationBody.Airship.class, MessageListenerService.AIRSHIP_MESSAGE)).create();
        Intrinsics.checkExpressionValueIsNotNull(create, "GsonBuilder()\n          …                .create()");
        return create;
    }
}
