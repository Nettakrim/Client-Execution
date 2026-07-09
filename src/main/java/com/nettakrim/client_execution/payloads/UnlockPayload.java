package com.nettakrim.client_execution.payloads;

import com.nettakrim.client_execution.ExecutionNetwork;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jspecify.annotations.NonNull;

public record UnlockPayload(String command) implements CustomPacketPayload {
    public static final Type<UnlockPayload> PACKET_ID = new Type<>(ExecutionNetwork.unlockCommand);
    public static final StreamCodec<RegistryFriendlyByteBuf, UnlockPayload> PACKET_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            UnlockPayload::command,
            UnlockPayload::new
    );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return PACKET_ID;
    }

    public static void register() {
        PayloadTypeRegistry.clientboundPlay().register(PACKET_ID, PACKET_CODEC);
    }
}
