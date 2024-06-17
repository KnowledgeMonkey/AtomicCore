package me.nox.atomiccore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameModeTabCompleter implements TabCompleter {

    private static final List<String> GAMEMODES = Arrays.asList("survival", "creative", "adventure", "spectator");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], GAMEMODES, new ArrayList<>());
        }
        return null;
    }
}
