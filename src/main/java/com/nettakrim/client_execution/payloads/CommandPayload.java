package com.nettakrim.client_execution.payloads;

import com.nettakrim.client_execution.ExecutionNetwork;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jspecify.annotations.NonNull;

public record CommandPayload(String command) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<CommandPayload> PACKET_ID = new CustomPacketPayload.Type<>(ExecutionNetwork.executeClient);
    public static final StreamCodec<RegistryFriendlyByteBuf, CommandPayload> PACKET_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            CommandPayload::command,
            CommandPayload::new
    );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return PACKET_ID;
    }

    public static void register() {
        PayloadTypeRegistry.clientboundPlay().register(PACKET_ID, PACKET_CODEC);
    }
}
