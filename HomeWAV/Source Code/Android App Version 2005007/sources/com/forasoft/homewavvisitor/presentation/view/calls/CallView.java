package com.forasoft.homewavvisitor.presentation.view.calls;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/calls/CallView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "hideWarningMessage", "", "setRecordingWarningText", "englishResId", "", "spanishResId", "showAdminMessage", "message", "", "showReportBugButton", "showWarningMessage", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CallView.kt */
public interface CallView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: CallView.kt */
    public static final class DefaultImpls {
        public static void showProgress(CallView callView, boolean z) {
            BaseView.DefaultImpls.showProgress(callView, z);
        }

        public static void updateNotificationMenu(CallView callView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(callView, i);
        }
    }

    void hideWarningMessage();

    void setRecordingWarningText(int i, int i2);

    void showAdminMessage(String str);

    void showReportBugButton();

    void showWarningMessage();
}
