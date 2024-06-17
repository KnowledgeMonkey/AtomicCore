package me.nox.atomiccore;

import me.nox.atomiccore.Commands.*;
import me.nox.atomiccore.Hub.DamageListener;
import me.nox.atomiccore.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class AtomicCore extends JavaPlugin {


    private static AtomicCore plugin;


    //Colorcodes for the Chatmessages

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";



    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        getLogger().info(ANSI_GREEN + "--------------------" + ANSI_RESET);
        getLogger().info(ANSI_GREEN + "AtomicCore loaded" + ANSI_RESET);
        getLogger().info(ANSI_GREEN + "Made by DuckyS3npai" + ANSI_RESET);
        getLogger().info(ANSI_GREEN + "--------------------" + ANSI_RESET);

        getServer().getPluginManager().registerEvents(new JoinAndLeaveListener(this), this);
        getLogger().info(ANSI_CYAN + "Loaded JoinAndLeaveListener Events" + ANSI_RESET);

        getServer().getPluginManager().registerEvents(new ProjectileHitListener(), this);
        getLogger().info(ANSI_CYAN + "Loaded ProjectileHitListener Events" + ANSI_RESET);

        getServer().getPluginManager().registerEvents(new EntityInteractListener(), this);
        getLogger().info(ANSI_CYAN + "Loaded EntityInteractListener Events" + ANSI_RESET);

        getServer().getPluginManager().registerEvents(new VerificationListener(this), this);
        getLogger().info(ANSI_CYAN + "Loaded VerificationListener Events" + ANSI_RESET);

        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getLogger().info(ANSI_CYAN + "Loaded DeathListener Events" + ANSI_RESET);

        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getLogger().info(ANSI_CYAN + "Loaded Hub DamageListener successfully" + ANSI_RESET);

        getLogger().info(ANSI_YELLOW + "All Events were loaded successfully!" + ANSI_RESET);

        getServer().getPluginManager().registerEvents(new BanInventoryListener(), this);


        //Loading Commands

        getCommand("discord").setExecutor(new DiscordCommand());
        getLogger().info(ANSI_CYAN + "Loaded discord command successfully" + ANSI_RESET);


        getCommand("god").setExecutor(new GodCommand());
        getLogger().info(ANSI_CYAN + "Loaded Godmode command successfully" + ANSI_RESET);

        getCommand("bangui").setExecutor(new BanGUICommand());
        getLogger().info(ANSI_CYAN + "Loaded Bangui command successfully" + ANSI_RESET);

        getCommand("enchantwithpig").setExecutor(new EnchantWithPigCommand());
        getLogger().info(ANSI_CYAN + "Loaded Enchantwithpig command successfully" + ANSI_RESET);

        getCommand("gamemode").setExecutor(new GameModeCommand(this));
        getCommand("gamemode").setTabCompleter(new GameModeTabCompleter());
        getLogger().info(ANSI_CYAN + "Loaded Gamemode and its tab completion command successfully" + ANSI_RESET);

        getLogger().info(ANSI_YELLOW + "All commands were loaded successfully!" + ANSI_RESET);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AtomicCore getPlugin(){
        return plugin;
    }
}
