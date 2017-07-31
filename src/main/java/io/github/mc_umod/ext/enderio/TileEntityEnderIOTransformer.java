package io.github.mc_umod.ext.enderio;

import crazypants.enderio.power.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityEnderIOTransformer extends TileEntity implements IPowerStorage, IPowerApiAdapter, IPowerInterface, IEnergyStorage {
	
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
