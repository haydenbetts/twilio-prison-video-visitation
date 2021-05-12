package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.payment.ChooseFundsView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ChooseFundsPresenter.kt */
final class ChooseFundsPresenter$setPaymentScope$1<T> implements Consumer<ApiResponse<? extends Facility>> {
    final /* synthetic */ ChooseFundsPresenter this$0;

    ChooseFundsPresenter$setPaymentScope$1(ChooseFundsPresenter chooseFundsPresenter) {
        this.this$0 = chooseFundsPresenter;
    }

    public final void accept(ApiResponse<Facility> apiResponse) {
        ChooseFundsPresenter chooseFundsPresenter = this.this$0;
        Facility body = apiResponse.getBody();
        if (body != null) {
            chooseFundsPresenter.currentSelectedFacility = body;
            String[] strArr = new String[2];
            Facility access$getCurrentSelectedFacility$p = this.this$0.currentSelectedFacility;
            if (access$getCurrentSelectedFacility$p == null) {
                Intrinsics.throwNpe();
            }
            if (!access$getCurrentSelectedFacility$p.isTalkToMeFundsDisabled()) {
                strArr[0] = "inmate";
            }
            Facility access$getCurrentSelectedFacility$p2 = this.this$0.currentSelectedFacility;
            if (access$getCurrentSelectedFacility$p2 == null) {
                Intrinsics.throwNpe();
            }
            if (!access$getCurrentSelectedFacility$p2.isGeneralFundsDisabled()) {
                strArr[1] = "occupant";
            }
            Facility access$getCurrentSelectedFacility$p3 = this.this$0.currentSelectedFacility;
            if (access$getCurrentSelectedFacility$p3 == null) {
                Intrinsics.throwNpe();
            }
            if (!access$getCurrentSelectedFacility$p3.isTalkToMeFundsDisabled()) {
                this.this$0.fetchHandlingFee("inmate");
                ((ChooseFundsView) this.this$0.getViewState()).initPaymentScope("inmate", strArr);
                this.this$0.currentPaymentScope = "inmate";
                return;
            }
            Facility access$getCurrentSelectedFacility$p4 = this.this$0.currentSelectedFacility;
            if (access$getCurrentSelectedFacility$p4 == null) {
                Intrinsics.throwNpe();
            }
            if (!access$getCurrentSelectedFacility$p4.isGeneralFundsDisabled()) {
                this.this$0.fetchHandlingFee("occupant");
                ((ChooseFundsView) this.this$0.getViewState()).initPaymentScope("occupant", strArr);
                this.this$0.currentPaymentScope = "occupant";
            }
        }
    }
}
