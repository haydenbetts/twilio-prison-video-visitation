package ru.terrakok.cicerone.android.pure;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import java.util.LinkedList;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;

public class AppNavigator implements Navigator {
    protected final Activity activity;
    protected final int containerId;
    protected final FragmentManager fragmentManager;
    protected LinkedList<String> localStackCopy;

    /* access modifiers changed from: protected */
    public Bundle createStartActivityOptions(Command command, Intent intent) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void errorWhileCreatingScreen(AppScreen appScreen) {
    }

    /* access modifiers changed from: protected */
    public void setupFragmentTransaction(Command command, Fragment fragment, Fragment fragment2, FragmentTransaction fragmentTransaction) {
    }

    /* access modifiers changed from: protected */
    public void unexistingActivity(AppScreen appScreen, Intent intent) {
    }

    public AppNavigator(Activity activity2, int i) {
        this(activity2, activity2.getFragmentManager(), i);
    }

    public AppNavigator(Activity activity2, FragmentManager fragmentManager2, int i) {
        this.activity = activity2;
        this.fragmentManager = fragmentManager2;
        this.containerId = i;
    }

    public void applyCommands(Command[] commandArr) {
        this.fragmentManager.executePendingTransactions();
        copyStackToLocal();
        for (Command command : commandArr) {
            try {
                applyCommand(command);
            } catch (RuntimeException e) {
                errorOnApplyCommand(command, e);
            }
        }
    }

    private void copyStackToLocal() {
        this.localStackCopy = new LinkedList<>();
        int backStackEntryCount = this.fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackEntryCount; i++) {
            this.localStackCopy.add(this.fragmentManager.getBackStackEntryAt(i).getName());
        }
    }

    /* access modifiers changed from: protected */
    public void applyCommand(Command command) {
        if (command instanceof Forward) {
            activityForward((Forward) command);
        } else if (command instanceof Replace) {
            activityReplace((Replace) command);
        } else if (command instanceof BackTo) {
            backTo((BackTo) command);
        } else if (command instanceof Back) {
            fragmentBack();
        }
    }

    /* access modifiers changed from: protected */
    public void activityForward(Forward forward) {
        AppScreen appScreen = (AppScreen) forward.getScreen();
        Intent activityIntent = appScreen.getActivityIntent(this.activity);
        if (activityIntent != null) {
            checkAndStartActivity(appScreen, activityIntent, createStartActivityOptions(forward, activityIntent));
        } else {
            fragmentForward(forward);
        }
    }

    /* access modifiers changed from: protected */
    public void fragmentForward(Forward forward) {
        AppScreen appScreen = (AppScreen) forward.getScreen();
        Fragment createFragment = createFragment(appScreen);
        FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
        setupFragmentTransaction(forward, this.fragmentManager.findFragmentById(this.containerId), createFragment, beginTransaction);
        beginTransaction.replace(this.containerId, createFragment).addToBackStack(appScreen.getScreenKey()).commit();
        this.localStackCopy.add(appScreen.getScreenKey());
    }

    /* access modifiers changed from: protected */
    public void fragmentBack() {
        if (this.localStackCopy.size() > 0) {
            this.fragmentManager.popBackStack();
            this.localStackCopy.removeLast();
            return;
        }
        activityBack();
    }

    /* access modifiers changed from: protected */
    public void activityBack() {
        this.activity.finish();
    }

    /* access modifiers changed from: protected */
    public void activityReplace(Replace replace) {
        AppScreen appScreen = (AppScreen) replace.getScreen();
        Intent activityIntent = appScreen.getActivityIntent(this.activity);
        if (activityIntent != null) {
            checkAndStartActivity(appScreen, activityIntent, createStartActivityOptions(replace, activityIntent));
            this.activity.finish();
            return;
        }
        fragmentReplace(replace);
    }

    /* access modifiers changed from: protected */
    public void fragmentReplace(Replace replace) {
        AppScreen appScreen = (AppScreen) replace.getScreen();
        Fragment createFragment = createFragment(appScreen);
        if (this.localStackCopy.size() > 0) {
            this.fragmentManager.popBackStack();
            this.localStackCopy.removeLast();
            FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
            setupFragmentTransaction(replace, this.fragmentManager.findFragmentById(this.containerId), createFragment, beginTransaction);
            beginTransaction.replace(this.containerId, createFragment).addToBackStack(appScreen.getScreenKey()).commit();
            this.localStackCopy.add(appScreen.getScreenKey());
            return;
        }
        FragmentTransaction beginTransaction2 = this.fragmentManager.beginTransaction();
        setupFragmentTransaction(replace, this.fragmentManager.findFragmentById(this.containerId), createFragment, beginTransaction2);
        beginTransaction2.replace(this.containerId, createFragment).commit();
    }

    /* access modifiers changed from: protected */
    public void backTo(BackTo backTo) {
        if (backTo.getScreen() == null) {
            backToRoot();
            return;
        }
        String screenKey = backTo.getScreen().getScreenKey();
        int indexOf = this.localStackCopy.indexOf(screenKey);
        int size = this.localStackCopy.size();
        if (indexOf != -1) {
            for (int i = 1; i < size - indexOf; i++) {
                this.localStackCopy.removeLast();
            }
            this.fragmentManager.popBackStack(screenKey, 0);
            return;
        }
        backToUnexisting((AppScreen) backTo.getScreen());
    }

    private void backToRoot() {
        this.fragmentManager.popBackStack((String) null, 1);
        this.localStackCopy.clear();
    }

    private void checkAndStartActivity(AppScreen appScreen, Intent intent, Bundle bundle) {
        if (intent.resolveActivity(this.activity.getPackageManager()) != null) {
            this.activity.startActivity(intent, bundle);
        } else {
            unexistingActivity(appScreen, intent);
        }
    }

    /* access modifiers changed from: protected */
    public Fragment createFragment(AppScreen appScreen) {
        Fragment fragment = appScreen.getFragment();
        if (fragment != null) {
            return fragment;
        }
        errorWhileCreatingScreen(appScreen);
        throw new RuntimeException("Can't create a screen: " + appScreen.getScreenKey());
    }

    /* access modifiers changed from: protected */
    public void backToUnexisting(AppScreen appScreen) {
        backToRoot();
    }

    /* access modifiers changed from: protected */
    public void errorOnApplyCommand(Command command, RuntimeException runtimeException) {
        throw runtimeException;
    }
}
