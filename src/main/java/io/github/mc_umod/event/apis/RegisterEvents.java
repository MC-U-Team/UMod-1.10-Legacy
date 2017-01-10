package io.github.mc_umod.event.apis;

import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class RegisterEvents extends Event{
	
	protected final World worldObj;
	protected final BlockPos pos;
	
	public RegisterEvents(World worldIn,BlockPos pos) {
		this.worldObj = worldIn;
		this.pos = pos;
	}
	
}
