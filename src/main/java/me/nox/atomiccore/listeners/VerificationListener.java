package me.nox.atomiccore.listeners;

import me.nox.atomiccore.AtomicCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VerificationListener implements Listener {

    private final AtomicCore plugin;
    private final Map<UUID, Boolean> playerVerificationStatus = new HashMap<>();
    private final Map<UUID, Integer> playerRandomSlot = new HashMap<>();
    private final Map<UUID, Inventory> playerVerifyGUI = new HashMap<>();

    public VerificationListener(AtomicCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (!playerVerificationStatus.getOrDefault(playerUUID, false)) {
                verify(player);
                player.openInventory(playerVerifyGUI.get(playerUUID));
            }
        }, 5L);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        Inventory inventory = event.getInventory();

        if (inventory.equals(playerVerifyGUI.get(playerUUID))) {
            event.setCancelled(true);
            int clickedSlot = event.getSlot();

            if (clickedSlot == playerRandomSlot.get(playerUUID)) {
                playerVerificationStatus.put(playerUUID, true);
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + "You have successfully been verified. Have a good time on " + ChatColor.DARK_PURPLE + "AtomicNetwork!");
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) return;

        Player player = (Player) event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Inventory inventory = event.getInventory();

        if (inventory.equals(playerVerifyGUI.get(playerUUID))) {
            if (!playerVerificationStatus.getOrDefault(playerUUID, false)) {
                player.kickPlayer(ChatColor.RED + "Please " + ChatColor.DARK_RED + ChatColor.BOLD + "verify" + ChatColor.RED + " if you want to play.");
            }
        }
    }

    private void verify(Player player) {
        UUID playerUUID = player.getUniqueId();
        int randomSlot = (int) (Math.random() * 9);
        playerRandomSlot.put(playerUUID, randomSlot);

        Inventory verifyGUI = Bukkit.createInventory(null, 9, ChatColor.RED + "" + ChatColor.BOLD + "Anti Bot Verification");

        for (int i = 0; i < 9; i++) {
            if (i == randomSlot) {
                ItemStack greenPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                ItemMeta meta = greenPane.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName(ChatColor.GREEN + "Click to verify");
                    greenPane.setItemMeta(meta);
                }
                verifyGUI.setItem(i, greenPane);
            } else {
                verifyGUI.setItem(i, new ItemStack(Material.AIR));
            }
        }

        playerVerifyGUI.put(playerUUID, verifyGUI);
    }
}