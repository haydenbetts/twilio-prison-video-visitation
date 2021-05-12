package com.forasoft.homewavvisitor.model.data.register;

import com.forasoft.homewavvisitor.model.Constants;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class Connection__MemberInjector implements MemberInjector<Connection> {
    public void inject(Connection connection, Scope scope) {
        connection.constants = (Constants) scope.getInstance(Constants.class);
    }
}
