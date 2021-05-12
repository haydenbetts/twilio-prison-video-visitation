package com.forasoft.homewavvisitor.presentation.view.account;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class AccountView$$State extends MvpViewState<AccountView> implements AccountView {
    public void setName(String str) {
        SetNameCommand setNameCommand = new SetNameCommand(str);
        this.viewCommands.beforeApply(setNameCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView name : this.views) {
                name.setName(str);
            }
            this.viewCommands.afterApply(setNameCommand);
        }
    }

    public void setPhone(String str) {
        SetPhoneCommand setPhoneCommand = new SetPhoneCommand(str);
        this.viewCommands.beforeApply(setPhoneCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView phone : this.views) {
                phone.setPhone(str);
            }
            this.viewCommands.afterApply(setPhoneCommand);
        }
    }

    public void setEmail(String str) {
        SetEmailCommand setEmailCommand = new SetEmailCommand(str);
        this.viewCommands.beforeApply(setEmailCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView email : this.views) {
                email.setEmail(str);
            }
            this.viewCommands.afterApply(setEmailCommand);
        }
    }

    public void setPin(String str) {
        SetPinCommand setPinCommand = new SetPinCommand(str);
        this.viewCommands.beforeApply(setPinCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView pin : this.views) {
                pin.setPin(str);
            }
            this.viewCommands.afterApply(setPinCommand);
        }
    }

    public void setAvatarUrl(String str) {
        SetAvatarUrlCommand setAvatarUrlCommand = new SetAvatarUrlCommand(str);
        this.viewCommands.beforeApply(setAvatarUrlCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView avatarUrl : this.views) {
                avatarUrl.setAvatarUrl(str);
            }
            this.viewCommands.afterApply(setAvatarUrlCommand);
        }
    }

    public void setEmailSubscription(boolean z) {
        SetEmailSubscriptionCommand setEmailSubscriptionCommand = new SetEmailSubscriptionCommand(z);
        this.viewCommands.beforeApply(setEmailSubscriptionCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView emailSubscription : this.views) {
                emailSubscription.setEmailSubscription(z);
            }
            this.viewCommands.afterApply(setEmailSubscriptionCommand);
        }
    }

    public void setLocation(String str) {
        SetLocationCommand setLocationCommand = new SetLocationCommand(str);
        this.viewCommands.beforeApply(setLocationCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView location : this.views) {
                location.setLocation(str);
            }
            this.viewCommands.afterApply(setLocationCommand);
        }
    }

    public void createAvatarFromName(String str) {
        CreateAvatarFromNameCommand createAvatarFromNameCommand = new CreateAvatarFromNameCommand(str);
        this.viewCommands.beforeApply(createAvatarFromNameCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView createAvatarFromName : this.views) {
                createAvatarFromName.createAvatarFromName(str);
            }
            this.viewCommands.afterApply(createAvatarFromNameCommand);
        }
    }

    public void enableEditPhoto(boolean z) {
        EnableEditPhotoCommand enableEditPhotoCommand = new EnableEditPhotoCommand(z);
        this.viewCommands.beforeApply(enableEditPhotoCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView enableEditPhoto : this.views) {
                enableEditPhoto.enableEditPhoto(z);
            }
            this.viewCommands.afterApply(enableEditPhotoCommand);
        }
    }

    public void showSubscribeDialog() {
        ShowSubscribeDialogCommand showSubscribeDialogCommand = new ShowSubscribeDialogCommand();
        this.viewCommands.beforeApply(showSubscribeDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView showSubscribeDialog : this.views) {
                showSubscribeDialog.showSubscribeDialog();
            }
            this.viewCommands.afterApply(showSubscribeDialogCommand);
        }
    }

    public void showUnsubscribeDialog() {
        ShowUnsubscribeDialogCommand showUnsubscribeDialogCommand = new ShowUnsubscribeDialogCommand();
        this.viewCommands.beforeApply(showUnsubscribeDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView showUnsubscribeDialog : this.views) {
                showUnsubscribeDialog.showUnsubscribeDialog();
            }
            this.viewCommands.afterApply(showUnsubscribeDialogCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (AccountView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (AccountView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: AccountView$$State */
    public class SetNameCommand extends ViewCommand<AccountView> {
        public final String fullName;

        SetNameCommand(String str) {
            super("setName", AddToEndSingleStrategy.class);
            this.fullName = str;
        }

        public void apply(AccountView accountView) {
            accountView.setName(this.fullName);
        }
    }

    /* compiled from: AccountView$$State */
    public class SetPhoneCommand extends ViewCommand<AccountView> {
        public final String phone;

        SetPhoneCommand(String str) {
            super("setPhone", AddToEndSingleStrategy.class);
            this.phone = str;
        }

        public void apply(AccountView accountView) {
            accountView.setPhone(this.phone);
        }
    }

    /* compiled from: AccountView$$State */
    public class SetEmailCommand extends ViewCommand<AccountView> {
        public final String email;

        SetEmailCommand(String str) {
            super("setEmail", AddToEndSingleStrategy.class);
            this.email = str;
        }

        public void apply(AccountView accountView) {
            accountView.setEmail(this.email);
        }
    }

    /* compiled from: AccountView$$State */
    public class SetPinCommand extends ViewCommand<AccountView> {
        public final String pin;

        SetPinCommand(String str) {
            super("setPin", AddToEndSingleStrategy.class);
            this.pin = str;
        }

        public void apply(AccountView accountView) {
            accountView.setPin(this.pin);
        }
    }

    /* compiled from: AccountView$$State */
    public class SetAvatarUrlCommand extends ViewCommand<AccountView> {
        public final String avatarUrl;

        SetAvatarUrlCommand(String str) {
            super("setAvatarUrl", AddToEndSingleStrategy.class);
            this.avatarUrl = str;
        }

        public void apply(AccountView accountView) {
            accountView.setAvatarUrl(this.avatarUrl);
        }
    }

    /* compiled from: AccountView$$State */
    public class SetEmailSubscriptionCommand extends ViewCommand<AccountView> {
        public final boolean isEnabled;

        SetEmailSubscriptionCommand(boolean z) {
            super("setEmailSubscription", SkipStrategy.class);
            this.isEnabled = z;
        }

        public void apply(AccountView accountView) {
            accountView.setEmailSubscription(this.isEnabled);
        }
    }

    /* compiled from: AccountView$$State */
    public class SetLocationCommand extends ViewCommand<AccountView> {
        public final String location;

        SetLocationCommand(String str) {
            super("setLocation", AddToEndSingleStrategy.class);
            this.location = str;
        }

        public void apply(AccountView accountView) {
            accountView.setLocation(this.location);
        }
    }

    /* compiled from: AccountView$$State */
    public class CreateAvatarFromNameCommand extends ViewCommand<AccountView> {
        public final String name;

        CreateAvatarFromNameCommand(String str) {
            super("createAvatarFromName", AddToEndSingleStrategy.class);
            this.name = str;
        }

        public void apply(AccountView accountView) {
            accountView.createAvatarFromName(this.name);
        }
    }

    /* compiled from: AccountView$$State */
    public class EnableEditPhotoCommand extends ViewCommand<AccountView> {
        public final boolean enable;

        EnableEditPhotoCommand(boolean z) {
            super("enableEditPhoto", AddToEndSingleStrategy.class);
            this.enable = z;
        }

        public void apply(AccountView accountView) {
            accountView.enableEditPhoto(this.enable);
        }
    }

    /* compiled from: AccountView$$State */
    public class ShowSubscribeDialogCommand extends ViewCommand<AccountView> {
        ShowSubscribeDialogCommand() {
            super("showSubscribeDialog", SkipStrategy.class);
        }

        public void apply(AccountView accountView) {
            accountView.showSubscribeDialog();
        }
    }

    /* compiled from: AccountView$$State */
    public class ShowUnsubscribeDialogCommand extends ViewCommand<AccountView> {
        ShowUnsubscribeDialogCommand() {
            super("showUnsubscribeDialog", SkipStrategy.class);
        }

        public void apply(AccountView accountView) {
            accountView.showUnsubscribeDialog();
        }
    }

    /* compiled from: AccountView$$State */
    public class ShowMessageCommand extends ViewCommand<AccountView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(AccountView accountView) {
            accountView.showMessage(this.message);
        }
    }

    /* compiled from: AccountView$$State */
    public class ShowMessage1Command extends ViewCommand<AccountView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(AccountView accountView) {
            accountView.showMessage(this.resId);
        }
    }

    /* compiled from: AccountView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<AccountView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(AccountView accountView) {
            accountView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: AccountView$$State */
    public class ShowProgressCommand extends ViewCommand<AccountView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(AccountView accountView) {
            accountView.showProgress(this.show);
        }
    }

    /* compiled from: AccountView$$State */
    public class ShowServerErrorCommand extends ViewCommand<AccountView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(AccountView accountView) {
            accountView.showServerError();
        }
    }
}
