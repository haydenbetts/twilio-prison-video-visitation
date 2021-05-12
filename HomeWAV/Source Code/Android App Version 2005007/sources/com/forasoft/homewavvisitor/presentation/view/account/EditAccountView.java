package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import java.util.List;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0007H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0007H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0007H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0007H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0007H&J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0007H&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0007H&J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0007H&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0007H&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0007H&J\u0012\u0010\u001e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\u0007H&J \u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"H&J\u0018\u0010%\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007H&J\b\u0010'\u001a\u00020\u0003H&J\u0016\u0010(\u001a\u00020\u00032\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00070*H&J\b\u0010+\u001a\u00020\u0003H&J\u0010\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0007H&¨\u0006."}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/EditAccountView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "clearErrors", "", "hideProgress", "scrollToError", "field", "", "setBirthDate", "birthDate", "setCity", "city", "setEmail", "email", "setFirstName", "firstName", "setLastName", "lastName", "setPhone", "phone", "setPin", "pin", "setState", "state", "setStreet", "street", "setUsername", "username", "setZip", "zip", "showDate", "formatedDate", "showDatePickDialog", "day", "", "month", "year", "showFieldError", "message", "showPasswordField", "showPickStateDialog", "statesList", "", "showProgress", "showSateAbbreviation", "abbreviation", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: EditAccountView.kt */
public interface EditAccountView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: EditAccountView.kt */
    public static final class DefaultImpls {
        public static void showProgress(EditAccountView editAccountView, boolean z) {
            BaseView.DefaultImpls.showProgress(editAccountView, z);
        }

        public static void updateNotificationMenu(EditAccountView editAccountView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(editAccountView, i);
        }
    }

    void clearErrors();

    void hideProgress();

    void scrollToError(String str);

    void setBirthDate(String str);

    void setCity(String str);

    void setEmail(String str);

    void setFirstName(String str);

    void setLastName(String str);

    void setPhone(String str);

    void setPin(String str);

    void setState(String str);

    void setStreet(String str);

    void setUsername(String str);

    void setZip(String str);

    void showDate(String str);

    void showDatePickDialog(int i, int i2, int i3);

    void showFieldError(String str, String str2);

    void showPasswordField();

    void showPickStateDialog(List<String> list);

    void showProgress();

    void showSateAbbreviation(String str);
}
