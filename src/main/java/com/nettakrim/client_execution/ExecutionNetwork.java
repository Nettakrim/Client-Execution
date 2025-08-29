package com.nettakrim.client_execution;

import com.nettakrim.client_execution.payloads.CommandPayload;
import com.nettakrim.client_execution.payloads.LockPayload;
import com.nettakrim.client_execution.payloads.UnlockPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ExecutionNetwork {
    public static Identifier executeClient = Identifier.of(ClientExecution.modid, "execute_client");
    public static Identifier lockCommand = Identifier.of(ClientExecution.modid, "lock_command");
    public static Identifier unlockCommand = Identifier.of(ClientExecution.modid, "unlock_command");


    public static void onExecuteClient(ServerPlayerEntity player, String command) {
        ServerPlayNetworking.send(player, new CommandPayload(command));
    }

    public static void onLockCommand(ServerPlayerEntity player, String command) {
        ServerPlayNetworking.send(player, new LockPayload(command));
    }

    public static void onUnlockCommand(ServerPlayerEntity player, String command) {
        ServerPlayNetworking.send(player, new UnlockPayload(command));
    }
}
