package net.hycrafthd.umod.event.apis;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnergyUnregisterEvent extends RegisterEvents{

	public EnergyUnregisterEvent(World worldIn, BlockPos pos) {
		super(worldIn, pos);
	}
	
}
