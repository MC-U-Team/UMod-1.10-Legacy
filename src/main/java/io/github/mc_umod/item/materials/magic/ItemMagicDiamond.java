package io.github.mc_umod.item.materials.magic;

import io.github.mc_umod.api.*;

public class ItemMagicDiamond extends Crystal {
	
	public ItemMagicDiamond() {
		this.setMaxStackSize(1);
	}
	
	@Override
	public int energyUnits() {
		return 1;
	}
	
}
