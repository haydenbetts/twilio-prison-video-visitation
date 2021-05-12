package com.forasoft.homewavvisitor.presentation.view.visits;

import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class VisitDetailsView$$State extends MvpViewState<VisitDetailsView> implements VisitDetailsView {
    public void displayVisit(ScheduledVisit scheduledVisit) {
        DisplayVisitCommand displayVisitCommand = new DisplayVisitCommand(scheduledVisit);
        this.viewCommands.beforeApply(displayVisitCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitDetailsView displayVisit : this.views) {
                displayVisit.displayVisit(scheduledVisit);
            }
            this.viewCommands.afterApply(displayVisitCommand);
        }
    }

    public void updateInmateStatus(String str) {
        UpdateInmateStatusCommand updateInmateStatusCommand = new UpdateInmateStatusCommand(str);
        this.viewCommands.beforeApply(updateInmateStatusCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitDetailsView updateInmateStatus : this.views) {
                updateInmateStatus.updateInmateStatus(str);
            }
            this.viewCommands.afterApply(updateInmateStatusCommand);
        }
    }

    public void showConfirmDialog() {
        ShowConfirmDialogCommand showConfirmDialogCommand = new ShowConfirmDialogCommand();
        this.viewCommands.beforeApply(showConfirmDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitDetailsView showConfirmDialog : this.views) {
                showConfirmDialog.showConfirmDialog();
            }
            this.viewCommands.afterApply(showConfirmDialogCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitDetailsView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (VisitDetailsView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitDetailsView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitDetailsView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitDetailsView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: VisitDetailsView$$State */
    public class DisplayVisitCommand extends ViewCommand<VisitDetailsView> {
        public final ScheduledVisit visit;

        DisplayVisitCommand(ScheduledVisit scheduledVisit) {
            super("displayVisit", AddToEndSingleStrategy.class);
            this.visit = scheduledVisit;
        }

        public void apply(VisitDetailsView visitDetailsView) {
            visitDetailsView.displayVisit(this.visit);
        }
    }

    /* compiled from: VisitDetailsView$$State */
    public class UpdateInmateStatusCommand extends ViewCommand<VisitDetailsView> {
        public final String status;

        UpdateInmateStatusCommand(String str) {
            super("updateInmateStatus", AddToEndSingleStrategy.class);
            this.status = str;
        }

        public void apply(VisitDetailsView visitDetailsView) {
            visitDetailsView.updateInmateStatus(this.status);
        }
    }

    /* compiled from: VisitDetailsView$$State */
    public class ShowConfirmDialogCommand extends ViewCommand<VisitDetailsView> {
        ShowConfirmDialogCommand() {
            super("showConfirmDialog", AddToEndSingleStrategy.class);
        }

        public void apply(VisitDetailsView visitDetailsView) {
            visitDetailsView.showConfirmDialog();
        }
    }

    /* compiled from: VisitDetailsView$$State */
    public class ShowMessageCommand extends ViewCommand<VisitDetailsView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(VisitDetailsView visitDetailsView) {
            visitDetailsView.showMessage(this.message);
        }
    }

    /* compiled from: VisitDetailsView$$State */
    public class ShowMessage1Command extends ViewCommand<VisitDetailsView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(VisitDetailsView visitDetailsView) {
            visitDetailsView.showMessage(this.resId);
        }
    }

    /* compiled from: VisitDetailsView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<VisitDetailsView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(VisitDetailsView visitDetailsView) {
            visitDetailsView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: VisitDetailsView$$State */
    public class ShowProgressCommand extends ViewCommand<VisitDetailsView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(VisitDetailsView visitDetailsView) {
            visitDetailsView.showProgress(this.show);
        }
    }

    /* compiled from: VisitDetailsView$$State */
    public class ShowServerErrorCommand extends ViewCommand<VisitDetailsView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(VisitDetailsView visitDetailsView) {
            visitDetailsView.showServerError();
        }
    }
}
