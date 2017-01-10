package io.github.mc_umod.ext.ic2;

import ic2.api.recipe.*;
import io.github.mc_umod.*;
import io.github.mc_umod.enumtype.*;
import io.github.mc_umod.ext.abs.*;
import net.minecraft.item.*;

public class IC2Recipes extends AbstractRecipeRegistry {
	
	@Override
	public void register() {
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(UBlocks.ores, 1, i)), null, false, new ItemStack(UItems.dusts, 1, i));
		}
	}
	
}
