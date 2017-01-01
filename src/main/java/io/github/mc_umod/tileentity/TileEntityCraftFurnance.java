package io.github.mc_umod.tileentity;

import io.github.mc_umod.api.energy.IPowerProvieder;
import io.github.mc_umod.gui.container.ContainerCraftFurnace;
import io.github.mc_umod.utils.ModRegistryUtils;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;

public class TileEntityCraftFurnance extends TileEntityBase implements IPowerProvieder, ITickable {
	
	public ItemStack[] stack = new ItemStack[11];
	public double energy,max_energy;
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return null;
	}
	
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return false;
	}
	
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 9;
	}
	
	@Override
	public int getSizeInventory() {
		return stack.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return stack[index];
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.stack[index] != null) {
			ItemStack itemstack;
			
			if (this.stack[index].stackSize <= count) {
				itemstack = this.stack[index];
				this.stack[index] = null;
				return itemstack;
			} else {
				itemstack = this.stack[index].splitStack(count);
				
				if (this.stack[index].stackSize == 0) {
					this.stack[index] = null;
				}
				
				return itemstack;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.stack[index] = stack;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}
	
	@Override
	public int getField(int id) {
		return 0;
	}
	
	@Override
	public void setField(int id, int value) {
	}
	
	@Override
	public int getFieldCount() {
		return 0;
	}
	
	@Override
	public void clear() {
		stack = new ItemStack[stack.length];
	}
	
	@Override
	public String getName() {
		return "tile.entity.craftfurnance";
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return new ContainerCraftFurnace(player, pos);
	}
	
	@Override
	public String getGuiID() {
		return "4";
	}
	
	private int time = 0;
	private boolean working;
	
	@Override
	public void update() {
		ItemStack stac = ModRegistryUtils.isCraftSmelt(new ItemStack[] { stack[0], stack[1], stack[2] }, new ItemStack[] { stack[3], stack[4], stack[5] }, new ItemStack[] { stack[6], stack[7], stack[8] });
		if (stac != null && this.energy > 150) {
			working = true;
			time++;
			if (time == 80) {
				this.energy -= 150;
				time = 0;
				if (stack[9] == null) {
					stack[9] = stac.copy();
				} else if (stack[9] != null && stack[9].stackSize < 64 && stack[9].isItemEqual(stac)) {
					stack[9].stackSize += stac.stackSize;
				}
				for (int i = 0; i < 9; i++) {
					if (stack[i].stackSize > 1) {
						stack[i].stackSize--;
					} else {
						stack[i] = null;
					}
				}
			}
		}else{
			working = false;
		}
	}
	
	@Override
	public double getPowerProducNeeds() {
		return 10;
	}
	
	@Override
	public boolean needsPower() {
		return true;
	}
	
	@Override
	public boolean productsPower() {
		return false;
	}
	
	@Override
	public boolean isInput() {
		return false;
	}
	
	@Override
	public boolean isOutput() {
		return true;
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (this.stack[index] != null) {
			ItemStack itemstack = this.stack[index];
			this.stack[index] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public double getStoredPower() {
		return energy;
	}

	@Override
	public void addPower(double power) {
		energy += power;
	}

	@Override
	public double getPower(double powerneed) {
		energy -= powerneed;
		return powerneed;
	}

	@Override
	public double getMaximalPower() {
		return max_energy;
	}

	@Override
	public boolean isWorking() {
		return this.working;
	}

	@Override
	public String getErrorMessage() {
		return null;
	}

	@Override
	public boolean hasPower() {
		return energy > 0;
	}

	@Override
	public void setEnergy(double coun) {
		this.energy = coun;
	}
	
}
