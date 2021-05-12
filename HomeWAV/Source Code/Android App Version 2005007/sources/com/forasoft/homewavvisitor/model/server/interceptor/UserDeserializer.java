package com.forasoft.homewavvisitor.model.server.interceptor;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.google.gson.JsonDeserializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J(\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/interceptor/UserDeserializer;", "Lcom/google/gson/JsonDeserializer;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "(Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;)V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "typeOfT", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: UserDeserializer.kt */
public final class UserDeserializer implements JsonDeserializer<User> {
    private final AuthHolder authHolder;

    public UserDeserializer(AuthHolder authHolder2) {
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        this.authHolder = authHolder2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.forasoft.homewavvisitor.model.data.auth.User deserialize(com.google.gson.JsonElement r30, java.lang.reflect.Type r31, com.google.gson.JsonDeserializationContext r32) {
        /*
            r29 = this;
            r1 = r29
            r2 = 0
            r3 = r2
            com.forasoft.homewavvisitor.model.data.register.SignUpResponse r3 = (com.forasoft.homewavvisitor.model.data.register.SignUpResponse) r3
            com.google.gson.GsonBuilder r0 = new com.google.gson.GsonBuilder     // Catch:{ Exception -> 0x0028 }
            r0.<init>()     // Catch:{ Exception -> 0x0028 }
            java.lang.Class<com.forasoft.homewavvisitor.model.data.auth.UserPhoto> r4 = com.forasoft.homewavvisitor.model.data.auth.UserPhoto.class
            java.lang.reflect.Type r4 = (java.lang.reflect.Type) r4     // Catch:{ Exception -> 0x0028 }
            com.forasoft.homewavvisitor.model.server.interceptor.UserPhotoDeserializer r5 = new com.forasoft.homewavvisitor.model.server.interceptor.UserPhotoDeserializer     // Catch:{ Exception -> 0x0028 }
            r5.<init>()     // Catch:{ Exception -> 0x0028 }
            com.google.gson.GsonBuilder r0 = r0.registerTypeAdapter(r4, r5)     // Catch:{ Exception -> 0x0028 }
            com.google.gson.Gson r0 = r0.create()     // Catch:{ Exception -> 0x0028 }
            java.lang.Class<com.forasoft.homewavvisitor.model.data.register.SignUpResponse> r4 = com.forasoft.homewavvisitor.model.data.register.SignUpResponse.class
            r5 = r30
            java.lang.Object r0 = r0.fromJson((com.google.gson.JsonElement) r5, r4)     // Catch:{ Exception -> 0x0028 }
            com.forasoft.homewavvisitor.model.data.register.SignUpResponse r0 = (com.forasoft.homewavvisitor.model.data.register.SignUpResponse) r0     // Catch:{ Exception -> 0x0028 }
            r3 = r0
            goto L_0x002e
        L_0x0028:
            r0 = move-exception
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            timber.log.Timber.e(r0)
        L_0x002e:
            if (r3 == 0) goto L_0x0104
            java.lang.String r0 = r3.getDob()
            java.text.SimpleDateFormat r4 = new java.text.SimpleDateFormat
            java.lang.String r5 = "MM/dd/yyyy"
            r4.<init>(r5)
            java.util.Date r11 = r4.parse(r0)
            com.forasoft.homewavvisitor.model.data.auth.UserPhoto r0 = r3.getPerson_capture()
            if (r0 == 0) goto L_0x004c
            java.lang.String r0 = r0.getUrl()
            if (r0 == 0) goto L_0x004c
            goto L_0x0058
        L_0x004c:
            com.forasoft.homewavvisitor.model.data.auth.AuthHolder r0 = r1.authHolder
            com.forasoft.homewavvisitor.model.data.auth.User r0 = r0.getUser()
            if (r0 == 0) goto L_0x005b
            java.lang.String r0 = r0.getPhotoProfileUrl()
        L_0x0058:
            r22 = r0
            goto L_0x005d
        L_0x005b:
            r22 = r2
        L_0x005d:
            com.forasoft.homewavvisitor.model.data.auth.UserPhoto r0 = r3.getPhoto_id_capture()
            if (r0 == 0) goto L_0x006a
            java.lang.String r0 = r0.getUrl()
            if (r0 == 0) goto L_0x006a
            goto L_0x0076
        L_0x006a:
            com.forasoft.homewavvisitor.model.data.auth.AuthHolder r0 = r1.authHolder
            com.forasoft.homewavvisitor.model.data.auth.User r0 = r0.getUser()
            if (r0 == 0) goto L_0x0079
            java.lang.String r0 = r0.getPhotoIdUrl()
        L_0x0076:
            r23 = r0
            goto L_0x007b
        L_0x0079:
            r23 = r2
        L_0x007b:
            java.lang.String r0 = r3.is_admin()
            java.lang.String r4 = "1"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r4)
            if (r0 != 0) goto L_0x0096
            java.lang.String r0 = r3.is_mega_admin()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r4)
            if (r0 == 0) goto L_0x0092
            goto L_0x0096
        L_0x0092:
            r0 = 0
            r27 = 0
            goto L_0x0099
        L_0x0096:
            r0 = 1
            r27 = 1
        L_0x0099:
            java.lang.String r0 = r3.getNotification_subscription_enabled()
            boolean r28 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r4)
            com.forasoft.homewavvisitor.model.data.auth.User r0 = new com.forasoft.homewavvisitor.model.data.auth.User
            java.lang.String r7 = r3.getId()
            java.lang.String r8 = r3.getVisitor_id()
            java.lang.String r9 = r3.getCreated()
            java.lang.String r10 = r3.getPin()
            java.lang.String r4 = "dateString"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r4)
            java.lang.String r12 = r3.getUsername()
            java.lang.String r13 = r3.getFirst_name()
            java.lang.String r14 = r3.getLast_name()
            java.lang.String r15 = r3.getFull_name()
            java.lang.String r16 = r3.getPhone()
            java.lang.String r17 = r3.getEmail()
            java.lang.String r18 = r3.getUs_state_abbreviation()
            java.lang.String r19 = r3.getCity()
            java.lang.String r20 = r3.getStreet1()
            java.lang.String r21 = r3.getZipcode()
            com.forasoft.homewavvisitor.model.data.auth.UserPhoto r4 = r3.getPerson_capture()
            if (r4 == 0) goto L_0x00ed
            java.lang.String r4 = r4.getPubid()
            r24 = r4
            goto L_0x00ef
        L_0x00ed:
            r24 = r2
        L_0x00ef:
            com.forasoft.homewavvisitor.model.data.auth.UserPhoto r4 = r3.getPhoto_id_capture()
            if (r4 == 0) goto L_0x00f9
            java.lang.String r2 = r4.getPubid()
        L_0x00f9:
            r25 = r2
            boolean r26 = r3.getVerified()
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            return r0
        L_0x0104:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.server.interceptor.UserDeserializer.deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext):com.forasoft.homewavvisitor.model.data.auth.User");
    }
}
