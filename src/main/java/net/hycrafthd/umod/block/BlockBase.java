package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;

public abstract class BlockBase extends Block {
	
	
	public BlockBase(Material mat) {
		super(mat);
		this.setCreativeTab(UReference.blocks);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
}
