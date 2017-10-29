package de.knieschraube.spigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import static de.knieschraube.spigot.configuration.Constants.SCOREBOARD_NAME;

public class ScoreboardUtils {

    private static ScoreboardUtils instance = new ScoreboardUtils();

    private ManaUtils manaUtils;

    private ScoreboardManager scoreboardManager;
    private Scoreboard sideScoreboard;
    private Objective sideScoreboardObjective;

    public static ScoreboardUtils getInstance() {
        return instance;
    }

    private ScoreboardUtils() {
        manaUtils = ManaUtils.getInstance();

        scoreboardManager = Bukkit.getScoreboardManager();

        sideScoreboard = scoreboardManager.getNewScoreboard();
        sideScoreboardObjective = createSideScoreboardObjective(sideScoreboard);
    }

    public Objective createSideScoreboardObjective(Scoreboard sideScoreboard){

        Objective objective = sideScoreboard.registerNewObjective("schowmana", "mana");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§a" + SCOREBOARD_NAME);

        return objective;
    }

    public void updatePlayerScoreboardForAllPlayer() {
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            updatePlayerScoreboard(player);
        });
    }

    public void updatePlayerScoreboard(Player player){
        if(manaUtils.containsPlayer(player)) {
            sideScoreboardObjective.getScore("Mana ").setScore(manaUtils.getMana(player));
        }
        if(ElytraUtils.hasElytra(player)) {
            sideScoreboardObjective.getScore("Elytra ").setScore(ElytraUtils.getHealth(player));
        }

        player.setScoreboard(sideScoreboard);
    }
}
