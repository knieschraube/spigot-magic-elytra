package de.knieschraube.spigot.configuration;

import de.knieschraube.spigot.model.ElytraRecipe;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final List<ElytraRecipe> ELYTRA_DEFINITIONS = new ArrayList<ElytraRecipe>() {{
        add(ElytraRecipe.newBuilder()
                .baseMaterial(Material.IRON_INGOT)
                .wingsMaterial(Material.FEATHER)
                .displayName("Die Fliege")
                .velocity(1.1)
                .build());
        add(ElytraRecipe.newBuilder()
                .baseMaterial(Material.GOLD_INGOT)
                .wingsMaterial(Material.FEATHER)
                .displayName("Der Zaunkönig")
                .velocity(1.25)
                .build());
        add(ElytraRecipe.newBuilder()
                .baseMaterial(Material.EMERALD)
                .wingsMaterial(Material.FEATHER)
                .displayName("Der Uhu")
                .velocity(1.5)
                .build());
        add(ElytraRecipe.newBuilder()
                .baseMaterial(Material.DIAMOND)
                .wingsMaterial(Material.FEATHER)
                .displayName("Der majestätische Adler")
                .velocity(1.75)
                .build());
        add(ElytraRecipe.newBuilder()
                .baseMaterial(Material.ENDER_PEARL)
                .wingsMaterial(Material.FEATHER)
                .displayName("Das Photon")
                .velocity(3)
                .build());
    }};

    public static final String SCOREBOARD_NAME = "Status";

    public static final int BOOST_DELAY = 300;
    public static final float STD_VELOCITY = 1.3F;
    public static final int FUEL_COST = 8;

    public final static double STD_BOOST_MULTIPLIER = 0.9;

    public static final Particle PARTICLE_ELYTRA = Particle.valueOf("CLOUD");
    public static final Particle PARTICLE_POWER = Particle.valueOf("FLAME");
    public static final Sound SOUND_POWER = Sound.valueOf("ITEM_FIRECHARGE_USE");
    public static final Sound SOUND_RELOAD = Sound.valueOf("BLOCK_FIRE_EXTINGUISH");

    public static final float SOUND_VOLUME_POWER = 1.0F;
    public static final float SOUND_VOLUME_ELYTRA = 1.0F;

    public static final float SOUND_PICH_POWER = 1.0F;
    public static final float SOUND_PICH_ELYTRA = 1.0F;

    public static final int PARTICLE_COUNT_POWER = 16;
    public static final float PARTICLE_POWER_DX = 0.1F;
    public static final float PARTICLE_POWER_DY = 0.1F;
    public static final float PARTICLE_POWER_DZ = 0.1F;
    public static final float PARTICLE_POWER_SPEED = 0.15F;

    public static final int PARTICLE_COUNT_ELYTRA = 3;
    public static final float PARTICLE_FLY_DX = 0.1F;
    public static final float PARTICLE_FLY_DY = 0.1F;
    public static final float PARTICLE_FLY_DZ = 0.1F;
    public static final float PARTICLE_FLY_SPEED = 0.1F;
}
