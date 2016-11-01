package net.hycrafthd.umod.ext.ic2;

import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.minecraft.tileentity.TileEntity;

public class TileEntityIC2Transformer extends TileEntity implements IPowerProvieder{

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
	
}
