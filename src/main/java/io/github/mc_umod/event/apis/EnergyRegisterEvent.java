package io.github.mc_umod.event.apis;

import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class EnergyRegisterEvent extends RegisterEvents{

	public EnergyRegisterEvent(World worldIn, BlockPos pos) {
		super(worldIn, pos);
	}

}
