package com.forasoft.homewavvisitor.presentation.view.home;

import java.util.List;
import kotlin.Metadata;
import moxy.MvpView;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/home/TutorialsView;", "Lmoxy/MvpView;", "showError", "", "showTutorials", "tutorials", "", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TutorialsView.kt */
public interface TutorialsView extends MvpView {
    void showError();

    void showTutorials(List<String> list);
}
