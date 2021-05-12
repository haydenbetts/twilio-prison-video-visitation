package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.model.data.Card;
import java.util.List;
import kotlin.Metadata;
import moxy.MvpView;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/ManageCardsView;", "Lmoxy/MvpView;", "displayCards", "", "cards", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "hideProgress", "showProgress", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(OneExecutionStateStrategy.class)
/* compiled from: ManageCardsView.kt */
public interface ManageCardsView extends MvpView {
    void displayCards(List<Card> list);

    void hideProgress();

    void showProgress();
}
