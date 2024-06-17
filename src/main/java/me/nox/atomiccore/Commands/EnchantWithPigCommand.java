package me.nox.atomiccore.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class EnchantWithPigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("enchantwithpig")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou currently don't have access to this permission!"));
            return true;
        }

        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (heldItem == null) {
            player.sendMessage(ChatColor.RED + "You must be holding an item to enchant.");
            return true;
        }

        ItemMeta meta = heldItem.getItemMeta();
        meta.setLore(Collections.singletonList(ChatColor.GOLD + "Pig Shooter I"));
        heldItem.setItemMeta(meta);

        player.sendMessage(ChatColor.GREEN + "Your item has been enchanted with Pig Shooter I!");

        return true;
    }
}
