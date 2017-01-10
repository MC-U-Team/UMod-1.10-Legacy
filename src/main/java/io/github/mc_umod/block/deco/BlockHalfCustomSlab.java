package io.github.mc_umod.block.deco;

import net.minecraft.block.state.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class BlockHalfCustomSlab extends BlockCustomSlab {
	
	BlockHalfCustomSlab(IBlockState modelState, String name) {
		super(modelState, null);
		this.setUnlocalizedName(name);
	}
	
	@Override
	public boolean isDouble() {
		return false;
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
