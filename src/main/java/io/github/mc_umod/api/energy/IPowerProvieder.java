package io.github.mc_umod.api.energy;

import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface IPowerProvieder {
	
	public double getStoredPower();
	
	public void addPower(double power);
	
	public double getPower(double powerneed);
	
	public double getMaximalPower();
	
	public boolean isWorking();
	
	public String getErrorMessage();
	
	public boolean hasPower();
	
	public double getPowerProducNeeds();
	
	public BlockPos getPos();
	
	public World getWorld();
	
	public void setEnergy(double coun);
	
	public boolean needsPower();
	
	public boolean productsPower();
	
	/**
	 * @return emits energy
	 */
	public boolean isInput();
	
	/**
	 * 
	 * @return takes energy
	 */
	public boolean isOutput();
}
