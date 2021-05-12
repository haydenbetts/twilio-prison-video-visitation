package com.urbanairship.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import com.urbanairship.Cancelable;
import com.urbanairship.ResultCallback;

public interface LocationAdapter {
    void cancelLocationUpdates(Context context, PendingIntent pendingIntent);

    int getRequestCode();

    boolean isAvailable(Context context);

    void onSystemLocationProvidersChanged(Context context, LocationRequestOptions locationRequestOptions, PendingIntent pendingIntent);

    void requestLocationUpdates(Context context, LocationRequestOptions locationRequestOptions, PendingIntent pendingIntent);

    Cancelable requestSingleLocation(Context context, LocationRequestOptions locationRequestOptions, ResultCallback<Location> resultCallback);
}
