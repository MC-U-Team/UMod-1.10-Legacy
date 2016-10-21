package net.hycrafthd.umod.utils;

import net.hycrafthd.corelib.registry.BlockRegistry;
import net.hycrafthd.corelib.registry.ItemRegistry;
import net.hycrafthd.corelib.registry.OreDictionaryRegistry;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.block.BlockDoubleCustomSlab;
import net.hycrafthd.umod.block.BlockHalfCustomSlab;
import net.hycrafthd.umod.block.BlockSlabCreator;
import net.hycrafthd.umod.item.ItemBlockBase;
import net.hycrafthd.umod.item.ItemBlockCustomSlab;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class URegistryUtils {
	
	public static double getDistanceAtoB(double x1, double z1, double x2, double z2) {
		double dx = x1 - x2;
		double dz = z1 - z2;
		return Math.sqrt((dx * dx + dz * dz));
	}
	
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

	public static void registerEntity(){
		
	}
}
