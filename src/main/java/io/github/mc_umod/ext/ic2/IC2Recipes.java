package io.github.mc_umod.ext.ic2;

import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import io.github.mc_umod.UBlocks;
import io.github.mc_umod.UItems;
import io.github.mc_umod.enumtype.EnumTypeBaseStuff;
import io.github.mc_umod.ext.abs.AbstractRecipeRegistry;
import net.minecraft.item.ItemStack;

public class IC2Recipes extends AbstractRecipeRegistry {
	
	@Override
	public void register() {
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(UBlocks.ores, 1, i)), null, false, new ItemStack(UItems.dusts, 1, i));
		}
	}
	
}
