package com.forasoft.homewavvisitor.presentation.view.home;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.urbanairship.messagecenter.Message;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class HomeView$$State extends MvpViewState<HomeView> implements HomeView {
    public void showApprovedInmates(List<Inmate> list) {
        ShowApprovedInmatesCommand showApprovedInmatesCommand = new ShowApprovedInmatesCommand(list);
        this.viewCommands.beforeApply(showApprovedInmatesCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView showApprovedInmates : this.views) {
                showApprovedInmates.showApprovedInmates(list);
            }
            this.viewCommands.afterApply(showApprovedInmatesCommand);
        }
    }

    public void updatePendingInmate(Inmate inmate, boolean z) {
        UpdatePendingInmateCommand updatePendingInmateCommand = new UpdatePendingInmateCommand(inmate, z);
        this.viewCommands.beforeApply(updatePendingInmateCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView updatePendingInmate : this.views) {
                updatePendingInmate.updatePendingInmate(inmate, z);
            }
            this.viewCommands.afterApply(updatePendingInmateCommand);
        }
    }

    public void showPendingInmate(boolean z) {
        ShowPendingInmateCommand showPendingInmateCommand = new ShowPendingInmateCommand(z);
        this.viewCommands.beforeApply(showPendingInmateCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView showPendingInmate : this.views) {
                showPendingInmate.showPendingInmate(z);
            }
            this.viewCommands.afterApply(showPendingInmateCommand);
        }
    }

    public void updateNextVisit(ScheduledVisit scheduledVisit) {
        UpdateNextVisitCommand updateNextVisitCommand = new UpdateNextVisitCommand(scheduledVisit);
        this.viewCommands.beforeApply(updateNextVisitCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView updateNextVisit : this.views) {
                updateNextVisit.updateNextVisit(scheduledVisit);
            }
            this.viewCommands.afterApply(updateNextVisitCommand);
        }
    }

    public void showNextVisit(boolean z) {
        ShowNextVisitCommand showNextVisitCommand = new ShowNextVisitCommand(z);
        this.viewCommands.beforeApply(showNextVisitCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView showNextVisit : this.views) {
                showNextVisit.showNextVisit(z);
            }
            this.viewCommands.afterApply(showNextVisitCommand);
        }
    }

    public void showWarningDialog(List<Inmate> list) {
        ShowWarningDialogCommand showWarningDialogCommand = new ShowWarningDialogCommand(list);
        this.viewCommands.beforeApply(showWarningDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView showWarningDialog : this.views) {
                showWarningDialog.showWarningDialog(list);
            }
            this.viewCommands.afterApply(showWarningDialogCommand);
        }
    }

    public void showEmptyMessageCenter() {
        ShowEmptyMessageCenterCommand showEmptyMessageCenterCommand = new ShowEmptyMessageCenterCommand();
        this.viewCommands.beforeApply(showEmptyMessageCenterCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView showEmptyMessageCenter : this.views) {
                showEmptyMessageCenter.showEmptyMessageCenter();
            }
            this.viewCommands.afterApply(showEmptyMessageCenterCommand);
        }
    }

    public void updateMessageCenterView(Message message, int i, boolean z) {
        UpdateMessageCenterViewCommand updateMessageCenterViewCommand = new UpdateMessageCenterViewCommand(message, i, z);
        this.viewCommands.beforeApply(updateMessageCenterViewCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView updateMessageCenterView : this.views) {
                updateMessageCenterView.updateMessageCenterView(message, i, z);
            }
            this.viewCommands.afterApply(updateMessageCenterViewCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (HomeView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (HomeView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    /* compiled from: HomeView$$State */
    public class ShowApprovedInmatesCommand extends ViewCommand<HomeView> {
        public final List<Inmate> inmates;

        ShowApprovedInmatesCommand(List<Inmate> list) {
            super("showApprovedInmates", AddToEndSingleStrategy.class);
            this.inmates = list;
        }

        public void apply(HomeView homeView) {
            homeView.showApprovedInmates(this.inmates);
        }
    }

    /* compiled from: HomeView$$State */
    public class UpdatePendingInmateCommand extends ViewCommand<HomeView> {
        public final boolean displayLink;
        public final Inmate inmate;

        UpdatePendingInmateCommand(Inmate inmate2, boolean z) {
            super("updatePendingInmate", AddToEndSingleStrategy.class);
            this.inmate = inmate2;
            this.displayLink = z;
        }

        public void apply(HomeView homeView) {
            homeView.updatePendingInmate(this.inmate, this.displayLink);
        }
    }

    /* compiled from: HomeView$$State */
    public class ShowPendingInmateCommand extends ViewCommand<HomeView> {
        public final boolean show;

        ShowPendingInmateCommand(boolean z) {
            super("showPendingInmate", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(HomeView homeView) {
            homeView.showPendingInmate(this.show);
        }
    }

    /* compiled from: HomeView$$State */
    public class UpdateNextVisitCommand extends ViewCommand<HomeView> {
        public final ScheduledVisit visit;

        UpdateNextVisitCommand(ScheduledVisit scheduledVisit) {
            super("updateNextVisit", AddToEndSingleStrategy.class);
            this.visit = scheduledVisit;
        }

        public void apply(HomeView homeView) {
            homeView.updateNextVisit(this.visit);
        }
    }

    /* compiled from: HomeView$$State */
    public class ShowNextVisitCommand extends ViewCommand<HomeView> {
        public final boolean show;

        ShowNextVisitCommand(boolean z) {
            super("showNextVisit", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(HomeView homeView) {
            homeView.showNextVisit(this.show);
        }
    }

    /* compiled from: HomeView$$State */
    public class ShowWarningDialogCommand extends ViewCommand<HomeView> {
        public final List<Inmate> inmates;

        ShowWarningDialogCommand(List<Inmate> list) {
            super("showWarningDialog", SkipStrategy.class);
            this.inmates = list;
        }

        public void apply(HomeView homeView) {
            homeView.showWarningDialog(this.inmates);
        }
    }

    /* compiled from: HomeView$$State */
    public class ShowEmptyMessageCenterCommand extends ViewCommand<HomeView> {
        ShowEmptyMessageCenterCommand() {
            super("showEmptyMessageCenter", AddToEndSingleStrategy.class);
        }

        public void apply(HomeView homeView) {
            homeView.showEmptyMessageCenter();
        }
    }

    /* compiled from: HomeView$$State */
    public class UpdateMessageCenterViewCommand extends ViewCommand<HomeView> {
        public final int count;
        public final boolean isNeedFullRerender;
        public final Message message;

        UpdateMessageCenterViewCommand(Message message2, int i, boolean z) {
            super("updateMessageCenterView", AddToEndSingleStrategy.class);
            this.message = message2;
            this.count = i;
            this.isNeedFullRerender = z;
        }

        public void apply(HomeView homeView) {
            homeView.updateMessageCenterView(this.message, this.count, this.isNeedFullRerender);
        }
    }

    /* compiled from: HomeView$$State */
    public class ShowServerErrorCommand extends ViewCommand<HomeView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(HomeView homeView) {
            homeView.showServerError();
        }
    }

    /* compiled from: HomeView$$State */
    public class ShowMessageCommand extends ViewCommand<HomeView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(HomeView homeView) {
            homeView.showMessage(this.message);
        }
    }

    /* compiled from: HomeView$$State */
    public class ShowMessage1Command extends ViewCommand<HomeView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(HomeView homeView) {
            homeView.showMessage(this.resId);
        }
    }

    /* compiled from: HomeView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<HomeView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(HomeView homeView) {
            homeView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: HomeView$$State */
    public class ShowProgressCommand extends ViewCommand<HomeView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(HomeView homeView) {
            homeView.showProgress(this.show);
        }
    }
}
