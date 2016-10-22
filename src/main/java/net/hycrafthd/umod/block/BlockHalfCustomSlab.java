package net.hycrafthd.umod.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;

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
