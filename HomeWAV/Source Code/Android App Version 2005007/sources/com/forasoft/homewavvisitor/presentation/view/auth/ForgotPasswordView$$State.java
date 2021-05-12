package com.forasoft.homewavvisitor.presentation.view.auth;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class ForgotPasswordView$$State extends MvpViewState<ForgotPasswordView> implements ForgotPasswordView {
    public void disableReset(boolean z) {
        DisableResetCommand disableResetCommand = new DisableResetCommand(z);
        this.viewCommands.beforeApply(disableResetCommand);
        if (!hasNotView().booleanValue()) {
            for (ForgotPasswordView disableReset : this.views) {
                disableReset.disableReset(z);
            }
            this.viewCommands.afterApply(disableResetCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (ForgotPasswordView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (ForgotPasswordView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (ForgotPasswordView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (ForgotPasswordView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (ForgotPasswordView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: ForgotPasswordView$$State */
    public class DisableResetCommand extends ViewCommand<ForgotPasswordView> {
        public final boolean disable;

        DisableResetCommand(boolean z) {
            super("disableReset", AddToEndSingleStrategy.class);
            this.disable = z;
        }

        public void apply(ForgotPasswordView forgotPasswordView) {
            forgotPasswordView.disableReset(this.disable);
        }
    }

    /* compiled from: ForgotPasswordView$$State */
    public class ShowMessageCommand extends ViewCommand<ForgotPasswordView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(ForgotPasswordView forgotPasswordView) {
            forgotPasswordView.showMessage(this.message);
        }
    }

    /* compiled from: ForgotPasswordView$$State */
    public class ShowMessage1Command extends ViewCommand<ForgotPasswordView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(ForgotPasswordView forgotPasswordView) {
            forgotPasswordView.showMessage(this.resId);
        }
    }

    /* compiled from: ForgotPasswordView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<ForgotPasswordView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(ForgotPasswordView forgotPasswordView) {
            forgotPasswordView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: ForgotPasswordView$$State */
    public class ShowProgressCommand extends ViewCommand<ForgotPasswordView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(ForgotPasswordView forgotPasswordView) {
            forgotPasswordView.showProgress(this.show);
        }
    }

    /* compiled from: ForgotPasswordView$$State */
    public class ShowServerErrorCommand extends ViewCommand<ForgotPasswordView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(ForgotPasswordView forgotPasswordView) {
            forgotPasswordView.showServerError();
        }
    }
}
