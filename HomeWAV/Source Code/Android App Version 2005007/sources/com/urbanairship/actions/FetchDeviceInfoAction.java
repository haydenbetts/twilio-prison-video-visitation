package com.urbanairship.actions;

import com.urbanairship.UAirship;
import com.urbanairship.actions.ActionRegistry;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.modules.location.AirshipLocationClient;
import java.util.Set;

public class FetchDeviceInfoAction extends Action {
    public static final String CHANNEL_ID_KEY = "channel_id";
    public static final String DEFAULT_REGISTRY_NAME = "fetch_device_info";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^fdi";
    public static final String LOCATION_ENABLED_KEY = "location_enabled";
    public static final String NAMED_USER_ID_KEY = "named_user";
    public static final String PUSH_OPT_IN_KEY = "push_opt_in";
    public static final String TAGS_KEY = "tags";

    public ActionResult perform(ActionArguments actionArguments) {
        AirshipLocationClient locationClient = UAirship.shared().getLocationClient();
        JsonMap.Builder putOpt = JsonMap.newBuilder().put("channel_id", UAirship.shared().getChannel().getId()).put(PUSH_OPT_IN_KEY, UAirship.shared().getPushManager().isOptIn()).put(LOCATION_ENABLED_KEY, locationClient != null && locationClient.isLocationUpdatesEnabled()).putOpt("named_user", UAirship.shared().getNamedUser().getId());
        Set<String> tags = UAirship.shared().getChannel().getTags();
        if (!tags.isEmpty()) {
            putOpt.put(TAGS_KEY, (JsonSerializable) JsonValue.wrapOpt(tags));
        }
        return ActionResult.newResult(new ActionValue(putOpt.build().toJsonValue()));
    }

    public static class FetchDeviceInfoPredicate implements ActionRegistry.Predicate {
        public boolean apply(ActionArguments actionArguments) {
            return actionArguments.getSituation() == 3 || actionArguments.getSituation() == 0;
        }
    }
}
