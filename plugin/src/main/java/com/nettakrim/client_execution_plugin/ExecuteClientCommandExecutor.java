package com.nettakrim.client_execution_plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ExecuteClientCommandExecutor implements CommandExecutor {
    public ClientExecution plugin;

    public ExecuteClientCommandExecutor(ClientExecution plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getLabel().equalsIgnoreCase("executeclient")) {
            if (args.length < 2) {
                commandSender.sendMessage("not enough arguments! /executeclient <player> <command>");
                return false;
            }

            String playerName = args[0];

            Player player = commandSender.getServer().getPlayer(playerName);
            if (player != null) {
                StringBuilder s = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    if (i > 1) s.append(' ');
                    s.append(args[i]);
                }
                plugin.sendClientExecution(player, s.toString());
            }
            plugin.getLogger().info(commandSender+" "+command+" "+Arrays.toString(args));
        }

        return true;
    }
}
