package com.forasoft.homewavvisitor.presentation.view.visits;

import com.forasoft.homewavvisitor.model.data.TimeSlot;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0005H&¨\u0006\u000e"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/visits/TimeChooserView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "displaySelectedDay", "", "date", "", "displaySlots", "slots", "", "Lcom/forasoft/homewavvisitor/model/data/TimeSlot;", "showConfirmDialog", "inmate", "updateToolbar", "title", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TimeChooserView.kt */
public interface TimeChooserView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: TimeChooserView.kt */
    public static final class DefaultImpls {
        public static void showProgress(TimeChooserView timeChooserView, boolean z) {
            BaseView.DefaultImpls.showProgress(timeChooserView, z);
        }

        public static void updateNotificationMenu(TimeChooserView timeChooserView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(timeChooserView, i);
        }
    }

    void displaySelectedDay(String str);

    void displaySlots(List<TimeSlot> list);

    void showConfirmDialog(String str, String str2);

    void updateToolbar(String str);
}
