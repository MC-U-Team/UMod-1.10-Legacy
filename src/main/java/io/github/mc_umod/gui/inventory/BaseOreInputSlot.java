package io.github.mc_umod.gui.inventory;

import io.github.mc_umod.api.render.StringMethod;
import io.github.mc_umod.block.BlockOres;
import io.github.mc_umod.util.RGBA;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class BaseOreInputSlot extends BaseSlot {
	
	public BaseOreInputSlot(IInventory to, int index, int xPosition, int yPosition) {
		super(to, index, xPosition, yPosition);
		RGBA n = new RGBA(0, 255, 0, 25);
		RGBA e = new RGBA(0, 255, 0, 255);
		this.setHoverColor(n, n, e, e);
		this.setStringRet(new StringMethod() {
			
			@Override
			public String getString() {
				return "Ore Inputslot\nPut a Modore in";
			}
		});
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		Block bl = Block.getBlockFromItem(stack.getItem());
		if (bl != null) {
			if (bl instanceof BlockOres) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public void onSlotChanged() {
		super.onSlotChanged();
		this.inventory.markDirty();
	}
	
	@Override
	public int getFontColor() {
		return 0xFFFFFF;
	}
	
}
