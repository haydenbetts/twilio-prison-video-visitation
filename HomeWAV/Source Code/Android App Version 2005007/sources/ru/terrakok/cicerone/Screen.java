package ru.terrakok.cicerone;

public abstract class Screen {
    protected String screenKey = getClass().getCanonicalName();

    public String getScreenKey() {
        return this.screenKey;
    }
}
