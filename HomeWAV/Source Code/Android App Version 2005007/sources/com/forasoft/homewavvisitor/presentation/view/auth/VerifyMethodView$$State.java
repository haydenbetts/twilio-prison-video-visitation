package com.forasoft.homewavvisitor.presentation.view.auth;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class VerifyMethodView$$State extends MvpViewState<VerifyMethodView> implements VerifyMethodView {
    public void showPhone(String str) {
        ShowPhoneCommand showPhoneCommand = new ShowPhoneCommand(str);
        this.viewCommands.beforeApply(showPhoneCommand);
        if (!hasNotView().booleanValue()) {
            for (VerifyMethodView showPhone : this.views) {
                showPhone.showPhone(str);
            }
            this.viewCommands.afterApply(showPhoneCommand);
        }
    }

    public void showEmail(String str) {
        ShowEmailCommand showEmailCommand = new ShowEmailCommand(str);
        this.viewCommands.beforeApply(showEmailCommand);
        if (!hasNotView().booleanValue()) {
            for (VerifyMethodView showEmail : this.views) {
                showEmail.showEmail(str);
            }
            this.viewCommands.afterApply(showEmailCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (VerifyMethodView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (VerifyMethodView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (VerifyMethodView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (VerifyMethodView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (VerifyMethodView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: VerifyMethodView$$State */
    public class ShowPhoneCommand extends ViewCommand<VerifyMethodView> {
        public final String phone;

        ShowPhoneCommand(String str) {
            super("showPhone", AddToEndSingleStrategy.class);
            this.phone = str;
        }

        public void apply(VerifyMethodView verifyMethodView) {
            verifyMethodView.showPhone(this.phone);
        }
    }

    /* compiled from: VerifyMethodView$$State */
    public class ShowEmailCommand extends ViewCommand<VerifyMethodView> {
        public final String email;

        ShowEmailCommand(String str) {
            super("showEmail", AddToEndSingleStrategy.class);
            this.email = str;
        }

        public void apply(VerifyMethodView verifyMethodView) {
            verifyMethodView.showEmail(this.email);
        }
    }

    /* compiled from: VerifyMethodView$$State */
    public class ShowMessageCommand extends ViewCommand<VerifyMethodView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(VerifyMethodView verifyMethodView) {
            verifyMethodView.showMessage(this.message);
        }
    }

    /* compiled from: VerifyMethodView$$State */
    public class ShowMessage1Command extends ViewCommand<VerifyMethodView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(VerifyMethodView verifyMethodView) {
            verifyMethodView.showMessage(this.resId);
        }
    }

    /* compiled from: VerifyMethodView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<VerifyMethodView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(VerifyMethodView verifyMethodView) {
            verifyMethodView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: VerifyMethodView$$State */
    public class ShowProgressCommand extends ViewCommand<VerifyMethodView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(VerifyMethodView verifyMethodView) {
            verifyMethodView.showProgress(this.show);
        }
    }

    /* compiled from: VerifyMethodView$$State */
    public class ShowServerErrorCommand extends ViewCommand<VerifyMethodView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(VerifyMethodView verifyMethodView) {
            verifyMethodView.showServerError();
        }
    }
}
