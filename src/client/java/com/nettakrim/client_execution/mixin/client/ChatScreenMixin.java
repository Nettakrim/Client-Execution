package com.nettakrim.client_execution.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.nettakrim.client_execution.ClientExecutionClient;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    @WrapWithCondition(method = "handleChatInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;sendCommand(Ljava/lang/String;)V"))
    private boolean lockCommand(ClientPacketListener instance, String command) {
        for (String lock : ClientExecutionClient.commandLocks) {
            if (command.startsWith(lock)) {
                return false;
            }
        }

        return true;
    }
}
