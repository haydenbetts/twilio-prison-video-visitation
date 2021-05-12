package com.forasoft.homewavvisitor.presentation.view.account;

import java.util.List;
import kotlin.Metadata;
import moxy.MvpView;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/RefundsView;", "Lmoxy/MvpView;", "showInmates", "", "inmates", "", "", "showMessage", "message", "updateTotalAmount", "amount", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RefundsView.kt */
public interface RefundsView extends MvpView {
    void showInmates(List<String> list);

    void showMessage(String str);

    void updateTotalAmount(float f);
}
