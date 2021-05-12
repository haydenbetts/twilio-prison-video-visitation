package com.forasoft.homewavvisitor.presentation.view.register;

import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class SignUp4View$$State extends MvpViewState<SignUp4View> implements SignUp4View {
    public void showTotalBalance(double d) {
        ShowTotalBalanceCommand showTotalBalanceCommand = new ShowTotalBalanceCommand(d);
        this.viewCommands.beforeApply(showTotalBalanceCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp4View showTotalBalance : this.views) {
                showTotalBalance.showTotalBalance(d);
            }
            this.viewCommands.afterApply(showTotalBalanceCommand);
        }
    }

    public void showConnections(List<InmateByVisitor> list) {
        ShowConnectionsCommand showConnectionsCommand = new ShowConnectionsCommand(list);
        this.viewCommands.beforeApply(showConnectionsCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp4View showConnections : this.views) {
                showConnections.showConnections(list);
            }
            this.viewCommands.afterApply(showConnectionsCommand);
        }
    }

    public void showSuccessMessage() {
        ShowSuccessMessageCommand showSuccessMessageCommand = new ShowSuccessMessageCommand();
        this.viewCommands.beforeApply(showSuccessMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp4View showSuccessMessage : this.views) {
                showSuccessMessage.showSuccessMessage();
            }
            this.viewCommands.afterApply(showSuccessMessageCommand);
        }
    }

    public void showProgress() {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand();
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp4View showProgress : this.views) {
                showProgress.showProgress();
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void hideProgress() {
        HideProgressCommand hideProgressCommand = new HideProgressCommand();
        this.viewCommands.beforeApply(hideProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp4View hideProgress : this.views) {
                hideProgress.hideProgress();
            }
            this.viewCommands.afterApply(hideProgressCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp4View showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (SignUp4View showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp4View updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgress1Command showProgress1Command = new ShowProgress1Command(z);
        this.viewCommands.beforeApply(showProgress1Command);
        if (!hasNotView().booleanValue()) {
            for (SignUp4View showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgress1Command);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp4View showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: SignUp4View$$State */
    public class ShowTotalBalanceCommand extends ViewCommand<SignUp4View> {
        public final double balance;

        ShowTotalBalanceCommand(double d) {
            super("showTotalBalance", AddToEndSingleStrategy.class);
            this.balance = d;
        }

        public void apply(SignUp4View signUp4View) {
            signUp4View.showTotalBalance(this.balance);
        }
    }

    /* compiled from: SignUp4View$$State */
    public class ShowConnectionsCommand extends ViewCommand<SignUp4View> {
        public final List<InmateByVisitor> connections;

        ShowConnectionsCommand(List<InmateByVisitor> list) {
            super("showConnections", AddToEndSingleStrategy.class);
            this.connections = list;
        }

        public void apply(SignUp4View signUp4View) {
            signUp4View.showConnections(this.connections);
        }
    }

    /* compiled from: SignUp4View$$State */
    public class ShowSuccessMessageCommand extends ViewCommand<SignUp4View> {
        ShowSuccessMessageCommand() {
            super("showSuccessMessage", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp4View signUp4View) {
            signUp4View.showSuccessMessage();
        }
    }

    /* compiled from: SignUp4View$$State */
    public class ShowProgressCommand extends ViewCommand<SignUp4View> {
        ShowProgressCommand() {
            super("showProgress", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp4View signUp4View) {
            signUp4View.showProgress();
        }
    }

    /* compiled from: SignUp4View$$State */
    public class HideProgressCommand extends ViewCommand<SignUp4View> {
        HideProgressCommand() {
            super("hideProgress", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp4View signUp4View) {
            signUp4View.hideProgress();
        }
    }

    /* compiled from: SignUp4View$$State */
    public class ShowMessageCommand extends ViewCommand<SignUp4View> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(SignUp4View signUp4View) {
            signUp4View.showMessage(this.message);
        }
    }

    /* compiled from: SignUp4View$$State */
    public class ShowMessage1Command extends ViewCommand<SignUp4View> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(SignUp4View signUp4View) {
            signUp4View.showMessage(this.resId);
        }
    }

    /* compiled from: SignUp4View$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<SignUp4View> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(SignUp4View signUp4View) {
            signUp4View.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: SignUp4View$$State */
    public class ShowProgress1Command extends ViewCommand<SignUp4View> {
        public final boolean show;

        ShowProgress1Command(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(SignUp4View signUp4View) {
            signUp4View.showProgress(this.show);
        }
    }

    /* compiled from: SignUp4View$$State */
    public class ShowServerErrorCommand extends ViewCommand<SignUp4View> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(SignUp4View signUp4View) {
            signUp4View.showServerError();
        }
    }
}
