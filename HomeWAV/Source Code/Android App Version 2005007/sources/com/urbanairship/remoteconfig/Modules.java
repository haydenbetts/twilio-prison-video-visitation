package com.urbanairship.remoteconfig;

import java.util.Arrays;
import java.util.List;

interface Modules {
    public static final List<String> ALL_MODULES = Arrays.asList(new String[]{PUSH_MODULE, ANALYTICS_MODULE, MESSAGE_CENTER, IN_APP_MODULE, AUTOMATION_MODULE, "named_user", "location", CHANNEL_MODULE});
    public static final String ANALYTICS_MODULE = "analytics";
    public static final String AUTOMATION_MODULE = "automation";
    public static final String CHANNEL_MODULE = "channel";
    public static final String IN_APP_MODULE = "in_app_v2";
    public static final String LOCATION_MODULE = "location";
    public static final String MESSAGE_CENTER = "message_center";
    public static final String NAMED_USER_MODULE = "named_user";
    public static final String PUSH_MODULE = "push";
}
