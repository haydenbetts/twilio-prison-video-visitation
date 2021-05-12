package com.urbanairship.util;

import com.urbanairship.AirshipComponent;
import com.urbanairship.UAirship;
import java.util.concurrent.Callable;

public class AirshipComponentUtils {
    public static <T extends AirshipComponent> Callable<T> callableForComponent(final Class<T> cls) {
        return new Callable<T>() {
            public T call() throws Exception {
                return UAirship.shared().requireComponent(cls);
            }
        };
    }
}
