package net.hycrafthd.umod.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

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
	
}
