package me.nox.atomiccore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class BanGUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            ArrayList<Player> list = new ArrayList<>(p.getServer().getOnlinePlayers());

            Inventory bangui = Bukkit.createInventory(p, 45, ChatColor.BLUE + "Player List");

            for (int j = 0; j < 45; j++){

                ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
                ItemMeta fillermeta = filler.getItemMeta();

                fillermeta.setDisplayName(ChatColor.BLACK + " ");

                bangui.setItem(j,filler);
            }


            for (int i = 0; i < list.size(); i++){

                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                ItemMeta meta = playerHead.getItemMeta();



                meta.setDisplayName(list.get(i).getDisplayName());
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.GOLD + "PLayer Health: " + ChatColor.RED + list.get(i).getHealth());
                lore.add(ChatColor.GOLD + "EXP: " + ChatColor.AQUA + list.get(i).getExp());
                meta.setLore(lore);


                playerHead.setItemMeta(meta);

                bangui.setItem(i,playerHead);

            }



            p.openInventory(bangui);
        }

        return true;
    }
}
