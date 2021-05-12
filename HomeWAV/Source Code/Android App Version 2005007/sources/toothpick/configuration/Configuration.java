package toothpick.configuration;

import toothpick.Scope;
import toothpick.config.Binding;

public class Configuration implements RuntimeCheckConfiguration, MultipleRootScopeCheckConfiguration {
    private MultipleRootScopeCheckConfiguration multipleRootScopeCheckConfiguration = new MultipleRootScopeCheckOffConfiguration();
    private RuntimeCheckConfiguration runtimeCheckConfiguration = new RuntimeCheckOffConfiguration();

    public static Configuration forDevelopment() {
        Configuration configuration = new Configuration();
        configuration.runtimeCheckConfiguration = new RuntimeCheckOnConfiguration();
        return configuration;
    }

    public static Configuration forProduction() {
        return new Configuration();
    }

    public Configuration allowMultipleRootScopes() {
        this.multipleRootScopeCheckConfiguration = new MultipleRootScopeCheckOffConfiguration();
        return this;
    }

    public Configuration preventMultipleRootScopes() {
        this.multipleRootScopeCheckConfiguration = new MultipleRootScopeCheckOnConfiguration();
        return this;
    }

    public void checkIllegalBinding(Binding binding, Scope scope) {
        this.runtimeCheckConfiguration.checkIllegalBinding(binding, scope);
    }

    public void checkCyclesStart(Class cls, String str) {
        this.runtimeCheckConfiguration.checkCyclesStart(cls, str);
    }

    public void checkCyclesEnd(Class cls, String str) {
        this.runtimeCheckConfiguration.checkCyclesEnd(cls, str);
    }

    public void checkMultipleRootScopes(Scope scope) {
        this.multipleRootScopeCheckConfiguration.checkMultipleRootScopes(scope);
    }

    public void onScopeForestReset() {
        this.multipleRootScopeCheckConfiguration.onScopeForestReset();
    }
}
