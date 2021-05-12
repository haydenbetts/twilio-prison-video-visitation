package com.urbanairship.modules;

import android.content.Context;
import com.urbanairship.AirshipComponent;
import com.urbanairship.actions.ActionRegistry;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Module {
    private final int actionsXmlId;
    private final Set<? extends AirshipComponent> components;

    protected Module(Set<? extends AirshipComponent> set) {
        this(set, 0);
    }

    protected Module(Set<? extends AirshipComponent> set, int i) {
        this.components = set;
        this.actionsXmlId = i;
    }

    public static Module singleComponent(AirshipComponent airshipComponent, int i) {
        return new Module(Collections.singleton(airshipComponent), i);
    }

    public static Module multipleComponents(Collection<AirshipComponent> collection, int i) {
        return new Module(new HashSet(collection), i);
    }

    public Set<? extends AirshipComponent> getComponents() {
        return this.components;
    }

    public void registerActions(Context context, ActionRegistry actionRegistry) {
        int i = this.actionsXmlId;
        if (i != 0) {
            actionRegistry.registerActions(context, i);
        }
    }
}
