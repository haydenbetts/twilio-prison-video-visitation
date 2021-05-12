package com.forasoft.homewavvisitor.presentation.view.visits;

import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/visits/VisitDetailsView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "displayVisit", "", "visit", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "showConfirmDialog", "updateInmateStatus", "status", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitDetailsView.kt */
public interface VisitDetailsView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: VisitDetailsView.kt */
    public static final class DefaultImpls {
        public static void showProgress(VisitDetailsView visitDetailsView, boolean z) {
            BaseView.DefaultImpls.showProgress(visitDetailsView, z);
        }

        public static void updateNotificationMenu(VisitDetailsView visitDetailsView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(visitDetailsView, i);
        }
    }

    void displayVisit(ScheduledVisit scheduledVisit);

    void showConfirmDialog();

    void updateInmateStatus(String str);
}
