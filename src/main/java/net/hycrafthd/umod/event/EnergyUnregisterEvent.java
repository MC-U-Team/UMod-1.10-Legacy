package net.hycrafthd.umod.event;

import net.hycrafthd.umod.api.energy.ICabel;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EnergyUnregisterEvent extends Event{
	
	protected final ICabel cab;
	
	public EnergyUnregisterEvent(ICabel cab) {
		this.cab = cab;
	}
	
}
