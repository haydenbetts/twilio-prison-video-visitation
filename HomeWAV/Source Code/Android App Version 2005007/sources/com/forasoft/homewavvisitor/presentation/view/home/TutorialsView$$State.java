package com.forasoft.homewavvisitor.presentation.view.home;

import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;

public class TutorialsView$$State extends MvpViewState<TutorialsView> implements TutorialsView {
    public void showTutorials(List<String> list) {
        ShowTutorialsCommand showTutorialsCommand = new ShowTutorialsCommand(list);
        this.viewCommands.beforeApply(showTutorialsCommand);
        if (!hasNotView().booleanValue()) {
            for (TutorialsView showTutorials : this.views) {
                showTutorials.showTutorials(list);
            }
            this.viewCommands.afterApply(showTutorialsCommand);
        }
    }

    public void showError() {
        ShowErrorCommand showErrorCommand = new ShowErrorCommand();
        this.viewCommands.beforeApply(showErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (TutorialsView showError : this.views) {
                showError.showError();
            }
            this.viewCommands.afterApply(showErrorCommand);
        }
    }

    /* compiled from: TutorialsView$$State */
    public class ShowTutorialsCommand extends ViewCommand<TutorialsView> {
        public final List<String> tutorials;

        ShowTutorialsCommand(List<String> list) {
            super("showTutorials", AddToEndSingleStrategy.class);
            this.tutorials = list;
        }

        public void apply(TutorialsView tutorialsView) {
            tutorialsView.showTutorials(this.tutorials);
        }
    }

    /* compiled from: TutorialsView$$State */
    public class ShowErrorCommand extends ViewCommand<TutorialsView> {
        ShowErrorCommand() {
            super("showError", AddToEndSingleStrategy.class);
        }

        public void apply(TutorialsView tutorialsView) {
            tutorialsView.showError();
        }
    }
}
