package io.github.mc_umod.event.apis;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class RenderEntityClearEvent extends RegisterEvents {

	public RenderEntityClearEvent(World worldIn, BlockPos pos) {
		super(worldIn, pos);
	}
	
}
