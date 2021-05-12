package com.forasoft.homewavvisitor.presentation.view;

import com.forasoft.homewavvisitor.model.data.Call;
import kotlin.Metadata;
import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/MainView;", "Lmoxy/MvpView;", "showCallDialog", "", "call", "Lcom/forasoft/homewavvisitor/model/data/Call;", "showDisapprovedDialog", "showTermsConditionsDialog", "updateMessagesBadge", "count", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MainView.kt */
public interface MainView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showCallDialog(Call call);

    void showDisapprovedDialog();

    void showTermsConditionsDialog();

    void updateMessagesBadge(int i);
}
