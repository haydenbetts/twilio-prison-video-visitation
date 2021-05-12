package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.model.data.account.HistoryItem;
import java.util.ArrayList;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class HistoryView$$State extends MvpViewState<HistoryView> implements HistoryView {
    public void showData(List<HistoryItem> list) {
        ShowDataCommand showDataCommand = new ShowDataCommand(list);
        this.viewCommands.beforeApply(showDataCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView showData : this.views) {
                showData.showData(list);
            }
            this.viewCommands.afterApply(showDataCommand);
        }
    }

    public void initPeriodSpinner(ArrayList<String> arrayList) {
        InitPeriodSpinnerCommand initPeriodSpinnerCommand = new InitPeriodSpinnerCommand(arrayList);
        this.viewCommands.beforeApply(initPeriodSpinnerCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView initPeriodSpinner : this.views) {
                initPeriodSpinner.initPeriodSpinner(arrayList);
            }
            this.viewCommands.afterApply(initPeriodSpinnerCommand);
        }
    }

    public void initOrderSpinner(ArrayList<String> arrayList) {
        InitOrderSpinnerCommand initOrderSpinnerCommand = new InitOrderSpinnerCommand(arrayList);
        this.viewCommands.beforeApply(initOrderSpinnerCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView initOrderSpinner : this.views) {
                initOrderSpinner.initOrderSpinner(arrayList);
            }
            this.viewCommands.afterApply(initOrderSpinnerCommand);
        }
    }

    public void initInmatesSpinner(ArrayList<String> arrayList) {
        InitInmatesSpinnerCommand initInmatesSpinnerCommand = new InitInmatesSpinnerCommand(arrayList);
        this.viewCommands.beforeApply(initInmatesSpinnerCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView initInmatesSpinner : this.views) {
                initInmatesSpinner.initInmatesSpinner(arrayList);
            }
            this.viewCommands.afterApply(initInmatesSpinnerCommand);
        }
    }

    public void selectActivities() {
        SelectActivitiesCommand selectActivitiesCommand = new SelectActivitiesCommand();
        this.viewCommands.beforeApply(selectActivitiesCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView selectActivities : this.views) {
                selectActivities.selectActivities();
            }
            this.viewCommands.afterApply(selectActivitiesCommand);
        }
    }

    public void showPeriod(String str) {
        ShowPeriodCommand showPeriodCommand = new ShowPeriodCommand(str);
        this.viewCommands.beforeApply(showPeriodCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView showPeriod : this.views) {
                showPeriod.showPeriod(str);
            }
            this.viewCommands.afterApply(showPeriodCommand);
        }
    }

    public void selectMessages() {
        SelectMessagesCommand selectMessagesCommand = new SelectMessagesCommand();
        this.viewCommands.beforeApply(selectMessagesCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView selectMessages : this.views) {
                selectMessages.selectMessages();
            }
            this.viewCommands.afterApply(selectMessagesCommand);
        }
    }

    public void selectCalls() {
        SelectCallsCommand selectCallsCommand = new SelectCallsCommand();
        this.viewCommands.beforeApply(selectCallsCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView selectCalls : this.views) {
                selectCalls.selectCalls();
            }
            this.viewCommands.afterApply(selectCallsCommand);
        }
    }

    public void selectMoney() {
        SelectMoneyCommand selectMoneyCommand = new SelectMoneyCommand();
        this.viewCommands.beforeApply(selectMoneyCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView selectMoney : this.views) {
                selectMoney.selectMoney();
            }
            this.viewCommands.afterApply(selectMoneyCommand);
        }
    }

    public void setActivitiesCount(int i) {
        SetActivitiesCountCommand setActivitiesCountCommand = new SetActivitiesCountCommand(i);
        this.viewCommands.beforeApply(setActivitiesCountCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView activitiesCount : this.views) {
                activitiesCount.setActivitiesCount(i);
            }
            this.viewCommands.afterApply(setActivitiesCountCommand);
        }
    }

    public void setMessagesCount(int i) {
        SetMessagesCountCommand setMessagesCountCommand = new SetMessagesCountCommand(i);
        this.viewCommands.beforeApply(setMessagesCountCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView messagesCount : this.views) {
                messagesCount.setMessagesCount(i);
            }
            this.viewCommands.afterApply(setMessagesCountCommand);
        }
    }

    public void setCallsCount(int i) {
        SetCallsCountCommand setCallsCountCommand = new SetCallsCountCommand(i);
        this.viewCommands.beforeApply(setCallsCountCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView callsCount : this.views) {
                callsCount.setCallsCount(i);
            }
            this.viewCommands.afterApply(setCallsCountCommand);
        }
    }

    public void setMoneyAmount(float f) {
        SetMoneyAmountCommand setMoneyAmountCommand = new SetMoneyAmountCommand(f);
        this.viewCommands.beforeApply(setMoneyAmountCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView moneyAmount : this.views) {
                moneyAmount.setMoneyAmount(f);
            }
            this.viewCommands.afterApply(setMoneyAmountCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (HistoryView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (HistoryView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: HistoryView$$State */
    public class ShowDataCommand extends ViewCommand<HistoryView> {
        public final List<HistoryItem> body;

        ShowDataCommand(List<HistoryItem> list) {
            super("showData", AddToEndSingleStrategy.class);
            this.body = list;
        }

        public void apply(HistoryView historyView) {
            historyView.showData(this.body);
        }
    }

    /* compiled from: HistoryView$$State */
    public class InitPeriodSpinnerCommand extends ViewCommand<HistoryView> {
        public final ArrayList<String> historyPeriodList;

        InitPeriodSpinnerCommand(ArrayList<String> arrayList) {
            super("initPeriodSpinner", AddToEndSingleStrategy.class);
            this.historyPeriodList = arrayList;
        }

        public void apply(HistoryView historyView) {
            historyView.initPeriodSpinner(this.historyPeriodList);
        }
    }

    /* compiled from: HistoryView$$State */
    public class InitOrderSpinnerCommand extends ViewCommand<HistoryView> {
        public final ArrayList<String> historyOrderList;

        InitOrderSpinnerCommand(ArrayList<String> arrayList) {
            super("initOrderSpinner", AddToEndSingleStrategy.class);
            this.historyOrderList = arrayList;
        }

        public void apply(HistoryView historyView) {
            historyView.initOrderSpinner(this.historyOrderList);
        }
    }

    /* compiled from: HistoryView$$State */
    public class InitInmatesSpinnerCommand extends ViewCommand<HistoryView> {
        public final ArrayList<String> inmatesList;

        InitInmatesSpinnerCommand(ArrayList<String> arrayList) {
            super("initInmatesSpinner", AddToEndSingleStrategy.class);
            this.inmatesList = arrayList;
        }

        public void apply(HistoryView historyView) {
            historyView.initInmatesSpinner(this.inmatesList);
        }
    }

    /* compiled from: HistoryView$$State */
    public class SelectActivitiesCommand extends ViewCommand<HistoryView> {
        SelectActivitiesCommand() {
            super("selectActivities", AddToEndSingleStrategy.class);
        }

        public void apply(HistoryView historyView) {
            historyView.selectActivities();
        }
    }

    /* compiled from: HistoryView$$State */
    public class ShowPeriodCommand extends ViewCommand<HistoryView> {
        public final String text;

        ShowPeriodCommand(String str) {
            super("showPeriod", AddToEndSingleStrategy.class);
            this.text = str;
        }

        public void apply(HistoryView historyView) {
            historyView.showPeriod(this.text);
        }
    }

    /* compiled from: HistoryView$$State */
    public class SelectMessagesCommand extends ViewCommand<HistoryView> {
        SelectMessagesCommand() {
            super("selectMessages", AddToEndSingleStrategy.class);
        }

        public void apply(HistoryView historyView) {
            historyView.selectMessages();
        }
    }

    /* compiled from: HistoryView$$State */
    public class SelectCallsCommand extends ViewCommand<HistoryView> {
        SelectCallsCommand() {
            super("selectCalls", AddToEndSingleStrategy.class);
        }

        public void apply(HistoryView historyView) {
            historyView.selectCalls();
        }
    }

    /* compiled from: HistoryView$$State */
    public class SelectMoneyCommand extends ViewCommand<HistoryView> {
        SelectMoneyCommand() {
            super("selectMoney", AddToEndSingleStrategy.class);
        }

        public void apply(HistoryView historyView) {
            historyView.selectMoney();
        }
    }

    /* compiled from: HistoryView$$State */
    public class SetActivitiesCountCommand extends ViewCommand<HistoryView> {
        public final int count;

        SetActivitiesCountCommand(int i) {
            super("setActivitiesCount", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(HistoryView historyView) {
            historyView.setActivitiesCount(this.count);
        }
    }

    /* compiled from: HistoryView$$State */
    public class SetMessagesCountCommand extends ViewCommand<HistoryView> {
        public final int count;

        SetMessagesCountCommand(int i) {
            super("setMessagesCount", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(HistoryView historyView) {
            historyView.setMessagesCount(this.count);
        }
    }

    /* compiled from: HistoryView$$State */
    public class SetCallsCountCommand extends ViewCommand<HistoryView> {
        public final int count;

        SetCallsCountCommand(int i) {
            super("setCallsCount", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(HistoryView historyView) {
            historyView.setCallsCount(this.count);
        }
    }

    /* compiled from: HistoryView$$State */
    public class SetMoneyAmountCommand extends ViewCommand<HistoryView> {
        public final float amount;

        SetMoneyAmountCommand(float f) {
            super("setMoneyAmount", AddToEndSingleStrategy.class);
            this.amount = f;
        }

        public void apply(HistoryView historyView) {
            historyView.setMoneyAmount(this.amount);
        }
    }

    /* compiled from: HistoryView$$State */
    public class ShowMessageCommand extends ViewCommand<HistoryView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(HistoryView historyView) {
            historyView.showMessage(this.message);
        }
    }

    /* compiled from: HistoryView$$State */
    public class ShowMessage1Command extends ViewCommand<HistoryView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(HistoryView historyView) {
            historyView.showMessage(this.resId);
        }
    }

    /* compiled from: HistoryView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<HistoryView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(HistoryView historyView) {
            historyView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: HistoryView$$State */
    public class ShowProgressCommand extends ViewCommand<HistoryView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(HistoryView historyView) {
            historyView.showProgress(this.show);
        }
    }

    /* compiled from: HistoryView$$State */
    public class ShowServerErrorCommand extends ViewCommand<HistoryView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(HistoryView historyView) {
            historyView.showServerError();
        }
    }
}
