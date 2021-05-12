package com.forasoft.homewavvisitor.presentation.view.home;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class InmateDetailView$$State extends MvpViewState<InmateDetailView> implements InmateDetailView {
    public void setName(String str) {
        SetNameCommand setNameCommand = new SetNameCommand(str);
        this.viewCommands.beforeApply(setNameCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView name : this.views) {
                name.setName(str);
            }
            this.viewCommands.afterApply(setNameCommand);
        }
    }

    public void setCredits(String str) {
        SetCreditsCommand setCreditsCommand = new SetCreditsCommand(str);
        this.viewCommands.beforeApply(setCreditsCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView credits : this.views) {
                credits.setCredits(str);
            }
            this.viewCommands.afterApply(setCreditsCommand);
        }
    }

    public void setId(String str) {
        SetIdCommand setIdCommand = new SetIdCommand(str);
        this.viewCommands.beforeApply(setIdCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView id : this.views) {
                id.setId(str);
            }
            this.viewCommands.afterApply(setIdCommand);
        }
    }

    public void setInvisible(boolean z) {
        SetInvisibleCommand setInvisibleCommand = new SetInvisibleCommand(z);
        this.viewCommands.beforeApply(setInvisibleCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView invisible : this.views) {
                invisible.setInvisible(z);
            }
            this.viewCommands.afterApply(setInvisibleCommand);
        }
    }

    public void setMoney(String str) {
        SetMoneyCommand setMoneyCommand = new SetMoneyCommand(str);
        this.viewCommands.beforeApply(setMoneyCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView money : this.views) {
                money.setMoney(str);
            }
            this.viewCommands.afterApply(setMoneyCommand);
        }
    }

    public void setStatus(String str) {
        SetStatusCommand setStatusCommand = new SetStatusCommand(str);
        this.viewCommands.beforeApply(setStatusCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView status : this.views) {
                status.setStatus(str);
            }
            this.viewCommands.afterApply(setStatusCommand);
        }
    }

    public void showVisitsNumber(String str) {
        ShowVisitsNumberCommand showVisitsNumberCommand = new ShowVisitsNumberCommand(str);
        this.viewCommands.beforeApply(showVisitsNumberCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView showVisitsNumber : this.views) {
                showVisitsNumber.showVisitsNumber(str);
            }
            this.viewCommands.afterApply(showVisitsNumberCommand);
        }
    }

    public void showVisitsText(String str) {
        ShowVisitsTextCommand showVisitsTextCommand = new ShowVisitsTextCommand(str);
        this.viewCommands.beforeApply(showVisitsTextCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView showVisitsText : this.views) {
                showVisitsText.showVisitsText(str);
            }
            this.viewCommands.afterApply(showVisitsTextCommand);
        }
    }

    public void showDeleteDialog(String str) {
        ShowDeleteDialogCommand showDeleteDialogCommand = new ShowDeleteDialogCommand(str);
        this.viewCommands.beforeApply(showDeleteDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView showDeleteDialog : this.views) {
                showDeleteDialog.showDeleteDialog(str);
            }
            this.viewCommands.afterApply(showDeleteDialogCommand);
        }
    }

    public void setCheckActive() {
        SetCheckActiveCommand setCheckActiveCommand = new SetCheckActiveCommand();
        this.viewCommands.beforeApply(setCheckActiveCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView checkActive : this.views) {
                checkActive.setCheckActive();
            }
            this.viewCommands.afterApply(setCheckActiveCommand);
        }
    }

    public void setCheckInactive() {
        SetCheckInactiveCommand setCheckInactiveCommand = new SetCheckInactiveCommand();
        this.viewCommands.beforeApply(setCheckInactiveCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView checkInactive : this.views) {
                checkInactive.setCheckInactive();
            }
            this.viewCommands.afterApply(setCheckInactiveCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateDetailView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class SetNameCommand extends ViewCommand<InmateDetailView> {
        public final String name;

        SetNameCommand(String str) {
            super("setName", AddToEndSingleStrategy.class);
            this.name = str;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.setName(this.name);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class SetCreditsCommand extends ViewCommand<InmateDetailView> {
        public final String balance;

        SetCreditsCommand(String str) {
            super("setCredits", AddToEndSingleStrategy.class);
            this.balance = str;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.setCredits(this.balance);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class SetIdCommand extends ViewCommand<InmateDetailView> {
        public final String identifier;

        SetIdCommand(String str) {
            super("setId", AddToEndSingleStrategy.class);
            this.identifier = str;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.setId(this.identifier);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class SetInvisibleCommand extends ViewCommand<InmateDetailView> {
        public final boolean allowCalls;

        SetInvisibleCommand(boolean z) {
            super("setInvisible", AddToEndSingleStrategy.class);
            this.allowCalls = z;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.setInvisible(this.allowCalls);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class SetMoneyCommand extends ViewCommand<InmateDetailView> {
        public final String credit_balance;

        SetMoneyCommand(String str) {
            super("setMoney", AddToEndSingleStrategy.class);
            this.credit_balance = str;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.setMoney(this.credit_balance);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class SetStatusCommand extends ViewCommand<InmateDetailView> {
        public final String status;

        SetStatusCommand(String str) {
            super("setStatus", AddToEndSingleStrategy.class);
            this.status = str;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.setStatus(this.status);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class ShowVisitsNumberCommand extends ViewCommand<InmateDetailView> {
        public final String visits;

        ShowVisitsNumberCommand(String str) {
            super("showVisitsNumber", AddToEndSingleStrategy.class);
            this.visits = str;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.showVisitsNumber(this.visits);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class ShowVisitsTextCommand extends ViewCommand<InmateDetailView> {
        public final String text;

        ShowVisitsTextCommand(String str) {
            super("showVisitsText", AddToEndSingleStrategy.class);
            this.text = str;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.showVisitsText(this.text);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class ShowDeleteDialogCommand extends ViewCommand<InmateDetailView> {
        public final String name;

        ShowDeleteDialogCommand(String str) {
            super("showDeleteDialog", AddToEndSingleStrategy.class);
            this.name = str;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.showDeleteDialog(this.name);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class SetCheckActiveCommand extends ViewCommand<InmateDetailView> {
        SetCheckActiveCommand() {
            super("setCheckActive", AddToEndSingleStrategy.class);
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.setCheckActive();
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class SetCheckInactiveCommand extends ViewCommand<InmateDetailView> {
        SetCheckInactiveCommand() {
            super("setCheckInactive", AddToEndSingleStrategy.class);
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.setCheckInactive();
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class ShowMessageCommand extends ViewCommand<InmateDetailView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.showMessage(this.message);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class ShowMessage1Command extends ViewCommand<InmateDetailView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.showMessage(this.resId);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<InmateDetailView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class ShowProgressCommand extends ViewCommand<InmateDetailView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.showProgress(this.show);
        }
    }

    /* compiled from: InmateDetailView$$State */
    public class ShowServerErrorCommand extends ViewCommand<InmateDetailView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(InmateDetailView inmateDetailView) {
            inmateDetailView.showServerError();
        }
    }
}
