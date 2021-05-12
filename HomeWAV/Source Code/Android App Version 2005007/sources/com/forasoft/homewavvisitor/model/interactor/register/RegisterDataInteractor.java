package com.forasoft.homewavvisitor.model.interactor.register;

import android.net.Uri;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.data.register.SignUpData;
import com.forasoft.homewavvisitor.model.repository.register.RegisterRepository;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.stripe.android.view.ShippingInfoWidget;
import com.urbanairship.util.Attributes;
import io.reactivex.Single;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bJr\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterDataInteractor;", "", "registerRepository", "Lcom/forasoft/homewavvisitor/model/repository/register/RegisterRepository;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "(Lcom/forasoft/homewavvisitor/model/repository/register/RegisterRepository;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;)V", "photoId", "Landroid/net/Uri;", "getPhotoId", "()Landroid/net/Uri;", "setPhotoId", "(Landroid/net/Uri;)V", "photoUser", "getPhotoUser", "setPhotoUser", "saveStep1Data", "", "userIDImagePath", "profileImagePath", "sendStep2Data", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "username", "", "email", "password", "firstName", "lastName", "birthDate", "phone", "street", "city", "state", "zip", "isNotificationSubscriptionEnabled", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RegisterDataInteractor.kt */
public final class RegisterDataInteractor {
    private final AuthHolder authHolder;
    private Uri photoId;
    private Uri photoUser;
    private final RegisterRepository registerRepository;

    @Inject
    public RegisterDataInteractor(RegisterRepository registerRepository2, AuthHolder authHolder2) {
        Intrinsics.checkParameterIsNotNull(registerRepository2, "registerRepository");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        this.registerRepository = registerRepository2;
        this.authHolder = authHolder2;
    }

    public final Uri getPhotoUser() {
        return this.photoUser;
    }

    public final void setPhotoUser(Uri uri) {
        this.photoUser = uri;
    }

    public final Uri getPhotoId() {
        return this.photoId;
    }

    public final void setPhotoId(Uri uri) {
        this.photoId = uri;
    }

    public final void saveStep1Data(Uri uri, Uri uri2) {
        Intrinsics.checkParameterIsNotNull(uri, "userIDImagePath");
        Intrinsics.checkParameterIsNotNull(uri2, "profileImagePath");
        this.photoUser = uri2;
        this.photoId = uri;
    }

    public final Single<ApiResponse<User>> sendStep2Data(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, Attributes.USERNAME);
        Intrinsics.checkParameterIsNotNull(str2, "email");
        Intrinsics.checkParameterIsNotNull(str3, "password");
        Intrinsics.checkParameterIsNotNull(str4, "firstName");
        Intrinsics.checkParameterIsNotNull(str5, "lastName");
        Intrinsics.checkParameterIsNotNull(str6, "birthDate");
        Intrinsics.checkParameterIsNotNull(str7, ShippingInfoWidget.PHONE_FIELD);
        Intrinsics.checkParameterIsNotNull(str8, "street");
        Intrinsics.checkParameterIsNotNull(str9, "city");
        Intrinsics.checkParameterIsNotNull(str10, "state");
        Intrinsics.checkParameterIsNotNull(str11, "zip");
        Single<ApiResponse<User>> applyAsync = CommonKt.applyAsync(this.registerRepository.register(new SignUpData(str, str2, str3, str3, str4, str5, str6, str7, str9, str8, str10, str11, this.authHolder.getFcmToken(), z, 0, false, SctpParameterType.ForwardTsnSupportedParameter, (DefaultConstructorMarker) null).toJson(), this.photoUser, this.photoId));
        Intrinsics.checkExpressionValueIsNotNull(applyAsync, "registerRepository.regis…            .applyAsync()");
        return applyAsync;
    }
}
