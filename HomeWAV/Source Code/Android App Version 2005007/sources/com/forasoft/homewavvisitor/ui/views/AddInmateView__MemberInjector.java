package com.forasoft.homewavvisitor.ui.views;

import android.app.Activity;
import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.toothpick.FacilitiesSubjectWrapper;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class AddInmateView__MemberInjector implements MemberInjector<AddInmateView> {
    public void inject(AddInmateView addInmateView, Scope scope) {
        addInmateView.constants = (Constants) scope.getInstance(Constants.class);
        addInmateView.facilitiesSubjectWrapper = (FacilitiesSubjectWrapper) scope.getInstance(FacilitiesSubjectWrapper.class);
        addInmateView.activity = (Activity) scope.getInstance(Activity.class);
    }
}
