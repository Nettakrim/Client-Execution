package com.nettakrim.client_execution;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ExecutionNetwork {
    public static Identifier executeClient = Identifier.of(ClientExecution.modid, "execute_client");

    public static void onExecuteClient(ServerPlayerEntity player, String command) {
        PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
        packet.writeString(command);

        ServerPlayNetworking.send(player, executeClient, packet);
    }
}
