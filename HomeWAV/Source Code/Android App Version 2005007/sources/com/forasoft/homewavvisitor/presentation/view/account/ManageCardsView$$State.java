package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.model.data.Card;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.OneExecutionStateStrategy;

public class ManageCardsView$$State extends MvpViewState<ManageCardsView> implements ManageCardsView {
    public void displayCards(List<Card> list) {
        DisplayCardsCommand displayCardsCommand = new DisplayCardsCommand(list);
        this.viewCommands.beforeApply(displayCardsCommand);
        if (!hasNotView().booleanValue()) {
            for (ManageCardsView displayCards : this.views) {
                displayCards.displayCards(list);
            }
            this.viewCommands.afterApply(displayCardsCommand);
        }
    }

    public void showProgress() {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand();
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (ManageCardsView showProgress : this.views) {
                showProgress.showProgress();
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void hideProgress() {
        HideProgressCommand hideProgressCommand = new HideProgressCommand();
        this.viewCommands.beforeApply(hideProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (ManageCardsView hideProgress : this.views) {
                hideProgress.hideProgress();
            }
            this.viewCommands.afterApply(hideProgressCommand);
        }
    }

    /* compiled from: ManageCardsView$$State */
    public class DisplayCardsCommand extends ViewCommand<ManageCardsView> {
        public final List<Card> cards;

        DisplayCardsCommand(List<Card> list) {
            super("displayCards", OneExecutionStateStrategy.class);
            this.cards = list;
        }

        public void apply(ManageCardsView manageCardsView) {
            manageCardsView.displayCards(this.cards);
        }
    }

    /* compiled from: ManageCardsView$$State */
    public class ShowProgressCommand extends ViewCommand<ManageCardsView> {
        ShowProgressCommand() {
            super("showProgress", OneExecutionStateStrategy.class);
        }

        public void apply(ManageCardsView manageCardsView) {
            manageCardsView.showProgress();
        }
    }

    /* compiled from: ManageCardsView$$State */
    public class HideProgressCommand extends ViewCommand<ManageCardsView> {
        HideProgressCommand() {
            super("hideProgress", OneExecutionStateStrategy.class);
        }

        public void apply(ManageCardsView manageCardsView) {
            manageCardsView.hideProgress();
        }
    }
}
