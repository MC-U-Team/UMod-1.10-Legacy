package io.github.mc_umod.api.energy;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ICabel {
	
	public BlockPos getPos();
	
	public World getWorld();
				
	public int getTunnel();
		
	public void setTunnel(int i);
	
	public int getRate();
}
