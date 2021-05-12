package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.model.data.Inmate;
import kotlin.Metadata;
import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH'J\b\u0010\n\u001a\u00020\u0003H'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eH'¨\u0006\u000f"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/TransferFundsView;", "Lmoxy/MvpView;", "hideProgressDialog", "", "showFromInmate", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "showMessage", "resId", "", "showProgressDialog", "showToInmate", "showTransferFee", "transferFee", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TransferFundsView.kt */
public interface TransferFundsView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void hideProgressDialog();

    void showFromInmate(Inmate inmate);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showMessage(int i);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showProgressDialog();

    void showToInmate(Inmate inmate);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showTransferFee(String str);
}
