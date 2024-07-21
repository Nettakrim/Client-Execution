package com.nettakrim.client_execution;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ClientExecutionNetwork {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(CommandPayload.PACKET_ID, ((payload, context) -> {
            if (context.client() == null) {
                return;
            }
            String command = payload.string();

            // could also use ClientCommandInternals.executeCommand() to directly execute a client command
            // but this way it can do both (and doing so would break connector)
            ClientPlayNetworkHandler networkHandler = context.client().getNetworkHandler();
            if (networkHandler != null) {
                context.client().execute(() -> networkHandler.sendChatCommand(command));
            }
        }));
    }
}
