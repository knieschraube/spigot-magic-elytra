package de.knieschraube.spigot;

import de.knieschraube.spigot.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import static de.knieschraube.spigot.configuration.Constants.*;

public class MagicElytra extends JavaPlugin implements Listener {

    private ManaUtils manaUtils;
    private ScoreboardUtils scoreboardUtils;
    private PlayerManipulation playerManipulation;
    private PlayerDelay playerDelay;

    @Override
    public void onEnable() {
        super.onEnable();
        manaUtils = ManaUtils.getInstance();
        scoreboardUtils = ScoreboardUtils.getInstance();
        playerManipulation = PlayerManipulation.getInstance();
        playerDelay = PlayerDelay.getInstance();

        ElytraUtils.addRecipies();

        getServer().getPluginManager().registerEvents(this, this);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            manaUtils.increaseManaForAllPlayers();
            scoreboardUtils.updatePlayerScoreboardForAllPlayer();
        }, 20, 20);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void onMove(PlayerMoveEvent move) {
        Player player = move.getPlayer();

        if (ElytraUtils.hasElytra(player) && PlayerManipulation.isNotOnGround(player)) {
            if (!playerDelay.isDelayed(player)) {
                if (player.isSneaking()) {
                    if(manaUtils.reducePlayerMana(player, FUEL_COST)) {
                        playerManipulation.boostPlayer(player);
                        playerDelay.increaseDelay(player);
                        scoreboardUtils.updatePlayerScoreboard(player);
                    } else {
                        player.getWorld().playSound(player.getLocation(), SOUND_RELOAD, SOUND_VOLUME_ELYTRA, SOUND_PICH_ELYTRA);
                    }
                }
            } else {
                player.getWorld().spawnParticle(PARTICLE_ELYTRA, player.getLocation(), PARTICLE_COUNT_ELYTRA, PARTICLE_FLY_DX, PARTICLE_FLY_DY, PARTICLE_FLY_DZ, PARTICLE_FLY_SPEED);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(!manaUtils.containsPlayer(player)) {
            manaUtils.setMana(player, 100);
        }
        scoreboardUtils.updatePlayerScoreboard(player);
    }
}