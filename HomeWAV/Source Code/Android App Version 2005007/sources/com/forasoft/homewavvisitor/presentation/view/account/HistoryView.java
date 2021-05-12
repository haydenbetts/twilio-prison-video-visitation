package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.model.data.account.HistoryItem;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007H&J \u0010\b\u001a\u00020\u00032\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007H&J \u0010\n\u001a\u00020\u00032\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0016\u0010\u0018\u001a\u00020\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0006H&¨\u0006\u001e"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/HistoryView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "initInmatesSpinner", "", "inmatesList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "initOrderSpinner", "historyOrderList", "initPeriodSpinner", "historyPeriodList", "selectActivities", "selectCalls", "selectMessages", "selectMoney", "setActivitiesCount", "count", "", "setCallsCount", "setMessagesCount", "setMoneyAmount", "amount", "", "showData", "body", "", "Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem;", "showPeriod", "text", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: HistoryView.kt */
public interface HistoryView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: HistoryView.kt */
    public static final class DefaultImpls {
        public static void showProgress(HistoryView historyView, boolean z) {
            BaseView.DefaultImpls.showProgress(historyView, z);
        }

        public static void updateNotificationMenu(HistoryView historyView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(historyView, i);
        }
    }

    void initInmatesSpinner(ArrayList<String> arrayList);

    void initOrderSpinner(ArrayList<String> arrayList);

    void initPeriodSpinner(ArrayList<String> arrayList);

    void selectActivities();

    void selectCalls();

    void selectMessages();

    void selectMoney();

    void setActivitiesCount(int i);

    void setCallsCount(int i);

    void setMessagesCount(int i);

    void setMoneyAmount(float f);

    void showData(List<HistoryItem> list);

    void showPeriod(String str);
}
