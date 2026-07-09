package com.nettakrim.client_execution;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import java.util.Iterator;
import java.util.function.BiConsumer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.server.level.ServerPlayer;

public class ClientExecutionCommand implements Command<CommandSourceStack> {
    private final BiConsumer<ServerPlayer, String> onRun;

    public ClientExecutionCommand(RootCommandNode<CommandSourceStack> root, String name, BiConsumer<ServerPlayer, String> onRun) {
        this.onRun = onRun;

        LiteralCommandNode<CommandSourceStack> clientExecutionNode = Commands
                .literal(name)
                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(
                        Commands.argument("targets", EntityArgument.players())
                                .then(
                                        Commands.argument("command", MessageArgument.message())
                                                .executes(this)
                                )
                )
                .build();

        root.addChild(clientExecutionNode);
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String command = MessageArgument.getMessage(context, "command").getString();

        int i = 0;

        for(Iterator<ServerPlayer> targets = EntityArgument.getPlayers(context, "targets").iterator(); targets.hasNext(); ++i) {
            ServerPlayer player = targets.next();
            onRun.accept(player, command);
        }

        return i;
    }
}
