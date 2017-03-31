package io.github.mc_umod.ext.ic2;

import ic2.api.energy.event.*;
import ic2.api.energy.tile.*;
import io.github.mc_umod.api.energy.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraftforge.common.*;

public class TileEntityIC2Transformer extends TileEntity implements IPowerProvieder,IEnergySource,IEnergySink,ITickable{
	
	private double power,maxpower = 150000.0;
	
	public TileEntityIC2Transformer() {
	}

	@Override
	public double getStoredPower() {
		return this.power;
	}
	
	@Override
	public void addPower(double power) {
		this.power += power;
	}
	
	@Override
	public double getPower(double powerneed) {
		this.power -= powerneed;
		return powerneed;
	}
	
	@Override
	public double getMaximalPower() {
		return maxpower;
	}
	
	@Override
	public boolean isWorking() {
		return true;
	}
	
	@Override
	public String getErrorMessage() {
		return null;
	}
	
	@Override
	public boolean hasPower() {
		return power > 0;
	}
	
	@Override
	public double getIOPower() {
		return 0;
	}
	
	@Override
	public void setEnergy(double coun) {
		this.power = coun;
	}
	
	@Override
	public boolean needsPower() {
		return UE_TO_EU;
	}
	
	@Override
	public boolean productsPower() {
		return !UE_TO_EU;
	}
	
	private boolean UE_TO_EU = true;
	
	@Override
	public boolean isInput() {
		return true;
	}
	
	@Override
	public boolean isOutput() {
		return true;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
		return UE_TO_EU;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return !UE_TO_EU;
	}

	@Override
	public double getDemandedEnergy() {
		return maxpower - power;
	}

	@Override
	public int getSinkTier() {
		return Integer.MAX_VALUE;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		if(amount < maxpower - power){
			this.addPower(amount*10);
			return 0;
		}else{
			double p = maxpower - power;
			this.addPower(p*10);
			return amount - p;
		}
	}

	@Override
	public double getOfferedEnergy() {
		return power;
	}

	@Override
	public void drawEnergy(double amount) {
		this.getPower(amount*10);
	}

	@Override
	public int getSourceTier() {
		return Integer.MAX_VALUE;
	}
	
	private boolean firsttick = true;
	
	@Override
	public void update() {
		if(firsttick && !worldObj.isRemote){
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			firsttick = false;
		}
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		if(!worldObj.isRemote)
		MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
	}
	
}
