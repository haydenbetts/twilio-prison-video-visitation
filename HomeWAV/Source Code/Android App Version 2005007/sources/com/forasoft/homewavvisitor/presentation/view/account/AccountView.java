package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\bH'J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0005H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0005H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0005H&J\b\u0010\u0017\u001a\u00020\u0003H'J\b\u0010\u0018\u001a\u00020\u0003H'¨\u0006\u0019"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/AccountView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "createAvatarFromName", "", "name", "", "enableEditPhoto", "enable", "", "setAvatarUrl", "avatarUrl", "setEmail", "email", "setEmailSubscription", "isEnabled", "setLocation", "location", "setName", "fullName", "setPhone", "phone", "setPin", "pin", "showSubscribeDialog", "showUnsubscribeDialog", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: AccountView.kt */
public interface AccountView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: AccountView.kt */
    public static final class DefaultImpls {
        public static void showProgress(AccountView accountView, boolean z) {
            BaseView.DefaultImpls.showProgress(accountView, z);
        }

        public static void updateNotificationMenu(AccountView accountView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(accountView, i);
        }
    }

    void createAvatarFromName(String str);

    void enableEditPhoto(boolean z);

    void setAvatarUrl(String str);

    void setEmail(String str);

    @StateStrategyType(SkipStrategy.class)
    void setEmailSubscription(boolean z);

    void setLocation(String str);

    void setName(String str);

    void setPhone(String str);

    void setPin(String str);

    @StateStrategyType(SkipStrategy.class)
    void showSubscribeDialog();

    @StateStrategyType(SkipStrategy.class)
    void showUnsubscribeDialog();
}
