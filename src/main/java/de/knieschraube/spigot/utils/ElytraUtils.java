package de.knieschraube.spigot.utils;

import de.knieschraube.spigot.configuration.Constants;
import de.knieschraube.spigot.model.ElytraRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class ElytraUtils {

    private static ElytraUtils instance = new ElytraUtils();

    public static ElytraUtils getInstance() {
        return instance;
    }

	private ElytraUtils() {}

	private static Optional<ItemStack> getChestplate(Player player) {
		return Optional.ofNullable(player.getInventory().getChestplate());
	}

	public static boolean hasElytra(Player player) {
		return getChestplate(player).isPresent() && getChestplate(player).get().getType().equals(Material.ELYTRA);
	}
	
	public static int getHealth(Player player) {
    	if(hasElytra(player)) {
			int maxDurability = Material.ELYTRA.getMaxDurability();
			return (100 * (maxDurability-player.getInventory().getChestplate().getDurability()))/maxDurability;
		}

		return 0;
	}

	private static ShapedRecipe createElytraRecipe(ElytraRecipe elytraRecipe) {
		ItemStack elytra = new ItemStack(Material.ELYTRA);

		ItemMeta elytraMeta = elytra.getItemMeta();
		elytraMeta.setDisplayName(elytraRecipe.getDisplayName());
		elytra.setItemMeta(elytraMeta);

		ShapedRecipe recipe = new ShapedRecipe(elytra);
		recipe.shape("LLL", "FMF", "FLF");
		recipe.setIngredient('M', elytraRecipe.getBaseMaterial());
		recipe.setIngredient('F', elytraRecipe.getWingsMaterial());

		return recipe;
	}

    public static void addRecipies() {
    	Constants.ELYTRA_DEFINITIONS.stream()
				.map(ElytraUtils::createElytraRecipe)
				.forEach(Bukkit::addRecipe);
    }
}