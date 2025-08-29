package com.nettakrim.client_execution_plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public record ClientExecutionCommandExecutor(String name, BiConsumer<Player, String> onRun) implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getLabel().equalsIgnoreCase(name)) {
            if (args.length < 2) {
                commandSender.sendMessage("not enough arguments! /executeclient <player> <command>");
                return false;
            }

            String playerName = args[0];

            Player[] players;
            if (playerName.startsWith("@")) {
                players = commandSender.getServer().getOnlinePlayers().toArray(new Player[0]);
            } else {
                players = new Player[]{commandSender.getServer().getPlayer(playerName)};
            }

            StringBuilder s = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                if (i > 1) s.append(' ');
                s.append(args[i]);
            }
            String commandString = s.toString();

            for (Player player : players) {
                if (player != null) {
                    onRun.accept(player, commandString);
                }
            }
        }

        return true;
    }
}
