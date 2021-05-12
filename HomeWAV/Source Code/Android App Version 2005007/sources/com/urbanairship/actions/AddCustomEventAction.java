package com.urbanairship.actions;

import com.urbanairship.Logger;
import com.urbanairship.actions.ActionRegistry;
import com.urbanairship.analytics.CustomEvent;
import com.urbanairship.json.JsonMap;
import com.urbanairship.push.PushMessage;
import com.urbanairship.util.Checks;

public class AddCustomEventAction extends Action {
    public static final String DEFAULT_REGISTRY_NAME = "add_custom_event_action";

    public ActionResult perform(ActionArguments actionArguments) {
        String string;
        JsonMap optMap = actionArguments.getValue().toJsonValue().optMap();
        String string2 = optMap.opt(CustomEvent.EVENT_NAME).getString();
        Checks.checkNotNull(string2, "Missing event name");
        String string3 = optMap.opt(CustomEvent.EVENT_VALUE).getString();
        double d = optMap.opt(CustomEvent.EVENT_VALUE).getDouble(0.0d);
        String string4 = optMap.opt(CustomEvent.TRANSACTION_ID).getString();
        String string5 = optMap.opt(CustomEvent.INTERACTION_TYPE).getString();
        String string6 = optMap.opt(CustomEvent.INTERACTION_ID).getString();
        JsonMap map = optMap.opt(CustomEvent.PROPERTIES).getMap();
        CustomEvent.Builder interaction = CustomEvent.newBuilder(string2).setTransactionId(string4).setAttribution((PushMessage) actionArguments.getMetadata().getParcelable(ActionArguments.PUSH_MESSAGE_METADATA)).setInteraction(string5, string6);
        if (string3 != null) {
            interaction.setEventValue(string3);
        } else {
            interaction.setEventValue(d);
        }
        if (string6 == null && string5 == null && (string = actionArguments.getMetadata().getString(ActionArguments.RICH_PUSH_ID_METADATA)) != null) {
            interaction.setMessageCenterInteraction(string);
        }
        if (map != null) {
            interaction.setProperties(map);
        }
        CustomEvent build = interaction.build();
        build.track();
        if (build.isValid()) {
            return ActionResult.newEmptyResult();
        }
        return ActionResult.newErrorResult(new IllegalArgumentException("Unable to add custom event. Event is invalid."));
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        if (actionArguments.getValue().getMap() == null) {
            Logger.error("CustomEventAction requires a map of event data.", new Object[0]);
            return false;
        } else if (actionArguments.getValue().getMap().get(CustomEvent.EVENT_NAME) != null) {
            return true;
        } else {
            Logger.error("CustomEventAction requires an event name in the event data.", new Object[0]);
            return false;
        }
    }

    public static class AddCustomEventActionPredicate implements ActionRegistry.Predicate {
        public boolean apply(ActionArguments actionArguments) {
            return 1 != actionArguments.getSituation();
        }
    }
}
