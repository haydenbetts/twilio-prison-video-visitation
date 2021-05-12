package com.forasoft.homewavvisitor.presentation.view;

import com.forasoft.homewavvisitor.ui.activity.ServerErrorDisplay;
import kotlin.Metadata;
import moxy.MvpView;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&¨\u0006\u0006"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/SplashView;", "Lmoxy/MvpView;", "Lcom/forasoft/homewavvisitor/ui/activity/ServerErrorDisplay;", "onLoadedLibrary", "", "showUpdateVersionDialog", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SplashView.kt */
public interface SplashView extends MvpView, ServerErrorDisplay {
    void onLoadedLibrary();

    void showUpdateVersionDialog();
}
