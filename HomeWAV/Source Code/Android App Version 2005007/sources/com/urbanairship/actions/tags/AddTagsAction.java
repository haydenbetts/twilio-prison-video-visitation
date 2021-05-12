package com.urbanairship.actions.tags;

import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionRegistry;
import com.urbanairship.actions.ActionResult;
import com.urbanairship.channel.TagGroupsEditor;
import java.util.Map;
import java.util.Set;

public class AddTagsAction extends BaseTagsAction {
    public static final String DEFAULT_REGISTRY_NAME = "add_tags_action";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^+t";

    public /* bridge */ /* synthetic */ boolean acceptsArguments(ActionArguments actionArguments) {
        return super.acceptsArguments(actionArguments);
    }

    public /* bridge */ /* synthetic */ ActionResult perform(ActionArguments actionArguments) {
        return super.perform(actionArguments);
    }

    /* access modifiers changed from: package-private */
    public void applyChannelTags(Set<String> set) {
        Logger.info("AddTagsAction - Adding tags: %s", set);
        getChannel().editTags().addTags(set).apply();
    }

    /* access modifiers changed from: package-private */
    public void applyChannelTagGroups(Map<String, Set<String>> map) {
        Logger.info("AddTagsAction - Adding channel tag groups: %s", map);
        TagGroupsEditor editTagGroups = getChannel().editTagGroups();
        for (Map.Entry next : map.entrySet()) {
            editTagGroups.addTags((String) next.getKey(), (Set) next.getValue());
        }
        editTagGroups.apply();
    }

    /* access modifiers changed from: package-private */
    public void applyNamedUserTagGroups(Map<String, Set<String>> map) {
        Logger.info("AddTagsAction - Adding named user tag groups: %s", map);
        TagGroupsEditor editTagGroups = UAirship.shared().getNamedUser().editTagGroups();
        for (Map.Entry next : map.entrySet()) {
            editTagGroups.addTags((String) next.getKey(), (Set) next.getValue());
        }
        editTagGroups.apply();
    }

    public static class AddTagsPredicate implements ActionRegistry.Predicate {
        public boolean apply(ActionArguments actionArguments) {
            return 1 != actionArguments.getSituation();
        }
    }
}
