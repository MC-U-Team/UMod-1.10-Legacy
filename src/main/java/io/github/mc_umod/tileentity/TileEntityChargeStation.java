package io.github.mc_umod.tileentity;

import io.github.mc_umod.*;
import io.github.mc_umod.api.energy.*;
import io.github.mc_umod.gui.container.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class TileEntityChargeStation extends TileEntityBase implements IPowerProvieder, ITickable {
	
	private ItemStack stack = null;
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] { 0 };
	}
	
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if (direction.equals(EnumFacing.UP)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (direction.equals(EnumFacing.DOWN)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}
	
	@Override
	public void setEnergy(double coun) {
		stored = coun;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return stack;
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.stack != null) {
			ItemStack itemstack;
			
			if (this.stack.stackSize <= count) {
				itemstack = this.stack;
				this.stack = null;
				return itemstack;
			} else {
				itemstack = this.stack.splitStack(count);
				
				if (this.stack.stackSize == 0) {
					this.stack = null;
				}
				
				return itemstack;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.stack = stack;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (stack.isItemEqual(new ItemStack(UItems.battery))) {
			return true;
		}
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
		stack = null;
	}
	
	@Override
	public String getName() {
		return "tile.entity.chargstation";
	}
	
	public boolean getMode() {
		return mode;
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer pl) {
		return new ContainerChargeStation(pl, pos);
	}
	
	@Override
	public String getGuiID() {
		return null;
	}
	
	private double stored;
	public static final double MAXIMAL_POWER = 50000;
	private boolean mode = false;
	
	@Override
	public void update() {
		// if (stack != null && stack.getItemDamage() > 0 && this.canAddPower(pos, 2) && mode) {
		// stack.setItemDamage(stack.getItemDamage() + 2);
		// stored += 2;
		// } else if (!mode && stack != null && stack.getItemDamage() < stack.getMaxDamage() && stored - 2 >= 0) {
		// stored -= 2;
		// stack.setItemDamage(stack.getItemDamage() - 2);
		// }
	}
	
	public void setMode(boolean m) {
		mode = m;
	}
	
	@Override
	public double getStoredPower() {
		return stored;
	}
	
	@Override
	public void addPower(double power) {
		stored += power;
	}
	
	@Override
	public double getPower(double powerneed) {
		stored -= powerneed;
		return powerneed;
	}
	
	@Override
	public double getMaximalPower() {
		return MAXIMAL_POWER;
	}
	
	@Override
	public boolean isWorking() {
		return (stack != null) && stack.getItemDamage() < stack.getMaxDamage();
	}
	
	@Override
	public String getErrorMessage() {
		return null;
	}
	
	@Override
	public boolean hasPower() {
		return stored > 0;
	}
	
	@Override
	public double getIOPower() {
		return 2;
	}
	
	@Override
	public boolean needsPower() {
		return false;
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
		ItemStack s = stack;
		stack = null;
		return s;
	}
	
}
