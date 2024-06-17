package me.nox.atomiccore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){

            Player p = (Player) sender;

            if (args.length == 0){
                if (p.isInvulnerable()){
                    p.setInvulnerable(false);
                    p.sendMessage(ChatColor.RED + "Godmod disabled!");
                } else {
                    p.setInvulnerable(true);
                    p.sendMessage(ChatColor.GREEN + "Godmode enabled!");

            }
            }else{
                if (args.length == 1){
                    Player player = Bukkit.getServer().getPlayer(args[0]);
                    if(player.isInvulnerable()){
                        player.setInvulnerable(false);
                        player.sendMessage(ChatColor.RED + "Godmod disabled!");
                        p.sendMessage(ChatColor.GREEN + "Godmode enabled for " + player.getDisplayName());


                    }else {
                        if(!player.isInvulnerable()){
                            player.setInvulnerable(true);
                            player.sendMessage(ChatColor.RED + "Godmod disabled!");
                            p.sendMessage(ChatColor.RED + "Godmod disabled for " + player.getDisplayName());
                        }
                    }

                }
            }


        }

        return true;
    }
}
