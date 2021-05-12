package com.forasoft.homewavvisitor.presentation.view.register;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class CreateAccountView$$State extends MvpViewState<CreateAccountView> implements CreateAccountView {
    public void setStepperStep(int i) {
        SetStepperStepCommand setStepperStepCommand = new SetStepperStepCommand(i);
        this.viewCommands.beforeApply(setStepperStepCommand);
        if (!hasNotView().booleanValue()) {
            for (CreateAccountView stepperStep : this.views) {
                stepperStep.setStepperStep(i);
            }
            this.viewCommands.afterApply(setStepperStepCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (CreateAccountView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (CreateAccountView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (CreateAccountView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (CreateAccountView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (CreateAccountView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: CreateAccountView$$State */
    public class SetStepperStepCommand extends ViewCommand<CreateAccountView> {
        public final int step;

        SetStepperStepCommand(int i) {
            super("setStepperStep", AddToEndSingleStrategy.class);
            this.step = i;
        }

        public void apply(CreateAccountView createAccountView) {
            createAccountView.setStepperStep(this.step);
        }
    }

    /* compiled from: CreateAccountView$$State */
    public class ShowMessageCommand extends ViewCommand<CreateAccountView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(CreateAccountView createAccountView) {
            createAccountView.showMessage(this.message);
        }
    }

    /* compiled from: CreateAccountView$$State */
    public class ShowMessage1Command extends ViewCommand<CreateAccountView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(CreateAccountView createAccountView) {
            createAccountView.showMessage(this.resId);
        }
    }

    /* compiled from: CreateAccountView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<CreateAccountView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(CreateAccountView createAccountView) {
            createAccountView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: CreateAccountView$$State */
    public class ShowProgressCommand extends ViewCommand<CreateAccountView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(CreateAccountView createAccountView) {
            createAccountView.showProgress(this.show);
        }
    }

    /* compiled from: CreateAccountView$$State */
    public class ShowServerErrorCommand extends ViewCommand<CreateAccountView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(CreateAccountView createAccountView) {
            createAccountView.showServerError();
        }
    }
}
