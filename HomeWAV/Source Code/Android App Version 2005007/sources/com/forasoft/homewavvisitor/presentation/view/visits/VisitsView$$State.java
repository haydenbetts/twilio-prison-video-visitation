package com.forasoft.homewavvisitor.presentation.view.visits;

import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class VisitsView$$State extends MvpViewState<VisitsView> implements VisitsView {
    public void displayScheduledVisits(List<ScheduledVisit> list) {
        DisplayScheduledVisitsCommand displayScheduledVisitsCommand = new DisplayScheduledVisitsCommand(list);
        this.viewCommands.beforeApply(displayScheduledVisitsCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitsView displayScheduledVisits : this.views) {
                displayScheduledVisits.displayScheduledVisits(list);
            }
            this.viewCommands.afterApply(displayScheduledVisitsCommand);
        }
    }

    public void displayPendingVisits(List<ScheduledVisit> list) {
        DisplayPendingVisitsCommand displayPendingVisitsCommand = new DisplayPendingVisitsCommand(list);
        this.viewCommands.beforeApply(displayPendingVisitsCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitsView displayPendingVisits : this.views) {
                displayPendingVisits.displayPendingVisits(list);
            }
            this.viewCommands.afterApply(displayPendingVisitsCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitsView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitsView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (VisitsView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitsView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (VisitsView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    /* compiled from: VisitsView$$State */
    public class DisplayScheduledVisitsCommand extends ViewCommand<VisitsView> {
        public final List<ScheduledVisit> visits;

        DisplayScheduledVisitsCommand(List<ScheduledVisit> list) {
            super("displayScheduledVisits", AddToEndSingleStrategy.class);
            this.visits = list;
        }

        public void apply(VisitsView visitsView) {
            visitsView.displayScheduledVisits(this.visits);
        }
    }

    /* compiled from: VisitsView$$State */
    public class DisplayPendingVisitsCommand extends ViewCommand<VisitsView> {
        public final List<ScheduledVisit> visits;

        DisplayPendingVisitsCommand(List<ScheduledVisit> list) {
            super("displayPendingVisits", AddToEndSingleStrategy.class);
            this.visits = list;
        }

        public void apply(VisitsView visitsView) {
            visitsView.displayPendingVisits(this.visits);
        }
    }

    /* compiled from: VisitsView$$State */
    public class ShowServerErrorCommand extends ViewCommand<VisitsView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(VisitsView visitsView) {
            visitsView.showServerError();
        }
    }

    /* compiled from: VisitsView$$State */
    public class ShowMessageCommand extends ViewCommand<VisitsView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(VisitsView visitsView) {
            visitsView.showMessage(this.message);
        }
    }

    /* compiled from: VisitsView$$State */
    public class ShowMessage1Command extends ViewCommand<VisitsView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(VisitsView visitsView) {
            visitsView.showMessage(this.resId);
        }
    }

    /* compiled from: VisitsView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<VisitsView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(VisitsView visitsView) {
            visitsView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: VisitsView$$State */
    public class ShowProgressCommand extends ViewCommand<VisitsView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(VisitsView visitsView) {
            visitsView.showProgress(this.show);
        }
    }
}
