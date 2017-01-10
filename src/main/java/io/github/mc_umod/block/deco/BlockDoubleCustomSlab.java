package io.github.mc_umod.block.deco;

import net.minecraft.block.*;
import net.minecraft.block.state.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class BlockDoubleCustomSlab extends BlockCustomSlab {
	
	BlockDoubleCustomSlab(IBlockState modelState, Block slab, String name) {
		super(modelState, slab);
		this.setUnlocalizedName(name);
	}
	
	@Override
	public boolean isDouble() {
		return true;
	}
	
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return null;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
}
