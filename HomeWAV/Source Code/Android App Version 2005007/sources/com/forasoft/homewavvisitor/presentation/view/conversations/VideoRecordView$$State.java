package com.forasoft.homewavvisitor.presentation.view.conversations;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;

public class VideoRecordView$$State extends MvpViewState<VideoRecordView> implements VideoRecordView {
    public void recordMp4Video(int i, boolean z, boolean z2) {
        RecordMp4VideoCommand recordMp4VideoCommand = new RecordMp4VideoCommand(i, z, z2);
        this.viewCommands.beforeApply(recordMp4VideoCommand);
        if (!hasNotView().booleanValue()) {
            for (VideoRecordView recordMp4Video : this.views) {
                recordMp4Video.recordMp4Video(i, z, z2);
            }
            this.viewCommands.afterApply(recordMp4VideoCommand);
        }
    }

    public void updateRemainingTime(int i) {
        UpdateRemainingTimeCommand updateRemainingTimeCommand = new UpdateRemainingTimeCommand(i);
        this.viewCommands.beforeApply(updateRemainingTimeCommand);
        if (!hasNotView().booleanValue()) {
            for (VideoRecordView updateRemainingTime : this.views) {
                updateRemainingTime.updateRemainingTime(i);
            }
            this.viewCommands.afterApply(updateRemainingTimeCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (VideoRecordView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    /* compiled from: VideoRecordView$$State */
    public class RecordMp4VideoCommand extends ViewCommand<VideoRecordView> {
        public final boolean canRecordVideo;
        public final boolean canTakePhoto;
        public final int facing;

        RecordMp4VideoCommand(int i, boolean z, boolean z2) {
            super("recordMp4Video", AddToEndSingleStrategy.class);
            this.facing = i;
            this.canRecordVideo = z;
            this.canTakePhoto = z2;
        }

        public void apply(VideoRecordView videoRecordView) {
            videoRecordView.recordMp4Video(this.facing, this.canRecordVideo, this.canTakePhoto);
        }
    }

    /* compiled from: VideoRecordView$$State */
    public class UpdateRemainingTimeCommand extends ViewCommand<VideoRecordView> {
        public final int remaining;

        UpdateRemainingTimeCommand(int i) {
            super("updateRemainingTime", AddToEndSingleStrategy.class);
            this.remaining = i;
        }

        public void apply(VideoRecordView videoRecordView) {
            videoRecordView.updateRemainingTime(this.remaining);
        }
    }

    /* compiled from: VideoRecordView$$State */
    public class ShowMessageCommand extends ViewCommand<VideoRecordView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", AddToEndSingleStrategy.class);
            this.message = str;
        }

        public void apply(VideoRecordView videoRecordView) {
            videoRecordView.showMessage(this.message);
        }
    }
}
