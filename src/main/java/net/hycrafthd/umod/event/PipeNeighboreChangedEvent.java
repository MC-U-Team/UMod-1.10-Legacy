package net.hycrafthd.umod.event;

import net.minecraft.world.World;

public class PipeNeighboreChangedEvent {
	
	private World world;
	
	public PipeNeighboreChangedEvent(World world) {
		this.world = world;
	}
	
}
