package com.forasoft.homewavvisitor.presentation.view.home;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import com.forasoft.homewavvisitor.ui.activity.ServerErrorDisplay;
import com.urbanairship.messagecenter.Message;
import java.util.List;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u00012\u00020\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\b\u0010\b\u001a\u00020\u0004H&J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\r\u001a\u00020\u0004H'J\u0016\u0010\u000e\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H'J \u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000bH&J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0018\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u000bH&¨\u0006\u001b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/home/HomeView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "Lcom/forasoft/homewavvisitor/ui/activity/ServerErrorDisplay;", "showApprovedInmates", "", "inmates", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "showEmptyMessageCenter", "showNextVisit", "show", "", "showPendingInmate", "showServerError", "showWarningDialog", "updateMessageCenterView", "message", "Lcom/urbanairship/messagecenter/Message;", "count", "", "isNeedFullRerender", "updateNextVisit", "visit", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "updatePendingInmate", "inmate", "displayLink", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: HomeView.kt */
public interface HomeView extends BaseView, ServerErrorDisplay {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: HomeView.kt */
    public static final class DefaultImpls {
        public static void showProgress(HomeView homeView, boolean z) {
            BaseView.DefaultImpls.showProgress(homeView, z);
        }

        public static void updateNotificationMenu(HomeView homeView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(homeView, i);
        }
    }

    void showApprovedInmates(List<Inmate> list);

    void showEmptyMessageCenter();

    void showNextVisit(boolean z);

    void showPendingInmate(boolean z);

    @StateStrategyType(SkipStrategy.class)
    void showServerError();

    @StateStrategyType(SkipStrategy.class)
    void showWarningDialog(List<Inmate> list);

    void updateMessageCenterView(Message message, int i, boolean z);

    void updateNextVisit(ScheduledVisit scheduledVisit);

    void updatePendingInmate(Inmate inmate, boolean z);
}
