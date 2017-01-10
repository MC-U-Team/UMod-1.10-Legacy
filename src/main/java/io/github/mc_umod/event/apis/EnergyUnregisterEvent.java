package io.github.mc_umod.event.apis;

import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class EnergyUnregisterEvent extends RegisterEvents{

	public EnergyUnregisterEvent(World worldIn, BlockPos pos) {
		super(worldIn, pos);
	}
	
}
