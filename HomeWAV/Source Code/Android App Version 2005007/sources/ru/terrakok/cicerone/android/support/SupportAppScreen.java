package ru.terrakok.cicerone.android.support;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import ru.terrakok.cicerone.Screen;

public abstract class SupportAppScreen extends Screen {
    public Intent getActivityIntent(Context context) {
        return null;
    }

    public Fragment getFragment() {
        return null;
    }

    public FragmentParams getFragmentParams() {
        return null;
    }
}
