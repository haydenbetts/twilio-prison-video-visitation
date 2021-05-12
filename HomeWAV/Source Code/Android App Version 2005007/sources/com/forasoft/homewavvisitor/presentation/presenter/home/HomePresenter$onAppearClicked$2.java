package com.forasoft.homewavvisitor.presentation.presenter.home;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.model.data.Empty;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.home.HomeView;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "Lcom/forasoft/homewavvisitor/model/data/Empty;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: HomePresenter.kt */
final class HomePresenter$onAppearClicked$2<T> implements Consumer<ApiResponse<? extends List<? extends Empty>>> {
    final /* synthetic */ HomePresenter this$0;

    HomePresenter$onAppearClicked$2(HomePresenter homePresenter) {
        this.this$0 = homePresenter;
    }

    public final void accept(ApiResponse<? extends List<Empty>> apiResponse) {
        ((HomeView) this.this$0.getViewState()).showMessage((int) R.string.visibility_change_success);
        this.this$0.getInmates();
    }
}
