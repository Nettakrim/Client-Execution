package com.nettakrim.client_execution;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ExecutionNetwork {
    public static Identifier executeClient = Identifier.of(ClientExecution.modid, "execute_client");

    public static void onExecuteClient(ServerPlayerEntity player, String command) {
        ServerPlayNetworking.send(player, new CommandPayload(command));
    }
}
