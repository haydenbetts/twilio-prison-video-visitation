package com.forasoft.homewavvisitor.model.repository;

import com.forasoft.homewavvisitor.AppInfo;
import com.forasoft.homewavvisitor.dao.CreditDao;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.payment.BraintreeToken;
import com.forasoft.homewavvisitor.model.data.payment.Credit;
import com.forasoft.homewavvisitor.model.server.apis.PaymentApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.google.gson.JsonObject;
import com.urbanairship.json.matchers.VersionMatcher;
import io.reactivex.Single;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eJ2\u0010\u0011\u001a&\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00120\u0012 \u0013*\u0012\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00120\u0012\u0018\u00010\u000e0\u000e2\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/forasoft/homewavvisitor/model/repository/PaymentRepository;", "", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/PaymentApi;", "creditDao", "Lcom/forasoft/homewavvisitor/dao/CreditDao;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "appInfo", "Lcom/forasoft/homewavvisitor/AppInfo;", "(Lcom/forasoft/homewavvisitor/model/server/apis/PaymentApi;Lcom/forasoft/homewavvisitor/dao/CreditDao;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/AppInfo;)V", "obtainToken", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/payment/BraintreeToken;", "saveCredit", "", "kotlin.jvm.PlatformType", "credit", "Lcom/forasoft/homewavvisitor/model/data/payment/Credit;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PaymentRepository.kt */
public final class PaymentRepository {
    private final PaymentApi api;
    private final AppInfo appInfo;
    /* access modifiers changed from: private */
    public final CreditDao creditDao;
    /* access modifiers changed from: private */
    public final InmateDao inmateDao;
    /* access modifiers changed from: private */
    public final NotificationDao notificationDao;

    @Inject
    public PaymentRepository(PaymentApi paymentApi, CreditDao creditDao2, NotificationDao notificationDao2, InmateDao inmateDao2, AppInfo appInfo2) {
        Intrinsics.checkParameterIsNotNull(paymentApi, "api");
        Intrinsics.checkParameterIsNotNull(creditDao2, "creditDao");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(appInfo2, "appInfo");
        this.api = paymentApi;
        this.creditDao = creditDao2;
        this.notificationDao = notificationDao2;
        this.inmateDao = inmateDao2;
        this.appInfo = appInfo2;
    }

    public final Single<ApiResponse<BraintreeToken>> obtainToken() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(VersionMatcher.ALTERNATE_VERSION_KEY, this.appInfo.getVersionName());
        jsonObject.addProperty("device_type", this.appInfo.getDeviceType());
        Single<ApiResponse<BraintreeToken>> applyAsync = CommonKt.applyAsync(this.api.getToken(jsonObject));
        Intrinsics.checkExpressionValueIsNotNull(applyAsync, "api.getToken(json)\n                .applyAsync()");
        return applyAsync;
    }

    public final Single<Unit> saveCredit(Credit credit) {
        Intrinsics.checkParameterIsNotNull(credit, "credit");
        return Single.fromCallable(new PaymentRepository$saveCredit$1(this, credit)).flatMap(new PaymentRepository$saveCredit$2(this, credit)).flatMap(new PaymentRepository$saveCredit$3(this, credit)).flatMap(new PaymentRepository$saveCredit$4(this, credit));
    }
}
