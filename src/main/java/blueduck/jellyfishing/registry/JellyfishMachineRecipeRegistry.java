package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.misc.JellyfishJellyRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;

/**
 * Registry for Infusion Table Recipes.
 * You can add, remove, search for recipes using the methods below.
 * Works even at runtime, to let you add recipes for some challenges, remove some from other mods and so on.
 * */

public class JellyfishMachineRecipeRegistry {

	public static final  ArrayList<JellyfishJellyRecipe> recipes = new ArrayList<>();

	private static void fillWithBuiltInRecipes() {
		addInfuserRecipe(new ItemStack(JellyfishingItems.JELLYFISH.get()), 20, new ItemStack(JellyfishingItems.JELLYFISH_JELLY.get()));
	}

	private static JellyfishJellyRecipe createInfuserRecipe(ItemStack input, int time, ItemStack output) {
		return new JellyfishJellyRecipe(input, time, output);
	}

	public static synchronized void addInfuserRecipe(ItemStack input, int time, ItemStack output) {
		JellyfishJellyRecipe toAdd = createInfuserRecipe(input, time, output);
		recipes.add(toAdd);
	}

	public static void removeRecipe(ItemStack input, int essenceAmount, ItemStack secondaryMaterials) {
		JellyfishJellyRecipe toRemove = searchRecipe(input);
		recipes.remove(toRemove);
	}

	public static JellyfishJellyRecipe searchRecipe(ItemStack inputStack) {
		if (!inputStack.isEmpty()) {
			for (JellyfishJellyRecipe recipe : recipes) {
				ItemStack input = recipe.getInput();
				if (input.isItemEqual(inputStack) && inputStack.getCount() >= input.getCount()) {
					return recipe;
				}
			}
		}
		return null;
	}

	public static boolean isValidInput(ItemStack stack) {
		if (!stack.isEmpty()) {
			for (JellyfishJellyRecipe recipe : recipes) {
				if (recipe.getInput().isItemEqual(stack)) {
					return true;
				}
			}
		}
		return false;
	}



	public static boolean isInfused(ItemStack stack) {
		if (!stack.isEmpty()) {
			for (JellyfishJellyRecipe recipe : recipes) {
				if (recipe.getOutput().isItemEqual(stack)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void initRegistry() {
		fillWithBuiltInRecipes();
	}
}
