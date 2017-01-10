package io.github.mc_umod.utils;

import io.github.mc_umod.block.deco.BlockDoubleCustomSlab;
import io.github.mc_umod.block.deco.BlockHalfCustomSlab;
import io.github.mc_umod.block.deco.BlockSlabCreator;
import io.github.mc_umod.item.block.ItemBlockBase;
import io.github.mc_umod.item.block.ItemBlockCustomSlab;
import net.hycrafthd.corelib.registry.BlockRegistry;
import net.hycrafthd.corelib.registry.ItemRegistry;
import net.hycrafthd.corelib.registry.OreDictionaryRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class URegistryUtils {
	
	public static void registerItem(Item item) {
		ItemRegistry.register(item, item.getUnlocalizedName().substring(5));
		OreDictionaryRegistry.register(item);
	}
	
	public static void registerBlock(Block block) {
		registerBlock(block, ItemBlockBase.class);
	}
	
	public static void registerBlock(Block block, Class<? extends ItemBlock> bl) {
		BlockRegistry.register(block, bl, block.getUnlocalizedName().substring(5));
		OreDictionaryRegistry.register(block);
	}
	
	public static void registerHalfSlabs(BlockSlabCreator creator) {
		registerHalfSlabs(creator, ItemBlockCustomSlab.class);
	}
	
	public static void registerHalfSlabs(BlockSlabCreator creator, Class<? extends ItemBlockCustomSlab> bl) {
		BlockHalfCustomSlab slab = creator.getSlab();
		BlockDoubleCustomSlab doubleSlab = creator.getDoubleslab();
		GameRegistry.registerBlock(slab, bl, slab.getUnlocalizedName().substring(5), slab, doubleSlab);
		GameRegistry.registerBlock(doubleSlab, null, doubleSlab.getUnlocalizedName().substring(5), slab, doubleSlab);
		OreDictionaryRegistry.register(slab);
	}
	
	public static void registerEntity() {
		
	}
}
