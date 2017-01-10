package io.github.mc_umod.ext.enderio;

import crazypants.enderio.power.*;
import io.github.mc_umod.api.energy.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.energy.*;

public class TileEntityEnderIOTransformer extends TileEntity implements IPowerProvieder, IPowerStorage, IPowerApiAdapter, IPowerInterface, IEnergyStorage {
	
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
	public void addEnergy(int arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public IPowerStorage getController() {
		return this;
	}
	
	@Override
	public long getEnergyStoredL() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public long getMaxEnergyStoredL() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getMaxInput() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getMaxOutput() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isCreative() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isInputEnabled(EnumFacing arg0) {
		return true;
	}
	
	@Override
	public boolean isNetworkControlledIo(EnumFacing arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isOutputEnabled(EnumFacing arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getEnergyStored() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getMaxEnergyStored() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean canExtract() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean canReceive() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public Object getProvider() {
		// TODO Auto-generated method stub
		return this;
	}
	
	@Override
	public IEnergyStorage getCapability(ICapabilityProvider arg0, EnumFacing arg1) {
		return this;
	}
	
	@Override
	public IPowerInterface getPowerInterface(ICapabilityProvider arg0, EnumFacing arg1) {
		return this;
	}
	
}
