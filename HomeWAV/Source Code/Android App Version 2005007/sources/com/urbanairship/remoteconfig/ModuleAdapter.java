package com.urbanairship.remoteconfig;

import android.util.SparseArray;
import com.urbanairship.AirshipComponent;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.json.JsonMap;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class ModuleAdapter {
    private SparseArray<Set<AirshipComponent>> componentGroupMap = null;

    ModuleAdapter() {
    }

    public void setComponentEnabled(String str, boolean z) {
        for (AirshipComponent componentEnabled : findAirshipComponents(str)) {
            componentEnabled.setComponentEnabled(z);
        }
    }

    public void onNewConfig(String str, JsonMap jsonMap) {
        for (AirshipComponent airshipComponent : findAirshipComponents(str)) {
            if (airshipComponent.isComponentEnabled()) {
                airshipComponent.onNewConfig(jsonMap);
            }
        }
    }

    private Collection<? extends AirshipComponent> findAirshipComponents(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1693017210:
                if (str.equals(Modules.ANALYTICS_MODULE)) {
                    c = 0;
                    break;
                }
                break;
            case -1071151692:
                if (str.equals(Modules.IN_APP_MODULE)) {
                    c = 1;
                    break;
                }
                break;
            case -280467183:
                if (str.equals("named_user")) {
                    c = 2;
                    break;
                }
                break;
            case 3452698:
                if (str.equals(Modules.PUSH_MODULE)) {
                    c = 3;
                    break;
                }
                break;
            case 344200471:
                if (str.equals(Modules.AUTOMATION_MODULE)) {
                    c = 4;
                    break;
                }
                break;
            case 536871821:
                if (str.equals(Modules.MESSAGE_CENTER)) {
                    c = 5;
                    break;
                }
                break;
            case 738950403:
                if (str.equals(Modules.CHANNEL_MODULE)) {
                    c = 6;
                    break;
                }
                break;
            case 1901043637:
                if (str.equals("location")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return getComponentsByGroup(1);
            case 1:
                return getComponentsByGroup(3);
            case 2:
                return getComponentsByGroup(5);
            case 3:
                return getComponentsByGroup(0);
            case 4:
                return getComponentsByGroup(4);
            case 5:
                return getComponentsByGroup(2);
            case 6:
                return getComponentsByGroup(7);
            case 7:
                return getComponentsByGroup(6);
            default:
                Logger.verbose("ModuleAdapter - Unable to find module: %s", str);
                return Collections.emptyList();
        }
    }

    private Set<AirshipComponent> getComponentsByGroup(int i) {
        if (this.componentGroupMap == null) {
            this.componentGroupMap = createComponentGroupMap(UAirship.shared().getComponents());
        }
        return this.componentGroupMap.get(i, Collections.emptySet());
    }

    private static SparseArray<Set<AirshipComponent>> createComponentGroupMap(Collection<AirshipComponent> collection) {
        SparseArray<Set<AirshipComponent>> sparseArray = new SparseArray<>();
        for (AirshipComponent next : collection) {
            Set set = sparseArray.get(next.getComponentGroup());
            if (set == null) {
                set = new HashSet();
                sparseArray.put(next.getComponentGroup(), set);
            }
            set.add(next);
        }
        return sparseArray;
    }
}
