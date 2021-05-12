package com.forasoft.homewavvisitor.presentation.view.register;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import java.util.List;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0007H&J \u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH'J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H&J\u0016\u0010\u0011\u001a\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0013H'J\b\u0010\u0014\u001a\u00020\u0003H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0007H&¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/register/SignUp2View;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "clearErrors", "", "hideProgress", "scrollToError", "field", "", "showDate", "formatedDate", "showDatePickDialog", "day", "", "month", "year", "showFieldError", "message", "showPickStateDialog", "statesList", "", "showProgress", "showSateAbbreviation", "abbreviation", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: SignUp2View.kt */
public interface SignUp2View extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: SignUp2View.kt */
    public static final class DefaultImpls {
        public static void showProgress(SignUp2View signUp2View, boolean z) {
            BaseView.DefaultImpls.showProgress(signUp2View, z);
        }

        public static void updateNotificationMenu(SignUp2View signUp2View, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(signUp2View, i);
        }
    }

    void clearErrors();

    void hideProgress();

    void scrollToError(String str);

    void showDate(String str);

    @StateStrategyType(SkipStrategy.class)
    void showDatePickDialog(int i, int i2, int i3);

    void showFieldError(String str, String str2);

    @StateStrategyType(SkipStrategy.class)
    void showPickStateDialog(List<String> list);

    void showProgress();

    void showSateAbbreviation(String str);
}
