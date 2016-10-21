package net.hycrafthd.umod.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

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
	
}
