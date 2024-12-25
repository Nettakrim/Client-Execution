package com.nettakrim.client_execution_plugin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class ClientExecution extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("executeclient").setExecutor(new ExecuteClientCommandExecutor(this));
        getServer().getMessenger().registerOutgoingPluginChannel(this, "client_execution:execute_client");
    }

    public void sendClientExecution(Player player, String command) {
        byte[] packetBuf = getStringBuf(command);

        player.sendPluginMessage(this, "client_execution:execute_client", packetBuf);
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
