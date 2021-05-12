package com.forasoft.homewavvisitor.presentation.view;

import com.forasoft.homewavvisitor.ui.activity.ServerErrorDisplay;
import kotlin.Metadata;
import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH'J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0006H\u0016¨\u0006\u000e"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "Lmoxy/MvpView;", "Lcom/forasoft/homewavvisitor/ui/activity/ServerErrorDisplay;", "showMessage", "", "resId", "", "message", "", "showProgress", "show", "", "updateNotificationMenu", "count", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: BaseView.kt */
public interface BaseView extends MvpView, ServerErrorDisplay {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: BaseView.kt */
    public static final class DefaultImpls {
        public static void showProgress(BaseView baseView, boolean z) {
        }

        public static void updateNotificationMenu(BaseView baseView, int i) {
        }
    }

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showMessage(int i);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showMessage(String str);

    void showProgress(boolean z);

    void updateNotificationMenu(int i);
}
