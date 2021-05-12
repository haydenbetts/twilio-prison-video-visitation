package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/AcView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: AcView.kt */
public interface AcView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: AcView.kt */
    public static final class DefaultImpls {
        public static void showProgress(AcView acView, boolean z) {
            BaseView.DefaultImpls.showProgress(acView, z);
        }

        public static void updateNotificationMenu(AcView acView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(acView, i);
        }
    }
}
