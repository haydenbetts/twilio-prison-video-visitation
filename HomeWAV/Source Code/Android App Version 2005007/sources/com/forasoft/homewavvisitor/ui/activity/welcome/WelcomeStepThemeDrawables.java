package com.forasoft.homewavvisitor.ui.activity.welcome;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeStepThemeDrawables;", "", "active", "", "inactive", "(II)V", "getActive", "()I", "getInactive", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: WelcomeStepper.kt */
public final class WelcomeStepThemeDrawables {
    private final int active;
    private final int inactive;

    public static /* synthetic */ WelcomeStepThemeDrawables copy$default(WelcomeStepThemeDrawables welcomeStepThemeDrawables, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = welcomeStepThemeDrawables.active;
        }
        if ((i3 & 2) != 0) {
            i2 = welcomeStepThemeDrawables.inactive;
        }
        return welcomeStepThemeDrawables.copy(i, i2);
    }

    public final int component1() {
        return this.active;
    }

    public final int component2() {
        return this.inactive;
    }

    public final WelcomeStepThemeDrawables copy(int i, int i2) {
        return new WelcomeStepThemeDrawables(i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WelcomeStepThemeDrawables)) {
            return false;
        }
        WelcomeStepThemeDrawables welcomeStepThemeDrawables = (WelcomeStepThemeDrawables) obj;
        return this.active == welcomeStepThemeDrawables.active && this.inactive == welcomeStepThemeDrawables.inactive;
    }

    public int hashCode() {
        return (this.active * 31) + this.inactive;
    }

    public String toString() {
        return "WelcomeStepThemeDrawables(active=" + this.active + ", inactive=" + this.inactive + ")";
    }

    public WelcomeStepThemeDrawables(int i, int i2) {
        this.active = i;
        this.inactive = i2;
    }

    public final int getActive() {
        return this.active;
    }

    public final int getInactive() {
        return this.inactive;
    }
}
