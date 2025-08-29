package com.nettakrim.client_execution.payloads;

import com.nettakrim.client_execution.ExecutionNetwork;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record LockPayload(String command) implements CustomPayload {
    public static final Id<LockPayload> PACKET_ID = new Id<>(ExecutionNetwork.lockCommand);
    public static final PacketCodec<RegistryByteBuf, LockPayload> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            LockPayload::command,
            LockPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

    public static void register() {
        PayloadTypeRegistry.playS2C().register(PACKET_ID, PACKET_CODEC);
    }
}
