package com.forasoft.homewavvisitor.model.server.interceptor;

import androidx.core.app.NotificationCompat;
import com.forasoft.homewavvisitor.model.UploadWorker;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.MessageStatus;
import com.forasoft.homewavvisitor.model.data.MessageType;
import com.forasoft.homewavvisitor.model.data.Sender;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/interceptor/MessageDeserializer;", "Lcom/google/gson/JsonDeserializer;", "Lcom/forasoft/homewavvisitor/model/data/Message;", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "typeOfT", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MessageDeserializer.kt */
public final class MessageDeserializer implements JsonDeserializer<Message> {
    public Message deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        String str;
        String str2;
        String str3;
        String str4;
        JsonDeserializationContext jsonDeserializationContext2 = jsonDeserializationContext;
        Intrinsics.checkParameterIsNotNull(jsonElement, "json");
        Intrinsics.checkParameterIsNotNull(type, "typeOfT");
        Intrinsics.checkParameterIsNotNull(jsonDeserializationContext2, "context");
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = asJsonObject.get("id");
        Intrinsics.checkExpressionValueIsNotNull(jsonElement2, "get(\"id\")");
        String asString = jsonElement2.getAsString();
        JsonElement jsonElement3 = asJsonObject.get(UploadWorker.KEY_PUB_ID);
        Intrinsics.checkExpressionValueIsNotNull(jsonElement3, "get(\"pubid\")");
        String asString2 = jsonElement3.getAsString();
        LocalDateTime localDateTime = (LocalDateTime) jsonDeserializationContext2.deserialize(asJsonObject.get("created"), LocalDateTime.class);
        JsonElement jsonElement4 = asJsonObject.get(UploadWorker.KEY_INMATE_ID);
        Intrinsics.checkExpressionValueIsNotNull(jsonElement4, "get(\"inmate_id\")");
        String asString3 = jsonElement4.getAsString();
        JsonElement jsonElement5 = asJsonObject.get(UploadWorker.KEY_VISITOR_ID);
        Intrinsics.checkExpressionValueIsNotNull(jsonElement5, "get(\"visitor_id\")");
        String asString4 = jsonElement5.getAsString();
        try {
            JsonElement jsonElement6 = asJsonObject.get("body");
            Intrinsics.checkExpressionValueIsNotNull(jsonElement6, "get(\"body\")");
            str = jsonElement6.getAsString();
        } catch (UnsupportedOperationException unused) {
            str = "";
        }
        String str5 = str;
        Sender sender = (Sender) jsonDeserializationContext2.deserialize(asJsonObject.get("sender"), Sender.class);
        MessageType messageType = (MessageType) jsonDeserializationContext2.deserialize(asJsonObject.get("type"), MessageType.class);
        String str6 = "type";
        JsonElement jsonElement7 = asJsonObject.get(NotificationCompat.CATEGORY_STATUS);
        String str7 = NotificationCompat.CATEGORY_STATUS;
        MessageStatus messageStatus = (MessageStatus) jsonDeserializationContext2.deserialize(jsonElement7, MessageStatus.class);
        JsonElement jsonElement8 = asJsonObject.get("view_count");
        Intrinsics.checkExpressionValueIsNotNull(jsonElement8, "get(\"view_count\")");
        String asString5 = jsonElement8.getAsString();
        JsonElement jsonElement9 = asJsonObject.get("sender_name");
        String str8 = asString5;
        Intrinsics.checkExpressionValueIsNotNull(jsonElement9, "get(\"sender_name\")");
        String asString6 = jsonElement9.getAsString();
        JsonElement jsonElement10 = asJsonObject.get("stream_name");
        String str9 = asString6;
        Intrinsics.checkExpressionValueIsNotNull(jsonElement10, "get(\"stream_name\")");
        String asString7 = jsonElement10.getAsString();
        String str10 = null;
        if (asJsonObject.has("stream_url")) {
            JsonElement jsonElement11 = asJsonObject.get("stream_url");
            str3 = asString7;
            Intrinsics.checkExpressionValueIsNotNull(jsonElement11, "get(\"stream_url\")");
            JsonPrimitive asJsonPrimitive = jsonElement11.getAsJsonPrimitive();
            str2 = !asJsonPrimitive.isBoolean() ? asJsonPrimitive.getAsString() : null;
        } else {
            str3 = asString7;
            str2 = null;
        }
        if (asJsonObject.has("image_url")) {
            JsonElement jsonElement12 = asJsonObject.get("image_url");
            Intrinsics.checkExpressionValueIsNotNull(jsonElement12, "get(\"image_url\")");
            JsonPrimitive asJsonPrimitive2 = jsonElement12.getAsJsonPrimitive();
            str4 = !asJsonPrimitive2.isBoolean() ? asJsonPrimitive2.getAsString() : null;
        } else {
            str4 = null;
        }
        if (asJsonObject.has("video_url")) {
            JsonElement jsonElement13 = asJsonObject.get("video_url");
            Intrinsics.checkExpressionValueIsNotNull(jsonElement13, "get(\"video_url\")");
            JsonPrimitive asJsonPrimitive3 = jsonElement13.getAsJsonPrimitive();
            if (!asJsonPrimitive3.isBoolean()) {
                str10 = asJsonPrimitive3.getAsString();
            }
        }
        String str11 = str10;
        Intrinsics.checkExpressionValueIsNotNull(asString, "id");
        Intrinsics.checkExpressionValueIsNotNull(asString2, "pubId");
        Intrinsics.checkExpressionValueIsNotNull(localDateTime, "created");
        Intrinsics.checkExpressionValueIsNotNull(asString3, "inmateId");
        Intrinsics.checkExpressionValueIsNotNull(asString4, "visitorId");
        Intrinsics.checkExpressionValueIsNotNull(str5, "body");
        Intrinsics.checkExpressionValueIsNotNull(sender, "sender");
        Intrinsics.checkExpressionValueIsNotNull(messageType, str6);
        Intrinsics.checkExpressionValueIsNotNull(messageStatus, str7);
        String str12 = str8;
        Intrinsics.checkExpressionValueIsNotNull(str12, "views");
        String str13 = str9;
        Intrinsics.checkExpressionValueIsNotNull(str13, "senderName");
        String str14 = str3;
        Intrinsics.checkExpressionValueIsNotNull(str14, "streamName");
        return new Message(asString, asString2, localDateTime, asString3, asString4, str5, sender, messageType, messageStatus, str12, str13, str14, str2, str4, str11, (String) null, 32768, (DefaultConstructorMarker) null);
    }
}
