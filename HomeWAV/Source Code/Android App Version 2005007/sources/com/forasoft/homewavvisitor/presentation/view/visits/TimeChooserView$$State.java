package com.forasoft.homewavvisitor.presentation.view.visits;

import com.forasoft.homewavvisitor.model.data.TimeSlot;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class TimeChooserView$$State extends MvpViewState<TimeChooserView> implements TimeChooserView {
    public void updateToolbar(String str) {
        UpdateToolbarCommand updateToolbarCommand = new UpdateToolbarCommand(str);
        this.viewCommands.beforeApply(updateToolbarCommand);
        if (!hasNotView().booleanValue()) {
            for (TimeChooserView updateToolbar : this.views) {
                updateToolbar.updateToolbar(str);
            }
            this.viewCommands.afterApply(updateToolbarCommand);
        }
    }

    public void displaySelectedDay(String str) {
        DisplaySelectedDayCommand displaySelectedDayCommand = new DisplaySelectedDayCommand(str);
        this.viewCommands.beforeApply(displaySelectedDayCommand);
        if (!hasNotView().booleanValue()) {
            for (TimeChooserView displaySelectedDay : this.views) {
                displaySelectedDay.displaySelectedDay(str);
            }
            this.viewCommands.afterApply(displaySelectedDayCommand);
        }
    }

    public void displaySlots(List<TimeSlot> list) {
        DisplaySlotsCommand displaySlotsCommand = new DisplaySlotsCommand(list);
        this.viewCommands.beforeApply(displaySlotsCommand);
        if (!hasNotView().booleanValue()) {
            for (TimeChooserView displaySlots : this.views) {
                displaySlots.displaySlots(list);
            }
            this.viewCommands.afterApply(displaySlotsCommand);
        }
    }

    public void showConfirmDialog(String str, String str2) {
        ShowConfirmDialogCommand showConfirmDialogCommand = new ShowConfirmDialogCommand(str, str2);
        this.viewCommands.beforeApply(showConfirmDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (TimeChooserView showConfirmDialog : this.views) {
                showConfirmDialog.showConfirmDialog(str, str2);
            }
            this.viewCommands.afterApply(showConfirmDialogCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (TimeChooserView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (TimeChooserView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (TimeChooserView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (TimeChooserView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (TimeChooserView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: TimeChooserView$$State */
    public class UpdateToolbarCommand extends ViewCommand<TimeChooserView> {
        public final String title;

        UpdateToolbarCommand(String str) {
            super("updateToolbar", AddToEndSingleStrategy.class);
            this.title = str;
        }

        public void apply(TimeChooserView timeChooserView) {
            timeChooserView.updateToolbar(this.title);
        }
    }

    /* compiled from: TimeChooserView$$State */
    public class DisplaySelectedDayCommand extends ViewCommand<TimeChooserView> {
        public final String date;

        DisplaySelectedDayCommand(String str) {
            super("displaySelectedDay", AddToEndSingleStrategy.class);
            this.date = str;
        }

        public void apply(TimeChooserView timeChooserView) {
            timeChooserView.displaySelectedDay(this.date);
        }
    }

    /* compiled from: TimeChooserView$$State */
    public class DisplaySlotsCommand extends ViewCommand<TimeChooserView> {
        public final List<TimeSlot> slots;

        DisplaySlotsCommand(List<TimeSlot> list) {
            super("displaySlots", AddToEndSingleStrategy.class);
            this.slots = list;
        }

        public void apply(TimeChooserView timeChooserView) {
            timeChooserView.displaySlots(this.slots);
        }
    }

    /* compiled from: TimeChooserView$$State */
    public class ShowConfirmDialogCommand extends ViewCommand<TimeChooserView> {
        public final String date;
        public final String inmate;

        ShowConfirmDialogCommand(String str, String str2) {
            super("showConfirmDialog", AddToEndSingleStrategy.class);
            this.inmate = str;
            this.date = str2;
        }

        public void apply(TimeChooserView timeChooserView) {
            timeChooserView.showConfirmDialog(this.inmate, this.date);
        }
    }

    /* compiled from: TimeChooserView$$State */
    public class ShowMessageCommand extends ViewCommand<TimeChooserView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(TimeChooserView timeChooserView) {
            timeChooserView.showMessage(this.message);
        }
    }

    /* compiled from: TimeChooserView$$State */
    public class ShowMessage1Command extends ViewCommand<TimeChooserView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(TimeChooserView timeChooserView) {
            timeChooserView.showMessage(this.resId);
        }
    }

    /* compiled from: TimeChooserView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<TimeChooserView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(TimeChooserView timeChooserView) {
            timeChooserView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: TimeChooserView$$State */
    public class ShowProgressCommand extends ViewCommand<TimeChooserView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(TimeChooserView timeChooserView) {
            timeChooserView.showProgress(this.show);
        }
    }

    /* compiled from: TimeChooserView$$State */
    public class ShowServerErrorCommand extends ViewCommand<TimeChooserView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(TimeChooserView timeChooserView) {
            timeChooserView.showServerError();
        }
    }
}
