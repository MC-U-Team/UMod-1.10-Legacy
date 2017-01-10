package io.github.mc_umod.event.apis;

import net.minecraft.util.math.*;
import net.minecraft.world.*;


public class RenderEntityClearEvent extends RegisterEvents {

	public RenderEntityClearEvent(World worldIn, BlockPos pos) {
		super(worldIn, pos);
	}
	
}
