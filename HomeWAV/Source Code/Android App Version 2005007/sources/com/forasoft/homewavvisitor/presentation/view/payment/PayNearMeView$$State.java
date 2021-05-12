package com.forasoft.homewavvisitor.presentation.view.payment;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class PayNearMeView$$State extends MvpViewState<PayNearMeView> implements PayNearMeView {
    public void showSuccessMessage() {
        ShowSuccessMessageCommand showSuccessMessageCommand = new ShowSuccessMessageCommand();
        this.viewCommands.beforeApply(showSuccessMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (PayNearMeView showSuccessMessage : this.views) {
                showSuccessMessage.showSuccessMessage();
            }
            this.viewCommands.afterApply(showSuccessMessageCommand);
        }
    }

    public void showErrorMessage(String str) {
        ShowErrorMessageCommand showErrorMessageCommand = new ShowErrorMessageCommand(str);
        this.viewCommands.beforeApply(showErrorMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (PayNearMeView showErrorMessage : this.views) {
                showErrorMessage.showErrorMessage(str);
            }
            this.viewCommands.afterApply(showErrorMessageCommand);
        }
    }

    public void setInitialEmail(String str) {
        SetInitialEmailCommand setInitialEmailCommand = new SetInitialEmailCommand(str);
        this.viewCommands.beforeApply(setInitialEmailCommand);
        if (!hasNotView().booleanValue()) {
            for (PayNearMeView initialEmail : this.views) {
                initialEmail.setInitialEmail(str);
            }
            this.viewCommands.afterApply(setInitialEmailCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (PayNearMeView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (PayNearMeView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (PayNearMeView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (PayNearMeView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (PayNearMeView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: PayNearMeView$$State */
    public class ShowSuccessMessageCommand extends ViewCommand<PayNearMeView> {
        ShowSuccessMessageCommand() {
            super("showSuccessMessage", OneExecutionStateStrategy.class);
        }

        public void apply(PayNearMeView payNearMeView) {
            payNearMeView.showSuccessMessage();
        }
    }

    /* compiled from: PayNearMeView$$State */
    public class ShowErrorMessageCommand extends ViewCommand<PayNearMeView> {
        public final String message;

        ShowErrorMessageCommand(String str) {
            super("showErrorMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(PayNearMeView payNearMeView) {
            payNearMeView.showErrorMessage(this.message);
        }
    }

    /* compiled from: PayNearMeView$$State */
    public class SetInitialEmailCommand extends ViewCommand<PayNearMeView> {
        public final String email;

        SetInitialEmailCommand(String str) {
            super("setInitialEmail", AddToEndSingleStrategy.class);
            this.email = str;
        }

        public void apply(PayNearMeView payNearMeView) {
            payNearMeView.setInitialEmail(this.email);
        }
    }

    /* compiled from: PayNearMeView$$State */
    public class ShowMessageCommand extends ViewCommand<PayNearMeView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(PayNearMeView payNearMeView) {
            payNearMeView.showMessage(this.message);
        }
    }

    /* compiled from: PayNearMeView$$State */
    public class ShowMessage1Command extends ViewCommand<PayNearMeView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(PayNearMeView payNearMeView) {
            payNearMeView.showMessage(this.resId);
        }
    }

    /* compiled from: PayNearMeView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<PayNearMeView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(PayNearMeView payNearMeView) {
            payNearMeView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: PayNearMeView$$State */
    public class ShowProgressCommand extends ViewCommand<PayNearMeView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(PayNearMeView payNearMeView) {
            payNearMeView.showProgress(this.show);
        }
    }

    /* compiled from: PayNearMeView$$State */
    public class ShowServerErrorCommand extends ViewCommand<PayNearMeView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(PayNearMeView payNearMeView) {
            payNearMeView.showServerError();
        }
    }
}
