package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.model.data.Card;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class PaymentMethodsView$$State extends MvpViewState<PaymentMethodsView> implements PaymentMethodsView {
    public void displayCards(List<Card> list) {
        DisplayCardsCommand displayCardsCommand = new DisplayCardsCommand(list);
        this.viewCommands.beforeApply(displayCardsCommand);
        if (!hasNotView().booleanValue()) {
            for (PaymentMethodsView displayCards : this.views) {
                displayCards.displayCards(list);
            }
            this.viewCommands.afterApply(displayCardsCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (PaymentMethodsView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (PaymentMethodsView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (PaymentMethodsView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (PaymentMethodsView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (PaymentMethodsView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: PaymentMethodsView$$State */
    public class DisplayCardsCommand extends ViewCommand<PaymentMethodsView> {
        public final List<Card> cards;

        DisplayCardsCommand(List<Card> list) {
            super("displayCards", AddToEndSingleStrategy.class);
            this.cards = list;
        }

        public void apply(PaymentMethodsView paymentMethodsView) {
            paymentMethodsView.displayCards(this.cards);
        }
    }

    /* compiled from: PaymentMethodsView$$State */
    public class ShowMessageCommand extends ViewCommand<PaymentMethodsView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(PaymentMethodsView paymentMethodsView) {
            paymentMethodsView.showMessage(this.message);
        }
    }

    /* compiled from: PaymentMethodsView$$State */
    public class ShowMessage1Command extends ViewCommand<PaymentMethodsView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(PaymentMethodsView paymentMethodsView) {
            paymentMethodsView.showMessage(this.resId);
        }
    }

    /* compiled from: PaymentMethodsView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<PaymentMethodsView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(PaymentMethodsView paymentMethodsView) {
            paymentMethodsView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: PaymentMethodsView$$State */
    public class ShowProgressCommand extends ViewCommand<PaymentMethodsView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(PaymentMethodsView paymentMethodsView) {
            paymentMethodsView.showProgress(this.show);
        }
    }

    /* compiled from: PaymentMethodsView$$State */
    public class ShowServerErrorCommand extends ViewCommand<PaymentMethodsView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(PaymentMethodsView paymentMethodsView) {
            paymentMethodsView.showServerError();
        }
    }
}
