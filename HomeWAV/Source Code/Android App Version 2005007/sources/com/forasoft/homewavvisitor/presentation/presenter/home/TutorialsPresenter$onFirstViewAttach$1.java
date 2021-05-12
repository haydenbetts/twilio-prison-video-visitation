package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.home.TutorialsView;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: TutorialsPresenter.kt */
final class TutorialsPresenter$onFirstViewAttach$1 extends Lambda implements Function1<ApiResponse<? extends List<? extends String>>, Unit> {
    final /* synthetic */ TutorialsPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TutorialsPresenter$onFirstViewAttach$1(TutorialsPresenter tutorialsPresenter) {
        super(1);
        this.this$0 = tutorialsPresenter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApiResponse<? extends List<String>>) (ApiResponse) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ApiResponse<? extends List<String>> apiResponse) {
        if (apiResponse.getBody() != null) {
            ((TutorialsView) this.this$0.getViewState()).showTutorials((List) apiResponse.getBody());
        } else {
            ((TutorialsView) this.this$0.getViewState()).showError();
        }
    }
}
