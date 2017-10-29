package de.knieschraube.spigot.utils;


import de.knieschraube.spigot.configuration.Constants;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlayerDelay {

    private static PlayerDelay instance = new PlayerDelay();

    private Map<String, Long> playerDelays = Collections.synchronizedMap(new HashMap<>());

    public static PlayerDelay getInstance() {
        return instance;
    }

    private PlayerDelay() {}

    public void increaseDelay(Player player) {
        playerDelays.put(player.getName(), System.currentTimeMillis() + (Constants.BOOST_DELAY));
    }


    public boolean isDelayed(Player player) {
        return playerDelays.containsKey(player.getName()) && playerDelays.get(player.getName()) > System.currentTimeMillis();

    }

    public void removeDelay(Player player) {
        if (playerDelays.containsKey(player.getName())) {
            playerDelays.remove(player.getName());
        }
    }
}
