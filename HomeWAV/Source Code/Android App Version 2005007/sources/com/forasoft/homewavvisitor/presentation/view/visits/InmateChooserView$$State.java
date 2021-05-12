package com.forasoft.homewavvisitor.presentation.view.visits;

import com.forasoft.homewavvisitor.model.data.Inmate;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;

public class InmateChooserView$$State extends MvpViewState<InmateChooserView> implements InmateChooserView {
    public void displayInmates(List<Inmate> list) {
        DisplayInmatesCommand displayInmatesCommand = new DisplayInmatesCommand(list);
        this.viewCommands.beforeApply(displayInmatesCommand);
        if (!hasNotView().booleanValue()) {
            for (InmateChooserView displayInmates : this.views) {
                displayInmates.displayInmates(list);
            }
            this.viewCommands.afterApply(displayInmatesCommand);
        }
    }

    /* compiled from: InmateChooserView$$State */
    public class DisplayInmatesCommand extends ViewCommand<InmateChooserView> {
        public final List<Inmate> inmates;

        DisplayInmatesCommand(List<Inmate> list) {
            super("displayInmates", AddToEndSingleStrategy.class);
            this.inmates = list;
        }

        public void apply(InmateChooserView inmateChooserView) {
            inmateChooserView.displayInmates(this.inmates);
        }
    }
}
