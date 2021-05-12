package com.forasoft.homewavvisitor.presentation.view.visits;

import com.forasoft.homewavvisitor.model.data.DaySlot;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import com.forasoft.homewavvisitor.ui.activity.ServerErrorDisplay;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import org.threeten.bp.LocalDate;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\u001c\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H&J\b\u0010\n\u001a\u00020\u0004H&J\b\u0010\u000b\u001a\u00020\u0004H'J\u0016\u0010\f\u001a\u00020\u00042\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000eH'J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H&¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/visits/DateChooserView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "Lcom/forasoft/homewavvisitor/ui/activity/ServerErrorDisplay;", "hideProgress", "", "initCalendar", "schedule", "", "Lorg/threeten/bp/LocalDate;", "Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "showProgress", "showServerError", "updateCalendar", "slots", "", "updateToolbar", "title", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DateChooserView.kt */
public interface DateChooserView extends BaseView, ServerErrorDisplay {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: DateChooserView.kt */
    public static final class DefaultImpls {
        public static void showProgress(DateChooserView dateChooserView, boolean z) {
            BaseView.DefaultImpls.showProgress(dateChooserView, z);
        }

        public static void updateNotificationMenu(DateChooserView dateChooserView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(dateChooserView, i);
        }
    }

    void hideProgress();

    void initCalendar(Map<LocalDate, DaySlot> map);

    void showProgress();

    @StateStrategyType(SkipStrategy.class)
    void showServerError();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void updateCalendar(List<DaySlot> list);

    void updateToolbar(String str);
}
