package com.nettakrim.client_execution;

import com.mojang.brigadier.tree.RootCommandNode;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

public class Commands {
    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            RootCommandNode<ServerCommandSource> root = dispatcher.getRoot();

            ExecuteClientCommand.registerNode(root);
        });
    }
}
