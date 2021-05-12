package ru.terrakok.cicerone;

import ru.terrakok.cicerone.commands.Command;

public interface Navigator {
    void applyCommands(Command[] commandArr);
}
