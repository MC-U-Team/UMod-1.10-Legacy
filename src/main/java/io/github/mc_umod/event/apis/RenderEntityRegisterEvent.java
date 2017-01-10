package io.github.mc_umod.event.apis;

import net.minecraft.util.math.*;
import net.minecraft.world.*;


public class RenderEntityRegisterEvent extends RegisterEvents {

	public RenderEntityRegisterEvent(World worldIn, BlockPos pos) {
		super(worldIn, pos);
	}
	
}
