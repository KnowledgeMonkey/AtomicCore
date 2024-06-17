package me.nox.atomiccore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GameModeCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public GameModeCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(ChatColor.GRAY + "/gamemode <gamemode> [player]");
            return true;
        }

        String gamemodeArg = args[0].toLowerCase();
        Player target = player;

        if (args.length > 1) {
            if (!player.hasPermission("gamemode.other")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou currently don't have access to this permission!"));
                return true;
            }
            target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "Player not found.");
                return true;
            }
        }

        GameMode gameMode;
        switch (gamemodeArg) {
            case "survival":
            case "s":
            case "0":
                gameMode = GameMode.SURVIVAL;
                break;
            case "creative":
            case "c":
            case "1":
                gameMode = GameMode.CREATIVE;
                break;
            case "adventure":
            case "a":
            case "2":
                gameMode = GameMode.ADVENTURE;
                break;
            case "spectator":
            case "sp":
            case "3":
                gameMode = GameMode.SPECTATOR;
                break;
            default:
                player.sendMessage(ChatColor.RED + "Invalid gamemode.");
                return true;
        }

        target.setGameMode(gameMode);
        target.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.YELLOW + gameMode.name() + ChatColor.GRAY + "!");
        if (target != player) {
            player.sendMessage(ChatColor.GRAY + "You have set " + ChatColor.AQUA + target.getName() + ChatColor.GRAY + "'s gamemode to " + ChatColor.YELLOW + gameMode.name() + ChatColor.GRAY + "!");
        }

        return true;
    }
}
