package com.nettakrim.client_execution;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.MessageArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Iterator;
import java.util.function.BiConsumer;

public class ClientExecutionCommand implements Command<ServerCommandSource> {
    private final BiConsumer<ServerPlayerEntity, String> onRun;

    public ClientExecutionCommand(RootCommandNode<ServerCommandSource> root, String name, BiConsumer<ServerPlayerEntity, String> onRun) {
        this.onRun = onRun;

        LiteralCommandNode<ServerCommandSource> clientExecutionNode = CommandManager
                .literal(name)
                .requires((source) -> source.hasPermissionLevel(2))
                .then(
                        CommandManager.argument("targets", EntityArgumentType.players())
                                .then(
                                        CommandManager.argument("command", MessageArgumentType.message())
                                                .executes(this)
                                )
                )
                .build();

        root.addChild(clientExecutionNode);
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        String command = MessageArgumentType.getMessage(context, "command").getString();

        int i = 0;

        for(Iterator<ServerPlayerEntity> targets = EntityArgumentType.getPlayers(context, "targets").iterator(); targets.hasNext(); ++i) {
            ServerPlayerEntity player = targets.next();
            onRun.accept(player, command);
        }

        return i;
    }
}
