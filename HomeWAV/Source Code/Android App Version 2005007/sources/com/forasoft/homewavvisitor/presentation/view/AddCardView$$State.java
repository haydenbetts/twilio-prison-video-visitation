package com.forasoft.homewavvisitor.presentation.view;

import com.braintreepayments.api.models.CardBuilder;
import com.stripe.android.model.Card;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class AddCardView$$State extends MvpViewState<AddCardView> implements AddCardView {
    public void addCardSystemWithIndex(int i) {
        AddCardSystemWithIndexCommand addCardSystemWithIndexCommand = new AddCardSystemWithIndexCommand(i);
        this.viewCommands.beforeApply(addCardSystemWithIndexCommand);
        if (!hasNotView().booleanValue()) {
            for (AddCardView addCardSystemWithIndex : this.views) {
                addCardSystemWithIndex.addCardSystemWithIndex(i);
            }
            this.viewCommands.afterApply(addCardSystemWithIndexCommand);
        }
    }

    public void clearCardSystems() {
        ClearCardSystemsCommand clearCardSystemsCommand = new ClearCardSystemsCommand();
        this.viewCommands.beforeApply(clearCardSystemsCommand);
        if (!hasNotView().booleanValue()) {
            for (AddCardView clearCardSystems : this.views) {
                clearCardSystems.clearCardSystems();
            }
            this.viewCommands.afterApply(clearCardSystemsCommand);
        }
    }

    public void tokenize(CardBuilder cardBuilder) {
        TokenizeCommand tokenizeCommand = new TokenizeCommand(cardBuilder);
        this.viewCommands.beforeApply(tokenizeCommand);
        if (!hasNotView().booleanValue()) {
            for (AddCardView addCardView : this.views) {
                addCardView.tokenize(cardBuilder);
            }
            this.viewCommands.afterApply(tokenizeCommand);
        }
    }

    public void tokenizeStripe(Card card) {
        TokenizeStripeCommand tokenizeStripeCommand = new TokenizeStripeCommand(card);
        this.viewCommands.beforeApply(tokenizeStripeCommand);
        if (!hasNotView().booleanValue()) {
            for (AddCardView addCardView : this.views) {
                addCardView.tokenizeStripe(card);
            }
            this.viewCommands.afterApply(tokenizeStripeCommand);
        }
    }

    public void initPayment(String str) {
        InitPaymentCommand initPaymentCommand = new InitPaymentCommand(str);
        this.viewCommands.beforeApply(initPaymentCommand);
        if (!hasNotView().booleanValue()) {
            for (AddCardView initPayment : this.views) {
                initPayment.initPayment(str);
            }
            this.viewCommands.afterApply(initPaymentCommand);
        }
    }

    public void showCardTypes() {
        ShowCardTypesCommand showCardTypesCommand = new ShowCardTypesCommand();
        this.viewCommands.beforeApply(showCardTypesCommand);
        if (!hasNotView().booleanValue()) {
            for (AddCardView showCardTypes : this.views) {
                showCardTypes.showCardTypes();
            }
            this.viewCommands.afterApply(showCardTypesCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (AddCardView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (AddCardView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (AddCardView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (AddCardView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (AddCardView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: AddCardView$$State */
    public class AddCardSystemWithIndexCommand extends ViewCommand<AddCardView> {
        public final int index;

        AddCardSystemWithIndexCommand(int i) {
            super("addCardSystemWithIndex", AddToEndSingleStrategy.class);
            this.index = i;
        }

        public void apply(AddCardView addCardView) {
            addCardView.addCardSystemWithIndex(this.index);
        }
    }

    /* compiled from: AddCardView$$State */
    public class ClearCardSystemsCommand extends ViewCommand<AddCardView> {
        ClearCardSystemsCommand() {
            super("clearCardSystems", AddToEndSingleStrategy.class);
        }

        public void apply(AddCardView addCardView) {
            addCardView.clearCardSystems();
        }
    }

    /* compiled from: AddCardView$$State */
    public class TokenizeCommand extends ViewCommand<AddCardView> {
        public final CardBuilder cardBuilder;

        TokenizeCommand(CardBuilder cardBuilder2) {
            super("tokenize", AddToEndSingleStrategy.class);
            this.cardBuilder = cardBuilder2;
        }

        public void apply(AddCardView addCardView) {
            addCardView.tokenize(this.cardBuilder);
        }
    }

    /* compiled from: AddCardView$$State */
    public class TokenizeStripeCommand extends ViewCommand<AddCardView> {
        public final Card card;

        TokenizeStripeCommand(Card card2) {
            super("tokenizeStripe", AddToEndSingleStrategy.class);
            this.card = card2;
        }

        public void apply(AddCardView addCardView) {
            addCardView.tokenizeStripe(this.card);
        }
    }

    /* compiled from: AddCardView$$State */
    public class InitPaymentCommand extends ViewCommand<AddCardView> {
        public final String token;

        InitPaymentCommand(String str) {
            super("initPayment", AddToEndSingleStrategy.class);
            this.token = str;
        }

        public void apply(AddCardView addCardView) {
            addCardView.initPayment(this.token);
        }
    }

    /* compiled from: AddCardView$$State */
    public class ShowCardTypesCommand extends ViewCommand<AddCardView> {
        ShowCardTypesCommand() {
            super("showCardTypes", AddToEndSingleStrategy.class);
        }

        public void apply(AddCardView addCardView) {
            addCardView.showCardTypes();
        }
    }

    /* compiled from: AddCardView$$State */
    public class ShowMessageCommand extends ViewCommand<AddCardView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(AddCardView addCardView) {
            addCardView.showMessage(this.message);
        }
    }

    /* compiled from: AddCardView$$State */
    public class ShowMessage1Command extends ViewCommand<AddCardView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(AddCardView addCardView) {
            addCardView.showMessage(this.resId);
        }
    }

    /* compiled from: AddCardView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<AddCardView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(AddCardView addCardView) {
            addCardView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: AddCardView$$State */
    public class ShowProgressCommand extends ViewCommand<AddCardView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(AddCardView addCardView) {
            addCardView.showProgress(this.show);
        }
    }

    /* compiled from: AddCardView$$State */
    public class ShowServerErrorCommand extends ViewCommand<AddCardView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(AddCardView addCardView) {
            addCardView.showServerError();
        }
    }
}
