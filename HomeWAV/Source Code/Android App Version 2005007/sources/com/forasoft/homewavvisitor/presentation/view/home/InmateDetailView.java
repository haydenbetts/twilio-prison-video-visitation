package com.forasoft.homewavvisitor.presentation.view.home;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0007H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0007H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0007H&J\u0012\u0010\u0013\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0007H&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0007H&¨\u0006\u0018"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/home/InmateDetailView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "setCheckActive", "", "setCheckInactive", "setCredits", "balance", "", "setId", "identifier", "setInvisible", "allowCalls", "", "setMoney", "credit_balance", "setName", "name", "setStatus", "status", "showDeleteDialog", "showVisitsNumber", "visits", "showVisitsText", "text", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: InmateDetailView.kt */
public interface InmateDetailView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: InmateDetailView.kt */
    public static final class DefaultImpls {
        public static void showProgress(InmateDetailView inmateDetailView, boolean z) {
            BaseView.DefaultImpls.showProgress(inmateDetailView, z);
        }

        public static void updateNotificationMenu(InmateDetailView inmateDetailView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(inmateDetailView, i);
        }
    }

    void setCheckActive();

    void setCheckInactive();

    void setCredits(String str);

    void setId(String str);

    void setInvisible(boolean z);

    void setMoney(String str);

    void setName(String str);

    void setStatus(String str);

    void showDeleteDialog(String str);

    void showVisitsNumber(String str);

    void showVisitsText(String str);
}
