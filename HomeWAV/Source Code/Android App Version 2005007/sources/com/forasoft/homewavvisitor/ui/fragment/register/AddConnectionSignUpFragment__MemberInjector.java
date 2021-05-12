package com.forasoft.homewavvisitor.ui.fragment.register;

import com.forasoft.homewavvisitor.model.Constants;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class AddConnectionSignUpFragment__MemberInjector implements MemberInjector<AddConnectionSignUpFragment> {
    public void inject(AddConnectionSignUpFragment addConnectionSignUpFragment, Scope scope) {
        addConnectionSignUpFragment.constants = (Constants) scope.getInstance(Constants.class);
    }
}
