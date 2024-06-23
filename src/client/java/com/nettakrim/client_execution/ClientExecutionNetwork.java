package com.nettakrim.client_execution;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.Text;

public class ClientExecutionNetwork {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(ExecutionNetwork.executeClient, ((client, handler, buf, responseSender) -> {
            String command = buf.readString();
            client.execute(() -> {
                client.player.sendMessage(Text.literal(command));
            });
        }));
    }
}
