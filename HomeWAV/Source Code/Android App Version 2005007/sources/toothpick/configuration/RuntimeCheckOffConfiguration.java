package toothpick.configuration;

import toothpick.Scope;
import toothpick.config.Binding;

class RuntimeCheckOffConfiguration implements RuntimeCheckConfiguration {
    public void checkCyclesEnd(Class cls, String str) {
    }

    public void checkCyclesStart(Class cls, String str) {
    }

    public void checkIllegalBinding(Binding binding, Scope scope) {
    }

    RuntimeCheckOffConfiguration() {
    }
}
