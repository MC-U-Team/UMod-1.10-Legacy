package net.hycrafthd.umod.block.deco;

import net.hycrafthd.umod.UReference;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;

public class BlockCustomStair extends BlockStairs {
	
	BlockCustomStair(IBlockState modelState, String name) {
		super(modelState);
		this.setUnlocalizedName(name);
		this.setCreativeTab(UReference.blocks);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
}
