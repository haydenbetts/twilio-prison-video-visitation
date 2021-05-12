package ru.terrakok.cicerone;

import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;

public class Router extends BaseRouter {
    public void navigateTo(Screen screen) {
        executeCommands(new Forward(screen));
    }

    public void newRootScreen(Screen screen) {
        executeCommands(new BackTo((Screen) null), new Replace(screen));
    }

    public void replaceScreen(Screen screen) {
        executeCommands(new Replace(screen));
    }

    public void backTo(Screen screen) {
        executeCommands(new BackTo(screen));
    }

    public void newChain(Screen... screenArr) {
        int length = screenArr.length;
        Command[] commandArr = new Command[length];
        for (int i = 0; i < length; i++) {
            commandArr[i] = new Forward(screenArr[i]);
        }
        executeCommands(commandArr);
    }

    public void newRootChain(Screen... screenArr) {
        int i = 1;
        Command[] commandArr = new Command[(screenArr.length + 1)];
        commandArr[0] = new BackTo((Screen) null);
        if (screenArr.length > 0) {
            commandArr[1] = new Replace(screenArr[0]);
            while (i < screenArr.length) {
                int i2 = i + 1;
                commandArr[i2] = new Forward(screenArr[i]);
                i = i2;
            }
        }
        executeCommands(commandArr);
    }

    public void finishChain() {
        executeCommands(new BackTo((Screen) null), new Back());
    }

    public void exit() {
        executeCommands(new Back());
    }
}
