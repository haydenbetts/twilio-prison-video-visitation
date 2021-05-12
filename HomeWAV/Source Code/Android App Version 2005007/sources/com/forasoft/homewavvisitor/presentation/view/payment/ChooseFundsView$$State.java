package com.forasoft.homewavvisitor.presentation.view.payment;

import com.forasoft.homewavvisitor.model.data.Card;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class ChooseFundsView$$State extends MvpViewState<ChooseFundsView> implements ChooseFundsView {
    public void setName(String str) {
        SetNameCommand setNameCommand = new SetNameCommand(str);
        this.viewCommands.beforeApply(setNameCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView name : this.views) {
                name.setName(str);
            }
            this.viewCommands.afterApply(setNameCommand);
        }
    }

    public void setBalance(String str) {
        SetBalanceCommand setBalanceCommand = new SetBalanceCommand(str);
        this.viewCommands.beforeApply(setBalanceCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView balance : this.views) {
                balance.setBalance(str);
            }
            this.viewCommands.afterApply(setBalanceCommand);
        }
    }

    public void initPaymentScope(String str, String[] strArr) {
        InitPaymentScopeCommand initPaymentScopeCommand = new InitPaymentScopeCommand(str, strArr);
        this.viewCommands.beforeApply(initPaymentScopeCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView initPaymentScope : this.views) {
                initPaymentScope.initPaymentScope(str, strArr);
            }
            this.viewCommands.afterApply(initPaymentScopeCommand);
        }
    }

    public void setPaymentScope(String str) {
        SetPaymentScopeCommand setPaymentScopeCommand = new SetPaymentScopeCommand(str);
        this.viewCommands.beforeApply(setPaymentScopeCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView paymentScope : this.views) {
                paymentScope.setPaymentScope(str);
            }
            this.viewCommands.afterApply(setPaymentScopeCommand);
        }
    }

    public void showGeneralFundsConfirmation() {
        ShowGeneralFundsConfirmationCommand showGeneralFundsConfirmationCommand = new ShowGeneralFundsConfirmationCommand();
        this.viewCommands.beforeApply(showGeneralFundsConfirmationCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView showGeneralFundsConfirmation : this.views) {
                showGeneralFundsConfirmation.showGeneralFundsConfirmation();
            }
            this.viewCommands.afterApply(showGeneralFundsConfirmationCommand);
        }
    }

    public void setFraudState() {
        SetFraudStateCommand setFraudStateCommand = new SetFraudStateCommand();
        this.viewCommands.beforeApply(setFraudStateCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView fraudState : this.views) {
                fraudState.setFraudState();
            }
            this.viewCommands.afterApply(setFraudStateCommand);
        }
    }

    public void showHandling(float f) {
        ShowHandlingCommand showHandlingCommand = new ShowHandlingCommand(f);
        this.viewCommands.beforeApply(showHandlingCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView showHandling : this.views) {
                showHandling.showHandling(f);
            }
            this.viewCommands.afterApply(showHandlingCommand);
        }
    }

    public void displayCards(List<Card> list) {
        DisplayCardsCommand displayCardsCommand = new DisplayCardsCommand(list);
        this.viewCommands.beforeApply(displayCardsCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView displayCards : this.views) {
                displayCards.displayCards(list);
            }
            this.viewCommands.afterApply(displayCardsCommand);
        }
    }

    public void initPayment(String str) {
        InitPaymentCommand initPaymentCommand = new InitPaymentCommand(str);
        this.viewCommands.beforeApply(initPaymentCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView initPayment : this.views) {
                initPayment.initPayment(str);
            }
            this.viewCommands.afterApply(initPaymentCommand);
        }
    }

    public void executePayment() {
        ExecutePaymentCommand executePaymentCommand = new ExecutePaymentCommand();
        this.viewCommands.beforeApply(executePaymentCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView executePayment : this.views) {
                executePayment.executePayment();
            }
            this.viewCommands.afterApply(executePaymentCommand);
        }
    }

    public void showSuccessMessage() {
        ShowSuccessMessageCommand showSuccessMessageCommand = new ShowSuccessMessageCommand();
        this.viewCommands.beforeApply(showSuccessMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView showSuccessMessage : this.views) {
                showSuccessMessage.showSuccessMessage();
            }
            this.viewCommands.afterApply(showSuccessMessageCommand);
        }
    }

    public void showErrorMessage() {
        ShowErrorMessageCommand showErrorMessageCommand = new ShowErrorMessageCommand();
        this.viewCommands.beforeApply(showErrorMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView showErrorMessage : this.views) {
                showErrorMessage.showErrorMessage();
            }
            this.viewCommands.afterApply(showErrorMessageCommand);
        }
    }

    public void showBraintreeError() {
        ShowBraintreeErrorCommand showBraintreeErrorCommand = new ShowBraintreeErrorCommand();
        this.viewCommands.beforeApply(showBraintreeErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView showBraintreeError : this.views) {
                showBraintreeError.showBraintreeError();
            }
            this.viewCommands.afterApply(showBraintreeErrorCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (ChooseFundsView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class SetNameCommand extends ViewCommand<ChooseFundsView> {
        public final String name;

        SetNameCommand(String str) {
            super("setName", AddToEndSingleStrategy.class);
            this.name = str;
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.setName(this.name);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class SetBalanceCommand extends ViewCommand<ChooseFundsView> {
        public final String balance;

        SetBalanceCommand(String str) {
            super("setBalance", AddToEndSingleStrategy.class);
            this.balance = str;
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.setBalance(this.balance);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class InitPaymentScopeCommand extends ViewCommand<ChooseFundsView> {
        public final String[] allowedScopes;
        public final String scope;

        InitPaymentScopeCommand(String str, String[] strArr) {
            super("initPaymentScope", AddToEndSingleStrategy.class);
            this.scope = str;
            this.allowedScopes = strArr;
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.initPaymentScope(this.scope, this.allowedScopes);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class SetPaymentScopeCommand extends ViewCommand<ChooseFundsView> {
        public final String scope;

        SetPaymentScopeCommand(String str) {
            super("setPaymentScope", OneExecutionStateStrategy.class);
            this.scope = str;
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.setPaymentScope(this.scope);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class ShowGeneralFundsConfirmationCommand extends ViewCommand<ChooseFundsView> {
        ShowGeneralFundsConfirmationCommand() {
            super("showGeneralFundsConfirmation", SkipStrategy.class);
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.showGeneralFundsConfirmation();
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class SetFraudStateCommand extends ViewCommand<ChooseFundsView> {
        SetFraudStateCommand() {
            super("setFraudState", AddToEndSingleStrategy.class);
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.setFraudState();
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class ShowHandlingCommand extends ViewCommand<ChooseFundsView> {
        public final float handling;

        ShowHandlingCommand(float f) {
            super("showHandling", AddToEndSingleStrategy.class);
            this.handling = f;
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.showHandling(this.handling);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class DisplayCardsCommand extends ViewCommand<ChooseFundsView> {
        public final List<Card> cards;

        DisplayCardsCommand(List<Card> list) {
            super("displayCards", AddToEndSingleStrategy.class);
            this.cards = list;
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.displayCards(this.cards);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class InitPaymentCommand extends ViewCommand<ChooseFundsView> {
        public final String token;

        InitPaymentCommand(String str) {
            super("initPayment", OneExecutionStateStrategy.class);
            this.token = str;
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.initPayment(this.token);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class ExecutePaymentCommand extends ViewCommand<ChooseFundsView> {
        ExecutePaymentCommand() {
            super("executePayment", SkipStrategy.class);
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.executePayment();
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class ShowSuccessMessageCommand extends ViewCommand<ChooseFundsView> {
        ShowSuccessMessageCommand() {
            super("showSuccessMessage", OneExecutionStateStrategy.class);
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.showSuccessMessage();
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class ShowErrorMessageCommand extends ViewCommand<ChooseFundsView> {
        ShowErrorMessageCommand() {
            super("showErrorMessage", OneExecutionStateStrategy.class);
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.showErrorMessage();
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class ShowBraintreeErrorCommand extends ViewCommand<ChooseFundsView> {
        ShowBraintreeErrorCommand() {
            super("showBraintreeError", OneExecutionStateStrategy.class);
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.showBraintreeError();
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class ShowMessageCommand extends ViewCommand<ChooseFundsView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.showMessage(this.message);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class ShowMessage1Command extends ViewCommand<ChooseFundsView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.showMessage(this.resId);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<ChooseFundsView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class ShowProgressCommand extends ViewCommand<ChooseFundsView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.showProgress(this.show);
        }
    }

    /* compiled from: ChooseFundsView$$State */
    public class ShowServerErrorCommand extends ViewCommand<ChooseFundsView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(ChooseFundsView chooseFundsView) {
            chooseFundsView.showServerError();
        }
    }
}
