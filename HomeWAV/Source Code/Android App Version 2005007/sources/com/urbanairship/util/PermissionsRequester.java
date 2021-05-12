package com.urbanairship.util;

import android.content.Context;
import java.util.List;

public interface PermissionsRequester {
    int[] requestPermissions(Context context, List<String> list);
}
