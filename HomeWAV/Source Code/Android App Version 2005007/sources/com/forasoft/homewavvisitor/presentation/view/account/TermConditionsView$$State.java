package com.forasoft.homewavvisitor.presentation.view.account;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class TermConditionsView$$State extends MvpViewState<TermConditionsView> implements TermConditionsView {
    public void showTerms(String str) {
        ShowTermsCommand showTermsCommand = new ShowTermsCommand(str);
        this.viewCommands.beforeApply(showTermsCommand);
        if (!hasNotView().booleanValue()) {
            for (TermConditionsView showTerms : this.views) {
                showTerms.showTerms(str);
            }
            this.viewCommands.afterApply(showTermsCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (TermConditionsView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (TermConditionsView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (TermConditionsView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (TermConditionsView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (TermConditionsView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: TermConditionsView$$State */
    public class ShowTermsCommand extends ViewCommand<TermConditionsView> {
        public final String termsText;

        ShowTermsCommand(String str) {
            super("showTerms", AddToEndSingleStrategy.class);
            this.termsText = str;
        }

        public void apply(TermConditionsView termConditionsView) {
            termConditionsView.showTerms(this.termsText);
        }
    }

    /* compiled from: TermConditionsView$$State */
    public class ShowMessageCommand extends ViewCommand<TermConditionsView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(TermConditionsView termConditionsView) {
            termConditionsView.showMessage(this.message);
        }
    }

    /* compiled from: TermConditionsView$$State */
    public class ShowMessage1Command extends ViewCommand<TermConditionsView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(TermConditionsView termConditionsView) {
            termConditionsView.showMessage(this.resId);
        }
    }

    /* compiled from: TermConditionsView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<TermConditionsView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(TermConditionsView termConditionsView) {
            termConditionsView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: TermConditionsView$$State */
    public class ShowProgressCommand extends ViewCommand<TermConditionsView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(TermConditionsView termConditionsView) {
            termConditionsView.showProgress(this.show);
        }
    }

    /* compiled from: TermConditionsView$$State */
    public class ShowServerErrorCommand extends ViewCommand<TermConditionsView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(TermConditionsView termConditionsView) {
            termConditionsView.showServerError();
        }
    }
}
