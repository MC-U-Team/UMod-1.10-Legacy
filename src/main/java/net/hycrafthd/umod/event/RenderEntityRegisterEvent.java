package net.hycrafthd.umod.event;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;


public class RenderEntityRegisterEvent extends Event {
	
	protected final BlockPos pos;
	protected final World worldObj;
	
	public RenderEntityRegisterEvent(BlockPos pos,World w) {
		this.pos = pos;
		this.worldObj = w;
	}
}
