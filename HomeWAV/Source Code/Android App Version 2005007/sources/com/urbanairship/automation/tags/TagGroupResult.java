package com.urbanairship.automation.tags;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class TagGroupResult {
    public final boolean success;
    public final Map<String, Set<String>> tagGroups;

    public TagGroupResult(boolean z, Map<String, Set<String>> map) {
        this.success = z;
        this.tagGroups = map == null ? Collections.emptyMap() : map;
    }
}
