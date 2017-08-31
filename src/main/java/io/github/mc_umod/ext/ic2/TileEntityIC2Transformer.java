package io.github.mc_umod.ext.ic2;

//import ic2.api.energy.event.*;
//import ic2.api.energy.tile.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityIC2Transformer extends TileEntity /*implements IEnergySource,IEnergySink,ITickable*/ {
	
	private double power,maxpower = 150000.0;
	
	public TileEntityIC2Transformer() {
	}

	/*@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
		return false;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return false;
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
		return 0;
	}

	@Override
	public double getOfferedEnergy() {
		return power;
	}

	@Override
	public void drawEnergy(double amount) {
		
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
	}*/
	
}
