package com.urbanairship.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;

public abstract class ThemedActivity extends FragmentActivity {
    private static Boolean isAppCompatDependencyAvailable;
    private AppCompatDelegateWrapper delegate;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (isAppCompatAvailable(this)) {
            this.delegate = AppCompatDelegateWrapper.create(this);
        }
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.onCreate(bundle);
        }
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.onPostCreate(bundle);
        }
    }

    public MenuInflater getMenuInflater() {
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            return appCompatDelegateWrapper.getMenuInflater();
        }
        return super.getMenuInflater();
    }

    public void setContentView(int i) {
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.setContentView(i);
        } else {
            super.setContentView(i);
        }
    }

    public void setContentView(View view) {
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.setContentView(view);
        } else {
            super.setContentView(view);
        }
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.setContentView(view, layoutParams);
        } else {
            super.setContentView(view, layoutParams);
        }
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.addContentView(view, layoutParams);
        } else {
            super.addContentView(view, layoutParams);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.onConfigurationChanged(configuration);
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.onStop();
        }
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.onPostResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.onDestroy();
        }
    }

    /* access modifiers changed from: protected */
    public void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.setTitle(charSequence);
        }
    }

    public void invalidateOptionsMenu() {
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            appCompatDelegateWrapper.invalidateOptionsMenu();
        } else {
            super.invalidateOptionsMenu();
        }
    }

    /* access modifiers changed from: protected */
    public void setDisplayHomeAsUpEnabled(boolean z) {
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            if (appCompatDelegateWrapper.getSupportActionBar() != null) {
                this.delegate.getSupportActionBar().setDisplayHomeAsUpEnabled(z);
                this.delegate.getSupportActionBar().setHomeButtonEnabled(z);
            }
        } else if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(z);
            getActionBar().setHomeButtonEnabled(z);
        }
    }

    /* access modifiers changed from: protected */
    public void hideActionBar() {
        AppCompatDelegateWrapper appCompatDelegateWrapper = this.delegate;
        if (appCompatDelegateWrapper != null) {
            if (appCompatDelegateWrapper.getSupportActionBar() != null) {
                this.delegate.getSupportActionBar().hide();
            }
        } else if (getActionBar() != null) {
            getActionBar().hide();
        }
    }

    static boolean isAppCompatAvailable(Activity activity) {
        int identifier;
        if (isAppCompatDependencyAvailable == null) {
            try {
                Class.forName("androidx.appcompat.app.AppCompatDelegate");
                isAppCompatDependencyAvailable = true;
            } catch (ClassNotFoundException unused) {
                isAppCompatDependencyAvailable = false;
            }
        }
        if (!isAppCompatDependencyAvailable.booleanValue() || (identifier = activity.getResources().getIdentifier("colorPrimary", "attr", activity.getPackageName())) == 0) {
            return false;
        }
        TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(new int[]{identifier});
        boolean hasValue = obtainStyledAttributes.hasValue(0);
        obtainStyledAttributes.recycle();
        return hasValue;
    }
}
