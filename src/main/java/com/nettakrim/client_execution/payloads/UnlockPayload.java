package com.nettakrim.client_execution.payloads;

import com.nettakrim.client_execution.ExecutionNetwork;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record UnlockPayload(String command) implements CustomPayload {
    public static final Id<UnlockPayload> PACKET_ID = new Id<>(ExecutionNetwork.unlockCommand);
    public static final PacketCodec<RegistryByteBuf, UnlockPayload> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            UnlockPayload::command,
            UnlockPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

    public static void register() {
        PayloadTypeRegistry.playS2C().register(PACKET_ID, PACKET_CODEC);
    }
}
