package net.hycrafthd.umod.ext.ic2;

import ic2.api.recipe.*;
import net.hycrafthd.corelib.util.ItemStackUtil;
import net.hycrafthd.umod.*;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.ext.IUmodExtension;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.oredict.OreDictionary;

public class IC2ModExtension implements IUmodExtension {
	
	@Override
	public void preinit(FMLPreInitializationEvent evt) {
		
	}
	
	@Override
	public void init(FMLInitializationEvent evt) {
		
	}
	
	@Override
	public void postinit(FMLPostInitializationEvent evt) {
		registerOre("oreCopper", new ItemStack(UBlocks.ores, 1, EnumTypeBaseStuff.COPPER.getMetadata()));
		registerOre("oreLead", new ItemStack(UBlocks.ores, 1, EnumTypeBaseStuff.LEAD.getMetadata()));
		registerOre("oreTin", new ItemStack(UBlocks.ores, 1, EnumTypeBaseStuff.TIN.getMetadata()));
		registerOre("oreUranium", new ItemStack(UBlocks.ores, 1, EnumTypeBaseStuff.URAN.getMetadata()));
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(UBlocks.ores, 1, i)), null, false, new ItemStack(UItems.dusts, 1, i));
		}
	}
	
	@Override
	public void serverstarting(FMLServerStartingEvent evt) {
		
	}
	
	@Override
	public void clientRegistery() {
		
	}
	
	private void registerOre(String name, Block block) {
		OreDictionary.registerOre(name, block);
	}
	
	private void registerOre(String name, Item item) {
		OreDictionary.registerOre(name, item);
	}
	
	private void registerOre(String name, ItemStack stack) {
		OreDictionary.registerOre(name, stack);
	}
	
}
