package net.hycrafthd.umod.event.apis;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnergyRegisterEvent extends RegisterEvents{

	public EnergyRegisterEvent(World worldIn, BlockPos pos) {
		super(worldIn, pos);
	}

}
