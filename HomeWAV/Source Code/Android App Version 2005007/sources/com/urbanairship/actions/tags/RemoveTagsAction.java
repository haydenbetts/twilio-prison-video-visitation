package com.urbanairship.actions.tags;

import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionRegistry;
import com.urbanairship.actions.ActionResult;
import com.urbanairship.channel.TagGroupsEditor;
import java.util.Map;
import java.util.Set;

public class RemoveTagsAction extends BaseTagsAction {
    public static final String DEFAULT_REGISTRY_NAME = "remove_tags_action";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^-t";

    public /* bridge */ /* synthetic */ boolean acceptsArguments(ActionArguments actionArguments) {
        return super.acceptsArguments(actionArguments);
    }

    public /* bridge */ /* synthetic */ ActionResult perform(ActionArguments actionArguments) {
        return super.perform(actionArguments);
    }

    /* access modifiers changed from: package-private */
    public void applyChannelTags(Set<String> set) {
        Logger.info("RemoveTagsAction - Removing tags: %s", set);
        getChannel().editTags().removeTags(set).apply();
    }

    /* access modifiers changed from: package-private */
    public void applyChannelTagGroups(Map<String, Set<String>> map) {
        Logger.info("RemoveTagsAction - Removing channel tag groups: %s", map);
        TagGroupsEditor editTagGroups = getChannel().editTagGroups();
        for (Map.Entry next : map.entrySet()) {
            editTagGroups.removeTags((String) next.getKey(), (Set) next.getValue());
        }
        editTagGroups.apply();
    }

    /* access modifiers changed from: package-private */
    public void applyNamedUserTagGroups(Map<String, Set<String>> map) {
        Logger.info("RemoveTagsAction - Removing named user tag groups: %s", map);
        TagGroupsEditor editTagGroups = UAirship.shared().getNamedUser().editTagGroups();
        for (Map.Entry next : map.entrySet()) {
            editTagGroups.removeTags((String) next.getKey(), (Set) next.getValue());
        }
        editTagGroups.apply();
    }

    public static class RemoveTagsPredicate implements ActionRegistry.Predicate {
        public boolean apply(ActionArguments actionArguments) {
            return 1 != actionArguments.getSituation();
        }
    }
}
