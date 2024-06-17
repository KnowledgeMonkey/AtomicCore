package me.nox.atomiccore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;


public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile.getShooter() instanceof Player) {
            Player shooter = (Player) projectile.getShooter();
            // Check if the shooter is in the "Hub" world
            if (shooter.getWorld().getName().equalsIgnoreCase("Hub")) {
                // Delete the projectile
                projectile.remove();

                // Save shooter's pitch and yaw
                float pitch = shooter.getLocation().getPitch();
                float yaw = shooter.getLocation().getYaw();

                // Teleport shooter to the projectile's location
                Location projectileLocation = projectile.getLocation();
                shooter.teleport(projectileLocation);

                // Set shooter's pitch and yaw
                Location shooterLocation = shooter.getLocation();
                shooterLocation.setPitch(pitch);
                shooterLocation.setYaw(yaw);
                shooter.teleport(shooterLocation);
            }
        }
    }
}