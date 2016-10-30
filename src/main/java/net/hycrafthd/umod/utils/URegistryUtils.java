package net.hycrafthd.umod.utils;

import net.hycrafthd.corelib.registry.*;
import net.hycrafthd.umod.block.*;
import net.hycrafthd.umod.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.*;
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
	
	public static void registerEntity() {
		
	}
}
