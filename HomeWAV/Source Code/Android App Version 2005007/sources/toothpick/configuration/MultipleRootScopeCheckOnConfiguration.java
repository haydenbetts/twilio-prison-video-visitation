package toothpick.configuration;

import toothpick.Scope;

class MultipleRootScopeCheckOnConfiguration implements MultipleRootScopeCheckConfiguration {
    private Scope rootScope;

    MultipleRootScopeCheckOnConfiguration() {
    }

    public synchronized void checkMultipleRootScopes(Scope scope) {
        Scope scope2 = this.rootScope;
        if (scope2 == null && scope != null) {
            this.rootScope = scope;
        } else if (scope != scope2) {
            throw new MultipleRootException(scope);
        }
    }

    public synchronized void onScopeForestReset() {
        this.rootScope = null;
    }
}
