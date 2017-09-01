package io.github.mc_umod.item.block;

import io.github.mc_umod.block.*;
import io.github.mc_umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockOres extends ItemBlockSubBase {
	
	public ItemBlockOres(Block block) {
		super(block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(stack.getMetadata());
		System.out.println(type.name());
		if (block instanceof BlockOres) {
			return "tile.ore" + type.getName();
		} else if (block instanceof BlockNetherOres) {
			return "tile.netherore" + type.getName();
		} else {
			return "";
		}
	}
	
}
