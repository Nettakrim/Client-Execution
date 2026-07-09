package com.nettakrim.client_execution;

import com.nettakrim.client_execution.payloads.CommandPayload;
import com.nettakrim.client_execution.payloads.LockPayload;
import com.nettakrim.client_execution.payloads.UnlockPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

public class ExecutionNetwork {
    public static Identifier executeClient = Identifier.fromNamespaceAndPath(ClientExecution.modid, "execute_client");
    public static Identifier lockCommand = Identifier.fromNamespaceAndPath(ClientExecution.modid, "lock_command");
    public static Identifier unlockCommand = Identifier.fromNamespaceAndPath(ClientExecution.modid, "unlock_command");


    public static void onExecuteClient(ServerPlayer player, String command) {
        ServerPlayNetworking.send(player, new CommandPayload(command));
    }

    public static void onLockCommand(ServerPlayer player, String command) {
        ServerPlayNetworking.send(player, new LockPayload(command));
    }

    public static void onUnlockCommand(ServerPlayer player, String command) {
        ServerPlayNetworking.send(player, new UnlockPayload(command));
    }
}
