package com.forasoft.homewavvisitor.model.server.interceptor;

import com.forasoft.homewavvisitor.model.data.auth.UserPhoto;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J(\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/interceptor/UserPhotoDeserializer;", "Lcom/google/gson/JsonDeserializer;", "Lcom/forasoft/homewavvisitor/model/data/auth/UserPhoto;", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "typeOfT", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: UserPhotoDeserializer.kt */
public final class UserPhotoDeserializer implements JsonDeserializer<UserPhoto> {
    public UserPhoto deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        try {
            return (UserPhoto) new Gson().fromJson(jsonElement, UserPhoto.class);
        } catch (Exception unused) {
            return null;
        }
    }
}
