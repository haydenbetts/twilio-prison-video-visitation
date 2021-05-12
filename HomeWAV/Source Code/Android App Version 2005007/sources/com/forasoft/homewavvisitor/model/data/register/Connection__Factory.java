package com.forasoft.homewavvisitor.model.data.register;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class Connection__Factory implements Factory<Connection> {
    private MemberInjector<Connection> memberInjector = new Connection__MemberInjector();

    public Scope getTargetScope(Scope scope) {
        return scope;
    }

    public boolean hasProvidesReleasableAnnotation() {
        return false;
    }

    public boolean hasProvidesSingletonAnnotation() {
        return false;
    }

    public boolean hasReleasableAnnotation() {
        return false;
    }

    public boolean hasScopeAnnotation() {
        return false;
    }

    public boolean hasSingletonAnnotation() {
        return false;
    }

    public Connection createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        Connection connection = new Connection();
        this.memberInjector.inject(connection, targetScope);
        return connection;
    }
}
