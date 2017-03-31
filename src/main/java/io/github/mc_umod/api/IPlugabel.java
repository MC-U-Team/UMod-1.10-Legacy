package io.github.mc_umod.api;

import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface IPlugabel {
	
	/**
	 * Connection API for Pipes
	 * 
	 * @param w
	 * @param p
	 * @return
	 */
	public boolean canConnect(IBlockAccess w, BlockPos p);
	
}
