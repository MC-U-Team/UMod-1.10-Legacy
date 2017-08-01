package io.github.mc_umod.tileentity;

import io.github.mc_umod.api.energy.*;
import io.github.mc_umod.gui.container.ContainerCraftFurnace;
import io.github.mc_umod.util.ModRegistryUtils;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;

public class TileEntityCraftFurnance extends TileEntityBase implements ITickable, IEnergyProvider {
	
	private ItemStack[] stack = new ItemStack[11];
	private Energy energy = new Energy(800, false, true);
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] {};
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
		return ItemStackHelper.getAndSplit(stack, index, count);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(stack, index);
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
		//TODO ADD RUN
	}

	@Override
	public Energy getEnergy() {
		return this.energy;
	}	
}
