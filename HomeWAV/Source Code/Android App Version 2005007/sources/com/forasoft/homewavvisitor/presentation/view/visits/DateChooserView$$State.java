package com.forasoft.homewavvisitor.presentation.view.visits;

import com.forasoft.homewavvisitor.model.data.DaySlot;
import java.util.List;
import java.util.Map;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import org.threeten.bp.LocalDate;

public class DateChooserView$$State extends MvpViewState<DateChooserView> implements DateChooserView {
    public void updateToolbar(String str) {
        UpdateToolbarCommand updateToolbarCommand = new UpdateToolbarCommand(str);
        this.viewCommands.beforeApply(updateToolbarCommand);
        if (!hasNotView().booleanValue()) {
            for (DateChooserView updateToolbar : this.views) {
                updateToolbar.updateToolbar(str);
            }
            this.viewCommands.afterApply(updateToolbarCommand);
        }
    }

    public void initCalendar(Map<LocalDate, DaySlot> map) {
        InitCalendarCommand initCalendarCommand = new InitCalendarCommand(map);
        this.viewCommands.beforeApply(initCalendarCommand);
        if (!hasNotView().booleanValue()) {
            for (DateChooserView initCalendar : this.views) {
                initCalendar.initCalendar(map);
            }
            this.viewCommands.afterApply(initCalendarCommand);
        }
    }

    public void updateCalendar(List<DaySlot> list) {
        UpdateCalendarCommand updateCalendarCommand = new UpdateCalendarCommand(list);
        this.viewCommands.beforeApply(updateCalendarCommand);
        if (!hasNotView().booleanValue()) {
            for (DateChooserView updateCalendar : this.views) {
                updateCalendar.updateCalendar(list);
            }
            this.viewCommands.afterApply(updateCalendarCommand);
        }
    }

    public void showProgress() {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand();
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (DateChooserView showProgress : this.views) {
                showProgress.showProgress();
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void hideProgress() {
        HideProgressCommand hideProgressCommand = new HideProgressCommand();
        this.viewCommands.beforeApply(hideProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (DateChooserView hideProgress : this.views) {
                hideProgress.hideProgress();
            }
            this.viewCommands.afterApply(hideProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (DateChooserView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (DateChooserView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (DateChooserView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (DateChooserView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgress1Command showProgress1Command = new ShowProgress1Command(z);
        this.viewCommands.beforeApply(showProgress1Command);
        if (!hasNotView().booleanValue()) {
            for (DateChooserView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgress1Command);
        }
    }

    /* compiled from: DateChooserView$$State */
    public class UpdateToolbarCommand extends ViewCommand<DateChooserView> {
        public final String title;

        UpdateToolbarCommand(String str) {
            super("updateToolbar", AddToEndSingleStrategy.class);
            this.title = str;
        }

        public void apply(DateChooserView dateChooserView) {
            dateChooserView.updateToolbar(this.title);
        }
    }

    /* compiled from: DateChooserView$$State */
    public class InitCalendarCommand extends ViewCommand<DateChooserView> {
        public final Map<LocalDate, DaySlot> schedule;

        InitCalendarCommand(Map<LocalDate, DaySlot> map) {
            super("initCalendar", AddToEndSingleStrategy.class);
            this.schedule = map;
        }

        public void apply(DateChooserView dateChooserView) {
            dateChooserView.initCalendar(this.schedule);
        }
    }

    /* compiled from: DateChooserView$$State */
    public class UpdateCalendarCommand extends ViewCommand<DateChooserView> {
        public final List<DaySlot> slots;

        UpdateCalendarCommand(List<DaySlot> list) {
            super("updateCalendar", OneExecutionStateStrategy.class);
            this.slots = list;
        }

        public void apply(DateChooserView dateChooserView) {
            dateChooserView.updateCalendar(this.slots);
        }
    }

    /* compiled from: DateChooserView$$State */
    public class ShowProgressCommand extends ViewCommand<DateChooserView> {
        ShowProgressCommand() {
            super("showProgress", AddToEndSingleStrategy.class);
        }

        public void apply(DateChooserView dateChooserView) {
            dateChooserView.showProgress();
        }
    }

    /* compiled from: DateChooserView$$State */
    public class HideProgressCommand extends ViewCommand<DateChooserView> {
        HideProgressCommand() {
            super("hideProgress", AddToEndSingleStrategy.class);
        }

        public void apply(DateChooserView dateChooserView) {
            dateChooserView.hideProgress();
        }
    }

    /* compiled from: DateChooserView$$State */
    public class ShowServerErrorCommand extends ViewCommand<DateChooserView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(DateChooserView dateChooserView) {
            dateChooserView.showServerError();
        }
    }

    /* compiled from: DateChooserView$$State */
    public class ShowMessageCommand extends ViewCommand<DateChooserView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(DateChooserView dateChooserView) {
            dateChooserView.showMessage(this.message);
        }
    }

    /* compiled from: DateChooserView$$State */
    public class ShowMessage1Command extends ViewCommand<DateChooserView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(DateChooserView dateChooserView) {
            dateChooserView.showMessage(this.resId);
        }
    }

    /* compiled from: DateChooserView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<DateChooserView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(DateChooserView dateChooserView) {
            dateChooserView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: DateChooserView$$State */
    public class ShowProgress1Command extends ViewCommand<DateChooserView> {
        public final boolean show;

        ShowProgress1Command(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(DateChooserView dateChooserView) {
            dateChooserView.showProgress(this.show);
        }
    }
}
