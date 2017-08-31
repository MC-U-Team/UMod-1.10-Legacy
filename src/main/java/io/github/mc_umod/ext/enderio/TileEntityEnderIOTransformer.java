package io.github.mc_umod.ext.enderio;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityEnderIOTransformer extends TileEntity implements IEnergyStorage {

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
		return false;
	}

	@Override
	public boolean canReceive() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
