package io.github.mc_umod;

import net.minecraft.client.*;
import net.minecraft.creativetab.*;
import net.minecraft.item.*;

public class UTab extends CreativeTabs {
	
	public UTab() {
		super("utab");
	}
	
	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(UBlocks.pulver);
	}
	
}
