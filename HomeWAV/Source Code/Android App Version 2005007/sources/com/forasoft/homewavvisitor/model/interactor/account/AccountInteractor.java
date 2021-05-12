package com.forasoft.homewavvisitor.model.interactor.account;

import android.net.Uri;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.account.History;
import com.forasoft.homewavvisitor.model.data.account.ScheduledResponse;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.repository.account.AccountRepository;
import com.forasoft.homewavvisitor.model.repository.register.RegisterRepository;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.urbanairship.util.Attributes;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006JJ\u0010\u0007\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\n \u000b*\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\t \u000b*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\n \u000b*\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\t\u0018\u00010\b0\b2\u0006\u0010\f\u001a\u00020\rJb\u0010\u000e\u001aV\u0012$\u0012\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f \u000b*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f\u0018\u00010\t0\t \u000b**\u0012$\u0012\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f \u000b*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f\u0018\u00010\t0\t\u0018\u00010\b0\b2\u0006\u0010\u0010\u001a\u00020\rJ¿\u0001\u0010\u0011\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0012 \u000b*\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\t0\t \u000b*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0012 \u000b*\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\t0\t\u0018\u00010\b0\b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\u0002\u0010\u001eJ^\u0010\u001f\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0012 \u000b*\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\t0\t \u000b*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0012 \u000b*\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\t0\t\u0018\u00010\b0\b2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010#\u001a\u00020\rJB\u0010$\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020% \u000b*\n\u0012\u0004\u0012\u00020%\u0018\u00010\t0\t \u000b*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020% \u000b*\n\u0012\u0004\u0012\u00020%\u0018\u00010\t0\t\u0018\u00010\b0\bJB\u0010&\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020' \u000b*\n\u0012\u0004\u0012\u00020'\u0018\u00010\t0\t \u000b*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020' \u000b*\n\u0012\u0004\u0012\u00020'\u0018\u00010\t0\t\u0018\u00010\b0\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;", "", "registerRepository", "Lcom/forasoft/homewavvisitor/model/repository/register/RegisterRepository;", "accountRepository", "Lcom/forasoft/homewavvisitor/model/repository/account/AccountRepository;", "(Lcom/forasoft/homewavvisitor/model/repository/register/RegisterRepository;Lcom/forasoft/homewavvisitor/model/repository/account/AccountRepository;)V", "changeVisibleToInmateState", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "inmateId", "", "deleteInmate", "", "id", "editAccount", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "oldPassword", "newPassword", "birthDate", "email", "phone", "street", "city", "stateId", "zip", "isNotificationSubscriptionEnabled", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lio/reactivex/Single;", "editPhotos", "photo", "Landroid/net/Uri;", "photoId", "username", "getHistory", "Lcom/forasoft/homewavvisitor/model/data/account/History;", "getScheduledVisits", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledResponse;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AccountInteractor.kt */
public final class AccountInteractor {
    private final AccountRepository accountRepository;
    private final RegisterRepository registerRepository;

    @Inject
    public AccountInteractor(RegisterRepository registerRepository2, AccountRepository accountRepository2) {
        Intrinsics.checkParameterIsNotNull(registerRepository2, "registerRepository");
        Intrinsics.checkParameterIsNotNull(accountRepository2, "accountRepository");
        this.registerRepository = registerRepository2;
        this.accountRepository = accountRepository2;
    }

    public static /* synthetic */ Single editAccount$default(AccountInteractor accountInteractor, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Boolean bool, int i, Object obj) {
        int i2 = i;
        return accountInteractor.editAccount((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7, (i2 & 128) != 0 ? null : str8, (i2 & 256) != 0 ? null : str9, (i2 & 512) != 0 ? null : bool);
    }

    public final Single<ApiResponse<User>> editAccount(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Boolean bool) {
        return CommonKt.applyAsync(this.registerRepository.editProfile(str, str2, str3, str4, str5, str6, str7, str8, str9, bool));
    }

    public final Single<ApiResponse<User>> editPhotos(Uri uri, Uri uri2, String str) {
        Intrinsics.checkParameterIsNotNull(str, Attributes.USERNAME);
        return CommonKt.applyAsync(this.registerRepository.editUserImages(uri, uri2, str));
    }

    public final Single<ApiResponse<History>> getHistory() {
        return CommonKt.applyAsync(this.accountRepository.getHistory());
    }

    public final Single<ApiResponse<ScheduledResponse>> getScheduledVisits() {
        return CommonKt.applyAsync(this.registerRepository.getVisits());
    }

    public final Single<ApiResponse<List<Inmate>>> deleteInmate(String str) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        return CommonKt.applyAsync(this.accountRepository.deleteInmate(str));
    }

    public final Single<ApiResponse<Inmate>> changeVisibleToInmateState(String str) {
        Intrinsics.checkParameterIsNotNull(str, "inmateId");
        return CommonKt.applyAsync(this.accountRepository.changeVisibleToInmateState(str));
    }
}
