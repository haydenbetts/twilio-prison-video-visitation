package ru.terrakok.cicerone.commands;

import ru.terrakok.cicerone.Screen;

public class BackTo implements Command {
    private Screen screen;

    public BackTo(Screen screen2) {
        this.screen = screen2;
    }

    public Screen getScreen() {
        return this.screen;
    }
}
