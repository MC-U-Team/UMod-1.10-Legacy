package net.hycrafthd.umod.ext.ic2;

import net.hycrafthd.umod.*;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.ext.abs.AbstractOreDictionaryRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.oredict.OreDictionary;

public class IC2OreDictionary extends AbstractOreDictionaryRegistry {
	
	@Override
	public void register() {
		// Ores
		registerOre("oreCopper", new ItemStack(UBlocks.ores, 1, EnumTypeBaseStuff.COPPER.getMetadata()));
		registerOre("oreLead", new ItemStack(UBlocks.ores, 1, EnumTypeBaseStuff.LEAD.getMetadata()));
		registerOre("oreSilver", new ItemStack(UBlocks.ores, 1, EnumTypeBaseStuff.SILVER.getMetadata()));
		registerOre("oreTin", new ItemStack(UBlocks.ores, 1, EnumTypeBaseStuff.TIN.getMetadata()));
		registerOre("oreUranium", new ItemStack(UBlocks.ores, 1, EnumTypeBaseStuff.URAN.getMetadata()));
		
		// Dusts
		registerOre("dustStone", new ItemStack(UItems.cdust));
		registerOre("dustCopper", new ItemStack(UItems.dusts, 1, EnumTypeBaseStuff.COPPER.getMetadata()));
		registerOre("dustLead", new ItemStack(UItems.dusts, 1, EnumTypeBaseStuff.LEAD.getMetadata()));
		registerOre("dustSilver", new ItemStack(UItems.dusts, 1, EnumTypeBaseStuff.SILVER.getMetadata()));
		registerOre("dustTin", new ItemStack(UItems.dusts, 1, EnumTypeBaseStuff.TIN.getMetadata()));
		
		// Ingots
		registerOre("ingotCopper", new ItemStack(UItems.ingots, 1, EnumTypeBaseStuff.COPPER.getMetadata()));
		registerOre("ingotLead", new ItemStack(UItems.ingots, 1, EnumTypeBaseStuff.LEAD.getMetadata()));
		registerOre("ingotSilver", new ItemStack(UItems.ingots, 1, EnumTypeBaseStuff.SILVER.getMetadata()));
		registerOre("ingotTin", new ItemStack(UItems.ingots, 1, EnumTypeBaseStuff.TIN.getMetadata()));
		
		// Blocks
		registerOre("blockCopper", new ItemStack(UBlocks.blocks, 1, EnumTypeBaseStuff.COPPER.getMetadata()));
		registerOre("blockLead", new ItemStack(UBlocks.blocks, 1, EnumTypeBaseStuff.LEAD.getMetadata()));
		registerOre("blockTin", new ItemStack(UBlocks.blocks, 1, EnumTypeBaseStuff.TIN.getMetadata()));
		registerOre("blockUranium", new ItemStack(UBlocks.blocks, 1, EnumTypeBaseStuff.URAN.getMetadata()));
	}
	
}
