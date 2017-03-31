package io.github.mc_umod.api.energy;

import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface ICabel {
	
	public BlockPos getPos();
	
	public World getWorld();
				
	public int getTunnel();
		
	public void setTunnel(int i);
	
	public double getRate();
}
