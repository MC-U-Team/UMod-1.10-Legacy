package io.github.mc_umod.util;

import java.util.ArrayList;

import io.github.mc_umod.api.crafting.*;
import net.minecraft.item.ItemStack;

public class ModRegistryUtils {
	
	private static ArrayList<PulverizerRecepie> list = new ArrayList<PulverizerRecepie>();
	private static ArrayList<CraftSmeltRecepie> craftlist = new ArrayList<CraftSmeltRecepie>();
	private static ArrayList<CraftSmeltRecepieShapless> craftlist2 = new ArrayList<CraftSmeltRecepieShapless>();
		
	public static void addPulverRecipe(PulverizerRecepie re) {
		list.add(re);
	}
	
	public static ItemStack[] isRecipe(ItemStack rec) {
		if (rec == null) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			PulverizerRecepie re = list.get(i);
			if (re.getInput().isItemEqual(rec)) {
				return new ItemStack[] { re.getOutput(), re.getSecondOutput(), re.getRandomSecondoutput() };
			}
		}
		return null;
	}
	
	public static void addCraftSmeltRecipe(CraftSmeltRecepie re) {
		craftlist.add(re);
	}
	
	public static void addCraftSmeltRecipeShapless(CraftSmeltRecepieShapless re) {
		craftlist2.add(re);
	}
}
