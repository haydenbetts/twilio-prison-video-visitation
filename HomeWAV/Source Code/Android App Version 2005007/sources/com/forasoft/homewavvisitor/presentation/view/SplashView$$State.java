package com.forasoft.homewavvisitor.presentation.view;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class SplashView$$State extends MvpViewState<SplashView> implements SplashView {
    public void showUpdateVersionDialog() {
        ShowUpdateVersionDialogCommand showUpdateVersionDialogCommand = new ShowUpdateVersionDialogCommand();
        this.viewCommands.beforeApply(showUpdateVersionDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (SplashView showUpdateVersionDialog : this.views) {
                showUpdateVersionDialog.showUpdateVersionDialog();
            }
            this.viewCommands.afterApply(showUpdateVersionDialogCommand);
        }
    }

    public void onLoadedLibrary() {
        OnLoadedLibraryCommand onLoadedLibraryCommand = new OnLoadedLibraryCommand();
        this.viewCommands.beforeApply(onLoadedLibraryCommand);
        if (!hasNotView().booleanValue()) {
            for (SplashView onLoadedLibrary : this.views) {
                onLoadedLibrary.onLoadedLibrary();
            }
            this.viewCommands.afterApply(onLoadedLibraryCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (SplashView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: SplashView$$State */
    public class ShowUpdateVersionDialogCommand extends ViewCommand<SplashView> {
        ShowUpdateVersionDialogCommand() {
            super("showUpdateVersionDialog", AddToEndSingleStrategy.class);
        }

        public void apply(SplashView splashView) {
            splashView.showUpdateVersionDialog();
        }
    }

    /* compiled from: SplashView$$State */
    public class OnLoadedLibraryCommand extends ViewCommand<SplashView> {
        OnLoadedLibraryCommand() {
            super("onLoadedLibrary", AddToEndSingleStrategy.class);
        }

        public void apply(SplashView splashView) {
            splashView.onLoadedLibrary();
        }
    }

    /* compiled from: SplashView$$State */
    public class ShowServerErrorCommand extends ViewCommand<SplashView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(SplashView splashView) {
            splashView.showServerError();
        }
    }
}
