package net.hycrafthd.umod.ext.ic2;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.comp.Energy;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.tileentity.TileEntityBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityIC2Transformer extends TileEntity implements IPowerProvieder,IEnergyConductor,IEnergySource,IEnergySink{
		
	public TileEntityIC2Transformer() {
	}
	
	@Override
	public void onLoad() {
		if(!worldObj.isRemote)
		MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
	}
	
	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		if(!worldObj.isRemote)
		MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
	}
	
	@Override
	public double getStoredPower() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void addPower(double power) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public double getPower(double powerneed) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getMaximalPower() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isWorking() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean hasPower() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public double getPowerProducNeeds() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setEnergy(double coun) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean needsPower() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean productsPower() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isInput() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isOutput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
		return false;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return false;
	}

	@Override
	public double getConductionLoss() {
		return 0;
	}

	@Override
	public double getInsulationEnergyAbsorption() {
		return 0;
	}

	@Override
	public double getInsulationBreakdownEnergy() {
		return 0;
	}

	@Override
	public double getConductorBreakdownEnergy() {
		return 0;
	}

	@Override
	public void removeInsulation() {
		
	}

	@Override
	public void removeConductor() {
		
	}

	@Override
	public double getDemandedEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSinkTier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOfferedEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void drawEnergy(double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSourceTier() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
