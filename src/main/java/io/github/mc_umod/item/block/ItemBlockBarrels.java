package io.github.mc_umod.item.block;

import io.github.mc_umod.enumtype.*;
import net.minecraft.block.*;
import net.minecraft.item.*;

public class ItemBlockBarrels extends ItemBlockSubBase {
	
	public ItemBlockBarrels(Block block) {
		super(block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeBarrels type = EnumTypeBarrels.byID(stack.getMetadata());
		return "tile.barrels" + type.getName();
	}
	
}
