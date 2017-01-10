package io.github.mc_umod.api.energy;

import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface ICabel {
	
	public BlockPos getPos();
	
	public World getWorld();
	
	public boolean isInput();
	
	public boolean isOutput();
	
	public String getEnergyClass();
	
	public void addToTunnel(ICabel cab);
	
	public ICabel[] getOutputsFromTunnel();
	
	public ICabel[] getInputsFromTunnel();
	
	public int getTunnel();
		
	public void setTunnel(int i);
	
	public double getRate();
	
	public BlockPos[] getInputs();
	
	public BlockPos[] getOutputs();
	
}
