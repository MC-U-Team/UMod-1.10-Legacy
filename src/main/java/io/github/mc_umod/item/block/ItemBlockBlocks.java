package io.github.mc_umod.item.block;

import io.github.mc_umod.enumtype.*;
import net.minecraft.block.*;
import net.minecraft.item.*;

public class ItemBlockBlocks extends ItemBlockSubBase {
	
	public ItemBlockBlocks(Block block) {
		super(block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(stack.getMetadata());
		return "tile.block" + type.getName();
	}
	
}
