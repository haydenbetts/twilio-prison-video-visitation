package com.urbanairship.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatCallback;
import androidx.appcompat.app.AppCompatDelegate;

class AppCompatDelegateWrapper {
    private AppCompatDelegate delegate;

    AppCompatDelegateWrapper() {
    }

    static AppCompatDelegateWrapper create(Activity activity) {
        AppCompatDelegateWrapper appCompatDelegateWrapper = new AppCompatDelegateWrapper();
        appCompatDelegateWrapper.delegate = AppCompatDelegate.create(activity, (AppCompatCallback) null);
        return appCompatDelegateWrapper;
    }

    /* access modifiers changed from: package-private */
    public void onCreate(Bundle bundle) {
        AppCompatDelegate appCompatDelegate = this.delegate;
        if (appCompatDelegate != null) {
            appCompatDelegate.installViewFactory();
            this.delegate.onCreate(bundle);
        }
    }

    /* access modifiers changed from: package-private */
    public void onPostCreate(Bundle bundle) {
        this.delegate.onPostCreate(bundle);
    }

    /* access modifiers changed from: package-private */
    public MenuInflater getMenuInflater() {
        return this.delegate.getMenuInflater();
    }

    /* access modifiers changed from: package-private */
    public void setContentView(int i) {
        this.delegate.setContentView(i);
    }

    /* access modifiers changed from: package-private */
    public void setContentView(View view) {
        this.delegate.setContentView(view);
    }

    /* access modifiers changed from: package-private */
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.delegate.setContentView(view, layoutParams);
    }

    /* access modifiers changed from: package-private */
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.delegate.addContentView(view, layoutParams);
    }

    /* access modifiers changed from: package-private */
    public void onConfigurationChanged(Configuration configuration) {
        this.delegate.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: package-private */
    public void onPostResume() {
        this.delegate.onPostResume();
    }

    /* access modifiers changed from: package-private */
    public void onStop() {
        this.delegate.onStop();
    }

    /* access modifiers changed from: package-private */
    public void invalidateOptionsMenu() {
        this.delegate.invalidateOptionsMenu();
    }

    /* access modifiers changed from: package-private */
    public void setTitle(CharSequence charSequence) {
        this.delegate.setTitle(charSequence);
    }

    /* access modifiers changed from: package-private */
    public void onDestroy() {
        this.delegate.onDestroy();
    }

    /* access modifiers changed from: package-private */
    public ActionBar getSupportActionBar() {
        return this.delegate.getSupportActionBar();
    }
}
