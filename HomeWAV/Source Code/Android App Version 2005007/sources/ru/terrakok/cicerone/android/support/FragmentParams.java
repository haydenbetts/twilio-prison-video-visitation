package ru.terrakok.cicerone.android.support;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

public final class FragmentParams {
    private final Bundle arguments;
    private final Class<? extends Fragment> fragmentClass;

    public final Class<? extends Fragment> getFragmentClass() {
        return this.fragmentClass;
    }

    public final Bundle getArguments() {
        return this.arguments;
    }

    public FragmentParams(Class<? extends Fragment> cls, Bundle bundle) {
        this.fragmentClass = cls;
        this.arguments = bundle;
    }

    public FragmentParams(Class<? extends Fragment> cls) {
        this.fragmentClass = cls;
        this.arguments = null;
    }
}
