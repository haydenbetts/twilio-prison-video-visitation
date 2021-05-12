package toothpick.configuration;

import toothpick.Scope;

interface MultipleRootScopeCheckConfiguration {
    void checkMultipleRootScopes(Scope scope);

    void onScopeForestReset();
}
