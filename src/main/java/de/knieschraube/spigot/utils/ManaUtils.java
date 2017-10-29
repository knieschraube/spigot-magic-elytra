package de.knieschraube.spigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ManaUtils {

    private static ManaUtils instance = new ManaUtils();
    private Map<String, Integer> manaMap = Collections.synchronizedMap(new HashMap<String, Integer>());

    public static ManaUtils getInstance() {
        return instance;
    }

    public boolean reducePlayerMana(Player player, int cost) {
        String playerName = player.getName();
        if(manaMap.containsKey(playerName) && manaMap.get(playerName) >= cost) {
            manaMap.put(playerName, manaMap.get(playerName) - cost);
            return true;
        }

        return false;
    }

    public void increaseManaForAllPlayers() {
        manaMap.forEach((playerName, mana) -> {
            if(mana >= 0 && mana < 100) {
                manaMap.put(playerName, mana+1);
                Player player = Bukkit.getServer().getPlayer(playerName);
            }
        });
    }

    public boolean containsPlayer(Player player) {
        return manaMap.containsKey(player.getName());
    }

    public int getMana(Player player) {
        return manaMap.get(player.getName());
    }

    public void setMana(Player player, int mana) {
        manaMap.put(player.getName(), mana);
    }

    public void removePlayer(Player player) {
        manaMap.remove(player.getName());
    }
}