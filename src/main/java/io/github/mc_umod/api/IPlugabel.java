package io.github.mc_umod.api;

import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface IPlugabel {
	
	public boolean canConnect(IBlockAccess w, BlockPos p);
	
}
