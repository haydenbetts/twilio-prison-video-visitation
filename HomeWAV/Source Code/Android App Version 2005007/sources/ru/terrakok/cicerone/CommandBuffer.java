package ru.terrakok.cicerone;

import java.util.LinkedList;
import java.util.Queue;
import ru.terrakok.cicerone.commands.Command;

class CommandBuffer implements NavigatorHolder {
    private Navigator navigator;
    private Queue<Command[]> pendingCommands = new LinkedList();

    CommandBuffer() {
    }

    public void setNavigator(Navigator navigator2) {
        this.navigator = navigator2;
        while (!this.pendingCommands.isEmpty() && navigator2 != null) {
            executeCommands(this.pendingCommands.poll());
        }
    }

    public void removeNavigator() {
        this.navigator = null;
    }

    /* access modifiers changed from: package-private */
    public void executeCommands(Command[] commandArr) {
        Navigator navigator2 = this.navigator;
        if (navigator2 != null) {
            navigator2.applyCommands(commandArr);
        } else {
            this.pendingCommands.add(commandArr);
        }
    }
}
