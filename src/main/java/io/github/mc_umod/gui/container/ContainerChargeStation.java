package io.github.mc_umod.gui.container;

import io.github.mc_umod.gui.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;

public class ContainerChargeStation extends ContainerBase {
	
	public ContainerChargeStation(EntityPlayer pl, BlockPos pos) {
		super(pl, pos);
		super.addSlotToContainer(new BaseBatteryInputSlot((IInventory) this.tile, 0, 80, 30));
		
		int i = 0;
		int v = 9;
		int j = 0;
		
		for (i = 0; i < 3; ++i) {
			for (j = 0; j < 9; ++j) {
				super.addSlotToContainer(new BaseNormalSlot(pl.inventory, (j + (i * 9)) + v, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for (i = 0; i < 9; ++i) {
			super.addSlotToContainer(new BaseNormalSlot(pl.inventory, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (index == 36) {
				if (!this.mergeItemStack(itemstack1, 0, 36, true)) {
					return null;
				}
				
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (((Slot) inventorySlots.get(0)).isItemValid(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 36, 37, false)) {
						return null;
					}
				} else if (index >= 0 && index < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 37, false)) {
						return null;
					}
				} else if (index >= 30 && index < 37 && !this.mergeItemStack(itemstack1, 0, 30, false)) {
					return null;
				}
			}
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
			
			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
			
			slot.onPickupFromSlot(playerIn, itemstack1);
		}
		
		return itemstack;
	}
	
}
