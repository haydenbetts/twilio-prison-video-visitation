package com.forasoft.homewavvisitor.presentation.view.auth;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class SignInView$$State extends MvpViewState<SignInView> implements SignInView {
    public void showError() {
        ShowErrorCommand showErrorCommand = new ShowErrorCommand();
        this.viewCommands.beforeApply(showErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (SignInView showError : this.views) {
                showError.showError();
            }
            this.viewCommands.afterApply(showErrorCommand);
        }
    }

    public void hideError() {
        HideErrorCommand hideErrorCommand = new HideErrorCommand();
        this.viewCommands.beforeApply(hideErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (SignInView hideError : this.views) {
                hideError.hideError();
            }
            this.viewCommands.afterApply(hideErrorCommand);
        }
    }

    public void performLoginClick() {
        PerformLoginClickCommand performLoginClickCommand = new PerformLoginClickCommand();
        this.viewCommands.beforeApply(performLoginClickCommand);
        if (!hasNotView().booleanValue()) {
            for (SignInView performLoginClick : this.views) {
                performLoginClick.performLoginClick();
            }
            this.viewCommands.afterApply(performLoginClickCommand);
        }
    }

    public void showLoading(boolean z) {
        ShowLoadingCommand showLoadingCommand = new ShowLoadingCommand(z);
        this.viewCommands.beforeApply(showLoadingCommand);
        if (!hasNotView().booleanValue()) {
            for (SignInView showLoading : this.views) {
                showLoading.showLoading(z);
            }
            this.viewCommands.afterApply(showLoadingCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (SignInView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (SignInView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (SignInView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (SignInView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (SignInView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    /* compiled from: SignInView$$State */
    public class ShowErrorCommand extends ViewCommand<SignInView> {
        ShowErrorCommand() {
            super("showError", AddToEndSingleStrategy.class);
        }

        public void apply(SignInView signInView) {
            signInView.showError();
        }
    }

    /* compiled from: SignInView$$State */
    public class HideErrorCommand extends ViewCommand<SignInView> {
        HideErrorCommand() {
            super("hideError", AddToEndSingleStrategy.class);
        }

        public void apply(SignInView signInView) {
            signInView.hideError();
        }
    }

    /* compiled from: SignInView$$State */
    public class PerformLoginClickCommand extends ViewCommand<SignInView> {
        PerformLoginClickCommand() {
            super("performLoginClick", AddToEndSingleStrategy.class);
        }

        public void apply(SignInView signInView) {
            signInView.performLoginClick();
        }
    }

    /* compiled from: SignInView$$State */
    public class ShowLoadingCommand extends ViewCommand<SignInView> {
        public final boolean isLoading;

        ShowLoadingCommand(boolean z) {
            super("showLoading", AddToEndSingleStrategy.class);
            this.isLoading = z;
        }

        public void apply(SignInView signInView) {
            signInView.showLoading(this.isLoading);
        }
    }

    /* compiled from: SignInView$$State */
    public class ShowServerErrorCommand extends ViewCommand<SignInView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(SignInView signInView) {
            signInView.showServerError();
        }
    }

    /* compiled from: SignInView$$State */
    public class ShowMessageCommand extends ViewCommand<SignInView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(SignInView signInView) {
            signInView.showMessage(this.message);
        }
    }

    /* compiled from: SignInView$$State */
    public class ShowMessage1Command extends ViewCommand<SignInView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(SignInView signInView) {
            signInView.showMessage(this.resId);
        }
    }

    /* compiled from: SignInView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<SignInView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(SignInView signInView) {
            signInView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: SignInView$$State */
    public class ShowProgressCommand extends ViewCommand<SignInView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(SignInView signInView) {
            signInView.showProgress(this.show);
        }
    }
}
