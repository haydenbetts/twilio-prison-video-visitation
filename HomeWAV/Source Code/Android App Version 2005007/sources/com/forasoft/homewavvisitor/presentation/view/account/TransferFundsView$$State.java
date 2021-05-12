package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.model.data.Inmate;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;

public class TransferFundsView$$State extends MvpViewState<TransferFundsView> implements TransferFundsView {
    public void showFromInmate(Inmate inmate) {
        ShowFromInmateCommand showFromInmateCommand = new ShowFromInmateCommand(inmate);
        this.viewCommands.beforeApply(showFromInmateCommand);
        if (!hasNotView().booleanValue()) {
            for (TransferFundsView showFromInmate : this.views) {
                showFromInmate.showFromInmate(inmate);
            }
            this.viewCommands.afterApply(showFromInmateCommand);
        }
    }

    public void showToInmate(Inmate inmate) {
        ShowToInmateCommand showToInmateCommand = new ShowToInmateCommand(inmate);
        this.viewCommands.beforeApply(showToInmateCommand);
        if (!hasNotView().booleanValue()) {
            for (TransferFundsView showToInmate : this.views) {
                showToInmate.showToInmate(inmate);
            }
            this.viewCommands.afterApply(showToInmateCommand);
        }
    }

    public void showProgressDialog() {
        ShowProgressDialogCommand showProgressDialogCommand = new ShowProgressDialogCommand();
        this.viewCommands.beforeApply(showProgressDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (TransferFundsView showProgressDialog : this.views) {
                showProgressDialog.showProgressDialog();
            }
            this.viewCommands.afterApply(showProgressDialogCommand);
        }
    }

    public void hideProgressDialog() {
        HideProgressDialogCommand hideProgressDialogCommand = new HideProgressDialogCommand();
        this.viewCommands.beforeApply(hideProgressDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (TransferFundsView hideProgressDialog : this.views) {
                hideProgressDialog.hideProgressDialog();
            }
            this.viewCommands.afterApply(hideProgressDialogCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(i);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (TransferFundsView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showTransferFee(String str) {
        ShowTransferFeeCommand showTransferFeeCommand = new ShowTransferFeeCommand(str);
        this.viewCommands.beforeApply(showTransferFeeCommand);
        if (!hasNotView().booleanValue()) {
            for (TransferFundsView showTransferFee : this.views) {
                showTransferFee.showTransferFee(str);
            }
            this.viewCommands.afterApply(showTransferFeeCommand);
        }
    }

    /* compiled from: TransferFundsView$$State */
    public class ShowFromInmateCommand extends ViewCommand<TransferFundsView> {
        public final Inmate inmate;

        ShowFromInmateCommand(Inmate inmate2) {
            super("showFromInmate", AddToEndSingleStrategy.class);
            this.inmate = inmate2;
        }

        public void apply(TransferFundsView transferFundsView) {
            transferFundsView.showFromInmate(this.inmate);
        }
    }

    /* compiled from: TransferFundsView$$State */
    public class ShowToInmateCommand extends ViewCommand<TransferFundsView> {
        public final Inmate inmate;

        ShowToInmateCommand(Inmate inmate2) {
            super("showToInmate", AddToEndSingleStrategy.class);
            this.inmate = inmate2;
        }

        public void apply(TransferFundsView transferFundsView) {
            transferFundsView.showToInmate(this.inmate);
        }
    }

    /* compiled from: TransferFundsView$$State */
    public class ShowProgressDialogCommand extends ViewCommand<TransferFundsView> {
        ShowProgressDialogCommand() {
            super("showProgressDialog", OneExecutionStateStrategy.class);
        }

        public void apply(TransferFundsView transferFundsView) {
            transferFundsView.showProgressDialog();
        }
    }

    /* compiled from: TransferFundsView$$State */
    public class HideProgressDialogCommand extends ViewCommand<TransferFundsView> {
        HideProgressDialogCommand() {
            super("hideProgressDialog", OneExecutionStateStrategy.class);
        }

        public void apply(TransferFundsView transferFundsView) {
            transferFundsView.hideProgressDialog();
        }
    }

    /* compiled from: TransferFundsView$$State */
    public class ShowMessageCommand extends ViewCommand<TransferFundsView> {
        public final int resId;

        ShowMessageCommand(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(TransferFundsView transferFundsView) {
            transferFundsView.showMessage(this.resId);
        }
    }

    /* compiled from: TransferFundsView$$State */
    public class ShowTransferFeeCommand extends ViewCommand<TransferFundsView> {
        public final String transferFee;

        ShowTransferFeeCommand(String str) {
            super("showTransferFee", AddToEndSingleStrategy.class);
            this.transferFee = str;
        }

        public void apply(TransferFundsView transferFundsView) {
            transferFundsView.showTransferFee(this.transferFee);
        }
    }
}
