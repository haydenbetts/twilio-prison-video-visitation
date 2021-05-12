package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.register.AddConnectionView;
import io.reactivex.functions.Consumer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u000122\u0010\u0002\u001a.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddConnectionSignUpPresenter.kt */
final class AddConnectionSignUpPresenter$onFacilitySelected$1<T> implements Consumer<ApiResponse<? extends Map<String, ? extends String>>> {
    final /* synthetic */ AddConnectionSignUpPresenter this$0;

    AddConnectionSignUpPresenter$onFacilitySelected$1(AddConnectionSignUpPresenter addConnectionSignUpPresenter) {
        this.this$0 = addConnectionSignUpPresenter;
    }

    public final void accept(ApiResponse<? extends Map<String, String>> apiResponse) {
        if (apiResponse.getOk()) {
            AddConnectionView addConnectionView = (AddConnectionView) this.this$0.getViewState();
            Map map = (Map) apiResponse.getBody();
            String str = map != null ? (String) map.get("url") : null;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            addConnectionView.showFacilityAgreement(str);
            this.this$0.loadInmatesForFacility();
            return;
        }
        this.this$0.loadInmatesForFacility();
    }
}
