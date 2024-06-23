package com.nettakrim.client_execution;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ClientExecutionNetwork {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(ExecutionNetwork.executeClient, ((client, handler, buf, responseSender) -> {
            String command = buf.readString();

            // could also use ClientCommandInternals.executeCommand() to directly execute a client command
            // but this way it can do both
            ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
            if (networkHandler != null) {
                client.execute(() -> networkHandler.sendCommand(command));
            }
        }));
    }
}
