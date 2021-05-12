package ru.terrakok.cicerone.android.pure;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import ru.terrakok.cicerone.Screen;

public abstract class AppScreen extends Screen {
    public Intent getActivityIntent(Context context) {
        return null;
    }

    public Fragment getFragment() {
        return null;
    }
}
