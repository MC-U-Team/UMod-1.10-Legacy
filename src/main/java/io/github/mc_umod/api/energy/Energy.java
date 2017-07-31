package io.github.mc_umod.api.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

public class Energy implements IEnergyStorage {

	private int max_energy, stored_energy;
	private boolean can_extract, can_receive;
	private BlockPos pos;
	private World world;

	public Energy(int max_energy, boolean can_extract, boolean can_receive) {
		this.max_energy = max_energy;
		this.can_extract = can_extract;
		this.can_receive = can_receive;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int amount = 0;
		if (this.stored_energy + maxReceive <= this.max_energy) {
			amount = maxReceive;
		} else {
			amount = this.max_energy - this.stored_energy;
		}
		if (!simulate) {
			this.stored_energy += amount;
		}
		return amount;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int amount = 0;
		if (this.stored_energy - maxExtract >= 0) {
			amount = maxExtract;
		} else {
			amount = this.stored_energy;
		}
		if (!simulate) {
			this.stored_energy -= amount;
		}
		return amount;
	}

	@Override
	public int getEnergyStored() {
		return this.stored_energy;
	}

	@Override
	public int getMaxEnergyStored() {
		return this.max_energy;
	}

	@Override
	public boolean canExtract() {
		return this.can_extract && this.stored_energy > 0;
	}

	@Override
	public boolean canReceive() {
		return this.can_receive && this.stored_energy < this.max_energy;
	}

	public void writeToNBT(NBTTagCompound comp) {
		comp.setInteger("energy", this.stored_energy);
	}

	public void readFromNBT(NBTTagCompound comp, World w) {
		this.stored_energy = comp.getInteger("energy");
		this.pos = new BlockPos(comp.getInteger("x"), comp.getInteger("y"), comp.getInteger("z"));
		this.world = w;
	}

}
