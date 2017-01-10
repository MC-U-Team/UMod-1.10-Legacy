package io.github.mc_umod.block;

import io.github.mc_umod.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.util.*;

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
