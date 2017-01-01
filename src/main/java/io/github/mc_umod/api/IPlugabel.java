package io.github.mc_umod.api;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface IPlugabel {
	
	public boolean canConnect(IBlockAccess w, BlockPos p);
	
}
