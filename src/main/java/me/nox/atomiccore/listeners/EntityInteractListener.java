package me.nox.atomiccore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class EntityInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (entity.getCustomName() != null && entity.getCustomName().equals(ChatColor.translateAlternateColorCodes('&', "&9Discord"))) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "<link:https://discord.gg/Uabh8TW88x>&9Click here for the server's Discord!<reset>"));
        }
    }
}
