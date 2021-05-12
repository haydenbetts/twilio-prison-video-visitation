package com.forasoft.homewavvisitor.presentation.presenter.auth;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.view.auth.VerifyMethodView;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "kotlin.jvm.PlatformType", "accept", "com/forasoft/homewavvisitor/presentation/presenter/auth/VerifyMethodPresenter$onSendClicked$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: VerifyMethodPresenter.kt */
final class VerifyMethodPresenter$onSendClicked$$inlined$let$lambda$1<T> implements Consumer<ApiResponse<? extends List<? extends Object>>> {
    final /* synthetic */ String $channel$inlined;
    final /* synthetic */ VerifyMethodPresenter this$0;

    VerifyMethodPresenter$onSendClicked$$inlined$let$lambda$1(VerifyMethodPresenter verifyMethodPresenter, String str) {
        this.this$0 = verifyMethodPresenter;
        this.$channel$inlined = str;
    }

    public final void accept(ApiResponse<? extends List<? extends Object>> apiResponse) {
        if (apiResponse.getOk()) {
            this.this$0.router.navigateTo(new Screens.VerifyCodeScreen(this.$channel$inlined));
        } else {
            ((VerifyMethodView) this.this$0.getViewState()).showMessage((int) R.string.label_server_error);
        }
    }
}
