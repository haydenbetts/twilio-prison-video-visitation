package com.forasoft.homewavvisitor.model.interactor;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001an\u00120\u0012.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003 \u0006*\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0018\u00010\u00020\u0002 \u0006*6\u00120\u0012.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003 \u0006*\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "", "kotlin.jvm.PlatformType", "it", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: PaymentGateway.kt */
final class PaymentGateway$updateCardStatus$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ boolean $default;
    final /* synthetic */ String $token;
    final /* synthetic */ PaymentGateway this$0;

    PaymentGateway$updateCardStatus$1(PaymentGateway paymentGateway, String str, boolean z) {
        this.this$0 = paymentGateway;
        this.$token = str;
        this.$default = z;
    }

    public final Single<ApiResponse<Map<String, Boolean>>> apply(PaymentGatewayType paymentGatewayType) {
        Intrinsics.checkParameterIsNotNull(paymentGatewayType, "it");
        if (paymentGatewayType == PaymentGatewayType.STRIPE) {
            return CommonKt.applyAsync(this.this$0.paymentApi.updateCardStatusFromStripe(this.$token, this.$default));
        }
        return CommonKt.applyAsync(this.this$0.paymentApi.updateCardStatus(this.$token, this.$default));
    }
}
