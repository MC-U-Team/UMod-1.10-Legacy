package io.github.mc_umod.gui.inventory;

import io.github.mc_umod.*;
import io.github.mc_umod.api.render.*;
import io.github.mc_umod.corelib.util.*;
import io.github.mc_umod.utils.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;

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
