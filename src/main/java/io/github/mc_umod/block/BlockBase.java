package io.github.mc_umod.block;

import io.github.mc_umod.UReference;
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
