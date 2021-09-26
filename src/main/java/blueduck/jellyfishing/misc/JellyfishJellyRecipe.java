package blueduck.jellyfishing.misc;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class JellyfishJellyRecipe {

	private final ItemStack input;
	private final int time;
	private final ItemStack output;

	public JellyfishJellyRecipe(ItemStack input, int time, ItemStack output) {
		this.input = input;
		this.time = time;
		this.output = output;
	}

	public ItemStack getOutput() {
		return output.copy();
	}


	public ItemStack getInput() {
		return input.copy();
	}

	public List<ItemStack> getIngredients() {
		List<ItemStack> ingredients = new ArrayList<>();
		ingredients.add(input);
		return ingredients;
	}

	@Override
	public String toString() {
		return "InfuserRecipe -> " +
				       "input=" + input +
						", time=" + time +
				       ", output=" + output
					;
	}
}
