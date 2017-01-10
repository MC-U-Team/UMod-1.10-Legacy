package io.github.mc_umod.item.block;

import io.github.mc_umod.block.*;
import io.github.mc_umod.enumtype.*;
import net.minecraft.block.*;
import net.minecraft.item.*;

public class ItemBlockOres extends ItemBlockSubBase {
	
	public ItemBlockOres(Block block) {
		super(block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(stack.getMetadata());
		if (block instanceof BlockOres) {
			return "tile.ore" + type.getName();
		} else if (block instanceof BlockNetherOres) {
			return "tile.netherore" + type.getName();
		} else {
			return "";
		}
	}
	
}
