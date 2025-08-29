package com.nettakrim.client_execution_plugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class ClientExecution extends JavaPlugin {

    @Override
    public void onEnable() {
        register("executeclient", "client_execution:execute_client");
        register("commandlock", "client_execution:lock_command");
        register("commandunlock", "client_execution:unlock_command");
    }

    private void register(String name, String packet) {
        getCommand(name).setExecutor(new ClientExecutionCommandExecutor(name, (player, command) -> player.sendPluginMessage(this, packet, getStringBuf(command))));
        getServer().getMessenger().registerOutgoingPluginChannel(this, packet);
    }

    private byte[] getStringBuf(String s) {
        byte[] utf = s.getBytes(StandardCharsets.UTF_8);
        byte[] varInt = getVarInt(utf.length);
        byte[] buf = new byte[varInt.length+utf.length];
        System.arraycopy(varInt, 0, buf, 0, varInt.length);
        System.arraycopy(utf, 0, buf, varInt.length, utf.length);
        return buf;
    }

    private byte[] getVarInt(int i) {
        List<Byte> buf = new ArrayList<>();

        while((i & -128) != 0) {
            buf.add((byte)(i & 127 | 128));
            i >>>= 7;
        }

        buf.add((byte)i);

        byte[] bytes = new byte[buf.size()];
        for (int j = 0; j < bytes.length; j++) {
            bytes[j] = buf.get(j);
        }
        return bytes;
    }
}
