package net.hycrafthd.umod.ext.ic2;

import ic2.api.recipe.*;
import net.hycrafthd.umod.*;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.ext.abs.AbstractRecipeRegistry;
import net.minecraft.item.ItemStack;

public class IC2Recipes extends AbstractRecipeRegistry {
	
	@Override
	public void register() {
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(UBlocks.ores, 1, i)), null, false, new ItemStack(UItems.dusts, 1, i));
		}
	}
	
}
