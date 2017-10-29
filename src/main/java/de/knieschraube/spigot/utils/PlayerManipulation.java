package de.knieschraube.spigot.utils;

import de.knieschraube.spigot.configuration.Constants;
import de.knieschraube.spigot.model.ElytraRecipe;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import static de.knieschraube.spigot.configuration.Constants.*;

public class PlayerManipulation {

    private static PlayerManipulation instance = new PlayerManipulation();

    private ElytraUtils elytraUtils;
    private ManaUtils manaUtils;


    private PlayerManipulation() {
        elytraUtils = ElytraUtils.getInstance();
        manaUtils = ManaUtils.getInstance();
    }

    public static PlayerManipulation getInstance() {
        return instance;
    }

    public void boostPlayer(Player player){
        Vector pv = player.getLocation().getDirection();

        double factor = getElytraPowerFactor(player);

        Vector v = pv.multiply(Constants.STD_VELOCITY*factor);
        player.setVelocity(v);
        player.getWorld().playSound(player.getLocation(), SOUND_POWER, SOUND_VOLUME_POWER, SOUND_PICH_POWER);
        player.getWorld().spawnParticle(PARTICLE_POWER, player.getLocation(), PARTICLE_COUNT_POWER, PARTICLE_POWER_DX, PARTICLE_POWER_DY, PARTICLE_POWER_DZ, PARTICLE_POWER_SPEED);
    }

    private static double getElytraPowerFactor(Player player) {
        if(player != null && player.getInventory() != null) {
            ItemStack chestItem = player.getInventory().getChestplate();
            if(chestItem != null) {
                ItemMeta meta = chestItem.getItemMeta();
                if(meta != null){
                    String displayName = meta.getDisplayName();
                    if(displayName != null) {
                        for (ElytraRecipe recipe : Constants.ELYTRA_DEFINITIONS) {
                            if(displayName.equals(recipe.getDisplayName())){
                                return recipe.getVelocity();
                            }
                        }
                    }
                }
            }
        }

        return Constants.STD_BOOST_MULTIPLIER;
    }

    public static boolean isNotOnGround(Player player) {
        return !player.isFlying() && !((LivingEntity) player).isOnGround();
    }
}
