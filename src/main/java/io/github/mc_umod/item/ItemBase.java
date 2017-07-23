package io.github.mc_umod.item;

import io.github.mc_umod.UReference;
import net.minecraft.item.Item;

public abstract class ItemBase extends Item {
	
	public ItemBase() {
		this.setCreativeTab(UReference.things);
	}
		
}
