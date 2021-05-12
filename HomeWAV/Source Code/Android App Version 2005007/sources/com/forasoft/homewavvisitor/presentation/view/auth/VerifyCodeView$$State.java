package com.forasoft.homewavvisitor.presentation.view.auth;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;

public class VerifyCodeView$$State extends MvpViewState<VerifyCodeView> implements VerifyCodeView {
    public void showSuccessMessage(int i) {
        ShowSuccessMessageCommand showSuccessMessageCommand = new ShowSuccessMessageCommand(i);
        this.viewCommands.beforeApply(showSuccessMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (VerifyCodeView showSuccessMessage : this.views) {
                showSuccessMessage.showSuccessMessage(i);
            }
            this.viewCommands.afterApply(showSuccessMessageCommand);
        }
    }

    public void showErrorMessage(int i) {
        ShowErrorMessageCommand showErrorMessageCommand = new ShowErrorMessageCommand(i);
        this.viewCommands.beforeApply(showErrorMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (VerifyCodeView showErrorMessage : this.views) {
                showErrorMessage.showErrorMessage(i);
            }
            this.viewCommands.afterApply(showErrorMessageCommand);
        }
    }

    public void updateHint(String str) {
        UpdateHintCommand updateHintCommand = new UpdateHintCommand(str);
        this.viewCommands.beforeApply(updateHintCommand);
        if (!hasNotView().booleanValue()) {
            for (VerifyCodeView updateHint : this.views) {
                updateHint.updateHint(str);
            }
            this.viewCommands.afterApply(updateHintCommand);
        }
    }

    /* compiled from: VerifyCodeView$$State */
    public class ShowSuccessMessageCommand extends ViewCommand<VerifyCodeView> {
        public final int resId;

        ShowSuccessMessageCommand(int i) {
            super("showSuccessMessage", AddToEndSingleStrategy.class);
            this.resId = i;
        }

        public void apply(VerifyCodeView verifyCodeView) {
            verifyCodeView.showSuccessMessage(this.resId);
        }
    }

    /* compiled from: VerifyCodeView$$State */
    public class ShowErrorMessageCommand extends ViewCommand<VerifyCodeView> {
        public final int resId;

        ShowErrorMessageCommand(int i) {
            super("showErrorMessage", AddToEndSingleStrategy.class);
            this.resId = i;
        }

        public void apply(VerifyCodeView verifyCodeView) {
            verifyCodeView.showErrorMessage(this.resId);
        }
    }

    /* compiled from: VerifyCodeView$$State */
    public class UpdateHintCommand extends ViewCommand<VerifyCodeView> {
        public final String message;

        UpdateHintCommand(String str) {
            super("updateHint", AddToEndSingleStrategy.class);
            this.message = str;
        }

        public void apply(VerifyCodeView verifyCodeView) {
            verifyCodeView.updateHint(this.message);
        }
    }
}
