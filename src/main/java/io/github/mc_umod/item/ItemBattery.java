package io.github.mc_umod.item;

import io.github.mc_umod.UReference;

public class ItemBattery extends ItemBase {
	
	public ItemBattery() {
		this.setMaxDamage(500);
		this.setMaxStackSize(1);
		setCreativeTab(UReference.maschines);
	}
	
}
