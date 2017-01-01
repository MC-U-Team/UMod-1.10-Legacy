package io.github.mc_umod.block.deco;

import net.minecraft.block.state.IBlockState;

public class BlockStairCreator {
	
	private BlockCustomStair stair;
	
	public BlockStairCreator(IBlockState modelState, String name) {
		stair = new BlockCustomStair(modelState, "stair_" + name);
	}
	
	public BlockCustomStair getStair() {
		return stair;
	}
	
}
