package com.forasoft.homewavvisitor.presentation.view.conversations;

import com.forasoft.homewavvisitor.model.Media;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;

public class MediaChooserView$$State extends MvpViewState<MediaChooserView> implements MediaChooserView {
    public void showMedia(List<? extends Media> list) {
        ShowMediaCommand showMediaCommand = new ShowMediaCommand(list);
        this.viewCommands.beforeApply(showMediaCommand);
        if (!hasNotView().booleanValue()) {
            for (MediaChooserView showMedia : this.views) {
                showMedia.showMedia(list);
            }
            this.viewCommands.afterApply(showMediaCommand);
        }
    }

    /* compiled from: MediaChooserView$$State */
    public class ShowMediaCommand extends ViewCommand<MediaChooserView> {
        public final List<? extends Media> media;

        ShowMediaCommand(List<? extends Media> list) {
            super("showMedia", AddToEndSingleStrategy.class);
            this.media = list;
        }

        public void apply(MediaChooserView mediaChooserView) {
            mediaChooserView.showMedia(this.media);
        }
    }
}
