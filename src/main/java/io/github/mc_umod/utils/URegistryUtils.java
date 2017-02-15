package io.github.mc_umod.utils;

import io.github.mc_umod.block.deco.*;
import io.github.mc_umod.corelib.*;
import io.github.mc_umod.corelib.core.*;
import io.github.mc_umod.item.block.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.registry.*;

public class URegistryUtils {
	
	public static void registerItem(Item item) {
		CoreLib.getInstance().getCommonRegistry().registerItem(item, item.getUnlocalizedName().substring(5));
		CoreLib.getInstance().getCommonRegistry().registerOreDictionary(item,item.getUnlocalizedName().substring(5));
	}
	
	public static void registerBlock(Block block) {
		registerBlock(block, ItemBlockBase.class);
	}
	
	public static void registerBlock(Block block, Class<? extends ItemBlock> bl) {
		CoreLib.getInstance().getCommonRegistry().registerBlock(block, block.getUnlocalizedName().substring(5));
		CoreLib.getInstance().getCommonRegistry().registerOreDictionary(block,block.getUnlocalizedName().substring(5));
	}
	
	public static void registerHalfSlabs(BlockSlabCreator creator) {
		registerHalfSlabs(creator, ItemBlockCustomSlab.class);
	}
	
	public static void registerHalfSlabs(BlockSlabCreator creator, Class<? extends ItemBlockCustomSlab> bl) {
		BlockHalfCustomSlab slab = creator.getSlab();
		BlockDoubleCustomSlab doubleSlab = creator.getDoubleslab();
		GameRegistry.registerBlock(slab, bl, slab.getUnlocalizedName().substring(5), slab, doubleSlab);
		GameRegistry.registerBlock(doubleSlab, null, doubleSlab.getUnlocalizedName().substring(5), slab, doubleSlab);
		CoreLib.getInstance().getCommonRegistry().registerOreDictionary(slab,slab.getUnlocalizedName().substring(5));
	}
	
	public static void registerEntity() {
		
	}
}
