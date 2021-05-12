package toothpick.config;

import java.util.HashSet;
import java.util.Set;
import toothpick.config.Binding;

public class Module {
    private Set<Binding> bindingSet = new HashSet();

    public <T> Binding<T>.CanBeNamed bind(Class<T> cls) {
        Binding binding = new Binding(cls);
        this.bindingSet.add(binding);
        return new Binding.CanBeNamed();
    }

    public Set<Binding> getBindingSet() {
        return this.bindingSet;
    }
}
