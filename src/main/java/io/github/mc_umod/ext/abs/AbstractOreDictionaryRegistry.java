package io.github.mc_umod.ext.abs;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraftforge.oredict.*;

public abstract class AbstractOreDictionaryRegistry {
	
	public abstract void register();
	
	public void registerOre(String name, Block block) {
		OreDictionary.registerOre(name, block);
	}
	
	public void registerOre(String name, Item item) {
		OreDictionary.registerOre(name, item);
	}
	
	public void registerOre(String name, ItemStack stack) {
		OreDictionary.registerOre(name, stack);
	}
	
}
