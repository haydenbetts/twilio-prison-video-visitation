package com.urbanairship.actions.tags;

import com.urbanairship.UAirship;
import com.urbanairship.actions.Action;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionResult;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAStringUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

abstract class BaseTagsAction extends Action {
    private static final String CHANNEL_KEY = "channel";
    private static final String DEVICE_KEY = "device";
    private static final String NAMED_USER_KEY = "named_user";

    /* access modifiers changed from: package-private */
    public abstract void applyChannelTagGroups(Map<String, Set<String>> map);

    /* access modifiers changed from: package-private */
    public abstract void applyChannelTags(Set<String> set);

    /* access modifiers changed from: package-private */
    public abstract void applyNamedUserTagGroups(Map<String, Set<String>> map);

    BaseTagsAction() {
    }

    /* access modifiers changed from: protected */
    public AirshipChannel getChannel() {
        return UAirship.shared().getChannel();
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        if (actionArguments.getValue().isNull()) {
            return false;
        }
        if (actionArguments.getValue().getString() == null && actionArguments.getValue().getList() == null && actionArguments.getValue().getMap() == null) {
            return false;
        }
        return true;
    }

    public ActionResult perform(ActionArguments actionArguments) {
        if (actionArguments.getValue().getString() != null) {
            HashSet hashSet = new HashSet();
            hashSet.add(String.valueOf(actionArguments.getValue().getString()));
            applyChannelTags(hashSet);
        }
        if (actionArguments.getValue().getList() != null) {
            HashSet hashSet2 = new HashSet();
            Iterator<JsonValue> it = actionArguments.getValue().getList().iterator();
            while (it.hasNext()) {
                JsonValue next = it.next();
                if (next.getString() != null) {
                    hashSet2.add(next.getString());
                }
            }
            applyChannelTags(hashSet2);
        }
        if (actionArguments.getValue().getMap() != null) {
            HashMap hashMap = new HashMap();
            for (Map.Entry next2 : actionArguments.getValue().getMap().opt("channel").optMap().getMap().entrySet()) {
                String str = (String) next2.getKey();
                HashSet hashSet3 = new HashSet();
                for (JsonValue string : ((JsonValue) next2.getValue()).optList().getList()) {
                    hashSet3.add(string.getString());
                }
                if (!UAStringUtil.isEmpty(str) && !hashSet3.isEmpty()) {
                    hashMap.put(str, hashSet3);
                }
            }
            if (!hashMap.isEmpty()) {
                applyChannelTagGroups(hashMap);
            }
            HashMap hashMap2 = new HashMap();
            for (Map.Entry next3 : actionArguments.getValue().getMap().opt("named_user").optMap().getMap().entrySet()) {
                String str2 = (String) next3.getKey();
                HashSet hashSet4 = new HashSet();
                for (JsonValue string2 : ((JsonValue) next3.getValue()).optList().getList()) {
                    hashSet4.add(string2.getString());
                }
                if (!UAStringUtil.isEmpty(str2) && !hashSet4.isEmpty()) {
                    hashMap2.put(str2, hashSet4);
                }
            }
            if (!hashMap2.isEmpty()) {
                applyNamedUserTagGroups(hashMap2);
            }
            HashSet hashSet5 = new HashSet();
            Iterator<JsonValue> it2 = actionArguments.getValue().getMap().opt(DEVICE_KEY).optList().iterator();
            while (it2.hasNext()) {
                JsonValue next4 = it2.next();
                if (next4.getString() != null) {
                    hashSet5.add(next4.getString());
                }
            }
            if (!hashSet5.isEmpty()) {
                applyChannelTags(hashSet5);
            }
        }
        return ActionResult.newEmptyResult();
    }
}
