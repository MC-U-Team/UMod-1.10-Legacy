package io.github.mc_umod.block.deco;

import io.github.mc_umod.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;
import net.minecraft.util.*;

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
