package toothpick.configuration;

import toothpick.Scope;
import toothpick.config.Binding;

interface RuntimeCheckConfiguration {
    void checkCyclesEnd(Class cls, String str);

    void checkCyclesStart(Class cls, String str);

    void checkIllegalBinding(Binding binding, Scope scope);
}
