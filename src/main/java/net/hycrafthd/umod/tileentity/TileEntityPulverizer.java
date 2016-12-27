package net.hycrafthd.umod.tileentity;

import java.util.ArrayList;

import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.api.render.*;
import net.hycrafthd.umod.block.BlockOres;
import net.hycrafthd.umod.gui.container.ContainerPulverizer;
import net.hycrafthd.umod.utils.*;
import net.minecraft.block.Block;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.*;

public class TileEntityPulverizer extends TileEntityBase implements IPowerProvieder, ITickable, IIOMode, ISidedInventory, IWorldView {
	
	private ItemStack[] stack = new ItemStack[5];
	private double energy;
	
	@Override
	public int getSizeInventory() {
		return stack.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return stack[index];
	}
	
	@Override
	public void setEnergy(double coun) {
		energy = coun;
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
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.stack[index] = stack;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		switch (index) {
		case 0:
			return false;
		case 1:
			return false;
		case 2:
			return false;
		case 3:
			Block in = Block.getBlockFromItem(stack.getItem());
			if (in != null) {
				if (in instanceof BlockOres) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
		
	@Override
	public void setField(int id, int value) {}
	
	@Override
	public int getField(int id) {return 0;}
	
	@Override
	public int getFieldCount() {return 0;}
	
	@Override
	public void clear() {
		for (int i = 0; i < this.stack.length; ++i) {
			this.stack[i] = null;
		}
	}
	
	@Override
	public String getName() {
		return "tile.entity.Pulveriser";
	}
	
	private int time = 0;
	public boolean work = false;
	
	@Override
	public void update() {
		if(!worldObj.isRemote)return;
		ItemStack[] args = ModRegistryUtils.isRecipe(stack[3]);
		if (args != null && this.energy > 10) {
			if (isAddebal(0, args[0]) && isAddebal(1, args[1]) && isAddebal(2, args[2])) {
				work = true;
				if (time >= 100) {
					time = 0;
					this.decrStackSize(3, 1);
					if (stack[0] == null) {
						stack[0] = new ItemStack(args[0].getItem());
					} else {
						stack[0].stackSize++;
					}
					if (stack[1] == null) {
						stack[1] = new ItemStack(args[1].getItem());
					} else {
						stack[1].stackSize++;
					}
					if (args[2] != null) {
						if (stack[2] == null) {
							stack[2] = new ItemStack(args[2].getItem());
						} else {
							stack[2].stackSize++;
						}
					}
					this.getPower(1000);
				}
				time++;
			} else {
				time = 0;
				work = false;
			}
		} else {
			time = 0;
			work = false;
		}
	}
	
	public int getTime() {
		return time;
	}
	
	public boolean isAddebal(int i, ItemStack st) {
		return (stack[i] == null || (stack[i].stackSize < 64 && stack[i].getItem().equals(st.getItem())));
	}
	
	public static final String
	FACING_LIST = "face_list", INT_ENERGY = "Energy", SHORT_TIME = "Time", BYTE_SLOTS = "slot", LIST_ITEMS = "items", STRING_PLAYER = "play";
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		if (getModeFromFacing(side) == 1) {
			return new int[] { 3 };
		} else if (getModeFromFacing(side) == 0) {
			return new int[] { 0, 1, 2 };
		}
		return new int[] {};
	}
	
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if (getModeFromFacing(direction) == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (index < 3) {
			return true;
		}
		return false;
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerPulverizer(playerIn, this.pos);
	}
	
	@Override
	public String getGuiID() {
		return "0";
	}
	
	@Override
	public double getPowerProducNeeds() {
		return 10;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger(SHORT_TIME, time);
		NBTTagList facelist = new NBTTagList();
		for(int mode : modes){
			NBTTagInt inTag = new NBTTagInt(mode);
			facelist.appendTag(inTag);
		}
		tag.setTag(FACING_LIST, facelist);
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
		super.readFromNBT(tag);
		time = tag.getInteger(SHORT_TIME);
		NBTTagList facelist = tag.getTagList(FACING_LIST, 3);
		for (int i = 0; i < facelist.tagCount(); ++i) {
			NBTTagInt tagss = (NBTTagInt) facelist.get(i);
			modes[i] = tagss.getInt();
		}
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
		return 4000;
	}
	
	@Override
	public boolean isWorking() {
		return work;
	}
	
	@Override
	public String getErrorMessage() {
		return "";
	}
	
	@Override
	public boolean hasPower() {
		return false;
	}
	
	@Override
	public boolean showPower() {
		return true;
	}
	
	@Override
	public String[] textToAdd() {
		if (this.getStackInSlot(3) != null) {
			return new String[] { this.getStackInSlot(3).getDisplayName(), "Progress " + this.time + "%" };
		}
		return null;
	}

	int[] modes = new int[]{-1,-1,-1,-1,-1,-1};
	
	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public EnumFacing[] getFacingFromMode(int mode) {
		ArrayList<EnumFacing> faces = new ArrayList<EnumFacing>();
		int z = 0;
		for(int i : modes){
			if(i == mode){
				faces.add(EnumFacing.values()[z]);
			}
			z++;
		}
		return new Transformer<EnumFacing>(faces).transforme();
	}

	@Override
	public int getModeFromFacing(EnumFacing face) {
		return modes[getPlace(face)];
	}

	@Override
	public void setModeToFace(EnumFacing face, int mode) {
		modes[getPlace(face)] = mode;
	}

	@Override
	public boolean hasModeForFaceing(EnumFacing face) {
		return getModeFromFacing(face) > -1;
	}

	@Override
	public int getModeCount() {
		return 2;
	}
	
	private int getPlace(EnumFacing face){
		int i = 0;
		for(EnumFacing fc : EnumFacing.VALUES){
			if(fc.equals(face))return i;
			i++;
		}
		return 0;
	}
}
