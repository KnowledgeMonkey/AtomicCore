package me.nox.atomiccore.Server;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.lang.reflect.Field;

public class ScoreboardUtil {

    public static void createScoreboard(Player player, JavaPlugin plugin) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective objective = board.registerNewObjective("AtomicNetwork", "dummy", ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Atomic Network");
        objective.setDisplaySlot(org.bukkit.scoreboard.DisplaySlot.SIDEBAR);

        Score line6 = objective.getScore(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "---------------");
        Score line5 = objective.getScore(ChatColor.AQUA + "Server Info");
        Score line4 = objective.getScore(ChatColor.DARK_AQUA + "⇩ ⇩ ⇩ ⇩ ⇩");
        Score line3 = objective.getScore(ChatColor.YELLOW + "Tps: " + ChatColor.LIGHT_PURPLE + String.format("%.2f", getServerTPS()));
        Score line2 = objective.getScore(ChatColor.YELLOW + "Online Players: " + ChatColor.LIGHT_PURPLE + Bukkit.getOnlinePlayers().size());
        Score line1 = objective.getScore(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "---------------");

        line6.setScore(6);
        line5.setScore(5);
        line4.setScore(4);
        line3.setScore(3);
        line2.setScore(2);
        line1.setScore(1);

        player.setScoreboard(board);
    }

    private static double getServerTPS() {
        try {
            Object minecraftServer = Class.forName("org.bukkit.craftbukkit.v1_20_R1.CraftServer")
                    .getDeclaredMethod("getServer")
                    .invoke(Bukkit.getServer());
            Field tpsField = minecraftServer.getClass().getDeclaredField("recentTps");
            tpsField.setAccessible(true);
            double[] tps = (double[]) tpsField.get(minecraftServer);
            return tps[0]; // Get the 1-minute TPS value
        } catch (Exception e) {
            e.printStackTrace();
            return 20.0; // Default TPS value if reflection fails
        }
    }
}
