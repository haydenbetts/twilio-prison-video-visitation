package ru.terrakok.cicerone;

import ru.terrakok.cicerone.commands.Command;

public abstract class BaseRouter {
    private CommandBuffer commandBuffer = new CommandBuffer();

    /* access modifiers changed from: package-private */
    public CommandBuffer getCommandBuffer() {
        return this.commandBuffer;
    }

    /* access modifiers changed from: protected */
    public void executeCommands(Command... commandArr) {
        this.commandBuffer.executeCommands(commandArr);
    }
}
