package com.nettakrim.client_execution;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record CommandPayload(String string) implements CustomPayload {
    public static final CustomPayload.Id<CommandPayload> PACKET_ID = new CustomPayload.Id<>(ExecutionNetwork.executeClient);
    public static final PacketCodec<RegistryByteBuf, CommandPayload> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            CommandPayload::string,
            CommandPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

    public static void register() {
        PayloadTypeRegistry.playS2C().register(PACKET_ID, PACKET_CODEC);
    }
}
