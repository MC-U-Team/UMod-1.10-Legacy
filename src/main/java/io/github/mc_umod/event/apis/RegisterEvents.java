package io.github.mc_umod.event.apis;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class RegisterEvents extends Event{
	
	public final World worldObj;
	public final BlockPos pos;
	
	public RegisterEvents(World worldIn,BlockPos pos) {
		this.worldObj = worldIn;
		this.pos = pos;
	}
	
}
