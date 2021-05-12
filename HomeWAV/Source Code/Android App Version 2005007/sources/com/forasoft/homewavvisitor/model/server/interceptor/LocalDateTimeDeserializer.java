package com.forasoft.homewavvisitor.model.server.interceptor;

import com.google.gson.JsonDeserializer;
import kotlin.Metadata;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/interceptor/LocalDateTimeDeserializer;", "Lcom/google/gson/JsonDeserializer;", "Lorg/threeten/bp/LocalDateTime;", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "typeOfT", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: LocalDateTimeDeserializer.kt */
public final class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r1 = r1.getAsJsonPrimitive();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.threeten.bp.LocalDateTime deserialize(com.google.gson.JsonElement r1, java.lang.reflect.Type r2, com.google.gson.JsonDeserializationContext r3) {
        /*
            r0 = this;
            if (r1 == 0) goto L_0x000d
            com.google.gson.JsonPrimitive r1 = r1.getAsJsonPrimitive()
            if (r1 == 0) goto L_0x000d
            java.lang.String r1 = r1.getAsString()
            goto L_0x000e
        L_0x000d:
            r1 = 0
        L_0x000e:
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.lang.String r2 = "yyyy-MM-dd HH:mm:ss"
            org.threeten.bp.format.DateTimeFormatter r2 = org.threeten.bp.format.DateTimeFormatter.ofPattern(r2)
            org.threeten.bp.LocalDateTime r1 = org.threeten.bp.LocalDateTime.parse(r1, r2)
            java.lang.String r2 = "UTC"
            org.threeten.bp.ZoneId r2 = org.threeten.bp.ZoneId.of(r2)
            org.threeten.bp.ZonedDateTime r1 = r1.atZone((org.threeten.bp.ZoneId) r2)
            org.threeten.bp.Instant r1 = r1.toInstant()
            org.threeten.bp.ZoneId r2 = org.threeten.bp.ZoneId.systemDefault()
            org.threeten.bp.LocalDateTime r1 = org.threeten.bp.LocalDateTime.ofInstant(r1, r2)
            java.lang.String r2 = "LocalDateTime.ofInstant(…, ZoneId.systemDefault())"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.server.interceptor.LocalDateTimeDeserializer.deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext):org.threeten.bp.LocalDateTime");
    }
}
