package io.github.mc_umod.tileentity;

import io.github.mc_umod.UItems;
import io.github.mc_umod.gui.container.ContainerChargeStation;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;

public class TileEntityChargeStation extends TileEntityBase implements ITickable {
	
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
	public ItemStack getStackInSlot(int index) {
		return stack;
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(new ItemStack[] {stack}, index, count);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(new ItemStack[] {stack}, index);
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
		
	}
	
	public void setMode(boolean m) {
		mode = m;
	}
	
}
