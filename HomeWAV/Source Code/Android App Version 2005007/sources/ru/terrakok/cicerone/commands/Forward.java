package ru.terrakok.cicerone.commands;

import ru.terrakok.cicerone.Screen;

public class Forward implements Command {
    private Screen screen;

    public Forward(Screen screen2) {
        this.screen = screen2;
    }

    public Screen getScreen() {
        return this.screen;
    }
}
