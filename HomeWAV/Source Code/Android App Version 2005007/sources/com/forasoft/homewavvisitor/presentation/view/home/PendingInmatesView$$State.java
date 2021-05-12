package com.forasoft.homewavvisitor.presentation.view.home;

import com.forasoft.homewavvisitor.model.data.Inmate;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class PendingInmatesView$$State extends MvpViewState<PendingInmatesView> implements PendingInmatesView {
    public void displayInmates(List<Inmate> list) {
        DisplayInmatesCommand displayInmatesCommand = new DisplayInmatesCommand(list);
        this.viewCommands.beforeApply(displayInmatesCommand);
        if (!hasNotView().booleanValue()) {
            for (PendingInmatesView displayInmates : this.views) {
                displayInmates.displayInmates(list);
            }
            this.viewCommands.afterApply(displayInmatesCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (PendingInmatesView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (PendingInmatesView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (PendingInmatesView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (PendingInmatesView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (PendingInmatesView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: PendingInmatesView$$State */
    public class DisplayInmatesCommand extends ViewCommand<PendingInmatesView> {
        public final List<Inmate> inmates;

        DisplayInmatesCommand(List<Inmate> list) {
            super("displayInmates", AddToEndSingleStrategy.class);
            this.inmates = list;
        }

        public void apply(PendingInmatesView pendingInmatesView) {
            pendingInmatesView.displayInmates(this.inmates);
        }
    }

    /* compiled from: PendingInmatesView$$State */
    public class ShowMessageCommand extends ViewCommand<PendingInmatesView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(PendingInmatesView pendingInmatesView) {
            pendingInmatesView.showMessage(this.message);
        }
    }

    /* compiled from: PendingInmatesView$$State */
    public class ShowMessage1Command extends ViewCommand<PendingInmatesView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(PendingInmatesView pendingInmatesView) {
            pendingInmatesView.showMessage(this.resId);
        }
    }

    /* compiled from: PendingInmatesView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<PendingInmatesView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(PendingInmatesView pendingInmatesView) {
            pendingInmatesView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: PendingInmatesView$$State */
    public class ShowProgressCommand extends ViewCommand<PendingInmatesView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(PendingInmatesView pendingInmatesView) {
            pendingInmatesView.showProgress(this.show);
        }
    }

    /* compiled from: PendingInmatesView$$State */
    public class ShowServerErrorCommand extends ViewCommand<PendingInmatesView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(PendingInmatesView pendingInmatesView) {
            pendingInmatesView.showServerError();
        }
    }
}
