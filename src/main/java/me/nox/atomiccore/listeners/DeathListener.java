package me.nox.atomiccore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class DeathListener implements Listener {

    private final Map<Material, String> customDeathReasons = new HashMap<Material, String>() {{
        put(Material.DIAMOND_SWORD, "a fierce battle ⚔️");
        put(Material.NETHERITE_SWORD, "a fierce battle ⚔️");
        put(Material.IRON_SWORD, "a fierce battle ⚔️");
        put(Material.GOLDEN_SWORD, "a fierce battle ⚔️");
        put(Material.WOODEN_SWORD, "a fierce battle ⚔️");
        put(Material.STONE_SWORD, "a fierce battle ⚔️");
        put(Material.BOW, "an arrow to the knee 🏹");
        put(Material.CROSSBOW, "an arrow to the knee 🏹");
        put(Material.TRIDENT, "a powerful trident 🌊");
        put(Material.POTION, "a magical spell ✨");
        put(Material.LINGERING_POTION, "a magical spell ✨");
        put(Material.SPLASH_POTION, "a magical spell ✨");
        // Add more mappings as needed
    }};

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player attacker = victim.getKiller();

        if (attacker != null) {
            ItemStack weapon = attacker.getInventory().getItemInMainHand();
            Material weaponType = weapon.getType();

            String reason = customDeathReasons.getOrDefault(weaponType, "an unknown force");

            String deathMessage = ChatColor.DARK_RED + "☠ " + ChatColor.RED + victim.getName() +
                    ChatColor.WHITE + " was killed by " + ChatColor.DARK_RED + attacker.getName() +
                    ChatColor.WHITE + " using " + ChatColor.GOLD + reason;

            event.setDeathMessage(deathMessage);
        }
    }
}