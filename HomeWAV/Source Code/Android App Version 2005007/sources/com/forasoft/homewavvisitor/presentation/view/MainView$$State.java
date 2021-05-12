package com.forasoft.homewavvisitor.presentation.view;

import com.forasoft.homewavvisitor.model.data.Call;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class MainView$$State extends MvpViewState<MainView> implements MainView {
    public void updateMessagesBadge(int i) {
        UpdateMessagesBadgeCommand updateMessagesBadgeCommand = new UpdateMessagesBadgeCommand(i);
        this.viewCommands.beforeApply(updateMessagesBadgeCommand);
        if (!hasNotView().booleanValue()) {
            for (MainView updateMessagesBadge : this.views) {
                updateMessagesBadge.updateMessagesBadge(i);
            }
            this.viewCommands.afterApply(updateMessagesBadgeCommand);
        }
    }

    public void showTermsConditionsDialog() {
        ShowTermsConditionsDialogCommand showTermsConditionsDialogCommand = new ShowTermsConditionsDialogCommand();
        this.viewCommands.beforeApply(showTermsConditionsDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (MainView showTermsConditionsDialog : this.views) {
                showTermsConditionsDialog.showTermsConditionsDialog();
            }
            this.viewCommands.afterApply(showTermsConditionsDialogCommand);
        }
    }

    public void showDisapprovedDialog() {
        ShowDisapprovedDialogCommand showDisapprovedDialogCommand = new ShowDisapprovedDialogCommand();
        this.viewCommands.beforeApply(showDisapprovedDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (MainView showDisapprovedDialog : this.views) {
                showDisapprovedDialog.showDisapprovedDialog();
            }
            this.viewCommands.afterApply(showDisapprovedDialogCommand);
        }
    }

    public void showCallDialog(Call call) {
        ShowCallDialogCommand showCallDialogCommand = new ShowCallDialogCommand(call);
        this.viewCommands.beforeApply(showCallDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (MainView showCallDialog : this.views) {
                showCallDialog.showCallDialog(call);
            }
            this.viewCommands.afterApply(showCallDialogCommand);
        }
    }

    /* compiled from: MainView$$State */
    public class UpdateMessagesBadgeCommand extends ViewCommand<MainView> {
        public final int count;

        UpdateMessagesBadgeCommand(int i) {
            super("updateMessagesBadge", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(MainView mainView) {
            mainView.updateMessagesBadge(this.count);
        }
    }

    /* compiled from: MainView$$State */
    public class ShowTermsConditionsDialogCommand extends ViewCommand<MainView> {
        ShowTermsConditionsDialogCommand() {
            super("showTermsConditionsDialog", AddToEndSingleStrategy.class);
        }

        public void apply(MainView mainView) {
            mainView.showTermsConditionsDialog();
        }
    }

    /* compiled from: MainView$$State */
    public class ShowDisapprovedDialogCommand extends ViewCommand<MainView> {
        ShowDisapprovedDialogCommand() {
            super("showDisapprovedDialog", AddToEndSingleStrategy.class);
        }

        public void apply(MainView mainView) {
            mainView.showDisapprovedDialog();
        }
    }

    /* compiled from: MainView$$State */
    public class ShowCallDialogCommand extends ViewCommand<MainView> {
        public final Call call;

        ShowCallDialogCommand(Call call2) {
            super("showCallDialog", SkipStrategy.class);
            this.call = call2;
        }

        public void apply(MainView mainView) {
            mainView.showCallDialog(this.call);
        }
    }
}
