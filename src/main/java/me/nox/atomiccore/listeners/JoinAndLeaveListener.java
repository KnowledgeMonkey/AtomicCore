package me.nox.atomiccore.listeners;

import me.nox.atomiccore.Server.ScoreboardUtil;
import me.nox.atomiccore.AtomicCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinAndLeaveListener implements Listener {

    private final AtomicCore plugin;

    public JoinAndLeaveListener(AtomicCore plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        Player player = e.getPlayer();

        e.setQuitMessage(ChatColor.DARK_GRAY +"[" + ChatColor.DARK_GREEN + "AtomicNetwork" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " » " + ChatColor.YELLOW + player.getDisplayName() + " has left AtomicNetwork");


    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        new BukkitRunnable(){
            @Override
            public void run() {
                if (e.getPlayer().isOnline()) {
                    ScoreboardUtil.createScoreboard(e.getPlayer(), plugin);
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);


        Player player = e.getPlayer();
        e.setJoinMessage(ChatColor.DARK_GRAY +"[" + ChatColor.DARK_GREEN + "AtomicNetwork" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY +  " » " + ChatColor.YELLOW + player.getDisplayName() + " has joined AtomicNetwork");

    }





}
