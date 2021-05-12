package com.forasoft.homewavvisitor.presentation.view.account;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class TestVideoView$$State extends MvpViewState<TestVideoView> implements TestVideoView {
    public void updateRemainingTime(int i) {
        UpdateRemainingTimeCommand updateRemainingTimeCommand = new UpdateRemainingTimeCommand(i);
        this.viewCommands.beforeApply(updateRemainingTimeCommand);
        if (!hasNotView().booleanValue()) {
            for (TestVideoView updateRemainingTime : this.views) {
                updateRemainingTime.updateRemainingTime(i);
            }
            this.viewCommands.afterApply(updateRemainingTimeCommand);
        }
    }

    public void stopRecording() {
        StopRecordingCommand stopRecordingCommand = new StopRecordingCommand();
        this.viewCommands.beforeApply(stopRecordingCommand);
        if (!hasNotView().booleanValue()) {
            for (TestVideoView stopRecording : this.views) {
                stopRecording.stopRecording();
            }
            this.viewCommands.afterApply(stopRecordingCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (TestVideoView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (TestVideoView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (TestVideoView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (TestVideoView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (TestVideoView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: TestVideoView$$State */
    public class UpdateRemainingTimeCommand extends ViewCommand<TestVideoView> {
        public final int remaining;

        UpdateRemainingTimeCommand(int i) {
            super("updateRemainingTime", AddToEndSingleStrategy.class);
            this.remaining = i;
        }

        public void apply(TestVideoView testVideoView) {
            testVideoView.updateRemainingTime(this.remaining);
        }
    }

    /* compiled from: TestVideoView$$State */
    public class StopRecordingCommand extends ViewCommand<TestVideoView> {
        StopRecordingCommand() {
            super("stopRecording", AddToEndSingleStrategy.class);
        }

        public void apply(TestVideoView testVideoView) {
            testVideoView.stopRecording();
        }
    }

    /* compiled from: TestVideoView$$State */
    public class ShowMessageCommand extends ViewCommand<TestVideoView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(TestVideoView testVideoView) {
            testVideoView.showMessage(this.message);
        }
    }

    /* compiled from: TestVideoView$$State */
    public class ShowMessage1Command extends ViewCommand<TestVideoView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(TestVideoView testVideoView) {
            testVideoView.showMessage(this.resId);
        }
    }

    /* compiled from: TestVideoView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<TestVideoView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(TestVideoView testVideoView) {
            testVideoView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: TestVideoView$$State */
    public class ShowProgressCommand extends ViewCommand<TestVideoView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(TestVideoView testVideoView) {
            testVideoView.showProgress(this.show);
        }
    }

    /* compiled from: TestVideoView$$State */
    public class ShowServerErrorCommand extends ViewCommand<TestVideoView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(TestVideoView testVideoView) {
            testVideoView.showServerError();
        }
    }
}
