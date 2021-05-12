package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.presentation.presenter.account.NotificationWithInmateStatus;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class NotificationsView$$State extends MvpViewState<NotificationsView> implements NotificationsView {
    public void displayNotifications(List<NotificationWithInmateStatus> list) {
        DisplayNotificationsCommand displayNotificationsCommand = new DisplayNotificationsCommand(list);
        this.viewCommands.beforeApply(displayNotificationsCommand);
        if (!hasNotView().booleanValue()) {
            for (NotificationsView displayNotifications : this.views) {
                displayNotifications.displayNotifications(list);
            }
            this.viewCommands.afterApply(displayNotificationsCommand);
        }
    }

    public void updateToolbar(int i) {
        UpdateToolbarCommand updateToolbarCommand = new UpdateToolbarCommand(i);
        this.viewCommands.beforeApply(updateToolbarCommand);
        if (!hasNotView().booleanValue()) {
            for (NotificationsView updateToolbar : this.views) {
                updateToolbar.updateToolbar(i);
            }
            this.viewCommands.afterApply(updateToolbarCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (NotificationsView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (NotificationsView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (NotificationsView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (NotificationsView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (NotificationsView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: NotificationsView$$State */
    public class DisplayNotificationsCommand extends ViewCommand<NotificationsView> {
        public final List<NotificationWithInmateStatus> notifications;

        DisplayNotificationsCommand(List<NotificationWithInmateStatus> list) {
            super("displayNotifications", AddToEndSingleStrategy.class);
            this.notifications = list;
        }

        public void apply(NotificationsView notificationsView) {
            notificationsView.displayNotifications(this.notifications);
        }
    }

    /* compiled from: NotificationsView$$State */
    public class UpdateToolbarCommand extends ViewCommand<NotificationsView> {
        public final int notifications;

        UpdateToolbarCommand(int i) {
            super("updateToolbar", AddToEndSingleStrategy.class);
            this.notifications = i;
        }

        public void apply(NotificationsView notificationsView) {
            notificationsView.updateToolbar(this.notifications);
        }
    }

    /* compiled from: NotificationsView$$State */
    public class ShowMessageCommand extends ViewCommand<NotificationsView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(NotificationsView notificationsView) {
            notificationsView.showMessage(this.message);
        }
    }

    /* compiled from: NotificationsView$$State */
    public class ShowMessage1Command extends ViewCommand<NotificationsView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(NotificationsView notificationsView) {
            notificationsView.showMessage(this.resId);
        }
    }

    /* compiled from: NotificationsView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<NotificationsView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(NotificationsView notificationsView) {
            notificationsView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: NotificationsView$$State */
    public class ShowProgressCommand extends ViewCommand<NotificationsView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(NotificationsView notificationsView) {
            notificationsView.showProgress(this.show);
        }
    }

    /* compiled from: NotificationsView$$State */
    public class ShowServerErrorCommand extends ViewCommand<NotificationsView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(NotificationsView notificationsView) {
            notificationsView.showServerError();
        }
    }
}
