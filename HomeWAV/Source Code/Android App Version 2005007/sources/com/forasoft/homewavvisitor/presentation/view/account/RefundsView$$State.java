package com.forasoft.homewavvisitor.presentation.view.account;

import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;

public class RefundsView$$State extends MvpViewState<RefundsView> implements RefundsView {
    public void showInmates(List<String> list) {
        ShowInmatesCommand showInmatesCommand = new ShowInmatesCommand(list);
        this.viewCommands.beforeApply(showInmatesCommand);
        if (!hasNotView().booleanValue()) {
            for (RefundsView showInmates : this.views) {
                showInmates.showInmates(list);
            }
            this.viewCommands.afterApply(showInmatesCommand);
        }
    }

    public void updateTotalAmount(float f) {
        UpdateTotalAmountCommand updateTotalAmountCommand = new UpdateTotalAmountCommand(f);
        this.viewCommands.beforeApply(updateTotalAmountCommand);
        if (!hasNotView().booleanValue()) {
            for (RefundsView updateTotalAmount : this.views) {
                updateTotalAmount.updateTotalAmount(f);
            }
            this.viewCommands.afterApply(updateTotalAmountCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (RefundsView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    /* compiled from: RefundsView$$State */
    public class ShowInmatesCommand extends ViewCommand<RefundsView> {
        public final List<String> inmates;

        ShowInmatesCommand(List<String> list) {
            super("showInmates", AddToEndSingleStrategy.class);
            this.inmates = list;
        }

        public void apply(RefundsView refundsView) {
            refundsView.showInmates(this.inmates);
        }
    }

    /* compiled from: RefundsView$$State */
    public class UpdateTotalAmountCommand extends ViewCommand<RefundsView> {
        public final float amount;

        UpdateTotalAmountCommand(float f) {
            super("updateTotalAmount", AddToEndSingleStrategy.class);
            this.amount = f;
        }

        public void apply(RefundsView refundsView) {
            refundsView.updateTotalAmount(this.amount);
        }
    }

    /* compiled from: RefundsView$$State */
    public class ShowMessageCommand extends ViewCommand<RefundsView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", AddToEndSingleStrategy.class);
            this.message = str;
        }

        public void apply(RefundsView refundsView) {
            refundsView.showMessage(this.message);
        }
    }
}
