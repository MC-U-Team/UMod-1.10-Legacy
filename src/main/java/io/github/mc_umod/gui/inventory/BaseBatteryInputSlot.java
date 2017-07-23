package io.github.mc_umod.gui.inventory;

import io.github.mc_umod.UItems;
import io.github.mc_umod.api.render.StringMethod;
import io.github.mc_umod.util.RGBA;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class BaseBatteryInputSlot extends BaseSlot {
	
	public BaseBatteryInputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		RGBA back = new RGBA(255, 0, 0, 255);
		RGBA nback = new RGBA(255, 0, 0, 25);
		this.setStringRet(new StringMethod() {
			
			@Override
			public String getString() {
				return "Batteryslot\nPut a Battery in to Load the Maschine";
			}
		});
		this.setHoverColor(nback, nback, back, back);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.isItemEqual(new ItemStack(UItems.battery));
	}
	
}
