package com.forasoft.homewavvisitor.model.server.interceptor;

import com.braintreepayments.api.internal.GraphQLConstants;
import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001c\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/interceptor/ErrorCauseDeserializer;", "Lcom/google/gson/JsonDeserializer;", "Lcom/forasoft/homewavvisitor/model/data/ErrorCause;", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "typeOfT", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "parseVisitorErrors", "", "", "errors", "Lcom/google/gson/JsonObject;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ErrorCauseDeserializer.kt */
public final class ErrorCauseDeserializer implements JsonDeserializer<ErrorCause> {
    public ErrorCause deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        JsonObject asJsonObject;
        JsonElement jsonElement2;
        JsonObject jsonObject = null;
        JsonPrimitive jsonPrimitive = (JsonPrimitive) (!(jsonElement instanceof JsonPrimitive) ? null : jsonElement);
        if (jsonPrimitive != null && jsonPrimitive.isString()) {
            return new ErrorCause(jsonElement.toString());
        }
        ErrorCause errorCause = (ErrorCause) new Gson().fromJson(jsonElement, ErrorCause.class);
        if (!(jsonElement == null || (asJsonObject = jsonElement.getAsJsonObject()) == null || (jsonElement2 = asJsonObject.get(GraphQLConstants.Keys.ERRORS)) == null)) {
            jsonObject = jsonElement2.getAsJsonObject();
        }
        if (jsonObject != null) {
            errorCause.setErrorList(parseVisitorErrors(jsonObject));
            Intrinsics.checkExpressionValueIsNotNull(errorCause, "errorCause");
            return errorCause;
        }
        Intrinsics.checkExpressionValueIsNotNull(errorCause, "errorCause");
        return errorCause;
    }

    private final Map<String, String> parseVisitorErrors(JsonObject jsonObject) {
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        if (jsonObject.has("visitor")) {
            JsonElement jsonElement = jsonObject.get("visitor");
            Intrinsics.checkExpressionValueIsNotNull(jsonElement, "errors.get(\"visitor\")");
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            for (String next : asJsonObject.keySet()) {
                JsonArray asJsonArray = asJsonObject.getAsJsonArray(next);
                if (asJsonArray.size() > 0) {
                    Intrinsics.checkExpressionValueIsNotNull(next, "key");
                    String jsonElement2 = asJsonArray.get(0).toString();
                    Intrinsics.checkExpressionValueIsNotNull(jsonElement2, "elementErrors[0].toString()");
                    linkedHashMap.put(next, jsonElement2);
                }
            }
        }
        return linkedHashMap;
    }
}
