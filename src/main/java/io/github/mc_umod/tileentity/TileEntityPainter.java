package io.github.mc_umod.tileentity;

import io.github.mc_umod.UMod;
import io.github.mc_umod.api.render.*;
import io.github.mc_umod.item.ItemBackPack;
import io.github.mc_umod.utils.DirectionUtils;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.*;

public class TileEntityPainter extends TileEntityBase implements ITickable, ISliderEntry, IWorldView {
	
	private ItemStack[] stack = new ItemStack[6];
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		switch (side) {
		case DOWN:
			return new int[] { 2, 4 };
		case UP:
			return new int[] { 0, 1, 3 };
		}
		return new int[] {};
	}
	
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if (index == 0 || index == 1 || index == 3) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (index == 2 || index == 4) {
			return true;
		}
		return false;
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
		this.markDirty();
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index != 2 && index != 4) {
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
		stack = new ItemStack[getSizeInventory()];
	}
	
	@Override
	public String getName() {
		return "tilepainter";
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return null;
	}
	
	@Override
	public String getGuiID() {
		return "6";
	}
	
	public static final String
	
	ENUMFACING_OUTPUT = "OP", ENUMFACING_INPUT = "IP", INT_ENERGY = "Energy", SHORT_TIME = "Time", BYTE_SLOTS = "slot", LIST_ITEMS = "items", STRING_PLAYER = "play";
	
	private EnumFacing enumfI;
	private EnumFacing enumfO;
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		UMod.log.info("Write IO");
		tag.setByte(ENUMFACING_OUTPUT, (byte) DirectionUtils.getShortFromFacing(enumfO));
		tag.setByte(ENUMFACING_INPUT, (byte) DirectionUtils.getShortFromFacing(enumfI));
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < stack.length; ++i) {
			if (stack[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte(BYTE_SLOTS, (byte) i);
				stack[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		tag.setTag(LIST_ITEMS, nbttaglist);
		return tag;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		enumfI = DirectionUtils.getFacingFromShort(tag.getShort(ENUMFACING_INPUT));
		enumfO = DirectionUtils.getFacingFromShort(tag.getShort(ENUMFACING_OUTPUT));
		NBTTagList nbttaglist = tag.getTagList(LIST_ITEMS, 10);
		stack = new ItemStack[this.getSizeInventory()];
		
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int b0 = nbttagcompound1.getByte(BYTE_SLOTS);
			
			if (b0 >= 0 && b0 < stack.length) {
				stack[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}
	
	@Override
	public void update() {
		if (this.getStackInSlot(3) != null) {
			if (this.getStackInSlot(3).getItem() instanceof ItemBackPack) {
//				ColorUtils.setColor(this.getStackInSlot(3), new RGBA(this.getValueFromId(0), this.getValueFromId(1), this.getValueFromId(2), this.getValueFromId(3)).toAWTColor().getRGB());
			}
			// if(Block.getBlockFromItem(((Slot)this.inventorySlots.get(3)).getStack().getItem()) != null && Block.getBlockFromItem(((Slot)this.inventorySlots.get(3)).getStack().getItem()) instanceof BlockConduit){}
		}
	}
	
	public int[] ids = { 0, 0, 0, 100 };
	
	@Override
	public void storeValueForId(int id, int vl) {
		ids[id] = vl;
	}
	
	@Override
	public int getValueFromId(int id) {
		return ids[id];
	}
	
	@Override
	public boolean showPower() {
		return true;
	}
	
	@Override
	public String[] textToAdd() {
		return null;
	}
	
}
