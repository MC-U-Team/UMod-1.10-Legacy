package net.hycrafthd.umod.ext.abs;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.oredict.OreDictionary;

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
